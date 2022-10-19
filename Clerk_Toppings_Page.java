import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

/**
 * This class creates the layout for the clerk-side toppings page. Once on this page, you will see numerous options to select
 * the type of order the customer requests.
 * @author Roee Belkin, Sam Brokaw
 */
public class Clerk_Toppings_Page {
    JFrame f = new JFrame("Clerk Toppings Page");
    /**
     * The constructor is where all of the layout buttons are created and modified.
     * @param orders     a vector of orders that stores the customer's orders
     * @param inventory  a vector of tuples that contains the inventory of items
     * @param entrees    a vector of strings that contains the options available for the entrees
     * @param protein    a vector of strings that contains the options available for proteins
     * @param sides      a vector of strings that contains the options available for the sides
     * @param additional_entree a boolean value that represents whether or not the customer decides to get another order
     */
    Clerk_Toppings_Page(Vector<Order> orders, ArrayList<Tuple> inventory, ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides, boolean additional_entree){  
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       JButton home_page_button =new JButton("Back To Clerk Home Page");
       JButton lettuce_button = new JButton("Lettuce"); 
       JButton cheese_button = new JButton("Cheese"); 
       JButton tomatoes_button = new JButton("Tomatoes"); 
       JButton pico_button = new JButton("Pico De Gallo"); 
       JButton corn_button = new JButton("Corn"); 
       JButton sour_cream_button = new JButton("Sour Cream"); 
       JButton continue_button = new JButton("Continue"); 
        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    orders.remove(orders.size() -1);
                    new Clerk_Home_Page(orders, inventory, entrees, protein, sides, additional_entree); 
                    f.dispose(); 
                }  
            }); 
        lettuce_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (lettuce_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("lettuce")){
                            item.amount++; 
                        }
                    }
                    lettuce_button.setBackground(null);     
                    lettuce_button.setOpaque(true);
                    lettuce_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("lettuce")){
                            item.amount++; 
                        }
                    }
                    lettuce_button.setBackground(Color.green);     
                    lettuce_button.setOpaque(true);
                    lettuce_button.setBorderPainted(false);
                }
            }
        });
        cheese_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (cheese_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("cheese")){
                            item.amount++; 
                        }
                    }
                    cheese_button.setBackground(null);     
                    cheese_button.setOpaque(true);
                    cheese_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("cheese")){
                            item.amount++; 
                        }
                    }
                    cheese_button.setBackground(Color.green);     
                    cheese_button.setOpaque(true);
                    cheese_button.setBorderPainted(false);
                }
            }
        });
        tomatoes_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (tomatoes_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("tomatoes")){
                            item.amount++; 
                        }
                    }
                    tomatoes_button.setBackground(null);     
                    tomatoes_button.setOpaque(true);
                    tomatoes_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("tomatoes")){
                            item.amount++; 
                        }
                    }
                    tomatoes_button.setBackground(Color.green);     
                    tomatoes_button.setOpaque(true);
                    tomatoes_button.setBorderPainted(false);
                }
            }
        });
        pico_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (pico_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("pico de gallo")){
                            item.amount++; 
                        }
                    }
                    pico_button.setBackground(null);     
                    pico_button.setOpaque(true);
                    pico_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("pico de gallo")){
                            item.amount++; 
                        }
                    }
                    pico_button.setBackground(Color.green);     
                    pico_button.setOpaque(true);
                    pico_button.setBorderPainted(false);
                }
            }
        });
        corn_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (corn_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("corn")){
                            item.amount++; 
                        }
                    }
                    corn_button.setBackground(null);     
                    corn_button.setOpaque(true);
                    corn_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("corn")){
                            item.amount++; 
                        }
                    }
                    corn_button.setBackground(Color.green);     
                    corn_button.setOpaque(true);
                    corn_button.setBorderPainted(false);
                }
            }
        });
        sour_cream_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (sour_cream_button.getBackground() == Color.green){
                    for (Tuple item : inventory){
                        if (item.side_name.equals("sour cream")){
                            item.amount++; 
                        }
                    }
                    sour_cream_button.setBackground(null);     
                    sour_cream_button.setOpaque(true);
                    sour_cream_button.setBorderPainted(false);
                }
                else{
                    for (Tuple item : inventory){
                        if (item.side_name.equals("sour cream")){
                            item.amount++; 
                        }
                    }
                    sour_cream_button.setBackground(Color.green);     
                    sour_cream_button.setOpaque(true);
                    sour_cream_button.setBorderPainted(false);
                }
            }
        });
        continue_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new Clerk_Sides_Page(orders, inventory, entrees, protein, sides, additional_entree); 
                f.dispose(); 
            }
        });


       panel.add(home_page_button);  
       panel.add(lettuce_button); 
       panel.add(cheese_button);
       panel.add(tomatoes_button);
       panel.add(pico_button);
       panel.add(corn_button);
       panel.add(sour_cream_button);
       panel.add(continue_button);
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);    
       }  
       
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    

}
