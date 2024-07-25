//Source file: E:\\ObjectsToplink\\com\\pearson\\pix\\dto\\common\\Reference.java

package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contains all Reference information
 */
public class Reference implements Serializable 
{
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Description of the Reference
    */
   private String description;
   
   /**
    * Date and Time of the Modification
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private String modifiedBy;
   
   /**
    * displayOrder of the Reference
    */
   private BigDecimal displayOrder;
   private String activeFlag;
   
   /**
    * Reference Code of the user
    */
   private String refCode;
   
   /**
    * Reference Id of the user
    */
   private Integer refId;
   
   /**
    * Table Detail of the Table
    */
   private Table tableDetail;
   
   /**
    * Constructor of the Reference
    */
   public Reference() 
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
   public String getModifiedBy() 
   {
	return this.modifiedBy;    
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
   public void setModifiedBy(String modifiedBy) 
   {
	this.modifiedBy = modifiedBy;    
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
    * Sets activeFlag
    * @param activeFlag
    */
   public void setActiveFlag(String activeFlag) 
   {
	this.activeFlag = activeFlag;    
   }
   
   /**
    * Gets displayOrder
    * @return java.Math.BigDecimal
    */
   public BigDecimal getDisplayOrder() 
   {
	return this.displayOrder;    
   }
   
   /**
    * Gets refCode
    * @return java.lang.String
    */
   public String getRefCode() 
   {
	return this.refCode;    
   }
   
   /**
    * Gets refId
    * @return java.Math.BigDecimal
    */
   public Integer getRefId() 
   {
	return this.refId;    
   }
   
   /**
    * Gets tableDetail
    * @return com.pearson.pix.dto.common.Table
    */
   public Table getTableDetail() 
   {
	return this.tableDetail;    
   }
   
   /**
    * Sets displayOrder
    * @param displayOrder
    */
   public void setDisplayOrder(BigDecimal displayOrder) 
   {
	this.displayOrder = displayOrder;    
   }
   
   /**
    * Sets refCode
    * @param refCode
    */
   public void setRefCode(String refCode) 
   {
	this.refCode = refCode;    
   }
   
   /**
    * Sets refId
    * @param refId
    */
   public void setRefId(Integer refId) 
   {
	this.refId = refId;    
   }
   
   /**
    * Sets tableDetail
    * @param tableDetail
    */
   public void setTableDetail(Table tableDetail) 
   {
	this.tableDetail = tableDetail;    
   }
}
