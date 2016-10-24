package com.four.servlet;

import com.four.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		String passwd = request.getParameter("passwd");
		String qq = request.getParameter("qq");
		
		Cookie[] cookies = request.getCookies();
		
		Cookie username = null;
		if(cookies!=null && cookies.length > 0){
			for(Cookie c : cookies){
				if(c.getName().equals("username")){
					username = c;
				}
			}
		}

		
		//声明语句
		Statement stmt = null;
		//结果集
		ResultSet rs = null;
		//SQL语句
		String sqlQuery = "select * from info where user = "+username.getValue();
		
		
		String sqlInsert = "insert into info(user,passwd,qq) values("+user+","+passwd+","+qq+")";
		
		try{
			//获取数据库连接
			Connection conn = DBConnection.getConnection();
			//获得活动声明
			stmt = conn.createStatement();
			//获得查询结果集
			rs = stmt.executeQuery(sqlQuery);
		    //插入
			if(rs == null){
				stmt.executeUpdate(sqlInsert);
			}
			else {
				response.getWriter().print("用户名已存在!</br>5秒后跳转回注册页面……</br><a href='/login/index.html'>立即跳转</a>");
				Thread.sleep(5000);
				request.getRequestDispatcher("/login/index.html").forward(request, response);
			}
		    conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
