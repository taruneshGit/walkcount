<%@page import="co.raystech.proj4.util.HTMLUtility"%>
<%@page import="co.raystech.proj4.bean.RoleBean"%>
<%@page import="java.util.List"%>
<%@page import="co.raystech.proj4.controller.UserCtl"%>
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

	<jsp:useBean id="userBean" class="co.raystech.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%
		List<RoleBean> roleList = (List) request.getAttribute("roleList");
	%>

	<form action="<%=ORSView.USER_CTL%>" method="post">
		<center>
			Name <input type="text" name="gangs"> <br> Role
			<%=HTMLUtility.getList("role", String.valueOf(userBean.getRoleId()), roleList)%>

			<br> <br> <br> <input type="submit" name="operation"
				value="<%=UserCtl.OP_SAVE%>">
		</center>
	</form>
</body>
</html>