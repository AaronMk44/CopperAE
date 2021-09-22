package copper.controllers.developer;

import com.jfoenix.controls.JFXButton;

import copper.entities.Developer;
import copper.models.DeveloperModel;
import copper.views.dialogs.SuccessView;
import copper.views.tables.DevelopersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteDeveloperController 
{
    private Developer user;
    private Stage window;
    private DeveloperModel model;
    private DevelopersTable table;
    
    public DeleteDeveloperController()
    {
        this.model = new DeveloperModel();
    }

    @FXML
    private JFXButton yesBtn;

    @FXML
    private JFXButton noBtn;
    
    @FXML
    private Label userTag;

    @FXML
    void closeWindow(ActionEvent event) 
    {
        this.window.close();
    }

    @FXML
    void delete(ActionEvent event) 
    {
        this.model.delete(this.user.getDeveloperID());
        this.table.reload();
        this.window.close();
        
        SuccessView box = new SuccessView("Developer successfully deleted.\n"
                                              + "Delete the FTP and Database accounts also.");
        box.getWindow();   
    }
    
    public void setData(Developer developer, Stage window, DevelopersTable table)
    {
        this.user = developer;
        this.window = window;
        this.table = table;
        this.userTag.setText(developer.getFirstName() + " " + developer.getLastName());
    }

}
