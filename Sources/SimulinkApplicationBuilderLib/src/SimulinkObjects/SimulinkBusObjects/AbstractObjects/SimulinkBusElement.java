/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects.SimulinkBusObjects.AbstractObjects;

/**
 *
 * @author braul
 */
public abstract class SimulinkBusElement
{
    private String Name;
    
    private String DataType;
    
    private double MinValue;
    
    private double MaxValue;
    
    private double InitialValue;
    
    private String Unit;
    
    private String Description;
    
    public SimulinkBusElement()
    {
        Name = "";
        DataType = "double";
        MinValue = Double.NEGATIVE_INFINITY;
        MaxValue = Double.POSITIVE_INFINITY;
        InitialValue = Double.NaN;
        Unit = "";
        Description = "";

    }
    
    public SimulinkBusElement(String Name, String DataType, double Min, double Max, double Initial, String Unit, String Description)
    {
        this.Name = Name;
        this.DataType = DataType;
        this.MinValue = Min;
        this.MaxValue = Max;
        this.InitialValue = Initial;
        this.Unit = Unit;
        this.Description = Description;
    }
}
