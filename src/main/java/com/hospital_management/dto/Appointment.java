package com.hospital_management.dto;

public class Appointment {
	private int id;
	private String patientUsername;
	private String reason;
	private String status;
	private String doctorUsername;
	private String appointmentDate;
	private String appliedDate;
	private String remarks;

	public Appointment() {
		super();
	}

	public Appointment(int id, String patientUsername, String reason, String status, String doctorUsername,
			String appointmentDate, String appliedDate, String remarks) {
		super();
		this.id = id;
		this.patientUsername = patientUsername;
		this.reason = reason;
		this.status = status;
		this.doctorUsername = doctorUsername;
		this.appointmentDate = appointmentDate;
		this.appliedDate = appliedDate;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientUsername=" + patientUsername + ", reason=" + reason + ", status="
				+ status + ", doctorUsername=" + doctorUsername + ", appointmentDate=" + appointmentDate
				+ ", appliedDate=" + appliedDate + ", remarks=" + remarks + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientUsername() {
		return patientUsername;
	}

	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
