package view;


import controller.RoomDetails;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.HotelArray;
import model.Standard;
import model.Suite;

public class CenterPane{
	
		
	public ScrollPane addCenterPane () throws Exception {
		
		//Setting ScrollPane
		ScrollPane pane = new ScrollPane();
		
		//setting GridPane		
		GridPane RoomGP = new GridPane();
		RoomGP.setPadding(new Insets(10, 10, 10, 10));
		RoomGP.setVgap(5.5);
		//set the grid to be visible for better user interface
		RoomGP.setGridLinesVisible(true);
		
		//Title Grid
		Label lb1 = new Label("Picture");
		lb1.setPadding(new Insets(10, 10, 10, 10));
		Label lb2 = new Label("Room Type");
		lb2.setPadding(new Insets(10, 10, 10, 10));
		Label lb3 = new Label("Bedroom");
		lb3.setPadding(new Insets(10, 10, 10, 10));
		Label lb4 = new Label("Status");
		lb4.setPadding(new Insets(10, 10, 10, 10));
		Label lb5 = new Label("Summary");
		lb5.setPadding(new Insets(10, 10, 10, 10));
		Label lb6 = new Label("Price/Night");
		lb6.setPadding(new Insets(10, 10, 10, 10));	
		RoomGP.add(lb1, 0, 0);
		RoomGP.add(lb2, 1, 0);
		RoomGP.add(lb3, 2, 0);
		RoomGP.add(lb4, 3, 0);
		RoomGP.add(lb5, 4, 0);
		RoomGP.add(lb6, 5, 0);					
		
		HotelArray hotelarray = new HotelArray();
		
		HotelArray.getRoomlist().clear();
		HotelArray.getRecords().clear();
		hotelarray.RoomArray();
		hotelarray.RecordArray();
		
		
		for (int i = 0 ; i < HotelArray.getRoomlist().size() ; i++) {
			int index = i;
			
			ImageView stdroom1 = new ImageView(new Image("file:images/"+ HotelArray.getRoomlist().get(i).getPicnames()));
			stdroom1.setFitHeight(150);
			stdroom1.setFitWidth(250);
			Label stdlb1 = new Label(HotelArray.getRoomlist().get(i).getType());
			stdlb1.setPadding(new Insets(10, 10, 10, 10));
			String bednum = Integer.toString(HotelArray.getRoomlist().get(i).getBednum());
			Label stdlb2 = new Label(bednum);
			stdlb2.setPadding(new Insets(10, 10, 10, 30));
			Label stdlb3 = new Label(HotelArray.getRoomlist().get(i).getStatus());
			stdlb3.setPadding(new Insets(10, 10, 10, 10));
			Label stdlb4 = new Label(HotelArray.getRoomlist().get(i).getSummary());
			stdlb4.setPadding(new Insets(10, 10, 10, 10));
						
			if (HotelArray.getRoomlist().get(i) instanceof Standard ) {
				String rates = Double.toString(((Standard) HotelArray.getRoomlist().get(i)).getRates());
				Label stdlbl5 = new Label(rates);
				stdlbl5.setPadding(new Insets(10, 10, 10, 30));
				RoomGP.add(stdlbl5, 5, i+1);				
			}
			else if (HotelArray.getRoomlist().get(i) instanceof Suite) {
				String rates = Double.toString(((Suite) HotelArray.getRoomlist().get(i)).getRates());
				Label stdlbl5 = new Label(rates);
				stdlbl5.setPadding(new Insets(10, 10, 10, 30));
				RoomGP.add(stdlbl5, 5, i+1);
			}				
			RoomGP.add(stdroom1, 0, i+1);
			RoomGP.add(stdlb1, 1, i+1);
			RoomGP.add(stdlb2, 2, i+1);
			RoomGP.add(stdlb3, 3, i+1);
			RoomGP.add(stdlb4, 4, i+1);
			Button btAdd = new Button("Select");			
			RoomGP.add(btAdd, 6, i+1);		
			btAdd.setOnAction(new RoomDetails(index));		
		}
		
		pane.setContent(RoomGP);
		
		return pane;	
		
		
	}
}

	

	

