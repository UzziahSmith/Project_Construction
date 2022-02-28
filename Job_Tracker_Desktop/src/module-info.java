module Job_Tracker_Desktop {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
}
