package com.hospital_management.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital_management.dao.DoctorCrud;
import com.hospital_management.dto.Doctor;

@WebServlet("/fetchDoctorImage")
public class DoctorImageFetch extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		DoctorCrud crud = new DoctorCrud();
		try {
			Doctor doctor = crud.fetchByUsername(username);
			if (doctor != null && doctor.getPhoto() != null) {
				response.setContentType("image/jpeg");
				InputStream photo = doctor.getPhoto();
				byte[] buffer = new byte[4096];
				OutputStream out = response.getOutputStream();
				while (photo.read(buffer) != -1) {
					out.write(buffer);
				}
				out.flush();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor photo not found");
			}
		} catch (Exception e) {
			throw new ServletException("Error retrieving doctor image", e);
		}
	}
}
