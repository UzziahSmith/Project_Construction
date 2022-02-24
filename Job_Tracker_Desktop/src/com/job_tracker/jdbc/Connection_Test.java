package com.job_tracker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import application.Main;

public class Connection_Test {
	// variables
	public static void main(String[] args) throws Exception {
		try {
			// register MySQL thin driver with DriverManager service
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// establish the connection
			Connection con = DriverManager.getConnection(Main.url, Main.user, Main.password);
			
			// display status message
			if (con == null) {
				System.out.println("JDBC connection is not established");
				return;
			} else {
				System.out.println("JDBC connection is established successfully.\n");
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
