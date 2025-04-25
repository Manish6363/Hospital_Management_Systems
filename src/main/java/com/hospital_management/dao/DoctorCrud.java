package com.hospital_management.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.hospital_management.dto.Doctor;

public class DoctorCrud {

	// SQL to create the Doctor table
	private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS DOCTOR ("
			+ "username VARCHAR(30) UNIQUE, " + "password VARCHAR(255), " + "name VARCHAR(35), "
			+ "email VARCHAR(50) UNIQUE, " + "phone BIGINT(10), " + "dob DATE, " + "gender VARCHAR(6), "
			+ "address VARCHAR(500), " + "specialization VARCHAR(30), " + "experience INT(2), "
			+ "workdesc VARCHAR(1000), " + "license VARCHAR(10) UNIQUE, " + "photo LONGBLOB)";

	// SQL to insert a new Doctor data
	private static final String INSERT_DOCTOR_SQL = "INSERT INTO DOCTOR (username, password, name, email, phone, dob, gender, address, specialization, experience, workdesc, license, photo) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_DOCTOR_SQL = "UPDATE DOCTOR SET password=?, name=?, email=?, phone=?, dob=?, gender=?, address=?, specialization=?, experience=?, workdesc=?, license=?, photo=? WHERE username=?";

	private static final String UPDATE_DOCTOR_PASSWORD_SQL = "UPDATE DOCTOR SET password=? WHERE username=?";

	// SQL to fetch all Doctor data
	private static final String FETCH_ALL_DOCTOR_SQL = "SELECT * FROM DOCTOR";

	// SQL to fetch particular Doctor data
	private static final String FETCH_BY_USERNAME_DOCTOR_SQL = "SELECT * FROM DOCTOR WHERE username=?";

	// SQL to fetch particular Doctor data
	private static final String FETCH_BY_EMAIL_DOCTOR_SQL = "SELECT * FROM DOCTOR WHERE email=?";

	// SQL to fetch Doctor count
	private static final String FETCH_ALL_DOCTOR_COUNT_SQL = "SELECT COUNT(*) FROM DOCTOR";
	// SQL to delete Doctor by username
	private static final String DELETE_DOCTOR_BY_USERNAME_SQL = "DELETE FROM DOCTOR WHERE username=?";

	// Create Doctor table if not exists
	private void createDoctorTable() throws ClassNotFoundException, SQLException, IOException {
		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
			stmt.execute(CREATE_TABLE_SQL);
		}
	}

	public boolean deleteDoctorByUsername(String username) throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(DELETE_DOCTOR_BY_USERNAME_SQL)) {

			pst.setString(1, username);
			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		}
	}

	// Fetch an Doctor-count
	public int fetchCount() throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		int count = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_DOCTOR_COUNT_SQL);
				ResultSet rs = pst.executeQuery()) {

			if (rs.next()) {
				count = rs.getInt(1); // Gets the value from the first column
			}
		}
		return count;
	}

	// Fetch all Doctor record
	public ArrayList<Doctor> fetchAll() throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		ArrayList<Doctor> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_DOCTOR_SQL);
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
				String specialization = rs.getString(9);
				int experience = rs.getInt(10);
				String workdesc = rs.getString(11);
				String license = rs.getString(12);
				// For fetching a photo
				Blob blob = rs.getBlob(13);
				InputStream photo = null;
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				Doctor doctor = new Doctor(username, password, name, email, phone, dob, gender, address, specialization,
						experience, workdesc, license, photo);
				list.add(doctor);
			}
		}
		return list;
	}

	// Fetch an Doctor record by username
	public Doctor fetchByUsername(String username) throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		Doctor doctor = null;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_BY_USERNAME_DOCTOR_SQL)) {

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
				String specialization = rs.getString(9);
				int experience = rs.getInt(10);
				String workdesc = rs.getString(11);
				String license = rs.getString(12);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(13);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				doctor = new Doctor(username, password, name, email, phone, dob, gender, address, specialization,
						experience, workdesc, license, photo);
			}
		}
		return doctor;
	}

	public Doctor fetchByEmail(String email) throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		Doctor doctor = new Doctor();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_BY_EMAIL_DOCTOR_SQL)) {
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
				String specialization = rs.getString(9);
				int experience = rs.getInt(10);
				String workdesc = rs.getString(11);
				String license = rs.getString(12);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(13);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				doctor = new Doctor(username, password, name, email, phone, dob, gender, address, specialization,
						experience, workdesc, license, photo);
			}
		}
		return doctor;
	}

	// Save new Doctor record
	public void save(Doctor doctor) throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_DOCTOR_SQL)) {
			pst.setString(1, doctor.getUsername());
			pst.setString(2, doctor.getPassword());
			pst.setString(3, doctor.getName());
			pst.setString(4, doctor.getEmail());
			pst.setLong(5, doctor.getPhone());
			pst.setString(6, doctor.getDob());
			pst.setString(7, doctor.getGender());
			pst.setString(8, doctor.getAddress());
			pst.setString(9, doctor.getSpecialization());
			pst.setInt(10, doctor.getExperience());
			pst.setString(11, doctor.getWorkdesc());
			pst.setString(12, doctor.getLicense());
			pst.setBlob(13, doctor.getPhoto());
			pst.executeUpdate();
		}
	}

	public int updateByUsername(Doctor doctor, String username)
			throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		int n = 0;
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE_DOCTOR_SQL)) {
			pst.setString(1, doctor.getPassword());
			pst.setString(2, doctor.getName());
			pst.setString(3, doctor.getEmail());
			pst.setLong(4, doctor.getPhone());
			pst.setString(5, doctor.getDob());
			pst.setString(6, doctor.getGender());
			pst.setString(7, doctor.getAddress());
			pst.setString(8, doctor.getSpecialization());
			pst.setInt(9, doctor.getExperience());
			pst.setString(10, doctor.getWorkdesc());
			pst.setString(11, doctor.getLicense());
			pst.setBlob(12, doctor.getPhoto());
			pst.setString(13, username);
			n = pst.executeUpdate();
		}
		return n;
	}

	public int updateByUsername(String username, String password)
			throws ClassNotFoundException, SQLException, IOException {
		createDoctorTable();
		int n = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(UPDATE_DOCTOR_PASSWORD_SQL)) {
			pst.setString(1, password);
			pst.setString(2, username);
			n = pst.executeUpdate();
		}
		return n;
	}

	// Load DB connection using properties file
	private Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
//			Here we have to pass the complete path of dbconfig.properties
		FileInputStream fis = new FileInputStream("D:/Dynamic  Project/Hospital_Management_System/dbconfig.properties");
		Properties info = new Properties();
		info.load(fis);
		String url = "jdbc:mysql://localhost:3306/hospital_management?createDatabaseIfNotExist=true";
		return DriverManager.getConnection(url, info);
	}

}
