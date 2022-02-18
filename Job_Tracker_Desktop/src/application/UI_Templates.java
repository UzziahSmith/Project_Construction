package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class UI_Templates {
	static Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
	static double screen_width = screen_bounds.getWidth();
	static double screen_height = screen_bounds.getHeight();
	
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
}
