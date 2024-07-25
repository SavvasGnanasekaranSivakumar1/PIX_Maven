package com.pearson.pix.dto.arptitlesetup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.common.UOM;



/**
 * Contains all Planning information
 */
public class PotentialARP implements Serializable 
{
  
	  private FlipreportdetailsArp potentialarpDetail;
	  
	  private BigDecimal specId;
	  private BigDecimal specVersion;
	  private String requestDate;
	  private String arpDueDate;
	  //private WqstatesArp statusDetail;

	  /**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the arpDueDate
	 */
	public String getArpDueDate() {
		return arpDueDate;
	}

	/**
	 * @param arpDueDate the arpDueDate to set
	 */
	public void setArpDueDate(String arpDueDate) {
		this.arpDueDate = arpDueDate;
	}

	/**
	    * Constructor to initialize
	    */
	   public PotentialARP() 
	   {
		super();
		potentialarpDetail = new FlipreportdetailsArp();  
		//statusDetail = new WqstatesArp();
		//titleDetail = new TitlePrinting();
		//statusDetail=new Status();  
	   }

	/**
	 * @return the potentialarpDetail
	 */
	public FlipreportdetailsArp getPotentialarpDetail() {
		return potentialarpDetail;
	}

	/**
	 * @param potentialarpDetail the potentialarpDetail to set
	 */
	public void setPotentialarpDetail(FlipreportdetailsArp potentialarpDetail) {
		this.potentialarpDetail = potentialarpDetail;
	}

	public BigDecimal getSpecId() {
		return specId;
	}

	public void setSpecId(BigDecimal specId) {
		this.specId = specId;
	}

	public BigDecimal getSpecVersion() {
		return specVersion;
	}

	public void setSpecVersion(BigDecimal specVersion) {
		this.specVersion = specVersion;
	}

	
	
	
}
