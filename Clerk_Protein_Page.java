import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

/**
 * This class provides the layout and functionality for the clerk's protein page.
 * @author Sam Brokaw, Roee Belkin 
 */
public class Clerk_Protein_Page {
    JFrame f = new JFrame("Clerk Protein Page");
    /**
     * The constructor creates an instance of the page that is used to help the clerk select the protein options ordered
     * by the customer.
     * @param orders                a vector of Order objects that represent the orders made by the customer
     * @param inventory             a vector of Tuple objects that represent the inventory items
     * @param entrees               a vector of strings that represent the options of possible entrees
     * @param protein               a vector of strings that represent the options of possible proteins
     * @param sides                 a vector of strings that represent the sides ordered by the customer
     * @param additional_entree     a boolean variable that represents whether or not the customer is ordering multiple items
     */
    Clerk_Protein_Page(Vector<Order> orders, ArrayList<Tuple> inventory, ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides, boolean additional_entree){  

       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       JButton home_page_button =new JButton("Back To Clerk Home Page");

        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    orders.remove(orders.size() -1);
                    new Clerk_Home_Page(orders, inventory, entrees, protein, sides, additional_entree); 
                    f.dispose(); 
                }  
            }); 
        panel.add(home_page_button); 
        for (String temp_protein : protein){
            if (temp_protein != "all" && temp_protein != "ALL" && temp_protein != "All" && temp_protein != "NONE" && temp_protein != "None" && temp_protein != "none"){
                JButton temp_button = new JButton(temp_protein); 
                temp_button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        orders.lastElement().Protein = temp_protein; 
                        for (Tuple item : inventory){
                            if (item.side_name.equals(temp_protein)){
                                item.amount++; 
                            }
                        }
                        new Clerk_Toppings_Page(orders, inventory, entrees, protein, sides, additional_entree); 
                        f.dispose();
                    }
                });
                panel.add(temp_button); 
            }
        }
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);    
       }  
       
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    

}
