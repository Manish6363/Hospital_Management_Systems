package com.hospital_management.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital_management.dao.AdminCrud;

@WebServlet("/changeAdminPass")
public class AdminPassChange extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String pass = req.getParameter("password");

		// Encode password using Base64
		Encoder encoder = Base64.getEncoder();
		String password = encoder.encodeToString(pass.getBytes());

		try {
			AdminCrud crud = new AdminCrud();
			int n = crud.updateByUsername(username, password);
			if (n > 0) {
				RequestDispatcher rd = req.getRequestDispatcher("admin_dashboard.jsp");
				rd.forward(req, resp);
			} else {
				req.setAttribute("message", "An error occurred while updating the password.");
				RequestDispatcher rd = req.getRequestDispatcher("admin_password_change.jsp");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("message", "An error occurred while updating the password.");
			RequestDispatcher rd = req.getRequestDispatcher("admin_password_change.jsp");
			rd.forward(req, resp);
		}
	}

}

