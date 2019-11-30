package controller;

import database.AddRoomDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.HotelArray;
import model.Room;
import model.Standard;
import model.Suite;
import view.CompleteAlert;

public class AddRoomController implements EventHandler<ActionEvent> {

	private String type;
	private String roomid;
	private int bednum;
	private String summary;
	HotelArray hotelarray = new HotelArray();
	
	@Override
	public void handle(ActionEvent event) {
		
		//create room object from database and put in arraylist
		try {
			hotelarray.RoomArray();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
		Stage addRoom = new Stage();
		
		GridPane pane = new GridPane();
		pane.setMinSize(450, 250);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		// Room type choice using radio Button
		RadioButton standard = new RadioButton ("Standard");
		RadioButton suite = new RadioButton ("Suite");
		pane.add(standard, 0,0);
		pane.add(suite, 1, 0);		
		ToggleGroup group = new ToggleGroup();
		standard.setToggleGroup(group);
		suite.setToggleGroup(group);;
		
		standard.setOnAction(e -> {
			if (standard.isSelected()) {
				this.type = "Standard";
			}
		});
		suite.setOnAction(e -> {
			if (suite.isSelected()) {
				this.type = "Suite";
			}
		});
		
		// Room information pane for user
		pane.add(new Label("Room ID: ( R_XXX for standard S_XXX for suite)"), 0, 1);
		TextField roomidtext = new TextField();
		pane.add(roomidtext, 1, 1);
				
		pane.add(new Label("Number of bed: (for suite only 6 beds)"), 0, 2);
		ComboBox<Integer> bednumtext = new ComboBox<>();
		bednumtext.getItems().addAll(1, 2 , 4, 6);
		pane.add(bednumtext, 1, 2);
				
		pane.add(new Label("Room summary:"), 0, 3);
		TextField summarytext = new TextField();
		pane.add(summarytext, 1, 3);

		Button btAdd = new Button("Add Room");
		
		btAdd.setOnAction(e ->  
		{
			//validity check for blank field		
			if(roomidtext.getText().isEmpty() || summarytext.getText().isEmpty() || bednumtext.getValue() == null || type == null) {
				CompleteAlert.blankField();
			}
			//vallidity check for duplicated rooms
			else if (checkRoomID(roomidtext.getText()) == false) {
				CompleteAlert.duplicatedRoom();
			}				
			else {
				//put the newly added room to database
				AddRoomDatabase add = new AddRoomDatabase();
				
				this.roomid = roomidtext.getText();
				this.bednum = bednumtext.getValue();
				this.summary = summarytext.getText();

				if(type == "Standard") {
					Room s1 = new Standard (roomid, bednum, summary, type, "noimage");
					HotelArray.addRoom(s1);
					add.addRoom(roomid, bednum, type, s1.getStatus(), null , summary, ((Standard) s1).getRates(), "noimage.jpg");				
				}				
				else if(type == "Suite") {
					Room s2 = new Suite (roomid, summary, type, "noimage");
					HotelArray.addRoom(s2);
					
					String lastmain = String.valueOf(((Suite) s2).getLastmaintenance());
					
					add.addRoom(roomid, s2.getBednum(), type, s2.getStatus(), lastmain, summary, ((Suite) s2).getRates(), "noimage.jpg");
				}
				
				CompleteAlert.successAddRoom();
			}
		}
		);
		pane.add(btAdd, 1, 5);
		
		// Create a scene and place it in the stage				
		Scene scene = new Scene(pane);
		addRoom.setTitle("Add Room");
		addRoom.setScene(scene);
		addRoom.show();
		
	}
	
	
	//Method for checking room duplicated ID
	private boolean checkRoomID(String roomid) {
		int count = 0;
		for (int i = 0 ; i < HotelArray.getRoomlist().size() ; i++) {
			if (HotelArray.getRoomlist().get(i).getRoomid().equals(roomid)) {
				count++;
			}			
		}
		if (count > 0) {
			return false;
		}
		else
			return true;
	}

	

}
