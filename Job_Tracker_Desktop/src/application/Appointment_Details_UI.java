package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Appointment_Details_UI {
	private BorderPane header(Stage primary_stage, boolean administrator) {

		BorderPane header = UI_Templates.header(primary_stage, "Appointment Details");
		
		HBox navigation_button_bar = new HBox();
		Button btn_navigate_home = new Button("Home");
		Button btn_navigate_appointments = new Button("Appointments");
		if(administrator) {
			Button btn_navigate_clients = new Button("Clients");
			Button btn_navigate_employees = new Button("Employees");
			Button btn_navigate_trades = new Button("Trades");
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Home_Screen_Administrator_UI.");
					Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Clients_Screen_Administrator_UI.");
					Clients_Screen_Administrator_UI client_screen_layout = new Clients_Screen_Administrator_UI();
					Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage));
					client_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_screen_screen);
				}
			});
			btn_navigate_employees.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Employee_Details_Screen_Administrator_UI.");
					Employee_Details_Screen_Administrator_UI employee_details_layout = new Employee_Details_Screen_Administrator_UI();
					Scene employee_details_screen = new Scene(employee_details_layout.get_scene(primary_stage));
					employee_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(employee_details_screen);
				}
			});
			btn_navigate_trades.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Trade_List_Screen_Administrator_UI.");
					Trade_List_Screen_Administrator_UI trade_list_layout = new Trade_List_Screen_Administrator_UI();
					Scene trade_list_screen = new Scene(trade_list_layout.get_scene(primary_stage));
					trade_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(trade_list_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_appointments,btn_navigate_clients,btn_navigate_employees,btn_navigate_trades);
		} else {
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_User_UI to Home_Screen_User_UI.");
					Home_Screen_User_UI home_screen_layout = new Home_Screen_User_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_Details_User_UI to Appointment_List_User_UI.");
					Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
					Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,false));
					appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_list_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_appointments);
		}
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	private GridPane centre_panel(boolean administrator) {
		GridPane centre_panel = new GridPane();
		
		Label lbl_time_title = new Label("Time:");
		centre_panel.add(lbl_time_title,0,0);
		Label lbl_date_title = new Label("Date:");
		centre_panel.add(lbl_date_title,0,1);
		Label lbl_client_name_title = new Label("Client Name:");
		centre_panel.add(lbl_client_name_title,0,2);
		Label lbl_address_title = new Label("Address:");
		centre_panel.add(lbl_address_title,0,3);
		Label lbl_employee_title = new Label("Employee:");
		centre_panel.add(lbl_employee_title,0,4);
		Label lbl_job_status_title = new Label("Job Status:");
		centre_panel.add(lbl_job_status_title,0,5);
		Label lbl_brief_title = new Label("Brief:");
		centre_panel.add(lbl_brief_title,3,0);
		
		Label lbl_job_status = new Label();
		centre_panel.add(lbl_job_status,1,5);
		lbl_job_status.getStyleClass().add("output_user_label");
		lbl_job_status.setId("output_user_label");
		lbl_job_status.setMinSize(100,20);
		
		Button btn_job_status_complete = new Button("CHANGE TO TICK");
		centre_panel.add(btn_job_status_complete,2,5);
		Button btn_job_status_inprogress = new Button("CHANGE TO IN PROGRESS IMAGE");
		
		if(administrator) {
			HBox button_bar = new HBox();
			Button btn_new_appointment = new Button("NEW");
			Button btn_add = new Button("ADD");
			Button btn_remove = new Button("REMOVE");
			Button btn_update = new Button("UPDATE");
			Button btn_cancel = new Button("CANCEL");
			Button btn_new_client = new Button("+");
			centre_panel.add(btn_new_client,2,1);
			Button btn_new_date = new Button("CHANGE TO CALENDER IMAGE");
			centre_panel.add(btn_new_date,2,2);
			btn_new_appointment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Initialising UI for new appointment");
				}
			});
			btn_add.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Adding new appointment");
				}
			});
			btn_remove.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Removing selected appointment");
				}
			});
			btn_update.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Updating selected appointment details");
				}
			});
			btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Cancel new appointment details");
				}
			});
			button_bar.getChildren().addAll(btn_new_appointment,btn_add,btn_remove,btn_update,btn_cancel);
			centre_panel.add(button_bar,0,6,3,1);
			btn_new_client.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Initialising popup for new client");
					//TO DO SETUP POPUP WINDOW TO ALLOW NEW CLIENT INPUT//
				}
			});
			btn_new_date.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Changing appointment date.");
				}
			});
			
			TextField tf_time = new TextField();
			centre_panel.add(tf_time,1,0);
			TextField tf_date = new TextField();
			centre_panel.add(tf_date,1,1);
			TextField tf_client_name = new TextField();
			centre_panel.add(tf_client_name,1,2);
			TextField tf_address = new TextField();
			centre_panel.add(tf_address,1,3);
			TextField tf_employee = new TextField();
			centre_panel.add(tf_employee,1,4);

			TextArea ta_brief = new TextArea();
			centre_panel.add(ta_brief,3,1,3,6);
			
		} else {
			Label lbl_time = new Label();
			centre_panel.add(lbl_time,1,0);
			lbl_time.getStyleClass().add("output_user_label");
			lbl_time.setId("output_user_label");
			lbl_time.setMinSize(Algorithms.dimension_calculator(60.0,false),Algorithms.dimension_calculator(20.0,true));
			
			Label lbl_date = new Label();
			centre_panel.add(lbl_date,1,1);
			lbl_date.getStyleClass().add("output_user_label");
			lbl_date.setId("output_user_label");
			lbl_date.setMinSize(Algorithms.dimension_calculator(40.0,false),Algorithms.dimension_calculator(20.0,true));
			
			Label lbl_client_name = new Label();
			centre_panel.add(lbl_client_name,1,2);
			lbl_client_name.getStyleClass().add("output_user_label");
			lbl_client_name.setId("output_user_label");
			lbl_client_name.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			
			Label lbl_address = new Label();
			centre_panel.add(lbl_address,1,3);
			lbl_address.getStyleClass().add("output_user_label");
			lbl_address.setId("output_user_label");
			lbl_address.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));

			Label lbl_employee = new Label();
			centre_panel.add(lbl_employee,1,4);
			lbl_employee.getStyleClass().add("output_user_label");
			lbl_employee.setId("output_user_label");
			lbl_employee.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			
			Label lbl_brief = new Label();
			centre_panel.add(lbl_brief,3,1,3,6);
			lbl_brief.getStyleClass().add("output_user_label");
			lbl_brief.setId("output_user_label");
			lbl_brief.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(300.0,true));
		}
		
		return centre_panel;
	}
	
	public BorderPane get_scene(Stage primary_stage, boolean administrator) {
		BorderPane border_pane = new BorderPane();
		border_pane.setTop(header(primary_stage, administrator));
		border_pane.setCenter(centre_panel(administrator));
		return border_pane;
	}
}
