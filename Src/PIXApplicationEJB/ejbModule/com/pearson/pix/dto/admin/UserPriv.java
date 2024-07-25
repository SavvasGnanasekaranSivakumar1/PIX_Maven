package com.pearson.pix.dto.admin;

import com.pearson.pix.dto.common.Reference;
import java.util.Date;
import java.io.Serializable;

import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;

/**
 * Contains UserPrivilages information
 */
public class UserPriv implements Serializable 
{
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
    * Access Flag of the user for a Privilege
    */
   private String accessFlag;
   
   /**
    * Created By user Id
    */
   private Integer createdBy;
   
   /**
    * Creation Date and Time
    */
   private Date creationDateTime;
   
   /**
    * Modified Date and Time
    */
   private Date modDateTime;
   
   /**
    * Modified By user Id
    */
   private Integer modifiedBy;
   
   /**
    * User Identification
    */
   private ValueHolderInterface userDetail;
   
   /**
    * moduleDetail of the UserPrivlages
    */
   private Reference moduleDetail;
   
   /**
    * Constructor of the UserPriv
    */
   public UserPriv() 
   {
		super();   
		this.userDetail = new ValueHolder();
   }
   
   /**
    * Gets accessFlag
    * @return java.lang.String
    */
   public String getAccessFlag() 
   {
		return this.accessFlag;    
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
    * Gets userId
    * @return User
    */
    public User getUserDetail() {
		return (User) this.userDetail.getValue();
	}

	protected ValueHolderInterface getUserDetailHolder() {
		return this.userDetail;
	}
   
   /**
    * Sets accessFlag
    * @param accessFlag
    */
   public void setAccessFlag(String accessFlag) 
   {
		this.accessFlag = accessFlag;    
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
    * Sets userDetail
    * @param userDetail
    */
    public void setUserDetail(User userDetail) {
		this.userDetail.setValue(userDetail);
	}

	protected void setUserDetailHolder(ValueHolderInterface userDetail) {
		this.userDetail = userDetail;
	}
   
   /**
    * Gets moduleDetail
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getModuleDetail() 
   {
		return this.moduleDetail;    
   }
   
   /**
    * Sets moduleDetail
    * @param moduleDetail
    */
   public void setModuleDetail(Reference moduleDetail) 
   {
		this.moduleDetail = moduleDetail;    
   }
}
