<%@page import="co.raystech.proj4.controller.CourseListCtl"%>
<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@page import="java.util.Iterator"%>
<%@page import="co.raystech.proj4.bean.CourseBean"%>
<%@page import="co.raystech.proj4.util.ServletUtility"%>
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
<jsp:useBean id="bean" class="co.raystech.proj4.bean.CourseBean"
	scope="request">
</jsp:useBean>
<body>
	<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
	
		<table border="2">
			<tr>
				<th>Select</th>
				<th>S no.</th>
				<th>Course Name</th>
				<th>Course Description</th>
				<th>Course Duration</th>
				<th>Edit</th>
			</tr>
			<%
				List<CourseBean> list = ServletUtility.getList(request);
				int pageNo = (int) request.getAttribute("pageNo");
				int pageSize = (int) request.getAttribute("pageSize");
				int index = (pageNo - 1) * pageSize + 1;
				Iterator<CourseBean> iterator = list.iterator();
				while (iterator.hasNext()) {

					bean = iterator.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=bean.getCourseName()%></td>
				<td><%=bean.getCourseDescription()%></td>
				<td><%=bean.getCourseDuration()%></td>
				<td><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
			</tr>
			<%
				}
			%>
		</table>

		<input type="submit" name="operation"
			value="<%=CourseListCtl.OP_NEXT%>" <%if (pageSize != list.size()) {%>
			disabled="disabled" <%}%>> <input type="hidden" name="pageNo"
			value="<%=pageNo%>"> <input type="submit" name="operation"
			value="<%=CourseListCtl.OP_PREVIOUS%>"
			<%if (pageNo == 1 || list.size() == 0) {%> disabled="disabled" <%}%>>
	</form>

</body>
</html>