package com.pearson.pix.dto.admin;


import com.pearson.pix.dto.common.Country;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;

/**
 * Contains User Detial Information
 */
public class User implements Serializable 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
   private String emailActivityFlag;
   
   /**
    * First Fax Number of the User
    */
   private String fax1;
   
   /**
    * Second Fax Number of User
    */
   private String fax2;
   
   /**
    * First Name of the User
    */
   private String firstName;
   
   /**
    * Last Name of the User
    */
   private String lastName;
   
   /**
    * Login of the User
    */
   private String login;
   
   /**
    * mobile number of the User
    */
   private String mobile;
   
   /**
    * Modification date and time of the User
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private Integer modifiedBy;
   
   /**
    * password of the User
    */
   private String password;
   
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
    * State of Party
    */
   private String state;
   
   /**
    * user Identification
    */
   private Integer userId;
   
   /**
    * Website of the User
    */
   private String website;
   
   /**
    * Date of the LastLogin by the User
    */
   private Date lastLogin;
   
   /**
    * Collection of Parties
    */
   private Collection partyCollection;
   
   /**
    * Country Detail of the User
    */
   private Country countryDetail;
   
   /**
    * Collection of user privileges
    */
   private Collection privilegeCollection;
   
   /**
    * roleType Detail fo the User
    */
   private UserRole roleTypeDetail;
   
   /**
    * Password Expiry of the User
    */
   private String passwordExpiry;
   
   /**
    * Full name of User who added this party
    */
   private String fullName;
   
   private String ssoid;
   private String linkFromPepms;
   
   public String getSsoid() {
	return ssoid;
}

public void setSsoid(String ssoid) {
	this.ssoid = ssoid;
}

/**
    * Constructor of the Admin
    */
   public User() 
   {
	super();
	countryDetail = new Country();
	roleTypeDetail = new UserRole();
	partyCollection = new Vector();
	privilegeCollection = new Vector();  
	
   }
   
   /**
    * Adds partyCollection
    * @param com.pearson.pix.dto.admin.Party
    */
   public void addToPartyCollection(Party aParty) {
		this.partyCollection.add(aParty);
	}
   
   /**
    * Adds privilegeCollection
    * @param com.pearson.pix.dto.admin.UserPriv
    */
	public void addToPrivilegeCollection(UserPriv anUserPriv) {
		this.privilegeCollection.add(anUserPriv);
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
    * Gets emailActivityFlag
    * @return java.lang.String
    */
   public String getEmailActivityFlag() 
   {
		return this.emailActivityFlag;    
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
    * Gets firstName
    * @return java.lang.String
    */
   public String getFirstName() 
   {
		return this.firstName;    
   }
   
   /**
    * Gets lastName
    * @return java.lang.String
    */
   public String getLastName() 
   {
		return this.lastName;    
   }
   
   /**
    * Gets login
    * @return java.lang.String
    */
   public String getLogin() 
   {
		return this.login;    
   }
   
   /**
    * Gets mobile
    * @return java.math.BigDecimal
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
    * Gets partyCollection
    * @return java.util.Collection
    */
   public Collection getPartyCollection() 
   {
		return this.partyCollection;    
   }
   
   
   
   /**
    * Gets password
    * @return java.lang.String
    */
   public String getPassword() 
   {
		return this.password;    
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
    * Gets state
    * @return java.lang.String
    */
   public String getState() 
   {
		return this.state;    
   }
   
   /**
    * Gets userId
    * @return java.math.BigDecimal
    */
   public Integer getUserId() 
   {
		return this.userId;    
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
    * Gets passwordExpiry
    * @return java.lang.String
    */
   public String getPasswordExpiry() {
		return this.passwordExpiry;
	}
   
   /**
    * Removes aParty
    * @param com.pearson.pix.dto.admin.Party
    */
   public void removeFromPartyCollection(Party aParty) {
		this.partyCollection.remove(aParty);
	}
   
   /**
    * Removes anUserPriv
    * @param com.pearson.pix.dto.admin.UserPriv
    */
	public void removeFromPrivilegeCollection(UserPriv anUserPriv) {
		this.privilegeCollection.remove(anUserPriv);
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
    * Sets emailActivityFlag
    * @param emailActivityFlag
    */
   public void setEmailActivityFlag(String emailActivityFlag) 
   {
		this.emailActivityFlag = emailActivityFlag;    
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
    * Sets firstName
    * @param firstName
    */
   public void setFirstName(String firstName) 
   {
		this.firstName = firstName;    
   }
   
   /**
    * Sets lastName
    * @param lastName
    */
   public void setLastName(String lastName) 
   {
		this.lastName = lastName;    
   }
   
   /**
    * Sets login
    * @param login
    */
   public void setLogin(String login) 
   {
		this.login = login;    
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
    * Sets partyCollection
    * @param partyCollection
    */
   public void setPartyCollection(Collection partyCollection) 
   {
		this.partyCollection = partyCollection;    
   }
   
   
   /**
    * Sets password
    * @param password
    */
   public void setPassword(String password) 
   {
		this.password = password;    
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
    * Sets state
    * @param state
    */
   public void setState(String state) 
   {
		this.state = state;    
   }
   
   /**
    * Sets userId
    * @param userId
    */
   public void setUserId(Integer userId) 
   {
		this.userId = userId;    
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
    * Sets lastLogin
    * @param lastLogin
    */
   public void setLastLogin(Date lastLogin) 
   {
		this.lastLogin = lastLogin;    
   }
   
   /**
    * Gets lastLogin
    * @return Date
    */
   public Date getLastLogin() 
   {
		return this.lastLogin;    
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
    * Gets privilegeCollection
    * @return java.util.Collection
    */
   public Collection getPrivilegeCollection() 
   {
		return this.privilegeCollection;    
   }
   
   /**
    * Gets roleTypeDetail
    * @return com.pearson.pix.dto.admin.UserRole
    */
   public UserRole getRoleTypeDetail() 
   {
		return this.roleTypeDetail;    
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
    * Sets privilegeCollection
    * @param privilegeCollection
    */
   public void setPrivilegeCollection(Collection privilegeCollection) 
   {
		this.privilegeCollection = privilegeCollection;    
   }
   
   /**
    * Sets roleTypeDetail
    * @param roleTypeDetail
    */
   public void setRoleTypeDetail(UserRole roleTypeDetail) 
   {
		this.roleTypeDetail = roleTypeDetail;    
   }
   
   /**
    * Sets passwordExpiry
    * @param passwordExpiry
    */
   public void setPasswordExpiry(String passwordExpiry) 
   {
		this.passwordExpiry = passwordExpiry;
   } 
   
   /**
    * Sets fullName
    * @param fullName
    */
   public void setFullName(String fullName) 
   {
		this.fullName = fullName;    
   }

public String getLinkFromPepms() {
	return linkFromPepms;
}

public void setLinkFromPepms(String linkFromPepms) {
	this.linkFromPepms = linkFromPepms;
}
}
