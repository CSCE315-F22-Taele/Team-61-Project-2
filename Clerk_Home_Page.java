import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Home_Page {
    JFrame f = new JFrame("Clerk Home Page"); 
    Clerk_Home_Page(Vector<Order> orders, Inventory inventory, boolean additional_entree){  
       //JFrame f= new JFrame("Panel Example");
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);    
       JButton home_page_button =new JButton("Back To Home Page");
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       JButton bowl_button = new JButton("Bowl"); 
       JButton burrito_button = new JButton("Burrito"); 
       JButton taco_button = new JButton("Taco"); 
       JButton salad_button = new JButton("Salad"); 
       home_page_button.setBounds(50,100,80,30);    
        home_page_button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    home_page_button.setBackground(Color.green);  
                        new Home_Page(); 
                        f.dispose(); 
                    }  
            }); 
        bowl_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Order current_order; 
                if (additional_entree){
                    current_order = new Order(orders.lastElement().Sale_Id); 
                }
                else{
                    current_order = new Order(); 
                }
                current_order.Entree = "Bowl"; 
                orders.add(current_order); 
                new Clerk_Protein_Page(orders, inventory, additional_entree); 
                f.dispose();
            }
        });
        burrito_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Order current_order; 
                if (additional_entree){
                    current_order = new Order(orders.lastElement().Sale_Id); 
                }
                else{
                    current_order = new Order(); 
                }
                current_order.Entree = "Burrito"; 
                orders.add(current_order); 
                new Clerk_Protein_Page(orders, inventory, additional_entree); 
                f.dispose();
            }
        });
        taco_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Order current_order; 
                if (additional_entree){
                    current_order = new Order(orders.lastElement().Sale_Id); 
                }
                else{
                    current_order = new Order(); 
                }
                current_order.Entree = "Taco"; 
                orders.add(current_order); 
                new Clerk_Protein_Page(orders, inventory, additional_entree); 
                f.dispose();
            }
        });
        salad_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Order current_order; 
                if (additional_entree){
                    current_order = new Order(orders.lastElement().Sale_Id); 
                }
                else{
                    current_order = new Order(); 
                }
                current_order.Entree = "Salad"; 
                orders.add(current_order); 
                new Clerk_Protein_Page(orders, inventory, additional_entree); 
                f.dispose();
            }
        });
       panel.add(home_page_button);  
       panel.add(bowl_button);
       panel.add(burrito_button);
       panel.add(taco_button); 
       panel.add(salad_button); 
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);    
       }  
<<<<<<< HEAD
<<<<<<< HEAD
       
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    
    //    public static void main(String args[])  
    //    {  
    //    new Clerk_Home_Page();  
    //    }  
=======
>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
=======
>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
}
