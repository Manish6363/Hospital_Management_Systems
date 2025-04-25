<%@page import="com.hospital_management.dto.Patient"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient's Details</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style>
section {
	background: #f0f2f5;
	padding: 40px;
}

#table_struct {
	width: auto;
	margin:auto;
	border-collapse: collapse;
	background: #fff;
	border-radius: 16px;
	overflow: hidden;
	box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
	transform: perspective(1000px);
}

.heading, .column {
	padding: 15px;
	border: none;
	text-align: left;
	font-size: 16px;
}

.heading {
	background: #f7f8fc;
	color: #333;
	font-weight: bold;
}

.row:not(:first-child):hover {
	background: #f1f5ff;
	transition: background 0.3s ease;
}

#profile {
	width: 180px;
	height: 180px;
	border-radius: 12px;
	object-fit: cover;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	margin-top: 5px;
	transition: transform 0.3s ease;
}

#profile:hover {
	transform: scale(1.05);
}

</style>
</head>
<body>

	<%
	String username = (String) session.getAttribute("username");
	String name = (String) session.getAttribute("name");
	if (username == null) {
		session.removeAttribute("username");
		session.removeAttribute("name");
		session.invalidate();
		response.sendRedirect("admin_login.jsp");
	}
	%>

	<header>
		<div id="head-container">
			<div id="logo">
				<i class="fa-solid fa-house-medical"></i> MediCare
			</div>
			<i id="hamburger" class="fa-solid fa-bars"></i>
			<nav id="nav-bar-right">
				<a href="admin_dashboard.jsp" class="anchor" title="Go to Home Page">HOME</a>
				<div id="profile-section">
					<img src="fetchAdminImage?username=<%=username%>" alt="Admin Photo"
						id="profile-pic" /> <span id="profile-name"><%=name%> <i
						class="fa-solid fa-caret-down"></i></span>
					<div class="dropdown-content">
						<a href="#">Update Profile</a> <a href="#">Change Password</a> <a
							href="logout">Logout</a>
					</div>
				</div>
			</nav>
		</div>
	</header>

	<h1>H</h1>

	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
