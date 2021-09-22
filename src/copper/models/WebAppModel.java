package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import copper.confidential.RemoteDatabase;
import copper.entities.WebApp;

public class WebAppModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "webapps";

    public WebAppModel()
    {
    }

    public boolean insert(WebApp obj)
    {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "INSERT INTO " + this.TABLE_NAME + 
            " (developer_id, app_name, domain_name, document_root, logo_root, "
            + "category_id, languages, seo_keywords, brief_description, status)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDeveloperID());
            stmt.setString(2, obj.getAppName());
            stmt.setString(3, obj.getDomainName());
            stmt.setString(4, obj.getDocumentRoot());
            stmt.setString(5, obj.getLogoRoot());
            stmt.setString(6, obj.getCategoryID());
            stmt.setString(7, obj.getLanguages());
            stmt.setString(8, obj.getSeoKeywords());
            stmt.setString(9, obj.getBriefDescription());
            stmt.setString(10, obj.getStatus());

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

    public WebApp getWebApp(String query)
    {
        WebApp obj = new WebApp();

        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE app_name = ? "
            + "OR domain_name = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, query);
            stmt.setString(2, query);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                obj.setID(results.getInt("id") + "");
                obj.setDeveloperID(results.getString("developer_id"));
                obj.setAppName(results.getString("app_name"));
                obj.setDomainName(results.getString("domain_name"));
                obj.setDocumentRoot(results.getString("document_root"));
                obj.setLogoRoot(results.getString("logo_root"));
                obj.setCategoryID(results.getString("category_id"));
                obj.setLanguages(results.getString("languages"));
                obj.setSeoKeywords(results.getString("seo_keywords"));
                obj.setBriefDescription(results.getString("brief_description"));
                obj.setCreatedAt(results.getTimestamp("created_at").toString());
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

    public boolean update(WebApp obj)
    {
        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "UPDATE " + this.TABLE_NAME + 
            " SET app_name = ?, domain_name = ?, logo_root = ?, category_id = ?, "
            + "languages = ?, seo_keywords = ?, brief_description = ?, status = ?"
            + "WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getAppName());
            stmt.setString(2, obj.getDomainName());
            stmt.setString(3, obj.getLogoRoot());
            stmt.setString(4, obj.getCategoryID());
            stmt.setString(5, obj.getLanguages());
            stmt.setString(6, obj.getSeoKeywords());
            stmt.setString(7, obj.getBriefDescription());
            stmt.setString(8, obj.getStatus());
            stmt.setInt(9, Integer.parseInt(obj.getID()));

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

    public boolean delete(String ID)
    {
        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "DELETE FROM " + this.TABLE_NAME + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(ID));

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

    public WebApp[] getWebApps(String query)
    {
        WebApp[] data = null;

        if(query.equals(""))
        {
            /*This block will execute when fetching all apps*/ 
            RemoteDatabase dbr = new RemoteDatabase();
            Connection conn = dbr.getConnection();

            data = new WebApp[this.getNumberOfWebApps()];
            
            String sql = "SELECT * FROM "
            + "((zictc_intra_webapps INNER JOIN zictc_intra_developers "
            + "ON zictc_intra_webapps.developer_id = zictc_intra_developers.id) "
            + "INNER JOIN zictc_intra_webapp_categories "
            + "ON zictc_intra_webapps.category_id = zictc_intra_webapp_categories.id)";      
        
            try
            {
                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet results = stmt.executeQuery();

                int counter = 0;
                while (results.next())
                {
                    data[counter] = new WebApp();
                    data[counter].setID(results.getString("id"));
                    data[counter].setDeveloperID(results.getString("developer_id"));
                    data[counter].setAppName(results.getString("app_name"));
                    data[counter].setDomainName(results.getString("domain_name"));
                    data[counter].setDocumentRoot(results.getString("document_root"));
                    data[counter].setLogoRoot(results.getString("logo_root"));
                    data[counter].setCategoryID(results.getString("category_id"));
                    data[counter].setCategoryName(results.getString("zictc_intra_webapp_categories.category_name"));                        
                    data[counter].setLanguages(results.getString("languages"));
                    data[counter].setSeoKeywords(results.getString("seo_keywords"));
                    data[counter].setBriefDescription(results.getString("brief_description"));
                    data[counter].setStatus(results.getString("zictc_intra_webapps.status"));
                    data[counter].setCreatedAt(results.getTimestamp("created_at").toString());

                    counter++;
                }
                
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            
            dbr.closeConnection(conn);

            return data;
            
        }else
        {   /*otherwise, it means we want to get apps by the query*/
            int numberOfAppsByQuery = this.getNumberOfAppsBy(query);            
            if(numberOfAppsByQuery == 0)
            {
                return null;
            }else
            {
                /*This block will be executed when the search query is defined 
                  and results will be returned*/
  
                RemoteDatabase dbr = new RemoteDatabase();
                Connection conn = dbr.getConnection();

                data = new WebApp[numberOfAppsByQuery];
                
                data = new WebApp[this.getNumberOfWebApps()];
                String sql = "SELECT * FROM "
                + "((zictc_intra_webapps INNER JOIN zictc_intra_developers "
                + "ON zictc_intra_webapps.developer_id = zictc_intra_developers.id) "
                + "INNER JOIN zictc_intra_webapp_categories "
                + "ON zictc_intra_webapps.category_id = zictc_intra_webapp_categories.id)"
                + "WHERE zictc_intra_webapps.developer_id LIKE ? OR zictc_intra_webapps.app_name LIKE ? OR"
                + "zictc_intra_webapps.domain_name LIKE ? OR zictc_intra_webapps.document_root LIKE ? OR"
                + "zictc_intra_webapps.logo_root LIKE ? OR zictc_intra_webapp_categories.category_name LIKE ? OR"
                + "zictc_intra_webapps.languages LIKE ? OR zictc_intra_webapps.status LIKE ? OR"
                + "zictc_intra_webapps.seo_keywords LIKE ? OR zictc_intra_webapps.brief_description LIKE ? OR"
                + "zictc_intra_webapp_categories.web_app_ext LIKE ?";      

                try
                {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, "%"+query+"%");
                    stmt.setString(2, "%"+query+"%");
                    stmt.setString(3, "%"+query+"%");
                    stmt.setString(4, "%"+query+"%");
                    stmt.setString(5, "%"+query+"%");
                    stmt.setString(6, "%"+query+"%");
                    stmt.setString(7, "%"+query+"%");
                    stmt.setString(8, "%"+query+"%");
                    stmt.setString(9, "%"+query+"%");
                    stmt.setString(10, "%"+query+"%");
                    stmt.setString(11, "%"+query+"%");

                    ResultSet results = stmt.executeQuery();

                    int counter = 0;
                    while (results.next())
                    {
                        data[counter] = new WebApp();
                        data[counter].setID(results.getString("id"));
                        data[counter].setDeveloperID(results.getString("developer_id"));
                        data[counter].setAppName(results.getString("app_name"));
                        data[counter].setDomainName(results.getString("domain_name"));
                        data[counter].setDocumentRoot(results.getString("document_root"));
                        data[counter].setLogoRoot(results.getString("logo_root"));
                        data[counter].setCategoryID(results.getString("category_id"));
                        data[counter].setCategoryName(results.getString("zictc_intra_webapp_categories.category_name"));                        
                        data[counter].setLanguages(results.getString("languages"));
                        data[counter].setSeoKeywords(results.getString("seo_keywords"));
                        data[counter].setBriefDescription(results.getString("brief_description"));
                        data[counter].setStatus(results.getString("zictc_intra_webapps.status"));
                        data[counter].setCreatedAt(results.getTimestamp("created_at").toString());

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

    public int getSize(String ID)
    {
        int size = 0;

        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE developer_id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                size++;
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

        return size;
    }
    
    public int getNumberOfWebApps()
    {
        int size = 0;

        /*
	* Get connection
        */
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        /*
	* Use connection
        */
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
	* Close connection
        */
        dbr.closeConnection(conn);

        return size;
    }
    
    public int getNumberOfApps(String devID)
    {
        int size = 0;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE id = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(devID));
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
    
     public int getNumberOfAppsBy(String query)
    {
        int size = 0;

        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();
        
        String sql = "SELECT * FROM "
            + "((zictc_intra_webapps INNER JOIN zictc_intra_developers "
            + "ON zictc_intra_webapps.developer_id = zictc_intra_developers.id) "
            + "INNER JOIN zictc_intra_webapp_categories "
            + "ON zictc_intra_webapps.category_id = zictc_intra_webapp_categories.id)"
            + "WHERE zictc_intra_webapps.developer_id LIKE ? OR zictc_intra_webapps.app_name LIKE ? OR"
            + "zictc_intra_webapps.domain_name LIKE ? OR zictc_intra_webapps.document_root LIKE ? OR"
            + "zictc_intra_webapps.logo_root LIKE ? OR zictc_intra_webapp_categories.category_name LIKE ? OR"
            + "zictc_intra_webapps.languages LIKE ? OR zictc_intra_webapps.status LIKE ? OR"
            + "zictc_intra_webapps.seo_keywords LIKE ? OR zictc_intra_webapps.brief_description LIKE ? OR"
            + "zictc_intra_categories.web_app_ext LIKE ?";      
        
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+query+"%");
            stmt.setString(2, "%"+query+"%");
            stmt.setString(3, "%"+query+"%");
            stmt.setString(4, "%"+query+"%");
            stmt.setString(5, "%"+query+"%");
            stmt.setString(6, "%"+query+"%");
            stmt.setString(7, "%"+query+"%");
            stmt.setString(8, "%"+query+"%");
            stmt.setString(9, "%"+query+"%");
            stmt.setString(10, "%"+query+"%");
            stmt.setString(11, "%"+query+"%");
            
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
     
     public boolean isDNSTaken(String dns)
     {
        RemoteDatabase dbr = new RemoteDatabase();
        Connection conn = dbr.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE domain_name = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dns);

            ResultSet results = stmt.executeQuery();

            while (results.next())
            {
                dbr.closeConnection(conn);
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        dbr.closeConnection(conn);
        return false;
     }
}
