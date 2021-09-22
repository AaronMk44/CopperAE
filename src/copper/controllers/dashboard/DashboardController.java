	package copper.controllers.dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import copper.views.dashboard.HomeView;
import copper.views.developer.DeveloperView;
import copper.views.devtools.DevToolsView;
import copper.views.mail.MailView;
import copper.views.settings.SettingsView;
import copper.views.user.UserView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable
{
    private VBox homeContainer;
    private VBox userContainer;
    private VBox developerContainer;
    private VBox devToolsContainer;
    private VBox settingsContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        /*The reason for placing these initializes here, 
        is dashboard loading speed*/
                
        UserView userView = new UserView();
        this.userContainer = new VBox(userView.getScene());
        
        DeveloperView developerView = new DeveloperView();
        this.developerContainer = new VBox(developerView.getScene());
        
        DevToolsView devtoolsView = new DevToolsView();
        this.devToolsContainer = new VBox(devtoolsView.getScene());
        
        //control transfer to setDeveloper()
                
        SettingsView settingsView = new SettingsView();
        this.settingsContainer = new VBox(settingsView.getScene());
    }
    
    @FXML
    private BorderPane rootLayout;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton usersBtn;

    @FXML
    private JFXButton developersBtn;

    @FXML
    private JFXButton devToolsBtn;

    @FXML
    private JFXButton settingsBtn;
    
    @FXML
    private JFXButton mailBtn;

    @FXML
    void loadDevelopers(ActionEvent event) 
    {
        this.rootLayout.setCenter(this.developerContainer);
    }

    @FXML
    void loadHome(ActionEvent event) 
    {        
        this.rootLayout.setCenter(this.homeContainer);
    }

    @FXML
    void loadSettings(ActionEvent event) 
    {
        this.rootLayout.setCenter(this.settingsContainer);
    }

    @FXML
    void loadUsers(ActionEvent event) 
    {        
        this.rootLayout.setCenter(this.userContainer);
    }
    
    @FXML
    void loadMail(ActionEvent event) 
    {
    	MailView view = new MailView();
    	view.getWindow();
    }

    @FXML
    void loadDevTools(ActionEvent event) 
    {
        this.rootLayout.setCenter(this.devToolsContainer);
    }
    
    //For public consumption
    public void loadHome()
    {
        HomeView homeView = new HomeView();
        this.homeContainer = new VBox(homeView.getScene());
        this.rootLayout.setCenter(this.homeContainer);
    }
}