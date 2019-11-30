package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ReturnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private String custID;
	
	
	public String getReason() {
		return reason;
	}


	public String getCustID() {
		return custID;
	}


	public ReturnException (String reason, String custID) {
		this.reason = reason;
		this.custID = custID;
		
	}
	
	public void ReturnExceptionAlert (String reason, String custID) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Exception");
		alert.setHeaderText("Return Exception");
		alert.setContentText(reason + " for " + custID);
		alert.showAndWait();
	}
	
}
