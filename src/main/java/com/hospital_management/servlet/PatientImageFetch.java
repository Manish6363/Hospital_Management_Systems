package com.hospital_management.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital_management.dao.PatientCrud;
import com.hospital_management.dto.Patient;

@WebServlet("/fetchPatientImage")
public class PatientImageFetch extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		PatientCrud crud = new PatientCrud();
		try {
			Patient patient = crud.fetchByUsername(username);
			if (patient != null && patient.getPhoto() != null) {
				resp.setContentType("image/jpeg");
				InputStream photo = patient.getPhoto();
				byte[] buffer = new byte[4096];
				OutputStream out = resp.getOutputStream();
				while (photo.read(buffer) != -1) {
					out.write(buffer);
				}
				out.flush();
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient photo not found");
			}
		} catch (Exception e) {
			throw new ServletException("Error retrieving patient image", e);
		}
	}
}
