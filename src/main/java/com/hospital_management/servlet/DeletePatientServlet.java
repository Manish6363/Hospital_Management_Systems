package com.hospital_management.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital_management.dao.PatientCrud;

@WebServlet("/deletePatientServlet")
public class DeletePatientServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("pUsername");
		PatientCrud crud = new PatientCrud();
		try {
			int n = crud.deleteByUsername(username);
			
			RequestDispatcher rd = req.getRequestDispatcher("admin_all_patients_record.jsp");
			rd.include(req, resp);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}
