package com.pearson.pix.dto.goodsreceipt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.purchaseorder.POLine;
/**
* Contains the GoodsReceipt Information
*/
public class GoodsReceipt extends DeliveryMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	* Identification of the Receipt
	*/
	private BigDecimal receiptId;
	/**
	* Receipt Number
	*/
	private String receiptNo;
	/**
	* Defines the state of GoodsRecieved as compared to DeliveryMesage specification.
	*/
	private String acceptanceDescription;
	/**
	* Defines the state of GoodsRecieved as compared to DeliveryMesage specification.
	*/
	private String headerAcceptanceDescription;
	/**
	 *  component vendor
	 */
	 private String comp_vendor;
	 /**
	  *  ship-to vendor
	  */
	 private String ship_to_vendor;
	 /**
	  * Actual arrival date
	  */
	 private Date actualarrivalDate;
	/**
	* Transaction History Number of Goods Receipt
	*/
	private BigDecimal transactionHistoryNo;
	/**
	* Collection fo Goods ReceiptLine
	*/
	private Collection goodsReceiptLineCollection;
	/**
	* Collection of line items in DeliveryMessage
	*/
	private Collection lineItemCollection;
	
	private String materialNo;
	
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	/**
	 *  Constructor of the GoodsReceipt
	 */
	public GoodsReceipt() {
		super();
		this.lineItemCollection = new Vector();
		// TODO Auto-generated constructor stub
	}
    /**
    * Gets goodsReceiptLineCollection
    * @return java.util.Collection
    */
	public Collection getGoodsReceiptLineCollection() {
		return goodsReceiptLineCollection;
	}
    /**
    * Sets goodsReceiptLineCollection
    * @param goodsReceiptLineCollection
    */
	public void setGoodsReceiptLineCollection(Collection goodsReceiptLineCollection) {
		this.goodsReceiptLineCollection = goodsReceiptLineCollection;
	}
	/**
	* Gets goodsReceiptLine
	* @return com.pearson.pix.dto.goodsReceipt.goodsReceiptLine
	*/
	public GoodsReceiptLine getGoodsReceiptLineCollectionIdx(int index) {
		return (GoodsReceiptLine)((Vector)this.goodsReceiptLineCollection).get(index);
	}

	/**
	 * Sets goodsReceiptLine
	 * @param goodsReceiptLine
	 */
		public void setGoodsReceiptLineCollectionIdx(int index, GoodsReceiptLine goodsReceiptLine) {
			((Vector)this.goodsReceiptLineCollection).set(index, goodsReceiptLine);			
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
    * Gets headerAcceptanceDescription
    * @return java.lang.String
    */
	public String getHeaderAcceptanceDescription() {
		return headerAcceptanceDescription;
	}
     /**
    * Sets headerAcceptanceDescription
    * @param headerAcceptanceDescription
    */
	public void setHeaderAcceptanceDescription(String headerAcceptanceDescription) {
		this.headerAcceptanceDescription = headerAcceptanceDescription;
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
    * Gets receiptNo
    * @return java.math.BigDecimal
    */
	public String getReceiptNo() {
		return receiptNo;
	}
     /**
    * Sets receiptNo
    * @param receiptNo
    */
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
   /**
    * Gets transactionHistoryNo
    * @return java.math.BigDecimal
    */
	public BigDecimal getTransactionHistoryNo() {
		return transactionHistoryNo;
	}
     /**
    * Sets transactionHistoryNo
    * @param transactionHistoryNo
    */
	public void setTransactionHistoryNo(BigDecimal transactionHistoryNo) {
		this.transactionHistoryNo = transactionHistoryNo;
	}
	public Date getActualarrivalDate() {
		return actualarrivalDate;
	}
	public void setActualarrivalDate(Date actualarrivalDate) {
		this.actualarrivalDate = actualarrivalDate;
	}
	public String getComp_vendor() {
		return comp_vendor;
	}
	public void setComp_vendor(String comp_vendor) {
		this.comp_vendor = comp_vendor;
	}
	public String getShip_to_vendor() {
		return ship_to_vendor;
	}
	public void setShip_to_vendor(String ship_to_vendor) {
		this.ship_to_vendor = ship_to_vendor;
	}
	
	/**
	 * Sets poLine
	 * @param poLine
	 */
		public void setLineItemCollectionIdx(int index, POLine poLine) {
			((Vector)this.lineItemCollection).set(index, poLine);			
		}
   /**
 	* Gets POLine
    * @return com.pearson.pix.dto.purchaseorder.POLine
    */
	public POLine getLineItemCollectionIdx(int index) {
	       return (POLine)((Vector)this.lineItemCollection).get(index);
	}
	
	
	
}
