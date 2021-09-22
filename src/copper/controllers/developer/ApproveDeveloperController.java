package copper.controllers.developer;

import com.jfoenix.controls.JFXButton;

import copper.entities.Developer;
import copper.models.DeveloperModel;
import copper.views.dialogs.ErrorView;
import copper.views.dialogs.SuccessView;
import copper.views.tables.DevelopersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ApproveDeveloperController 
{
    private Developer user;
    private Stage window;
    private DeveloperModel model;
    private DevelopersTable table;
    
    public ApproveDeveloperController()
    {
        this.model = new DeveloperModel();
    }

    @FXML
    private Label userTag;

    @FXML
    private JFXButton yesBtn;

    @FXML
    private JFXButton noBtn;

    @FXML
    void closeWindow(ActionEvent event) 
    {
        this.window.close();
    }

    @FXML
    void approve(ActionEvent event) 
    {
        if(model.approve(this.user))
        {
            SuccessView view = 
                new SuccessView("Developer successfully approved.");
            view.getWindow();
            this.table.reload();
            this.window.close();
        }else
        {
            ErrorView box = 
                new ErrorView("Could not approve developer account.");
            box.getWindow();
            this.window.close();
        }          
    }
    
    public void setData(Developer developer, Stage window, DevelopersTable table)
    {
        this.user = developer;
        this.window = window;
        this.table = table;
        this.userTag.setText("By Clicking Approve, you consent to approval.\n"
                + "Create a database and FileGator account for :\n"
                + developer.getFirstName() +" "+ developer.getLastName()
                + "\nusing the appropriate credentials.");
    }

}
