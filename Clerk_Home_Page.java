import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Home_Page {
    JFrame f = new JFrame("Clerk Home Page"); 
    Clerk_Home_Page(Vector<Order> orders, Inventory inventory, ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides, boolean additional_entree){  
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);    
       JButton home_page_button =new JButton("Back To Home Page");
        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    home_page_button.setBackground(Color.green);  
                        new Home_Page(); 
                        f.dispose(); 
                    }  
            }); 
        panel.add(home_page_button);  
        for (String temp_entree : entrees){
            if (temp_entree != "all" || temp_entree != "ALL" || temp_entree != "All" || temp_entree != "NONE" || temp_entree != "None" || temp_entree != "none"){
                JButton temp_button = new JButton(temp_entree); 
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
                panel.add(temp_button); 
            }
        }

        
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);  
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       }  
}
