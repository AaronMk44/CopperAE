package copper.views.settings;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class SettingsView
{
    public BorderPane getScene()
    {
        BorderPane root = new BorderPane();
        
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/copper/views/settings/SettingsView.fxml"));
            root = (BorderPane)loader.load();
            System.out.println("the setting viewis created");
            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}
