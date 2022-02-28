package com.job_tracker.attribute_creation;

public class Location {
	public String id;
	public String street_number;
	public String street_name;
	public int postcode;
	public String client_id;
	
	public Location(String id, String street_number, String street_name, int postcode, String client_id) {
		this.id = id;
		this.street_number = street_number;
		this.street_name = street_name;
		this.postcode = postcode;
		this.client_id = client_id;
	}
}
 