package com.pearson.pix.dto.usage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import com.pearson.pix.dto.orderstatus.OrderStatusLine;
import com.pearson.pix.dto.purchaseorder.POHeader;

public class Usage extends POHeader implements Serializable {
	/**
	 * Unique Identification of the Usage
	 */
	private BigDecimal usageId;
	/**
	 * Unique identifier assigned to each Usage message as agreed by Trading Partners.
	 */
	private String usageNo;
	/**
	 * Number of the job
	 */
	private String jobNo;
	/**
	 * Status of the Usage 
	 */
	private String status;
	/**
	 * Collection of the Usage Line Items
	 */
	private Collection usageLineCollection;
	/**
	 * Constructor to initialize Usage
	 */
	public Usage() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gets jobNo
	 * @return java.lang.String
	 */
	public String getJobNo() {
		return jobNo;
	}
	/**
    * Sets jobNo
    * @param jobNo
    */ 
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
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
	 * Gets usageLineCollection
	 * @return java.lang.String
	 */
	public Collection getUsageLineCollection() {
		return usageLineCollection;
	}
	/**
    * Sets usageLineCollection
    * @param usageLineCollection
    */
	public void setUsageLineCollection(Collection usageLineCollection) {
		this.usageLineCollection = usageLineCollection;
	}
	
	/**
	* Gets OrderStatusLine for OderStatus
	* @return com.pearson.pix.dto.orderstatus.OrderStatus
	*/
	public UsageLine getUsageLineItemCollectionIdx(int index) {
		return (UsageLine)((Vector)this.usageLineCollection).get(index);
	}
	
	/**
    * Sets OrderStatusLine with index
    * @param int
    * @param com.pearson.pix.dto.orderstatus.OrderStatusLine
    */
	public void setUsageLineItemCollectionIdx(int index, UsageLine usageLine) {
		((Vector)this.usageLineCollection).set(index, usageLine);			
	}
	
	/**
	 * Gets usageNo
	 * @return java.lang.String
	 */
	public String getUsageNo() {
		return usageNo;
	}
	/**
    * Sets usageNo
    * @param usageNo
    */
	public void setUsageNo(String usageNo) {
		this.usageNo = usageNo;
	}
}
