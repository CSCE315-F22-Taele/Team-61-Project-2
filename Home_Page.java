import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*; 

import java.util.*; 

import java.util.ArrayList;


import java.util.ArrayList;

public class Home_Page {  
    
    ArrayList<String> entrees = new ArrayList<String>() {{
        add("None");
        add("All");
        add("bowl");
        add("burrito");
        add("tacos");
    }};
    ArrayList<String> protein = new ArrayList<String>() {{
        add("None");
        add("All");
        add("chicken");
        add("steak");
        add("beef");
        add("vegetable medley");
    }};
    ArrayList<String> sides = new ArrayList<String>() {{
        add("None");
        add("chips_and_salsa");
        add("chips_and_queso");
        add("chips_and_guac");
        add("drink");
    }};

    JFrame f = new JFrame("Home Page");

    Vector<Order> orders = new Vector<Order>(); 
    Inventory inventory = new Inventory();   

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
                        new Clerk_Home_Page(false); 
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
           
    public static void main(String args[])  
    {  
        new Home_Page();  
    }  

    public void add_order(Order o){
        System.out.println("add order running");
        orders.add(o);
        System.out.println(orders.firstElement().Entree);
    }

    public void set_protein(String p){
        if(!orders.isEmpty()){
            orders.lastElement().Protein = p;
        }
    }

    public void main_cost_update(){
        if(!orders.isEmpty()){
            orders.lastElement().update_cost();
        }
    }

    public void remove(){
        if(!orders.isEmpty()){
            orders.remove(orders.size() -1);
        }
    }

    public void setGuac(int i){
        if(!orders.isEmpty()){
            orders.lastElement().guac = i;
        }
    }
    
    public void setSalsa(int i){
        if(!orders.isEmpty()){
            orders.lastElement().salsa = i;
        }
    }

    public void setQueso(int i){
        if(!orders.isEmpty()){
            orders.lastElement().queso = i;
        }
    }

    public void setDrink(int i){
        if(!orders.isEmpty()){
            orders.lastElement().drink = i;
        }
    }

    public String get_entree() {
        if(!orders.isEmpty()){
            return orders.lastElement().Entree;
        }
        return "null";
    }

    public float get_order_total(){
        if(!orders.isEmpty()){
            return orders.lastElement().cost;
        }
        return 0;
    }

    public void main_print(){
        orders.lastElement().print();
    }

}