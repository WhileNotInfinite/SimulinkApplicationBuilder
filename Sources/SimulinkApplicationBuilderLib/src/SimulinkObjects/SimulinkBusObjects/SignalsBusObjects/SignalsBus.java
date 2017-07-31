/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects.SimulinkBusObjects.SignalsBusObjects;

import SimulinkObjects.SimulinkBusObjects.AbstractObjects.SimulinkBus;
import SimulinkObjects.SimulinkItemScope;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author braul
 */
public class SignalsBus extends SimulinkBus
{
    private List<SignalElement> Signals;
    
    public SignalsBus()
    {
        Signals=new ArrayList<>();
    }
    
    public SignalsBus(String Name, SimulinkItemScope Scope, String HFile, String Description)
    {
        super(Name, Scope, HFile, Description);
        Signals=new ArrayList<>();
    }

    public List<SignalElement> getSignals()
    {
        return Signals;
    }
}
