package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import model.HotelArray;

public class SaveRentalDB {

	
	//Save the changed made from array list to database, so it will not get deleted if user closed the program
	
	public static void SaveRentalArray () {
				
		String DB_NAME = "testDB";
		
		try (Connection con = ConnectDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();) {
			
			CreateDatabase.createRentalDB();
					
			for (int i = 0 ; i < HotelArray.getRecords().size() ; i++) {
				
				if (HotelArray.getRecords().get(i).getActualReturnDate() != null) {
					String SQL1 = "INSERT INTO RentalRecord (Room_ID, Record_ID, Rent_Date, Return_Date, Actual_Return, Rental_Fee, Late_fee) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(SQL1);

					String roomid = HotelArray.getRecords().get(i).getRoomid();
					String recid = HotelArray.getRecords().get(i).getRoomid() + "_" + HotelArray.getRecords().get(i).getCustid() + "_" + HotelArray.getRecords().get(i).getRentdate().getEightDigitDate();
					String rentdate = HotelArray.getRecords().get(i).getRentdate().toString();
					String returndate = HotelArray.getRecords().get(i).getEstReturnDate().toString();
					String actualreturn = HotelArray.getRecords().get(i).getActualReturnDate().toString();
					double rentalfee = HotelArray.getRecords().get(i).getRentalFee();
					double latefee = HotelArray.getRecords().get(i).getLateFee();
					
					ps1.setString(1, roomid);
					ps1.setString(2, recid);
					ps1.setString(3, rentdate);
					ps1.setString(4, returndate);
					ps1.setString(5, actualreturn);
					ps1.setDouble(6, rentalfee);
					ps1.setDouble(7, latefee);
					ps1.executeUpdate();					
				}
				
				else {
					String SQL2 = "INSERT INTO RentalRecord (Room_ID, Record_ID, Rent_Date, Return_Date) VALUES (?,?,?,?)";
					PreparedStatement ps2 = con.prepareStatement(SQL2);

					String roomid = HotelArray.getRecords().get(i).getRoomid();
					String recid = HotelArray.getRecords().get(i).getRoomid() + "_" + HotelArray.getRecords().get(i).getCustid() + "_" + HotelArray.getRecords().get(i).getRentdate().getEightDigitDate();
					String rentdate = HotelArray.getRecords().get(i).getRentdate().toString();
					String returndate = HotelArray.getRecords().get(i).getEstReturnDate().toString();
					
					ps2.setString(1, roomid);
					ps2.setString(2, recid);
					ps2.setString(3, rentdate);
					ps2.setString(4, returndate);
					ps2.executeUpdate();					
							
				}
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			}		
		
	}
	
}
