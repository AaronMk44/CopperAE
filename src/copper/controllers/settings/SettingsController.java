package copper.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;

import copper.views.settings.AppCategoriesView;
import copper.views.settings.ManageAccView;
import copper.views.settings.SiteRootView;
import copper.views.settings.ThemeView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class SettingsController implements Initializable
{

    private AppCategoriesView categories;
    private ThemeView theme;
    private SiteRootView root;
    private ManageAccView account;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.categories = new AppCategoriesView();
        this.container.getChildren().add(this.categories.getScene());
        
        /*this.theme = new ThemeView();
        this.container.getChildren().add(this.theme.getScene());
        
        this.root = new SiteRootView();
        this.container.getChildren().add(this.root.getScene());
        
        this.account = new ManageAccView();
        this.container.getChildren().add(this.account.getScene());*/
    }    
    
    @FXML
    private VBox container;
}


