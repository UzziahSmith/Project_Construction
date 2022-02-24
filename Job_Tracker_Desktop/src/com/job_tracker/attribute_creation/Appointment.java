package com.job_tracker.attribute_creation;

public class Appointment {
	public String id;
	public String client_id;
	public String employee_id;
	public String time;
	public String date;
	public String brief;
	public String feedback;
	public String street_number;
	public String street_name;
	public int postcode;
	
	public Appointment(String id, String time, String date, String brief, String feedback, String client_id, String employee_id, String street_number, String street_name, int postcode) {
		this.id = id;
		this.time = time;
		this.date = date;
		this.brief = brief;
		this.feedback = feedback;
		this.client_id = client_id;
		this.employee_id = employee_id;
		this.street_number = street_number;
		this.street_name = street_name;
		this.postcode = postcode;
	}
}
