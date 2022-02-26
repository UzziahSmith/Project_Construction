package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.job_tracker.attribute_creation.Trade;
import com.job_tracker.database_interaction.Add_DB;
import com.job_tracker.database_interaction.Delete_DB;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Trade_List_Screen_Administrator_UI {
	
	private String current_id;
	
	private void reset_listview(List<Trade> trades, ObservableList<String> observable_list, ListView<String> listview) throws SQLException {
		Main.trades_array = Select_DB.Extract_Data_Record_Trades(Main.url,Main.user,Main.password,Main.user_data.business);
		observable_list.clear();
		if(Main.trades_array != null) {
			for(Trade trade : trades) {
				String item_add_string = String.format("%s - %s",trade.id,trade.title);
				observable_list.add(item_add_string);
				System.out.println(item_add_string);
			}
		}
		listview.setItems(observable_list);
	}
	
	private int get_current_id_int(String current_id) {
		String current_id_s = ""; 
		for(int i = 1; i < current_id.length(); i++) {
			current_id_s += current_id.charAt(i);
		}
		int current_id_int = Integer.parseInt(current_id_s);
		return current_id_int;
	}
	
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
				Clients_UI client_list_layout = new Clients_UI();
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
	private GridPane centre_view(Stage primary_stage, List<Trade> trades) {
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
		Algorithms.add_quantity_limiter(tf_trade_title, 50);
		tf_trade_title.setMinSize(Algorithms.dimension_calculator(250.0,false),Algorithms.dimension_calculator(20.0,true));
		left_view.add(tf_trade_title,1,0);
		
		Label lbl_trade_list_view_title = new Label("Trades");
		UI_Templates.title_label_style(lbl_trade_list_view_title);
		lbl_trade_list_view_title.setAlignment(Pos.CENTER);
		lbl_trade_list_view_title.setMaxWidth(Double.MAX_VALUE);
		lbl_trade_list_view_title.setPadding(new Insets(0,0,Algorithms.dimension_calculator(20.0,true),0));
		
		ListView<String> lv_trades = new ListView<String>();
		UI_Templates.list_view_style(lv_trades);
		ObservableList<String> lv_trade_items = FXCollections.observableArrayList();
		if(trades != null) {
			for(Trade trade : trades) {
				String item_add_string = String.format("%s - %s",trade.id,trade.title);
				lv_trade_items.add(item_add_string);
			}
		}
		lv_trades.setItems(lv_trade_items);
		lv_trades.setPrefSize(Algorithms.dimension_calculator(300.0,false),Algorithms.dimension_calculator(500.0,true));
		lv_trades.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				String current_item_selected = lv_trades.getSelectionModel().getSelectedItem();
				String current_item_title_output = "";
				int spaces_counter = 0;
				for(int i = 0; i < current_item_selected.length(); i++) {
					if(current_item_selected.charAt(i) == ' ') {
						spaces_counter++;
					}
					if(spaces_counter >= 2) {
						if(i == current_item_selected.length()-1) {
							break;
						} else {
							current_item_title_output += current_item_selected.charAt(i+1);
						}
					}
				}
				tf_trade_title.setText(current_item_title_output);
				current_id = "";
				for(int i = 0; i < current_item_selected.length(); i++) {
					if(current_item_selected.charAt(i) != ' ') {
						current_id += current_item_selected.charAt(i);
					} else {
						break;
					}
				}
				System.out.println(current_id);
			}
		});
		
		right_vb.getChildren().addAll(lbl_trade_list_view_title, lv_trades);
		
		HBox trade_interactive_button_bar = new HBox();
		trade_interactive_button_bar.setSpacing(Algorithms.dimension_calculator(10.0,false));
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
				tf_trade_title.clear();
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

				//String(<51) title
				String title_s = tf_trade_title.getText();
				boolean title_s_valid = title_s.length() < 51 ? true : false;
				if(!title_s_valid) {
					System.out.println("Logical Error: title allowing title's over 50 characters.");
				} else {
					try {
						if(Algorithms.trade_title_exists(title_s)) {
							String error_message = String.format("Warning: Title: %s already exists", title_s);
							UI_Templates.error_popup(primary_stage, error_message);
							tf_trade_title.clear();
						} else {
							Add_DB.Trade(Main.url,Main.user,Main.password,Main.user_data.business,title_s);
							Main.trades_array = Select_DB.Extract_Data_Record_Trades(Main.url, Main.user, Main.password, Main.user_data.business);
						}
						int size = Main.trades_array.size();
						String item_add_string = String.format("%s - %s",Main.trades_array.get(size-1).id,Main.trades_array.get(size-1).title);
						lv_trade_items.add(item_add_string);
						lv_trades.setItems(lv_trade_items);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tf_trade_title.clear();
			}
		});
//		btn_remove.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				System.out.println("Remove selected client");
//				Popup warning = new Popup();
//				Button btn_popup_cancel = new Button("Cancel");
//				Button btn_popup_remove = new Button("Remove");
//				Label lbl_warning = new Label("WARNING: removing records is permanent and cannot be undone.");
//				GridPane popup_grid = new GridPane();
//				UI_Templates.popup_error_style(popup_grid);
//				popup_grid.add(lbl_warning,0,0,2,1);
//				popup_grid.add(btn_popup_cancel,0,1);
//				popup_grid.add(btn_popup_remove,1,1);
//				popup_grid.setVgap(20.0);
//				popup_grid.setHgap(50.0);
//				warning.getContent().add(popup_grid);
//				warning.show(primary_stage);
//				btn_popup_cancel.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent e) {
//						warning.hide();
//					}
// 				});
//				btn_popup_remove.setOnAction(new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent e) {
//						warning.hide();
//						if(Delete_DB.Delete_Record(Main.url,Main.user,Main.password,Main.user_data.business,"trades","trade_id",current_id)) {
//							System.out.println("Successfully removed record");
//							for(Trade trade : Main.trades_array) {
//								if(trade.id.equals("T" + current_id)) {
//									Main.trades_array.remove(trade);
//									break;
//								}
//							}
//							try {
//								reset_listview(trades, lv_trade_items, lv_trades);
//								tf_trade_title.clear();
//							} catch (SQLException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//						} else {
//							System.out.println("Failed to remove record");
//							UI_Templates.warning_popup(primary_stage, "Failed to remove record");
//						}
//					}
//				});
//			}
//		});
		btn_update.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(Main.trades_array != null) {
					if(tf_trade_title.getText() != null) {
						Update_DB.Update_String_Record(Main.url,Main.user,Main.password,Main.user_data.business,"trades","title",current_id,tf_trade_title.getText());
						try {
							reset_listview(trades, lv_trade_items, lv_trades);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Updated selected client information");
					} else {
						UI_Templates.error_popup(primary_stage, "Cannot update information to be blank.");
					}
				}
				tf_trade_title.clear();
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
			}
		});
		trade_interactive_button_bar.getChildren().addAll(btn_new,btn_add,btn_update,btn_cancel);
		trade_interactive_button_bar.setAlignment(Pos.CENTER);
		left_view.add(trade_interactive_button_bar,0,1,2,1);
		
		grid.add(left_view,0,0);
		grid.add(right_vb,1,0);
		
		return grid;
	}
	
	public BorderPane get_scene(Stage primary_stage) {
		BorderPane border_pane = new BorderPane();
		
		List<Trade> test_trades = Main.trades_array;
		
		border_pane.setTop(header(primary_stage));
		border_pane.setCenter(centre_view(primary_stage, test_trades));
		return border_pane;
	}
}
