
package Adapter;

import Model.Automobile;


public final class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto{
    
    private Automobile myobj;

}
