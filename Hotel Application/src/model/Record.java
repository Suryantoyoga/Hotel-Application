package model;
import util.DateTime;

public class Record {
	
	private String roomid;
	private String custid;
	private DateTime rentdate;
	private DateTime estReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;
	
	//Record constructor 1
	public Record(String roomid, String custid, DateTime rentdate, DateTime estReturnDate) {
		this.roomid = roomid;
		this.custid = custid;
		this.rentdate = rentdate;
		this.estReturnDate = estReturnDate;
	}
	
	//Overloading Record constructor after room succesfully returned
	public Record(String roomid, String custid, DateTime rentdate, DateTime estReturnDate, DateTime actualReturnDate, double rentalFee,
			double lateFee) {

		this.roomid = roomid;
		this.custid = custid;
		this.rentdate = rentdate;
		this.estReturnDate = estReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public DateTime getRentdate() {
		return rentdate;
	}
	
	public void setRentdate(DateTime rentdate) {
		this.rentdate = rentdate;
	}
	
	public DateTime getEstReturnDate() {
		return estReturnDate;
	}

	public void setEstReturnDate(DateTime estReturnDate) {
		this.estReturnDate = estReturnDate;
	}

	public DateTime getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public double getRentalFee() {
		return rentalFee;
	}

	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	
	@Override
	public String toString() {
		return getRoomid() + "_" + getCustid() + "_" + rentdate.getEightDigitDate() + ":" + getRentdate() + ":" + getEstReturnDate() + ":" + getActualReturnDate() + ":" + getRentalFee() + ":" + getLateFee();
		
	}
	
	//Create String for record while being rented
	public String getDetails(String rented) {
				
		return "Record ID:" + "\t" + getRoomid() + "_" + getCustid() + "_" + rentdate.getEightDigitDate() + "\n" +
				"Rent Date:" + "\t" + getRentdate() + "\n" +
				"Estimated Return Date:" + "\t" + getEstReturnDate() + "\n" +
				"---------------------------------------------";
	}
	
	//overloading String method for record while room succesfully returned
	public String getDetails() {
		
		return "Record ID:" + "\t" + getRoomid() + "_" + getCustid() + "_" + rentdate.getEightDigitDate() + "\n" +
				"Rent Date:" + "\t" + getRentdate() + "\n" +
				"Estimated Return Date:" + "\t" + getEstReturnDate() + "\n" +
				"Actual Return Date:" + "\t" + getActualReturnDate() + "\n" +
				"Rental Fee:" + "\t" + String.format("%.2f",getRentalFee()) + "\n" + 
				"Late Fee" + "\t" + String.format("%.2f",getLateFee()) + "\n" +
				"---------------------------------------------";
		
	}
	
}
