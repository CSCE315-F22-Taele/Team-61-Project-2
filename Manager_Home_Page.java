import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.ArrayList;
import java.sql.*; 
/**
 * This class is responsible for displaying the manager view in the point-of-sale system. In the manager view,
 * the user can update prices, quantity and supply, view the inventory and sales report, and also view the restock report.
 * @author Justin Singletary
 */
public class Manager_Home_Page {

    JFrame f = new JFrame("Manager Page");

    /**
     * This constructor creates the layout for the manager side of the POS system. 
     * @param entrees a vector of strings that contains the 3 different entree types (bowl, burrito, tacos)
     * @param protein a vector of strings that contains the 4 different protein options
     * @param sides a vector of strings that contains the side items
     */
    Manager_Home_Page(ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides) {   

        JPanel panel = new JPanel();  
        panel.setBounds(10, 10, 2000, 600); 

        // Label outputs on GUI the selections
        JTextArea queryTextBox = new JTextArea();
        Font queryTextBoxFont = new Font("Times New Roman", Font.PLAIN, 15);
        queryTextBox.setFont(queryTextBoxFont); 
                
        // Label outputs total
        JTextArea totalLabel = new JTextArea();
        totalLabel.setBounds(50, 360, 200, 50);

        JButton b1 = new JButton("Back To Home Page");     
        b1.setBounds(30,100,80,30);    
        b1.addActionListener(new ActionListener() { 
            /**
             * This functions sets the background color of the "Back To Home Page" button green on click
             * @param e the ActionEvent status which is represented by a mouse click
             */ 
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                new Home_Page(); 
                f.dispose(); 
            }  
        }); 

        JButton update_inventory = new JButton("Update Inventory Supply");
        update_inventory.addActionListener(new ActionListener(){
            /**
             * When the update inventory button is pressed, the logic within this function is executed thus updating the 
             * inventory.
             * @param e represents the status of the button when it is clicked
             */
            public void actionPerformed(ActionEvent e){
                jdbcpostgreSQL databaseConnection = new jdbcpostgreSQL("Update Inventory Supply");
                totalLabel.setText("");
                queryTextBox.setText("");
                queryTextBox.append("\n    Result - " + databaseConnection.sql_output);
            }
        });

        JButton edit_item_button = new JButton("Adjust Prices / Add New Items"); 
        edit_item_button.setBounds(180,200,100,40);
        edit_item_button.addActionListener(new ActionListener(){
            /**
             * Creates a new page view when the edit item button is selected
             * @param e represents the click of the button
             */
            public void actionPerformed(ActionEvent e){
                new Edit_Item_Page(entrees, protein, sides);
                f.dispose(); 
            }
        }); 

        JButton excess_report = new JButton("Excess Report"); 
        b1.setBounds(30,100,80,30);   
        excess_report.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Excess_Report_Page();
                f.dispose(); 
            }
        });  

        JButton restock_report = new JButton("Restock Report"); 
        b1.setBounds(30,200,80,30);   
        restock_report.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jdbcpostgreSQL databaseConnection = new jdbcpostgreSQL("Restock Report");
                totalLabel.setText("");
                queryTextBox.setText("");
                queryTextBox.append("\n    Result - Items with Low Supply\n\n" + databaseConnection.sql_output);

                double total = databaseConnection.total_output;
                String totalString = String.format("%.2f", total);
                totalLabel.append("Total: $" + totalString);

                String count = databaseConnection.count_output;
                totalLabel.append("\n" + count); 
            }
        }); 
        panel.add(b1);  
        panel.add(update_inventory); 
        panel.add(edit_item_button); 
        panel.add(excess_report);  
        panel.add(restock_report);

        //Start Date TextBox
        JTextArea start = new JTextArea();  
        start.setBounds(220,100, 100,20);   
        f.add(start);

        JLabel startLabel = new JLabel("Start Date");
        startLabel.setBounds(230, 75, 100, 20);
        f.add(startLabel);

        //End Date TextBox
        JTextArea end = new JTextArea();  
        end.setBounds(340,100, 100,20); 
        f.add(end);

        JLabel endLabel = new JLabel("End Date");
        endLabel.setBounds(360, 75, 100, 20);
        f.add(endLabel);

        //Lists
        DefaultListModel<String> l0 = new DefaultListModel<>();  
        l0.addElement("Inventory");
        l0.addElement("Sales Report");
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
        l4.addElement("All"); 
        l4.addElement("protein");
        l4.addElement("side");
        l4.addElement("topping");
        l4.addElement("tortilla");
        l4.addElement("misc");
        JList<String> list4 = new JList<>(l4);  
        list4.setBounds(80, 220, 120, 105);  
        f.add(list4); 

        JLabel selectTableLabel = new JLabel(" Select Table ");
        selectTableLabel.setBounds(85, 70, 110, 20);
        selectTableLabel.setBackground(Color.lightGray);
        selectTableLabel.setOpaque(true);

        JLabel inventorySelectLabel = new JLabel(" Inventory Criteria ");
        inventorySelectLabel.setBounds(60, 190, 160, 20);
        inventorySelectLabel.setBackground(Color.lightGray);
        inventorySelectLabel.setOpaque(true);

        JLabel salesSelectLabel = new JLabel(" Sales Report Criteria ");
        salesSelectLabel.setBounds(540, 70, 185, 20);
        salesSelectLabel.setBackground(Color.lightGray);
        salesSelectLabel.setOpaque(true);

        Font selectLabelFont = new Font("Segoe Script", Font.BOLD, 15);
        selectTableLabel.setFont(selectLabelFont);
        inventorySelectLabel.setFont(selectLabelFont);
        salesSelectLabel.setFont(selectLabelFont);
        f.add(inventorySelectLabel);
        f.add(salesSelectLabel);
        f.add(selectTableLabel);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(270, 200, 100, 30);

        // Text box to type in Item ID
        JTextArea itemIDTextBox = new JTextArea();
        itemIDTextBox.setBounds(20, 470, 50, 20);
        f.add(itemIDTextBox); 
        // Text above Item ID text box
        JLabel itemIDTextLabel = new JLabel("Item ID");
        itemIDTextLabel.setBounds(20, 440, 100, 30);
        f.add(itemIDTextLabel);
        // Text box for quantity 
        JTextArea quantityTextBox = new JTextArea();
        quantityTextBox.setBounds(85, 470, 50, 20);
        f.add(quantityTextBox);
        // Text above quantity text box
        JLabel quantityTextLabel = new JLabel("Quantity");
        quantityTextLabel.setBounds(80, 440, 100, 30);
        f.add(quantityTextLabel);
        // Text box for sufficient supply 
        JTextArea sufficientSupplyTextBox = new JTextArea();
        sufficientSupplyTextBox.setBounds(150, 470, 50, 20);
        f.add(sufficientSupplyTextBox);
        // Text above sufficient supply text box
        JLabel sufficientSupplyTextLabel = new JLabel("Sufficient Supply");
        sufficientSupplyTextLabel.setBounds(150, 440, 150, 30);
        f.add(sufficientSupplyTextLabel);
        
        // After drop down menu items are selected and submit button is pressed, the values are stored and outputted on the frame
        submitButton.addActionListener(new ActionListener() {
            /**
             * When the submit button is pressed, the query is outputted into the text box
             * @param e represents the button being clicked
             */
            public void actionPerformed(ActionEvent e) {
                itemIDTextBox.setText("");
                quantityTextBox.setText("");
                sufficientSupplyTextBox.setText("");
                queryTextBox.setText("");
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
                if (database == "Sales Report") {
                    queryTextBox.append("\n    Result - Sales Report\n\n" + output);
                } else if (database == "Inventory") {
                    queryTextBox.append("\n    Result - Inventory\n\n" + output);
                }

                double total = databaseConnection.total_output;
                String totalString = String.format("%.2f", total);
                totalLabel.append("Total: $" + totalString);

                String count = databaseConnection.count_output;
                totalLabel.append("\n" + count); 
            }
        });

        // Update button to update inventory quantity and sufficient supply field
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(220, 470, 100, 30);
        updateButton.addActionListener(new ActionListener() {
            /**
             * when the update button is pressed, the database updates the quantity and sufficient supply of the corresponding item id
             * @param e repreesnts the click of the button
             */
            public void actionPerformed(ActionEvent e) {
                String id = "";
                String quantity = "";
                String suffSupp = "";
                id = itemIDTextBox.getText();
                quantity = quantityTextBox.getText();
                suffSupp = sufficientSupplyTextBox.getText();
                    
                jdbcpostgreSQL databaseConnection = new jdbcpostgreSQL("inventory", quantity, suffSupp, id);
            }
        });

        Font font = new Font("Segoe Script", Font.BOLD, 20);
        totalLabel.setFont(font);
        totalLabel.setForeground(Color.BLUE);

        JScrollPane scrollableTextArea = new JScrollPane(queryTextBox);
        scrollableTextArea.setBounds(350, 280, 550, 200);

        f.add(updateButton); f.add(submitButton); f.add(totalLabel); f.add(scrollableTextArea); f.add(panel); 
        f.setSize(1010,610);    
        f.setVisible(true);   
    }  
}