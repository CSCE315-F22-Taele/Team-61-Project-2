import java.sql.*; 
import java.io.*;
import java.util.*;
import java.time.*;    
import java.text.*; 
import java.util.Date;
import javax.security.sasl.Sasl;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This class is used to aid in creating orders to be inserted into the database. A proper order will consist of 6 main attributes: Sale ID, date,
 * entree, protein, sides, and the total cost. 
 * @author Roee Belkin, Sam Brokaw
 */
public class Order {
    int Sale_Id; 
    String date; 
    String Entree; 
    String Protein;
    ArrayList<Tuple> Sides; 
    float cost; 

    Order(int saleid){
        Date temp_date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        date = formatter.format(temp_date);
        Entree = ""; 
        Protein = "";
        Sides = new ArrayList<Tuple>(); 
        cost = 0; 
        Sale_Id = saleid; 
    }

    Order(){
        Date temp_date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  

        // connecting to our data base to get sale id start number
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
            Statement stmt = conn.createStatement(); 
            String sqlQuery = "SELECT MAX(sale_id) from cabo_grill_sales;";
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result.next(); 
            Sale_Id = result.getInt("max") + 1; 
        }
        catch (Exception e){
            e.printStackTrace();
            //System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        date = formatter.format(temp_date); 
        //date = "2022-12-12"; //needs to be changed to accomodate for proper date
        Sides = new ArrayList<Tuple>(); 
        Entree = ""; 
        Protein = "";
        cost = 0; 
    }


    public void add_side(String side){
        boolean found = false; 
        for (int i = 0; i < Sides.size(); i++){
            if (Sides.get(i).side_name == side){
                Sides.get(i).amount++; 
                found = true; 
            }
        }
        if (!found){
            Sides.add(new Tuple(side, 1)); 
        }


    }

    public void update_cost_individual(){
        cost = 0; 

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
            String sqlQuery = "Select sale_cost from cabo_grill where item_name = '" + Protein + "';"; 
            Statement stmt = conn.createStatement(); 
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result = stmt.executeQuery(sqlQuery); 
            result.next(); 
            cost += result.getFloat("sale_cost"); 
            if (Sides != null){
                for (Tuple Side : Sides){
                    String temp_side = Side.side_name; 
                    float item_cost; 
                    sqlQuery = "SELECT sale_cost from cabo_grill where item_name = '" + temp_side + "';";
                    result = stmt.executeQuery(sqlQuery); 
                    while(result.next()){
                        item_cost = result.getFloat("sale_cost"); 
                        cost += item_cost * Side.amount; 
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

}
