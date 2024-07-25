package com.pearson.pix.presentation.usage.action;

import java.util.Vector;

import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.dto.usage.UsageLine;
import com.pearson.pix.presentation.base.action.BaseForm;


public class UsageFormList extends BaseForm {
	 private UsageLine usageLine;
	 private Usage usage;
	 private Vector usageCollection;
	 private String startDate;
	 private String endDate;
	 private POHeader poHeader;
	 private String selectedEntry;
	 
	public final void reset(org.apache.struts.action.ActionMapping mapping,javax.servlet.http.HttpServletRequest request)
	{
	   if(usage == null){
		 usage=new Usage();
	   }
	    //   this.usageLine = new UsageLine();
	}
	   
	   /**
	     * Gets Usage
	     * @return Usage
	     */
	   
	   public Usage getUsage()
	    {
	         return this.usage;
	    }
	 	 
	   /**
	     * Sets Usage
	     * @param Usage
	     */
	   
	    public void setUsage(Usage usage)
	    {
	         this.usage = usage;
	    }
	   
	    /**
	     * Gets UsageLine
	     * @return UsageLine
	     */
	   
	   public UsageLine getUsageLine()
	    {
	         return this.usageLine;
	    }
	 	  
	   /**
	     * Sets UsageLine
	     * @param UsageLine
	     */
	   
	    public void setUsageLine(UsageLine usageLine)
	    {
	         this.usageLine = usageLine;
	    }
	    
	     /**
	     * Gets UsageCollection
	     * @return java.util.Collection
	     */
	  	public Vector getUsageCollection() {
	  		return usageCollection;
	  	}
	  	/**
	      * Sets UsageCollection
	      * @param OrderStatusCollection
	      */
	  	public void setUsageCollection(Vector usageCollection) {
	  		this.usageCollection = usageCollection;
	  	}
	  	
	  	public String getStartDate()
		{
		   return this.startDate;
		}
		public void setStartDate(String startDate)
		{
		    this.startDate = startDate;
		}
		 
		public String getEndDate()
		{
		    return this.endDate;
        }
		
		public void setEndDate(String endDate)
		{
		    this.endDate = endDate;
		}

		public POHeader getPoHeader() {
			return poHeader;
		}

		public void setPoHeader(POHeader poHeader) {
			this.poHeader = poHeader;
		}
		public String getSelectedEntry() {
	  		return selectedEntry;
	  	}
		public void setSelectedEntry(String selectedEntry) {
	  		this.selectedEntry = selectedEntry;
	  	}

}
