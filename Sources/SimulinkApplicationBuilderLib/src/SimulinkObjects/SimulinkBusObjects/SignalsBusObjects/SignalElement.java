/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects.SimulinkBusObjects.SignalsBusObjects;

import SimulinkObjects.SimulinkBusObjects.AbstractObjects.SimulinkBusElement;

/**
 *
 * @author braul
 */
public class SignalElement extends SimulinkBusElement
{
    public SignalElement()
    {
        
    }
    
    public SignalElement(String Name, String DataType, double Min, double  Max, double Initial, String Unit, String Description)
    {
        super(Name, DataType, Min, Max, Initial, Unit, Description);
    }
}
