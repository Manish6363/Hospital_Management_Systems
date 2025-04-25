<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Change Password-Admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/admin_registration.css">
</head>
<body>
	<%@include file="component/admin_navbar.jsp"%>
	<%
	String message = (String) request.getAttribute("message");
	%>
	<div id="registerbody"
		style="margin: 0; padding-top: 30px; padding-bottom: 30px; height: auto;">
		<div id="register">
			<h1>Change Admin Password</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form action="changeAdminPass" method="POST">
				<input type="hidden" id="username" name="username"
					value=<%=username%> />
				<fieldset>
					<legend>Account Info</legend>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password" />
					</div>
				</fieldset>

				<button type="submit">Change Password</button>
			</form>
		</div>
	</div>

	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
</body>
</html>
