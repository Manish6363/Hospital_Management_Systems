<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Doctor-Login Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/login_form.css">
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	String usernameMsg = (String) request.getAttribute("usernameMsg");
	String passwordMsg = (String) request.getAttribute("passwordMsg");
	%>
	<%@include file="component/header.jsp"%>
	<div id="formbody">
		<div id="login">
			<h1 style="color: #218838; text-align: center;">Doctor Login</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form id="loginForm" action="doctor-Login" method="POST">
				<%
				if (usernameMsg != null) {
				%>
				<p style="color: red; text-align: center;"><%=usernameMsg%></p>
				<%
				request.removeAttribute("usernameMsg");
				}
				%>
				<label for="username">User-name</label> <input type="text"
					name="username" id="username" autofocus />
				<%
				if (passwordMsg != null) {
				%>
				<p style="color: red; text-align: center;"><%=passwordMsg%></p>
				<%
				request.removeAttribute("passwordMsg");
				}
				%>
				<label for="password">Password</label> <input type="password"
					name="password" id="password" />

				<button type="submit">Login</button>
				<div id="message"></div>
			</form>
			<p id="message" style="margin-top: 30px">
				Don't have an account? <a href='doctor_register.jsp'>Register
					Now</a>
			</p>
		</div>

	</div>
	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
</body>
</html>
