package copper.controllers.webapps;

import org.mindrot.jbcrypt.BCrypt;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import copper.entities.WebApp;
import copper.models.Configurations;
import copper.views.dialogs.ErrorView;
import copper.views.tables.WebAppsTable;
import copper.views.webapp.DeleteWebAppView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class VerifyAdminForAppController 
{
    private WebApp app = null;
    private WebAppsTable tableObj = null;
    private Stage window = null;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton verifyBtn;

    @FXML
    void verify(ActionEvent event) 
    {
        if (BCrypt.checkpw(this.passwordField.getText(), Configurations.getUser().getUsrPassword()))
        {
            this.window.close();
            DeleteWebAppView view = new DeleteWebAppView();
            view.getWindow(this.app, tableObj);
        } else
        {
            String message = "Password entered doesn't match our records. \nTry again.";
            ErrorView box = new ErrorView(message);
            box.getWindow();
        }
    }
    public void setData(final WebApp app, final Stage window, final WebAppsTable tableObj)
    {
        this.app = app;
        this.window = window;
        this.tableObj = tableObj;        
    }
}
   
    


