package com.zh;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;


public class ReadFromStopWords {
	public static  HashSet<String> stopWords = new HashSet<String>();
	static {
		try{
//			InputStream inputStream =  ReadFromStopWords.class.getClassLoader()
//					.getResourceAsStream('/'+"Chinese-StopWords.txt");
//			File file = new File(ClassLoader.getResource("config/config.propertis").getFile());
			String path = ReadFromStopWords.class.getResource("/").toString();
			path = path.replaceAll("file:/", "")+"stopWords.txt";
			
//			System.out.println(path);
			
			FileInputStream inputStream = new FileInputStream(
					new File(path));
			if(inputStream!=null){
				BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(inputStream));
				String line = bufferedReader.readLine();
				while(line!=null){
					stopWords.add(line);
					line = bufferedReader.readLine();
				}
//				System.out.println(stopWords);
			}else{
				System.out.println("文件不存在");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static Properties config = null;
	static {
		InputStream in = null;
		try {
			File config_file_path = new File("zhong.properties");
			if (config_file_path.exists()) {
				in = new FileInputStream(config_file_path);
			} else {
				in = ReadFromStopWords.class.getClassLoader().getResourceAsStream(
						"zhong.properties");
			}
		} catch (FileNotFoundException e1) {
			System.out.println("config file not found nlpir.properties!");
		}
		config = new Properties();
		try {
			if(in==null){
				System.out.println("config file not found nlpir.properties!");
			}else {
				config.load(in);
				in.close();				
			}
		} catch (IOException e) {
			System.out.println("No nlpir.properties defined error");
		}
	}

	// 根据key读取value
	public static String getValue(String key) {
		// Properties props = new Properties();
		try {
			String value = config.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("ConfigInfoError" + e.toString());
			return null;
		}
	}

	public static void main(String args[]) {
		System.out.println(getValue("path"));
	}

	public static void addNewParam(String key, String value) {
		// TODO Auto-generated method stub
		 Properties prop = new Properties();  
		  try {
			  OutputStream fos = new FileOutputStream("nlpir1.properties");   
			  prop.setProperty(key, value);   
			  prop.store(fos, "Update '" + "' value");   
			  prop.store(fos, "just for test");   
			  
			  fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}
