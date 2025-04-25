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

@WebServlet("/adminRegister")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class AdminRegister extends HttpServlet {
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
		long phone = Long.parseLong(req.getParameter("phone"));
		String doj = req.getParameter("doj");
		String designation = req.getParameter("designation");

		// Process photo
		Part photoPart = req.getPart("photo");
		InputStream photo = null;
		if (photoPart != null) {
			photo = photoPart.getInputStream();
		}

		System.out.println(username + ", " + password + ", " + name + ", " + email + ", " + phone + ", " + doj + ", "
				+ designation + ", " + photo);
		try {
			crud.createAdminTable();
			// Check if admin with the username already exists
			Admin existingAdmin = crud.fetchByUsername(username);
			if (existingAdmin == null) {
				// Save new admin
				Admin newAdmin = new Admin(username, password, name, email, phone, doj, designation, photo);
				crud.save(newAdmin);
				// Redirect or forward with success message
				req.setAttribute("pageName", "admin_login.jsp");
				req.setAttribute("name", name);
				req.setAttribute("username", username);
				req.setAttribute("password", pass);
				RequestDispatcher rd = req.getRequestDispatcher("congratulation.jsp");
				rd.forward(req, resp);
			} else if (existingAdmin.getUsername().equals(username) && existingAdmin.getEmail().equals(email)) {
				req.setAttribute("usernameMsg", "Username is registered. Try with another username..!");
				req.setAttribute("emailMsg", "Email is registered. Try with another email..!");
				RequestDispatcher rd = req.getRequestDispatcher("admin_register.jsp");
				rd.include(req, resp);
			} else if (existingAdmin.getUsername().equals(username)) {
				req.setAttribute("usernameMsg", "Username is registered. Try with another username..!");
				RequestDispatcher rd = req.getRequestDispatcher("admin_register.jsp");
				rd.include(req, resp);
			} else if (existingAdmin.getEmail().equals(email)) {
				req.setAttribute("emailMsg", "Email Id is registered. Try with another email..!");
				RequestDispatcher rd = req.getRequestDispatcher("admin_register.jsp");
				rd.include(req, resp);
			}

		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while registering the admin.");
			System.out.println("Already");
			RequestDispatcher rd = req.getRequestDispatcher("admin_register.jsp");
			rd.forward(req, resp);
		}
	}
}
