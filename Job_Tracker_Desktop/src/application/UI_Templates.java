package application;

import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class UI_Templates {
	private static Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
	private static double screen_width = screen_bounds.getWidth();
	private static double screen_height = screen_bounds.getHeight();
	static Month current_month = LocalDate.now().getMonth();
	static int current_month_val = current_month.getValue();
	static int current_year_val = Calendar.getInstance().get(Calendar.YEAR);
	static Integer[] position_array = month_day_val_array(current_month_val,current_year_val);

	
	@SuppressWarnings("static-access")
	public static BorderPane header(Stage primary_stage, String title) {
		BorderPane header = new BorderPane();
		header.setPadding(new Insets(Algorithms.dimension_calculator(20.0,true),0,
				Algorithms.dimension_calculator(20.0,true),
				Algorithms.dimension_calculator(100.0,false)));
		header.setPrefHeight(screen_height*0.1);
		header.setPrefWidth(screen_width);
		header.getStyleClass().add("admin_home_screen_header");
		header.setId("admin_home_screen_header");
		
		Button btn_sign_out = new Button("Sign out");
//		Image image_sign_out = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\resources\\sign_out.png");
//		ImageView image_view_sign_out = new ImageView(image_sign_out);
//		btn_sign_out.setGraphic(image_view_sign_out);
		btn_sign_out.getStyleClass().add("header_button");
		btn_sign_out.setId("header_button");
		btn_sign_out.setMaxSize(Algorithms.dimension_calculator(75.0,false),Algorithms.dimension_calculator(75.0,true));
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
		
		Label lbl_title = new Label(title);
		lbl_title.setMaxWidth(Double.MAX_VALUE);
		lbl_title.setAlignment(Pos.CENTER);
		lbl_title.getStyleClass().add("admin_home_title_text");
		lbl_title.setId("admin_home_title_text");
		
		header.setAlignment(btn_sign_out, Pos.CENTER);
		header.setLeft(btn_sign_out);
		header.setCenter(lbl_title);
		return header;
	}
	
	public static void header_button_style(Button button) {
		button.getStyleClass().add("header_button");
		button.setId("header_button");
	}
	
	public static void title_label_style(Label label) {
		label.getStyleClass().add("admin_home_listview_label");
		label.setId("admin_home_listview_label");
	}
	
	public static void output_label_style(Label label) {
		label.getStyleClass().add("output_user_label");
		label.setId("output_user_label");
	}
	
	public static <T> void list_view_style(ListView<T> listview) {
		listview.getStyleClass().add("admin_home_listviw");
		listview.setId("admin_home_listviw");
	}
	
	public static void disabled_button_style(Button button) {
		button.getStyleClass().add("disabled_button");
		button.setId("disabled_button");
	}
	
	public static void disabled_label_style(Label label) {
		label.getStyleClass().add("disabled_button");
		label.setId("disabled_button");
	}
	public static void enabled_button_style(Button button) {
		button.getStyleClass().add("enabled_button");
		button.setId("enabled_button");
	}
	
	public static void enabled_label_style(Label label) {
		label.getStyleClass().add("enabled_button");
		label.setId("enabled_button");
	}
	
	
	private static Integer[] month_day_val_array(int month_val, int year_val) {
		Integer[] output_array = new Integer[42];
		int day_counter = 1;
		int pos = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(year_val,month_val,1);
		String first_day_of_month = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
		System.out.println(first_day_of_month);
		YearMonth yearMonthObject = YearMonth.of(year_val,month_val);
		int days_in_month = yearMonthObject.lengthOfMonth();
		while(true) {
			if(day_counter == 1) {
				switch(first_day_of_month) {
				case "Sunday" : 
					output_array[0] = 1;
					pos = 1;
				case "Monday" :
					output_array[0] = null;
					output_array[1] = 1;
					pos = 2;
					break;
				case "Tuesday" :
					output_array[0] = null;
					output_array[1] = null;
					output_array[2] = 1;
					pos = 3;
					break;
				case "Wednesday" :
					output_array[0] = null;
					output_array[1] = null;
					output_array[2] = null;
					output_array[3] = 1;
					pos = 4;
					break;
				case "Thursday" :
					output_array[0] = null;
					output_array[1] = null;
					output_array[2] = null;
					output_array[3] = null;
					output_array[4] = 1;
					pos = 5;
					break;
				case "Friday" :
					output_array[0] = null;
					output_array[1] = null;
					output_array[2] = null;
					output_array[3] = null;
					output_array[4] = null;
					output_array[5] = 1;
					pos = 6;
					break;
				case "Saturday" :
					output_array[0] = null;
					output_array[1] = null;
					output_array[2] = null;
					output_array[3] = null;
					output_array[4] = null;
					output_array[5] = null;
					output_array[6] = 1;
					pos = 7;
					break;
				default :
					System.out.println("Logical error ---> UI_Templates.month_day_val_array ----> second for loop switch inside first if.");
				}
				day_counter++;
			} else if(day_counter <= days_in_month) {
				output_array[pos] = day_counter;
				pos++;
				day_counter++;
			} else if(day_counter < 42){
				output_array[pos] = null;
				day_counter++;
			} else {
				break;
			}
		}
		return output_array;
	}
	
	public static GridPane Calender(boolean administrator, float calendar_width, float calendar_height) {
		GridPane calender = new GridPane();
		
		float width = calendar_width/7;
		float height = calendar_height/7;
		
		Label title_month_year = new Label(Month.of(current_month_val).name() + " " + String.valueOf(current_year_val));
		title_month_year.setMaxWidth(Double.MAX_VALUE);
		title_month_year.setAlignment(Pos.CENTER);
		title_month_year.setMinSize(width*5,height/2);
		calender.add(title_month_year,2,0,5,1);
		
		Label title_sunday = new Label("Sun");
		title_sunday.setMinSize(width,height/2);
		calender.add(title_sunday,0,1);
		Label title_monday = new Label("Mon");
		title_monday.setMinSize(width,height/2);
		calender.add(title_monday,1,1);
		Label title_tuesday = new Label("Tue");
		title_tuesday.setMinSize(width,height/2);
		calender.add(title_tuesday,2,1);
		Label title_wednesday = new Label("Wed");
		title_wednesday.setMinSize(width,height/2);
		calender.add(title_wednesday,3,1);
		Label title_thursday = new Label("Thu");
		title_thursday.setMinSize(width,height/2);
		calender.add(title_thursday,4,1);
		Label title_friday = new Label("Fri");
		title_friday.setMinSize(width,height/2);
		calender.add(title_friday,5,1);
		Label title_saturday = new Label("Sat");
		title_saturday.setMinSize(width,height/2);
		calender.add(title_saturday,6,1);
					
		Button btn_previous_month = new Button("  <-");
		calender.add(btn_previous_month,0,0);
		btn_previous_month.setMaxWidth(Double.MAX_VALUE);
		btn_previous_month.setAlignment(Pos.BASELINE_LEFT);
		Button btn_next_month = new Button("->  ");
		calender.add(btn_next_month,6,0);
		btn_next_month.setMaxWidth(Double.MAX_VALUE);
		btn_next_month.setAlignment(Pos.BASELINE_RIGHT);
		
		if(administrator) {
			Button btn_sun_1 = new Button();
			Button btn_sun_2 = new Button();
			Button btn_sun_3 = new Button();
			Button btn_sun_4 = new Button();
			Button btn_sun_5 = new Button();
			Button btn_sun_6 = new Button();
			btn_sun_1.setMaxWidth(Double.MAX_VALUE);
			btn_sun_1.setAlignment(Pos.BASELINE_CENTER);
			btn_sun_1.setMinSize(width,height);
			btn_sun_2.setMaxWidth(Double.MAX_VALUE);
			btn_sun_2.setAlignment(Pos.CENTER);
			btn_sun_2.setMinSize(width,height);
			btn_sun_3.setMaxWidth(Double.MAX_VALUE);
			btn_sun_3.setAlignment(Pos.CENTER);
			btn_sun_3.setMinSize(width,height);
			btn_sun_4.setMaxWidth(Double.MAX_VALUE);
			btn_sun_4.setAlignment(Pos.CENTER);
			btn_sun_4.setMinSize(width,height);
			btn_sun_5.setMaxWidth(Double.MAX_VALUE);
			btn_sun_5.setAlignment(Pos.CENTER);
			btn_sun_5.setMinSize(width,height);
			btn_sun_6.setMaxWidth(Double.MAX_VALUE);
			btn_sun_6.setAlignment(Pos.CENTER);
			btn_sun_6.setMinSize(width,height);
			calender.add(btn_sun_1,0,2);
			calender.add(btn_sun_2,0,3);
			calender.add(btn_sun_3,0,4);
			calender.add(btn_sun_4,0,5);
			calender.add(btn_sun_5,0,6);
			calender.add(btn_sun_6,0,7);

			Button btn_mon_1 = new Button();
			Button btn_mon_2 = new Button();
			Button btn_mon_3 = new Button();
			Button btn_mon_4 = new Button();
			Button btn_mon_5 = new Button();
			Button btn_mon_6 = new Button();
			btn_mon_1.setMaxWidth(Double.MAX_VALUE);
			btn_mon_1.setAlignment(Pos.CENTER);
			btn_mon_1.setMinSize(width,height);
			btn_mon_2.setMaxWidth(Double.MAX_VALUE);
			btn_mon_2.setAlignment(Pos.CENTER);
			btn_mon_2.setMinSize(width,height);
			btn_mon_3.setMaxWidth(Double.MAX_VALUE);
			btn_mon_3.setAlignment(Pos.CENTER);
			btn_mon_3.setMinSize(width,height);
			btn_mon_4.setMaxWidth(Double.MAX_VALUE);
			btn_mon_4.setAlignment(Pos.CENTER);
			btn_mon_4.setMinSize(width,height);
			btn_mon_5.setMaxWidth(Double.MAX_VALUE);
			btn_mon_5.setAlignment(Pos.CENTER);
			btn_mon_5.setMinSize(width,height);
			btn_mon_6.setMaxWidth(Double.MAX_VALUE);
			btn_mon_6.setAlignment(Pos.CENTER);
			btn_mon_6.setMinSize(width,height);
			calender.add(btn_mon_1,1,2);
			calender.add(btn_mon_2,1,3);
			calender.add(btn_mon_3,1,4);
			calender.add(btn_mon_4,1,5);
			calender.add(btn_mon_5,1,6);
			calender.add(btn_mon_6,1,7);
			
			Button btn_tue_1 = new Button();
			Button btn_tue_2 = new Button();
			Button btn_tue_3 = new Button();
			Button btn_tue_4 = new Button();
			Button btn_tue_5 = new Button();
			Button btn_tue_6 = new Button();
			btn_tue_1.setMaxWidth(Double.MAX_VALUE);
			btn_tue_1.setAlignment(Pos.CENTER);
			btn_tue_1.setMinSize(width,height);
			btn_tue_2.setMaxWidth(Double.MAX_VALUE);
			btn_tue_2.setAlignment(Pos.CENTER);
			btn_tue_2.setMinSize(width,height);
			btn_tue_3.setMaxWidth(Double.MAX_VALUE);
			btn_tue_3.setAlignment(Pos.CENTER);
			btn_tue_3.setMinSize(width,height);
			btn_tue_4.setMaxWidth(Double.MAX_VALUE);
			btn_tue_4.setAlignment(Pos.CENTER);
			btn_tue_4.setMinSize(width,height);
			btn_tue_5.setMaxWidth(Double.MAX_VALUE);
			btn_tue_5.setAlignment(Pos.CENTER);
			btn_tue_5.setMinSize(width,height);
			btn_tue_6.setMaxWidth(Double.MAX_VALUE);
			btn_tue_6.setAlignment(Pos.CENTER);
			btn_tue_6.setMinSize(width,height);
			calender.add(btn_tue_1,2,2);
			calender.add(btn_tue_2,2,3);
			calender.add(btn_tue_3,2,4);
			calender.add(btn_tue_4,2,5);
			calender.add(btn_tue_5,2,6);
			calender.add(btn_tue_6,2,7);
			
			Button btn_wed_1 = new Button();
			Button btn_wed_2 = new Button();
			Button btn_wed_3 = new Button();
			Button btn_wed_4 = new Button();
			Button btn_wed_5 = new Button();
			Button btn_wed_6 = new Button();
			btn_wed_1.setMaxWidth(Double.MAX_VALUE);
			btn_wed_1.setAlignment(Pos.CENTER);
			btn_wed_1.setMinSize(width,height);
			btn_wed_2.setMaxWidth(Double.MAX_VALUE);
			btn_wed_2.setAlignment(Pos.CENTER);
			btn_wed_2.setMinSize(width,height);
			btn_wed_3.setMaxWidth(Double.MAX_VALUE);
			btn_wed_3.setAlignment(Pos.CENTER);
			btn_wed_3.setMinSize(width,height);
			btn_wed_4.setMaxWidth(Double.MAX_VALUE);
			btn_wed_4.setAlignment(Pos.CENTER);
			btn_wed_4.setMinSize(width,height);
			btn_wed_5.setMaxWidth(Double.MAX_VALUE);
			btn_wed_5.setAlignment(Pos.CENTER);
			btn_wed_5.setMinSize(width,height);
			btn_wed_6.setMaxWidth(Double.MAX_VALUE);
			btn_wed_6.setAlignment(Pos.CENTER);
			btn_wed_6.setMinSize(width,height);
			calender.add(btn_wed_1,3,2);
			calender.add(btn_wed_2,3,3);
			calender.add(btn_wed_3,3,4);
			calender.add(btn_wed_4,3,5);
			calender.add(btn_wed_5,3,6);
			calender.add(btn_wed_6,3,7);
			
			Button btn_thu_1 = new Button();
			Button btn_thu_2 = new Button();
			Button btn_thu_3 = new Button();
			Button btn_thu_4 = new Button();
			Button btn_thu_5 = new Button();
			Button btn_thu_6 = new Button();
			btn_thu_1.setMaxWidth(Double.MAX_VALUE);
			btn_thu_1.setAlignment(Pos.CENTER);
			btn_thu_1.setMinSize(width,height);
			btn_thu_2.setMaxWidth(Double.MAX_VALUE);
			btn_thu_2.setAlignment(Pos.CENTER);
			btn_thu_2.setMinSize(width,height);
			btn_thu_3.setMaxWidth(Double.MAX_VALUE);
			btn_thu_3.setAlignment(Pos.CENTER);
			btn_thu_3.setMinSize(width,height);
			btn_thu_4.setMaxWidth(Double.MAX_VALUE);
			btn_thu_4.setAlignment(Pos.CENTER);
			btn_thu_4.setMinSize(width,height);
			btn_thu_5.setMaxWidth(Double.MAX_VALUE);
			btn_thu_5.setAlignment(Pos.CENTER);
			btn_thu_5.setMinSize(width,height);
			btn_thu_6.setMaxWidth(Double.MAX_VALUE);
			btn_thu_6.setAlignment(Pos.CENTER);
			btn_thu_6.setMinSize(width,height);
			calender.add(btn_thu_1,4,2);
			calender.add(btn_thu_2,4,3);
			calender.add(btn_thu_3,4,4);
			calender.add(btn_thu_4,4,5);
			calender.add(btn_thu_5,4,6);
			calender.add(btn_thu_6,4,7);
	
			Button btn_fri_1 = new Button();
			Button btn_fri_2 = new Button();
			Button btn_fri_3 = new Button();
			Button btn_fri_4 = new Button();
			Button btn_fri_5 = new Button();
			Button btn_fri_6 = new Button();
			btn_fri_1.setMaxWidth(Double.MAX_VALUE);
			btn_fri_1.setAlignment(Pos.CENTER);
			btn_fri_1.setMinSize(width,height);
			btn_fri_2.setMaxWidth(Double.MAX_VALUE);
			btn_fri_2.setAlignment(Pos.CENTER);
			btn_fri_2.setMinSize(width,height);
			btn_fri_3.setMaxWidth(Double.MAX_VALUE);
			btn_fri_3.setAlignment(Pos.CENTER);
			btn_fri_3.setMinSize(width,height);
			btn_fri_4.setMaxWidth(Double.MAX_VALUE);
			btn_fri_4.setAlignment(Pos.CENTER);
			btn_fri_4.setMinSize(width,height);
			btn_fri_5.setMaxWidth(Double.MAX_VALUE);
			btn_fri_5.setAlignment(Pos.CENTER);
			btn_fri_5.setMinSize(width,height);
			btn_fri_6.setMaxWidth(Double.MAX_VALUE);
			btn_fri_6.setAlignment(Pos.CENTER);
			btn_fri_6.setMinSize(width,height);
			calender.add(btn_fri_1,5,2);
			calender.add(btn_fri_2,5,3);
			calender.add(btn_fri_3,5,4);
			calender.add(btn_fri_4,5,5);
			calender.add(btn_fri_5,5,6);
			calender.add(btn_fri_6,5,7);
			
			Button btn_sat_1 = new Button();
			Button btn_sat_2 = new Button();
			Button btn_sat_3 = new Button();
			Button btn_sat_4 = new Button();
			Button btn_sat_5 = new Button();
			Button btn_sat_6 = new Button();
			btn_sat_1.setMaxWidth(Double.MAX_VALUE);
			btn_sat_1.setAlignment(Pos.CENTER);
			btn_sat_1.setMinSize(width,height);
			btn_sat_2.setMaxWidth(Double.MAX_VALUE);
			btn_sat_2.setAlignment(Pos.CENTER);
			btn_sat_2.setMinSize(width,height);
			btn_sat_3.setMaxWidth(Double.MAX_VALUE);
			btn_sat_3.setAlignment(Pos.CENTER);
			btn_sat_3.setMinSize(width,height);
			btn_sat_4.setMaxWidth(Double.MAX_VALUE);
			btn_sat_4.setAlignment(Pos.CENTER);
			btn_sat_4.setMinSize(width,height);
			btn_sat_5.setMaxWidth(Double.MAX_VALUE);
			btn_sat_5.setAlignment(Pos.CENTER);
			btn_sat_5.setMinSize(width,height);
			btn_sat_6.setMaxWidth(Double.MAX_VALUE);
			btn_sat_6.setAlignment(Pos.CENTER);
			btn_sat_6.setMinSize(width,height);
			calender.add(btn_sat_1,6,2);
			calender.add(btn_sat_2,6,3);
			calender.add(btn_sat_3,6,4);
			calender.add(btn_sat_4,6,5);
			calender.add(btn_sat_5,6,6);
			calender.add(btn_sat_6,6,7);
			
			Button[] btn_row_1_array = {btn_sun_1,btn_mon_1,btn_tue_1,btn_wed_1,btn_thu_1,btn_fri_1,btn_sat_1};
			Button[] btn_row_2_array = {btn_sun_2,btn_mon_2,btn_tue_2,btn_wed_2,btn_thu_2,btn_fri_2,btn_sat_2};
			Button[] btn_row_3_array = {btn_sun_3,btn_mon_3,btn_tue_3,btn_wed_3,btn_thu_3,btn_fri_3,btn_sat_3};
			Button[] btn_row_4_array = {btn_sun_4,btn_mon_4,btn_tue_4,btn_wed_4,btn_thu_4,btn_fri_4,btn_sat_4};
			Button[] btn_row_5_array = {btn_sun_5,btn_mon_5,btn_tue_5,btn_wed_5,btn_thu_5,btn_fri_5,btn_sat_5};
			Button[] btn_row_6_array = {btn_sun_6,btn_mon_6,btn_tue_6,btn_wed_6,btn_thu_6,btn_fri_6,btn_sat_6};
			Button[][] button_arraylist = {btn_row_1_array,btn_row_2_array,btn_row_3_array,btn_row_4_array,btn_row_5_array,btn_row_6_array};
			
			int pos = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 7; j++) {
					UI_Templates.enabled_button_style(button_arraylist[i][j]);
					if(position_array[pos] != null) {
						button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
					} else {
						button_arraylist[i][j].setDisable(true);
						UI_Templates.disabled_button_style(button_arraylist[i][j]);
					}
					pos++;
				}
			}
			
			btn_next_month.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					current_month_val++;
					if(current_month_val > 12) {
						current_month_val = 1;
						current_year_val++;
					}
					position_array = month_day_val_array(current_month_val,current_year_val);
					String next_month_string = String.valueOf(Month.of(current_month_val).name());
					System.out.println("change from month: " + title_month_year.getText() + " to month: " + next_month_string);
					title_month_year.setText(next_month_string);
					int pos = 0;
					for(int i = 0; i < 6; i++) {
						for(int j = 0; j < 7; j++) {
							button_arraylist[i][j].setText(null);
							UI_Templates.enabled_button_style(button_arraylist[i][j]);
							button_arraylist[i][j].setDisable(false);
							if(position_array[pos] != null) {
								button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
							} else {
								button_arraylist[i][j].setDisable(true);
								UI_Templates.disabled_button_style(button_arraylist[i][j]);
							}
							pos++;
						}
					}
				}
			});
			btn_previous_month.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					current_month_val--;
					if(current_month_val < 1) {
						current_month_val = 12;
						current_year_val--;
					}
					System.out.println(Arrays.toString(position_array));
					position_array = month_day_val_array(current_month_val,current_year_val);
					String previous_month_string = String.valueOf(Month.of(current_month_val).name());
					System.out.println("change from month: " + title_month_year.getText() + " to month: " + previous_month_string);
					title_month_year.setText(previous_month_string);
					int pos = 0;
					for(int i = 0; i < 6; i++) {
						for(int j = 0; j < 7; j++) {
							button_arraylist[i][j].setText(null);
							UI_Templates.enabled_button_style(button_arraylist[i][j]);
							button_arraylist[i][j].setDisable(false);
							if(position_array[pos] != null) {
								button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
							} else {
								button_arraylist[i][j].setDisable(true);
								UI_Templates.disabled_button_style(button_arraylist[i][j]);
							}
							pos++;
						}
					}
				}
			});
			
		} else {
			Label lbl_sun_1 = new Label();
			Label lbl_sun_2 = new Label();
			Label lbl_sun_3 = new Label();
			Label lbl_sun_4 = new Label();
			Label lbl_sun_5 = new Label();
			Label lbl_sun_6 = new Label();
			lbl_sun_1.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_1.setAlignment(Pos.CENTER);
			lbl_sun_1.setMinSize(width,height);
			lbl_sun_2.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_2.setAlignment(Pos.CENTER);
			lbl_sun_2.setMinSize(width,height);
			lbl_sun_3.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_3.setAlignment(Pos.CENTER);
			lbl_sun_3.setMinSize(width,height);
			lbl_sun_4.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_4.setAlignment(Pos.CENTER);
			lbl_sun_4.setMinSize(width,height);
			lbl_sun_5.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_5.setAlignment(Pos.CENTER);
			lbl_sun_5.setMinSize(width,height);
			lbl_sun_6.setMaxWidth(Double.MAX_VALUE);
			lbl_sun_6.setAlignment(Pos.CENTER);
			lbl_sun_6.setMinSize(width,height);
			calender.add(lbl_sun_1,0,2);
			calender.add(lbl_sun_2,0,3);
			calender.add(lbl_sun_3,0,4);
			calender.add(lbl_sun_4,0,5);
			calender.add(lbl_sun_5,0,6);
			calender.add(lbl_sun_6,0,7);

			Label lbl_mon_1 = new Label();
			Label lbl_mon_2 = new Label();
			Label lbl_mon_3 = new Label();
			Label lbl_mon_4 = new Label();
			Label lbl_mon_5 = new Label();
			Label lbl_mon_6 = new Label();
			lbl_mon_1.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_1.setAlignment(Pos.CENTER);
			lbl_mon_1.setMinSize(width,height);
			lbl_mon_2.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_2.setAlignment(Pos.CENTER);
			lbl_mon_2.setMinSize(width,height);
			lbl_mon_3.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_3.setAlignment(Pos.CENTER);
			lbl_mon_3.setMinSize(width,height);
			lbl_mon_4.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_4.setAlignment(Pos.CENTER);
			lbl_mon_4.setMinSize(width,height);
			lbl_mon_5.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_5.setAlignment(Pos.CENTER);
			lbl_mon_5.setMinSize(width,height);
			lbl_mon_6.setMaxWidth(Double.MAX_VALUE);
			lbl_mon_6.setAlignment(Pos.CENTER);
			lbl_mon_6.setMinSize(width,height);
			calender.add(lbl_mon_1,1,2);
			calender.add(lbl_mon_2,1,3);
			calender.add(lbl_mon_3,1,4);
			calender.add(lbl_mon_4,1,5);
			calender.add(lbl_mon_5,1,6);
			calender.add(lbl_mon_6,1,7);
			
			Label lbl_tue_1 = new Label();
			Label lbl_tue_2 = new Label();
			Label lbl_tue_3 = new Label();
			Label lbl_tue_4 = new Label();
			Label lbl_tue_5 = new Label();
			Label lbl_tue_6 = new Label();
			lbl_tue_1.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_1.setAlignment(Pos.CENTER);
			lbl_tue_1.setMinSize(width,height);
			lbl_tue_2.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_2.setAlignment(Pos.CENTER);
			lbl_tue_2.setMinSize(width,height);
			lbl_tue_3.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_3.setAlignment(Pos.CENTER);
			lbl_tue_3.setMinSize(width,height);
			lbl_tue_4.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_4.setAlignment(Pos.CENTER);
			lbl_tue_4.setMinSize(width,height);
			lbl_tue_5.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_5.setAlignment(Pos.CENTER);
			lbl_tue_5.setMinSize(width,height);
			lbl_tue_6.setMaxWidth(Double.MAX_VALUE);
			lbl_tue_6.setAlignment(Pos.CENTER);
			lbl_tue_6.setMinSize(width,height);
			calender.add(lbl_tue_1,2,2);
			calender.add(lbl_tue_2,2,3);
			calender.add(lbl_tue_3,2,4);
			calender.add(lbl_tue_4,2,5);
			calender.add(lbl_tue_5,2,6);
			calender.add(lbl_tue_6,2,7);
			
			Label lbl_wed_1 = new Label();
			Label lbl_wed_2 = new Label();
			Label lbl_wed_3 = new Label();
			Label lbl_wed_4 = new Label();
			Label lbl_wed_5 = new Label();
			Label lbl_wed_6 = new Label();
			lbl_wed_1.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_1.setAlignment(Pos.CENTER);
			lbl_wed_1.setMinSize(width,height);
			lbl_wed_2.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_2.setAlignment(Pos.CENTER);
			lbl_wed_2.setMinSize(width,height);
			lbl_wed_3.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_3.setAlignment(Pos.CENTER);
			lbl_wed_3.setMinSize(width,height);
			lbl_wed_4.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_4.setAlignment(Pos.CENTER);
			lbl_wed_4.setMinSize(width,height);
			lbl_wed_5.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_5.setAlignment(Pos.CENTER);
			lbl_wed_5.setMinSize(width,height);
			lbl_wed_6.setMaxWidth(Double.MAX_VALUE);
			lbl_wed_6.setAlignment(Pos.CENTER);
			lbl_wed_6.setMinSize(width,height);
			calender.add(lbl_wed_1,3,2);
			calender.add(lbl_wed_2,3,3);
			calender.add(lbl_wed_3,3,4);
			calender.add(lbl_wed_4,3,5);
			calender.add(lbl_wed_5,3,6);
			calender.add(lbl_wed_6,3,7);
			
			Label lbl_thu_1 = new Label();
			Label lbl_thu_2 = new Label();
			Label lbl_thu_3 = new Label();
			Label lbl_thu_4 = new Label();
			Label lbl_thu_5 = new Label();
			Label lbl_thu_6 = new Label();
			lbl_thu_1.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_1.setAlignment(Pos.CENTER);
			lbl_thu_1.setMinSize(width,height);
			lbl_thu_2.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_2.setAlignment(Pos.CENTER);
			lbl_thu_2.setMinSize(width,height);
			lbl_thu_3.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_3.setAlignment(Pos.CENTER);
			lbl_thu_3.setMinSize(width,height);
			lbl_thu_4.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_4.setAlignment(Pos.CENTER);
			lbl_thu_4.setMinSize(width,height);
			lbl_thu_5.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_5.setAlignment(Pos.CENTER);
			lbl_thu_5.setMinSize(width,height);
			lbl_thu_6.setMaxWidth(Double.MAX_VALUE);
			lbl_thu_6.setAlignment(Pos.CENTER);
			lbl_thu_6.setMinSize(width,height);
			calender.add(lbl_thu_1,4,2);
			calender.add(lbl_thu_2,4,3);
			calender.add(lbl_thu_3,4,4);
			calender.add(lbl_thu_4,4,5);
			calender.add(lbl_thu_5,4,6);
			calender.add(lbl_thu_6,4,7);
			
			
			Label lbl_fri_1 = new Label();
			Label lbl_fri_2 = new Label();
			Label lbl_fri_3 = new Label();
			Label lbl_fri_4 = new Label();
			Label lbl_fri_5 = new Label();
			Label lbl_fri_6 = new Label();
			lbl_fri_1.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_1.setAlignment(Pos.CENTER);
			lbl_fri_1.setMinSize(width,height);
			lbl_fri_2.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_2.setAlignment(Pos.CENTER);
			lbl_fri_2.setMinSize(width,height);
			lbl_fri_3.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_3.setAlignment(Pos.CENTER);
			lbl_fri_3.setMinSize(width,height);
			lbl_fri_4.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_4.setAlignment(Pos.CENTER);
			lbl_fri_4.setMinSize(width,height);
			lbl_fri_5.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_5.setAlignment(Pos.CENTER);
			lbl_fri_5.setMinSize(width,height);
			lbl_fri_6.setMaxWidth(Double.MAX_VALUE);
			lbl_fri_6.setAlignment(Pos.CENTER);
			lbl_fri_6.setMinSize(width,height);
			calender.add(lbl_fri_1,5,2);
			calender.add(lbl_fri_2,5,3);
			calender.add(lbl_fri_3,5,4);
			calender.add(lbl_fri_4,5,5);
			calender.add(lbl_fri_5,5,6);
			calender.add(lbl_fri_6,5,7);
			
			Label lbl_sat_1 = new Label();
			Label lbl_sat_2 = new Label();
			Label lbl_sat_3 = new Label();
			Label lbl_sat_4 = new Label();
			Label lbl_sat_5 = new Label();
			Label lbl_sat_6 = new Label();
			lbl_sat_1.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_1.setAlignment(Pos.CENTER);
			lbl_sat_1.setMinSize(width,height);
			lbl_sat_2.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_2.setAlignment(Pos.CENTER);
			lbl_sat_2.setMinSize(width,height);
			lbl_sat_3.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_3.setAlignment(Pos.CENTER);
			lbl_sat_3.setMinSize(width,height);
			lbl_sat_4.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_4.setAlignment(Pos.CENTER);
			lbl_sat_4.setMinSize(width,height);
			lbl_sat_5.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_5.setAlignment(Pos.CENTER);
			lbl_sat_5.setMinSize(width,height);
			lbl_sat_6.setMaxWidth(Double.MAX_VALUE);
			lbl_sat_6.setAlignment(Pos.CENTER);
			lbl_sat_6.setMinSize(width,height);
			calender.add(lbl_sat_1,6,2);
			calender.add(lbl_sat_2,6,3);
			calender.add(lbl_sat_3,6,4);
			calender.add(lbl_sat_4,6,5);
			calender.add(lbl_sat_5,6,6);
			calender.add(lbl_sat_6,6,7);
			
			Label[] lbl_row_1_array = {lbl_sun_1,lbl_mon_1,lbl_tue_1,lbl_wed_1,lbl_thu_1,lbl_fri_1,lbl_sat_1};
			Label[] lbl_row_2_array = {lbl_sun_2,lbl_mon_2,lbl_tue_2,lbl_wed_2,lbl_thu_2,lbl_fri_2,lbl_sat_2};
			Label[] lbl_row_3_array = {lbl_sun_3,lbl_mon_3,lbl_tue_3,lbl_wed_3,lbl_thu_3,lbl_fri_3,lbl_sat_3};
			Label[] lbl_row_4_array = {lbl_sun_4,lbl_mon_4,lbl_tue_4,lbl_wed_4,lbl_thu_4,lbl_fri_4,lbl_sat_4};
			Label[] lbl_row_5_array = {lbl_sun_5,lbl_mon_5,lbl_tue_5,lbl_wed_5,lbl_thu_5,lbl_fri_5,lbl_sat_5};
			Label[] lbl_row_6_array = {lbl_sun_6,lbl_mon_6,lbl_tue_6,lbl_wed_6,lbl_thu_6,lbl_fri_6,lbl_sat_6};
			Label[][] label_arraylist = {lbl_row_1_array,lbl_row_2_array,lbl_row_3_array,lbl_row_4_array,lbl_row_5_array,lbl_row_6_array};
			
			int pos = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 7; j++) {
					UI_Templates.enabled_label_style(label_arraylist[i][j]);
					if(position_array[pos] != null) {
						label_arraylist[i][j].setText(null);
						label_arraylist[i][j].setText(String.valueOf(position_array[pos]));
					} else {
						UI_Templates.disabled_label_style(label_arraylist[i][j]);
					}
					pos++;
				}
			}
			
			btn_next_month.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					current_month_val++;
					if(current_month_val > 12) {
						current_month_val = 1;
						current_year_val++;
					}
					position_array = month_day_val_array(current_month_val,current_year_val);
					String next_month_string = String.valueOf(Month.of(current_month_val).name());
					System.out.println("change from month: " + title_month_year.getText() + " to month: " + next_month_string);
					title_month_year.setText(next_month_string);
					int pos = 0;
					for(int i = 0; i < 6; i++) {
						for(int j = 0; j < 7; j++) {
							label_arraylist[i][j].setText(null);
							UI_Templates.enabled_label_style(label_arraylist[i][j]);
							if(position_array[pos] != null) {
								label_arraylist[i][j].setText(String.valueOf(position_array[pos]));
							} else {
								UI_Templates.disabled_label_style(label_arraylist[i][j]);
							}
							pos++;
						}
					}
				}
			});
			btn_previous_month.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					current_month_val--;
					if(current_month_val < 1) {
						current_month_val = 12;
						current_year_val--;
					}
					System.out.println(Arrays.toString(position_array));
					position_array = month_day_val_array(current_month_val,current_year_val);
					String previous_month_string = String.valueOf(Month.of(current_month_val).name());
					System.out.println("change from month: " + title_month_year.getText() + " to month: " + previous_month_string);
					title_month_year.setText(previous_month_string);
					int pos = 0;
					for(int i = 0; i < 6; i++) {
						for(int j = 0; j < 7; j++) {
							label_arraylist[i][j].setText(null);
							UI_Templates.enabled_label_style(label_arraylist[i][j]);
							if(position_array[pos] != null) {
								label_arraylist[i][j].setText(String.valueOf(position_array[pos]));
							} else {
								UI_Templates.disabled_label_style(label_arraylist[i][j]);
							}
							pos++;
						}
					}
				}
			});
		}
		return calender;
	}
}
