package com.job_tracker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Test {
	public static void main(String[] args) throws Exception {
		// register MySQL thin driver with DriverManager service
		Class.forName("com.mysql.cj.jdbc.Driver");
		 
		// variables
		final String url = "jdbc:mysql://localhost:3306";
		final String user = "root";
		final String password = "constructpd173";
		
		// establish the connection
		Connection con = DriverManager.getConnection(url, user, password);
		
		// display status message
		if (con == null) {
			System.out.println("JDBC connection is not established");
			return;
		} else {
			System.out.println("JDBC connection is established successfully.\n");
		}
		con.close();
	}
}
