package com.zh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sourceforge.htmlunit.cyberneko.HTMLElements.Element;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TShortName {
	WebClient webclient = new WebClient();
	HtmlPage htmlPage;
	HtmlInput inputs;
	HtmlInput btn;
	HtmlButton hbtn;
	public TShortName() throws Exception{
		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
		File f = new File("e:\\log");
		if(!f.exists()){
			f.mkdirs();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd- hh-mm-ss");
		String ste = simpleDateFormat.format(new Date());
		
		System.out.println(f.exists()+ "  "+f.getAbsolutePath() );
		
		File file = new File(f.getAbsolutePath()+"\\log"+ste+".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		
		System.out.println(file.exists());
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		PrintStream out = new PrintStream(fileOutputStream); 
		System.setOut(out);
			//3.调用getPage()来获得入口网页（百度首页“百度一下，你就知道”）
		try {
			String sear = "headache";
			//这种方法才能实现查询
			htmlPage = webclient.getPage("https://www.ncbi.nlm.nih.gov/pubmed?term="+sear);
			
			inputs = (HtmlInput) htmlPage.getElementById("term");
			inputs.setDefaultValue(sear);
			
			HtmlForm htmlForm = (HtmlForm) htmlPage.getElementById("EntrezForm");
			
			HtmlPage htm = htmlForm.click();
//			System.out.println(htm.asXml());
			List<DomElement> elements = htm.getElementsByTagName("a");
			int ii = 0;
			for(DomElement domElement:elements){
				if(domElement.hasAttribute("ref")){
					String s = domElement.getAttribute("href");
					if(s.startsWith("/pubmed")){
						HtmlPage temp = webclient.getPage("https://www.ncbi.nlm.nih.gov"+s);
						DomNode domNode = temp.querySelector(".abstr");//class="abstr"
						if(domElement!=null){
							System.out.println(ii+" "+domElement.asText());
						}else{
							System.out.println("不存在标题");
						}
						if(domNode!=null){
							System.out.println(domNode.asText());
						}else{
							System.out.println("不存在摘要");
						}
						ii++;
					}
					
				}
			}
			
			
			
			
//			hbtn = (HtmlButton)htmlPage.getElementById("search");
//			HtmlPage htmlPage1 =  hbtn.click();
////			hbtn.
//			System.out.println(htmlPage1.asXml());
			//5.获得表单接口的提交按钮
//			btn = (HtmlInput) htmlPage.getElementById("");
//			System.out.println(htmlPage);
			
		} catch (FailingHttpStatusCodeException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		TShortName tShortName = new TShortName();
		//<em>广州凯恒企业集团有限公司</em>,中国 广东 广州市 广州市广汕路长安148号凯恒科技园,13802694590,13802694590,如需购买请联系我们<em>广州凯恒企业集团有限公司</em>,是我们公司的主...
//		HtmlPage htmlPage = tShortName.getPageOfSearch("深圳市振业(集团)股份有限公司"+"  简称");
////		<div class="c-abstract">慧聪网(Hc360.Com)供应商<em>上海棱光实业股份有限公司</em>,地址:中国.上海.虹口区上海市龙吴路4900号,主营产品:,联系方式:86-021-51161618,欢迎联系洽谈!360行,行行在...</div>
////		DomNode domNode = htmlPage.querySelector("div class=\"c-abstract\"");
////		System.out.println(domNode.asXml());
//		for(int i=1;i<=10;i++){
//			   DomElement domElement =  htmlPage.getHtmlElementById(i+"");
//			   traves(domElement,i);
//		}
		
//		System.out.println(htmlPage.asText());
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
