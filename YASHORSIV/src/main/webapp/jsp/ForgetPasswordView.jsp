<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@page import="co.raystech.proj4.controller.UserCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Welcome to Forget Password Page</p>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

		Emaild ID:<input type="text" name="emailId"> <input
			type="submit" name="operation" value="<%=UserCtl.OP_GO%>">


	</form>
</body>
</html>