package controller;

import exception.MaintenanceException;
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

public class MaintController implements EventHandler<ActionEvent> {

	private int index;
	private DateTime completemaint;
	
	public MaintController (int index) {
		this.index = index;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Stage stage = new Stage ();
		
		GridPane maintpane = new GridPane();
		maintpane.setAlignment(Pos.CENTER);
		maintpane.setPadding(new Insets (10, 10, 10, 10));
		maintpane.setHgap(10);
		maintpane.setVgap(10);
		Scene maintscene = new Scene(maintpane, 450, 200);
		stage.setTitle("Perform Maintenance");
		stage.setScene(maintscene);
		stage.show();
		
		// Label
		Text mainttext = new Text(0, 0, "Complete maint. for " + HotelArray.getRoomlist().get(index).getRoomid());
		mainttext.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		maintpane.add(mainttext, 0, 0);
		
		// Add TextField
		maintpane.add(new Label ("Input completion date : "), 0, 1);
		TextField completedate = new TextField();
		completedate.setPromptText("dd/mm/yyyy");
		maintpane.add(completedate, 1, 1);
		
		// Add Button for return
		Button maintbutton = new Button ("Complete Maintenance");
		maintbutton.setAlignment(Pos.CENTER);
		maintpane.add(maintbutton, 1, 2);
		
		maintbutton.setOnAction(e -> {
			//validity check on empty blank
			if (completedate.getText().isEmpty()) {
				CompleteAlert.blankField();
			}
			
			else {
			try {	
			String date = completedate.getText();
			String array[] = date.split("/");
			String dd = array [0];
			int dayint = Integer.parseInt(dd);
			String mm = array [1];
			int monthint = Integer.parseInt(mm);
			String yy = array [2];
			int yearint = Integer.parseInt(yy);
			completemaint = new DateTime (dayint, monthint, yearint);
				//calling complete maintenance method from room class		
				HotelArray.getRoomlist().get(index).completeMaintenance(completemaint);
			}
			catch (NumberFormatException e2) {
				CompleteAlert.mismatchInput();				
			}
			
			catch (IndexOutOfBoundsException e3) {
				CompleteAlert.mismatchDate();				
				
			} catch (MaintenanceException e1) {
				String reason = "Input Correct date";
				e1.alertMaintException(reason);	
				}			
			}
			stage.close();
		});
		
	}

}
