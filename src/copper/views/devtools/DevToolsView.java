package copper.views.devtools;

import java.io.IOException;

import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class DevToolsView
{
    public Pane getScene()
    {
        Pane root = new Pane();
        
        try 
        {
            FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/devtools/DevToolsViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/devtools/DevToolsView.fxml"));
            }
            
            root = (Pane) loader.load();            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}