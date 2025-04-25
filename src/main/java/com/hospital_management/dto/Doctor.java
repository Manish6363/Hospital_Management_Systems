package com.hospital_management.dto;

import java.io.InputStream;

public class Doctor {
	private String username;
	private String password;
	private String name;
	private String email;
	private long phone;
	private String dob;
	private String gender;
	private String address;
	private String specialization;
	private int experience;
	private String workdesc;
	private String license;
	private InputStream photo;

	public Doctor() {
		super();
	}

	public Doctor(String username, String password, String name, String email, long phone, String dob, String gender,
			String address, String specialization, int experience, String workdesc, String license, InputStream photo) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.specialization = specialization;
		this.experience = experience;
		this.workdesc = workdesc;
		this.license = license;
		this.photo = photo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null) {
			this.username = username;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null) {
			this.password = password;
		}
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
		if (email != null) {
			this.email = email;
		}
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		if ((phone + "").length() == 10) {
			this.phone = phone;
		}
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		if (experience >= 0) {
			this.experience = experience;
		}
	}

	public String getWorkdesc() {
		return workdesc;
	}

	public void setWorkdesc(String workdesc) {
		this.workdesc = workdesc;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		if (license != null) {
			this.license = license;
		}
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

}
