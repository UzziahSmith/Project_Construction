package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Appointment_List_UI {
	private BorderPane header(Stage primary_stage, boolean administrator) {
		String header_title;
		if(administrator) {
			header_title = "Appointments";
		} else {
			header_title = "My Assigned Jobs";
		}
		
		BorderPane header = UI_Templates.header(primary_stage,header_title);
		
		HBox navigation_button_bar = new HBox();		
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		UI_Templates.header_button_style(btn_navigate_home);
		Button btn_navigate_clients = new Button("Clients");
		UI_Templates.header_button_style(btn_navigate_clients);
		Button TEST_BTN_NAVIGATE_APPT_DETAILS = new Button("TEST \n APPOINTMENT DETAILS");
		if(administrator) {
			Button btn_navigate_employees = new Button("Employees");
			UI_Templates.header_button_style(btn_navigate_employees);
			Button btn_navigate_trades = new Button("Trades");
			UI_Templates.header_button_style(btn_navigate_trades);
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_Administrator_UI to Home_Screen_Administrator_UI.");
					Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_employees.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_Administrator_UI to Employee_Details_Screen_Administrator_UI.");
					Employee_Details_Screen_Administrator_UI employee_details_layout = new Employee_Details_Screen_Administrator_UI();
					Scene employee_details_screen = new Scene(employee_details_layout.get_scene(primary_stage));
					employee_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(employee_details_screen);
				}
			});
			btn_navigate_trades.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_Administrator_UI to Trade_List_Screen_Administrator_UI");
					Trade_List_Screen_Administrator_UI trade_list_layout = new Trade_List_Screen_Administrator_UI();
					Scene trade_list_screen = new Scene(trade_list_layout.get_scene(primary_stage));
					trade_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(trade_list_screen);
				}
			});
			btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_Administrator_UI to Clients_Screen_UI (Administrator).");
					Clients_Screen_UI client_details_layout = new Clients_Screen_UI();
					Scene client_details_screen = new Scene(client_details_layout.get_scene(primary_stage,true));
					client_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_details_screen);
				}
			});
			TEST_BTN_NAVIGATE_APPT_DETAILS.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("TEST Navigate from Appointment_List_UI to Appointment_Details_UI.");
					Appointment_Details_UI appointment_details_layout = new Appointment_Details_UI();
					Scene appointment_details_screen = new Scene(appointment_details_layout.get_scene(primary_stage,true));
					appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_details_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_clients,btn_navigate_employees,btn_navigate_trades,TEST_BTN_NAVIGATE_APPT_DETAILS);
		} else {
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_User_UI to Home_Screen_User_UI.");
					Home_Screen_User_UI home_screen_layout = new Home_Screen_User_UI();
					Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
					home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(home_screen_screen);
				}
			});
			btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Navigate from Appointment_List_User_UI to Clients_Screen_UI (User).");
					Clients_Screen_UI client_details_layout = new Clients_Screen_UI();
					Scene client_details_screen = new Scene(client_details_layout.get_scene(primary_stage,false));
					client_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_details_screen);
				}
			});
			TEST_BTN_NAVIGATE_APPT_DETAILS.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("TEST Navigate from Appointment_List_UI to Appointment_Details_UI.");
					Appointment_Details_UI appointment_details_layout = new Appointment_Details_UI();
					Scene appointment_details_screen = new Scene(appointment_details_layout.get_scene(primary_stage,false));
					appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_details_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_clients,TEST_BTN_NAVIGATE_APPT_DETAILS);
		}
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	private GridPane centre_panel(ArrayList<String> appointments, boolean administrator) {
		GridPane centre_panel = new GridPane();
		centre_panel.setHgap(Algorithms.dimension_calculator(100.0,false));
		centre_panel.setVgap(Algorithms.dimension_calculator(50.0,true));
		centre_panel.setAlignment(Pos.CENTER);
		
		ArrayList<String> day1_AL = new ArrayList<String>();
		ArrayList<String> day2_AL = new ArrayList<String>();
		ArrayList<String> day3_AL = new ArrayList<String>();
		ArrayList<String> day4_AL = new ArrayList<String>();
		ArrayList<String> day5_AL = new ArrayList<String>();
		
		Calendar calender = Calendar.getInstance();
		Date date = calender.getTime();
		int next_day_value = 86400000;
		String day_one = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime());
		String day_two = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime() + next_day_value);
		String day_three = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime() + (next_day_value*2));
		String day_four = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime() + (next_day_value*3));
		String day_five = new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date.getTime() + (next_day_value*4));
		
		VBox vb_day1 = new VBox();
		Label lbl_day1_title = new Label(day_one);
		UI_Templates.title_label_style(lbl_day1_title);
		ListView<String> lv_day1 = new ListView<String>();
		UI_Templates.list_view_style(lv_day1);
		ObservableList<String> ol_day1 = FXCollections.observableArrayList();
		for(String appointment : day1_AL) {
			ol_day1.add(appointment);
		}
		lv_day1.getItems().addAll(ol_day1);
		lv_day1.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
		vb_day1.getChildren().addAll(lbl_day1_title,lv_day1);
		centre_panel.add(vb_day1,0,0);
		
		VBox vb_day2 = new VBox();
		Label lbl_day2_title = new Label(day_two);
		UI_Templates.title_label_style(lbl_day2_title);
		ListView<String> lv_day2 = new ListView<String>();
		UI_Templates.list_view_style(lv_day2);
		ObservableList<String> ol_day2 = FXCollections.observableArrayList();
		for(String appointment : day2_AL) {
			ol_day2.add(appointment);
		}
		lv_day2.getItems().addAll(ol_day2);
		lv_day2.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
		vb_day2.getChildren().addAll(lbl_day2_title,lv_day2);
		centre_panel.add(vb_day2,1,0);
		
		VBox vb_day3 = new VBox();
		Label lbl_day3_title = new Label(day_three);
		UI_Templates.title_label_style(lbl_day3_title);
		ListView<String> lv_day3 = new ListView<String>();
		UI_Templates.list_view_style(lv_day3);
		ObservableList<String> ol_day3 = FXCollections.observableArrayList();
		for(String appointment : day3_AL) {
			ol_day3.add(appointment);
		}
		lv_day3.getItems().addAll(ol_day3);
		lv_day3.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
		vb_day3.getChildren().addAll(lbl_day3_title,lv_day3);
		centre_panel.add(vb_day3,2,0);
		
		VBox vb_day4 = new VBox();
		Label lbl_day4_title = new Label(day_four);
		UI_Templates.title_label_style(lbl_day4_title);
		ListView<String> lv_day4 = new ListView<String>();
		UI_Templates.list_view_style(lv_day4);
		ObservableList<String> ol_day4 = FXCollections.observableArrayList();
		for(String appointment : day4_AL) {
			ol_day4.add(appointment);
		}
		lv_day4.getItems().addAll(ol_day4);
		lv_day4.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
		vb_day4.getChildren().addAll(lbl_day4_title,lv_day4);
		centre_panel.add(vb_day4,3,0);
		
		if(administrator) {
			VBox vb_master = new VBox();
			Label lbl_master_title = new Label("Master");
			UI_Templates.title_label_style(lbl_master_title);
			ListView<String> lv_master = new ListView<String>();
			UI_Templates.list_view_style(lv_master);
			ObservableList<String> ol_master = FXCollections.observableArrayList();
			for(String appointment : appointments) {
				ol_master.add(appointment);
			}
			lv_master.getItems().addAll(ol_master);
			lv_master.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
			vb_master.getChildren().addAll(lbl_master_title,lv_master);
			centre_panel.add(vb_master,4,0);
		} else {
			VBox vb_day5 = new VBox();
			Label lbl_day5_title = new Label(day_five);
			UI_Templates.title_label_style(lbl_day5_title);
			ListView<String> lv_day5 = new ListView<String>();
			UI_Templates.list_view_style(lv_day5);
			ObservableList<String> ol_day5 = FXCollections.observableArrayList();
			for(String appointment : day5_AL) {
				ol_day5.add(appointment);
			}
			lv_day5.getItems().addAll(ol_day5);
			lv_day5.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
			vb_day5.getChildren().addAll(lbl_day5_title,lv_day5);
			centre_panel.add(vb_day5,4,0);
		}
		
		// TO DO ADD INTERACTIIVE CALENDER //
		
		VBox vb_date_selected = new VBox();
		Label lbl_date_selected_title = new Label("Date: Day selected on calender");
		UI_Templates.title_label_style(lbl_date_selected_title);
		ListView<String> lv_date_selected = new ListView<String>();
		UI_Templates.list_view_style(lv_date_selected);
		ObservableList<String> ol_date_selected = FXCollections.observableArrayList();
		for(String appointment : appointments) {
			ol_date_selected.add(appointment);
		}
		lv_date_selected.getItems().addAll(ol_date_selected);
		lv_date_selected.setMaxSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(350.0,true));
		vb_date_selected.getChildren().addAll(lbl_date_selected_title,lv_date_selected);
		centre_panel.add(vb_date_selected,3,1,2,1);
		vb_date_selected.setAlignment(Pos.CENTER);
		
		return centre_panel;
	}
	
	public BorderPane get_scene(Stage primary_stage, boolean administrator) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> appointments_master = new ArrayList<String>();
		
		border_pane.setTop(header(primary_stage, administrator));
		border_pane.setCenter(centre_panel(appointments_master,administrator));
		return border_pane;
	}
}

