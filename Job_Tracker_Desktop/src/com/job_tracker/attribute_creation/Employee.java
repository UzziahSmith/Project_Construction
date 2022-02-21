package com.job_tracker.attribute_creation;

public class Employee {
	public String id;
	public String first_name;
	public String surname;
	public String phone_number;
	public boolean employed;
	public String trade_id;
	
	public Employee(String id, String first_name, String surname, String phone_number, boolean employed, String trade_id) {
		this.id = id;
		this.first_name = first_name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.employed = employed;
		this.trade_id = trade_id;
	}
}
