package codechallengenulogy;


/**
 *
 * @author William Charles Addison Keizer
 * Note that I chose to not implement an array or hash table for the different types of products. 
 * I did this because I assumed that this would be the extent of NuPack. 
 * I made the markup percentages as fields so that it would be easy to change (if the prices changed) and 
 * to make the code more readable. 
 * 
 * I made calcTotalCostWithMarkup public because I was not sure if you wanted the value to appear as a string with the dollar sign
 * if you want a number value (for more computations) call calcTotalCostWithMarkup, if you want a string with a dollar sign
 * call calculateTotalCost
 * 
 * The other methods are accessor methods that provide more functionality 
 */
public final class NuPack {
    
    private static double foodMarkup = 13; 
    private static double electronicsMarkup = 2;
    private static double pharmaceuticalsMarkup = 7.5;
    private static double otherMarkup = 0; 
    private static double perPersonMarkup = 1.2; 
    
    
    private NuPack(){
        
    }
    
    public static String calculateTotalCost(String baseCost, String numberOfPeople, String type){
        double numBaseCost = Double.parseDouble(baseCost.substring(1));
        int index = numberOfPeople.indexOf("people"); 
        if(index == -1){
            index = numberOfPeople.indexOf("person"); 
        }
        int numPeople = Integer.valueOf(numberOfPeople.substring(0, index-1));
        
        Double cost = calcTotalCostWithMarkup(numBaseCost, numPeople, type); 
        return "$"+cost; 
    }
    
    public static double calcTotalCostWithMarkup(double baseCost, int numberOfPeople, String type){
       double flatPrice = baseCost + calcMarkup(baseCost, 5);
       double typeMarkup = calcMarkup(flatPrice, getTypeMarkupPercent(type));
       double personMarkup = calcMarkup(flatPrice, calcPersonMarkupPercent(numberOfPeople));
       double totalPrice = (double)(Math.round((float)(flatPrice + typeMarkup + personMarkup)*100)); 
       totalPrice = totalPrice/100; 
       return totalPrice; 
       
    }
    
    public static double calcMarkup(double baseCost, double markupPercent){
        double markupCost = baseCost * markupPercent/100;
        return  markupCost;
    }
    
    public static double calcPersonMarkupPercent(int numPeople){
        return perPersonMarkup*numPeople; 
    }
   
    public static double getTypeMarkupPercent(String type){
        if(type.equals("food")){
            return foodMarkup;
        }
        else if(type.equals("pharmaceuticals") || type.equals("drugs")){
            return pharmaceuticalsMarkup;
        }
        else if(type.equals("Electronics")){
            return electronicsMarkup; 
        }
        else{
            return otherMarkup;
        }
    }
}
