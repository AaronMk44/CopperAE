package copper.controllers.user;

import com.jfoenix.controls.JFXButton;

import copper.entities.User;
import copper.models.UserModel;
import copper.views.dialogs.SuccessView;
import copper.views.tables.UsersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteUserController 
{
    private User user;
    private Stage window;
    private UserModel model;
    private UsersTable table;
    
    public DeleteUserController()
    {
        this.model = new UserModel();
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
        this.model.delete(this.user.getEmail());
        this.table.reload();
        this.window.close();
        
        SuccessView box = new SuccessView("User successfully deleted.");
        box.getWindow();   
    }
    
    public void setData(User user, Stage window, UsersTable table)
    {
        this.user = user;
        this.window = window;
        this.table = table;
        this.userTag.setText(user.getFirstName() + " " + user.getLastName());
    }

}
