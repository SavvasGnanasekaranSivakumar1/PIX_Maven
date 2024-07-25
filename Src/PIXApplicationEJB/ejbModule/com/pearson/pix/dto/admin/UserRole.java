package com.pearson.pix.dto.admin;

import java.util.Date;
import java.io.Serializable;

/**
 * Contains UserRole Details Information
 */
public class UserRole implements Serializable 
{
   
   /**
    * Created By user Id
    */
   private Integer createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Description of the UserRole
    */
   private String description;
   
   /**
    * Modified Date and Time
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private Integer modifiedBy;
   
   /**
    * roleType fo the User
    */
   private String roleType;
   
   /**
    * Constructor of the UserRole
    */
   public UserRole() 
   {
	super();    
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
    * Gets description
    * @return java.lang.String
    */
   public String getDescription() 
   {
	return this.description;    
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
    * Gets roleType
    * @return java.lang.String
    */
   public String getRoleType() 
   {
	return this.roleType;    
   }
   
   /**
    * Sets createdBy
    * @param createdBy
    */
   public void setCreatedBy(Integer createdBy) 
   {
	this.createdBy = createdBy;    
   }
   
   /**
    * Sets creationDateTime
    * @param creationDateTime
    */
   public void setCreationDateTime(Date creationDateTime) 
   {
	this.creationDateTime = creationDateTime;    
   }
   
   /**
    * Sets description
    * @param description
    */
   public void setDescription(String description) 
   {
	this.description = description;    
   }
   
   /**
    * Sets modDateTime
    * @param modDateTime
    */
   public void setModDateTime(Date modDateTime) 
   {
	this.modDateTime = modDateTime;    
   }
   
   /**
    * Sets modifiedBy
    * @param modifiedBy
    */
   public void setModifiedBy(Integer modifiedBy) 
   {
	this.modifiedBy = modifiedBy;    
   }
   
   /**
    * Sets roleType
    * @param roleType
    */
   public void setRoleType(String roleType) 
   {
	this.roleType = roleType;    
   }
}
