package application;

import java.util.ArrayList;
import java.util.List;

import com.job_tracker.attribute_creation.Appointment;
import com.job_tracker.attribute_creation.Client;
import com.job_tracker.attribute_creation.Employee;
import com.job_tracker.attribute_creation.Location;
import com.job_tracker.attribute_creation.Trade;
import com.job_tracker.attribute_creation.User;
import com.job_tracker.database_interaction.Add_DB;
import com.job_tracker.jdbc.Connection_Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final String url = "jdbc:mysql://localhost:3306";
	public static final String user = "root";
	public static final String password = "constructpd173";
	
	public static User user_data;
	
	public static List<Appointment> appointments_array = new ArrayList<Appointment>();
	public static List<Client> clients_array = new ArrayList<Client>();
	public static List<Employee> employees_array = new ArrayList<Employee>();
	public static List<Location> locations_array = new ArrayList<Location>();
	public static List<Trade> trades_array = new ArrayList<Trade>();
	
	@Override
	public void start(Stage primary_stage) throws Exception {
		System.out.println("Program started");
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
