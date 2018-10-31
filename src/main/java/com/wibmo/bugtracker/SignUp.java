package com.wibmo.bugtracker;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(SignUp.class);
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("doPost() called for SignUp");
		String name = (String) request.getParameter("name");
		String email = (String) request.getParameter("email");
		String loginid = (String) request.getParameter("loginid");
		String password = (String) request.getParameter("password");
		String repassword = (String) request.getParameter("repassword");
		String type = (String) request.getParameter("type");
		if ("".equals(name) || "".equals(email) || "".equals(loginid)
				|| "".equals(password) || "".equals(repassword)
				|| !(password.equals(repassword))) {
			if ("User".equals(type)) {
				RequestDispatcher rd = request
						.getRequestDispatcher("signup.jsp?msg=blank");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request
						.getRequestDispatcher("addexpert.jsp?msg=blank");
				rd.forward(request, response);
			}

		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bug_tracker?" + "user=root&password=kingfisher");
				String insertquery = "insert into login values(?,?,?,?,?)";
				PreparedStatement stmt = con.prepareStatement(insertquery);
				stmt.setString(1, loginid);
				stmt.setString(2, password);
				stmt.setString(3, name);
				stmt.setString(4, email);
				stmt.setString(5, type);
				stmt.executeUpdate();
				stmt.close();
				con.close();
				if ("User".equals(type)) {
					HttpSession session = request.getSession();
					session.setAttribute("login", "yes");
					session.setAttribute("type", type);
					session.setAttribute("user", name);
					session.setAttribute("loginid", loginid);
					RequestDispatcher rd = request
							.getRequestDispatcher("user.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("admin.jsp");
					rd.forward(request, response);
				}

			} catch (Exception e) {

				if ("User".equals(type)) {
					RequestDispatcher rd = request
							.getRequestDispatcher("signup.jsp?msg=error");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("addexpert.jsp?msg=error");
					rd.forward(request, response);
				}
			}
		}
	}
}
