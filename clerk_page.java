
import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;

public class clerk_page {  
    clerk_page()  
       {  
       JFrame f= new JFrame("Panel Example");   
       JPanel panel=new JPanel();  
       panel.setBounds(10,10,1000,600);    
       panel.setBackground(Color.gray);  
       JButton b1=new JButton("Button 1");     
       b1.setBounds(50,100,80,30);    
       b1.setBackground(Color.red);
       JButton b2=new JButton("Button 2");   
       b2.setBounds(100,100,80,30);    
       b2.setBackground(Color.red);   
       panel.add(b1); panel.add(b2);  
       f.add(panel);  
               f.setSize(1010,610);    
               f.setLayout(null);    
               f.setVisible(true);    
       }
    }