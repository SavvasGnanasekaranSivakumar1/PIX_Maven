package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contins Status information
 */
public class Status implements Serializable 
{
   
	private String titleISBN;
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * Active Flag of user
    */
   private String activeFlag;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * displayOrder
    */
   private BigDecimal displayOrder;
   
   /**
    * Date and Time of the Modification
    */
   private Date modDateTime;
   
   /**
    * code of the status
    */
   private String statusCode;
   
   /**
    * Description of the status
    */
   private String statusDescription;
   
   /**
    * Id of the status
    */
   private Integer statusId;
   
   /**
    * Detail of the Table
    */
   private Table tableDetail;
   
   /**
    * Constructor of the Status
    */
   public Status() 
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
    * Gets displayOrder
    * @return java.math.BigDecimal
    */
   public BigDecimal getDisplayOrder() 
   {
		return this.displayOrder;    
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
    * Gets statusCode
    * @return java.lang.String
    */
   public String getStatusCode() 
   {
		return this.statusCode;    
   }
   
   /**
    * Gets statusDescription
    * @return java.lang.String
    */
   public String getStatusDescription() 
   {
		return this.statusDescription;    
   }
   
   /**
    * Gets statusId
    * @return Integer
    */
   public Integer getStatusId() 
   {
		return this.statusId;    
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
    * Sets displayOrder
    * @param displayOrder
    */
   public void setDisplayOrder(BigDecimal displayOrder) 
   {
		this.displayOrder = displayOrder;    
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
    * Sets statusCode
    * @param statusCode
    */
   public void setStatusCode(String statusCode) 
   {
		this.statusCode = statusCode;    
   }
   
   /**
    * Sets statusDescription
    * @param statusDescription
    */
   public void setStatusDescription(String statusDescription) 
   {
		this.statusDescription = statusDescription;    
   }
   
   /**
    * Sets statusId
    * @param statusId
    */
   public void setStatusId(Integer statusId) 
   {
		this.statusId = statusId;    
   }
   
   /**
    * Sets tableDetail
    * @param tableDetail
    */
   public void setTableDetail(Table tableDetail) 
   {
		this.tableDetail = tableDetail;    
   }

/**
 * @return the titleISBN
 */
public String getTitleISBN() {
	return titleISBN;
}

/**
 * @param titleISBN the titleISBN to set
 */
public void setTitleISBN(String titleISBN) {
	this.titleISBN = titleISBN;
}
}
