package controller;

import javafx.geometry.Insets;
import exception.RentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.HotelArray;
import util.DateTime;
import view.CompleteAlert;

public class RentController implements EventHandler<ActionEvent> {

	private int index;
	private String roomid;
	private String custID;
	private DateTime rentdate;
	private int NumOfRentDay;
	
	public RentController (int index) {
		this.index = index;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage();
		
		//Pane for Rent
		GridPane rentpane = new GridPane();
		rentpane.setAlignment(Pos.CENTER);
		rentpane.setPadding(new Insets (10, 10, 10, 10));
		rentpane.setHgap(10);
		rentpane.setVgap(10);
		Scene rentscene = new Scene(rentpane, 400, 200);
		stage.setTitle("Rent Room");
		stage.setScene(rentscene);
		stage.show();
		
		// Label
		Text renttext = new Text(0, 0, "Rent room for " + HotelArray.getRoomlist().get(index).getRoomid());
		renttext.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		rentpane.add(renttext, 0, 0);
		
		// Add TextField
		rentpane.add(new Label ("Input check-in date : "), 0, 1);
		TextField checkindate = new TextField();
		checkindate.setPromptText("dd/mm/yyyy");
		rentpane.add(checkindate, 1, 1);
		
		rentpane.add(new Label ("Input customer ID (begin with C) "), 0, 2);
		TextField userID = new TextField();
		rentpane.add(userID, 1, 2);
		
		rentpane.add(new Label ("Input estimated rent days : "), 0, 3);
		TextField estrentdate = new TextField();
		rentpane.add(estrentdate, 1, 3);
		
		// Add Button for rent
		Button rentbutton = new Button ("Rent Room");
		rentbutton.setAlignment(Pos.CENTER);
		rentpane.add(rentbutton, 1, 4);
		
		rentbutton.setOnAction(e -> {
			//validity check for empty blank
			if (estrentdate.getText().isEmpty() || checkindate.getText().isEmpty() || userID.getText().isEmpty()) {
				CompleteAlert.blankField();
			}
			
			else {
			try {
			this.roomid = HotelArray.getRoomlist().get(index).getRoomid();
			this.custID = userID.getText();
			String estdate = estrentdate.getText();
			this.NumOfRentDay = Integer.parseInt(estdate);
			String date = checkindate.getText();
			String array[] = date.split("/");
			String dd = array [0];
			int dayint = Integer.parseInt(dd);
			String mm = array [1];
			int monthint = Integer.parseInt(mm);
			String yy = array [2];
			int yearint = Integer.parseInt(yy);
			
			rentdate = new DateTime (dayint, monthint, yearint);
			//calling rent method from room class
			HotelArray.getRoomlist().get(index).rent(roomid, custID, rentdate, NumOfRentDay);
			
			}
			catch (IndexOutOfBoundsException e3) {
				CompleteAlert.mismatchDate();
			}
			catch (NumberFormatException e2) {
				CompleteAlert.mismatchInput();				
			}
			catch (RentException e1) {
				String reason = "Failed to rent room";
				e1.RentExceptionAlert(reason, roomid);
			}
			stage.close();
			}
			
			
		});
	}

}
