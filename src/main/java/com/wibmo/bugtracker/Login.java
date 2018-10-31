package com.wibmo.bugtracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebServlet("/Login")
public class Login extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(Login.class);
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("doPost() called for Login");
		String loginid=request.getParameter("loginid");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		boolean flag=false;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bug_tracker?" + "user=root&password=kingfisher");
		   	Statement stmt = con.createStatement();
		   	String query = "select * from login where loginid ='" + loginid + "' and type='"+type+"'";
		   	ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String pass= rs.getString("password");
			 	if( pass.equals(password)) {
					flag=true;
					break;
				}		
			}	
			if (flag){
			
				HttpSession session=request.getSession();
				session.setAttribute("login", "yes");
				session.setAttribute("type", rs.getString("type"));
				session.setAttribute("user", rs.getString("name"));
				session.setAttribute("loginid", rs.getString("loginid"));
				RequestDispatcher rd=null;
				if("Admin".equals(type))
					rd=request.getRequestDispatcher("admin.jsp");
				else if("Expert".equals(type))
					rd=request.getRequestDispatcher("expert.jsp");
				else if("User".equals(type))
					rd=request.getRequestDispatcher("user.jsp");
				
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp?msg=loginerror");
				rd.forward(request, response);
			}
		}catch(Exception e){
			logger.debug("Exception occured in doPost:" + e.getMessage());
		}		
	}
}
