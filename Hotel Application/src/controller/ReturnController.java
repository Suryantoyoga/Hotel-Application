package controller;

import exception.ReturnException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public class ReturnController implements EventHandler<ActionEvent> {

	private int index;
	private DateTime checkout;
	private String custID;
	
	//pass the room index using constructor
	public ReturnController (int index) {
		this.index = index;
	}
		
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage();
		
		//Pane for return
		GridPane returnpane = new GridPane();
		returnpane.setAlignment(Pos.CENTER);
		returnpane.setPadding(new Insets (10, 10, 10, 10));
		returnpane.setHgap(10);
		returnpane.setVgap(10);
		Scene returnscene = new Scene(returnpane, 400, 200);
		stage.setTitle("Return Room");
		stage.setScene(returnscene);
		stage.show();
		
		// Label
		Text returntext = new Text(0, 0, "Return room for " + HotelArray.getRoomlist().get(index).getRoomid());
		returntext.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		returnpane.add(returntext, 0, 0);
		
		// Add TextField
		returnpane.add(new Label ("Input check-out date : "), 0, 1);
		TextField checkoutdate = new TextField();
		checkoutdate.setPromptText("dd/mm/yyyy");
		returnpane.add(checkoutdate, 1, 1);		
		returnpane.add(new Label ("Input customer ID (begin with C) "), 0, 2);
		TextField userID = new TextField();
		returnpane.add(userID, 1, 2);
				
		// Add Button for return
		Button returnbutton = new Button ("Return Room");
		returnbutton.setAlignment(Pos.CENTER);
		returnpane.add(returnbutton, 1, 4);
		
		returnbutton.setOnAction(e -> {
			
			if (userID.getText().isEmpty() || checkoutdate.getText().isEmpty()) {
				CompleteAlert.blankField();
			}
			
			else {
			try {	
			this.custID = userID.getText();			
			String date = checkoutdate.getText();
			String array[] = date.split("/");
			String dd = array [0];
			int dayint = Integer.parseInt(dd);
			String mm = array [1];
			int monthint = Integer.parseInt(mm);
			String yy = array [2];
			int yearint = Integer.parseInt(yy);
			checkout = new DateTime (dayint, monthint, yearint);
			
				//calling the return method from room class
				HotelArray.getRoomlist().get(index).returnRoom(checkout, custID);
			} catch (ReturnException e1) {
				String reason = "Failed to rent room";
				e1.ReturnExceptionAlert(reason, custID);
			}
			catch (NumberFormatException e2) {
				CompleteAlert.mismatchInput();
			}
			catch (IndexOutOfBoundsException e3) {
				CompleteAlert.mismatchDate();
			}
			stage.close();
			}
		});

	}

}
