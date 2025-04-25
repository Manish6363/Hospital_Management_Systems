<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Report Card</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<link rel="stylesheet" href="component/appointment_letter.css" />
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

	<%@include file="component/appointment_letter.jsp"%>


	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>