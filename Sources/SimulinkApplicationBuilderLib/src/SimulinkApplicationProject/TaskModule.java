/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject;

import SimulinkObjects.ModuleSubSystem;
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
    
    
    
}
