package com.pearson.pix.dto.planning;

import com.pearson.pix.dto.common.Country;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

/**
 * Contains all Planning Party  information
 */
public class PlanningParty implements Serializable 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String acknowledgeFlag;
   
   /**
    * The primary-level address of an organisation or business entity (for 
    * example,street name, and number).
    */
   private String address1;
   
   /**
    * The Second-level address of an organisation or business entity
    */
   private String address2;
   
   /**
    * The third-level address of an organisation or business entity
    */
   private String address3;
   
   /**
    * The fourth-level address of an organisation or business entity
    */
   private String address4;
   
   /**
    * Name of the city associated with the address elements.
    */
   private String city;
   
   /**
    * comments
    */
   private String comments;
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Date and Time of Creation
    */
   private Date creationDateTime;
   
   /**
    * Finished Vendor Flag of planning
    */
   private String finishedVendorFlag;
   
   /**
    * Date and Time of modification
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
    * A description of the Organisation Unit.
    */
   private String orgUnitName;
   
   /**
    * Identifies the business role associated with the particular party
    */
   private String partyType;
   
   /**
    * plan Identification
    */
   private BigDecimal planId;
   
   /**
    * plan Version
    */
   private BigDecimal planVersion;
   
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
    * Url of party
    */
   private String url;
   
   /**
    * Collection of contacts associated with planning.
    */
   private Collection contactCollection;
   
   /**
    * The name of the country associated with address element.
    */
   private Country countryDetail;
   
   /**
    * Constructor of PlanningParty
    */
   public PlanningParty() 
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
    * @return com.pearson.pix.dto.common.Country
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
    * @return Date
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
    * Sets url
    * @param url
    */
   public void setUrl(String url) 
   {
	this.url = url;    
   }
}
