package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Contains all Currency information
 */
public class Currency implements Serializable 
{
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDate;
   
   /**
    * Code of the currency
    */
   private String currencyCode;
   
   /**
    * Description of the currency
    */
   private String description;
   
   /**
    * Last modified Date and Time
    */
   private Date lastModificationDate;
   
   /**
    * Last Modified By user Id
    */
   private String lastModifiedBy;
   
   /**
    * Constructor of Currency
    */
   public Currency() 
   {
	super();    
   }
   
   /**
    * Gets createdBy
    * @return java.lang.String
    */
   public String getCreatedBy() 
   {
	return this.createdBy;    
   }
   
   /**
    * Gets creationDate
    * @return Date
    */
   public Date getCreationDate() 
   {
	return this.creationDate;    
   }
   
   /**
    * Gets currencyCode
    * @return java.lang.String
    */
   public String getCurrencyCode() 
   {
	return this.currencyCode;    
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
    * Gets lastModificationDate
    * @return Date
    */
   public Date getLastModificationDate() 
   {
	return this.lastModificationDate;    
   }
   
   /**
    * Gets lastModifiedBy
    * @return java.lang.String
    */
   public String getLastModifiedBy() 
   {
	return this.lastModifiedBy;    
   }
   
   /**
    * Sets createdBy
    * @param createdBy
    */
   public void setCreatedBy(String createdBy) 
   {
	this.createdBy = createdBy;    
   }
   
   /**
    * Sets creationDate
    * @param creationDate
    */
   public void setCreationDate(Date creationDate) 
   {
	this.creationDate = creationDate;    
   }
   
   /**
    * Sets currencyCode
    * @param currencyCode
    */
   public void setCurrencyCode(String currencyCode) 
   {
	this.currencyCode = currencyCode;    
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
    * Sets lastModificationDate
    * @param lastModificationDate
    */
   public void setLastModificationDate(Date lastModificationDate) 
   {
	this.lastModificationDate = lastModificationDate;    
   }
   
   /**
    * Sets lastModifiedBy
    * @param lastModifiedBy
    */
   public void setLastModifiedBy(String lastModifiedBy) 
   {
	this.lastModifiedBy = lastModifiedBy;    
   }
}
