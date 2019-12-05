<%@page import="co.raystech.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
<%@page import="co.raystech.proj4.util.HTMLUtility"%>
<%@page import="co.raystech.proj4.controller.FacultyCtl"%>
<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<jsp:useBean id="facultyBean" class="co.raystech.proj4.bean.FacultyBean"
	scope="request"></jsp:useBean>
</head>
<body>
	<%
		List<FacultyBean> facultyList = (List) request.getAttribute("facultyList");
	%>

	<form action="<%=ORSView.FACULTY_CTL%>" method="post">
		<table border="2" align="center" bgcolor="yellow">
			<caption align="bottom">ADD FACULTY</caption>
			<tr>

				<th>CollegeName:</th>
				<td><%=HTMLUtility.getList("collegeName", String.valueOf(facultyBean.getId()), facultyList)%></td>
			</tr>
			<tr>
				<th>Faculty Name:</th>
				<td><%=HTMLUtility.getList("facultyName", String.valueOf(facultyBean.getId()), facultyList)%></td>
			</tr>
			<tr>
				<th>Subject Name:</th>
				<td><%=HTMLUtility.getList("subjectName", String.valueOf(facultyBean.getId()), facultyList)%></td>
			</tr>
			</tr>
			<tr>

				<td></td>
				<td><input type="submit" name="opeartion"
					value="<%=FacultyCtl.OP_SAVE%>"> <input type="submit"
					name="opeartion" value="<%=FacultyCtl.OP_CANCEL%>"> <input
					type="submit" name="opeartion" value="<%=FacultyCtl.OP_RESET%>">
				</td>
			</tr>





		</table>

	</form>
</body>
</html>