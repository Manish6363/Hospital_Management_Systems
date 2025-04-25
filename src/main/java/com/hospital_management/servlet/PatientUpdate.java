package com.hospital_management.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hospital_management.dao.PatientCrud;
import com.hospital_management.dto.Patient;

@WebServlet("/updatePatient")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class PatientUpdate extends HttpServlet {
	PatientCrud crud = new PatientCrud();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String pass = req.getParameter("password");

		// Encode password using Base64
		Encoder encoder = Base64.getEncoder();
		String password = encoder.encodeToString(pass.getBytes());

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone").isEmpty() ? 0 + "" : req.getParameter("phone"));
		String dob = null;
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String bloodgroup = req.getParameter("bloodgroup");
		String allergies = req.getParameter("allergies");
		String conditions = req.getParameter("conditions");

		// Process photo
		Part photoPart = req.getPart("photo");
		InputStream photo = null;
		if (photoPart != null) {
			photo = photoPart.getInputStream();
		}
		RequestDispatcher rd;

		try {
			// Check if patient with the username already exists
			Patient existingPatient = crud.fetchByUsername(username);
			if (pass == null || pass.isEmpty()) {
				password = existingPatient.getPassword();
			}
			if (name == null || name.isEmpty()) {
				name = existingPatient.getName();
			}
			if (phone == 0) {
				phone = existingPatient.getPhone();
			}
			if (dob == null || dob.isEmpty()) {
				dob = existingPatient.getDob();
			}
			if (gender == null || gender.isEmpty()) {
				gender = existingPatient.getGender();
			}
			if (address == null || address.isEmpty()) {
				address = existingPatient.getAddress();
			}
			if (bloodgroup == null || bloodgroup.isEmpty()) {
				bloodgroup = existingPatient.getBloodgroup();
			}
			if (allergies == null || allergies.isEmpty()) {
				allergies = existingPatient.getAllergies();
			}
			if (conditions == null || conditions.isEmpty()) {
				conditions = existingPatient.getConditions();
			}
			if (photoPart == null || photoPart.getSize() == 0) {
				photo = existingPatient.getPhoto();
			}
			if (!email.isEmpty() && (email != null && crud.fetchByEmail(email) != null)) {
				req.setAttribute("emailMsg", "Email is already exists. Try with another..!");
				rd = req.getRequestDispatcher("patient_profile_update.jsp");
				rd.include(req, resp);
			} else {
				if (email == null || email.isEmpty()) {
					email = existingPatient.getEmail();
				}
				Patient patient = new Patient(username, password, name, email, phone, dob, gender, address, bloodgroup, allergies, conditions, photo);
				int n = crud.updateByUsername(patient, username);
				if (n > 0) {
					rd = req.getRequestDispatcher("patient_dashboard.jsp");
					rd.forward(req, resp);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while updating the patient.");
			rd = req.getRequestDispatcher("patient_profile_update.jsp");
			rd.forward(req, resp);
		}
	}
}
