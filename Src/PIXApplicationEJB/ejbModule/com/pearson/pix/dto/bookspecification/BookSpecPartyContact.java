package com.pearson.pix.dto.bookspecification;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains Book Specification Party Contact Details
 */
public class BookSpecPartyContact implements Serializable 
{
   
   /**
    * First Name of Contact
    */
   private String contactFirstName;
   
   /**
    * Last Name of Contact
    */
   private String contactLastName;
   
   /**
    * Contact No
    */
   private BigDecimal contactNo;
   
   /**
    * Identifies the role of the contact within the party.
    */
   private String contactType;
   
   /**
    * The primary business email address of the individual associated with the 
    * ContactName.
    */
   private String email;
   
   /**
    * The primary business fax number of the individual associated with the 
    * ContactName.
    */
   private String fax;
   
   /**
    * The mobile phone number of the individual associated with the ContactName.
    */
   private String mobile;
   
   /**
    * Order Number of the PartyContact
    */
   private BigDecimal orderNo;
   
   /**
    * The business phone number of the individual associated with the ContactName.
    */
   private String phone;
   
   /**
    * Supplier Address Number
    */
   private String san;
   
   /**
    * Specification Id
    */
   private BigDecimal specId;
   
   /**
    * Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * Constructor to initialize BoodSpecPartyContact
    */
   public BookSpecPartyContact() 
   {
	super();    
   }
   
   /**
    * Gets contactFirstName
    * @return java.lang.String
    */
   public String getContactFirstName() 
   {
	return this.contactFirstName;    
   }
   
   /**
    * Gets contactLastName
    * @return java.lang.String
    */
   public String getContactLastName() 
   {
	return this.contactLastName;    
   }
   
   /**
    * Gets contactNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getContactNo() 
   {
	return this.contactNo;    
   }
   
   /**
    * Gets contactType
    * @return java.lang.String
    */
   public String getContactType() 
   {
	return this.contactType;    
   }
   
   /**
    * Gets email
    * @return java.lang.String
    */
   public String getEmail() 
   {
	return this.email;    
   }
   
   /**
    * Gets fax
    * @return java.lang.String
    */
   public String getFax() 
   {
	return this.fax;    
   }
   
   /**
    * Gets mobile
    * @return java.lang.String
    */
   public String getMobile() 
   {
	return this.mobile;    
   }
   
   /**
    * Gets orderNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getOrderNo() 
   {
	return this.orderNo;    
   }
   
   /**
    * Gets phone
    * @return java.lang.String
    */
   public String getPhone() 
   {
	return this.phone;    
   }
   
   /**
    * Gets san
    * @return java.lang.String
    */
   public String getSan() 
   {
	return this.san;    
   }
   
   /**
    * Gets specId
    * @return java.math.BigDecimal
    */
   public BigDecimal getSpecId() 
   {
	return this.specId;    
   }
   
   /**
    * Gets specVersion
    * @return java.math.BigDecimal
    */
   public BigDecimal getSpecVersion() 
   {
	return this.specVersion;    
   }
   
   /**
    * Sets contactFirstName
    * @param contactFirstName
    */
   public void setContactFirstName(String contactFirstName) 
   {
	this.contactFirstName = contactFirstName;    
   }
   
   /**
    * Sets contactLastName
    * @param contactLastName
    */
   public void setContactLastName(String contactLastName) 
   {
	this.contactLastName = contactLastName;    
   }
   
   /**
    * Sets contactNo
    * @param contactNo
    */
   public void setContactNo(BigDecimal contactNo) 
   {
	this.contactNo = contactNo;    
   }
   
   /**
    * Sets contactType
    * @param contactType
    */
   public void setContactType(String contactType) 
   {
	this.contactType = contactType;    
   }
   
   /**
    * Sets email
    * @param email
    */
   public void setEmail(String email) 
   {
	this.email = email;    
   }
   
   /**
    * Sets fax
    * @param fax
    */
   public void setFax(String fax) 
   {
	this.fax = fax;    
   }
   
   /**
    * Sets mobile
    * @param mobile
    */
   public void setMobile(String mobile) 
   {
	this.mobile = mobile;    
   }
   
   /**
    * Sets orderNo
    * @param orderNo
    */
   public void setOrderNo(BigDecimal orderNo) 
   {
	this.orderNo = orderNo;    
   }
   
   /**
    * Sets phone
    * @param phone
    */
   public void setPhone(String phone) 
   {
	this.phone = phone;    
   }
   
   /**
    * Sets san
    * @param san
    */
   public void setSan(String san) 
   {
	this.san = san;    
   }
   
   /**
    * Sets specId
    * @param specId
    */
   public void setSpecId(BigDecimal specId) 
   {
	this.specId = specId;    
   }
   
   /**
    * Sets specVersion
    * @param specVersion
    */
   public void setSpecVersion(BigDecimal specVersion) 
   {
	this.specVersion = specVersion;    
   }
}
