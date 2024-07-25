package com.pearson.pix.dto.bookspecification;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contain's the Book Specification Non press Details
 */
public class BookSpecNonPress implements Serializable 
{
   
   /**
    * Master storage option
    */
   private String masterStorageOption;
   
   /**
    * Specification Id
    */
   private BigDecimal specId;
   
   /**
    * Specification Line Number
    */
   private BigDecimal specLineNo;
   
   /**
    * Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * The total number of units.
    */
   private BigDecimal totalUnits;
   
   /**
    * Detail of BookSpecificationMedia
    */
   private BookSpecMedia mediaDetail;
   
   /**
    * Constructor to initialize BookSpecNonPress
    */
   public BookSpecNonPress() 
   {
		super();    
   }
   
   /**
    * Gets masterStorageOption
    * @return java.lang.String
    */
   public String getMasterStorageOption() 
   {
		return this.masterStorageOption;    
   }
   
   /**
    * Gets mediaDetail
    * @return com.pearson.pix.dto.BookSpecification.BookSpecNonPress
    */
   public BookSpecMedia getMediaDetail() 
   {
		return this.mediaDetail;    
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
    * Gets specLineNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getSpecLineNo() 
   {
		return this.specLineNo;    
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
    * Gets totalUnits
    * @return java.math.BigDecimal
    */
   public BigDecimal getTotalUnits() 
   {
		return this.totalUnits;    
   }
   
   /**
    * Sets masterStorageOption
    * @param masterStorageOption
    */
   public void setMasterStorageOption(String masterStorageOption) 
   {
		this.masterStorageOption = masterStorageOption;    
   }
   
   /**
    * Sets mediaDetail
    * @param mediaDetail
    */
   public void setMediaDetail(BookSpecMedia mediaDetail) 
   {
		this.mediaDetail = mediaDetail;    
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
    * Sets specLineNo
    * @param specLineNo
    */
   public void setSpecLineNo(BigDecimal specLineNo) 
   {
		this.specLineNo = specLineNo;    
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
    * Sets totalUnits
    * @param totalUnits
    */
   public void setTotalUnits(BigDecimal totalUnits) 
   {
		this.totalUnits = totalUnits;    
   }
}
