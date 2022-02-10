import com.job_tracker.database_interaction.*;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "constructpd173";
		System.out.println("Hello World!");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter business name --> ");
		String new_business = sc.nextLine();
		Add_DB.Business_Schema(new_business, url, username, password);
		System.out.println("END OF PROGRAM.");
	}	
}
