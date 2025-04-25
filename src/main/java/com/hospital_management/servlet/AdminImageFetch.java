package com.hospital_management.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital_management.dao.AdminCrud;
import com.hospital_management.dto.Admin;

@WebServlet("/fetchAdminImage")
public class AdminImageFetch extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		AdminCrud crud = new AdminCrud();
		try {
			Admin admin = crud.fetchByUsername(username);
			if (admin != null && admin.getPhoto() != null) {
				resp.setContentType("image/jpeg");
				InputStream photo = admin.getPhoto();
				byte[] buffer = new byte[4096];
				OutputStream out = resp.getOutputStream();
				while (photo.read(buffer) != -1) {
					out.write(buffer);
				}
				out.flush();
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Admin photo not found");
			}
		} catch (Exception e) {
			throw new ServletException("Error retrieving admin image", e);
		}
	}
}
