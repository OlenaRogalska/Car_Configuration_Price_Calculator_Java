package Model;

import java.util.ArrayList;

public abstract class Automobile {

     public abstract String getMake();

    public abstract void setMake(String make);

    public abstract String getModel();

    public abstract void setModel(String model);

    public abstract float getBasePrice();

    public abstract void setBasePrice(float basePrice);

    public abstract ArrayList<OptionSet> getOptSets();
    
    public abstract void setOptSets();
  
    public abstract void setOpts(String optSetName);

    public abstract void setOpt(int indexOfOptSet, String nameOfOpt, float priceOfOpt);

    public  abstract void setNameAuto(String nameAuto);

    public abstract String getNameAuto();

    public abstract void printAuto();

    public abstract void deleteOptName(int indexOfOptSet, int indexOfOption);

    public abstract void deleteOptPrice(int indexOfOptSet, int indexOfOption);

    public abstract void deleteOptionSet();

    public abstract int findOptionSetNameIndex(String optSetNameToChange);

    public abstract void changeOptionSetName(String optSetOldName, String optSetNewName);

    public abstract int findOptionNameIndexByName(String optSetName, String optName);
    
    public abstract int findOptionNameIndexByIndex(int optSetNameIndex, String optName);

    public abstract void changeOptionPrice(String optionsName, String optName, float newPrice);
    
    public abstract String getOptionChoice(String setName);

    public abstract int getOptionChoicePrice(String setName);

    public abstract int getTotalPrice();

    public abstract void setOptionChoice(String setName, String optionName);
    

}

