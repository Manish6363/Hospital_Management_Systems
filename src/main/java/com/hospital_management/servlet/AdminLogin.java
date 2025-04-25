package com.hospital_management.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital_management.dao.AdminCrud;
import com.hospital_management.dto.Admin;

@WebServlet("/admin-Login")
public class AdminLogin extends HttpServlet {
	AdminCrud crud = new AdminCrud();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		RequestDispatcher rd;
		try {
			Admin admin = crud.fetchByUsername(username);
			if (admin != null) {
				// Decoding the encoded password fetched from Database
				Decoder decoder = Base64.getDecoder();
				byte[] decodedBytes = decoder.decode(admin.getPassword());
				String decodedPassword = new String(decodedBytes);
				if (password.equals(decodedPassword)) {
					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("name", admin.getName().split(" ")[0]);
					req.setAttribute("usernameMsg", "Invalid username. Please try again..!");
					rd = req.getRequestDispatcher("admin_dashboard.jsp");
					rd.forward(req, resp);
				} else {
					req.setAttribute("passwordMsg", "Invalid password. Please try again..!");
					rd = req.getRequestDispatcher("admin_login.jsp");
					rd.include(req, resp);
				}
			} else {
				req.setAttribute("usernameMsg", "Invalid username. Please try again..!");
				rd = req.getRequestDispatcher("admin_login.jsp");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			req.setAttribute("message", "An error occurred while loging the admin..!");
			rd = req.getRequestDispatcher("admin_login.jsp");
			rd.include(req, resp);
			e.printStackTrace();
		}
	}

}
