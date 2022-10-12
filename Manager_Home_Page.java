import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 

public class Manager_Home_Page {
    JFrame f = new JFrame("Home Page");
    Manager_Home_Page(){  
       //JFrame f= new JFrame("Panel Example");    
       JPanel panel=new JPanel();  
       panel.setBounds(10,10,1000,600);    
       JButton b1=new JButton("Back To Home Page");     
       b1.setBounds(50,100,80,30);    
        b1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    b1.setBackground(Color.green);  
                        new Home_Page(); 
                        f.dispose(); 
                    }  
            }); 
       panel.add(b1);  
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
       new Manager_Home_Page();  
       }  
}
