import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.*; 
=======
import java.util.ArrayList;

>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
=======
import java.util.ArrayList;

>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
public class Home_Page {  
    
    ArrayList<String> entrees = new ArrayList<String>() {{
        add("None");
        add("All");
        add("bowl");
        add("burrito");
        add("tacos");
    }};
    ArrayList<String> protein = new ArrayList<String>() {{
        add("None");
        add("All");
        add("chicken");
        add("steak");
        add("beef");
        add("vegetable medley");
    }};
    ArrayList<String> sides = new ArrayList<String>() {{
        add("None");
        add("chips_and_salsa");
        add("chips_and_queso");
        add("chips_and_guac");
        add("drink");
    }};

    JFrame f = new JFrame("Home Page");
<<<<<<< HEAD
<<<<<<< HEAD
    Vector<Order> orders = new Vector<Order>(); 
    Inventory inventory = new Inventory(); 
     Home_Page(){  
        //JFrame f= new JFrame("Panel Example");    
=======

    Home_Page() {    

>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
=======

    Home_Page() {    

>>>>>>> 3471b940b1f4290257501a0acbed5bfa8a0914d2
        JPanel panel=new JPanel();  
        panel.setBounds(10,10,1000,600);    
        panel.setBackground(Color.gray);  

        JButton b1=new JButton("Clerk Page");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.red);
        b1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    b1.setBackground(Color.green);  
                        new Clerk_Home_Page(orders, inventory, false); 
                        f.dispose(); 
                    }  
        }); 

        JButton b2=new JButton("Manager Page");   
        b2.setBounds(100,100,80,30);    
        b2.setBackground(Color.yellow);   
        b2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                new Manager_Home_Page(entrees, protein, sides); 
                f.dispose(); 
            }  
        }); 

        panel.add(b1); panel.add(b2); 

        f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);    
    }  
           
    public static void main(String args[])  
    {  
        new Home_Page();  
    }  
}  