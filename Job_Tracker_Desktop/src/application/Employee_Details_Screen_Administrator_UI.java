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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Employee_Details_Screen_Administrator_UI { 
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
		
		Label lbl_title = new Label("Employees");
		lbl_title.setAlignment(Pos.CENTER);
		lbl_title.setTextAlignment(TextAlignment.CENTER);
		lbl_title.getStyleClass().add("admin_home_title_text");
		lbl_title.setId("admin_home_title_text");
		
		HBox navigation_button_bar = new HBox();
		Button btn_navigate_home = new Button("Home");
		Button btn_navigate_clients = new Button("Clients");
		Button btn_navigate_appointments = new Button("Appointments");
		Button btn_navigate_trades = new Button("Trades");
		
		btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Home_Screen_Administrator_UI.");
				Home_Screen_Administrator_UI home_screen_layout = new Home_Screen_Administrator_UI();
				Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
				home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(home_screen_screen);
			}
		});
		btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Clients_Screen_Administrator_UI.");
				Clients_Screen_Administrator_UI clients_screen_layout = new Clients_Screen_Administrator_UI();
				Scene clients_screen_screen = new Scene(clients_screen_layout.get_scene(primary_stage));
				clients_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(clients_screen_screen);
			}
		});
		btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Appointment_List_Administrator_UI.");
			}
		});
		btn_navigate_trades.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Trade_List_Screen_Administrator_UI.");
				Trade_List_Screen_Administrator_UI trade_details_UI_layout = new Trade_List_Screen_Administrator_UI();
				Scene trade_details_UI_screen = new Scene(trade_details_UI_layout.get_scene(primary_stage));
				trade_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(trade_details_UI_screen);
			}
		});
		
		navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_clients,btn_navigate_appointments,btn_navigate_trades);
		
		header.getChildren().addAll(btn_sign_out,lbl_title,navigation_button_bar);
		return header;
	}
	
	private VBox centre_view(ArrayList<String> trade_title) {
		VBox centre_view = new VBox();
		
		HBox hbox_first_name = new HBox();
		Label lbl_first_name = new Label("First Name:");
		TextField tf_first_name = new TextField("");
		hbox_first_name.getChildren().addAll(lbl_first_name, tf_first_name);
		
		HBox hbox_surname = new HBox();
		Label lbl_surname = new Label("Surname:");
		TextField tf_surname = new TextField();
		hbox_surname.getChildren().addAll(lbl_surname, tf_surname);
		
		HBox hbox_phone_number = new HBox();
		Label lbl_phone_name = new Label("Phone Number:");
		TextField tf_phone_name = new TextField();
		hbox_phone_number.getChildren().addAll(lbl_phone_name, tf_phone_name);
		
		HBox hbox_employed = new HBox();
		Label lbl_employed = new Label("Employed: ");
		RadioButton rb_employed_true = new RadioButton();
		Image image_employed_true = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\resources\\check_true.png");
		ImageView image_view_employed_true = new ImageView(image_employed_true);
		image_view_employed_true.setFitHeight(50);
		image_view_employed_true.setFitWidth(50);
		rb_employed_true.setGraphic(image_view_employed_true);
		RadioButton rb_employed_false = new RadioButton();
		Image image_employed_false = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\resources\\cross_false.png");
		ImageView image_view_employed_false = new ImageView(image_employed_false);
		image_view_employed_false.setFitHeight(50);
		image_view_employed_false.setFitWidth(50);
		rb_employed_false.setGraphic(image_view_employed_false);
		ToggleGroup toggle_group = new ToggleGroup();
		rb_employed_true.setToggleGroup(toggle_group);
		rb_employed_false.setToggleGroup(toggle_group);
		toggle_group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
		hbox_employed.getChildren().addAll(lbl_employed, rb_employed_true, rb_employed_false);
		
		HBox hbox_trade = new HBox();
		Label lbl_trade = new Label("Trade: ");
		ComboBox<String> trade_combo_box = new ComboBox<String>();
		for(String title : trade_title) {
			trade_combo_box.getItems().add(title);
		}
		hbox_trade.getChildren().addAll(lbl_trade, trade_combo_box);
		
		HBox trade_details_button_bar = new HBox();
		Button btn_new = new Button("NEW");
		Button btn_add = new Button("ADD");
		Button btn_remove = new Button("REMOVE");
		Button btn_update = new Button("UPDATE");
		Button btn_cancel = new Button("CANCEL");
		trade_details_button_bar.getChildren().addAll(btn_new, btn_add, btn_remove, btn_update, btn_cancel);
		
		centre_view.getChildren().addAll(hbox_first_name, hbox_surname, hbox_phone_number, hbox_employed, hbox_trade, trade_details_button_bar);
		
		return centre_view;
	}
	
	private VBox right_view(ArrayList<String> employees) {
		VBox right_view = new VBox();
		right_view.setPadding(new Insets(50,250,20,20));
		
		Label lbl_employee_list_view_title = new Label("Employees");
		lbl_employee_list_view_title.setAlignment(Pos.CENTER);
		lbl_employee_list_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_employee_list_view_title.setPadding(new Insets(0,0,20,0));
		
		ListView<String> lv_employees = new ListView<String>();
		ObservableList<String> lv_employee_items = FXCollections.observableArrayList();
		for(String employee : employees) {
			lv_employee_items.add(employee);
		}
		lv_employees.setItems(lv_employee_items);
		lv_employees.setPrefSize(300,500);
		
		right_view.getChildren().addAll(lbl_employee_list_view_title, lv_employees);
		
		return right_view;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_trades = new ArrayList<String>();
		test_trades.add("Plasterer");
		test_trades.add("Accountant");
		test_trades.add("Plumber");
		test_trades.add("General Labourer");
		
		ArrayList<String> test_employees = new ArrayList<String>();
		test_employees.add("John Green");
		test_employees.add("Jeremy Turnball");
		test_employees.add("Michael Bolter");
		test_employees.add("Sesna 2903");
		
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view(test_trades));
		border_pane.setRight(right_view(test_employees));
		return border_pane;
	}
}
