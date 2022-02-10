package com.job_tracker.attribute_creation;

public class Employee {
	String ID;
	String first_name;
	String surname;
	int phone_number;
	String trade_ID;
	
	private Employee(String ID, String first_name, String surname, int phone_number, String trade_ID) {
		this.ID = ID;
		this.first_name = first_name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.trade_ID = trade_ID;
	}
}
