<%@page import="co.raystech.proj4.controller.ORSView"%>
<html>
<body>
	<h2>Hello World!</h2>

<a href="/YASHORSIV/jsp/LoginView.jsp"> welcome</a>
<a href="/YASHORSIV/jsp/NewFile.jsp"> welcome to ORSP IV </a>
	<a href="<%= ORSView.APP_CONTEXT%><%=ORSView.LOGIN_VIEW%>"> welcome to ORSP </a>


<%-- <a href="/YASHORSIV<%=ORSView.LOGIN_CTL%>"> welcome to ORS </a> --%>
<a href="<%=ORSView.LOGIN_CTL%>"> welcome to ORS </a>
</body>
</html>
