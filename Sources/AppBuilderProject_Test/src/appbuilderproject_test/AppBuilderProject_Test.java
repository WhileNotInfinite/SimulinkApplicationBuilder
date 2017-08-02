/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbuilderproject_test;

import SimulinkApplicationProject.ApplicationData.ApplicationParameter;
import SimulinkApplicationProject.ApplicationData.ApplicationSignal;
import SimulinkApplicationProject.ApplicationProject;
import SimulinkApplicationProject.ApplicationTask;
import SimulinkApplicationProject.ApplicationTaskCallingMode;
import SimulinkApplicationProject.TaskModule;
import SimulinkObjects.ModuleSubSystem;

/**
 *
 * @author braul
 */
public class AppBuilderProject_Test
{
    private static ApplicationProject oProject;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String ProjectFile = "C:\\Users\\BRAUL\\Documents\\NetBeansProjects\\SimulinkApplicationBuilder\\Demo\\EngineControl\\Engine.xml";
        String ProjectFile2 = "C:\\Users\\BRAUL\\Documents\\NetBeansProjects\\SimulinkApplicationBuilder\\Demo\\EngineControl\\Engine2.xml";
        
        createTestProject();
        System.out.println("Application project class instancied !");
        try
        {
            oProject.writeProjectXml(ProjectFile);
            System.out.println("Project XML file created !");
            
            oProject = new ApplicationProject();
            oProject.readProjectXml(ProjectFile);
            System.out.println("Project XML file loaded !");
            
            oProject.writeProjectXml(ProjectFile2);
            System.out.println("Project XML file 2 created !");
            
        } catch (Exception e)
        {
            System.out.println("XML file creation failed !");
            System.out.println(e.getStackTrace());
        }
        
    }
    
    private static void createTestProject()
    {
        ApplicationTask oTask;
        TaskModule oModule;
        ModuleSubSystem oSubSystem;

        oProject = new ApplicationProject();

        oProject.setName("EngineControlTestProject");
        
        //Create application signals collection
        oProject.getApplicationSignals().add(new ApplicationSignal("Rev", "Single", 0, 9000, 0, "rpm", "Engine speed"));
        oProject.getApplicationSignals().add(new ApplicationSignal("ThrPos", "Single", 0, 100, 0, "%", "Throttle position"));
        oProject.getApplicationSignals().add(new ApplicationSignal("WaterTemp", "Single", -50, 150, 20, "°C", "Water temperature"));
        oProject.getApplicationSignals().add(new ApplicationSignal("AirTemp", "Single", -50, 50, 20, "°C", "Intake air temperature"));
        oProject.getApplicationSignals().add(new ApplicationSignal("InjectorStates", "uint8", 0, 15, 0, "", "Injectors status bits field"));
        oProject.getApplicationSignals().add(new ApplicationSignal("FuelPressure", "Single", 0, 500, 0, "bar", "Fuel pressure"));
        oProject.getApplicationSignals().add(new ApplicationSignal("FuelPumpError", "boolean", 0, 1, 0, "", "Fuel pump error flag"));
        
        oProject.getApplicationSignals().add(new ApplicationSignal("TInj1", "Single", 0, 100, 0, "ms", "Injection time for injector 1"));
        oProject.getApplicationSignals().add(new ApplicationSignal("TInj2", "Single", 0, 100, 0, "ms", "Injection time for injector 2"));
        oProject.getApplicationSignals().add(new ApplicationSignal("TInj3", "Single", 0, 100, 0, "ms", "Injection time for injector 3"));
        oProject.getApplicationSignals().add(new ApplicationSignal("TInj4", "Single", 0, 100, 0, "ms", "Injection time for injector 4"));
        oProject.getApplicationSignals().add(new ApplicationSignal("InjPhase", "Single", -720, 720, 0, "°", "Injection phase"));
        oProject.getApplicationSignals().add(new ApplicationSignal("MInj", "Single", 0, 100, 0, "mg", "Fuel mass to be injected"));
        oProject.getApplicationSignals().add(new ApplicationSignal("TWaterCorr", "Single", 0, 2, 1, "", "Water temperature correction factor"));
        oProject.getApplicationSignals().add(new ApplicationSignal("TAirCorr", "Single", 0, 2, 1, "", "Air temperature correction factor"));
        
        //Create application parameter collection
        oProject.getApplicationParameters().add(new ApplicationParameter(12, 10, 1, "PhaseMap", "Single", -720, 720, 0, "°", "Injection phase angle map"));
        oProject.getApplicationParameters().add(new ApplicationParameter(12, 10, 1, "MinjMap", "Single", 0, 720, 100, "mg", "Injection quantity map"));
        oProject.getApplicationParameters().add(new ApplicationParameter(10, 1, 1, "TWaterCorrectionMap", "Single", 0, 2, 1, "", "Injection quantity correction on water temp map"));
        oProject.getApplicationParameters().add(new ApplicationParameter(10, 1, 1, "TAirCorrectionMap", "Single", 0, 2, 1, "", "Injection quantity correction on air temp map"));
        oProject.getApplicationParameters().add(new ApplicationParameter(1, 1, 1, "FuelPressureFactor", "Single", 0, 10, 0.1, "", "Injection Quantity to time fuel pressure factor"));
        
        //Add tasks to the application
        oTask = new ApplicationTask();
        oTask.setName("EngineControl1msTask");
        oTask.setCallingMode(ApplicationTaskCallingMode.CYCLIC);
        oTask.setCycleTime(0.001); //1ms

        oModule = new TaskModule();
        oModule.setName("Injection");

        oSubSystem = new ModuleSubSystem();
        oSubSystem.setName("Injection_V1");
        
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("Rev"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("ThrPos"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("WaterTemp"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("AirTemp"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("InjectorStates"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("FuelPressure"));
        oSubSystem.getInputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("FuelPumpError"));
        
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TInj1"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TInj2"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TInj3"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TInj4"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("InjPhase"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("MInj"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TWaterCorr"));
        oSubSystem.getOutputs().add(oProject.getApplicationSignals().getSignalIdentifierKey("TAirCorr"));
        
        oSubSystem.getParameters().add(oProject.getApplicationParameters().getParameterIdentifierKey("PhaseMap"));
        oSubSystem.getParameters().add(oProject.getApplicationParameters().getParameterIdentifierKey("MinjMap"));
        oSubSystem.getParameters().add(oProject.getApplicationParameters().getParameterIdentifierKey("TWaterCorrectionMap"));
        oSubSystem.getParameters().add(oProject.getApplicationParameters().getParameterIdentifierKey("TAirCorrectionMap"));
        oSubSystem.getParameters().add(oProject.getApplicationParameters().getParameterIdentifierKey("FuelPressureFactor"));
        
        oModule.getModels().add(oSubSystem);

        oTask.getModules().add(oModule);

        oProject.getTasks().add(oTask);

    }
   
}
