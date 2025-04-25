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

import com.hospital_management.dao.DoctorCrud;
import com.hospital_management.dto.Doctor;

@WebServlet("/updateDoctor")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class DoctorUpdate extends HttpServlet {
	DoctorCrud crud = new DoctorCrud();

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
		String specialization = req.getParameter("specialization");
		int experience = Integer.parseInt(req.getParameter("experience").isEmpty() ? 0 + "" :req.getParameter("experience"));
		String workdesc = req.getParameter("workdesc");
		String license = null;

		// Process photo
		Part photoPart = req.getPart("photo");
		InputStream photo = null;
		if (photoPart != null) {
			photo = photoPart.getInputStream();
		}
		RequestDispatcher rd;

		try {
			// Check if doctor with the username already exists
			Doctor existingDoctor = crud.fetchByUsername(username);
			if (pass == null || pass.isEmpty()) {
				password = existingDoctor.getPassword();
			}
			if (name == null || name.isEmpty()) {
				name = existingDoctor.getName();
			}
			if (phone == 0) {
				phone = existingDoctor.getPhone();
			}
			if (dob == null || dob.isEmpty()) {
				dob = existingDoctor.getDob();
			}
			if (gender == null || gender.isEmpty()) {
				gender = existingDoctor.getGender();
			}
			if (address == null || address.isEmpty()) {
				address = existingDoctor.getAddress();
			}
			if (specialization == null || specialization.isEmpty()) {
				specialization = existingDoctor.getSpecialization();
			}
			if (workdesc == null || workdesc.isEmpty()) {
				workdesc = existingDoctor.getWorkdesc();
			}
			if (license == null || license.isEmpty()) {
				license = existingDoctor.getLicense();
			}
			if (photoPart == null || photoPart.getSize() == 0) {
				photo = existingDoctor.getPhoto();
			}
			if (!email.isEmpty() && (email != null && crud.fetchByEmail(email) != null)) {
				req.setAttribute("emailMsg", "Email is already exists. Try with another..!");
				rd = req.getRequestDispatcher("admin_update_profile.jsp");
				rd.include(req, resp);
			} else {
				if (email == null || email.isEmpty()) {
					email = existingDoctor.getEmail();
				}
				Doctor doctor = new Doctor(username, password, name, email, phone, dob, gender, address, specialization,
						experience, workdesc, license, photo);
				int n = crud.updateByUsername(doctor, username);
				if (n > 0) {
					rd = req.getRequestDispatcher("doctor_dashboard.jsp");
					rd.forward(req, resp);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while updating the doctor.");
			rd = req.getRequestDispatcher("doctor_update.jsp");
			rd.forward(req, resp);
		}
	}
}
