package application;

import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Clients_Screen_Administrator_UI {
	private HBox header(Stage primary_stage) {
		HBox header = new HBox();
		header.setPadding(new Insets(20,12,15,12));
		header.setSpacing(20);
		header.setPrefHeight(100);
		header.getStyleClass().add("admin_home_screen_header");
		header.setId("admin_home_screen_header");
		
		Button btn_sign_out = new Button("Sign out");
		btn_sign_out.setPrefSize(75,50);
		btn_sign_out.setAlignment(Pos.CENTER);
		btn_sign_out.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Sign out initiated");
				Sign_In_UI sign_in_layout = new Sign_In_UI();
				Scene sign_in_screen = new Scene(sign_in_layout.get_scene(primary_stage));
				sign_in_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(sign_in_screen);
			}
		});
		
		Label lbl_title = new Label("Clients");
		lbl_title.setAlignment(Pos.CENTER);
		lbl_title.setTextAlignment(TextAlignment.CENTER);
		lbl_title.getStyleClass().add("admin_home_title_text");
		lbl_title.setId("admin_home_title_text");
		
		HBox navigation_button_bar = new HBox();
		Button btn_navigate_home = new Button("Home");
		Button btn_navigate_appointments = new Button("Appointments");
		Button btn_navigate_employees = new Button("Employees");
		Button btn_navigate_trades = new Button("Trades");
		btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Clients_Screen_Administrator_UI to Home_Screen_Administrator_UI.");
				Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
				Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
				home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(home_screen_screen);
			}
		});
//		btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				System.out.println("Navigate from Clients_Screen_Administrator_UI to Appointment_List_Administrator_UI.");
//				Appointment_List_Administrator_UI appointment_list_layout = new Appointment_List_Administrator_UI();
//				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage));
//				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//				primary_stage.setScene(appointment_list_screen);
//			}
//		});
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
		
		header.getChildren().addAll(btn_sign_out,lbl_title,navigation_button_bar);
		return header;
	}
	
	@SuppressWarnings("static-access")
	private GridPane centre_view() {
		GridPane centre_view = new GridPane();
		
		Label lbl_first_name = new Label("First Name:");
		TextField tf_first_name = new TextField();
		centre_view.add(lbl_first_name,0,0);
		centre_view.add(tf_first_name,1,0);
		
		Label lbl_surname = new Label("Surname:");
		TextField tf_surname = new TextField();
		centre_view.add(lbl_surname,0,1);
		centre_view.add(tf_surname,1,1);
		
		Label lbl_phone_number = new Label("Phone Number:");
		TextField tf_phone_number = new TextField();
		centre_view.add(lbl_phone_number,0,2);
		centre_view.add(tf_phone_number,1,2);
		
		Label lbl_previous_client = new Label("Previous Client");
		TextField tf_previous_client = new TextField();
		centre_view.add(lbl_previous_client,0,3);
		centre_view.add(tf_previous_client,1,3);
		
		HBox client_interactive_button_bar = new HBox();
		Button btn_new = new Button("NEW");
		Button btn_add = new Button("ADD");
		Button btn_remove = new Button("REMOVE");
		Button btn_update = new Button("UPDATE");
		Button btn_cancel = new Button("CANCEL");
		btn_new.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("New client initialisation");
				tf_first_name.clear();
				tf_surname.clear();
				tf_phone_number.clear();
				tf_previous_client.clear();
			}
		});
		btn_add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Add new client");
			}
		});
		btn_remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Remove selected client");
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
				tf_previous_client.clear();
			}
		});
		client_interactive_button_bar.getChildren().addAll(btn_new, btn_add, btn_remove, btn_update, btn_cancel);
		centre_view.add(client_interactive_button_bar,0,4);
		centre_view.setColumnSpan(client_interactive_button_bar,2);
		
		return centre_view;
	}
	
	private VBox right_view(ArrayList<String> clients) {
		VBox right_view = new VBox();
		right_view.setPadding(new Insets(50,250,20,20));
		
		Label lbl_clients_list_title = new Label("Clients");
		lbl_clients_list_title.setAlignment(Pos.CENTER);
		lbl_clients_list_title.setMaxWidth(Double.MAX_VALUE);
		lbl_clients_list_title.setPadding(new Insets(0,0,20,0));
		
		ListView<String> lv_clients = new ListView<String>();
		ObservableList<String> lv_clients_items = FXCollections.observableArrayList();
		for(String client : clients) {
			lv_clients_items.add(client);
		}
		lv_clients.setItems(lv_clients_items);
		lv_clients.setPrefSize(300,500);
		right_view.getChildren().addAll(lbl_clients_list_title, lv_clients);
		
		return right_view;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_clients_AL = new ArrayList<String>();
		
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view());
		border_pane.setRight(right_view(test_clients_AL));
		return border_pane;
	}
}
