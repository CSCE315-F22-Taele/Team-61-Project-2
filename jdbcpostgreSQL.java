import java.sql.*;
import java.io.*;   
import java.util.Random;
import java.util.ArrayList;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
CSCE 331
9-28-2022 Lab
*/
// @author Justin, Brandon, Sam, Roee
public class jdbcpostgreSQL {

  	String statement = "";
	String totalAmount = "";
	String sql_output = "Result: \n";
	String total_output = "Total: ";

  	jdbcpostgreSQL(String database, String inventory_item, String start, String end, String entree, String protein, String side) {

		boolean whereUsed = false;

		if (database == "Sales") {

			this.statement = "SELECT * FROM cabo_grill_sales";
			this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales";
			String additionalCriteria = "";

			if (start != "" && end != "") {
				additionalCriteria += " WHERE date BETWEEN '" + start + "' AND '" + end + "'";
				whereUsed = true;
			}
			if (entree != "None") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " entree_type = '" + entree + "'";
			}
			if (protein != "None") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " protein = '" + protein + "'";
			}
			if (side != "None") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " " + side + " = 1";
			}
			this.statement += additionalCriteria + " ORDER BY sale_id DESC;";
			this.totalAmount += additionalCriteria + ";";
		}

		if (database == "Inventory") {
			if (inventory_item == "All") {
				this.statement = "SELECT * FROM cabo_grill ORDER BY id DESC;";
			} else {
				this.statement = "SELECT * FROM cabo_grill WHERE type = " + "'" + inventory_item + "' ORDER BY id DESC;";
			}
		}
		
    	//Building the connection with your credentials
    	Connection conn = null;
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
    	//System.out.println("Opened database successfully");


    	try {
        	Statement stmt = conn.createStatement();
        	stmt.execute(statement);
			ResultSet rs = stmt.executeQuery(statement);
			if (database == "Sales") {
				while (rs.next()) {
					String id = rs.getString(1);
					String date = rs.getString(2);
					String entree_type = rs.getString(3);
					String protein_type = rs.getString(4);
					String chips_and_salsa = rs.getString(5);
					String chips_and_queso = rs.getString(6);
					String chips_and_guac = rs.getString(7);
					String drink = rs.getString(8);
					String cost = rs.getString(9);
					sql_output += (id + " " + date + " " + entree_type + " " + protein_type + " " + chips_and_salsa + " " + chips_and_queso + " " + chips_and_guac + " " + drink + " " + cost +"\n");
				}		
				Statement stmt2 = conn.createStatement();
        		stmt2.execute(totalAmount);
				ResultSet rs2 = stmt2.executeQuery(totalAmount);
				while (rs2.next()) {
					total_output += rs2.getString(1);
				}		
			}
			
			if (database == "Inventory") {
				while (rs.next()) {
					String id = rs.getString(1);
					String item_name = rs.getString(2);
					String type = rs.getString(3);
					String quantity = rs.getString(4);
					String sufficient_supply = rs.getString(5);
					sql_output += (id + " " + item_name + " " + type + " " + quantity + " " + sufficient_supply + "\n");
				}
			}

    	} catch (Exception e){
    		e.printStackTrace();
    		System.err.println(e.getClass().getName()+": "+e.getMessage());
    		System.exit(0);
    	}
    	//closing the connection
    	try {
      		conn.close();
      		//System.out.println("Connection Closed.");
    	} catch(Exception e) {
      		System.out.println("Connection NOT Closed.");
    	}
  	}

	// constructor for update statement
	jdbcpostgreSQL(String table, String quantityAmt, String sufficientSupplyValue, String itemID) {
		//Building the connection with your credentials
    	Connection conn = null;
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

		// Try Except block executes UPDATE query
		try {
			Statement stmt = conn.createStatement();
			String sql_query = "";
			if (table == "cabo_grill") {
				sql_query = "UPDATE cabo_grill SET quantity = " + quantityAmt + ", sufficient_supply = '" + sufficientSupplyValue
							+ "' WHERE id = " + itemID;
				// System.out.println(sql_query);
				stmt.executeUpdate(sql_query);
			}
		} catch (Exception e){
    		e.printStackTrace();
    		System.err.println(e.getClass().getName()+": "+e.getMessage());
    		System.exit(0);
    	}

		//closing the connection
    	try {
			conn.close();
			//System.out.println("Connection Closed.");
		} catch(Exception e) {
			System.out.println("Connection NOT Closed.");
		}
	}
}