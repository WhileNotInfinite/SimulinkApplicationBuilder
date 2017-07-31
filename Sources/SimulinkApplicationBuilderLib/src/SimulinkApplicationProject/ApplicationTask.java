/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author braul
 */
public class ApplicationTask
{
    private String Name;
    
    private ApplicationTaskCallingMode CallingMode;
   
    private double CycleTime;
    
    private final List<TaskModule> Modules;
    
    public ApplicationTask()
    {
        Name = "";
        CallingMode= ApplicationTaskCallingMode.BACKGROUND;
        CycleTime = 0;
        Modules = new ArrayList<>();
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public ApplicationTaskCallingMode getCallingMode()
    {
        return CallingMode;
    }

    public void setCallingMode(ApplicationTaskCallingMode CallingMode)
    {
        this.CallingMode = CallingMode;
    }

    public double getCycleTime()
    {
        return CycleTime;
    }

    public void setCycleTime(double CycleTime)
    {
        this.CycleTime = CycleTime;
    }

    public List<TaskModule> getModules()
    {
        return Modules;
    }
    
    
}
