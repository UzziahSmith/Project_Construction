package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primary_stage) throws Exception {
		Stage window = primary_stage;
		Sign_In_UI sign_in_layout = new Sign_In_UI();
		Scene sign_in_screen = new Scene(sign_in_layout.get_scene(window));
		sign_in_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(sign_in_screen);
//		UI_Templates calender_layout = new UI_Templates();
//		Scene calender_screen = new Scene(UI_Templates.Calender(primary_stage,false,350,350));
//		calender_screen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		window.setScene(calender_screen);
		window.setTitle("Project_Construction");
		window.centerOnScreen();
		window.show();
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
