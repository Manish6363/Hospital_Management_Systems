<%@page import="com.hospital_management.dao.AppointmentCrud"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Dashboard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style>
/* Dashboard Styles */
section {
	padding: 5rem 3rem;
}

#dashboard-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 2rem;
	max-width: 1200px;
	margin: auto;
	align-items: stretch;
}

.dashboard-card {
	border-radius: 20px;
	padding: 2rem 1.5rem;
	text-align: center;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	cursor: pointer;
	color: #222;
	background-size: cover;
	background-position: center;
	box-shadow: 0 12px 20px rgba(0, 0, 0, 0.15), 0 8px 12px
		rgba(0, 0, 0, 0.1), inset 0 1px 0 rgba(255, 255, 255, 0.4);
	min-height: 280px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.dashboard-card:hover {
	transform: translateY(-10px) scale(1.02);
	box-shadow: 0 20px 30px rgba(50, 205, 50, 0.3), 0 10px 20px
		rgba(0, 0, 0, 0.2);
}

.dashboard-card:nth-child(1) {
	background: linear-gradient(135deg, #a1c4fd, #c2e9fb);
}

.dashboard-card:nth-child(2) {
	background: linear-gradient(135deg, #fbc2eb, #a6c1ee);
}

.dashboard-card:nth-child(3) {
	background: linear-gradient(135deg, #fddb92, #d1fdff);
}

.icon {
	font-size: 3rem;
	color: #333;
	margin-bottom: 1rem;
}

.dashboard-card h3 {
	margin-bottom: 0.5rem;
	font-size: 1.5rem;
}

.dashboard-card p {
	margin: 0.3rem 0;
	color: #222;
}

.count {
	font-weight: bold;
	font-size: 1.2rem;
}
</style>

</head>
<body>
	<%@include file="component/admin_navbar.jsp"%>
	<%
	int patientCount = new PatientCrud().fetchCount();
	int doctorCount = new DoctorCrud().fetchCount() + 4;
	int approvedCount = new AppointmentCrud().fetchApprovedAppointment().size();
	int pendingCount = new AppointmentCrud().fetchPendingAppointment().size();
	%>
	<section>
		<div id="dashboard-container">
			<div class="dashboard-card"
				onclick="location.href='admin_all_doctor_team.jsp'">
				<i class="fa-solid fa-user-doctor icon"></i>
				<h3>Doctor</h3>
				<p>
					Total: <span class="count"><%=doctorCount%></span>
				</p>
			</div>
			<div class="dashboard-card"
				onclick="location.href='admin_all_patients_record.jsp'">
				<i class="fa-solid fa-hospital-user icon"></i>
				<h3>Patient</h3>
				<p>
					Total: <span class="count"><%=patientCount%></span>
				</p>
			</div>
			<div class="dashboard-card"
				onclick="location.href='admin_appointment_details.jsp'">
				<i class="fa-solid fa-calendar-check icon"></i>
				<h3>Appointments</h3>
				<p>
					Approved: <span class="count"><%=approvedCount%></span>
				</p>
				<p>
					Pending: <span class="count"><%=pendingCount%></span>
				</p>
			</div>
		</div>
	</section>


	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
