import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;


public class MTes {
	public static void main(String[] args) throws Exception{
		StringReader stri = new StringReader("I am a student");
//		System.out.println(stri.);
//		String str = new String(stri);
		int co =0;
		int len = 0;
		while(stri.ready()){
			System.out.println((char)stri.read());
			len++;
			co++;
			if(co==10){
				break;
			}
		}
		System.out.println("读出 终止");
		
		System.out.println(" 字符总共的长度为   "+len);
		
		
	}
}
