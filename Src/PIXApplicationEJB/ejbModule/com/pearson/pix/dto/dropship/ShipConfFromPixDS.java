package com.pearson.pix.dto.dropship;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShipConfFromPixDS implements Serializable{
	
		private Integer seqId;
		private Integer pcsTranId;
		private String pearsonPO;
		private String docCntrl;
		private String  bkNumber;
		private String isbn;
		private String trackingNumber;
		private Integer unitShipped;
		private Integer numOfCartons;
		private BigDecimal totalWeight;
		private String carrierLevel;
		private String actualCarrier;
		private String shipTo;
		private java.sql.Date shipDate;
		private String status;
		private String sendToCES;
		private java.sql.Date dateEntered;
		
		/**
		 * @return the seqId
		 */
		public Integer getSeqId() {
			return seqId;
		}
		/**
		 * @param seqId the seqId to set
		 */
		public void setSeqId(Integer seqId) {
			this.seqId = seqId;
		}
		/**
		 * @return the pcsTranId
		 */
		public Integer getPcsTranId() {
			return pcsTranId;
		}
		/**
		 * @param pcsTranId the pcsTranId to set
		 */
		public void setPcsTranId(Integer pcsTranId) {
			this.pcsTranId = pcsTranId;
		}
		/**
		 * @return the pearsonPO
		 */
		public String getPearsonPO() {
			return pearsonPO;
		}
		/**
		 * @param pearsonPO the pearsonPO to set
		 */
		public void setPearsonPO(String pearsonPO) {
			this.pearsonPO = pearsonPO;
		}
		/**
		 * @return the docCntrl
		 */
		public String getDocCntrl() {
			return docCntrl;
		}
		/**
		 * @param docCntrl the docCntrl to set
		 */
		public void setDocCntrl(String docCntrl) {
			this.docCntrl = docCntrl;
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
		 * @return the isbn
		 */
		public String getIsbn() {
			return isbn;
		}
		/**
		 * @param isbn the isbn to set
		 */
		public void setIsbn(String isbn) {
			this.isbn = isbn;
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
		 * @return the unitShipped
		 */
		public Integer getUnitShipped() {
			return unitShipped;
		}
		/**
		 * @param unitShipped the unitShipped to set
		 */
		public void setUnitShipped(Integer unitShipped) {
			this.unitShipped = unitShipped;
		}
		/**
		 * @return the numOfCartons
		 */
		public Integer getNumOfCartons() {
			return numOfCartons;
		}
		/**
		 * @param numOfCartons the numOfCartons to set
		 */
		public void setNumOfCartons(Integer numOfCartons) {
			this.numOfCartons = numOfCartons;
		}
		/**
		 * @return the totalWeight
		 */
		public BigDecimal getTotalWeight() {
			return totalWeight;
		}
		/**
		 * @param totalWeight the totalWeight to set
		 */
		public void setTotalWeight(BigDecimal totalWeight) {
			this.totalWeight = totalWeight;
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
		 * @return the actualCarrier
		 */
		public String getActualCarrier() {
			return actualCarrier;
		}
		/**
		 * @param actualCarrier the actualCarrier to set
		 */
		public void setActualCarrier(String actualCarrier) {
			this.actualCarrier = actualCarrier;
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
		public java.sql.Date getShipDate() {
			return shipDate;
		}
		/**
		 * @param shipDate the shipDate to set
		 */
		public void setShipDate(java.sql.Date shipDate) {
			this.shipDate = shipDate;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the sendToCES
		 */
		public String getSendToCES() {
			return sendToCES;
		}
		/**
		 * @param sendToCES the sendToCES to set
		 */
		public void setSendToCES(String sendToCES) {
			this.sendToCES = sendToCES;
		}
		/**
		 * @return the dateEntered
		 */
		public java.sql.Date getDateEntered() {
			return dateEntered;
		}
		/**
		 * @param dateEntered the dateEntered to set
		 */
		public void setDateEntered(java.sql.Date dateEntered) {
			this.dateEntered = dateEntered;
		}
		
}
