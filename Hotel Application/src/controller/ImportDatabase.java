package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import database.ConnectDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.HotelArray;


public class ImportDatabase implements EventHandler<ActionEvent>  {
	
	@Override
	public void handle(ActionEvent arg0) {

		Stage stage = new Stage();
		//choose file to import (only txt files)
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Import File");
				
		//set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(extFilter);
		File file = chooser.showOpenDialog(stage);
		
		if (file != null) {
			try {
				Read(file);
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			}
		}			
	}
	
	//Method for read txt file and insert the object inside the file into database
	public void Read(File file_name) throws FileNotFoundException {
		
		HotelArray hotelarray = new HotelArray();
		
		double onebed = 59;
		double twobed = 99;
		double fourbed = 199;
		double sixbed = 999;
		
		String DB_NAME = "testDB";
		
		//Scan the txt file
		InputStream inputStream = new FileInputStream(file_name);
		Scanner scanner = new Scanner(inputStream);
		
		//Prepare to add to database
			
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			

			//use try-with-resources Statement
			try (Connection con = ConnectDB.getConnection(DB_NAME);
					Statement stmt = con.createStatement();) {

				if (line.charAt(6) != 'C' && line.charAt(0) == 'R') {
					
					String SQL2 = "INSERT INTO HotelRoom (Room_ID, Bed_Number, Type, Status, Last_Maintenance, Summary, Rates, Picture_Names) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement ps2 = con.prepareStatement(SQL2);
					String array [] = line.split(":");
					String roomid = array[0];
					String bednum = array[1];
					int bnum = Integer.parseInt(bednum);
					String type = array[2];
					double rates = 0;
					if (bnum == 1)
						rates = onebed;
					else if(bnum == 2)
						rates= twobed;
					else if (bnum == 4)
						rates = fourbed;
					else if(bnum == 6)
						rates= sixbed;					
					String status = array[3];
					String summary = array[4];
					String picture = array[5];
					
					ps2.setString(1,roomid);
					ps2.setInt(2, bnum);
					ps2.setString(3, type);
					ps2.setString(4, status);
					ps2.setString(5, null);
					ps2.setString(6, summary);
					ps2.setDouble(7, rates);
					ps2.setString(8, picture);					
					ps2.executeUpdate();
					
					hotelarray.RoomArray();																								
				}
				
				else if (line.charAt(6) != 'C' && line.charAt(0) == 'S') {

					String SQL3 = "INSERT INTO HotelRoom (Room_ID, Bed_Number, Type, Status, Last_Maintenance, Summary, Rates, Picture_Names) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement ps3 = con.prepareStatement(SQL3);
					String array [] = line.split(":");
					String roomid = array[0];
					String bednum = array[1];
					int bnum = Integer.parseInt(bednum);
					String type = array[2];
					double rates = 0;
					if (bnum == 1)
						rates = onebed;
					else if(bnum == 2)
						rates= twobed;
					else if (bnum == 4)
						rates = fourbed;
					else if(bnum == 6)
						rates= sixbed;					
					String status = array[3];
					String lastmaint = array[4];
					String summary = array[5];
					String picture = array[6];
					
					ps3.setString(1,roomid);
					ps3.setInt(2, bnum);
					ps3.setString(3, type);
					ps3.setString(4, status);
					ps3.setString(5, lastmaint);
					ps3.setString(6, summary);
					ps3.setDouble(7, rates);
					ps3.setString(8, picture);					
					ps3.executeUpdate();
					
					hotelarray.RoomArray();											
				}
				
				else if (line.charAt(6) == 'C') {

					String SQL1 = "INSERT INTO RentalRecord (Room_ID, Record_ID, Rent_Date, Return_Date, Actual_Return, Rental_Fee, Late_fee) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(SQL1);
					String array [] = line.split(":");
					String recordid = array[0];
					String rentdate = array[1];
					String returndate = array[2];
					String actualreturn = array[3];
					String rentalfee = array[4];
					String latefee = array[5];
					String array2 [] = recordid.split("_");
					String roomid = array2 [0] + "_" + array2 [1];
										
					ps1.setString(1, roomid);
					ps1.setString(2, recordid);
					ps1.setString(3, rentdate);
					ps1.setString(4, returndate);
					ps1.setString(5, actualreturn);
					ps1.setDouble(6, Double.parseDouble(rentalfee));
					ps1.setDouble(7, Double.parseDouble(latefee));
										
					ps1.executeUpdate();
					
					hotelarray.RecordArray();

				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				}		
		}
		scanner.close();
	}	
	
}

