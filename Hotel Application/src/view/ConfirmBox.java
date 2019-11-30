package view;

import database.SaveRentalDB;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Confirmation box when user want to exit the program, give the user choice to save the database
public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display () {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Exit confirmation");
		window.setMinWidth(300);
		window.setMinHeight(200);
		Label label = new Label();
		label.setText("Are you sure want to exit?");
		Button yes = new Button ("Save Database and exit");
		Button yesno = new Button ("Not Saving Database and exit");
		Button no = new Button ("No");
		
		yes.setOnAction(e -> {
			answer = true;
			SaveRentalDB.SaveRentalArray();
			window.close();
		});
		
		yesno.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		no.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		VBox choice = new VBox(10);
		choice.getChildren().addAll(label, yes, yesno, no);
		choice.setAlignment(Pos.CENTER);
		Scene scene = new Scene (choice);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}

}
