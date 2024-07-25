package com.pearson.pix.presentation.purchaseorder.command;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.lowagie.text.Document;
import com.pearson.pix.presentation.pdf.PlanningMillPdf;
import com.pearson.pix.presentation.pdf.Planningpdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderMillPdf;
import com.pearson.pix.presentation.pdf.PurchaseOrderPdf;
import com.pearson.pix.presentation.home.action.HomePageForm;
import com.pearson.pix.presentation.home.delegate.HomePageDelegate;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailFormHist;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderListForm;
import com.pearson.pix.presentation.purchaseorder.delegate.PurchaseOrderDelegate;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POSuppliedComp;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Contains the definitions of methods for different commands raised for Purchase 
 * Order.
 * @author Mohit Bajaj
 */
public class PurchaseOrderCommand extends BaseCommand 
{
   
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PurchaseOrderCommand.class.getName());
   /**
    * Constructor for Initializing PurchaseOrderCommand
    */
   public PurchaseOrderCommand() 
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
	   Collection objCollection = null;
	   String partyType=request.getParameter("party");	   
	   request.setAttribute("partytype",partyType);
	   String status=request.getParameter("status");
	   String orderType="";
	   String page="";
	   String poNumber=request.getParameter("ponumber");
	   //added....
	   String idHidden="";	   
	   
		if(request.getParameter("idHidden")!=null){
			idHidden=request.getParameter("idHidden");
		}
		
	   User objUser = null;
	   if(partyType.equals("V"))	//For Component Vendor
	   {
		   orderType="PO";
	   }
	   else if(partyType.equals("S"))	//For Furnished Vendor
	   {
		   orderType="FO";
	   }else if(partyType.equals("M"))
	   {
		   orderType="MILL_PO";
	   }
	   int 	userId=0;
	   
	   if(request.getSession().getAttribute("USER_INFO")!=null){
		   objUser = (User)request.getSession().getAttribute("USER_INFO");
		   userId = objUser.getUserId().intValue();
	   }
	   
	   //String page=request.getParameter("page");
	   if(request.getParameter("pageFilter")!=null)
	   {
		   page=request.getParameter("pageFilter");
	   }
	   else
	   {
		   page=request.getParameter("page"); //catching the page parameter
		   
	   }
	   request.setAttribute("pageFilter", page);
	   if(PIXUtil.checkNullField(page)==false){//checking whether page is History or Current page
		   page = "C";
	   }
	   
	   //int	pagination=1;
	   String orderby=PIXConstants.POSTED_DATE;
	   String sort="DESC";
	   PurchaseOrderDelegate objPurchaseOrderDelegate = new PurchaseOrderDelegate();  
	   POHeader objpoheader=null;
	   String endDate = null;
	   BigDecimal poId=null;
	   String poidFilter="";
	   String startDate = null;
	   String sbBDate = null;
	   String ebBDate = null;
	   PurchaseOrderListForm objpoForm= null;
	   int currentValue = 1;
	   try
	   {
		 if (form instanceof PurchaseOrderListForm)//if through purchase order
		 {
	   objpoForm = (PurchaseOrderListForm)form;
	   objpoheader = objpoForm.getPoHeader();
	   
	   Status objstatus=new Status();
	   if(request.getParameter("poid")!=null)//checking poid parameter for null
	   {
		       poId=BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));//parsing the string poid to a BigDecimal value  
			   objpoheader.setPoId(poId); 
	   }
	   if((PIXUtil.checkNullField(status)==false))
		 {	 
		 objstatus.setStatusDescription(objpoForm.getPoHeader().getStatusDetail().getStatusDescription());
		 }
		 else
		 {
		  objstatus.setStatusDescription(status);
		 }
	   request.setAttribute("PATH", request.getContextPath());
	   /*setting the values to be passed along with next and previous handling on list page*/
	   String ponoFilter = request.getParameter("poHeader.poNo");
	   String isbnFilter = request.getParameter("poHeader.titleDetail.isbn10");
	   String printNoFilter = request.getParameter("poHeader.titleDetail.printNo");
	   String statusFilter = request.getParameter("poHeader.statusDetail.statusDescription");
	   String gangNameFilter = request.getParameter("poHeader.gangName");
	   String jobnoFilter=objpoheader.getJobNo();	   
	   String startDateFilter=(String)objpoForm.getstartDate();
	   String endDateFilter=(String)objpoForm.getendDate();
	   String sbBDateFilter=(String)objpoForm.getsbBDate();
	   String ebBDateFilter=(String)objpoForm.getebBDate();	   
	   //if(request.getParameter(PIXConstants.PAGE_VALUE)!=null)
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
	   /*gang added by gaurav for CES change*/
	   if(PIXUtil.checkNullField(gangNameFilter))
	   {
		   request.setAttribute("gangNameFilter",gangNameFilter);
	   }
	   else if(PIXUtil.checkNullField(request.getParameter("gangNameFilter")))
	   {
		   request.setAttribute("gangNameFilter",request.getParameter("gangNameFilter"));
		   gangNameFilter = request.getParameter("gangNameFilter");
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
	   }
	   else if(PIXUtil.checkNullField(request.getParameter("statusFilter")))
	   {
		   request.setAttribute("statusFilter",request.getParameter("statusFilter"));
		   statusFilter = request.getParameter("statusFilter");
		   objstatus.setStatusDescription(statusFilter);
	   }
	   
	   if(PIXUtil.checkNullField(jobnoFilter))
	   {
		   request.setAttribute("jobnoFilter",jobnoFilter);
	   }
	   else if(PIXUtil.checkNullField(request.getParameter("jobnoFilter")))
	   {
		   request.setAttribute("jobnoFilter",request.getParameter("jobnoFilter"));
		   jobnoFilter = request.getParameter("jobnoFilter");		  
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
	   if(objpoheader==null)
	   {
		   objpoheader = new POHeader();
	   }
	  
	   TitlePrinting td = new TitlePrinting();
	   td.setIsbn10(isbnFilter);
	   td.setPrintNo(printNoFilter);
	   if(PIXUtil.checkNullField(poNumber))
	   {
		   objpoheader.setPoNo(poNumber); 
	   }
	   else
	   {
	   objpoheader.setPoNo(ponoFilter);
	   }
	   /*if(PIXUtil.checkNullField(gangNameFilter))
	   {
		   objpoheader.setGangName(gangNameFilter); 
	   }*/
	   
	   objpoheader.setTitleDetail(td); 
	   objpoheader.setStatusDetail(objstatus);
	   objpoheader.setJobNo(jobnoFilter);
	   startDate=startDateFilter;
	   endDate=endDateFilter;
	   sbBDate=sbBDateFilter;
	   ebBDate=ebBDateFilter;
	   objpoheader.setGangName(gangNameFilter);
	 }
		 else if (form instanceof HomePageForm)//if through HomePage
		   {
			   String statusFilter="";
			   objpoForm = new PurchaseOrderListForm();
			   HomePageForm homePageForm = (HomePageForm)form;
			   objpoheader = new POHeader();
			   String isbnFilter = homePageForm.getIsbn();
			   String printNoFilter = homePageForm.getPrintno();
			   String ponoFilter =homePageForm.getPono() ;
			   String jobnoFilter=homePageForm.getJobno();
			   //String gangNameFilter=homePageForm.getJobno();
			   TitlePrinting td = new TitlePrinting();
			   td.setIsbn10(homePageForm.getIsbn());
			   td.setPrintNo(homePageForm.getPrintno());
			   objpoheader.setTitleDetail(td);
			   objpoheader.setPoNo(homePageForm.getPono());
			   objpoheader.setJobNo(homePageForm.getJobno());
			   Status objStatus=new Status();
			  
			   if(request.getParameter("status")!=null)//parameter thrown through Inbox of homepage
			   {
			   statusFilter=request.getParameter("status");
			   }
			   else
			   {
				  statusFilter=homePageForm.getStatusDescription();
			   }
			   		   
			   if(PIXUtil.checkNullField(statusFilter))
			   {
				   request.setAttribute("statusFilter",statusFilter);
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
			   
			   if(PIXUtil.checkNullField(jobnoFilter))
			   {
				   request.setAttribute("jobnoFilter",jobnoFilter);
			   }
			   else if(PIXUtil.checkNullField(request.getParameter("jobnoFilter")))
			   {
				   request.setAttribute("jobnoFilter",request.getParameter("jobnoFilter"));
				   jobnoFilter = request.getParameter("jobnoFilter");
				  
			   }
			   objpoheader.setStatusDetail(objStatus);
			   request.setAttribute("orderListForm", objpoForm);
			   request.setAttribute("PATH", request.getContextPath());
		   }
		 
		 
		 
		 if(request.getSession().getAttribute("USER_INFO")!=null){	 
		   objCollection = objPurchaseOrderDelegate.displayOrderList(objpoheader,startDate,endDate,orderType,userId,page,currentValue,orderby,sort,sbBDate,ebBDate);
		   int size = objCollection.size();
		   PIXUtil.getNextPage(request,currentValue,size);
		   PIXUtil.getPrevPage(request,currentValue);
		   if(size > PIXConstants.PAGE_SIZE)
	       {           
	        objCollection.remove(((Vector)objCollection).get(size-1));
	       }  
		  Vector objVec= new Vector();
		   for(int j=0;j<objCollection.size();j++)
		   {
			  // String objcost=null;
			   //String objpaper=null;
			   POHeader poHead=(POHeader)((Vector)objCollection).get(j);
			   String sdesc=poHead.getStatusDetail().getStatusDescription();
			   if(sdesc.contains("*"))
			   {
				   poHead.getStatusDetail().setActiveFlag("paper");
				   String newStr=sdesc.replace('*',' ');
				   poHead.getStatusDetail().setStatusDescription(newStr);
			   }
				   //objcost="paper";
			   if(sdesc.contains("$")){
				   //objpaper="cost";
				   poHead.getStatusDetail().setStatusCode("cost");
				   String newStr= sdesc.replace('$',' ');
			     poHead.getStatusDetail().setStatusDescription(newStr);
			   }
			   if(sdesc.contains("*") && sdesc.contains("$")){
				   //objpaper="cost";
				   poHead.getStatusDetail().setStatusCode("papcos");
				   String newStr= sdesc.replace('$',' ');
				   String newStr1= newStr.replace('*',' ');
			     poHead.getStatusDetail().setStatusDescription(newStr1);
			   }
			  // request.setAttribute("Cost",objcost);
			   //request.setAttribute("Paper",objpaper);
			   objVec.add(poHead); 
		   }
		   
		   
		   objpoForm.setpoCollection(objVec);
		   //request.setAttribute("partyType",partyType);
		   request.setAttribute("checkBoxIdList",idHidden);
		 }
	   return FrontEndConstants.LIST;
	   }
	   catch(AppException e)
		 {
		     log.debug("Error occurred while fetching Order List"); 
		     String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderCommand,executeList",e);
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
	   Vector vecLine = null;
	   BigDecimal poId = null;
	   BigDecimal poVersion = null;
	   POParty poParty = null;
	   String delDate = null;
	   String reqDelDate = null;
	   Vector poPriceDetails = null;
	   PurchaseOrderDetailForm poDetailForm = null;
	   PurchaseOrderDetailFormHist poDetailForm1 = null;
	   POHeader poHeader = null;
	   String accessRight = "";
	   String partyComments = "";
	   String lineComments = "";
	   String party = null;
	   String poStatus = "";
	   String jobno="";
	   User objUser = null;
	   Integer userId = null;
	   try
	   {
		   vecParty = new Vector();
		   vecLine = new Vector();
		   	 
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId();
		   }
		   String orderType=request.getParameter("orderType");
		   
		   if((request.getParameter("page").equals("LH")))
		   {	   
		   poDetailForm = ((PurchaseOrderDetailForm)form);
		   
		   if(request.getParameter("partyM")!=null)
			   party=request.getParameter("partyM");
		   if(request.getParameter("poid")!=null)
			   poId = BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));
		   if(request.getParameter("poversion")!=null)
			   poVersion = BigDecimal.valueOf(Long.parseLong(request.getParameter("poversion")));
		   
		   PurchaseOrderDelegate poDelegate = new PurchaseOrderDelegate();
		   if(orderType!=null&&"O".equals(orderType)){
			   party="M" ; 
		   }
		   HashMap poDetailData = poDelegate.displayOrderDetail(poId,poVersion,party,userId,request.getParameter("fo"));
		   poHeader = (POHeader)poDetailData.get(PIXConstants.PO_HEADER);
		   poHeader.setJobNo(PIXUtil.unEscapeHTMLChars(poHeader.getJobNo()));
		   if(poHeader.getStatusDetail()!=null)
		   {
			   poStatus = poHeader.getStatusDetail().getStatusCode();
		   }
			   //For Write access to Purchase Order
		   if(("P".equals(request.getParameter("order")) &&  PIXUtil.checkVendorWriteAccess(request,PIXConstants.PO_CODE)) && (!(PIXConstants.STATUS_HEADER_ORDERDELIVERED).equals(poStatus)))
		   {
			   accessRight = "WRITE";
		   }
		   else
		   {
			   accessRight = "READ";
		   }
		   //For history page
		   if("H".equals(request.getParameter("page")))
		   {
			   request.setAttribute("HistoryFlag","true");
		   }
		   else
		   {
			   request.setAttribute("HistoryFlag","false");
		   }
		   request.setAttribute("POAccessRight",accessRight);
		   int partySize = poHeader.getPartyCollection() == null ? 0 : poHeader.getPartyCollection().size();
		   for(int i=0;i<partySize;i++){
			   	poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			   
				if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE) || poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE) ||poParty.getPartyType().equals(PIXConstants.MILL_ROLE))	//For Buyer and Vendor
				
				{
					delDate = poParty.getDeliveryDate();
					delDate = PIXUtil.sqlToStandardDate(delDate);
					poParty.setDeliveryDate(delDate);
					partyComments = PIXUtil.unEscapeHTMLChars(poParty.getComments());
					poParty.setComments(partyComments);
					vecParty.add(poParty);
				}
				
				//added in if ||poParty.getPartyType().equals(PIXConstants.MILL_ROLE)  @@@
				else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))	//For Ship To
				{
					
					vecParty.add(poParty);
				}
				
		   }
		   int lineItemSize = poHeader.getLineItemCollection() == null ? 0 : poHeader.getLineItemCollection().size();
		   for(int i=0;i<lineItemSize;i++){
			   	POLine poLine = (POLine)((Vector)poHeader.getLineItemCollection()).get(i);
			   	Vector lineSupp= new Vector();
			   	 		String shipdate=null,shipdate1=null;
					   	int lineComp=poLine.getSuppliedCompCollection().size();
					   	for(int k=0;k<lineComp;k++){
					   		POSuppliedComp poSuppComp=(POSuppliedComp)((Vector)poLine.getSuppliedCompCollection()).get(k);
					   	shipdate= poSuppComp.getShipDate();
					   	shipdate1 = PIXUtil.sqlToStandardDate(shipdate);
					   	poSuppComp.setShipDate(shipdate1);
					   	lineSupp.add(poSuppComp);
					   	}
					  	poLine.setSuppliedCompCollection(lineSupp);
							   	
			   	reqDelDate = poLine.getRequestedDeliveryDate();
			   	reqDelDate = PIXUtil.sqlToStandardDate(reqDelDate);
			   	poLine.setRequestedDeliveryDate(reqDelDate);
			   	
				delDate = poLine.getEstimatedDeliveryDate();
				delDate = PIXUtil.sqlToStandardDate(delDate);
				poLine.setEstimatedDeliveryDate(delDate);
				lineComments = PIXUtil.unEscapeHTMLChars(poLine.getSupplierComments());
				jobno=PIXUtil.unEscapeHTMLChars(poLine.getJobNo());
				poLine.setJobNo(jobno);
				poLine.setSupplierComments(lineComments);
				vecLine.add(poLine);
		   }
		   poHeader.setPartyCollection(vecParty);
		   poHeader.setLineItemCollection(vecLine);
		   
		   poDetailForm.setPoHeader(poHeader);
		  // poDetailForm1.setPoHeader(poHeader);
		   
		   //For Header Status
		   Vector poAllHeaderStatus = (Vector)poDetailData.get(PIXConstants.PO_ALL_HEADER_STATUS);
		   poDetailForm.setPoAllHeaderStatus(poAllHeaderStatus);
		  // poDetailForm1.setPoAllHeaderStatus(poAllHeaderStatus);
		   //For Line Items Status
		   Vector poAllLineStatus = (Vector)poDetailData.get(PIXConstants.PO_ALL_LINE_STATUS);
		   poDetailForm.setPoAllLineStatus(poAllLineStatus);
		   //poDetailForm1.setPoAllLineStatus(poAllLineStatus);
		   //For Price details
		  // System.out.println("--++++++++-----"+poDetailData.get(PIXConstants.PO_PRICE_DETAILS));
		   if(poDetailData.get(PIXConstants.PO_PRICE_DETAILS)!=null){
			   poPriceDetails = (Vector)poDetailData.get("PO_PRICE_DETAILS");
			   poDetailForm.setPoPriceDetails(poPriceDetails);
			  // poDetailForm1.setPoPriceDetails(poPriceDetails);
			   if("P".equals(request.getParameter("order")))
				{
				   poDetailForm.setPocosting("P");
				 //  poDetailForm1.setPocosting("P");
				   
				}
			   else 
			   {
				   poDetailForm.setPocosting("F");
				  // poDetailForm1.setPocosting("F");
			   }
		   }

		   
		   
		   }
		   else
		   {
		   poDetailForm1 = ((PurchaseOrderDetailFormHist)form);
		   
		   if(request.getParameter("partyM")!=null)
			   party=request.getParameter("partyM");
		   if(request.getParameter("poid")!=null)
			   poId = BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));
		   if(request.getParameter("poversion")!=null)
			   poVersion = BigDecimal.valueOf(Long.parseLong(request.getParameter("poversion")));
		   
		   PurchaseOrderDelegate poDelegate = new PurchaseOrderDelegate();
		   HashMap poDetailData = poDelegate.displayOrderDetail(poId,poVersion,party,userId,request.getParameter("fo"));
		   poHeader = (POHeader)poDetailData.get(PIXConstants.PO_HEADER);
		   poHeader.setJobNo(PIXUtil.unEscapeHTMLChars(poHeader.getJobNo()));
		   if(poHeader.getStatusDetail()!=null)
		   {
			   poStatus = poHeader.getStatusDetail().getStatusCode();
		   }
			   //For Write access to Purchase Order
		   if(("P".equals(request.getParameter("order")) &&  PIXUtil.checkVendorWriteAccess(request,PIXConstants.PO_CODE)) && (!(PIXConstants.STATUS_HEADER_ORDERDELIVERED).equals(poStatus)))
		   {
			   accessRight = "WRITE";
		   }
		   else
		   {
			   accessRight = "READ";
		   }
		   //For history page
		   if("H".equals(request.getParameter("page")))
		   {
			   request.setAttribute("HistoryFlag","true");
		   }
		   else
		   {
			   request.setAttribute("HistoryFlag","false");
		   }
		   request.setAttribute("POAccessRight",accessRight);
		   
		   int partySize = poHeader.getPartyCollection() == null ? 0 : poHeader.getPartyCollection().size();
		   for(int i=0;i<partySize;i++){
			   	poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			   
				if("M".equals(party))		//in case Mill user is logged in
				{	
				   	if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE) || poParty.getPartyType().equals(PIXConstants.MILL_ROLE))	//For Buyer and Vendor
					
					{
						delDate = poParty.getDeliveryDate();
						delDate = PIXUtil.sqlToStandardDate(delDate);
						poParty.setDeliveryDate(delDate);
						partyComments = PIXUtil.unEscapeHTMLChars(poParty.getComments());
						poParty.setComments(partyComments);
						vecParty.add(poParty);
					}
									
					else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))	//For Ship To
					{
						vecParty.add(poParty);
					}
				}
				else
				{
					if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE) || poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))	//For Buyer and Vendor
						
					{
						delDate = poParty.getDeliveryDate();
						delDate = PIXUtil.sqlToStandardDate(delDate);
						poParty.setDeliveryDate(delDate);
						partyComments = PIXUtil.unEscapeHTMLChars(poParty.getComments());
						poParty.setComments(partyComments);
						vecParty.add(poParty);
					}
									
					else if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))	//For Ship To
					{
						vecParty.add(poParty);
					}
				}
				
		   }
		   int lineItemSize = poHeader.getLineItemCollection() == null ? 0 : poHeader.getLineItemCollection().size();
		   for(int i=0;i<lineItemSize;i++){
			   	POLine poLine = (POLine)((Vector)poHeader.getLineItemCollection()).get(i);
			   	Vector lineSupp= new Vector();
			   	 		String shipdate=null,shipdate1=null;
					   	int lineComp=poLine.getSuppliedCompCollection().size();
					   	for(int k=0;k<lineComp;k++){
					   		POSuppliedComp poSuppComp=(POSuppliedComp)((Vector)poLine.getSuppliedCompCollection()).get(k);
					   	shipdate= poSuppComp.getShipDate();
					   	shipdate1 = PIXUtil.sqlToStandardDate(shipdate);
					   	poSuppComp.setShipDate(shipdate1);
					   	lineSupp.add(poSuppComp);
					   	}
					  	poLine.setSuppliedCompCollection(lineSupp);
							   	
			   	reqDelDate = poLine.getRequestedDeliveryDate();
			   	reqDelDate = PIXUtil.sqlToStandardDate(reqDelDate);
			   	poLine.setRequestedDeliveryDate(reqDelDate);
			   	
				delDate = poLine.getEstimatedDeliveryDate();
				delDate = PIXUtil.sqlToStandardDate(delDate);
				poLine.setEstimatedDeliveryDate(delDate);
				lineComments = PIXUtil.unEscapeHTMLChars(poLine.getSupplierComments());
				jobno=PIXUtil.unEscapeHTMLChars(poLine.getJobNo());
				poLine.setJobNo(jobno);
				poLine.setSupplierComments(lineComments);
				vecLine.add(poLine);
		   }
		   poHeader.setPartyCollection(vecParty);
		   poHeader.setLineItemCollection(vecLine);
		   
		   //poDetailForm.setPoHeader(poHeader);
		   poDetailForm1.setPoHeader(poHeader);
		   
		   //For Header Status
		   Vector poAllHeaderStatus = (Vector)poDetailData.get(PIXConstants.PO_ALL_HEADER_STATUS);
		  // poDetailForm.setPoAllHeaderStatus(poAllHeaderStatus);
		   poDetailForm1.setPoAllHeaderStatus(poAllHeaderStatus);
		   //For Line Items Status
		   Vector poAllLineStatus = (Vector)poDetailData.get(PIXConstants.PO_ALL_LINE_STATUS);
		   //poDetailForm.setPoAllLineStatus(poAllLineStatus);
		   poDetailForm1.setPoAllLineStatus(poAllLineStatus);
		   //For Price details
		  // System.out.println("----------"+poDetailData.get(PIXConstants.PO_PRICE_DETAILS));
		   if(poDetailData.get(PIXConstants.PO_PRICE_DETAILS)!=null){
			   poPriceDetails = (Vector)poDetailData.get("PO_PRICE_DETAILS");
			  // poDetailForm.setPoPriceDetails(poPriceDetails);
			   poDetailForm1.setPoPriceDetails(poPriceDetails);
			   if("P".equals(request.getParameter("order")))
				{
				  // poDetailForm.setPocosting("P");
				   poDetailForm1.setPocosting("P");
				   
				}
			   else 
			   {
				   //poDetailForm.setPocosting("F");
				   poDetailForm1.setPocosting("F");
			   }
		   }
		   } 
		   
		   if(orderType!=null&&"O".equals(orderType)){
			   return "displaypaper";
		   }else{
		   
		   return FrontEndConstants.DISPLAY;
		   }
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderCommand,executeDisplay",e);
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
	   POLine poLine = null;
	   POParty poParty = null;
	   User objUser = null;
	   String login = "";
	   String party=null;
	   String partyComments = "";
	   String lineComments = "";
	   String url= "/purchaseorder/purchaseorderlist.do?&party=V" ;
	   try
	   {
		   
		   if(request.getParameter("partyM")!=null)
	    	{
			   party = request.getParameter("partyM");
			   url="/purchaseorder/millpurchaseorderlist.do?&party=M" ;
	    	}
		   
		   if(PIXUtil.checkNullField(request.getParameter("PAGE_VALUE")))
		   {
			   String page_value = request.getParameter("PAGE_VALUE");
			   String list_page ="&PAGE_VALUE="+page_value;
			    if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")) & !PIXUtil.checkNullField(request.getParameter("sbBDateFilter")) & !PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
			    {
			    	
					request.setAttribute(PIXConstants.OK_URL,url+"&PAGE_VALUE="+page_value);	//For "Ok" button on Success/Error page
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
			    	
			    	request.setAttribute(PIXConstants.OK_URL,url+list_page);	//For "Ok" button on Success/Error page
			    }
		   }
		   else
		   {
			   request.setAttribute(PIXConstants.OK_URL,url+"&PAGE_VALUE=1");
		   }
		   
		   //For "Ok" button on Success/Error page
		   PurchaseOrderDetailForm poDetailForm = ((PurchaseOrderDetailForm)form);
		   
		  
		   POHeader poHeader = poDetailForm.getPoHeader();
		   
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   //login = objUser.getLogin();
			   login = objUser.getFirstName()+" "+objUser.getLastName();
		   }
		   else{	//User's session has expired.
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.SESSION_EXCEPTION,"PurchaseOrderCommand,executeInsert");
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
		   poHeader.setJobNo(PIXUtil.escapeHTMLChars(poHeader.getJobNo()));
		   PurchaseOrderDelegate poDelegate = new PurchaseOrderDelegate();
		   poHeader.setModifiedBy(login);
		   int partySize = poHeader.getPartyCollection() == null ? 0 : poHeader.getPartyCollection().size();
		   for(int i=0;i<partySize;i++){
			   poParty = (POParty)((Vector)poHeader.getPartyCollection()).get(i);
			  
			   if(poParty.getPartyType().equals("V")||poParty.getPartyType().equals("M"))
			   {
				   poParty.setModifiedBy(login);
				   partyComments = PIXUtil.escapeHTMLChars(poParty.getComments());
				   poParty.setComments(partyComments);
				   
			   }
		   }
		   int lineItemSize = poHeader.getLineItemCollection() == null ? 0 : poHeader.getLineItemCollection().size();
		   for(int i=0;i<lineItemSize;i++){
			   	poLine = (POLine)((Vector)poHeader.getLineItemCollection()).get(i);
			   	poLine.setModifiedBy(login);
			   	lineComments = PIXUtil.escapeHTMLChars(poLine.getSupplierComments());
			   	poLine.setSupplierComments(lineComments);
		   }
		   
		   String poNumber = poDelegate.saveOrderConfirmation(poHeader,party);
		   String messageKey = "Purchase Order No. "+poNumber+" has been successfully saved.";
		   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
		   return FrontEndConstants.INSERT;
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderCommand,executeInsert",e);
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
   /*method to be used to ferch the status list for respective modules to be used in drop downs*/
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse reponse, final ActionMessages messages) 
   {
	   try
	   {
		   User objUser=null;
		   int userId=0;
		   if(request.getSession().getAttribute("USER_INFO")!=null){
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId().intValue();
		   }
		   PurchaseOrderDelegate objPurchaseOrderDelegate = new PurchaseOrderDelegate();
		   Vector objAllStatus=(Vector)objPurchaseOrderDelegate.displayPurchaseOrderStatus();
		   request.setAttribute("poAllStatus",objAllStatus);
		  
			   
		  
		if((form instanceof HomePageForm)&&(request.getSession().getAttribute("USER_INFO")!=null)) //fetching status list for transaction type on HomePage*/
		   {
			   Vector objInboxdetail=new Vector();
			   HomePageDelegate objHomePageDelegate=new HomePageDelegate();
			   if(request.getSession().getAttribute("USER_INFO")!=null){	//added by Shweta for session timeput handling
			   	   objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
			   	if(objInboxdetail.size()==0) //Checking condition for no records in inbox
				{
					request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
				}
			       request.setAttribute("inboxdetails",objInboxdetail);
			   }
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderCommand,executeRelatedList",e);
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
   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
	   BigDecimal poId = null;
	   BigDecimal poVersion = null;
	   User objUser = null;
	   String login = "";
	   String partyM=null ;
	   String url= "/purchaseorder/purchaseorderlist.do?&party=V" ;
	   try
	   {
		   if(request.getParameter("partyM")!=null){
			   partyM=request.getParameter("partyM");
			   url="/purchaseorder/millpurchaseorderlist.do?&party=M" ;
		   }
		   if("suppliedcomp".equals(actioncommand))	//Handling for Supplied Components
		   {
			   PurchaseOrderDetailForm poDetailForm = ((PurchaseOrderDetailForm)form);
			   if(request.getParameter("poid")!=null)
				   poId = BigDecimal.valueOf(Long.parseLong(request.getParameter("poid")));
			   if(request.getParameter("poversion")!=null)
				   poVersion = BigDecimal.valueOf(Long.parseLong(request.getParameter("poversion")));
			   
			   PurchaseOrderDelegate poDelegate = new PurchaseOrderDelegate();
			   Vector lineItems = (Vector)poDelegate.displayOrderLineItems(poId,poVersion);
			   
			   Vector lineSupp1= new Vector();
			   String shipdate=null,shipdate1=null;
			   int lineCompSize = lineItems.size() == 0 ? 0 : lineItems.size();
			   for(int j=0;j<lineCompSize;j++){
				   Vector lineSupp= new Vector();
				   POLine poline = (POLine)(lineItems.get(j));
				   	int lineComp=poline.getSuppliedCompCollection().size();
				   	for(int k=0;k<lineComp;k++){
				   		POSuppliedComp poSuppComp=(POSuppliedComp)((Vector)poline.getSuppliedCompCollection()).get(k);
				   	shipdate= poSuppComp.getShipDate();
				   	shipdate1 = PIXUtil.sqlToStandardDate(shipdate);
				 	poSuppComp.setShipDate(shipdate1);
				   	lineSupp.add(poSuppComp);
				   			   	
				   	}
				   	poline.setSuppliedCompCollection(lineSupp);
				   	lineSupp1.add(poline);
				   			   	
			   }
			   
			   poDetailForm.setLineItems(lineSupp1);
			   
			  return "suppliedcomp";
		   }
		   else if("multiordersAccept".equals(actioncommand))	//Multiple Orders Acceptance through List page
		   {
			   if(PIXUtil.checkNullField(request.getParameter("page_order_list")))
			   {
				   String page_order_list = request.getParameter("page_order_list");
				   String list_page ="&PAGE_VALUE="+page_order_list;
				    if(!PIXUtil.checkNullField(request.getParameter("pageFilter")) & !PIXUtil.checkNullField(request.getParameter("ponoFilter")) & !PIXUtil.checkNullField(request.getParameter("isbnFilter")) & !PIXUtil.checkNullField(request.getParameter("printNoFilter")) & !PIXUtil.checkNullField(request.getParameter("statusFilter")) & !PIXUtil.checkNullField(request.getParameter("startDateFilter")) & !PIXUtil.checkNullField(request.getParameter("endDateFilter")) & !PIXUtil.checkNullField(request.getParameter("sbBDateFilter")) & !PIXUtil.checkNullField(request.getParameter("ebBDateFilter")))
				    {
						// request.setAttribute(PIXConstants.OK_URL,"/purchaseorder/purchaseorderlist.do?PAGE_VALUE="+page_order_list+"&party=V");	//For "Ok" button on Success/Error page
				    	request.setAttribute(PIXConstants.OK_URL,url+"&PAGE_VALUE="+page_order_list);	//For "Ok" button on Success/Error page
				    
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
				    	
				    	//request.setAttribute(PIXConstants.OK_URL,"/purchaseorder/purchaseorderlist.do"+list_page+"&party=V");	//For "Ok" button on Success/Error page
				    	request.setAttribute(PIXConstants.OK_URL,url+list_page);	//For "Ok" button on Success/Error page
				    }
			   }
			   else
			   {
				   
				   //request.setAttribute(PIXConstants.OK_URL,"/purchaseorder/purchaseorderlist.do?PAGE_VALUE=1&party=V");
				   request.setAttribute(PIXConstants.OK_URL,url+"&PAGE_VALUE=1");
			   }
			   //For "Ok" button on Success/Error page
			   
			   String strSelectedCheckboxes = request.getParameter("checksArr");
			   if(request.getSession().getAttribute("USER_INFO")!=null){
				   objUser = (User)request.getSession().getAttribute("USER_INFO");
				   login = objUser.getLogin();
			   }
			   PurchaseOrderDelegate poDelegate = new PurchaseOrderDelegate();
			   poDelegate.insertMultiplePOAccept(strSelectedCheckboxes,login);
			   String messageKey = "Purchase Orders have been successfully Accepted.";
			   request.setAttribute(PIXConstants.SUCCESS_STRING,messageKey);	//For message on Success page
			   return "multiordersAccept";
		   }		   
		   return "";
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
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderCommand,executeGeneral",e);
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
	   try
		  {
		   PurchaseOrderDetailForm poDetailForm = ((PurchaseOrderDetailForm)form);
		   
		   if(poDetailForm.getPoHeader().getOrderType().equalsIgnoreCase("o"))
		   {
  			 String reportName="PurchaseOrderMill";
		   
  			PurchaseOrderMillPdf objPurchaseOrderMillPdf = new PurchaseOrderMillPdf();
 			    request.getSession().setAttribute("PDF_OBJECT",objPurchaseOrderMillPdf);
 	  		    request.getSession().setAttribute("PDF_Name",reportName);
 	  		    response.sendRedirect("../pdfFileDownloadServlet");
		   }
		   else{
			    String reportName="PurchaseOrder";
			    PurchaseOrderPdf objPurchaseOrderPdf = new PurchaseOrderPdf();
			    request.getSession().setAttribute("PDF_OBJECT",objPurchaseOrderPdf);
	  		    request.getSession().setAttribute("PDF_Name",reportName);
	  		    response.sendRedirect("../pdfFileDownloadServlet");
		   }
  		    
		   /*if(poDetailForm.getPoHeader().getOrderType().equalsIgnoreCase("o"))
		   {
			   PurchaseOrderMillPdf objPurchaseOrderMillPdf = new PurchaseOrderMillPdf();
				objDocument = objPurchaseOrderMillPdf.display(request,response);
				objDocument.close();
		  		response.getOutputStream().close();
		   }
		   else{
			   PurchaseOrderPdf objPurchaseOrderPdf = new PurchaseOrderPdf();
				objDocument = objPurchaseOrderPdf.display(request,response);
				objDocument.close();
		  		response.getOutputStream().close();
		   }*/
		   
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
   /**
    * Method for display of Purchase Order
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   private String executePODisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
    return null;
   }
   
   /**
    * Method for display of Furnished Order
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   private String executeFODisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	 return null;
   }
}
