package copper.controllers.devtools;

import java.io.IOException;

import copper.models.Configurations;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DevToolsController 
{

    @FXML
    private VBox browser;

    @FXML
    private VBox fileManager;

    @FXML
    private VBox phpMyAdmin;

    @FXML
    private VBox configurations;

    @FXML
    void loadBrowser(MouseEvent event) 
    {  
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("H:\\Projects\\Copper\\Adapters\\BrowserAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadConfigurations(MouseEvent event) 
    {
    	
    }

    @FXML
    void loadFileManager(MouseEvent event) 
    {
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("H:\\Projects\\Copper\\Adapters\\FileManagerAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadPhpMyAdmin(MouseEvent event) 
    {
    	Configurations.authorise();
    	Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec("H:\\Projects\\Copper\\Adapters\\PhpMyAdminAdapter.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
