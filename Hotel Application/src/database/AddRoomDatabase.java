package database;

import database.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AddRoomDatabase {
	
	private String DB_NAME = "testDB";
	//this method is for addRoom object to the database
	public void addRoom (String roomid, int bednum, String type, String status, String lastmaint, String summary, double rates, String picture) {
				
        try (Connection con = ConnectDB.getConnection(DB_NAME);
        		){
	    //Using preparedStatement to execute the SQL Query    
        String sql = "INSERT INTO HotelRoom (Room_ID, Bed_Number, Type, Status, Last_Maintenance, Summary, Rates, Picture_Names) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,roomid);
		ps.setInt(2, bednum);
		ps.setString(3, type);
		ps.setString(4, status);
		ps.setString(5, lastmaint);
		ps.setString(6, summary);
		ps.setDouble(7, rates);
		ps.setString(8, picture);
		
		ps.executeUpdate();
        }
        
        catch (Exception e) {
			System.out.println(e.getMessage());
			}		
        
        
	}

}
