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
import javafx.geometry.Rectangle2D;
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
	
	static ArrayList<Appointment> appointments_by_Date(String date) {
		ArrayList<Appointment> output_array = new ArrayList<Appointment>();
		if(output_array != null) {
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
		return null;
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
}
