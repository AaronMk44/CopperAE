package copper.views.developer;

import java.io.IOException;

import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class DeveloperView
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
                getResource("/copper/views/developer/DeveloperViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/developer/DeveloperView.fxml"));
            }
            root = (BorderPane)loader.load();
            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}
