package application;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Home_Screen_User_UI {
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
		
		Label lbl_title = new Label("Administrator: COMPANY_NAME");
		lbl_title.setAlignment(Pos.CENTER);
		lbl_title.setMaxHeight(Double.MAX_VALUE);
		lbl_title.getStyleClass().add("admin_home_title_text");
		lbl_title.setId("admin_home_title_text");
		
		header.getChildren().addAll(btn_sign_out,lbl_title);
		return header;
	}
	
	private Popup appointment_brief(String client_name, String time, String date, String address, String assigned_employee) {
		Popup popup = new Popup();
		
		VBox Vbox = new VBox();
		popup.getContent().add(Vbox);
		
		Label lbl_client_name = new Label(client_name);
		Label lbl_time = new Label(time);
		Label lbl_date = new Label(date);
		Label lbl_address = new Label(address);
		Label lbl_assigned_employee = new Label(assigned_employee);
		
		Vbox.getChildren().addAll(lbl_client_name,lbl_time,lbl_date,lbl_address,lbl_assigned_employee);
		
		return popup;
	}
	
	private GridPane left_view(Stage primary_stage) {
		GridPane grid = new GridPane();
		grid.setHgap(50);
		grid.setVgap(20);
		grid.setPadding(new Insets(20,20,20,20));
		
		Label lbl_todays_appointments = new Label("Today's Appointments");
		lbl_todays_appointments.setAlignment(Pos.CENTER);
		lbl_todays_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_todays_appointments.setPadding(new Insets(30,10,10,10));
		lbl_todays_appointments.getStyleClass().add("admin_home_listview_label");
		lbl_todays_appointments.setId("admin_home_listview_label");
		
		ListView<String> todays_appointments_list = new ListView<String>();
		ObservableList<String> todays_appointments_items = FXCollections.observableArrayList("John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken");
		todays_appointments_list.setItems(todays_appointments_items);
		todays_appointments_list.setPrefWidth(75);
		todays_appointments_list.setPrefHeight(350);
		todays_appointments_list.setPadding(new Insets(20,100,70,100));
		todays_appointments_list.getStyleClass().add("admin_home_listview");
		todays_appointments_list.setId("admin_home_listview");
		todays_appointments_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				String currentItemSelected = todays_appointments_list.getSelectionModel().getSelectedItem();
				System.out.println(currentItemSelected);
				if (click.getClickCount() == 1) {
					System.out.println("Open Brief");
					Popup appointment_brief = appointment_brief("Client Name","Appointment Time","Appointment Date",
							"Appointment Address","Assigned Employee");
					if(!appointment_brief.isShowing()) {
						appointment_brief.show(primary_stage);
						System.out.println("Open brief");
					} else {
						appointment_brief.setAutoHide(true);
						System.out.println("Close brief");
					}
				} else if (click.getClickCount() == 2) {
					System.out.println("Open Details");
				}
			}
		});
		
		Label lbl_tomorrows_appointments = new Label("Tomorrow's Appointments");
		lbl_tomorrows_appointments.setAlignment(Pos.CENTER);
		lbl_tomorrows_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_tomorrows_appointments.setPadding(new Insets(30,10,10,10));
		lbl_tomorrows_appointments.getStyleClass().add("admin_home_listview_label");
		lbl_tomorrows_appointments.setId("admin_home_listview_label");
		
		ListView<String> tomorrows_appointments_list = new ListView<String>();
		ObservableList<String> tomorrows_appointments_items = FXCollections.observableArrayList("Uzziah Gary Smith: 9:00AM\nBull Creek","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken");
		tomorrows_appointments_list.setItems(tomorrows_appointments_items);
		tomorrows_appointments_list.setPrefWidth(75);
		tomorrows_appointments_list.setPrefHeight(350);
		tomorrows_appointments_list.setPadding(new Insets(20,100,50,100));
		tomorrows_appointments_list.getStyleClass().add("admin_home_listview");
		tomorrows_appointments_list.setId("admin_home_listview");
		tomorrows_appointments_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				String currentItemSelected = tomorrows_appointments_list.getSelectionModel().getSelectedItem();
				System.out.println(currentItemSelected);
				if(click.getClickCount() == 1) {
					System.out.println("Open Brief");
				} else if(click.getClickCount() == 2) {
					System.out.println("Open Details");
				} 
			}
		});
		
		grid.add(lbl_todays_appointments,0,0);;
		grid.add(todays_appointments_list,0,1);
		grid.add(lbl_tomorrows_appointments,1,0);
		grid.add(tomorrows_appointments_list,1,1);
		
		return grid;
	}
	
	private VBox right_view(Stage primary_stage) {
		VBox Vbox = new VBox(3);
		Vbox.setSpacing(50);
		Vbox.setPadding(new Insets(100,300,100,0));
		
		Label lbl_view_title = new Label("View");
		lbl_view_title.setAlignment(Pos.CENTER);
		lbl_view_title.setMaxWidth(Double.MAX_VALUE);
		
		Button btn_view_assigned_jobs = new Button("Assigned Jobs");
		btn_view_assigned_jobs.setPrefSize(450,150);
		
		Button btn_view_clients = new Button("Clients");
		btn_view_clients.setPrefSize(450,150);
		
		Vbox.getChildren().addAll(lbl_view_title, btn_view_assigned_jobs, btn_view_clients);
		return Vbox;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		try {
			BorderPane border_pane = new BorderPane();			
			border_pane.setTop(header(primary_stage));
			border_pane.setRight(right_view(primary_stage));
			border_pane.setLeft(left_view(primary_stage));

			return border_pane;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
