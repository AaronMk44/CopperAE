package copper.views.dashboard;

import java.io.IOException;

import copper.controllers.dashboard.DashboardController;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DashboardView
{
    private static Scene scene = null;
    public void getWindow()
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/dashboard/DashboardViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/dashboard/DashboardView.fxml"));
            }
            
            root = loader.load();
            DashboardController ctr = loader.getController();
            ctr.loadHome();;

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        scene = new Scene(root);
        if(Configurations.getConfig("theme").equals("dark"))
        {
            scene.getStylesheets().add(getClass().getResource("/copper/assets/dark.css")
            .toExternalForm());
        }
        window.setTitle("Copper Administrator's Edition - Dashboard");
        window.setScene(scene);
        window.setResizable(true);
        window.setMinWidth(1100);
        window.setMinHeight(700);

        try
        {
            if(Configurations.getConfig("theme").equals("dark"))
            {
                Image icon = new Image(getClass().
                getResourceAsStream("/copper/assets/images/logoDark.png"));
                window.getIcons().add(icon);
            }else
            {
                Image icon = new Image(getClass().
                getResourceAsStream("/copper/assets/images/logoLight.png"));
                window.getIcons().add(icon);
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        window.show();
    }
}