package com.job_tracker.database_interaction;

import com.job_tracker.jdbc.Connection_Execute;

public class Delete_DB {
	
	public static boolean Delete_Record(String schema, String table, String record, String url, String username, String password) {
		
	}
	
	public static boolean Delete_Business(String schema, String url, String username, String password) {
		String sql_statement = "DROP DATABASE " + schema;
		if(sql_statement.equals("information_schema") || sql_statement.equals("mysql") || sql_statement.equals("performance_schema") || sql_statement.equals("sys")) {
			System.out.println("Cannot delete essential database: " + schema);
			return false;
		} else {
			if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
				System.out.println("Deletion of database " + schema + ": successful.");
				return true;
			} else {
				System.out.println("Deletion of database " + schema + ": failure.");
				return false;
			}
		}
	}
}