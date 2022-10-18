import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

import java.sql.*; 
import java.io.*;
import java.util.*;
import java.time.*;    
import java.text.*; 

import javax.security.sasl.Sasl;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Clerk_Sides_Page {

    //function deals with getting cost when we have multiple entrees under the same persons bill, so same sale_id. 
    public float sale_id_cost(Vector<Order> orders){
        float return_cost = 0; 
        //orders.lastElement().update_cost_individual();
        return_cost = orders.lastElement().cost; 
        for (int i = 0; i < orders.size() -1; i++){
            if (orders.get(i).Sale_Id == orders.lastElement().Sale_Id){
                return_cost += orders.get(i).cost; 
            }
        }
        return return_cost; 
    }


    JFrame f = new JFrame("Clerk Sides Page");
    float total_cost = 0; 
    Clerk_Sides_Page(Vector<Order> orders, ArrayList<Tuple> inventory, ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides, boolean additional_entree){  
       
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       orders.lastElement().update_cost_individual();
       total_cost = sale_id_cost(orders); 
       JButton home_page_button =new JButton("Back To Clerk Home Page");
       JButton more_food = new JButton("More Food"); 
       JButton pay_button = new JButton("Pay Now: " + total_cost); 
       home_page_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                orders.remove(orders.size() -1);
                new Clerk_Home_Page(orders, inventory, entrees, protein, sides, false); 
                f.dispose(); 
            }  
        }); 

        for (String temp_side : sides){
            if (temp_side != "all" && temp_side != "ALL" && temp_side != "All" && temp_side != "NONE" && temp_side != "None" && temp_side != "none"){
                JButton temp_button = new JButton(temp_side); 
                temp_button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        for (Tuple item : inventory){
                            if (item.side_name.equals(temp_side)){
                                item.amount++; 
                            }
                        }
                        orders.lastElement().add_side(temp_side);
                        orders.lastElement().update_cost_individual();
                        total_cost = sale_id_cost(orders); 
                        pay_button.setText("Pay Now: " + total_cost);
                    }
                });
                panel.add(temp_button); 
            }
        }


        more_food.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                orders.lastElement().update_cost_individual();
                new Clerk_Home_Page(orders, inventory, entrees, protein, sides, true); 
                f.dispose(); 
            }  
        }); 
        pay_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                new Clerk_Home_Page(orders, inventory, entrees, protein, sides, additional_entree);
                f.dispose();
            }
        }); 
        pay_button.setText("Pay Now: " + total_cost);
        panel.add(pay_button); 
        panel.add(home_page_button); 
        panel.add(more_food); 
        panel.add(pay_button); 
        f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);    
       }  
       
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    
    //    public static void main(String args[])  
    //    {  
    //    new Clerk_Protein_Page();  
    //    }  
}
