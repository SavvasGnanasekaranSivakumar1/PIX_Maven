package com.pearson.pix.dto.bookspecification;

import com.pearson.pix.dto.common.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * Contains BookSpecificationMisc detials
 */
public class BookSpecMisc implements Serializable 
{
   
   /**
    * Created By user Id
    */
   private String createdBy;
   
   /**
    * Creation Date and Time
    */
   private Date creationDate;
   
   /**
    * label
    */
   private String label;
   
   /**
    * Last modification date
    */
   private Date lastModificationDate;
   
   /**
    * Last Modified By user Id
    */
   private String lastModifiedBy;
   
   /**
    * Line No in miscellaneous
    */
   private BigDecimal miscLineNo;
   
   /**
    * Specification Id
    */
   private BigDecimal specId;
   
   /**
    * Specification Line No
    */
   private BigDecimal specLineNo;
   
   /**
    * Specification Version
    */
   private BigDecimal specVersion;
   
   /**
    * Value
    */
   private String value;
   
   /**
    * Detail of the Table
    */
   private Table tableDetail;
   
   /**
    * Constructor for intializing BookSpecMisc
    */
   public BookSpecMisc() 
   {
		super();    
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
    * Gets creationDate
    * @return Date
    */
   public Date getCreationDate() 
   {
		return this.creationDate;    
   }
   
   /**
    * Gets label
    * @return java.lang.String
    */
   public String getLabel() 
   {
		return this.label;    
   }
   
   /**
    * Gets lastModificationDate
    * @return Date
    */
   public Date getLastModificationDate() 
   {
		return this.lastModificationDate;    
   }
   
   /**
    * Gets lastModifiedBy
    * @return java.lang.String
    */
   public String getLastModifiedBy() 
   {
		return this.lastModifiedBy;    
   }
   
   /**
    * Gets misLineNo
    * @return java.math.BigDecimal
    */
   public BigDecimal getMiscLineNo() 
   {
		return this.miscLineNo;    
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
    * Gets tableDetail
    * @return com.pearson.pix.dto.common.Table
    */
   public Table getTableDetail() 
   {
		return this.tableDetail;    
   }
   
   /**
    * Gets value
    * @return java.lang.String
    */
   public String getValue() 
   {
		return this.value;    
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
    * Sets creationDate
    * @param creationDate
    */
   public void setCreationDate(Date creationDate) 
   {
		this.creationDate = creationDate;    
   }
   
   /**
    * Sets label
    * @param label
    */
   public void setLabel(String label) 
   {
		this.label = label;    
   }
   
   /**
    * Sets lastModificationDate
    * @param lastModificationDate
    */
   public void setLastModificationDate(Date lastModificationDate) 
   {
		this.lastModificationDate = lastModificationDate;    
   }
   
   /**
    * Sets lastModifiedBy
    * @param lastModifiedBy
    */
   public void setLastModifiedBy(String lastModifiedBy) 
   {
		this.lastModifiedBy = lastModifiedBy;    
   }
   
   /**
    * Sets miscLineNo
    * @param miscLineNo
    */
   public void setMiscLineNo(BigDecimal miscLineNo) 
   {
		this.miscLineNo = miscLineNo;    
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
    * Sets tableDetail
    * @param tableDetail
    */
   public void setTableDetail(Table tableDetail) 
   {
		this.tableDetail = tableDetail;    
   }
   
   /**
    * Sets value
    * @param value
    */
   public void setValue(String value) 
   {
		this.value = value;    
   }
}
