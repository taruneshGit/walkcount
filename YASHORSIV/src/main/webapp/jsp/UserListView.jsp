<%@page import="co.raystech.proj4.controller.UserCtl"%>
<%@page import="java.beans.beancontext.BeanContext"%>
<%@page import="co.raystech.proj4.bean.UserBean"%>
<%@page import="java.util.Iterator"%>
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
	<jsp:useBean id="userBean" class="co.raystech.proj4.bean.UserBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
		welcome to user_list_view
		<table border="2">
			<tr>
				Ifd
				<th>Select</th>
				<th>Id</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>MobileNo</th>

			</tr>
			<%
				List<UserBean> list = (List) request.getAttribute("list");
				Iterator<UserBean> iterator = list.iterator();
				int pageNo = (Integer) request.getAttribute("pageNo");
				int pageSize = (Integer) request.getAttribute("pageSize");
				int index = (pageNo - 1) * pageSize + 1;

				while (iterator.hasNext()) {

					userBean = iterator.next();
			%>
			<tr>
				<td><input type="checkbox" name="ids"
					value="<%=userBean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=userBean.getFirstName()%></td>
				<td><%=userBean.getLastName()%></td>
				<td><%=userBean.getMobileNo()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="submit" value="Next" <%if (pageSize != list.size()) {%>
			disabled="disabled">
		<%
			}
		%>
		name="operation" ><input type="hidden" value=<%=pageNo%> name="pageNo">


		<input type="submit" name="operation" value="<%=UserCtl.OP_DELETE%>">

		<input type="submit" name="operation"
			value="<%=UserCtl.OP_PREVIOUS%>"> <input type="submit"
			name="operation" value="<%=UserCtl.OP_DELETE%>">
			
			
			
	</form>
</body>
</html>