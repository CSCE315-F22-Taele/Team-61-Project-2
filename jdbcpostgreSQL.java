import java.sql.*;
import java.io.*;   
import java.util.Random;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class's main purpose is to be utilized by the manager home page
 * to execute specific queries to view the inventory and sales report from the database. 
 * @author Justin Singletary, Brandon Moon
 */
public class jdbcpostgreSQL {

  	String statement = "";
	String totalAmount = "";
	String count = "";
	String sql_output = "";
	double total_output = 0.0;
	String count_output = "Count: ";

	/**
	 * This constructor is used for the manager home page's queries. It mainly uses select statements to access specific values from the database.
	 * @author Justin Singletary
	 * @param  table          the name of table that will be queried
	 * @param  inventory_item the type of item to be selected in the table (protein, side, topping, etc.)
	 * @param  start          the beginning date to start the query
	 * @param  end            the end date to end the query time interval
	 * @param  entree         the entree that is selected from the list (bowl, taco, burrito, etc.)
	 * @param  protein		  the protein option that is ordered (chicken, steak, beef, etc.)
	 * @param  side 		  the side that is ordered with the entree (chips, salsa, guac, etc.)
	 */
  	jdbcpostgreSQL(String table, String inventory_item, String start, String end, String entree, String protein, String side) {

		boolean whereUsed = false;

		if (table == "Sales Report") {

			this.statement = "SELECT * FROM cabo_grill_sales";
			this.totalAmount = "SELECT SUM(cost) FROM cabo_grill_sales";
			this.count = "SELECT COUNT(*) FROM cabo_grill_sales";
			String additionalCriteria = "";

			if (start != "" && end != "") {
				additionalCriteria += " WHERE date BETWEEN '" + start + "' AND '" + end + "'";
				whereUsed = true;
			}
			if (entree != "NONE") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " entree_type = '" + entree + "'";
			}
			if (protein != "NONE") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " protein = '" + protein + "'";
			}
			if (side != "NONE") {
				if (!whereUsed) {
					additionalCriteria += " WHERE";
					whereUsed = true;
				} else {
					additionalCriteria += " AND";
				}
				additionalCriteria += " " + side + " = 1";
			}
			this.statement += additionalCriteria + ";";
			this.totalAmount += additionalCriteria + ";";
			this.count += additionalCriteria + ";";
		}

		if (table == "Inventory") {
			if (inventory_item == "All") {
				this.statement = "SELECT * FROM cabo_grill ORDER BY id ASC;";
			} else {
				this.statement = "SELECT * FROM cabo_grill WHERE type = " + "'" + inventory_item + "' ORDER BY id ASC;";
			}
		}
		
		Connection conn = new connectionSetup().conn;

    	try {
        	Statement stmt = conn.createStatement();
        	stmt.execute(statement);
			ResultSet rs = stmt.executeQuery(statement);
			if (table == "Sales Report") {
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
					sql_output += ("    " + id + " " + date + " " + entree_type + " " + protein_type + " " + chips_and_salsa + " " + chips_and_queso + " " + chips_and_guac + " " + drink + " " + cost +"\n");
				}		

				Statement stmt2 = conn.createStatement();
        		stmt2.execute(totalAmount);
				ResultSet rs2 = stmt2.executeQuery(totalAmount);
				while (rs2.next()) {
					total_output += rs2.getDouble(1);
				}	

				Statement stmt3 = conn.createStatement();
				stmt3.execute(count);
				ResultSet rs3 = stmt3.executeQuery(count);
				while (rs3.next()) {
					count_output += rs3.getString(1);
				}			
			}
			
			if (table == "Inventory") {
				while (rs.next()) {
					String id = rs.getString(1);
					String item_name = rs.getString(2);
					String type = rs.getString(3);
					String quantity = rs.getString(4);
					String sufficient_supply = rs.getString(5);
					double sale_cost = rs.getDouble(6);
					int minimum_supply = rs.getInt(7);
					sql_output += ("    " + id + " " + item_name + " " + type + " " + quantity + " " + sufficient_supply + " " + sale_cost + " " + minimum_supply + "\n");
				}

				count = "SELECT COUNT(*) FROM cabo_grill";
				if (inventory_item != "All") {
					count += " WHERE type = '" + inventory_item + "'";
				}
				count += ";";

				Statement stmt2 = conn.createStatement();
				stmt2.execute(count);
				ResultSet rs2 = stmt2.executeQuery(count);
				while (rs2.next()) {
					count_output += rs2.getString(1);
				}		
			}

    	} catch (Exception e){
			sql_output += " Error: Query failed.";
    	}
    	//closing the connection
    	try {
      		conn.close();
    	} catch(Exception e) {
			System.out.println("Connection NOT Closed.");
    	}
  	}

	/**
	 * This constructor is used for the update function of the manager home page. The constructor uses the UPDATE clause to create a statement
	 * that modifies the database's quantity and sufficient supply.
	 * @author Brandon Moon
	 * @param table  	  			the table within the database to be queried from 
	 * @param quantityAmt 			the new quantity of the item that you want to change
	 * @param sufficientSupplyValue the value for whether or not the item has a sufficient supply (0 or 1)
	 * @param itemID				the ID of the item that you want to modify
	 */
	jdbcpostgreSQL(String table, String quantityAmt, String sufficientSupplyValue, String itemID) {
	
		Connection conn = new connectionSetup().conn;

		// Try Except block executes UPDATE query
		try {
			Statement stmt = conn.createStatement();
			String sql_query = "";
			if (table == "inventory") {
				sql_query = "UPDATE cabo_grill SET quantity = " + quantityAmt + ", sufficient_supply = '" + sufficientSupplyValue + "' WHERE id = " + itemID;
				stmt.executeUpdate(sql_query);		
			}

		} catch (Exception e){
    		sql_output += " Error: Update failed.";
    	}

		//closing the connection
    	try {
			conn.close();
		} catch(Exception e) {
			System.out.println("Connection NOT Closed.");
		}
	}


	/**
	 * This constructor is used for the Restock Report and Update Inventory Supply 
	 * @author Justin Singletary
	 * @param inventoryFunction  	function to be executed on Inventory table
	 */
	jdbcpostgreSQL(String inventoryFunction) {

		Connection conn = new connectionSetup().conn;

		// Try Except block executes UPDATE query
		try {
			Statement stmt = conn.createStatement();
			String sql_query = "";

			if (inventoryFunction == "Restock Report") {
				statement = "SELECT * FROM cabo_grill WHERE sufficient_supply = '0' ORDER BY id ASC;";
        		stmt.execute(statement);
				ResultSet rs = stmt.executeQuery(statement);
				while (rs.next()) {
					String id = rs.getString(1);
					String item_name = rs.getString(2);
					String type = rs.getString(3);
					String quantity = rs.getString(4);
					String sufficient_supply = rs.getString(5);
					double sale_cost = rs.getDouble(6);
					int minimum_supply = rs.getInt(7);
					sql_output += ("    " + id + " " + item_name + " " + type + " " + quantity + " " + sufficient_supply + " " + sale_cost + " " + minimum_supply + "\n"); 
				}

				statement = "SELECT COUNT(*) FROM cabo_grill WHERE sufficient_supply = '0';";
				stmt.execute(statement);
				rs = stmt.executeQuery(statement);
				while(rs.next()) {
					count_output += rs.getString(1);
				}
			}

			if (inventoryFunction == "Update Inventory Supply") {
                statement = "update cabo_grill set sufficient_supply = '0' where quantity < minimum_supply;";
                stmt.executeUpdate(statement); 
                statement = "update cabo_grill set sufficient_supply = '1' where quantity > minimum_supply;";
                stmt.executeUpdate(statement);
				sql_output += "Inventory Updated";
            }
        
		} catch (Exception e){
    		sql_output += " Error: Restock report failed.";
    	}

		//closing the connection
    	try {
			conn.close();
		} catch(Exception e) {
			System.out.println("Connection NOT Closed.");
		}
	}
}