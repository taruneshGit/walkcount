<%@page import="co.raystech.proj4.controller.ChangePasswordCtl"%>
<%@page import="javax.swing.text.ChangedCharSetException"%>
<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<form action="<%=ORSView.CHANGE_PASSWORD_CTL %>" method="post">
OldPassword:<input type="text" name="oldPassword" ><br>
NewPassword:<input type="text" name="newPassword" ><br>
ConfirmPassword:<input type="text" name="confirmPassword" ><br>
<input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">
<input type="reset" name="operation" value="<%=ChangePasswordCtl.OP_RESET%>">
</form>
</center>
</body>
</html>