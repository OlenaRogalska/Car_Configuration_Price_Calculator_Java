package Driver;

import Adapter.BuildAuto;
import Adapter.ProxyAutomobile;
import Model.Automobile;
import Model.AutomobileSet;


public class Driver {

    public static void main(String[] args) {

        
       ProxyAutomobile autoOne = new BuildAuto();
        autoOne.buildAuto("/Users/elenarogalskaa/Documents/workspace/Car_Configuration_Price_Calculator_DeAnza/src/ModelOptionsDocument_NoException.txt");
        Automobile unknownTypeOne = autoOne.getAuto();  

        System.out.println();
        //autoOne.printAuto(carOneName);
        System.out.println(autoOne.setChoice());      
        
        ProxyAutomobile autoTwo = new BuildAuto();
        
       autoTwo.buildAuto("/Users/elenarogalskaa/Documents/workspace/CIS35B_Assignment3_extra/src/ModelOptionsDocument2.txt");
       Automobile unknownTypeTwo = autoTwo.getAuto();        
 
        System.out.println();
       // autoTwo.printAuto(carTwoName);
        System.out.println(autoTwo.setChoice());
        System.out.println();
        System.out.println("CarPark's number of models "+autoTwo.getVehiclePark().size());
        System.out.println();
        autoTwo.searchVehicle(" EZGO RXV");
    }
}

