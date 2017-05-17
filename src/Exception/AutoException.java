package Exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import Adapter.FixAuto;
import Model.Automobile;
import Model.Car;
import Model.GolfCart;

public class AutoException extends Exception implements FixAuto {

    private static final long serialVersionUID = 1L;
    private int exceptionID;
    private Automobile auto1;

    public AutoException(String message, int exceptionID, Automobile auto1) {
        super(message);
        this.exceptionID = exceptionID;
        System.out.println("Recorded exception: " + exceptionID);
        this.auto1 = auto1;
        this.updateLogFile(message);
        this.fix(exceptionID);
    }

    public int getExceptionID() {
        return exceptionID;
    }

    public Automobile getAuto1() {
        return auto1;
    }

    
    
    public void updateLogFile(String exceptionInfo) {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        try (FileWriter fileWritter = new FileWriter(
                "/Users/elenarogalskaa/Documents/workspace/CIS35B_Assignment2/src/Exception/ExceptionLog/", true)) {
            try (BufferedWriter bufferWritter = new BufferedWriter(fileWritter)) {        
                exceptionInfo= exceptionInfo+ts;
                bufferWritter.append(exceptionInfo); 
                bufferWritter.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Can't write exception to log");
        }
    }

    public void fix(int errorNumber) {
        String correctValue = "";
        Fix1to100 fixObject = new Fix1to100();
        switch (errorNumber) {
        case 1:
            correctValue = fixObject.fix1(errorNumber);
            break;
        case 2:
            correctValue = fixObject.fix2(errorNumber);
            break;
        case 3:
            correctValue = fixObject.fix3(errorNumber);
            auto1.setBasePrice(Float.parseFloat(correctValue));
            break;
        case 4:
            correctValue = fixObject.fix4(errorNumber);
            break;
        case 5:
            correctValue = fixObject.fix5(errorNumber);
            break;
        }
    }

}
