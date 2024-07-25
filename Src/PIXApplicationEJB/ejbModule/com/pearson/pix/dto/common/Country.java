package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Contains all Country information
 */
public class Country implements Serializable 
{
   
   /**
    * The code of the Country.
    */
   private String countryCode;
   
   /**
    * The name of the Country
    */
   private String countryName;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Date and Time of Modification
    */
   private Date modDateTime;
   
   /**
    * Constructor of Country
    */
   public Country() 
   {
	super();    
   }
   
   /**
    * Gets countryCode
    * @return java.lang.String
    */
   public String getCountryCode() 
   {
	return this.countryCode;    
   }
   
   /**
    * Gets countryName
    * @return java.lang.String
    */
   public String getCountryName() 
   {
	return this.countryName;    
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
    * Gets modDateTime
    * @return Date
    */
   public Date getModDateTime() 
   {
	return this.modDateTime;    
   }
   
   /**
    * Sets countryCode
    * @param countryCode
    */
   public void setCountryCode(String countryCode) 
   {
	this.countryCode = countryCode;    
   }
   
   /**
    * Sets countryName
    * @param countryName
    */
   public void setCountryName(String countryName) 
   {
	this.countryName = countryName;    
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
    * Sets modDateTime
    * @param modDateTime
    */
   public void setModDateTime(Date modDateTime) 
   {
	this.modDateTime = modDateTime;    
   }
}
