import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 

public class Manager_Home_Page {

    JFrame f = new JFrame("Home Page");

    Manager_Home_Page(){     
        JPanel panel=new JPanel();  
        panel.setBounds(10,10,1000,600);    

        JButton b1=new JButton("Back To Home Page");     
        b1.setBounds(50,100,80,30);
        // Redirects to home page when button is clicked    
        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                    new Home_Page(); 
                    f.dispose(); 
                }  
        }); 
        panel.add(b1);  

        DefaultListModel<String> l1 = new DefaultListModel<>();  
        l1.addElement("All");  
        l1.addElement("Burrito");  
        l1.addElement("Bowl");  
        l1.addElement("None");  
        JList<String> list = new JList<>(l1);  
        list.setBounds(100,100, 75,75);  
        final JLabel label = new JLabel();  // label outputs what options are selected
        label.setSize(500,100);
        f.add(list); 

        
        JButton b2 = new JButton("Submit");  
        b2.setBounds(200,150,80,30);

        // When "Submit" button clicked, label text is set to the option selected
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if(list.getSelectedIndex() != -1) {
                    data = list.getSelectedValue();
                    label.setText(data);
                }
            }
        });


        f.add(b2); f.add(label); f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);   
    }  

    public static void main(String args[])  
    {  
        new Manager_Home_Page();  
    }  
}
