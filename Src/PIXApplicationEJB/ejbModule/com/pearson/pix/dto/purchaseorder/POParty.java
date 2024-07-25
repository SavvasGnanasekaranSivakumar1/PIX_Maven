package com.pearson.pix.dto.purchaseorder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.common.Status;

/**
 *  Contains the POParty Information Details
 */

public class POParty implements Serializable{

	private String address1;
    /**
	* The primary-level address of an organisation or business entity (for example,
street name, and number).
	*/
	private String address2;
 /**
	*The second-level address of an organisation or business entity.
	*/
	private String address3;
    /**
	* The third-level address of an organisation or business entity.
	*/
	private String address4;
     /**
	* The fourth-level address of an organisation or business entity.
	*/
	private String city;
    /**
	*Name of the city associated with the address elements.
	*/
	private String comments;
	private String comments1;
    /** 
	* Collection of Contacts for POParty
	*/
	private Collection contactCollection;
    /**
	* The detail of the country associated with the address elements. To ensure reliable matching of addresses, it is suggested to use the attribute ISOCountryCode as well.
	*/
	private Country countryDetail;
    /**
	*The name of the county that the address is in.
	*/
	private String createdBy;
    /**
	* Creation Date and Time 
	*/
	private Date creationDateTime;
    /**
	* Delivery Date and Time
	*/
	private String deliveryDate;
    /**
	* Finished Verdor Flag
	*/
	private String finishedVendorFlag;
    /**
	* Modification Date and Time
	*/
	private Date modDateTime;
    /**
    * Modified By user Id
    */
	private String modifiedBy;
     /**
	* The primary-level name of an organisation or business entity (for example, company name).
	*/
	private String name1;
    /**
	* The second-level name of an organisation or business entity (for example, department, division, and so on)
	*/
	private String name2;
     /**
	*  The third-level name of an organisation or business entity.
	*/
	private String name3;
     /**
	* Organizational Unit code
	*/
	private String orgUnitCode;
     /**
	*  Organizational Unit Name 
	*/
	private String orgUnitName;
     /**
	*  Party Abbrivation
	*/
	private String partyAbbr;
     /**
	*  Line Number of the party
	*/
	private BigDecimal partyLineNo;
     /**
	* Party type 
	*/
	private String partyType;
     /**
	* Purchase Order Identification
	*/
	private BigDecimal poId;
     /**
	* The postal code associated with the address elements.
	*/
	private String postalCode;
     /**
	* Purchase Order Version
	*/
	private BigDecimal poVersion;
    /**
    * Standard Address Number is the unique identifier of a specific party.
    */
	private String san;
     /**
	* The political designation or boundary of a specific area of land.
	*/
	private String state;
     /**
	* Universal Resource Locator. While typically a web address you could use this field to hold an email address.
	*/
	private String url;
	/**
     * Status Id of Pub Unit/Supplier
	 */
	private Integer statusId;
	/**
     * Vendor Plant Code or V3
	 */
	private String vendorPlantCode;
   /**
   * Constructor of the POParty
   */
public POParty() {
	super();
	this.contactCollection = new Vector();
}
  /**
    * Gets address1
    * @return java.lang.String
    */
public String getAddress1() {
	return this.address1;
}
  /**
    * Gets address2
    * @return java.lang.String
    */
public String getAddress2() {
	return this.address2;
}
  /**
    * Gets address3
    * @return java.lang.String
    */
public String getAddress3() {
	return this.address3;
}
  /**
    * Gets address4
    * @return java.lang.String
    */
public String getAddress4() {
	return this.address4;
}
  /**
    * Gets city
    * @return java.lang.String
    */
public String getCity() {
	return this.city;
}
  /**
    * Gets comments
    * @return java.lang.String
    */
public String getComments() {
	return this.comments;
}
  /**
    * Gets contactCollection
    * @return java.util.Collection
    */
public Collection getContactCollection() {
	return this.contactCollection;
}
  /**
    * Gets countryDetail
    * @return com.pearson.pix.dto.common.Country
    */
public Country getCountryDetail() {
	return this.countryDetail;
}
  /**
    * Gets createdBy
    * @return java.lang.String
    */
public String getCreatedBy() {
	return this.createdBy;
}
  /**
    * Gets creationDateTime
    * @return Date
    */
public Date getCreationDateTime() {
	return this.creationDateTime;
}
  /**
    * Gets deliveryDate
    * @return Date
    */
public String getDeliveryDate() {
	return this.deliveryDate;
}
  /**
    * Gets finishedVendorFlag
    * @return java.lang.String
    */
public String getFinishedVendorFlag() {
	return this.finishedVendorFlag;
}
  /**
    * Gets modDateTime
    * @return Date
    */
public Date getModDateTime() {
	return this.modDateTime;
}
  /**
    * Gets modifiedBy
    * @return java.lang.String
    */
public String getModifiedBy() {
	return this.modifiedBy;
}
  /**
    * Gets name1
    * @return java.lang.String
    */
public String getName1() {
	return this.name1;
}
  /**
    * Gets name2
    * @return java.lang.String
    */
public String getName2() {
	return this.name2;
}
  /**
    * Gets name3
    * @return java.lang.String
    */
public String getName3() {
	return this.name3;
}
  /**
    * Gets orgUnitCode
    * @return java.lang.String
    */
public String getOrgUnitCode() {
	return this.orgUnitCode;
}
  /**
    * Gets orgUnitName
    * @return java.lang.String
    */
public String getOrgUnitName() {
	return this.orgUnitName;
}
  /**
    * Gets partyAbbr
    * @return java.lang.String
    */
public String getPartyAbbr() {
	return this.partyAbbr;
}
  /**
    * Gets partyLineNo
    * @return java.math.BigDecimal
    */
public BigDecimal getPartyLineNo() {
	return this.partyLineNo;
}
  /**
    * Gets partyType
    * @return java.lang.String
    */
public String getPartyType() {
	return this.partyType;
}
  /**
    * Gets poId
    * @return java.math.BigDecimal
    */
public BigDecimal getPoId() {
	return this.poId;
}
  /**
    * Gets poVersion
    * @return java.math.BigDecimal
    */
public BigDecimal getPoVersion() {
	return this.poVersion;
}
  /**
    * Gets postalCode
    * @return java.lang.String
    */
public String getPostalCode() {
	return this.postalCode;
}
  /**
    * Gets san
    * @return java.lang.String
    */
public String getSan() {
	return this.san;
}
  /**
    * Gets state
    * @return java.lang.String
    */
public String getState() {
	return this.state;
}
  /**
    * Gets url
    * @return java.lang.String
    */
public String getUrl() {
	return this.url;
}
/**
 * Gets statusDetail
 * @return com.pearson.pix.dto.common.Status
 */
public Integer getStatusId() {
	return this.statusId;
}
   /**
    * Sets address1
    * @param address1
    */
public void setAddress1(String address1) {
	this.address1 = address1;
}
  
   /**
    * Sets address2
    * @param address2
    */
public void setAddress2(String address2) {
	this.address2 = address2;
}
  
   /**
    * Sets address3
    * @param address3
    */
public void setAddress3(String address3) {
	this.address3 = address3;
}
  
   /**
    * Sets address4
    * @param address4
    */
public void setAddress4(String address4) {
	this.address4 = address4;
}
  
   /**
    * Sets city
    * @param city
    */
public void setCity(String city) {
	this.city = city;
}
  
   /**
    * Sets comments
    * @param comments
    */
public void setComments(String comments) {
	this.comments = comments;
}
  
   /**
    * Sets contactCollection
    * @param contactCollection
    */
public void setContactCollection(Collection contactCollection) {
	this.contactCollection = contactCollection;
}
  
   /**
    * Sets countryDetail
    * @param countryDetail
    */
public void setCountryDetail(Country countryDetail) {
	this.countryDetail = countryDetail;
}
  
   /**
    * Sets createdBy
    * @param createdBy
    */
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
  
   /**
    * Sets creationDateTime
    * @param creationDateTime
    */
public void setCreationDateTime(Date creationDateTime) {
	this.creationDateTime = creationDateTime;
}
  
   /**
    * Sets deliveryDate
    * @param deliveryDate
    */
public void setDeliveryDate(String deliveryDate) {
	this.deliveryDate = deliveryDate;
}
  
   /**
    * Sets finishedVendorFlag
    * @param finishedVendorFlag
    */
public void setFinishedVendorFlag(String finishedVendorFlag) {
	this.finishedVendorFlag = finishedVendorFlag;
}
  
   /**
    * Sets modDateTime
    * @param modDateTime
    */
public void setModDateTime(Date modDateTime) {
	this.modDateTime = modDateTime;
}
  
   /**
    * Sets modifiedBy
    * @param modifiedBy
    */
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
  
   /**
    * Sets name1
    * @param name1
    */
public void setName1(String name1) {
	this.name1 = name1;
}
  
   /**
    * Sets name2
    * @param name2
    */
public void setName2(String name2) {
	this.name2 = name2;
}
  
   /**
    * Sets name3
    * @param name3
    */
public void setName3(String name3) {
	this.name3 = name3;
}
  
   /**
    * Sets orgUnitCode
    * @param orgUnitCode
    */
public void setOrgUnitCode(String orgUnitCode) {
	this.orgUnitCode = orgUnitCode;
}
  
   /**
    * Sets orgUnitName
    * @param orgUnitName
    */
public void setOrgUnitName(String orgUnitName) {
	this.orgUnitName = orgUnitName;
}
  
   /**
    * Sets partyAbbr
    * @param partyAbbr
    */
public void setPartyAbbr(String partyAbbr) {
	this.partyAbbr = partyAbbr;
}
  
   /**
    * Sets partyLineNo
    * @param partyLineNo
    */
public void setPartyLineNo(BigDecimal partyLineNo) {
	this.partyLineNo = partyLineNo;
}
  
   /**
    * Sets partyType
    * @param partyType
    */
public void setPartyType(String partyType) {
	this.partyType = partyType;
}
  
   /**
    * Sets poId
    * @param poId
    */
public void setPoId(BigDecimal poId) {
	this.poId = poId;
}
  
   /**
    * Sets poVersion
    * @param poVersion
    */
public void setPoVersion(BigDecimal poVersion) {
	this.poVersion = poVersion;
}
  
   /**
    * Sets postalCode
    * @param postalCode
    */
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}
  
   /**
    * Sets san
    * @param san
    */
public void setSan(String san) {
	this.san = san;
}
  
   /**
    * Sets state
    * @param state
    */
public void setState(String state) {
	this.state = state;
}
  
   /**
    * Sets url
    * @param url
    */
public void setUrl(String url) {
	this.url = url;
}

/**
 * Sets statusId
 * @param statusId
 */
public void setStatusId(Integer statusId) {
	this.statusId = statusId;
}
public String getComments1() {
	return comments1;
}
public void setComments1(String comments1) {
	this.comments1 = comments1;
}
public String getVendorPlantCode() {
	return vendorPlantCode;
}
public void setVendorPlantCode(String vendorPlantCode) {
	this.vendorPlantCode = vendorPlantCode;
}
}
