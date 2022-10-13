import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 

public class Manager_Home_Page {

    JFrame f = new JFrame("Home Page");

    Manager_Home_Page(String[] entrees, String[] protein, String[] sides) {   
          
        JPanel panel = new JPanel();  
        panel.setBounds(10, 10, 1000, 600); 

        JButton b1 = new JButton("Back To Home Page");     
        b1.setBounds(50,100,80,30); 
        
        // On click - Goes back to home page
        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                    new Home_Page(); 
                    f.dispose(); 
                }  
        }); 
        panel.add(b1);  


        //Start Date TextBox
        JTextArea startDate = new JTextArea();  
        startDate.setBounds(180,100, 100,20);  
        f.add(startDate);

        //End Date TextBox
        JTextArea endDate = new JTextArea();  
        endDate.setBounds(300,100, 100,20);  
        f.add(endDate);


        //Lists
        DefaultListModel<String> l1 = new DefaultListModel<>();  
        for (String e : entrees) {
            l1.addElement(e);
        }
        JList<String> list1 = new JList<>(l1);  
        list1.setBounds(420, 100, 100, 110);  
        f.add(list1); 

        DefaultListModel<String> l2 = new DefaultListModel<>();  
        for (String p : protein) {
            l2.addElement(p);
        }
        JList<String> list2 = new JList<>(l2);  
        list2.setBounds(540, 100, 100, 110);  
        f.add(list2); 

        DefaultListModel<String> l3 = new DefaultListModel<>();  
        for (String s : sides) {
            l3.addElement(s);
        }
        JList<String> list3 = new JList<>(l3);  
        list3.setBounds(660, 100, 100, 110);  
        f.add(list3); 

        // Label outputs on GUI the selections
        final JLabel label = new JLabel();
        label.setSize(500, 100);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(200, 150, 80, 30);
        
        // After drop down menu items are selected and submit button is pressed, the values are stored and outputted on the frame
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                String entreeType; String proteinType; String extrasSelected;
                if(list1.getSelectedIndex() != -1) {
                    entreeType = list1.getSelectedValue();
                    data += entreeType + " ";
                    label.setText(data);
                }
                if(list2.getSelectedIndex() != -1) {
                    proteinType = list2.getSelectedValue();
                    data += proteinType + " ";
                    label.setText(data);
                }
                if(list3.getSelectedIndex() != -1) {
                    extrasSelected = list3.getSelectedValue();
                    data += extrasSelected + " ";
                    label.setText(data);
                }
            }
        });
    
        f.add(submitButton); f.add(label);

        f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);   
    }  
}
