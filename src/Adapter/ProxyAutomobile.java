package Adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Exception.AutoException;
import Model.Automobile;
import Model.AutomobileSet;
import Util.FileIO;

public abstract class ProxyAutomobile {
    
    
    private Automobile a1;
    private static AutomobileSet vehiclePark = new AutomobileSet();
     
   
    public Automobile getAuto(){
        return a1;
    }
    
    //
    public void addCarToAutopark(Automobile a){
        //System.out.println(a.getNameAuto());
        int parkSizeBeforeAdd = vehiclePark.autoPark.size();
        //System.out.println("parkSizeBeforeAdd "+ parkSizeBeforeAdd);
        //vehiclePark.autoPark.put(this.getModelName(), this.getAuto());
        vehiclePark.addCarToAutopark(a, a.getNameAuto());
        //System.out.println("vehiclePark.autoPark.size() "+ vehiclePark.autoPark.size());
        if(vehiclePark.autoPark.size() != parkSizeBeforeAdd+1){
            System.out.println("Prosper error: new car "+ a.getNameAuto()+" not added");
        }
    }
    
    public LinkedHashMap<String, Automobile> getVehiclePark(){
        return vehiclePark.autoPark;
    }
    
    
    public Automobile searchVehicle(String modelName){
        Automobile currentVehicle=null;
        Set<Entry<String, Automobile>> setFromMap  = vehiclePark.autoPark.entrySet();
        Iterator<Entry<String, Automobile>> itrSet = setFromMap.iterator();
        while(itrSet.hasNext()){
            Map.Entry<String, Automobile> itrEntry = (Map.Entry<String, Automobile>)itrSet.next();
            if(itrEntry.getKey().equals(modelName)){
                currentVehicle = itrEntry.getValue();
                System.out.println(itrEntry.getKey()+" model's paramters are");
                ((Automobile) currentVehicle).printAuto();
                return currentVehicle;
            }
      }
        System.out.println( currentVehicle);
        return currentVehicle;
    }
    
   
    
    public void buildAuto(String fileName){
        FileIO fileio = new FileIO();
        try {
            a1 = fileio.readFile(fileName);
            //System.out.println(a1.getNameAuto() + " a1");
            this.addCarToAutopark(a1);
        } catch (AutoException e) {
            System.out.println("File cann't be open");
            e.printStackTrace();
        }
    }

    public void updateOptionSetName(String modelName, String optionSetname, String newName){
        a1.changeOptionSetName(optionSetname, newName);
    }


    public void updateOptionPrice(String modelName, String optionName, String option, float newPrice){
        a1.changeOptionPrice(optionName, option, newPrice);
    }
    
    public void printAuto(String modelName){
        a1.printAuto();
    }
    
    public int setChoice(){
        int priceForChoice = a1.getTotalPrice();
        return priceForChoice;
    }

    public String getModelName(){
        return a1.getNameAuto();
    }
}
