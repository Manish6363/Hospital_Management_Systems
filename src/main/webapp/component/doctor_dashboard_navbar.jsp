
<%
String username = (String) session.getAttribute("username");
String name = (String) session.getAttribute("name");
if (username == null) {
	session.removeAttribute("username");
	session.removeAttribute("name");
	session.invalidate();
	response.sendRedirect("doctor_login.jsp");
}
%>
<header>
	<div id="head-container">
		<div id="logo">
			<i class="fa-solid fa-house-medical"></i> MediCare
		</div>
		<i id="hamburger" class="fa-solid fa-bars"></i>
		<nav id="nav-bar-right">
			<a href="doctor_dashboard.jsp" class="anchor" title="Go to Home Page">HOME</a>
			<a href="doctor_assigned_appointment.jsp" class="anchor"
				title="Go to Appointment Assigned">ASSIGNED APPOINTMENT</a>
			<div id="profile-section">
				<img src="fetchDoctorImage?username=<%=username%>"
					alt="Doctor Photo" id="profile-pic" /> <span id="profile-name"><%=name%>
					<i class="fa-solid fa-caret-down"></i></span>
				<div class="dropdown-content">
					<a href="doctor_profile_update.jsp">Update Profile</a> <a href="doctor_password_change.jsp">Change Password</a> <a
						href="logout">Logout</a>
				</div>
			</div>
		</nav>
	</div>
</header>