package com.pearson.pix.presentation.purchaseorder.action;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;
import com.pearson.pix.dto.purchaseorder.POHeader;

public class PurchaseOrderListForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private POHeader poHeader;
	private Collection poCollection;
	private String startDate;
	private String endDate;
	private String selectedEntry;
	private String sbBDate;
	private String ebBDate;
	
	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request) 
    {
	 this.poHeader = new POHeader();
	}
   
	public POHeader getPoHeader()
	{
	    return this.poHeader;
	}
	public void setPoHeader(POHeader poHeader)
    {
    	this.poHeader = poHeader;
    }
	
	 /**
	 * Gets planningCollection
	 * @return java.util.Collection
	 */
		public Collection getpoCollection() {
			return poCollection;
		}
		/**
	  * Sets planningCollection
	  * @param planningCollection
	  */
		public void setpoCollection(Collection poCollection) {
			this.poCollection = poCollection;
		}
		
		public String getstartDate()
		   {
		        return this.startDate;
		           
		   }
		 public void setstartDate(String startDate)
		   {
		        this.startDate = startDate;
		   }
		 
		 public String getendDate()
		   {
		        return this.endDate;
		           
		   }
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
		 
		 public String getSelectedEntry() {
		  		return selectedEntry;
		  	}
		 public void setSelectedEntry(String selectedEntry) {
		  		this.selectedEntry = selectedEntry;
		  	}
	
}
