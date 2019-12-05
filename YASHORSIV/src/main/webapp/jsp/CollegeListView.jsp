<%@page import="co.raystech.proj4.util.ServletUtility"%>
<%@page import="co.raystech.proj4.controller.CollegeListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="co.raystech.proj4.bean.CollegeBean"%>
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
	<%=ServletUtility.getSuccessMessage(request)%>
	hello
	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">
		
		<h5>CollegeName:</h5><input type="text" name="search"
			value="<%=ServletUtility.getParameter("search", request)%>">
			 <input
			type="submit" name="operation" value="<%=CollegeListCtl.OP_SEARCH%>">
		<table border="2px" bgcolor="yellow" width="30%">
			<tr>
				<th>Select</th>
				<th width="50%">Id</th>
				<th>College Name</th>
				<th>College Address</th>
				<th>College State</th>
				<th>College City</th>
				<th>College ContactNo</th>
				<th>Edit</th>

			</tr>
			<%
				CollegeBean bean = new CollegeBean();
				List<CollegeBean> list = (List<CollegeBean>) request.getAttribute("list");
				int pageNo = (Integer) request.getAttribute("pageNo");
				int pageSize = (Integer) request.getAttribute("pageSize");
				int index = (pageNo - 1) * pageSize + 1;
				int buttonNumber = (Integer) request.getAttribute("buttonNumber");
				Iterator<CollegeBean> iterator = list.iterator();
				while (iterator.hasNext()) {

					bean = iterator.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getState()%></td>
				<td><%=bean.getCity()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><a href="CollegeCtl?Id=<%=bean.getId()%>">Edit</a></td>

			</tr>
			<%
				}
			%>

		</table>
		<input type="submit" name="operation"
			value="<%=CollegeListCtl.OP_NEXT%>"
			<%if (pageNo == buttonNumber || pageSize != list.size()) {%>
			disabled="disabled" <%}%>> <input type="hidden" name="pageNo"
			value="<%=pageNo%>"> <input type="submit" name="operation"
			value="<%=CollegeListCtl.OP_PREVIOUS%>" <%if (pageNo == 1) {%>
			disabled="disabled" <%}%>> <input type="submit"
			name="operation" value="<%=CollegeListCtl.OP_DELETE%>"
			<%if (list.size() == 0) {%> disabled="disabled" <%}%>>

	</form>

</body>
</html>