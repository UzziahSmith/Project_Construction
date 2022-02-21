package application;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UI_Templates {
	private static Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
	private static double screen_width = screen_bounds.getWidth();
	private static double screen_height = screen_bounds.getHeight();
	private static Month current_month = LocalDate.now().getMonth();
	private static int current_month_val = current_month.getValue();
	private static int current_year_val = Calendar.getInstance().get(Calendar.YEAR);
	private static Integer[] position_array = month_day_val_array(current_month_val,current_year_val);
	static boolean is_appt_details_shown = false;
	
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
	
	static void header_button_style(Button button) {
		button.getStyleClass().add("header_button");
		button.setId("header_button");
	}
	
	static void UI_button(Button button) {
		button.getStyleClass().add("admin_home_screen_button");
		button.setId("admin_home_screen_button");
	}
	
	static void title_label_style(Label label) {
		label.getStyleClass().add("admin_home_listview_label");
		label.setId("admin_home_listview_label");
	}
	
	static void output_label_style(Label label) {
		label.getStyleClass().add("output_user_label");
		label.setId("output_user_label");
	}
	
	static <T> void list_view_style(ListView<T> listview) {
		listview.getStyleClass().add("admin_home_listviw");
		listview.setId("admin_home_listviw");
	}
	
	static void enabled_label_style(Label label) {
		label.getStyleClass().add("enabled_button");
		label.setId("enabled_button");
	}
	static void disabled_label_style(Label label) {
		label.getStyleClass().add("disabled_button");
		label.setId("disabled_button");
	}
	
	static void calendar_disabled_button_style(Button button) {
		button.getStyleClass().add("disabled_button");
		button.setId("disabled_button");
	}
	static void calendar_enabled_button_style(Button button) {
		button.getStyleClass().add("enabled_button");
		button.setId("enabled_button");
	}
	
	static void calendar_disable_button(Button button) {
		button.setDisable(true);
		calendar_disabled_button_style(button);
	}
	
	static void calendar_enable_button(Button button) {
		button.setDisable(false);
		calendar_enabled_button_style(button);
	}
	
	static void disable_interaction_button(Button button) {
		button.setDisable(true);
		button.getStyleClass().add("disabled_interaction_button");
		button.setId("disabled_interaction_button");
	}
	
	static void enable_interaction_button(Button button) {
		button.setDisable(false);
		button.getStyleClass().add("enabled_interaction_button");
		button.setId("enabled_interaction_button");
	}
	
	static void add_input_limiter_integers(final TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String>  observable, String old_value, String new_value) {
				if(!new_value.matches("\\d*")) {
					tf.setText(new_value.replaceAll("[^\\d]", ""));
				}
			}
		});	
	}
	
	static void add_integer_quantity_limiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> ov, final String old_value, final String new_value) {
				if(tf.getText().length() > maxLength) {
					String string = tf.getText().substring(0, maxLength);
					tf.setText(string);
				}
			}
		});
	}
	
	
	private static String date_corrector(int day_val, int month_val, int year_val) {
		Calendar cal = Calendar.getInstance();
		switch(month_val) {
		case 1 :
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			break;
		case 2 :
			cal.set(Calendar.MONTH, Calendar.FEBRUARY);
			break;
		case 3 :
			cal.set(Calendar.MONTH, Calendar.MARCH);
			break;
		case 4 :
			cal.set(Calendar.MONTH, Calendar.APRIL);
			break;
		case 5 :
			cal.set(Calendar.MONTH, Calendar.MAY);
			break;
		case 6 :
			cal.set(Calendar.MONTH, Calendar.JUNE);
			break;
		case 7 :
			cal.set(Calendar.MONTH, Calendar.JULY);
			break;
		case 8 :
			cal.set(Calendar.MONTH, Calendar.AUGUST);
			break;
		case 9 :
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			break;
		case 10 :
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
			break;
		case 11 :
			cal.set(Calendar.MONTH, Calendar.NOVEMBER);
			break;
		case 12 :
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
			break;
		default : 
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			System.out.println("Logical error ---> UI_Templates.date_corrector ----> switch.");
			break;
		}
		cal.set(Calendar.YEAR, year_val);
		cal.set(Calendar.DAY_OF_MONTH, day_val);
		Date date = cal.getTime();
		System.out.println(String.valueOf(date));
		SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");    
		String date_string = date_format.format(date);
		System.out.println(date_string);
		return date_string;
	}
	
	private static Integer[] month_day_val_array(int month_val, int year_val) {
		Integer[] output_array = new Integer[42];
		int day_counter = 1;
		int pos = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year_val);
		cal.set(Calendar.MONTH, month_val);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		DateFormat sdf = new SimpleDateFormat("EEEEEEEE");
		String first_day_of_month = sdf.format(date);
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
					System.out.println("Logical error ---> UI_Templates.month_day_val_array ----> switch inside first if.");
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
	
	private static Scene calendar_button_action(Stage primary_stage, boolean administrator, int Pos) {
		UI_Templates.is_appt_details_shown = true;
		String appt_date = date_corrector(position_array[Pos],current_month_val,current_year_val);
		System.out.println("Navigate to Appointment_Details_UI.");
		Appointment_Details_UI appointment_details_layout = new Appointment_Details_UI();
		Scene appointment_details_screen = new Scene(appointment_details_layout.get_scene(primary_stage,administrator,appt_date));
		return appointment_details_screen;
	}
	
	private static void current_day_styler(Integer[] month_day_val_array, Button[][] cal_button_array) {
		Calendar cal = Calendar.getInstance();
		boolean current_day_button_found = false;
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
		int current_month = cal.get(Calendar.MONTH)+1;
		if(current_month == current_month_val) {
			for(Button[] button_array : cal_button_array) {
				for(Button button : button_array) {
					if(button.getText() != null) {
						if(button.getText().equals(String.valueOf(day_of_month))) {
							button.getStyleClass().add("current_day_style");		
							button.setId("current_day_style");	
							current_day_button_found = true;
							System.out.println("Button found");
							break;
						}
					}
				}
			}
			if(current_day_button_found) {
				System.out.println("Logic error inside UI_Templates.current_day_styler for loop");
			}
		}
	}
	
	public static GridPane Calender(Stage primary_stage, boolean administrator, double calender_width, double calender_height) {
		GridPane calender = new GridPane();
		
		double width = calender_width/7;
		double height = calender_height/7;
		
		Calendar cal = Calendar.getInstance();
		
		calender.getStyleClass().add("enabled_button");
		calender.setId("enabled_button");
		
		Label title_month_year = new Label(Month.of(current_month_val).name() + "	" + String.valueOf(current_year_val));
		title_month_year.setMaxWidth(Double.MAX_VALUE);
		title_month_year.setAlignment(Pos.CENTER);
		title_month_year.setMinHeight(height/2);
		calender.add(title_month_year,1,0,5,1);
		
		Label title_sun = new Label("Sun");
		title_sun.setMaxWidth(Double.MAX_VALUE);
		title_sun.setAlignment(Pos.BASELINE_CENTER);
		title_sun.setMinSize(width,height/2);
		enabled_label_style(title_sun);
		calender.add(title_sun,0,1);
		Label title_mon = new Label("Mon");
		title_mon.setMaxWidth(Double.MAX_VALUE);
		title_mon.setAlignment(Pos.BASELINE_CENTER);
		title_mon.setMinSize(width,height/2);
		enabled_label_style(title_mon);
		calender.add(title_mon,1,1);
		Label title_tue = new Label("Tue");
		title_tue.setMaxWidth(Double.MAX_VALUE);
		title_tue.setAlignment(Pos.BASELINE_CENTER);
		title_tue.setMinSize(width,height/2);
		enabled_label_style(title_tue);
		calender.add(title_tue,2,1);
		Label title_wed = new Label("Wed");
		title_wed.setMaxWidth(Double.MAX_VALUE);
		title_wed.setAlignment(Pos.BASELINE_CENTER);
		title_wed.setMinSize(width,height/2);
		enabled_label_style(title_wed);
		calender.add(title_wed,3,1);
		Label title_thu = new Label("Thu");
		title_thu.setMaxWidth(Double.MAX_VALUE);
		title_thu.setAlignment(Pos.BASELINE_CENTER);
		title_thu.setMinSize(width,height/2);
		enabled_label_style(title_thu);
		calender.add(title_thu,4,1);
		Label title_fri = new Label("Fri");
		title_fri.setMaxWidth(Double.MAX_VALUE);
		title_fri.setAlignment(Pos.BASELINE_CENTER);
		title_fri.setMinSize(width,height/2);
		enabled_label_style(title_fri);
		calender.add(title_fri,5,1);
		Label title_sat = new Label("Sat");
		title_sat.setMaxWidth(Double.MAX_VALUE);
		title_sat.setAlignment(Pos.BASELINE_CENTER);
		title_sat.setMinSize(width,height/2);
		enabled_label_style(title_sat);
		calender.add(title_sat,6,1);
					
		Button btn_previous_month = new Button("  <-");
		calender.add(btn_previous_month,0,0);
		btn_previous_month.setMaxWidth(Double.MAX_VALUE);
		btn_previous_month.setAlignment(Pos.BASELINE_LEFT);
		Button btn_next_month = new Button("->  ");
		calender.add(btn_next_month,6,0);
		btn_next_month.setMaxWidth(Double.MAX_VALUE);
		btn_next_month.setAlignment(Pos.BASELINE_RIGHT);

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
				UI_Templates.calendar_enabled_button_style(button_arraylist[i][j]);
				if(position_array[pos] != null) {
					button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
				} else {
					button_arraylist[i][j].setDisable(true);
					UI_Templates.calendar_disabled_button_style(button_arraylist[i][j]);
				}
				pos++;
			}
		}
		
		current_day_styler(position_array, button_arraylist);
		
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
				String year_string = String.valueOf(current_year_val);
				System.out.println("change from month: " + title_month_year.getText() + " to month: " + next_month_string);
				title_month_year.setText(next_month_string + " " + year_string);
				int pos = 0;
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 7; j++) {
						button_arraylist[i][j].setText(null);
						UI_Templates.calendar_enable_button(button_arraylist[i][j]);
						if(position_array[pos] != null) {
							button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
						} else {
							UI_Templates.calendar_disable_button(button_arraylist[i][j]);
						}
						pos++;
					}
				}
				current_day_styler(position_array, button_arraylist);
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
				String year_string = String.valueOf(current_year_val);
				System.out.println("change from month: " + title_month_year.getText() + " to month: " + previous_month_string);
				title_month_year.setText(previous_month_string + " " + year_string);
				int pos = 0;
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 7; j++) {
						button_arraylist[i][j].setText(null);
						UI_Templates.calendar_enable_button(button_arraylist[i][j]);
						if(position_array[pos] != null) {
							button_arraylist[i][j].setText(String.valueOf(position_array[pos]));
						} else {
							button_arraylist[i][j].setDisable(true);
							UI_Templates.calendar_disable_button(button_arraylist[i][j]);
						}
						pos++;
					}
				}
				current_day_styler(position_array, button_arraylist);
			}
		});
		
		btn_sun_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,0);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,1);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,2);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {;
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,3);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,4);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,5);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,6);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});			
		btn_sun_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,7);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,8);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,9);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,10);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,11);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,12);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,13);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sun_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,14);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,15);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,16);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,17);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,18);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,19);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,20);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sun_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,21);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,22);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,23);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,24);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,25);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,26);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,27);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sun_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,28);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,29);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,30);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,31);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,32);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,33);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,34);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sun_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,35);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_mon_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,36);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_tue_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,37);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_wed_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,38);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_thu_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,39);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_fri_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,40);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});
		btn_sat_6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Scene appointment_details_screen = calendar_button_action(primary_stage,administrator,41);
				appointment_details_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primary_stage.setScene(appointment_details_screen);
			}
		});			
		return calender;
	}

	public static Node time_picker(Stage primary_stage, Label lbl_time) {
		HBox picker = new HBox();
		
		TextField tf_hour = new TextField();
		tf_hour.setMaxWidth(Algorithms.dimension_calculator(30.0,false));
		tf_hour.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String>  observable, String old_value, String new_value) {
				if(!new_value.matches("\\d*")) {
					tf_hour.setText(new_value.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		TextField tf_minutes = new TextField();
		tf_minutes.setMaxWidth(Algorithms.dimension_calculator(30.0,false));
		tf_minutes.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String old_value, String new_value) {
				if(!new_value.matches("\\d*")) {
					tf_minutes.setText(new_value.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		Label lbl_colon = new Label(":");
		lbl_colon.getStyleClass().add("bold_label");
		lbl_colon.setId("bold_label");
		lbl_colon.setMaxHeight(Double.MAX_VALUE);
		lbl_colon.setAlignment(Pos.CENTER);
		
		ComboBox<String> combo_box = new ComboBox<String>();
		combo_box.getItems().add("AM");
		combo_box.getItems().add("PM");
		combo_box.getSelectionModel().selectFirst();
		
		Button btn_enter_time = new Button("Enter");
		btn_enter_time.setMaxHeight(Algorithms.dimension_calculator(5.0,true));
		btn_enter_time.setMinWidth(Algorithms.dimension_calculator(40.0,false));
		btn_enter_time.setDefaultButton(true);
		btn_enter_time.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if((Integer.parseInt(tf_hour.getText()) < 13 && Integer.parseInt(tf_hour.getText()) > 0) && 
						(Integer.parseInt(tf_minutes.getText()) < 60 && Integer.parseInt(tf_minutes.getText()) >= 1)) {
					// completely correct
					String hour_string = tf_hour.getText();
					String minutes_string = tf_minutes.getText();
					String am_pm_string = combo_box.getValue();
					String output = hour_string + ":" + minutes_string + " " + am_pm_string;
					lbl_time.setText(output);
				} else if((Integer.parseInt(tf_hour.getText()) > 12 || Integer.parseInt(tf_hour.getText()) < 1) && 
						(Integer.parseInt(tf_minutes.getText()) < 59 && Integer.parseInt(tf_minutes.getText()) > 0)) {
					// hours wrong
					tf_hour.clear();
					tf_minutes.clear();
					Label lbl_error = new Label("ERROR: Hours cannot exceed 12.\nHours cannot be below 1.");
					GridPane grid = new GridPane();
					grid.add(lbl_error,0,0);
					grid.getStyleClass().add("popup_error_style");
					grid.setId("popup_error_style");
					Popup error_message = new Popup();
					error_message.getContent().add(grid);
					error_message.setAutoHide(true);
					PauseTransition delay = new PauseTransition(Duration.seconds(2));
					delay.setOnFinished(ex -> error_message.hide());
					if(!error_message.isShowing()) {
						error_message.show(primary_stage);
						delay.play();
					}
				} else if((Integer.parseInt(tf_hour.getText()) < 12 && Integer.parseInt(tf_hour.getText()) > 1) && 
						(Integer.parseInt(tf_minutes.getText()) > 59 || Integer.parseInt(tf_minutes.getText()) < 0)) {
					// minutes wrong
					tf_hour.clear();
					tf_minutes.clear();
					Label lbl_error = new Label("ERROR: Minutes cannot exceed 59.\nMinutes cannot be below 0.");
					GridPane grid = new GridPane();
					grid.add(lbl_error,0,0);
					grid.getStyleClass().add("popup_error_style");
					grid.setId("popup_error_style");
					Popup error_message = new Popup();
					error_message.getContent().add(grid);
					error_message.setAutoHide(true);
					PauseTransition delay = new PauseTransition(Duration.seconds(2));
					delay.setOnFinished(ex -> error_message.hide());
					if(!error_message.isShowing()) {
						error_message.show(primary_stage);
						delay.play();
					}
				} else if((Integer.parseInt(tf_hour.getText()) > 12 || Integer.parseInt(tf_hour.getText()) < 0) && 
						(Integer.parseInt(tf_minutes.getText()) > 59 || Integer.parseInt(tf_minutes.getText()) < 0)){
					// hours & minutes wrong 
					tf_hour.clear();
					tf_minutes.clear();
					Label lbl_error = new Label("ERROR: Hours cannot exceed 12 or be below 1.\nMinutes cannot exceed 59 or be below 0.");
					GridPane grid = new GridPane();
					grid.add(lbl_error,0,0);
					grid.getStyleClass().add("popup_error_style");
					grid.setId("popup_error_style");
					Popup error_message = new Popup();
					error_message.getContent().add(grid);
					error_message.setAutoHide(true);
					PauseTransition delay = new PauseTransition(Duration.seconds(2));
					delay.setOnFinished(ex -> error_message.hide());
					if(!error_message.isShowing()) {
						error_message.show(primary_stage);
						delay.play();
					}
				}
			}
		});
		picker.getChildren().addAll(tf_hour,lbl_colon,tf_minutes,combo_box,btn_enter_time);
		return picker;
	}
}
