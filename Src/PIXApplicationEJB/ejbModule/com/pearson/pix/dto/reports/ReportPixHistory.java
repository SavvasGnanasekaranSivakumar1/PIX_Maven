package com.pearson.pix.dto.reports;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReportPixHistory implements Serializable {
	
	private String transactionType;
	private Date transactionDate;
	/**
	 *International standard book number assigned to Book
	 */
	private String isbn;
	
	/**
	 * printNo in Report
	 */
	private BigDecimal printNo;
	   
	/**
	* Purchase Order Number
	*/
	private String poNo;
		
	private Date bbd;
	private Integer qty;
	
	/**
	 * Vendor of the Transaction Item
	 */
	private String vendor;
	
	private Date vendorDate;
	private Date deliveryDate;
	private Date pressDate;
	private Date arrivalDate;
	private String comments;
	private BigDecimal transactionId;
	
	private BigDecimal id;
	/**
	 * ID of the Item
	 */
	private BigDecimal poid;
	
	
	/**
	 * Constructor to initialize Report
	 */
	public ReportPixHistory() {
		// TODO Auto-generated constructor stub
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public BigDecimal getPrintNo() {
		return printNo;
	}


	public void setPrintNo(BigDecimal printNo) {
		this.printNo = printNo;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getBbd() {
		return bbd;
	}

	public void setBbd(Date bbd) {
		this.bbd = bbd;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public Date getVendorDate() {
		return vendorDate;
	}


	public void setVendorDate(Date vendorDate) {
		this.vendorDate = vendorDate;
	}


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public Date getPressDate() {
		return pressDate;
	}


	public void setPressDate(Date pressDate) {
		this.pressDate = pressDate;
	}


	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public BigDecimal getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(BigDecimal transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public BigDecimal getPoid() {
		return poid;
	}


	public void setPoid(BigDecimal poid) {
		this.poid = poid;
	}



}



