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

public class Trade_List_Screen_Administrator_UI {
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
		Button btn_navigate_clients = new Button("Clients");
		Button btn_navigate_appointments = new Button("Appointments");
		Button btn_navigate_employees = new Button("Employees");
		btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Home_Screen_Administrator_UI.");
				Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
				Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
				home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(home_screen_screen);
			}
		});
		btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Clients_Screen_Administrator_UI.");
				Clients_Screen_Administrator_UI client_list_layout = new Clients_Screen_Administrator_UI();
				Scene client_list_screen = new Scene(client_list_layout.get_scene(primary_stage));
				client_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(client_list_screen);
			}
		});
//		btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Appointment_List_Administrator_UI.");
//				Appointment_List_Administrator_UI appointment_list_layout = new Appointment_List_Administrator_UI();
//				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage));
//				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//				primary_stage.setScene(appointment_list_screen);
//			}
//		});
		btn_navigate_employees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Employee_Details_Screen_Administrator_UI.");
				Employee_Details_Screen_Administrator_UI employee_details_layout = new Employee_Details_Screen_Administrator_UI();
				Scene employee_details_screen = new Scene(employee_details_layout.get_scene(primary_stage));
				employee_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(employee_details_screen);
			}
		});

		navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_clients,btn_navigate_appointments,btn_navigate_employees);
		
		header.getChildren().addAll(btn_sign_out,lbl_title,navigation_button_bar);
		return header;
	}
	
	@SuppressWarnings("static-access")
	private GridPane centre_view() {
		GridPane grid = new GridPane();
		
		Label lbl_trade_title = new Label("Title:");
		grid.add(lbl_trade_title,0,0);
		
		TextField tf_trade_title = new TextField();
		grid.add(tf_trade_title,1,0);
		
		HBox trade_interactive_button_bar = new HBox();
		Button btn_add = new Button("ADD");
		Button btn_remove = new Button("REMOVE");
		Button btn_cancel = new Button("CANCEL");
		btn_add.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				System.out.println("Add new trade title");
			}
		});
		btn_remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Remove current trade title");
			}
		});
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Cancel new trade title");
				tf_trade_title.clear();
			}
		});
		trade_interactive_button_bar.getChildren().addAll(btn_add,btn_remove,btn_cancel);
		grid.add(trade_interactive_button_bar,0,1);
		grid.setColumnSpan(trade_interactive_button_bar,2);
		
		return grid;
	}
	
	private VBox right_view(ArrayList<String> trades) {
		VBox right_view = new VBox();
		right_view.setPadding(new Insets(50,250,20,20));
		
		Label lbl_trade_list_view_title = new Label("Trades");
		lbl_trade_list_view_title.setAlignment(Pos.CENTER);
		lbl_trade_list_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_trade_list_view_title.setPadding(new Insets(0,0,20,0));
		
		ListView<String> lv_trades = new ListView<String>();
		ObservableList<String> lv_trade_items = FXCollections.observableArrayList();
		for(String trade : trades) {
			lv_trade_items.add(trade);
		}
		lv_trades.setItems(lv_trade_items);
		lv_trades.setPrefSize(300,500);
		
		right_view.getChildren().addAll(lbl_trade_list_view_title, lv_trades);
		
		return right_view;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_trades = new ArrayList<String>();
		
		border_pane.setTop(header(primary_stage));
		border_pane.setRight(right_view(test_trades));
		border_pane.setCenter(centre_view());
		return border_pane;
	}
}
