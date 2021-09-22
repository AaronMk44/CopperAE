package copper.controllers.developer;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.Developer;
import copper.models.DeveloperModel;
import copper.views.developer.ApproveDeveloperView;
import copper.views.developer.DeleteDeveloperView;
import copper.views.dialogs.ErrorView;
import copper.views.dialogs.InfoView;
import copper.views.tables.DevelopersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.BorderPane;

public class DeveloperViewController implements Initializable
{
    private DevelopersTable tableObj;
    private TableView<Developer> tableV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.tableObj = new DevelopersTable();
        this.tableV = tableObj.getTable();
        this.tableV.setMinSize(1000, 600);
        this.scrollContent.setContent(this.tableV);
        this.scrollContent.setMinViewportHeight(460);
    } 
 @FXML
    private BorderPane rootLayout;

    @FXML
    private JFXButton approveAndSetupBtn;

    @FXML
    private JFXButton credentials;

    @FXML
    private JFXButton deleteDevBtn;

    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton reloadBtn;

    @FXML
    private ScrollPane scrollContent;
    
    @FXML
    void approveAndSetupAccount(ActionEvent event) 
    {
        DeveloperModel model = new DeveloperModel();
        TableViewSelectionModel<Developer> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            if(selected.getSelectedItem().getStatus().equals("approved"))
            {
                ErrorView box = new ErrorView("The developer is already\n"
                                                  + "approved and setup");
                box.getWindow();
            }else
            {
               ApproveDeveloperView view = new ApproveDeveloperView();
               view.getWindow(selected.getSelectedItem(), this.tableObj);
            }            
        }
    }
       
    @FXML
    void viewCredentials(ActionEvent event) 
    {
        TableViewSelectionModel<Developer> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            DeveloperModel dModel = new DeveloperModel();
            String pwd = dModel.getNormalPwd(selected.getSelectedItem().getDeveloperID());
            
            String msg = "Name : " + selected.getSelectedItem().getFirstName() + " " + 
                    selected.getSelectedItem().getLastName()
                    + "\nUsername : " + selected.getSelectedItem().getUsername() + ""
                    + "\nPassword : " + pwd;
            
            InfoView box = new InfoView(msg);
            box.getWindow();
        }
        
    }

    @FXML
    void deleteDev(ActionEvent event) 
    {
        TableViewSelectionModel<Developer> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            DeleteDeveloperView view = new DeleteDeveloperView();
            view.getWindow(selected.getSelectedItem(), tableObj);
        }
    }

    @FXML
    void search(ActionEvent event) 
    {
        this.tableObj.filter(this.searchBar.getText());
    }
    
    @FXML
    void reload(ActionEvent event) 
    {
        this.tableObj.reload();
    }

}
