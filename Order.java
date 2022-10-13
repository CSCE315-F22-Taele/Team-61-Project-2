import java.sql.*; 
import java.io.*;
import java.util.*;
import java.time.*;    
import java.text.*; 
import java.util.Date;

import javax.security.sasl.Sasl;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Order {
    int Sale_Id; 
    String date; 
    String Entree; 
    String Protein;
    int salsa;
    int guac; 
    int queso;
    int drink;
    float cost; 

    Order(int saleid){
        Date temp_date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        date = formatter.format(temp_date);
        Entree = ""; 
        Protein = "";
        salsa = 0;
        guac = 0;
        queso = 0;
        drink = 0; 
        cost = 0; 
        Sale_Id = saleid; 
    }

    Order(){
        Date temp_date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

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
        System.out.println("Opened database successfully");
        try{
            Statement stmt = conn.createStatement(); 
            String sqlQuery = "SELECT MAX(sale_id) from cabo_grill_sales;";
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result.next(); 
            Sale_Id = result.getInt("max") + 1; 
            System.out.println(Sale_Id);
        }
        catch (Exception e){
            e.printStackTrace();
            //System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        date = formatter.format(temp_date);
        Entree = ""; 
        Protein = "";
        salsa = 0;
        guac = 0;
        queso = 0;
        drink = 0; 
        cost = 0; 
    }

    public void update_cost(){
        cost = 0; 
        if (Protein == "Chicken" && Entree == "Taco"){
            cost += 7.89;
        }
        else if (Protein == "Chicken"){
            cost += 8.5; 
        }
        else if (Protein == "Steak"){
            cost += 8.89;
        }
        else if (Protein == "Beef"){
            cost += 8.79; 
        }
        else if (Protein == "Vegetable"){
            cost += 7.89; 
        }
        cost += 2.19 * salsa;
        cost += 3.49 * queso;
        cost += 3.69 * guac; 
        cost += 2.45 * drink; 
    }

    public void print(){
        System.out.println(Sale_Id + ", " + date + ", " + Entree + ", " + Protein + ", " + salsa + ", " + queso + ", " + guac + ", " + drink + "," + cost);
    }
}
