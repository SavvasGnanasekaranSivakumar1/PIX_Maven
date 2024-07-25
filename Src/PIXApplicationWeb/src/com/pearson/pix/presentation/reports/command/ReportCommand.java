package com.pearson.pix.presentation.reports.command;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.reports.action.ReportForm;
import com.pearson.pix.presentation.reports.delegate.ReportDelegate;

/**
 * @author sudam.sahu
 */
/**
 * Contains the definitions of methods for different commands raised for 
 * ReportCommand.
 */
public class ReportCommand extends BaseCommand 
{
	/**
     * Logger.
     */
	
	private static Log log = LogFactory.getLog(ReportCommand.class.getName());
   /**
    * Constructor for Initializing ReportCommand
    */
   public ReportCommand() 
   {
    
   }
   
   /**
    * Method for display of Report Search screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   String orderBy=PIXConstants.POSTED_DATE;//sets the display of detail page ordr by date
	   String sort="DESC";//sets the sorting value of detail page into descending order
	   int pagination=1;
	   User objUser = null;
	   int userId=0;
	   if(request.getSession().getAttribute("USER_INFO")!=null)//to check the null value of user info
	   {
		   objUser = (User)request.getSession().getAttribute("USER_INFO");
		   userId = objUser.getUserId().intValue();//to get the user id from login page
	   }
	  try
	  {
	   Collection objCollection=null;
	   ReportForm objReportForm=(ReportForm)form;
	   ReportDelegate  objReportDelegate=new ReportDelegate();
	   String itemType=objReportForm.getReference().getRefCode();
	   if(itemType=="ALL")//to check the item type to all from the form
	   {
		   itemType="ALL";
	   }
	   String isbn=objReportForm.getIsbn10();
	   String pOrderNo=objReportForm.getPorderNo();
	   String printNo=objReportForm.getPrintNo();
	   
	   String sdate=objReportForm.getstartDate();
	   String edate=objReportForm.getendDate();
	   request.getSession().setAttribute("sdate",sdate);
	   request.getSession().setAttribute("edate",edate);
	   
	   objReportForm.setItem(itemType);
	   objReportForm.setPorderNo(pOrderNo);
	   objReportForm.setPrintNo(printNo);
	   objCollection=objReportDelegate.displayReportList(isbn,pOrderNo,printNo,userId,pagination,itemType,orderBy,sort,sdate,edate);
	   objReportForm.setReportCollection(objCollection);
	   return FrontEndConstants.LIST;
	  }
	  catch(AppException e)
		 {
			  String errMsg = e.getSErrorDescription();
			  log.info("The Error Mesage is = " +errMsg);
			  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			  return FrontEndConstants.ERROR;
		 }
	  catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"ReportCommand,executeList",e);
		   String errMsg = ae.getSErrorDescription();
		   log.info("The Error Mesage is = " +errMsg);
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	  
   }
   
   /**
    * This should be overridden by subclasses.
    * The subclass's executeRelatedLists() is called before any other executeXXXX() 
    * by BaseAction to ensure required lists of related data are available in some 
    * context to the JSPs when each page is called.
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param reponse
    * @param messages
    */
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse reponse, final ActionMessages messages) 
   {
	   	  try
	   	  {
	   		  Collection objCollection=null;
	   		  ReportDelegate objReportDelegate = new ReportDelegate();  
	          objCollection=objReportDelegate.displaySearchCriteria();
	          request.setAttribute("ReportItemName",objCollection);
	          return FrontEndConstants.RELATEDLIST;
	   	  }
	   	catch(AppException e)
		   {
				  String errMsg = e.getSErrorDescription();
				  log.info("The Error Mesage is = " +errMsg);
				  request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				  return FrontEndConstants.ERROR;
		   }
	   	catch(Exception e)
		   {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"ReportCommand,executeRelatedList",e);
			   String errMsg = ae.getSErrorDescription();
			   log.info("The Error Mesage is = " +errMsg);
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }	
   }
	   	
   
}
