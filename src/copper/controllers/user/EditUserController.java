package copper.controllers.user;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.User;
import copper.models.UserModel;
import copper.views.dialogs.SuccessView;
import copper.views.tables.UsersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditUserController implements Initializable
{
    private User user;
    private Stage window;
    private UserModel model;
    private UsersTable table;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.model = new UserModel();
        
        ToggleGroup genderTGroup = new ToggleGroup();
        this.maleRadioBtn.setToggleGroup(genderTGroup);
        this.femaleRadioBtn.setToggleGroup(genderTGroup);
      
        ToggleGroup userGroupTGroup = new ToggleGroup();
        this.adminRadioBtn.setToggleGroup(userGroupTGroup);
        this.regularRadioBtn.setToggleGroup(userGroupTGroup);
    }

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXRadioButton adminRadioBtn;

    @FXML
    private JFXRadioButton regularRadioBtn;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXRadioButton maleRadioBtn;

    @FXML
    private JFXRadioButton femaleRadioBtn;

    @FXML
    void updateUser(ActionEvent event) 
    {
        this.user.setFirstName(this.firstName.getText());
        this.user.setLastName(this.lastName.getText());
        if(this.femaleRadioBtn.isSelected())
        {
            this.user.setGender("female");
        }else if(this.maleRadioBtn.isSelected())
        {
            this.user.setGender("male");
        }
        this.user.setEmail(this.email.getText());
        if(this.adminRadioBtn.isSelected())
        {
            this.user.setUserGroup("admin");
        }else if(this.regularRadioBtn.isSelected())
        {
            this.user.setUserGroup("regular");
        }

        this.model.update(this.user);
        this.table.reload();
        
        SuccessView box = new SuccessView("User successfully updated.");
        box.getWindow();
        this.window.close();
    }

    public void setData(UsersTable table, User user, Stage window)
    {
        this.table = table;
        this.user = user;
        this.window = window;
        
        this.firstName.setText(user.getFirstName());
        this.lastName.setText(user.getLastName());
        this.email.setText(user.getEmail());
        if(user.getGender().equals("female"))
        {
            this.femaleRadioBtn.setSelected(true);
        }else 
        {
            this.maleRadioBtn.setSelected(true);
        }
        
        if (user.getUserGroup().equals("admin"))
        {
            this.adminRadioBtn.setSelected(true);
        }else
        {
            this.regularRadioBtn.setSelected(true);
        }
    }
}
