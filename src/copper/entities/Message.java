package copper.entities;

public class Message 
{
	private String ID;
	private String userID;
	private String title;
	private String message;
	private String status;
	private String createdAt;
		
	public Message() 
	{
		this.ID = "";
		this.userID = "";
		this.title = "";
		this.message = "";
		this.status = "";
		this.createdAt = "";
	}

	public String getID() 
	{
		return this.ID;
	}
	
	public void setID(String iD) 
	{
		this.ID = iD;
	}
	
	public String getUserID() 
	{
		return this.userID;
	}
	
	public void setUserID(String userID) 
	{
		this.userID = userID;
	}
	
	public String getTitle() 
	{
		return this.title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	public String getCreatedAt() 
	{
		return this.createdAt;
	}
	
	public void setCreatedAt(String createdAt) 
	{
		this.createdAt = createdAt;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
		
}
