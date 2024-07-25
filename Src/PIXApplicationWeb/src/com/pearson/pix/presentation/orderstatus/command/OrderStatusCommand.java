package com.pearson.pix.presentation.orderstatus.command;

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
import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.dto.orderstatus.OrderStatusLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.orderstatus.action.OrderStatusForm;
import com.pearson.pix.presentation.orderstatus.delegate.OrderStatusDelegate;
import com.pearson.pix.presentation.pdf.OrderStatusPdf;

/**
 * Contains the definitions of methods for different commands raised for Order Status.
 * @author Dandu Thirupathi
 */
public class OrderStatusCommand extends BaseCommand 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(OrderStatusCommand.class.getName());
   
   /**
    * Constructor for Initializing OrderStatusCommand
    */
   public OrderStatusCommand() {
    
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
	   Integer objPoId = null;  
	   Integer objPoVersion = null;
	   int currentValue=0;
       //	 added....
	   String idHidden="";
		if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
	   //....
	     OrderStatusForm objOrderStatusForm=(OrderStatusForm)form;
	     
		 try
		 {    
			 if(request.getParameter("poid")!=null)
				 objPoId = Integer.valueOf(request.getParameter("poid"));
			 if(request.getParameter("poversion")!=null)
				 objPoVersion = Integer.valueOf(request.getParameter("poversion"));
			 String objorderby =PIXConstants.CREATION_DATE_TIME; 
			 String objsort = "DESC";
			 log.debug("Order Status Command executeList before calling Delegate ");
			 OrderStatusDelegate orderStatusDelgate=new OrderStatusDelegate();
			 if(request.getParameter(PIXConstants.PAGE_VALUE)!=null)
				 currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
			 Integer objPagination=new Integer(currentValue);
			// invoking delegate displayOrderStatusList method with passing poid,poversion,pagination,oderby and sort values  
			 Vector objVector = orderStatusDelgate.displayOrderStatusList(objPoId,objPoVersion,objPagination,objorderby,objsort);
			 int size = objVector.size();
			 PIXUtil.getNextPage(request,currentValue,size);
			 PIXUtil.getPrevPage(request,currentValue);
					
			 if(size > PIXConstants.PAGE_SIZE)
			 { 	   
				 objVector.remove(((Vector)objVector).get(size-1));
			 }   
			//	setting collection of orderstatus line items to the form 
			 objOrderStatusForm.setOrderStatusCollection(objVector);
			 
			 
		 } 
		 catch(AppException e)
		 {
			   String errMsg = e.getSErrorDescription();
			   log.debug("In AppException ");	   
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		 {
			   AppException ae = new AppException();
			   log.debug("In Exception ");
			   ae.performErrorAction(Exceptions.EXCEPTION,"OrderStatusCommand,executeList",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		 }
		 request.setAttribute("checkBoxIdList",idHidden);
		 return FrontEndConstants.LIST;
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
	   Integer objPoId=null;
	   Integer objPoVersion=null;
	   String objStatusId=null;
	   String objStatusNo=null;
	   String poNo = null;
	   String releaseNo = null;
	   String order=null;
	   OrderStatusLine objOrderStatusLine=null;
	   String objCommnets=null;
	   Vector objUsageLineVec=null;
	   OrderStatusForm objOrderStatusForm=(OrderStatusForm)form; 
		 try
		 {  
			 if(request.getParameter("poid")!=null)
				  objPoId = Integer.valueOf(request.getParameter("poid"));
			 if(request.getParameter("poversion")!=null)
				  objPoVersion = Integer.valueOf(request.getParameter("poversion"));
			 if(request.getParameter("pono")!=null)
				 poNo = request.getParameter("pono");
			 if(request.getParameter("rno")!=null)
				 releaseNo = request.getParameter("rno");
			 if(request.getParameter("OS_ID")!=null)
			 {				 
				 objStatusId=(String)request.getParameter("OS_ID");			 
			 }
			 if(request.getParameter("OS_NO")!=null)
			 {				 
				 objStatusNo=(String)request.getParameter("OS_NO");			 
				 request.setAttribute("StatusNo",objStatusNo);
			 }
			 if(request.getParameter("order")!=null)
			 {				 
				 order=(String)request.getParameter("order");			 
				 request.setAttribute("order",order);
			 }
			 
	   
			 OrderStatusDelegate orderStatusDelgate=new OrderStatusDelegate();
			 Vector objVectorDisplay = (Vector)orderStatusDelgate.displayOrderStatusDetail(objPoId,objPoVersion,objStatusId);
			 //displaying single quotes
			 objUsageLineVec=new Vector();
			   int lineSize = objVectorDisplay == null ? 0 : objVectorDisplay.size();
			   for(int i=0;i< lineSize;i++){
				   objOrderStatusLine = (OrderStatusLine)objVectorDisplay.get(i);
				   objCommnets = PIXUtil.unEscapeHTMLChars(objOrderStatusLine.getComments());
				   objOrderStatusLine.setComments(objCommnets);
				   objUsageLineVec.add(objOrderStatusLine);
			   }
			 
             //	setting collection of orderstatus line items to the form
			 objOrderStatusForm.setOrderStatusCollection(objUsageLineVec);
			 objOrderStatusForm.setPoNo(poNo);
			 objOrderStatusForm.setReleaseNo(releaseNo);
			 objOrderStatusForm.setStatusNo(objStatusNo);
			 
		 } 
		 catch(AppException e)
		 {
			   String errMsg = e.getSErrorDescription();
			   log.debug("In AppException ");	   
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		 {
			   AppException ae = new AppException();
			   log.debug("In Exception ");
			   ae.performErrorAction(Exceptions.EXCEPTION,"OrderStatusCommand,executeDisplay",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		 }
		 return FrontEndConstants.DISPLAY;
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
	   OrderStatusForm objOrderStatusForm=(OrderStatusForm)form; 
	   OrderStatus objOrderStatus=new OrderStatus();
	   User objUser=null;
	   String login = "";
	   String orderStatusNo=null;
	   Integer objPoId = null;  
	   Integer objPoVersion = null;  
	   String objpono = null;  
	   Integer objrno= null; 
	   String objComments=null;
	   try
		 {    
		   if(request.getParameter("poid")!=null)
				  objPoId = Integer.valueOf(request.getParameter("poid"));
		   if(request.getParameter("poversion")!=null)
				  objPoVersion = Integer.valueOf(request.getParameter("poversion"));
		   if(request.getParameter("pono")!=null)
			   objpono = request.getParameter("pono");
		   if(request.getParameter("rno")!=null)
			   objrno = Integer.valueOf(request.getParameter("rno"));
		   // catching the order parameter
		   String order = request.getParameter("order");
		   //catching the page order list parameter
		   String pageOrderList = request.getParameter("page_order_list");	   	
		   	if(request.getSession().getAttribute("USER_INFO")!=null){
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                login = objUser.getLogin();
                }
            else{
                AppException ae = new AppException();
                log.debug("Error occered while Accessing user information ");
                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"OrderStatusCommand,executeInsert");
                String errMsg = ae.getSErrorDescription();
                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
                return FrontEndConstants.ERROR;
             }
		   	 //For "Ok" button on Success/Error page
		   	 	String list_page = "?PAGE_VALUE=1&poid="+objPoId+"&poversion="+objPoVersion+"&pono="+objpono+"&rno="+objrno+"&order="+order+"&page_order_list="+pageOrderList;
				if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")) & !PIXUtil.checkNullField(request.getParameter("sbBDateFilter")) & !PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
			    {
					request.setAttribute(PIXConstants.OK_URL,"/orderStatus/orderStatusList.do?PAGE_VALUE=1&poid="+objPoId+"&poversion="+objPoVersion+"&pono="+objpono+"&rno="+objrno+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
			    }
			    else
			    {
			    	if(request.getParameter("pageFilter")!="")
			    	{
			    		list_page += "&pageFilter="+request.getParameter("pageFilter");
			    	}
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
			    	request.setAttribute(PIXConstants.OK_URL,"/orderStatus/orderStatusList.do"+list_page+"&party=V");	//For "Ok" button on Success/Error page
			      }
		   	 
			 objOrderStatus = objOrderStatusForm.getOrderStatus();
			 //decoding single quotes
			 int lineSize = objOrderStatus.getLineItemCollection() == null ? 0 : objOrderStatus.getLineItemCollection().size();
			   for(int i=0;i<lineSize;i++){
				   objComments  = objOrderStatus.getLineItemCollectionIdx(i).getComments();
				   objComments=  PIXUtil.escapeHTMLChars(objComments);
				   objOrderStatus.getLineItemCollectionIdx(i).setComments(objComments);
			   }
			 
			 OrderStatusDelegate orderStatusDelgate=new OrderStatusDelegate();
			 orderStatusNo = orderStatusDelgate.saveOrderStatus(objOrderStatus,objPoId,objPoVersion,objpono,objrno,login);
			 //	message on Success of Status No 
			 log.info("In OS Command inserted orderStatusNo ::"+orderStatusNo);
			 String messageKey = "Status No "+orderStatusNo+" created successfully.";
			 log.info("In OS Command messageKey ::"+messageKey);
			 request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
		 
		 } 
		 catch(AppException e)
		 {
			   String errMsg = e.getSErrorDescription();
			   log.debug("In AppException ");		   
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		 {
			   AppException ae = new AppException();
			   log.debug("In Exception ");
			   ae.performErrorAction(Exceptions.EXCEPTION,"OrderStatusCommand,executeInsert",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		 }
		 return FrontEndConstants.INSERT;
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
	   HashMap objHashMap=null;
	   Integer objPoId=null;
	   Integer objPoVersion=null;
	   OrderStatusForm objOrderStatusForm=(OrderStatusForm)form; 
	   OrderStatus orderStatus=new OrderStatus();
		 try
		 {    
			 if(request.getParameter("poid")!=null)
				  objPoId = Integer.valueOf(request.getParameter("poid"));
			 if(request.getParameter("poversion")!=null)
				  objPoVersion = Integer.valueOf(request.getParameter("poversion"));
			 
			 OrderStatusDelegate orderStatusDelgate=new OrderStatusDelegate();
			 objHashMap = (HashMap)orderStatusDelgate.displayBasicOrderStatusInfo(objPoId,objPoVersion);
			 Vector objVectorDisplay=(Vector)objHashMap.get("ListDisplay");
			 orderStatus.setLineItemCollection(objVectorDisplay);
			 //	setting collection of orderstatus line items to the form
			 objOrderStatusForm.setOrderStatus(orderStatus);
			 Vector objListCombo=(Vector)objHashMap.get("ListCombo");
			 request.setAttribute("TimeLineCombo",objListCombo);
			 Vector objStatusCombo=(Vector)objHashMap.get("StatusCombo");
			 request.setAttribute("StatusCombo",objStatusCombo);
		     			 
		 } 
		 catch(AppException e)
		 {
			   String errMsg = e.getSErrorDescription();
			   log.debug("In AppException ");		   
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 catch(Exception e)
		 {
			   AppException ae = new AppException();
			   log.debug("In Exception ");
			   ae.performErrorAction(Exceptions.EXCEPTION,"OrderStatusCommand,executeRelatedList",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		 }
		 return FrontEndConstants.RELATEDLIST;
	   
   }
   
   /**
    * Method for General purpose if any (Here used for PDF Components of OrderStaus Detail)
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
   String reportName="OrderStatus";
   try
   {
	    OrderStatusPdf objOrderStatusPdf = new OrderStatusPdf();
	    request.getSession().setAttribute("PDF_OBJECT",objOrderStatusPdf);
	    request.getSession().setAttribute("PDF_Name",reportName);
	    response.sendRedirect("../pdfFileDownloadServlet");
		/*objDocument = objOrderStatusPdf.display(request,response);
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
 