package application;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Home_Screen_User_UI {
	
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
	
	private GridPane centre_view(Stage primary_stage) {
		GridPane grid = new GridPane();
		grid.setHgap(Algorithms.dimension_calculator(150.0,false));
		grid.setVgap(Algorithms.dimension_calculator(20.0,true));
		grid.setPadding(new Insets(Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(20.0,false),
				Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(20.0,false)));
		
		Label lbl_jobs_title = new Label("Jobs");
		lbl_jobs_title.setAlignment(Pos.CENTER);
		lbl_jobs_title.setMaxWidth(Double.MAX_VALUE);
		lbl_jobs_title.getStyleClass().add("user_home_title");
		lbl_jobs_title.setId("user_home_title");
		
		VBox vb_todays_appointments = new VBox();
		Label lbl_todays_appointments = new Label("Today");
		lbl_todays_appointments.setAlignment(Pos.CENTER);
		lbl_todays_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_todays_appointments.setPadding(new Insets(0,
				Algorithms.dimension_calculator(10.0,false),
				Algorithms.dimension_calculator(10.0,true),
				Algorithms.dimension_calculator(10.0,false)));
		UI_Templates.title_label_style(lbl_todays_appointments);
		ListView<String> todays_appointments_list = new ListView<String>();
		ObservableList<String> todays_appointments_items = FXCollections.observableArrayList(
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
				"John Green","Celery Adams","Barker Chicken",
				"John Green","Celery Adams","Barker Chicken"
				);
		todays_appointments_list.setItems(todays_appointments_items);
		todays_appointments_list.setPrefWidth(Algorithms.dimension_calculator(75.0,false));
		todays_appointments_list.setPrefHeight(Algorithms.dimension_calculator(350.0,true));
		todays_appointments_list.setPadding(new Insets(Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(100.0,false),
				Algorithms.dimension_calculator(70.0,true),
				Algorithms.dimension_calculator(100.0,false)));
		UI_Templates.list_view_style(todays_appointments_list);
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
		vb_todays_appointments.getChildren().addAll(lbl_todays_appointments,todays_appointments_list);
		
		VBox vb_tomorrows_appointments = new VBox();
		Label lbl_tomorrows_appointments = new Label("Tomorrow");
		lbl_tomorrows_appointments.setAlignment(Pos.CENTER);
		lbl_tomorrows_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_tomorrows_appointments.setPadding(new Insets(0,
				Algorithms.dimension_calculator(10.0,false),
				Algorithms.dimension_calculator(10.0,true),
				Algorithms.dimension_calculator(10.0,false)));
		UI_Templates.title_label_style(lbl_tomorrows_appointments);
		ListView<String> tomorrows_appointments_list = new ListView<String>();
		ObservableList<String> tomorrows_appointments_items = FXCollections.observableArrayList(
				"Uzziah Gary Smith: 9:00AM\nBull Creek","Celery Adams","Barker Chicken",
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
				"John Green","Celery Adams","Barker Chicken"
				);
		tomorrows_appointments_list.setItems(tomorrows_appointments_items);
		tomorrows_appointments_list.setPrefWidth(Algorithms.dimension_calculator(75.0,false));
		tomorrows_appointments_list.setPrefHeight(Algorithms.dimension_calculator(350.0,true));
		tomorrows_appointments_list.setPadding(new Insets(Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(100.0,false),
				Algorithms.dimension_calculator(50.0,true),
				Algorithms.dimension_calculator(100.0,false)));
		UI_Templates.list_view_style(tomorrows_appointments_list);
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
		vb_tomorrows_appointments.getChildren().addAll(lbl_tomorrows_appointments,tomorrows_appointments_list);
		
		HBox left_view = new HBox();
		left_view.setSpacing(Algorithms.dimension_calculator(50.0,false));
		left_view.getChildren().addAll(vb_todays_appointments,vb_tomorrows_appointments);
		left_view.setMaxWidth(Double.MAX_VALUE);
		left_view.setAlignment(Pos.CENTER);
		
		Label lbl_view_title = new Label("View");
		lbl_view_title.setAlignment(Pos.CENTER);
		lbl_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_view_title.getStyleClass().add("user_home_title");
		lbl_view_title.setId("user_home_title");
		
		VBox button_vb = new VBox();
		button_vb.setSpacing(Algorithms.dimension_calculator(70.0,true));
		button_vb.setPadding(new Insets(Algorithms.dimension_calculator(20.0,true),0,0,0));
		Button btn_view_assigned_jobs = new Button("Assigned Jobs");
		btn_view_assigned_jobs.setPrefSize(Algorithms.dimension_calculator(450.0,false),Algorithms.dimension_calculator(150.0,true));
		UI_Templates.UI_button(btn_view_assigned_jobs);
		
		Button btn_view_clients = new Button("Clients");
		btn_view_clients.setPrefSize(Algorithms.dimension_calculator(450.0,false),Algorithms.dimension_calculator(150.0,true));
		UI_Templates.UI_button(btn_view_clients);
		
		btn_view_assigned_jobs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Home_Screen_User_UI to Appointment_List_UI.");
				Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,false));
				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_list_screen);
			}
		});
		btn_view_clients.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Home_Screen_User_UI to Clients_Screen_UI (User).");
				Clients_Screen_UI client_screen_layout = new Clients_Screen_UI();
				Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage,false));
				client_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(client_screen_screen);
			}
		});
		button_vb.getChildren().addAll(btn_view_assigned_jobs,btn_view_clients);
		
		GridPane Calender = UI_Templates.Calender(primary_stage,false,500,300);
		
		grid.add(Calender,0,2);
		grid.add(lbl_jobs_title,0,0);
		grid.add(left_view,0,1);;
		grid.add(lbl_view_title,2,0);
		grid.add(button_vb,2,1,1,2);
		grid.setAlignment(Pos.CENTER);
		grid.setGridLinesVisible(true);
		return grid;
	}

	public BorderPane get_scene(Stage primary_stage) {
		try {
			BorderPane border_pane = new BorderPane();
			Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
			border_pane.setPrefSize(screen_bounds.getWidth()*0.8,screen_bounds.getHeight()*0.8);
			border_pane.setTop(UI_Templates.header(primary_stage, Main.user_data.email + ": " + Main.user_data.business));
			border_pane.setCenter(centre_view(primary_stage));
			return border_pane;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
