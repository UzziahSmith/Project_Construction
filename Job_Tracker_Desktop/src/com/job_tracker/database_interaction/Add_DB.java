package com.job_tracker.database_interaction;

import com.job_tracker.attribute_creation.*;
import com.job_tracker.jdbc.Connection_Execute;

import application.Main;

import java.lang.Integer;
import java.util.List;

public class Add_DB {
	
	public static Integer Record_Counter(String url, String username, String password, String schema, String table) {
		// Setup sql command statement to count records.
		String sql_statement = String.format("SELECT COUNT(*) FROM %s.%s", schema, table);
		
		Integer schema_count = Connection_Execute.Int_Query_Database(url, username, password, sql_statement);
		if(schema_count != null) {
			System.out.println("Successfully counted " + schema_count + " records");
			return schema_count;
		} else {
			System.out.println("Failed to count records.");
			return null;
		}
	}
	
	public static Integer Schema_Business_Counter(String url, String username, String password) {
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
	
	@SuppressWarnings("null")
	private static int get_last_record(List<Appointment> appt_list, List<Client> client_list, List<Employee> employee_list, List<Trade> trade_list) {
		String id;
		String parse_string = null;
		int parse_val;
		if(appt_list != null) {
			int max_pos = appt_list.size()-1;
			id = appt_list.get(max_pos).id;
		} else if(client_list != null) {
			int max_pos = client_list.size()-1;
			id = client_list.get(max_pos).id;
		} else if(employee_list != null) {
			int max_pos = employee_list.size()-1;
			id = employee_list.get(max_pos).id;
		} else if(trade_list != null) {
			int max_pos = trade_list.size()-1;
			id = trade_list.get(max_pos).id;
		} else {
			return (Integer) null;
		}
		for(int i = 1; i < id.length(); i++) {
			if(parse_string == null) {
				parse_string = String.valueOf(id.charAt(1));
			} else {
				parse_string += id.charAt(i);
			}
		}
		parse_val = Integer.parseInt(parse_string);
		return parse_val;
	}
	
	private static String Create_ID(char prefix, int count) {
		//converts int count to string and return an id with a count and a prefix
		Integer new_count = Integer.valueOf(count+1);
		String string_new_count = String.valueOf(new_count);
		String return_string = prefix + string_new_count;
		return return_string;
	}
	
	public static boolean Business_Schema(String business_name, String url, String username, String password) {
		// SQL command to create a database in MySQL
		String db_url = String.format("%s/%s", url, business_name);
		// Creates SQL commands that outlines the creation of each table.
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
				+ "employed BOOLEAN, "
				+ "trade_id VARCHAR(5), "
				+ "PRIMARY KEY (employee_id), "
				+ "FOREIGN KEY (trade_id) REFERENCES trades(trade_id));";
		String sql_statement_table_locations = "CREATE TABLE locations ("
				+ "location_id VARCHAR(10), "
				+ "street_number VARCHAR(7), "
				+ "street_name VARCHAR(50), "
				+ "postcode INT(4), "
				+ "client_id VARCHAR(7), "
				+ "PRIMARY KEY (location_id), "
				+ "FOREIGN KEY (client_id) REFERENCES clients(client_id))";	
		String sql_statement_table_appointments = "CREATE TABLE appointments ("
				+ "appointment_id VARCHAR(14), "
				+ "time VARCHAR(5), "
				+ "date VARCHAR(10), "
				+ "brief VARCHAR(999), "
				+ "feedback VARCHAR(999), "
				+ "client_id VARCHAR(7), "
				+ "employee_id VARCHAR(7), "
				+ "location_id VARCHAR(7), "
				+ "PRIMARY KEY (appointment_id), "
				+ "FOREIGN KEY (client_id) REFERENCES clients(client_id), "
				+ "FOREIGN KEY (employee_id) REFERENCES employees(employee_id), "
				+ "FOREIGN KEY (location_id) REFERENCES locations(location_id));";
		
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
	
	public static boolean Client(String url, String username, String password, String schema, String first_name, String surname, String phone_number) {
		@SuppressWarnings("unused")
		int client_count;
		if(get_last_record(null,Main.clients_array,null,null) == (Integer) null) {
			client_count = 0;
		} else {
			client_count = get_last_record(null,null,null,Main.trades_array);
		}
		String id = Create_ID('C', get_last_record(null,Main.clients_array,null,null));
		Client new_client = new Client(id, first_name, surname, phone_number, false);
		String sql_statement = "INSERT INTO " + schema + ".clients (client_id, first_name, surname, phone_number, previous_client) "
				+ "VALUES (" + "'" + new_client.id + "'" + "," + "'" + new_client.first_name + "'" + "," + "'" + new_client.surname + "'" + ","
						+ "" + new_client.phone_number + "," + new_client.previous_client + ")";
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println("Successfully created Client: " + new_client.first_name + " " + new_client.surname);
			return true;
		} else {
			System.out.println("Failed to create Client: " +  new_client.first_name + " " + new_client.surname);
			return false;
		}
	}
	
	public static boolean Trade(String url, String username, String password, String schema, String title) {
		int trade_count;
		if(get_last_record(null,null,null,Main.trades_array) == (Integer) null) {
			trade_count = 0;
		} else {
			trade_count = get_last_record(null,null,null,Main.trades_array);
		}
		String id = Create_ID('T', trade_count+1);
		Trade new_trade = new Trade(id, title);
		String sql_statement = "INSERT INTO " + schema + ".trades (trade_id, title) "
				+ "VALUES (" + "'" + new_trade.id + "'" + "," + "'" + new_trade.title + "'" + ")";
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println("Successfully created Trade: " + new_trade.title);
			return true;
		} else {
			System.out.println("Failed to create Trade: " + new_trade.title);
			return false;
		}
	}
	
	public static boolean Employee(String url, String username, String password, String schema, String first_name, String surname, String phone_number, boolean employed, String trade_id) {
		@SuppressWarnings("unused")
		int employee_count;
		if(get_last_record(null,null,Main.employees_array,null) == (Integer) null) {
			employee_count = 0;
		} else {
			employee_count = get_last_record(null,null,Main.employees_array,null);
		}
		String id = Create_ID('E', get_last_record(null,null,Main.employees_array,null));
		Employee new_employee = new Employee(id, first_name, surname, phone_number, employed, trade_id);
		String sql_statement = "INSERT INTO " + schema + ".employees (employee_id, first_name, surname, phone_number, trade_id) "
				+ "VALUES (" + "'" + new_employee.id + "'" + "," + "'" + new_employee.first_name + "'" + "," + "'" + new_employee.surname + "'" + ","
						+ "" + new_employee.phone_number + "," + new_employee.employed + "," + "'" + new_employee.trade_id + "'" + ")";
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println("Successfully created Employee: " + new_employee.first_name + " " + new_employee.surname);
			return true;
		} else {
			System.out.println("Failed to create Employee: " + new_employee.first_name + " " + new_employee.surname);
			return false;
		}
	}	
		
	public static boolean Location(String url, String username, String password, String schema, String street_number, String street_name, int postcode, String client_id) {
		Location new_location = new Location(street_number, street_name, postcode, client_id);
		String sql_statement = "INSERT INTO " + schema + ".locations (street_number, street_name, postcode, client_id) "
				+ "VALUES (" + "'" + new_location.street_number + "'" + "," + "'" + new_location.street_name + "'" + "," + new_location.postcode + ","
						+ "" + "'" + new_location.client_id + "'" + ")";
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println("Successfully created Location: " + new_location.street_number + " " + new_location.street_name + ", " + new_location.postcode);
			return true;
		} else {
			System.out.println("Failed to create Location: " + new_location.street_number + " " + new_location.street_name + ", " + new_location.postcode);
			return false;
		}
	}

	public static boolean Appointment(String url, String username, String password, String schema, String time, String date, String brief, String feedback, String client_id, String employee_id, String street_number, String street_name, int postcode) {
		@SuppressWarnings("unused")
		int appointment_count;
		if(get_last_record(Main.appointments_array,null,null,null) == (Integer) null) {
			appointment_count = 0;
		} else {
			appointment_count = get_last_record(null,null,Main.employees_array,null);
		}
		String id = Create_ID('A', get_last_record(Main.appointments_array,null,null,null));
		Appointment new_appointment = new Appointment(id,time,date,brief,feedback,client_id,employee_id,street_number,street_name,postcode);
		String sql_statement = "INSERT INTO " + schema + ".appointments (appointment_id, time, date, brief, client_id, employee_id, street_number, street_name, postcode) "
				+ "VALUES (" + "'" + new_appointment.id + "'" + "," + new_appointment.time + "," + new_appointment.date + ","
						+ "" + "'" + new_appointment.brief + "'" + "," + "'" + new_appointment.brief + "'" + "," + "'" + new_appointment.client_id + "'" + "," + "'"
								+ "" + new_appointment.employee_id + "'" + "," + "'" + street_number + "'" + "," + "'" + street_name + "'"
										+ "" + "," + postcode + ")";
		if(Connection_Execute.Command_Database(url,username,password,sql_statement)) {
			System.out.println("Successfully created Appointment: " + new_appointment.id);
			return true;
		} else {
			System.out.println("Failed to create Appointment: " + new_appointment.id);
			return false;
		}
	}	
}
