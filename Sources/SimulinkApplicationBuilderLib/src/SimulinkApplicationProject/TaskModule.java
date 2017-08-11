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
public class TaskModule
{
    private String Name;
    
    private final List<ModuleSubSystem> Models;
    
    public TaskModule()
    {
        Name = "";
        Models = new ArrayList<>();
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public List<ModuleSubSystem> getModels()
    {
        return Models;
    }
    
    public String getActiveModelName()
    {
        for (ModuleSubSystem oSubSystem : Models)
        {
            if (oSubSystem.isActive())
            {
                return (oSubSystem.getName());
            }
        }

        return ("");
    }
    
    public ModuleSubSystem getSubSystemByName(String ModelName)
    {
        for (ModuleSubSystem oSubSystem : Models)
        {
            if (oSubSystem.getName().equals(ModelName))
            {
                return (oSubSystem); //Assuumes that active model is unique into the module
            }
        }

        return (null);
    }
}
