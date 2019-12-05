<%@page import="co.raystech.proj4.util.ServletUtility"%>
<%@page import="co.raystech.proj4.controller.CourseCtl"%>
<%@page import="co.raystech.proj4.util.DataUtility"%>
<%@page import="co.raystech.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page
	import="com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker"%>
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
	<jsp:useBean id="bean" class="co.raystech.proj4.bean.CourseBean"
		scope="request">
	</jsp:useBean>

	<h1>welcome to ADD Course</h1>
	<h1>


		<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
	</h1>
	</font>

	<form action="<%=ORSView.COURSE_CTL%>" method="post">

		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<center>
			Course Name:<input type="text" name="courseName"
				value="<%=DataUtility.getStringData(bean.getCourseName())%>">
			<font color="red"> <%=ServletUtility.getErrorMessage("courseName", request)%>
			</font><br> <br> Course Description:<input type="text"
				name="courseDescription"
				value="<%=bean.getCourseDescription() == null ? "" : bean.getCourseDescription()%>"><br>
			<%
				HashMap hashMap = new HashMap();
				hashMap.put("3 years", "3 years");
				hashMap.put("2 years", "2 years");
				hashMap.put("4 years", "4 years");
			%>
			<br> CourseDuration:<%=HTMLUtility.getList("courseDuration", DataUtility.getStringData(bean.getCourseDuration()),
					hashMap, true)%>
			<input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">
		</center>
	</form>
</body>
</html>