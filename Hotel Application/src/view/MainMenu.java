package view;

import controller.AddRoomController;
import controller.ExportDatabase;
import controller.ImportDatabase;
import controller.ResetDatabase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.HotelArray;

public class MainMenu extends Application{
	
	private Stage menu;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		menu = primaryStage;		
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 1000, 750);
		
		//Application title
		Pane topMenu = new Pane();
		ImageView logo = new ImageView(new Image ("file:images/topmenu.jpg"));
		logo.fitWidthProperty().bind(topMenu.widthProperty());		
		
		Text text1 = new Text(600, 50, "City Lodge Hotel");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
		text1.setFill(Color.WHITE);		
		topMenu.getChildren().addAll(logo, text1);
		topMenu.setStyle("-fx-border-width: 3px;-fx-border-color: green");
		
		//Application Menu in the left
		VBox leftMenu = new VBox(50);
		//Reset Database
		Button resetdb = new Button ("Reset Database");
		resetdb.setOnAction(e -> {
			ResetDatabase reset = new ResetDatabase();
			reset.handle(e);		
			HotelArray.getRoomlist().clear();
			HotelArray.getRecords().clear();
		});
		
		Button addRoom = new Button ("Add Room");
		addRoom.setOnAction(new AddRoomController());
								
		Button importDatabase = new Button ("Import Database");
		importDatabase.setOnAction(new ImportDatabase());
		
		Button exportDatabase = new Button ("Export Database");
		exportDatabase.setOnAction(new ExportDatabase());
		
		Button refresh = new Button ("Refresh Display");
		CenterPane cp = new CenterPane();
		refresh.setOnAction(e -> {
			try {
				borderPane.setCenter(cp.addCenterPane());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		);
		
		Button exit = new Button ("Exit Citylodge");
		exit.setOnAction(e -> {
			closeProgram();
		});
		
		leftMenu.getChildren().addAll(resetdb, addRoom, importDatabase, exportDatabase, refresh, exit);
		leftMenu.setAlignment(Pos.CENTER);
		leftMenu.setStyle("-fx-border-width: 3px;-fx-border-color: green");
		leftMenu.setPadding(new Insets(20, 20, 20, 20));
				
		borderPane.setTop(topMenu);
		borderPane.setLeft(leftMenu);
		borderPane.setCenter(cp.addCenterPane());
		
		//calling the method for asking user to save database
		menu.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();			
		});
		
		menu.setTitle("CityLodge Hotel");
		menu.setScene(scene);
		menu.show();	
	}
	
	public void closeProgram() {
		Boolean answer = ConfirmBox.display();
		if (answer)
			menu.close();
	}
	

}


