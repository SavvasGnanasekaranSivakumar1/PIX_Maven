package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Contins InboxLine information
 */
public class InboxLine implements Serializable 
{
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * InboxId of the InboxLine
    */
   private BigDecimal inboxId;
   
   /**
    * InboxLineNo of the inboxline
    */
   private BigDecimal inboxLineNo;
   
   /**
    * Date and Time of the Modification
    */
   private Date modDateTime;
   
   /**
    * tranrefId1 of the inboxline
    */
   private BigDecimal tranrefId1;
   
   /**
    * tranrefId2 of the inboxline
    */
   private BigDecimal tranRefId2;
   
   /**
    * Constructor of InboxLine
    */
   public InboxLine() 
   {
	super();    
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
    * Gets inboxId
    * @return java.Math.BigDecimal
    */
   public BigDecimal getInboxId() 
   {
	return this.inboxId;    
   }
   
   /**
    * Gets inboxLineNo
    * @return java.Math.BigDecimal
    */
   public BigDecimal getInboxLineNo() 
   {
	return this.inboxLineNo;    
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
    * Gets tranRefId2
    * @return java.Math.BigDecimal
    */
   public BigDecimal getTranRefId2() 
   {
	return this.tranRefId2;    
   }
   
   /**
    * Gets tranrefId1
    * @return java.Math.BigDecimal
    */
   public BigDecimal getTranrefId1() 
   {
	return this.tranrefId1;    
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
    * Sets inboxId
    * @param inboxId
    */
   public void setInboxId(BigDecimal inboxId) 
   {
	this.inboxId = inboxId;    
   }
   
   /**
    * Sets inboxLineNo
    * @param inboxLineNo
    */
   public void setInboxLineNo(BigDecimal inboxLineNo) 
   {
	this.inboxLineNo = inboxLineNo;    
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
    * Sets tranRefId2
    * @param tranRefId2
    */
   public void setTranRefId2(BigDecimal tranRefId2) 
   {
	this.tranRefId2 = tranRefId2;    
   }
   
   /**
    * Sets tranrefId1
    * @param tranrefId1
    */
   public void setTranrefId1(BigDecimal tranrefId1) 
   {
	this.tranrefId1 = tranrefId1;    
   }
}
