package com.pearson.pix.dto.deliverymessage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Vector;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.common.DeliveryMessageFile; 
/**
* Contains Delivery Message Detail Information
* @author zaheer abbas
*/

public class DeliveryMessage extends POHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	* Delivery Message Identification
	*/
	private BigDecimal msgId;
	/**
	* A unique delivery identifier assigned to each DeliveryMessage message as agreed between the trading partners.
	*/
	private String msgNo;
	/**
	* Defines the type of Delivery message: either an Initial shipment advice or delivery message.
	*/
	private String msgTypeDetail;
	/**
	* Status of the DeliveryMessage
	*/
	private String status;
	/**
	 * Delivery Destination
	 */
	private String name_1;
	
	/**
	 * Message Type Identification
	 */
	private BigDecimal msg_type_id;
	/**
	 *  Message Type Description
	 */
	private String msg_type;
	/**
	* Collection of Delivery messageLine
	*/
	private Collection deliveryMsgLineCollection;
	 /**
	* Collection of line items in DeliveryMessage
	*/
	private Collection lineItemCollection;
	
	private Integer lineNo;
	
	
	
	private LinkedHashSet sanWithPOLine;
	private String materialNo;
	
	//constants for cost accounting
	private Integer deliveredQuan;
	private Integer requestedQuan;
	
	//Naveen
	private Integer receivedQuan;
	private Integer ownedQuan;
	private String ownershipMode;

	
	private Integer owningQuantity;
	
	//Naveen
	
	private String millName;
	private String printername;
	
	
	
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	/**
	 * Constructor for the DeliveryMessage
	 */
	public DeliveryMessage() {
	super();
	this.lineItemCollection = new Vector();
	
		// TODO Auto-generated constructor stub
	}
   /**
   * Gets deliveryMsgLineCollection
   * @return java.util.Collection
   */
	public Collection getDeliveryMsgLineCollection() {
		return deliveryMsgLineCollection;
	}
	
	/**
	* Gets DeliveryMessageLine
	* @return com.pearson.pix.dto.deliverymessage.DeliveryMessageLine
	*/
	public DeliveryMessageLine getDeliveryMsgLineCollectionIdx(int index) {
		if(deliveryMsgLineCollection!=null)
		{
			return (DeliveryMessageLine)((Vector)this.deliveryMsgLineCollection).get(index);
		}
		return (new DeliveryMessageLine());
	}
	
	/**
    * Sets deliveryMsgLineCollection
    * @param deliveryMsgLineCollection
    */
	public void setDeliveryMsgLineCollection(Collection deliveryMsgLineCollection) {
		this.deliveryMsgLineCollection = deliveryMsgLineCollection;
	}
	
	/**
    * Sets deliveryMessageLine
    * @param deliveryMessageLine
    */
	public void setDeliveryMsgLineCollectionIdx(int index, DeliveryMessageLine deliveryMessageLine) {
		((Vector)this.deliveryMsgLineCollection).set(index, deliveryMessageLine);			
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
  /**
   * Gets msgId
   * @return java.math.BigDecimal
   */
	public BigDecimal getMsgId() {
		return msgId;
	}
   /**
    * Sets msgId
    * @param msgId
    */
	public void setMsgId(BigDecimal msgId) {
		this.msgId = msgId;
	}
  /**
   * Gets msgNo
   * @return java.lang.String
   */
	public String getMsgNo() {
		return msgNo;
	}
  /**
    * Sets msgNo
    * @param msgNo
    */
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}
  /**
   * Gets msgType
   * @return java.lang.String
   */
	public String getMsgTypeDetail() {
		return msgTypeDetail;
	}
  /**
    * Sets msgType
    * @param msgType
    */
	public void setMsgTypeDetail(String msgTypeDetail) {
		this.msgTypeDetail = msgTypeDetail;
	}
	/**
	   * Gets msgType
	   * @return java.lang.String
	   */
		public String getName_1() {
			return name_1;
		}
	  /**
	    * Sets msgType
	    * @param msgType
	    */
		public void setName_1(String name_1) {
			this.name_1 = name_1;
		}
  /**
   * Gets status
   * @return java.lang.String
   */
	public String getStatus() {
		return status;
	}
	/**
    * Sets status
    * @param status
    */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public BigDecimal getMsg_type_id() {
		return msg_type_id;
	}
	public void setMsg_type_id(BigDecimal msg_type_id) {
		this.msg_type_id = msg_type_id;
	}
	public LinkedHashSet getSanWithPOLine() {
		return sanWithPOLine;
	}
	public void setSanWithPOLine(LinkedHashSet sanWithPOLine) {
		this.sanWithPOLine = sanWithPOLine;
	}
	public Integer getLineNo() {
		return lineNo;
	}
	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}
	
	
	//constant for cost accounting
	public Integer getDeliveredQuan() {
		return deliveredQuan;
	}
	public void setDeliveredQuan(Integer deliveredQuan) {
		this.deliveredQuan = deliveredQuan;
	}
	public Integer getRequestedQuan() {
		return requestedQuan;
	}
	public void setRequestedQuan(Integer requestedQuan) {
		this.requestedQuan = requestedQuan;
	}
	public String getMillName() {
		return millName;
	}
	public void setMillName(String millName) {
		this.millName = millName;
	}
	public String getPrintername() {
		return printername;
	}
	public void setPrintername(String printername) {
		this.printername = printername;
	}
	/**
	 * @return the receivedQuan
	 */
	public Integer getReceivedQuan() {
		return receivedQuan;
	}
	/**
	 * @param receivedQuan the receivedQuan to set
	 */
	public void setReceivedQuan(Integer receivedQuan) {
		this.receivedQuan = receivedQuan;
	}
	/**
	 * @return the ownedQuan
	 */
	public Integer getOwnedQuan() {
		return ownedQuan;
	}
	/**
	 * @param ownedQuan the ownedQuan to set
	 */
	public void setOwnedQuan(Integer ownedQuan) {
		this.ownedQuan = ownedQuan;
	}
	/**
	 * @return the ownershipMode
	 */
	public String getOwnershipMode() {
		return ownershipMode;
	}
	/**
	 * @param ownershipMode the ownershipMode to set
	 */
	public void setOwnershipMode(String ownershipMode) {
		this.ownershipMode = ownershipMode;
	}
	
}
