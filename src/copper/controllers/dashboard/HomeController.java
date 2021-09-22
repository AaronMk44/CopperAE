package copper.controllers.dashboard;

import java.util.Calendar;

import com.jfoenix.controls.JFXButton;

import copper.models.Configurations;
import copper.models.DeveloperModel;
import copper.models.UserModel;
import copper.models.WebAppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeController 
{
    @FXML
    private BorderPane rootLayout;

    @FXML
    private Label greetingText;

    @FXML
    private Label numberOfUsers;

    @FXML
    private Label numberOfDevelopers;

    @FXML
    private Label numberOfWebApps;

    @FXML
    private JFXButton refreshStatsBtn;  

    @FXML
    void refreshStatistics(ActionEvent event) 
    {
        UserModel uModel = new UserModel();
        this.numberOfUsers.setText(uModel.getNumberOfUsers()+"");
        
        DeveloperModel dModel = new DeveloperModel();
        this.numberOfDevelopers.setText(dModel.getNumberOfDevelopers()+"");
        
        WebAppModel wModel = new WebAppModel();
        this.numberOfWebApps.setText(wModel.getNumberOfWebApps()+"");
    }

    public void loadStatistics()
    {
        UserModel uModel = new UserModel();
        this.numberOfUsers.setText(uModel.getNumberOfUsers()+"");
        
        DeveloperModel dModel = new DeveloperModel();
        this.numberOfDevelopers.setText(dModel.getNumberOfDevelopers()+"");
        
        WebAppModel wModel = new WebAppModel();
        this.numberOfWebApps.setText(wModel.getNumberOfWebApps()+"");
        
        Calendar cl = Calendar.getInstance();
        int hour = cl.get(Calendar.HOUR_OF_DAY);
        
        if (hour >= 1 && hour < 12)
        {
            this.greetingText.setText("Good Morning, " + Configurations.getUser().getFirstName());
        }else if (hour >= 12  && hour < 18)
        {
            this.greetingText.setText("Good Afternoon, " + Configurations.getUser().getFirstName());
        }else if (hour >= 18 && hour < 24)
        {
            this.greetingText.setText("Good Evening, " + Configurations.getUser().getFirstName());
        }else if(hour == 24)
        {
            this.greetingText.setText("It's Midnight, " + Configurations.getUser().getFirstName());
        }
    }
}
