/**
 * This class is used to create an array of tuples within the Home_Page to store each side with it's corresponding amount
 * @author Roee Belkin
 */
public class Tuple{
    public String side_name; 
    public Integer amount; 

    /**
     * The tuple constructor initalizes the attributes with their default values of an empty string for the side name and 0 for the price.
     */
    Tuple(){
        side_name = "";
        amount = 0; 
    }

    /**
     * This constructor is used to create Tuple objects for a specific side with its' corresponding price.
     * @param side the name of the side to be passed into the constructor
     * @param amt  the corresponding price of the side 
     */
    Tuple(String side, Integer amt){
        side_name = side; 
        amount = amt; 
    }
}