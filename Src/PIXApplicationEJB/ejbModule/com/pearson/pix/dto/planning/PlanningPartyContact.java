package com.pearson.pix.dto.planning;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains all PlanningPartyContact information
 */
public class PlanningPartyContact implements Serializable 
{
  
/**
    * First Name of the Contact
    */
   private String contactFirstName;
   
   /**
    * Last Name of the Contact
    */
   private String contactLastName;
   
   /**
    * contact no of PlanningPartyContact
    */
   private BigDecimal contactNo;
   
   /**
    * contactType
    */
   private String contactType;
   
   /**
    * Email address of PlanningPartyContact
    */
   private String email;
   
   /**
    * Fax Number of Planning
    */
   private String fax;
   
   /**
    * Mobile Number of the PlanningParty
    */
   private String mobile;
   
   /**
    * orderNo of the planning
    */
   private BigDecimal orderNo;
   
   /**
    * Phone Number of PlanningParty
    */
   private String phone;
   
   /**
    * planId of the PlanningParty
    */
   private BigDecimal planId;
   
   /**
    * planVersion of the PlanningParty
    */
   private BigDecimal planVersion;
   
   /**
    * Standard Address Number is the unique identifier of a specific PlanningParty.
    */
   private String san;
   
   /**
    * Constructor of the PlanningPartyContact
    */
   public PlanningPartyContact() 
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
    * @return java.Math.BigDecimal
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
    * @return java.Math.BigDecimal
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
    * Gets planId
    * @return java.Math.BigDecimal
    */
   public BigDecimal getPlanId() 
   {
	return this.planId;    
   }
   
   /**
    * Gets planVersion
    * @return java.Math.BigDecimal
    */
   public BigDecimal getPlanVersion() 
   {
	return this.planVersion;    
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
    * Sets planId
    * @param planId
    */
   public void setPlanId(BigDecimal planId) 
   {
	this.planId = planId;    
   }
   
   /**
    * Sets planVersion
    * @param planVersion
    */
   public void setPlanVersion(BigDecimal planVersion) 
   {
	this.planVersion = planVersion;    
   }
   
   /**
    * Sets san
    * @param san
    */
   public void setSan(String san) 
   {
	this.san = san;    
   }
}
