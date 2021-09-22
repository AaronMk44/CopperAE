package copper.views.tables;

import java.util.Arrays;
import java.util.List;

import copper.entities.User;
import copper.models.UserModel;
import copper.views.dialogs.ErrorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class UsersTable
{
    private UserModel model;
    private List<User> dataList;
    private TableView<User> table;
    private ObservableList<User> users;

    public UsersTable()
    {
        this.model = new UserModel();
        this.dataList = this.getUsers("");
    }
    
    public TableView getTable()
    {
        this.table = new TableView<User>();
        
        ObservableList<User> users = FXCollections.observableList(this.dataList);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory("userID"));
        
        TableColumn<User, String> firstNameCol =
            new TableColumn<User, String>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        
        TableColumn<User, String> lastNameCol = 
            new TableColumn<User, String>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        
        TableColumn<User, String> emailCol = 
            new TableColumn<User, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        
        TableColumn<User, String> genderCol = 
            new TableColumn<User, String>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        
        TableColumn<User, String> userGroupCol = 
            new TableColumn<User, String>("User Group");
        userGroupCol.setCellValueFactory(new PropertyValueFactory("userGroup"));
                
        TableColumn<User, String> isDeveloperCol = 
            new TableColumn<User, String>("is Developer");
        isDeveloperCol.setCellValueFactory(new PropertyValueFactory("isDeveloper"));
        
        TableColumn<User, String> createdAtCol = 
            new TableColumn<User, String>("Account Created");
        createdAtCol.setCellValueFactory(new PropertyValueFactory("usrCreatedAt"));
        
        table.getColumns().add(idColumn);
        table.getColumns().add(firstNameCol);
        table.getColumns().add(lastNameCol);
        table.getColumns().add(genderCol);
        table.getColumns().add(emailCol);
        table.getColumns().add(userGroupCol);
        table.getColumns().add(isDeveloperCol);
        table.getColumns().add(createdAtCol);
        
        table.setItems(users);
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
    
    private List<User> getUsers(String query)
    {
        User[] users = this.model.getUsers(query);
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
        this.dataList = this.getUsers("");
        this.users = FXCollections.observableList(this.dataList);
        this.table.setItems(users);
    }
    
    public void filter(String query)
    {
       this.dataList = this.getUsers(query);
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
