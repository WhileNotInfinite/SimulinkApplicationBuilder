/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject.ApplicationData;

import SimulinkApplicationProject.AbstractObjects.ApplicationDataObject;

/**
 *
 * @author braul
 */
public class ApplicationSignal extends ApplicationDataObject
{
    public ApplicationSignal()
    {
        
    }
    
    public ApplicationSignal(String Name, String DataType, double Min, double  Max, double Initial, String Unit, String Description)
    {
        super(Name, DataType, Min, Max, Initial, Unit, Description);
    }
}
