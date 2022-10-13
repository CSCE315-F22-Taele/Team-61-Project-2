import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Sides_Page {
    JFrame f = new JFrame("Clerk Sides Page");
    int chips_salsa = 0;
    int chips_queso = 0;
    int chips_guac = 0;
    int drinks = 0; 
    float cost = 0; 
    Clerk_Sides_Page(Vector<Order> orders, Inventory inventory, boolean additional_entree){  
       //JFrame f= new JFrame("Panel Example");
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       orders.lastElement().update_cost();
       JButton home_page_button =new JButton("Back To Clerk Home Page");
       JButton chips_salsa_button = new JButton("Chips and Salsa: " + chips_salsa); 
       JButton chips_guac_button = new JButton("Chips and Guac: " + chips_guac ); 
       JButton chips_queso_button = new JButton("Chips and Queso: " + chips_queso); 
       JButton drink_button = new JButton("Drink: " + drinks); 
       JButton more_food = new JButton("More Food"); 
       JButton pay_button = new JButton("Pay Now: " + orders.lastElement().cost); 
       home_page_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                orders.remove(orders.size() -1);
                new Clerk_Home_Page(orders, inventory, false); 
                f.dispose(); 
            }  
        }); 
        chips_salsa_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_salsa += 1; 
                chips_salsa_button.setText("chips and Salsa: " + chips_salsa);
                orders.lastElement().update_cost();
                pay_button.setText("Pay Now: " + orders.lastElement().cost); 
            }  
        }); 
        chips_guac_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_guac += 1; 
                chips_guac_button.setText("Chips and Guac: " + chips_guac);
                orders.lastElement().update_cost();
                pay_button.setText("Pay Now: " + orders.lastElement().cost); 
            }  
        }); 
        chips_queso_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_queso += 1; 
                chips_queso_button.setText("Chips and Queso: " + chips_queso);
                orders.lastElement().update_cost();
                pay_button.setText("Pay Now: " + orders.lastElement().cost); 
            }  
        }); 
        drink_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                drinks += 1; 
                drink_button.setText("Drinks: " + drinks);
                orders.lastElement().update_cost();
                pay_button.setText("Pay Now: " + orders.lastElement().cost); 
            }  
        }); 
        more_food.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                orders.lastElement().guac = chips_guac; 
                orders.lastElement().salsa = chips_salsa;
                orders.lastElement().queso = chips_queso; 
                orders.lastElement().drink = drinks; 
                new Clerk_Home_Page(orders, inventory, true); 
                f.dispose(); 
            }  
        }); 
        pay_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                new Clerk_Home_Page(orders, inventory, additional_entree);
                f.dispose(); 
            }
        }); 
       panel.add(pay_button); 
       panel.add(home_page_button); 
       panel.add(chips_salsa_button);
       panel.add(chips_guac_button);
       panel.add(chips_queso_button);
       panel.add(drink_button); 
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