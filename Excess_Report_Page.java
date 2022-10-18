import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

import java.sql.*;
import java.io.*;   
import java.util.Random;
import java.util.ArrayList;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Excess_Report_Page {
    JFrame f = new JFrame("Excess Report Page"); 
    Excess_Report_Page(){  
        JPanel panel= new JPanel();  
        panel.setBounds(10,10,1000,600);    
        panel.setBackground(Color.lightGray); 

        JButton b1 = new JButton("Back To Home Page");     
        b1.setBounds(50,50,150,30);    
        b1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                new Home_Page(); 
                f.dispose(); 
            }  
        }); 
        f.add(b1);

        JLabel itemIDTextLabel = new JLabel("Enter start date");
        itemIDTextLabel.setBounds(450, 50, 100, 30);
        f.add(itemIDTextLabel);

        JTextArea date = new JTextArea();  
        date.setBounds(440, 80, 100, 20);   
        f.add(date);

        JTextArea queryLabel = new JTextArea();
        queryLabel.setBounds(200, 200, 600, 500);
        f.add(queryLabel);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(440, 110, 100, 30);
        f.add(submitButton);
        
        ArrayList<String> chips = new ArrayList<String>() { 
            { 
                add("chips_and_guac"); 
                add("chips_and_salsa"); 
                add("chips_and_queso"); 
            } 
        };

        ArrayList<String> protein = new ArrayList<String>() { 
            { 
                add("chicken"); 
                add("beef"); 
                add("steak");
                add("vegetable medley");
            } 
        };

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String beginDate = "";
                ArrayList<String> excess = new ArrayList<String>();
                if (!date.getText().isEmpty()) {
                    beginDate = date.getText();
                }
                queryLabel.setText("");
                Connection conn = null;
                String teamNumber = "61"; // Your team number
                String sectionNumber = "905"; // Your section number
                String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
                String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
                dbSetup myCredentials = new dbSetup(); 

                try {
                    conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getClass().getName()+": "+ex.getMessage());
                    System.exit(0);
                }
                //System.out.println("Opened database successfully");
                for(String s : chips){
                    try{
                        Statement stmt = conn.createStatement(); 
                        String sqlQuery = "select count(" + s + ") from cabo_grill_sales where date > \'" + beginDate + "\';";
                        ResultSet result = stmt.executeQuery(sqlQuery); 
                        result.next();
                        int sold =  ((Number) result.getObject("count")).intValue();

                        String sqlQuery2 = "select quantity from cabo_grill where item_name = \'"+ s + "\';";
                        ResultSet result2 = stmt.executeQuery(sqlQuery2); 
                        result2.next();
                        int curr_quantity =  ((Number) result2.getObject("quantity")).intValue();
                        double sale_percent = (double)sold / (sold + curr_quantity);

                        if(sale_percent < 0.1){
                            excess.add(s);
                        }
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                        //System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }
                }

                for(String s : protein){
                    try{
                        Statement stmt = conn.createStatement(); 
                        String sqlQuery = "select count(protein) from cabo_grill_sales where date > \'" + beginDate + "\' and protein = \'" + s + "\';";
                        ResultSet result = stmt.executeQuery(sqlQuery); 
                        result.next();
                        int sold =  ((Number) result.getObject("count")).intValue();

                        String sqlQuery2 = "select quantity from cabo_grill where item_name = \'"+ s + "\';";
                        ResultSet result2 = stmt.executeQuery(sqlQuery2); 
                        result2.next();
                        int curr_quantity =  ((Number) result2.getObject("quantity")).intValue();
                        double sale_percent = (double)sold / (sold + curr_quantity);

                        if(sale_percent < 0.1){
                            excess.add(s);
                        }
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                        //System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }
                }

                queryLabel.setText("The items that did not sell 10% of their invetory since " + beginDate + " are:\n");
                for(String exc : excess){
                    queryLabel.append("\n" + exc);
                }
                
            }
        });
        f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);    
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }

}