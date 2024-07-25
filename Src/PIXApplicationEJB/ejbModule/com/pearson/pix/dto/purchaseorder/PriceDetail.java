package com.pearson.pix.dto.purchaseorder;

import java.math.BigDecimal;
import java.io.Serializable;

import com.pearson.pix.dto.common.Currency;

/**
 *  Contains PriceDetail Information
 */

public class PriceDetail implements Serializable{

    /**
	* Currency detail of Purchase Order
	*/
	private Currency currencyDetail;
	/**
	* General Ledger Account code
	*/
	private String glCode;
	/**
	* General Ledger Account desc
	*/
	private String glDesc;
	/**
	* Purchase Order Identification
	*/
	private BigDecimal poId;
	/**
	* Purchase Order Line Number
	*/
	private BigDecimal poLineNo;
	/**
	* Purchase Order Version number
	*/
	private BigDecimal poVersion;
	/**
	*  An element that groups together price information.
	*/
	private BigDecimal price;
/**
*  Constructor of the PriceDetail
*/
public PriceDetail() {
	super();
}
  /**
    * Gets currencyDetail
    * @return com.pearson.pix.dto.PurchaseOrder.PriceDetail
    */
public Currency getCurrencyDetail() {
	return this.currencyDetail;
}
  /**
    * Gets glCode
    * @return java.lang.String
    */
public String getGlCode() {
	return this.glCode;
}
/**
 * Gets glDesc
 * @return java.lang.String
 */
public String getGlDesc() {
	return this.glDesc;
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
    * Gets price
    * @return java.math.BigDecimal
    */
public BigDecimal getPrice() {
	return this.price;
}
 /**
    * Sets currencyDetail
    * @param currencyDetail
    */
public void setCurrencyDetail(Currency currencyDetail) {
	this.currencyDetail = currencyDetail;
}
 /**
    * Sets glCode
    * @param glCode
    */
public void setGlCode(String glCode) {
	this.glCode = glCode;
}
/**
 * Sets glDesc
 * @param glDesc
 */
public void setGlDesc(String glDesc) {
	this.glDesc = glDesc;
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
    * Sets price
    * @param price
    */
public void setPrice(BigDecimal price) {
	this.price = price;
}

}
