package com.pearson.pix.dto.purchaseorder;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

import com.pearson.pix.dto.common.UOM;

/**
 *  Contains POSuppliedComp information
 */

public class POSuppliedComp implements Serializable{
	
	/**
	* Purchase Order Party detail
	*/
	private POSuppliedCompParty partyDetail;
    /**
	* Purchase Order Identification
	*/
	private BigDecimal poId;
	/**
	* Purchase Order Line Number
	*/
	private BigDecimal poLineNo;
	/**
	* PO Version 
	*/
	private BigDecimal poVersion;
	/**
	* Product code of the POSupplied Component
	*/
	private String productCode;
	/**
	* Product Description of the POSupplied Component
	*/
	private String productDescription;
	  /**
	* pubUnit comments of the POSupplied Component
	*/
	private String pubUnitComments;
  /**
	*  The Quantity element contains attributes that provide information about the
    *   type of quantity that is being communicated, the context in which the
    *   particular quantity is to be viewed
	*/
	private BigDecimal quantity;
	/**
	* QuantityType communicates information about the type of quantity being communicated and how the receiver might use the information.
	*/
	private String quantityType;
	/**
	* Date supplied component is expected to ship
	*/
	private String shipDate;
	  /**
	* supplied component Line Number
	*/
	private BigDecimal suppCompLineNo;
	  /**
	*  A numeric value expressed in the unit of measure (UOM).
	*/
	private UOM UOMDetail;

/**
* Constructor of the POSuppliedComp
*/
public POSuppliedComp() {
	super();
}
/**
 * Gets partyDetail
 * @return com.pearson.pix.dto.purchaseorder.POSuppliedCompParty
 */
public POSuppliedCompParty getPartyDetail() {
	return this.partyDetail;
}

 /**
    * Gets poId
    * @return java.math.BigDecimal
    */
public BigDecimal getPoId() {
	return this.poId;
}
 /**
    * Gets poLineNo
    * @return java.math.BigDecimal
    */
public BigDecimal getPoLineNo() {
	return this.poLineNo;
}
 /**
    * Gets poVersion
    * @return java.math.BigDecimal
    */
public BigDecimal getPoVersion() {
	return this.poVersion;
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
    * Gets quantity
    * @return java.math.BigDecimal
    */
public BigDecimal getQuantity() {
	return this.quantity;
}
 /**
    * Gets quantityType
    * @return java.lang.String
    */
public String getQuantityType() {
	return this.quantityType;
}
 /**
    * Gets shipDate
    * @return Date
    */
public String getShipDate() {
	return this.shipDate;
}
 /**
    * Gets suppCompLineNo
    * @return java.math.BigDecimal
    */
public BigDecimal getSuppCompLineNo() {
	return this.suppCompLineNo;
}
 /**
    * Gets uom
    * @return com.pearson.pix.dto.common.UOM
    */
public UOM getUOMDetail() {
	return this.UOMDetail;
}
/**
 * Sets partyDetail
 * @param partyDetail
 */
public void setPartyDetail(POSuppliedCompParty partyDetail) {
	this.partyDetail = partyDetail;
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
    * Sets quantity
    * @param quantity
    */
public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
}
 /**
    * Sets quantityType
    * @param quantityType
    */
public void setQuantityType(String quantityType) {
	this.quantityType = quantityType;
}
 /**
    * Sets shipDate
    * @param shipDate
    */
public void setShipDate(String shipDate) {
	this.shipDate = shipDate;
}
 /**
    * Sets suppCompLineNo
    * @param suppCompLineNo
    */
public void setSuppCompLineNo(BigDecimal suppCompLineNo) {
	this.suppCompLineNo = suppCompLineNo;
}
 /**
    * Sets UOMDetail
    * @param UOMDetail
    */
public void setUOMDetail(UOM UOMDetail) {
	this.UOMDetail = UOMDetail;
}

}
