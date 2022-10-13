import java.sql.*;
import java.io.*;   
import java.util.Random;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
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
	String sql_output = "";

  	jdbcpostgreSQL(String database, String inventory, String start, String end, String entree, String protein, String side) {

		if (database == "cabo_grill_sales") {
			//Select All
			if (entree == "All" && protein == "All" && side == "All") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales;";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales;";

				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE date BETWEEN '" + start + "' AND '" + end + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select All Entrees
    		if (entree == "All" && protein == "None" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'entree'";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE type = 'entree';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'entree' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE type = 'entree' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select All Protein
    		if (entree == "None" && protein == "All" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'protein'";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE type = 'protein';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'protein' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE type = 'protein' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Entree
    		if (entree != "All" && protein == "None" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE entree_type = '" + entree + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE entree_type = '" + entree + "';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE entree_type = '" + entree + "' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE entree_type = '" + entree + "' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Protein
			if (entree == "None" && protein != "All" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE protein = '" + protein + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE protein = '" + protein + "';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE protein = '" + protein + "' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE protein = '" + protein + "' AND WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Side
    		if (entree == "None" && protein == "None" && side != "All") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE " + side + " = 1;";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE " + side + " = 1;";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE " + side  + " = 1 AND WHERE date BETWEEN '" + start + "' AND '" + end + "'";
					this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales WHERE " + side  + " = 1 AND WHERE date BETWEEN '" + start + "' AND '" + end + "'";
				}
			}
		}

		if (database == "cabo_grill") {
			if (entree == "None" && protein == "None" && side == "None") {
				if (inventory == "All") {
					this.statement = "SELECT * FROM cabo_grill;";
				} else {
					this.statement = "SELECT * FROM cabo_grill WHERE type = " + "'" + inventory + "'";
				}
			}
		}

		/*
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
    	System.out.println("Opened database successfully");


    	try {
        	Statement stmt = conn.createStatement();
        	//stmt.executeUpdate(statement);
			//ResultSet rs = stmt.executeQuery(statement);
    	} catch (Exception e){
    		e.printStackTrace();
    		System.err.println(e.getClass().getName()+": "+e.getMessage());
    		System.exit(0);
    	}
    	//closing the connection
    	try {
      		conn.close();
      		System.out.println("Connection Closed.");
    	} catch(Exception e) {
      		System.out.println("Connection NOT Closed.");
    	}
		*/
  	}
	public static void main(String[] args) {}


	/*
    String[] entree = new String[]{"burrito", "bowl", "tacos"};
    String[] protein = new String[]{"chicken", "steak", "beef", "vegetable medley"};
    Double[] protein_prices = new Double[]{8.50, 8.89, 8.79, 7.89};

    Random random_num = new Random();
    int num_entries = 20;
    int count = 0;
    int month = 1;
    int day = 1;
    int year = 2022;

    for (int i = 0; i < num_entries; i++) {
      if (count == 25) {
        day += 1;
        count = 0;
      }
      int sale_id = 1000 + i;
      String date =  year + "-" + month + "-" + day;
      int entree_index = random_num.nextInt(entree.length); //[0, entree.length)
      int protein_index = random_num.nextInt(protein.length);
      int chips_and_queso = random_num.nextInt(2);
      int chips_and_salsa = random_num.nextInt(2);
      int chips_and_guac = random_num.nextInt(2);
      int num_drinks = random_num.nextInt(2);

      double total_price = protein_prices[protein_index] + chips_and_queso*3.49 + chips_and_salsa*2.19 + chips_and_guac*3.69 + num_drinks*2.45;
      double rounded_total = Math.round(total_price * 100.0) / 100.0;

      String sql_entry = "INSERT INTO cabo_grill_sales (sale_id, date, entree_type, protein, chips_and_salsa, chips_and_queso, chips_and_guac, drink, cost) VALUES('"+
      sale_id + "', '" + date + "', '" + entree[entree_index] + "', '" + protein[protein_index] + "', " + 
      chips_and_salsa + ", " + chips_and_queso + ", " + chips_and_guac  + ", " + num_drinks + ", " + rounded_total +")\n";

      //Print each entry
      System.out.print(sql_entry);

      try {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql_entry);
      } 
      catch (Exception e){
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }
      count += 1;
    }
  
    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } 
    catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
  //end main
  }
  */
}
//end Class
