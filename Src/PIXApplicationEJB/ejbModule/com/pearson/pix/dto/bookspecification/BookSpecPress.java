package com.pearson.pix.dto.bookspecification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import java.io.Serializable;

import com.pearson.pix.dto.common.UOM;

/**
 * Contains Book Specification Press information
 */
public class BookSpecPress implements Serializable 
{
   
   /**
    * Barcode
    */
   private String barcode;
   
   /**
    * The weight of the paper expressed as a mass for a given surface area
    */
   private BigDecimal basisWt;
   
   /**
    * Ink Colour Specification
    */
   private String inkColourSpecs;
   
   /**
    * The sheet length measurement expressed with decimals, not fractions.
    */
   private BigDecimal length;
   
   /**
    * The Number of Pages
    */
   private BigDecimal noOfPages;
   
   /**
    * The number of Signatures
    */
   private BigDecimal noOfSign;
   
   /**
    * Pages per Signature
    */
   private BigDecimal pagesPerSign;
   
   /**
    * The distance from the edge of the paper. This element is used to communicate 
    * where the hole is.
    */
   private BigDecimal perfoEdgeDistance;
   
   /**
    * Perforation type
    */
   private String perfoType;
   
   /**
    * A group item used to communicate the number of pages in an inch of thickness 
    * either as a target or actual value. Equal to the number of sheets per inch 
    * times 2.
    */
   private BigDecimal ppi;
   
   /**
    * press PreInput Type
    */
   private String pressPrepInputType;
   
   /**
    * The type of press used
    */
   private String pressType;
   
   /**
    * print product code
    */
   private String printProductCode;
   
   /**
    * Description of the printproduct
    */
   private String printProductDescription;
   
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
    * The sheet width measurement using a valid UOM; can be expressed with decimals, 
    * but not fractions.
    */
   private BigDecimal width;
   
   /**
    * Collection of margins
    */
   private Collection marginCollection;
   
   /**
    * Number of colors
    */
   	private Integer noOfColors;
   	
   	/**
     * color description
     */
	private String colorDesc;
	
	/**
    * Trim Size description
    */
	private String trimSizeDesc;
	/**
    * Basis Weight UOM Details
    */
	private UOM basisWtUomDetail;
	/**
    * PPI UOM Details
    */
	private UOM ppiUomDetail;
	/**
    * Length UOM Details
    */
	private UOM lengthUomDetail;
	/**
    * Width UOM Details
    */
	private UOM widthUomDetail;
   
   /**
    * Constructor of the BookSpecPress
    */
   public BookSpecPress() 
   {
		super();
		this.marginCollection = new Vector();    
   }
   
   /**
    * Gets barcode
    * @return java.lang.String
    */
   public String getBarcode() 
   {
		return this.barcode;    
   }
   
   /**
    * Gets basisWt
    * @return java.math.BigDecimal
    */
   public BigDecimal getBasisWt() 
   {
		return this.basisWt;    
   }
   /**
    * Gets basisWtUomDetail
    * @return com.pearson.pix.dto.common.UOM
    */
   public UOM getBasisWtUomDetail() {
		return this.basisWtUomDetail;
	}
   /**
    * Gets colorDesc
    * @return java.lang.String
    */
   public String getColorDesc() {
		return this.colorDesc;
	}
   
   /**
    * Gets inkColourSpecs
    * @return java.lang.String
    */
   public String getInkColourSpecs() 
   {
		return this.inkColourSpecs;    
   }
   
   /**
    * Gets length
    * @return java.math.BigDecimal
    */
   public BigDecimal getLength() 
   {
		return this.length;    
   }
   
   /**
    * Gets marginCollection
    * @return java.util.Collection
    */
   public Collection getMarginCollection() 
   {
		return this.marginCollection;    
   }
   
   /**
    * Gets lengthUomDetail
    * @return com.pearson.pix.dto.common.UOM
    */
   public UOM getLengthUomDetail() {
		return this.lengthUomDetail;
	}
   /**
    * Gets noOfColors
    * @return Integer
    */
   public Integer getNoOfColors() {
		return this.noOfColors;
	}
   
   /**
    * Gets noOfPages
    * @return java.math.BigDecimal
    */
   public BigDecimal getNoOfPages() 
   {
		return this.noOfPages;    
   }
   
   /**
    * Gets noOfSign
    * @return java.math.BigDecimal
    */
   public BigDecimal getNoOfSign() 
   {
		return this.noOfSign;    
   }
   
   /**
    * Gets pagesPerSign
    * @return java.math.BigDecimal
    */
   public BigDecimal getPagesPerSign() 
   {
		return this.pagesPerSign;    
   }
   
   /**
    * Gets perfoEdgeDistance
    * @return java.math.BigDecimal
    */
   public BigDecimal getPerfoEdgeDistance() 
   {
		return this.perfoEdgeDistance;    
   }
   
   /**
    * Gets perfoType
    * @return java.lang.String
    */
   public String getPerfoType() 
   {
		return this.perfoType;    
   }
   
   /**
    * Gets ppiUomDetail
    * @return com.pearson.pix.dto.common.UOM
    */
   public UOM getPpiUomDetail() {
		return this.ppiUomDetail;
	}
   /**
    * Gets ppi
    * @return java.math.BigDecimal
    */
   public BigDecimal getPpi() 
   {
		return this.ppi;    
   }
   
   /**
    * Gets pressPrepInputType
    * @return java.lang.String
    */
   public String getPressPrepInputType() 
   {
		return this.pressPrepInputType;    
   }
   
   /**
    * Gets pressType
    * @return java.lang.String
    */
   public String getPressType() 
   {
		return this.pressType;    
   }
   
   /**
    * Gets printProductCode
    * @return java.lang.String
    */
   public String getPrintProductCode() 
   {
		return this.printProductCode;    
   }
   
   /**
    * Gets printProductDescription
    * @return java.lang.String
    */
   public String getPrintProductDescription() 
   {
		return this.printProductDescription;    
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
    * Gets trimSizeDesc
    * @return java.lang.String
    */
   public String getTrimSizeDesc() {
		return this.trimSizeDesc;
	}
   /**
    * Gets width
    * @return java.math.BigDecimal
    */
   public BigDecimal getWidth() 
   {
		return this.width;    
   }
   
   /**
    * Gets widthUomDetail
    * @return com.pearson.pix.dto.common.UOM
    */
   public UOM getWidthUomDetail() {
		return this.widthUomDetail;
	}
   /**
    * Sets barcode
    * @param barcode
    */
   public void setBarcode(String barcode) 
   {
		this.barcode = barcode;    
   }
   /**
    * Sets basisWtUomDetail
    * @param basisWtUomDetail
    */
   public void setBasisWtUomDetail(UOM basisWtUomDetail) {
		this.basisWtUomDetail = basisWtUomDetail;
	}
   /**
    * Sets basisWt
    * @param basisWt
    */
   public void setBasisWt(BigDecimal basisWt) 
   {
		this.basisWt = basisWt;    
   }
   
   /**
    * Sets colorDesc
    * @param colorDesc
    */
   public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}
   /**
    * Sets inkColourSpecs
    * @param inkColourSpecs
    */
   public void setInkColourSpecs(String inkColourSpecs) 
   {
		this.inkColourSpecs = inkColourSpecs;    
   }
   
   /**
    * Sets length
    * @param length
    */
   public void setLength(BigDecimal length) 
   {
		this.length = length;    
   }
   
   /**
    * Sets lengthUomDetail
    * @param lengthUomDetail
    */
   public void setLengthUomDetail(UOM lengthUomDetail) {
		this.lengthUomDetail = lengthUomDetail;
	}
   /**
    * Sets marginCollection
    * @param marginCollection
    */
   public void setMarginCollection(Collection marginCollection) 
   {
		this.marginCollection = marginCollection;    
   }
   
   /**
    * Sets noOfColors
    * @param noOfColors
    */
   public void setNoOfColors(Integer noOfColors) {
		this.noOfColors = noOfColors;
	}
   
   /**
    * Sets noOfPages
    * @param noOfPages
    */
   public void setNoOfPages(BigDecimal noOfPages) 
   {
		this.noOfPages = noOfPages;    
   }
   
   /**
    * Sets noOfSign
    * @param noOfSign
    */
   public void setNoOfSign(BigDecimal noOfSign) 
   {
		this.noOfSign = noOfSign;    
   }
   
   /**
    * Sets pagesPerSign
    * @param pagesPerSign
    */
   public void setPagesPerSign(BigDecimal pagesPerSign) 
   {
		this.pagesPerSign = pagesPerSign;    
   }
   
   /**
    * Sets perfoEdgeDistance
    * @param perfoEdgeDistance
    */
   public void setPerfoEdgeDistance(BigDecimal perfoEdgeDistance) 
   {
		this.perfoEdgeDistance = perfoEdgeDistance;    
   }
   
   /**
    * Sets perfoType
    * @param perfoType
    */
   public void setPerfoType(String perfoType) 
   {
		this.perfoType = perfoType;    
   }
   
   /**
    * Sets ppi
    * @param ppi
    */
   public void setPpi(BigDecimal ppi) 
   {
		this.ppi = ppi;    
   }
   
   /**
    * Sets ppiUomDetail
    * @param ppiUomDetail
    */
   public void setPpiUomDetail(UOM ppiUomDetail) {
		this.ppiUomDetail = ppiUomDetail;
	}
   /**
    * Sets pressPrepInputType
    * @param pressPrepInputType
    */
   public void setPressPrepInputType(String pressPrepInputType) 
   {
		this.pressPrepInputType = pressPrepInputType;    
   }
   
   /**
    * Sets pressType
    * @param pressType
    */
   public void setPressType(String pressType) 
   {
		this.pressType = pressType;    
   }
   
   /**
    * Sets printProductCode
    * @param printProductCode
    */
   public void setPrintProductCode(String printProductCode) 
   {
		this.printProductCode = printProductCode;    
   }
   
   /**
    * Sets printProductDescription
    * @param printProductDescription
    */
   public void setPrintProductDescription(String printProductDescription) 
   {
		this.printProductDescription = printProductDescription;    
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
    * Sets trimSizeDesc
    * @param trimSizeDesc
    */
   public void setTrimSizeDesc(String trimSizeDesc) {
		this.trimSizeDesc = trimSizeDesc;
	}
   /**
    * Sets width
    * @param width
    */
   public void setWidth(BigDecimal width) 
   {
		this.width = width;    
   }
   /**
    * Sets widthUomDetail
    * @param widthUomDetail
    */
   public void setWidthUomDetail(UOM widthUomDetail) {
		this.widthUomDetail = widthUomDetail;
	}
}
