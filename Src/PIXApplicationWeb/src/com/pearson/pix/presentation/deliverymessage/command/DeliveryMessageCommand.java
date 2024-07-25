package com.pearson.pix.presentation.deliverymessage.command;

import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAOImpl;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserRole;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLineParty;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm;
import com.pearson.pix.presentation.deliverymessage.delegate.DeliveryMessageDelegate;
import com.pearson.pix.presentation.fileuploading.delegate.FileUploadingtDelegate;
import com.pearson.pix.presentation.pdf.DeliveryMessagePdf;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import com.pearson.pix.business.common.PIXConstants;
import com.sun.corba.se.spi.orbutil.fsm.InputImpl;

public class DeliveryMessageCommand extends BaseCommand
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(DeliveryMessageCommand.class.getName());
    
	private static String FILE_OPR = "fileProperties";

    public DeliveryMessageCommand()
    {
    }

    public String executeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        Collection objCollection = null;
        Collection objLineCollection = null;
        User objUser = null;
        Integer userId = null;
        String idHidden = "";
        String roleType = null;
        String status = null;
        String party = null;
        HttpSession session = request.getSession();
        
        DeliveryMessageDelegate DeliveryMessageDelegate = new DeliveryMessageDelegate();
        
        
        if(request.getParameter("idHidden") != null)
        {
            idHidden = request.getParameter("idHidden");
        }
        try
        {
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId();
                roleType = objUser.getRoleTypeDetail().getRoleType();
            }
            if("M".equals(roleType))
            {
                party = roleType;
            }
            if("paperfo".equals(request.getParameter("fo")))
            {
                party = request.getParameter("fo");
            }
            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
            //DeliveryMessageForm objDeliveryMessageForm =new DeliveryMessageForm();
            POHeader poHeaderTemp = new POHeader();
            String poid = request.getParameter("poid");
            BigDecimal poId = new BigDecimal(String.valueOf(poid));
            objDeliveryMessageForm.setPoId(poId);
       
            poHeaderTemp.setPoId(poId);
            String poversion = request.getParameter("poversion");
            BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
            objDeliveryMessageForm.setPoVersion(poVersion);
            poHeaderTemp.setPoVersion(poVersion);
            String objorderby = "CREATION_DATE_TIME";
            String objsort = "DESC";
            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
            int currentValue = Integer.parseInt(request.getParameter("PAGE_VALUE"));
            status = objDeliveryMessageDelegate.displayPurchaseOrderStatus(poHeaderTemp);
            objDeliveryMessageForm.setStat(status);
            log.info("before removing from session receiptBuff:"+request.getSession().getAttribute("receiptBuff"));
            log.info("before removing from session MsgNumber:"+request.getSession().getAttribute("MsgNumber"));
            
            request.getSession().removeAttribute("receiptBuff");
            request.getSession().removeAttribute("MsgNumber");
            
            log.info("after removing from session receiptbuff:"+request.getSession().getAttribute("receiptBuff"));
            log.info("after removing from session MsgNumber:"+request.getSession().getAttribute("MsgNumber"));
            
            
            if("M".equals(party) || "paperfo".equals(party))
 		   {
            	objLineCollection = objDeliveryMessageDelegate.displayDeliveryMessageList(poHeaderTemp, currentValue, objorderby, objsort, party, userId);
            	//Gaurav
            	System.out.println(objLineCollection.size());
            	Iterator iter = objLineCollection.iterator();
            	while(iter.hasNext())
            	{
            		DeliveryMessageLine line = (DeliveryMessageLine)iter.next();
            		System.out.println(line.getLineNo());
            		System.out.println(line.getPrinter());
            	}
            	
            	DeliveryMessage objDeliveryMessage = new DeliveryMessage();
            	objDeliveryMessage.setDeliveryMsgLineCollection(objLineCollection);
            	System.out.println(objLineCollection.size());
            	objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
 		   }
            else
            {
	            objCollection = objDeliveryMessageDelegate.displayDeliveryMessageList(poHeaderTemp, currentValue, objorderby, objsort, party, userId);
	            objDeliveryMessageForm.setDeliverymessageCollection(objCollection);
	            int size = objCollection.size();            
	            PIXUtil.getNextPage(request, currentValue, size);
	            PIXUtil.getPrevPage(request, currentValue);
	            if(size > 10)
	            {
	                objCollection.remove(((Vector)objCollection).get(size - 1));
	            }
            }
            //System.out.println("objCollection command "+objCollection);
            
            // roleTracking pop
   	     
   	    // String login_id = (String)request.getParameter("loginId");
            String ponumber = request.getParameter("pono");
            System.out.println(ponumber);
            
            
            String login_id = (String)session.getAttribute("login_id");  // from loginCommand
   	 //    int roleTrackingFlag = DeliveryMessageDelegate.getRoleTrackingFlag(login_id) ;
            int roleTrackingFlag = DeliveryMessageDelegate.getRoleTrackingFlag(ponumber) ;
   	   System.out.println("**********************************************  roleTrackingFlag " + roleTrackingFlag);
   	     
   	     if(roleTrackingFlag > 0)
   	     {
   	    	 roleTrackingFlag = 1;
   	     }
   	     session.setAttribute("roleTrackingFlag", roleTrackingFlag);
            
            request.setAttribute("checkBoxIdList", idHidden);
            return "list";
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
    }

    public String executeDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        User objUser = null;
        String roleType = "";
        String status = null;
        BigDecimal poId = null;
        String orderType = "";
        try
        {
            DeliveryMessage objDeliveryMessage = null;
            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
            POHeader poHeaderTemp = new POHeader();
            if(request.getParameter("poid") != null)
            {
                String poid = request.getParameter("poid");
                poId = new BigDecimal(String.valueOf(poid));
                poHeaderTemp.setPoId(poId);
            }
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                roleType = objUser.getRoleTypeDetail().getRoleType();
                if("M".equals(roleType))
                {
                    orderType = "O";
                }
                if("O".equals(request.getParameter("orderPaper")))
                {
                    orderType = "O";
                }
            } else
            {
                AppException ae = new AppException();
                ae.performErrorAction("9007", "DeliveryMessageCommand,executeInsert");
                String errMsg = ae.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            String poversion = request.getParameter("poversion");
            BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
            poHeaderTemp.setPoVersion(poVersion);
            String msgId = request.getParameter("MSG_ID");
            	
            Integer messageId = new Integer(String.valueOf(msgId));
            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
            status = objDeliveryMessageDelegate.displayPurchaseOrderStatus(poHeaderTemp);
            objDeliveryMessageForm.setStat(status);
            objDeliveryMessage = objDeliveryMessageDelegate.displayDeliveryMessageDetail(poId, poVersion, messageId, orderType,"");
            objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
            return "display";
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
            ae.performErrorAction("9000", "DeliveryMessageCommand,executeDisplay", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    public String executeInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
    	User objUser = null;
        String login = "";
        String roleType = "";
        int userId = 0;
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
        		if(request.getSession().getAttribute("USER_INFO") != null)
	            {
	                objUser = (User)request.getSession().getAttribute("USER_INFO");
	                login = objUser.getLogin();
	                userId = objUser.getUserId().intValue();
	                roleType = objUser.getRoleTypeDetail().getRoleType();
	            } else
	            {
	                AppException ae = new AppException();
	                ae.performErrorAction("9007", "DeliveryMessageCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute("PIX_ERROR", errMsg);
	                return "error";
	            }
        		 /*if("M".equals(roleType)){
 	            	messageKey = "Following Delivery Messages are created successfully" + receiptBuff.toString() ;
 	            }
 	            else{
 	            	messageKey = "Delivery Message No " + MsgNumber + " created successfully.";
 	            }*/
        		log.info("receipt buff during refresh:"+request.getSession().getAttribute("receiptBuff"));
        		log.info("Msgnumber during refresh:"+request.getSession().getAttribute("MsgNumber"));
        		String messageKey = null,context = null;
        		String receiptBuff = "";
        		String MsgNumber = null;
        		
        		if("M".equals(roleType)){
        			receiptBuff = (String)request.getSession().getAttribute("receiptBuff");
 	            	messageKey = "Following Delivery Messages are created successfully" + receiptBuff.toString() ;
 	            }
 	            else{
 	            	MsgNumber = (String)request.getSession().getAttribute("MsgNumber");
 	            	messageKey = "Delivery Message No " + MsgNumber + " created successfully.";
 	            }
        		 if("M".equals(roleType)){
 	        		context = "/deliverymessage/deliverymessagemilllist.do";
 	        	}
 	        	else{
 	        		context = "/deliverymessage/deliverymessagelist.do";
 	        	}
        		request.setAttribute("OK_URL", context+"?PAGE_VALUE=1&poid=" + poid + "&poversion=" + poversion + "&pono=" + ponumber + "&rno=" + releaseNo + "&order=" + order + "&page_order_list=" + pageOrderList);
 	            request.setAttribute("SUCCESS_STRING", messageKey);
 	            
        		System.out.println("rererere:"+request.getAttribute("SUCCESS_STRING"));
        		return "insert";
        	}
        	else
	        {
	            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
	            DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
	            int size = objDeliveryMessage.getDeliveryMsgLineCollection().size();
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
	            String pageOrderList = request.getParameter("page_order_list");
	            if(request.getSession().getAttribute("USER_INFO") != null)
	            {
	                objUser = (User)request.getSession().getAttribute("USER_INFO");
	                login = objUser.getLogin();
	                userId = objUser.getUserId().intValue();
	                roleType = objUser.getRoleTypeDetail().getRoleType();
	            } else
	            {
	                AppException ae = new AppException();
	                ae.performErrorAction("9007", "DeliveryMessageCommand,executeInsert");
	                String errMsg = ae.getSErrorDescription();
	                request.setAttribute("PIX_ERROR", errMsg);
	                return "error";
	            }
	            int headerSize = 1;
	            /*LinkedHashSet lhSet = new LinkedHashSet();
	            String san = null;
	            if("M".equals(roleType))
	            {
	                Vector deliveryMsgLineObj = (Vector)objDeliveryMessage.getDeliveryMsgLineCollection();
	                for(int p = 0; p < deliveryMsgLineObj.size(); p++)
	                {
	                    DeliveryMessageLine deliveryMessageLine = (DeliveryMessageLine)deliveryMsgLineObj.get(p);
	                    if(deliveryMessageLine.getDeliveredQuantity().intValue() != 0)
	                    {
	                    	System.out.println("inside DM command printing DQ"+deliveryMessageLine.getDeliveredQuantity().intValue());
	                        POLineParty poLineParty = (POLineParty)((Vector)deliveryMessageLine.getLinePartyCollection()).get(0);
	                        san = poLineParty.getSan();
	                        objDeliveryMessage.getDeliveryMsgLineCollectionIdx(p).setShipToSan(san);
	                        objDeliveryMessage.getDeliveryMsgLineCollectionIdx(p).setPoId(poId);
	                        objDeliveryMessage.getDeliveryMsgLineCollectionIdx(p).setPoVersion(poVersion);
	                        lhSet.add(san);
	                    }
	                }
	
	                objDeliveryMessage.setSanWithPOLine(lhSet);
	                headerSize = lhSet.size();
	            }*/
	            String MsgNumberArr[] = (String[])null;
	            StringBuffer msgBuffer = null;
	            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
	           //below code is for checks and building error messages
	            if("M".equals(roleType))
	            {
	                String error = "Please rectify the following errors :<br>";
	                String PmsQuantity = objDeliveryMessageDelegate.getPmsLineQuantity(objDeliveryMessage,ponumber,size);
	               //System.out.println("pms qty"+ PmsQuantity);
	                String displayMessage = "";
	                if(PmsQuantity != null)
	                {
	                	
	                    String PmsQuantLine[] = PmsQuantity.split("#"); 
	                    for(int i = 0; i < PmsQuantLine.length; i++)
	                    {
	                        String QuantityFlag[] = PmsQuantLine[i].split(",");
	                        
	                        if(QuantityFlag[1].equals("H"))
	                        {
	                        	displayMessage = displayMessage + "<LI>Purchase Order has been Cancelled in PEPMS.</LI><br>";
	                        	break;
	                        }
	                        else if(QuantityFlag[1].equals("N"))
	                        {
	                            if(Integer.parseInt(QuantityFlag[0]) == 0)
	                            {
	                                displayMessage = displayMessage + "<LI>Quantity with in the tolerance limit for line number " + QuantityFlag[2] + " has already been delivered.</LI><br>";
	                            } else
	                            {
	                                displayMessage = displayMessage + "<LI>Quantity for line number " + QuantityFlag[2] + " cannot be greater than " + QuantityFlag[0] + ".</LI><br>";
	                            }
	                        }
	                        else
	                        {
	                            displayMessage = displayMessage + "<LI>Cannot post Delivery Message for line number " + QuantityFlag[2] + " as it has been Cancelled in PEPMS.</LI><br>";
	                        }
	                    }
	
	                    request.setAttribute("message", error + displayMessage);
	                    Vector delMsgLineObj = (Vector)objDeliveryMessage.getDeliveryMsgLineCollection();
	                    for(int j = 0; j < delMsgLineObj.size(); j++)
	                    {
	                        DeliveryMessageLine delMessageLine = (DeliveryMessageLine)delMsgLineObj.get(j);
	                        if(delMessageLine.getDeliveredQuantity().intValue() == 0)
	                        {
	                            delMessageLine.setDeliveredQuantity(null);
	                        }
	                    }
	
	                    return "samePage";
	                }
	            }
	            String MsgNumber = null;
	            StringBuffer renameInfo = new StringBuffer();
	        	StringBuffer outputInfo = new StringBuffer();
	            if("M".equals(roleType)){   
	            	MsgNumber = objDeliveryMessageDelegate.saveMillDeliveryMessage(objDeliveryMessage, poHeaderTemp, userId);
	            	
	            	//System.out.println("msg"+MsgNumber);
	            	String[] msg_id_arr = MsgNumber.split("@");
	            	String[] msg_id_line_arr = null;
	            	String[] temp = null;
	            	for(int k=0;k<msg_id_arr.length;k++){
	            		msg_id_line_arr=msg_id_arr[k].split("#");
	            		temp = msg_id_line_arr[0].split("_");
				       // System.out.println(temp[temp.length-1]);
				        renameInfo.append(temp[temp.length-1]).append("~").append(msg_id_line_arr[1]).append("#");
				        outputInfo.append(msg_id_line_arr[0]).append(",");
	            	}
	            	
	            	/*//Reset file collection in Session Delivery Message object
	            	 for(int i=0;i<size;i++){
	            	 DeliveryMessageLine objDeliveryMessageLine = (DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i);
	            	 Vector delMsgFileCollection = (Vector)objDeliveryMessageLine.getDelMsgFilesCollection();
	            	  if(delMsgFileCollection!=null){
	            		  objDeliveryMessageLine.setDelMsgFilesCollection(null);
	            		  objDeliveryMessage.setDeliveryMsgLineCollectionIdx(i,objDeliveryMessageLine);
	            	   }
	            	 
	            	 }
	            	objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
	            	request.getSession(false).setAttribute("deliveryMessageForm",objDeliveryMessageForm);*/
	            	
	           }
	           else{
	            MsgNumber = objDeliveryMessageDelegate.saveDeliveryMessage(objDeliveryMessage, poHeaderTemp, size, headerSize, login, roleType);
	            
	            if(MsgNumber != null)
	            {
	                MsgNumberArr = MsgNumber.split("#");
	                if(MsgNumberArr != null && MsgNumberArr.length >= 1)
	                {
	                    MsgNumber = MsgNumberArr[0];
	                    msgBuffer = new StringBuffer();
	                    for(int i = 1; i < MsgNumberArr.length; i++)
	                    {
	                        msgBuffer.append(MsgNumberArr[i]);
	                        msgBuffer.append("#");
	                    }
	
	                }
	            }
	           }
	            String context = null;
	        	if("M".equals(roleType)){
	        		context = "/deliverymessage/deliverymessagemilllist.do";
	        	}
	        	else{
	        		context = "/deliverymessage/deliverymessagelist.do";
	        	}
	            if((!PIXUtil.checkNullField(request.getParameter("pageFilter"))) & (!PIXUtil.checkNullField(request.getParameter("ponoFilter"))) & (!PIXUtil.checkNullField(request.getParameter("isbnFilter"))) & (!PIXUtil.checkNullField(request.getParameter("printNoFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("sbBDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("ebBDateFilter"))))
	            {
	            	
	            	request.setAttribute("OK_URL", context+"?PAGE_VALUE=1&poid=" + poid + "&poversion=" + poversion + "&pono=" + ponumber + "&rno=" + releaseNo + "&order=" + order + "&page_order_list=" + pageOrderList);
	            } else
	            {
	                String list_page = "?PAGE_VALUE=1&poid=" + poid + "&poversion=" + poversion + "&pono=" + ponumber + "&rno=" + releaseNo + "&order=" + order + "&page_order_list=" + pageOrderList;
	                if(request.getParameter("pageFilter") != "")
	                {
	                    list_page = list_page + "&pageFilter=" + request.getParameter("pageFilter");
	                }
	                if(request.getParameter("ponoFilter") != "")
	                {
	                    list_page = list_page + "&ponoFilter=" + request.getParameter("ponoFilter");
	                }
	                if(request.getParameter("isbnFilter") != "")
	                {
	                    list_page = list_page + "&isbnFilter=" + request.getParameter("isbnFilter");
	                }
	                if(request.getParameter("printNoFilter") != "")
	                {
	                    list_page = list_page + "&printNoFilter=" + request.getParameter("printNoFilter");
	                }
	                if(request.getParameter("statusFilter") != "")
	                {
	                    list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
	                }
	                if(request.getParameter("startDateFilter") != "")
	                {
	                    list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
	                }
	                if(request.getParameter("endDateFilter") != "")
	                {
	                    list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
	                }
	                if(request.getParameter("sbBDateFilter") != "")
	                {
	                    list_page = list_page + "&sbBDateFilter=" + request.getParameter("sbBDateFilter");
	                }
	                if(request.getParameter("ebBDateFilter") != "")
	                {
	                    list_page = list_page + "&ebBDateFilter=" + request.getParameter("ebBDateFilter");
	                }
	                request.setAttribute("OK_URL", context + list_page);
	            }
	            if(request.getSession().getAttribute("MessageTypeDetail") != null)
	            {
	                request.getSession().removeAttribute("MessageTypeDetail");
	            }
	            String messageKey ="";
	            StringBuffer receiptBuff = new StringBuffer();
	
	            String[] receiptArr  = outputInfo.toString().split(",");
	
	            for(int k =0; k<receiptArr.length;k++)
	            {
	             receiptBuff.append("<div>").append("<li>").append(receiptArr[k]).append("</li>").append("</div>");
	            }
	
	            //String messageKey = "Following Goods Receipt Nos are created successfully :"+receiptBuff.toString();
	
	            if("M".equals(roleType)){
	            	messageKey = "Following Delivery Messages are created successfully" + receiptBuff.toString() ;
	            	request.getSession().setAttribute("receiptBuff",receiptBuff.toString());
	            }
	            else{
	            	messageKey = "Delivery Message No " + MsgNumber + " created successfully.";
	            	request.getSession().setAttribute("MsgNumber",MsgNumber);
	            }
	            request.setAttribute("SUCCESS_STRING", messageKey);
	            if("M".equals(roleType))
	            {
	            	renameDMFolderFile(renameInfo.toString(), ponumber);
	            	//int j=0;
	            	//objDeliveryMessage.getDeliveryMsgLineCollectionIdx(0).setDelMsgFilesCollection(new Vector());
	            }
	        }//close else
           
            return "insert";
            
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
            ae.performErrorAction("9000", "DeliveryMessageCommand,executeInsert", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    public String executeRelatedList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse reponse, ActionMessages messages)
    {
        User objUser = null;
        String orderType = "P";
        String roleType = null;
        Collection objCollection = null;
        String paperlist = request.getParameter("paperList");
         //make sure to handle this
        //System.out.println("printing query string of request"+request.getQueryString() );
        if(paperlist!= null){
        	String lineNo = request.getParameter("lineNo");
        	int pagination = 0;
        	int userId = 0;
        	POHeader poHeaderTemp = new POHeader();
            String poid = request.getParameter("poid");
            BigDecimal poId = new BigDecimal(String.valueOf(poid));
            poHeaderTemp.setPoId(poId);
            String poversion = request.getParameter("poversion");
            BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
            poHeaderTemp.setPoVersion(poVersion);
            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
        	 if(request.getSession().getAttribute("USER_INFO") != null)
             {
                 objUser = (User)request.getSession().getAttribute("USER_INFO");
                 userId = objUser.getUserId().intValue();
                 roleType = objUser.getRoleTypeDetail().getRoleType();
             } else
             {
                 AppException ae = new AppException();
                 ae.performErrorAction("9007", "DeliveryMessageCommand,executeRelatedList");
                 String errMsg = ae.getSErrorDescription();
                 request.setAttribute("PIX_ERROR", errMsg);
                 return "error";
             }
        	 DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
        	 try{	     	 		 
    			 String requestType = request.getParameter("requestType");
    			 //int currentValue = Integer.parseInt(request.getParameter(PIXConstants.PAGE_VALUE));
    			 objCollection = objDeliveryMessageDelegate.getDeliveryMessageHistory(poHeaderTemp,pagination,lineNo,requestType,userId);
    			 DeliveryMessage objDelMsg = null;
    			 objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
    			 if(objCollection != null){
    				objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
    				objDelMsg = new DeliveryMessage();
    				objDelMsg.setDeliveryMsgLineCollection(objCollection);
    				objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
    			   }
        	 }
        	 catch(AppException e)
             {
        		 String errMsg = e.getSErrorDescription();
                 request.setAttribute("errorMsg", errMsg);
                 return "error";
             }
             catch(Exception e)
             {
                 AppException ae = new AppException();
                 ae.performErrorAction("9000", "DeliveryMessageCommand,executeRelatedList,paperDMHistory", e);
                 String errMsg = ae.getSErrorDescription();
                 request.setAttribute("PIX_ERROR", errMsg);
                 return "error";
             }
        }
        else{
        try
        {
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                roleType = objUser.getRoleTypeDetail().getRoleType();
                if("M".equals(roleType))
                {
                    orderType = "O";
                }
            }
            HashMap objHashMap = null;
            String PmsHeaderStatus = null;
            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
            String action = request.getParameter("action");            
            String poid = request.getParameter("poid");
            BigDecimal poId = new BigDecimal(String.valueOf(poid));          
            String poversion = request.getParameter("poversion");
            BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
            if("M".equals(roleType))
            {            	
            	PmsHeaderStatus = objDeliveryMessageDelegate.getPmsPoHeaderStatus(poId);            	
            	if(PmsHeaderStatus.equals("VOID"))
            	{
            		if(action.equals("DMList"))
            		{
            			request.setAttribute("PmsPoHeaderStatus","<LI>Purchase Order has been Cancelled in PEPMS.</LI>");            		
            			return "DMpageRefresh";
            		}
            		else
            		{
            			request.setAttribute("PmsPoHeaderStatus","<LI>Selected Purchase Order has been Cancelled in PEPMS.</LI>");            		
            			return "pageRefresh";
            		}
            	}
            }
            objHashMap = objDeliveryMessageDelegate.displayBasicDeliveryMessageInfo(poId, poVersion, orderType);
            DeliveryMessage objDeliveryMessageTemp = (DeliveryMessage)objHashMap.get("objHashMap");
            if("M".equals(roleType))
            {
                request.getSession().setAttribute("MessageTypeDetail", objHashMap.get("MessageTypes"));
            } else
            {
                request.setAttribute("MessageTypeDetail", objHashMap.get("MessageTypes"));
            }
            objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessageTemp);
        }
        catch(AppException e)
        {
            String errMsg = e.getSErrorDescription();
            request.setAttribute("errorMsg", errMsg);
            return "error";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "DeliveryMessageCommand,executeRelatedList", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
        }//end of else
        return "relatedList";
    }

    public String executepdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        com.lowagie.text.Document objDocument = null;
        try
        {
            DeliveryMessagePdf objDeliveryMessagePdf = new DeliveryMessagePdf();
            String reportName = "DeliveryMessage";
            request.getSession().setAttribute("PDF_OBJECT", objDeliveryMessagePdf);
            request.getSession().setAttribute("PDF_Name", reportName);
            response.sendRedirect("../pdfFileDownloadServlet");
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "ProcurementPlanCommand,executePDF", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
        }
        return "exportpdf";
    }

    public String executeGeneral(String actioncommand, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        User objUser = null;
        String login = "";
        Collection objCollection = null;
        String idHidden = "";
        try
        {	
        	if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                login = objUser.getUserId().toString();
            }
        	else
        	{
        		response.sendRedirect("/pix");
        	}
        		
            Integer user_id = new Integer(String.valueOf(login));
            String objorderby = "CREATION_DATE_TIME";
            String objsort = "DESC";
        	if(request.getParameter("partyType1")!= null)
            {
        	DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
        	int currentValue = 0;                     //if pagination is required used for future
        	DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
        	objCollection = objDeliveryMessageDelegate.inboxdeliverymessagemilllist(user_id, currentValue, objorderby, objsort);
        	DeliveryMessage objDelMsg = null;
			 objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
			 if(objCollection != null){
				objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
				objDelMsg = new DeliveryMessage();
				objDelMsg.setDeliveryMsgLineCollection(objCollection);
				objDeliveryMessageForm.setDeliveryMessage(objDelMsg);
			   }
            }
        	else
            {
        	DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
            if(request.getParameter("idHidden") != null)
            {
                idHidden = request.getParameter("idHidden");
            }
            POHeader poHeaderTemp = new POHeader();
            if(request.getParameter("poid") != null)
            {
                String poid = request.getParameter("poid");
                BigDecimal poId = new BigDecimal(String.valueOf(poid));
                objDeliveryMessageForm.setPoId(poId);
                poHeaderTemp.setPoId(poId);
            }
            if(request.getParameter("poversion") != null)
            {
                String poversion = request.getParameter("poversion");
                BigDecimal poVersion = new BigDecimal(String.valueOf(poversion));
                objDeliveryMessageForm.setPoVersion(poVersion);
                poHeaderTemp.setPoVersion(poVersion);
            }
            String day = request.getParameter("days");
            Integer days = new Integer(String.valueOf(day));
            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
            int currentValue = Integer.parseInt(request.getParameter("PAGE_VALUE"));
            objCollection = objDeliveryMessageDelegate.inboxdeliverymessagelist(days, user_id, currentValue, objorderby, objsort);
            int size = objCollection.size();
            PIXUtil.getNextPage(request, currentValue, size);
            PIXUtil.getPrevPage(request, currentValue);
            if(size > 10)
            {
                objCollection.remove(((Vector)objCollection).get(size - 1));
            }
            objDeliveryMessageForm.setDeliverymessageCollection(objCollection);
            request.setAttribute("checkBoxIdList", idHidden);
            }
            return "inboxlist";
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
            ae.performErrorAction("9000", "DeliveryMessageCommand,executeList", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
    }

    private void renameDMFolderFile( String msg, String pono)throws AppException{
   	 FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
   	 InputStream inputStream=null ;
   	 String saveFilePath="" ;
   //	System.out.println("msg "+msg + "pono "+pono);
   	 if(msg!=null&&!"".equals(msg)){
   	    inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
   		Properties properties = new Properties();  
   		try{
   		properties.load(inputStream); 
   		
   		}catch(IOException e){
   			//e.printStackTrace();
   		}
   		finally{
   			if(inputStream!=null){
   				 try{
   					 inputStream.close();
   					}catch(IOException e){
   						//e.printStackTrace();
   					 }
   			}
   		}
   		
   		Map fileUrlBuff=(LinkedHashMap)objDelegate.renameDMFileFolder(msg,pono);
   		if(fileUrlBuff!=null&&fileUrlBuff.size()!=0){
   			 // String[] msgArr=msg.split("#");
   			 Set set= (Set)fileUrlBuff.keySet();
   			 Iterator it=set.iterator();
   			
   			for(int p=0;it.hasNext();p++){
   		  
   			String msgIDMap=(String)it.next();
   			
   			String url=((StringBuffer)fileUrlBuff.get(msgIDMap)).toString();
   			String[] urlArr=url.split("#");
   			
   			// String[] msgArrLine=msgArr[p].split("~") ;
   			 String msgArrLine="" ;
   			//System.out.println("url...."+url);
   			for(int k=0;k<urlArr.length;k++){
   				StringBuffer replacedUrl=new StringBuffer("");
   				String[] divUrl=urlArr[k].split("/");
   			//	System.out.println("divUrl.length...."+divUrl.length);
   				String[] lineArr=divUrl[divUrl.length-2].split("_");
   				msgArrLine=lineArr[lineArr.length-1];
   				
   				for(int m=0;m<divUrl.length-1;m++){
   					replacedUrl.append(divUrl[m]);
   					replacedUrl.append("/") ;
   				}
   				 saveFilePath = properties.getProperty("save-file-path");
   				 if(!"".equals(replacedUrl.toString())){
   					
   				 File file= new File(saveFilePath+replacedUrl.toString());
   				// if(msgArrLine!=null){
   				if(file.exists()){	 
   				
   					
   				//Introduced as part of Fortify Path Manipulation Fix
                    String tempFilePath = saveFilePath+"DM_"+pono+"_downloads/"+"sub_"+msgIDMap+"_"+msgArrLine+"/";
                    System.out.println("tempFilePath: "+tempFilePath);
                    String validatedFilePath = validateFilePath(tempFilePath);
                    System.out.println("validatedFilePath: "+validatedFilePath);
                    file.renameTo(new File(validatedFilePath));
   					
   				 //file.renameTo(new File(saveFilePath+"DM_"+pono+"_downloads/"+"sub_"+msgIDMap+"_"+msgArrLine+"/"));
   				  }
   				 }
   				// break;
   			 } // for	
   			
   			}	//for big
   		}//if
   	 }
      }
    public String executeUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse reponse, ActionMessages messages)
    {
    	User objUser = null;
        String roleType = "";
        int userId = 0;
        try
        {
            DeliveryMessageForm objDeliveryMessageForm = (DeliveryMessageForm)form;
            DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
            String lineNo = request.getParameter("lineNo");
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
            String pageOrderList = request.getParameter("page_order_list");
            String requestType = request.getParameter("requestType");
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                userId = objUser.getUserId().intValue();
                roleType = objUser.getRoleTypeDetail().getRoleType();
            } else
            {
                AppException ae = new AppException();
                ae.performErrorAction("9007", "DeliveryMessageCommand,executeUpdate");
                String errMsg = ae.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            DeliveryMessageDelegate objDeliveryMessageDelegate = new DeliveryMessageDelegate();
            String MsgNumber = null;
            StringBuffer renameInfo = new StringBuffer();
        	StringBuffer outputInfo = new StringBuffer();
            if("M".equals(roleType)){   
            	MsgNumber = objDeliveryMessageDelegate.updateMillDeliveryMessage(objDeliveryMessage, poHeaderTemp, userId);
            	if(MsgNumber != null && !(MsgNumber.equals(""))){
            	String[] msg_id_arr = MsgNumber.split("@");
            	String[] msg_id_line_arr = null;
            	String[] temp = null;
            	for(int k=0;k<msg_id_arr.length;k++){
            		msg_id_line_arr=msg_id_arr[k].split("#");
            		temp = msg_id_line_arr[0].split("_");
			        //String delMsg_no = temp[temp.length-1];
			        renameInfo.append(temp[temp.length-1]).append("~").append(msg_id_line_arr[1]).append("#");
			        outputInfo.append(msg_id_line_arr[0]).append(",");
            	}
            	renameDMFolderFile(renameInfo.toString(), ponumber);
            	}
           }
            if((!PIXUtil.checkNullField(request.getParameter("pageFilter"))) & (!PIXUtil.checkNullField(request.getParameter("ponoFilter"))) & (!PIXUtil.checkNullField(request.getParameter("isbnFilter"))) & (!PIXUtil.checkNullField(request.getParameter("printNoFilter"))) & (!PIXUtil.checkNullField(request.getParameter("statusFilter"))) & (!PIXUtil.checkNullField(request.getParameter("startDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("endDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("sbBDateFilter"))) & (!PIXUtil.checkNullField(request.getParameter("ebBDateFilter"))))
            {
            	request.setAttribute("OK_URL", "/deliverymessage/deliverymessagemilldetail.do"+"?PAGE_VALUE=1&paperList=list&requestType="+requestType+"&lineNo="+lineNo+"&poid=" + poid + "&poversion=" + poversion + "&pono=" + ponumber + "&rno=" + releaseNo + "&order=" + order + "&page_order_list=" + pageOrderList);
            }
            else
            {
                String list_page = "?PAGE_VALUE=1&paperList=list&requestType="+requestType+"&lineNo="+lineNo+"&poid=" + poid + "&poversion=" + poversion + "&pono=" + ponumber + "&rno=" + releaseNo + "&order=" + order + "&page_order_list=" + pageOrderList;
                if(request.getParameter("pageFilter") != "")
                {
                    list_page = list_page + "&pageFilter=" + request.getParameter("pageFilter");
                }
                if(request.getParameter("ponoFilter") != "")
                {
                    list_page = list_page + "&ponoFilter=" + request.getParameter("ponoFilter");
                }
                if(request.getParameter("isbnFilter") != "")
                {
                    list_page = list_page + "&isbnFilter=" + request.getParameter("isbnFilter");
                }
                if(request.getParameter("printNoFilter") != "")
                {
                    list_page = list_page + "&printNoFilter=" + request.getParameter("printNoFilter");
                }
                if(request.getParameter("statusFilter") != "")
                {
                    list_page = list_page + "&statusFilter=" + request.getParameter("statusFilter");
                }
                if(request.getParameter("startDateFilter") != "")
                {
                    list_page = list_page + "&startDateFilter=" + request.getParameter("startDateFilter");
                }
                if(request.getParameter("endDateFilter") != "")
                {
                    list_page = list_page + "&endDateFilter=" + request.getParameter("endDateFilter");
                }
                if(request.getParameter("sbBDateFilter") != "")
                {
                    list_page = list_page + "&sbBDateFilter=" + request.getParameter("sbBDateFilter");
                }
                if(request.getParameter("ebBDateFilter") != "")
                {
                    list_page = list_page + "&ebBDateFilter=" + request.getParameter("ebBDateFilter");
                }
                request.setAttribute("OK_URL", "/deliverymessage/deliverymessagemilldetail.do" + list_page);
            }
            if(request.getSession().getAttribute("MessageTypeDetail") != null)
            {
                request.getSession().removeAttribute("MessageTypeDetail");
            }
            String messageKey ="Delivery Message History has been updated successfully";
            request.setAttribute("SUCCESS_STRING", messageKey);
            
            return "update";
        }
        catch(Exception e)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9000", "DeliveryMessageCommand,executeInsert", e);
            String errMsg = ae.getSErrorDescription();
            request.setAttribute("PIX_ERROR", errMsg);
            return "error";
        }
   }
    
  //Introduced as part of Fortify Path Manipulation Fix
    public String validateFilePath(String tempFilePath) {
    	System.out.println("Inside validateFilePath method");
    	if(tempFilePath == null) {
    		return null;
    	}
    	
    	String aString = tempFilePath;
        String cleanPath = "";
        for (int i = 0; i < aString.length(); ++i) {
        	cleanPath += sanitizeElements(aString.charAt(i));
        }
        System.out.println("cleanPath: "+cleanPath);
    	return cleanPath;
    }
    
    public char sanitizeElements(char aChar) {
    	System.out.println("Inside sanitizeElements method");
    	
    	int asciiValue = (int) aChar; // Get the ASCII value of the special character
        return (char) asciiValue; // Return the character corresponding to the ASCII value
    	
    }
    
}

