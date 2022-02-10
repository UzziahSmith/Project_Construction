package com.job_tracker.database_interaction;

import com.job_tracker.jdbc.Connection_Execute;
import java.lang.Integer;

public class Add_DB {
	
//	private static int Field_Counter(String schema, String table) {}
	
	public static java.lang.Integer Schema_Business_Counter(String url, String username, String password) {
		//SQL command to count the databases in MySQL
		String sql_statement = "SELECT COUNT(*) FROM information_schema.SCHEMATA where schema_name not in ('information_schema', 'mysql', 'performance_schema', 'sys')";
		
		// creates an object and tries to establish a connection with the SQL server, then implements statement.
		Integer schema_count = Connection_Execute.Int_Query_Database(url, username, password, sql_statement);
		if(schema_count != null) {
			System.out.println("Successfully counted " + schema_count + " business schema's.");
			return Connection_Execute.Int_Query_Database(url, username, password, sql_statement);
		} else {
			System.out.println("Unsuccessful count.");
			return null;
		}
	}
	
	private static String Create_ID(char prefix, int count) {
		//converts int count to string and return an id with a count and a prefix
		Integer new_count = Integer.valueOf(count+1);
		String return_string = String.valueOf(prefix) + String.valueOf(new_count);
		return return_string;
	}
	
	public static boolean Business_Schema(String business_name, String url, String username, String password) {
		// SQL command to create a database in MySQL
		String db_url = String.format("%s/%s", url, business_name);
		String sql_statement_business = String.format("CREATE DATABASE IF NOT EXISTS %s", business_name);
		String sql_statement_table_clients = "CREATE TABLE clients ("
				+ "client_id VARCHAR(7), "
				+ "first_name VARCHAR(35), "
				+ "surname VARCHAR(50), "
				+ "phone_number VARCHAR(10), "
				+ "previous_client BOOLEAN, "
				+ "PRIMARY KEY (client_id));";
		String sql_statement_table_trades = "CREATE TABLE trades ("
				+ "trade_id VARCHAR(5), "
				+ "title VARCHAR(50), "
				+ "PRIMARY KEY (trade_id))";
		String sql_statement_table_employees = "CREATE TABLE employees ("
				+ "employee_id VARCHAR(7), "
				+ "first_name VARCHAR(35), "
				+ "surname VARCHAR(50), "
				+ "phone_number VARCHAR(10), "
				+ "trade_id VARCHAR(5), "
				+ "PRIMARY KEY (employee_id), "
				+ "FOREIGN KEY (trade_id) REFERENCES trades(trade_id));";
		String sql_statement_table_locations = "CREATE TABLE locations ("
				+ "street_number VARCHAR(7), "
				+ "street_name VARCHAR(50), "
				+ "postcode INT(4), "
				+ "client_id VARCHAR(7), "
				+ "PRIMARY KEY (street_number, street_name, postcode), "
				+ "FOREIGN KEY (client_id) REFERENCES clients(client_id))";	
		String sql_statement_table_appointments = "CREATE TABLE appointments ("
				+ "appointment_id VARCHAR(14), "
				+ "time TIME, "
				+ "date DATE, "
				+ "brief VARCHAR(999), "
				+ "client_id VARCHAR(7), "
				+ "employee_id VARCHAR(7), "
				+ "PRIMARY KEY (appointment_id), "
				+ "FOREIGN KEY (client_id) REFERENCES clients(client_id), "
				+ "FOREIGN KEY (employee_id) REFERENCES employees(employee_id))";
		
		// establishes connection with the server, processes statement, then tests whether it was successful or not.
		if(Connection_Execute.Command_Database(url, username, password, sql_statement_business)) {
			System.out.println("Database " + business_name + " : successfully created.");
			if(Connection_Execute.Command_Database(db_url, username, password, String.format("USE %s", business_name))) {
				System.out.println("Successfully reset database in use.");
				if(Connection_Execute.Command_Database(db_url, username, password, sql_statement_table_clients)) {
					System.out.println("Database " + business_name + " --> table - CLIENTS : successfully created.");
					if(Connection_Execute.Command_Database(db_url, username, password, sql_statement_table_trades)) {
						System.out.println("Database " + business_name + " --> table - TRADES : successfully created.");
						if(Connection_Execute.Command_Database(db_url, username, password, sql_statement_table_employees)) {
							System.out.println("Database " + business_name + " --> table - EMPLOYEES : successfully created.");
							if(Connection_Execute.Command_Database(db_url, username, password, sql_statement_table_locations)) {
								System.out.println("Database " + business_name + " --> table - LOCATIONS : successfully created.");
								if(Connection_Execute.Command_Database(db_url, username, password, sql_statement_table_appointments)) {
									System.out.println("Database " + business_name + " --> table - APPOINTMENTS : successfully created.");
									return true;
								} else {
									System.out.println("Database " + business_name + " --> table - APPOINTMENTS : failed to create.");
									return false;
								}
							} else {
								System.out.println("Database " + business_name + " --> table - LOCATIONS : failed to create.");
								return false;
							}
						} else {
							System.out.println("Database " + business_name + " --> table - EMPLOYEES : failed to create.");
							return false;
						}
					} else {
						System.out.println("Database " + business_name + " --> table - TRADES : failed to create.");
						return false;
					}
				} else {
					System.out.println("Database " + business_name + " --> table - CLIENTS : failed to create.");
					return false;
				}
			} else {
				System.out.println("Failed to reset database in use.");
				return false;
			}
		} else {
			System.out.println("Database " + business_name + " : failed to create.");
			return false;
		}
	}
	
//	public static boolean Client(String first_name, String surname, String address, int phone_number, boolean previous_client) {}
	
//	public static boolean Employee(String first_name, String surname, int phone_number, String trade_id) {}	
	
//	public static boolean Trade(String title) {}
	
//	public static boolean Location(String location_address, String client_id) {}

//	public static boolean Appointment(String client_id, String employee_id, int time, String date, String brief) {}
	
}
