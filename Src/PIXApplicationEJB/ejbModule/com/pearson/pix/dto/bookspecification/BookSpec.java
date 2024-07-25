package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.planning.Planning;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

/**
 * Contains all BookSpecification information
 */
public class BookSpec implements Serializable 
{
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Modification Date and Time
    */
   private Date modDateTime;
   
   /**
    * modified By userId
    */
   private String modifiedBy;
   
   /**
    * Used to indicate the specification must conform to the NASTA requirements
    */
   private String nasta;
   
   /**
    * SpecificationId
    */
   private BigDecimal specId;
   
   /**
    * Acknowledgement flag
    */
   private String ackflag;
   
   /**
    * Issue Date of the Book Specification
    */
   private Date specIssueDate;
   
   /**
    * Specification No
    */
   private String specNo;
   
   /**
    * Book Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * Total pagecount in the Book
    */
   private BigDecimal totalPageCount;
   
   /**
    * ReleaseNo of the Book Specification
    */
   private String releaseNo;
   
   /**
    * status Detail of the Book Specification
    */
   private Status statusDetail;
   
   /**
    * transactionHistoryNo of the Book Specification
    */
   private BigDecimal transactionHistoryNo;
   
   /**
    * Collection of LineItems
    */
   private Collection lineItemCollection;
   
   /**
    * Collection of Party
    */
   private Collection partyCollection;
   
   /**
    * titleDetail of the Book Specification
    */
   private TitlePrinting titleDetail;
   
   /**
    * Contructor to initialize BookSpec
    */
   public BookSpec() 
   {
	super();
	this.lineItemCollection = new Vector();
	this.partyCollection = new Vector();  
	titleDetail = new TitlePrinting();
	statusDetail=new Status();  
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
    * Gets lineItemCollection
    * @return java.util.Collection
    */
   public Collection getLineItemCollection() 
   {
	return this.lineItemCollection;    
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
    * Gets nasta
    * @return java.lang.String
    */
   public String getNasta() 
   {
		return this.nasta;    
   }
   
   /**
    * Gets specId
    * @return java.math.BigDecimal
    */
   public BigDecimal getSpecId() 
   {
		return this.specId;    
   }
   
   /**
    * Gets ackflag
    * @return String
    */
   public String getAckflag() 
   {
		return this.ackflag;    
   }
   
   /**
    * Gets specIssueDate
    * @return Date
    */
   public Date getSpecIssueDate() 
   {
		return this.specIssueDate;    
   }
   
   /**
    * Gets specNo
    * @return java.lang.String
    */
   public String getSpecNo() 
   {
		return this.specNo;    
   }
   
   /**
    * Gets totalPageCount
    * @return java.math.BigDecimal
    */
   public BigDecimal getTotalPageCount() 
   {
		return this.totalPageCount;    
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
    * Sets lineItemCollection
    * @param lineItemCollection
    */
   public void setLineItemCollection(Collection lineItemCollection) 
   {
		this.lineItemCollection = lineItemCollection;    
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
    * Sets nasta
    * @param nasta
    */
   public void setNasta(String nasta) 
   {
		this.nasta = nasta;    
   }
   
   /**
    * Sets specId
    * @param specId
    */
   public void setSpecId(BigDecimal specId) 
   {
		this.specId = specId;    
   }
   
   /**
    * Sets ackflag
    * @param ackflag
    */
   public void setAckflag(String ackflag) 
   {
		this.ackflag = ackflag;    
   }
   
   /**
    * Sets specIssueDate
    * @param specIssueDate
    */
   public void setSpecIssueDate(Date specIssueDate) 
   {
		this.specIssueDate = specIssueDate;    
   }
   
   /**
    * Sets specNo
    * @param specNo
    */
   public void setSpecNo(String specNo) 
   {
		this.specNo = specNo;    
   }
   
   /**
    * Sets totalPageCount
    * @param totalPageCount
    */
   public void setTotalPageCount(BigDecimal totalPageCount) 
   {
		this.totalPageCount = totalPageCount;    
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
	* Gets partyCollection
	* @return java.util.Collection
	*/
	public BookSpecParty getPartyCollectionIdx(int index) {
		return (BookSpecParty)((Vector)this.partyCollection).get(index);
	}
 
   /**
    * Gets releaseNo
    * @return java.lang.String
    */
   public String getReleaseNo() 
   {
		return this.releaseNo;    
   }
   
   /**
    * Gets specVersion
    * @return java.math.BigDecimal
    */
   public BigDecimal getSpecVersion() 
   {
		return this.specVersion;    
   }
   
   /**
    * Gets statusDetail
    * @return com.pearson.pix.dto.common.Status
    */
   public Status getStatusDetail() {
		return this.statusDetail;
	}
   
   /**
    * Gets titleDetail
    * @return com.pearson.pix.dto.common.TitlePrinting
    */
   public TitlePrinting getTitleDetail() 
   {
		return this.titleDetail;    
   }
   
   /**
    * Gets transactionHistoryNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getTransactionHistoryNo() 
   {
		return this.transactionHistoryNo;    
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
    * Sets partyCollection
    * @param partyCollection
    */
	public void setPartyCollectionIdx(int index, BookSpecParty bookSpecParty) {
			((Vector)this.partyCollection).set(index, bookSpecParty);			
	}
   
   /**
    * Sets releaseNo
    * @param releaseNo
    */
   public void setReleaseNo(String releaseNo) 
   {
		this.releaseNo = releaseNo;    
   }
   
   /**
    * Sets specVersion
    * @param specVersion
    */
   public void setSpecVersion(BigDecimal specVersion) 
   {
		this.specVersion = specVersion;    
   }
   
   /**
    * Sets statusDetail
    * @param statusDetail
    */
   public void setStatusDetail(Status statusDetail) {
		this.statusDetail = statusDetail;
	}
   
   /**
    * Sets titleDetail
    * @param titleDetail
    */
   public void setTitleDetail(TitlePrinting titleDetail) 
   {
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
}
