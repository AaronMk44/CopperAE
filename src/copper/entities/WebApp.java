package copper.entities;

public class WebApp
{
    private String appID;
    private String developerID;
    private String appName;
    private String domainName;
    private String documentRoot;
    private String logoRoot;
    private String categoryID;
    private String categoryName;
    private String languages;
    private String seoKeywords;
    private String briefDescription;
    private String status;
    private String createdAt;

    public WebApp()
    {
        this.appID = "";
        this.developerID = "";
        this.appName = "";
        this.domainName = "";
        this.documentRoot = "";
        this.logoRoot = "";
        this.categoryID = "";
        this.categoryName = "";
        this.languages = "";
        this.seoKeywords = "";
        this.briefDescription = "";
        this.status = "";
        this.createdAt = "";
    }

    public String getID()
    {
        return this.appID;
    }

    public void setID(String iD)
    {
        this.appID = iD;
    }

    public String getDeveloperID()
    {
        return this.developerID;
    }

    public void setDeveloperID(String developerID)
    {
        this.developerID = developerID;
    }

    public String getAppName()
    {
        return this.appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getDomainName()
    {
        return this.domainName;
    }

    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }

    public String getDocumentRoot()
    {
        return this.documentRoot;
    }

    public void setDocumentRoot(String documentRoot)
    {
        this.documentRoot = documentRoot;
    }

    public String getLogoRoot()
    {
        return this.logoRoot;
    }

    public void setLogoRoot(String logoRoot)
    {
        this.logoRoot = logoRoot;
    }

    public String getCategoryID()
    {
        return this.categoryID;
    }

    public void setCategoryID(String categoryID)
    {
        this.categoryID = categoryID;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
    
    public String getLanguages()
    {
        return this.languages;
    }

    public void setLanguages(String languages)
    {
        this.languages = languages;
    }

    public String getSeoKeywords()
    {
        return this.seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords)
    {
        this.seoKeywords = seoKeywords;
    }

    public String getBriefDescription()
    {
        return this.briefDescription;
    }

    public void setBriefDescription(String briefDescription)
    {
        this.briefDescription = briefDescription;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

}
