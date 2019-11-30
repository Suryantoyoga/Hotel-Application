package model;

import java.sql.*;
import java.util.ArrayList;
import util.DateTime;

//Create ArrayList to store Room object and Record object from database
public class HotelArray {
	private Connection con;
	private ResultSet resultSet1;
	private ResultSet resultSet2;
	
	private static ArrayList <Room> roomlist = new ArrayList <> ();
	
	private static ArrayList<Record> records = new ArrayList <> ();

	public static ArrayList<Room> getRoomlist() {
		return roomlist;
	}
	
	public static void addRoom (Room c) {
		roomlist.add(c);			
	}

	public static ArrayList<Record> getRecords() {
		return records;
	}
	
	public static void addRecord (Record c) {
		records.add(c);
	}

	//Method for populate room arraylist
	public void RoomArray () throws Exception{
		con = DriverManager.getConnection("jdbc:hsqldb:file:database/testDB", "SA", "");
        con.createStatement();
        PreparedStatement ps1 = con.prepareStatement("SELECT * FROM HotelRoom WHERE Room_ID LIKE 'R%'");
        PreparedStatement ps2 = con.prepareStatement("SELECT * FROM HotelRoom WHERE Room_ID LIKE 'S%'");
        resultSet1 = ps1.executeQuery();
        resultSet2 = ps2.executeQuery();
        
        while (resultSet1.next() ) {
        	String roomid = resultSet1.getString(1);
        	int bednum = resultSet1.getInt(2);
        	String type = resultSet1.getString(3);
        	String status = resultSet1.getString(4);
        	String summary = resultSet1.getString(6);
        	String picnames = resultSet1.getString(8);
        	Room s1 = new Standard (roomid, bednum, summary, type, status, picnames);
        	roomlist.add(s1);
        }
        
        while (resultSet2.next()) {
        	String roomid = resultSet2.getString(1);
        	int bednum = resultSet2.getInt(2);
        	String type = resultSet2.getString(3);
        	String status = resultSet2.getString(4);
        	String lastmaint = resultSet2.getString(5);
        	
        	String array [] = lastmaint.split("/");
			String dd = array[0];
			int dayint = Integer.parseInt(dd);
			String mm = array[1];
			int monthint = Integer.parseInt(mm);
			String yy = array[2];
			int yearint = Integer.parseInt(yy);			
			DateTime lastmain = new DateTime (dayint, monthint,yearint);
			
        	String summary = resultSet2.getString(6);
        	String picnames = resultSet2.getString(8);
        	Room s2 = new Suite (roomid, bednum, summary, type, status, lastmain, picnames);
        	roomlist.add(s2);
        }
        con.close();
		
	}
	
	//method for populate record arraylist
	public void RecordArray () throws Exception{
		con = DriverManager.getConnection("jdbc:hsqldb:file:database/testDB", "SA", "");
        con.createStatement();
        PreparedStatement ps3 = con.prepareStatement("SELECT * FROM RentalRecord");
        resultSet1 = ps3.executeQuery();
		while (resultSet1.next()) {
			String recid = resultSet1.getString(2);
			String array [] = recid.split("_");
			String character = array [0];
			String number = array [1];
			String custID = array [2];
			String roomid = character + "_" + number;
		
			String rentdate1 = resultSet1.getString(3);			
	       	String array2 [] = rentdate1.split("/");
			String dd = array2[0];
			int dayint = Integer.parseInt(dd);
			String mm = array2[1];
			int monthint = Integer.parseInt(mm);
			String yy = array2[2];
			int yearint = Integer.parseInt(yy);			
			DateTime rentdate = new DateTime (dayint, monthint,yearint);
				
			String returndate = resultSet1.getString(4);
			String array3 [] = returndate.split ("/");
			String dd2 = array3[0];
			int dayint2 = Integer.parseInt(dd2);
			String mm2 = array3[1];
			int monthint2 = Integer.parseInt(mm2);
			String yy2 = array3[2];
			int yearint2 = Integer.parseInt(yy2);			
			DateTime estReturnDate = new DateTime (dayint2, monthint2,yearint2);
			
			if (resultSet1.getString(5) == null) {
				Record r2 = new Record (roomid, custID, rentdate, estReturnDate);
				records.add(r2);				
			}
			
			else {
				String actualreturn = resultSet1.getString(5);
				String array4 [] = actualreturn.split ("/");
				String dd3 = array4[0];
				int dayint3 = Integer.parseInt(dd3);
				String mm3 = array4[1];
				int monthint3 = Integer.parseInt(mm3);
				String yy3 = array4[2];
				int yearint3 = Integer.parseInt(yy3);			
				DateTime actualReturnDate = new DateTime (dayint3, monthint3,yearint3);
				
				double rentalfee = resultSet1.getDouble(6);
				double latefee = resultSet1.getDouble(7);
				
				Record r1 = new Record (roomid, custID, rentdate, estReturnDate, actualReturnDate, rentalfee, latefee);
				records.add(r1);
				
			}		
		}
		con.close();
	}

}
