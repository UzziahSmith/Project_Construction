package application;

import java.util.ArrayList;

import com.job_tracker.attribute_creation.Appointment;
import com.job_tracker.attribute_creation.Client;
import com.job_tracker.attribute_creation.Employee;
import com.job_tracker.attribute_creation.Location;
import com.job_tracker.attribute_creation.Trade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	ArrayList<Appointment> appointments_array = new ArrayList<Appointment>();
	ArrayList<Client> clients_array = new ArrayList<Client>();
	ArrayList<Employee> employee_array = new ArrayList<Employee>();
	ArrayList<Location> location_array = new ArrayList<Location>();
	ArrayList<Trade> trade_array = new ArrayList<Trade>();
	
	private ArrayList<Appointment> get_appointments_from_database(String database, ArrayList<Appointment> appointment_array) {
		return appointment_array;
	}
	private ArrayList<Client> get_clients_from_database(String database, ArrayList<Client> clients_array) {
		return clients_array;
	}
	private ArrayList<Employee> get_employees_from_database(String database, ArrayList<Employee> employee_array) {
		return employee_array;
	}
	private ArrayList<Location> get_locations_from_database(String database, ArrayList<Location> location_array) {
		return location_array;
	}
	private ArrayList<Trade> get_trades_from_database(String database, ArrayList<Trade> trade_array) {
		return trade_array;
	}
	
	@Override
	public void start(Stage primary_stage) throws Exception {
		Stage window = primary_stage;
		Sign_In_UI sign_in_layout = new Sign_In_UI();
		Scene sign_in_screen = new Scene(sign_in_layout.get_scene(window));
		sign_in_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(sign_in_screen);
		window.setTitle("Project_Construction");
		window.centerOnScreen();
		window.show();
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
