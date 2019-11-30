package controller;

import exception.MaintenanceException;
import controller.RoomDetails;
import database.SaveRentalDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HotelArray;

public class RoomDetails implements EventHandler<ActionEvent> {

	private int index;
	//RoomDetails constructor to pass the index from room that has been chosen
	public RoomDetails (int index) {
		this.index = index;
	}
		
	@Override
	public void handle(ActionEvent event) {
		Stage roomdetails = new Stage();
		//make sure that the user execute this window first 
		roomdetails.initModality(Modality.APPLICATION_MODAL);
		BorderPane roombp = new BorderPane();
		roombp.setPrefSize(800, 600);
	
		//Title
		Pane title = new Pane();
		ImageView logo = new ImageView(new Image ("file:images/showroom.jpg"));
		logo.fitWidthProperty().bind(title.widthProperty());		
		
		Text text1 = new Text(100, 75, "Room Details");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
		text1.setFill(Color.BLACK);		
		title.getChildren().addAll(logo, text1);
		title.setStyle("-fx-border-width: 3px;-fx-border-color: green");								
		
		//Room Details		
		BorderPane center = new BorderPane();
		ImageView centerpic = new ImageView(new Image("file:images/"+ HotelArray.getRoomlist().get(index).getPicnames()));
		centerpic.setFitHeight(400);
		centerpic.setFitWidth(550);
		
		Text roomtext = new Text (HotelArray.getRoomlist().get(index).getDetails());
		roomtext.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		BorderPane.setAlignment(roomtext, Pos.CENTER);
		BorderPane.setMargin(roomtext, new Insets(10,10,10,10));
		
		center.setCenter(centerpic);
		center.setRight(roomtext);
		
		ScrollPane rentalpane = new ScrollPane();
		rentalpane.setPadding(new Insets(10, 10, 10, 10));
		rentalpane.setFitToWidth(true);
		rentalpane.setFitToHeight(true);		
		
		//create listview for rental record pane
		ListView<String> rentallist = new ListView<String>();
		
		for (int j = 0 ; j < HotelArray.getRecords().size() ; j++) {
			
			if(HotelArray.getRecords().get(j).getRoomid().equals(HotelArray.getRoomlist().get(index).getRoomid())) {
				rentallist.getItems().addAll(HotelArray.getRecords().get(j).getDetails());
			}
		}
		
		rentalpane.setContent(rentallist);
					
		//Menu Pane in bottom borderpane
		HBox bottommenu = new HBox(20); 
		Button rentroom = new Button ("Rent Room");
		rentroom.setOnAction(new RentController(index));
				
		Button returnroom = new Button ("Return Room");
		returnroom.setOnAction(new ReturnController(index));
		
		Button domaintenance = new Button ("Perform Room Maintenance");
		domaintenance.setOnAction(e -> {
			try {
				HotelArray.getRoomlist().get(index).performMaintenance();
			} catch (MaintenanceException e1) {
				String reason = "Failed to rent room";
				e1.alertMaintException(reason);	
			}
		});
		
		Button completeroom = new Button ("Complete Room Maintenance");
		completeroom.setOnAction(new MaintController(index));
		
		Button savetodb = new Button ("Save to Database");
		savetodb.setOnAction(e -> {
			SaveRentalDB.SaveRentalArray();
			roomdetails.close();
			handle(e);
					
		});
		
		Button returntomain = new Button ("Return to Main Menu");
		returntomain.setOnAction(e -> {
			roomdetails.close();
		});
				
		bottommenu.getChildren().addAll(rentroom, returnroom, domaintenance, completeroom, savetodb, returntomain);
		bottommenu.setAlignment(Pos.CENTER);
		bottommenu.setStyle("-fx-border-width: 3px;-fx-border-color: green");
		bottommenu.setPadding(new Insets(20, 20, 20, 20));
		
		roombp.setTop(title);
		roombp.setCenter(center);
		roombp.setRight(rentalpane);
		roombp.setBottom(bottommenu);
		
		
		Scene scene = new Scene(roombp);
		
		roomdetails.setTitle("Room Details");
		roomdetails.setScene(scene);
		roomdetails.show();

	}

}
