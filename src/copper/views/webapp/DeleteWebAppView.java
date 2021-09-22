package copper.views.webapp;

import java.io.IOException;

import copper.controllers.webapps.DeleteWebAppController;
import copper.entities.WebApp;
import copper.models.Configurations;
import copper.views.tables.WebAppsTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteWebAppView
{
    public void getWindow(WebApp app, WebAppsTable table)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader  = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/webapp/DeleteWebAppViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/webapp/DeleteWebAppView.fxml"));
            }
            
            root = loader.load();

            DeleteWebAppController ctrl = loader.getController();
            ctrl.setData(app, window, table);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root);

        window.setTitle("Delete App");
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

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
