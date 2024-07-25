package com.pearson.pix.dto.bookspecification;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains all BoodSpecClassification information
 */
public class BookSpecClassif implements Serializable 
{
   
   /**
    * A code assigned by the indicated Agency for this particular class of product.
    */
   private String classifCode;
   
   /**
    * An element used to communicate a human readable description of
    * the product in the language specified by the Language attribute.
    */
   private String description;
   
   /**
    * Specification Id.
    */
   private BigDecimal specId;
   
   /**
    * Specification Line No.
    */
   private BigDecimal specLineNo;
   
   /**
    * Specification version.
    */
   private BigDecimal specVersion;
   
   /**
    * Contructor to initialize BookSpecClassif
    */
   public BookSpecClassif() 
   {
	super();    
   }
   
   /**
    * Gets activeFlag
    * @return java.lang.String
    */
   public String getClassifCode() 
   {
	return this.classifCode;    
   }
   
   /**
    * Gets description
    * @return java.lang.String
    */
   public String getDescription() 
   {
	return this.description;    
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
    * Sets classifCode
    * @param classifCode
    */
   public void setClassifCode(String classifCode) 
   {
		this.classifCode = classifCode;    
   }
   
   /**
    * Sets description
    * @param description
    */
   public void setDescription(String description) 
   {
		this.description = description;    
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
}
