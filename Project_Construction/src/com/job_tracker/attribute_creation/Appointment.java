package com.job_tracker.attribute_creation;

public class Appointment {
	String ID;
	String client_ID;
	String employee_ID;
	int time;
	String date;
	String brief;
	
	private Appointment(String ID, String client_ID, String employee_ID, int time, String date, String brief) {
		this.ID = ID;
		this.client_ID = client_ID;
		this.employee_ID = employee_ID;
		this.time = time;
		this.date = date;
		this.brief = brief;
	}
}
