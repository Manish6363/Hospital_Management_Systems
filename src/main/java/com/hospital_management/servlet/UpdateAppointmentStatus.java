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
import com.hospital_management.dto.Appointment;

@WebServlet("/updateAppointmentStatus")
public class UpdateAppointmentStatus extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String doctorUsername = req.getParameter("doctorUsername");
		String appointmentDate = req.getParameter("appointmentDate");
		String status = req.getParameter("action");
		Appointment app = new Appointment(id, null, null, status, doctorUsername, appointmentDate, null, null);
		AppointmentCrud crud = new AppointmentCrud();
		try {
			int n = crud.updateAppointment(app);
			RequestDispatcher rd;
			if (n > 0) {
				rd = req.getRequestDispatcher("admin_appointment_details.jsp");
				rd.include(req, resp);
			} else {
				rd = req.getRequestDispatcher("admin_dashboard.jsp");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
