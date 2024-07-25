package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Reference;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains the Details of the Book Specification Media
 */
public class BookSpecMedia implements Serializable 
{
   
   /**
    * Describes the finish to be applied to the CD.
    */
   private String cdrType;
   
   /**
    * CD format type
    */
   private String formatType;
   
   /**
    * A free form description of the Colour
    */
   private String labelColorDesc;
   
   /**
    * Media length
    */
   private BigDecimal mediaLength;
   
   /**
    * The number of colours
    */
   private BigDecimal noOfColors;
   
   /**
    * Element to identify press preparation media and type of media required for 
    * input to the press component
    */
   private String pressPreinputType;
   
   /**
    * Printing Materials Information
    */
   private String printing;
   
   /**
    * A code indicating the colour.
    */
   private String slideColourCode;
   
   /**
    * Slide container description
    */
   private String slideCountainerDesc;
   
   /**
    * The sheet length measurement expressed with decimals, not fractions.
    */
   private BigDecimal slideLength;
   
   /**
    * slide Length UomId
    */
   private BigDecimal slideLengthUomId;
   
   /**
    * Slide mount material
    */
   private String slideMountMaterial;
   
   /**
    * Slide mount description
    */
   private String slideMountMaterialDesc;
   
   /**
    * Pages per Set
    */
   private BigDecimal slidePagesPerSet;
   
   /**
    * Slides per page
    */
   private BigDecimal slidePerPages;
   
   /**
    * Sequance No for slide.
    */
   private BigDecimal slideSeqNo;
   
   /**
    * The sheet width measurement using a valid UOM; can be expressed with
    * decimals, but not fractions.
    */
   private BigDecimal slideWidth;
   
   /**
    * Slide witdth UomId
    */
   private BigDecimal slideWidthUomId;
   
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
    * Communicates the type of meterial used to wrap the product
    */
   private String wrapType;
   
   /**
    * The Detail source of the media
    */
   private Reference mediaSourceDetail;
   
   /**
    * Constructor for initializing Book Specification Media
    */
   public BookSpecMedia() 
   {
		super();
		this.mediaSourceDetail = new Reference();    
   }
   
   /**
    * Gets cdrType
    * @return java.lang.String
    */
   public String getCdrType() 
   {
		return this.cdrType;    
   }
   
   /**
    * Gets formatType
    * @return java.lang.String
    */
   public String getFormatType() 
   {
		return this.formatType;    
   }
   
   /**
    * Gets labelColorDesc
    * @return java.lang.String
    */
   public String getLabelColorDesc() 
   {
		return this.labelColorDesc;    
   }
   
   /**
    * Gets mediaLength
    * @return java.math.BigDecimal
    */
   public BigDecimal getMediaLength() 
   {
		return this.mediaLength;    
   }
   
   /**
    * Gets mediaSourceDetail
    * @return com.pearson.pix.dto.common.Reference
    */
   public Reference getMediaSourceDetail() 
   {
		return this.mediaSourceDetail;    
   }
   
   /**
    * Gets noOfColors
    * @return java.lang.BigDecimal
    */
   public BigDecimal getNoOfColors() 
   {
		return this.noOfColors;    
   }
   
   /**
    * Gets pressPreinputType
    * @return java.lang.String
    */
   public String getPressPreinputType() 
   {
		return this.pressPreinputType;    
   }
   
   /**
    * Gets printing
    * @return java.lang.String
    */
   public String getPrinting() 
   {
		return this.printing;    
   }
   
   /**
    * Gets slideColourCode
    * @return java.lang.String
    */
   public String getSlideColourCode() 
   {
		return this.slideColourCode;    
   }
   
   /**
    * Gets slideCountainerDesc
    * @return java.lang.String
    */
   public String getSlideCountainerDesc() 
   {
		return this.slideCountainerDesc;    
   }
   
   /**
    * Gets slideLength
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlideLength() 
   {
		return this.slideLength;    
   }
   
   /**
    * Gets slideLengthUomId
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlideLengthUomId() 
   {
		return this.slideLengthUomId;    
   }
   
   /**
    * Gets slideMountMaterial
    * @return java.lang.String
    */
   public String getSlideMountMaterial() 
   {
		return this.slideMountMaterial;    
   }
   
   /**
    * Gets slideMountMaterialDesc
    * @return java.lang.String
    */
   public String getSlideMountMaterialDesc() 
   {
		return this.slideMountMaterialDesc;    
   }
   
   /**
    * Gets slidePagesPerSet
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlidePagesPerSet() 
   {
		return this.slidePagesPerSet;    
   }
   
   /**
    * Gets slidePerPages
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlidePerPages() 
   {
		return this.slidePerPages;    
   }
   
   /**
    * Gets slideSeqNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlideSeqNo() 
   {
		return this.slideSeqNo;    
   }
   
   /**
    * Gets slideWidth
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlideWidth() 
   {
		return this.slideWidth;    
   }
   
   /**
    * Gets slideWidthUomId
    * @return java.math.BigDecimal
    */
   public BigDecimal getSlideWidthUomId() 
   {
		return this.slideWidthUomId;    
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
    * Gets wrapType
    * @return java.lang.String
    */
   public String getWrapType() 
   {
		return this.wrapType;    
   }
   
   /**
    * Sets cdrType
    * @param cdrType
    */
   public void setCdrType(String cdrType) 
   {
		this.cdrType = cdrType;    
   }
   
   /**
    * Sets formatType
    * @param formatType
    */
   public void setFormatType(String formatType) 
   {
		this.formatType = formatType;    
   }
   
   /**
    * Sets labelColorDesc
    * @param labelColorDesc
    */
   public void setLabelColorDesc(String labelColorDesc) 
   {
		this.labelColorDesc = labelColorDesc;    
   }
   
   /**
    * Sets mediaLength
    * @param mediaLength
    */
   public void setMediaLength(BigDecimal mediaLength) 
   {
		this.mediaLength = mediaLength;    
   }
   
   /**
    * Sets mediaSourceDetail
    * @param mediaSourceDetail
    */
   public void setMediaSourceDetail(Reference mediaSourceDetail) 
   {
		this.mediaSourceDetail = mediaSourceDetail;    
   }
   
   /**
    * Sets noOfColors
    * @param noOfColors
    */
   public void setNoOfColors(BigDecimal noOfColors) 
   {
		this.noOfColors = noOfColors;    
   }
   
   /**
    * Sets pressPreinputType
    * @param pressPreinputType
    */
   public void setPressPreinputType(String pressPreinputType) 
   {
		this.pressPreinputType = pressPreinputType;    
   }
   
   /**
    * Sets printing
    * @param printing
    */
   public void setPrinting(String printing) 
   {
		this.printing = printing;    
   }
   
   /**
    * Sets slideColourCode
    * @param slideColourCode
    */
   public void setSlideColourCode(String slideColourCode) 
   {
		this.slideColourCode = slideColourCode;    
   }
   
   /**
    * Sets slideCountainerDesc
    * @param slideCountainerDesc
    */
   public void setSlideCountainerDesc(String slideCountainerDesc) 
   {
		this.slideCountainerDesc = slideCountainerDesc;    
   }
   
   /**
    * Sets slideLength
    * @param slideLength
    */
   public void setSlideLength(BigDecimal slideLength) 
   {
		this.slideLength = slideLength;    
   }
   
   /**
    * Sets slideLengthUomId
    * @param slideLengthUomId
    */
   public void setSlideLengthUomId(BigDecimal slideLengthUomId) 
   {
		this.slideLengthUomId = slideLengthUomId;    
   }
   
   /**
    * Sets slideMountMaterial
    * @param slideMountMaterial
    */
   public void setSlideMountMaterial(String slideMountMaterial) 
   {
		this.slideMountMaterial = slideMountMaterial;    
   }
   
   /**
    * Sets slideMountMaterialDesc
    * @param slideMountMaterialDesc
    */
   public void setSlideMountMaterialDesc(String slideMountMaterialDesc) 
   {
		this.slideMountMaterialDesc = slideMountMaterialDesc;    
   }
   
   /**
    * Sets slidePagesPerSet
    * @param slidePagesPerSet
    */
   public void setSlidePagesPerSet(BigDecimal slidePagesPerSet) 
   {
		this.slidePagesPerSet = slidePagesPerSet;    
   }
   
   /**
    * Sets slidePerPages
    * @param slidePerPages
    */
   public void setSlidePerPages(BigDecimal slidePerPages) 
   {
		this.slidePerPages = slidePerPages;    
   }
   
   /**
    * Sets slideSeqNo
    * @param slideSeqNo
    */
   public void setSlideSeqNo(BigDecimal slideSeqNo) 
   {
		this.slideSeqNo = slideSeqNo;    
   }
   
   /**
    * Sets slideWidth
    * @param slideWidth
    */
   public void setSlideWidth(BigDecimal slideWidth) 
   {
		this.slideWidth = slideWidth;    
   }
   
   /**
    * Sets slideWidthUomId
    * @param slideWidthUomId
    */
   public void setSlideWidthUomId(BigDecimal slideWidthUomId) 
   {
		this.slideWidthUomId = slideWidthUomId;    
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
    * Sets wrapType
    * @param wrapType
    */
   public void setWrapType(String wrapType) 
   {
		this.wrapType = wrapType;    
   }
}
