package com.pearson.pix.dto.orderstatus;

import java.io.Serializable;

import com.pearson.pix.dto.purchaseorder.POLine;

/**
 *  Contains OrderStatusLine Detials Information
 *  @author Dandu Thirupathi
 */

public class OrderStatusLine extends POLine implements Serializable{
 
    /**
	* Status Identification of the Order
	*/
	private Integer statusId;
	/**
	* Status Number of the Order
	*/
	private String statusNo;
	/**
	* Estimated Date of the Order, when it will complete
	*/
	private String estimatedDate;
	/**
	* status time line's of an order
	*/
	private Integer timelineId;
	/**
	* status time line's of an order
	*/
	private String timelineDescription;
	/**
	* Detailed Status of an Order
	*/
	private String statusDescription;
	/**
	* timeline  for an Order
	*/
	private String timelineDate;
	/**
	* comments
	*/
	private String comments;

    /**
    * Constructor of OrderStatusLine
    */
   public OrderStatusLine() 
   {
	super();    
   }
   
    /**
    * Gets timelineId
    * @return java.lang.Integer
    */
   public Integer getTimelineId()
   {
	  return timelineId;
   }
   
    /**
    * Sets timelineId
    * @param java.lang.Integer
    */
   public void setTimelineId(Integer timelineId)
   {
	   
	   this.timelineId = timelineId;
   }
   
   /**
    * Gets timelineDescription
    * @return java.lang.String
    */
   public String getTimelineDescription()
   {
	  return this.timelineDescription;
   }
   
   /**
    * Sets timelineDescription
    * @param java.lang.String
   */
   public void setTimelineDescription(String timelineDescription)
   {
	   
	   this.timelineDescription = timelineDescription;
   }
   
   /**
   * Gets comments
   * @return java.lang.String
   */
	public String getComments() {
		return this.comments;
	}
	
	/**
    * Sets comments
    * @param comments
    */
	public void setComments(String comments) {
		this.comments = comments;
	}
	   
	/**
   * Gets estimatedDate
   * @return java.lang.String
   */
	public String getEstimatedDate() {
		return estimatedDate;
	}
	/**
    * Sets estimatedDate
    * @param java.lang.String
    */
	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}
	   
	/**
   * Gets orderStatusId
   * @return java.lang.Integer
   */
	public Integer getStatusId() {
		return this.statusId;
	}

	/**
    * Sets orderStatusId
    * @param java.lang.Integer
    */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	   
	/**
   * Gets status
   * @return java.lang.String
   */
	public String getStatusDescription() {
		return this.statusDescription;
	}
   /**
    * Sets status
    * @param java.lang.String
    */ 
  	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	   
  /**
   * Gets statusNo
   * @return java.lang.String
   */
	public String getStatusNo() {
		return this.statusNo;
	}
	
   /**
    * Sets statusNo
    * @param java.lang.String
    */
	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}
	   
  /**
   * Gets timelineDate
   * @return java.lang.String
   */
	public String getTimelineDate() {
		return timelineDate;
	}
	
   /**
    * Sets timelineDate
    * @param java.lang.String
    */
	public void setTimelineDate(String timelineDate) {
		this.timelineDate = timelineDate;
	}
		
 }
