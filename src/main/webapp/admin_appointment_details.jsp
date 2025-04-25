<%@page import="com.hospital_management.dto.Patient"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@page import="com.hospital_management.dao.AppointmentCrud"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hospital_management.dto.Appointment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>All Patient's Appointments-Admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style type="text/css">
section {
	width: 100%;
	font-family: 'Segoe UI', sans-serif;
	background: linear-gradient(to right, #f5f7fa, #c3cfe2);
	padding: 10px;
	padding-bottom: 100px;
}

.appointment-table {
	width: 98%;
	margin: 10px auto;
	border-collapse: collapse;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
	border-radius: 12px;
	overflow: hidden;
	background: #f7f9fc;
}

.appointment-table th, .appointment-table td {
	padding: 5px 10px;
	text-align: center;
	border-bottom: 1px solid #e0e0e0;
}

.appointment-table th {
	background-color: #003366;
	color: #ffffff;
	font-size: 1rem;
}

.appointment-table tr:hover {
	background-color: #eaf3ff;
	transition: background-color 0.3s ease;
}

select, input[type="date"], .action-btn {
	padding: 3px 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.action-btn {
	background-color: #28a745;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-top: 5px;
}

.action-btn:hover {
	background-color: #218838;
}

@media ( max-width : 910px) {
	.appointment-table th, .appointment-table td {
		padding: 0;
		margin: 0;
	}
	select, input {
		width: 100%;
		padding-left: 2px;
		padding-right: 2px;
	}
	.appointment-table th, .appointment-table td, select, input {
		padding: 10px;
		font-size: 0.75rem;
	}
	.action-btn {
		font-size: .75rem;
	}
	#date {
		width: 90%;
	}
}

@media ( max-width : 655px) {
	.appointment-table th, .appointment-table td {
		padding: 0;
		margin: 0;
	}
	select, input {
		width: 100%;
		padding-left: 1px;
		padding-right: 1px;
	}
	.appointment-table th, .appointment-table td, select, input {
		padding: 10px;
		font-size: 0.5rem;
	}
	.action-btn {
		font-size: .5rem;
	}
}

@media ( max-width : 505px) {
	.appointment-table th, .appointment-table td {
		padding: 0;
		margin: 0;
	}
	select, input {
		width: 100%;
		padding-left: 1px;
		padding-right: 1px;
	}
	.appointment-table th, .appointment-table td, select, input {
		padding: 10px;
		font-size: 0.3rem;
		padding-left: 0px;
		padding-right: 0px;
	}
	.action-btn {
		font-size: .3rem;
	}
	#date {
		max-width: 20px;
	}
}
</style>
</head>
<body>

	<%@include file="component/admin_navbar.jsp" %>

	<%
	AppointmentCrud dao = new AppointmentCrud();
	DoctorCrud doctorDao = new DoctorCrud();

	ArrayList<Appointment> appointments = dao.fetchAll();
	ArrayList<Doctor> doctors = doctorDao.fetchAll();
	%>

	<section>
		<h2 style="text-align: center; margin-top: 20px; color: #218838;">Pending
			Appointments</h2>
		<table class="appointment-table">
			<tr>
				<th>Appointment ID</th>
				<th>Patient Username</th>
				<th>Reason</th>
				<th>Applied Date</th>
				<th>Status</th>
				<th>Assign Doctor</th>
				<th>Appointment Date</th>
				<th>Action</th>
			</tr>
			<%
			for (Appointment ap : appointments) {
				PatientCrud pcrud = new PatientCrud();
				Patient patient = pcrud.fetchByUsername(ap.getPatientUsername());
				if ("Pending".equalsIgnoreCase(ap.getStatus())) {
			%>
			<tr>
				<form action="updateAppointmentStatus" method="post">
					<td><%=ap.getId()%><input type="hidden" name="id"
						value="<%=ap.getId()%>"></td>
					<td><%=patient.getName()%></td>
					<td><%=ap.getReason()%></td>
					<td><%=ap.getAppliedDate()%></td>
					<td style="color: red;"><%=ap.getStatus()%></td>
					<td><select name="doctorUsername" required>
							<option value="" disabled selected>Select Doctor</option>
							<%
							for (Doctor doc : doctors) {
							%>
							<option value="<%=doc.getUsername()%>"><%=doc.getName()%></option>
							<%
							}
							%>
					</select></td>
					<td><input id="date" type="date" name="appointmentDate"
						required /></td>
					<td>
						<button class="action-btn" name="action" value="Approve">Approve</button>
						<button class="action-btn" name="action" value="Pending"
							style="background-color: #ffc107;">Postpone</button>
					</td>
				</form>
			</tr>
			<%
			}
			}
			%>
		</table>
	</section>

	<section>
		<h2 style="text-align: center; margin-top: 20px; color: #218838;">Approve
			Appointments</h2>
		<table class="appointment-table">
			<tr>
				<th>Appointment ID</th>
				<th>Patient Username</th>
				<th>Reason</th>
				<th>Applied Date</th>
				<th>Status</th>
				<th>Assign Doctor</th>
				<th>Appointment Date</th>
				<th>Remarks</th>
			</tr>
			<%
			ArrayList<Appointment> app = dao.fetchApprovedAppointment();
			for (Appointment ap : app) {
				PatientCrud pcrud = new PatientCrud();
				Patient patient = pcrud.fetchByUsername(ap.getPatientUsername());
				Doctor doctor = doctorDao.fetchByUsername(ap.getDoctorUsername());
				if ("Approve".equalsIgnoreCase(ap.getStatus())) {
			%>
			<tr>

				<td><%=ap.getId()%><input type="hidden" name="id"
					value="<%=ap.getId()%>"></td>
				<td><%=patient.getName()%></td>
				<td><%=ap.getReason()%></td>
				<td><%=ap.getAppliedDate()%></td>
				<td style="color: #32CD32; font-weight: 700;"><%=ap.getStatus()%></td>
				<td><%=doctor.getName()%></td>
				<td><%=ap.getAppointmentDate()%></td>
				<td>
					<%
					if (ap.getRemarks() != null) {
					%>
					<form action="admin_print_patient_app.jsp" method="get">
						<input type="hidden" name="id" value="<%=ap.getId()%>">
						<button class="view-btn">View Report</button>
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
			%>
		</table>
	</section>


	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>