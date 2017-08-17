/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulinkapplicationbuilderprojecteditor;


import ApplicationTypes.ProjectTreeItem;
import SimulinkApplicationProject.ApplicationData.ApplicationParameter;
import SimulinkApplicationProject.ApplicationData.ApplicationSignal;
import SimulinkApplicationProject.ApplicationProject;
import SimulinkApplicationProject.ApplicationTask;
import SimulinkApplicationProject.ModuleSubSystem;
import SimulinkApplicationProject.TaskModule;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 *
 * @author BRAUL
 */
public class FrmMainController implements Initializable
{
    private ApplicationProject oProject;
    
    private String CurrentProjectPath;
    
    @FXML 
    private BorderPane bpMain;
    
    @FXML
    private TreeView<String> tvProject;
    
    @FXML
    private Button tbBtnNewProject;
    
    @FXML
    private Button tbBtnOpenProject; 
    
    @FXML
    private Button tbBtnSaveProject;
    
    @FXML
    private Button tbBtnSaveAsProject;
    
    @FXML
    private MenuItem miAddTask;
    
    @FXML
    private MenuItem miAddModule;
    
    @FXML
    private MenuItem miAddModel;
    
    @FXML
    private MenuItem miAddSignal;
    
    @FXML
    private MenuItem miAddParameter;
    
    @FXML
    private void tvProject_KeyPressed(KeyEvent event)
    {
        switch (event.getCode())
        {
            case ENTER:
                
                TreeItem<String> SelectedItem = tvProject.getSelectionModel().getSelectedItem();
                
                if (SelectedItem != null)
                {
                    if(SelectedItem.getChildren().size() > 0)
                    {
                        SelectedItem.setExpanded(!SelectedItem.isExpanded());
                    }
                }
                
                break;

            default:
                break;
        }
                
    }
    
    @FXML
    private void tbBtnNewProject_Click(ActionEvent event)
    {
        newProject();
    }
    
    @FXML
    private void tbBtnOpenProject_Click(ActionEvent event)
    {
        openProject();
    }
    
    @FXML
    private void tbBtnSaveProject_Click(ActionEvent event)
    {
        saveProject();
    }
    
    @FXML
    private void tbBtnSaveAsProject_Click(ActionEvent event)
    {
        CurrentProjectPath = "";
        saveProject();
    }
    
    @FXML
    private void miAddTask_Click(ActionEvent event)
    {
        
    }
    
    @FXML
    private void miAddModule_Click(ActionEvent event)
    {
        
    }
    
    @FXML
    private void miAddModel_Click(ActionEvent event)
    {
        
    }
    
    @FXML
    private void miAddSignal_Click(ActionEvent event)
    {
        
    }
    
    @FXML
    private void miAddParameter_Click(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        oProject = null;
        CurrentProjectPath = "";
        
        TreeItem<String> tvProjectRootItem = new TreeItem<>("Project");
        tvProjectRootItem.setExpanded(true);
        
        tvProject.setRoot(tvProjectRootItem);
        tvProject.showRootProperty().setValue(false);
        
        tvProject.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> old_val, TreeItem<String> new_val) ->
        {
            TreeItem<String> selectedItem = new_val;
            showSelectedProjectItem((ProjectTreeItem)new_val);
        });
    } 
    
    protected void refreshProjectTree()
    {
        showProject();
    }
    
    protected void showApplicationTaskProperties(ApplicationTask oTask)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppTaskPropertiesEditor.fxml"));
            bpMain.setCenter(loader.load());

            AppTaskPropertiesEditorController controller = loader.<AppTaskPropertiesEditorController>getController();
            controller.setMainForMainController(this);
            controller.setoTask(oTask);
        } 
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    protected void showTaskModuleProperties(TaskModule oModule)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskModulePropertiesEditor.fxml"));
            bpMain.setCenter(loader.load());
            
            TaskModulePropertiesEditorController controller = loader.<TaskModulePropertiesEditorController>getController();
            controller.setMainForMainController(this);
            controller.setoModule(oModule);
        } 
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    
    protected void showModuleSubSystemProperties(ModuleSubSystem oSubSystem)
    {
        
    }
    
    private void newProject()
    {
        oProject = new ApplicationProject();
        CurrentProjectPath = "";
        showProject();
    }
    
    private void openProject()
    {
        FileChooser dlgOpen = new FileChooser();
        dlgOpen.setTitle("Open Application Project");

        File file = dlgOpen.showOpenDialog(tbBtnOpenProject.getScene().getWindow());
        
        if (file != null)
        {
            oProject = new ApplicationProject();
            oProject.readProjectXml(file.getAbsolutePath());
            CurrentProjectPath = file.getAbsolutePath();
            showProject();
        }
    }
    
    private void saveProject()
    {
        if (CurrentProjectPath.equals(""))
        {
            FileChooser dlgSave = new FileChooser();
            dlgSave.setTitle("Save Application Project");
            
            File file = dlgSave.showSaveDialog(tbBtnSaveAsProject.getScene().getWindow());
            
            if (file == null) return;
            
            CurrentProjectPath = file.getAbsolutePath();
        }
        
        saveProject(CurrentProjectPath);
    }
    
    private void saveProject(String FilePath)
    {
        oProject.writeProjectXml(FilePath);
        CurrentProjectPath = FilePath;
    }
    
    private void showProject()
    {
        if (oProject == null)
        {
            return;
        }
        
        tvProject.getRoot().getChildren().clear();
       
        ProjectTreeItem nPrjTasks = new ProjectTreeItem("Application Tasks", oProject.getTasks());
        tvProject.getRoot().getChildren().add(nPrjTasks);
        nPrjTasks.setExpanded(true);
        
        for (ApplicationTask oTask : oProject.getTasks())
        {
            ProjectTreeItem nodeTask = new ProjectTreeItem(oTask.getName(), oTask);
            
            for (TaskModule oModule : oTask.getModules())
            {
               ProjectTreeItem nodeModule = new ProjectTreeItem(oModule.getName(), oModule);

                for (ModuleSubSystem oSubSystem : oModule.getModels())
                {
                    ProjectTreeItem nodeModel = new ProjectTreeItem(oSubSystem.getName(), oSubSystem);

                    ProjectTreeItem nItem = null;

                    nItem = new ProjectTreeItem("Inputs", oSubSystem.getInputs());

                    for (Long SigId : oSubSystem.getInputs())
                    {
                        nItem.getChildren().add(new ProjectTreeItem(oProject.getApplicationSignals().getSignalByIdentifierKey(SigId).getName(), SigId));
                    }

                    nodeModel.getChildren().add(nItem);

                    nItem = new ProjectTreeItem("Outpus", oSubSystem.getOutputs());

                    for (Long SigId : oSubSystem.getOutputs())
                    {
                        nItem.getChildren().add(new ProjectTreeItem(oProject.getApplicationSignals().getSignalByIdentifierKey(SigId).getName(), SigId));
                    }

                    nodeModel.getChildren().add(nItem);

                    nItem = new ProjectTreeItem("Parameters", oSubSystem.getParameters());

                    for (Long ParamId : oSubSystem.getParameters())
                    {
                        nItem.getChildren().add(new ProjectTreeItem(oProject.getApplicationParameters().getApplicationParameterByIdentifierKey(ParamId).getName(), ParamId));
                    }

                    nodeModel.getChildren().add(nItem);

                    nodeModule.getChildren().add(nodeModel);
                }

                nodeTask.getChildren().add(nodeModule);
            }

            nPrjTasks.getChildren().add(nodeTask);
        }
        
        ProjectTreeItem nodeSignals = new ProjectTreeItem("Application Signals", oProject.getApplicationSignals());
        
        for(ApplicationSignal oSignal: oProject.getApplicationSignals())
        {
            nodeSignals.getChildren().add(new ProjectTreeItem(oSignal.getName(), oSignal));
        }
        
        tvProject.getRoot().getChildren().add(nodeSignals);
        
        ProjectTreeItem nodeParams = new ProjectTreeItem("Application Parameters", oProject.getApplicationParameters());
        
        for(ApplicationParameter oParameter: oProject.getApplicationParameters())
        {
            nodeParams.getChildren().add(new ProjectTreeItem(oParameter.getName(), oParameter));
        }
        
        tvProject.getRoot().getChildren().add(nodeParams);
    }
    
    private void showSelectedProjectItem(ProjectTreeItem nodeItem)
    {
        if ((nodeItem.getTag().getClass() == ArrayList.class) 
           || (nodeItem.getTag().getClass().getSuperclass() == ArrayList.class))
        {
            try
            {
                //URL location = getClass().getResource("SubForm3.fxml");
                //Node node = FXMLLoader.load(location);
                //bpMain.setCenter(node);
                
            } catch (Exception e)
            {
                System.out.println(e.toString());
            }
            
        }
        else
        {
            if (nodeItem.getTag().getClass() == ApplicationTask.class)
            {
                showApplicationTaskProperties((ApplicationTask)nodeItem.getTag());
            }
            else if (nodeItem.getTag().getClass() == TaskModule.class)
            {
                showTaskModuleProperties((TaskModule)nodeItem.getTag());
            }
            else if (nodeItem.getTag().getClass() == ModuleSubSystem.class)
            {
                
            }
            else if (nodeItem.getTag().getClass() == ApplicationSignal.class)
            {
                
            }
            else if (nodeItem.getTag().getClass() == ApplicationParameter.class)
            {
                
            } 
            else
            {
                
            }
        }
    }
    
}
