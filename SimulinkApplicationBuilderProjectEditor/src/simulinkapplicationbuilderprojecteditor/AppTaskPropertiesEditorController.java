/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulinkapplicationbuilderprojecteditor;

import SimulinkApplicationProject.ApplicationTask;
import SimulinkApplicationProject.ApplicationTaskCallingMode;
import SimulinkApplicationProject.TaskModule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BRAUL
 */
public class AppTaskPropertiesEditorController implements Initializable
{
    private FrmMainController MainForMainController;
    
    private ApplicationTask oTask;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private ChoiceBox<String> cmbCallingMode;
    
    @FXML
    private Label lblCycleTimeName;
    
    @FXML
    private TextField txtCycleTime;
    
    @FXML
    private  Label lblCycleTimeUnit;
    
    @FXML
    private ListView<TaskModule> lstModules;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        oTask = null;
        
        cmbCallingMode.getItems().clear();
        
        for(ApplicationTaskCallingMode val:ApplicationTaskCallingMode.values())
        {
            cmbCallingMode.getItems().add(String.valueOf(val));
        }
        
        txtName.focusedProperty().addListener(((observable, oldValue, newValue) ->
        {
            if (!newValue)
            {
                setTaskName();
            }
        }));
        
        cmbCallingMode.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->
        {
            setCallingMode();
        }));
        
        txtCycleTime.focusedProperty().addListener(((observable, oldValue, newValue) ->
        {
            if (!newValue)
            {
                setCycleTime();
            }
        }));
        
        lstModules.setCellFactory((ListView<TaskModule> p) -> new ListCell<TaskModule>()
        {
            @Override
            protected void updateItem(TaskModule value, boolean empty)
            {
                super.updateItem(value, empty);
                final String text = (value == null || empty) ? null : value.getName();
                setText(text);
            }
        });
        
    }    

    public void setMainForMainController(FrmMainController MainForMainController)
    {
        this.MainForMainController = MainForMainController;
    }
    
    public void setoTask(ApplicationTask oTask)
    {
        this.oTask = oTask;
        
        if (oTask != null)
        {
            showTaskProperties();
        }
    }
    
    @FXML
    private void txtName_TextChanged(ActionEvent event)
    {
        setTaskName();
    }
    
    @FXML
    private void txtCycleTime_TextChanged(ActionEvent event)
    {
        setCycleTime();
    }
    
    @FXML
    private void lstModules_Clicked(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Double click
        {
            showSelectedModule();
        }
    }
    
    private void showTaskProperties()
    {
        txtName.setText(oTask.getName());
        cmbCallingMode.setValue(String.valueOf(oTask.getCallingMode()));
        
        if(oTask.getCallingMode() == ApplicationTaskCallingMode.CYCLIC)
        {
            DisableCycleTimeControls(false);
            txtCycleTime.setText(String.valueOf(oTask.getCycleTime()));
        }
        else
        {
            txtCycleTime.setText("");
            DisableCycleTimeControls(true);
        }
        
        lstModules.getItems().clear();
        for(TaskModule oModule:oTask.getModules())
        {
            lstModules.getItems().add(oModule);
        }
    }
    
    private void DisableCycleTimeControls(boolean State)
    {
        lblCycleTimeName.setDisable(State);
        txtCycleTime.setDisable(State);
        lblCycleTimeUnit.setDisable(State);
    }
    
    private void setTaskName()
    {
        if (oTask != null)
        {
            if (!(txtName.getText().equals("")))
            {
                oTask.setName(txtName.getText());
                MainForMainController.refreshProjectTree();
            }
        }
    }
    
    private void setCallingMode()
    {
        if (oTask != null)
        {
            try
            {
                oTask.setCallingMode(ApplicationTaskCallingMode.valueOf(ApplicationTaskCallingMode.class, cmbCallingMode.getValue()));
                
                if(oTask.getCallingMode() == ApplicationTaskCallingMode.CYCLIC)
                {
                    DisableCycleTimeControls(false);
                }
                else
                {
                    DisableCycleTimeControls(true);
                }
            } 
            catch (Exception e)
            {
            }
        }
    }
    
    private void setCycleTime()
    {
        if (oTask != null)
        {
            if (!(txtCycleTime.getText().equals("")))
            {
                double CycleTime = Double.NaN;
                
                try
                {
                    CycleTime = Double.parseDouble(txtCycleTime.getText());
                    
                    if (CycleTime > 0)
                    {
                        oTask.setCycleTime(CycleTime);
                    }
                    else
                    {
                        Alert msgAlert = new Alert(Alert.AlertType.WARNING);
                        msgAlert.setTitle("Value out of range");
                        msgAlert.setContentText("Task cycle time must be greater than zero !");
                        msgAlert.showAndWait();

                        txtCycleTime.setText(String.valueOf(oTask.getCycleTime()));
                    }
                } 
                catch (Exception e)
                {
                    Alert msgAlert = new Alert(Alert.AlertType.WARNING);
                    msgAlert.setTitle("Not a number");
                    msgAlert.setContentText("Value " + txtCycleTime.getText() + " is not a number !");
                    msgAlert.showAndWait();
                    
                    txtCycleTime.setText(String.valueOf(oTask.getCycleTime()));
                }
            }
        }
    }
    
    private void showSelectedModule()
    {
        TaskModule module = lstModules.getSelectionModel().getSelectedItem();
            
        if (module != null)
        {
            MainForMainController.showTaskModuleProperties(module);
        }
    }
    
}
