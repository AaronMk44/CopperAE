package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.Developer;

public class DeveloperModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "developers";

    public boolean insert(Developer obj)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sqlCreateDeveloper = "INSERT INTO " + this.TABLE_NAME + 
            " (user_id, username, password, has_read_licence, developer_status, "
            + "db_account_status) VALUES(?,?,?,?,?)";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sqlCreateDeveloper);
            stmt.setString(1, obj.getUserID());
            stmt.setString(2, obj.getUsername());
            stmt.setString(3, obj.getDevPassword());
            stmt.setString(4, obj.getHasReadLicence());
            stmt.setString(5, obj.getStatus());
           
            stmt.execute();
            stmt.close();

            dbr.closeConnection(conn);
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
    }
      

    public Developer getDeveloper(String email)
    {
        Developer dev = null;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM zictc_intra_developers "
            + "INNER JOIN zictc_intra_users ON zictc_intra_developers.user_id = zictc_intra_users.id "
            + "WHERE email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                dev = new Developer();
                dev.setUserID(results.getInt("zictc_intra_users.id") + "");
                dev.setFirstName(results.getString("first_name"));
                dev.setLastName(results.getString("last_name"));
                dev.setGender(results.getString("gender"));
                dev.setEmail(results.getString("email"));
                dev.setUsrPassword(results.getString("password"));
                dev.setUserGroup(results.getString("user_group"));
                dev.setIsDeveloper(true);
                dev.setUsrCreatedAt(results.getTimestamp("created_at").toString());
                dev.setDeveloperID(results.getInt("zictc_intra_developers.id") + "");
                dev.setUsername(results.getString("username"));
                dev.setDevPassword(results.getString("password"));
                dev.setStatus(results.getString("developer_status"));
                dev.setNumberOfApps(this.getNumberOfApps(dev.getDeveloperID()));
                dev.setHasReadLicence(results.getString("has_read_licence"));
                dev.setDevCreatedAt(results.getTimestamp("created_at").toString());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return dev;
    }
    
    public boolean delete(String id)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sqlDeleteDeveloper = "DELETE FROM " + this.TABLE_NAME + 
            " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sqlDeleteDeveloper);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.execute();
            stmt.close();
 
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        String sqlDeleteDeveloper2 = "DELETE FROM zictc_intra_developer_ledger "+
            " WHERE developer_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sqlDeleteDeveloper2);
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            
            dbr.closeConnection(conn);
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        dbr.closeConnection(conn);
        return false;
    }
     
    public Developer[] getDevelopers(String query)
    {     
        Developer[] data = null;
        
        if(query.equals(""))
        {
            /* This block will execute when fetching all users*/
            RemoteDatabase dbr = new RemoteDatabase();
            Connection conn = dbr.getConnection();

            data = new Developer[this.getNumberOfDevelopers()];
            String sql = "SELECT * FROM " + this.TABLE_NAME +" "
                       + "INNER JOIN zictc_intra_users ON " + this.TABLE_NAME + 
                         ".user_id = zictc_intra_users.id";
            try
            {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet results = stmt.executeQuery();

                int counter = 0;
                while (results.next())
                {
                    data[counter] = new Developer();
                    data[counter].setUserID(results.getInt("zictc_intra_users.id") + "");
                    data[counter].setFirstName(results.getString("first_name"));
                    data[counter].setLastName(results.getString("last_name"));
                    data[counter].setGender(results.getString("gender"));
                    data[counter].setEmail(results.getString("email"));
                    data[counter].setUsrPassword(results.getString("password"));
                    data[counter].setUserGroup(results.getString("user_group"));
                    data[counter].setIsDeveloper(true);
                    data[counter].setUsrCreatedAt(results.getTimestamp("created_at").toString());
                    data[counter].setDeveloperID(results.getInt("zictc_intra_developers.id") + "");
                    data[counter].setUsername(results.getString("username"));
                    data[counter].setDevPassword(results.getString("zictc_intra_developers.password"));
                    data[counter].setStatus(results.getString("developer_status"));
                    data[counter].setNumberOfApps(this.getNumberOfApps(data[counter].getDeveloperID()));
                    data[counter].setHasReadLicence(results.getString("has_read_licence"));
                    data[counter].setDevCreatedAt(results.getTimestamp("created_at").toString());

                    counter++;
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            dbr.closeConnection(conn);

            return data;
            
        }else
        {
            int numberOfDeveloperByQuery = this.getNumberOfDevelopersBy(query);
            //otherwise, it means we want to get user by the query
            if(numberOfDeveloperByQuery == 0)
            {
                //no results were found
                return null;
            }else
            {
                /**
                 * This block will be executed when the search query is defined and results
                 * will be returned
                 */
                
                /*
                * Get connection
                */
                RemoteDatabase dbr = new RemoteDatabase();
                Connection conn = dbr.getConnection();

                /*
                * Use connection
                */
                data = new Developer[numberOfDeveloperByQuery];
                String sql = "SELECT * FROM " + this.TABLE_NAME +" "                   
                    + "INNER JOIN zictc_intra_users ON " + this.TABLE_NAME + ".user_id = zictc_intra_users.id"
                    + "WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR status LIKE ?";
                try
                {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, "%"+query+"%");
                    stmt.setString(2, "%"+query+"%");
                    stmt.setString(3, "%"+query+"%");
                    stmt.setString(4, "%"+query+"%");

                    ResultSet results = stmt.executeQuery();

                    int counter = 0;
                    while (results.next())
                    {
                        data[counter] = new Developer();
                        data[counter].setUserID(results.getInt("zictc_intra_users.id") + "");
                        data[counter].setFirstName(results.getString("first_name"));
                        data[counter].setLastName(results.getString("last_name"));
                        data[counter].setGender(results.getString("gender"));
                        data[counter].setEmail(results.getString("email"));
                        data[counter].setUsrPassword(results.getString("password"));
                        data[counter].setUserGroup(results.getString("user_group"));
                        data[counter].setIsDeveloper(true);
                        data[counter].setUsrCreatedAt(results.getTimestamp("created_at").toString());
                        data[counter].setDeveloperID(results.getInt("zictc_intra_developers.id") + "");
                        data[counter].setUsername(results.getString("username"));
                        data[counter].setDevPassword(results.getString("zictc_intra_developers.password"));
                        data[counter].setStatus(results.getString("developer_status"));
                        data[counter].setNumberOfApps(this.getNumberOfApps(data[counter].getDeveloperID()));
                        data[counter].setHasReadLicence(results.getString("has_read_licence"));
                        data[counter].setDevCreatedAt(results.getTimestamp("created_at").toString());

                        counter++;
                    }

                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                dbr.closeConnection(conn);                
                return data;
            }
        }     
    }
    
    public boolean isDeveloper(String email)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT " + this.TABLE_NAME + ".developer_status FROM " + this.TABLE_NAME + " "
            + "INNER JOIN zictc_intra_users ON " + this.TABLE_NAME + ".user_id = zictc_intra_users.ID "
            + "WHERE zictc_intra_users.email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                dbr.closeConnection(conn);
                return true;
            }
            dbr.closeConnection(conn);
            return false;
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
    }

    public boolean isApprovedAndSetup(Developer dev)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT " + this.TABLE_NAME + ".developer_status, " + 
            this.TABLE_NAME + ".accounts_status FROM " + this.TABLE_NAME + " "
            + "WHERE username = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dev.getUsername());

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                if(results.getString("developer_status").equals("approved")
                    && results.getString("accounts_status").equals("created"))
                {
                    dbr.closeConnection(conn);
                    return true;
                }else
                {
                    dbr.closeConnection(conn);
                    return false;
                }
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        dbr.closeConnection(conn);
        return false;
    }
    
    public boolean approve(Developer dev)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();
        
        String npwd = this.getNormalPwd(dev.getDeveloperID());

        String sql = 
        	  "update " + this.TABLE_NAME + " "
	  		+ "set developer_status = 'approved', db_account_status = 'created' "
	  		+ "WHERE id = "+dev.getDeveloperID()+"; ";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);              

              stmt.execute();
              stmt.close();
              dbr.closeConnection(conn);
              return true;

          } catch (SQLException e)
          {
              e.printStackTrace();
          }

        dbr.closeConnection(conn);
        return false;
    }
    
    public int getNumberOfDevelopers()
    {
        int size = 0;
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME;
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while (results.next())
            {
                size++;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        dbr.closeConnection(conn);
        return size;
    }
    
    public int getNumberOfDevelopersBy(String query)
    {
        int size = 0;
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT id, first_name, last_name, username, email, status, "
                    + "created_at FROM " + this.TABLE_NAME +" "
                    + "WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR status LIKE ?"
                    + "INNER JOIN zictc_intra_users ON " + this.TABLE_NAME + 
                    ".user_id = zictc_intra_users.id";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+query+"%");
            stmt.setString(2, "%"+query+"%");
            stmt.setString(3, "%"+query+"%");
            stmt.setString(4, "%"+query+"%");
            
            ResultSet results = stmt.executeQuery();            
            while (results.next())
            {
                size++;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return size;
    }
    
    private int getNumberOfApps(String id)
    {
        WebAppModel model = new WebAppModel();
        return model.getNumberOfApps(id);
    }
    
    public String getNormalPwd(String id)
    {
        int size = 0;
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT password FROM zictc_intra_developer_ledger "
                + "WHERE developer_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet results = stmt.executeQuery();
            String data;
            while (results.next())
            {
                data = results.getString("password");
                dbr.closeConnection(conn);
                return data;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        dbr.closeConnection(conn);
        return "";
    }

}
