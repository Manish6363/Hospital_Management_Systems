package com.hospital_management.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.hospital_management.dto.Appointment;

public class AppointmentCrud {

	// SQL to create the Appointment table
	private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS APPOINTMENT ( id INT PRIMARY KEY AUTO_INCREMENT, patient_username VARCHAR(30), reason TEXT, status VARCHAR(10) DEFAULT 'Pending', applied_date DATE DEFAULT (CURRENT_DATE), appointment_date DATE, doctor_username VARCHAR(30), remarks VARCHAR(255), FOREIGN KEY (patient_username) REFERENCES patient(username) ON DELETE CASCADE, FOREIGN KEY (doctor_username) REFERENCES doctor(username) ON DELETE CASCADE )";

	// SQL to insert a new Patient data
	private static final String INSERT_APPOINTMENT_SQL = "INSERT INTO APPOINTMENT (patient_username, reason) VALUES (?, ?)";

	// SQL to insert a new Patient data
	private static final String UPDATE_APPOINTMENT_SQL = "UPDATE appointment SET status = ?, appointment_date = ?, doctor_username = ? WHERE id = ?";

	// SQL to insert a new Patient data
	private static final String UPDATE_REMARKS_SQL = "UPDATE appointment SET remarks = ? WHERE id = ?";

	// SQL to fetch all pending Appointment
	private static final String FETCH_ALL_PENDING_APPOINTMENT_SQL = "SELECT * FROM APPOINTMENT WHERE status='Pending'";

	// SQL to fetch all pending Appointment
	private static final String FETCH_APPOINTMENT_BY_ID_SQL = "SELECT * FROM APPOINTMENT WHERE id = ?";

	// SQL to fetch all approved Appointment
	private static final String FETCH_ALL_APPROVED_APPOINTMENT_SQL = "SELECT * FROM APPOINTMENT WHERE status='Approve'";

	// SQL to fetch all approved Appointment
	private static final String FETCH_ALL_APPROVED_APPOINTMENT_BY_USERNAME_SQL = "SELECT * FROM APPOINTMENT WHERE doctor_username =? ORDER BY appointment_date";

	// SQL to fetch all approved Appointment
	private static final String FETCH_APPOINTMENT_BY_DOCTOR_USERNAME_SQL = "SELECT * FROM APPOINTMENT WHERE status='Approve' ORDER BY appointment_date";

	// SQL to fetch all approved Appointment
	private static final String FETCH_ALL_APPOINTMENT_SQL = "SELECT * FROM APPOINTMENT";

	// SQL to fetch all approved Appointment
	private static final String FETCH_APPOINTMENT_BY_USERNAME_SQL = "SELECT * FROM APPOINTMENT WHERE patient_username=? ORDER BY status DESC";

	// Create Appointment table if not exists
	public void createAppointmentTable() throws ClassNotFoundException, SQLException, IOException {
		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
			stmt.execute(CREATE_TABLE_SQL);
			stmt.executeUpdate("ALTER TABLE appointment AUTO_INCREMENT = 1000");
		}
	}

	// Fetch all Appointment by username
	public Appointment fetchById(int id) throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		Appointment appointment = new Appointment();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_APPOINTMENT_BY_ID_SQL)) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				appointment = new Appointment(id, patientUsername, reason, status, doctorUsername, appointmentDate,
						appliedDate, remarks);
			}
		}
		return appointment;
	}

	// Fetch all Appointment by username
	public ArrayList<Appointment> fetchByUsername(String username)
			throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_APPOINTMENT_BY_USERNAME_SQL)) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				Appointment appointment = new Appointment(id, patientUsername, reason, status, doctorUsername,
						appointmentDate, appliedDate, remarks);
				list.add(appointment);
			}
		}
		return list;
	}

	// Fetch all Appointment
	public ArrayList<Appointment> fetchAll() throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_APPOINTMENT_SQL);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				Appointment appointment = new Appointment(id, patientUsername, reason, status, doctorUsername,
						appointmentDate, appliedDate, remarks);
				list.add(appointment);
			}
		}
		return list;
	}

	// Fetch all Appointment whose status is approved
	public ArrayList<Appointment> fetchApprovedAppointment() throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_APPROVED_APPOINTMENT_SQL);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				Appointment appointment = new Appointment(id, patientUsername, reason, status, doctorUsername,
						appointmentDate, appliedDate, remarks);
				list.add(appointment);
			}
		}
		return list;
	}

	// Fetch all Appointment whose status is approved
	public ArrayList<Appointment> fetchApprovedAppointment(String username)
			throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_APPROVED_APPOINTMENT_BY_USERNAME_SQL)) {
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				Appointment appointment = new Appointment(id, patientUsername, reason, status, doctorUsername,
						appointmentDate, appliedDate, remarks);
				list.add(appointment);
			}
		}
		return list;
	}

	// Fetch all Appointment whose status is pending
	public ArrayList<Appointment> fetchPendingAppointment() throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_PENDING_APPOINTMENT_SQL);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String patientUsername = rs.getString(2);
				String reason = rs.getString(3);
				String status = rs.getString(4);
				String appliedDate = rs.getString(5);
				String appointmentDate = rs.getString(6);
				String doctorUsername = rs.getString(7);
				String remarks = rs.getString(8);
				Appointment appointment = new Appointment(id, patientUsername, reason, status, doctorUsername,
						appointmentDate, appliedDate, remarks);
				list.add(appointment);
			}
		}
		return list;
	}

	// Save new Appointment record
	public void save(String username, String reason) throws ClassNotFoundException, SQLException, IOException {
		createAppointmentTable();
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_APPOINTMENT_SQL)) {
			pst.setString(1, username);
			pst.setString(2, reason);
			pst.executeUpdate();
		}
	}

//	UPDATE appointment SET status = ?, appointment_date = ?, doctor_username = ? WHERE id = ?
	public int updateAppointment(Appointment app) throws SQLException, ClassNotFoundException, IOException {
		createAppointmentTable();
		int rowsUpdated = 0;
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE_APPOINTMENT_SQL)) {
			pst.setString(1, app.getStatus());
			pst.setDate(2, java.sql.Date.valueOf(app.getAppointmentDate()));
			pst.setString(3, app.getDoctorUsername());
			pst.setInt(4, app.getId());
			rowsUpdated = pst.executeUpdate();
		}
		return rowsUpdated;
	}

	public int updateRemarks(int id, String remarks) throws SQLException, ClassNotFoundException, IOException {
		createAppointmentTable();
		int rowsUpdated = 0;
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE_REMARKS_SQL)) {
			pst.setString(1, remarks);
			pst.setInt(2, id);
			rowsUpdated = pst.executeUpdate();
		}
		return rowsUpdated;
	}

	// Load DB connection using properties file
	private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Here we have to pass the complete path of dbconfig.properties
		FileInputStream fis = new FileInputStream("D:/Dynamic  Project/Hospital_Management_System/dbconfig.properties");
		Properties info = new Properties();
		info.load(fis);
		String url = "jdbc:mysql://localhost:3306/hospital_management?createDatabaseIfNotExist=true";
		return DriverManager.getConnection(url, info);
	}

}
