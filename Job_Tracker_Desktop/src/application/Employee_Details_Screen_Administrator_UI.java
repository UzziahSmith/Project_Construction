package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.job_tracker.attribute_creation.Client;
import com.job_tracker.attribute_creation.Employee;
import com.job_tracker.attribute_creation.Trade;
import com.job_tracker.database_interaction.Add_DB;
import com.job_tracker.database_interaction.Select_DB;
import com.job_tracker.database_interaction.Update_DB;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Employee_Details_Screen_Administrator_UI { 
	
	private String[] formatted_employees = Algorithms.gather_employees();
	
	private String get_record_id(String first_name, String surname, String phone_number, String trade_id) {
		List<Employee> employees = Main.employees_array;
		for(Employee employee : employees) {
			if(first_name.equals(employee.first_name) && surname.equals(employee.surname) && phone_number.equals(employee.phone_number) && trade_id.equals(employee.trade_id)) {
				return employee.id;
			}
		}
		return null;
	}

	private void reset_listview(List<Employee> employee, ObservableList<String> observable_list, ListView<String> listview) throws SQLException {
		Main.employees_array = Select_DB.Extract_Data_Record_Employees(Main.url,Main.user,Main.password,Main.user_data.business);
		observable_list.clear();
		if(Main.employees_array != null) {
			int max_pos = Main.employees_array.size()-1;
			for(Employee emp : Main.employees_array) {
				String item_add_string = String.format("(%s)\n%s %s - %s\n%s", Main.employees_array.get(max_pos).id, Main.employees_array.get(max_pos).first_name, Main.employees_array.get(max_pos).surname, Algorithms.output_trade_title(Main.employees_array.get(max_pos).trade_id));
				observable_list.add(item_add_string);
				System.out.println(item_add_string);
			}
		}
		listview.setItems(observable_list);
	} 
	
	private BorderPane header(Stage primary_stage) {
		BorderPane header = UI_Templates.header(primary_stage,"Employee Details");
		
		HBox navigation_button_bar = new HBox();
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		UI_Templates.header_button_style(btn_navigate_home);
		Button btn_navigate_clients = new Button("Clients");
		UI_Templates.header_button_style(btn_navigate_clients);
		Button btn_navigate_appointments = new Button("Appointments");
		UI_Templates.header_button_style(btn_navigate_appointments);
		Button btn_navigate_trades = new Button("Trades");
		UI_Templates.header_button_style(btn_navigate_trades);

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
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Clients_Screen_UI (Administrator).");
				Clients_UI clients_screen_layout = new Clients_UI();
				Scene clients_screen_screen = new Scene(clients_screen_layout.get_scene(primary_stage,true));
				clients_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(clients_screen_screen);
			}
		});
		btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Employee_Details_Screen_Administrator_UI to Appointment_List_UI.");
				Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,true));
				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_list_screen);
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
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	private GridPane centre_view(Stage primary_stage) {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(Algorithms.dimension_calculator(200.0,false));
		GridPane left_grid = new GridPane();
		left_grid.setVgap(Algorithms.dimension_calculator(50.0,true));
		left_grid.setHgap(Algorithms.dimension_calculator(10.0,false));
		left_grid.setPadding(new Insets(Algorithms.dimension_calculator(40.0,true),0,0,0));
		VBox right_vb = new VBox();
		
		Label lbl_first_name = new Label("First Name:");
		lbl_first_name.setMaxWidth(Double.MAX_VALUE);
		lbl_first_name.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_first_name);
		TextField tf_first_name = new TextField("");
		Algorithms.add_quantity_limiter(tf_first_name, 35);
		tf_first_name.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_first_name,0,0);
		left_grid.add(tf_first_name,1,0,3,1);
		
		Label lbl_surname = new Label("Surname:");
		lbl_surname.setMaxWidth(Double.MAX_VALUE);
		lbl_surname.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_surname);
		TextField tf_surname = new TextField();
		Algorithms.add_quantity_limiter(tf_surname, 50);
		tf_surname.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_surname,0,1);
		left_grid.add(tf_surname,1,1,3,1);
		
		Label lbl_phone_number = new Label("Phone Number:");
		lbl_phone_number.setMaxWidth(Double.MAX_VALUE);
		lbl_phone_number.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_phone_number);
		TextField tf_phone_number = new TextField();
		Algorithms.add_input_limiter_integers(tf_phone_number);
		Algorithms.add_quantity_limiter(tf_phone_number,10);
		tf_phone_number.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_phone_number,0,2);
		left_grid.add(tf_phone_number,1,2,3,1);

		Label lbl_employed = new Label("Employed:");
		lbl_employed.setMaxWidth(Double.MAX_VALUE);
		lbl_employed.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_employed);
		RadioButton rb_employed_true = new RadioButton();
		Image image_employed_true = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\resources\\check_true.png");
		ImageView image_view_employed_true = new ImageView(image_employed_true);
		image_view_employed_true.setFitHeight(Algorithms.dimension_calculator(30.0,true));
		image_view_employed_true.setFitWidth(Algorithms.dimension_calculator(30.0,false));
		rb_employed_true.setGraphic(image_view_employed_true);
		RadioButton rb_employed_false = new RadioButton();
		Image image_employed_false = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\resources\\cross_false.png");
		ImageView image_view_employed_false = new ImageView(image_employed_false);
		image_view_employed_false.setFitHeight(Algorithms.dimension_calculator(30.0,true));
		image_view_employed_false.setFitWidth(Algorithms.dimension_calculator(30.0,false));
		rb_employed_false.setGraphic(image_view_employed_false);
		ToggleGroup toggle_group = new ToggleGroup();
		rb_employed_true.setToggleGroup(toggle_group);
		rb_employed_false.setToggleGroup(toggle_group);
		toggle_group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));
		left_grid.add(lbl_employed,0,3);
		left_grid.add(rb_employed_true,1,3);
		left_grid.add(rb_employed_false,2,3);
		
		Label lbl_trade = new Label("Trade:");
		lbl_trade.setMaxWidth(Double.MAX_VALUE);
		lbl_trade.setAlignment(Pos.CENTER_RIGHT);
		UI_Templates.title_label_style(lbl_trade);
		ComboBox<String> trade_combo_box = new ComboBox<String>();
		for(Trade trade : Main.trades_array) {
			trade_combo_box.getItems().add(trade.title);
		}
		left_grid.add(lbl_trade,0,4);
		left_grid.add(trade_combo_box,1,4,4,1);
		
		Label lbl_employee_list_view_title = new Label("Employees");
		lbl_employee_list_view_title.setAlignment(Pos.CENTER);
		lbl_employee_list_view_title.setMaxWidth(Double.MAX_VALUE);
		UI_Templates.title_label_style(lbl_employee_list_view_title);
		lbl_employee_list_view_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_employees = new ListView<String>();
		ObservableList<String> lv_employee_items = FXCollections.observableArrayList();
		for(int i = 0; i < Main.employees_array.size(); i++) {
			lv_employee_items.add(formatted_employees[i]);
		}
		lv_employees.setItems(lv_employee_items);
		UI_Templates.list_view_style(lv_employees);
		lv_employees.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));
		
		HBox hb_search = new HBox();
		hb_search.setSpacing(Algorithms.dimension_calculator(10.0, false));
		Label lbl_search = new Label("Find");
		lbl_search.setMaxHeight(Double.MAX_VALUE);
		lbl_search.setAlignment(Pos.CENTER);
		TextField tf_search = new TextField();
		Algorithms.item_filter_listener(tf_search, formatted_employees, lv_employee_items, lv_employees);
		
		hb_search.getChildren().addAll(lbl_search, tf_search);

		right_vb.getChildren().addAll(lbl_employee_list_view_title,hb_search,lv_employees);
		
		HBox trade_details_button_bar = new HBox();
		trade_details_button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
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
				tf_first_name.setText(null);
				tf_surname.setText(null);
				tf_phone_number.clear();
				rb_employed_true.setSelected(false);
				rb_employed_false.setSelected(false);
				trade_combo_box.getSelectionModel().clearSelection();
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
				UI_Templates.disable_interaction_button(btn_add);
				UI_Templates.disable_interaction_button(btn_cancel);
				UI_Templates.enable_interaction_button(btn_new);
				UI_Templates.enable_interaction_button(btn_update);

				//String(<36) first_name
				String first_name_s = tf_first_name.getText();
				boolean first_name_valid = first_name_s.length() < 36 ? true : false;
				if(!first_name_valid) {}
				//String(<51) surname
				String surname_s = tf_surname.getText();
				boolean surname_valid = surname_s.length() < 51 ? true : false;
				if(!surname_valid) {}
				//String(=10) phone_number
				String phone_number_s = tf_phone_number.getText();
				boolean phone_number_valid = phone_number_s.length() == 10 ? true : false;
				if(!phone_number_valid) {System.out.println("Logical Error: phone number allowing numbers above or below 10 digits");}
				//boolean employed
				boolean is_employed = rb_employed_true.isSelected() == true ? true : false;
				
				if(first_name_valid && surname_valid && phone_number_valid) {
					try {
						if(Algorithms.employee_exists(first_name_s, surname_s, phone_number_s, trade_combo_box.getValue())) {
							String error_message = String.format("Warning: Employee: %s %s (%s) - %s already exists", first_name_s, surname_s, phone_number_s, trade_combo_box.getValue());
							UI_Templates.error_popup(primary_stage, error_message);
						} else {
							Add_DB.Employee(Main.url,Main.user,Main.password,Main.user_data.business,first_name_s,surname_s,phone_number_s,is_employed,trade_combo_box.getValue());
							Main.employees_array = Select_DB.Extract_Data_Record_Employees(Main.url,Main.user,Main.password,Main.user_data.business);
							formatted_employees = Algorithms.gather_employees();
						}
						List<Employee> array = Main.employees_array;
						int max_pos = Main.employees_array.size()-1;
						String item_add_string = String.format("(%s)\n%s %s - %s\n%s", array.get(max_pos).id, array.get(max_pos).first_name, array.get(max_pos).surname, Algorithms.output_trade_title(array.get(max_pos).trade_id));
						lv_employee_items.add(item_add_string);
						lv_employees.setItems(lv_employee_items);
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
					tf_first_name.clear();
					tf_surname.clear();
					tf_phone_number.clear();
					rb_employed_true.setSelected(false);
					rb_employed_false.setSelected(false);
					trade_combo_box.getSelectionModel().clearSelection();
				}
			}
		});
		btn_update.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(Main.employees_array != null) {
					if(tf_first_name.getText() == null || tf_surname.getText() == null || tf_phone_number.getText() == null || trade_combo_box.getValue() == null) {
						UI_Templates.error_popup(primary_stage, "Cannot update information when fields are blank.");
					} else {
						try {
							String record_id = get_record_id(tf_first_name.getText(), tf_surname.getText(), tf_phone_number.getText(), trade_combo_box.getValue());
							if(record_id != null) {
								boolean is_employed = rb_employed_true.isSelected() == true ? true : false;
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"employees","first_name", record_id, tf_first_name.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"employees", "surname", record_id, tf_surname.getText());
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"employees", "phone_number", record_id, tf_phone_number.getText());
								Update_DB.Update_Boolean_Record(Main.url,Main.user,Main.password,Main.user_data.business,"employees", "employed", record_id, is_employed);
								Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"employees", "trade_id", record_id, Algorithms.output_trade_id(trade_combo_box.getValue()));
								Main.employees_array = Select_DB.Extract_Data_Record_Employees(Main.url,Main.user,Main.password,Main.user_data.business);
								formatted_employees = Algorithms.gather_employees();
								reset_listview(Main.employees_array, lv_employee_items, lv_employees);
								System.out.println("Updated selected client information");
							} else {
								System.out.println("Logic Error: Cannot find client to be updated.");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
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
				tf_first_name.setText(null);
				tf_surname.setText(null);
				tf_phone_number.clear();
				rb_employed_true.setSelected(false);
				rb_employed_false.setSelected(false);
				trade_combo_box.getSelectionModel().clearSelection();
				UI_Templates.disable_interaction_button(btn_add);
				UI_Templates.disable_interaction_button(btn_cancel);
				UI_Templates.enable_interaction_button(btn_new);
				UI_Templates.enable_interaction_button(btn_update);
			}
		});
		button_bar.getChildren().addAll(btn_new,btn_add,btn_update,btn_cancel);
		trade_details_button_bar.setPadding(new Insets(Algorithms.dimension_calculator(50.0,true),0,0,0));
		trade_details_button_bar.setAlignment(Pos.CENTER_RIGHT);
		trade_details_button_bar.setMaxWidth(Double.MAX_VALUE);
		trade_details_button_bar.getChildren().addAll(btn_new, btn_add, btn_update, btn_cancel);
		left_grid.add(trade_details_button_bar,0,5,5,1);
		
		if(Main.trades_array == null) {
			tf_first_name.setEditable(false);
			tf_surname.setEditable(false);
			tf_phone_number.setEditable(false);
			UI_Templates.disable_interaction_button(btn_add);
			UI_Templates.enable_interaction_button(btn_new);
			Popup popup = new Popup();
			GridPane p_grid = new GridPane();
			Label lbl_error = new Label("Before adding an employee a title must be defined in trades.");
			p_grid.add(lbl_error,0,0);
			p_grid.setPadding(new Insets(Algorithms.dimension_calculator(50.0,true),Algorithms.dimension_calculator(50.0,false),Algorithms.dimension_calculator(50.0,true),Algorithms.dimension_calculator(50.0,false)));
			UI_Templates.popup_error_style(p_grid); 
			popup.getContent().add(p_grid);
			popup.show(popup);
		}
		
		grid.add(left_grid,0,0,3,1);
		grid.add(right_vb,3,0);
		
		return grid;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
				
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view(primary_stage));
		return border_pane;
	}
}
