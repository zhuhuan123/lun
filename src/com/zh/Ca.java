package com.zh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class Ca {
	public static void main(String[] args) throws Exception{
//		Ca ca = new Ca();
//		double x0 = 2;
//		double x1 =x0;
//		while(Math.abs(x0-x1*x1)>0.00000001){
//			x1 = (x1+(x0/x1))/2;
////			x0 = x1;
//			System.out.println(x0+"  "+x1);
//		}
//		System.out.println("得到的解为   "+x1);
		InputStream input = new FileInputStream(new File("e:\\DealUtil.java"));
		OutputStream output = new FileOutputStream(new File("e:\\test.java"));
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(output);
		
	}
	double f(double x){
		return x*x;
	}
	
	double fd(double x){
		return 2*x;
	}
	
}
