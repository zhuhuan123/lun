package test;

import java.util.StringTokenizer;

enum Mon{
	MonDay,TuesDay,Third
}

public class TeJavaEnum {
	public static void main(String[] args) {
		Mon on = Mon.MonDay;
		System.out.println(on);
		StringTokenizer stringTokenizer = new StringTokenizer("this is a word", " ");
		while(stringTokenizer.hasMoreElements()){
			String res = (String) stringTokenizer.nextElement();
			System.out.println(res);
		}
//		System.out.println(stringTokenizer);
	}
}
