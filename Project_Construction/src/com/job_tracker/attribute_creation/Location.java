package com.job_tracker.attribute_creation;

public class Location {
	String street_number;
	String street_name;
	int postcode;
	String client_ID;
	
	private Location(String street_number, String street_name, int postcode, String client_ID) {
		this.street_number = street_number;
		this.street_name = street_name;
		this.postcode = postcode;
		this.client_ID = client_ID;
	}
}
