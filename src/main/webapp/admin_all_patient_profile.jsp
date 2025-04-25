<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@page import="com.hospital_management.dto.Appointment"%>
<%@page import="com.hospital_management.dao.AppointmentCrud"%>
<%@page import="com.hospital_management.dto.Patient"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Patient's Record</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style>
section {
	padding-top: 50px;
	padding-bottom: 50px;
}

#table_struct {
	width: auto;
	margin: auto;
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
	background: #E6E6FA;
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
	margin: auto;
	border-radius: 12px;
	object-fit: cover;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
	margin-top: 5px;
	transition: transform 0.3s ease;
	border-radius: 12px;
}

#profile:hover {
	transform: scale(1.05);
}

@media ( max-width : 615px) {
	section {
		margin: auto;
		padding-bottom: 30px;
		padding-top: 30px;
	}
	#table_struct {
		margin: auto;
		padding: 2px;
	}
	.heading, .column {
		padding: 5px;
		margin: 0;
		font-size: .8rem;
	}
	#profile {
		width: 100px;
		height: 100px;
		border-radius: 1px;
	}
}

@media ( max-width : 410px) {
	section {
		margin: auto;
		padding-bottom: 20px;
		padding-top: 20px;
	}
	#table_struct {
		margin: auto;
		padding: 2px;
		border-radius: 5px;
	}
	.heading, .column {
		padding: 2px;
		margin: 0;
		font-size: .5rem;
	}
	#profile {
		width: 60px;
		height: 60px;
		border-radius: 1px;
	}
}
</style>
</head>
<body>
	<%@include file="component/admin_navbar.jsp" %>

	<%
	String patientUsername = request.getParameter("pusername");
	PatientCrud pcrud = new PatientCrud();
	Patient patient = pcrud.fetchByUsername(patientUsername);
	%>

	<section>
		<table id="table_struct">
			<tr class="row">
				<th class="heading">Username</th>
				<td class="column"><%=patientUsername%></td>
				<td class="column" rowspan="4" colspan="4"
					style="text-align: center; vertical-align: top;"><img
					src="fetchPatientImage?username=<%=patientUsername%>"
					alt="Patient Photo" id="profile" /></td>
			</tr>
			<tr class="row">
				<th class="heading">Name</th>
				<td class="column"><%=patient.getName()%></td>
			</tr>
			<tr class="row">
				<th class="heading">Email</th>
				<td class="column"><%=patient.getEmail()%></td>
			</tr>
			<tr class="row">
				<th class="heading">Phone</th>
				<td class="column" ><%=patient.getPhone()%></td>
			</tr>
			<!-- Remaining fields -->
			<tr class="row">
				<th class="heading">Date of Birth</th>
				<td class="column" colspan="2"><%=patient.getDob()%></td>
				<th class="heading">Gender</th>
				<td class="column" colspan="2"><%=patient.getGender()%></td>
			</tr>
			<tr class="row">
				<th class="heading">Address</th>
				<td class="column" colspan="2" style="width: 30px"><%=patient.getAddress()%></td>
				<th class="heading">Blood Group</th>
				<td class="column" colspan="2"><%=patient.getBloodgroup()%></td>
			</tr>
			<tr class="row">
				<th class="heading">Allergies</th>
				<td class="column" colspan="2"><%=patient.getAllergies()%></td>
				<th class="heading">Conditions</th>
				<td class="column" colspan="2"><%=patient.getConditions()%></td>
			</tr>
			<tr class="row">
				<th class="heading">Appointment ID</th>
				<th class="heading">Reason</th>
				<th class="heading">Appointment Date</th>
				<th class="heading">Doctor Name</th>
				<th class="heading">Remarks</th>
			</tr>
			<%
			AppointmentCrud appCrud = new AppointmentCrud();
			ArrayList<Appointment> list = appCrud.fetchByUsername(patientUsername);
			for (Appointment ap : list) {
				Doctor doc = new DoctorCrud().fetchByUsername(ap.getDoctorUsername());
				String docname = "Not Assigned";
				if (doc != null)
					docname = doc.getName();
			%>
			<tr class="row">
				<th class="heading"><%=ap.getId()%></th>
				<td class="column"><%=ap.getReason()%></td>
				<td class="column"><%=ap.getAppointmentDate() != null ? ap.getAppointmentDate() : "Waiting"%></td>
				<td class="column"><%=docname%></td>
				<td class="column"><%=ap.getRemarks() != null ? ap.getRemarks() : "Waiting"%></td>
			</tr>
			<%
			}
			%>
		</table>
	</section>


	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
