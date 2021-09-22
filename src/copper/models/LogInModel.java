package copper.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import copper.confidential.RemoteDatabase;

public class LogInModel
{

    private final String prefix = "zictc_intra_";
    private final String TABLE_NAME = this.prefix + "users";

    public boolean verifyCredentials(final String EMAIL, final String PASSWORD)
    {
        RemoteDatabase db = new RemoteDatabase();
        Connection conn = db.getConnection();

        String sql = "SELECT * FROM " + this.TABLE_NAME + " WHERE email = ? AND user_group = ?";
        try
        {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, EMAIL);
            stmt.setString(2, "admin");

            ResultSet results = stmt.executeQuery();

            short counter = 0;
            String hashedPassword = "";

            while (results.next())
            {
                hashedPassword = results.getString("password");
                counter++;
            }
            stmt.close();

            DeveloperModel dModel = new DeveloperModel();
            if (counter == 1 && BCrypt.checkpw(PASSWORD, hashedPassword)
                && dModel.isDeveloper(EMAIL))
            {
                db.closeConnection(conn);
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        db.closeConnection(conn);

        return false;
    }

}
