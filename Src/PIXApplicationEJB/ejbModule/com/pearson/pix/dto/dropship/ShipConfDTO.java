package com.pearson.pix.dto.dropship;

import java.util.Date;
import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class ShipConfDTO implements Serializable,Cloneable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String sessionId;
	private String rowId;
	private String poNumber;  
	private String bkNumber;  
	private String isbn10;
	private String trackingNumber;
	private String carrierLevel;
	private String shipTo;			//	destination;
	private Date shipDate;
	private java.math.BigDecimal grossWeight;		//shipmnetWeight;
	private java.math.BigDecimal totalUnits;
	private java.math.BigDecimal totalCartons;
	private String deskCopy;
	private String processingFlag;
	private String errorDesc;
	private String fileName;
	private Date creationDate;
	private Date modDate;
	private Integer pageNo;
	private FormFile file;
	private String truckShipmentFlag;
	
	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	/**
	 * @return the bkNumber
	 */
	public String getBkNumber() {
		return bkNumber;
	}
	/**
	 * @param bkNumber the bkNumber to set
	 */
	public void setBkNumber(String bkNumber) {
		this.bkNumber = bkNumber;
	}
	/**
	 * @return the isbn10
	 */
	public String getIsbn10() {
		return isbn10;
	}
	/**
	 * @param isbn10 the isbn10 to set
	 */
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	
	/**
	 * @return the carrierLevel
	 */
	public String getCarrierLevel() {
		return carrierLevel;
	}
	/**
	 * @param carrierLevel the carrierLevel to set
	 */
	public void setCarrierLevel(String carrierLevel) {
		this.carrierLevel = carrierLevel;
	}
	/**
	 * @return the shipTo
	 */
	public String getShipTo() {
		return shipTo;
	}
	/**
	 * @param shipTo the shipTo to set
	 */
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	/**
	 * @return the shipDate
	 */
	public Date getShipDate() {
		return shipDate;
	}
	/**
	 * @param shipDate the shipDate to set
	 */
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	/**
	 * @return the grossWeight
	 */
	public java.math.BigDecimal getGrossWeight() {
		return grossWeight;
	}
	/**
	 * @param grossWeight the grossWeight to set
	 */
	public void setGrossWeight(java.math.BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}
	/**
	 * @return the totalUnits
	 */
	public java.math.BigDecimal getTotalUnits() {
		return totalUnits;
	}
	/**
	 * @param totalUnits the totalUnits to set
	 */
	public void setTotalUnits(java.math.BigDecimal totalUnits) {
		this.totalUnits = totalUnits;
	}
	
	/**
	 * @return the deskCopy
	 */
	public String getDeskCopy() {
		return deskCopy;
	}
	/**
	 * @param deskCopy the deskCopy to set
	 */
	public void setDeskCopy(String deskCopy) {
		this.deskCopy = deskCopy;
	}
	/**
	 * @return the processingFlag
	 */
	public String getProcessingFlag() {
		return processingFlag;
	}
	/**
	 * @param processingFlag the processingFlag to set
	 */
	public void setProcessingFlag(String processingFlag) {
		this.processingFlag = processingFlag;
	}
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}
	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	/**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	 @Override
	   public ShipConfDTO clone() throws CloneNotSupportedException {
	      return (ShipConfDTO)super.clone();
	   }
	/**
	 * @return the trackingNumber
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}
	/**
	 * @param trackingNumber the trackingNumber to set
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	/**
	 * @return the totalCartons
	 */
	public java.math.BigDecimal getTotalCartons() {
		return totalCartons;
	}
	/**
	 * @param totalCartons the totalCartons to set
	 */
	public void setTotalCartons(java.math.BigDecimal totalCartons) {
		this.totalCartons = totalCartons;
	}
	/**
	 * @return the truckShipmentFlag
	 */
	public String getTruckShipmentFlag() {
		return truckShipmentFlag;
	}
	/**
	 * @param truckShipmentFlag the truckShipmentFlag to set
	 */
	public void setTruckShipmentFlag(String truckShipmentFlag) {
		this.truckShipmentFlag = truckShipmentFlag;
	}
	
}
