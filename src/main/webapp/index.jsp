<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Welcome to MediCare</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<link rel="stylesheet" href="component/header_style.css">
<link rel="stylesheet" href="component/main_body.css">
<link rel="stylesheet" href="component/footer_style.css">
</head>
<body>
	<%@include file="component/header.jsp"%>
	<main>
		<section>
			<!-- Carousel -->
			<!-- About Us & Carousel Section -->
			<div class="carousel-container">
				<div id="about-us">
					<h1>About</h1>
					<p>
						At <b>MediCare</b>, we believe healthcare is more than just
						treatment—it's about <b>trust, compassion, and excellence</b>. As
						one of the most progressive and patient-focused hospitals in the
						region, MediCare is dedicated to <b>delivering world-class
							medical care</b> through a combination of cutting-edge technology,
						highly qualified specialists, and a deeply human approach. <br />
						<br /> From <b>24/7 emergency services</b> to a team of <b>renowned
							doctors and surgeons</b>, our mission is to be a beacon of hope and
						healing. Every patient who walks through our doors is treated with
						<b>personalized care</b>, guided through their healthcare journey
						with empathy and transparency. <br /> <br /> With
						state-of-the-art infrastructure, innovative medical solutions, and
						a relentless commitment to well-being, <b>MediCare is not just
							a hospital—it’s a partner in your health</b>. We are here for you,
						every step of the way, ensuring that your health is always in the
						best hands. <br /> <br /> <b>MediCare – Where Compassion
							Meets Innovation.</b>
					</p>
					<a href="#">About-us <i
						class="fa-solid fa-square-arrow-up-right"></i></a>
				</div>

				<div class="carousel-slide-wrapper">
					<div class="carousel-slide" id="carousel">
						<img src="image/infrastructure-building.jpg" alt="Image 1" /> <img
							src="image/doctor-team.jpg" alt="Image 2" /> <img
							src="image/emergency.jpg" alt="Image 3" /> <img
							src="image/waiting-hall.jpg" alt="Image 4" /> <img
							src="image/patient-ward.jpg" alt="Image 5" /> <img
							src="image/doctors-and-nurses-surrounding-patient-in-hospital-bed-GJ39A6.jpg"
							alt="Image 6" /> <img src="image/ambulence.jpg" alt="Image 7" />
					</div>
					<div class="carousel-dots" id="dots"></div>
				</div>
			</div>

			<!-- Key Features -->
			<div id="Key-features">
				<h1>Key Features of our Hospital</h1>
				<div id="features">
					<div id="doctor">
						<img src="image/doctor.jpg" alt="Doctor" />
					</div>
					<div class="feature-box">
						<h3>Advanced Medical Technology</h3>
						<p>We utilize cutting-edge diagnostic and surgical equipment
							to ensure accurate results and effective treatments.</p>
					</div>
					<div class="feature-box">
						<h3>24/7 Emergency Services</h3>
						<p>Our emergency department is fully equipped and staffed by
							professionals around the clock, ready to handle any medical
							situation.</p>
					</div>
					<div class="feature-box">
						<h3>Highly Qualified Specialists</h3>
						<p>Our team includes board-certified doctors, surgeons, and
							nurses who bring expertise and compassion to every patient
							interaction.</p>
					</div>
					<div class="feature-box">
						<h3>Patient-Centered Care</h3>
						<p>We prioritize your comfort and wellbeing, offering
							personalized care plans and support throughout your healthcare
							journey.</p>
					</div>
				</div>
			</div>

			<!-- Doctors -->
			<div id="doctor-cards-container">
				<h1>Our Doctor's Team</h1>
				<div id="doctor-cards">
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

					<div class="doctor">
						<img src="image/Dr. Arvind Mehra-Orthopedics.jpg" alt="" />
						<h3>Dr. Arvind Mehra</h3>
						<h4>Orthopedics [19+ yrs Exp]</h4>
						<p class="doctor-desc">Dr. Arvind Mehra is a leading
							Traumatologist with over 19 years of experience, specializing in
							complex trauma, bone, joint, and spine disorders.</p>
					</div>

					<div class="doctor">
						<img src="image/Dr. Isha Verma-Endocrinologist.jpg" alt="" />
						<h3>Dr. Isha Verma</h3>
						<h4>Endocrinologist [16+ yrs Exp]</h4>
						<p class="doctor-desc">Expert in managing complex hormonal
							disorders, Dr. Isha is especially focused on diabetes, thyroid,
							and metabolic syndromes.</p>
					</div>

					<div class="doctor">
						<img src="image/Dr. KabirSethyCancer.jpg" alt="" />
						<h3>Dr. Kabir Sethi</h3>
						<h4>Oncologist (Cancer Specialist) [22+ yrs Exp]</h4>
						<p class="doctor-desc">With a track record of pioneering
							treatment plans, Dr. Kabir is a leading oncologist specializing
							in personalized cancer therapies and clinical trials.</p>
					</div>
					<%
					DoctorCrud crud = new DoctorCrud();
					ArrayList<Doctor> drList = crud.fetchAll();
					if (drList != null) {
						for (Doctor dr : drList) {
					%>
					<div class="doctor">
						<img src="fetchDoctorImage?username=<%=dr.getUsername()%>"
							alt="Doctor Photo" />
						<h3><%=dr.getName()%></h3>
						<h4><%=dr.getSpecialization()%>
							[<%=dr.getExperience()%>
							yrs Exp]
						</h4>
						<p class="doctor-desc"><%=dr.getWorkdesc()%></p>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>
		</section>
	</main>
	<%@include file="component/footer.jsp"%>
	<script src="component/header_script.js"></script>
	<script src="component/carousel_script.js"></script>
</body>
</html>
