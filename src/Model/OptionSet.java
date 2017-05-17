package Model;

import java.io.Serializable;
import java.util.ArrayList;




public class OptionSet implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    private String nameOptSet;
    private ArrayList <Option> opts; 

    //changed all methods to public
    
    public OptionSet(){}
       
    public OptionSet(String nameOptSet){
        this.opts =  new ArrayList <Option>();
        this.nameOptSet = nameOptSet;
    }
    
    public void setOption(String nameOfOpt, float priceOfOpt){
        opts.add(new Option (nameOfOpt, priceOfOpt));
    }
    
    public ArrayList <Option> getOptions(){
        return opts;
    }
    
    public String getNameOptSet() {
        return nameOptSet;
    }

    public void setNameOptSet(String nameOptSet) {
        this.nameOptSet = nameOptSet;
    }

    public Option getOption(int indexOfOptionInOptions){
        Option pickedOption = this.opts.get(indexOfOptionInOptions);
        return pickedOption;
    }
    
    public String toStringOpts(){
        String printoptname = this.getNameOptSet() ;
    StringBuffer printopt = new StringBuffer();
        for(int j=0; j<opts.size(); j++){
            printopt.append(opts.get(j).toStringOpt());
        }
        return printoptname + printopt;
    }
}
    