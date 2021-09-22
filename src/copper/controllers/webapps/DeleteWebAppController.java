package copper.controllers.webapps;

import com.jfoenix.controls.JFXButton;

import copper.entities.WebApp;
import copper.models.WebAppModel;
import copper.views.dialogs.SuccessView;
import copper.views.tables.WebAppsTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteWebAppController 
{
    private WebApp app;
    private Stage window;
    private WebAppModel model;
    private WebAppsTable table;
    
    public DeleteWebAppController()
    {
        this.model = new WebAppModel();
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
        this.model.delete(this.app.getID());
        this.table.reload();
        this.window.close();
        
        SuccessView box = new SuccessView("App successfully deleted.");
        box.getWindow();   
    }
    
    public void setData(WebApp app, Stage window, WebAppsTable table)
    {
        this.app = app;
        this.window = window;
        this.table = table;
        this.userTag.setText(app.getAppName());
    }

}
