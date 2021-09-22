package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.User;

public class UserModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "users";

    public UserModel()
    {
    }

    public boolean insert(User obj)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "INSERT INTO " + this.TABLE_NAME + 
            " (first_name, last_name, gender, email, password, user_group) "
            + "VALUES(?,?,?,?,?,?)";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getFirstName());
            stmt.setString(2, obj.getLastName());
            stmt.setString(3, obj.getGender());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getUsrPassword());
            stmt.setString(6, obj.getUserGroup());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        /*
	* Close connection
        */
        dbr.closeConnection(conn);

        return false;
    }

    public User getUser(String email)
    {
        User obj = new User();

        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                obj.setUserID(results.getInt("id") + "");
                obj.setFirstName(results.getString("first_name"));
                obj.setLastName(results.getString("last_name"));
                obj.setEmail(results.getString("email"));
                obj.setGender(results.getString("gender"));
                obj.setUsrPassword(results.getString("password"));
                obj.setUserGroup(results.getString("user_group"));
                obj.setIsDeveloper(this.isDeveloper(results.getInt("id") + ""));
                obj.setUsrCreatedAt(results.getTimestamp("created_at").toString());
            }

        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
	* Close connection
        */
        dbr.closeConnection(conn);

        return obj;
    }

    public boolean update(User obj)
    {
        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "UPDATE " + this.TABLE_NAME + " SET first_name = ?, last_name = ?, gender = ?, email = ?, user_group = ?"
            + "WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getFirstName());
            stmt.setString(2, obj.getLastName());
            stmt.setString(3, obj.getGender());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getUserGroup());
            stmt.setInt(6, Integer.parseInt(obj.getUserID()));

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return false;
    }

    public boolean delete(String email)
    {
        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection [delete also the developer ]
        */
        String sql = "DELETE FROM " + this.TABLE_NAME + " WHERE email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
	* Close connection
        */
        dbr.closeConnection(conn);

        return false;
    }
    
    public User[] getUsers(String query)
    {     
        User[] data = null;
        
        if(query.equals(""))
        {
            /*This block will execute when fetching all users*/ 
            RemoteDatabase dbr = new RemoteDatabase();
            Connection conn = dbr.getConnection();

            data = new User[this.getNumberOfUsers()];
            String sql = "SELECT * FROM " + this.TABLE_NAME;
            try
            {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet results = stmt.executeQuery();

                int counter = 0;
                while (results.next())
                {
                    data[counter] = new User();
                    data[counter].setUserID(results.getInt("id") + "");
                    data[counter].setFirstName(results.getString("first_name"));
                    data[counter].setLastName(results.getString("last_name"));
                    data[counter].setEmail(results.getString("email"));
                    data[counter].setGender(results.getString("gender"));
                    data[counter].setUsrPassword(results.getString("password"));
                    data[counter].setUserGroup(results.getString("user_group"));
                    data[counter].setIsDeveloper(this.isDeveloper(data[counter].getUserID()));
                    data[counter].setUsrCreatedAt(results.getTimestamp("created_at").toString());

                    counter++;
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            
            dbr.closeConnection(conn);
            return data;
            
        }else
        {   /*otherwise, it means we want to get user by the query*/
            int numberOfUserByQuery = this.getNumberOfUsersBy(query);            
            if(numberOfUserByQuery == 0)
            {
                return null;
            }else
            {
                /*This block will be executed when the search query is defined 
                  and results will be returned*/
  
                RemoteDatabase dbr = new RemoteDatabase();
                Connection conn = dbr.getConnection();

                data = new User[numberOfUserByQuery];
                String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR user_group LIKE ?";
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
                        data[counter] = new User();
                        data[counter].setUserID(results.getInt("id") + "");
                        data[counter].setFirstName(results.getString("first_name"));
                        data[counter].setLastName(results.getString("last_name"));
                        data[counter].setEmail(results.getString("email"));
                        data[counter].setGender(results.getString("gender"));
                        data[counter].setUsrPassword(results.getString("password"));
                        data[counter].setUserGroup(results.getString("user_group"));
                        data[counter].setIsDeveloper(this.isDeveloper(data[counter].getUserID()));
                        data[counter].setUsrCreatedAt(results.getTimestamp("created_at").toString());

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
    
    public boolean isDeveloper(String ID)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT developer_status FROM zictc_intra_developers "
            + "WHERE user_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                if (results.getString("developer_status").equals(""))
                {
                    return false;
                } else
                {
                    return true;
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        dbr.closeConnection(conn);
        return false;
    }

    public String getStatus(String ID)
    {
        String status = null;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT status FROM zictc_intra_developers WHERE user_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                status = results.getString("status");
            }

            return status;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);

        return status;
    }
    
    /*
    This method os used by the username generator method to check if the username
    generated is taken
    */
    public boolean usernameTaken(String username)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT developer_status FROM zictc_intra_developers "
            + "WHERE username = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                if (results.getString("developer_status").length() == 0)
                {
                    return false;
                } else
                {
                    return true;
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
    }
    
    public boolean isUser(String email)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT password FROM " + this.TABLE_NAME + " WHERE email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                if (results.getString("password").length() == 0)
                {
                    return false;
                } else
                {
                    return true;
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
    }
    
    public int getNumberOfUsers()
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
    
    public int getNumberOfUsersBy(String query)
    {
        int size = 0;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();
        
        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR user_group LIKE ?";
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
    
    public String getUserID(String email)
    {
        RemoteDatabase rdb = new RemoteDatabase();
        Connection conn = rdb.getConnection();
        
        String sql = "SELECT id FROM " + this.TABLE_NAME + " WHERE email = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                return results.getInt("id") + "";
            }
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
