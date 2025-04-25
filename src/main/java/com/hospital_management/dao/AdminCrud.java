package com.hospital_management.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.hospital_management.dto.Admin;

public class AdminCrud {

	// SQL to create the Admin table
	private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ADMIN ("
			+ "username VARCHAR(30) UNIQUE, " + "password VARCHAR(255), " + "name VARCHAR(35), "
			+ "email VARCHAR(50) UNIQUE, " + "phone BIGINT(10), " + "doj DATE, " + "designation VARCHAR(20), "
			+ "photo LONGBLOB)";

	// SQL to insert a new Admin data
	private static final String INSERT_ADMIN_SQL = "INSERT INTO ADMIN (username, password, name, email, phone, doj, designation, photo) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ADMIN_SQL = "UPDATE ADMIN SET password=?, name=?, email=?, phone=?, doj=?, designation=?, photo=? WHERE username=?";

	private static final String UPDATE_ADMIN_PASSWORD_SQL = "UPDATE ADMIN SET password=? WHERE username=?";

	// SQL to fetch all data
	private static final String FETCH_ALL_ADMIN_SQL = "SELECT * FROM ADMIN";

	// SQL to fetch particular Doctor data
	private static final String FETCH_BY_USERNAME_ADMIN_SQL = "SELECT * FROM ADMIN WHERE username=?";

	// SQL to fetch particular Doctor data
	private static final String FETCH_BY_EMAIL_ADMIN_SQL = "SELECT * FROM ADMIN WHERE email=?";

	// Create ADMIN table if not exists
	public void createAdminTable() throws ClassNotFoundException, SQLException, IOException {
		try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
			stmt.execute(CREATE_TABLE_SQL);
		}
	}

	// Fetch all Admin record
	public ArrayList<Admin> fetchAll() throws ClassNotFoundException, SQLException, IOException {
		ArrayList<Admin> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_ALL_ADMIN_SQL);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				long phone = rs.getLong(5);
				String doj = rs.getString(6);
				String designation = rs.getString(7);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(8);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				Admin admin = new Admin(username, password, name, email, phone, doj, designation, photo);
				list.add(admin);
			}
		}
		return list;
	}

	// Fetch an Admin record by username
	public Admin fetchByUsername(String username) throws ClassNotFoundException, SQLException, IOException {
		Admin admin = null;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(FETCH_BY_USERNAME_ADMIN_SQL)) {

			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { // Use if, since username should be unique
				String password = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				long phone = rs.getLong(5);
				String doj = rs.getString(6);
				String designation = rs.getString(7);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(8);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				admin = new Admin(username, password, name, email, phone, doj, designation, photo);
			}
		}
		return admin;
	}

	// Fetch an Admin record by username
	public Admin fetchByEmail(String email) throws ClassNotFoundException, SQLException, IOException {
		Admin admin = new Admin();
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(FETCH_BY_EMAIL_ADMIN_SQL)) {

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) { // Use if, since username should be unique
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				long phone = rs.getLong(5);
				String doj = rs.getString(6);
				String designation = rs.getString(7);
				// For fetching a photo
				InputStream photo = null;
				Blob blob = rs.getBlob(8);
				if (blob != null) {
					photo = blob.getBinaryStream();
				}
				admin = new Admin(username, password, name, email, phone, doj, designation, photo);
			}
		}
		return admin;
	}

	// Save new Admin record
	public void save(Admin admin) throws ClassNotFoundException, SQLException, IOException {
		createAdminTable();
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT_ADMIN_SQL)) {
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			pst.setString(3, admin.getName());
			pst.setString(4, admin.getEmail());
			pst.setLong(5, admin.getPhone());
			pst.setString(6, admin.getDoj()); // Make sure date is in yyyy-MM-dd format
			pst.setString(7, admin.getDesignation());
			pst.setBlob(8, admin.getPhoto());
			pst.executeUpdate();
		}
	}

	public int updateByUsername(Admin admin, String username) throws ClassNotFoundException, SQLException, IOException {
		createAdminTable();
		int n = 0;
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE_ADMIN_SQL)) {
			pst.setString(1, admin.getPassword());
			pst.setString(2, admin.getName());
			pst.setString(3, admin.getEmail());
			pst.setLong(4, admin.getPhone());
			pst.setString(5, admin.getDoj()); // Make sure date is in yyyy-MM-dd format
			pst.setString(6, admin.getDesignation());
			pst.setBlob(7, admin.getPhoto());
			pst.setString(8, admin.getUsername());
			n = pst.executeUpdate();
		}
		return n;
	}

	public int updateByUsername(String username, String password)
			throws ClassNotFoundException, SQLException, IOException {
		createAdminTable();
		int n = 0;
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(UPDATE_ADMIN_PASSWORD_SQL)) {
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
