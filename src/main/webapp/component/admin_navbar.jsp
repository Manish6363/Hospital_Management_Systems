
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
					<a href="admin_update_profile.jsp">Update Profile</a> <a href="admin_password_change.jsp">Change
						Password</a> <a href="logout">Logout</a>
				</div>
			</div>
		</nav>
	</div>
</header>