package copper.views.settings;

import java.io.IOException;

import copper.controllers.settings.ModifyCategoryController;
import copper.models.Configurations;
import copper.views.tables.AppCategoryTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCategoryView
{
    public void getWindow(AppCategoryTable table)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/copper/views/settings/AddCategoryView.fxml"));
            root = loader.load();

            ModifyCategoryController ctrl = loader.getController();
            ctrl.setData(table, window);

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root);

        window.setTitle("Add Category");
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
