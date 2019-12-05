<%@page import="co.raystech.proj4.controller.FacultyCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="co.raystech.proj4.bean.FacultyBean"%>
<%@page import="java.util.List"%>
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
	<jsp:useBean id="facultyBean"
		class="co.raystech.proj4.bean.FacultyBean" scope="request">
	</jsp:useBean>
	<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

		<table border="2">
			<tr>
			<td>Select</td>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>MobileNo</th>
				<th>EmailId</th>
				<th>CollegeName</th>
				<th>SubjectName</th>

			</tr>
			<%
				List<FacultyBean> list = (List) request.getAttribute("list");
				Iterator<FacultyBean> iterator = list.iterator();
				int pageNo = (Integer) request.getAttribute("pageNo");
				int pageSize = (Integer) request.getAttribute("pageSize");
				int buttonNumber = (Integer) request.getAttribute("buttonNumber");
				int index = (pageNo - 1) * pageSize + 1;
				while (iterator.hasNext()) {

					facultyBean = iterator.next();
			%>
			<tr>
				<td><%=index++%></td>
				<td><%=facultyBean.getId() %></td>
				<td><%=facultyBean.getFirstName()%></td>
				<td><%=facultyBean.getLastName()%></td>
				<td><%=facultyBean.getMobileNo()%></td>
				<td><%=facultyBean.getEmailId()%></td>
				<td><%=facultyBean.getCollegeName()%></td>
				<td><%=facultyBean.getSubjectName()%></td>

			</tr>
			<%
				}
			%>
		</table>

		<input type="submit" value="Next" <%if (pageNo==buttonNumber|| pageSize != list.size()) {%>
			disabled="disabled" <%}%> name="operation"><input
			type="hidden" value=<%=pageNo%> name="pageNo">
			<input type="submit" name="operation" value="<%=FacultyCtl.OP_DELETE%>">
			

</html>