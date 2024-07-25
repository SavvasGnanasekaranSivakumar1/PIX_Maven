package com.pearson.pix.dto.purchaseorder;

import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.dto.common.Currency;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.common.UOM;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.io.Serializable;

import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;
import com.pearson.pix.dto.purchaseorder.DropshipDTO;

/**
 *  Contains POHeader Detials Information
 */

public class POHeader implements Serializable{
   /**
   * Acknowledgement 
   */
	private String acknowledgeFlag;

	private String activeFlag;
	private String stat;
	private List<DropshipDTO> dropshipDTOList;
	private List<DropshipDTO> dropshipshippinfinfo;
	
	
    /**
    * Created By user Id
    */
	private String createdBy;
	/**
    * Date and Time of Creation
    */
	private Date creationDateTime;
    /**
    * CurrencyDetial of the POHeader
    */
	private Currency currencyDetail;
    /**
    * IssueDate of the POHeader
    */
	private Date issueDate;
    /**
	* jobNo of the POHeader
	*/
	private String jobNo;
    /**
	* Collection of line items in POHeader
	*/
	private Collection lineItemCollection;
    /**
	* Modified Date and Time.
	*/
	private Date modDateTime;
   	/**
    * Modified By user Id
    */
	private String modifiedBy;
	/**
	* Party Collection 
	*/
	private Collection partyCollection;
    /**
	* PurchaseOrder Identification
	*/

	private BigDecimal poId;
    /**
	* PurchaseOrder Number
	*/
	private String poNo;
    /**
	* Purchase Order posted Date
	*/
	private Date postedDate;
	/**
	* Purchase Order Version
	*/
	private BigDecimal poVersion;
    /**
	*  Release Number of the purchase order
	*/
	private Integer releaseNo;
	/**
     * Status Detail of POHeader
	 */
	private Status statusDetail;
    /**
	* termsConditions
	*/
	private String termsConditions;
	/**
	* termsConditions1
	*/
	private String termsConditions1;
	/**
	* gang name
	*/
	private String gangName;
    /**
	* Title Description of the POHeader
	*/
	private String titleDesc;
    /**
	* TitleId of the POHeader
	*/
	private TitlePrinting titleDetail;
    /**
	* A field containing the total amount including tax.
	*/
	private BigDecimal totalAmount;
	/**
	* Total Quantity is primarirly used in the summary section of message where it is repeatable to permit totalling for diferent units of measures.
	*/
	private BigDecimal totalQuantity;
    /**
	* A sequential number that keeps track of the version of a document being sent by the document originator 
	*/
	private BigDecimal transactionHistoryNo;
	/**
	* Unit of Measure Detail
	*/
	private UOM UOMDetail;
	/**
	* Book Spec Detail
	*/
	private ValueHolderInterface bookSpecDetail;
	
	/**
	* Order Type of PO (S/R) 
	*/
	private String orderType;
	
	private Integer lineItemCount; //in case of paper PO
	
	private String paperGrade;
	
	private String GlcodeDesc;

	  public String getGlcodeDesc() {
		return GlcodeDesc;
	}
	public void setGlcodeDesc(String glcodeDesc) {
		GlcodeDesc = glcodeDesc;
	}
	public String getPaperGrade() {
		return paperGrade;
	}
	public void setPaperGrade(String paperGrade) {
		this.paperGrade = paperGrade;
	}
	public Integer getLineItemCount() {
			return lineItemCount;
		}
		public void setLineItemCount(Integer lineItemCount) {
			this.lineItemCount = lineItemCount;
		}
/**
  * Constructor of the POHeader
  */
	public POHeader() {
		super();
		this.bookSpecDetail = new ValueHolder();
		this.lineItemCollection = new Vector();
		this.partyCollection = new Vector();
		titleDetail=new TitlePrinting();
		statusDetail=new Status();
	}
	 /**
		* Gets acknowledgeFlag
		* @return java.lang.String
		*/

	public String getAcknowledgeFlag() {
		return this.acknowledgeFlag;
	}
	 /**
		* Gets activeFlag
		* @return java.lang.String
		*/
	public String getActiveFlag() {
		return this.activeFlag;
	}
	 /**
		* Gets bookSpecDetail
		* @return com.pearson.pix.dto.BoolSpecification.BookSpec
		*/
	public BookSpec getBookSpecDetail() {
		return (BookSpec) this.bookSpecDetail.getValue();
	}
	/**
	* Gets bookSpecDetailHolder
	* @return ValueHolderInterface
	*/
	protected ValueHolderInterface getBookSpecDetailHolder() {
		return this.bookSpecDetail;
	}
	 /**
		* Gets createdBy
		* @return java.lang.String
		*/
	public String getCreatedBy() {
		return this.createdBy;
	}
	 /**
		* Gets creationDateTime
		* @return Date
		*/
	public Date getCreationDateTime() {
		return this.creationDateTime;
	}
	 /**
		* Gets currencyDetail
		* @return com.pearson.pix.dto.common.Currency
		*/
	public Currency getCurrencyDetail() {
		return this.currencyDetail;
	}
	 /**
		* Gets issueDate
		* @return Date
		*/
	public Date getIssueDate() {
		return this.issueDate;
	}
	 /**
		* Gets jobNo
		* @return java.lang.String
		*/
	public String getJobNo() {
		return this.jobNo;
	}
	 /**
		* Gets lineItemCollection
		* @return java.math.Collection
		*/
	public Collection getLineItemCollection() {
		return this.lineItemCollection;
	}
	
	/**
	* Gets POLine
	* @return com.pearson.pix.dto.purchaseorder.POLine
	*/
	public POLine getLineItemCollectionIdx(int index) {
		return (POLine)((Vector)this.lineItemCollection).get(index);
	}
	
	 /**
		* Gets modDateTime
		* @return Date
		*/
	public Date getModDateTime() {
		return this.modDateTime;
	}
	 /**
		* Gets modifiedBy
		* @return java.lang.String
		*/
	public String getModifiedBy() {
		return this.modifiedBy;
	}
	
	/**
	* Gets orderType
	* @return java.lang.String
	*/
	public String getOrderType() {
		return this.orderType;
	}
	
	 /**
		* Gets partyCollection
		* @return java.util.Collection
		*/
	public Collection getPartyCollection() {
		return this.partyCollection;
	}
	
	/**
	* Gets POParty
	* @return com.pearson.pix.dto.purchaseorder.POParty
	*/
	public POParty getPartyCollectionIdx(int index) {
		return (POParty)((Vector)this.partyCollection).get(index);
	}

	 /**
		* Gets poId
		* @return java.lang.String
		*/
	public BigDecimal getPoId() {
		return this.poId;
	}
	 /**
		* Gets poNo
		* @return java.lang.String
		*/
	public String getPoNo() {
		return this.poNo;
	}
	 /**
		* Gets poVersion
		* @return java.math.BigDecimal
		*/
	public BigDecimal getPoVersion() {
		return this.poVersion;
	}
	 /**
		* Gets postedDate
		* @return Date
		*/
	public Date getPostedDate() {
		return this.postedDate;
	}
	 /**
		* Gets releaseNo
		* @return java.lang.String
		*/
	public Integer getReleaseNo() {
		return this.releaseNo;
	}
	 /**
	* Gets statusId
	* @return com.pearson.pix.dto.common.Status
	*/
	public Status getStatusDetail() {
		return this.statusDetail;
	}
	 /**
		* Gets termsConditions
		* @return java.lang.String
		*/
	public String getTermsConditions() {
		return this.termsConditions;
	}
	 /**
		* Gets titleDesc
		* @return java.lang.String
		*/
	public String getTitleDesc() {
		return this.titleDesc;
	}
	 /**
		* Gets titleDetail
		* @return com.pearson.pix.dto.common.TitlePrinting
		*/
	public TitlePrinting getTitleDetail() {
		return this.titleDetail;
	}
	 /**
		* Gets totalAmount
		* @return java.math.BigDecimal
		*/
	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}
	 /**
		* Gets totalQuantity
		* @return java.math.BigDecimal
		*/
	public BigDecimal getTotalQuantity() {
		return this.totalQuantity;
	}
	public String getStat() {
		return this.stat;
	}
	 /**
		* Gets transactionHistoryNo
		* @return java.math.BigDecimal
		*/
	public BigDecimal getTransactionHistoryNo() {
		return this.transactionHistoryNo;
	}
	 /**
		* Gets UOMDetail
		* @return com.pearson.pix.dto.common.UOM
		*/
	public UOM getUOMDetail() {
		return this.UOMDetail;
	}
    /**
    * Sets acknowledgeFlag
    * @param acknowledgeFlag
    */
	public void setAcknowledgeFlag(String acknowledgeFlag) {
		this.acknowledgeFlag = acknowledgeFlag;
	}
    /**
    * Sets activeFlag
    * @param activeFlag
    */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
    /**
    * Sets bookSpecDetail
    * @param bookSpecDetail
    */
	public void setBookSpecDetail(BookSpec bookSpecDetail) {
		this.bookSpecDetail.setValue(bookSpecDetail);
	}
	/**
    * Sets bookSpecDetail
    * @param bookSpecDetail
    */
	protected void setBookSpecDetailHolder(ValueHolderInterface bookSpecDetail) {
		this.bookSpecDetail = bookSpecDetail;
	}
    /**
    * Sets createdBy
    * @param createdBy
    */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    /**
    * Sets creationDateTime
    * @param creationDateTime
    */
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
    /**
    * Sets currencyDetail
    * @param currencyDetail
    */
	public void setCurrencyDetail(Currency currencyDetail) {
		this.currencyDetail = currencyDetail;
	}
    /**
    * Sets issueDate
    * @param issueDate
    */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
    /**
    * Sets jobNo
    * @param jobNo
    */
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
    /**
    * Sets lineItemCollection
    * @param lineItemCollection
    */
	public void setLineItemCollection(Collection lineItemCollection) {
		this.lineItemCollection = lineItemCollection;
	}
	
	/**
    * Sets poLine
    * @param poLine
    */
	public void setLineItemCollectionIdx(int index, POLine poLine) {
		((Vector)this.lineItemCollection).set(index, poLine);			
	}
		
    /**
    * Sets modDateTime
    * @param modDateTime
    */
	public void setModDateTime(Date modDateTime) {
		this.modDateTime = modDateTime;
	}
    /**
    * Sets modifiedBy
    * @param modifiedBy
    */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
    
	/**
    * Sets orderType
    * @param orderType
    */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	/**
    * Sets partyCollection
    * @param partyCollection
    */
	public void setPartyCollection(Collection partyCollection) {
		this.partyCollection = partyCollection;		
	}
	
	/**
    * Sets poParty
    * @param poParty
    */
	public void setPartyCollectionIdx(int index, POParty poParty) {
			((Vector)this.partyCollection).set(index, poParty);			
	}
		
   /**
    * Sets poId
    * @param poId
    */
	public void setPoId(BigDecimal poId) {
		this.poId = poId;
	}
   /**
    * Sets poNo
    * @param poNo
    */
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
    /**
    * Sets poVersion
    * @param poVersion
    */
	public void setPoVersion(BigDecimal poVersion) {
		this.poVersion = poVersion;
	}
   /**
    * Sets postedDate
    * @param postedDate
    */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
    /**
    * Sets releaseNo
    * @param releaseNo
    */
	public void setReleaseNo(Integer releaseNo) {
		this.releaseNo = releaseNo;
	}
  /**
   * Sets statusDetail
   * @param statusDetail
   */
	public void setStatusDetail(Status statusDetail) {
		this.statusDetail = statusDetail;
	}
    /**
    * Sets termsConditions
    * @param termsConditions
    */
	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}
    /**
    * Sets titleDesc
    * @param titleDesc
    */
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
    /**
    * Sets titleDetail
    * @param titleDetail
    */
	public void setTitleDetail(TitlePrinting titleDetail) {
		this.titleDetail = titleDetail;
	}
    /**
    * Sets totalAmount
    * @param totalAmount
    */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
    /**
    * Sets totalQuantity
    * @param totalQuantity
    */
	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
    /**
    * Sets transactionHistoryNo
    * @param transactionHistoryNo
    */
	public void setTransactionHistoryNo(BigDecimal transactionHistoryNo) {
		this.transactionHistoryNo = transactionHistoryNo;
	}
    /**
    * Sets UOMDetail
    * @param UOMDetail
    */
	public void setUOMDetail(UOM UOMDetail) {
		this.UOMDetail = UOMDetail;
	}
	
	public void setStat(String stat) {
		this.stat=stat;
		
	}
	 /**
	    * Gets TermsConditions1
	    */
	public String getTermsConditions1() {
		return termsConditions1;
	}
	 /**
	    * Sets TermsConditions1
	    * @param termsConditions1
	    */
	public void setTermsConditions1(String termsConditions1) {
		this.termsConditions1 = termsConditions1;
	}
	/**
	    * Sets gangName
	    */
	public String getGangName() {
		return gangName;
	}
	/**
	    * Sets gangName
	    * @param gangName
	    */
	public void setGangName(String gangName) {
		this.gangName = gangName;
	}
	/**
	 * @return the pixLink
	 */
	public List<DropshipDTO> getDropshipDTOList() {
		return dropshipDTOList;
	}
	public void setDropshipDTOList(List<DropshipDTO> dropshipDTOList) {
		this.dropshipDTOList = dropshipDTOList;
	}
	/**
	 * @return the dropshipshippinfinfo
	 */
	public List<DropshipDTO> getDropshipshippinfinfo() {
		return dropshipshippinfinfo;
	}
	/**
	 * @param dropshipshippinfinfo the dropshipshippinfinfo to set
	 */
	public void setDropshipshippinfinfo(List<DropshipDTO> dropshipshippinfinfo) {
		this.dropshipshippinfinfo = dropshipshippinfinfo;
	}
	
	
}
