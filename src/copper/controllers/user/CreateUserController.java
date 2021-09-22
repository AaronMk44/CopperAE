/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copper.controllers.user;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.SecurityUtils;
import copper.entities.User;
import copper.models.UserModel;
import copper.views.dialogs.ErrorView;
import copper.views.dialogs.SuccessView;
import copper.views.tables.UsersTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron Mkandawire
 */
public class CreateUserController implements Initializable
{
    private Stage window;
    private UserModel model;
    private UsersTable table;
    private SecurityUtils sec;
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
    private JFXTextField defaultPwd;

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
    void createUser(ActionEvent event) 
    {
        if(this.model.isUser(this.email.getText()))
        {
            ErrorView box = new ErrorView("That email is taken.");
            box.getWindow();
        }else
        {
            User obj = new User();
            String msg = "";
            obj.setFirstName(this.firstName.getText());
            obj.setLastName(this.lastName.getText());
            if(this.femaleRadioBtn.isSelected())
            {
                obj.setGender("female");
            }else if(this.maleRadioBtn.isSelected())
            {
                obj.setGender("male");
            }
            obj.setUsrPassword(sec.hashText(this.defaultPwd.getText()));
            obj.setEmail(this.email.getText());
            if(this.adminRadioBtn.isSelected())
            {
                obj.setUserGroup("admin");
                msg = "Create a developer account for the admin.\n"
                    + "Approve without account.";
            }else if(this.regularRadioBtn.isSelected())
            {
                obj.setUserGroup("regular");
            }

            this.model.insert(obj);
            this.table.reload();

            SuccessView box = new SuccessView("User successfully created.\n"
                + msg);
            box.getWindow();
            this.window.close();
        }       
        
    }
    
    public void setData(UsersTable table, Stage window)
    {
        this.table = table;
        this.window = window;
    }
}
