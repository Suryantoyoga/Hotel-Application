package model;
import java.util.Collections;

import exception.MaintenanceException;
import exception.RentException;
import exception.ReturnException;
import util.DateTime;
import view.CompleteAlert;

public class Standard extends Room {
	
	private double onebed = 59;
	private double twobed = 99;
	private double fourbed = 199;
    private double rates;
    private double rentalfee;
    private double latefee;
    private double latefeerates = 1.35;

	//Standard room constructor
    
    public Standard(String roomid, int bednum, String summary, String type, String status,  String picnames) {
    	super(roomid, bednum, summary, type, status, picnames);
    	this.setRates(rates);
    }
    
	public Standard(String roomid, int bednum, String summary, String type, String picnames) {
		super(roomid, bednum, summary, type, "Available", picnames);
		this.setRates(rates);
	}

	public double getRates() {
		return rates;
	}

	//The rates will be automaticaly set depends on number of bedrooms input
	public void setRates(double rates) {
		if ( getBednum() == 1 ) {
			this.rates = onebed;
		}
		
		else if ( getBednum() == 2) {
			this.rates = twobed;
		}
		
		else if ( getBednum() == 4 ) {
			this.rates = fourbed;
		}
		
		else 
			System.out.println("invalid room input");
	}

	public double getLatefee() {
		return latefee;
	}

	public double getRentalfee() {
		return rentalfee;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	//Method for renting Standard Room
	@Override
	public void rent(String roomid, String custID, DateTime rentdate, int NumOfRentDay) throws RentException{		
		DateTime today = new DateTime();
		int diff = DateTime.diffDays(rentdate, today);
		//exception if the user want to rate at the date before today
		if (diff <= -1) {
			String reason = ("input correct date");
			throw new RentException (reason, roomid);
			
		}
		
		else if (checkday(rentdate, NumOfRentDay) == true) {		
			DateTime estReturnDate = new DateTime (rentdate, NumOfRentDay);						
			Record r1 = new Record (roomid, custID, rentdate, estReturnDate);
			
			HotelArray.addRecord(r1);
			
			setStatus("Rented");

			CompleteAlert.successrent(roomid, custID, rentdate);
		
		}
		else 
			CompleteAlert.failedrent(roomid, custID);				
	}
	
	//Validation day check for renting Standard Room
	public boolean checkday (DateTime rentdate, int NumOfRentDay) {
		
		if(getStatus().equals("Available") && NumOfRentDay <= 10 && (NumOfRentDay >= 2 && rentdate.getNameOfDay() != "Saturday" && rentdate.getNameOfDay() != "Sunday" )) {
			return true;
		}
		
		else if (getStatus().equals("Available") && NumOfRentDay <= 10 && (NumOfRentDay >= 3  && rentdate.getNameOfDay() == "Saturday" || rentdate.getNameOfDay() == "Sunday" )) {
			return true;
		}		
		else
			return false;
	}
	
	//Method for returning room, and calculate latefee and rental fee for Standard Room
	@Override
	public void returnRoom(DateTime returnDate, String custID) throws ReturnException {
		
		for (Record obj : HotelArray.getRecords()) {
			if (obj.getCustid().equals(custID)) {
				int index = HotelArray.getRecords().indexOf(obj);

				if (DateTime.diffDays(returnDate, obj.getRentdate()) < 0 || !getStatus().equals("Rented")) {
					CompleteAlert.failedreturn(custID);
				}
				else if (DateTime.diffDays(returnDate, obj.getRentdate()) > 0 || getStatus().equals("Rented")) {
				
					rentalfee = DateTime.diffDays(returnDate, obj.getRentdate()) * getRates();
					
					if (DateTime.diffDays(returnDate, obj.getEstReturnDate()) < 0) {
						latefee = 0;
					}
					else if (DateTime.diffDays(returnDate, obj.getEstReturnDate()) >= 0){
						latefee = DateTime.diffDays(returnDate, obj.getEstReturnDate()) * latefeerates * getRates();
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
	
	//Method performing maintenance for standard room
	@Override
	public void performMaintenance() throws MaintenanceException {
		super.performMaintenance();	
	}

	//Method completing maintenance for standard room
	@Override
	public void completeMaintenance(DateTime completionDate) throws MaintenanceException {
		super.completeMaintenance(completionDate);
	}

	//print standard room details in human readable form
	public String getDetails() {
		return super.getDetails();
				}

	//method for printing records arraylist, printing different format of records using method overloading
	public void recorddetails() {
		//java collections for sorting the printing order
		Collections.reverse(HotelArray.getRecords());
		for (Record obj : HotelArray.getRecords()) {
			if(obj.getActualReturnDate() == null) {
				System.out.println(obj.getDetails("Rented"));
			}
			else
				System.out.println(obj.getDetails());
			}
	}
				
		
}
