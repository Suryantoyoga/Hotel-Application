package model;
import exception.MaintenanceException;
import exception.RentException;
import exception.ReturnException;
import util.DateTime;
import view.CompleteAlert;

public class Suite extends Room {

	//Suite Room have last maintenance attribute
	private double rates;
	private double sixroom = 999;
	private double rentalfee;
	private double latefee;
	private double latefeerates = 1099;
	private DateTime today = new DateTime();
	private DateTime lastmaintenance;
	private int maintinterval = 10;
	
	//Suite Room Constructor
	
	public Suite(String roomid, int bednum, String summary, String type, String status,  DateTime lastmaintenance, String picnames) {
		super(roomid, bednum, summary, type, status, picnames);
		this.rates = sixroom;
		this.lastmaintenance = lastmaintenance;	
	}
	
	
	public Suite(String roomid, String summary, String type, String picnames) {
		super(roomid, 6, summary, type, "Available", picnames);
		this.rates = sixroom;
		this.lastmaintenance = today;	
	}
	
	public DateTime getLastmaintenance() {
		return lastmaintenance;
	}

	public void setLastmaintenance(DateTime lastmaintenance) {
		this.lastmaintenance = lastmaintenance;
	}

	public double getRates() {
		return rates;
	}

	public void setRates(double rates) {
		this.rates = rates;
	}
	
	public double getRentalfee() {
		return rentalfee;
	}

	public void setRentalfee(double rentalfee) {
		this.rentalfee = rentalfee;
	}

	public double getLatefee() {
		return latefee;
	}

	public void setLatefee(double latefee) {
		this.latefee = latefee;
	}

	@Override
	public String toString() {
		return getRoomid() + ":" + getBednum() + ":" + getType() + ":" + getStatus() + ":" + getLastmaintenance() + ":" + getSummary() + ":" + getPicnames();
	}

	@Override
	//Method for renting Suite Room
	public void rent(String roomid, String custID, DateTime rentdate, int NumOfRentDay) throws RentException {
		today = new DateTime ();
		int diff = DateTime.diffDays(rentdate, today);
		//exception if the user want to rent at the date before today		
		if (diff <= -1) {
			String reason = ("input correct date");
			throw new RentException (reason, roomid);			
		}
		
		else if (checkmaintenance(rentdate, NumOfRentDay) == true) {		
			DateTime estReturnDate = new DateTime (rentdate, NumOfRentDay);
						
			Record r2 = new Record (roomid, custID, rentdate, estReturnDate);
			HotelArray.addRecord(r2);
			setStatus("Rented");
			CompleteAlert.successrent(roomid, custID, rentdate);					
		}
		else {
			CompleteAlert.failedrent(roomid, custID);
		}
			
	}
	
	//Validation check for renting Suite Room
	public boolean checkmaintenance (DateTime rentdate, int NumOfRentDay) {
		DateTime estReturnDate = new DateTime (rentdate, NumOfRentDay);
				
		if(getStatus().equals("Available") && (DateTime.diffDays(estReturnDate, lastmaintenance) < maintinterval) ) {
			return true;
		}
		
		else
			return false;
	}

	@Override
	//Method for returning room, and calculate latefee and rental fee for Standard Room
	public void returnRoom(DateTime returnDate, String custID) throws ReturnException {
		
		for (Record obj : HotelArray.getRecords()) {
			if (obj.getCustid().equals(custID)) {
				int index = HotelArray.getRecords().indexOf(obj);

				if (DateTime.diffDays(returnDate, obj.getRentdate()) < 0 || getStatus() != "Rented") {
					CompleteAlert.failedreturn(custID);
				}
				else if (DateTime.diffDays(returnDate, obj.getRentdate()) > 0 || getStatus() == "Rented") {
				
					rentalfee = DateTime.diffDays(returnDate, obj.getRentdate()) * getRates();
					
					if (DateTime.diffDays(returnDate, obj.getEstReturnDate()) < 0) {
						latefee = 0;
					}
					else if (DateTime.diffDays(returnDate, obj.getEstReturnDate()) >= 0){
						latefee = DateTime.diffDays(returnDate, obj.getEstReturnDate()) * latefeerates;
					}
								
				Record r2 = new Record (getRoomid(), obj.getCustid(), obj.getRentdate(), obj.getEstReturnDate(), returnDate, rentalfee, latefee);
	
				//Replace record object with newest record
				HotelArray.getRecords().set(index, r2);
								
				setStatus("Available");
				CompleteAlert.successreturn(custID, rentalfee, latefee);
				}				
			}						
		}		
	}

	//Method performing maintenance for suite
	@Override
	public void performMaintenance() throws MaintenanceException {
		super.performMaintenance();
		
	}

	//Method completing maintenance for suite room, update last maintenance date for suite room

	public void completeMaintenance(DateTime completionDate) throws MaintenanceException {

		today = new DateTime ();
		int diff = DateTime.diffDays(completionDate, today);
		
		if (!getStatus().equals("Maintenance")) {
			String reason = ("Room is not in Maintenance");
			throw new MaintenanceException (reason, getRoomid());
		}
		
		else if (diff <= -1) {
			String reason = ("input correct date");
			throw new MaintenanceException (reason, getRoomid());
		}
		
		else if (getStatus().equals("Maintenance")) {
			setStatus("Available");
			setLastmaintenance(completionDate);
			CompleteAlert.successcompletemaint();
		}		

	}

	//print standard room details in human readable form
	@Override
	public String getDetails() {
		return super.getDetails() + String.format("Last maintenance date: %s%n", getLastmaintenance());
	}
	

	//method for printing records arraylist, printing different format of records using method overloading
	@Override
	public void recorddetails() {
		for (Record obj : HotelArray.getRecords()) {
			if(obj.getActualReturnDate() == null) {
				System.out.println(obj.getDetails("Rented"));
			}
			else
				System.out.println(obj.getDetails());
			}
	}


}
