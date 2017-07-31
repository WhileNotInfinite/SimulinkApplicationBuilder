/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects.SimulinkBusObjects.AbstractObjects;

import SimulinkObjects.SimulinkItemScope;

/**
 *
 * @author braul
 */
public abstract class SimulinkBus
{
    private String Name;
    
    private SimulinkItemScope Scope;
    
    private String HeaderFile;
    
    private String Description;
    
    public SimulinkBus()
    {
        Name = "";
        Scope = SimulinkItemScope.AUTO;
        HeaderFile = "";
        Description = "";
    }
    
    public SimulinkBus(String Name, SimulinkItemScope Scope, String HFile, String Description)
    {
        this.Name = Name;
        this.Scope = Scope;
        this.HeaderFile = HFile;
        this.Description = Description;
    }
}
