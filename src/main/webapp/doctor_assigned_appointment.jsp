<%@page import="com.hospital_management.dto.Patient"%>
<%@page import="com.hospital_management.dto.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@page import="com.hospital_management.dao.AppointmentCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Assigned Appointment</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style type="text/css">
section {
	width: 100%;
	height: 80vh;
	font-family: 'Segoe UI', sans-serif;
	background: linear-gradient(to right, #f5f7fa, #c3cfe2);
	margin: 0;
	padding: 10px;
	font-family: 'Segoe UI', sans-serif;
	font-family: 'Segoe UI', sans-serif;
}

.appointment-table {
	width: 95%;
	margin: 20px auto;
	border-collapse: collapse;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
	border-radius: 12px;
	overflow: hidden;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f7f9fc;
}

.appointment-table th, .appointment-table td {
	padding: 15px 20px;
	text-align: center;
	border-bottom: 1px solid #e0e0e0;
}

.appointment-table th {
	background-color: #003366;
	color: #ffffff;
	font-size: 18px;
}

.appointment-table tr:hover {
	background-color: #eaf3ff;
	transition: background-color 0.3s ease;
}

.view-btn {
	padding: 8px 16px;
	background-color: #0099cc;
	border: none;
	color: white;
	border-radius: 6px;
	cursor: pointer;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.view-btn:hover {
	background-color: #0077aa;
}

@media ( max-width : 740px) {
	section {
		margin: 0;
		padding-left: 0px;
		padding-right: 0px;
	}
	table {
		width: 100%;
	}
	.appointment-table th, .appointment-table td {
		padding: 2px 5px;
		font-size: .85rem;
	}
}

@media ( max-width : 440px) {
	h2 {
		font-size: 1rem;
	}
	table {
		width: 100%;
	}
	.appointment-table th, .appointment-table td {
		padding: 2px 5px;
		font-size: .5rem;
	}
}
</style>
</head>
<body>
	<%@include file="component/doctor_dashboard_navbar.jsp"%>

	<%
	AppointmentCrud dao = new AppointmentCrud();
	PatientCrud patientDao = new PatientCrud();
	ArrayList<Appointment> appointments = dao.fetchApprovedAppointment(username);
	%>

	<section>
		<h2 style="text-align: center; margin-top: 20px; color: #218838;">Your
			Appointments</h2>
		<table class="appointment-table">
			<tr>
				<th>Appointment ID</th>
				<th>Reason</th>
				<th>Patient Name</th>
				<th>Status</th>
				<th>Appointment Date</th>
				<th>Remarks</th>
			</tr>
			<%
			if (appointments != null) {
				for (Appointment ap : appointments) {
					String remarks = ap.getRemarks();
					String patientName = patientDao.fetchByUsername(ap.getPatientUsername()).getName();
					if (remarks == null) {
			%>
			<tr>
				<td><%=ap.getId()%></td>
				<td><%=ap.getReason()%></td>
				<td><%=patientName%></td>
				<td><%=ap.getStatus()%></td>
				<td><%=ap.getAppointmentDate() != null ? ap.getAppointmentDate() : "Not Assigned"%></td>
				<td>
					<%
					if (remarks == null) {
					%>
					<form action="doctor_add_remarks.jsp" method="get">
						<input type="hidden" name="id" value="<%=ap.getId()%>">
						<button class="view-btn">Add Remarks</button>
					</form> <%
					 } else {
					 %> <span style="color: gray;">Waiting</span> <%
					 }
					 %>
				</td>
			</tr>
			<%
			}
			}
			}
			%>
		</table>
	</section>

	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
