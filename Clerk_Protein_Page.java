import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Protein_Page extends Home_Page{
    JFrame f = new JFrame("Clerk Protein Page");
    Clerk_Protein_Page(boolean additional_entree){  
       //JFrame f= new JFrame("Panel Example");
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);           
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       JButton home_page_button =new JButton("Back To Clerk Home Page");
       JButton chicken_button = new JButton("Chicken"); 
       JButton steak_button = new JButton("Steak"); 
       JButton beef_button = new JButton("Beef"); 
       JButton vegetable_button = new JButton("Vegetable Medley"); 
        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    orders.remove(orders.size() -1);
                    new Clerk_Home_Page(additional_entree); 
                    f.dispose(); 
                }  
            }); 
        chicken_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                set_protein("Chicken");
                new Clerk_Toppings_Page(additional_entree);
                f.dispose(); 
            }
        });
        steak_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                set_protein("Steak");
                new Clerk_Toppings_Page(additional_entree);
                f.dispose(); 
            }
        });
        beef_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                set_protein("Beef");
                new Clerk_Toppings_Page(additional_entree);
                f.dispose(); 
            }
        });
        vegetable_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                set_protein("Vegetable"); 
                new Clerk_Toppings_Page(additional_entree);
                f.dispose(); 
            }
        });
       panel.add(home_page_button);  
       panel.add(chicken_button);
       panel.add(steak_button);
       panel.add(beef_button); 
       panel.add(vegetable_button); 
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
