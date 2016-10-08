package com.four.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnection {
	//数据相关参数
		//用户名
	private static String user = "root";
	//密码
	private static String password = "root";
	//JDBC驱动
	private static String driver = "com.mysql.jdbc.Driver";
	//数据库url
	private static String url = "jdbc:mysql://localhost:3306/ex";
	
	private static Connection conn = null;
	
	
	//静态数据块加载驱动
	static{
		try{
			//加载驱动
			Class.forName(driver);
			//获得数据库连接
			conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return conn;
	}
	
	/*public static void main(String[] args){
		//声明语句
		Statement stmt = null;
		//结果集
		ResultSet rs = null;
		//SQL语句
		String sql = "select * from user";
		
		try{
			//获取数据库连接
			Connection conn = DBConnection.getConnection();
			//获得活动声明
			stmt = conn.createStatement();
			//获得查询结果集
		    rs = stmt.executeQuery(sql);
		    System.out.println("cool");
		    //编辑结果集
		    while(rs.next()){
		    	System.out.println("username ="+rs.getString("username")+",password="+rs.getString("password"));
		    }
		    conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
}
