<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Patient-Registration Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/footer_style.css">
<link rel="stylesheet" href="component/patient_registration.css">
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
			<h1>Patient Registration</h1>
			<%
			if (message != null) {
			%>
			<p style="color: red; text-align: center;"><%=message%></p>
			<%
			request.removeAttribute("message");
			}
			%>
			<form action="patientRegister" method="POST"
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
					<legend>Medical Info</legend>
					<div class="form-group">
						<label for="bloodgroup">Blood Group:</label> <select
							id="bloodgroup" name="bloodgroup" required>
							<option value="">Select</option>
							<option value="A+">A+</option>
							<option value="A-">A-</option>
							<option value="B+">B+</option>
							<option value="B-">B-</option>
							<option value="AB+">AB+</option>
							<option value="AB-">AB-</option>
							<option value="O+">O+</option>
							<option value="O-">O-</option>
						</select>
					</div>
					<div class="form-group">
						<label for="allergies">Allergies:</label>
						<textarea id="allergies" name="allergies" rows="2"
							placeholder="List any known allergies"></textarea>
					</div>
					<div class="form-group">
						<label for="conditions">Existing Conditions:</label>
						<textarea id="conditions" name="conditions" rows="3"
							placeholder="Chronic illnesses, etc."></textarea>
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
