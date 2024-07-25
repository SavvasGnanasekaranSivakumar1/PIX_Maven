package com.pearson.pix.dto.inventory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class Inventory implements Serializable {
	/**
	 * Identification of the Inventory
	 */
	private BigDecimal inventoryId;
	/**
	 * Version of the Inventory
	 */
	private BigDecimal inventoryVersion;
	/**
	 * Line Item No of the inventory
	 */
	private BigDecimal lineItemNo;
	/**
	 * Supplier Address Number
	 */
	private String san;
	/**
	 * 10 digit International standard book number assigned to Book
	 */
	private String isbn10;
	/**
	 * 13 digit International standard book number assigned to Book
	 */
	private String isbn13;
	/**
	 * Code of the product
	 */
	private String productCode;
	/**
	 * An element used to communicate a human readable description
	 * of the product in the language specified by the Language attribute
	 */
	private String productDescription;
	/**
	 * Type of the Product
	 */
	private String productType;
	/**
	 * Raw Material Number
	 */
	private String  rawMaterialNo;
	/**
	 * Stock in hand
	 */
	private Integer stockInHand;
	/**
	 * Unit Of Measure of the quantity
	 */
	private String UOM;
	/**
	 * Creation Date and Time of the Inventory
	 */
	private Date creationDateTime;
	/**
	 * Created By of the Inventory
	 */
	private String createdBy;
	/**
	 * Modification Date and Time of the Inventory
	 */
	private Date modDateTime;
	/**
	 * Modified By of the Inventory
	 */
	private String modifiedBy;
	
	/**
	 * Constructor to initialize Inventory
	 */
	public Inventory() {
		super();
		
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
	 * @return java.util.Date
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
	 * Gets inventoryId
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getInventoryId() {
		return inventoryId;
	}
	/**
    * Sets inventoryId
    * @param inventoryId
    */
	public void setInventoryId(BigDecimal inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * Gets inventoryVersion
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getInventoryVersion() {
		return inventoryVersion;
	}
	/**
    * Sets inventoryVersion
    * @param inventoryVersion
    */
	public void setInventoryVersion(BigDecimal inventoryVersion) {
		this.inventoryVersion = inventoryVersion;
	}
	
	/**
	 * Gets inventoryLineItemNo
	 * @return java.math.BigDecimal
	 */
	public BigDecimal getLineItemNo() {
		return lineItemNo;
	}
	/**
    * Sets inventoryVersion
    * @param inventoryVersion
    */
	public void setLineItemNo(BigDecimal lineItemNo) {
		this.lineItemNo = lineItemNo;
	}
	/**
	 * Gets isbn10
	 * @return java.lang.String
	 */
	public String getIsbn10() {
		return isbn10;
	}
	/**
    * Sets isbn10
    * @param isbn10
    */
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	/**
	 * Gets isbn13
	 * @return java.lang.String
	 */
	public String getIsbn13() {
		return isbn13;
	}
	/**
    * Sets isbn13
    * @param isbn13
    */
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	/**
	 * Gets modDateTime
	 * @return java.util.Date
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
	 * Gets productCode
	 * @return java.lang.String
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
    * Sets productCode
    * @param productCode
    */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * Gets productDescription
	 * @return java.lang.String
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
    * Sets productDescription
    * @param productDescription
    */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * Gets productType
	 * @return java.lang.String
	 */
	public String getProductType() {
		return productType;
	}
	/**
    * Sets productType
    * @param productType
    */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * Gets rawMaterialNo
	 * @return java.math.BigDecimal
	 */
	public String getRawMaterialNo() {
		return rawMaterialNo;
	}
	/**
    * Sets rawMaterialNo
    * @param rawMaterialNo
    */
	public void setRawMaterialNo(String rawMaterialNo) {
		this.rawMaterialNo = rawMaterialNo;
	}
	/**
	 * Gets san
	 * @return java.lang.String
	 */
	public String getSan() {
		return san;
	}
	/**
    * Sets san
    * @param san
    */
	public void setSan(String san) {
		this.san = san;
	}
	/**
	 * Gets stockInHand
	 * @return java.math.Integer
	 */
	public Integer getStockInHand() {
		return stockInHand;
	}
	/**
    * Sets stockInHand
    * @param stockInHand
    */
	public void setStockInHand(Integer stockInHand) {
		this.stockInHand = stockInHand;
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
	
}
