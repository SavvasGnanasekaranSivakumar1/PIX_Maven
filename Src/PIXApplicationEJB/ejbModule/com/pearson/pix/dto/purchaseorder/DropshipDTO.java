package com.pearson.pix.dto.purchaseorder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DropshipDTO implements Serializable {
	private String  bknumber             ;                                                                 
	private String  trackingNo       ;
	private String  carrierLevel         ;                                                           
	private String  shippedTo            ;                                                          
	private Date  shipDate             ;  
	private BigDecimal  weight                ; 
	private BigDecimal  totalCartons ;                                              
	private BigDecimal  unitPerCarton  ;
	private BigDecimal totalQty;
	private String shipTo;
	private String billTo;
	private String school;
	private Integer userId;
	
	
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
	public String getBknumber() {
		return bknumber;
	}
	public void setBknumber(String bknumber) {
		this.bknumber = bknumber;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getCarrierLevel() {
		return carrierLevel;
	}
	public void setCarrierLevel(String carrierLevel) {
		this.carrierLevel = carrierLevel;
	}
	public String getShippedTo() {
		return shippedTo;
	}
	public void setShippedTo(String shippedTo) {
		this.shippedTo = shippedTo;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getTotalCartons() {
		return totalCartons;
	}
	public void setTotalCartons(BigDecimal totalCartons) {
		this.totalCartons = totalCartons;
	}
	public BigDecimal getUnitPerCarton() {
		return unitPerCarton;
	}
	public void setUnitPerCarton(BigDecimal unitPerCarton) {
		this.unitPerCarton = unitPerCarton;
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
	 * @return the billTo
	 */
	public String getBillTo() {
		return billTo;
	}
	/**
	 * @param billTo the billTo to set
	 */
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	/**
	 * @return the totalQty
	 */
	public BigDecimal getTotalQty() {
		return totalQty;
	}
	/**
	 * @param totalQty the totalQty to set
	 */
	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	
	
	}
