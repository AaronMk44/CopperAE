package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;

import copper.entities.Developer;
import copper.entities.WebApp;
import copper.models.DeveloperModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WebAppDetailsController  
{
    private Stage window = null;

    @FXML
    private Label appID;

    @FXML
    private Label developerID;

    @FXML
    private Label developerName;

    @FXML
    private Label domainName;

    @FXML
    private Label documentRoot;

    @FXML
    private Label category;

    @FXML
    private Label description;

    @FXML
    private JFXButton closeBtn;

    @FXML
    void close(ActionEvent event) 
    {
        this.window.close();
    }
    
    public void setData(WebApp app, Stage window)
    {
        DeveloperModel model = new DeveloperModel();
        Developer devUsr = model.getDeveloper(app.getDeveloperID());
        
        this.window = window;
        this.appID.setText(app.getID());
        this.developerID.setText(app.getDeveloperID());
        this.developerName.setText(devUsr.getFirstName() + " " + devUsr.getLastName());
        this.domainName.setText(app.getDomainName());
        this.documentRoot.setText(app.getDocumentRoot());               
        this.category.setText(app.getCategoryName());
        this.description.setText(app.getBriefDescription());
        this.description.setWrapText(true);       
    }
}