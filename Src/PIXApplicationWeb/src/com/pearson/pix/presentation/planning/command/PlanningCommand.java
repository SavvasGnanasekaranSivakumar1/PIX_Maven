package com.pearson.pix.presentation.planning.command;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import com.lowagie.text.Document;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
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
import com.pearson.pix.presentation.pdf.PlanningMillPdf;
import com.pearson.pix.presentation.pdf.Planningpdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderMillPdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderPdf;
import com.pearson.pix.presentation.planning.action.PlanningForm;
import com.pearson.pix.presentation.planning.delegate.PlanningDelegate;
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
 * @author faisal
 */
public class PlanningCommand extends BaseCommand 
{
   
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PlanningCommand.class.getName());
    
   /**
    * Constructor for Initializing PlanningCommand
    */
   public PlanningCommand() 
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
	   int 	userId=0;
	   //added....
	   String idHidden="";
		if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
	   //....
		
	   if(request.getSession().getAttribute("USER_INFO")!=null){  //Checking User Information for null
		   objUser = (User)request.getSession().getAttribute("USER_INFO");//Assigning User object with user Information 
		   userId = objUser.getUserId().intValue();
		   roleType=objUser.getRoleTypeDetail().getRoleType();
	   }
	   String status=request.getParameter("status"); //catching the status parameter sent from the Homepage Inbox links
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
	   String poNumber=request.getParameter("ponumber");//catching the ponumber parameter 
	   BigDecimal poId=null;
	   String orderby=PIXConstants.PLAN_POSTED_DATE;//ordering the list data by posted date
	   String sort="DESC";
	   PlanningDelegate objPlanningDelegate = new PlanningDelegate();  
	   POHeader objpoHeader= null;
	   String endDate = null;
	   String startDate = null;
	   String sbBDate = null;
	   String ebBDate = null;
	   PlanningForm objplanningForm = null;
	   int currentValue = 1; //setting variable for pagination
	   try
	   {
		   if(request.getSession().getAttribute("homeStatus")!=null)
			 {
				 request.getSession().removeAttribute("homeStatus");
			 }
		   if (form instanceof PlanningForm) //Through Planning Page
		   {
			  
			   Status objstatus=new Status();
			   objplanningForm = (PlanningForm)form;
			   objpoHeader = objplanningForm.getPoHeader();
			   
		   if(PIXUtil.checkNullField(poNumber))//the Util method checks ponumber for null
		   {
			   objpoHeader.setPoNo(poNumber); 
		   }
		   if(request.getParameter("poid")!=null)//checking poid parameter for null
		   {
				   poId=BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));//parsing the string poid to a BigDecimal value  
				   objpoHeader.setPoId(poId); 
		   }
// If parameter status is not thrown the status is fetched through filter through Planning Form 
		   if((PIXUtil.checkNullField(status)==false))
			 {	 
		    	 objstatus.setStatusDescription(objplanningForm.getPoHeader().getStatusDetail().getStatusDescription());
			 }
			 else
			 {
				 objstatus.setStatusDescription(status);
			 }
	   request.setAttribute("PATH", request.getContextPath());
	   
	   /**Code for handling filtering while using pagination--start*/  
	   /**The variables below will be passed as hidden along with the form*/  
	   String isbnFilter = request.getParameter("poHeader.titleDetail.isbn10");
	   String printNoFilter = request.getParameter("poHeader.titleDetail.printNo");
	   String statusFilter = request.getParameter("poHeader.statusDetail.statusDescription");
	   String startDateFilter=(String)objplanningForm.getstartDate();
	   String endDateFilter=(String)objplanningForm.getendDate();
	   String sbBDateFilter=(String)objplanningForm.getsbBDate();
	   String ebBDateFilter=(String)objplanningForm.getebBDate();
	  
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
		   if(PIXUtil.checkNullField(statusFilter))
		   {
			   request.setAttribute("statusFilter",statusFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
		   {
			   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
			   statusFilter = request.getParameter("statusFilter");
			   objstatus.setStatusDescription(statusFilter);
		   }
		   
		   if(PIXUtil.checkNullField(startDateFilter))
		   {
			   request.setAttribute("startDateFilter",startDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("startDateFilter")))
		   {
			   request.setAttribute("startDateFilter",request.getParameter("startDateFilter"));
			   startDateFilter = request.getParameter("startDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(endDateFilter))
		   {
			   request.setAttribute("endDateFilter",endDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("endDateFilter")))
		   {
			   request.setAttribute("endDateFilter",request.getParameter("endDateFilter"));
			   endDateFilter = request.getParameter("endDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(sbBDateFilter))
		   {
			   request.setAttribute("sbBDateFilter",sbBDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("sbBDateFilter")))
		   {
			   request.setAttribute("sbBDateFilter",request.getParameter("sbBDateFilter"));
			   sbBDateFilter = request.getParameter("sbBDateFilter");
			  
		   }
		   if(PIXUtil.checkNullField(ebBDateFilter))
		   {
			   request.setAttribute("ebBDateFilter",ebBDateFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
		   {
			   request.setAttribute("ebBDateFilter",request.getParameter("ebBDateFilter"));
			   ebBDateFilter = request.getParameter("ebBDateFilter");
			  
		   }
			   TitlePrinting td = new TitlePrinting();
			   td.setIsbn10(isbnFilter);
			   td.setPrintNo(printNoFilter);
			   objpoHeader.setTitleDetail(td);
			   
			   startDate=startDateFilter;
			   endDate=endDateFilter;
			   sbBDate=sbBDateFilter;
			   ebBDate=ebBDateFilter;
			   /**Code for handling filtering while using pagination--end*/  
			   objpoHeader.setStatusDetail(objstatus);
		       currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
		   }
		   else if (form instanceof HomePageForm)//Through Home Page
		   {
			   String statusFilter="";
			   objplanningForm = new PlanningForm();
			   HomePageForm homePageForm = (HomePageForm)form;
			   String isbnFilter = homePageForm.getIsbn();
			   String printNoFilter = homePageForm.getPrintno();
			   String ponoFilter =homePageForm.getPono() ;
			   Status objStatus=new Status();
			   objpoHeader = new POHeader();
			   TitlePrinting td = new TitlePrinting();
			   td.setIsbn10(homePageForm.getIsbn());
			   td.setPrintNo(homePageForm.getPrintno());
			   objpoHeader.setTitleDetail(td);
			   if(request.getParameter("status")!=null)//status parameter is thrown when through Home Page Inbox
			   {
			   statusFilter=request.getParameter("status");//Setting the variable to be sent as hidden while pagination
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
			   objpoHeader.setPoNo(homePageForm.getPono());
			   objpoHeader.setStatusDetail(objStatus);
			   request.setAttribute("planningForm", objplanningForm);
			   request.setAttribute("PATH", request.getContextPath());
		   }
		   /*Change started by Shweta for handling session timeout for userId*/
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
			   objCollection = objPlanningDelegate.displayPlanningList(objpoHeader,startDate,endDate,userId,page,currentValue,orderby,sort,sbBDate,ebBDate,roleType);
		       int size = objCollection.size();
		       /* Pagination Handling*/
		       PIXUtil.getNextPage(request,currentValue,size);
		       PIXUtil.getPrevPage(request,currentValue);
		       if(size > PIXConstants.PAGE_SIZE)
		       {           
	          objCollection.remove(((Vector)objCollection).get(size-1));
		       }   
		       objplanningForm.setPlanningCollection(objCollection);
		       request.getSession().setAttribute("planCollection",objplanningForm.getPlanningCollection());
		       request.setAttribute("checkBoxIdList",idHidden);
		   }
		   /*Change end by Shweta*/
	       return FrontEndConstants.LIST;
	   }
	   catch(AppException e)
		 {
		   log.debug("Error occurred while fetching Planning List");
		   String errMsg = e.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
		 }
	   	
   }
   
   /**
    * Method for display of Detail screens
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   BigDecimal poId = null;
	   BigDecimal poVersion = null;
	   POHeader poHeaderTemp=null;
	   PurchaseOrderDetailForm poDetailForm=null;
	   POParty poParty=null;
	   String delDate=null;
	   Vector vecParty=new Vector();
	   String accessRight = "";
	   String reqDelDate = null;
	   String roleType="" ;
	   String orderType="R" ;
	   Vector vecLine = new Vector();
	   try
	   {  
		   poDetailForm =((PurchaseOrderDetailForm)form);
		   if(request.getParameter("roleType")!=null){
			   roleType=request.getParameter("roleType");
			   orderType="P";
		   }
           if(request.getParameter("poid")!=null) //checking poid and poversion for null values
			   poId = BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));
		   if(request.getParameter("poversion")!=null)
			   poVersion = BigDecimal.valueOf(Long.parseLong(request.getParameter("poversion")));
		  	   PlanningDelegate objPlanningDelegate = new PlanningDelegate(); 
		   poHeaderTemp=objPlanningDelegate.displayPlanningDetail(poId,poVersion,orderType);
		   Vector objAllStatus=(Vector)objPlanningDelegate.displayPlanningStatus();//getting planning status
	       request.setAttribute("planningAllStatus",objAllStatus);
	       poDetailForm.setPlanningAllStatus(objAllStatus);
		   if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))//Checking for Acces rights
		   {
			   accessRight = "WRITE";
		   }
		   else
		   {
			   accessRight = "READ";
		   }
		   
		   for(int i=0;i<poHeaderTemp.getPartyCollection().size();i++){
			   poParty = (POParty)((Vector)poHeaderTemp.getPartyCollection()).get(i);
			   
			   //checking for party type
			   if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE)||poParty.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE)) //For Buyer and Vendor
				{
				   delDate = poParty.getDeliveryDate();
				   delDate = PIXUtil.sqlToStandardDate(delDate);
				   poParty.setDeliveryDate(delDate);
				   vecParty.add(poParty);
				}
				
				else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))//For Ship To
				{
					vecParty.add(poParty);
				}
		   }
		   
		   
		   for(int i=0;i<poHeaderTemp.getLineItemCollection().size();i++){ //Iterating for the line items
			   	POLine poLine = (POLine)((Vector)poHeaderTemp.getLineItemCollection()).get(i);
			   	
			   	reqDelDate = poLine.getRequestedDeliveryDate();
			   	reqDelDate = PIXUtil.sqlToStandardDate(reqDelDate);
			   	poLine.setRequestedDeliveryDate(reqDelDate);
			   	vecLine.add(poLine);
		   }	
		   
		   poHeaderTemp.setLineItemCollection(vecLine);
		   poHeaderTemp.setPartyCollection(vecParty);
		   poDetailForm.setPoHeader(poHeaderTemp);
		   request.setAttribute("PlanningAccessRight",accessRight);
		   return FrontEndConstants.DISPLAY;  
	   
		   }
		   
	   catch(AppException ae)
	   {
		   log.debug("Error occurred while displaying Planning Detail");
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"PlanningCommand,executeDisplay",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   
   }
   
   /**
    * Method for Accept operation
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
	   String login = "";
	   String status = "";
	   String roleType="";
	   String homeUrl="/home/planninglist.do";
	   String url="/planning/planninglist.do";
	   try
	   {
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   roleType=objUser.getRoleTypeDetail().getRoleType();
			   if("M".equals(roleType)){
				   homeUrl="/home/millplanninglist.do";
					   url="/planning/millplanninglist.do";
			   }
			   login = objUser.getLogin();
		   }
		   
		   if("multipleplanningpoAccept".equals(actioncommand)) //Cheking for Multiple plan Acceptance through List page
		   {
			   if(request.getSession().getAttribute("homeStatus")!=null)
			   {		   
				   status = (String)request.getSession().getAttribute("homeStatus");
				   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
					{
						String page_value = request.getParameter("PAGE_VALUE");
						request.setAttribute(PIXConstants.OK_URL,homeUrl+"?page=I&status="+status+"&PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page called this url when coming from homepage
					}
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,homeUrl+"?page=I&status="+status+"&PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from homepage
				   }
				   request.getSession().removeAttribute("homeStatus");
			   }
			   else
			   {
				   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
					{
						String page_value = request.getParameter("PAGE_VALUE");
						String list_page ="?PAGE_VALUE="+page_value;
						   if(!PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")) & !PIXUtil.checkNullField(request.getParameter("sbBDateFilter")) & !PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
					       {
							   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
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
					    		log.info("list_page"+list_page);
					    		request.setAttribute(PIXConstants.OK_URL,url+list_page);	//For "Ok" button on Success/Error page
					      }
						   
					}
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from activelist
				   }
			   }
			   String strSelectedCheckboxes = request.getParameter("checksArr");
			   
			   PlanningDelegate planningDelegate = new PlanningDelegate();
			   planningDelegate.insertMultiplePlanningPOAccept(strSelectedCheckboxes,login);
			   String messageKey = "Selected Planned Order(s) have been successfully acknowledged.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
			   return "multipleplanningpoAccept";
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"PlanningCommand,executeGeneral",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	   
   }
   /**
    * Method for General purpose if any (Here used for Supplied Components of Purchase Order)
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
    return null;
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
	   
		   PlanningDelegate objPlanningDelegate = new PlanningDelegate();  
	       Vector objAllStatus=(Vector)objPlanningDelegate.displayPlanningStatus();
	       request.setAttribute("planningAllStatus",objAllStatus);
	   
	    if((form instanceof HomePageForm)&&(request.getSession().getAttribute("USER_INFO")!=null))//If through Home page
	   {
		   
		   Vector objInboxdetail=new Vector();
		   HomePageDelegate objHomePageDelegate=new HomePageDelegate();
		   /*Changed by Shweta for handling session timeout in case of userId*/
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
		   	   objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
		   	   if(objInboxdetail.size()==0) //Checking condition for no records in inbox
				{
					request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
				}
		   }
		   /*Changed End*/
	       request.setAttribute("inboxdetails",objInboxdetail);
	   }
	   request.setAttribute("PATH", request.getContextPath());    
	   return FrontEndConstants.RELATEDLIST;
	   
	 }
	   catch(AppException e)
		 {
		     log.debug("Error occurred while fetching planning status");
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
   /**This method is used for Planning acknowledgement*/
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
					   request.setAttribute(PIXConstants.OK_URL,"/planning/millplanninglist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				   }
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/planning/planninglist.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
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
			    		request.setAttribute(PIXConstants.OK_URL,"/planning/millplanninglist.do"+list_page);	//For "Ok" button on Success/Error page
					}
			    	else
			    	{
			    		request.setAttribute(PIXConstants.OK_URL,"/planning/planninglist.do"+list_page);	//For "Ok" button on Success/Error page
			    	}
		      }
			   
		   }
		   else
		   {
			   if(roleType.equals("M"))
			   {
				   request.setAttribute(PIXConstants.OK_URL,"/planning/millplanninglist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page
			   }
			   else
			   {
				   request.setAttribute(PIXConstants.OK_URL,"/planning/planninglist.do?PAGE_VALUE=1");	//For "Ok" button on Success/Error page
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
		   PlanningDelegate planningDelegate = new PlanningDelegate();
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
		   String poNumber = planningDelegate.savePlanningAcknowledgement(poHeader);
		   String messagekey="Planning No."+""+ poNumber +" "+"has been successfully acknowledged.";
		   request.setAttribute(PIXConstants.SUCCESS_STRING,messagekey);	//For message on Success page
		   return FrontEndConstants.UPDATE;
			   
	      
		 }
		 catch(AppException e)
			{
			log.debug("Error occurred in  planning acknowledgement");
			String errMsg = e.getSErrorDescription();
			e.printStackTrace();
			request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			return FrontEndConstants.ERROR;
			}
		 catch(Exception e)
		   {
			
			 AppException ae = new AppException();
			 ae.performErrorAction(Exceptions.EXCEPTION,"PlanningCommand,executeUpdate",e);
			 String errMsg = ae.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		   }	
  	}   
   
   /**
    * Method for General purpose if any (Here used for Executing pdf for Planning)
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
  public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
  {
	 
    /*try
     {
    	
    	
    	
    	
    	Document objDocument =null;
   	   HttpSession session=null;
   	   PurchaseOrderDetailForm poDetailForm=null;*/
   	  
   	  
   	   try
   		  {
   		 Document objDocument =null;
    	   HttpSession session=null;
    	   session = request.getSession();
    	   //PurchaseOrderDetailForm poDetailForm=null;
    	   PurchaseOrderDetailForm poDetailForm = ((PurchaseOrderDetailForm)form);
   		    
   		 /* PurchaseOrderPdf objPurchaseOrderPdf = new PurchaseOrderPdf();
   		    request.getSession().setAttribute("PDF_OBJECT",objPurchaseOrderPdf);
  		    request.getSession().setAttribute("PDF_Name",reportName);
  		    response.sendRedirect("../pdfFileDownloadServlet");*/
  		    
  		  if(poDetailForm.getPoHeader().getOrderType().equalsIgnoreCase("p"))
		   {
  			 String reportName="PlanningMill";
		   
 			    PlanningMillPdf objPlanningMillPdf = new PlanningMillPdf();
 			    request.getSession().setAttribute("PDF_OBJECT",objPlanningMillPdf);
 	  		    request.getSession().setAttribute("PDF_Name",reportName);
 	  		    response.sendRedirect("../pdfFileDownloadServlet");
		   }
		   else{
			    String reportName="Planning";
		   	    Planningpdf objPlanningpdf = new Planningpdf();
			    request.getSession().setAttribute("PDF_OBJECT",objPlanningpdf);
	  		    request.getSession().setAttribute("PDF_Name",reportName);
	  		    response.sendRedirect("../pdfFileDownloadServlet");
		   }
  		    
  		    
  		    
  		    
  		    
  		    
   			 
   		  }
   	   catch(Exception e)
   	   {
   		  AppException ae = new AppException();
 		   ae.performErrorAction(Exceptions.EXCEPTION,"ProcurementPlanCommand,executePDF",e);
 		   String errMsg = ae.getSErrorDescription();
 		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
   	   }
   		  return FrontEndConstants.EXPORTPDF;
    	
    	
  }  	
    	
    	
}
