package com.job_tracker.attribute_creation;

public class Client {
	String ID;
	String first_name;
	String surname;
	int phone_number;
	boolean previous_client;
	
	private Client(String ID, String first_name, String surname, int phone_number, boolean previous_client) {
		this.ID = ID;
		this.first_name = first_name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.previous_client = previous_client;
	}

}
