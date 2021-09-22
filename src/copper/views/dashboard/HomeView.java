package copper.views.dashboard;

import java.io.IOException;

import copper.controllers.dashboard.HomeController;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class HomeView
{
    public BorderPane getScene()
    {
        BorderPane root = new BorderPane();
        try 
        {
            FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                    getResource("/copper/views/dashboard/HomeViewDark.fxml"));
                root = (BorderPane)loader.load();
            }else
            {
                loader = new FXMLLoader(getClass().
                    getResource("/copper/views/dashboard/HomeView.fxml"));
                root = (BorderPane)loader.load();
            }  
            HomeController ctr = loader.getController();
            ctr.loadStatistics();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return root;
    }
}
