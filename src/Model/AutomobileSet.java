package Model;

import java.util.LinkedHashMap;

public class AutomobileSet<T> {

    public AutomobileSet() {
    }

    public LinkedHashMap<String, T> autoPark = new LinkedHashMap<String, T>();

    public LinkedHashMap<String, T> getAutoPark() {
        return this.autoPark;
    }

    public void removeCarFromAutopark(String modelName) {
        int parkSizeBeforeRemove = this.autoPark.size();
        this.autoPark.remove(modelName);
        if (this.autoPark.size() != parkSizeBeforeRemove - 1) {
            System.out.println("Prosper error: car not removed");
        }
    }

    // method from ProxyAuto added and modified
    public void addCarToAutopark(T a, String name) {
        int parkSizeBeforeAdd = this.autoPark.size();
        //System.out.println(parkSizeBeforeAdd+" - parkSizeBeforeAdd; "+ this.autoPark.size() +" - this.autoPark.size()");
        System.out.println(name);
        this.autoPark.put(name, a);
        if (this.autoPark.size() != parkSizeBeforeAdd + 1) {
            System.out.println(
                    "Prosper error: new car not added" + " " + this.autoPark.size() + "!=" + (parkSizeBeforeAdd + 1));
        }
    }

    public void update(String oldModelName, String newModelName, T newT) {
        try {
            T autoBeforeUpdate = this.autoPark.get(oldModelName);
            removeCarFromAutopark(oldModelName);
            this.autoPark.put(newModelName, newT);
        } catch (NullPointerException e) {
            System.out.println("No such element in the auto Park");
        }
        
    }

}
