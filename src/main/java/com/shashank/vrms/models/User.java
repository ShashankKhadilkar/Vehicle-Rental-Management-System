package com.shashank.vrms.models;

import java.sql.Timestamp;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private UserDetails details;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	public UserDetails getDetails() {
		return details;
	}
	public void setDetails(UserDetails details) {
		this.details = details;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", details=" + details + "]";
	}
	
	
	public User(int id, String firstName, String lastName, String email, String password, String role,
			Timestamp createdOn, Timestamp updatedOn, UserDetails details) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.details = details;
	}

}