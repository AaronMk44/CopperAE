/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copper.views.settings;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author Aaron Mkandawire
 */
public class SiteRootView
{
    public VBox getScene()
    {
        VBox root = new VBox();
        
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/copper/views/settings/AppCategoriesView.fxml"));
            root = (VBox)loader.load();
            
            //HomeController ctrl = loader.getController();
            //ctrl.loadStatistics(obj);
        } catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return root;
    }
}
