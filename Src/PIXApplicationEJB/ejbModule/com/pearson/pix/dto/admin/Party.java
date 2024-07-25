package com.pearson.pix.dto.admin;

import com.pearson.pix.dto.common.Country;
import java.util.Date;
import java.io.Serializable;

/**
 * Contains Party Detials information
 */
public class Party implements Serializable 
{
   
   /**
    * The active flag of an organisation or business entity.
    */
   private String activeFlag;
   
   /**
    * The primary-level address of an organisation or business entity (for 
    * example,street name, and number).
    */
   private String address1;
   
   /**
    * The second-level address of an organisation or business entity.
    */
   private String address2;
   
   /**
    * The third-level address of an organisation or business entity.
    */
   private String address3;
   
   /**
    * The fourth-level address of an organisation or business entity.
    */
   private String address4;
   
   /**
    * Name of the city associated with the address elements.
    */
   private String city;
   
   /**
    * First Name of the User
    */
   private String contactFirstName;
   
   /**
    * Last Name of the User
    */
   private String contactLastName;
   
   /**
    * Created By user Id
    */
   private Integer createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Email address of Party
    */
   private String email;
   
   /**
    * First Fax Number of Party
    */
   private String fax1;
   
   /**
    * Second Fax Number of Party
    */
   private String fax2;
   
   /**
    * Mobile Number of the Party
    */
   private String mobile;
   
   /**
    * Date and Time of modification
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private Integer modifiedBy;
   
   /**
    * The primary level name of an Organisation or Business Entity.
    */
   private String name1;
   
   /**
    * The second-level name of an organisation or business entity (for example, 
    * department, division, and so on).
    */
   private String name2;
   
   /**
    * The third-level name of an organisation or business entity.
    */
   private String name3;
   
   /**
    * A short represntation of Organisation unit.
    */
   private String orgUnitCode;
   
   /**
    * A description of the Organisation Unit Name.
    */
   private String orgUnitName;
   
   /**
    * First Phone Number of Party
    */
   private String phone1;
   
   /**
    * Second Phone Number of Party
    */
   private String phone2;
   
   /**
    * The postal code associated with the address elements.
    */
   private String postalCode;
   
   /**
    * Standard Address Number is the unique identifier of a specific party.
    */
   private String san;
   
   /**
    * State of Party
    */
   private String state;
   
   /**
    * Website of Party
    */
   private String website;
   
   /**
    * Party Type
    */
   private String partyType;
   
   /**
    * Transport Detail of Party
    */
   private PartyTransport transportDetail;
   
   /**
    * Country Detail of Admin Party
    */
   private Country countryDetail;
   
   /**
    * Full name of User who added this party
    */
   private String fullName;
   
   /**
    * Constructor of the Party
    */
   public Party() 
   {
	super();
	countryDetail = new Country();
	transportDetail = new PartyTransport();
   }
   
   /**
    * Gets activeFlag
    * @return java.lang.String
    */
   public String getActiveFlag() 
   {
	return this.activeFlag;    
   }

   /**
    * Gets address1
    * @return java.lang.String
    */
   public String getAddress1() 
   {
	return this.address1;    
   }
   
   /**
    * Gets address2
    * @return java.lang.String
    */
   public String getAddress2() 
   {
	return this.address2;    
   }
   
   /**
    * Gets address3
    * @return java.lang.String
    */
   public String getAddress3() 
   {
	return this.address3;    
   }
   
   /**
    * Gets address4
    * @return java.lang.String
    */
   public String getAddress4() 
   {
	return this.address4;    
   }
   
   /**
    * Gets city
    * @return java.lang.String
    */
   public String getCity() 
   {
	return this.city;    
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
    * Gets createdBy
    * @return java.lang.String
    */
   public Integer getCreatedBy() 
   {
	return this.createdBy;    
   }
   
   /**
    * Gets creationDateTime
    * @return Date
    */
   public Date getCreationDateTime() 
   {
	return this.creationDateTime;    
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
    * Gets fax1
    * @return java.lang.String
    */
   public String getFax1() 
   {
	return this.fax1;    
   }
   
   /**
    * Gets fax2
    * @return java.lang.String
    */
   public String getFax2() 
   {
	return this.fax2;    
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
    * Gets modDateTime
    * @return Date
    */
   public Date getModDateTime() 
   {
	return this.modDateTime;    
   }
   
   /**
    * Gets modifiedBy
    * @return java.lang.String
    */
   public Integer getModifiedBy() 
   {
	return this.modifiedBy;    
   }
   
   /**
    * Gets name1
    * @return java.lang.String
    */
   public String getName1() 
   {
	return this.name1;    
   }
   
   /**
    * Gets name2
    * @return java.lang.String
    */
   public String getName2() 
   {
	return this.name2;    
   }
   
   /**
    * Gets name3
    * @return java.lang.String
    */
   public String getName3() 
   {
	return this.name3;    
   }
   
   /**
    * Gets orgUnitCode
    * @return java.lang.String
    */
   public String getOrgUnitCode() 
   {
	return this.orgUnitCode;    
   }
   
   /**
    * Gets orgUnitName
    * @return java.lang.String
    */
   public String getOrgUnitName() 
   {
	return this.orgUnitName;    
   }
   
   /**
    * Gets phone1
    * @return java.lang.String
    */
   public String getPhone1() 
   {
	return this.phone1;    
   }
   
   /**
    * Gets phone2
    * @return java.lang.String
    */
   public String getPhone2() 
   {
	return this.phone2;    
   }
   
   /**
    * Gets postalCode
    * @return java.lang.String
    */
   public String getPostalCode() 
   {
	return this.postalCode;    
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
    * Gets state
    * @return java.lang.String
    */
   public String getState() 
   {
	return this.state;    
   }
   
   /**
    * Gets website
    * @return java.lang.String
    */
   public String getWebsite() 
   {
	return this.website;    
   }
   
   /**
    * Gets fullname
    * @return java.lang.String
    */
   public String getFullName() 
   {
	return this.fullName;    
   }
   
   /**
    * Sets activeFlag
    * @param activeFlag
    */
   public void setActiveFlag(String activeFlag) 
   {
		this.activeFlag = activeFlag;    
   }

   /**
    * Sets address1
    * @param address1
    */
   public void setAddress1(String address1) 
   {
		this.address1 = address1;    
   }
   
   /**
    * Sets address2
    * @param address2
    */
   public void setAddress2(String address2) 
   {
		this.address2 = address2;    
   }
   
   /**
    * Sets address3
    * @param address3
    */
   public void setAddress3(String address3) 
   {
		this.address3 = address3;    
   }
   
   /**
    * Sets address4
    * @param address4
    */
   public void setAddress4(String address4) 
   {
		this.address4 = address4;    
   }
   
   /**
    * Sets city
    * @param city
    */
   public void setCity(String city) 
   {
		this.city = city;    
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
    * Sets createdBy
    * @param createdBy
    */
   public void setCreatedBy(Integer createdBy) 
   {
		this.createdBy = createdBy;    
   }
   
   /**
    * Sets creationDateTime
    * @param creationDateTime
    */
   public void setCreationDateTime(Date creationDateTime) 
   {
		this.creationDateTime = creationDateTime;    
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
    * Sets fax1
    * @param fax1
    */
   public void setFax1(String fax1) 
   {
		this.fax1 = fax1;    
   }
   
   /**
    * Sets fax2
    * @param fax2
    */
   public void setFax2(String fax2) 
   {
		this.fax2 = fax2;    
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
    * Sets modDateTime
    * @param modDateTime
    */
   public void setModDateTime(Date modDateTime) 
   {
		this.modDateTime = modDateTime;    
   }
   
   /**
    * Sets modifiedBy
    * @param modifiedBy
    */
   public void setModifiedBy(Integer modifiedBy) 
   {
		this.modifiedBy = modifiedBy;    
   }
   
   /**
    * Sets name1
    * @param name1
    */
   public void setName1(String name1) 
   {
		this.name1 = name1;    
   }
   
   /**
    * Sets name2
    * @param name2
    */
   public void setName2(String name2) 
   {
		this.name2 = name2;    
   }
   
   /**
    * Sets name3
    * @param name3
    */
   public void setName3(String name3) 
   {
		this.name3 = name3;    
   }
   
   /**
    * Sets orgUnitCode
    * @param orgUnitCode
    */
   public void setOrgUnitCode(String orgUnitCode) 
   {
		this.orgUnitCode = orgUnitCode;    
   }
   
   /**
    * Sets orgUnitName
    * @param orgUnitName
    */
   public void setOrgUnitName(String orgUnitName) 
   {
		this.orgUnitName = orgUnitName;    
   }
   
   /**
    * Sets phone1
    * @param phone1
    */
   public void setPhone1(String phone1) 
   {
		this.phone1 = phone1;    
   }
   
   /**
    * Sets phone2
    * @param phone2
    */
   public void setPhone2(String phone2) 
   {
		this.phone2 = phone2;    
   }
   
   /**
    * Sets postalCode
    * @param postalCode
    */
   public void setPostalCode(String postalCode) 
   {
		this.postalCode = postalCode;    
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
    * Sets state
    * @param state
    */
   public void setState(String state) 
   {
		this.state = state;    
   }
   
   /**
    * Sets website
    * @param website
    */
   public void setWebsite(String website) 
   {
		this.website = website;    
   }
   
   /**
    * Gets countryDetail
    * @return com.pearson.pix.dto.common.Country
    */
   public Country getCountryDetail() 
   {
	return this.countryDetail;    
   }
   
   /**
    * Gets partyType
    * @return java.lang.String
    */
   public String getPartyType() 
   {
	return this.partyType;    
   }
   
   /**
    * Gets transportDetail
    * @return com.pearson.pix.dto.admin.PartyTransport
    */
   public PartyTransport getTransportDetail() 
   {
   return this.transportDetail;    
   }
   
   /**
    * Sets countryDetail
    * @param countryDetail
    */
   public void setCountryDetail(Country countryDetail) 
   {
		this.countryDetail = countryDetail;    
   }
   
   /**
    * Sets partyType
    * @param partyType
    */
   public void setPartyType(String partyType) 
   {
		this.partyType = partyType;    
   }
   
   /**
    * Sets transportDetail
    * @param transportDetail
    */
   public void setTransportDetail(PartyTransport transportDetail) 
   {
		this.transportDetail = transportDetail;    
   }
   
   /**
    * Sets fullName
    * @param fullName
    */
   public void setFullName(String fullName) 
   {
		this.fullName = fullName;    
   }
}
