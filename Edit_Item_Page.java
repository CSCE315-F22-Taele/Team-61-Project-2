import java.awt.*;  
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.event.*; 
import java.util.*; 
import java.util.ArrayList;
import java.sql.*; 

/**
 * This class provides the basis for the layout and queries of the "Add/Update New Item Page". This page can be directed to within
 * the manager view of the POS. 
 */
public class Edit_Item_Page {  
    JFrame f = new JFrame("Home Page");
    Connection conn = null;
    String teamNumber = "61"; // Your team number
    String sectionNumber = "905"; // Your section number
    String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
    String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    Statement stmt = null; 
    dbSetup myCredentials = new dbSetup();
    
    /**
     * The constructor is where all of the layout code and queries are executed. This constructor takes in as input 3 values: the entrees, proteins,
     * and sides to be edited.
     * @param entrees an array of strings that represent the entree options
     * @param protein an array of strings that represent the protein options
     * @param sides   an array of strings that represent the possible sides
     */
    Edit_Item_Page(ArrayList<String> entrees, ArrayList<String> protein, ArrayList<String> sides) { 
        
        GridLayout test_layout = new GridLayout(1, 3); 
        JTextArea page_info = new JTextArea("Please use the top half of this page for editing current prices, and the bottom half for adding new items."); 
        JPanel button_panel = new JPanel();
        button_panel.setBounds(450,200,300,300); 
        JButton b1 = new JButton("Back To Home Page");      
        b1.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e){  
                b1.setBackground(Color.green);  
                new Home_Page(); 
                f.dispose(); 
            }  
        }); 
        button_panel.add(b1); 
        
        page_info.setFont(new Font("Serif", Font.PLAIN, 20)); 
        page_info.setEditable(false);
        page_info.setBounds(60, 50, 900, 50);
        f.add(page_info); 
        DefaultListModel<String> l2 = new DefaultListModel<>();  
        for (String p : protein) {
            l2.addElement(p);
        }
        JList<String> list2 = new JList<>(l2);  
        list2.setBounds(50, 100, 100, 150);  
        list2.setBorder(new LineBorder(Color.black));
        f.add(list2); 

        DefaultListModel<String> l3 = new DefaultListModel<>();  
        for (String s : sides) {
            l3.addElement(s);
        }
        JList<String> list3 = new JList<>(l3);  
        list3.setBounds(200, 100, 140, 150);  
        list3.setBorder(new LineBorder(Color.black));
        f.add(list3); 

        DefaultListModel<String> type_l = new DefaultListModel<>(); 
        type_l.addElement("entree");
        type_l.addElement("side");    
        type_l.addElement("protein");
        JList<String> type_list = new JList<>(type_l); 
        type_list.setBounds(50, 400, 100, 150); 
        type_list.setBorder(new LineBorder(Color.black)); 
        f.add(type_list); 

        JTextArea name_area = new JTextArea("item name");
        name_area.setBounds(200, 400, 100, 30);
        name_area.setBorder(new LineBorder(Color.black));
        f.add(name_area); 

        JTextArea minimum_area = new JTextArea("minimum supply");
        minimum_area.setBounds(350, 400, 150, 30);
        minimum_area.setBorder(new LineBorder(Color.black));
        f.add(minimum_area); 

        JTextArea sale_area = new JTextArea("sale cost");
        sale_area.setBounds(550, 400, 100, 30);
        sale_area.setBorder(new LineBorder(Color.black));
        f.add(sale_area); 

        JTextArea quantity_area = new JTextArea("quantity");
        quantity_area.setBounds(700, 400, 100, 30);
        quantity_area.setBorder(new LineBorder(Color.black));
        f.add(quantity_area); 

        JTextArea id_area = new JTextArea("id");
        id_area.setBounds(850, 400, 100, 30);
        id_area.setBorder(new LineBorder(Color.black));
        f.add(id_area); 

        JTextArea new_cost_area = new JTextArea("123");
        new_cost_area.setBounds(600, 150, 100, 30);
        new_cost_area.setBorder(new LineBorder(Color.black));
        f.add(new_cost_area); 
        // Text above Item ID text box

        JButton update_cost_button = new JButton("Press here to update new cost");
        update_cost_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean good_input = false; 
                String item_to_update = ""; 
                if (list2.getSelectedIndex() != -1) {
                    item_to_update = list2.getSelectedValue();
                    good_input = true; 
                }
                else if (list3.getSelectedIndex() != -1){
                    item_to_update = list3.getSelectedValue(); 
                    good_input = true; 
                }
                else{
                    new_cost_area.setText("invalid input");
                }
                if(good_input){
                    try {
                        conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
                    } 
                    catch (Exception eee) {
                        eee.printStackTrace();
                        System.err.println(eee.getClass().getName()+": "+eee.getMessage());
                        System.exit(0);
                    }
                    float new_cost = Float.parseFloat(new_cost_area.getText()); 
                    try{
                        Statement stmt = conn.createStatement(); 
                        String sqlQuery = "UPDATE cabo_grill SET sale_cost = " + new_cost + " where item_name = '" + item_to_update + "';";
                        stmt.executeUpdate(sqlQuery); 
            
                    }
                    catch (Exception ee){
                        ee.printStackTrace();
                        //System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }
                    new_cost_area.setText("Cost Updated"); 
                }
            }
        });
        
        //update_cost_button.setOpaque(true); 

        JButton add_new_button = new JButton("Press here to add new item"); 
        add_new_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = "";
                int quantity = 0; 
                int minimum = 0; 
                int id = 0; 
                float cost = 0; 
                String type = ""; 
                boolean good_input = false; 
                try {
                    name = name_area.getText(); 
                    quantity = Integer.parseInt(quantity_area.getText()); 
                    minimum = Integer.parseInt(minimum_area.getText()); 
                    cost = Float.parseFloat(sale_area.getText()); 
                    id = Integer.parseInt(id_area.getText()); 
                    good_input = true; 
                }
                catch (Exception ee){
                    good_input = false; 
                    name_area.setText("Invalid Input"); 
                    quantity_area.setText("Invalid Input"); 
                    minimum_area.setText("Invalid Input"); 
                    sale_area.setText("Invalid Input"); 
                    id_area.setText("Invalid Input");
                }
                if (type_list.getSelectedIndex() != -1) {
                    type = type_list.getSelectedValue();
                    good_input = true; 
                }
                else{
                    name_area.setText("Invalid Input"); 
                    quantity_area.setText("Invalid Input"); 
                    minimum_area.setText("Invalid Input"); 
                    sale_area.setText("Invalid Input"); 
                    id_area.setText("Invalid Input");
                    good_input = false; 
                }
                if (good_input){
                    try {
                        conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
                    } 
                    catch (Exception eee) {
                        eee.printStackTrace();
                        System.err.println(eee.getClass().getName()+": "+eee.getMessage());
                        System.exit(0);
                    }
                    try {
                        Statement stmt = conn.createStatement(); 
                        String sqlQuery = "Insert Into cabo_grill (id, item_name, type, quantity, sale_cost, minimum_supply) VALUES (" + id + ", '" + name + "' , '" + type + "' , " + quantity + ", " + cost + ", " + minimum + ");";
                        stmt.executeUpdate(sqlQuery); 
                    }
                    catch (Exception ee){
                        ee.printStackTrace();
                        //System.err.println(e.getClass().getName()+": "+e.getMessage());
                        System.exit(0);
                    }
                    name_area.setText("Item Added"); 
                    quantity_area.setText("Item Added"); 
                    minimum_area.setText("Item Added"); 
                    sale_area.setText("Item Added"); 
                    id_area.setText("Item Added");
                }


            }
        });
        
        button_panel.add(update_cost_button); 
        button_panel.add(add_new_button); 
        f.add(button_panel); 
        f.setSize(1010,610);    
        f.setBackground(Color.gray);
        f.setVisible(true);

    }
}