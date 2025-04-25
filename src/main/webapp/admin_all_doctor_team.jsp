<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>All Doctors Team-Admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/login_header_style.css" />
<link rel="stylesheet" href="component/main_body.css">
<link rel="stylesheet" href="component/footer_style.css" />
<style type="text/css">
.delete-btn {
	margin-top: 10px;
	padding: 5px 10px;
	border: none;
	background-color: #ff4d4d;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.delete-btn:hover {
	background-color: #e60000;
}

.card-form {
	text-decoration: none;
	color: inherit;
}

.card-form form {
	margin: 0;
}

.doctor-wrapper {
	text-decoration: none;
	color: inherit;
}
</style>
</head>
<body>
	<%@include file="component/admin_navbar.jsp" %>

	<section>
		<!-- Doctors -->
		<div id="doctor-cards-container">
			<h1>Doctor's Team</h1>
			<div id="doctor-cards">
				<!-- Hardcoded Doctors - Wrapped in anchor tags -->
				<a class="doctor-wrapper"
					href="doctorDetails.jsp?username=arjunmehra">
					<div class="doctor">
						<img src="image/Dr. Arjun Mehra (Cardiothoracic Surgeon).jpg"
							alt="" />
						<h3>Dr. Arjun Mehra</h3>
						<h4>Cardiothoracic Surgeon [18+ yrs Exp]</h4>
						<p class="doctor-desc">A pioneer in minimally invasive heart
							surgeries, Dr. Mehra has performed over 1,000 successful
							open-heart procedures and is renowned for his compassionate
							patient care.</p>
					</div>
				</a> <a class="doctor-wrapper"
					href="doctorDetails.jsp?username=arvindmehra">
					<div class="doctor">
						<img src="image/Dr. Arvind Mehra-Orthopedics.jpg" alt="" />
						<h3>Dr. Arvind Mehra</h3>
						<h4>Orthopedics [19+ yrs Exp]</h4>
						<p class="doctor-desc">Dr. Arvind Mehra is a leading
							Traumatologist with over 19 years of experience, specializing in
							complex trauma, bone, joint, and spine disorders.</p>
					</div>
				</a> <a class="doctor-wrapper"
					href="doctorDetails.jsp?username=ishaverma">
					<div class="doctor">
						<img src="image/Dr. Isha Verma-Endocrinologist.jpg" alt="" />
						<h3>Dr. Isha Verma</h3>
						<h4>Endocrinologist [16+ yrs Exp]</h4>
						<p class="doctor-desc">Expert in managing complex hormonal
							disorders, Dr. Isha is especially focused on diabetes, thyroid,
							and metabolic syndromes.</p>
					</div>
				</a> <a class="doctor-wrapper" 
					href="doctorDetails.jsp?username=kabirsethi">
					<div class="doctor">
						<img src="image/Dr. KabirSethyCancer.jpg" alt="" />
						<h3>Dr. Kabir Sethi</h3>
						<h4>Oncologist (Cancer Specialist) [22+ yrs Exp]</h4>
						<p class="doctor-desc">With a track record of pioneering
							treatment plans, Dr. Kabir is a leading oncologist specializing
							in personalized cancer therapies and clinical trials.</p>
					</div>
				</a>

				<%
				DoctorCrud crud = new DoctorCrud();
				ArrayList<Doctor> drList = crud.fetchAll();
				if (drList != null) {
					for (Doctor dr : drList) {
				%>
				<div class="doctor">
					<!-- Doctor Detail Link -->
					<a class="doctor-wrapper"
						href="doctorDetails.jsp?username=<%=dr.getUsername()%>"> <img
						src="fetchDoctorImage?username=<%=dr.getUsername()%>"
						alt="Doctor Photo" />
						<h3><%=dr.getName()%></h3>
						<h4><%=dr.getSpecialization()%>
							[<%=dr.getExperience()%>
							yrs Exp]
						</h4>
						<p class="doctor-desc"><%=dr.getWorkdesc()%></p>
					</a>

					<!-- Delete Button Form -->
					<form action="deleteDoctorServlet" method="post"
						onsubmit="return confirm('Are you sure, you want to remove this doctor?');">
						<input type="hidden" name="username" value="<%=dr.getUsername()%>">
						<button type="submit" class="delete-btn">Removes</button>
					</form>
					<%
					String failMsg = (String) request.getAttribute("failMsg");
					if (failMsg != null) {
					%>
					<p style="color: red; text-align: center;"><%=failMsg%></p>
					<%
					request.removeAttribute("failMsg");
					}
					%>
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
