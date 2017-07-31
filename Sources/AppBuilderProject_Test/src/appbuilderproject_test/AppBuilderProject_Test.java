/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appbuilderproject_test;

import SimulinkApplicationProject.ApplicationProject;
import SimulinkApplicationProject.ApplicationTask;
import SimulinkApplicationProject.ApplicationTaskCallingMode;
import SimulinkApplicationProject.TaskModule;
import SimulinkObjects.ModuleSubSystem;
import SimulinkObjects.SimulinkBusObjects.SignalsBusObjects.SignalElement;
import SimulinkObjects.SimulinkBusObjects.SignalsBusObjects.SignalsBus;
import SimulinkObjects.SimulinkItemScope;

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

        //Add tasks to the application
        oTask = new ApplicationTask();
        oTask.setName("EngineControl1msTask");
        oTask.setCallingMode(ApplicationTaskCallingMode.CYCLIC);
        oTask.setCycleTime(0.001); //1ms

        oModule = new TaskModule();
        oModule.setName("Injection");

        oSubSystem = new ModuleSubSystem();
        oSubSystem.setName("Injection_V1");
        
        SignalsBus InputBus1 = new SignalsBus("EngineSensors", SimulinkItemScope.AUTO, "", "Engine sensors data");
        InputBus1.getSignals().add(new SignalElement("Rev", "Single", 0, 9000, 0, "rpm", "Engine speed"));
        InputBus1.getSignals().add(new SignalElement("ThrPos", "Single", 0, 100, 0, "%", "Throttle position"));
        InputBus1.getSignals().add(new SignalElement("WaterTemp", "Single", -50, 150, 20, "°C", "Water temperature"));
        InputBus1.getSignals().add(new SignalElement("AirTemp", "Single", -50, 50, 20, "°C", "Intake air temperature"));
        
        SignalsBus InputBus2 = new SignalsBus("EngineDiagnostics", SimulinkItemScope.AUTO, "", "Engine diagnostics data");
        InputBus2.getSignals().add(new SignalElement("InjectorStates", "uint8", 0, 15, 0, "", "Injectors status bits field"));
        InputBus2.getSignals().add(new SignalElement("FuelPumpError", "boolean", 0, 1, 0, "", "Fuel pump error flag"));
        
        oSubSystem.getInputs().add(InputBus1);
        oSubSystem.getInputs().add(InputBus2);
        
        SignalsBus OutputsBus = new SignalsBus("InjectionOutputs", SimulinkItemScope.AUTO, "", "Injection module outputs");
        OutputsBus.getSignals().add(new SignalElement("TInj1", "Single", 0, 100, 0, "ms", "Injection time for injector 1"));
        OutputsBus.getSignals().add(new SignalElement("TInj2", "Single", 0, 100, 0, "ms", "Injection time for injector 2"));
        OutputsBus.getSignals().add(new SignalElement("TInj3", "Single", 0, 100, 0, "ms", "Injection time for injector 3"));
        OutputsBus.getSignals().add(new SignalElement("TInj4", "Single", 0, 100, 0, "ms", "Injection time for injector 4"));
        OutputsBus.getSignals().add(new SignalElement("InjPhase", "Single", -720, 720, 0, "°", "Injection phase"));
        
        oSubSystem.setOutputs(OutputsBus);
        
        SignalsBus LoggingBus = new SignalsBus("InjectionLogging", SimulinkItemScope.AUTO, "", "Injection module logging data");
        LoggingBus.getSignals().add(new SignalElement("MInj", "Single", 0, 100, 0, "mg", "Fuel mass to be injected"));
        LoggingBus.getSignals().add(new SignalElement("TWaterCorr", "Single", 0, 2, 0, "", "Water temperature correction factor"));
        LoggingBus.getSignals().add(new SignalElement("TAirCorr", "Single", 0, 2, 0, "", "Air temperature correction factor"));
        
        oSubSystem.setLogging(LoggingBus);
        
        oModule.getModels().add(oSubSystem);

        oTask.getModules().add(oModule);

        oProject.getTasks().add(oTask);

    }
   
}
