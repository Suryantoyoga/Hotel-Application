package controller;

import java.sql.Connection;
import java.sql.Statement;
import database.ConnectDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CompleteAlert;

public class ResetDatabase implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		
		final String DB_NAME = "testDB";
			
		//use try-with-resources Statement
		try (Connection con = ConnectDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			//drop table
			stmt.executeUpdate("DROP TABLE RentalRecord");
			stmt.executeUpdate("DROP TABLE HotelRoom");

			
			//Create New Table
			int result = stmt.executeUpdate("CREATE TABLE HotelRoom ("
										+ "Room_ID VARCHAR(8) NOT NULL,"
										+ "Bed_Number INT NOT NULL," 
										+ "Type VARCHAR(20) NOT NULL,"
										+ "Status VARCHAR(12) NOT NULL,"
										+ "Last_Maintenance VARCHAR(10),"
										+ "Summary VARCHAR(40) NOT NULL,"
										+ "Rates DOUBLE PRECISION NOT NULL,"
										+ "Picture_Names VARCHAR (20),"
										+ "PRIMARY KEY (Room_ID))");
			
			result = stmt.executeUpdate("CREATE TABLE RentalRecord (" 
											+ "Room_ID VARCHAR (8) NOT NULL," 
											+ "Record_ID VARCHAR (20) NOT NULL,"
											+ "Rent_Date VARCHAR (10) NOT NULL,"
											+ "Return_Date VARCHAR (10) NOT NULL,"
											+ "Actual_Return VARCHAR (10)," 
											+ "Rental_Fee DECIMAL (10, 3),"
											+ "Late_Fee DOUBLE PRECISION,"
											+ "PRIMARY KEY (Room_ID, Record_ID),"
											+ "FOREIGN KEY (Room_ID) REFERENCES HotelRoom (Room_ID))");
			
			if(result == 0) {
				CompleteAlert.resetcomplete();
			} else {
				CompleteAlert.resetfailed();
			}
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
