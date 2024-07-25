/**
 * 
 */
package com.pearson.pix.presentation.arptitlesetup.command;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import com.lowagie.text.Document;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.home.action.HomePageForm;
import com.pearson.pix.presentation.home.delegate.HomePageDelegate;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
//import com.pearson.pix.presentation.pdf.PotentialARPMillPdf;
//import com.pearson.pix.presentation.pdf.PotentialARPpdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderMillPdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderPdf;
import com.pearson.pix.presentation.arptitlesetup.action.PotentialARPForm;
import com.pearson.pix.presentation.arptitlesetup.delegate.PotentialARPDelegate;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Contains the implementation of methods for different commands raised by the 
 * user.
 * @author arvind.yadav
 */
public class PotentialARPCommand extends BaseCommand 
{
   
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PotentialARPCommand.class.getName());
    
   /**
    * Constructor for Initializing PotentialARPCommand
    */
   public PotentialARPCommand()   
   {
    
   }
   
   /**
    * Method for display of List screens. This method also takes care of filtering, 
    * pagination and history display by passing page no, filter criteria and its 
    * value, history flag respectively.
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   Collection objCollection = null;
	   User objUser=null;
	   String page="";
	   String roleType="";
	   String status ="";

	   int 	userId=0;
	   //added....
	   String idHidden="";
		if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
	   if(request.getSession().getAttribute("USER_INFO")!=null){  //Checking User Information for null
		   objUser = (User)request.getSession().getAttribute("USER_INFO");//Assigning User object with user Information 
		   userId = objUser.getUserId().intValue();
		   roleType=objUser.getRoleTypeDetail().getRoleType();
	   }
	  
	   if(request.getParameter("pageFilter")!=null)
	   {
		   page=request.getParameter("pageFilter");
	   }
	   else
	   {
		   page=request.getParameter("page"); //catching the page parameter
	   }
	   request.setAttribute("pageFilter", page);
	   
	   if(PIXUtil.checkNullField(page)==false){ //Handling the current page and history page 
		   page = "C";
	   }
	   String orderby=PIXConstants.PLAN_POSTED_DATE;//ordering the list data by posted date
	   String sort="DESC";
	   PotentialARPDelegate objPotentialARPDelegate = new PotentialARPDelegate();  
	   PotentialARP objPotentialARP= null;
	   String whereClause ="";
	   PotentialARPForm objpotentialARPForm = null;
	   
	   
	   int currentValue = 1; //setting variable for pagination
	   try
	   {
		   if(request.getSession().getAttribute("homeStatus")!=null)
		   {
			 request.getSession().removeAttribute("homeStatus");
		   }
		   if (form instanceof PotentialARPForm) //Through PotentialARP Page
		   {
			  
		   objpotentialARPForm = (PotentialARPForm)form;
		   objPotentialARP = objpotentialARPForm.getDetailArp();

		   if(request.getParameter("status")!=null)//status parameter is thrown when through Home Page Inbox
		   {
			   status=request.getParameter("status");
		   }
		   else
		   {
			   objpotentialARPForm.setStatus(status);
		   }
		   request.setAttribute("PATH", request.getContextPath());
		   
		   /**Code for handling filtering while using pagination--start*/  
		   /**The variables below will be passed as hidden along with the form*/  
		   String isbnFilter = request.getParameter("detailArp.potentialarpDetail.titleIsbn");
		   String authorFilter = request.getParameter("detailArp.potentialarpDetail.author");
		   String editionFilter = (String)objpotentialARPForm.getEdition();
		   String buyerFilter = request.getParameter("detailArp.potentialarpDetail.buyerName");
		   String copyrightFilter = (String)objpotentialARPForm.getCopyRightYear();
		   String statusFilter = (String)objpotentialARPForm.getStatus();  
		   String arpFromDueDateFilter=(String)objpotentialARPForm.getArpFromDueDate();
		   String arpToDueDateFilter=(String)objpotentialARPForm.getArpToDueDate();
		   String reqFromDateFilter=(String)objpotentialARPForm.getFromReqDate();
		   String reqToDateFilter=(String)objpotentialARPForm.getToReqDate();
		   Integer flipStatusFilter = (Integer)objpotentialARPForm.getStatusId();
		   
		  
		   if(PIXUtil.checkNullField(isbnFilter))
		   {
			   request.setAttribute("isbnFilter",isbnFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("isbnFilter")))
		   {
			   request.setAttribute("isbnFilter",request.getParameter("isbnFilter"));
			   isbnFilter = request.getParameter("isbnFilter");
			  
		   }
		   if(PIXUtil.checkNullField(authorFilter))
		   {
			   request.setAttribute("authorFilter",authorFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("authorFilter")))
		   {
			   request.setAttribute("authorFilter",request.getParameter("authorFilter"));
			   authorFilter = request.getParameter("authorFilter");
			  
		   }
		   if(PIXUtil.checkNullField(editionFilter))
		   {
			   request.setAttribute("editionFilter",editionFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("editionFilter")))
		   {
			   request.setAttribute("editionFilter",request.getParameter("editionFilter"));
			   editionFilter = request.getParameter("editionFilter");
		   }
		   if(PIXUtil.checkNullField(buyerFilter))
		   {
			   request.setAttribute("buyerFilter",buyerFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("buyerFilter")))
		   {
			   request.setAttribute("buyerFilter",request.getParameter("buyerFilter"));
			   buyerFilter = request.getParameter("buyerFilter");
			  
		   }
		   if(PIXUtil.checkNullField(copyrightFilter))
		   {
			   request.setAttribute("copyrightFilter",copyrightFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("copyrightFilter")))
		   {
			   request.setAttribute("copyrightFilter",request.getParameter("copyrightFilter"));
			   copyrightFilter = request.getParameter("copyrightFilter");
			  
		   }
		   if(PIXUtil.checkNullField(statusFilter))
		   {
			   request.setAttribute("statusFilter",statusFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
		   {
			   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
			   statusFilter = request.getParameter("statusFilter");
//			   objstatus.setStatusDescription(statusFilter);
		   }
		   if(PIXUtil.checkNullField(arpToDueDateFilter))
		   {
			   request.setAttribute("arpToDueDateFilter",arpToDueDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("arpToDueDateFilter")))
		   {
			   request.setAttribute("arpToDueDateFilter",request.getParameter("arpToDueDateFilter"));
			   arpToDueDateFilter = request.getParameter("arpToDueDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(arpFromDueDateFilter))
		   {
			   request.setAttribute("arpFromDueDateFilter",arpFromDueDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("arpFromDueDateFilter")))
		   {
			   request.setAttribute("arpFromDueDateFilter",request.getParameter("arpFromDueDateFilter"));
			   arpToDueDateFilter = request.getParameter("arpFromDueDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(reqFromDateFilter))
		   {
			   request.setAttribute("reqFromDateFilter",reqFromDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("reqFromDateFilter")))
		   {
			   request.setAttribute("reqFromDateFilter",request.getParameter("reqFromDateFilter"));
			   reqFromDateFilter = request.getParameter("reqFromDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(reqToDateFilter))
		   {
			   request.setAttribute("reqToDateFilter",reqToDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("reqToDateFilter")))
		   {
			   request.setAttribute("reqToDateFilter",request.getParameter("reqToDateFilter"));
			   reqToDateFilter = request.getParameter("reqToDateFilter");
		   }
		   if(flipStatusFilter!=null)  
		   {
			   request.setAttribute("flipStatusFilter",flipStatusFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("flipStatusFilter")))
		   {
			   request.setAttribute("flipStatusFilter",request.getParameter("flipStatusFilter"));
			   flipStatusFilter = Integer.parseInt(request.getParameter("flipStatusFilter"));
			  
		   }
   
		   whereClause = this.buildWhereClause(isbnFilter, authorFilter, editionFilter, buyerFilter, copyrightFilter, flipStatusFilter, arpFromDueDateFilter, arpToDueDateFilter, reqFromDateFilter, reqToDateFilter);
		   //System.err.println("WHERECLAUSE :: "+whereClause);
		   /**Code for handling filtering while using pagination--end*/  
		   currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
		   }
		   else if (form instanceof HomePageForm)//Through Home Page
		   {
			   String statusFilter="";
			   objpotentialARPForm = new PotentialARPForm();
			   HomePageForm homePageForm = (HomePageForm)form;
			   String isbnFilter = homePageForm.getIsbn();
			   String printNoFilter = homePageForm.getPrintno();
			   String ponoFilter =homePageForm.getPono() ;
			   Integer flipStatusFilter = homePageForm.getStatusId();
			   Status objStatus=new Status();
			   objPotentialARP = new PotentialARP();
			   if(request.getParameter("status")!=null)//status parameter is thrown when through Home Page Inbox
			   {
				   statusFilter=request.getParameter("status");//Setting the variable to be sent as hidden while pagination
				   status=request.getParameter("status");
				   objpotentialARPForm.setStatus(status);
			   }
			   else
			   {
				  statusFilter=homePageForm.getStatusDescription();
			   }
			   if(PIXUtil.checkNullField(statusFilter))
			   {
				   request.setAttribute("statusFilter",statusFilter);
				   request.getSession().setAttribute("homeStatus",statusFilter);
				   objStatus.setStatusDescription(statusFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
			   {
				   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
				   statusFilter = request.getParameter("statusFilter");
				   objStatus.setStatusDescription(statusFilter);
			   }
			   if(PIXUtil.checkNullField(isbnFilter))
			   {
				   request.setAttribute("isbnFilter",isbnFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("isbnFilter")))
			   {
				   request.setAttribute("isbnFilter",request.getParameter("isbnFilter"));
				   isbnFilter = request.getParameter("isbnFilter");
				  
			   }
			   if(PIXUtil.checkNullField(printNoFilter))
			   {
				   request.setAttribute("printNoFilter",printNoFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("printNoFilter")))
			   {
				   request.setAttribute("printNoFilter",request.getParameter("printNoFilter"));
				   printNoFilter = request.getParameter("printNoFilter");
				  
			   }
			   if(PIXUtil.checkNullField(ponoFilter))
			   {
				   request.setAttribute("ponoFilter",ponoFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("ponoFilter")))
			   {
				   request.setAttribute("ponoFilter",request.getParameter("ponoFilter"));
				   ponoFilter = request.getParameter("ponoFilter");
				  
			   }
			   if(flipStatusFilter!=null)
			   {  
				   request.setAttribute("flipStatusFilter",flipStatusFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("flipStatusFilter")))
			   {
				   request.setAttribute("flipStatusFilter",request.getParameter("flipStatusFilter"));
				   flipStatusFilter = Integer.parseInt(request.getParameter("flipStatusFilter"));
				  
			   }
               request.setAttribute("potentialARPForm", objpotentialARPForm);
			   request.setAttribute("PATH", request.getContextPath());
		   }
		   /*For handling session timeout for userId*/
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
			   objCollection = objPotentialARPDelegate.displayPotentialARPList(userId,roleType,page,currentValue,orderby,sort,whereClause,status);
		       int size = objCollection.size();
		       /* Pagination Handling*/
		       PIXUtil.getNextPage(request,currentValue,size);
		       PIXUtil.getPrevPage(request,currentValue);
		       if(size > PIXConstants.PAGE_SIZE)        
		       {           
		    	    objCollection.remove(((Vector)objCollection).get(size-1));
		       }   
		       objpotentialARPForm.setPotentialARPCollection(objCollection);
		       request.setAttribute("checkBoxIdList",idHidden);
		   }
		   return FrontEndConstants.LIST;
	   }
	   catch(AppException e)
		 {
		   log.debug("Error occurred while fetching PotentialARP List");
		   String errMsg = e.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
		 }
	   	
   }
   
   
   /**
    * Method for Accept/Reject operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   
   
   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
	   User objUser = null;  
	   String status = "";
	   String roleType="";
	   String finalStr ="";
	   String eventId="";
	   String titleIsbn="";
	   String vendorPageCount="";
	   String isReqProvided = "";
	   String isReviewReq ="";
	   String comments="";
	   String statusMsg = "";
	   String titleISBN ="";
	   String mode="";
	   
	   String homeUrl="/home/potentialarplist.do";
	   String url="/potentialarp/potentialarplist.do";
	   PotentialARPForm objpotentialARPForm = null;
	   String actionParam = request.getParameter("action");  
	   try
	   {

		   int userId=0;
			 if(request.getSession().getAttribute("USER_INFO")!=null){
				   objUser = (User)request.getSession().getAttribute("USER_INFO");
				   userId = objUser.getUserId().intValue();
			   }
		   objpotentialARPForm = (PotentialARPForm)form;
		   if(request.getParameter("status")!=null)//status parameter is thrown when through Home Page Inbox
		   {
			   status=request.getParameter("status");
			   objpotentialARPForm.setStatus(status);
		   }
		   else
		   {
			   objpotentialARPForm.setStatus(status);
		   }
		   
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   roleType=objUser.getRoleTypeDetail().getRoleType();
			   if("V".equals(roleType)){
				   homeUrl="/home/potentialarplist.do";
					   url="/potentialarp/potentialarplist.do";
			   }
		   }
		   if("accept".equalsIgnoreCase(actionParam)){
			   
		   	   eventId = request.getParameter("eventId");
		   	   titleIsbn = request.getParameter("titleIsbn");
		   	   vendorPageCount = request.getParameter("vendorPageCount");
		   	   isReqProvided = request.getParameter("isProvided");
		   	   isReviewReq = request.getParameter("isReviewReq");  
		   	   comments = request.getParameter("comments");
		   	   
		   	   PotentialARPDelegate potentialARPDelegate = new PotentialARPDelegate();
		   	   String errorMsg = potentialARPDelegate.validateTransaction(titleIsbn,eventId,vendorPageCount,isReqProvided,isReviewReq,comments,userId,actionParam);
		   	   statusMsg = errorMsg.substring((errorMsg.lastIndexOf("-")+1));
		   	   titleISBN = errorMsg.substring(0, errorMsg.lastIndexOf("-"));
		   	   objpotentialARPForm.getDetailArp().getPotentialarpDetail().setTitleIsbn(titleISBN);
			   objpotentialARPForm.setStatusMessage(statusMsg);
			   objpotentialARPForm.setMode(actionParam);
			   
			   String messageKey = "Selected Planned Order(s) have been successfully acknowledged.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
		       objpotentialARPForm.setResultsPopUp(false);
			   
			   return FrontEndConstants.POTENTIAL_STATUS_FORWARD;  
			   
		   }
           else if("reject".equalsIgnoreCase(actionParam)){
			   
		   	   eventId = request.getParameter("eventId");
		   	   titleIsbn = request.getParameter("titleIsbn");
		   	   vendorPageCount = request.getParameter("vendorPageCount");
		   	   isReqProvided = request.getParameter("isProvided");
		   	   isReviewReq = request.getParameter("isReviewReq");  
		   	   comments = request.getParameter("comments");
		   	   
		   	   PotentialARPDelegate potentialARPDelegate = new PotentialARPDelegate();
		   	   String errorMsg = potentialARPDelegate.validateTransaction(titleIsbn,eventId,vendorPageCount,isReqProvided,isReviewReq,comments,userId,actionParam);
		   	   
		   	   statusMsg = errorMsg.substring((errorMsg.lastIndexOf("-")+1));
		   	   titleISBN = errorMsg.substring(0, errorMsg.lastIndexOf("-"));
		   	   objpotentialARPForm.getDetailArp().getPotentialarpDetail().setTitleIsbn(titleISBN);
			   objpotentialARPForm.setStatusMessage(statusMsg);
			   objpotentialARPForm.setMode(actionParam);
			      
			   String messageKey = "Selected Planned Order(s) have been successfully acknowledged.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
		       objpotentialARPForm.setResultsPopUp(false);  
			   
			   return FrontEndConstants.POTENTIAL_STATUS_FORWARD;  
			   
		   }
		   else if("openPopup".equalsIgnoreCase((String)actionParam)){
			   if(request.getParameter("mode")!=null)//status parameter is thrown when through Home Page Inbox
			   {
				   mode=request.getParameter("mode");
			   }
			   if(request.getParameter("finalStr")!=null)
			   {
				   finalStr=request.getParameter("finalStr");
				   objpotentialARPForm.setFinalStr(finalStr);
				   objpotentialARPForm.setMode(mode);
				   objpotentialARPForm.setResultsPopUp(true);
			   }
			   return FrontEndConstants.POTENTIAL_RESULTPOPUP_FORWARD;
		   }
		   else
		   {
			   objpotentialARPForm.setResultsPopUp(false);
		   }	   
		   return "";
	   }
	   catch(AppException ae)
	   {  
		   log.debug("Error occured while accepting multiple plans");
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"PotentialARPCommand,executeGeneral",e);
		   String errMsg = ae.getSErrorDescription();
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
   /**This method is used to fetch the status information to be used in drop downs in Home page and module filters*/ 
   
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	  
	   try
	   {
		   User objUser=null;
		   int userId=0;
			 if(request.getSession().getAttribute("USER_INFO")!=null){
				   objUser = (User)request.getSession().getAttribute("USER_INFO");
				   userId = objUser.getUserId().intValue();
			   }
	     
		   //PotentialARPDelegate objPotentialARPDelegate = new PotentialARPDelegate();  
	       //Vector objAllStatus=(Vector)objPotentialARPDelegate.displayPotentialARPStatus();
	       //request.setAttribute("potentialARPAllStatus",objAllStatus);
	       String status =(String)request.getParameter("status");
	   
	    if((form instanceof HomePageForm)&&(request.getSession().getAttribute("USER_INFO")!=null))//If through Home page
	   {
		   
		   Vector objInboxdetail=new Vector();
		   HomePageDelegate objHomePageDelegate=new HomePageDelegate();
		   /*For handling session timeout in case of userId*/
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
		   	   objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
		   	   if(objInboxdetail.size()==0) //Checking condition for no records in inbox
				{
					request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
				}
		   }
	   
		   request.setAttribute("inboxdetails",objInboxdetail);
	   }
	   request.getSession().setAttribute("homeStatus",status);
	   request.setAttribute("PATH", request.getContextPath());    
	   return FrontEndConstants.RELATEDLIST;
	   
	 }
	   catch(AppException e)
		 {
		     log.debug("Error occurred while fetching potentialARP status");
		     String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
   }
   
   /**
    * Method for Update operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return String
    */
   /**This method is used for PotentialARP acknowledgement*/
   public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
  	{	
	   User objUser = null;
	   String login = "";
	   POLine poLine = null;
	   POParty poParty = null;
	   String partyComments = "";
	   String lineComments = "";
	   String roleType="" ;
	   String orderType="R" ;
	   try
	   {
		   if(request.getParameter("roleType")!=null){
			   roleType=request.getParameter("roleType");
			   orderType="P";
		   }
		   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
		   {
			   String page_value = request.getParameter("PAGE_VALUE");
			   String list_page ="?PAGE_VALUE="+page_value;
			   if(!PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")) & !PIXUtil.checkNullField(request.getParameter("sbBDateFilter")) & !PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
		       {
				   if(roleType.equals("M"))
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/potentialARP/millpotentialARPlist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				   }
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/potentialARP/potentialARPlist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				   }
		       }
		       else
		       {
		    		if(request.getParameter("isbnFilter")!="")
		    		{
		    			list_page += "&isbnFilter="+request.getParameter("isbnFilter");
		    		}
		    		if(request.getParameter("printNoFilter")!="")
		    		{
		    			list_page += "&printNoFilter="+request.getParameter("printNoFilter");
		    		}
		    		if(request.getParameter("statusFilter")!="")
		    		{
		    			list_page += "&statusFilter="+request.getParameter("statusFilter");
		    		}
		    		if(request.getParameter("startDateFilter")!="")
		    		{
		    			list_page += "&startDateFilter="+request.getParameter("startDateFilter");
		    		}
		    		if(request.getParameter("endDateFilter")!="")
		    		{
		    			list_page += "&endDateFilter="+request.getParameter("endDateFilter");
		    		}
		    		if(request.getParameter("sbBDateFilter")!="")
			    	{
			    		list_page += "&sbBDateFilter="+request.getParameter("sbBDateFilter");
			    	}
			    	if(request.getParameter("ebBDateFilter")!="")
			    	{
			    		list_page += "&ebBDateFilter="+request.getParameter("ebBDateFilter");
			    	}
		    		
			    	if(roleType.equals("M"))
					{
			    		request.setAttribute(PIXConstants.OK_URL,"/potentialARP/millpotentialARPlist.do"+list_page);	//For "Ok" button on Success/Error page
					}
			    	else
			    	{
			    		request.setAttribute(PIXConstants.OK_URL,"/potentialARP/potentialARPlist.do"+list_page);	//For "Ok" button on Success/Error page
			    	}
		      }
			   
		   }
		   else
		   {
			   if(roleType.equals("M"))
			   {
				   request.setAttribute(PIXConstants.OK_URL,"/potentialARP/millpotentialARPlist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page
			   }
			   else
			   {
				   request.setAttribute(PIXConstants.OK_URL,"/potentialARP/potentialARPlist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page
			   }
		   }
		   PurchaseOrderDetailForm objPurchaseOrderForm = (PurchaseOrderDetailForm)form;
		
		   
		   
		   POHeader poHeader = objPurchaseOrderForm.getPoHeader();
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   login = objUser.getLogin();
		   }
		   else{
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"PurchaseOrderCommand,executeInsert");
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
		   PotentialARPDelegate potentialARPDelegate = new PotentialARPDelegate();
		   poHeader.setModifiedBy(login);
		   for(int i=0;i<poHeader.getPartyCollection().size();i++){//iterating through the party collection
			   poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			   if(poParty.getPartyType().equals("V") ||poParty.getPartyType().equals("M"))//checking for party type
			   {
				   poParty.setModifiedBy(login);//setting the modified by field
				   partyComments = PIXUtil.escapeHTMLChars(poParty.getComments());
				   poParty.setComments(partyComments);
				   
			   }
		   }
		   int lineItemSize = poHeader.getLineItemCollection() == null ? 0 : poHeader.getLineItemCollection().size();
		   for(int i=0;i<lineItemSize;i++){//iterating the line items
			   	poLine = (POLine)((Vector)poHeader.getLineItemCollection()).get(i);
			   	poLine.setModifiedBy(login);//setting modified by field for line item
				lineComments = PIXUtil.escapeHTMLChars(poLine.getSupplierComments());
			   	poLine.setSupplierComments(lineComments);
		   }
		   poHeader.setOrderType(orderType);
		   //String poNumber = potentialARPDelegate.savePotentialARPAcknowledgement(poHeader);
		 //  String messagekey="PotentialARP No."+""+ poNumber +" "+"has been successfully acknowledged.";
		  // request.setAttribute(PIXConstants.SUCCESS_STRING,messagekey);	//For message on Success page
		   return FrontEndConstants.UPDATE;
			   
	      
		 }
//		 catch(AppException e)
//			{
//			log.debug("Error occurred in  potentialARP acknowledgement");
//			String errMsg = e.getSErrorDescription();
//			e.printStackTrace();
//			request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
//			return FrontEndConstants.ERROR;
//			}
		 catch(Exception e)  
		   {
			
			 AppException ae = new AppException();
			 ae.performErrorAction(Exceptions.EXCEPTION,"PotentialARPCommand,executeUpdate",e);
			 String errMsg = ae.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		   }	
  	}   
   
   
  /** 
   *  * Create where clause of sql based on search criteria entered by user that are present in Filter
   * @param carProjectDTO
   * @return
   */
  
  
  private String buildWhereClause(String isbn,String author,String edition,String buyerName,String copyRightYear,Integer status,String arpFromDueDate,String arpToDueDate,String fromReqDate,String toReqDueDate) {
     
	 boolean addANDClause = false;
     StringBuilder whereClause = new StringBuilder(1024);
     
     if (!PIXUtil.isNull(isbn)) {
        whereClause.append(addANDClause ? " AND " : " ");
        addANDClause = true;
        whereClause.append(" PALS.ISBN_10 like '" + isbn + "'");
     }
     if (!PIXUtil.isNull(author)) {
        whereClause.append(addANDClause ? " AND " : " ");
        addANDClause = true;
        whereClause.append(" PALS.AUTHOR like '%" + author+"%'");
     }
     if (!PIXUtil.isNull(edition)) {
         whereClause.append(addANDClause ? " AND " : " ");
         addANDClause = true;
         whereClause.append(" PALS.EDITION = '" + edition +"'");
      }
     if (!PIXUtil.isNull(buyerName)) {
         whereClause.append(addANDClause ? " AND " : " ");
         addANDClause = true;
         whereClause.append(" PALS.BUYER_NAME like '%" + buyerName+"%'" );
      }
     if (!PIXUtil.isNull(copyRightYear)) {
         whereClause.append(addANDClause ? " AND " : " ");
         addANDClause = true;
         whereClause.append(" PALS.COPYRIGHT_YEAR = '"+ copyRightYear+"'" );  
      }
     if (!PIXUtil.isNull(arpFromDueDate) && !PIXUtil.isNull(arpToDueDate)) {  
         whereClause.append(addANDClause ? " AND " : " ");
         addANDClause = true;
         whereClause.append(" TRUNC(PALS.ARP_EXPCTED_DATE) >= NVL(TO_DATE('" + arpFromDueDate+"','mm/dd/yyyy') , TRUNC(PALS.ARP_EXPCTED_DATE)) AND TRUNC(ARP_EXPCTED_DATE) <= NVL(TO_DATE('" + arpToDueDate+"','mm/dd/yyyy') , TRUNC(ARP_EXPCTED_DATE)) ");
      }
    
     if (!PIXUtil.isNull(fromReqDate) && !PIXUtil.isNull(toReqDueDate)) {
         whereClause.append(addANDClause ? " AND " : " ");
         addANDClause = true;
         whereClause.append(" TRUNC(PALS.INVENTORY_REQUSTED_DATE) >= NVL(TO_DATE('" + fromReqDate+"','mm/dd/yyyy') , TRUNC(PALS.INVENTORY_REQUSTED_DATE)) AND TRUNC(INVENTORY_REQUSTED_DATE) <= NVL(TO_DATE('" + toReqDueDate+"','mm/dd/yyyy') , TRUNC(INVENTORY_REQUSTED_DATE)) ");
      }
     if (status!=null && status!=0) {
         whereClause.append(addANDClause ? " AND " : " ");  
         addANDClause = true;
         whereClause.append(" PALS.STATUS_ID = "+ status );
      }  
     return whereClause.toString();
    }
    	
}
