package Exception;

import java.util.Scanner;

public class Fix1to100 {

    public String fix1(int errorNumberOne) {
        String correctValue1 = "fix1 value";
        return correctValue1;

    }

    public String fix2(int errorNumberTwo) {
        String correctedBasePrice = "fix2 value";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input price by using numbers only");
        correctedBasePrice = keyboard.nextLine();       
        return correctedBasePrice;
    }
    public String fix3(int errorNumberTwo) {
        String correctedOptionPrice = "fix3 value";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input price by using numbers only");
        correctedOptionPrice = keyboard.nextLine();       
        return correctedOptionPrice;
    }

    public String fix4(int errorNumberTwo) {
        String correctValue4 = "fix4 value";
        return correctValue4;
    }

    public String fix5(int errorNumberTwo) {
        String correctValue5 = "fix5 value";
        return correctValue5;
    }

  

}
