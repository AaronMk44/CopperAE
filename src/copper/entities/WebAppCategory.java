package copper.entities;

public class WebAppCategory
{

    private String id;
    private String name;
    private String extension;

    public WebAppCategory()
    {
        this.id = "";
        this.name = "";
        this.extension = "";
    }

    public String getID()
    {
        return this.id;
    }

    public void setID(String iD)
    {
        this.id = iD;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String categoryName)
    {
        this.name = categoryName;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public void setExtension(String webAppExt)
    {
        this.extension = webAppExt;
    }

}
