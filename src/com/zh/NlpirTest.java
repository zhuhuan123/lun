package com.zh;

import java.io.UnsupportedEncodingException;

import utils.SystemParas;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
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
		System.out.println("牡丹江新材料科技股份有限公司和上虞金地置业有限公司".length());
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return;
		}

		String sInput = "中国服装股份有限公司";
//		String sInput = "在吸收合并过程中，公司拟出资 118700美元的价格收购美国帝财股份有限公司持有帝财公司49%的股权； 1,301,205.00  ";
//		CLibrary.Instance.NLPIR_AddUserWord("当代投资	n");
//		CLibrary.Instance.NLPIR_AddUserWord("实际控制人	n");
		
		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("分词结果为： " + nativeBytes);
			
//			CLibrary.Instance.NLPIR_AddUserWord("要求美方加强对输 n");
//			CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("增加用户词典后分词结果为： " + nativeBytes);
//			
//			CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("删除用户词典后分词结果为： " + nativeBytes);
			
			String temp = nativeBytes;
			
			int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);
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
							System.out.println("人名有 "+ff);
						}
					}
//					System.out.println();
				}
				
			}

//			nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10,false);
//			System.out.println("这里执行了吗？");
//			System.out.print("关键词提取结果是：" + nativeByte);

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
	//苹果信息一直由北京中瀚海联资产管理有限公司100%控股，中瀚海联认缴出资1380万元。
	static void getCom(String string){
		String sss[] = string.split("/wd");
		for(String s:sss){
			int ns = s.indexOf("/ns");//172
			int i = s.indexOf("公司");//102
			if(ns>0 && i>0){
				if(i>ns){
					String tem = s.substring(ns-2, i+2);
					System.out.println("公司的名称为  : "+cl(tem));
				}else{
					String te = s.substring(i-2, ns+2);
					System.out.println("公司的名称为  : "+cl(te));
				}
			}
		}
//		System.out.println("公司的下标为 "+i);
	}
}
