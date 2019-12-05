<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@page import="co.raystech.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="bean" class="co.raystech.proj4.bean.UserBean"
	scope="session"></jsp:useBean>
<body>
	<%
		bean = (UserBean) session.getAttribute("user");
		String message = "Hi";
	%>
	<%=message + " " + bean.getFirstName()%>
	<h3 align="right">
		<b><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></b>
	</h3>
	
</body>
</html>