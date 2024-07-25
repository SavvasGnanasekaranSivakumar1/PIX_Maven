package com.pearson.pix.dto.reports;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Report implements Serializable {
	/**
	 * Purchase Order Number
	 */
	private String PONo;
	/**
	 * Name of the Transaction Item
	 */
	private String itemName;
	/**
	 * Number of the Item
	 */
	private String itemNo;
	/**
	 * ID of the Item
	 */
	private BigDecimal id;
	/**
	 * ID of the Item
	 */
	private BigDecimal poid;
	/**
	 * Version of the Transaction Item
	 */
	private BigDecimal version;
	/**
	 * Posted Date of the Transaction Item
	 */
	private Date postedDate;
	/**
	 * Posted By of the Transaction Item
	 */
	private String postedBy;
	/**
	 * Vendor of the Transaction Item
	 */
	private String vendor;
	
	/**
	    *International standard book number assigned to Book
	    */
	private String isbn;
	
	/**
	    * printNo in Report
	    */
	   private String printNo;
	/**
	 * Constructor to initialize Report
	 */
	public Report() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Gets itemName
	 * @return java.lang.String
	 */	
	public String getItemName() {
		return itemName;
	}
	/**
    * Sets itemName
    * @param itemName
    */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Gets id
	 * @return java.math.BigDecimal
	 */	
	public BigDecimal getId() {
		return id;
	}
	/**
    * Sets id
    * @param id
    */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	/**
	 * Gets poid
	 * @return java.math.BigDecimal
	 */	
	public BigDecimal getPoid() {
		return poid;
	}
	/**
    * Sets poid
    * @param poid
    */
	public void setPoid(BigDecimal poid) {
		this.poid =poid;
	}
	
	/**
	 * Gets itemNo
	 * @return java.math.BigDecimal
	 */
	public String getItemNo() {
		return itemNo;
	}
	/**
    * Sets itemNo
    * @param itemNo
    */
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	/**
	 * Gets PONo
	 * @return java.lang.String
	 */
	public String getPONo() {
		return PONo;
	}
	/**
    * Sets PONo
    * @param PONo
    */
	public void setPONo(String no) {
		PONo = no;
	}
	/**
	 * Gets postedBy
	 * @return java.lang.String
	 */
	public String getPostedBy() {
		return postedBy;
	}
	/**
    * Sets postedBy
    * @param postedBy
    */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	/**
	 * Gets postedDate
	 * @return java.util.Date
	 */
	public Date getPostedDate() {
		return postedDate;
	}
	/**
    * Sets postedDate
    * @param postedDate
    */
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	/**
	 * Gets vendor
	 * @return java.lang.String
	 */
	public String getVendor() {
		return vendor;
	}
	/**
    * Sets vendor
    * @param vendor
    */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	/**
	 * Gets version
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getVersion() {
		return version;
	}
	/**
    * Sets version
    * @param version
    */
	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	
	/**
	    * Gets isbn
	    * @return java.lang.String
	    */
	   public String getIsbn() 
	   {
			return this.isbn;    
	   }
	   
	   /**
	    * Sets isbn
	    * @param isbn
	    */
	   public void setIsbn(String isbn) 
	   {
			this.isbn = isbn;    
	   }
	   
	   /**
	    * Gets printNo
	    * @return java.lang.String
	    */
	   public String getPrintNo() 
	   {
			return this.printNo;    
	   }
	   
	   /**
	    * Sets printNo
	    * @param printNo
	    */
	   public void setPrintNo(String printNo) 
	   {
			this.printNo = printNo;    
	   }
	   
}
