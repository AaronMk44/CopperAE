package copper.views.user;

import java.io.IOException;

import copper.models.Configurations;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class UserView
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
                getResource("/copper/views/user/UserViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/user/UserView.fxml"));
            }  
            
            root = (BorderPane)loader.load();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}
