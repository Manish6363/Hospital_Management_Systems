<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Update Profile-Admin</title>
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
	String emailMsg = (String) request.getAttribute("emailMsg");
	%>
	<div id="registerbody">
		<div id="register">
			<h1>Update Admin Profile</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form action="updateAdmin" method="POST"
				enctype="multipart/form-data">
				<input type="hidden" id="username" name="username"
					value=<%=username%> />
				<fieldset>
					<legend>Account Info</legend>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password" />
					</div>
				</fieldset>

				<fieldset>
					<legend>Admin Info</legend>
					<div class="form-group">
						<label for="name">Full Name:</label> <input type="text" id="name"
							name="name" />
					</div>
					<%
					if (emailMsg != null) {
					%>
					<p style="color: red; text-align: center;"><%=emailMsg%></p>
					<%
					request.removeAttribute("emailMsg");
					}
					%>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" />
					</div>
					<div class="form-group">
						<label for="phone">Phone:</label> <input type="tel" id="phone"
							name="phone" />
					</div>
					<div class="form-group">
						<label for="doj">Date of Joining:</label> <input type="date"
							id="doj" name="doj" />
					</div>
					<div class="form-group">
						<label for="designation">Designation:</label> <input type="text"
							id="designation" name="designation"
							placeholder="e.g. Chief Admin" />
					</div>
					<div class="form-group">
						<label for="photo">Profile Photo:</label> <input type="file"
							id="photo" name="photo" accept="image/*" />
					</div>
				</fieldset>

				<button type="submit">Update Profile</button>
			</form>
		</div>
	</div>

	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
</body>
</html>
