package com.hospital_management.dto;

import java.io.InputStream;

public class Admin {
	private String username;
	private String password;
	private String name;
	private String email;
	private long phone;
	private String doj;
	private String designation;
	private InputStream photo;

	public Admin() {
		super();
	}

	public Admin(String username, String password, String name, String email, long phone, String doj,
			String designation, InputStream photo) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.doj = doj;
		this.designation = designation;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", doj=" + doj + ", designation=" + designation + ", photo=" + photo + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

}
