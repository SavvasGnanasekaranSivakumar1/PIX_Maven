package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contins Table information
 */
public class Table implements Serializable 
{
   
   /**
    * Active Flag of user
    */
   private String activeFlag;
   
   /**
    * Date and Time of the Creation
    */
   private Date creationDateTime;
   
   /**
    * Description of the Table
    */
   private String description;
   
   /**
    * Date and Time of the Modification
    */
   private Date modDateTime;
   
   /**
    * Id of the Table
    */
   private BigDecimal tableId;
   
   /**
    * name of the Table
    */
   private String tableName;
   
   /**
    * Constructor of the Table
    */
   public Table() 
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
    * Gets tableId
    * @return java.math.BigDecimal
    */
   public BigDecimal getTableId() 
   {
		return this.tableId;    
   }
   
   /**
    * Gets tableName
    * @return java.lang.String
    */
   public String getTableName() 
   {
		return this.tableName;    
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
    * Sets tableId
    * @param tableId
    */
   public void setTableId(BigDecimal tableId) 
   {
		this.tableId = tableId;    
   }
   
   /**
    * Sets tableName
    * @param tableName
    */
   public void setTableName(String tableName) 
   {
		this.tableName = tableName;    
   }
}
