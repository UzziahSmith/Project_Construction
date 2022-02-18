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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Employee_Details_Screen_Administrator_UI { 
	private BorderPane header(Stage primary_stage) {
		BorderPane header = UI_Templates.header(primary_stage,"Employee Details");
		
		HBox navigation_button_bar = new HBox();
		navigation_button_bar.setAlignment(Pos.CENTER_RIGHT);
		navigation_button_bar.setMaxHeight(Double.MAX_VALUE);
		navigation_button_bar.setPadding(new Insets(0,Algorithms.dimension_calculator(80.0,false),0,0));
		Button btn_navigate_home = new Button("Home");
		btn_navigate_home.getStyleClass().add("header_button");
		btn_navigate_home.setId("header_button");
		Button btn_navigate_clients = new Button("Clients");
		btn_navigate_clients.getStyleClass().add("header_button");
		btn_navigate_clients.setId("header_button");
		Button btn_navigate_appointments = new Button("Appointments");
		btn_navigate_appointments.getStyleClass().add("header_button");
		btn_navigate_appointments.setId("header_button");
		Button btn_navigate_trades = new Button("Trades");
		btn_navigate_trades.getStyleClass().add("header_button");
		btn_navigate_trades.setId("header_button");
		
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
	
	private GridPane centre_view(ArrayList<String> trade_title,ArrayList<String> employees) {
		
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
		lbl_first_name.getStyleClass().add("admin_home_listview_label");
		lbl_first_name.setId("admin_home_listview_label");
		TextField tf_first_name = new TextField("");
		tf_first_name.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_first_name,0,0);
		left_grid.add(tf_first_name,1,0,3,1);
		
		Label lbl_surname = new Label("Surname:");
		lbl_surname.setMaxWidth(Double.MAX_VALUE);
		lbl_surname.setAlignment(Pos.CENTER_RIGHT);
		lbl_surname.getStyleClass().add("admin_home_listview_label");
		lbl_surname.setId("admin_home_listview_label");
		TextField tf_surname = new TextField();
		tf_surname.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_surname,0,1);
		left_grid.add(tf_surname,1,1,3,1);
		
		Label lbl_phone_name = new Label("Phone Number:");
		lbl_phone_name.setMaxWidth(Double.MAX_VALUE);
		lbl_phone_name.setAlignment(Pos.CENTER_RIGHT);
		lbl_phone_name.getStyleClass().add("admin_home_listview_label");
		lbl_phone_name.setId("admin_home_listview_label");
		TextField tf_phone_name = new TextField();
		tf_phone_name.setMinSize(Algorithms.dimension_calculator(250.0,false), Algorithms.dimension_calculator(20.0,false));
		left_grid.add(lbl_phone_name,0,2);
		left_grid.add(tf_phone_name,1,2,3,1);

		Label lbl_employed = new Label("Employed:");
		lbl_employed.setMaxWidth(Double.MAX_VALUE);
		lbl_employed.setAlignment(Pos.CENTER_RIGHT);
		lbl_employed.getStyleClass().add("admin_home_listview_label");
		lbl_employed.setId("admin_home_listview_label");
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
		lbl_trade.getStyleClass().add("admin_home_listview_label");
		lbl_trade.setId("admin_home_listview_label");
		ComboBox<String> trade_combo_box = new ComboBox<String>();
		for(String title : trade_title) {
			trade_combo_box.getItems().add(title);
		}
		left_grid.add(lbl_trade,0,4);
		left_grid.add(trade_combo_box,1,4,4,1);
		
		HBox trade_details_button_bar = new HBox();
		trade_details_button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
		Button btn_new = new Button("NEW");
		Button btn_add = new Button("ADD");
		Button btn_remove = new Button("REMOVE");
		Button btn_update = new Button("UPDATE");
		Button btn_cancel = new Button("CANCEL");
		trade_details_button_bar.setPadding(new Insets(Algorithms.dimension_calculator(50.0,true),0,0,0));
		trade_details_button_bar.setAlignment(Pos.CENTER_RIGHT);
		trade_details_button_bar.setMaxWidth(Double.MAX_VALUE);
		trade_details_button_bar.getChildren().addAll(btn_new, btn_add, btn_remove, btn_update, btn_cancel);
		left_grid.add(trade_details_button_bar,0,5,5,1);
		
		Label lbl_employee_list_view_title = new Label("Employees");
		lbl_employee_list_view_title.setAlignment(Pos.CENTER);
		lbl_employee_list_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_employee_list_view_title.getStyleClass().add("admin_home_listview_label");
		lbl_employee_list_view_title.setId("admin_home_listview_label");
		lbl_employee_list_view_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_employees = new ListView<String>();
		ObservableList<String> lv_employee_items = FXCollections.observableArrayList();
		for(String employee : employees) {
			lv_employee_items.add(employee);
		}
		lv_employees.setItems(lv_employee_items);
		lv_employees.getStyleClass().add("admin_home_listview");
		lv_employees.setId("admin_home_listview");
		lv_employees.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));

		right_vb.getChildren().addAll(lbl_employee_list_view_title, lv_employees);
		
		grid.add(left_grid,0,0,3,1);
		grid.add(right_vb,3,0);
		
		return grid;
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
		border_pane.setCenter(centre_view(test_trades,test_employees));
		return border_pane;
	}
}
