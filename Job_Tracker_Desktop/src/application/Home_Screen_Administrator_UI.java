package application;

import java.util.Calendar;
import java.util.List;

import com.job_tracker.attribute_creation.Appointment;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Home_Screen_Administrator_UI {
	
	Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
	double screen_width = screen_bounds.getWidth();
	double screen_height = screen_bounds.getHeight();
	
	private Calendar cal = Calendar.getInstance();
	private int current_day = cal.get(Calendar.DAY_OF_MONTH);
	private int current_month = cal.get(Calendar.MONTH);
	private int current_year = cal.get(Calendar.YEAR);
	private String todays_date = UI_Templates.date_corrector(current_day,current_month,current_year);
	private String tomorrows_date = UI_Templates.date_corrector(current_day+1,current_month,current_year);

	private VBox vb_appointments(Stage primary_stage) {
		
		VBox vb_appointments = new VBox();
		vb_appointments.setPrefWidth(screen_width*0.3);
		vb_appointments.getStyleClass().add("admin_home_screen_vb_appointments");
		vb_appointments.setId("admin_home_screen_vb_appointments");
		vb_appointments.setAlignment(Pos.CENTER);
		
		Label lbl_todays_appointments = new Label("Today's Appointments");
		lbl_todays_appointments.setAlignment(Pos.TOP_CENTER);
		lbl_todays_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_todays_appointments.setPadding(new Insets(screen_height*0.028,screen_width*0.0052,screen_height*0.0093,screen_width*0.0052));
		lbl_todays_appointments.getStyleClass().add("admin_home_listview_label");
		lbl_todays_appointments.setId("admin_home_listview_label");
		
		ListView<String> todays_appointments_list = new ListView<String>();
		ObservableList<String> todays_appointments_items = FXCollections.observableArrayList();
		List<Appointment> todays_appointments = Algorithms.appointments_by_date(todays_date);
		int counter = 1;
		for(Appointment appointment : todays_appointments) {
			String output = String.format("%s: %s, %s\nClient: %s\nEmployee: %s\nLocation: %s",
					counter,
					appointment.time,
					appointment.date,
					Algorithms.client_details_output(appointment.client_id),
					Algorithms.employee_details_output(appointment.employee_id),
					Algorithms.location_details(appointment.location_id));
			todays_appointments_items.add(output);
			counter++;
		}
		todays_appointments_list.setItems(todays_appointments_items);
		todays_appointments_list.setPrefWidth(screen_width*0.0695);
		todays_appointments_list.setPrefHeight(screen_height*0.324);
		todays_appointments_list.setPadding(new Insets(screen_height*0.0185,screen_width*0.052,screen_height*0.0648,screen_width*0.052));
		todays_appointments_list.getStyleClass().add("admin_home_listview");
		todays_appointments_list.setId("admin_home_listview");
		todays_appointments_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				String currentItemSelected = todays_appointments_list.getSelectionModel().getSelectedItem();
				System.out.println(currentItemSelected);
				if (click.getClickCount() == 1) {
					System.out.println("Open Brief");
					Popup appointment_brief = UI_Templates.appointment_brief_popup(primary_stage,Main.appointments_array.get(Algorithms.get_appointment_count(currentItemSelected)));
					if(!appointment_brief.isShowing()) {
						appointment_brief.show(primary_stage);
						System.out.println("Open brief");
					} else {
						appointment_brief.setAutoHide(true);
						System.out.println("Close brief");
					}
				} else if (click.getClickCount() == 2) {
					System.out.println("Open Details");
					Appointment_Details_UI appointment_details_UI_layout = new Appointment_Details_UI();
					Scene appointment_details_UI_screen = new Scene(appointment_details_UI_layout.get_scene(primary_stage,true,null,Main.appointments_array.get(Algorithms.get_appointment_count(currentItemSelected))));
					appointment_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_details_UI_screen);
				}
			}
		});
		
		Label lbl_tomorrows_appointments = new Label("Tomorrow's Appointments");
		lbl_tomorrows_appointments.setAlignment(Pos.CENTER);
		lbl_tomorrows_appointments.setTextAlignment(TextAlignment.CENTER);
		lbl_tomorrows_appointments.setPadding(new Insets(0,screen_width*0.005,screen_height*0.01,screen_width*0.005));
		lbl_tomorrows_appointments.getStyleClass().add("admin_home_listview_label");
		lbl_tomorrows_appointments.setId("admin_home_listview_label");
		
		ListView<String> tomorrows_appointments_list = new ListView<String>();
		ObservableList<String> tomorrows_appointments_items = FXCollections.observableArrayList();
		List<Appointment> tomorrows_appointments = Algorithms.appointments_by_date(tomorrows_date);
		for(Appointment appointment : tomorrows_appointments) {
			String output = String.format("%s: %s, %s\nClient: %s\nEmployee: %s\nLocation: %s",
					appointment.time,
					appointment.date,
					Algorithms.client_details_output(appointment.client_id),
					Algorithms.employee_details_output(appointment.employee_id),
					Algorithms.location_details(appointment.location_id));
			todays_appointments_items.add(output);
		}
		tomorrows_appointments_list.setItems(tomorrows_appointments_items);
		tomorrows_appointments_list.setPrefWidth(screen_width*0.0695);
		tomorrows_appointments_list.setPrefHeight(screen_height*0.324);
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
					Popup appointment_brief = UI_Templates.appointment_brief_popup(primary_stage,Main.appointments_array.get(Algorithms.get_appointment_count(currentItemSelected)));
					if(!appointment_brief.isShowing()) {
						appointment_brief.show(primary_stage);
						System.out.println("Open brief");
					} else {
						appointment_brief.setAutoHide(true);
						System.out.println("Close brief");
					}
				} else if(click.getClickCount() == 2) {
					System.out.println("Open Details");
					System.out.println("Open Details");
					Appointment_Details_UI appointment_details_UI_layout = new Appointment_Details_UI();
					Scene appointment_details_UI_screen = new Scene(appointment_details_UI_layout.get_scene(primary_stage,true,null,Main.appointments_array.get(Algorithms.get_appointment_count(currentItemSelected))));
					appointment_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_details_UI_screen);
				} 
			}
		});
		vb_appointments.getChildren().addAll(lbl_todays_appointments,todays_appointments_list,lbl_tomorrows_appointments,tomorrows_appointments_list);
		return vb_appointments;
	}
	
	private GridPane centre_selection_grid(Stage primary_stage) {
		GridPane grid = new GridPane();
		grid.setHgap(screen_height*0.07);
		grid.setVgap(screen_width*0.04);
		grid.setPadding(new Insets(screen_height*0.0231,screen_width*0.013,screen_height*0.0231,screen_width*0.013));
		grid.setAlignment(Pos.CENTER);
		
		Button btn_clients = new Button("Clients");
		Button btn_appointments = new Button("Appointments");
		Button btn_employees = new Button("Employees");
		Button btn_trades = new Button("Trades");
		
		btn_clients.setPrefSize(screen_width*0.1,screen_height*0.14);
		btn_appointments.setPrefSize(screen_width*0.1,screen_height*0.14);
		btn_employees.setPrefSize(screen_width*0.1,screen_height*0.14);
		btn_trades.setPrefSize(screen_width*0.1,screen_height*0.14);
		
		btn_clients.getStyleClass().add("admin_home_screen_button");
		btn_clients.setId("admin_home_screen_button");
		btn_appointments.getStyleClass().add("admin_home_screen_button");
		btn_appointments.setId("admin_home_screen_button");
		btn_employees.getStyleClass().add("admin_home_screen_button");
		btn_employees.setId("admin_home_screen_button");
		btn_trades.getStyleClass().add("admin_home_screen_button");
		btn_trades.setId("admin_home_screen_button");
		
		btn_clients.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Clients button pressed");
				Clients_UI client_details_UI_Layout = new Clients_UI();
				Scene client_details_UI_screen = new Scene(client_details_UI_Layout.get_scene(primary_stage,true));
				client_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(client_details_UI_screen);
			}
		});
		btn_appointments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Appointments button pressed");
				Appointment_List_UI appointment_list_UI_layout = new Appointment_List_UI();
				Scene appointment_list_UI_screen = new Scene(appointment_list_UI_layout.get_scene(primary_stage,true));
				appointment_list_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_list_UI_screen);
			}
		});
		btn_employees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Employees button pressed");
				Employee_Details_Screen_Administrator_UI employee_details_UI_Layout = new Employee_Details_Screen_Administrator_UI();
				Scene employee_details_UI_screen = new Scene(employee_details_UI_Layout.get_scene(primary_stage));
				employee_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(employee_details_UI_screen);
			}
		});
		btn_trades.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Trades button pressed");
				Trade_List_Screen_Administrator_UI trade_details_UI_layout = new Trade_List_Screen_Administrator_UI();
				Scene trade_details_UI_screen = new Scene(trade_details_UI_layout.get_scene(primary_stage));
				trade_details_UI_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(trade_details_UI_screen);
			}
		});
		
		grid.add(btn_clients,0,0);
		grid.add(btn_appointments,0,1);
		grid.add(btn_employees,1,0);
		grid.add(btn_trades,1,1);
		
		return grid;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		try {
			BorderPane border_pane = new BorderPane();
			border_pane.setPrefSize(screen_width*0.8,screen_height*0.8);
			border_pane.setTop(UI_Templates.header(primary_stage, "Administrator: " + Main.user_data.business));
			border_pane.setCenter(centre_selection_grid(primary_stage));
			border_pane.setLeft(vb_appointments(primary_stage));
			return border_pane;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
