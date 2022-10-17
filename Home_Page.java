import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 
import java.util.ArrayList;
import java.sql.*; 

public class Home_Page {  
    ArrayList<String> entrees = new ArrayList<String>() {{
        add("NONE");
        add("ALL");
        add("bowl");
        add("burrito");
        add("tacos"); 
    }};
    ArrayList<String> protein = new ArrayList<String>() {{

    }};
    ArrayList<String> sides = new ArrayList<String>() {{

    }};

    public void fill_arrays(){
        Connection conn = null;
        String teamNumber = "61"; // Your team number
        String sectionNumber = "905"; // Your section number
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        //System.out.println("Opened database successfully");
        try{
            Statement stmt = conn.createStatement(); 
            String sqlQuery = "SELECT item_name FROM cabo_grill WHERE type = 'protein';";
            ResultSet result = stmt.executeQuery(sqlQuery); 
            while(result.next()){
                protein.add(result.getString("item_name"));
            }
            protein.add("ALL");
            protein.add("NONE"); 
            sqlQuery = "SELECT item_name FROM cabo_grill WHERE type = 'side';";
            result = stmt.executeQuery(sqlQuery); 
            while (result.next()){
                sides.add(result.getString("item_name"));
            }
            sides.add("ALL");
            sides.add("NONE"); 

            //System.out.println(Sale_Id);
        }
        catch (Exception e){
            e.printStackTrace();
            //System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }


    JFrame f = new JFrame("Home Page");
    Vector<Order> orders = new Vector<Order>(); 
    Inventory inventory = new Inventory(); 
     Home_Page(){  
        fill_arrays();
        //JFrame f= new JFrame("Panel Example");    
        JPanel panel=new JPanel();  
        panel.setBounds(10,10,1000,600);    
        panel.setBackground(Color.gray);  

        JButton b1=new JButton("Clerk Page");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.red);
        b1.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    b1.setBackground(Color.green);  
                        new Clerk_Home_Page(orders, inventory, entrees, protein, sides, false); 
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
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }  
           
    public static void main(String args[])  
    {  
        new Home_Page();  
    }  
}  