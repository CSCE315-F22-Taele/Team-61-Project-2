import java.util.*; 
import java.sql.*; 

/**
 * The inventory class is used to query the database and output all columns in the inventory table
 * @author Roee Belkin
 */
public class Inventory {
    public ArrayList<Tuple> items; 

    /**
     * This default constructor establishes the queries used to access the inventory items and adds the item names
     * into the items array
     */
    Inventory(){
        items = new ArrayList<Tuple>(); 
        Connection conn = null;
        String teamNumber = "61"; // Your team number
        String sectionNumber = "905"; // Your section number
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup(); 

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try{
            String sqlQuery = "Select item_name from cabo_grill where id > 0;";
            Statement stmt = conn.createStatement(); 
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result = stmt.executeQuery(sqlQuery); 
            while (result.next()){
                items.add(new Tuple(result.getString("item_name"), 0));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}

