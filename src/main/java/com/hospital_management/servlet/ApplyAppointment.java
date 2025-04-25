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

@WebServlet("/applyAppointment")
public class ApplyAppointment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String reason = req.getParameter("reason");

		AppointmentCrud crud = new AppointmentCrud();
		RequestDispatcher rd;
		try {
			crud.save(username, reason);
			rd = req.getRequestDispatcher("admin_appointment_details.jsp");
			rd.forward(req, resp);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception...!");
			e.printStackTrace();
		}
	}

}
