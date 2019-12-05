
<%@page import="co.raystech.proj4.controller.ORSView"%>
<%@page import="co.raystech.proj4.bean.UserBean"%>
<script type="text/javascript" src="../js/jquery.min.js"></script>

<html>
<head>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/calendar.js"></script>
<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/Cal.js"></script>
<link rel="stylesheet" href="./css/cal.css">
<link rel="stylesheet" href="./css">
<link rel="stylesheet" href="./css/bootstrap.min.css">


<!-- <script type="text/javascript">
	function noBack() {
		window.history.forward()
	}
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack()
	}
	window.onunload = function() {
		void (0)
	}
</script> -->


<style type="text/css">
@import 'https://fonts.googleapis.com/css?family=Raleway';

body {
	font-family: 'Raleway', sans-serif;
}

.container {
	padding: 40px 0
}

.welcomeMsg {
	padding: 0
}

h1 {
	margin-top: 0;
	color: black
}

.rlinks {
	padding-top: 10px
}

.toplist a {
	margin-top: 10px;
}

.fline {
	margin: 30px 0
}

.loginbox {
	max-width: 300px;
	text-align: left;
}

.forgot {
	display: block;
	text-align: right;
	margin: 5px 0 10px
}

.signup {
	margin-top: 20px
}

.signup .btn-link {
	padding: 0;
	margin-top: -2px
}
</style>
</head>
<body>
	<%@include file="Header.jsp"%>

	<ul class="dropdown-menu">
		<li><a href="<%=ORSView.USER_CTL%>"> Add user</a></li>
		<li><a href="<%=ORSView.USER_LIST_CTL%>">User's List </a></li>
	</ul>
	<ul class="dropdown-menu">
		<li><a href="<%=ORSView.STUDENT_CTL%>"> Add student</a></li>
		<li><a href="<%=ORSView.STUDENT_LIST_CTL%>">Student's List </a></li>
	</ul>
	<ul class="dropdown-menu">

		<li><a href="<%=ORSView.FACULTY_CTL%>"> Add Faculty</a></li>
		<li><a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List </a></li>
	</ul>
	<ul class="dropdown-menu">

		<li><a href="<%=ORSView.COLLEGE_CTL%>"> Add College</a></li>
		<li><a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List </a></li>
	</ul>
	<ul class="dropdown-menu">
		<li><a href="<%=ORSView.COURSE_CTL%>">ADD Course</a>
		<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a></li>
	</ul>



</body>
</html>