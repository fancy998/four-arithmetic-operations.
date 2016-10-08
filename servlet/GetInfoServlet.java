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
 * Servlet implementation class GetInfoServlet
 */
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		Cookie username = null;
		if(cookies!=null && cookies.length > 0){
			for(Cookie c : cookies){
				if(c.getName().equals("username")){
					username = c;
				}
			}
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from info where user = "+username.getValue();
		try{
			Connection conn = DBConnection.getConnection();
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	System.out.println("username ="+rs.getString("username")+",password="+rs.getString("password"));
		    }
		    conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

		request.getRequestDispatcher("/white/right.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
