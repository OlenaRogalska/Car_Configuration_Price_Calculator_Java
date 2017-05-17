package Model;

import java.io.Serializable;

class Option implements Serializable{

    private static final long serialVersionUID = 1L;
    private String nameOpt;
    private float priceOpt;
    //changed all methods to public
    public Option(){}
    
    public Option(String nameOpt, float priceOpt){
        this.nameOpt = nameOpt;
        this.priceOpt = priceOpt;
    }
    
    public void setNameOpt(String nameOpt) {
        this.nameOpt = nameOpt;
    }

    public void setPriceOpt(float priceOpt) {
        this.priceOpt = priceOpt;
    }

    public String getNameOpt() {
        return nameOpt;
    }

    public float getPriceOpt() {
        return priceOpt;
    }
    
    public String toStringOpt(){
        String optDescription = " " + this.getNameOpt()+ " $"+Float.toString(this.getPriceOpt())+";";
        return optDescription;

    }

    public void toStringOptsName(){
        String listOfNames = this.getNameOpt();
        System.out.println(listOfNames);
        
    }

}
