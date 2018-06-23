package com.zh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class DealUtil {
	public static String SimToString(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String sDate = simpleDateFormat.format(new java.util.Date());
		return sDate;
	}
	public static void WriteToFile(String fileName){
		FileOutputStream fileOutputStream;
		BufferedWriter bufferedWriter =null;
		try {//fileName "e:\\temp\\out.txt"
			fileOutputStream = new FileOutputStream(new File(fileName));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**从文件中读取数据
	 * @param fileName
	 * @return
	 */
	public static String reInp(String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		try{
			FileInputStream fileInputStream=new FileInputStream(new File(fileName));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,("utf-8")));
			String line = bufferedReader.readLine();
			while(line!=null){
				stringBuffer.append(line);
//				System.out.println(line);
				line = bufferedReader.readLine();
			}
		}catch(Exception e){
			return null;
		}
		return stringBuffer.toString();
	}
	
	public static String YuDeal(String content){
		if(content==null){
			return null;
		}
		content= content.replaceFirst("[ ]{9}", "，"); 
		content= content.replaceFirst("[ ]{9}", "，"); 
		content= content.replaceFirst("[ ]{9}", "，"); 
		content= content.replaceAll("\t", ""); 
		content= content.replaceAll("\0", ""); 
		content= content.replaceAll("\r", ""); 
		content= content.replaceAll("\\s", ""); 
		content= content.replaceAll("\n", "，"); 
		content= content.replaceAll("\n\n", "，"); 
		content= content.replaceAll("\\d", ""); 
		content= content.replaceAll("[A-Za-z]","");
		content= content.replaceAll("\t\n", "，"); 
		content= content.replaceAll("<", "。"); 
		content= content.replaceAll("=", "。"); 
		content= content.replaceAll(">", "。"); 
		//content= content.replaceAll("  ", ""); 
		content= content.replaceAll("[\\t\\n\\r]", ""); 
		content= content.replaceAll(" ", "。"); 
		return content;
	}
	
	/**对文章进行分句
	 * @param s
	 * @return
	 */
	public static String[] split(String s){
		if(s!=null){
			String [] strings =s.split("[。,!,，,、]");
			return strings;
		}
		return null;
	}
}
