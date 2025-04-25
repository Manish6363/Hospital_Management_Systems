package com.hospital_management.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
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

@WebServlet("/doctorregister")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class DoctorRegister extends HttpServlet {
	DoctorCrud crud = new DoctorCrud();

	private int getAge(String dob) {
		int year = Integer.parseInt(dob.split("-")[0]);
		int month = Integer.parseInt(dob.split("-")[1]);
		int day = Integer.parseInt(dob.split("-")[2]);
		LocalDate d = LocalDate.now();
		String date = d.toString();
		int currYear = Integer.parseInt(date.split("-")[0]);
		int currMonth = Integer.parseInt(date.split("-")[1]);
		int currDay = Integer.parseInt(date.split("-")[2]);
		int age = currYear - year;
		// Adjust age if the birthday hasn't occurred yet this year
		if (currMonth < month || (currMonth == month && currDay < day)) {
			age--;
		}
		return age;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String pass = req.getParameter("password");

		// Encode password using Base64
		Encoder encoder = Base64.getEncoder();
		String password = encoder.encodeToString(pass.getBytes());

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long phone = Long.parseLong(req.getParameter("phone"));
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String specialization = req.getParameter("specialization");
		int experience = Integer.parseInt(req.getParameter("experience"));
		String workdesc = req.getParameter("workdesc");
		String license = req.getParameter("license");

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
			if (existingDoctor == null && getAge(dob) > 19) {
				// Save new doctor
				Doctor newDoctor = new Doctor(username, password, name, email, phone, dob, gender, address,
						specialization, experience, workdesc, license, photo);
				crud.save(newDoctor);
				// Redirect or forward with success message
				req.setAttribute("pageName", "doctor_login.jsp");
				req.setAttribute("name", name);
				req.setAttribute("username", username);
				req.setAttribute("password", pass);
				rd = req.getRequestDispatcher("congratulation.jsp");
				rd.forward(req, resp);
			} else if (existingDoctor == null) {
				req.setAttribute("ageMsg", "Your age is <20, therefore you are not eligible..!");
				rd = req.getRequestDispatcher("doctor_register.jsp");
				rd.include(req, resp);
			} else if (existingDoctor.getUsername().equals(username) && existingDoctor.getEmail().equals(email)
					&& existingDoctor.getLicense().equals(license)) {
				req.setAttribute("usernameMsg", "Username is registered. Try with another username..!");
				req.setAttribute("emailMsg", "Email is registered. Try with another email..!");
				req.setAttribute("licenseMsg", "License is registered. Try with another license..!");
				rd = req.getRequestDispatcher("doctor_register.jsp");
				rd.include(req, resp);
			} else if (existingDoctor.getUsername().equals(username)) {
				req.setAttribute("usernameMsg", "Username is registered. Try with another username..!");
				rd = req.getRequestDispatcher("doctor_register.jsp");
				rd.include(req, resp);
			} else if (existingDoctor.getEmail().equals(email)) {
				req.setAttribute("emailMsg", "Email Id is registered. Try with another email..!");
				rd = req.getRequestDispatcher("doctor_register.jsp");
				rd.include(req, resp);
			} else if (existingDoctor.getLicense().equals(license)) {
				req.setAttribute("licenseMsg", "License is registered. Try with another license..!");
				rd = req.getRequestDispatcher("doctor_register.jsp");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while registering the admin.");
			rd = req.getRequestDispatcher("doctor_register.jsp");
			rd.forward(req, resp);
			e.printStackTrace();
		}
	}
}
