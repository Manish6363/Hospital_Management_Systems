package com.hospital_management.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.hospital_management.dto.Patient;

public class PatientCrud {

	// SQL to create the Patient table
	private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS PATIENT ("
			+ "username VARCHAR(30) UNIQUE, " + "password VARCHAR(255), " + "name VARCHAR(35), "
			+ "email VARCHAR(50) UNIQUE, " + "phone BIGINT(10), " + "dob DATE, " + "gender VARCHAR(6), "
			+ "address VARCHAR(500), " + "bloodgroup VARCHAR(3), " + "allergies VARCHAR(255), "
			+ "conditions VARCHAR(100), " + "photo LONGBLOB)";

	// SQL to insert a new Patient data
	private static final String INSERT_PATIENT_SQL = "INSERT INTO PATIENT (username, password, name, email, phone, dob, gender, address, bloodgroup, allergies, conditions, photo) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// SQL to insert a new Patient data
	private static final String UPDATE_PATIENT_SQL = "UPDATE PATIENT SET password=?, name=?, email=?, phone=?, dob=?, gender=?, address=?, bloodgroup=?, allergies=?, conditions=?, photo=? WHERE username=? ";

	private static final String UPDATE_PATIENT_PASSWORD_SQL = "UPDATE PATIENT SET password=? WHERE username=? ";

	// SQL to delete particular Patient data
	private static final String DELETE_BY_USERNAME_PATIENT_SQL = "DELETE FROM PATIENT WHERE username=?";

	// SQL to fetch all Patient data
	private static final String FETCH_ALL_PATIENT_SQL = "SELECT * FROM PATIENT";

	// SQL to fetch particular Patient data
	private static final String FETCH_BY_USERNAME_PATIENT_SQL = "SELECT * FROM PATIENT WHERE username=?";

	// SQL to fetch particular Patient data
	private static final String FETCH_BY_EMAIL_PATIENT_SQL = "SELECT * FROM PATIENT WHERE email=?";

	// SQL to fetch Patient count
	private static final String FETCH_ALL_PATIENT_COUNT_SQL = "SELECT COUNT(*) FROM PATIENT";

	// Create Patient table if not exists
	private void createPatientTable() throws ClassNotFoundException, SQLException, IOException {
		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
			stmt.execute(CREATE_TABLE_SQL);
		}
	}

	public int deleteByUsername(String username) throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		int count = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(DELETE_BY_USERNAME_PATIENT_SQL);) {
			pst.setString(1, username);
			count = pst.executeUpdate();
		}
		return count;
	}

	// Fetch an Patient-count
	public int fetchCount() throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		int count = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_PATIENT_COUNT_SQL);
				ResultSet rs = pst.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt(1); // Gets the value from the first column
			}
		}
		return count;

	}

	// Fetch all Patient record
	public ArrayList<Patient> fetchAll() throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		ArrayList<Patient> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_PATIENT_SQL);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				long phone = rs.getLong(5);
				String dob = rs.getString(6);
				String gender = rs.getString(7);
				String address = rs.getString(8);
				String bloodgroup = rs.getString(9);
				String allergies = rs.getString(10);
				String conditions = rs.getString(11);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(12);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				Patient patient = new Patient(username, password, name, email, phone, dob, gender, address, bloodgroup,
						allergies, conditions, photo);
				list.add(patient);
			}
		}
		return list;
	}

	public Object fetchByEmail(String email) throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		Patient patient = null;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_BY_EMAIL_PATIENT_SQL)) {
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { // Use if, since username should be unique
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				long phone = rs.getLong(5);
				String dob = rs.getString(6);
				String gender = rs.getString(7);
				String address = rs.getString(8);
				String bloodgroup = rs.getString(9);
				String allergies = rs.getString(10);
				String conditions = rs.getString(11);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(12);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				patient = new Patient(username, password, name, email, phone, dob, gender, address, bloodgroup,
						allergies, conditions, photo);
			}
		}
		return patient;
	}

	// Fetch an Patient record by username
	public Patient fetchByUsername(String username) throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		Patient patient = null;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_BY_USERNAME_PATIENT_SQL)) {

			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { // Use if, since username should be unique
				String password = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				long phone = rs.getLong(5);
				String dob = rs.getString(6);
				String gender = rs.getString(7);
				String address = rs.getString(8);
				String bloodgroup = rs.getString(9);
				String allergies = rs.getString(10);
				String conditions = rs.getString(11);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(12);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				patient = new Patient(username, password, name, email, phone, dob, gender, address, bloodgroup,
						allergies, conditions, photo);
			}
		}
		return patient;
	}

	// Save new Patient record
	public void save(Patient patient) throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_PATIENT_SQL)) {
			pst.setString(1, patient.getUsername());
			pst.setString(2, patient.getPassword());
			pst.setString(3, patient.getName());
			pst.setString(4, patient.getEmail());
			pst.setLong(5, patient.getPhone());
			pst.setString(6, patient.getDob());
			pst.setString(7, patient.getGender());
			pst.setString(8, patient.getAddress());
			pst.setString(9, patient.getBloodgroup());
			pst.setString(10, patient.getAllergies());
			pst.setString(11, patient.getConditions());
			pst.setBlob(12, patient.getPhoto());
			pst.executeUpdate();
		}
	}

	public int updateByUsername(Patient patient, String username)
			throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		int n = 0;
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE_PATIENT_SQL)) {
			pst.setString(1, patient.getPassword());
			pst.setString(2, patient.getName());
			pst.setString(3, patient.getEmail());
			pst.setLong(4, patient.getPhone());
			pst.setString(5, patient.getDob());
			pst.setString(6, patient.getGender());
			pst.setString(7, patient.getAddress());
			pst.setString(8, patient.getBloodgroup());
			pst.setString(9, patient.getAllergies());
			pst.setString(10, patient.getConditions());
			pst.setBlob(11, patient.getPhoto());
			pst.setString(12, username);
			n = pst.executeUpdate();
		}
		return n;
	}

	public int updateByUsername(String username, String password)
			throws ClassNotFoundException, SQLException, IOException {
		createPatientTable();
		int n = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(UPDATE_PATIENT_PASSWORD_SQL)) {
			pst.setString(1, password);
			pst.setString(2, username);
			n = pst.executeUpdate();
		}
		return n;
	}

	// Load DB connection using properties file
	private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		Here we have to pass the complete path of dbconfig.properties
		FileInputStream fis = new FileInputStream("D:/Dynamic  Project/Hospital_Management_System/dbconfig.properties");
		Properties info = new Properties();
		info.load(fis);
		String url = "jdbc:mysql://localhost:3306/hospital_management?createDatabaseIfNotExist=true";
		return DriverManager.getConnection(url, info);
	}

}
