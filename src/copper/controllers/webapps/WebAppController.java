package copper.controllers.webapps;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.WebApp;
import copper.views.dialogs.ErrorView;
import copper.views.tables.WebAppsTable;
import copper.views.webapp.VerifyAdminForAppDeletionView;
import copper.views.webapp.WebAppDetailsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.BorderPane;

public class WebAppController implements Initializable
{
    private WebAppsTable tableObj;
    private TableView<WebApp> tableV;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.tableObj = new WebAppsTable();
        this.tableV = tableObj.getTable();
        this.tableV.setMinSize(1000, 600);
        this.scrollContent.setContent(this.tableV);
        this.scrollContent.setMinViewportHeight(460);
    } 
    
    @FXML
    private BorderPane rootLayout;

    @FXML
    private JFXButton viewDetailsBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton reloadBtn;

    @FXML
    private ScrollPane scrollContent;

    @FXML
    void deleteApp(ActionEvent event) 
    {
        TableViewSelectionModel<WebApp> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            VerifyAdminForAppDeletionView view = 
                    new VerifyAdminForAppDeletionView();
            view.getStage(selected.getSelectedItem(), tableObj);
        }
    }

    @FXML
    void viewAppDetails(ActionEvent event) 
    {
        TableViewSelectionModel<WebApp> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            WebAppDetailsView view = new WebAppDetailsView();
            view.getWindow(selected.getSelectedItem());
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
