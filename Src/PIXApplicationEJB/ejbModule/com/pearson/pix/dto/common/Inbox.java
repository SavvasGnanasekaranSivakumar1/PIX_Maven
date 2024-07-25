package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

/**
 * Contins Inbox information
 */
public class Inbox implements Serializable 
{
   
  /**
	* stores the number of days for delivery message
    */
   private Number days;
	   
   /**
    * Active Flag of user
    */
   private String activeFlag;
   
   /**
    * Inbox count
    */
   private BigDecimal count;
   
   /**
    * Date and Time of Creation
    */
   private BigDecimal creationDateTime;
   
   /**
    * display Order
    */
   private BigDecimal displayOrder;
   
   /**
    * inboxId of Inbox
    */
   private BigDecimal inboxId;
   
   /**
    * Date and Time of modification in Inbox
    */
   private BigDecimal modDateTime;
   
   /**
    * Standard Address Number is the unique identifier of a specific Inbox.
    */
   private String san;
   
   /**
    * Collection of inboxLine
    */
   private Collection inboxLineCollection;
   
   /**
    * Item Type Detail of the Inbox
    */
   private Reference itemTypeDetail;
   
   /**
    * Status Detail of the Inbox
    */
   private Status statusDetail;
   
   /**
    * Constructor of the Inbox
    */
   public Inbox() 
   {
	super();
	statusDetail=new Status();  
	itemTypeDetail=new Reference();
	this.inboxLineCollection = new Vector();    
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
    * Gets count
    * @return java.Math.BigDecimal
    */
   public BigDecimal getCount() 
   {
	return this.count;    
   }
   
   /**
    * Gets creationDateTime
    * @return java.Math.BigDecimal
    */
   public BigDecimal getCreationDateTime() 
   {
	return this.creationDateTime;    
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
    * Gets inboxId
    * @return java.Math.BigDecimal
    */
   public BigDecimal getInboxId() 
   {
	return this.inboxId;    
   }
   
   /**
    * Gets inboxLineCollection
    * @return java.util.Collection
    */
   public Collection getInboxLineCollection() 
   {
	return this.inboxLineCollection;    
   }
   
   /**
    * Gets itemTypeDetail
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getItemTypeDetail() 
   {
	return this.itemTypeDetail;    
   }
   
   /**
    * Gets ItemTypeId
    * @return java.Math.BigDecimal
    */
   public BigDecimal getItemTypeId() 
   {
	// Fill in method body here.
	return null;    
   }
   
   /**
    * Gets modDateTime
    * @return java.Math.BigDecimal
    */
   public BigDecimal getModDateTime() 
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
    * Gets statusDetail
    * @return com.pearson.pix.dto.common.Status
    */
   public Status getStatusDetail() 
   {
	return this.statusDetail;    
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
    * Sets count
    * @param count
    */
   public void setCount(BigDecimal count) 
   {
	this.count = count;    
   }
   
   /**
    * Sets creationDateTime
    * @param creationDateTime
    */
   public void setCreationDateTime(BigDecimal creationDateTime) 
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
    * Sets inboxId
    * @param inboxId
    */
   public void setInboxId(BigDecimal inboxId) 
   {
	this.inboxId = inboxId;    
   }
   
   /**
    * Sets inboxLineCollection
    * @param inboxLineCollection
    */
   public void setInboxLineCollection(Collection inboxLineCollection) 
   {
	this.inboxLineCollection = inboxLineCollection;    
   }
   
   /**
    * Sets itemTypeDetail
    * @param itemTypeDetail
    */
   public void setItemTypeDetail(Reference itemTypeDetail) 
   {
	this.itemTypeDetail = itemTypeDetail;    
   }
   
   /**
    * Sets ItemTypeId
    * @param ItemTypeId
    * @param aBigDecimal
    */
   public void setItemTypeId(BigDecimal aBigDecimal) 
   {
 	// Fill in method body here.    
   }
   
   /**
    * Sets modDateTime
    * @param modDateTime
    */
   public void setModDateTime(BigDecimal modDateTime) 
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
    * Sets statusDetail
    * @param statusDetail
    */
   public void setStatusDetail(Status statusDetail) 
   {
	this.statusDetail = statusDetail;    
   }

   /**
    * Gets delivery message days
    * @return delivery message days
    */
   public Number getDays() {
	return days;
   }

/**
 * Sets delivery message days
 * @param delivery message days
 */
public void setDays(Number days) {
	this.days = days;
}
}
