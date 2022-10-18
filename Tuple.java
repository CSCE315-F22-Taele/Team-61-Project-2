public class Tuple{
    public String side_name; 
    public Integer amount; 


    Tuple(){
        side_name = "";
        amount = 0; 
    }

    Tuple(String side, Integer amt){
        side_name = side; 
        amount = amt; 
    }
}