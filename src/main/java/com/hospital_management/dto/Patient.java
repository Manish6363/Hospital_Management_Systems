package com.hospital_management.dto;

import java.io.InputStream;

public class Patient {
	private String username;
	private String password;
	private String name;
	private String email;
	private long phone;
	private String dob;
	private String gender;
	private String address;
	private String bloodgroup;
	private String allergies;
	private String conditions;
	private InputStream photo;

	public Patient() {
		super();
	}

	public Patient(String username, String password, String name, String email, long phone, String dob, String gender,
			String address, String bloodgroup, String allergies, String conditions, InputStream photo) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.bloodgroup = bloodgroup;
		this.allergies = allergies;
		this.conditions = conditions;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Patient [username=" + username + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", dob=" + dob + ", gender=" + gender + ", address=" + address + ", bloodgroup="
				+ bloodgroup + ", allergies=" + allergies + ", conditions=" + conditions + ", photo=" + photo + "]";
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

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

}
