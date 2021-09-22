package copper.views.settings;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ThemeView
{
    public VBox getScene()
    {
        VBox root = new VBox();
        
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("/copper/views/settings/AppCategoriesView.fxml"));
            root = (VBox)loader.load();
            
            //HomeController ctrl = loader.getController();
            //ctrl.loadStatistics(obj);
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return root;
    }
}
