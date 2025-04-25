<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin-Registration Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/admin_registration.css">
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	String usernameMsg = (String) request.getAttribute("usernameMsg");
	String emailMsg = (String) request.getAttribute("emailMsg");
	%>
	<%@include file="component/header.jsp"%>
	<div id="registerbody">
		<div id="register">
			<h1>Admin Registration</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form action="adminRegister" method="POST"
				enctype="multipart/form-data">
				<fieldset>
					<legend>Account Info</legend>
					<div class="form-group">
						<%
						if (usernameMsg != null) {
						%>
						<p style="color: red; text-align: center;"><%=usernameMsg%></p>
						<%
						request.removeAttribute("usernameMsg");
						}
						%>
						<label for="username">Username:</label> <input type="text"
							id="username" name="username" required />
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password" required />
					</div>
				</fieldset>

				<fieldset>
					<legend>Admin Info</legend>
					<div class="form-group">
						<label for="name">Full Name:</label> <input type="text" id="name"
							name="name" required />
					</div>
					<div class="form-group">
						<%
						if (emailMsg != null) {
						%>
						<p style="color: red; text-align: center;"><%=emailMsg%></p>
						<%
						request.removeAttribute("emailMsg");
						}
						%>
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" required />
					</div>
					<div class="form-group">
						<label for="phone">Phone:</label> <input type="tel" id="phone"
							name="phone" required />
					</div>
					<div class="form-group">
						<label for="doj">Date of Joining:</label> <input type="date"
							id="doj" name="doj" required />
					</div>
					<div class="form-group">
						<label for="designation">Designation:</label> <input type="text"
							id="designation" name="designation"
							placeholder="e.g. Chief Admin" required />
					</div>
					<div class="form-group">
						<label for="photo">Profile Photo:</label> <input type="file"
							id="photo" name="photo" accept="image/*" />
					</div>
				</fieldset>

				<button type="submit">Register</button>
			</form>
		</div>
	</div>
	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
</body>
</html>
