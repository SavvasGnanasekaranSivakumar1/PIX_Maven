package com.pearson.pix.presentation.mismatchreport.delegate;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocal;
import com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;

public class MismatchReportDelegate {

	/**
	 * Constructor for initializing MismatchReportDelegate
	*/
	public MismatchReportDelegate() {
	}
	
	 /**
	    * Creates the Home Object first and then return the EJB Object from it
	    * @return com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocal
	    */
	   private MismatchReportLocal getMismatchReportLocal() 
	   {
		   MismatchReportLocal objMismatchReportLocal = null;
		  try
		    {
				
			  MismatchReportLocalHome objCostAccountingLocalHome = ServiceLocator.getMismatchReportLocalHome();
				objMismatchReportLocal = objCostAccountingLocalHome.create();
			} 
			catch (NamingException ne)
			{
				ne.printStackTrace();
			} 
			catch (CreateException ce)
			{
			    ce.printStackTrace();
			}
			return objMismatchReportLocal;
		}
	   
	   /**
	    * Method to get the MismatchReportDetails of PurchaseOrder;
	    * @param poNumber
	    * @return
	    * @throws AppException
	    */
	   
  public Collection getMismatchReportDetails(String poNumber) throws AppException{	  
	  Collection mismatchReportDetails = getMismatchReportLocal().getMismatchReportDetails(poNumber);
	  return mismatchReportDetails;
	  
  }
	   

}
