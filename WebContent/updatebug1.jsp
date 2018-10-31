<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%
String bugid=(String)request.getParameter("bugid");
String priority=(String)request.getParameter("priority");
String status=(String)request.getParameter("status");
String assign_to=(String)request.getParameter("assign_to");
System.out.print(bugid+" "+priority+" "+status+" "+assign_to);

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bug","bugtracker");
	Statement stmt = con.createStatement();
	String query = "Update bug set priority='"+priority+"', status='"+status+"', assign_to='"+assign_to+"' where bugid="+bugid;
	stmt.executeUpdate(query);
		RequestDispatcher rd=request.getRequestDispatcher("updatebug.jsp?bugid="+bugid);
		rd.forward(request, response);
	}catch(Exception e){
		RequestDispatcher rd=request.getRequestDispatcher("updatebug.jsp?bugid="+bugid);
		rd.forward(request, response);
	}	
	
%>
