package com.hospital_management.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital_management.dao.DoctorCrud;

@WebServlet("/deleteDoctorServlet")
public class DeleteDoctorServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		DoctorCrud crud = new DoctorCrud();
		boolean success;
		try {
			success = crud.deleteDoctorByUsername(username);
			if (success) {
				resp.sendRedirect("admin_all_doctor_team.jsp");
			} else {
				req.setAttribute("failMsg", "Failed to remove doctor.");
				RequestDispatcher rd = req.getRequestDispatcher("doctorDetails.jsp");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			req.setAttribute("failMsg", "Failed to remove doctor.");
			RequestDispatcher rd = req.getRequestDispatcher("doctorDetails.jsp");
			rd.include(req, resp);
			e.printStackTrace();
		}
	}
}