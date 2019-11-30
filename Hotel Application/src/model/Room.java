package model;
import exception.MaintenanceException;
import exception.RentException;
import exception.ReturnException;
import util.DateTime;
import view.CompleteAlert;

public abstract class Room {
		
	private String roomid;
    private int bednum;
    private String summary;
    private String type;
    private String status; // Available, Rented, Maintenance
    private String picnames;

    protected Room (String roomid, int bednum, String summary, String type, String status, String picnames) {
    	this.roomid = roomid;
    	this.bednum = bednum;
    	this.summary = summary;
    	this.type = type;
    	this.status = status;
    	this.picnames = picnames;
    }
    
    //Getter and Setter Method
    public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public int getBednum() {
		return bednum;
	}

	public void setBednum(int bednum) {
		this.bednum = bednum;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPicnames() {
		return picnames;
	}

	public void setPicnames(String picnames) {
		this.picnames = picnames;
	}

	//Abstract method
	public abstract void rent(String roomid, String custID, DateTime rentdate, int NumOfRentDay) throws RentException;
	
	public abstract void returnRoom(DateTime returnDate, String custID) throws ReturnException; 
		
	public abstract void recorddetails();
			
	@Override
	public String toString() {
		return getRoomid() + ":" + getBednum() + ":" + getType() + ":" + getStatus() + ":" + getSummary() + ":" + getPicnames();
	}
	
	public String getDetails() {
		return String.format("Room ID: %s %nNumber of bedrooms: %s %nType: %s %nStatus: %s %nFeature Summary: %s %n", 
				getRoomid(), getBednum(), getType(), getStatus(), getSummary());
	}
	
	public void performMaintenance() throws MaintenanceException {
		
		if (getStatus().equals("Available")) {
			setStatus("Maintenance");
			CompleteAlert.successperformmaint();
		}
			
		else if (!getStatus().equals("Maintenance")) {
			CompleteAlert.failedperformmaint();
		}		
	}
	
	public void completeMaintenance(DateTime completionDate) throws MaintenanceException {

		if (!getStatus().equals("Maintenance")) {
			CompleteAlert.failedcompletemaint();
		}
		else if (getStatus().equals("Maintenance")) {
			setStatus("Available");
			CompleteAlert.successcompletemaint();
		}		
	}
	

	
}
