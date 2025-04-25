
<%
String username = (String) session.getAttribute("username");
String name = (String) session.getAttribute("name");
if (username == null) {
	session.removeAttribute("username");
	session.removeAttribute("name");
	session.invalidate();
	response.sendRedirect("patient_login.jsp");
}
%>
<header>
	<div id="head-container">
		<div id="logo">
			<i class="fa-solid fa-house-medical"></i> MediCare
		</div>
		<i id="hamburger" class="fa-solid fa-bars"></i>
		<nav id="nav-bar-right">
			<a href="patient_dashboard.jsp" class="anchor"
				title="Go to Home Page">HOME</a>

			<!-- Appointment dropdown -->
			<div class="dropdown-section" id="appointment-dropdown">
				<div class="dropdown-toggle">
					APPOINTMENT <i class="fa-solid fa-caret-down"></i>
				</div>
				<div class="dropdown-content">
					<a href="patient_apply_appointment.jsp">Apply Appointment</a> <a
						href="patient_view_appointment.jsp">View Appointment</a>
				</div>
			</div>

			<!-- Profile section -->
			<div id="profile-section">
				<img src="fetchPatientImage?username=<%=username%>"
					alt="Patient Photo" id="profile-pic" /> <span id="profile-name"><%=name%>
					<i class="fa-solid fa-caret-down"></i></span>
				<div class="dropdown-content">
					<a href="patient_profile_update.jsp">Update Profile</a> <a href="patient_change_pass.jsp">Change Password</a> <a
						href="logout">Logout</a>
				</div>
			</div>
		</nav>
	</div>
</header>