package copper.controllers.user;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.User;
import copper.views.dialogs.ErrorView;
import copper.views.tables.UsersTable;
import copper.views.user.CreateUserView;
import copper.views.user.DeleteUserView;
import copper.views.user.EditUserView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class UserViewController implements Initializable
{
    private UsersTable tableObj;
    private TableView<User> tableV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.tableObj = new UsersTable();
        this.tableV = tableObj.getTable();
        this.tableV.setMinSize(1000, 600);
        this.scrollContent.setContent(this.tableV);
        this.scrollContent.setMinViewportHeight(460);
    } 
    
    @FXML
    private JFXButton createUserBtn;

    @FXML
    private JFXButton editUserBtn;

    @FXML
    private JFXButton deleteUserBtn;

    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton reloadBtn;

    @FXML
    private ScrollPane scrollContent;
    
    @FXML
    void createUser(ActionEvent event) 
    {
        CreateUserView view = new CreateUserView();
        view.getWindow(this.tableObj);
    }

    @FXML
    void deleteUser(ActionEvent event) 
    {
        TableViewSelectionModel<User> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            if(selected.getSelectedItem().getUserGroup().equals("admin"))
            {
                ErrorView box = new ErrorView("You can not delete an admin.");
                box.getWindow();
            }else if(selected.getSelectedItem().getIsDeveloper())
            {
            	ErrorView box = new ErrorView("You can not delete a developer directly.\nDelete their developer account first.");
                box.getWindow();
            }
            else
            {
                DeleteUserView view = new DeleteUserView();
                view.getWindow(selected.getSelectedItem(), tableObj);
            }            
        }
    }

    @FXML
    void editUser(ActionEvent event) 
    {
        TableViewSelectionModel<User> selected = this.tableV.getSelectionModel();
        if(selected.isEmpty())
        {
            ErrorView box = new ErrorView("You did not select anything.");
            box.getWindow();
        }else
        {
            EditUserView view = new EditUserView();
            view.getWindow(this.tableObj, selected.getSelectedItem());
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
