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
public class ModuleSubSystem
{
    private String Name;
    
    private String ModelPath;
    
    private boolean Active;
    
    private List<Long> Inputs;
    
    private List<Long> Outputs;
    
    private List<Long> Parameters;
    
    public ModuleSubSystem()
    {
        this.Name = "";
        this.ModelPath = "";
        this.Active = false;
        this.Inputs = new ArrayList<>();
        this.Outputs = new ArrayList<>();
        this.Parameters = new ArrayList<>();
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getModelPath()
    {
        return ModelPath;
    }

    public void setModelPath(String ModelPath)
    {
        this.ModelPath = ModelPath;
    }

    public boolean isActive()
    {
        return Active;
    }

    public void setActive(boolean Active)
    {
        this.Active = Active;
    }
    
    public List<Long> getInputs()
    {
        return Inputs;
    }

    public List<Long> getOutputs()
    {
        return Outputs;
    }

    public List<Long> getParameters()
    {
        return Parameters;
    }
    
    
}
