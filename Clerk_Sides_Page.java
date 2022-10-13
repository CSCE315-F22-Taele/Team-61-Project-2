import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Sides_Page extends Clerk_Toppings_Page{
    JFrame f = new JFrame("Clerk Sides Page");
    int chips_salsa = 0;
    int chips_queso = 0;
    int chips_guac = 0;
    int drinks = 0; 
    float cost = 0; 
    Clerk_Sides_Page(boolean additional_entree){  
       //JFrame f= new JFrame("Panel Example");
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       main_cost_update();
       JButton home_page_button =new JButton("Back To Clerk Home Page");
       JButton chips_salsa_button = new JButton("Chips and Salsa: " + chips_salsa); 
       JButton chips_guac_button = new JButton("Chips and Guac: " + chips_guac ); 
       JButton chips_queso_button = new JButton("Chips and Queso: " + chips_queso); 
       JButton drink_button = new JButton("Drink: " + drinks); 
       JButton more_food = new JButton("More Food"); 
       JButton pay_button = new JButton("Pay Now: " + get_order_total()); 
       home_page_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                remove();
                new Clerk_Home_Page(false); 
                f.dispose(); 
            }  
        }); 
        chips_salsa_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_salsa += 1; 
                chips_salsa_button.setText("chips and Salsa: " + chips_salsa);
                setSalsa(chips_salsa);
                main_cost_update();
                main_print();
                pay_button.setText("Pay Now: " + get_order_total()); 
            }  
        }); 
        chips_guac_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_guac += 1; 
                chips_guac_button.setText("Chips and Guac: " + chips_guac);
                main_cost_update();
                pay_button.setText("Pay Now: " + get_order_total()); 
            }  
        }); 
        chips_queso_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                chips_queso += 1; 
                chips_queso_button.setText("Chips and Queso: " + chips_queso);
                main_cost_update();
                pay_button.setText("Pay Now: " + get_order_total()); 
            }  
        }); 
        drink_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                drinks += 1; 
                drink_button.setText("Drinks: " + drinks);
                main_cost_update();
                pay_button.setText("Pay Now: " + get_order_total()); 
            }  
        }); 
        more_food.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                setGuac(chips_guac);
                setSalsa(chips_salsa);
                setQueso(chips_queso);
                setDrink(drinks);
                new Clerk_Home_Page(true); 
                f.dispose(); 
            }  
        }); 
        pay_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                new Clerk_Home_Page(additional_entree);
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
