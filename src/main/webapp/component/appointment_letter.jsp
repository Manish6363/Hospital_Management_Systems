
<%@page import="java.time.LocalDate"%>
<%@page import="com.hospital_management.dto.Patient"%>
<%@page import="com.hospital_management.dto.Doctor"%>
<%@page import="com.hospital_management.dao.DoctorCrud"%>
<%@page import="com.hospital_management.dao.PatientCrud"%>
<%@page import="com.hospital_management.dto.Appointment"%>
<%@page import="com.hospital_management.dao.AppointmentCrud"%>
<%
int id = Integer.parseInt(request.getParameter("id"));
AppointmentCrud acrud = new AppointmentCrud();
Appointment app = acrud.fetchById(id);
PatientCrud pcrud = new PatientCrud();
Patient patient = pcrud.fetchByUsername(app.getPatientUsername());
DoctorCrud dcrud = new DoctorCrud();
Doctor doctor = dcrud.fetchByUsername(app.getDoctorUsername());
%>
<section>

	<div class="report-card">
		<div class="header">
			<h1>Patient Medical Report</h1>
			<p>
				<strong>Issued by: MediCare Hospital</strong>
			</p>
			<hr>
		</div>

		<div class="statement">This report contains detailed information
			regarding the medical background and current health status of the
			patient, as evaluated by the assigned medical professional. It has
			been thoroughly reviewed and authenticated following a comprehensive
			checkup.</div>

		<div class="statement">The information provided is confidential
			and intended solely for medical assessment, follow-up care, and
			insurance purposes, as authorized. It is part of the hospital's
			standard record-keeping policy, ensuring proper continuity of care.</div>

		<div class="statement">Future medical decisions should be based
			on this report, in conjunction with updated clinical evaluations. The
			details reflect the patient's condition as of the date provided and
			should be reviewed during follow-up visits.</div>

		<div class="section">
			<div class="section-title">Patient Details</div>
			<div>
				<span class="label">Appointment ID:</span><span class="value"><%=app.getId()%></span>
			</div>
			<div>
				<span class="label">Full Name:</span><span class="value"><%=patient.getName()%></span>
			</div>
			<div>
				<span class="label">Gender:</span><span class="value"><%=patient.getGender()%></span>
			</div>
			<div>
				<span class="label">Date of Birth:</span><span class="value"><%=patient.getDob()%></span>
			</div>
			<div>
				<span class="label">Blood Group:</span><span class="value"><%=patient.getBloodgroup()%></span>
			</div>
			<div>
				<span class="label">Phone:</span><span class="value"><%=patient.getPhone()%></span>
			</div>
			<div>
				<span class="label">Email:</span><span class="value"><%=patient.getEmail()%></span>
			</div>
			<div>
				<span class="label">Address:</span><span class="value"><%=patient.getAddress()%></span>
			</div>
			<div>
				<span class="label">Appointment Date:</span><span class="value"><%=app.getAppointmentDate()%></span>
			</div>
			<div>
				<span class="label">Date of Report:</span><span class="value"><%=LocalDate.now()%></span>
			</div>
		</div>

		<div class="section">
			<div class="section-title">Doctor Information</div>
			<div>
				<span class="label">Doctor Name:</span><span class="value"><%=doctor.getName()%></span>
			</div>
			<div>
				<span class="label">Specialization:</span><span class="value"><%=doctor.getSpecialization()%></span>
			</div>
		</div>

		<div class="remarks-section">
			<h3>Doctor's Remarks</h3>
			<p><%=app.getRemarks()%></p>
		</div>

		<div class="statement">This report is considered valid only if
			signed by the attending physician. Alteration of the content in any
			manner is strictly prohibited. Patients are advised to keep a copy of
			this report for their records.</div>

		<div class="signature">
			<p>
				Dr.<%=doctor.getName()%></p>
			<div class="title">Attending Physician</div>
		</div>
	</div>

	<!-- Button outside the report card, at the bottom -->
	<div class="button-area">
		<button class="print-btn" onclick="window.print()">Download
			PDF</button>
	</div>

</section>