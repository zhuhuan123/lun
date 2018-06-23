package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zh.NlpirTest.CLibrary;
import com.zh.ReadFromStopWords;
import com.zh.TShortName122;


public class DataAccess {
//	static{
//		String argu = "F:\\worstudy\\20150805111452_20150702124141_ICTCLAS2015 (1)\\20140928";
//		// String system_charset = "GBK";//GBK----0
//		String system_charset = "UTF-8";
//		int charset_type = 1;
//		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
//		String nativeBytes = null;
//	}
	
	
	static Connection dbConn = null;
//	 static  {
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		//这里每次都要改数据库的名称
//		String dbURL = "jdbc:sqlserver://192.168.1.156:1433;databasename=CompanyName"; // 1433是端口，"USCSecondhandMarketDB"是数据库名称
//		String userName = "sa"; // 用户名
//		String userPwd = "123"; // 密码
//
//		try {
//			Class.forName(driverName).newInstance();
//			System.out.println("cheng");
//		} catch (Exception ex) {
//			System.out.println("驱动加载失败");
//			ex.printStackTrace();
//		}
//		try {
//			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
////			System.out.println("成功连接数据库！");
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
	 
	 public static void main(String[] args) throws Exception  {
		 se122();
	 }
	 
	 static List<String> se11(){
		 List<String> agreeVSet = new ArrayList<String>();
		 String path = ReadFromStopWords.class.getResource("/").toString();
			path = path.replaceAll("file:/", "")+"nn.txt";
			BufferedReader br=null;
			try {
				String brStr=null;
				br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
				while ((brStr=br.readLine())!=null) {
					agreeVSet.add(brStr);
				}
				
//				System.out.println(agreeVSet);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(br!=null)
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return agreeVSet;
	 }
	 
	 static void se122() throws Exception{
//			te();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			String sDate = simpleDateFormat.format(new java.util.Date());
			 File file1 = new File("e:\\"+sDate+".txt");
	         try {
				PrintStream ps = new PrintStream(file1);
				System.setOut(ps);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
//			PreparedStatement preparedStatement = dbConn.prepareStatement("SELECT  [Name] FROM [CompanyName].[dbo].[TradingCompany11] where id between 100 and 300");
//			ResultSet resultSet = preparedStatement.executeQuery();
	         List<String> strings  = se11();
			for(String string :strings){
				System.out.print(string+"|");
				TShortName122.Search(string);
				System.out.println();
			}
			
//			PreparedStatement preparedStatement = dbConn.prepareStatement("SELECT  [Name] FROM [CompanyName].[dbo].[TradingCompany11] where id between 100 and 300");
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()){
//				String reString = resultSet.getString(1);
//				TShortName122.Search(reString);
//			}
		}
	
	/**read   from  公司
	 * @throws Exception
	 */
	static void se() throws Exception{
//		te();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String sDate = simpleDateFormat.format(new java.util.Date());
		 File file1 = new File("f:\\temp\\tmpusecom"+sDate+".txt");
         try {
			PrintStream ps = new PrintStream(file1);
			System.setOut(ps);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement preparedStatement = dbConn.prepareStatement("SELECT top 1000 [Id],[Name],[NameSame],[NameOther] FROM [CompanyName].[dbo].[Company]");
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			String reString = resultSet.getString(1);
			reString = CLibrary.Instance.NLPIR_ParagraphProcess(reString, 1);
			String ss[] = reString.split(" ");
			System.out.println(ss[ss.length-1]);
			
		}
	}
	static void frome() throws Exception{
//		te();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String sDate = simpleDateFormat.format(new java.util.Date());
		File file1 = new File("f:\\temp\\tmpusecom"+sDate+".txt");
		try {
			PrintStream ps = new PrintStream(file1);
			System.setOut(ps);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement preparedStatement = dbConn.prepareStatement("SELECT top 1000 [Id],[Name],[NameSame],[NameOther] FROM [CompanyName].[dbo].[Company]");
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			String reString = resultSet.getString(2);
			reString = CLibrary.Instance.NLPIR_ParagraphProcess(reString, 1);
			System.out.println(reString);
//			String ss[] = reString.split(" ");
//			System.out.println(ss[ss.length-1]);
			
		}
	}
//	private static WordType[] DeaTempString(String temp) {
//		String []allwords =temp.split(" ");
//		WordType [] wordTypes = new WordType[allwords.length];
//		int j=0;
//		for(int i=0;i<allwords.length;i++){
//			String  line = allwords[i];
//			int index = line.indexOf("/");
//			if(index!=-1){
//				wordTypes[j] =new WordType();
//				String re = line.substring(0, index);
//				String re22 = line.substring(index+1, line.length());
//				wordTypes[j].setContent(re);
//				wordTypes[j].setType(re22);
//				j++;
////				allwords[i] = re;
//			}
//		}
//		return wordTypes;
//	}
	
	/**提取一些
	 * @throws Exception
	 */
//	static void savefromCom() throws Exception{
////		te();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//		String sDate = simpleDateFormat.format(new java.util.Date());
//		File file1 = new File("f:\\temp\\get"+sDate+".txt");
//		try {
//			PrintStream ps = new PrintStream(file1);
//			System.setOut(ps);
//		} catch (FileNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		BufferedReader bufferedReader = null;
//		bufferedReader = new BufferedReader(
//				new InputStreamReader(new FileInputStream(new File("f:\\temp\\tmp2015-12-15-04-24-26.txt"))));
//		String line = bufferedReader.readLine();
//		
//		while(line!=null){
//			String reString = CLibrary.Instance.NLPIR_ParagraphProcess(line, 1);
//			WordType [] wordTypes = DeaTempString(reString);
//			for(int i=0;i<wordTypes.length;i++){
//				if(wordTypes[i].getType().equals("ns") && i>1 && i+1<wordTypes.length &&wordTypes[i+1].getType().startsWith("v")){
//					System.out.println(wordTypes[i-1].getContent());
//				}
//			}
//			line = bufferedReader.readLine();
//		}
//	}
	public static HashMap<Integer, String> getAll() throws Exception{ 
		HashMap<Integer, String> maps = new HashMap<Integer, String>();
		PreparedStatement preparedStatement = dbConn.prepareStatement("select Name from Company");
		ResultSet resultSet = preparedStatement.executeQuery();
		int count = 1;
		while(resultSet.next()){
			String ss = resultSet.getString(1);
			maps.put(count, ss);
			count++;
		}
		return maps;
		
	}
	
	public static boolean inse(String s) throws Exception{
//		te();
		PreparedStatement preparedStatement = dbConn.prepareStatement("select * from necom3 where name =?");
		preparedStatement.setString(1, s);
		PreparedStatement preparedStatement2222 = dbConn.prepareStatement("select * from Company where Name =?");
		preparedStatement2222.setString(1, s);
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSet resultSet122 = preparedStatement2222.executeQuery();
		if(!resultSet.next()&& !resultSet122.next()){
			PreparedStatement preparedStatementInsert = dbConn.prepareStatement("insert into necom3(name) values(?);");
			preparedStatementInsert.setString(1, s);
			preparedStatementInsert.executeUpdate();
			return false;
		}
		return true;
		
	}
	

}
