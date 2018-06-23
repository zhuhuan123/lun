package com.zh;

import java.io.UnsupportedEncodingException;

import utils.SystemParas;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// ����ӿ�CLibrary���̳���com.sun.jna.Library
	public interface CLibrary extends Library {
		// ���岢��ʼ���ӿڵľ�̬����
		CLibrary Instance = (CLibrary) Native.loadLibrary(
				"g:\\20140928\\lib\\win64\\NLPIR", CLibrary.class);
//		CLibrary Instance = (CLibrary) Native.loadLibrary(
//				"D:\\sss\\20140928\\bin\\ICTCLAS2015\\NLPIR", CLibrary.class);
		
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);
				
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public int NLPIR_AddUserWord(String sWord);//add by qp 2008.11.10
		public int NLPIR_DelUsrWord(String sWord);//add by qp 2008.11.10
		public String NLPIR_GetLastErrorMsg();
		public void NLPIR_Exit();
	}

	public static String transString(String aidString, String ori_encoding,
			String new_encoding) {
		try {
			return new String(aidString.getBytes(ori_encoding), new_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
//		String argu = "D:\\sss\\20140928";
		String argu = "g:\\20140928";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "UTF-8";
		int charset_type = 1;
		System.out.println("ĵ�����²��ϿƼ��ɷ����޹�˾�����ݽ����ҵ���޹�˾".length());
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
			return;
		}

		String sInput = "�й���װ�ɷ����޹�˾";
//		String sInput = "�����պϲ������У���˾����� 118700��Ԫ�ļ۸��չ������۲ƹɷ����޹�˾���е۲ƹ�˾49%�Ĺ�Ȩ�� 1,301,205.00  ";
//		CLibrary.Instance.NLPIR_AddUserWord("����Ͷ��	n");
//		CLibrary.Instance.NLPIR_AddUserWord("ʵ�ʿ�����	n");
		
		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("�ִʽ��Ϊ�� " + nativeBytes);
			
//			CLibrary.Instance.NLPIR_AddUserWord("Ҫ��������ǿ���� n");
//			CLibrary.Instance.NLPIR_AddUserWord("�����׵Ĳ�����Դ n");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("�����û��ʵ��ִʽ��Ϊ�� " + nativeBytes);
//			
//			CLibrary.Instance.NLPIR_DelUsrWord("Ҫ��������ǿ����");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("ɾ���û��ʵ��ִʽ��Ϊ�� " + nativeBytes);
			
			String temp = nativeBytes;
			
			int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("�ؼ�����ȡ����ǣ�" + nativeByte);
			System.out.println();
			String res [] = temp.split("/wd");
			for(String s:res){
//				System.out.println("-------");
//				System.out.print("result "+s);
				if(s.contains("/nr")){
					int rr = s.indexOf("/nr");
//					char c = s.charAt(rr-3);
//					int re = s..lastIndexOf(" ");
					
					String ren [] = s.split("/nr");
//					System.out.println();
					int c =0;
					for(String ss:ren){
						c++;
						if(c!=ren.length){
							String ff = ss.substring((ss.lastIndexOf(" ")==-1)?0:ss.lastIndexOf(" "), ss.length());
							System.out.println("������ "+ff);
						}
					}
//					System.out.println();
				}
				
			}

//			nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\�����㳡\\2012\\5\\16766.txt", 10,false);
//			System.out.println("����ִ������");
//			System.out.print("�ؼ�����ȡ����ǣ�" + nativeByte);

			getCom(nativeBytes);

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
	static String cl(String sss){
		String aa[] =  sss.split("/[a-z]+");
		StringBuffer stringBuffer = new StringBuffer();
		for(String h:aa){
			stringBuffer.append(h.trim());
		}
//		String s = new String();
		return stringBuffer.toString();
		
	}
	//ƻ����Ϣһֱ�ɱ�����嫺����ʲ��������޹�˾100%�عɣ���嫺����Ͻɳ���1380��Ԫ��
	static void getCom(String string){
		String sss[] = string.split("/wd");
		for(String s:sss){
			int ns = s.indexOf("/ns");//172
			int i = s.indexOf("��˾");//102
			if(ns>0 && i>0){
				if(i>ns){
					String tem = s.substring(ns-2, i+2);
					System.out.println("��˾������Ϊ  : "+cl(tem));
				}else{
					String te = s.substring(i-2, ns+2);
					System.out.println("��˾������Ϊ  : "+cl(te));
				}
			}
		}
//		System.out.println("��˾���±�Ϊ "+i);
	}
}
