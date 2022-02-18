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

public class Clients_Screen_User_UI {
	private BorderPane header(Stage primary_stage) {

		BorderPane header = UI_Templates.header(primary_stage,"Clients");
		
		HBox navigation_button_bar = new HBox();
		Button btn_navigate_home = new Button("Home");
		Button btn_navigate_assigned_jobs = new Button("Assigned Jobs");
		btn_navigate_home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Clients_Screen_User_UI to Home_Screen_User_UI.");
				Home_Screen_User_UI home_screen_layout = new Home_Screen_User_UI();
				Scene home_screen_screen = new Scene(home_screen_layout.get_scene(primary_stage));
				home_screen_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(home_screen_screen);
			}
		});
		btn_navigate_assigned_jobs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Navigate from Clients_Screen_User_UI to Appointment_List_User_UI.");
				Appointment_List_UI appointment_list_layout = new Appointment_List_UI();
				Scene appointment_list_screen = new Scene(appointment_list_layout.get_scene(primary_stage,false));
				appointment_list_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_list_screen);
			}
		});

		navigation_button_bar.getChildren().addAll(btn_navigate_home,btn_navigate_assigned_jobs);
		
		header.setRight(navigation_button_bar);
		return header;
	}
	
	@SuppressWarnings("static-access")
	private GridPane centre_view(String first_name, String surname, String phone_number, boolean previous_client) {
		GridPane centre_view = new GridPane();
		
		Label lbl_first_name = new Label("First Name:");
		Label lbl_first_name_output = new Label(first_name);
		lbl_first_name_output.getStyleClass().add("output_user_label");
		lbl_first_name_output.setId("output_user_label");
		lbl_first_name_output.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(30.0,true));
		centre_view.add(lbl_first_name,0,0);
		centre_view.add(lbl_first_name_output,1,0);
		
		Label lbl_surname = new Label("Surname:");
		Label lbl_surname_output = new Label(surname);
		lbl_surname_output.getStyleClass().add("output_user_label");
		lbl_surname_output.setId("output_user_label");
		lbl_surname_output.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(30.0,true));
		centre_view.add(lbl_surname,0,1);
		centre_view.add(lbl_surname_output,1,1);
		
		Label lbl_phone_number = new Label("Phone Number:");
		Label lbl_phone_number_output = new Label(phone_number);
		lbl_phone_number_output.getStyleClass().add("output_user_label");
		lbl_phone_number_output.setId("output_user_label");
		lbl_phone_number_output.setMinSize(Algorithms.dimension_calculator(100.0,false),Algorithms.dimension_calculator(30.0,true));
		centre_view.add(lbl_phone_number,0,2);
		centre_view.add(lbl_phone_number_output,1,2);
		
		Label lbl_previous_client = new Label("Previous Client:");
		Label lbl_previous_client_output = new Label();
		lbl_previous_client_output.getStyleClass().add("output_user_label");;
		lbl_previous_client_output.setId("output_user_label");
		lbl_previous_client_output.setMinSize(Algorithms.dimension_calculator(50.0,false),Algorithms.dimension_calculator(30.0,true));
		if(first_name == null || surname == null || phone_number == null) {
			lbl_previous_client_output.setText("");
		} else if(previous_client) {
			lbl_previous_client_output.setText("Yes");
		} else {
			lbl_previous_client_output.setText("No");
		}
		centre_view.add(lbl_previous_client,0,3);
		centre_view.add(lbl_previous_client_output,1,3);
		
		return centre_view;
	}
	
	private VBox right_view(ArrayList<String> clients) {
		VBox right_view = new VBox(); //50,250,20,20
		right_view.setPadding(new Insets(Algorithms.dimension_calculator(50.0,true),
				Algorithms.dimension_calculator(250.0,false),
				Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(20.0,false)));
		
		Label lbl_clients_list_title = new Label("Clients");
		lbl_clients_list_title.setAlignment(Pos.CENTER);
		lbl_clients_list_title.setMaxWidth(Double.MAX_VALUE);
		lbl_clients_list_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		lbl_clients_list_title.getStyleClass().add("admin_home_listview_label");
		lbl_clients_list_title.setId("admin_home_listview_label");
		
		ListView<String> lv_clients = new ListView<String>();
		ObservableList<String> lv_clients_items = FXCollections.observableArrayList();
		for(String client : clients) {
			lv_clients_items.add(client);
		}
		lv_clients.setItems(lv_clients_items);
		lv_clients.setPrefSize(Algorithms.dimension_calculator(300.0,false),
				Algorithms.dimension_calculator(500.0,true));
		right_view.getChildren().addAll(lbl_clients_list_title, lv_clients);
		
		return right_view;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		ArrayList<String> test_clients_AL = new ArrayList<String>();
		
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view(null,null,null,false));
		border_pane.setRight(right_view(test_clients_AL));
		return border_pane;
	}
}
