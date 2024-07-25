package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contains UOM Information
 */
public class UOM implements Serializable 
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
    * Modification Date and Time
    */
   private Date modDateTime;
   
   /**
    * uom
    */
   private String uom;
   
   /**
    * Id of UOM
    */
   private BigDecimal uomId;
   
   /**
    * Contructor to initialize UOM
    */
   public UOM() 
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
    * Gets uom
    * @return java.lang.String
    */
   public String getUom() 
   {
		return this.uom;    
   }
   
   /**
    * Gets creationDateTime
    * @return Date
    */
   public BigDecimal getUomId() 
   {
		return this.uomId;    
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
    * Sets uom
    * @param uom
    */
   public void setUom(String uom) 
   {
		this.uom = uom;    
   }
   
   /**
    * Sets uomId
    * @param uomId
    */
   public void setUomId(BigDecimal uomId) 
   {
		this.uomId = uomId;    
   }
}
