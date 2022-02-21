package com.job_tracker.database_interaction;

import com.job_tracker.jdbc.Connection_Execute;

public class Delete_DB {
	
	/*
	 * public static boolean Delete_Record(String url, String username, String
	 * password, String schema, String table, String record_id) { String db_url =
	 * String.format("%s/%s", url, schema); String table_id = table + ".id"; String
	 * sql_statement = String.format("DELETE from %s where %s = '%s';", table,
	 * table_id, record_id); if(Connection_Execute.Command_Database(db_url,
	 * username, password, sql_statement)) {
	 * System.out.println("Successfully deleted record: " + record_id +
	 * " (table: ) " + table); return true; } else {
	 * System.out.println("Failed to delete record: " + record_id + " (table: ) " +
	 * table); return false; } }
	 */
	
	public static boolean Delete_Business(String url, String username, String password, String schema) {
		String sql_statement = "DROP DATABASE " + schema;
		if(sql_statement.equals("information_schema") || sql_statement.equals("mysql") || sql_statement.equals("performance_schema") || sql_statement.equals("sys")) {
			System.out.println("Cannot delete essential database: " + schema);
			return false;
		} else {
			if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
				System.out.println("Successfully deleted database: " + schema);
				return true;
			} else {
				System.out.println("Failed to delete database: " + schema);
				return false;
			}
		}
	}
}