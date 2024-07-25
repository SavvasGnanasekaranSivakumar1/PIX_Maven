package com.pearson.pix.dto.usage;

import java.io.Serializable;
import java.math.BigDecimal;

import com.pearson.pix.dto.purchaseorder.POLine;

public class UsageLine extends POLine implements Serializable {
	/**
	 * Unique Identification of the Usage
	 */
	private BigDecimal usageId;
	/**
	 * A sequential number that uniquely identifies the Usage line item.
	 */
	private BigDecimal usageLineNo;
	/**
	 * Usage of the Line Item quantity
	 */
	private Integer usageQuantity;
	/**
	 * Damaged quantity of the line item
	 */
	private Integer damagedQuantity;
	/**
	 * Unit Of Measure of the quantity
	 */
	private String UOM;
	/**
	 * Supplement Component Line no
	 */
	private BigDecimal suppCompLineNo;
	/**
	 * Comments for the Line Item
	 */
	private String comments;
	/**
	 * Status of the Line Item
	 */
	private String status;
	/**
	 * Constructor to intialize UsageLine
	 */
	public UsageLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gets comments
	 * @return java.lang.String
	 */	
	public String getComments() {
		return comments;
	}
	/**
    * Sets comments
    * @param comments
    */	
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * Gets damagedQuantity
	 * @return java.math.BigDecimal
	 */
	public Integer getDamagedQuantity() {
		return damagedQuantity;
	}
	/**
    * Sets damagedQuantity
    * @param damagedQuantity
    */	
	public void setDamagedQuantity(Integer damagedQuantity) {
		this.damagedQuantity = damagedQuantity;
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
	/**
	 * Gets suppCompLineNo
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getSuppCompLineNo() {
		return suppCompLineNo;
	}
	/**
    * Sets suppCompLineNo
    * @param suppCompLineNo
    */
	public void setSuppCompLineNo(BigDecimal suppCompLineNo) {
		this.suppCompLineNo = suppCompLineNo;
	}
	/**
	 * Gets UOM
	 * @return java.lang.String
	 */	
	public String getUOM() {
		return UOM;
	}
	/**
    * Sets UOM
    * @param UOM
    */
	public void setUOM(String uom) {
		UOM = uom;
	}
	/**
	 * Gets usageId
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getUsageId() {
		return usageId;
	}
	/**
    * Sets usageId
    * @param usageId
    */
	public void setUsageId(BigDecimal usageId) {
		this.usageId = usageId;
	}
	/**
	 * Gets usageLineNo
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getUsageLineNo() {
		return usageLineNo;
	}
	/**
    * Sets usageLineNo
    * @param usageLineNo
    */
	public void setUsageLineNo(BigDecimal usageLineNo) {
		this.usageLineNo = usageLineNo;
	}
	/**
	 * Gets usageQuantity
	 * @return java.math.BigDecimal
	 */
	public Integer getUsageQuantity() {
		return usageQuantity;
	}
	/**
    * Sets usageQuantity
    * @param usageQuantity
    */
	public void setUsageQuantity(Integer usageQuantity) {
		this.usageQuantity = usageQuantity;
	}
}
