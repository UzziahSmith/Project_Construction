package com.job_tracker.attribute_creation;

public class Client {
	public String id;
	public String first_name;
	public String surname;
	public int phone_number;
	public boolean previous_client;
	
	public Client(String id, String first_name, String surname, int phone_number, boolean previous_client) {
		this.id = id;
		this.first_name = first_name;
		this.surname = surname;
		this.phone_number = phone_number;
		this.previous_client = previous_client;
	}

}
