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

/**
 * This class provides the basis for the Excess Report Page within the Manager view of the POS. The excess report page
 * reports the items that only sold less than 10% of their inventory between the given timestamp and current time.
 * @author Sam Brokaw
 */
public class Excess_Report_Page {
    JFrame f = new JFrame("Excess Report Page"); 
    /**
     * The constructor completes all of the queries to access the excess report and outputs all of the items that didn't sell less than 10%
     * of their inventory to a textbox.
     */
    Excess_Report_Page(){  
        JPanel panel= new JPanel();  
        panel.setBounds(10,10,1000,600);    
        panel.setBackground(Color.lightGray); 

        JButton b1 = new JButton("Back To Home Page");     
        b1.setBounds(50,50,150,30);    
        b1.addActionListener(new ActionListener() { 
            /**
             * This function changes the "Back To Home Page" button green after it is pressed
             * @param e represents the click of the button  
             */ 
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
            /**
             * This function executes the queries for the excess report when the submit button is pressed.
             * @param e represents the click of the button
             */
            public void actionPerformed(ActionEvent e) {
                String beginDate = "";
                ArrayList<String> excess = new ArrayList<String>();
                if (!date.getText().isEmpty()) {
                    beginDate = date.getText();
                }
                queryLabel.setText("");
             
                Connection conn = new connectionSetup().conn;

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