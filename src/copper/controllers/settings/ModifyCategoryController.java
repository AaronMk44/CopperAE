package copper.controllers.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import copper.entities.WebAppCategory;
import copper.models.WebAppCategoriesModel;
import copper.views.dialogs.SuccessView;
import copper.views.tables.AppCategoryTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/*
This controller is used by two different views(windows). 
One veiw adds while the other edits a web app category
*/
public class ModifyCategoryController 
{
    private WebAppCategoriesModel model;
    private AppCategoryTable table;
    private Stage window;
    
    public ModifyCategoryController()
    {
        this.model = new WebAppCategoriesModel();
    }

    @FXML
    private JFXTextField name;

    @FXML
    private JFXButton addBtn;
    
    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXTextField extension;

    @FXML
    void add(ActionEvent event) 
    {
        WebAppCategory obj = new WebAppCategory();
        obj.setName(this.name.getText());
        obj.setExtension(this.extension.getText());
        
        this.model.insert(obj);
        this.table.reload();
        
        SuccessView box = new SuccessView("Category successfully added.");
        box.getWindow();
        this.window.close();
    }
    
    @FXML
    void edit(ActionEvent event) 
    {
        WebAppCategory obj = new WebAppCategory();
        obj.setName(this.name.getText());
        obj.setExtension(this.extension.getText());
        
        this.model.update(obj);
        this.table.reload();
        
        SuccessView box = new SuccessView("Category successfully edited.");
        box.getWindow();
        this.window.close();
    }

    public void setData(AppCategoryTable table, Stage window)
    {
        this.table = table;
        this.window = window;
    }
    
    public void loadData(WebAppCategory obj)
    {
        this.name.setText(obj.getName());
        this.extension.setText(obj.getExtension());
    }

}
