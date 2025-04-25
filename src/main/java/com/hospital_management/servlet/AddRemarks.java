package com.hospital_management.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital_management.dao.AppointmentCrud;

@WebServlet("/addRemarks")
public class AddRemarks extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String remarks = req.getParameter("remarks");
		AppointmentCrud crud = new AppointmentCrud();
		try {
			int n = crud.updateRemarks(id, remarks);
			RequestDispatcher rd = req.getRequestDispatcher("doctor_dashboard.jsp");
			rd.forward(req, resp);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
