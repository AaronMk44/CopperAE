package copper.views.webapp;

import java.io.IOException;

import copper.entities.Developer;
import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class WebAppView
{
    public BorderPane getScene(Developer dev)
    {
        BorderPane root = new BorderPane();
        
        try 
        {
            FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/webapp/WebAppViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/webapp/WebAppView.fxml"));
            }
            
            root = (BorderPane)loader.load();            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}