/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkObjects;

import SimulinkObjects.SimulinkBusObjects.ParametersBusObject.ParametersBus;
import SimulinkObjects.SimulinkBusObjects.SignalsBusObjects.SignalsBus;
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
    
    private List<SignalsBus> Inputs;
    
    private SignalsBus Outputs;
    
    private SignalsBus Logging;
    
    private ParametersBus Calibrations;
    
    private List<ParametersBus> ExternalCalibrations;
    
    public ModuleSubSystem()
    {
        Name = "";
        ModelPath = "";
        Active = false;
        Inputs = new ArrayList<>();
        Outputs = new SignalsBus();
        Logging = new SignalsBus();
        Calibrations = new ParametersBus();
        ExternalCalibrations = new ArrayList<>();
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

    public List<SignalsBus> getInputs()
    {
        return Inputs;
    }

    public void setInputs(List<SignalsBus> Inputs)
    {
        this.Inputs = Inputs;
    }

    public SignalsBus getOutputs()
    {
        return Outputs;
    }

    public void setOutputs(SignalsBus Outputs)
    {
        this.Outputs = Outputs;
    }

    public SignalsBus getLogging()
    {
        return Logging;
    }

    public void setLogging(SignalsBus Logging)
    {
        this.Logging = Logging;
    }

    public ParametersBus getCalibrations()
    {
        return Calibrations;
    }

    public void setCalibrations(ParametersBus Calibrations)
    {
        this.Calibrations = Calibrations;
    }

    public List<ParametersBus> getExternalCalibrations()
    {
        return ExternalCalibrations;
    }

    public void setExternalCalibrations(List<ParametersBus> ExternalCalibrations)
    {
        this.ExternalCalibrations = ExternalCalibrations;
    }
    
    
}
