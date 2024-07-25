package com.pearson.pix.dto.purchaseorder;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 *  Contains the POPartyContact Details
 */

public class POPartyContact implements Serializable{

    /**
    * First Name of the Contact
    */
	private String contactFirstName;
    /**
    * Last Name of the Contact
    */
	private String contactLastName;
 /**
   * Contact No of the COntact
   */
	private BigDecimal contactNo;
 /**
   * Type of the Contact
   */
	private String contactType;
 /**
    * Email address of Party
    */
	private String email;
 /**
   *  Fax Number of Party
   */
	private String fax;
 /**
   * Mobile Number of the Party
   */
	private String mobile;
 /**
   * Order No of the Party
   */
	private BigDecimal orderNo;
 /**
   * Line Number of the Party
   */
	private BigDecimal partyLineNo;
 /**
   * Phone Number of Party
   */
	private String phone;
 /**
   * purchase Order Identification
   */
	private BigDecimal poId;
 /**
   * Purchase Order Version number
   */
	private BigDecimal poVersion;

 /**
  * Constructor of POPartyContact
  */
public POPartyContact() {
	super();
}
 /**
    * Gets contactFirstName
    * @return java.lang.String
    */
public String getContactFirstName() {
	return this.contactFirstName;
}
 /**
    * Gets contactLastName
    * @return java.lang.String
    */
public String getContactLastName() {
	return this.contactLastName;
}
 /**
    * Gets contactNo
    * @return java.math.BigDecimal
    */
public BigDecimal getContactNo() {
	return this.contactNo;
}
 /**
    * Gets contactType
    * @return java.lang.String
    */
public String getContactType() {
	return this.contactType;
}
 /**
    * Gets email
    * @return java.lang.String
    */
public String getEmail() {
	return this.email;
}
 /**
    * Gets fax
    * @return java.lang.String
    */
public String getFax() {
	return this.fax;
}
 /**
    * Gets mobile
    * @return java.lang.String
    */
public String getMobile() {
	return this.mobile;
}
 /**
    * Gets orderNo
    * @return java.math.BigDecimal
    */
public BigDecimal getOrderNo() {
	return this.orderNo;
}
 /**
    * Gets partyLineNo
    * @return java.math.BigDecimal
    */
public BigDecimal getPartyLineNo() {
	return this.partyLineNo;
}
 /**
    * Gets phone
    * @return java.lang.String
    */
public String getPhone() {
	return this.phone;
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
    * Sets contactFirstName
    * @param contactFirstName
    */
public void setContactFirstName(String contactFirstName) {
	this.contactFirstName = contactFirstName;
}
 /**
    * Sets contactLastName
    * @param contactLastName
    */
public void setContactLastName(String contactLastName) {
	this.contactLastName = contactLastName;
}
 /**
    * Sets contactNo
    * @param contactNo
    */
public void setContactNo(BigDecimal contactNo) {
	this.contactNo = contactNo;
}
 /**
    * Sets contactType
    * @param contactType
    */
public void setContactType(String contactType) {
	this.contactType = contactType;
}
 /**
    * Sets email
    * @param email
    */
public void setEmail(String email) {
	this.email = email;
}
 /**
    * Sets fax
    * @param fax
    */
public void setFax(String fax) {
	this.fax = fax;
}
 /**
    * Sets mobile
    * @param mobile
    */
public void setMobile(String mobile) {
	this.mobile = mobile;
}
 /**
    * Sets orderNo
    * @param orderNo
    */
public void setOrderNo(BigDecimal orderNo) {
	this.orderNo = orderNo;
}
 /**
    * Sets partyLineNo
    * @param partyLineNo
    */
public void setPartyLineNo(BigDecimal partyLineNo) {
	this.partyLineNo = partyLineNo;
}
 /**
    * Sets phone
    * @param phone
    */
public void setPhone(String phone) {
	this.phone = phone;
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

}
