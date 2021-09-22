package copper.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import copper.entities.WebAppCategory;
import copper.views.dialogs.ErrorView;
import copper.views.settings.AddCategoryView;
import copper.views.settings.EditCategoryView;
import copper.views.tables.AppCategoryTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.VBox;

public class AppCategoriesController implements Initializable
{

    private AppCategoryTable tableObj;
    private TableView<WebAppCategory> tableV;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.tableObj = new AppCategoryTable();
        this.tableV = tableObj.getTable();
        this.tableContainer.getChildren().add(this.tableV);
    }    
    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton removeBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private VBox tableContainer;

    @FXML
    void add(ActionEvent event) 
    {
        AddCategoryView view = new AddCategoryView();
        view.getWindow(this.tableObj);
    }

    @FXML
    void remove(ActionEvent event) 
    {
        TableViewSelectionModel<WebAppCategory> selected = 
            this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            
        }
    }

    @FXML
    void update(ActionEvent event) 
    {
        TableViewSelectionModel<WebAppCategory> selected = 
            this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            EditCategoryView view = new EditCategoryView();
            view.getWindow(this.tableObj, selected.getSelectedItem());
        }
    }
}
