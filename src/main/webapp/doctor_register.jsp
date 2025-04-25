<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Doctor-Registration Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/doctor_registration.css">
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	String usernameMsg = (String) request.getAttribute("usernameMsg");
	String emailMsg = (String) request.getAttribute("emailMsg");
	String licenseMsg = (String) request.getAttribute("licenseMsg");
	String ageMsg = (String) request.getAttribute("ageMsg");
	%>
	<%@include file="component/header.jsp"%>
	<div id="registerbody">
		<div id="register">
			<h1>Doctor Registration</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form action="doctorregister" method="POST"
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
					<legend>Personal Info</legend>
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
						<%
						if (ageMsg != null) {
						%>
						<p style="color: red; text-align: center;"><%=ageMsg%></p>
						<%
						request.removeAttribute("ageMsg");
						}
						%>
						<label for="dob">Date of Birth:</label> <input type="date"
							id="dob" name="dob" required />
					</div>
					<div class="radio-line">
						<label>Gender:</label>
						<div class="radio-group">
							<label><input type="radio" name="gender" value="Male"
								required /> Male</label> <label><input type="radio"
								name="gender" value="Female" required /> Female</label> <label><input
								type="radio" name="gender" value="Other" required /> Other</label>
						</div>
					</div>
					<div class="form-group">
						<label for="address">Address:</label>
						<textarea id="address" name="address" rows="2" required></textarea>
					</div>
				</fieldset>

				<fieldset>
					<legend>Professional Info</legend>
					<div class="form-group">
						<label for="specialization">Specialization:</label> <input
							type="text" id="specialization" name="specialization" required />
					</div>
					<div class="form-group">
						<label for="experience">Experience (in years):</label> <input
							type="number" id="experience" name="experience" min="0" required />
					</div>
					<div class="form-group">
						<label for="workdesc">Work Experience:</label>
						<textarea id="workdesc" name="workdesc" rows="3"></textarea>
					</div>
					<div class="form-group">
						<%
						if (licenseMsg != null) {
						%>
						<p style="color: red; text-align: center;"><%=licenseMsg%></p>
						<%
						request.removeAttribute("licenseMsg");
						}
						%>
						<label for="license">License Number:</label> <input type="text"
							id="license" name="license" required />
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
