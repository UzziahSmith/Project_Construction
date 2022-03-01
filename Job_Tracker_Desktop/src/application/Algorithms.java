package application;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.job_tracker.attribute_creation.Appointment;
import com.job_tracker.attribute_creation.Client;
import com.job_tracker.attribute_creation.Employee;
import com.job_tracker.attribute_creation.Location;
import com.job_tracker.attribute_creation.Trade;
import com.job_tracker.attribute_creation.User;
import com.job_tracker.database_interaction.Add_DB;
import com.job_tracker.database_interaction.Select_DB;
import com.job_tracker.database_interaction.Update_DB;
import com.job_tracker.jdbc.Connection_Test;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

public class Algorithms {
	public ArrayList<String> array_list_date_extractor(ArrayList<String> master_array_list, Date search_date) {
		ArrayList<String> extracted_array = new ArrayList<String>();
		return extracted_array;
	}
	
	public static double dimension_calculator(double dimension,boolean height) {
		Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
		double screen_width = screen_bounds.getWidth();
		double screen_height = screen_bounds.getHeight();
		double proportion;
		double dimension_output;
		if(height) {
			proportion = dimension/1080;
			dimension_output = proportion*screen_height;
		} else {
			proportion = dimension/1920;
			dimension_output = proportion*screen_width;
		}
		return dimension_output;
	}
	
	static void add_input_limiter_integers(final TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String>  observable, String old_value, String new_value) {
				if(!new_value.matches("\\d*")) {
					tf.setText(new_value.replaceAll("[^\\d]", ""));
				}
			}
		});	
	}
	
	static void add_quantity_limiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String old_value, final String new_value) {
				if(tf.getText().length() > maxLength) {
					String string = tf.getText().substring(0, maxLength);
					tf.setText(string);
				}
			}
		});
	}
	
	static void add_quantity_limiter_textarea(final TextArea ta, final int maxLength) {
		ta.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String old_value, final String new_value) {
				if(ta.getText().length() > maxLength) {
					String string = ta.getText().substring(0, maxLength);
					ta.setText(string);
				}
			}
		});
	}
	
	static User search_match_user(String email, String password) throws SQLException {
		List<User> users_array = Select_DB.Extract_Data_Record_User(Main.url, Main.user, Main.password);
		int size = Select_DB.Extract_Data_Record_User(Main.url, Main.user, Main.password).size();
		for(int i = 0; i < size; i++) {
			System.out.println("search_match_user: checking user: " + users_array.get(i).email);
			if(email.equals(users_array.get(i).email) && password.equals(users_array.get(i).password))  {
				System.out.println("User: " + email + " found");
				return users_array.get(i);
			} else if(email.equals(users_array.get(i).email) && !password.equals(users_array.get(i).password)) {
				System.out.println("Invalid password");
				return null;
			}
		}
		System.out.println("Failed to find user");
		return null;
	}
	
	static ArrayList<Appointment> appointments_by_date(String date) {
		ArrayList<Appointment> output_array = new ArrayList<Appointment>();
		int size = Main.appointments_array.size();
		for(int i = 0; i < size; i++) {
			if(date.equals(Main.appointments_array.get(i).date)) {
				output_array.add(Main.appointments_array.get(i));
			}
		}
		if(output_array.size() > 0) {
			return output_array;
		} else {
			return null;
		}
	}
	
	static boolean trade_title_exists(String title) throws SQLException {
		if(Main.trades_array != null) {
			for(Trade trade : Main.trades_array) {
				if(title.equals(trade.title))  {
					return true;
				}
			}
		}
		return false;
	}
	
	static String output_trade_id(String title) throws SQLException {
		if(Main.trades_array != null) {
			for(Trade trade : Main.trades_array) {
				if(title.equals(trade.title))  {
					return trade.id;
				}
			}
		}
		return null;
	}
	
	static String output_trade_title(String trade_id) {
		if(Main.trades_array != null) {
			for(Trade trade : Main.trades_array) {
				if(trade_id.equals(trade.id)) {
					return trade.title;
				}
			}
		}
		return null;
	}
	
	static String output_client_id(String first_name, String surname, String phone_number) {
		if(Main.clients_array != null) {
			for(Client client : Main.clients_array) {
				if(first_name.equals(client.first_name) && surname.equals(client.surname) && phone_number.equals(client.phone_number)) {
					return client.id;
				}
			}
		}
		return null;
	}
	
	static boolean client_exists(String first_name, String surname, String phone_number) {
		if(Main.clients_array != null) {
			for(Client client : Main.clients_array) {
				if(first_name.equals(client.first_name) && surname.equals(client.surname) && phone_number.equals(client.phone_number)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean client_exists_id(String client_id) {
		if(Main.clients_array != null) {
			for(Client client : Main.clients_array) {
				if(client_id.equals(client.id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static String client_details_output(String client_id) {
		if(Main.clients_array != null) {
			for(Client client : Main.clients_array) {
				if(client_id.equals(client.id)) {
					String output = String.format("%s %s - %s", client.first_name, client.surname);
					return output;
				}
			}
		}
		return null;
	}
	
	static String output_employee_id(String first_name, String surname, String phone_number, String trade_id) {
		if(Main.employees_array != null) {
			for(Employee employee : Main.employees_array) {
				if(first_name.equals(employee.first_name) && surname.equals(employee.surname) && phone_number.equals(employee.phone_number) && trade_id.equals(employee.trade_id)) {
					return employee.id;
				}
			}
		}
		return null;
	}
	
	static boolean employee_exists(String first_name, String surname, String phone_number, String trade_title) {
		if(Main.employees_array != null) {
			for(Employee employee : Main.employees_array) {
				if(first_name.equals(employee.first_name) && surname.equals(employee.surname) && phone_number.equals(employee.phone_number) && trade_title.equals(trade_title)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static String employee_details_output(String employee_id) {
		if(Main.employees_array != null) {
			for(Employee employee : Main.employees_array) {
				if(employee_id.equals(employee.id)) {
					String output = String.format("%s %s %s - %s\n%s", employee.id, employee.first_name, employee.surname, employee.phone_number, output_trade_title(employee.trade_id));
					return output;
				}
			}
		}
		return null;
	}
	
	static boolean employee_exists_id(String employee_id) {
		if(Main.employees_array != null) {
			for(Employee employee : Main.employees_array) {
				if(employee_id.equals(employee.id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static String output_location_id(String street_number, String street_name, int postcode, String client_id) {
		if(Main.locations_array != null) {
			for(Location location : Main.locations_array) {
				if(street_number.equals(location.street_number) && street_name.equals(location.street_name) && postcode == location.postcode && client_id.equals(location.client_id)) {
					return location.id;
				}
			}
		}
		return null;
	}
	
	static String location_details(String location_id) {
		if(Main.locations_array != null) {
			for(Location location : Main.locations_array) {
				if(location_id.equals(location.id)) {
					String output = String.format("%s, %s, %s",location.street_number,location.street_name,location.postcode);
					return output;
				}
			}
		}
		return null;
	}
	
	static boolean location_exists(String street_number, String street_name, int postcode, String client_id) {
		if(Main.locations_array != null) {
			for(Location location : Main.locations_array) {
				if(street_number.equals(location.street_number) && street_name.equals(location.street_name) && postcode == location.postcode && client_id.equals(location.client_id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean appointment_exists(String time, String date, String brief, String feedback, String client_id, String employee_id, String location_id) {
		for(Appointment appointment : Main.appointments_array) {
			if(time.equals(appointment.time) && 
					date.equals(appointment.date) && 
					brief.equals(appointment.brief) && 
					feedback.equals(appointment.feedback) && 
					client_id.equals(appointment.client_id) && 
					employee_id.equals(appointment.employee_id) && 
					location_id.equals(appointment.location_id)) 
			{return true;}
		}
	return false;
	}
	
	static void item_filter_listener(final TextField tf, String[] formatted_items, ObservableList<String> observable_list, ListView<String> listview) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(final ObservableValue<? extends String> ov, final String old_value, final String new_value) {
				observable_list.clear();
				for(String item_s : formatted_items) {
					int match_count = 0;
					int character_pos = 0;
					for(int i = 0; i < item_s.length(); i++) {
						if(item_s.charAt(i) == tf.getText().charAt(character_pos)) {
							character_pos++;
							match_count++;
						}
						if(match_count == tf.getText().length()) {
							observable_list.add(item_s);
							break;
						}
					}
				}
				listview.setItems(observable_list);
			}
		});
	}
	
	static String[] gather_clients() {
		int array_size = Main.clients_array.size();
		List<Client> clients = Main.clients_array;
		String[] formatted_clients = new String[array_size];
		for(int i = 0; i < array_size; i++) {
			String output = String.format("%s %s - %s", clients.get(i).first_name, clients.get(i).surname,clients.get(i).phone_number);
			formatted_clients[i] = output;
		}
		return formatted_clients;
	}

	static String[] gather_employees() {
		int array_size = Main.employees_array.size();
		List<Employee> employees = Main.employees_array;
		String[] formatted_employees = new String[array_size];
		for(int i = 0; i < array_size; i++) {
			String output = String.format("%s %s %s - %s", employees.get(i).id, employees.get(i).first_name,employees.get(i).surname, employees.get(i).phone_number);
			formatted_employees[i] = output;
		}
		return formatted_employees;
	}
	
	static String[] gather_appointments() {
		int array_size = Main.appointments_array.size();
		List<Appointment> appointments = Main.appointments_array;
		String[] formatted_appointments = new String[array_size];
		for(int i = 0; i < array_size; i++) {
			String output = String.format("%s, %s\nClient: %s\nEmployee: %s\nLocation: %s",
					appointments.get(i).time,
					appointments.get(i).date,
					Algorithms.client_details_output(appointments.get(i).client_id),
					Algorithms.employee_details_output(appointments.get(i).employee_id),
					Algorithms.location_details(appointments.get(i).location_id));
			formatted_appointments[i] = output;
		}
		return formatted_appointments;
	}
	
	static String[] gather_trades() {
		int array_size = Main.trades_array.size();
		List<Trade> trades = Main.trades_array;
		String[] formatted_trades = new String[array_size];
		for(int i = 0; i < array_size; i++) {
			String output = String.format("%s - %s",trades.get(i).id,trades.get(i).title);
			formatted_trades[i] = output;
		}
		return formatted_trades;
	}
	
	static int get_appointment_count(String s) {
		String integer = null;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ':') {
				return Integer.parseInt(s);
			} else if(integer != null) {
				integer = String.valueOf(s.charAt(i));
			} else {
				integer = String.valueOf(s.charAt(0));
			}
		}
		return (Integer) null;
	}
}