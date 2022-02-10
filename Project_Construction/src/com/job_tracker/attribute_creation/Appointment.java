package com.job_tracker.attribute_creation;

public class Appointment {
	public String id;
	public String client_id;
	public String employee_id;
	public int time;
	public String date;
	public String brief;
	
	public Appointment(String id, int time, String date, String brief, String client_id, String employee_id) {
		this.id = id;
		this.time = time;
		this.date = date;
		this.brief = brief;
		this.client_id = client_id;
		this.employee_id = employee_id;
	}
}
