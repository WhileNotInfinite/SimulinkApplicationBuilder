/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulinkapplicationbuilderprojecteditor;

import SimulinkApplicationProject.ModuleSubSystem;
import SimulinkApplicationProject.TaskModule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BRAUL
 */
public class TaskModulePropertiesEditorController implements Initializable
{
    private FrmMainController MainForMainController;
    
    private TaskModule oModule;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private ListView<ModuleSubSystem> lstModels;
    
    @FXML
    private ContextMenu lstModels_ContextMenu;
    
    @FXML
    private MenuItem lstModels_ContextMenuItem_Active;
    
    @FXML
    private MenuItem lstModels_ContextMenuItem_Descative;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        MainForMainController = null;
        oModule = null;
        
        txtName.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (!newValue)
            {
                setModuleName();
            }
        });
        
        lstModels.setCellFactory((ListView<ModuleSubSystem> p) -> new ListCell<ModuleSubSystem>()
        {    
            @Override
            protected void updateItem(ModuleSubSystem value, boolean empty) 
            {
                super.updateItem(value, empty);
                final String text = (value == null || empty) ? null : value.getName();
                setText(text);
                String style = null;
                if (!empty && value != null & value.isActive())
                {
                    style = "-fx-font-weight: bold; -fx-text-fill: royalblue;";
                }
                setStyle(style);
            }
        });
    }

    public void setMainForMainController(FrmMainController MainForMainController)
    {
        this.MainForMainController = MainForMainController;
    }
    
    public void setoModule(TaskModule oModule)
    {
        this.oModule = oModule;
        
        if (this.oModule != null)
        {
            showModuleProperties();
        }
    }
    
    private void showModuleProperties()
    {
        txtName.setText(oModule.getName());
        
        lstModels.getItems().clear();
        
        for(ModuleSubSystem oSubSystem: oModule.getModels())
        {
            lstModels.getItems().add(oSubSystem);
        }
    }
    
    private void setModuleName()
    {
        if (oModule != null)
        {
            if (!txtName.getText().isEmpty())
            {
                oModule.setName(txtName.getText());
                MainForMainController.refreshProjectTree();
            }
        }
    }
    
    @FXML
    private void txtName_OnAction(ActionEvent event)
    {
        setModuleName();
    }
    
    @FXML
    private void lstModel_Clicked(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Double click
        {
            if (lstModels.getSelectionModel().getSelectedItem() != null)
            {
                MainForMainController.showModuleSubSystemProperties(lstModels.getSelectionModel().getSelectedItem());
            }
        }
    }
    
    @FXML
    private void lstModel_ContextMenuRequested(ContextMenuEvent event)
    {
        if (lstModels.getItems().isEmpty())
        {
            event.consume();
        }
        else
        {
            ModuleSubSystem SelectedItem = lstModels.getSelectionModel().getSelectedItem();
            
            if (SelectedItem == null)
            {
                lstModels_ContextMenuItem_Active.setDisable(true);
                lstModels_ContextMenuItem_Descative.setDisable(true);
            }
            else
            {
                if (SelectedItem.isActive())
                {
                    lstModels_ContextMenuItem_Active.setDisable(true);
                    lstModels_ContextMenuItem_Descative.setDisable(false);
                }
                else
                {
                    lstModels_ContextMenuItem_Active.setDisable(false);
                    lstModels_ContextMenuItem_Descative.setDisable(true);
                }
            }
        }
    }
    
    @FXML
    private void lstModels_ContextMenuItem_Active_OnAction(ActionEvent event)
    {
        ModuleSubSystem SelectedItem = lstModels.getSelectionModel().getSelectedItem();
        
        for(ModuleSubSystem model: oModule.getModels())
        {
            if (model.equals(SelectedItem))
            {
                model.setActive(true);
            }
            else
            {
                model.setActive(false);
            }
        }
        
        lstModels.refresh();
    }
    
    @FXML
    private void lstModels_ContextMenuItem_Descative_OnAction(ActionEvent event)
    {
        ModuleSubSystem SelectedItem = lstModels.getSelectionModel().getSelectedItem();
        
        for(ModuleSubSystem model: oModule.getModels())
        {
            if (model.equals(SelectedItem))
            {
                model.setActive(false);
            }
        }
        
        lstModels.refresh();
    }
}
