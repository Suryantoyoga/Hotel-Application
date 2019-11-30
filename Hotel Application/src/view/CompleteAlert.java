package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.DateTime;

//Alert collection to be called when user already completed certain command
public class CompleteAlert {
	
	public static void successrent (String roomid, String custID, DateTime rentdate) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Room Rent Sucessfull");
		alert.setContentText("Room Rent " + roomid + " Succesfull \nCustomer ID " + custID + " rentdate " + rentdate);
		alert.showAndWait();
	
	}
	
	public static void failedrent (String roomid, String custID) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Room Rent Failed");
		alert.setContentText("Room Rent for " + roomid + "and Customer ID " + custID + " failed");
		alert.showAndWait();
		
	}
	
	public static void successreturn (String custID, double rentalfee, double latefee) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Room Return Sucessfull");
		alert.setContentText("Room Return " + " Succesfull \nCustomer ID " + custID + "\nrental fee: " + rentalfee + "\nlate fee: " + latefee);
		alert.showAndWait();	
		
	}
	
	public static void failedreturn (String custID) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Room Return Failed");
		alert.setContentText("Room Return for Customer ID " + custID + " failed");
		alert.showAndWait();
		
	}
	
	public static void successperformmaint () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Perform Maintenance Success");
		alert.showAndWait();	
	}
	
	public static void failedperformmaint () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Perform Maintenance failed");
		alert.showAndWait();	
	}
	
	public static void successcompletemaint () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Complete Maintenance Success");
		alert.showAndWait();	
	}
	
	public static void failedcompletemaint () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Complete Maintenance failed");
		alert.showAndWait();	
	}
	
	public static void resetcomplete () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Database successfully reseted");
		alert.showAndWait();
	}
	
	public static void resetfailed () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Database failed to reset");
		alert.showAndWait();
	}
	
	public static void successAddRoom () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Room Successfully Added");
		alert.showAndWait();
	}
	
	public static void duplicatedRoom () {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Duplicated room ID");
		alert.showAndWait();
	}
	
	public static void blankField () {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Warning");
		alert.setHeaderText("The field cannot be left blank");
		alert.showAndWait();	
	}
	
	public static void mismatchInput () {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Please input correct data type");
		alert.showAndWait();
	}
	
	public static void mismatchDate () {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Please input correct date");
		alert.showAndWait();
	}
	
}
