package com.job_tracker.database_interaction;

import com.job_tracker.attribute_creation.*;
import com.job_tracker.jdbc.*;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select_DB {
//	private ArrayList<Appointment> Extract_Data_Record_Appointment(String url, String username, String password, String schema) {
//		ArrayList<Appointment> appointment_array = new ArrayList<Appointment>();
//		String sql_statement = String.format("SELECT * FROM %s.appointments", schema);
//		ResultSet result_set = Connection_Execute.ResultSet_Query_Database(url, username, password, sql_statement);
//		if(result_set != null) {
//			
//		} else {
//			System.out.println("Failed to extract table : appointments");
//			return null;
//		}
//	}
	
//	public List Result_Set_To_Array_List(ResultSet result_set) throws SQLException {
//		ResultSetMetaData meta_data = result_set.getMetaData();
//		int columns = meta_data.getColumnCount();
//		List<Map<String, Object>> list = new ArrayList<>();
//		while(result_set.next()) {
//			Map<String, Object> row = new HashMap<>(columns);
//			for(int i = 1; i <= columns; i++) {
//				row.put(meta_data.getColumnName(i), result_set.getObject(i));
//			}
//			list.add(row);
//		}
//		result_set.close();
//		return list;
//	}
	
	public static boolean appointments_null;
	public static boolean clients_null;
	public static boolean locations_null;
	public static boolean employees_null;
	
	public static List<Appointment> Extract_Data_Record_Appointments(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.appointments;", schema);
		List<Appointment> results = new ArrayList<Appointment>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			try {
				while(result_set.next()) {
					Appointment appointment = new Appointment("","","","","","","","");
					appointment.id = result_set.getString(1);
					appointment.time = result_set.getString(2);
					appointment.date = result_set.getString(3);
					appointment.brief = result_set.getString(4);
					appointment.feedback = result_set.getString(5);
					appointment.client_id = result_set.getString(6);
					appointment.employee_id = result_set.getString(7);
					appointment.location_id = result_set.getString(8);
					results.add(appointment);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			appointments_null = false;
			return results;
		} else {
			appointments_null = true;
			return null;
		}
	}
	
	public static List<Client> Extract_Data_Record_Clients(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.clients;",schema);
		List<Client> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			try {
				while(result_set.next()) {
					Client client = new Client("","","","",false);
					client.id = result_set.getString(1);
					client.first_name = result_set.getString(2);
					client.surname = result_set.getString(3);
					client.phone_number = result_set.getString(4);
					client.previous_client = result_set.getBoolean(5);
					results.add(client);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			clients_null = false;
			return results;
		} else {
			clients_null = true;
			return null;
		}
		
	}
	
	public static List<Location> Extract_Data_Record_Locations(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.locations;",schema);
		List<Location> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			try {
				while(result_set.next()) {
					Location location = new Location("","","",0,"");
					location.id = result_set.getString(1);
					location.street_number = result_set.getString(2);
					location.street_name = result_set.getString(3);
					location.postcode = result_set.getInt(4);
					location.client_id = result_set.getString(5);
					results.add(location);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			locations_null = false;
			return results;
		} else {
			locations_null = true;
			return null;
		}
	}
	
	public static List<Employee> Extract_Data_Record_Employees(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.employees;",schema);
		List<Employee> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			try {
				while(result_set.next()) {
					Employee employee = new Employee("","","","",false,"");
					employee.id = result_set.getString(1);
					employee.first_name = result_set.getString(2);
					employee.surname = result_set.getString(3);
					employee.phone_number = result_set.getString(4);
					employee.employed = result_set.getBoolean(5);
					employee.trade_id = result_set.getString(6);
					results.add(employee);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			employees_null = false;
			return results;
		} else {
			employees_null = true;
			return null;
		}	
	}
	
	public static List<Trade> Extract_Data_Record_Trades(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.trades;",schema);
		List<Trade> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			result_set.previous();
			try {
				while(result_set.next()) {
					Trade trade = new Trade("","");
					trade.id = result_set.getString(1);
					trade.title = result_set.getString(2);
					results.add(trade);
					System.out.println("new Trade: " + trade.title + " - added");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return results;
		} else {
			return null;
		}
	}
	
	public static List<User> Extract_Data_Record_User(String url, String username, String password) throws SQLException {
		String sql_statement = String.format("SELECT * FROM user_data.login_data;");
		List<User> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet result_set = statement.executeQuery(sql_statement);
		if(result_set.next() != false) {
			result_set.previous();
			try {
				while(result_set.next()) {
					User user = new User("","","",false);
					user.email = result_set.getString(1);
					user.password = result_set.getString(2);
					user.business = result_set.getString(3);
					user.admin = result_set.getBoolean(4);
					results.add(user);
					System.out.println("new user: " + user.email + " - added");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(statement != null) {
						statement.close();
						statement=null;
					} if(result_set != null) {
						result_set.close();
						result_set=null;
					} if(connection !=null) {
						connection.close();
						connection=null;
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println(String.valueOf(results.size()));
			return results;
		} else {
			return null;
		}
	}
}
