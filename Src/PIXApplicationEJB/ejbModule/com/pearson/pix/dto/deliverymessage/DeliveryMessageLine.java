package com.pearson.pix.dto.deliverymessage;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.purchaseorder.POLine;
/**
*  Contains the Delivery Message Line Information
*/

public class DeliveryMessageLine extends POLine implements Serializable{
  
//	private BigDecimal cuRollNumber;
//	private String cuRollNumber;
//	private BigDecimal cuQuantity;
	
	private String cuPono;
	private BigDecimal cuPoLineNo;
	private String cuProductCode;
	private String cuProductDescription;
	private String cuSupplierName;
	private String cuPrinter;
	private String cuRollNo;
	private BigDecimal cuDeliveredQty;
	private BigDecimal cuReceivedQty;
	private String cuStatus;
	private String rollDMExists;
	
	
	private static final long serialVersionUID = 1L;
	/**
	* Message Identification
	*/
	private BigDecimal msgId;
	/**
	* The sequential number that uniquely identifies the purchase order line item.
	*/
	private BigDecimal msgLineNo;
	/**
	* The Quantity Delivered by the vendor
	*/
	private Integer deliveredQuantity;
	/**
	* The Balance Quantity 
	*/
	private Integer balanceQuantity;

	/**
	* A numeric Value expressed in the Unit of Measure (UOM). Value contains the UOM attribute.
	*/
	private String uom;
	private String message_Text;
	/**
	* A group item defining a series of DeliveryDateWindow(s) in which specified
    * quantities must be delivered. 
	*/
	private Date deliveryDate;
	/**
	 * Constructor of the DeliveryMessageLine
	 */
	private BigDecimal uom_id;
	
	private String productCode;
	
	private Collection delMsgFilesCollection;
	
	private Integer postedQuantity;		//added for showing the cumulative quantity posted
	private String approvalFlag;
	private Integer fileExists;
	private Long maxToleranceVal;
	
	private String materialDesc;
	private String printer;
	private Integer colorFlag;
	private Integer totalDeliveredQuantity;	//added for showing totalReceivedQuantity on the history page for mill
		//added for showing totalReceivedQuantity on the history page for mill
	private String postedBy;   //added for showing totalReceivedQuantity on the history page for mill
	private Date postedDate;   //added for showing totalReceivedQuantity on the history page for mill
	private String poNo;
	private BigDecimal poVersion;
	//Naveen
	
	private Integer receivedQuantity;
	private Integer ownedQuantity;
	private Integer toBeOwnedQuantity;
	private String ownrshpMode;
	private Integer owningQuantity;
	private String consignmentFlag ="";
	
	//Anshu
	private String deliveredBy;
	private String materialTooltip;	
	
	private BigDecimal rtFlag;

	/**
	 *deliveryMsgNo. for this particular line item
	 */
	private String deliveryMsgNo; 
	
	
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public DeliveryMessageLine() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	  /**
    * Gets address3
    * @return java.lang.String
    */
	
	public Integer getBalanceQuantity() {
		return balanceQuantity;
	}
	/**
    * Sets balanceQuantity
    * @param balanceQuantity
    */
	public void setBalanceQuantity(Integer balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	/**
    * Gets deliveredQuantity
    * @return java.math.BigDecimal
    */
	public Integer getDeliveredQuantity() {
		return deliveredQuantity;
	}
	/**
    * Sets deliveredQuantity
    * @param deliveredQuantity
    */
	public void setDeliveredQuantity(Integer deliveredQuantity) {
		this.deliveredQuantity = deliveredQuantity;
	}
    /**
    * Gets deliveryDate
    * @return Date
    */
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	/**
    * Sets deliveryDate
    * @param deliveryDate
    */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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
    * Gets msgLineNo
    * @return java.math.BigDecimal
    */
	public BigDecimal getMsgLineNo() {
		return msgLineNo;
	}
	/**
    * Sets msgLineNo
    * @param msgLineNo
    */
	public void setMsgLineNo(BigDecimal msgLineNo) {
		this.msgLineNo = msgLineNo;
	}
    /**
    * Gets uom
    * @return java.lang.String
    */
	public String getUom() {
		return uom;
	}
	/**
    * Sets uom
    * @param uom
    */
	public void setUom(String uom) {
		this.uom = uom;
	}
	public BigDecimal getUom_id() {
		return uom_id;
	}
	public void setUom_id(BigDecimal uom_id) {
		this.uom_id = uom_id;
	}
	
	
	/**
	   * Gets delMsgFilesCollection
	   * @return java.util.Collection
	   */
		public Collection getDelMsgFilesCollection() {
			return delMsgFilesCollection;
		}
		
		/**
		* Gets DeliveryMessageLine
		* @return com.pearson.pix.dto.deliverymessage.DeliveryMessageLine
		*/
		public DeliveryMessageFile getDelMsgFilesCollectionIdx(int index) {
			return (DeliveryMessageFile)((Vector)this.delMsgFilesCollection).get(index);
		}
		
		/**
	    * Sets delMsgFilesCollection
	    * @param delMsgFilesCollection
	    */
		public void setDelMsgFilesCollection(Collection delMsgFilesCollection) {
			this.delMsgFilesCollection = delMsgFilesCollection;
		}
		
		/**
	    * Sets delMsgFilesCollection
	    * @param delMsgFilesCollection
	    */
		public void setDelMsgFilesCollectionIdx(int index, DeliveryMessageFile delMsgFilesCollection) {
			((Vector)this.delMsgFilesCollection).set(index, delMsgFilesCollection);			
		}
		
		public Integer getPostedQuantity() {
			return postedQuantity;
		}
		public void setPostedQuantity(Integer postedQuantity) {
			this.postedQuantity = postedQuantity;
		}
		public String getApprovalFlag() {
			return approvalFlag;
		}
		public void setApprovalFlag(String approvalFlag) {
			this.approvalFlag = approvalFlag;
		}
		public Integer getFileExists() {
			return fileExists;
		}
		public void setFileExists(Integer fileExists) {
			this.fileExists = fileExists;
		}
		public Long getMaxToleranceVal() {
			return maxToleranceVal;
		}
		public void setMaxToleranceVal(Long maxToleranceVal) {
			this.maxToleranceVal = maxToleranceVal;
		}
	//Gaurav
		/**
		* Gets Printer name
		* @return java.lang.String 
		*/
		public String getPrinter() {
			return printer;
		}
		/**
		    * Sets Printer Name
		    * @param java.lang.String 
		    */
			public void setPrinter(String printer) {
				this.printer = printer;
			}
			/**
			* Gets Material Description name
			* @return java.lang.String 
			*/
			public String getMaterialDesc() {
				return materialDesc;
			}
			/**
			* Sets Material Description
			* @param java.lang.String 
			*/
			public void setMaterialDesc(String materialDesc) {
					this.materialDesc = materialDesc;
			}
			/**
			* Gets Color Flag 
			* @return java.lang.Integer
			*/
			public Integer getColorFlag() {
				return colorFlag;
			}
			/**
			* Sets Color Flag
			* @param java.lang.Integer 
			*/
			public void setColorFlag(Integer colorFlag) {
					this.colorFlag = colorFlag;
			}
			/**
			* Gets totalDeliveredQuantity 
			* @return java.lang.Integer
			*/
			public Integer getTotalDeliveredQuantity() {
					return totalDeliveredQuantity;
			}
			/**
			* Sets totalDeliveredQuantity
			* @param java.lang.Integer 
			*/
			public void setTotalDeliveredQuantity(Integer totalDeliveredQuantity) {
						this.totalDeliveredQuantity = totalDeliveredQuantity;
			}
			public Integer getReceivedQuantity() {
				return receivedQuantity;
			}
			public void setReceivedQuantity(Integer receivedQuantity) {
				this.receivedQuantity = receivedQuantity;
			}
			public String getPostedBy() {
				return postedBy;
			}
			public void setPostedBy(String postedBy) {
				this.postedBy = postedBy;
			}
			public Date getPostedDate() {
				return postedDate;
			}
			public void setPostedDate(Date postedDate) {
				this.postedDate = postedDate;
			}
			public String getDeliveryMsgNo() {
				return deliveryMsgNo;
			}
			public void setDeliveryMsgNo(String deliveryMsgNo) {
				this.deliveryMsgNo = deliveryMsgNo;
			}
				public String getDeliveredBy() {
					return deliveredBy;
				}
				public void setDeliveredBy(String deliveredBy) {
					this.deliveredBy = deliveredBy;
				}
				public String getMaterialTooltip() {
					return materialTooltip;
				}
				public void setMaterialTooltip(String materialTooltip) {
					this.materialTooltip = materialTooltip;
				}
				public String getPoNo() {
					return poNo;
				}
				public void setPoNo(String poNo) {
					this.poNo = poNo;
				}
				public BigDecimal getPoVersion() {
					return poVersion;
				}
				public void setPoVersion(BigDecimal poVersion) {
					this.poVersion = poVersion;
				}
				/**
				 * @return the ownedQuantity
				 */
				public Integer getOwnedQuantity() {
					return ownedQuantity;
				}
				/**
				 * @param ownedQuantity the ownedQuantity to set
				 */
				public void setOwnedQuantity(Integer ownedQuantity) {
					this.ownedQuantity = ownedQuantity;
				}
				/**
				 * @return the ownrshpMode
				 */
				public String getOwnrshpMode() {
					return ownrshpMode;
				}
				/**
				 * @param ownrshpMode the ownrshpMode to set
				 */
				public void setOwnrshpMode(String ownrshpMode) {
					this.ownrshpMode = ownrshpMode;
				}
				/**
				 * @return the toBeOwnedQuantity
				 */
				public Integer getToBeOwnedQuantity() {
					return toBeOwnedQuantity;
				}
				/**
				 * @param toBeOwnedQuantity the toBeOwnedQuantity to set
				 */
				public void setToBeOwnedQuantity(Integer toBeOwnedQuantity) {
					this.toBeOwnedQuantity = toBeOwnedQuantity;
				}
				/**
				 * @return the owningQuantity
				 */
				public Integer getOwningQuantity() {
					return owningQuantity;
				}
				/**
				 * @param owningQuantity the owningQuantity to set
				 */
				public void setOwningQuantity(Integer owningQuantity) {
					this.owningQuantity = owningQuantity;
				}
				/**
				 * @return the consignmentFlag
				 */
				public String getConsignmentFlag() {
					return consignmentFlag;
				}
				/**
				 * @param consignmentFlag the consignmentFlag to set
				 */
				public void setConsignmentFlag(String consignmentFlag) {
					this.consignmentFlag = consignmentFlag;
				}
				/**
				 * @return the message_Text
				 */
				public String getMessage_Text() {
					return message_Text;
				}
				/**
				 * @param message_Text the message_Text to set
				 */
				public void setMessage_Text(String message_Text) {
					this.message_Text = message_Text;
				}
				public BigDecimal getRtFlag() {
					return rtFlag;
				}
				public void setRtFlag(BigDecimal rtFlag) {
					this.rtFlag = rtFlag;
				}
				public String getCuPono() {
					return cuPono;
				}
				public void setCuPono(String cuPono) {
					this.cuPono = cuPono;
				}
				public BigDecimal getCuPoLineNo() {
					return cuPoLineNo;
				}
				public void setCuPoLineNo(BigDecimal cuPoLineNo) {
					this.cuPoLineNo = cuPoLineNo;
				}
				public String getCuProductCode() {
					return cuProductCode;
				}
				public void setCuProductCode(String cuProductCode) {
					this.cuProductCode = cuProductCode;
				}
				public String getCuProductDescription() {
					return cuProductDescription;
				}
				public void setCuProductDescription(String cuProductDescription) {
					this.cuProductDescription = cuProductDescription;
				}
				public String getCuSupplierName() {
					return cuSupplierName;
				}
				public void setCuSupplierName(String cuSupplierName) {
					this.cuSupplierName = cuSupplierName;
				}
				public String getCuPrinter() {
					return cuPrinter;
				}
				public void setCuPrinter(String cuPrinter) {
					this.cuPrinter = cuPrinter;
				}
				public String getCuRollNo() {
					return cuRollNo;
				}
				public void setCuRollNo(String cuRollNo) {
					this.cuRollNo = cuRollNo;
				}
				public BigDecimal getCuDeliveredQty() {
					return cuDeliveredQty;
				}
				public void setCuDeliveredQty(BigDecimal cuDeliveredQty) {
					this.cuDeliveredQty = cuDeliveredQty;
				}
				public BigDecimal getCuReceivedQty() {
					return cuReceivedQty;
				}
				public void setCuReceivedQty(BigDecimal cuReceivedQty) {
					this.cuReceivedQty = cuReceivedQty;
				}
				public String getCuStatus() {
					return cuStatus;
				}
				public void setCuStatus(String cuStatus) {
					this.cuStatus = cuStatus;
				}
				public String getRollDMExists() {
					return rollDMExists;
				}
				public void setRollDMExists(String rollDMExists) {
					this.rollDMExists = rollDMExists;
				}

//				public BigDecimal getCuQuantity() {
//					return cuQuantity;
//				}
//				public void setCuQuantity(BigDecimal cuQuantity) {
//					this.cuQuantity = cuQuantity;
//				}
//				public String getCuRollNumber() {
//					return cuRollNumber;
//				}
//				public void setCuRollNumber(String cuRollNumber) {
//					this.cuRollNumber = cuRollNumber;
//				}
				
				
}
