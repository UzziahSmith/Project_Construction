package application;

import java.util.ArrayList;
import java.util.Date;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Algorithms {
	public ArrayList<String> array_list_date_extractor(ArrayList<String> master_array_list, Date search_date) {
		ArrayList<String> extracted_array = new ArrayList<String>();
		return extracted_array;
	}
	
	public static double dimension_calculator(double dimension,boolean height) {
		Rectangle2D screen_bounds = Screen.getPrimary().getBounds();
		double screen_width = screen_bounds.getWidth();
		double screen_height = screen_bounds.getHeight();
		double proportion;
		double dimension_output;
		if(height) {
			proportion = dimension/1080;
			dimension_output = proportion*screen_height;
		} else {
			proportion = dimension/1920;
			dimension_output = proportion*screen_width;
		}
		return dimension_output;
	}
}
