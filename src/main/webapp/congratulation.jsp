<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Congratulation</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/login_form.css">
<link rel="stylesheet" href="component/congratulation.css">
</head>
<body>
	<%
	String pageName = (String) request.getAttribute("pageName");
	String name = (String) request.getAttribute("name");
	String username = (String) request.getAttribute("username");
	String password = (String) request.getAttribute("password");
	if (name == null && username == null && password == null) {
		response.sendRedirect("index.jsp");
	}
	%>
	<%@include file="component/header.jsp"%>
	<div id="congrats">
		<div class="container-congrats">
			<h1 id="h">
				🎉 Congratulations
				<%=name%>
			</h1>
			<p id="para">Your registration was successful.</p>

			<div class="credentials">
				<span>👤 Username: <%=username%></span> <span>🔒 Password: <%=password%></span>
			</div>

			<a href="<%=pageName%>" class="login-link">Click here to Login</a>
		</div>
	</div>
	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
	<script src="component/congrats.js"></script>
	<%
	request.removeAttribute("name");
	request.removeAttribute("username");
	request.removeAttribute("password");
	%>
</body>
</html>
