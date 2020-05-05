package com.capgemini.librarymanagement.dto;

public class Student {
	private int studentId;
	private String name;
	private String branch;
	private long mobile;
	private String email;
	private String stPassword;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getStPassword() {
		return stPassword;
	}
	public void setStPassword(String stPassword) {
		this.stPassword = stPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
