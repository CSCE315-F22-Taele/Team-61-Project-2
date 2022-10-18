import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 
import java.util.*; 
import java.util.ArrayList;
import java.sql.*; 
/**
 * This class creates the base page for the point-of-sale system. It also has a method to help
 * populate a vector of the menu items. 
 * @author Roee Belkin, Sam Brokaw
 */
public class Home_Page {  
    Vector<Order> orders = new Vector<Order>(); 
    ArrayList<Tuple> inventory = new ArrayList<Tuple>();
    ArrayList<String> entrees = new ArrayList<String>() {{
    }};
    ArrayList<String> protein = new ArrayList<String>() {{

    }};
    ArrayList<String> sides = new ArrayList<String>() {{

    }};

    /**
     * This method fills the entree, protein, and side arrays with 
     * all the possible options that are within the database.
     */
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
        try {
            sides.add("NONE"); 
            protein.add("NONE");  
            entrees.add("NONE"); 
            Statement stmt = conn.createStatement(); 
            String sqlQuery = "SELECT item_name FROM cabo_grill WHERE type = 'protein';";
            ResultSet result = stmt.executeQuery(sqlQuery); 
            while(result.next()){
                protein.add(result.getString("item_name"));
            }
            sqlQuery = "SELECT item_name FROM cabo_grill WHERE type = 'side';";
            result = stmt.executeQuery(sqlQuery); 
            while (result.next()){
                sides.add(result.getString("item_name"));
            }
            sqlQuery = "SELECT item_name FROM cabo_grill WHERE type = 'entree';";
            result = stmt.executeQuery(sqlQuery); 
            while (result.next()){
                entrees.add(result.getString("item_name"));
            }
            

        }
        catch (Exception e){
            e.printStackTrace();
            //System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            String sqlQuery = "Select item_name from cabo_grill where id > 0;";
            Statement stmt = conn.createStatement(); 
            ResultSet result = stmt.executeQuery(sqlQuery); 
            result = stmt.executeQuery(sqlQuery); 
            while (result.next()){
                inventory.add(new Tuple(result.getString("item_name"), 0));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            //System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }


    JFrame f = new JFrame("Home Page");
    /*
     * This constructor creates the main layout for the home page as well as the buttons to access the clerk and manager view.
     */
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
           
    /**
     * this main method creates an instance of the home page to be displayed as a GUI.
     * @param args[] Doesn't serve a purpose for this program
     */
    public static void main(String args[])  
    {  
        new Home_Page();  
    }  
}  