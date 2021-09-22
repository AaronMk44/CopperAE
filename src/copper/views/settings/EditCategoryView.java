package copper.views.settings;

import java.io.IOException;

import copper.controllers.settings.ModifyCategoryController;
import copper.entities.WebAppCategory;
import copper.views.tables.AppCategoryTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCategoryView
{
    public void getWindow(AppCategoryTable table, WebAppCategory appCate)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/copper/views/settings/EditCategoryView.fxml"));
            root = loader.load();

            ModifyCategoryController ctrl = loader.getController();
            ctrl.setData(table, window);
            ctrl.loadData(appCate);

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root);

        window.setTitle("Update Category");
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        try
        {
            Image icon = new Image(getClass().getResourceAsStream("/copper/assets/images/logo.png"));
            window.getIcons().add(icon);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        window.show();

    }
}
