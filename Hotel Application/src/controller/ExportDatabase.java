package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.HotelArray;


//class for export the database into .txt files
public class ExportDatabase implements EventHandler<ActionEvent>{
	
	@Override
	public void handle(ActionEvent arg0) {
		//choose the directory to export
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Export Database");
		
		Stage stage = new Stage();
		
		try {
			File dirName = chooser.showDialog(stage);
			
			File file = new File (dirName, "export_data.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			//read the arraylist and write the hotel object and record object using for loops
			for (int i = 0 ; i <HotelArray.getRoomlist().size() ; i++) {
				writer.write(HotelArray.getRoomlist().get(i).toString());
				writer.newLine();
				
				for (int j = 0 ; j < HotelArray.getRecords().size() ; j++) {
					if(!HotelArray.getRecords().get(j).getRoomid().equals(HotelArray.getRoomlist().get(i).getRoomid())) {
						continue;
					}
					writer.write(HotelArray.getRecords().get(j).toString());
					writer.newLine();
				}
			}
			writer.flush();
			writer.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
