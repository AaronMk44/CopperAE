 package copper.views.tables;

import java.util.Arrays;
import java.util.List;

import copper.entities.WebAppCategory;
import copper.models.WebAppCategoriesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AppCategoryTable
{
    private WebAppCategoriesModel model;
    private List<WebAppCategory> dataList;
    private TableView<WebAppCategory> table;
    private ObservableList<WebAppCategory> categories;

    public AppCategoryTable()
    {
        this.model = new WebAppCategoriesModel();
        this.dataList = this.getCategories();
        System.out.println("in cate table");
    }
    
    public TableView getTable()
    {
        this.table = new TableView<WebAppCategory>();
        
        this.categories = FXCollections.observableList(this.dataList);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn<WebAppCategory, String> nameCol = 
            new TableColumn<WebAppCategory, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn<WebAppCategory, String> extensionCol = 
            new TableColumn<WebAppCategory, String>("Extension");
        extensionCol.setCellValueFactory(new PropertyValueFactory("extension"));
        
        table.getColumns().add(idColumn);
        table.getColumns().add(nameCol);
        table.getColumns().add(extensionCol);

        table.setItems(this.categories);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-font-size: 12px;");
        table.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>()
        {
            @Override
            public Boolean call(TableView.ResizeFeatures param)
            {
                return true;
            }
        });
        
        return table;
    }
    
    private List<WebAppCategory> getCategories()
    {
        WebAppCategory[] categories = this.model.getCategories();
        if(categories == null)
        {
            return null;
        }else
        {
           return Arrays.asList(categories);
        }      
    }
    
    public void reload()
    {
        this.dataList = this.getCategories();
        this.categories = FXCollections.observableList(this.dataList);
        this.table.setItems(categories);
    }
}
