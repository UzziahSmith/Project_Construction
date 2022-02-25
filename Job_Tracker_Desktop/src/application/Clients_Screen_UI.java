package application;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Clients_Screen_UI {
	Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
	
	private BorderPane header(Stage primary_stage,boolean administrator) {
		
		BorderPane header = UI_Templates.header(primary_stage,"Clients");
		
		HBox navigation_button_bar = new HBox();
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		UI_Templates.header_button_style(btn_navigate_home);
		if(administrator) {
			Button btn_navigate_appointments = new Button("Appointments");
			UI_Templates.header_button_style(btn_navigate_appointments);
			Button btn_navigate_employees = new Button("Employees");
			UI_Templates.header_button_style(btn_navigate_employees);
			Button btn_navigate_trades = new Button("Trades");
			UI_Templates.header_button_style(btn_navigate_trades);
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_UI to Home_Screen_Administrator_UI.");
					Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_Administrator_UI to Appointment_List_UI (Administrator).");
					Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
					Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,true));
					appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_list_screen);
				}
			});
			btn_navigate_employees.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_Administrator_UI to Employee_Details_Screen_Administrator_UI.");
					Employee_Details_Screen_Administrator_UI employee_details_layout = new Employee_Details_Screen_Administrator_UI();
					Scene employee_details_screen = new Scene(employee_details_layout.get_scene(primary_stage));
					employee_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(employee_details_screen);
				}
			});
			btn_navigate_trades.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_Administrator_UI to Trade_List_Screen_Administrator_UI.");
					Trade_List_Screen_Administrator_UI trade_list_layout = new Trade_List_Screen_Administrator_UI();
					Scene trade_list_screen = new Scene(trade_list_layout.get_scene(primary_stage));
					trade_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(trade_list_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_appointments,btn_navigate_employees,btn_navigate_trades);
		} else {
			Button btn_navigate_assigned_jobs = new Button("Assigned Jobs");
			UI_Templates.header_button_style(btn_navigate_assigned_jobs);
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_UI to Home_Screen_User_UI.");
					Home_Screen_User_UI home_screen_layout = new Home_Screen_User_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_assigned_jobs.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Clients_Screen_UI to Appointment_List_UI (User).");
					Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
					Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,false));
					appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_list_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_assigned_jobs);
			
		}
		header.setRight(navigation_button_bar);
		return header;
	}
	
	@SuppressWarnings("static-access")
	private GridPane centre_view(ArrayList<String> clients, ArrayList<String> jobs, boolean administrator) {
		GridPane centre_grid = new GridPane();
		centre_grid.setHgap(Algorithms.dimension_calculator(100.0,false));
		GridPane centre_view = new GridPane();
		centre_view.setVgap(Algorithms.dimension_calculator(50.0,true));
		centre_view.setHgap(Algorithms.dimension_calculator(10.0,false));
		
		Label lbl_first_name = new Label("First Name:");
		lbl_first_name.setMaxWidth(Double.MAX_VALUE);
		lbl_first_name.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_first_name);
		centre_view.add(lbl_first_name,1,1);
		
		Label lbl_surname = new Label("Surname:");
		lbl_surname.setMaxWidth(Double.MAX_VALUE);
		lbl_surname.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_surname);
		centre_view.add(lbl_surname,1,2);
		
		Label lbl_phone_number = new Label("Phone Number:");
		lbl_phone_number.setMaxWidth(Double.MAX_VALUE);
		lbl_phone_number.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_phone_number);
		centre_view.add(lbl_phone_number,1,3);
		
		if(administrator) {
			TextField tf_first_name = new TextField();
			Algorithms.add_quantity_limiter(tf_first_name,35);
			centre_view.add(tf_first_name,2,1);
			TextField tf_surname = new TextField();
			Algorithms.add_quantity_limiter(tf_surname,50);
			centre_view.add(tf_surname,2,2);
			TextField tf_phone_number = new TextField();
			Algorithms.add_input_limiter_integers(tf_phone_number);
			Algorithms.add_quantity_limiter(tf_phone_number,10);
			centre_view.add(tf_phone_number,2,3);

			HBox client_interactive_button_bar = new HBox();
			client_interactive_button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
			Button btn_new = new Button("NEW");
			UI_Templates.enable_interaction_button(btn_new);
			Button btn_add = new Button("ADD");
			UI_Templates.enable_interaction_button(btn_add);
			Button btn_update = new Button("UPDATE");
			UI_Templates.enable_interaction_button(btn_update);
			Button btn_cancel = new Button("CANCEL");
			UI_Templates.disable_interaction_button(btn_cancel);
			btn_new.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("New client initialisation");
					tf_first_name.clear();
					tf_surname.clear();
					tf_phone_number.clear();
					UI_Templates.enable_interaction_button(btn_add);
					UI_Templates.enable_interaction_button(btn_cancel);
					UI_Templates.disable_interaction_button(btn_new);
					UI_Templates.disable_interaction_button(btn_update);
				}
			});
			btn_add.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Add new client");
					tf_first_name.clear();
					tf_surname.clear();
					tf_phone_number.clear();
					UI_Templates.disable_interaction_button(btn_add);
					UI_Templates.disable_interaction_button(btn_cancel);
					UI_Templates.enable_interaction_button(btn_new);
					UI_Templates.enable_interaction_button(btn_update);
					//String(=7) client_id
					//String(<36) first_name
					String first_name_s = tf_first_name.getText();
					boolean first_name_valid = first_name_s.length() < 36 ? true : false;
					if(!first_name_valid) {}
					//String(<50) surname
					String surname_s = tf_surname.getText();
					boolean surname_valid = surname_s.length() < 36 ? true : false;
					if(!surname_valid) {}
					//String(=10) phone_number
					String phone_number_s = tf_phone_number.getText();
					boolean phone_number_valid = phone_number_s.length() == 10 ? true : false;
					if(!phone_number_valid) {System.out.println("Logical Error: phone number allowing numbers above or below 10 digits");}
					//Boolean previous_client
				}
			});
			btn_update.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Updated selected client information");
				}
			});
			btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Cancel inputted client data");
					tf_first_name.clear();
					tf_surname.clear();
					tf_phone_number.clear();
					UI_Templates.disable_interaction_button(btn_add);
					UI_Templates.disable_interaction_button(btn_cancel);
					UI_Templates.enable_interaction_button(btn_new);
					UI_Templates.enable_interaction_button(btn_update);
				}
			});
			client_interactive_button_bar.getChildren().addAll(btn_new,btn_add,btn_update,btn_cancel);
			centre_view.add(client_interactive_button_bar,1,5,2,1);
		} else {
			
			////TESTING PURPOSES////
			boolean previous_client = false;
			////TESTING PURPOSES////
			
			Label lbl_first_name_output = new Label();
			UI_Templates.output_label_style(lbl_first_name_output);
			lbl_first_name_output.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(30.0,true));
			centre_view.add(lbl_first_name_output,2,1);

			Label lbl_surname_output = new Label();
			UI_Templates.output_label_style(lbl_surname_output);
			lbl_surname_output.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(30.0,true));
			centre_view.add(lbl_surname_output,2,2);
			
			Label lbl_phone_number_output = new Label();
			UI_Templates.output_label_style(lbl_phone_number_output);
			lbl_phone_number_output.setMinSize(Algorithms.dimension_calculator(100.0,false),Algorithms.dimension_calculator(30.0,true));
			centre_view.add(lbl_phone_number_output,2,3);
			
			Label lbl_previous_client = new Label("Previous Client:");
			lbl_previous_client.setMaxWidth(Double.MAX_VALUE);
			lbl_previous_client.setAlignment(Pos.CENTER_RIGHT);
			UI_Templates.title_label_style(lbl_previous_client);
			centre_view.add(lbl_previous_client,1,4);
			
			Label lbl_previous_client_output = new Label();
			UI_Templates.output_label_style(lbl_previous_client_output);
			lbl_previous_client_output.setMinSize(Algorithms.dimension_calculator(50.0,false),Algorithms.dimension_calculator(30.0,true));
			if(lbl_first_name_output.getText() == null || lbl_surname_output.getText() == null || lbl_phone_number_output.getText() == null) {
				lbl_previous_client_output.setText("");
			} else if(previous_client) {
				lbl_previous_client_output.setText("Yes");
			} else {
				lbl_previous_client_output.setText("No");
			}
			centre_view.add(lbl_previous_client_output,2,4);
		}
		
		Label lbl_job_history_list_title = new Label("Job History");
		UI_Templates.title_label_style(lbl_job_history_list_title);
		lbl_job_history_list_title.setAlignment(Pos.CENTER);
		lbl_job_history_list_title.setMaxWidth(Double.MAX_VALUE);
		lbl_job_history_list_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_job_history = new ListView<String>();
		UI_Templates.list_view_style(lv_job_history);
		ObservableList<String> lv_job_history_items = FXCollections.observableArrayList();
		for(String job : jobs) {
			lv_job_history_items.add(job);
		}
		lv_job_history.setItems(lv_job_history_items);
		lv_job_history.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));
		centre_view.add(lv_job_history,0,1,1,4);
		
		Label lbl_clients_list_title = new Label("Clients");
		UI_Templates.title_label_style(lbl_clients_list_title);
		lbl_clients_list_title.setAlignment(Pos.CENTER);
		lbl_clients_list_title.setMaxWidth(Double.MAX_VALUE);
		lbl_clients_list_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_clients = new ListView<String>();
		UI_Templates.list_view_style(lv_clients);
		ObservableList<String> lv_clients_items = FXCollections.observableArrayList();
		for(String client : clients) {
			lv_clients_items.add(client);
		}
		lv_clients.setItems(lv_clients_items);
		lv_clients.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));
		
		centre_grid.add(lbl_job_history_list_title,0,0);
		centre_grid.add(lv_job_history,0,1);
		centre_grid.add(centre_view,1,1);
		centre_grid.add(lbl_clients_list_title,2,0);
		centre_grid.add(lv_clients,2,1);
		centre_grid.setAlignment(Pos.CENTER);
		
		return centre_grid;
	}

	public BorderPane get_scene(Stage primary_stage, boolean administrator) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_clients_AL = new ArrayList<String>();
		ArrayList<String> test_job_history_AL = new ArrayList<String>();
		border_pane.setPrefSize(screen_bounds.getWidth()*0.8,screen_bounds.getHeight()*0.8);
		border_pane.setTop(header(primary_stage,administrator));
		border_pane.setCenter(centre_view(test_clients_AL,test_job_history_AL,administrator));
		return border_pane;
	}
}
