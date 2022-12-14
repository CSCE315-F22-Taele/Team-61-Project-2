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

    /**
     * Order constructor that takes in the value of a sale ID 
     * @param saleid an integer value that represents the sale ID of an order 
     */
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

    /**
     * The base constructor for the Order class that creates a base order to be used to insert orders into the database.
     */
    Order(){
        Date temp_date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  

        Connection conn = new connectionSetup().conn;
        
        try {
            Statement stmt = conn.createStatement(); 
            String sqlQuery = "SELECT MAX(sale_id) from cabo_grill_sales;";
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result.next(); 
            Sale_Id = result.getInt("max") + 1; 
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        
        date = formatter.format(temp_date); 
        Sides = new ArrayList<Tuple>(); 
        Entree = ""; 
        Protein = "";
        cost = 0; 
    }

    /**
     * This method is used to add a side to the order
     * @param side A string variable that represents the side to be added to the order
     */
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

    /**
     * A method that is used to update an indivudal order's price.
     */
    public void update_cost_individual(){
        cost = 0; 

        Connection conn = new connectionSetup().conn;

        try {
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
