package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sign_In_UI {
	
	public GridPane get_scene(Stage primary_stage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Text scene_title = new Text("User Login");
			scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			scene_title.setFill(Color.web("cfcfcf"));
			grid.add(scene_title,0,2,2,1);
			
			Label lbl_email = new Label("User Email:");
			grid.add(lbl_email,0,3);
			lbl_email.getStyleClass().add("sign_in_label");
			lbl_email.setId("sign_in_label");
			
			TextField tf_email = new TextField();
			grid.add(tf_email,1,3);
			
			Label lbl_password = new Label("Password:");
			grid.add(lbl_password,0,4);
			lbl_password.getStyleClass().add("sign_in_label");
			lbl_password.setId("sign_in_label");
			
			PasswordField pf_password = new PasswordField();
			grid.add(pf_password,1,4);
			
			Button btn_sign_in = new Button("Sign in");
			btn_sign_in.getStyleClass().add("sign_in_button");
			btn_sign_in.setId("sign_in_button");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn_sign_in);
			grid.add(hbBtn,1,6);
			final Text sign_in_action_event = new Text();
			grid.add(sign_in_action_event,1,8);
			btn_sign_in.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String parse_email = tf_email.getText();
					String parse_password = pf_password.getText();
					if(parse_email.equals("admin") && parse_password.equals("admin")) {
						Home_Screen_Administrator_UI home_screen_administrator_layout = new Home_Screen_Administrator_UI();
						Scene home_screen_administrator_scene = new Scene(home_screen_administrator_layout.get_scene(primary_stage));
						home_screen_administrator_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primary_stage.setScene(home_screen_administrator_scene);
					} else if(parse_email.equals("user")&& parse_password.equals("user")) {
						Home_Screen_User_UI home_screen_user_layout = new Home_Screen_User_UI();
						Scene home_screen_user_scene = new Scene(home_screen_user_layout.get_scene(primary_stage));
						home_screen_user_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primary_stage.setScene(home_screen_user_scene);
					} else {
						sign_in_action_event.setFill(Color.FIREBRICK);
						sign_in_action_event.setText("Invalid login");
					}
				}
			});
			btn_sign_in.setDefaultButton(true);
			
			Image image_logo = new Image("C:\\Users\\uzzia\\git\\repository\\Job_Tracker_Desktop\\src\\resources\\logo_place_holder.jpg");
			ImageView imageV_logo = new ImageView();
			imageV_logo.setFitWidth(100);
			imageV_logo.setFitHeight(100);
			imageV_logo.setImage(image_logo);
			grid.add(imageV_logo,1,0);
			grid.setGridLinesVisible(false);
			return grid;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

