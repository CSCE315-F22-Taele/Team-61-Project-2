import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 

public class Clerk_Home_Page {
    Vector<Order> orders = new Vector<Order>(); 
    JFrame f = new JFrame("Home Page");
    boolean additional_entree = false; 
    Clerk_Home_Page(){  
       //JFrame f= new JFrame("Panel Example");
       GridLayout test_layout = new GridLayout(4, 4); 
       JPanel panel=new JPanel();  
       panel.setLayout(test_layout); 
       panel.setBounds(10,10,1000,600);    
       JButton b1=new JButton("Back To Home Page");
       //b1.setBackground(Color.red);     
       //b1.setOpaque(true);
       //b1.setBorderPainted(false);
       JButton b2= new JButton("Bowl"); 
       JButton b3= new JButton("Burrito"); 
       JButton b4= new JButton("Taco"); 
       JButton b5= new JButton("Salad"); 
       b1.setBounds(50,100,80,30);    
        b1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    b1.setBackground(Color.green);  
                        new Home_Page(); 
                        f.dispose(); 
                    }  
            }); 
        b2.addActionListener(new ActionListener() {
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
            }
        });
       panel.add(b1);  
       panel.add(b2);
       panel.add(b3);
       panel.add(b4); 
       panel.add(b5); 
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);    
       }  
       
       public void windowClosing (WindowEvent e) {    
           f.dispose();    
       }    
       public static void main(String args[])  
       {  
       new Clerk_Home_Page();  
       }  
}
