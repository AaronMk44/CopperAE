package copper.controllers.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import copper.models.Configurations;
import copper.models.DeveloperModel;
import copper.models.LogInModel;
import copper.views.dashboard.DashboardView;
import copper.views.dialogs.ErrorView;
import copper.views.dialogs.InfoView;
import copper.views.dialogs.SettingsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LogInController
{
    private Stage window = null;

    private String errorMsg;

    private LogInModel logInModel = null;

    public LogInController()
    {
        errorMsg = "";
        logInModel = new LogInModel();
    }

    public void setWindow(Stage window)
    {
        this.window = window;
    }

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField paaswordField;

    @FXML
    private JFXButton logInBtn;

    @FXML
    private Label lackAccLable;

    @FXML
    private ImageView settingsImg;

    @FXML
    void loadLackAcc(MouseEvent event)
    {
        InfoView box = new InfoView("Contact the administrator to create an"
                + " \naccount.");
        box.getWindow();
    }

    @FXML
    void verifyCredentials(ActionEvent event)
    {
        if (logInModel.verifyCredentials(this.emailField.getText(), 
                                        this.paaswordField.getText()))
        {
            DashboardView nextView = new DashboardView();
            DeveloperModel dModel = new DeveloperModel();
            Configurations.setUser(dModel.getDeveloper(this.emailField.getText()));
            nextView.getWindow();
            this.window.close();
        } else
        {
            this.errorMsg = "The email or password entered does "
                    + "\nnot match our records. Try again.";

            ErrorView notify = new ErrorView(this.errorMsg);
            notify.getWindow();
        }
    }

    @FXML
    void loadSettings(MouseEvent event)
    {
        System.out.println("about to load setting s");
        SettingsView view = new SettingsView();
        view.getWindow();
    }
}
