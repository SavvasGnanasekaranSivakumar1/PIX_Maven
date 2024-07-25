package com.pearson.pix.dto.goodsreceipt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
/**
* Contains the GoodsReceiptLine Information
*/
public class GoodsReceiptLine extends DeliveryMessageLine implements
		Serializable {
	
	private String poNumber;
	private String poLineNumber;
	private String productDescription;
	private String supplierName;
	private String orgUnitCode;
	private String orgUnitName;
	private String rollNumber;
//	private String quantity;
	private String status;
	
	private String roleTracking;
	
	private BigDecimal quantity;
	private BigDecimal delQuantity;
	private BigDecimal recQuantity;
	
    /**
	* Receipt Identification(Id)
	*/
	private BigDecimal receiptId;
	
	private String productCode;
	
	/**
	* This element specifies how a shipment was received for an individual delivery 
	* line for an purchase order and purchase order line item.
	*/
	private BigDecimal receiptLineNo;
	/**
	* Number of Units(Quantity) received .
	*/
	private Integer receivedQuantity;
	/**
	* Damaged Quantity in transport
	*/
	private Integer intransitDamagedQty;
	/**
	* A numeric Value expressed in the Unit of Measure (UOM). Value contains the UOM attribute.
	*/
	private String UOM;
	/**
	* The date and optionally time on which the received goods arrived.
	*/
	private Date requestedArrivalDate;
	/**
	* The Actual arrival date of the Goods.
	*/
	private String actualArrivalDate;
	/**
	* Defines the state of GoodsRecieved as compared to DeliveryMesage specification.
	*/
	private String acceptanceDescription;
	
	private Integer msgLine;			//for good receipt post message line number
	
	private Integer cumulativeQuanReceived;		//for cumulative received quantity
	
	private Long maxToleranceVal;
	
	//RFS 5922
	/**
	* Defines the Mill Name for Particular Item
	*/
	private String millName;
	/**
	* Defines the PO Number
	*/
	private String msgNo;
	
	private String receivedBy;
	/**
	* Default Quantity for GR
	*/
	private BigDecimal defaultQuantity;
	
	private String grReceiptNo;
	/**
	* Default Quantity for GR
	*/
	private String grRecDate;
	//End of RFS Change
	
	public String getGrReceiptNo() {
		return grReceiptNo;
	}
	public void setGrReceiptNo(String grReceiptNo) {
		this.grReceiptNo = grReceiptNo;
	}
	public Integer getMsgLine() {
		return msgLine;
	}
	public void setMsgLine(Integer msgLine) {
		this.msgLine = msgLine;
	}
	/**
	 * Constructior for the GoodsReceiptLine
	 */
	public GoodsReceiptLine() {
		super();
		// TODO Auto-generated constructor stub
	}
      /**
    * Gets acceptanceDescription
    * @return java.lang.String
    */
	public String getAcceptanceDescription() {
		return acceptanceDescription;
	}
     /**
    * Sets acceptanceDescription
    * @param acceptanceDescription
    */
	public void setAcceptanceDescription(String acceptanceDescription) {
		this.acceptanceDescription = acceptanceDescription;
	}
     /**
    * Gets actualArrivalDate
    * @return Date
    */
	public String getActualArrivalDate() {
		return actualArrivalDate;
	}
    /**
    * Sets actualArrivalDate
    * @param actualArrivalDate
    */
	public void setActualArrivalDate(String actualArrivalDate) {
		this.actualArrivalDate = actualArrivalDate;
	}
    /**
    * Gets intransitDamagedQty
    * @return java.math.BigDecimal
    */
	public Integer getIntransitDamagedQty() {
		return intransitDamagedQty;
	}
     /**
    * Sets intransitDamagedQty
    * @param intransitDamagedQty
    */
	public void setIntransitDamagedQty(Integer intransitDamagedQty) {
		this.intransitDamagedQty = intransitDamagedQty;
	}
     /**
    * Gets receiptId
    * @return java.math.BigDecimal
    */
	public BigDecimal getReceiptId() {
		return receiptId;
	}
     /**
    * Sets receiptId
    * @param receiptId
    */
	public void setReceiptId(BigDecimal receiptId) {
		this.receiptId = receiptId;
	}
      /**
    * Gets receiptLineNo
    * @return java.math.BigDecimal
    */
	public BigDecimal getReceiptLineNo() {
		return receiptLineNo;
	}
     /**
    * Sets receiptLineNo
    * @param receiptLineNo
    */
	public void setReceiptLineNo(BigDecimal receiptLineNo) {
		this.receiptLineNo = receiptLineNo;
	}
      /**
    * Gets receivedQuantity
    * @return java.math.BigDecimal
    */
	public Integer getReceivedQuantity() {
		return receivedQuantity;
	}
     /**
    * Sets receivedQuantity
    * @param receivedQuantity
    */
	public void setReceivedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}
      /**
    * Gets requestedArrivalDate
    * @return Date
    */
	public Date getRequestedArrivalDate() {
		return requestedArrivalDate;
	}
     /**
    * Sets requestedArrivalDate
    * @param requestedArrivalDate
    */
	public void setRequestedArrivalDate(Date requestedArrivalDate) {
		this.requestedArrivalDate = requestedArrivalDate;
	}
     /**
    * Gets UOM
    * @return java.lang.String
    */
	public String getUOM() {
		return UOM;
	}
     /**
    * Sets uom
    * @param uom
    */
	public void setUOM(String uom) {
		UOM = uom;
	}
	
	public Integer getCumulativeQuanReceived() {
		return cumulativeQuanReceived;
	}
	public void setCumulativeQuanReceived(Integer cumulativeQuanReceived) {
		this.cumulativeQuanReceived = cumulativeQuanReceived;
	}

	public Long getMaxToleranceVal() {
		return maxToleranceVal;
	}
	public void setMaxToleranceVal(Long maxToleranceVal) {
		this.maxToleranceVal = maxToleranceVal;
	}
	//	RFS 5922
	public String getMillName() {
		return millName;
	}
	public void setMillName(String millName) {
		this.millName = millName;
	}
	public String getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public BigDecimal getDefaultQuantity() {
		return defaultQuantity;
	}
	public void setDefaultQuantity(BigDecimal defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}
	public String getGrRecDate() {
		return grRecDate;
	}
	public void setGrRecDate(String grRecDate) {
		this.grRecDate = grRecDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPoLineNumber() {
		return poLineNumber;
	}
	public void setPoLineNumber(String poLineNumber) {
		this.poLineNumber = poLineNumber;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getOrgUnitCode() {
		return orgUnitCode;
	}
	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}
	public String getOrgUnitName() {
		return orgUnitName;
	}
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
//	public String getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(String quantity) {
//		this.quantity = quantity;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public String getRoleTracking() {
		return roleTracking;
	}
	public void setRoleTracking(String roleTracking) {
		this.roleTracking = roleTracking;
	}
	public BigDecimal getDelQuantity() {
		return delQuantity;
	}
	public void setDelQuantity(BigDecimal delQuantity) {
		this.delQuantity = delQuantity;
	}
	public BigDecimal getRecQuantity() {
		return recQuantity;
	}
	public void setRecQuantity(BigDecimal recQuantity) {
		this.recQuantity = recQuantity;
	}
	
	//end of RFS change
	
}
