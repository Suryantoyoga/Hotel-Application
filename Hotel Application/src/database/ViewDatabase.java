package database;

import org.hsqldb.util.DatabaseManagerSwing;

//This class is just helper class to view inside the database
public class ViewDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        DatabaseManagerSwing.main(new String[] {
                "--url","jdbc:hsqldb:file:database/testDB","--noexit"
        });
		
	}

}
