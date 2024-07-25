package com.pearson.pix.presentation.planning.action;

import java.util.Collection;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.presentation.base.action.BaseForm;

/**
 * @author faisalk
 *
 */
public class PlanningForm extends BaseForm{
	
	public static final long serialVersionUID = 001011L;
    private Collection planningCollection;
	private POHeader poHeader;
	private String startDate;
	private String endDate;
	private String selectedEntry;
	private POParty poparty;
	private String sbBDate;
	private String ebBDate;

	
	public final void reset
      (org.apache.struts.action.ActionMapping mapping,
      javax.servlet.http.HttpServletRequest request)
   {
      this.poHeader = new POHeader();
   }
  
	 /**
	 * Gets POParty
	 * @return POParty
	 */
	public POParty getPoparty() {
		return poparty;
	}
	/**
	  * Sets POParty
	  * @param POParty
	  */
	public void setPoparty(POParty poparty) {
		this.poparty = poparty;
	}
	  
	 /**
	 * Gets selectedEntry
	 * @return selectedEntry
	 */
	
	public String getSelectedEntry() {
		return selectedEntry;
	}
	
	/**
	  * Sets selectedEntry
	  * @param selectedEntry
	  */
	public void setSelectedEntry(String selectedEntry) {
		this.selectedEntry = selectedEntry;
	}
	
	 /**
	 * Gets POHeader
	 * @return POHeader
	 */
	public POHeader getPoHeader()
   {
        return this.poHeader;
   }
	 
	/**
	  * Sets POHeader
	  * @param POHeader
	  */
   public void setPoHeader(POHeader poHeader)
   {
        this.poHeader = poHeader;
   }
 /**
 * Gets planningCollection
 * @return java.util.Collection
 */
	public Collection getPlanningCollection() {
		return planningCollection;
	}
	/**
  * Sets planningCollection
  * @param planningCollection
  */
	public void setPlanningCollection(Collection planningCollection) {
		this.planningCollection = planningCollection;
	}
	
	/**
	  * Gets startdate
	  * return startdate
	  */
	
	public String getstartDate()
	   {
	        return this.startDate;
	   }
	 
	/**
	  * Sets startdate
	  * @param startdate
	  */
	public void setstartDate(String startDate)
	   {
	        this.startDate = startDate;
	   }
	 
	/**
	  * Gets enddate
	  * return enddate
	  */
	 public String getendDate()
	   {
	        return this.endDate;
	           
	   }
	 /**
	  * Sets enddate
	  * @param enddate
	  */
	 public void setendDate(String endDate)
	   {
	        this.endDate = endDate;
	   }
	 public String getsbBDate()
	   {
		return this.sbBDate;
		           
	   }
	 public void setsbBDate(String sbBDate)
	   {
		 this.sbBDate = sbBDate;
	  }
	 public String getebBDate()
	   {
		return this.ebBDate;
		           
	   }
	 public void setebBDate(String ebBDate)
	   {
		 this.ebBDate = ebBDate;
	  }
	 
}