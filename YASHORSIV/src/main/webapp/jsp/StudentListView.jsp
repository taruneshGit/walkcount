<%@page import="co.raystech.proj4.util.ServletUtility"%>
<%@page import="co.raystech.proj4.controller.StudentListCtl"%>
<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="co.raystech.proj4.bean.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="studentBean"
		class="co.raystech.proj4.bean.StudentBean" scope="request"></jsp:useBean>

	welcome to Student_list_view
	<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
		<h5>StudentName:</h5>
		<input type="text" name="search"
			value="<%=ServletUtility.getParameter("search", request)%>"> <input
			type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH%>">
		<table border="2">
			<tr>
				<th>Select</th>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>MobileNo</th>
				<th>EmailId</th>
				<th>CollgeName</th>
				<th>Edit</th>
			</tr>
			<%
				List<StudentBean> list = (List<StudentBean>) request.getAttribute("list");
				int pageNo = (Integer) request.getAttribute("pageNo");
				int pageSize = (Integer) request.getAttribute("pageSize");
				int buttonNumber = (Integer) request.getAttribute("buttonNumber");
				int index = (pageNo - 1) * pageSize + 1;
				Iterator<StudentBean> iterator = list.iterator();
				while (iterator.hasNext()) {

					studentBean = iterator.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids"
					value="<%=studentBean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=studentBean.getFirstName()%></td>
				<td><%=studentBean.getLastName()%></td>
				<td><%=studentBean.getMobileNo()%></td>
				<td><%=studentBean.getEmail()%></td>
				<td><%=studentBean.getCollegeName()%></td>
				<td></td>

			</tr>
			<%
				}
			%>

		</table>
		<input type="submit" value="<%=StudentListCtl.OP_NEXT%>"
			<%if (pageNo == buttonNumber || pageSize != list.size()) {%>
			disabled="disabled" <%}%> name="operation"> <input
			type="hidden" value="<%=pageNo%>" name="pageNo"> <input
			type="submit" name="operation" <%if (pageNo == 1) {%>
			disabled="disabled" <%}%> value="<%=StudentListCtl.OP_PREVIOUS%>">
		<input type="submit" name="operation" <%if (list.size() == 0) {%>
			disabled="disabled" <%}%> value="<%=StudentListCtl.OP_DELETE%>">

	</form>

</body>
</html>