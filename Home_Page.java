import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.ArrayList;

public class Home_Page {  
    
    ArrayList<String> entrees = new ArrayList<String>() {{
        add("None");
        add("All");
        add("Bowl");
        add("Burrito");
        add("Tacos");
    }};
    ArrayList<String> protein = new ArrayList<String>() {{
        add("None");
        add("All");
        add("Chicken");
        add("Steak");
        add("Beef");
        add("Vegetable");
    }};
    ArrayList<String> sides = new ArrayList<String>() {{
        add("None");
        add("All");
        add("Chips-Queso");
        add("Chips-Guac");
        add("Chips-Salsa");
        add("Water");
    }};

    JFrame f = new JFrame("Home Page");

    Home_Page() {    

        JPanel panel=new JPanel();  
        panel.setBounds(10,10,1000,600);    
        panel.setBackground(Color.gray);  
        JButton b1=new JButton("Clerk Page");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.red);
        b1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    b1.setBackground(Color.green);  
                        new Clerk_Home_Page(); 
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
        
        public void windowClosing (WindowEvent e) {    
            f.dispose();    
        }    
        public static void main(String args[])  
        {  
            new Home_Page();  
        }  
    }  