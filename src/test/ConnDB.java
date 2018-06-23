package test;
import java.sql.*;
import java.io.*;
import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class ConnDB
{
	public Connection conn=null;
	public Statement stmt=null;
	public ResultSet rs=null;
	private static String dbDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbUrl="jdbc:sqlserver://localhost;databaseName=";
	private static String dbUser="sa";
	private static String dbPwd="123";

	//鎵撳紑鏁版嵁搴撹繛鎺�
	public static Connection getConnection(String dbName)
	{
		Connection conn=null;
		
		try
		{
			Class.forName(dbDriver);
			conn=DriverManager.getConnection(dbUrl+dbName,dbUser,dbPwd);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	if (conn == null) 
    	{
      		System.err.println("璀﹀憡:鏁版嵁搴撹繛鎺ュけ璐�!");
    	}	//else System.out.println("鏉庣惔 鏁版嵁搴撹繛鎺ユ垚鍔燂紒");
		return conn;
	}
	//璇诲彇缁撴灉闆�
	public ResultSet doQuery(String sql,String dbName)
	{
		try
		{
			conn=ConnDB.getConnection(dbName);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	//鏇存柊鏁版嵁
	public int doUpdate(String sql,String dbName)
	{
		int result=0;
		try
		{
			conn=ConnDB.getConnection(dbName);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result=stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			result=0;
		}
		return result;
	}
	//鍏抽棴鏁版嵁搴撹繛鎺�
	public void closeConnection()
	{
		try
		{
			if (rs!=null)
				rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (stmt!=null)
				stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			if (conn!=null)
				conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//杩斿洖棰勭紪璇戝璞★紝瀹夊叏銆侀�熷害蹇�
	public PreparedStatement getPSMT(String sql,String dbName){
		conn=this.getConnection(dbName);
		PreparedStatement psmt=null;
		try {
			psmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return psmt;
	}
	
	public static Connection connect(String dbName) {
		return getConnection(dbName);
	}
	
	public static void main(String[] args) {
		Connection connection  = connect("Company");
		System.out.println(connection);
	}
}