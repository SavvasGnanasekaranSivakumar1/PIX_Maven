package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.dto.common.UOM;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains the Book Specification Margin Details
 */
public class BookSpecMargin implements Serializable 
{
   
   /**
    * Refer to the main item defination for any enumerations
    */
   private String bleed;
   
   /**
    * Id of the Book Specification
    */
   private BigDecimal specId;
   
   /**
    * LineNo of the Book Specification
    */
   private BigDecimal specLineNo;
   
   /**
    * Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * A numeric value expressed in the unit of measure (UOM).
    */
   private BigDecimal value;
   
   /**
    * Margin Detail
    */
   private Reference marginDetail;
   /**
    * UOM Detail
    */
   private UOM UOMDetail;
   
   /**
    * Constructor for initializing BookSpecMargin
    */
   public BookSpecMargin() 
   {
		super();
		this.marginDetail = new Reference();    
   }
   
   /**
    * Gets bleed
    * @return java.lang.String
    */
   public String getBleed() 
   {
		return this.bleed;    
   }
   
   /**
    * Gets marginDetail
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getMarginDetail() 
   {
		return this.marginDetail;    
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
    * Gets UOMDetail
    * @return com.pearson.pix.dto.common.UOM
    */
   public UOM getUOMDetail() {
		return this.UOMDetail;
   }
   /**
    * Gets value
    * @return java.math.BigDecimal
    */
   public BigDecimal getValue() 
   {
		return this.value;    
   }
   
   /**
    * Sets bleed
    * @param bleed
    */
   public void setBleed(String bleed) 
   {
		this.bleed = bleed;    
   }
   
   /**
    * Sets marginDetail
    * @param marginDetail
    */
   public void setMarginDetail(Reference marginDetail) 
   {
		this.marginDetail = marginDetail;    
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
    * Sets UOMDetail
    * @param UOMDetail
    */   
   public void setUOMDetail(UOM UOMDetail) {
		this.UOMDetail = UOMDetail;
	}   
   /**
    * Sets value
    * @param value
    */
   public void setValue(BigDecimal value) 
   {
		this.value = value;    
   }
}
