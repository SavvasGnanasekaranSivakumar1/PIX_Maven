package com.pearson.pix.dto.purchaseorder;

import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.UOM;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/**
 *  Contains the POLine Information of a Purchase Order
 */

public class POLine implements Serializable{

   /**
   * Additional Comments in POLine
   */
	private String additionalComments;
	/**
    * Created By user Id
    */
	private String createdBy;
    /**
    * POLine Creation Date and Time
    */
	private Date creationDateTime;
	/**
	* Estimated Delivery Date and Time of the POLine
	*/
	private String estimatedDeliveryDate;
	/**
	* Estimated Quantity
	*/
	private Integer estimatedQuantity;
    /**
	* JobNumber of the POLine
	*/
	private String jobNo;
    /**
	* Line Description of the POLine
	*/
	private String lineDecription;
	/**
	* The Sequential Number that uniquely identifes the purchase Order line item.  
    */
	private BigDecimal lineNo;
    /**
	* Modification Date and Time
	*/
	private Date modDateTime;
    /**
    * Modified By user Id
    */
	private String modifiedBy;
    /**
	* Purchase Order Identification
	*/
	private BigDecimal poId;
	/**
	* Particular Line item representing the Product/article order detail in particular Purchase Order.
	*/
	private BigDecimal poLineNo;
	/**
	* <<Purchase order release/version number>>
	*/
	private BigDecimal poVersion;
    /**
	* An element that groups together price information.
	*/
	private Collection pricesCollection;
    /**
	*  Product Classification Code 
	*/
	private String productClassifCode;
	/**
	* A group item that details the product classification according to a particular organization’s scheme
	*/
	private String productClassifDescription;
	/**
    * product code of Purchase Order
	*/
	private String productCode;
    /**
	* product Description
	*/
	private String productDescription;
    /**
	* Comments of the publishing unit
	*/
	private String pubUnitComments;
	private String pubUnitComments1;
	/**
	* Status detail of the publishing unit
	*/
	private Status pubUnitStatusDetail;
   /**
	* The DeliveryDate in  which has been requested by the user
	*/
	private String requestedDeliveryDate;
    /**
	* The total quantity which has been requested by the user
	*/
	private BigDecimal requestedQuantity;
    
	private String shipToSan;
    /**
	* Specification Id 
	*/
	private BigDecimal specId;
	/**
	* Specification Version POLine
	*/
	private BigDecimal specVersion;
    /**
	* Collection of supplied Components
	*/
	private Collection suppliedCompCollection;
    /**
	* Comments of the supplier
	*/
	private String supplierComments;
    /**
	* StatusId of the supplier
	*/
	private UOM UOMDetail;
	/**
	* Status Id of the supplier
	*/
	
	/** new added
	* Collection of line Party in POLine
	*/
	private Collection linePartyCollection;
	
	
	private Integer supplierStatusId;
   /**
   * Constructor of the POLine
   */
public POLine() {
	super();
	this.linePartyCollection = new Vector();
	this.pricesCollection = new Vector();
	this.suppliedCompCollection = new Vector();
	this.UOMDetail = new UOM();
}
  /**
    * Gets additionalComments
    * @return java.lang.String
    */
public String getAdditionalComments() {
	return this.additionalComments;
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
    * Gets estimatedDeliveryDate
    * @return String
    */
public String getEstimatedDeliveryDate() {
	return this.estimatedDeliveryDate;
}
  /**
    * Gets estimatedQuantity
    * @return Integer
    */
public Integer getEstimatedQuantity() {
	return this.estimatedQuantity;
}
  /**
    * Gets jobNo
    * @return java.lang.String
    */
public String getJobNo() {
	return this.jobNo;
}
  /**
    * Gets lineDecription
    * @return java.lang.String
    */
public String getLineDecription() {
	return this.lineDecription;
}
  /**
    * Gets lineNo
    * @return java.math.BigDecimal
    */
public BigDecimal getLineNo() {
	return this.lineNo;
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
    * Gets poId
    * @return java.lang.String
    */
public BigDecimal getPoId() {
	return this.poId;
}
  /**
    * Gets poLineNo
    * @return java.lang.String
    */
public BigDecimal getPoLineNo() {
	return this.poLineNo;
}
  /**
    * Gets poVersion
    * @return java.lang.String
    */
public BigDecimal getPoVersion() {
	return this.poVersion;
}

  /**
    * Gets priceDetail
    * @return java.util.Collection
    */
public Collection getPricesCollection() {
	return this.pricesCollection;
}

  /**
    * Gets productClassifCode
    * @return java.lang.String
    */
public String getProductClassifCode() {
	return this.productClassifCode;
}
  /**
    * Gets productClassifDescription
    * @return java.lang.String
    */
public String getProductClassifDescription() {
	return this.productClassifDescription;
}
  /**
    * Gets productCode
    * @return java.lang.String
    */
public String getProductCode() {
	return this.productCode;
}
  /**
    * Gets productDescription
    * @return java.lang.String
    */
public String getProductDescription() {
	return this.productDescription;
}
  /**
    * Gets pubUnitComments
    * @return java.lang.String
    */
public String getPubUnitComments() {
	return this.pubUnitComments;
}

  /**
    * Gets pubUnitStatusDetail
    * @return com.pearson.pix.dto.common.Status
    */
public Status getPubUnitStatusDetail() {
	return this.pubUnitStatusDetail;
}

  /**
    * Gets requestedDeliveryDate
    * @return Date
    */
public String getRequestedDeliveryDate() {
	return this.requestedDeliveryDate;
}
  /**
    * Gets requestedQuantity
    * @return java.math.BigDecimal
    */
public BigDecimal getRequestedQuantity() {
	return this.requestedQuantity;
}
  /**
    * Gets shipToSan
    * @return java.lang.String
    */
public String getShipToSan() {
	return this.shipToSan;
}
  /**
    * Gets specId
    * @return java.math.BigDecimal
    */
public BigDecimal getSpecId() {
	return this.specId;
}
  /**
    * Gets specVersion
    * @return java.math.BigDecimal
    */
public BigDecimal getSpecVersion() {
	return this.specVersion;
}
  /**
    * Gets suppliedCompCollection
    * @return java.util.Collection
    */
public Collection getSuppliedCompCollection() {
	return this.suppliedCompCollection;
}
  /**
    * Gets supplierComments
    * @return java.lang.String
    */
public String getSupplierComments() {
	return this.supplierComments;
}

  /**
    * Gets supplierStatusId
    * @return Integer
    */
public Integer getSupplierStatusId() {
	return this.supplierStatusId;
}
 
/**
   * Gets UOMDetail
   * @return com.pearson.pix.dto.common.UOM
   */

public UOM getUOMDetail() {
	return this.UOMDetail;
}
 /**
    * Sets additionalComments
    * @param additionalComments
    */
public void setAdditionalComments(String additionalComments) {
	this.additionalComments = additionalComments;
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
    * Sets estimatedDeliveryDate
    * @param estimatedDeliveryDate
    */
public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
	this.estimatedDeliveryDate = estimatedDeliveryDate;
}
 /**
    * Sets estimatedQuantity
    * @param estimatedQuantity
    */
public void setEstimatedQuantity(Integer estimatedQuantity) {
	this.estimatedQuantity = estimatedQuantity;	
}
 /**
    * Sets jobNo
    * @param jobNo
    */
public void setJobNo(String jobNo) {
	this.jobNo = jobNo;
}
 /**
    * Sets lineDecription
    * @param lineDecription
    */
public void setLineDecription(String lineDecription) {
	this.lineDecription = lineDecription;
}
 /**
    * Sets lineNo
    * @param lineNo
    */
public void setLineNo(BigDecimal lineNo) {
	this.lineNo = lineNo;
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
    * Sets poId
    * @param poId
    */
public void setPoId(BigDecimal poId) {
	this.poId = poId;
}
 /**
    * Sets poLineNo
    * @param poLineNo
    */
public void setPoLineNo(BigDecimal poLineNo) {
	this.poLineNo = poLineNo;
}
 /**
    * Sets poVersion
    * @param poVersion
    */
public void setPoVersion(BigDecimal poVersion) {
	this.poVersion = poVersion;
}

 /**
    * Sets pricesCollection
    * @param pricesCollection
    */
public void setPricesCollection(Collection pricesCollection) {
	this.pricesCollection = pricesCollection;
}

 /**
    * Sets productClassifCode
    * @param productClassifCode
    */
public void setProductClassifCode(String productClassifCode) {
	this.productClassifCode = productClassifCode;
}
 /**
    * Sets productClassifDescription
    * @param productClassifDescription
    */
public void setProductClassifDescription(String productClassifDescription) {
	this.productClassifDescription = productClassifDescription;
}
 /**
    * Sets productCode
    * @param productCode
    */
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
 /**
    * Sets productDescription
    * @param productDescription
    */
public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}
 /**
    * Sets pubUnitComments
    * @param pubUnitComments
    */
public void setPubUnitComments(String pubUnitComments) {
	this.pubUnitComments = pubUnitComments;
}

 /**
    * Sets pubUnitStatusDetail
    * @param pubUnitStatusDetail
    */

public void setPubUnitStatusDetail(Status pubUnitStatusDetail) {
	this.pubUnitStatusDetail = pubUnitStatusDetail;
}

 /**
    * Sets requestedDeliveryDate
    * @param requestedDeliveryDate
    */
public void setRequestedDeliveryDate(String requestedDeliveryDate) {
	this.requestedDeliveryDate = requestedDeliveryDate;
}
 /**
    * Sets requestedQuantity
    * @param requestedQuantity
    */
public void setRequestedQuantity(BigDecimal requestedQuantity) {
	this.requestedQuantity = requestedQuantity;
}
 /**
    * Sets shipToSan
    * @param shipToSan
    */
public void setShipToSan(String shipToSan) {
	this.shipToSan = shipToSan;
}
 /**
    * Sets specId
    * @param specId
    */
public void setSpecId(BigDecimal specId) {
	this.specId = specId;
}
 /**
    * Sets specVersion
    * @param specVersion
    */
public void setSpecVersion(BigDecimal specVersion) {
	this.specVersion = specVersion;
}
 /**
    * Sets suppliedCompCollection
    * @param suppliedCompCollection
    */
public void setSuppliedCompCollection(Collection suppliedCompCollection) {
	this.suppliedCompCollection = suppliedCompCollection;
}
 /**
    * Sets supplierComments
    * @param supplierComments
    */
public void setSupplierComments(String supplierComments) {
	this.supplierComments = supplierComments;
}

 /**
    * Sets supplierStatusId
    * @param supplierStatusId
    */
public void setSupplierStatusId(Integer supplierStatusId) {
	this.supplierStatusId = supplierStatusId;
}
 /**
    * Sets UOMDetail
    * @param UOMDetail
    */
public void setUOMDetail(UOM UOMDetail) {
	this.UOMDetail = UOMDetail;
}
public Collection getLinePartyCollection() {
	return linePartyCollection;
}
public void setLinePartyCollection(Collection linePartyCollection) {
	this.linePartyCollection = linePartyCollection;
}
public String getPubUnitComments1() {
	return pubUnitComments1;
}
public void setPubUnitComments1(String pubUnitComments1) {
	this.pubUnitComments1 = pubUnitComments1;
}

}
