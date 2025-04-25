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

import com.hospital_management.dao.AdminCrud;
import com.hospital_management.dto.Admin;

@WebServlet("/updateAdmin")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class AdminUpdate extends HttpServlet {
	AdminCrud crud = new AdminCrud();

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
		String doj = req.getParameter("doj");
		String designation = req.getParameter("designation");

		// Process photo
		Part photoPart = req.getPart("photo");
		InputStream photo = null;
		if (photoPart != null) {
			photo = photoPart.getInputStream();
		}

		try {
			// Check if admin with the username already exists
			Admin existingAdmin = crud.fetchByUsername(username);
			if (pass == null || pass.isEmpty()) {
				password = existingAdmin.getPassword();
			}
			if (name == null || name.isEmpty()) {
				name = existingAdmin.getName();
			}
			if (phone == 0) {
				phone = existingAdmin.getPhone();
			}
			if (doj == null || doj.isEmpty()) {
				doj = existingAdmin.getDoj();
			}
			if (designation == null || designation.isEmpty()) {
				designation = existingAdmin.getDesignation();
			}
			if (photoPart == null || photoPart.getSize() == 0) {
				photo = existingAdmin.getPhoto();
			}

			if (!email.isEmpty() && (email != null && crud.fetchByEmail(email) != null)) {
				System.out.println("email==>" + email);
				req.setAttribute("emailMsg", "Email is already exists. Try with another..!");
				RequestDispatcher rd = req.getRequestDispatcher("admin_update_profile.jsp");
				rd.include(req, resp);
			} else {
				if (email == null || email.isEmpty()) {
					email = existingAdmin.getEmail();
				}
				Admin admin = new Admin(username, password, name, email, phone, doj, designation, photo);
				int n = crud.updateByUsername(admin, username);
				if (n > 0) {
					RequestDispatcher rd = req.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(req, resp);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while updating the admin.");
			System.out.println("Already");
			RequestDispatcher rd = req.getRequestDispatcher("admin_update.jsp");
			rd.forward(req, resp);
		}
	}
}
