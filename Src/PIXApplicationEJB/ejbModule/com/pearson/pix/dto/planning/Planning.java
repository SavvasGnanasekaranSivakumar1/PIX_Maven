package com.pearson.pix.dto.planning;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.common.UOM;



/**
 * Contains all Planning information
 */
public class Planning implements Serializable 
{
  /**
    * The date and time this version of planning data was issued.
    */
   private Date issueDate;
   
   /**
    * planVersion of the plan
    */
   private BigDecimal planVersion;
   
     /**
    * Modified By user Id
    */
   private String modifiedBy;
   
   /**
    * Modification Date and Time
    */
   private Date modDateTime;
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * issuedBy name
    */
   private String issuedBy;
   
   /**
    * Creation Date and Time
    */
   private Date creationDateTime;
   
   /**
    * Book Bound Date
    */
   private Date bbd;
   
   /**
    * quantity
    */
   private BigDecimal quantity;
   
   /**
    * Identification of the plan
    */
   private BigDecimal planId;
   
   /**
    * title Detail of the plan
    */
   private TitlePrinting titleDetail;
   
   /**
    * transactionHistoryNo of the plan
    */
   private BigDecimal transactionHistoryNo;
   
   /**
    * partyCollection of the party
    */
   private Collection partyCollection;
   
   /**
    * Status detail of the planning
    */
   private Status statusDetail;
   
   /**
    * UOMDetail of the plan
    */
   private UOM UOMDetail;
   
   /**
    * Constructor to initialize
    */
   public Planning() 
   {
	super();
	titleDetail = new TitlePrinting();
	statusDetail=new Status();  
   }
   
   /**
    * Gets bbd
    * @return Date
    */
   public Date getBbd() 
   {
	return this.bbd;    
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
    * Gets issueDate
    * @return Date
    */
   public Date getIssueDate() 
   {
	return this.issueDate;    
   }
   
   /**
    * Gets issuedBy
    * @return Date
    */
   public String getIssuedBy() 
   {
	return this.issuedBy;    
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
    * Gets planId
    * @return java.Math.BigDecimal
    */
   public BigDecimal getPlanId() 
   {
	return this.planId;    
   }
   
   /**
    * Gets planVersion
    * @return java.Math.BigDecimal
    */
   public BigDecimal getPlanVersion() 
   {
	return this.planVersion;    
   }
   
   /**
    * Gets quantity
    * @return java.Math.BigDecimal
    */
   public BigDecimal getQuantity() 
   {
	return this.quantity;    
   }
   
   /**
    * Sets bbd
    * @param bbd
    */
   public void setBbd(Date bbd) 
   {
	this.bbd = bbd;    
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
    * Sets issueDate
    * @param issueDate
    */
   public void setIssueDate(Date issueDate) 
   {
	this.issueDate = issueDate;    
   }
   
   /**
    * Sets issuedBy
    * @param issuedBy
    */
   public void setIssuedBy(String issuedBy) 
   {
	this.issuedBy = issuedBy;    
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
    * Sets planId
    * @param planId
    */
   public void setPlanId(BigDecimal planId) 
   {
	this.planId = planId;    
   }
   
   /**
    * Sets planVersion
    * @param planVersion
    */
   public void setPlanVersion(BigDecimal planVersion) 
   {
	this.planVersion = planVersion;    
   }
   
   /**
    * Sets quantity
    * @param quantity
    */
   public void setQuantity(BigDecimal quantity) 
   {
	this.quantity = quantity;    
   }
   
   /**
    * Gets partyCollection
    * @return java.util.Collection
    */
   public Collection getPartyCollection() 
   {
	return this.partyCollection;    
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
    * Gets titleDetail
    * @return com.pearson.pix.dto.common.TitlePrinting
    */
   public TitlePrinting getTitleDetail() {
		return this.titleDetail;
	}
   
   /**
    * Gets transactionHistoryNo
    * @return java.Math.BidDecimal
    */
   public BigDecimal getTransactionHistoryNo() 
   {
	return this.transactionHistoryNo;    
   }
   
   /**
    * Gets UOMDetail
    * @return com.pearson.pix.dto.common.UOW
    */
   public UOM getUOMDetail() 
   {
	return this.UOMDetail;    
   }
   
   /**
    * Sets partyCollection
    * @param partyCollection
    */
   public void setPartyCollection(Collection partyCollection) 
   {
	this.partyCollection = partyCollection;    
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
    * Sets titleDetail
    * @param titleDetail
    */
   public void setTitleDetail(TitlePrinting titleDetail) {
		this.titleDetail = titleDetail;
	}
   
   /**
    * Sets transactionHistoryNo
    * @param transactionHistoryNo
    */
   public void setTransactionHistoryNo(BigDecimal transactionHistoryNo) 
   {
	this.transactionHistoryNo = transactionHistoryNo;    
   }
   
   /**
    * Sets UOMDetail
    * @param UOMDetail
    */
   public void setUOMDetail(UOM UOMDetail) 
   {
	this.UOMDetail = UOMDetail;    
   }

}
