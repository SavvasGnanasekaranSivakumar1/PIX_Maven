package com.pearson.pix.presentation.usage.command;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lowagie.text.Document;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.dto.usage.UsageLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.home.action.HomePageForm;
import com.pearson.pix.presentation.home.delegate.HomePageDelegate;
import com.pearson.pix.presentation.pdf.UsagePdf;
import com.pearson.pix.presentation.usage.action.UsageForm;
import com.pearson.pix.presentation.usage.action.UsageFormList;
import com.pearson.pix.presentation.usage.delegate.UsageDelegate;

/**
 * Contains the definitions of methods for different commands raised for Usage.
 * @author Dandu Thirupathi
 */
public class UsageCommand extends BaseCommand 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(UsageCommand.class.getName());
   
   /**
    * Constructor for Initializing UsageCommand
    */
   public UsageCommand() 
   {
    
   }
   
   /**
    * Method for display of List screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   Vector objVector = null;
	   User objUser=null;
	   Usage  objUsage=null;
	   int userId;
	   String poNumber=null;
	   String startDate=null;
	   String endDate=null;
	   int currentValue = 1;
	   String statusFilter=null;
	   Integer statusNo=null;
	   String page=request.getParameter("page");
	   if(PIXUtil.checkNullField(page) == false){
		   page = "C";
	   }
	   String orderby=PIXConstants.POSTED_DATE; 
	   String sort="DESC";
	   UsageFormList objUsageForm = null;
	   
	   
	   UsageDelegate objUsageDelegate = new UsageDelegate(); 
	   try
	   {
		   
		   if(request.getSession().getAttribute("USER_INFO")!=null){
	              objUser = (User)request.getSession().getAttribute("USER_INFO");
	                       
	            userId = objUser.getUserId().intValue();
	              }
	          else{
	              AppException ae = new AppException();
	              ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"OrderStatusCommand,executeList");
	              String errMsg = ae.getSErrorDescription();
	              request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	              return FrontEndConstants.ERROR;
	          }
		   
		   if(form instanceof UsageFormList)
		   {
			   objUsageForm = (UsageFormList)form;
			   objUsage = objUsageForm.getUsage();
		   if(objUsage==null)
		   {
			 objUsage = new Usage();
		   }
		   
		   Status objstatus=new Status();
		   request.setAttribute("PATH", request.getContextPath());
		   String ponoFilter=objUsage.getPoNo();
		   String isbnFilter=objUsage.getTitleDetail().getIsbn10();
		   String printNoFilter=objUsage.getTitleDetail().getPrintNo();
		   statusNo=objUsage.getStatusDetail().getStatusId();
		   if(statusNo != null){
			 statusFilter =statusNo.toString();
		   }
		   String startDateFilter=(String)objUsageForm.getStartDate();
		   String endDateFilter=(String)objUsageForm.getEndDate();
		   currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
		  
		   if(PIXUtil.checkNullField(ponoFilter))
		   {
			   request.setAttribute("ponoFilter",ponoFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("ponoFilter")))
		   {
			   request.setAttribute("ponoFilter",request.getParameter("ponoFilter"));
			   ponoFilter = request.getParameter("ponoFilter");
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
		   if(PIXUtil.checkNullField(statusFilter))
		   {
			   request.setAttribute("statusFilter",statusFilter);
			   objstatus.setStatusId(new Integer(statusFilter));
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
		   {
			   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
			   statusFilter = request.getParameter("statusFilter");
			   objstatus.setStatusId(new Integer(statusFilter));
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
		   if(PIXUtil.checkNullField(request.getParameter("poId")))
		   {
			   BigDecimal poid= new BigDecimal(request.getParameter("poId"));
			   objUsage.setPoId(poid);
		   }
		   if(page.equals("H"))
		   {
			 ponoFilter= request.getParameter("pno");
			 orderby="13";
		   } 
		   TitlePrinting td = new TitlePrinting();
		   td.setIsbn10(isbnFilter);
		   td.setPrintNo(printNoFilter);
		   objUsage.setTitleDetail(td); 
		   objUsage.setStatusDetail(objstatus);
		   startDate=startDateFilter;
		   endDate=endDateFilter;
		   poNumber= ponoFilter;
		   }
		    
		   
		   else if (form instanceof HomePageForm)
		   {
			   //String statusFilter="";
			   objUsageForm = new UsageFormList();
			   HomePageForm homePageForm = (HomePageForm)form;
			   objUsage = new Usage();
			   String isbnFilter = homePageForm.getIsbn();
			   String printNoFilter = homePageForm.getPrintno();
			   String ponoFilter =homePageForm.getPono() ;
			   TitlePrinting td = new TitlePrinting();
			   td.setIsbn10(homePageForm.getIsbn());
			   td.setPrintNo(homePageForm.getPrintno());
			   objUsage.setTitleDetail(td);
			   objUsage.setPoNo(homePageForm.getPono());
			   objUsage.setJobNo(homePageForm.getJobno());
			    Status objStatus=new Status();
			   
			   if(request.getParameter("status")!=null)
			   {
			   statusFilter=request.getParameter("status");
			   }
			   else
			   {
				   statusNo=homePageForm.getStatusId();
				   if(statusNo != null){
					 statusFilter =statusNo.toString();
				   }
				  //statusFilter=homePageForm.getStatusId();
			   }
			   		   
			   if(PIXUtil.checkNullField(statusFilter))
			   {
				   request.setAttribute("statusFilter",statusFilter);
				   objStatus.setStatusId(new Integer(statusFilter));
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
			   {
				   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
				   statusFilter = request.getParameter("statusFilter");
				   objStatus.setStatusId(new Integer(statusFilter));
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
			   
			   objUsage.setStatusDetail(objStatus);
			   request.setAttribute("usageFormList", objUsageForm);
			   request.setAttribute("PATH", request.getContextPath());
		   }
		 
		   /*Added by Shweta for session timeout problem */
		   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
		   {
		   
			   objVector = (Vector)objUsageDelegate.displayUsageList(objUsage,startDate,endDate,poNumber,userId,page,currentValue,orderby,sort);
			   int size = objVector.size();
			   PIXUtil.getNextPage(request,currentValue,size);
			   PIXUtil.getPrevPage(request,currentValue);
			   if(size > PIXConstants.PAGE_SIZE)
			   {           
				   objVector.remove(((Vector)objVector).get(size-1));
			   }   
			   objUsageForm.setUsageCollection(objVector);
		   }
		   /*Change End*/
		   return FrontEndConstants.LIST;
	   	}
	   catch(AppException e)
	   {
			 String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"UsageCommand,executeList",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	
   }
   
   /**
    * Method for Display Usage Status list
    * @param mapping
    * @param form
    * @param request
    * @param reponse
    * @param messages
    * @return String
    */
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
	   try
	   {
		   User objUser=null;
			 int userId=0;
			 if(request.getSession().getAttribute("USER_INFO")!=null){
				   objUser = (User)request.getSession().getAttribute("USER_INFO");
				   userId = objUser.getUserId().intValue();
			   }
		   UsageDelegate objUsageDelegate = new UsageDelegate();
		   Vector objUsageStatus = (Vector)objUsageDelegate.displayUsageStatus();
		   request.setAttribute("UsageStatus",objUsageStatus);
		   
		   if((form instanceof HomePageForm)&&(request.getSession().getAttribute("USER_INFO")!=null))//If through Home page
		   {
			   
			   Vector objInboxdetail=new Vector();
			   HomePageDelegate objHomePageDelegate=new HomePageDelegate();
			   /*Change done by shweta for session handling*/
			   if(request.getSession().getAttribute("USER_INFO")!=null)//to check null value for user info
			   {
				   	   objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
				   	if(objInboxdetail.size()==0) //Checking condition for no records in inbox
					{
						request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
					}
			   }
			   /*Change end by shweta for session handling*/
		       request.setAttribute("inboxdetails",objInboxdetail);
		   }
		   request.setAttribute("PATH", request.getContextPath()); 
		   return FrontEndConstants.RELATEDLIST;
	   }
	   catch(AppException e)
	   {
			 String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"UsageCommand,relatedList",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	   
   }
     
   /**
    * Method for display of Detail screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   Vector vecParty = null;
	   Integer poId = null;
	   Integer poVersion = null;
	   POParty poParty = null;
	   String delDate = null;
	   UsageForm usageForm = null;
	   POHeader poHeader = null;
	   HashMap usageHashMap=null;
	   Usage objUsage= new Usage();
	   UsageLine objUsageLine=null;
	   String objCommnets=null;
	   Integer uId=null;
	   Vector objUsageLineVec=null;
	   try
	   {
		   vecParty = new Vector();
		   usageForm = (UsageForm)form;
		     
		   if(request.getParameter("poid")!=null)
			   poId = Integer.valueOf(request.getParameter("poid"));
		   if(request.getParameter("poversion")!=null)
			   poVersion = Integer.valueOf(request.getParameter("poversion"));
		   if(request.getParameter("uid") != null )
			   uId = Integer.valueOf(request.getParameter("uid"));
		   		   
		   UsageDelegate usageDelegate = new UsageDelegate();
		   usageHashMap = usageDelegate.displayUsageDetail(poId,poVersion,uId);
		   poHeader= (POHeader)usageHashMap.get("PoHeader");
		   objUsage = (Usage)usageHashMap.get("UsageLineList");
		   objUsageLineVec=new Vector();
		   int lineSize = objUsage.getUsageLineCollection() == null ? 0 : objUsage.getUsageLineCollection().size();
		   for(int i=0;i< lineSize;i++){
			   objUsageLine = (UsageLine)((Vector)objUsage.getUsageLineCollection()).get(i);
			   objCommnets = PIXUtil.unEscapeHTMLChars(objUsageLine.getComments());
			   objUsageLine.setComments(objCommnets);
			   objUsageLineVec.add(objUsageLine);
		   }
		   objUsage.setUsageLineCollection(objUsageLineVec);
		   usageForm.setUsage(objUsage);
		   int size = poHeader.getPartyCollection() == null ? 0 : poHeader.getPartyCollection().size();
		   for(int i=0;i<size;i++){
			   	poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			   
				if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE) || poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
				{
					delDate = poParty.getDeliveryDate();
					delDate = PIXUtil.sqlToStandardDate(delDate);
					poParty.setDeliveryDate(delDate);
					vecParty.add(poParty);
				}
				else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
				{
					vecParty.add(poParty);
				}
				
		   }
		   
		   poHeader.setPartyCollection(vecParty);
		   usageForm.setPoHeader(poHeader);
		   		   
		   return FrontEndConstants.DISPLAY;
	   }
	   catch(AppException ae)
	   {
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"UsageCommand,executeDisplay",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	  
   }
   
   /**
    * Method for Insert operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return String
    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   UsageForm objUsageForm=(UsageForm)form; 
	   Usage objUsage=new Usage();
	   User objUser=null;
	   String login = "";
	   String usageNo=null;
	   Integer objPoId=null;
	   Integer objPoVersion =null;
	   String objpono=null;
	   Integer objrno=null;
	   try
		 {    
		   if(request.getParameter("poid")!=null)
			   objPoId = Integer.valueOf(request.getParameter("poid"));
		   if(request.getParameter("poversion")!=null)
			   objPoVersion = Integer.valueOf(request.getParameter("poversion"));
		   if(request.getParameter("pono")!=null)
			   objpono = (String)request.getParameter("pono");
		   if(request.getParameter("rno")!=null)
			   objrno = Integer.valueOf(request.getParameter("rno"));
		   	
		   if(request.getSession().getAttribute("USER_INFO")!=null){
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                login = objUser.getLogin();
                }
            else{
                AppException ae = new AppException();
                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"OrderStatusCommand,executeInsert");
                String errMsg = ae.getSErrorDescription();
                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
                return FrontEndConstants.ERROR;
            }
		    	    
		    if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
		    {
		    	String page_value = request.getParameter("PAGE_VALUE");
		    	String list_page ="?PAGE_VALUE="+page_value;
		    	if(!PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
		    	{
		    		request.setAttribute(PIXConstants.OK_URL,"/usage/usageList.do?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
		    	}
		    	else
		    	{
		    		if(request.getParameter("ponoFilter")!="")
		    		{
		    			list_page += "&ponoFilter="+request.getParameter("ponoFilter");
		    		}
		    		if(request.getParameter("isbnFilter")!="")
		    		{
		    			list_page += "&isbnFilter="+request.getParameter("isbnFilter");
		    		}
		    		if(request.getParameter("printNoFilter")!="")
		    		{
		    			list_page += "&printNoFilter="+request.getParameter("printNoFilter");
		    		}
		    		if(request.getParameter("startDateFilter")!="")
		    		{
		    			list_page += "&startDateFilter="+request.getParameter("startDateFilter");
		    		}
		    		if(request.getParameter("endDateFilter")!="")
		    		{
		    			list_page += "&endDateFilter="+request.getParameter("endDateFilter");
		    		}
		    		request.setAttribute(PIXConstants.OK_URL,"/usage/usageList.do"+list_page);	//For "Ok" button on Success/Error page
		    	}
		    }
		    else
		    {
		   		request.setAttribute(PIXConstants.OK_URL,"/usage/usageList.do?PAGE_VALUE=1");
		    }	
		   	objUsage = objUsageForm.getUsage();
		   	String objComments=null;
		   //	Vector objVec= (Vector)objUsage.getUsageLineCollection();
		   	int lineSize = objUsage.getUsageLineCollection() == null ? 0 : objUsage.getUsageLineCollection().size();
			   for(int i=0;i<lineSize;i++){
				   objComments  = objUsage.getUsageLineItemCollectionIdx(i).getComments();
				   objComments=  PIXUtil.escapeHTMLChars(objComments);
				   objUsage.getUsageLineItemCollectionIdx(i).setComments(objComments);
			   }
			   
		   	UsageDelegate usageDelegate = new UsageDelegate();
		   	usageNo = usageDelegate.saveUsage(objUsage,objPoId,objPoVersion,objpono,objrno,login);
		   	String messageKey = "Usage No "+usageNo+" created successfully.";
			request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
			return FrontEndConstants.INSERT;
		 } 
		 catch(AppException e)
		 {
			 String errMsg = e.getSErrorDescription();
	         request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		 {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"OrderStatusCommand,executeInsert",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		 }
   }
   
   /**
    * Method for General purpose if any
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeGeneral(final String actioncommand,final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
	   Vector vecParty = null;
	   Integer poId = null;
	   Integer poVersion = null;
	   POParty poParty = null;
	   String delDate = null;
	   UsageForm usageForm = null;
	   POHeader poHeader = null;
	   HashMap usageHashMap=null;
	   Usage objUsage=new Usage();
	   Integer uId=null;
	   
	   try
	   {
		   vecParty = new Vector();
		   usageForm = (UsageForm)form;
		     
		   if(request.getParameter("poid")!=null)
			   poId=Integer.valueOf(request.getParameter("poid"));
		   if(request.getParameter("poversion")!=null)
			   poVersion=Integer.valueOf(request.getParameter("poversion"));
		   if(PIXUtil.checkNullField(request.getParameter("uid"))){
			  uId = Integer.valueOf(request.getParameter("uid"));
		   }
		   		   
		   UsageDelegate usageDelegate = new UsageDelegate();
		   usageHashMap = usageDelegate.displayUsageDetail(poId,poVersion,uId);
		   poHeader= (POHeader)usageHashMap.get("PoHeader");
		   objUsage = (Usage)usageHashMap.get("UsageLineList");
		   usageForm.setUsage(objUsage);
		   int size = poHeader.getPartyCollection() == null ? 0 : poHeader.getPartyCollection().size();
		   for(int i=0;i<size;i++){
			   	poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			   
				if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE) || poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE) )
				{
					delDate = poParty.getDeliveryDate();
					delDate = PIXUtil.sqlToStandardDate(delDate);
					poParty.setDeliveryDate(delDate);
					vecParty.add(poParty);
				}
				else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
				{
					vecParty.add(poParty);
				}
				
		   }
		   
		   poHeader.setPartyCollection(vecParty);
		   usageForm.setPoHeader(poHeader);
		   		   
		   return "relatedListNew";
	   }
	   catch(AppException ae)
	   {
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"UsageCommand,executeDisplay",te);
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
  public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
  {
     Document objDocument = null;
     String reportName="Usage";
     try
     {
  	    UsagePdf objUsagePdf = new UsagePdf();
  	  request.getSession().setAttribute("PDF_OBJECT",objUsagePdf);
 	    request.getSession().setAttribute("PDF_Name",reportName);
 	    response.sendRedirect("../pdfFileDownloadServlet");
  		/*objDocument = objUsagePdf.display(request,response);
  		objDocument.close();*/		 
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
