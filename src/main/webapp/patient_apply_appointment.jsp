<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Apply Appointment</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/patient_login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />

<style>
form {
	max-width: 70%;
	margin: 60px auto;
	background: #f0f8ff;
	padding: 30px 40px;
	border-radius: 15px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
	transform: perspective(1000px);
	transition: transform 0.3s ease-in-out;
}

form:hover {
	transform: perspective(1000px) rotateX(1deg) rotateY(1deg);
}

form label {
	font-size: 1.1rem;
	font-weight: bold;
	color: #333;
}

form textarea {
	width: 100%;
	padding: 12px;
	border-radius: 10px;
	border: 1px solid #ccc;
	resize: vertical;
	font-size: 1rem;
	box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.1);
	transition: box-shadow 0.3s;
}

form textarea:focus {
	outline: none;
	box-shadow: inset 0 3px 10px rgba(0, 0, 0, 0.2);
}

form input[type="submit"] {
	margin-top: 20px;
	background-color: #007bff;
	color: white;
	padding: 12px 25px;
	font-size: 1rem;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	box-shadow: 0 5px 15px rgba(0, 123, 255, 0.3);
	transition: background 0.3s ease, transform 0.2s ease;
}

form input[type="submit"]:hover {
	background-color: #0056b3;
	transform: translateY(-2px);
	box-shadow: 0 8px 18px rgba(0, 86, 179, 0.5);
}
</style>
</head>

<body>
	<%@include file="component/patient_dashboard_navbar.jsp"%>

	<section>

		<form action="applyAppointment" method="post">
			<h1 style="margin:0; color: #218838; text-align: center; padding-bottom: 1.8rem">Apply
				Appointment</h1>
			<input type="hidden" name="username" value="<%=username%>"> <label
				for="reason">Reason for Checkup:</label><br>
			<textarea name="reason" rows="4" cols="50" required></textarea>
			<br> <br> <input type="submit" value="Request Appointment">
		</form>
	</section>



	<%@include file="component/footer.jsp"%>
	<script src="component/patient_login_navbar.js"></script>
</body>
</html>
