package com.pearson.pix.dto.orderstatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
* Contains StatusTimeline Information Detail
*/
public class StatusTimeline implements Serializable {
	/**
	* Identification of the Time line
	*/
	private String timelineId;
    /**
	* Description of the Time line
	*/
	private String timelineDescription;
    /**
	* Quantity of an order
	*/
	private BigDecimal displayOrder;
    /**
    * Active Flag of user
    */
	private String activeFlag;
   /**
    * Created By user Id
    */
	private String createdBy;
    /**
    * modified By userId
    */
	private String modifiedBy;
    /**
	* Creation Date and time
	*/
	private Date creationDateTime;
    /**
	* Modification Date and time
	*/
	private Date modDateTime;


 /**
    * Constructor of StatusTimeline
    */
   public StatusTimeline() 
   {
	super();    
   }
     /**
    * Gets activeFlag
    * @return java.lang.String
    */
	public String getActiveFlag() {
		return activeFlag;
	}
	 /**
    * Sets activeFlag
    * @param activeFlag
    */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	 /**
    * Gets createdBy
    * @return java.lang.String
    */
	public String getCreatedBy() {
		return createdBy;
	}
	 /**
    * Sets createdBy
    * @param createdBy
    */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	 /**
    * Gets creationDateTime
    * @return Date
    */
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	 /**
    * Sets creationDateTime
    * @param creationDateTime
    */
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	 /**
    * Gets displayOrder
    * @return java.math.BigDecimal
    */
	public BigDecimal getDisplayOrder() {
		return displayOrder;
	}
	 /**
    * Sets displayOrder
    * @param displayOrder
    */
	public void setDisplayOrder(BigDecimal displayOrder) {
		this.displayOrder = displayOrder;
	}
	 /**
    * Gets modDateTime
    * @return Date
    */
	public Date getModDateTime() {
		return modDateTime;
	}
	 /**
    * Sets modDateTime
    * @param modDateTime
    */
	public void setModDateTime(Date modDateTime) {
		this.modDateTime = modDateTime;
	}
	 /**
    * Gets modifiedBy
    * @return java.lang.String
    */
	public String getModifiedBy() {
		return modifiedBy;
	}
	 /**
    * Sets modifiedBy
    * @param modifiedBy
    */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	 /**
    * Gets timelineDescription
    * @return java.lang.String
    */
	public String getTimelineDescription() {
		return timelineDescription;
	}
	 /**
    * Sets timelineDescription
    * @param timelineDescription
    */
	public void setTimelineDescription(String timelineDescription) {
		this.timelineDescription = timelineDescription;
	}
	 /**
    * Gets timelineId
    * @return java.lang.String
    */
	public String getTimelineId() {
		return timelineId;
	}
	/**
    * Sets timelineId
    * @param timelineId
    */
	public void setTimelineId(String timelineId) {
		this.timelineId = timelineId;
	}
}
