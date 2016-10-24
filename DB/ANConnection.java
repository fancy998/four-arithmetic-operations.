package com.four.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ANConnection {

	//数据相关参数
			//用户名
		private static String user = "root";
		//密码
		private static String password = "root";
		//JDBC驱动
		private static String driver = "com.mysql.jdbc.Driver";
		//数据库url
		private static String url = "jdbc:mysql://localhost:3306/an";
		
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
}
