package com.pearson.pix.dto.admin;

import com.pearson.pix.dto.common.Reference;
import java.util.Date;
import java.io.Serializable;

/**
 * Contains PartyTransport Detail information
 */
public class PartyTransport implements Serializable 
{
   
   /**
    * Created By user Id
    */
   private Integer createdBy;
   
   /**
    * Creation Date and time
    */
   private Date creationDateTime;
   
   /**
    * folder
    */
   private String folder;
   
   /**
    * Login name of the PartyTransport.
    */
   private String login;
   
   /**
    * Modification Date and Time.
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private Integer modifiedBy;
   
   /**
    * Password of the PartyTransport
    */
   private String password;
   
   /**
    * Standard Address Number is the unique identifier of a specific party.
    */
   private String san;
   
   /**
    * Server Name
    */
   private String serverName;
   private Reference accessMethodDetail;
   
   private String putFolder;
   
   /**
    * Constructor of the PartyTransport
    */
   public PartyTransport() 
   {
	super();
	this.accessMethodDetail = new Reference();    
   }
   
   /**
    * Gets activeFlag
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getAccessMethodDetail() 
   {
	return this.accessMethodDetail;    
   }
   
   /**
    * Gets createdBy
    * @return java.lang.String
    */
   public Integer getCreatedBy() 
   {
	return this.createdBy;    
   }
   
   /**
    * Gets creationDateTime
    * @return Date
    */
   public Date getCreationDateTime() 
   {
	return this.creationDateTime;    
   }
   
   /**
    * Gets folder
    * @return java.lang.String
    */
   public String getFolder() 
   {
	return this.folder;    
   }
   
   /**
    * Gets login
    * @return java.lang.String
    */
   public String getLogin() 
   {
	return this.login;    
   }
   
   /**
    * Gets modDateTime
    * @return Date
    */
   public Date getModDateTime() 
   {
	return this.modDateTime;    
   }
   
   /**
    * Gets modifiedBy
    * @return java.lang.String
    */
   public Integer getModifiedBy() 
   {
	return this.modifiedBy;    
   }
   
   /**
    * Gets password
    * @return java.lang.String
    */
   public String getPassword() 
   {
	return this.password;    
   }
   /**
    * Gets putFolder
    * @return java.lang.String
    */
   public String getPutFolder() {
		return this.putFolder;
	}
   
   /**
    * Gets san
    * @return java.lang.String
    */
   public String getSan() 
   {
	return this.san;    
   }
   
   /**
    * Gets serverName
    * @return java.lang.String
    */
   public String getServerName() 
   {
	return this.serverName;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param accessMethodDetail
    */
   public void setAccessMethodDetail(Reference accessMethodDetail) 
   {
	this.accessMethodDetail = accessMethodDetail;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param createdBy
    */
   public void setCreatedBy(Integer createdBy) 
   {
	this.createdBy = createdBy;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param creationDateTime
    */
   public void setCreationDateTime(Date creationDateTime) 
   {
	this.creationDateTime = creationDateTime;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param folder
    */
   public void setFolder(String folder) 
   {
	this.folder = folder;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param login
    */
   public void setLogin(String login) 
   {
	this.login = login;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param modDateTime
    */
   public void setModDateTime(Date modDateTime) 
   {
	this.modDateTime = modDateTime;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param modifiedBy
    */
   public void setModifiedBy(Integer modifiedBy) 
   {
	this.modifiedBy = modifiedBy;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param password
    */
   public void setPassword(String password) 
   {
	this.password = password;    
   }
   
   /**
    * Sets putFolder
    * @param putFolder
    */
   public void setPutFolder(String putFolder) {
		this.putFolder = putFolder;
	}
   
   /**
    * Sets san
    * @param san
    */
   public void setSan(String san) 
   {
	this.san = san;    
   }
   
   /**
    * Sets name2
    * @param name2
    * @param serverName
    */
   public void setServerName(String serverName) 
   {
	this.serverName = serverName;    
   }
}
