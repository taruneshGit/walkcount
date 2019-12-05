<%@page import="co.raystech.proj4.util.DataUtility"%>
<%@page import="co.raystech.proj4.util.ServletUtility"%>
<%@page import="co.raystech.proj4.controller.LoginCtl"%>
<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head><jsp:useBean id="bean" class="co.raystech.proj4.bean.UserBean"
	scope="request"></jsp:useBean>
<body bgcolor="sky blue">
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		<table border="2" align="center">
			<caption align="top">LOGIN VIEW</caption>
			<tr>
				<th>EMAILID:</th>
				<td><input type="text" name="login"
					value="<%=DataUtility.getStringData(bean.getLogin())%>"> <font
					color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
			</tr>
			<br>
			<br>
			<tr>
				<th>PASSWORD:</th>
				<td><input type="text" name="password"></td>
			</tr>

			<br>
			<br>
			<tr>
				<td></td>
				<td><input type="submit" name="operation"
					value="<%=LoginCtl.OP_LOGIN%>"> <input type="reset"
					name="operation" value="<%=LoginCtl.OP_RESET%>">
					<a href="<%=ORSView.FORGET_PASSWORD_CTL%>"> Forget Password</a></td>

			</tr>
		</table>
	</form>
</body>
</html>