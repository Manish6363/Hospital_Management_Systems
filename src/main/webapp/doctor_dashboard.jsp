<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Doctor Dashboard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/footer_style.css" />
<style type="text/css">
#profile {
	margin: 0;
	padding: 40px;
	background-image: linear-gradient(135deg, #E3FDF5 10%, #FFE6FA 100%);
	font-family: 'Segoe UI', sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: auto;
}

.profile-card {
	width: auto;
	background-image: linear-gradient(135deg, #F12711 10%, #F5AF19 100%);
	backdrop-filter: blur(12px);
	border-radius: 20px;
	box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
	padding: 10px;
	color: #fff;
	transition: 0.3s;
	color: #fff;
}

.profile-card:hover {
	transform: scale(1.02);
	box-shadow: 0 25px 60px rgba(0, 0, 0, 0.3);
}

#profile-pic {
	object-fit: cover;
	border-radius: 50%;
	border: 3px solid #fff;
	margin: 0 auto 10px;
	display: block;
}

.profile-info {
	text-align: center;
}

.profile-info h2 {
	margin: 5px 0;
	font-size: 2.5rem;
}

.profile-info p {
	margin: 5px 0;
	font-size: 1.5rem;
	color: #eee;
}

.detail-grid {
	margin-top: 20px;
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 5px;
}

.detail-item {
	background: rgba(255, 255, 255, 0.15);
	padding: 10px;
	border-radius: 12px;
	text-align: center;
}

.detail-item label {
	display: block;
	font-size: 12px;
	color: black;
}

.detail-item span {
	font-weight: bold;
	font-size: 14px;
	color: #fff;
}

@media ( max-width : 320px) {
	.profile-card {
		width: auto;
	}
	#name {
		font-size: 2rem;
	}
	.detail-item label, .detail-item span {
		font-size: .75rem;
	}
}
</style>
</head>
<body>
	<%@include file="component/doctor_dashboard_navbar.jsp"%>

	<%
	DoctorCrud crud = new DoctorCrud();
	Doctor doctor = crud.fetchByUsername(username);
	%>
	<section id="profile">
		<div class="profile-card">
			<img class="profile-pic"
				src="fetchDoctorImage?username=<%=username%>" alt="Profile Picture"
				id="profile-pic" style="width: 200px; height: 200px;" />

			<div class="profile-info">
				<h2 id="name"><%=doctor.getName()%></h2>
				<p><%=doctor.getUsername()%></p>
			</div>

			<div class="detail-grid">
				<div class="detail-item">
					<label>License</label> <span><%=doctor.getLicense()%></span>
				</div>
				<div class="detail-item">
					<label>Email</label> <span><%=doctor.getEmail()%></span>
				</div>
				<div class="detail-item">
					<label>Phone</label> <span><%=doctor.getPhone()%></span>
				</div>
				<div class="detail-item">
					<label>DOB</label> <span><%=doctor.getDob()%></span>
				</div>
				<div class="detail-item">
					<label>Gender</label> <span><%=doctor.getGender()%></span>
				</div>
				<div class="detail-item">
					<label>Address</label> <span><%=doctor.getAddress()%></span>
				</div>
				<div class="detail-item">
					<label>Specialization</label> <span><%=doctor.getSpecialization()%></span>
				</div>
				<div class="detail-item">
					<label>Experience</label> <span><%=doctor.getExperience()%></span>
				</div>
			</div>
		</div>

	</section>
	<%@include file="component/footer.jsp"%>
	<script src="component/login_navbar.js"></script>
</body>
</html>
