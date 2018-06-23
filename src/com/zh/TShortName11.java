package com.zh;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TShortName11 {
	WebClient webclient = new WebClient();
	HtmlPage htmlPage;
	HtmlInput inputs;
	HtmlInput btn;
	public TShortName11() {
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
			//3.调用getPage()来获得入口网页（百度首页“百度一下，你就知道”）
		try {
			htmlPage = webclient.getPage("https://www.baidu.com/");
//			HtmlHiddenInput hiddenInput = 
			inputs = (HtmlInput) htmlPage.getElementById("kw");
			//5.获得表单接口的提交按钮
			btn = (HtmlInput) htmlPage.getElementById("su");
//			System.out.println(htmlPage);
			
		} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String search = "天津滨海天使创业投资有限公司";
//		String search = "\"天津滨海天使创业投资有限公司\"";
		
		String [] strings = new String[2];
		strings[0]= search;
		strings[1] = "\""+search+"\"";
		for(int i=0;i<strings.length;i++){
			System.out.println("要找的公司    "+strings[i]);
			String result =  getString(strings[i]);
//			MaiFuncionDeal11.deal(result,strings[i]);
		}
//		String result = "概要情况天津红日药业股份有限公司拟与英飞尼迪资本管理有限公司(以下简称“英飞尼迪”)、天津滨海天使创业投资有限公司(以下简称“滨海天使创投”)共同投资设立天以...";
//		MaiFuncionDeal11.deal(result,search);
//		System.out.println(htmlPage.asText());
	}
	private static String getString(String sea) {
		StringBuilder stringBuilder = new StringBuilder();
		TShortName11 tShortName = new TShortName11();
		//<em>广州凯恒企业集团有限公司</em>,中国 广东 广州市 广州市广汕路长安148号凯恒科技园,13802694590,13802694590,如需购买请联系我们<em>广州凯恒企业集团有限公司</em>,是我们公司的主...
		HtmlPage htmlPage = tShortName.getPageOfSearch(sea+"  简称");
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
		stringBuilder.append(domNode.asText());
		stringBuilder.append(",");
//		System.out.println("domNode.asText()   "+domNode.asText());
		for(int i=1;i<=10;i++){
			   DomElement domElement =  htmlPage.getHtmlElementById(i+"");
			   traves(domElement,i);
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
					 System.out.println(i+"   :  "+reString);
				 }
				 traves(domElement2, i);
			}
		}
	}
	
	//获取查询关键字的结果界面
	private HtmlPage getPageOfSearch(String wordsString){  
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
}
