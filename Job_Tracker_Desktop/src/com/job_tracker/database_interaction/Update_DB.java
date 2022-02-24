package com.job_tracker.database_interaction;

import com.job_tracker.jdbc.Connection_Execute;

public class Update_DB {
	
	public static boolean Update_String_Record(String url, String username, String password, String schema, String table, String field, String record_id, String updated_data) {
		String sql_id_field_find_statement = String.format("SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME = '%s' AND ORDINAL_POSITION = 1;", table);
		String id_field_name = Connection_Execute.String_Query_Database(url, username, password, sql_id_field_find_statement);
		String sql_statement = String.format("UPDATE %s.%s SET %s = '%s' WHERE %s = '%s';",schema, table, field, updated_data, id_field_name, record_id);
		System.out.println(sql_statement);
		String output_statement_success = String.format("Successfully updated field (%s) to (%s)", table, updated_data);
		String output_statement_failure = String.format("Failed to update field (%s) to (%s)", table, updated_data);
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println(output_statement_success);
			return true;
		} else {
			System.out.println(output_statement_failure);
			return false;
		}
	}
	
	public static boolean Update_Boolean_Record(String url, String username, String password, String schema, String table, String field, String record_id, boolean updated_data) {
		String sql_id_field_find_statement = String.format("SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME = '%s' AND ORDINAL_POSITION = 1;", table);
		String id_field_name = Connection_Execute.String_Query_Database(url, username, password, sql_id_field_find_statement);
		String sql_statement = String.format("UPDATE %s SET %s = '%b' WHERE %s = %d", table, field, updated_data, id_field_name, record_id);
		String output_statement_success = String.format("Successfully updated field (%s) to (%b)", table, updated_data);
		String output_statement_failure = String.format("Failed to update field (%s) to (%b)", table, updated_data);
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println(output_statement_success);
			return true;
		} else {
			System.out.println(output_statement_failure);
			return false;
		}
	}
	
	public static boolean Update_Int_Record(String url, String username, String password, String schema, String table, String field, String record_id, int updated_data) {
		String sql_id_field_find_statement = String.format("SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME = '%s' AND ORDINAL_POSITION = 1;", table);
		String id_field_name = Connection_Execute.String_Query_Database(url, username, password, sql_id_field_find_statement);
		String sql_statement = String.format("UPDATE %s SET %s = '%d' WHERE %s = %d", table, field, updated_data, id_field_name, record_id);
		String output_statement_success = String.format("Successfully updated field (%s) to (%d)", table, updated_data);
		String output_statement_failure = String.format("Failed to update field (%s) to (%d)", table, updated_data);
		if(Connection_Execute.Command_Database(url, username, password, sql_statement)) {
			System.out.println(output_statement_success);
			return true;
		} else {
			System.out.println(output_statement_failure);
			return false;
		}
	}
}
