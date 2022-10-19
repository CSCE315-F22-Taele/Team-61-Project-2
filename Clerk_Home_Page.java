import java.awt.*;
import java.sql.*; 
import javax.accessibility.AccessibleRelationSet;
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 
/**
 * This class consists of the components that make up the clerks point of view in the point-of-sale system.
 * @author Roee Belkin, Sam Brokaw
 */
public class Clerk_Home_Page {
    JFrame f = new JFrame("Clerk Home Page"); 
    /**
     * This constructor creates the layout for the clerk home page with buttons to take the customer's order
     * @param orders            the vector of orders taken from the Clerk-side view
     * @param inventory         a vector of tuples that stores the items in the inventory
     * @param entrees           a vector of strings that contains the 3 different entree types (bowl, burrito, tacos)
     * @param protein           a vector of strings that contains the 4 different protein options
     * @param sides             a vector of strings that contains the side items
     * @param additional_entree a boolean variable where 1 represents the customer is ordering another entree and 0 means no more entrees
     */
    Clerk_Home_Page(Vector<Order> orders, ArrayList<Tuple> inventory, ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides, boolean additional_entree){  
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);    
       JButton home_page_button =new JButton("Back To Home Page");
       JButton close_out_button = new JButton("Close Out"); 
        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    home_page_button.setBackground(Color.green);  
                        new Home_Page(); 
                        f.dispose(); 
                    }  
            }); 
        panel.add(home_page_button);  
        for (String temp_entree : entrees){
            if (temp_entree != "all" && temp_entree != "ALL" && temp_entree != "All" && temp_entree != "NONE" && temp_entree != "None" && temp_entree != "none"){
                JButton temp_button = new JButton(temp_entree); 
                temp_button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Order current_order; 
                        if (additional_entree){
                            current_order = new Order(orders.lastElement().Sale_Id); 
                        }
                        else{
                            current_order = new Order(); 
                        }
                        current_order.Entree = temp_entree; 
                        orders.add(current_order); 
                        new Clerk_Protein_Page(orders, inventory, entrees, protein, sides, additional_entree); 
                        f.dispose();
                    }
                });
                panel.add(temp_button); 
            }
        }
        close_out_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
         
                Connection conn = new connectionSetup().conn;

                for (Order order: orders){
                    try{
                        Statement stmt = conn.createStatement(); 
                        String sqlQuery = "INSERT INTO cabo_grill_sales VALUES (" + order.Sale_Id + ",'" + order.date + "','" + order.Entree + "','" + order.Protein + "', 0, 0, 0, 0, " + order.cost + " );"; 
                        stmt.executeUpdate(sqlQuery); 
                        for (Tuple side : order.Sides){
                            sqlQuery = "UPDATE cabo_grill_sales set " + side.side_name + " = " + side.amount + "where sale_id = " + order.Sale_Id + "AND protein = '" + order.Protein + "' AND entree_type = '" + order.Entree + "';"; 
                            stmt.executeUpdate(sqlQuery); 
                        }
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                        System.exit(0);
                    }
                }
                for (Tuple item : inventory){
                    try{
                        Statement stmt = conn.createStatement();
                        String sqlQuery = "select quantity from cabo_grill where item_name = '" + item.side_name + "';";
                        ResultSet result = stmt.executeQuery(sqlQuery); 
                        int current_count = 0; 
                        result.next(); 
                        current_count = result.getInt("quantity"); 
                        current_count = current_count - item.amount; 
                        sqlQuery = "Update cabo_grill set quantity = " + current_count + " where item_name = '" + item.side_name + "';"; 
                        stmt.executeUpdate(sqlQuery); 
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                        System.exit(0);
                    }
                }
            }
        });

       panel.add(close_out_button); 
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);  
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       }  

       /**
        * This method is used to close the instance of the GUI window so that the user doesn't have to close it manually through 
        * the terminal.
        * @param e a WindowEvent object that indicates the status of the java.swing window
        */
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    
}
