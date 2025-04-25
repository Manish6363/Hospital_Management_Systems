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
<title>All Patients Record-Admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style>
:root {
	--head-background-color: #32cd32;
	--accent-color: #218838;
	--text-color-white: #fff;
	--text-dark: #2d2d2d;
	--hover: #d4f7d4;
	--card-bg: linear-gradient(135deg, #E3FDF5 10%, #FFE6FA 100%);
	--shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

section {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
	margin: 0;
	padding: 0;
	font-size: .7rem;
}

#patient-cards-container {
	width: 90%;
	margin: 50px auto;
}

#patient-cards-container h1 {
	text-align: center;
	margin-bottom: 20px;
	color: var(--text-dark);
}

#patient-cards {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 20px;
}

.card-link {
	text-decoration: none;
	color: inherit;
}

.patient {
	background: var(--card-bg);
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	border-radius: 15px;
	box-shadow: var(--shadow);
	transition: transform 0.3s;
	flex-wrap: wrap;
}

.patient:hover {
	transform: translateY(-5px);
}

.patient-details {
	flex: 2;
	padding-right: 10px;
}

.patient-details h3, .patient-details h4 {
	margin: 5px 0;
	color: var(--text-dark);
}

.patient-image {
	flex: 1;
	text-align: center;
}

.patient-image img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	object-fit: cover;
	box-shadow: 0 0 8px rgba(0, 0, 0, 0.15);
}

.delete-form {
	width: 100%;
	text-align: center;
	margin-top: 5px;
}

.delete-btn {
	background-color: #ff4d4d;
	color: white;
	padding: 5px 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.delete-btn:hover {
	background-color: #cc0000;
}

@media ( max-width : 600px) {
	#patient-cards {
		display: flex;
		flex-direction: column;
	}
	#patient-cards {
		width: 70%;
		margin: auto;
	}
	.patient-details {
		padding-right: 0;
	}
	.patient-image {
		margin-top: 15px;
	}
}
</style>
</head>
<body>
	<%@include file="component/admin_navbar.jsp" %>

	<section>
		<div id="patient-cards-container">
			<h1 style="font-size: 2rem; color: var(--accent-color);">Patient
				Record</h1>
			<div id="patient-cards">
				<%
				PatientCrud crud = new PatientCrud();
				ArrayList<Patient> drList = crud.fetchAll();
				if (drList != null) {
					for (Patient p : drList) {
				%>
				<div class="patient">
					<!-- Left Side: Details -->
					<div class="patient-details"
						onclick="location.href='admin_all_patient_profile.jsp?pusername=<%=p.getUsername()%>'">
						<h3>
							<strong>Name:</strong>
							<%=p.getName()%></h3>
						<h3>
							<strong>DOB:</strong>
							<%=p.getDob()%></h3>
						<h3>
							<strong>Gender:</strong>
							<%=p.getGender()%></h3>
						<h3>
							<strong>Blood Group:</strong>
							<%=p.getBloodgroup()%></h3>
						<h4>
							<strong>Allergies:</strong>
							<%=p.getAllergies()%></h4>
					</div>

					<!-- Right Side: Image -->
					<div class="patient-image">
						<a href="admin_all_patient_profile.jsp?pusername=<%=p.getUsername()%>"> <img
							src="fetchPatientImage?username=<%=p.getUsername()%>"
							alt="Patient Photo" />
						</a>
					</div>

					<!-- Bottom: Delete Button -->
					<div class="delete-form">
						<form action="deletePatientServlet" method="POST"
							onsubmit="return confirm('Are you sure you want to remove this patient?');">
							<input type="hidden" name="pUsername"
								value="<%=p.getUsername()%>">
							<button type="submit" class="delete-btn">Remove</button>
						</form>
						<%
						String failMsg = (String) request.getAttribute("failMsg");
						if (failMsg != null) {
						%>
						<p style="color: red; margin-top: 5px;"><%=failMsg%></p>
						<%
						request.removeAttribute("failMsg");
						}
						%>
					</div>
				</div>
				<%
				}
				}
				%>
			</div>
		</div>
	</section>


	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
