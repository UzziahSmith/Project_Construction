package com.job_tracker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Connection_Execute {
	public static boolean Command_Database(String url, String username, String password, String sql_statement) {
		// creates an object and tries to establish a connection with the SQL server, then implements SQL statement.
		try(Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement statement = conn.prepareStatement(sql_statement)) {
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Integer Int_Query_Database(String url, String username, String password, String query_statement) {
		// creates an object and tries to establish a connection with the SQL server, implements statement then returns int.
		try(Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement statement = conn.prepareStatement(query_statement)) {
			ResultSet result_set = statement.executeQuery();
			result_set.next();
			int int_retrieval = result_set.getInt(1);
			return int_retrieval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String String_Query_Database(String url, String username, String password, String query_statement) {
		// creates an object and tries to establish a connection with the SQL server, implements statement then returns string.
		try(Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement statement = conn.prepareStatement(query_statement)) {
			ResultSet result_set = statement.executeQuery();
			result_set.next();
			String string_retrieval = result_set.getString(1);
			return string_retrieval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}