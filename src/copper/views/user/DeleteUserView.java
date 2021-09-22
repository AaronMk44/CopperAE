package copper.views.user;

import java.io.IOException;

import copper.controllers.user.DeleteUserController;
import copper.entities.User;
import copper.models.Configurations;
import copper.views.tables.UsersTable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteUserView
{
    public void getWindow(User user, UsersTable table)
    {
        Stage window = new Stage();
        Parent root = null;

        try
        {
            FXMLLoader loader = null;
            if(Configurations.getConfig("theme").equals("dark"))
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/user/DeleteUserViewDark.fxml"));
            }else
            {
                loader = new FXMLLoader(getClass().
                getResource("/copper/views/user/DeleteUserView.fxml"));
            }
            
            root = loader.load();
            DeleteUserController ctrl = loader.getController();
            ctrl.setData(user, window, table);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        Scene scene = new Scene(root);

        window.setTitle("Delete User");
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
