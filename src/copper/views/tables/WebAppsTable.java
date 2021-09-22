package copper.views.tables;

import java.util.Arrays;
import java.util.List;

import copper.entities.WebApp;
import copper.models.WebAppModel;
import copper.views.dialogs.ErrorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class WebAppsTable
{
    private WebAppModel model;
    private List<WebApp> dataList;
    private TableView<WebApp> table;
    private ObservableList<WebApp> apps;

    public WebAppsTable()
    {
        this.model = new WebAppModel();
        this.dataList = this.getApps("");
    }
    
    public TableView getTable()
    {
        this.table = new TableView<WebApp>();
        
        ObservableList<WebApp> apps = FXCollections.observableList(this.dataList);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("appID"));
        
        TableColumn<WebApp, String> devIdCol =
            new TableColumn<WebApp, String>("Developer ID");
        devIdCol.setCellValueFactory(new PropertyValueFactory("developerID"));
        
        TableColumn<WebApp, String> appNameCol = 
            new TableColumn<WebApp, String>("App Name");
        appNameCol.setCellValueFactory(new PropertyValueFactory("appName"));
        
        TableColumn<WebApp, String> domainNameCol = 
            new TableColumn<WebApp, String>("Domain Name");
        domainNameCol.setCellValueFactory(new PropertyValueFactory("domainName"));
        
        TableColumn<WebApp, String> docRootCol = 
            new TableColumn<WebApp, String>("Document Root");
        docRootCol.setCellValueFactory(new PropertyValueFactory("documentRoot"));
        
        TableColumn<WebApp, String> statusCol = 
            new TableColumn<WebApp, String>("App Status");
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
                
        TableColumn<WebApp, String> cateCol = 
            new TableColumn<WebApp, String>("Category");
        cateCol.setCellValueFactory(new PropertyValueFactory("categoryName"));
                
        TableColumn<WebApp, String> dateCol = 
            new TableColumn<WebApp, String>("Created");
        dateCol.setCellValueFactory(new PropertyValueFactory("createdAt"));
        
        table.getColumns().add(idColumn);
        table.getColumns().add(devIdCol);
        table.getColumns().add(appNameCol);
        table.getColumns().add(domainNameCol);
        table.getColumns().add(docRootCol);
        table.getColumns().add(statusCol);
        table.getColumns().add(cateCol);
        table.getColumns().add(dateCol);
        
        table.setItems(apps);
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
    
    private List<WebApp> getApps(String query)
    {
        WebApp[] users = this.model.getWebApps(query);
        if(users == null)
        {
            return null;
        }else
        {
           return Arrays.asList(users);
        }      
    }
    
    public void reload()
    {
        this.dataList = this.getApps("");
        this.apps = FXCollections.observableList(this.dataList);
        this.table.setItems(apps);
    }
    
    public void filter(String query)
    {
       this.dataList = this.getApps(query);
       if(this.dataList == null)
       {
           ErrorView box = new ErrorView("No results were found matching\n"
               + "your query.");
           box.getWindow();
       }else
       {
           this.apps = FXCollections.observableList(this.dataList);
           this.table.setItems(apps);
       }       
    }
}
