package Util;

import java.io.*;
import java.util.*;

import Adapter.ProxyAutomobile;
import Exception.AutoException;
import Model.Automobile;
import Model.Car;
import Model.GolfCart;

public class FileIO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Automobile auto;

    public FileIO() {}
    
    
    public void setAuto(Automobile vehicle){
        this.auto = vehicle;
    }

    public Automobile readFile(String fileName) throws AutoException {

        String vehicleType = "";
        String carModel = "";
        float priceAtribute = 0;
        int countOptionLines = 0;
        int countOptionBlock = 0;
        int counttoken;
        int exceptionCount = 0;
        int exceptionID = 0;
       //Automobile auto = null;
        
        try {
            FileReader input = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(input);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } 
                else if(line.contains("Vehicle Type") && countOptionLines==0){
                    //if yes, find out vehicle type and instantiate Car of GolfCart class
                        counttoken = 0;
                       // int lineTypeNameLength = this.countLineTokens(line);
                        StringTokenizer stColon = new StringTokenizer(line);
                        while (stColon.hasMoreTokens()) {
                            if (counttoken == 0 || counttoken == 1) {
                                stColon.nextToken();
                                counttoken++;
                            } else {
                                vehicleType = vehicleType  + " " + stColon.nextToken();
                                counttoken++;
                            } 
                        }
                            if(vehicleType.equals(" Car")){
                               
                               Automobile vehicle = new Car();
                                this.setAuto(vehicle);
                                continue;
                            }
                            else if (vehicleType.equals(" GolfCart")){
                                Automobile vehicle = new GolfCart();
                                this.setAuto(vehicle);
                                continue;
                            }
                            //TODO later: create new exception type that asks input the type of vehicle
                            else{
                                System.out.println("Programmer must create new error type and througth exception"); 
                            }                            
                        }
                else{
                    try {
                        // #1 check if there is empty line in file
                        if (line.equals("")) {
                            exceptionCount++;
                            exceptionID = 1;
                            String errorMessage = "Exception occures " + exceptionCount + " times. Empty Line";
                            throw new AutoException(errorMessage, exceptionID, auto);                                     
                    } else if (line.contains("Model")) {
                            counttoken = 0;
                            int lineModelNameLength = this.countLineTokens(line);
                            
                            // #3 check if model name is less then two words
                            
                            if (lineModelNameLength <=1) {
                                exceptionCount++;
                                exceptionID = 3;
                                String errorMessage = "Exception occurs " + exceptionCount
                                        + " times. Model name missed the part of name";
                                throw new AutoException(errorMessage, exceptionID, auto);
                            }
                            
                            StringTokenizer strColon = new StringTokenizer(line);
                            while (strColon.hasMoreTokens()) {
                                if (counttoken == 0) {
                                    strColon.nextToken();
                                } else if (counttoken < lineModelNameLength - 1 && counttoken != 0) {
                                    carModel = carModel + " " + strColon.nextToken();
                                } else if (counttoken >= lineModelNameLength - 1) {
                                    carModel = carModel + " " + strColon.nextToken();
                                    this.auto.setNameAuto(carModel);
                                }
                                counttoken++;
                            }
                        } else if (line.contains("Base Price")) {
                            counttoken = 0;
                            int lineBasePriseLength = this.countLineTokens(line);
                            StringTokenizer strColon = new StringTokenizer(line);
                            while (strColon.hasMoreTokens()) {
                                if (counttoken < lineBasePriseLength - 1) {
                                    strColon.nextToken();
                                } else if (counttoken >= lineBasePriseLength - 1) {
                                    //#4 check if price written as number or as a word
                                    String curentTockenToCheck = strColon.nextToken();
                                    if (this.notANumber(curentTockenToCheck)) {
                                        exceptionCount++;
                                        exceptionID = 4;
                                        String errorMessage = "Exception occurs " + exceptionCount
                                                + " times. Price written as word, not as number";
                                        throw new AutoException(errorMessage, exceptionID, auto);
                                    } else {
                                        priceAtribute = Float.parseFloat(curentTockenToCheck);
                                        this.auto.setBasePrice(priceAtribute);
                                    }
                                }
                                counttoken++;
                            }
                            // #5 check if base price not assigned
                            if (this.auto.getBasePrice() == 0.0) {
                                exceptionCount++;
                                exceptionID = 5;
                                String errorMessage = "Exception occurs " + exceptionCount
                                        + " times. Price written as word, not as number";
                                throw new AutoException(errorMessage, exceptionID, this.auto);
                            }
                        } 

                        else if (line.contains("Option")) {
                            counttoken = 0;
                            String optionSetName = "";
                            int optionSetSize = 0;
                            int lineOptionsLength = this.countLineTokens(line);
                            counttoken = 0;
                            countOptionLines = 0;
                            StringTokenizer strColon = new StringTokenizer(line);
                            while (strColon.hasMoreTokens()) {
                                if (counttoken < lineOptionsLength - 1) {
                                    optionSetName = optionSetName + " " + strColon.nextToken();
                                } else if (counttoken >= lineOptionsLength - 1) {
                                    optionSetSize = Integer.parseInt(strColon.nextToken());
                                }
                                counttoken++;
                            }
                            this.auto.setOpts(optionSetName);
                            countOptionBlock++;
                        } 
                        else if (line.contains("Vehicle Type")) {
                            countOptionLines++;
                        } else {
                            counttoken = 0;
                            String optionName = "";
                            float optionPrice = 0;
                            int lineOptionLength = this.countLineTokens(line);
                            StringTokenizer strColon = new StringTokenizer(line);
                            while (strColon.hasMoreTokens()) {
                                if (counttoken < lineOptionLength - 1) {
                                    optionName = optionName + " " + strColon.nextToken();
                                } else if (counttoken >= lineOptionLength - 1) {
                                    // #6 check if price written as number or as a word
                                    String curentTockenToCheckPrice = strColon.nextToken();
                                    if (this.notANumber(curentTockenToCheckPrice)) {
                                        exceptionCount++;
                                        exceptionID = 6;
                                        String errorMessage = "Exception occurs " + exceptionCount
                                                + " times. Price written as word, not as number";
                                        throw new AutoException(errorMessage, exceptionID, this.auto);
                                    }
                                        optionPrice = Float.parseFloat(curentTockenToCheckPrice);
                                }
                                    counttoken++;
                                }
                            this.auto.setOpt(countOptionBlock - 1, optionName, optionPrice);
                                countOptionLines++;
                            }
                    } catch (AutoException eFix) {
                        System.out.println("Exception occurs " + eFix.getExceptionID() + " caught");
                  
                    }

                }
            }
            buff.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return this.auto;
    }


    public boolean notANumber(String priceWrittenAs) {
        boolean priceNotNumeric = false;
        try {
            Double.parseDouble(priceWrittenAs);
        } catch (NumberFormatException nfe) {
            priceNotNumeric = true;
        }
        return priceNotNumeric;
    }

    public String serializeAuto(ProxyAutomobile staticAuto, String nameSerilObject) {
        try (FileOutputStream fileOut = new FileOutputStream(nameSerilObject + ".ser")) {
            ObjectOutputStream serialize = new ObjectOutputStream(fileOut);
            serialize.writeObject(staticAuto);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Object failed serialization. Error: " + e);
        }
        return nameSerilObject + ".ser";
    }

    public int countLineTokens(String toCount) {
        int amoundOfTokens = 0;
        StringTokenizer stColon = new StringTokenizer(toCount);
        while (stColon.hasMoreTokens()) {
            stColon.nextToken();
            amoundOfTokens++;
        }
        return amoundOfTokens;
    }

    public ProxyAutomobile deserializeAuto(String serializedFile) {
        ProxyAutomobile deserialized = null;
        try (FileInputStream fileIn = new FileInputStream(serializedFile)) {
            ObjectInputStream deserialize = new ObjectInputStream(fileIn);
            deserialized = (ProxyAutomobile) deserialize.readObject();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return deserialized;
    }

}
