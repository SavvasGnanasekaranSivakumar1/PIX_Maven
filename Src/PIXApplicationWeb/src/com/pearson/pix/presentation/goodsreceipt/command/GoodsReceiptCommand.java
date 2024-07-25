package com.pearson.pix.presentation.goodsreceipt.command;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.lowagie.text.Document;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.goodsreceipt.GoodsReceiptDAOImpl;
import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.goodsreceipt.GoodsReceiptLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.admin.action.AddPubUnitForm;
import com.pearson.pix.presentation.admin.action.SupplierForm;
import com.pearson.pix.presentation.admin.action.UserForm;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.goodsreceipt.action.GoodsReceiptForm;
import com.pearson.pix.presentation.goodsreceipt.delegate.GoodsReceiptDelegate;
import com.pearson.pix.presentation.pdf.GoodsReceiptPdf;

/**
 * Contains the definitions of methods for different commands raised for Goods 
 * Receipt.
 */
public class GoodsReceiptCommand extends BaseCommand 
{
   
   /**
    * Constructor for Initializing GoodsReceiptCommand
    */
   public GoodsReceiptCommand()  
   {
    
   } 
   /**
    * Logger.
    */
   private static Log log = LogFactory.getLog(GoodsReceiptDAOImpl.class.getName());
   
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
	   Collection objCollection = null;
//		 added....
	   String idHidden="";
	   User objUser=null;
	   Integer userId=null;
		if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
	   //....
		
	   try{
		    request.getSession().removeAttribute("MESSAGE"); 
		   if(request.getSession().getAttribute("USER_INFO")!=null)
		   {
              objUser = (User)request.getSession().getAttribute("USER_INFO");
              userId=objUser.getUserId();
           }
		     GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;
		     POHeader poHeaderTemp = new POHeader();
		     String poid = request.getParameter("poid");
			 BigDecimal poId = new BigDecimal(String.valueOf(poid));
			 objGoodsReceiptForm.setPoId(poId);
			 poHeaderTemp.setPoId(poId);
			 String poversion = request.getParameter("poversion");
			 BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
			 objGoodsReceiptForm.setPoVersion(poVersion);
			 poHeaderTemp.setPoVersion(poVersion);
		     String objorderby = "1";
			 String objsort = "DESC";
			 GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
			 int currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
			 objCollection = objGoodsReceiptDelegate.displayGoodsReceiptList(poHeaderTemp,currentValue,objorderby,objsort,userId);
			 int size = objCollection.size();
			 PIXUtil.getNextPage(request,currentValue,size);
			 PIXUtil.getPrevPage(request,currentValue);
			 if(size > PIXConstants.PAGE_SIZE)
			 { 	   
			   objCollection.remove(((Vector)objCollection).get(size-1));
			 }   
			 objGoodsReceiptForm.setGoodsreceiptCollection(objCollection);
			 request.setAttribute("checkBoxIdList",idHidden);
			 return FrontEndConstants.LIST;
	      }
	   catch(AppException e)
	   {
		   String errMsg = e.getSErrorDescription();
           request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
           request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeList",e);
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
	   BigDecimal poId=null;
	     User objUser = null;
	     String roletype =null;
	   try
	   {
		   GoodsReceipt objGoodsReceipt = null;
		   GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;
		   POHeader poHeaderTemp = new POHeader();
		   if(request.getParameter("poid")!=null)
		   {
			   String poid = request.getParameter("poid");
			   poId = new BigDecimal(String.valueOf(poid));
			   poHeaderTemp.setPoId(poId);
		   }
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   roletype  = objUser.getRoleTypeDetail().getRoleType();
		   }
		   
		   poHeaderTemp.setPoId(poId);
		   
		   String poversion = request.getParameter("poversion");
		   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
		   poHeaderTemp.setPoVersion(poVersion);
	       String poNumber = null;
	       String receiptId = request.getParameter(PIXConstants.RECEIPT_ID);
	       Integer receipt_Id = new Integer(String.valueOf(receiptId));
		   GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
		   objGoodsReceipt = objGoodsReceiptDelegate.displayGoodsReceiptDetail(poNumber,receipt_Id,request.getParameter("orderPaper"),roletype);
		   objGoodsReceiptForm.setGoodsreceipt(objGoodsReceipt);
		   
		  return FrontEndConstants.DISPLAY;
		   
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeDisplay",e);
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
	   User objUser=null;
	   String login = "";
	   GoodsReceipt objGoodsReceipt = null;
	   String orderPaper = null;
	   String list_page = null;
	   Integer userId=null;	
	   String fo =request.getParameter("fo");
	
	   try
	   {
		   if(isRepeated(request))
       	   {
			   String poid = request.getParameter("poid");
			   BigDecimal poId = new BigDecimal(String.valueOf(poid));
			   String poversion = request.getParameter("poversion");
			   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
			   String ponumber = request.getParameter("pono");
			   String releaseno = request.getParameter("rno");		   
			   Integer releaseNo = new Integer(String.valueOf(releaseno));
			   String order = request.getParameter("order");
			   String pageOrderList = request.getParameter("page_order_list");
			   log.info("repeated req:");
			   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
			   {					   
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
			   }
			   else
			   {				  
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
			   }
			   request.setAttribute(PIXConstants.SUCCESS_STRING,request.getSession().getAttribute("MESSAGE"));
			   //request.getSession().setAttribute("MESSAGE", messageKey);
			   return FrontEndConstants.INSERT;  
       	   }
		   else{
		   if(fo!=null)
		   {
			   GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;	
			   objGoodsReceipt = objGoodsReceiptForm.getGoodsreceipt();
			   
			   int size = objGoodsReceipt.getGoodsReceiptLineCollection().size();		   
			   POHeader poHeaderTemp = new POHeader();
			   String poid = request.getParameter("poid");
			   BigDecimal poId = new BigDecimal(String.valueOf(poid));
			   poHeaderTemp.setPoId(poId);
			   String poversion = request.getParameter("poversion");
			   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
			   poHeaderTemp.setPoVersion(poVersion);
			   String ponumber = request.getParameter("pono");
			   poHeaderTemp.setPoNo(ponumber);
			   String releaseno = request.getParameter("rno");		   
			   Integer releaseNo = new Integer(String.valueOf(releaseno));
			   poHeaderTemp.setReleaseNo(releaseNo);
			   String order = request.getParameter("order");
			   
			   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
			   {	   
				   orderPaper = request.getParameter("orderPaper");
			   } 
			   //catching the page order list parameter
				  String pageOrderList = request.getParameter("page_order_list");
				  
			   /*if(request.getSession().getAttribute("USER_INFO")!=null)
			   {
	              objUser = (User)request.getSession().getAttribute("USER_INFO");                
	              login = objUser.getLogin();           
	           }*/
				  
			  if(request.getSession().getAttribute("USER_INFO")!=null)
				   {				 
					  objUser = (User)request.getSession().getAttribute("USER_INFO");
					  userId=objUser.getUserId();		       
				      login=userId.toString();
				   }
	           else
	           {
	                AppException ae = new AppException();
	                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"GoodsReceiptCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	                return FrontEndConstants.ERROR;
	          }
			   GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();		   
			   String receiptNumber = objGoodsReceiptDelegate.saveGoodsReceipt(objGoodsReceipt,poHeaderTemp,size,login,request.getParameter("MSG_ID"),request.getParameter("fo"));
			   if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
			   {
				   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
				   {					   
					   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
				   }
				   else
				   {				  
					   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
				   }
			   }
			   else
			   {
				   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
				   {
					   list_page = "?PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList;
				   }
				   else
					   {
					   list_page = "?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList;
					   }
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
				   //request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do"+list_page);         //For "Ok" button on Success/Error page
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
			   }
			  //System.out.println("receiptNumber"+receiptNumber);
			   StringBuffer receiptBuff = new StringBuffer();
			   String[] receiptArr  = receiptNumber.split(",");
			   for(int k =0; k<receiptArr.length;k++){
				   receiptBuff.append("<div>").append("<li>").append(receiptArr[k]).append("</li>").append("</div>");
			   }
			   String messageKey = "Following Goods Receipt created successfully"+receiptBuff.toString();
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
			   
			   request.getSession().setAttribute("MESSAGE", messageKey);
			   //request.getSession().setAttribute("URL", messageKey);
			   
			   return FrontEndConstants.INSERT;
		   }
		   else
		   {
			   GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;	
			   objGoodsReceipt = objGoodsReceiptForm.getGoodsreceipt();
			   int size = objGoodsReceipt.getGoodsReceiptLineCollection().size();
			   POHeader poHeaderTemp = new POHeader();
			   String poid = request.getParameter("poid");
			   BigDecimal poId = new BigDecimal(String.valueOf(poid));
			   poHeaderTemp.setPoId(poId);
			   String poversion = request.getParameter("poversion");
			   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
			   poHeaderTemp.setPoVersion(poVersion);
			   String ponumber = request.getParameter("pono");
			   poHeaderTemp.setPoNo(ponumber);
			   String releaseno = request.getParameter("rno");
			   Integer releaseNo = new Integer(String.valueOf(releaseno));
			   poHeaderTemp.setReleaseNo(releaseNo);
			   String order = request.getParameter("order");
			   
			   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
			   {	   
				   orderPaper = request.getParameter("orderPaper");
			   } 
			   //catching the page order list parameter
				  String pageOrderList = request.getParameter("page_order_list");
			   if(request.getSession().getAttribute("USER_INFO")!=null)
			   {
	              objUser = (User)request.getSession().getAttribute("USER_INFO");
	                
	              login = objUser.getLogin();
	               
	           
	           }
	           else
	           {
	                AppException ae = new AppException();
	                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"OrderStatusCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	                return FrontEndConstants.ERROR;
	          }
			   GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
			   String receiptNumber = objGoodsReceiptDelegate.saveGoodsReceipt(objGoodsReceipt,poHeaderTemp,size,login,request.getParameter("MSG_ID"),request.getParameter("fo"));
			   if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
			   {
				   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
				   {		   
					   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
				   }
				   else
				   {
					   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
				   }
			   }
			   else
			   {
				   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
				   {
					   list_page = "?PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList;
				   }
				   else
					   {
					   list_page = "?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList;
					   }
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
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do"+list_page);         //For "Ok" button on Success/Error page
			   }
			   String messageKey = "Goods Receipt No "+receiptNumber+" created successfully.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
			   request.getSession().setAttribute("MESSAGE", messageKey);
			   return FrontEndConstants.INSERT; 
		   }
		   } //close of repeated else
			
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeInsert",e);
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
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse reponse, final ActionMessages messages) 
   {
	   User objUser=null;
	   String login = "";
	   try
	   {
		   
		   HashMap objHashMap = null;
		   GoodsReceipt objGoodsReceipt = null;
		   GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;
		   objGoodsReceipt = objGoodsReceiptForm.getGoodsreceipt();
		   POHeader poHeaderTemp = new POHeader();
		   String poid = request.getParameter("poid");
		   BigDecimal poId = new BigDecimal(String.valueOf(poid));
		   poHeaderTemp.setPoId(poId);
		   String poversion = request.getParameter("poversion");
		   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
		   poHeaderTemp.setPoVersion(poVersion);
		   String ponumber = request.getParameter("pono");
		   poHeaderTemp.setPoNo(ponumber);
		   String releaseno = request.getParameter("rno");
		   Integer releaseNo = new Integer(String.valueOf(releaseno));
		   poHeaderTemp.setReleaseNo(releaseNo);
		   if(request.getSession().getAttribute("USER_INFO")!=null)
		   {
              objUser = (User)request.getSession().getAttribute("USER_INFO");
                
              login = objUser.getLogin();
               
           }
          else
          {
                AppException ae = new AppException();
                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"OrderStatusCommand,executeInsert");
                String errMsg = ae.getSErrorDescription();
                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
                return FrontEndConstants.ERROR;
          }
		   GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
		   
		   objHashMap = objGoodsReceiptDelegate.displayBasicGoodsReceiptInfo(poHeaderTemp,login,objGoodsReceipt,request.getParameter("fo"),objUser.getUserId(),request.getParameter("MSG_ID"),request.getParameter("MSG_LINE_NO"));
		   
		   GoodsReceipt objGoodsReceiptTemp = (GoodsReceipt)objHashMap.get("HeaderandLineDetails");
		   request.setAttribute("GoodsCondition", objHashMap.get("GoodsCondition")); 
		   objGoodsReceiptForm.setGoodsreceipt(objGoodsReceiptTemp);
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeRelatedList",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   return FrontEndConstants.RELATEDLIST;
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
   String reportName="GoodsReceipt";
   try
   {
	    GoodsReceiptPdf objGoodsReceiptPdf = new GoodsReceiptPdf();
		/*objDocument = objGoodsReceiptPdf.display(request,response);
		objDocument.close();*/	
	    request.getSession().setAttribute("PDF_OBJECT",objGoodsReceiptPdf);
	    request.getSession().setAttribute("PDF_Name",reportName);
	    response.sendRedirect("../pdfFileDownloadServlet");
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
//anshu
/**
 * Method for display of Paper List screens
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @param messages
 * @return String
 */
public String executeGeneral(String actioncommand, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
{ 
	
	Collection objCollection = null;
	//String idHidden="";
	User objUser=null;
	Integer userId=null;
	
	String ponumber = request.getParameter("pono");
	System.out.println(ponumber);
/*	try{
	GoodsReceiptDelegate goodsReceiptDelegate = new GoodsReceiptDelegate();
	
	HttpSession session = request.getSession();
    // roleTracking pop
	     
	    // String login_id = (String)request.getParameter("loginId");
        String ponumber1 = request.getParameter("pono");
        String line = request.getParameter("asc");
        System.out.println(ponumber);
        
        
    //    String login_id = (String)session.getAttribute("login_id");  // from loginCommand
	 //    int roleTrackingFlag = DeliveryMessageDelegate.getRoleTrackingFlag(login_id) ;
        int roleTrackingFlag = goodsReceiptDelegate.getRoleTrackingFlag(ponumber1) ;
	   System.out.println("**********************************************  roleTrackingFlag " + roleTrackingFlag);
	     
	     if(roleTrackingFlag > 0)
	     {
	    	 roleTrackingFlag = 1;
	     }
	     session.setAttribute("grRoleTrackingFlag", roleTrackingFlag);
	     
	}
	catch(AppException e)
    {
        String errMsg = e.getSErrorDescription();
        request.setAttribute("PIX_ERROR", errMsg);
        return "error";
    }
    catch(Exception e)
    {
        AppException ae = new AppException();
        e.printStackTrace();
        ae.performErrorAction("9000", "DeliveryMessageCommand,executeList", e);
        String errMsg = ae.getSErrorDescription();
        request.setAttribute("PIX_ERROR", errMsg);
        return "error";
    }
	*/
	
	
    //get user id
	if(request.getSession().getAttribute("USER_INFO")!=null)
		   {
		       objUser = (User)request.getSession().getAttribute("USER_INFO");
		       userId=objUser.getUserId();
		   }
		request.getSession().removeAttribute("MESSAGE");
		 //setting poHeader
	     GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;
	     POHeader poHeaderTemp = new POHeader();
	     String poid = request.getParameter("poid");
	     
	     
	     String paperlist = request.getParameter("paperlist");  // passed as parameter from jsp for different operations.
		 BigDecimal poId = new BigDecimal(String.valueOf(poid));
		 objGoodsReceiptForm.setPoId(poId);
		 poHeaderTemp.setPoId(poId);
		 if(request.getParameter("poversion") != null)
		 {
			 String poversion = request.getParameter("poversion");
			 BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
			 objGoodsReceiptForm.setPoVersion(poVersion);
			 poHeaderTemp.setPoVersion(poVersion);
		 }
		 
		 
	//	 String paperlist = request.getParameter("paperlist");
		 GoodsReceipt objGoodsReceipt = null;
		 //System.out.println("inside execute general "+paperlist);
		 if(paperlist.equalsIgnoreCase("paperlist"))
		 {
			 try{	     	 		 
			 
			 GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
			 int currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
			 
			 	objCollection = objGoodsReceiptDelegate.displayNewPaperGoodsReceipt(poHeaderTemp,currentValue,userId, ponumber);
				// System.out.println("objCollection: "+ objCollection.toString());
			 				 	
				 objGoodsReceipt = new GoodsReceipt();
				 objGoodsReceipt.setGoodsReceiptLineCollection(objCollection);
				 objGoodsReceiptForm.setGoodsreceipt(objGoodsReceipt);
				 //request.setAttribute("checkBoxIdList",idHidden);				 
			   }
			
	  		
			  catch(AppException e)
			  {
				   String errMsg = e.getSErrorDescription();
			      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				   return FrontEndConstants.ERROR;
			  }
			  catch(Exception e)
			  {
				   AppException ae = new AppException();
				   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeGeneral",e);
				   String errMsg = ae.getSErrorDescription();
				   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				   return FrontEndConstants.ERROR;
			  }
			 return "forwardfinished";
			 
		 }
		 
		 if(paperlist.equalsIgnoreCase("paperGRHistory"))
		 {
			 try{	     	 		 
			 
			 GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
			 int currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
			 
			 	objCollection = objGoodsReceiptDelegate.displayHistoryPaperGoodsReceipt(poHeaderTemp,currentValue,userId,request.getParameter("msgId"),request.getParameter("actionKey")); // getPaperGoodsHistory in GRDAOImpl
				 objGoodsReceiptForm.setGoodsreceiptCollection(objCollection);
				 //request.setAttribute("checkBoxIdList",idHidden);				 
			   }
			
	  		
			  catch(AppException e)
			  {
				   String errMsg = e.getSErrorDescription();
			      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				   return FrontEndConstants.ERROR;
			  }
			  catch(Exception e)
			  {
				   AppException ae = new AppException();
				   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeGeneral",e);
				   String errMsg = ae.getSErrorDescription();
				   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				   return FrontEndConstants.ERROR;
			  }
			return "forwardfinished";
		 }
		 /*Code for GR Detail List popup*/
		 if(paperlist.equalsIgnoreCase("dmdetailist"))
		 {
			 try{	     	 		 
				 String msgNo = request.getParameter("msgNo");  // DM_7000000392_4_964805
				 // retrieving msgId
				 	int idx = msgNo.lastIndexOf('_');
				 	String msgId = msgNo.substring(idx+1);
				 // end retrieving msgId
				 	
				 	int idx1 = msgNo.indexOf('_');
				 	String DMGRMode = msgNo.substring(0,idx1);
				 	
				 	String productCode = request.getParameter("productcode");
				 	ponumber = productCode;
				 
				 GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
				 Vector dmDtlList = objGoodsReceiptDelegate.getGoodsReceiptRollInfo(msgId, ponumber, DMGRMode);// poid
				 
				 int printerRtDmNrt = objGoodsReceiptDelegate.printerRtDmNrtHist(msgId, DMGRMode);
				 
				 int totalRollWeight = 0;
				 int totalDelRollWeight = 0;
				 int totalRecRollWeight = 0;
				 int onceFlag = 0;
	 	    	 	GoodsReceiptLine goodsReceiptLine = new GoodsReceiptLine();
	 	    	 	for(int i=0; i<dmDtlList.size(); i++){
	 	    	 		goodsReceiptLine = (GoodsReceiptLine)dmDtlList.get(i);
	 	    	 		if(printerRtDmNrt >0){
	 	    	 		totalDelRollWeight = new Integer(goodsReceiptLine.getDelQuantity().intValue()) + totalDelRollWeight;
	 	    	 		totalRecRollWeight = new Integer(goodsReceiptLine.getRecQuantity().intValue()) + totalRecRollWeight;
	 	    	 		}
	 	    	 		else
	 	    	 		{
	 	    	 			if(onceFlag == 0)
	 	    	 			{
		 	    	 			totalDelRollWeight = new Integer(goodsReceiptLine.getDelQuantity().intValue()) + totalDelRollWeight;
		 	    	 			onceFlag = 1;
	 	    	 			}

		 	    	 		
	 	    	 			totalRecRollWeight = new Integer(goodsReceiptLine.getRecQuantity().intValue()) + totalRecRollWeight;
		 	    	 	//	break;
	 	    	 		}
	 	    	 	}
	 	    	 	System.out.println(totalDelRollWeight);
	 	    	 	System.out.println(totalRecRollWeight);
	 	    	 //	objGoodsReceiptForm.setTotalRollWeight(totalRollWeight);
	 	    	 	objGoodsReceiptForm.setTotalDelRollWeight(totalDelRollWeight);
	 	    	 	objGoodsReceiptForm.setTotalRecRollWeight(totalRecRollWeight);
				 
				 
				 objGoodsReceiptForm.setDmDtlList(dmDtlList);
				 request.setAttribute("poId", poid);
				 request.setAttribute("productCode", productCode);
				 request.setAttribute("msgId", msgId);
				   }
				  catch(AppException e)
				  {
					   String errMsg = e.getSErrorDescription();
				      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
				      request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
					   return FrontEndConstants.ERROR;
				  }
				  catch(Exception e)
				  {
					   AppException ae = new AppException();
					   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeGeneral",e);
					   String errMsg = ae.getSErrorDescription();
					   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
					   return FrontEndConstants.ERROR;
				  }
				return "forwardpopup";  
		 }
		 
		 
		 
		 return "forwardfinished";
		 
		 
		 
		 
		 
		//return FrontEndConstants.LIST;
}

/**
 * Method for Insert operation
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return String
 */
public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
{
	   User objUser=null;
	   String login = "";
	   String orderPaper = null;
	   String list_page = null;
	   Integer userId=null;	
	   Vector goodsReceiptVector=new Vector();
	
	   try
	   {
		   GoodsReceiptForm objGoodsReceiptForm = (GoodsReceiptForm)form;	
		   
		   goodsReceiptVector = (Vector)objGoodsReceiptForm.getGoodsreceiptCollection();
		 
		   POHeader poHeaderTemp = new POHeader();
		   String poid = request.getParameter("poid");
		   BigDecimal poId = new BigDecimal(String.valueOf(poid));
		   poHeaderTemp.setPoId(poId);
		   String poversion = request.getParameter("poversion");
		   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
		   poHeaderTemp.setPoVersion(poVersion);
		   String ponumber = request.getParameter("pono");
		   poHeaderTemp.setPoNo(ponumber);
		   String releaseno = request.getParameter("rno");
		   Integer releaseNo = new Integer(String.valueOf(releaseno));
		   poHeaderTemp.setReleaseNo(releaseNo);
		   String actionKey = request.getParameter("actionKey");
		   String msgId = request.getParameter("msgId");
		 
		   String order = request.getParameter("order");
		   
		   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
		   {	   
			   orderPaper = request.getParameter("orderPaper");
		   } 
		   //catching the page order list parameter
			  String pageOrderList = request.getParameter("page_order_list");
			  
			  
		  if(request.getSession().getAttribute("USER_INFO")!=null)
			   {				 
				  objUser = (User)request.getSession().getAttribute("USER_INFO");
				  userId=objUser.getUserId();		       
			      login=userId.toString();
			   }
        else
        {
             AppException ae = new AppException();
             ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"GRHistoryCommand,executeUpdate");
             String errMsg = ae.getSErrorDescription();
             request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
             return FrontEndConstants.ERROR;
       }
		   GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();		   
		   String receiptNumber = objGoodsReceiptDelegate.saveGoodsReceiptHistory(goodsReceiptVector,poHeaderTemp,login);
		   //System.out.println("inside Command DAO returns receiptNumber as "+ receiptNumber);
		   if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
		   {
			   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
			   {		   
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId="+msgId+"&PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&actionKey="+actionKey+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page				   
			   }
			   else
			   {				  
				   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
			   }
		   }
		   else
		   {
			   if(PIXUtil.checkNullField(request.getParameter("orderPaper")))
			   {
				   list_page = "?PAGE_VALUE=1&orderPaper="+orderPaper+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&order="+order+"&page_order_list="+pageOrderList;
			   }
			   else
				   {
				   list_page = "?PAGE_VALUE=1&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&order="+order+"&page_order_list="+pageOrderList;
				   }
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
			   //request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptlist.do"+list_page);         //For "Ok" button on Success/Error page
			   request.setAttribute(PIXConstants.OK_URL,"/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId="+msgId+"&PAGE_VALUE=1&orderPaper="+orderPaper+"&actionKey="+actionKey+"&poid="+poid+"&poversion="+poversion+"&pono="+ponumber+"&rno="+releaseNo+"&order="+order+"&page_order_list="+pageOrderList);           //For "Ok" button on Success/Error page
		   }
		   //System.out.println("receiptNumber"+receiptNumber);
		   if(receiptNumber==null)
		   {
		   
		   String messageKey = "There was no Goods Receipt to be updated.";
		   
		   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	
		   return FrontEndConstants.UPDATE;
		   }
		   else
		   {
			   StringBuffer receiptBuff = new StringBuffer();
			   String[] receiptArr  = receiptNumber.split(",");
			   for(int k =0; k<receiptArr.length;k++){
				   receiptBuff.append("<div>").append("<li>").append(receiptArr[k]).append("</li>").append("</div>");
			   }
			   String messageKey = "Goods Receipts have been updated successfully. ";//+receiptBuff.toString();
			   
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	
			   return FrontEndConstants.UPDATE;     
		   }
			
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"GoodsReceiptCommand,executeUpdate",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }	
}


//end of RFS 5922
}
