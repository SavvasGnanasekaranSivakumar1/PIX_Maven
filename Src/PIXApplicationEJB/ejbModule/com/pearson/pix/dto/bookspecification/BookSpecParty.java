package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Country;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

/**
 * ###  Generated by Oracle TopLink Workbench 10.1.3.0.0 - Mon Jun 19 13:56:08 IST 
 * 2006.  ###
 */
public class BookSpecParty implements Serializable 
{
   
   /**
    * Acknowledgement flag of the Book Spec Party
    */
   private String acknowledgeFlag;
   
   /**
    * The primary-level address of an organisation or business entity
    */
   private String address1;
   
   /**
    * The second-level address of an organisation or business entity.
    */
   private String address2;
   
   /**
    * The third-level address of an Organization or business entity.
    */
   private String address3;
   
   /**
    * The fourth-level address of an Organization or business entity.
    */
   private String address4;
   
   /**
    * Name of the city associatd with the address element.
    */
   private String city;
   
   /**
    * comments by party
    */
   private String comments;
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Creation Date and Time
    */
   private Date creationDateTime;
   
   /**
    * Finished Vendor Flag
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
    * The primary level name of an Organisation or Business Entity.
    */
   private String name1;
   
   /**
    * The second-level name of an organisation or business entity (for example, 
    * department,division, and so on).
    */
   private String name2;
   
   /**
    * The third-level name of an organisation or business entity.
    */
   private String name3;
   
   /**
    * Unit code of the Organization
    */
   private String orgUnitCode;
   
   /**
    * Unit Name of the Organization
    */
   private String orgUnitName;
   
   /**
    * Identifies the business role associated with the particular party.
    */
   private String partyType;
   
   /**
    * The postal code associated with the address element.
    */
   private String postalCode;
   
   /**
    * Supplier Address Number
    */
   private String san;
   
   /**
    * Book Specification Id
    */
   private BigDecimal specId;
   
   /**
    * Book Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * The political designation or boundry of a specific area of land.
    */
   private String state;
   
   /**
    * Universal Resource Locator. While typically a web address you could use this
    * field to hold an email address.
    */
   private String url;
   
   /**
    * A Collection of Contacts.
    */
   private Collection contactCollection;
   
   /**
    * Detail  of Country
    */
   private Country countryDetail;
   
   /**
    * Constructor to initialize BookSpecParty
    */
   public BookSpecParty() 
   {
		super();
		this.contactCollection = new Vector();    
   }
   
   /**
    * Gets acknowledgeFlag
    * @return java.lang.String
    */
   public String getAcknowledgeFlag() 
   {
		return this.acknowledgeFlag;    
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
    * Gets comments
    * @return java.lang.String
    */
   public String getComments() 
   {
		return this.comments;    
   }
   
   /**
    * Gets contactCollection
    * @return java.util.Collection
    */
   public Collection getContactCollection() 
   {
		return this.contactCollection;    
   }
   
   /**
    * Gets countryDetail
    * @return java.lang.String
    */
   public Country getCountryDetail() 
   {
		return this.countryDetail;    
   }
   
   /**
    * Gets createdBy
    * @return java.lang.String
    */
   public String getCreatedBy() 
   {
		return this.createdBy;    
   }
   
   /**
    * Gets creationDateTime
    * @return java.lang.String
    */
   public Date getCreationDateTime() 
   {
		return this.creationDateTime;    
   }
   
   /**
    * Gets finishedVendorFlag
    * @return java.lang.String
    */
   public String getFinishedVendorFlag() 
   {
		return this.finishedVendorFlag;    
   }
   
   /**
    * Gets modDateTime
    * @return java.lang.String
    */
   public Date getModDateTime() 
   {
		return this.modDateTime;    
   }
   
   /**
    * Gets modifiedBy
    * @return java.lang.String
    */
   public String getModifiedBy() 
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
    * Gets partyType
    * @return java.lang.String
    */
   public String getPartyType() 
   {
		return this.partyType;    
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
    * Gets state
    * @return java.lang.String
    */
   public String getState() 
   {
		return this.state;    
   }
   
   /**
    * Gets url
    * @return java.lang.String
    */
   public String getUrl() 
   {
		return this.url;    
   }
   
   /**
    * Sets acknowledgeFlag
    * @param acknowledgeFlag
    */
   public void setAcknowledgeFlag(String acknowledgeFlag) 
   {
		this.acknowledgeFlag = acknowledgeFlag;    
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
    * Sets comments
    * @param comments
    */
   public void setComments(String comments) 
   {
		this.comments = comments;    
   }
   
   /**
    * Sets contactCollection
    * @param contactCollection
    */
   public void setContactCollection(Collection contactCollection) 
   {
		this.contactCollection = contactCollection;    
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
    * Sets createdBy
    * @param createdBy
    */
   public void setCreatedBy(String createdBy) 
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
    * Sets finishedVendorFlag
    * @param finishedVendorFlag
    */
   public void setFinishedVendorFlag(String finishedVendorFlag) 
   {
		this.finishedVendorFlag = finishedVendorFlag;    
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
   public void setModifiedBy(String modifiedBy) 
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
    * Sets partyType
    * @param partyType
    */
   public void setPartyType(String partyType) 
   {
		this.partyType = partyType;    
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
   
   /**
    * Sets state
    * @param state
    */
   public void setState(String state) 
   {
		this.state = state;    
   }
   
   /**
    * Sets url
    * @param url
    */
   public void setUrl(String url) 
   {
		this.url = url;    
   }
}