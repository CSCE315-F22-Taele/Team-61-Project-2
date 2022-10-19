import java.awt.*;
import java.sql.*; 
import javax.accessibility.AccessibleRelationSet;
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

/**
 * This class creates a connection with the AWS server in order to connect to the postgreSQL database
 * @author Unknown, Code was provided
 */
public class connectionSetup {

    Connection conn = null;

    connectionSetup() {
		//Building the connection with your credentials
    	String teamNumber = "61"; // Your team number
    	String sectionNumber = "905"; // Your section number
    	String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
    	String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    	dbSetup myCredentials = new dbSetup(); 

    	//Connecting to the database
    	try {
      		conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
    	} catch (Exception e) {
      		e.printStackTrace();
      		System.err.println(e.getClass().getName()+": "+e.getMessage());
      		System.exit(0);
		}
	}
}