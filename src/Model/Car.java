
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Exception.AutoException;

public class Car extends Automobile implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nameAuto;
    private float basePrice;
    private String make;
    private String model; 
    private ArrayList<OptionSet> optSets;
    private ArrayList <Option> choice;

    public Car() {
        this.optSets = new ArrayList<OptionSet>();
        this.choice = new ArrayList<Option>();
    }
    
    
    
     public String getMake(){
         return this.make;
     }

    public void setMake(String make){
        this. make = make;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public float getBasePrice() {
        return basePrice;
    }


    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }
    

    public ArrayList<OptionSet> getOptSets() {
        return optSets;
           
        
    }
// TO THINK: do I need This?
    public void setOptSets() {
    }

    //creates new OptionSet object (e.g. for Color, transmission, brakes set of options) and adds this object to array list  
    public void setOpts(String optSetName) {
        this.optSets.add(new OptionSet(optSetName));
    }
//creates new Option object (with name and price) and adds it to array list <Option>
    public void setOpt(int indexOfOptSet, String nameOfOpt, float priceOfOpt) {
        this.optSets.get(indexOfOptSet).getOptions().add(new Option(nameOfOpt, priceOfOpt));
    }

    public void setNameAuto(String nameAuto) {
        this.nameAuto = nameAuto;
        String manufacturer="";
        String modelname="";
        int counttoken = 0;
        StringTokenizer stName = new StringTokenizer(nameAuto);
        while (stName.hasMoreTokens()) {
            if (counttoken == 0) {
                manufacturer= stName.nextToken();
                
            } else{
                modelname += modelname;
            }
        }
        this.setMake(manufacturer);    
        this.setModel(modelname);
    }

    // method can also be used as "findName"
    public String getNameAuto() {
        return nameAuto;
    }

    public void printAuto() {
        System.out.println("Base price: " + this.getBasePrice());
        System.out.println("Model name: " + this.getNameAuto());
        if (this.optSets == null) {
            System.out.println("Options: null");
        } else {
            for (int g = 0; g < optSets.size(); g++) {
                System.out.println(this.getOptSets().get(g).toStringOpts() + " ");
            }
        }
    }

    public void deleteOptName(int indexOfOptSet, int indexOfOption) {
        this.optSets.get(indexOfOptSet).getOption(indexOfOption).setNameOpt(null);
    }

    public void deleteOptPrice(int indexOfOptSet, int indexOfOption) {
        this.optSets.get(indexOfOptSet).getOption(indexOfOption).setPriceOpt(0);
    }

    public void deleteOptionSet() {
        this.optSets = null;
    }


    public int findOptionSetNameIndex(String optSetNameToChange) {
        int indexInOptSets = -1;
        try{
        for (int h = 0; h < this.getOptSets().size(); h++) {
            if ((this.getOptSets().get(h).getNameOptSet()).equals(optSetNameToChange)) {
                indexInOptSets = h;
                break;
            }
        }
        if (indexInOptSets == -1) {
            String errorMessage = "Exception occurs. Wrong option set name";
            throw new AutoException(errorMessage, 100, this);
        }
        
        }catch (AutoException eFix) {
            System.out.println("Exception occurs " + eFix.getExceptionID() + " caught");
            
            
        }
        return indexInOptSets;
    }

    public void changeOptionSetName(String optSetOldName, String optSetNewName) {
        int indexOfSetOpts = this.findOptionSetNameIndex(optSetOldName);
        this.getOptSets().get(indexOfSetOpts).setNameOptSet(optSetNewName);

    }

    public int findOptionNameIndexByName(String optSetName, String optName) {
        int indexInOpt = -1;
        int indexOptSet = this.findOptionSetNameIndex(optSetName);
        int arraylistSize = this.getOptSets().get(indexOptSet).getOptions().size();
            for (int g = 0; g < arraylistSize; g++) {
                String optionsToCompare = " "+this.getOptSets().get(indexOptSet).getOptions().get(g).getNameOpt().replace(",", "");
                if (optionsToCompare.equals(optName)) {
                    indexInOpt = g;
                    return indexInOpt;
                }
            }
        if (indexInOpt == -1) {
            System.out.println("Invalid option name. Please, try again");
        }
   
        return indexInOpt;
    }
    // by given option name and option set index returns option index
    public int findOptionNameIndexByIndex(int optSetNameIndex, String optName) {        
        int indexInOpt = -1;
        int arraylistSize = this.getOptSets().get(optSetNameIndex).getOptions().size();
            for (int g = 0; g < arraylistSize; g++) {
                String readedOptname = " "+(this.getOptSets().get(optSetNameIndex).getOptions().get(g).getNameOpt()).replace(",", "");
                if (readedOptname.equals(optName)){             
                    indexInOpt = g;
                    break;
                }
            }
        if (indexInOpt == -1) {
            System.out.println("Invalid option name or index. Please, try again");
        }
        return indexInOpt;
    }
// may be used later for personal discount set up?
    public void changeOptionPrice(String optionsName, String optName, float newPrice){
        int indexSets = this.findOptionSetNameIndex(optionsName);
        int indexOpt = this.findOptionNameIndexByIndex(indexSets, optName);
        this.getOptSets().get(indexSets).getOptions().get(indexOpt).setPriceOpt(newPrice);       
    }
    
    
    //method that asks user to input preferable option name  from given options (1) color name; 2) transmission name; 3)...)
   //put all functionality here. Not created getOptionChoice() in OptionSet class
    public String getOptionChoice(String setName){
        System.out.println("Enter your preferable "+setName + " from the next options:");
        int indexOfChosenOptSet = findOptionSetNameIndex(setName);//find the "color option set index"
        System.out.println(this.getOptSets().get(indexOfChosenOptSet).toStringOpts());
        Scanner input = new Scanner(System.in);
        String optSetChosen = input.nextLine();
        this.setOptionChoice(setName, optSetChosen);
        return optSetChosen;
    }
//for given set (e.g. Color set, Transmission set) calls returns the price for particular option
    public int getOptionChoicePrice(String setName){
        int priceForOpt = 0;
        int indexOfChosenOptSet = findOptionSetNameIndex(setName);
        String chocedOptName = this.getOptionChoice(setName);
        int optIndex =  this.findOptionNameIndexByIndex(indexOfChosenOptSet, chocedOptName);
        priceForOpt = (int)this.getOptSets().get(indexOfChosenOptSet).getOptions().get(optIndex).getPriceOpt();
        return priceForOpt;
    }

//calculate total price for all options from choice + base price for asked vehicle model
    //calls method "getOptionChoicePrice" to get the price for each option
    public int getTotalPrice(){
        int totalPrice = (int)this.basePrice;
        for(int d=0; d< this.optSets.size(); d++){
        int chosenOptPrice = this.getOptionChoicePrice(this.optSets.get(d).getNameOptSet());
        
        totalPrice = totalPrice+ chosenOptPrice;
        }
        System.out.print("Total price for chosen options is $");
        return totalPrice;
    }
    
    
  //sets information about chosen option to choice array list
  //put all functionality here. Not created setOptionChoice(optionName: String) in OptionSet class
    public void setOptionChoice(String setName, String optionName){
        Option optAddToChoice = this.getOptSets().get(findOptionSetNameIndex(setName)).getOptions().get(findOptionNameIndexByName(setName, optionName));
        this.choice.add(optAddToChoice);
    }
    

}
