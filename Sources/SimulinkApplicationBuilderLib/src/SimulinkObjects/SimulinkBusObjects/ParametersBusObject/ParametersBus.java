/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects.SimulinkBusObjects.ParametersBusObject;

import SimulinkApplicationProject.ApplicationData.ApplicationParameter;
import SimulinkObjects.SimulinkBusObjects.AbstractObjects.SimulinkBus;
import SimulinkObjects.SimulinkItemScope;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author braul
 */
public class ParametersBus extends SimulinkBus
{
    private List<ApplicationParameter> Parameters;

    public ParametersBus()
    {
        Parameters = new ArrayList<>();
    }
    
    public ParametersBus(String Name, SimulinkItemScope Scope, String HFile, String Description)
    {
        super(Name, Scope, HFile, Description);
        Parameters = new ArrayList<>();
    }
}
