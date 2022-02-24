package com.job_tracker.attribute_creation;

public class User {
	public String email;
	public String password;
	public String business;
	public boolean admin;
	
	public User(String email, String password, String business, boolean admin) {
		this.email = email;
		this.password = password;
		this.business = business;
		this.admin = admin;
	}
}