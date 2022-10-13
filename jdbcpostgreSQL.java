import java.io.*;   
import java.util.Random;
import java.util.ArrayList;
import java.sql.*;

/*
CSCE 331
9-28-2022 Lab
*/
// @author Justin, Brandon, Sam, Roee
public class jdbcpostgreSQL {
    static final String DB_URL = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce331_905_61";
    static final String USER = "csce331_905_brandon";
    static final String PASS = "ParkCity92?2";
  	String statement = "";

  	jdbcpostgreSQL(String database, String start, String end, String entree, String protein, String side) {

		if (database == "cabo_grill_sales") {
			//Select All
			if (entree == "All" && protein == "All" && side == "All") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales;";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select All Entrees
    		if (entree == "All" && protein == "None" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'entree'";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'entree' AND date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select All Protein
    		if (entree == "None" && protein == "All" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'protein'";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE type = 'protein' AND date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Entree
    		if (entree != "All" && protein == "None" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE entree_type = '" + entree + "';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE entree_type = '" + entree + "' AND date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Protein
			if (entree == "None" && protein != "All" && side == "None") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE protein = '" + protein + "';";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE protein = '" + protein + "' AND date BETWEEN '" + start + "' AND '" + end + "';";
				}
			}

			//Select all with Particular Side
    		if (entree == "None" && protein == "None" && side != "All") {
				if (start == "" && end == "") {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE " + side + " = 1;";
				} else {
					this.statement = "SELECT * FROM cabo_grill_sales WHERE " + side  + " = 1 AND date BETWEEN '" + start + "' AND '" + end + "'";
				}
			}
		}
    
      // Connect to database
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(this.statement);) {
          // Extract data from result set
          while (rs.next()) {
              // Output each column to terminal from cabo_grill_sales table
              System.out.print("ID: " + rs.getInt("sale_id"));
              System.out.print(", DATE: " + rs.getDate("date"));
              System.out.print(", ENTREE TYPE: " + rs.getString("entree_type"));
              System.out.print(", PROTEIN: " + rs.getString("protein"));
              System.out.print(", CHIPS & SALSA: " + rs.getInt("chips_and_salsa"));
              System.out.print(", CHIPSA & QUESO: " + rs.getInt("chips_and_queso"));
              System.out.print(", CHIPS & GUAC: " + rs.getInt("chips_and_guac"));
              System.out.print(", DRINK: " + rs.getInt("drink"));
              System.out.println(", COST: " + rs.getFloat("cost"));
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
    
  	}


  	//Commands to run this script
  	//This will compile all java files in this directory
  	//javac *.java
  	//This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code

  	/* DON"T COPY PASTE WRITE THE COMMANDS IN YOUR TERMINAL MANUALLY*/

  	//Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
  	//Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

  	//MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE

  	/* 
  	@param none
  	@return none
 	@throws Exception when database doesn't connect, when database's connection doesn't close, when update statement doesn't execute
  	*/


	/*
  	public static void main(String args[]) {
    
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
        	stmt.executeUpdate(statement);
    	} 
    	catch (Exception e){
    		e.printStackTrace();
    		System.err.println(e.getClass().getName()+": "+e.getMessage());
    		System.exit(0);
    	}
    	//closing the connection
    	try {
      		conn.close();
      		System.out.println("Connection Closed.");
    	} 
    	catch(Exception e) {
      		System.out.println("Connection NOT Closed.");
    	}//end try catch
	}
	*/






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
