package com.pearson.pix.dao.costaccounting;

import com.pearson.pix.dao.base.BaseDAO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import com.pearson.pix.dto.admin.UserRole;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 * @author zaheer abbas
 */
public class CostAccountingDAOImpl extends BaseDAO implements CostAccountingDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(CostAccountingDAOImpl.class.getName());
   
	
   public CostAccountingDAOImpl() 
   {
    
   }
   
   
   /**
    * Gets the Delivery Message Approval List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Collection
    */
   public Collection getDmApprovalList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId,String materialNoFilter,String delMsgNoFilter,String millMerchantFilter,String statusFilter,String startDate,String endDate) throws AppException
   {
	   
	   
	   Session objSession = getClientSession();
	   DeliveryMessage objDeliveryMessage = null;
	   Vector objListVector = new Vector();
	   try
	   {
		   Record objRecord = null;
		   StoredProcedureCall call = new StoredProcedureCall();
		   call.setProcedureName("pix_cost_actng_proc");
		   call.addNamedArgumentValue("i_id",null);
		   call.addNamedArgumentValue("i_user_id",userId);		//userId
           call.addNamedArgumentValue("i_input_1",statusFilter);
           if(!PIXUtil.checkNullField(poHeaderTemp.getPoNo()) && !PIXUtil.checkNullField(delMsgNoFilter) && !PIXUtil.checkNullField(startDate) && !PIXUtil.checkNullField(endDate) && !PIXUtil.checkNullField(materialNoFilter) && !PIXUtil.checkNullField(millMerchantFilter) && !PIXUtil.checkNullField(statusFilter))
           {	
        	   call.addNamedArgumentValue("i_request","LIST");
           }
           else
           {
        	   call.addNamedArgumentValue("i_request","FILTERED_LIST");
           }
           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
           call.addNamedArgumentValue("i_order_by",objorderby);
           call.addNamedArgumentValue("i_cons_flag",null);
           call.addNamedArgumentValue("i_sort",objsort); 
           call.addNamedArgumentValue("i_po_no",poHeaderTemp.getPoNo()); 
           call.addNamedArgumentValue("i_msg_no",delMsgNoFilter); 
           call.addNamedArgumentValue("i_start_date",startDate); 
           call.addNamedArgumentValue("i_end_date",endDate); 
           call.addNamedArgumentValue("i_mat_no",materialNoFilter); 
           call.addNamedArgumentValue("i_mill",millMerchantFilter); 
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           
           if (objVector != null && objVector.size()>0)
           {
          	   for (int i = 0; i < objVector.size(); i++)
        	   {
           		  objRecord = (Record)objVector.get(i);
        		  objDeliveryMessage = new DeliveryMessage();
        		  objDeliveryMessage.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
        		  objDeliveryMessage.setMsgNo((String)objRecord.get(PIXConstants.DELIVERY_MESSAGE_NO));
        		  objDeliveryMessage.setCreationDateTime((Date)objRecord.get(PIXConstants.CREATION_DATE_TIME));
        		  objDeliveryMessage.setCreatedBy((String)objRecord.get(PIXConstants.CREATED_BY));
        		  objDeliveryMessage.setMaterialNo((String)objRecord.get(PIXConstants.MATERIAL_NO));
        		  objDeliveryMessage.setPoId((BigDecimal)objRecord.get(PIXConstants.PO_ID));
        		  objDeliveryMessage.setPoVersion((BigDecimal)objRecord.get(PIXConstants.PO_VERSION));
        		  objDeliveryMessage.setLineNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.MSG_LINE_NO)).intValue()));
        		  objDeliveryMessage.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
        		  
        		  
        		  BigDecimal delivered_QTY=(BigDecimal)objRecord.get(PIXConstants.DELIVERED_QTY);
        		 if(delivered_QTY!=null){
        		   objDeliveryMessage.setDeliveredQuan(new Integer(delivered_QTY.intValue()));
        		  }
        		  objDeliveryMessage.setRequestedQuan(new Integer(((BigDecimal)objRecord.get(PIXConstants.REQUESTED_QTY)).intValue()));
        		 
        		  //Naveen
        		  objDeliveryMessage.setReceivedQuan(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QTY)).intValue()));
        		  objDeliveryMessage.setOwnedQuan(new Integer(((BigDecimal)objRecord.get(PIXConstants.OWNED_QTY)).intValue()));
        		  objDeliveryMessage.setOwnershipMode((String)objRecord.get(PIXConstants.OWNERSHIP_MODE));
        		  
        		  /*   objDeliveryMessage.setReceivedQuan(new Integer(100));
        		  objDeliveryMessage.setOwnedQuan(new Integer(100));
        		  objDeliveryMessage.setOwnershipMode("CONSIGNMENT");*/
        		  
        		  //Naveen.
        		  objDeliveryMessage.setMillName((String)objRecord.get(PIXConstants.MILL_NAME));
        		  objDeliveryMessage.setPrintername((String)objRecord.get(PIXConstants.PRINTER_NAME));
        		  objDeliveryMessage.setStatus((String)objRecord.get(PIXConstants.STATUS));
        		  objListVector.add(objDeliveryMessage);
        	   }
          	 
           }        
           else if(objVector.size()== 0 || objVector == null)
           {
        	     AppException appException = new AppException();
        	     
  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_DELIVERY_MESSAGE,"CostAccountingDAOImpl");  
  				 throw appException;
           }
		  
		  
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  CostAccountingDAOImpl in list");
		  
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "CostAccountingDAOImpl,getDmApprovalList",e);
		   throw appException;
	   }
	   finally
	   {
		   if(objSession!=null)
		   {
			  
			  objSession.release();
		   }
	   }    
	  return objListVector;
   }
   
   /**
    * Approves multiple Delivery Messages
    * 
    * @param idVersionString
    * @param userId
    * @return String 
    */
   
   public String insertMultipleDMApprove(String idVersionString,Integer userId,String ownrshpMode) throws AppException
   {
	   
	   
  	   Session clientSession = null;
  	   String cons_flag="";
  	   
  	   if(ownrshpMode.equals("OWNED ON DELIVERY")){
  		 cons_flag="D";
  		  
  	   }
  	   else if(ownrshpMode.equals("TRUE CONSIGNMENT")){
  		 cons_flag="T";
  	   }
  	   else if(ownrshpMode.equals("CONSIGNMENT")){
  		 cons_flag="C";
  	   }
  	   try
  	   {
  		   clientSession = getClientSession();
  		   StoredProcedureCall call = new StoredProcedureCall(); 
  		   call.setProcedureName("pix_cost_actng_proc");
  		   call.addNamedArgumentValue("i_id",null);
  		   call.addNamedArgumentValue("i_user_id",userId);					//userId
  		   call.addNamedArgumentValue("i_input_1",idVersionString);			//msgId's string
  		   call.addNamedArgumentValue("i_cons_flag",cons_flag);
  		   call.addNamedArgumentValue("i_request","UPDATE");
  		   call.addNamedArgumentValue("i_pagination",new Integer(0));
  		   call.addNamedArgumentValue("i_order_by",null);
  		   call.addNamedArgumentValue("i_sort",null); 
  		   call.addNamedArgumentValue("i_po_no",null); 
  		   call.addNamedArgumentValue("i_msg_no",null); 
  		   call.addNamedArgumentValue("i_start_date",null); 
  		   call.addNamedArgumentValue("i_end_date",null); 
  		   call.addNamedArgumentValue("i_mat_no",null); 
  		   call.addNamedArgumentValue("i_mill",null); 
  		   call.useNamedCursorOutputAsResultSet("list_refcursor");
  		   clientSession.executeNonSelectingCall(call);
  	   }
  	   catch(TopLinkException te)
  	   {
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"CostAccountingDAOImpl,insertMultipleDMApprove",te);
  		   throw ae;
  	   }
  	   finally
  	   {
  		   if(clientSession!=null)
  		   {
  			    clientSession.release();
  		   }
  	   }
  	   return "";
   }
   
   /**
    * Getting dropdown values for filter
    * 
    * @return java.util.LinkedHashMap
    */
   
   public LinkedHashMap getFilterDropDowns(Integer userId) throws AppException
   {
	   	LinkedHashMap objLinkedHashMap =null;
	   	Vector millAssociatedUser = null;
	   	Vector millAssociatedNewUser = null;
	   	Session objSession =  null;
	   	
	   	Vector statusVector = new Vector();
	   	try
	   	{
	   		objSession = getClientSession();
	   		objLinkedHashMap = new LinkedHashMap();
	   		millAssociatedNewUser = new Vector();
	   		/*Query to get the mill/merchant associated with the user*/
	   		millAssociatedUser = (Vector) objSession.executeSelectingCall(new SQLCall(
	   				"select name_1 from pix_admin_party pap,pix_admin_user pau,pix_admin_user_party paup" +
	   				" where pap.party_type IN ('M','D') and paup.user_id=" + userId + " and paup.user_id = pau.user_id" +
	   						" and paup.san = pap.san"));
	   		
	   		int vectorSize = millAssociatedUser == null ? 0 : millAssociatedUser.size();
    		if (millAssociatedUser != null && vectorSize>0)				//checking for vector size
    		{
    			for (int i = 0; i < vectorSize; i++)			//iterating to add each record in listVector
    			{
    				Record objDatabaseRecord = (Record)millAssociatedUser.get(i);
    				String name = (String)objDatabaseRecord.get(PIXConstants.NAME_1);
    				millAssociatedNewUser.add(name);
    			}
    		}
    		
    		statusVector = (Vector)objSession.readAllObjects(Reference.class,new SQLCall("select pr.ref_id,pr.description from pix_ref pr where pr.group_code='DM_STATUS' order by pr.display_order"));
	   		objLinkedHashMap.put(PIXConstants.MILL_ASSOCIATED_TO_USER,millAssociatedNewUser);
	   		objLinkedHashMap.put(PIXConstants.STATUS,statusVector);
	   		return objLinkedHashMap;
	   	}
	   	catch(TopLinkException e)
	   	{
	   		AppException appException = new AppException();
	   		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "CostAccountingDAOImpl,getFilterDropDowns",e);
	   	}
	   	finally
	   	{
	   		if(objSession!=null)
	   		{
	   			objSession.release();
	   		}
	   	}
	   	return objLinkedHashMap;
   }
   
   /**
    * method for the purpose of updating the quantity of delivery message detail.
    * 
    
    */
   
   public String updateDeliveryMessageLine(DeliveryMessage objDeliveryMessage,Integer messageId , BigDecimal poId , BigDecimal poVersion,Integer userId) throws AppException
   {
	   Session clientSession =  null;
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   Vector deliveryMessagecollection = null;
	   String lineNo = null;
	   //String quantity = null;
	   String owningQty=null;
	   String shipDate = null;
	   String consignmentFlag=null;
	   String ownershipMode=null;
	   try
	   {
		   clientSession = getClientSession();
		   deliveryMessagecollection = (Vector)objDeliveryMessage.getDeliveryMsgLineCollection();
		   int vectorSize = deliveryMessagecollection == null ? 0 : objDeliveryMessage.getDeliveryMsgLineCollection().size();
		   for(int i=0;i<vectorSize;i++)
		   {
			   objDeliveryMessageLine = (DeliveryMessageLine)deliveryMessagecollection.get(i);
			   if(lineNo==null)
			   {
				   lineNo = objDeliveryMessageLine.getLineNo().toString();
			   }
			   else
			   {
				   lineNo = lineNo + "," + objDeliveryMessageLine.getLineNo().toString();
			   }
			   
			 /*  if(quantity==null)
			   {
				   quantity = objDeliveryMessageLine.getDeliveredQuantity().toString();
			   }
			   else
			   {
				   quantity = quantity + "," + objDeliveryMessageLine.getDeliveredQuantity().toString();
			   }*/
			   //Naveen
			   
			   if(owningQty==null)
			   {
				   owningQty = objDeliveryMessageLine.getOwningQuantity().toString();
				   
			   }
			   else
			   {
				   owningQty = owningQty + "," + objDeliveryMessageLine.getOwningQuantity().toString();
				   
			   }
			   //Naveen
			   if(shipDate==null)
			   {
				   shipDate = objDeliveryMessageLine.getEstimatedDeliveryDate();
			   }
			   else
			   {
				   shipDate = shipDate + "," + objDeliveryMessageLine.getEstimatedDeliveryDate();
			   }
			  ownershipMode=objDeliveryMessageLine.getOwnrshpMode().toString();
			  if(ownershipMode.equals("TRUE CONSIGNMENT"))
			   {
				   consignmentFlag = "T";
				   
			   }
			   else if(ownershipMode.equals("OWNED ON DELIVERY"))
			   {
				   consignmentFlag = "D";
				   
			   }
			   else{
				   
				   consignmentFlag=objDeliveryMessageLine.getConsignmentFlag().toString();
				   
			   }
			   
			   
			   //Naveen
			   
			   
			   StoredProcedureCall call = new StoredProcedureCall(); 
	  		   call.setProcedureName("pix_cost_actng_proc");
	  		   call.addNamedArgumentValue("i_id",messageId);
			   call.addNamedArgumentValue("i_user_id",userId);					//messageId
	  		  // call.addNamedArgumentValue("i_input_1",quantity);
	  		   call.addNamedArgumentValue("i_input_1",owningQty);	//quantity string
	  		   call.addNamedArgumentValue("i_cons_flag",consignmentFlag);
	  		   
	  		   call.addNamedArgumentValue("i_request",PIXConstants.UPDATE_QUANTITY);
	  		   call.addNamedArgumentValue("i_pagination",null);
	  		   call.addNamedArgumentValue("i_order_by",null);
	  		   call.addNamedArgumentValue("i_sort",null); 
	  		   call.addNamedArgumentValue("i_po_no",null); 
	  		   call.addNamedArgumentValue("i_msg_no",lineNo); 			//lineNo string
	  		   call.addNamedArgumentValue("i_start_date",shipDate); 	//shipDate string send 
	  		   call.addNamedArgumentValue("i_end_date",null); 
	  		   call.addNamedArgumentValue("i_mat_no",null); 
	  		   call.addNamedArgumentValue("i_mill",null); 
	  		   call.useNamedCursorOutputAsResultSet("list_refcursor");
	  		   clientSession.executeNonSelectingCall(call);
			  
		   }
	   }
	   catch(TopLinkException e)
   	   {
		   AppException appException = new AppException();
   			appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "CostAccountingDAOImpl,updateDeliveryMessageLine",e);
   	   }
   	   finally
   	   {
   		   if(clientSession!=null)
   		   {
   			clientSession.release();
   		   }
   	   }
	   return null;
   }
   
   
   
}
