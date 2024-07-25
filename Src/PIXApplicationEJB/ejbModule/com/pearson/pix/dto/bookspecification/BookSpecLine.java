package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Reference;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import java.io.Serializable;

/**
 * Contains all BookSpecificationLine(Book Specification) information.
 */
public class BookSpecLine implements Serializable 
{
   
   /**
    * Describes the agency maintaining the list of codes. To be included in this
    * list the agency must be a recognized standards body or industry
    * organisation.
    */
   private String agency;
   
   /**
    * Instructions of the Assembly
    */
   private String asseInstructions;
   
   /**
    * Count of components per production
    */
   private BigDecimal compPerProduction;
   
   /**
    * Finished GoodFlag of user
    */
   private String finishedGoodFlag;
   
   /**
    * Used to communicate the code of the article, in variety of formats designated 
    * by Type.
    */
   private String productCode;
   
   /**
    * An element used to communicate a human readable description of
    * the product in the language specified by the Language attribute.
    */
   private String productDescription;
   
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
    * Detail's of Book Specification Binding.
    */
   private BookSpecBinding bindingDetail;
   
   /**
    * Collection of Classifications associated with Book Specification
    */
   private Collection classificationCollection;
   
   /**
    * Collection of misc associated with Book Specification
    */
   private Collection miscCollection;
   
   /**
    * Detail's of nonPressDetail
    */
   private BookSpecNonPress nonPressDetail;
   
   /**
    * Detail's of pressDetail.
    */
   private BookSpecPress pressDetail;
   private Reference productTypeDetail;
   
   /**
    * Contructor to initialize BookSpecLine
    */
   public BookSpecLine() 
   {
		super();
		this.classificationCollection = new Vector();
		this.miscCollection = new Vector();    
   }
   
   /**
    * Gets agency
    * @return java.lang.String
    */
   public String getAgency() 
   {
		return this.agency;    
   }
   
   /**
    * Gets asseInstructions
    * @return java.lang.String
    */
   public String getAsseInstructions() 
   {
		return this.asseInstructions;    
   }
   
   /**
    * Gets bindingDetail
    * @return com.pearson.pix.dto.BookSpecification.BookSpecBinding
    */
   public BookSpecBinding getBindingDetail() 
   {
		return this.bindingDetail;    
   }
   
   /**
    * Gets classificationCollection
    * @return java.util.Collection
    */
   public Collection getClassificationCollection() 
   {
		return this.classificationCollection;    
   }
   
   /**
    * Gets compPerProduction
    * @return java.math.BigDecimal
    */
   public BigDecimal getCompPerProduction() 
   {
		return this.compPerProduction;    
   }
   
   /**
    * Gets finishedGoodFlag
    * @return java.lang.String
    */
   public String getFinishedGoodFlag() 
   {
		return this.finishedGoodFlag;    
   }
   
   /**
    * Gets miscCollection
    * @return java.util.Collection
    */
   public Collection getMiscCollection() 
   {
		return this.miscCollection;    
   }
   
   /**
    * Gets nonPressDetail
    * @return com.pearson.pix.dto.BookSpecification.BookSpecNonPress
    */
   public BookSpecNonPress getNonPressDetail() 
   {
		return this.nonPressDetail;    
   }
   
   /**
    * Gets pressDetail
    * @return com.pearson.pix.dto.BookSpecification.BookSpecPress
    */
   public BookSpecPress getPressDetail() 
   {
		return this.pressDetail;    
   }
   
   /**
    * Gets productCode
    * @return java.lang.String
    */
   public String getProductCode() 
   {
		return this.productCode;    
   }
   
   /**
    * Gets productDescription
    * @return java.lang.String
    */
   public String getProductDescription() 
   {
		return this.productDescription;    
   }
   
   /**
    * Gets productTypeDetail
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getProductTypeDetail() 
   {
		return this.productTypeDetail;    
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
    * Sets agency
    * @param agency
    */
   public void setAgency(String agency) 
   {
		this.agency = agency;    
   }
   
   /**
    * Sets asseInstructions
    * @param asseInstructions
    */
   public void setAsseInstructions(String asseInstructions) 
   {
		this.asseInstructions = asseInstructions;    
   }
   
   /**
    * Sets bindingDetail
    * @param bindingDetail
    */
   public void setBindingDetail(BookSpecBinding bindingDetail) 
   {
		this.bindingDetail = bindingDetail;    
   }
   
   /**
    * Sets classificationCollection
    * @param classificationCollection
    */
   public void setClassificationCollection(Collection classificationCollection) 
   {
		this.classificationCollection = classificationCollection;    
   }
   
   /**
    * Sets compPerProduction
    * @param compPerProduction
    */
   public void setCompPerProduction(BigDecimal compPerProduction) 
   {
		this.compPerProduction = compPerProduction;    
   }
   
   /**
    * Sets finishedGoodFlag
    * @param finishedGoodFlag
    */
   public void setFinishedGoodFlag(String finishedGoodFlag) 
   {
		this.finishedGoodFlag = finishedGoodFlag;    
   }
   
   /**
    * Sets miscCollection
    * @param miscCollection
    */
   public void setMiscCollection(Collection miscCollection) 
   {
		this.miscCollection = miscCollection;    
   }
   
   /**
    * Sets nonPressDetail
    * @param nonPressDetail
    */
   public void setNonPressDetail(BookSpecNonPress nonPressDetail) 
   {
		this.nonPressDetail = nonPressDetail;    
   }
   
   /**
    * Sets pressDetail
    * @param pressDetail
    */
   public void setPressDetail(BookSpecPress pressDetail) 
   {
		this.pressDetail = pressDetail;    
   }
   
   /**
    * Sets productCode
    * @param productCode
    */
   public void setProductCode(String productCode) 
   {
		this.productCode = productCode;    
   }
   
   /**
    * Sets productDescription
    * @param productDescription
    */
   public void setProductDescription(String productDescription) 
   {
		this.productDescription = productDescription;    
   }
   
   /**
    * Sets productTypeDetail
    * @param productTypeDetail
    */
   public void setProductTypeDetail(Reference productTypeDetail) 
   {
		this.productTypeDetail = productTypeDetail;    
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
