package com.pearson.pix.presentation.purchaseorder.action;

import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.presentation.base.action.BaseForm;

/**
 * The form contains the variables and methods for Detail page related information of Purchase Order. 
 * @author Mohit Bajaj
 *
 */
public class PurchaseOrderDetailForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private POHeader poHeader;
	private Collection poCollection;
	private String startDate;
	private String endDate;
	private Vector lineItems;
	private Collection poAllHeaderStatus;
	private Collection poAllLineStatus;
	private Collection poPriceDetails;
	private Collection planningAllStatus;
	private String pocosting;
		 
	public Collection getPlanningAllStatus() {
		return planningAllStatus;
	}

	public void setPlanningAllStatus(Collection planningAllStatus) {
		this.planningAllStatus = planningAllStatus;
	}

	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request) 
    {
    }
   
	public POHeader getPoHeader()
	{
	    return this.poHeader;
	}
	public void setPoHeader(POHeader poHeader)
    {
    	this.poHeader = poHeader;
    }
	
	public Vector getLineItems()
	{
	    return this.lineItems;
	}
	public void setLineItems(Vector lineItems)
    {
    	this.lineItems = lineItems;
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

		public Collection getPoAllHeaderStatus() {
			return poAllHeaderStatus;
		}

		public void setPoAllHeaderStatus(Collection poAllHeaderStatus) {
			this.poAllHeaderStatus = poAllHeaderStatus;
		}

		public Collection getPoAllLineStatus() {
			return poAllLineStatus;
		}

		public void setPoAllLineStatus(Collection poAllLineStatus) {
			this.poAllLineStatus = poAllLineStatus;
		}

		public Collection getPoPriceDetails() {
			return poPriceDetails;
		}

		public void setPoPriceDetails(Collection poPriceDetails) {
			this.poPriceDetails = poPriceDetails;
		}


	   public String getPocosting() {
					return pocosting;
	   }

	  public void setPocosting(String pocosting) {
					this.pocosting = pocosting;
	  }
			 

	
}
