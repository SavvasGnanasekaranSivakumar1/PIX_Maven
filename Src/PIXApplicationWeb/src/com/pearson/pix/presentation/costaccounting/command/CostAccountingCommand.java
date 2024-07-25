
package com.pearson.pix.presentation.costaccounting.command;



import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.goodsreceipt.GoodsReceiptLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.costaccounting.action.CostAccountingForm;
import com.pearson.pix.presentation.costaccounting.delegate.CostAccountingDelegate;
import com.pearson.pix.presentation.deliverymessage.delegate.DeliveryMessageDelegate;
import com.pearson.pix.presentation.goodsreceipt.delegate.GoodsReceiptDelegate;
import com.pearson.pix.presentation.pdf.DeliveryMessageApprovalPdf;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Contains the definitions of methods for different commands raised for Cost Accounting
 * .
 * @author shweta gupta
 */
public class CostAccountingCommand extends BaseCommand 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(CostAccountingCommand.class.getName());
   /**
    * Constructor for Initializing CostAccountingCommand
    */
   public CostAccountingCommand() 
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
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeList");
	   Collection objCollection = null;
	   User objUser = null;
	   Integer userId = null;
	   String idHidden="";
	   String endDate = null;
	   String startDate = null;
	   POHeader poHeaderTemp = null;
	   String fromPage=request.getParameter("fromPage");
	   if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
		request.getSession().removeAttribute("MESSAGE");
	   try
	   {
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId();
		   }
		   
		   CostAccountingForm objCostAccountingForm = (CostAccountingForm)form;
		   poHeaderTemp = new POHeader();
		   
		   String objorderby = "CREATION_DATE_TIME";
		   String objsort = "DESC";
		   String poNoFilter="";
		   CostAccountingDelegate objCostAccountingDelegate = new CostAccountingDelegate();
		   
		   
		   /**Code for handling filtering while using pagination--start*/  
		   /**The variables below will be passed as hidden along with the form*/  
		   
		   if(fromPage!= null && "PPM".equalsIgnoreCase(fromPage) && !(fromPage.equalsIgnoreCase(""))){
			    poNoFilter = (String)request.getParameter("poNo");
		   }
		   else{
			    poNoFilter = (String)objCostAccountingForm.getPoNo();  
		   }
		   String materialNoFilter = (String)objCostAccountingForm.getMaterialNo();
		   String delMsgNoFilter = (String)objCostAccountingForm.getDeliverymessageNo();
		   String millMerchantFilter = (String)objCostAccountingForm.getMillMerchant();
		   String statusFilter = (String)objCostAccountingForm.getStatus();
		   String startDateFilter=(String)objCostAccountingForm.getStartDate();
		   String endDateFilter=(String)objCostAccountingForm.getEndDate();
			   
		   if(PIXUtil.checkNullField(poNoFilter))
		   {
			   request.setAttribute("poNoFilter",poNoFilter);
			   poHeaderTemp.setPoNo(poNoFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("poNoFilter")))
		   {
			   request.setAttribute("poNoFilter",request.getParameter("poNoFilter"));
			   poNoFilter = request.getParameter("poNoFilter");
			   poHeaderTemp.setPoNo(poNoFilter);
			  
		   }
		   if(PIXUtil.checkNullField(materialNoFilter))
		   {
			   request.setAttribute("materialNoFilter",materialNoFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("materialNoFilter")))
		   {
			   request.setAttribute("materialNoFilter",request.getParameter("materialNoFilter"));
			   materialNoFilter = request.getParameter("materialNoFilter");
			   //objstatus.setStatusDescription(statusFilter);
		   }
		   
		   if(PIXUtil.checkNullField(delMsgNoFilter))
		   {
			   request.setAttribute("delMsgNoFilter",delMsgNoFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("delMsgNoFilter")))
		   {
			   request.setAttribute("delMsgNoFilter",request.getParameter("delMsgNoFilter"));
			   delMsgNoFilter = request.getParameter("delMsgNoFilter");
			  
		   }
		   if(PIXUtil.checkNullField(millMerchantFilter))
		   {
			   request.setAttribute("millMerchantFilter",millMerchantFilter);
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("millMerchantFilter")))
		   {
			   request.setAttribute("millMerchantFilter",request.getParameter("millMerchantFilter"));
			   millMerchantFilter = request.getParameter("millMerchantFilter");
			  
		   }
		   
		   if(PIXUtil.checkNullField(statusFilter))
		   {
			   request.setAttribute("statusFilter",statusFilter);
			   
		   }
		   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
		   {
			   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
			   statusFilter = request.getParameter("statusFilter");
			  
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
		   startDate=startDateFilter;
		   endDate=endDateFilter;
		    /**Code for handling filtering while using pagination--end*/  
			   
		   
		   int currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
		   if(userId != null)
		   {
		   objCollection = objCostAccountingDelegate.getDmApprovalList(poHeaderTemp,currentValue,objorderby,objsort,userId,materialNoFilter,delMsgNoFilter,millMerchantFilter,statusFilter,startDate,endDate);
		   int size = objCollection.size();
		   PIXUtil.getNextPage(request,currentValue,size);
		   PIXUtil.getPrevPage(request,currentValue);
		   // this condition is used to check for the size greater 10.
		   	if(size > PIXConstants.PAGE_SIZE)
		   	{ 	   
		   		objCollection.remove(((Vector)objCollection).get(size-1));
		   	}   
		   }
		   objCostAccountingForm.setDeliverymessageCollection(objCollection);
		   request.setAttribute("checkBoxIdList",idHidden);
		   return FrontEndConstants.LIST;
	   }
	   catch(AppException e)
	   {
		   String errMsg = e.getSErrorDescription();
           request.setAttribute(PIXConstants.PIX_ERROR,"No Message is available for Approval.");
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"CostAccountingCommand,executeList",e);
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
	   
	   CostAccountingForm form1=(CostAccountingForm)form;
	   Vector vec =(Vector)form1.getDeliveryMessage().getDeliveryMsgLineCollection();
	   DeliveryMessageLine line=  (DeliveryMessageLine)vec.get(0);
	 
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeGeneral");
	   User objUser = null;
	   String ownrshpMode=request.getParameter("ownrshpMode");
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeGeneral ownership Mode is "+ ownrshpMode);
	   Integer userId = null;
	   String url="/costaccounting/approvallist.do";
	   
	   try
	   {
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId();
		   }
		  /* if("approveList".equals(actioncommand)) //Cheking for Multiple DM Approval through List page
		   {
			   
			   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
				{
					String page_value = request.getParameter("PAGE_VALUE");
					String list_page ="?PAGE_VALUE="+page_value;
					   if(!PIXUtil.checkNullField(request.getParameter("poNoFilter")) & !PIXUtil.checkNullField(request.getParameter("materialNoFilter")) & !PIXUtil.checkNullField(request.getParameter("delMsgNoFilter")) & !PIXUtil.checkNullField(request.getParameter("millMerchantFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
				       {
						   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				       }
				       else
				       {
				    		if(request.getParameter("poNoFilter")!="")
				    		{
				    			list_page += "&poNoFilter="+request.getParameter("poNoFilter");
				    		}
				    		if(request.getParameter("materialNoFilter")!="")
				    		{
				    			list_page += "&materialNoFilter="+request.getParameter("materialNoFilter");
				    		}
				    		if(request.getParameter("delMsgNoFilter")!="")
				    		{
				    			list_page += "&delMsgNoFilter="+request.getParameter("delMsgNoFilter");
				    		}
				    		if(request.getParameter("startDateFilter")!="")
				    		{
				    			list_page += "&startDateFilter="+request.getParameter("startDateFilter");
				    		}
				    		if(request.getParameter("endDateFilter")!="")
				    		{
				    			list_page += "&endDateFilter="+request.getParameter("endDateFilter");
				    		}
				    		if(request.getParameter("millMerchantFilter")!="")
					    	{
					    		list_page += "&millMerchantFilter="+request.getParameter("millMerchantFilter");
					    	}
					    	
				    		log.info("list_page"+list_page);
				    		request.setAttribute(PIXConstants.OK_URL,url+list_page);	//For "Ok" button on Success/Error page
				      }
					   
				}
			   else
			   {
				   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from activelist
			   }
			   //request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from activelist
			   
			   String strSelectedCheckboxes = request.getParameter("checksArr");
			   
			   CostAccountingDelegate objCostAccountingDelegate = new CostAccountingDelegate();
			   objCostAccountingDelegate.insertMultipleDMApprove(strSelectedCheckboxes,userId);
			   String messageKey = "Delivery Message(s) have been successfully Approved.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
			   return "approveList";
		   }*/
		   if("approveDetail".equals(actioncommand))
		   {
			   if(isRepeated(request))
			   {
				   String page_value = request.getParameter("PAGE_VALUE");
				   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				   request.setAttribute(PIXConstants.SUCCESS_STRING,request.getSession().getAttribute("MESSAGE"));
				   return "approveDetail";
			   }
			   else{
			   if("CONSIGNMENT".equals(line.getOwnrshpMode()))
				   executeUpdate(mapping,form,request,response,messages);
			   //request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");
			   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
				{
					String page_value = request.getParameter("PAGE_VALUE");
					String list_page ="?PAGE_VALUE="+page_value;
					   if(!PIXUtil.checkNullField(request.getParameter("poNoFilter")) & !PIXUtil.checkNullField(request.getParameter("materialNoFilter")) & !PIXUtil.checkNullField(request.getParameter("delMsgNoFilter")) & !PIXUtil.checkNullField(request.getParameter("millMerchantFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
				       {
						   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
				       }
				       else
				       {
				    		if(request.getParameter("poNoFilter")!="")
				    		{
				    			list_page += "&poNoFilter="+request.getParameter("poNoFilter");
				    		}
				    		if(request.getParameter("materialNoFilter")!="")
				    		{
				    			list_page += "&materialNoFilter="+request.getParameter("materialNoFilter");
				    		}
				    		if(request.getParameter("delMsgNoFilter")!="")
				    		{
				    			list_page += "&delMsgNoFilter="+request.getParameter("delMsgNoFilter");
				    		}
				    		if(request.getParameter("startDateFilter")!="")
				    		{
				    			list_page += "&startDateFilter="+request.getParameter("startDateFilter");
				    		}
				    		if(request.getParameter("endDateFilter")!="")
				    		{
				    			list_page += "&endDateFilter="+request.getParameter("endDateFilter");
				    		}
				    		if(request.getParameter("millMerchantFilter")!="")
					    	{
					    		list_page += "&millMerchantFilter="+request.getParameter("millMerchantFilter");
					    	}
					    	
				    		log.info("list_page"+list_page);
				    		request.setAttribute(PIXConstants.OK_URL,url+list_page);	//For "Ok" button on Success/Error page
				      }
					   
				}
			   else
			   {
				   request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from activelist
			   }
			   //String msgId = request.getParameter("MSG_ID");
			   String msgId = request.getParameter("checksArr");
			   String msg_no=request.getParameter("msg_No");
			   CostAccountingDelegate objCostAccountingDelegate = new CostAccountingDelegate();
			   objCostAccountingDelegate.insertMultipleDMApprove(msgId,userId,ownrshpMode);		//same func user as when approving from list
			   String messageKey = "Delivery Message has been successfully Approved.";
			   if(msg_no.startsWith("GR"))
				   messageKey="Goods Receipt " +  msg_no  + " has been successfully Approved.";				   
			   else
				   messageKey="Delivery Message " + msg_no + " has been successfully Approved."; 
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
			   request.getSession().setAttribute("MESSAGE", messageKey);
			   return "approveDetail";
		   }
		   }
		   return "";
	   }
	   catch(AppException ae)
	   {
		   log.debug("Error occured while accepting multiple delivery messages");
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"CostAccountingCommand,executeGeneral",e);
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
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeRelatedList");
	   LinkedHashMap objLinkedHashMap = null;
	   CostAccountingForm objCostAccountingForm = (CostAccountingForm)form;
	   Vector millAssociatedUser = null;
	   Vector statusVector = null;
	   try
	   {
		   User objUser=null;
		   Integer userId = null;
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId();
		   }
		   CostAccountingDelegate objCostAccountingDelegate = new CostAccountingDelegate();
		   objLinkedHashMap = objCostAccountingDelegate.getFilterDropDowns(userId);
		   millAssociatedUser = (Vector)objLinkedHashMap.get(PIXConstants.MILL_ASSOCIATED_TO_USER);
		   statusVector = (Vector)objLinkedHashMap.get(PIXConstants.STATUS);
		   
		   objCostAccountingForm.setMillMerchantVector(millAssociatedUser);
		   objCostAccountingForm.setStatusVector(statusVector);
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
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeDisplay");
	   BigDecimal poId=null;
	   String orderType="";
	   
	   String ownrshpMode=request.getParameter("ownrshpMode");
	   String operation = request.getParameter("operation");
	   String ownershipMode = request.getParameter("ownershipMode");
	  
	   try
	   {
		   DeliveryMessage objDeliveryMessage = null;
		   CostAccountingForm objCostAccountingForm = (CostAccountingForm)form;
		   POHeader poHeaderTemp = new POHeader();
		   // checking poid parameter for null
		// roleTracking
		   if(operation != null && operation.equals("ownedQtyPopup"))
		   {
		   
		   try{	     	 		 
			 String msgNo = request.getParameter("msgNo");  // DM_7000000392_4_964805
			 String pono = request.getParameter("pono");
			 // retrieving msgId
			 	int idx = msgNo.lastIndexOf('_');
			 	String msgId = msgNo.substring(idx+1);
			 	
			 	int idx1 = msgNo.indexOf('_');
			 	String DMGRMode = msgNo.substring(0,idx1);
			 // end retrieving msgId
			 
			 //	if(ownershipMode.equals(""))
			 	System.out.println(pono);
			 	
			 	
			 	
			 		DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
			 		
			 		int printerRtDmNrt = objDeliveryMessageDelegate.printerRtDmNrtHist(msgId, DMGRMode);
			 		
			 		Vector cuVec = objDeliveryMessageDelegate.cuOwnedQtyPopup(msgId, ownershipMode,pono);	
			 		objCostAccountingForm.setCuPopVector(cuVec);
			 		int totalDelRollWeight = 0;
					 int totalRecRollWeight = 0;
			 		int onceFlag = 0;
			 		DeliveryMessageLine deliveryMessageLine = new DeliveryMessageLine(); 
			 		for(int i=0; i<cuVec.size(); i++){
			 			deliveryMessageLine = (DeliveryMessageLine)cuVec.get(i);
			 			if(printerRtDmNrt >0)
			 			{
			 				totalDelRollWeight = new Integer(deliveryMessageLine.getCuDeliveredQty().intValue()) + totalDelRollWeight;
			    	 		totalRecRollWeight = new Integer(deliveryMessageLine.getCuReceivedQty().intValue()) + totalRecRollWeight;
			 			}
			 			else
	 	    	 		{
			 				if(onceFlag == 0)
			 				{
			 					totalDelRollWeight = new Integer(deliveryMessageLine.getCuDeliveredQty().intValue()) + totalDelRollWeight;
			 					onceFlag = 1;
			 				}
			 				
			    	 		totalRecRollWeight = new Integer(deliveryMessageLine.getCuReceivedQty().intValue()) + totalRecRollWeight;
		 	    	 	//	break;
	 	    	 		}
		    	 		
		    	 	}
			 		System.out.println(totalDelRollWeight);
		    	 	System.out.println(totalRecRollWeight);
		    	 	objCostAccountingForm.setTotalDelRollWeight(totalDelRollWeight);
		    	 	objCostAccountingForm.setTotalRecRollWeight(totalRecRollWeight);
		    	 	request.setAttribute("ownershipMode", ownershipMode);
		    	 	request.setAttribute("msgNo", msgNo);
		    	 	request.setAttribute("pono", pono);
		    	 	
		    	 	
			//		objDeliveryMessage = objDeliveryMessageDelegate.displayDeliveryMessageDetail(poId,poVersion,messageId,orderType,ownrshpMode);
			 	
			 	return "forwardpopup"; 
			 	
	/*		 
	 		 GoodsReceiptDelegate objGoodsReceiptDelegate = new GoodsReceiptDelegate();
			 Vector dmDtlList = objGoodsReceiptDelegate.getGoodsReceiptRollInfo(msgId, productCode);// poid
			 
			 int totalRollWeight = 0;
			 int totalDelRollWeight = 0;
			 int totalRecRollWeight = 0;
	    	 	GoodsReceiptLine goodsReceiptLine = new GoodsReceiptLine();
	    	 	for(int i=0; i<dmDtlList.size(); i++){
	    	 		goodsReceiptLine = (GoodsReceiptLine)dmDtlList.get(i);
	    	 	//	totalRollWeight = new Integer(goodsReceiptLine.getQuantity().intValue()) + totalRollWeight;
	    	 		totalDelRollWeight = new Integer(goodsReceiptLine.getDelQuantity().intValue()) + totalDelRollWeight;
	    	 		totalRecRollWeight = new Integer(goodsReceiptLine.getRecQuantity().intValue()) + totalRecRollWeight;
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
			   } */
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
			
		   }
			 	

		   
		   
		   
		   if(request.getParameter("poid")!=null)
		   {
			   // catching the poid parameter
			    String poid = request.getParameter("poid");
			    poId = new BigDecimal(String.valueOf(poid));
			    poHeaderTemp.setPoId(poId);
		   }
		   
		    if(request.getSession().getAttribute("USER_INFO")!=null)
			  {
	               
	                if("O".equals(request.getParameter("orderPaper")))
	                {
	                	orderType="O";
	                }
	          }
	          else
	          {
	                AppException ae = new AppException();
	                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"CostAccoutingCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	                return FrontEndConstants.ERROR;
	          }
		   
		   
		   // catching the poversion parameter
		   String poversion = request.getParameter("poversion");
		   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
		   poHeaderTemp.setPoVersion(poVersion);
		   // catching the msgid parameter
		   String msgId = request.getParameter(PIXConstants.MSG_ID);
		   Integer messageId=new Integer(String.valueOf(msgId));
		   DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
		   objDeliveryMessage = objDeliveryMessageDelegate.displayDeliveryMessageDetail(poId,poVersion,messageId,orderType,ownrshpMode);
		   DeliveryMessageLine line = ((DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(0));
		   line.setConsignmentFlag("U");
		   objDeliveryMessage.setDeliveryMsgLineCollectionIdx(0,line);
		   objCostAccountingForm.setDeliveryMessage(objDeliveryMessage);
		   
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"CostAccountingCommand,executeDisplay",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	   
	
   }
   
   /**
    * Method for update quantity from delivery Detail screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	   System.out.println("Naveen::::::::::::CostAccountingCommand.executeUpdate");
	   BigDecimal poId=null;
	   String orderType="";
	   String delMsgNo = "";
	   String poid = null;
	   User objUser=null;
	   Integer userId = null;
	   String ownrshpMode=request.getParameter("ownrshpMode");
	   //String url="/costaccounting/approvallist.do";
	   String url="/costaccounting/deliverymessagedetail.do";
	   try
	   {
		   DeliveryMessage objDeliveryMessage = null;
		   CostAccountingForm objCostAccountingForm = (CostAccountingForm)form;
		   POHeader poHeaderTemp = new POHeader();
		   String pono = request.getParameter("pono");
		   // checking poid parameter for null
		   if(request.getParameter("poid")!=null)
		   {
			   // catching the poid parameter
			    poid = request.getParameter("poid");
			    poId = new BigDecimal(String.valueOf(poid));
			    poHeaderTemp.setPoId(poId);
		   }
		   
		    if(request.getSession().getAttribute("USER_INFO")!=null)
			  {
		    		objUser = (User)request.getSession().getAttribute("USER_INFO");
		    		userId = objUser.getUserId();
	                if("O".equals(request.getParameter("orderPaper")))
	                {
	                	orderType="O";
	                }
	          }
	          else
	          {
	                AppException ae = new AppException();
	                ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"CostAccoutingCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	                return FrontEndConstants.ERROR;
	          }
		   
		   
		   // catching the poversion parameter
		   String poversion = request.getParameter("poversion");
		   BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
		   poHeaderTemp.setPoVersion(poVersion);
		   // catching the msgid parameter
		   String msgId = request.getParameter(PIXConstants.MSG_ID);
		   Integer messageId=new Integer(String.valueOf(msgId));
		   String msgNo = request.getParameter("msgNo");
		   CostAccountingDelegate objCostAccountingDelegate = new CostAccountingDelegate();
		   objDeliveryMessage = objCostAccountingForm.getDeliveryMessage();
		   
		  // objDeliveryMessage = objDeliveryMessageDelegate.displayDeliveryMessageDetail(poId,poVersion,messageId,orderType);
		   delMsgNo = objCostAccountingDelegate.updateDeliveryMessageLine(objDeliveryMessage,messageId,poId,poVersion,userId);
		   		   
		   objCostAccountingForm.setDeliveryMessage(objDeliveryMessage);
		   //request.setAttribute(PIXConstants.OK_URL,url+"?PAGE_VALUE=1");
		   String list_page = url + "?MSG_ID="+msgId+"&poid="+poid+"&poversion="+poVersion+"&pono="+pono+"&orderPaper=O"+"&ownrshpMode="+ownrshpMode;
		   
		   if(request.getParameter("PAGE_VALUE")!="" && request.getParameter("PAGE_VALUE")!=null)
			{
				String page_value = request.getParameter("PAGE_VALUE");
				 list_page =list_page + "&PAGE_VALUE="+page_value;
				 
				   if(!PIXUtil.checkNullField(request.getParameter("poNoFilter")) & !PIXUtil.checkNullField(request.getParameter("materialNoFilter")) & !PIXUtil.checkNullField(request.getParameter("delMsgNoFilter")) & !PIXUtil.checkNullField(request.getParameter("millMerchantFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")))
			       {
					   request.setAttribute(PIXConstants.OK_URL,list_page);	//For "Ok" button on Success/Error page
			       }
			       else
			       {
			    		if(request.getParameter("poNoFilter")!="")
			    		{
			    			list_page += "&poNoFilter="+request.getParameter("poNoFilter");
			    		}
			    		if(request.getParameter("materialNoFilter")!="")
			    		{
			    			list_page += "&materialNoFilter="+request.getParameter("materialNoFilter");
			    		}
			    		if(request.getParameter("delMsgNoFilter")!="")
			    		{
			    			list_page += "&delMsgNoFilter="+request.getParameter("delMsgNoFilter");
			    		}
			    		if(request.getParameter("startDateFilter")!="")
			    		{
			    			list_page += "&startDateFilter="+request.getParameter("startDateFilter");
			    		}
			    		if(request.getParameter("endDateFilter")!="")
			    		{
			    			list_page += "&endDateFilter="+request.getParameter("endDateFilter");
			    		}
			    		if(request.getParameter("millMerchantFilter")!="")
				    	{
				    		list_page += "&millMerchantFilter="+request.getParameter("millMerchantFilter");
				    	}
				    	
			    		log.info("list_page"+list_page);
			    		request.setAttribute(PIXConstants.OK_URL,list_page);	//For "Ok" button on Success/Error page
			    		
			      }
				   
			}
		   else
		   {
			   request.setAttribute(PIXConstants.OK_URL,list_page + "&PAGE_VALUE=1");	//For "Ok" button on Success/Error page called this url when coming from activelist
		   }
		   String msg_no=request.getParameter("msg_No");
		 
		   objCostAccountingDelegate.insertMultipleDMApprove(msgId,userId,ownrshpMode);		//same func user as when approving from list
		 String messageKey="";
		   if(msg_no.startsWith("GR"))
			   messageKey="Goods Receipt " +  msg_no  + " has been successfully updated.";				   
		   else
			   messageKey="Delivery Message " + msg_no + " has been successfully updated."; 
		//   String messageKey = "Delivery Message "+ msgNo + " has been successfully updated.";
		   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);
		   return FrontEndConstants.UPDATE;
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"CostAccountingCommand,executeDisplay",e);
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
	
	System.out.println("Naveen::::::::::::CostAccountingCommand.executepdf");
	String msg_no = (String)request.getParameter("msg_No");
	 String reportName="";
	if(msg_no.startsWith("GR"))
		reportName="GoodsReceiptApproval";
	else
		reportName="DeliveryMessageApproval";
			
		
   try
   {
	   DeliveryMessageApprovalPdf objDeliveryMessagePdf = new DeliveryMessageApprovalPdf();
	   
   	   
	    request.getSession().setAttribute("PDF_OBJECT",objDeliveryMessagePdf);
		    request.getSession().setAttribute("PDF_Name",reportName);
		    response.sendRedirect("../pdfFileDownloadServlet");
		/*objDocument = objDeliveryMessagePdf.display(request,response);
		objDocument.close();*/ 
   }
   catch(Exception e)
	   {
		  AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"CostAccountingCommand,executePDF",e);
		   String errMsg = ae.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	   }
		  return FrontEndConstants.EXPORTPDF;
}   


}
