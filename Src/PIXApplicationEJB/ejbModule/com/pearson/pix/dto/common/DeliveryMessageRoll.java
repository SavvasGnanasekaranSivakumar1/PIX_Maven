package com.pearson.pix.dto.common;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryMessageRoll {
	
	private BigDecimal message_ID;
	private BigDecimal messageLineNumber;
	private String rollNumber;
	private BigDecimal machineID;
	private String tambourID;
	private BigDecimal setPosition;
	private BigDecimal quantity;
	private BigDecimal uomID;
	private String createdBy;
	private String modifiedBy;
	private Date modDateTime;
	private Date creationDateTime;
	
	public BigDecimal getMessage_ID() {
		return message_ID;
	}
	public void setMessage_ID(BigDecimal message_ID) {
		this.message_ID = message_ID;
	}
	public BigDecimal getMessageLineNumber() {
		return messageLineNumber;
	}
	public void setMessageLineNumber(BigDecimal messageLineNumber) {
		this.messageLineNumber = messageLineNumber;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public BigDecimal getMachineID() {
		return machineID;
	}
	public void setMachineID(BigDecimal machineID) {
		this.machineID = machineID;
	}
	public String getTambourID() {
		return tambourID;
	}
	public void setTambourID(String tambourID) {
		this.tambourID = tambourID;
	}
	public BigDecimal getSetPosition() {
		return setPosition;
	}
	public void setSetPosition(BigDecimal setPosition) {
		this.setPosition = setPosition;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUomID() {
		return uomID;
	}
	public void setUomID(BigDecimal uomID) {
		this.uomID = uomID;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModDateTime() {
		return modDateTime;
	}
	public void setModDateTime(Date modDateTime) {
		this.modDateTime = modDateTime;
	}
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	
	
}
