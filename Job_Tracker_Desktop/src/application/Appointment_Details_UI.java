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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Appointment_Details_UI {
	private BorderPane header(Stage primary_stage, boolean administrator) {

		BorderPane header = UI_Templates.header(primary_stage, "Appointment Details");
		
		HBox navigation_button_bar = new HBox();
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		UI_Templates.header_button_style(btn_navigate_home);
		Button btn_navigate_appointments = new Button("Appointments");
		UI_Templates.header_button_style(btn_navigate_appointments);
		if(administrator) {
			Button btn_navigate_clients = new Button("Clients");
			UI_Templates.header_button_style(btn_navigate_clients);
			Button btn_navigate_employees = new Button("Employees");
			UI_Templates.header_button_style(btn_navigate_employees);
			Button btn_navigate_trades = new Button("Trades");
			UI_Templates.header_button_style(btn_navigate_trades);
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
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Clients_Screen_UI (Administrator).");
					Clients_Screen_UI client_screen_layout = new Clients_Screen_UI();
					Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage,true));
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
		centre_panel.setHgap(Algorithms.dimension_calculator(100.0,false));
		GridPane left_view = new GridPane();
		left_view.setHgap(Algorithms.dimension_calculator(10.0,false));
		left_view.setVgap(Algorithms.dimension_calculator(50.0,true));

		Label lbl_time_title = new Label("Time:");
		lbl_time_title.setMaxWidth(Double.MAX_VALUE);
		lbl_time_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_time_title);
		left_view.add(lbl_time_title,0,0);
		
		Label lbl_date_title = new Label("Date:");
		lbl_date_title.setMaxWidth(Double.MAX_VALUE);
		lbl_date_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_date_title);
		left_view.add(lbl_date_title,0,1);
		
		Label lbl_client_name_title = new Label("Client Name:");
		lbl_client_name_title.setMaxWidth(Double.MAX_VALUE);
		lbl_client_name_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_client_name_title);
		left_view.add(lbl_client_name_title,0,2);
		
		Label lbl_address_title = new Label("Address:");
		lbl_address_title.setMaxWidth(Double.MAX_VALUE);
		lbl_address_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_address_title);
		left_view.add(lbl_address_title,0,3);
		
		Label lbl_employee_title = new Label("Employee:");
		lbl_employee_title.setMaxWidth(Double.MAX_VALUE);
		lbl_employee_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_employee_title);
		left_view.add(lbl_employee_title,0,4);
		
		Label lbl_job_status_title = new Label("Job Status:");
		lbl_job_status_title.setMaxWidth(Double.MAX_VALUE);
		lbl_job_status_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_job_status_title);
		left_view.add(lbl_job_status_title,0,5);
		
		
		HBox hb_job_status_output = new HBox();
		hb_job_status_output.setSpacing(Algorithms.dimension_calculator(10.0,false));
		
		Label lbl_job_status = new Label();
		UI_Templates.output_label_style(lbl_job_status);
		lbl_job_status.setMinSize(100,20);
		Button btn_job_status_complete = new Button("CHANGE TO TICK");
		Button btn_job_status_incomplete = new Button("CHANGE TO INCOMPLETE IMAGE");
		hb_job_status_output.getChildren().addAll(lbl_job_status,btn_job_status_complete);
		left_view.add(hb_job_status_output,1,5);
		
		Label lbl_brief_title = new Label("Brief");
		lbl_brief_title.setMaxWidth(Double.MAX_VALUE);
		lbl_brief_title.setAlignment(Pos.CENTER_LEFT);
		UI_Templates.title_label_style(lbl_brief_title);
		left_view.add(lbl_brief_title,3,0);
		
		if(administrator) {
			HBox button_bar = new HBox();
			button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
			Button btn_new_appointment = new Button("NEW");
			Button btn_add = new Button("ADD");
			Button btn_remove = new Button("REMOVE");
			Button btn_update = new Button("UPDATE");
			Button btn_cancel = new Button("CANCEL");
			Button btn_new_client = new Button("+");
			left_view.add(btn_new_client,2,1);
			Button btn_new_date = new Button("CHANGE TO CALENDER IMAGE");
			left_view.add(btn_new_date,2,2);
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
			left_view.add(button_bar,0,6,3,1);
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
			left_view.add(tf_time,1,0);
			TextField tf_date = new TextField();
			left_view.add(tf_date,1,1);
			TextField tf_client_name = new TextField();
			left_view.add(tf_client_name,1,2);
			TextField tf_address = new TextField();
			left_view.add(tf_address,1,3);
			TextField tf_employee = new TextField();
			left_view.add(tf_employee,1,4);
			TextArea ta_brief = new TextArea();
			centre_panel.add(ta_brief,1,1);
			
		} else {
			Label lbl_time = new Label();
			UI_Templates.output_label_style(lbl_time);
			lbl_time.setMinSize(Algorithms.dimension_calculator(60.0,false),Algorithms.dimension_calculator(20.0,true));
			left_view.add(lbl_time,1,1);
			
			Label lbl_date = new Label();
			UI_Templates.output_label_style(lbl_date);
			lbl_date.setMinSize(Algorithms.dimension_calculator(40.0,false),Algorithms.dimension_calculator(20.0,true));
			left_view.add(lbl_date,1,2);
			
			Label lbl_client_name = new Label();
			UI_Templates.output_label_style(lbl_client_name);
			lbl_client_name.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			left_view.add(lbl_client_name,1,3);
			
			Label lbl_address = new Label();
			UI_Templates.output_label_style(lbl_address);
			lbl_address.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			left_view.add(lbl_address,1,4);

			Label lbl_employee = new Label();
			UI_Templates.output_label_style(lbl_employee);
			lbl_employee.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			left_view.add(lbl_employee,1,5);
			
			Label lbl_brief = new Label();
			UI_Templates.output_label_style(lbl_brief);
			lbl_brief.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(300.0,true));
			left_view.add(lbl_brief,3,1,2,6);
			centre_panel.add(lbl_brief,1,1);
		}
		centre_panel.add(left_view,0,1);
		centre_panel.add(lbl_brief_title,1,0);
		centre_panel.setAlignment(Pos.CENTER);
		return centre_panel;
	}
	
	public BorderPane get_scene(Stage primary_stage, boolean administrator) {
		BorderPane border_pane = new BorderPane();
		border_pane.setTop(header(primary_stage, administrator));
		border_pane.setCenter(centre_panel(administrator));
		return border_pane;
	}
}
