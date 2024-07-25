package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contins ToleranceLimit information
 */
public class ToleranceLimit implements Serializable 
{
   
   /**
    * Active Flag of user
    */
   private String activeFlag;
   
   /**
    * Creation Date and Time
    */
   private Date creationDateTime;
   
   /**
    * Modified Date and Time
    */
   private Date modDateTime;
   
   /**
    * Standard Address Number is the unique identifier of a specific party.
    */
   private String san;
   
   /**
    * Id of the Tolerance
    */
   private BigDecimal toleranceId;
   
   /**
    * Percentage of the Tolerance
    */
   private BigDecimal tolerancePercentage;
   
   /**
    * Constructor of the ToleranceLimit
    */
   public ToleranceLimit() 
   {
		super();    
   }
   
   /**
    * Gets activeFlag
    * @return java.lang.String
    */
   public String getActiveFlag() 
   {
		return this.activeFlag;    
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
    * Gets san
    * @return java.lang.String
    */
   public String getSan() 
   {
		return this.san;    
   }
   
   /**
    * Gets toleranceId
    * @return java.math.BigDecimal
    */
   public BigDecimal getToleranceId() 
   {
		return this.toleranceId;    
   }
   
   /**
    * Gets tolerancePercentage
    * @return java.math.BigDecimal
    */
   public BigDecimal getTolerancePercentage() 
   {
		return this.tolerancePercentage;    
   }
   
   /**
    * Sets activeFlag
    * @param activeFlag
    */
   public void setActiveFlag(String activeFlag) 
   {
		this.activeFlag = activeFlag;    
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
   
   /**
    * Sets san
    * @param san
    */
   public void setSan(String san) 
   {
		this.san = san;    
   }
   
   /**
    * Sets toleranceId
    * @param toleranceId
    */
   public void setToleranceId(BigDecimal toleranceId) 
   {
		this.toleranceId = toleranceId;    
   }
   
   /**
    * Sets tolerancePercentage
    * @param tolerancePercentage
    */
   public void setTolerancePercentage(BigDecimal tolerancePercentage) 
   {
		this.tolerancePercentage = tolerancePercentage;    
   }
}
