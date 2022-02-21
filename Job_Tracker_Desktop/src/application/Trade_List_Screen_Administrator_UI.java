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
import javafx.stage.Stage;

public class Trade_List_Screen_Administrator_UI {
	private BorderPane header(Stage primary_stage) {

		BorderPane header = UI_Templates.header(primary_stage,"Trade Titles");
		
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
		Button btn_navigate_employees = new Button("Employees");
		UI_Templates.header_button_style(btn_navigate_employees);
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
				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Clients_Screen_UI (Administrator).");
				Clients_Screen_UI client_list_layout = new Clients_Screen_UI();
				Scene client_list_screen = new Scene(client_list_layout.get_scene(primary_stage,true));
				client_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(client_list_screen);
			}
		});
		btn_navigate_appointments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Trade_List_Screen_Administrator_UI to Appointment_List_UI.");
				Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,true));
				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_list_screen);
			}
		});
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
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	@SuppressWarnings("static-access")
	private GridPane centre_view(ArrayList<String> trades) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(Algorithms.dimension_calculator(200.0,false));
		
		GridPane left_view = new GridPane();
		left_view.setVgap(Algorithms.dimension_calculator(50.0,true));
		left_view.setHgap(Algorithms.dimension_calculator(10.0,false));
		left_view.setPadding(new Insets(Algorithms.dimension_calculator(40.0,true),0,0,0));
		
		VBox right_vb = new VBox();
		
		Label lbl_trade_title = new Label("Title:");
		UI_Templates.title_label_style(lbl_trade_title);
		left_view.add(lbl_trade_title,0,0);
		
		TextField tf_trade_title = new TextField();
		tf_trade_title.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(20.0,true));
		left_view.add(tf_trade_title,1,0);
		
		HBox trade_interactive_button_bar = new HBox();
		trade_interactive_button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
		Button btn_new = new Button("NEW");
		UI_Templates.enable_interaction_button(btn_new);
		Button btn_add = new Button("ADD");
		UI_Templates.disable_interaction_button(btn_add);
		Button btn_remove = new Button("REMOVE");
		UI_Templates.enable_interaction_button(btn_remove);
		Button btn_update = new Button("UPDATE");
		UI_Templates.enable_interaction_button(btn_update);
		Button btn_cancel = new Button("CANCEL");
		UI_Templates.disable_interaction_button(btn_cancel);
		btn_new.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("New client initialisation");
				tf_trade_title.clear();
				UI_Templates.enable_interaction_button(btn_add);
				UI_Templates.enable_interaction_button(btn_cancel);
				UI_Templates.disable_interaction_button(btn_new);
				UI_Templates.disable_interaction_button(btn_update);
				UI_Templates.disable_interaction_button(btn_remove);
			}
		});
		btn_add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Add new client");
				tf_trade_title.clear();
				UI_Templates.disable_interaction_button(btn_add);
				UI_Templates.disable_interaction_button(btn_cancel);
				UI_Templates.enable_interaction_button(btn_new);
				UI_Templates.enable_interaction_button(btn_update);
				UI_Templates.enable_interaction_button(btn_remove);
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
				tf_trade_title.clear();
				UI_Templates.disable_interaction_button(btn_add);
				UI_Templates.disable_interaction_button(btn_cancel);
				UI_Templates.enable_interaction_button(btn_new);
				UI_Templates.enable_interaction_button(btn_update);
				UI_Templates.enable_interaction_button(btn_remove);
			}
		});
		trade_interactive_button_bar.getChildren().addAll(btn_new,btn_add,btn_update,btn_remove,btn_cancel);
		trade_interactive_button_bar.setAlignment(Pos.CENTER);
		left_view.add(trade_interactive_button_bar,0,1,2,1);

		Label lbl_trade_list_view_title = new Label("Trades");
		UI_Templates.title_label_style(lbl_trade_list_view_title);
		lbl_trade_list_view_title.setAlignment(Pos.CENTER);
		lbl_trade_list_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_trade_list_view_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_trades = new ListView<String>();
		UI_Templates.list_view_style(lv_trades);
		ObservableList<String> lv_trade_items = FXCollections.observableArrayList();
		for(String trade : trades) {
			lv_trade_items.add(trade);
		}
		lv_trades.setItems(lv_trade_items);
		lv_trades.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));
		
		right_vb.getChildren().addAll(lbl_trade_list_view_title, lv_trades);
		
		grid.add(left_view,0,0);
		grid.add(right_vb,1,0);
		
		return grid;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_trades = new ArrayList<String>();
		
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view(test_trades));
		return border_pane;
	}
}
