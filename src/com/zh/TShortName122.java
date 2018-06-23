package com.zh;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.apache.http.params.HttpAbstractParamBean;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class TShortName122 {
	WebClient webclient = new WebClient();
	HtmlPage htmlPage;
	HtmlInput inputs;
	HtmlInput btn;
	static String wanvalue = "5";
	public TShortName122() {
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
		try {
			htmlPage = webclient.getPage("https://www.ncbi.nlm.nih.gov/pubmed/?term=placental+growth+oxygen");
			System.out.println(htmlPage.asXml());
			
//			HtmlForm form = htmlPage.getFormByName("f");
//			if(form==null){
//				return ;
//			}
//			HtmlInput htmlInput = form.getInputByName("rn");
//			
//			htmlInput.setDefaultValue(wanvalue);
////			DomElement domElement = htmlPage.getElementByName("rn");
////			HtmlSelect select = form.getE//getEByName("rn");
////			select.setDefaultValue("20");
//			
//			inputs = (HtmlInput) htmlPage.getElementById("kw");
//			//5.获得表单接口的提交按钮
//			btn = (HtmlInput) htmlPage.getElementById("su");
//			System.out.println(htmlPage);
			
		} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public static void Search(String search){
		String [] strings = new String[2];
		strings[0]= search;
//		strings[1] = "\""+search+"\"";
//		System.out.print(search+"|");
		
//		String result =  getString(search);
//		MaiFuncionDeal11.deal(result,search);
		
		HashSet<String> result = new HashSet<String>();
		
//		for(int i=0;i<strings.length;i++){
//			String res =  getString(strings[i]);
//			HashSet<String> reSet = MaiFuncionDeal11.deal(res,strings[i]);
//			result.addAll(reSet);
//		}
//		
//		for(String reString:result){
//			System.out.println(reString);
//		}
//		if(result!=null && result.size()>0){
//			result.clear();
//		}
		
//		System.out.println();
	}
	public static void main(String[] args) {
		String search = "浙江中国小商品城集团股份有限公司";
		
//		String search = "\"天津滨海天使创业投资有限公司\"";
		
		TShortName122 tShortName122 = new TShortName122();
//		Search(search);
//		String [] strings = new String[2];
//		strings[0]= search;
//		strings[1] = "\""+search+"\"";
//		for(int i=0;i<strings.length;i++){
//			System.out.println("要找的公司    "+strings[i]);
//			String result =  getString(strings[i]);
//			MaiFuncionDeal11.deal(result,strings[i]);
//		}
		
		
		
//		String result = "概要情况天津红日药业股份有限公司拟与英飞尼迪资本管理有限公司(以下简称“英飞尼迪”)、天津滨海天使创业投资有限公司(以下简称“滨海天使创投”)共同投资设立天以...";
//		MaiFuncionDeal11.deal(result,search);
//		System.out.println(htmlPage.asText());
	}
	/**获取得到网页的内容  然后进行处理
	 * @param sea
	 * @return
	 */
	public static String getString(String sea) {
		StringBuilder stringBuilder = new StringBuilder();
		TShortName122 tShortName = new TShortName122();
		//<em>广州凯恒企业集团有限公司</em>,中国 广东 广州市 广州市广汕路长安148号凯恒科技园,13802694590,13802694590,如需购买请联系我们<em>广州凯恒企业集团有限公司</em>,是我们公司的主...
		HtmlPage htmlPage = tShortName.getPageOfSearch(sea+"  简称");
		if(htmlPage==null){
			return "";
		}
//		<div class="c-abstract">慧聪网(Hc360.Com)供应商<em>上海棱光实业股份有限公司</em>,地址:中国.上海.虹口区上海市龙吴路4900号,主营产品:,联系方式:86-021-51161618,欢迎联系洽谈!360行,行行在...</div>
//		DomNode domNode = htmlPage.querySelector("div class=\"c-abstract\"");
//		System.out.println(domNode.asXml());
		List<DomNode> domNodes = htmlPage.querySelectorAll("[class=c-abstract]");
		for(DomNode domNode:domNodes){
			stringBuilder.append(domNode.asText());
			stringBuilder.append(",");
//			System.out.println("domNode.asText()   "+domNode.asText());
		}
		DomNode domNode = htmlPage.querySelector("[class=c-abstract]");
		if(domNode==null){
			return stringBuilder.toString();
		}
		stringBuilder.append(domNode.asText());
		stringBuilder.append(",");
//		System.out.println("domNode.asText()   "+domNode.asText());
		int va = Integer.parseInt(wanvalue);
		for(int i=1;i<=va;i++){
				try{
					DomElement domElement =  htmlPage.getHtmlElementById(i+"");
					if(domNode==null){
						return stringBuilder.toString();
					}
					traves(domElement,i);
				}catch(Exception exception){
					exception.printStackTrace();
					break;
				}
		}
		return stringBuilder.toString();
	}
	
	private static void traves(DomElement domElement,int i){
		if(domElement!=null){
//			   String s = domElement.asText();
			Iterable<DomElement> childs = domElement.getChildElements();
			for (DomElement domElement2:childs){
				 String claName = domElement2.getAttribute("class");
	//				   System.out.println("claName  "+claName);
				 if(claName.equalsIgnoreCase("c-abstract")){
					 String reString = domElement2.asText();
//					 System.out.println(i+"   :  "+reString);
				 }
				 traves(domElement2, i);
			}
		}
	}
	
	//获取查询关键字的结果界面
	private HtmlPage getPageOfSearch(String wordsString){  
			//向文本框中填入关键字
		if(inputs==null){
			return null;
		}
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
}
