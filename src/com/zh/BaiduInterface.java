package com.zh;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BaiduInterface {
	
	// 1.初始化webClient，得到浏览器对象（相当于得到了一个浏览器）
	WebClient webclient = new WebClient();
	HtmlPage htmlPage;
	HtmlInput inputs;
	HtmlInput btn;
	
	//初始化
	public BaiduInterface() {
		// 2.配置不加载css和JavaScript
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
//		webclient.getOptions().
		try {
			//3.调用getPage()来获得入口网页（百度首页“百度一下，你就知道”）
			htmlPage = webclient.getPage("https://www.baidu.com/");
			//4.获得表单接口的搜索文本框n 
			inputs = (HtmlInput) htmlPage.getElementById("kw");
			//5.获得表单接口的提交按钮
			btn = (HtmlInput) htmlPage.getElementById("su");
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取查询关键字的结果界面
	HtmlPage getPageOfSearch(String wordsString){  
		//向文本框中填入关键字
		inputs.setValueAttribute(wordsString);
		HtmlPage tempPage = null;
		try { 
			//填入关键字之后，自动触发提交按钮,根据关键字跳转到下一个网页，用tempPage表示
			tempPage = btn.click(); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return tempPage;  //返回提交关键词之后获取到的网页(包含10条记录的第一页)
	}
	
	//提取结果页面（根据输入的关键字而获得的第一页）
	String filterPage(String kw) {
		HtmlPage page = getPageOfSearch(kw);//获取查询结果页面，关键字为传递过来的kw
		TreeMap<String, String> candidate = new TreeMap<String, String>();
		for (int i = 1; i <= 10; i++) {
			//HtmlDivision ，只找前面10个div块，即只找前面的10条有效信息
			DomElement divElement = page.getHtmlElementById(i+"");//定位到每一个目标块，id为1，2，3...10
			DomNode node = findCandidateNode(divElement,null);  //返回的node为获取到的h3
			if(node != null) {
				if (!isContainKeyWord(node.asText()) && 
						!candidate.containsKey(node.asText())) { //对candidate中的key值进行判断，如果有相同的就不进行put操作！！！			
					//因为通过百度搜索时，相关连接的都是在h3标签里面
					HtmlAnchor nodeA = (HtmlAnchor) node.getFirstChild();  //获取超链接节点<a>									
					candidate.put(node.asText(), nodeA.getAttribute("href"));
				}	
			}
		}			
		 
		//对存储在TreeMap类型的candidate里面的属性对进行判断分析
		Set<String> kwSet = candidate.keySet(); //获取key值
		Map<String, Double> semblance = new TreeMap<String, Double>();
		for(String str:kwSet){
			if(str.contains("官网")) {
				return candidate.get(str);
			} 				
			else{
				semblance.put(str, countSemblance(kw, str)); 
			}
		}
		System.out.println("每一条的文本内容相似度为： " + semblance);
		if(kw != null){
			System.out.println("最相似的记录为：" + biggestSemblance(semblance));
			return candidate.get(biggestSemblance(semblance));     //这里需要判断一下，获取前面的记录
		}
		return null;
	}
	
	//获得每个h3块的目标节点
	DomNode findCandidateNode(DomNode divNode,String textString) {
		DomNodeList<DomNode> childNode1s = divNode.getChildNodes();
		DomNode aimNode = null;
		for (int j = 0; j < childNode1s.size(); j++) {
			if(!childNode1s.get(j).asText().equals("") ||
					childNode1s.get(j).asText().length() > 0){//子节点必须包含文本，防止style等的出现
				if(textString == null){
					textString = childNode1s.get(j).asText();  //获得子节点的文本内容，
													//若textString不为空就不赋值，在下一个if里面进行比较
					//System.out.println("测试asText内容：" + textString);
					//System.out.println("测试asXml内容" + childNode1s.get(j).asXml());
				}					
			} else {
				//子节点没有文本内容（“**大学图书馆在标签内属于文本节点”），继续下一个孩子节点的判断
				continue;
			}
				
			if((childNode1s.get(j).asText().equals(textString) ||  
					childNode1s.get(j).asText().length() > 0) && 
					childNode1s.get(j).asXml().startsWith("<")){  				
				aimNode = childNode1s.get(j);  //找到目标节点h3
				break;
			} else {
				if(aimNode == null) {
					while(!divNode.asXml().contains("href")){
						divNode = divNode.getParentNode();
					}
					return divNode.getParentNode();		
				}	
			}
		}
		if(aimNode!=null)
			aimNode = findCandidateNode(aimNode,textString);		
		return aimNode;
		
	}
	
	//获取与关键字相似度最大的那条记录的文本内容
	String biggestSemblance(Map<String, Double> semblances){
		String aimString = null;
		double sbalance = 0.00;
		Set<String> kwSet = semblances.keySet();
		for(String str:kwSet){
			if(sbalance < semblances.get(str)){   //取相似度最大的那个str
				//注意问题：若相似度相同，取排在前面的那一条记录
				aimString = str;
				sbalance = semblances.get(str);
			}                        
		}		
		return aimString;		
	}
	
	//计算文本相似度===即kw与获取的文本内容的相似度
	//返回的是一个相似度的小数值
	double countSemblance(String kw, String objects){
		double counter = 0.0;
		for (int i = 0; i < kw.length(); i++) {
			//逐字进行比较
			String childStr = kw.substring(i, i+1);  
			if(objects.contains(childStr))
				counter++;
		}		
		return (counter/objects.length());
		
	}
	
	//判断目标块里是否包含敏感词，如果有说明目标块并不是所需要的，可以忽略
	boolean isContainKeyWord(String name){
		String []keyWord = {"百度","百度百科","新闻","百度知道","百度地图","博客","文库"};
		for (int i = 0; i < keyWord.length; i++) {
			if(name.contains(keyWord[i])){
				return true;
			}	
		}
		return false;
	}
	
	//获取最终首页
	String getAimPage(String url){		
		try {
			HtmlPage page = webclient.getPage(url);
			System.out.println("page.getUrl()是：" + page.getUrl());
			//System.out.println(page.asXml());
			return page.getUrl().toString();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
	//获取用户添加的信息源名称 
	public String getSourceName(String nameString,int num) {		
		return this.discoverUrl(nameString,num);		
	}
	
	public String discoverUrl(String sourceName, int number) {
		long startTime = System.currentTimeMillis();

		//BaiduInterface baidu = new BaiduInterface();
		String url = this.filterPage(sourceName);
//		String url = baidu.filterPage("暨南大学图书馆");
//		String url = baidu.filterPage("北京大学");
		//String url = baidu.filterPage("58同城");
		System.out.println("filterPage()之后的url为：" + url);
		if(url != null)
			url = this.getAimPage(url);
		if(url != null)
			System.out.println("getAimPage()之后的url为：" + url);
		System.out.println("这是接口发现部分");
		
		//通过传来的参数来调用不同领域的方法
		if (number == 1) {
			//表示图书领域，将此图书馆首页url传递到前端
			return url;			
		} else if (number == 2) {
			//表示租房领域，传递到租房领域方法中进行下一步的发现操作
			//传递aimUrl，进行进一步的发现
//			HouseSource houseSource = new HouseSource();
//			try {
//				return houseSource.getHouseUrl(url);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间为：" + (endTime - startTime));
		return "";
		}
	
//	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis();
//
//		BaiduInterface baidu = new BaiduInterface();
//		String aimUrl = null;
//
//		//String url = baidu.filterPage("暨南大学图书馆");
//		//String url = baidu.filterPage("中国航空订票");
//		
//		String url = baidu.filterPage("58同城");
//		//String url = baidu.filterPage("广州赶集网");
//		//String url = baidu.filterPage("广州安居客");
//		//String url = baidu.filterPage("满堂红");
//		//String url = baidu.filterPage("百姓网");
//		//String url = baidu.filterPage("Q房网");
//		//String url = baidu.filterPage("广州搜房网");
//		//String url = baidu.filterPage("喜客门");
//
//		//String url = baidu.filterPage("天猫网");
//		System.out.println("filterPage()之后的url为：" + url);
//		if(url != null)
//			aimUrl = baidu.getAimPage(url);
//		if(aimUrl != null)
//			System.out.println("getAimPage()之后的url为：" + aimUrl);
//		//System.out.println("这是接口发现的第一步！");
//		
//		HouseSource houseSource = new HouseSource();
//		try {
//			houseSource.getHouseUrl(aimUrl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		long endTime = System.currentTimeMillis();
//		System.out.println("程序运行时间为：" + (endTime - startTime));
//	}
}
