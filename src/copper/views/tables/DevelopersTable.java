package copper.views.tables;

import java.util.Arrays;
import java.util.List;

import copper.entities.Developer;
import copper.models.DeveloperModel;
import copper.views.dialogs.ErrorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DevelopersTable
{
    private DeveloperModel model;
    private List<Developer> dataList;
    private TableView<Developer> table;
    private ObservableList<Developer> users;

    public DevelopersTable()
    {
        this.model = new DeveloperModel();
        this.dataList = this.getDevelopers("");
    }
    
    public TableView getTable()
    {
        this.table = new TableView<Developer>();
        
        ObservableList<Developer> developers = FXCollections.observableList(this.dataList);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("developerID"));
        
        TableColumn<Developer, String> firstNameCol = 
            new TableColumn<Developer, String>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        
        TableColumn<Developer, String> lastNameCol = 
            new TableColumn<Developer, String>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        
        TableColumn<Developer, String> usernameCol = 
            new TableColumn<Developer, String>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        
        TableColumn<Developer, String> genderCol = 
            new TableColumn<Developer, String>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        
        TableColumn<Developer, String> emailCol = 
            new TableColumn<Developer, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        
        TableColumn<Developer, String> statusCol = 
            new TableColumn<Developer, String>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        
        TableColumn<Developer, String> hasReadLicenceCol = 
            new TableColumn<Developer, String>("Read Licence");
        hasReadLicenceCol.setCellValueFactory(new PropertyValueFactory("hasReadLicence"));
                  
        TableColumn<Developer, String> numbOfAppsCol = 
            new TableColumn<Developer, String>("#Apps");
        numbOfAppsCol.setCellValueFactory(new PropertyValueFactory("numberOfApps"));
        
        TableColumn<Developer, String> createdAtCol = 
            new TableColumn<Developer, String>("Created");
        createdAtCol.setCellValueFactory(new PropertyValueFactory("devCreatedAt"));
        
        table.getColumns().add(idColumn);
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(usernameCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(statusCol);
        table.getColumns().add(hasReadLicenceCol);
        table.getColumns().add(numbOfAppsCol);
        table.getColumns().add(createdAtCol);
        
        table.setItems(developers);
        table.setPrefWidth(700);
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
    
    private List<Developer> getDevelopers(String query)
    {
        Developer[] users = this.model.getDevelopers(query);
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
        this.dataList = this.getDevelopers("");
        this.users = FXCollections.observableList(this.dataList);
        this.table.setItems(users);
    }
    
    public void filter(String query)
    {
       this.dataList = this.getDevelopers(query);
       if(this.dataList == null)
       {
           ErrorView box = new ErrorView("No results were found matching\nyour query.");
           box.getWindow();
       }else
       {
           this.users = FXCollections.observableList(this.dataList);
           this.table.setItems(users);
       }       
    }
}
