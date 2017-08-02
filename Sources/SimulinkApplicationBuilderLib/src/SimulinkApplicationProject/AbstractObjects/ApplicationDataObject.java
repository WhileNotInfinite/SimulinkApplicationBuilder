/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject.AbstractObjects;

/**
 *
 * @author braul
 */
public abstract class ApplicationDataObject
{
    private String Name;
    
    private String DataType;
    
    private double MinValue;
    
    private double MaxValue;
    
    private double InitialValue;
    
    private String Unit;
    
    private String Description;
    
    private long IdentiferKey;
    
    public ApplicationDataObject()
    {
        this.Name = "";
        this.DataType = "double";
        this.MinValue = Double.NEGATIVE_INFINITY;
        this.MaxValue = Double.POSITIVE_INFINITY;
        this.InitialValue = Double.NaN;
        this.Unit = "";
        this.Description = "";
        this.IdentiferKey = 0;
    }
    
    public ApplicationDataObject(String Name, String DataType, double Min, double Max, double Initial, String Unit, String Description)
    {
        this.Name = Name;
        this.DataType = DataType;
        this.MinValue = Min;
        this.MaxValue = Max;
        this.InitialValue = Initial;
        this.Unit = Unit;
        this.Description = Description;
        this.IdentiferKey = 0;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }
    
    public long getIdentiferKey()
    {
        return IdentiferKey;
    }

    public void setIdentiferKey(long IdentiferKey)
    {
        this.IdentiferKey = IdentiferKey;
    }
    
    
}
