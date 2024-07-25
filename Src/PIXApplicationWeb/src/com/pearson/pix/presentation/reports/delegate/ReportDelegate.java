package com.pearson.pix.presentation.reports.delegate;
import com.pearson.pix.business.reports.interfaces.ReportLocal;
import com.pearson.pix.business.reports.interfaces.ReportLocalHome;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.naming.NamingException;
/**
 * @author sudam.sahu
 */
/**
 * Business Delegate for Report. It acts as the Client for EJB invocation.
 */
public class ReportDelegate 
{
   
   /**
    * Constructor for Initializing ReportDelegate
    */
   public ReportDelegate() 
   {
    
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * @return com.pearson.pix.business.reports.interfaces.ReportLocal
    */
   private ReportLocal getReportLocal() 
   {
	   ReportLocal objReportLocal = null;
		  try 
		  {
			  ReportLocalHome objReportLocalHome = ServiceLocator.getReportLocalHome();
			  objReportLocal = objReportLocalHome.create();
		  } 
		  catch (NamingException ne)
		  {
				ne.printStackTrace();
		  } 
		  catch (CreateException ce)
		  {
				ce.printStackTrace();
		  }
		  return objReportLocal;
   }
   
   /**
    * Calls corresponding Business method of ReportEJB for the purpose of Report 
    * Search Criteria
    * @return java.util.Collection
    */
   public Collection displaySearchCriteria()throws AppException 
   {
	   Collection objCollection = null;
       objCollection = getReportLocal().displaySearchCriteria();
       return objCollection;  
   }
   
   /**
    * Calls corresponding Business method of ReportEJB for the purpose of Report 
    * List
    * @return java.util.Collection
    */
   public Collection displayReportList(String isbn,String pOrderNo,String printNo,int userId,int pagination,String itemType,String orderBy,String sort,String sdate,String edate)throws AppException 
   {
	   Collection objCollection=null;
	   objCollection=getReportLocal().displayReportList(isbn,pOrderNo,printNo,userId,pagination,itemType,orderBy,sort,sdate,edate);
	   return objCollection;
   }
}
