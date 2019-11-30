package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MaintenanceException extends Exception {
	
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
	
	public MaintenanceException (String reason, String roomid) {
		this.reason = reason;
		this.roomid = roomid;	
	}
	
	public void alertMaintException (String reason) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Maintenance Exception");
		alert.setContentText(reason);
		alert.showAndWait();	
	}

}
