package application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.textfield.TextFields;

import com.job_tracker.attribute_creation.Appointment;
import com.job_tracker.attribute_creation.Client;
import com.job_tracker.attribute_creation.Employee;
import com.job_tracker.database_interaction.Add_DB;
import com.job_tracker.database_interaction.Select_DB;
import com.job_tracker.database_interaction.Update_DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Appointment_Details_UI {
	
	private static boolean is_calendar_popup_showing = false;
	private boolean job_status_complete = false;
	private boolean job_started = false;	
	private boolean job_cancelled = false;
	
	private String get_record_id(String time, String date, String brief, String feedback, String client_id, String employee_id, String location_id) {
		List<Appointment> appointments = Main.appointments_array;
		for(Appointment appointment : appointments) {
			if(time.equals(appointment.time) && 
					date.equals(appointment.date) && 
					brief.equals(appointment.brief) &&
					feedback.equals(appointment.feedback) &&
					client_id.equals(appointment.client_id) && 
					employee_id.equals(appointment.employee_id) && 
					location_id.equals(appointment.location_id)) {
				return appointment.id;
			}
		}
		return null;
	}
	
	private BorderPane header(Stage primary_stage, boolean administrator) {
		BorderPane header = UI_Templates.header(primary_stage, "Appointment Details");
		
		HBox navigation_button_bar = new HBox();
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		UI_Templates.header_button_style(btn_navigate_home);
		Button btn_navigate_appointments = new Button("<");
		UI_Templates.header_button_style(btn_navigate_appointments);
		Button btn_navigate_clients = new Button("Clients");
		UI_Templates.header_button_style(btn_navigate_clients);
		if(administrator) {
			Button btn_navigate_employees = new Button("Employees");
			UI_Templates.header_button_style(btn_navigate_employees);
			Button btn_navigate_trades = new Button("Trades");
			UI_Templates.header_button_style(btn_navigate_trades);
			btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					UI_Templates.is_appt_details_shown = false;
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
					UI_Templates.is_appt_details_shown = false;
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Clients_Screen_UI (Administrator).");
					Clients_UI client_screen_layout = new Clients_UI();
					Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage,true));
					client_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_screen_screen);
				}
			});
			btn_navigate_employees.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					UI_Templates.is_appt_details_shown = false;
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
					UI_Templates.is_appt_details_shown = false;
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Trade_List_Screen_Administrator_UI.");
					Trade_List_Screen_Administrator_UI trade_list_layout = new Trade_List_Screen_Administrator_UI();
					Scene trade_list_screen = new Scene(trade_list_layout.get_scene(primary_stage));
					trade_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(trade_list_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_appointments,btn_navigate_home,btn_navigate_clients,btn_navigate_employees,btn_navigate_trades);
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
					System.out.println("Navigate from Appointment_Details_UI (User) to Appointment_List_UI (User).");
					Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
					Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,false));
					appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(appointment_list_screen);
				}
			});
			btn_navigate_clients.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					UI_Templates.is_appt_details_shown = false;
					System.out.println("Navigate from Appointment_Details_UI (User) to Clients_Screen_UI (User).");
					Clients_UI client_screen_layout = new Clients_UI();
					Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage,false));
					client_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_screen_screen);
				}
			});
			navigation_button_bar.getChildren().addAll(btn_navigate_appointments,btn_navigate_home,btn_navigate_clients);
		}
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	private Popup calendar_popup(Stage primary_stage) {
		Popup popup = new Popup();
		Pane calendar = UI_Templates.Calender(primary_stage,true,Algorithms.dimension_calculator(600.0,false),Algorithms.dimension_calculator(400.0,true));
		popup.getContent().add(calendar);
		return popup;
	}
	
	private Popup time_picker(Stage primary_stage, Label lbl_time) {
		Popup popup = new Popup();
		GridPane grid = new GridPane();
		Node picker = UI_Templates.time_picker(primary_stage, lbl_time);
		grid.add(picker,0,0);
		popup.getContent().add(grid);
		grid.getStyleClass().add("popup_standard_style");
		grid.setId("popup_standard_style");
		return popup;
	}
	
	private GridPane centre_panel(Stage primary_stage, boolean administrator, String date) {
		GridPane centre_panel = new GridPane();
		centre_panel.setHgap(Algorithms.dimension_calculator(100.0,false));
		GridPane centre_view = new GridPane();
		centre_view.setHgap(Algorithms.dimension_calculator(10.0,false));
		centre_view.setVgap(Algorithms.dimension_calculator(50.0,true));
		VBox left_view = new VBox();
		left_view.setSpacing(Algorithms.dimension_calculator(10.0,false));
		
		Label lbl_masterview_titles = new Label("Master View");
		UI_Templates.title_label_style(lbl_masterview_titles);
		lbl_masterview_titles.setMaxWidth(Double.MAX_VALUE);
		lbl_masterview_titles.setAlignment(Pos.CENTER);
		
		HBox hb_search = new HBox();
		hb_search.setSpacing(Algorithms.dimension_calculator(10.0,false));
		Label lbl_search = new Label("Find");
		lbl_search.setMaxHeight(Double.MAX_VALUE);
		lbl_search.setAlignment(Pos.CENTER);
		TextField tf_search = new TextField();
		hb_search.getChildren().addAll(lbl_search,tf_search);
		
		ListView<String> lv_masterview = new ListView<String>();
		ObservableList<String> masterview_items = FXCollections.observableArrayList();
		for(Appointment appointment : Main.appointments_array) {
//			TO DO add formatted items to masterview.
		}
		lv_masterview.setItems(masterview_items);
		lv_masterview.setMinSize(Algorithms.dimension_calculator(75.0,false),Algorithms.dimension_calculator(350.0,true));
		
		left_view.getChildren().addAll(lbl_masterview_titles,hb_search,lv_masterview);

		Label lbl_time_title = new Label("Time:");
		lbl_time_title.setMaxWidth(Double.MAX_VALUE);
		lbl_time_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_time_title);
		centre_view.add(lbl_time_title,0,0);
		
		Label lbl_date_title = new Label("Date:");
		lbl_date_title.setMaxWidth(Double.MAX_VALUE);
		lbl_date_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_date_title);
		centre_view.add(lbl_date_title,0,1);
		
		Label lbl_client_name_title = new Label("Client Name:");
		lbl_client_name_title.setMaxWidth(Double.MAX_VALUE);
		lbl_client_name_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_client_name_title);
		centre_view.add(lbl_client_name_title,0,2);
		
		Label lbl_address_title = new Label("Address:");
		lbl_address_title.setMaxWidth(Double.MAX_VALUE);
		lbl_address_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_address_title);
		centre_view.add(lbl_address_title,0,3);
		
		Label lbl_employee_title = new Label("Employee:");
		lbl_employee_title.setMaxWidth(Double.MAX_VALUE);
		lbl_employee_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_employee_title);
		centre_view.add(lbl_employee_title,0,4);
		
		Label lbl_date = new Label();
		UI_Templates.output_label_style(lbl_date);
		lbl_date.setMaxWidth(Double.MAX_VALUE);
		lbl_date.setAlignment(Pos.CENTER);
		lbl_date.setMaxSize(Algorithms.dimension_calculator(70.0,false),Algorithms.dimension_calculator(20.0,true));

		Label lbl_time = new Label();
		UI_Templates.output_label_style(lbl_time);
		lbl_time.setMaxWidth(Double.MAX_VALUE);
		lbl_time.setAlignment(Pos.CENTER);
		lbl_time.setMaxSize(Algorithms.dimension_calculator(70.0,false),Algorithms.dimension_calculator(20.0,true));
		
		Label lbl_job_status_title = new Label("Job Status:");
		lbl_job_status_title.setMaxWidth(Double.MAX_VALUE);
		lbl_job_status_title.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_job_status_title);
		centre_view.add(lbl_job_status_title,0,5);
		GridPane job_status_grid = new GridPane();
		job_status_grid.setHgap(Algorithms.dimension_calculator(10.0,false));
		Label lbl_job_status = new Label();
		lbl_job_status.setMaxWidth(Double.MAX_VALUE);
		lbl_job_status.setAlignment(Pos.CENTER);
		UI_Templates.output_label_style(lbl_job_status);
		lbl_job_status.setMinSize(100,20);
		Button btn_js_start = new Button("CHANGE TO START ICON");
		Button btn_js_reset = new Button("CHANGE TO RESET ICON");
		Button btn_js_complete = new Button("CHANGE TO COMPLETE ICON");
		Button btn_js_cancel = new Button("CHANGE TO CANCEL ICON");
		job_status_grid.add(lbl_job_status,0,0);
		job_status_grid.add(btn_js_start,1,0);
		job_status_grid.add(btn_js_reset,1,0);
		job_status_grid.add(btn_js_complete,1,0);
		job_status_grid.add(btn_js_cancel,2,0);
		centre_view.add(job_status_grid,1,5);
		if(!job_started) {
			lbl_job_status.setText("Pending");
			btn_js_start.setVisible(true);
			btn_js_complete.setVisible(false);
			btn_js_cancel.setVisible(false);
			btn_js_reset.setVisible(false);
		} else if(!job_status_complete) {
			btn_js_complete.setVisible(true);
			btn_js_cancel.setVisible(true);
			btn_js_start.setVisible(false);
			btn_js_reset.setVisible(false);
		} else if(job_cancelled) {
			btn_js_start.setVisible(false);
			btn_js_complete.setVisible(false);
			btn_js_cancel.setVisible(false);
			btn_js_reset.setVisible(true);
		} else {
			btn_js_reset.setVisible(false);
			btn_js_complete.setVisible(false);
			btn_js_cancel.setVisible(false);
			btn_js_start.setVisible(false);
		}
		btn_js_start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Appointment status changed to started");
				lbl_job_status.setText("In-progress");
				btn_js_complete.setVisible(true);
				btn_js_cancel.setVisible(true);
				btn_js_start.setVisible(false);
				btn_js_reset.setVisible(false);
				job_started = true;
			}
		});
		btn_js_complete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Appointment status changed to complete.");
				lbl_job_status.setText("Finished");
				btn_js_start.setVisible(false);
				btn_js_complete.setVisible(false);
				btn_js_cancel.setVisible(false);
				btn_js_reset.setVisible(true);
				job_status_complete = true;
			}
		});
		btn_js_cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Appointment status change to cancelled");
				lbl_job_status.setText("Cancelled");
				btn_js_complete.setVisible(false);
				btn_js_cancel.setVisible(false);
				btn_js_start.setVisible(false);
				btn_js_reset.setVisible(true);
				job_cancelled = true;
			}
		});
		btn_js_reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Appointment status change to in-progress");
				lbl_job_status.setText("Pending");
				btn_js_complete.setVisible(true);
				btn_js_cancel.setVisible(true);
				btn_js_start.setVisible(false);
				btn_js_reset.setVisible(false);
				job_cancelled = false;
			}
		});
		
		Label lbl_brief_title = new Label("Brief");
		lbl_brief_title.setMaxWidth(Double.MAX_VALUE);
		lbl_brief_title.setAlignment(Pos.CENTER_LEFT);
		UI_Templates.title_label_style(lbl_brief_title);
		centre_view.add(lbl_brief_title,3,0);
		
		VBox customer_feedback_vb = new VBox();
		Label lbl_customer_feedback_title = new Label("Customer Feedback");
		UI_Templates.title_label_style(lbl_customer_feedback_title);
		TextArea ta_customer_feedback = new TextArea();
		ta_customer_feedback.setEditable(administrator);
		ta_customer_feedback.setWrapText(true);
		ta_customer_feedback.setPrefRowCount(20);
		Algorithms.add_quantity_limiter_textarea(ta_customer_feedback,999);
		ta_customer_feedback.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(150.0,true));
		if(!administrator) {
			ta_customer_feedback.setEditable(false);
			Button btn_send_feedback = new Button("Send");
			btn_send_feedback.setMinSize(Algorithms.dimension_calculator(40.0,false),Algorithms.dimension_calculator(20.0,true));
			btn_send_feedback.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Push feedback");
					Update_DB.Update_String_Record(Main.url, Main.user, Main.password, Main.user_data.business, "appointments", "feedback", null, ta_customer_feedback.getText());
				}
			});
			HBox hb_customer_feedback = new HBox();
			hb_customer_feedback.setSpacing(Algorithms.dimension_calculator(10.0,false));
			hb_customer_feedback.getChildren().addAll(ta_customer_feedback,btn_send_feedback);
			customer_feedback_vb.getChildren().addAll(lbl_customer_feedback_title,hb_customer_feedback);
		} else {
			ta_customer_feedback.setEditable(true);
			customer_feedback_vb.getChildren().addAll(lbl_customer_feedback_title,ta_customer_feedback);
		}
		TextArea ta_brief = new TextArea();
		Algorithms.add_quantity_limiter_textarea(ta_brief,999);
		ta_brief.setWrapText(true);
		ta_brief.setPrefRowCount(20);
		ta_brief.setEditable(administrator);
		ta_brief.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(150.0,true));
		VBox vb_brief = new VBox();
		vb_brief.getChildren().addAll(lbl_brief_title,ta_brief);
		GridPane briefs_grid = new GridPane();
		briefs_grid.setVgap(Algorithms.dimension_calculator(50.0,true));
		briefs_grid.add(vb_brief,0,0);
		briefs_grid.add(customer_feedback_vb,0,1);
		centre_panel.add(briefs_grid,2,0,1,2);
		
		if(administrator) {
			TextField tf_client_id = new TextField();
			centre_view.add(tf_client_id,1,2);
			TextFields.bindAutoCompletion(tf_client_id, Main.clients_array);
			Button btn_new_client = new Button("+");
			centre_view.add(btn_new_client,2,2);
			
			HBox hb_address = new HBox();
			hb_address.setSpacing(Algorithms.dimension_calculator(5.0,false));
			TextField tf_street_number = new TextField();
			Algorithms.add_quantity_limiter(tf_street_number,7);
			TextField tf_street_name = new TextField();
			Algorithms.add_quantity_limiter(tf_street_name,50);
			TextField tf_postcode = new TextField();
			Algorithms.add_quantity_limiter(tf_postcode,4);
			Algorithms.add_input_limiter_integers(tf_postcode);
			hb_address.getChildren().addAll(tf_street_number,tf_street_name,tf_postcode);
			centre_view.add(hb_address,1,3);
			
			TextField tf_employee_id = new TextField();
			centre_view.add(tf_employee_id,1,4);
			
			HBox date_hb = new HBox();
			date_hb.setSpacing(Algorithms.dimension_calculator(10.0,false));
			Button btn_date = new Button("Change to calendar icon");
			date_hb.getChildren().addAll(lbl_date,btn_date);
			btn_date.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println(String.valueOf(is_calendar_popup_showing));
					System.out.println("Initialising popup for date");
					Popup calendar_popup = calendar_popup(primary_stage);
					calendar_popup.setAutoHide(true);
					if(!calendar_popup.isShowing()) {
						calendar_popup.show(primary_stage);
					} 
				}
			});
			centre_view.add(date_hb,1,1);
			
			if(date != null) {
				lbl_date.setText(String.valueOf(date));
			}
			
			HBox time_hb = new HBox();
			time_hb.setSpacing(Algorithms.dimension_calculator(10.0,false));
			Button btn_time = new Button("Change to clock icon");
			time_hb.getChildren().addAll(lbl_time,btn_time);
			btn_time.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Initialising popup for time");
					Popup time_picker_popup = time_picker(primary_stage,lbl_time);
					time_picker_popup.setAutoHide(true);
					if(!time_picker_popup.isShowing()) {
						time_picker_popup.show(primary_stage);
					} 
				}
			});
			centre_view.add(time_hb,1,0);
		
			HBox button_bar = new HBox();
			button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
			Button btn_new = new Button("NEW");
			UI_Templates.enable_interaction_button(btn_new);
			Button btn_add = new Button("ADD");
			UI_Templates.disable_interaction_button(btn_add);
			Button btn_update = new Button("UPDATE");
			UI_Templates.enable_interaction_button(btn_update);
			Button btn_cancel = new Button("CANCEL");
			UI_Templates.disable_interaction_button(btn_cancel);
			btn_new.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("New client initialisation");
					lbl_date.setText(null);
					lbl_time.setText(null);
					tf_client_id.clear();
					tf_street_number.clear();
					tf_street_name.clear();
					tf_postcode.clear();
					tf_employee_id.clear();
					ta_brief.clear();
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
					lbl_date.setText(null);
					lbl_time.setText(null);
					tf_client_id.clear();
					tf_street_number.clear();
					tf_street_name.clear();
					tf_postcode.clear();
					tf_employee_id.clear();
					ta_brief.clear();
					UI_Templates.disable_interaction_button(btn_add);
					UI_Templates.disable_interaction_button(btn_cancel);
					UI_Templates.enable_interaction_button(btn_new);
					UI_Templates.enable_interaction_button(btn_update);
					//String(=5) time
					String time_s = lbl_time.getText();  
					SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
					SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
					try {
						Date time_output = parseFormat.parse(time_s);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					boolean time_valid = time_s.length() == 5 ? true : false;
					if(!time_valid) {System.out.println("Logical Error: time allowing strings over or below 5 characters.");}
					//String(8-10) date
					String date_s = lbl_date.getText();
					boolean date_valid = date_s.length() > 7 && date_s.length() < 11 ? true : false;
					if(!date_valid) {System.out.println("Logical Error: date allowing strings under 8 or above 10 characters.");}
					//String(<1000) brief
					String brief_s = ta_brief.getText();
					boolean brief_valid = brief_s.length() < 1000 ? true : false;
					if(!brief_valid) {System.out.println("Logical Error: brief allowing strings over 999 characters.");}
					//String(<1000) feedback
					String feedback_s = ta_customer_feedback.getText();
					boolean feedback_valid = feedback_s.length() < 1000 ? true : false;
					if(!feedback_valid) {System.out.println("Logical Error: customer_feedback allowing strings over 999 characters.");}
					//String(7) client_id
					String client_id_s = tf_client_id.getText();
					boolean client_exists = Algorithms.client_exists_id(client_id_s);
					//String(7) employee_id
					String employee_id_s = tf_employee_id.getText();
					boolean employee_exists = Algorithms.employee_exists_id(employee_id_s);
					//Location
					//String(<8) street_number
					String street_number_s = tf_street_number.getText();
					boolean street_number_valid = street_number_s.length() < 8 ? true : false;
					if(!street_number_valid) {System.out.println("Logical Error: street_number allowing number strings over 7 characters.");}
					//String(50) street_name
					String street_name_s = tf_street_name.getText();
					boolean street_name_valid = street_name_s.length() < 51 ? true : false;
					if(!street_name_valid) {System.out.println("Logical Error: street_name allowing strings over 50 characters.");}
					//Int(=4) postcode 
					int postcode_int = Integer.parseInt(tf_postcode.getText());
					boolean postcode_valid = postcode_int < 10000 ? true : false;
					if(!postcode_valid) {System.out.println("Logical Error: postcode allowing integers over 4 digits.");}
					
					//Location validity
					@SuppressWarnings("unused")
					boolean location_valid = false;
					if(street_number_valid && street_name_valid && postcode_valid && client_exists) {
						location_valid = true;
					} else {
						System.out.println("Logical Error: Cannot add location with an invalid client.");
					}
					
					//Add location
					boolean location_exists = Algorithms.location_exists(street_number_s, street_name_s, postcode_int, client_id_s);
					if(!location_exists && location_valid) {
						Add_DB.Location(Main.url,Main.user,Main.password,Main.user_data.business,street_number_s,street_name_s,postcode_int,client_id_s);
						try {
							Main.locations_array = Select_DB.Extract_Data_Record_Locations(Main.url,Main.user,Main.password,Main.user_data.business);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						location_exists = true;
					}
					
					String location_id_s = Algorithms.output_location_id(street_number_s, street_name_s, postcode_int, client_id_s);
					
					//Add appointment
					if(time_valid && date_valid & brief_valid && feedback_valid && client_exists && employee_exists && location_valid) {
						try {
							if(Algorithms.appointment_exists(time_s, date_s, brief_s, feedback_s, client_id_s, employee_id_s, location_id_s)) {
								String error_message = String.format("Warning: Appointment:\nTime: %s\nDate: %s\nBrief: %s\nFeedback: %s\nClient_id: %s\nEmployee_id: %s\nLocation_id: %s",time_s,date_s,brief_s,feedback_s,client_id_s,employee_id_s,location_id_s);
								UI_Templates.error_popup(primary_stage, error_message);
							} else {
								Add_DB.Appointment(Main.url,Main.user,Main.password,Main.user_data.business,time_s,date_s,brief_s,feedback_s,client_id_s,employee_id_s,location_id_s);
								Main.appointments_array = Select_DB.Extract_Data_Record_Appointments(Main.url,Main.user,Main.password,Main.user_data.business);
							}
							List<Appointment> array = Main.appointments_array;
							int max_pos = Main.appointments_array.size()-1;
							String item_add_string = String.format("%s, %s\nClient: %s\nEmployee: %s\nLocation: %s",time_s,date_s,Algorithms.client_details_output(client_id_s),Algorithms.employee_details_output(employee_id_s),Algorithms.location_details(location_id_s));
						} catch(SQLException e1) {
							e1.printStackTrace();
						}
						lbl_date.setText(null);
						lbl_time.setText(null);
						tf_client_id.clear();
						tf_street_number.clear();
						tf_street_name.clear();
						tf_postcode.clear();
						tf_employee_id.clear();
						ta_brief.clear();
					}
				}
			});
			btn_update.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if(Main.appointments_array != null) {
						if(lbl_date.getText() == null || lbl_time.getText() == null || tf_client_id.getText() == null || tf_street_number.getText() == null || tf_street_name.getText() == null || tf_postcode.getText() == null || tf_employee_id.getText() == null || ta_brief.getText() == null) {
							UI_Templates.error_popup(primary_stage, "Cannot update information when fields are blank.");
						} else {
							String location_id_s = Algorithms.output_location_id(tf_street_number.getText(),tf_street_name.getText(),Integer.parseInt(tf_postcode.getText()),tf_client_id.getText());
							String record_id = get_record_id(lbl_time.getText(),lbl_date.getText(),ta_brief.getText(),ta_customer_feedback.getText(),tf_client_id.getText(),tf_employee_id.getText(),location_id_s);
							if(record_id != null) {
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","time", record_id, lbl_time.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","date", record_id, lbl_date.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","brief", record_id, ta_brief.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","feedback", record_id, ta_customer_feedback.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","client_id", record_id, tf_client_id.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","employee_id", record_id, tf_employee_id.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"appointments","location_id", record_id, location_id_s);
								System.out.println("Updated selected appointment information");
							} else {
								System.out.println("Logic Error: Cannot find appointment to be updated.");
							}
						}
					} else {
						UI_Templates.error_popup(primary_stage, "Cannot update information when there are no records.");
					}
				}
			});
			btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Cancel inputted client data");
					lbl_date.setText(null);
					lbl_time.setText(null);
					tf_client_id.clear();
					tf_street_number.clear();
					tf_street_name.clear();
					tf_postcode.clear();
					tf_employee_id.clear();
					ta_brief.clear();
					UI_Templates.disable_interaction_button(btn_add);
					UI_Templates.disable_interaction_button(btn_cancel);
					UI_Templates.enable_interaction_button(btn_new);
					UI_Templates.enable_interaction_button(btn_update);
				}
			});
			button_bar.getChildren().addAll(btn_new,btn_add,btn_update,btn_cancel);
			centre_view.add(button_bar,0,6,3,1);
			btn_new_client.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					UI_Templates.is_appt_details_shown = false;
					System.out.println("Navigate from Appointment_Details_Administrator_UI to Clients_Screen_UI (Administrator).");
					Clients_UI client_screen_layout = new Clients_UI();
					Scene client_screen_screen = new Scene(client_screen_layout.get_scene(primary_stage,true));
					client_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primary_stage.setScene(client_screen_screen);
				}
			});
			
			if(Main.clients_array == null || Main.employees_array == null) {
				UI_Templates.disable_interaction_button(btn_new_client);
				UI_Templates.disable_interaction_button(btn_date);
				UI_Templates.disable_interaction_button(btn_add);
				UI_Templates.disable_interaction_button(btn_new);
				UI_Templates.disable_interaction_button(btn_date);	
				UI_Templates.disable_interaction_button(btn_time);
				tf_client_id.setEditable(false);
				tf_street_number.setEditable(false);
				tf_street_name.setEditable(false);
				tf_postcode.setEditable(false);
				tf_employee_id.setEditable(false);
				ta_brief.setEditable(false);
				Popup popup = new Popup();
				GridPane p_grid = new GridPane();
				Label lbl_error = new Label("Before adding an appointment a client and an employee must be created.");
				p_grid.add(lbl_error,0,0);
				p_grid.setPadding(new Insets(Algorithms.dimension_calculator(50.0,true),Algorithms.dimension_calculator(50.0,false),Algorithms.dimension_calculator(50.0,true),Algorithms.dimension_calculator(50.0,false)));
				UI_Templates.popup_error_style(p_grid); 
				popup.getContent().add(p_grid);
				popup.show(popup);
			}
			
		} else {		
			centre_view.add(lbl_date,1,1);
			centre_view.add(lbl_time,1,0);
			
			Label lbl_client_name = new Label();
			UI_Templates.output_label_style(lbl_client_name);
			lbl_client_name.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			centre_view.add(lbl_client_name,1,2);
			
			Label lbl_address = new Label();
			UI_Templates.output_label_style(lbl_address);
			lbl_address.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			centre_view.add(lbl_address,1,3);

			Label lbl_employee = new Label();
			UI_Templates.output_label_style(lbl_employee);
			lbl_employee.setMinSize(Algorithms.dimension_calculator(200.0,false),Algorithms.dimension_calculator(20.0,true));
			centre_view.add(lbl_employee,1,4);
			
			if(date != null) {
				lbl_date.setText(String.valueOf(date));
			}
		}
		centre_panel.add(centre_view,1,1);
		centre_panel.setAlignment(Pos.CENTER);
		centre_panel.add(left_view,0,1);
		return centre_panel;
	}
	
	public BorderPane get_scene(Stage primary_stage, boolean administrator, String date) {
		BorderPane border_pane = new BorderPane();
		border_pane.setTop(header(primary_stage, administrator));
		border_pane.setCenter(centre_panel(primary_stage,administrator,date));
		return border_pane;
	}
}
