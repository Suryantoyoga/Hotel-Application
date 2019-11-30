package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RentException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private String roomid;
	
	
	public String getReason() {
		return reason;
	}


	public String getRoomid() {
		return roomid;
	}
		
	public RentException (String reason, String roomid) {
		this.reason = reason;
		this.roomid = roomid;
		
	}
	
	public void RentExceptionAlert (String reason, String roomid) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Exception");
		alert.setHeaderText("Rent Exception");
		alert.setContentText(reason + " for " + roomid);
		alert.showAndWait();
	}

}
