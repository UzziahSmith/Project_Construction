package com.job_tracker.database_interaction;

import com.job_tracker.attribute_creation.*;
import com.job_tracker.jdbc.*;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Appointment> Extract_Data_Record_Appointments(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.appointments;");
		List<Appointment> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		try {
			while(result_set.next()) {
				Appointment appointment = new Appointment("","","","","","","","",0);
				appointment.id = result_set.getString(1);
				appointment.time = result_set.getString(2);
				appointment.date = result_set.getString(3);
				appointment.brief = result_set.getString(4);
				appointment.client_id = result_set.getString(5);
				appointment.employee_id = result_set.getString(6);
				appointment.street_number = result_set.getString(7);
				appointment.street_name = result_set.getString(8);
				appointment.postcode = result_set.getInt(9);
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
		return results;
	}
	
	public List<Client> Extract_Data_Record_Clients(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.clients;");
		List<Client> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
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
		return results;
	}
	
	public List<Location> Extract_Data_Record_Locations(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.locations;");
		List<Location> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		try {
			while(result_set.next()) {
				Location location = new Location("","",0,"");
				location.street_number = result_set.getString(1);
				location.street_name = result_set.getString(2);
				location.postcode = result_set.getInt(3);
				location.client_id = result_set.getString(4);
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
		return results;
	}
	
	public List<Employee> Extract_Data_Record_Trades(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.employees;");
		List<Employee> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
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
		return results;
	}
	
	public List<Trade> Extract_Data_Record_Employees(String url, String username, String password, String schema) throws SQLException {
		String sql_statement = String.format("SELECT * FROM %s.trades;");
		List<Trade> results = new ArrayList<>();
		Connection connection = DriverManager.getConnection(url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql_statement);
		ResultSet result_set = statement.executeQuery(sql_statement);
		try {
			while(result_set.next()) {
				Trade trade = new Trade("","");
				trade.id = result_set.getString(1);
				trade.title = result_set.getString(2);
				results.add(trade);
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
	}
}
