package com.pearson.pix.dto.bookspecification;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * Contains BookSpecificationBinding information
 */
public class BookSpecBinding implements Serializable 
{
   
   /**
    * Binding back style type
    */
   private String backStyleType;
   
   /**
    * Binding extras
    */
   private BigDecimal bindingExtraQuantity;
   
   /**
    * The finish applied to the blockedge.
    */
   private String blockEdgeFinish;
   
   /**
    * The number of hits in caseDecoration
    */
   private BigDecimal caseDecorationHits;
   
   /**
    * Describes the finishing process.
    */
   private String caseDecorationType;
   
   /**
    * Binding cover scoring.
    */
   private String coverScoring;
   
   /**
    * Describes the End sheet information..
    */
   private String endSheetDesc;
   
   /**
    * Describes the Hole PunchEdgeDistance Information..
    */
   private BigDecimal holepunchEdgeDistance;
   
   /**
    * Describes the Hole Punch Information..
    */
   private String holePunchInfo;
   
   /**
    * A definition of the size and shape of the punched holes..
    */
   private BigDecimal holepunchSize;
   
   /**
    * Hole punch type..
    */
   private String holePunchType;
   
   /**
    * The HoleSpacing element is used to communicate the distance between holes..
    */
   private BigDecimal perfoEdgeDistance;
   
   /**
    * Describes the perfoType.
    */
   private String perfoType;
   
   /**
    * Describes the Binding reinforcement.
    */
   private String reinforcement;
   
   /**
    * Describes the Specification Id.
    */
   private BigDecimal specId;
   
   /**
    * Describes the Specification Line Number.
    */
   private BigDecimal specLineNo;
   
   /**
    * Describes the specification version.
    */
   private BigDecimal specVersion;
   
   /**
    * Indicates the style of binding used..
    */
   private String styleType;
   
   /**
    * Wire Gauge.
    */
   private String wireGauge;
   
   /**
    * Contructor to initialize BookSpecBinding
    */
   public BookSpecBinding() 
   {
		super();    
   }
   
   /**
    * Gets backStyleType
    * @return java.lang.String
    */
   public String getBackStyleType() 
   {
		return this.backStyleType;    
   }
   
   /**
    * Gets bindingExtraQuantity
    * @return java.math.BigDecimal
    */
   public BigDecimal getBindingExtraQuantity() 
   {
		return this.bindingExtraQuantity;    
   }
   
   /**
    * Gets blockEdgeFinish
    * @return java.lang.String
    */
   public String getBlockEdgeFinish() 
   {
		return this.blockEdgeFinish;    
   }
   
   /**
    * Gets caseDecorationHits
    * @return java.math.BigDecimal
    */
   public BigDecimal getCaseDecorationHits() 
   {
		return this.caseDecorationHits;    
   }
   
   /**
    * Gets caseDecorationType
    * @return java.lang.String
    */
   public String getCaseDecorationType() 
   {
		return this.caseDecorationType;    
   }
   
   /**
    * Gets coverScoring
    * @return java.lang.String
    */
   public String getCoverScoring() 
   {
		return this.coverScoring;    
   }
   
   /**
    * Gets endSheetDesc
    * @return java.lang.String
    */
   public String getEndSheetDesc() 
   {
		return this.endSheetDesc;    
   }
   
   /**
    * Gets holePunchInfo
    * @return java.lang.String
    */
   public String getHolePunchInfo() 
   {
		return this.holePunchInfo;    
   }
   
   /**
    * Gets holePunchType
    * @return java.lang.String
    */
   public String getHolePunchType() 
   {
		return this.holePunchType;    
   }
   
   /**
    * Gets holepunchEdgeDistance
    * @return java.math.BigDecimal
    */
   public BigDecimal getHolepunchEdgeDistance() 
   {
		return this.holepunchEdgeDistance;    
   }
   
   /**
    * Gets holepunchSize
    * @return java.math.BigDecemal
    */
   public BigDecimal getHolepunchSize() 
   {
		return this.holepunchSize;    
   }
   
   /**
    * Gets perfoEdgeDistance
    * @return java.amth.BigDecimal
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
    * Gets reinforcement
    * @return java.lang.String
    */
   public String getReinforcement() 
   {
		return this.reinforcement;    
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
    * Gets styleType
    * @return java.lang.String
    */
   public String getStyleType() 
   {
		return this.styleType;    
   }
   
   /**
    * Gets wireGauge
    * @return java.lang.String
    */
   public String getWireGauge() 
   {
		return this.wireGauge;    
   }
   
   /**
    * Sets backStyleType
    * @param backStyleType
    */
   public void setBackStyleType(String backStyleType) 
   {
		this.backStyleType = backStyleType;    
   }
   
   /**
    * Sets bindingExtraQuantity
    * @param bindingExtraQuantity
    */
   public void setBindingExtraQuantity(BigDecimal bindingExtraQuantity) 
   {
		this.bindingExtraQuantity = bindingExtraQuantity;    
   }
   
   /**
    * Sets blockEdgeFinish
    * @param blockEdgeFinish
    */
   public void setBlockEdgeFinish(String blockEdgeFinish) 
   {
		this.blockEdgeFinish = blockEdgeFinish;    
   }
   
   /**
    * Sets caseDecorationHits
    * @param caseDecorationHits
    */
   public void setCaseDecorationHits(BigDecimal caseDecorationHits) 
   {
		this.caseDecorationHits = caseDecorationHits;    
   }
   
   /**
    * Sets caseDecorationType
    * @param caseDecorationType
    */
   public void setCaseDecorationType(String caseDecorationType) 
   {
		this.caseDecorationType = caseDecorationType;    
   }
   
   /**
    * Sets coverScoring
    * @param coverScoring
    */
   public void setCoverScoring(String coverScoring) 
   {
		this.coverScoring = coverScoring;    
   }
   
   /**
    * Sets endSheetDesc
    * @param endSheetDesc
    */
   public void setEndSheetDesc(String endSheetDesc) 
   {
		this.endSheetDesc = endSheetDesc;    
   }
   
   /**
    * Sets holePunchInfo
    * @param holePunchInfo
    */
   public void setHolePunchInfo(String holePunchInfo) 
   {
		this.holePunchInfo = holePunchInfo;    
   }
   
   /**
    * Sets holePunchType
    * @param holePunchType
    */
   public void setHolePunchType(String holePunchType) 
   {
		this.holePunchType = holePunchType;    
   }
   
   /**
    * Sets holepunchEdgeDistance
    * @param holepunchEdgeDistance
    */
   public void setHolepunchEdgeDistance(BigDecimal holepunchEdgeDistance) 
   {
		this.holepunchEdgeDistance = holepunchEdgeDistance;    
   }
   
   /**
    * Sets holepunchSize
    * @param holepunchSize
    */
   public void setHolepunchSize(BigDecimal holepunchSize) 
   {
		this.holepunchSize = holepunchSize;    
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
    * Sets reinforcement
    * @param reinforcement
    */
   public void setReinforcement(String reinforcement) 
   {
		this.reinforcement = reinforcement;    
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
    * Sets styleType
    * @param styleType
    */
   public void setStyleType(String styleType) 
   {
		this.styleType = styleType;    
   }
   
   /**
    * Sets wireGauge
    * @param wireGauge
    */
   public void setWireGauge(String wireGauge) 
   {
		this.wireGauge = wireGauge;    
   }
}
