package copper.entities;

import org.mindrot.jbcrypt.BCrypt;

import copper.models.UserModel;

public class SecurityUtils
{

    public static String hashText(String plainText)
    {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static String generateUsername(User obj)
    {
        String username = "";

        for (int i = 0; i < 3; i++)
        {
            username += obj.getFirstName().charAt(i);
        }

        for (int i = 0; i < 3; i++)
        {
            username += obj.getLastName().charAt(i);
        }

        UserModel model = new UserModel();

        short counter = 1;
        //To avoid the error of having a user name with a long digit
        String tempUsername = username;
        tempUsername.toLowerCase();

        while (model.usernameTaken(tempUsername))
        {
            tempUsername += counter;
            counter++;
        }

        username += counter + "@copper.dev";

        return username;
    }

    public static String generatePassword(String key)
    {
        String password = "";

        String temp = SecurityUtils.hashText(key);

        for (int i = 0; i < 10; i++)
        {
            password += temp.charAt(i);
        }

        return password;
    }

}
