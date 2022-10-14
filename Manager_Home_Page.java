import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.ArrayList;

public class Manager_Home_Page {

    JFrame f = new JFrame("Home Page");

    Manager_Home_Page(ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides) {   

        JPanel panel = new JPanel();  
        panel.setBounds(10, 10, 1000, 600); 

        JButton b1 = new JButton("Back To Home Page");     
        b1.setBounds(50,100,80,30);    
        b1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                new Home_Page(); 
                f.dispose(); 
            }  
        }); 
        panel.add(b1);  

        //Add new items to sides
        JTextArea addItemTextBox = new JTextArea();  
        addItemTextBox.setBounds(20,400, 100,20);   
        f.add(addItemTextBox);

        JButton addItem = new JButton("Add Item");     
        addItem.setBounds(140,400,100,30);    
        addItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                sides.add(addItemTextBox.getText());
                new Manager_Home_Page(entrees, protein, sides);
                f.dispose(); 
            }  
        }); 
        f.add(addItem);

        //Start Date TextBox
        JTextArea start = new JTextArea();  
        start.setBounds(220,100, 100,20);   
        f.add(start);

        //End Date TextBox
        JTextArea end = new JTextArea();  
        end.setBounds(340,100, 100,20); 
        f.add(end);

        //Lists
        DefaultListModel<String> l0 = new DefaultListModel<>();  
        l0.addElement("cabo_grill");
        l0.addElement("cabo_grill_sales");
        JList<String> list0 = new JList<>(l0);  
        list0.setBounds(80, 100, 120, 40);  
        f.add(list0); 

        DefaultListModel<String> l1 = new DefaultListModel<>();  
        for (String e : entrees) {
            l1.addElement(e);
        }
        JList<String> list1 = new JList<>(l1);  
        list1.setBounds(460, 100, 100, 150);  
        f.add(list1); 

        DefaultListModel<String> l2 = new DefaultListModel<>();  
        for (String p : protein) {
            l2.addElement(p);
        }
        JList<String> list2 = new JList<>(l2);  
        list2.setBounds(580, 100, 100, 150);  
        f.add(list2); 

        DefaultListModel<String> l3 = new DefaultListModel<>();  
        for (String s : sides) {
            l3.addElement(s);
        }
        JList<String> list3 = new JList<>(l3);  
        list3.setBounds(700, 100, 140, 150);  
        f.add(list3); 

        DefaultListModel<String> l4 = new DefaultListModel<>(); 
        l4.addElement("None"); 
        l4.addElement("All"); 
        l4.addElement("protein");
        l4.addElement("side");
        l4.addElement("topping");
        l4.addElement("tortilla");
        l4.addElement("misc");
        JList<String> list4 = new JList<>(l4);  
        list4.setBounds(80, 170, 120, 130);  
        f.add(list4); 

        // Label outputs on GUI the selections
        JTextArea queryLabel = new JTextArea();
        queryLabel.setBounds(250, 280, 600, 500);

        // Label outputs total
        JTextArea totalLabel = new JTextArea();
        totalLabel.setBounds(50, 320, 170, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(270, 200, 100, 30);

        // Label to type in Item ID
        JTextArea updateLabel = new JTextArea();
        updateLabel.setBounds(30, 500, 50, 20);
        
        // After drop down menu items are selected and submit button is pressed, the values are stored and outputted on the frame
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queryLabel.setText("");
                totalLabel.setText("");
                String database = "";
                String inventory = "";
                String entreeType = ""; 
                String proteinType = ""; 
                String extrasSelected = "";
                if (list0.getSelectedIndex() != -1) {
                    database = list0.getSelectedValue();
                }
                if(list1.getSelectedIndex() != -1) {
                    entreeType = list1.getSelectedValue();
                }
                if(list2.getSelectedIndex() != -1) {
                    proteinType = list2.getSelectedValue();
                }
                if(list3.getSelectedIndex() != -1) {
                    extrasSelected = list3.getSelectedValue();
                }
                if(list4.getSelectedIndex() != -1) {
                    inventory = list4.getSelectedValue();
                }

                String startDate = "";
                String endDate = "";
                if (!start.getText().isEmpty()) {
                    startDate = start.getText();
                }
                if (!end.getText().isEmpty()) {
                    endDate = end.getText();
                }

                jdbcpostgreSQL databaseConnection = new jdbcpostgreSQL(database, inventory, startDate, endDate, entreeType, proteinType, extrasSelected);
                String output = databaseConnection.sql_output;
                queryLabel.append(output);

                String total = databaseConnection.total_output;
                totalLabel.append(total);
            }
        });
    
        f.add(submitButton); f.add(queryLabel); f.add(totalLabel); f.add(updateLabel);

        f.add(panel);  
        f.setSize(1010,610);    
        f.setLayout(null);    
        f.setVisible(true);   
    }  
}