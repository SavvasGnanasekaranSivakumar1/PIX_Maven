package com.pearson.pix.dao.deliverymessage;

import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dao.orderstatus.OrderStatusDAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.queryframework.ValueReadQuery;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POLineParty;
import com.pearson.pix.dto.purchaseorder.POLinePartyContact;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 * @author zaheer abbas
 */
public class DeliveryMessageDAOImpl extends BaseDAO implements DeliveryMessageDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(DeliveryMessageDAOImpl.class.getName());
   
	
   public DeliveryMessageDAOImpl() 
   {
    
   }
   
     
// roleTracking popup
	
   public String retrieveSanForPO(String login_Id)
   {
	   
	   Vector sanVector = null;
	   	Session objSession =  null;
	   	Record objRecord = null;
	   	String sanRecord = null;
	   	BigDecimal val = null;
	   	Vector statusVector = new Vector();
	   	String ss = login_Id;
	   	try
	   	{
	   		objSession = getClientSession();
	   		sanVector = (Vector) objSession.executeSelectingCall(new SQLCall(
	   				  "select supplier_san san from pix_po_list_summary " +
	   			      " where po_no = " + "'" + login_Id +"'"));
	   			
	   				
//	   			"AND PAUP.USER_ID = PAU.USER_ID AND PAU.ROLE_TYPE = 'M' AND PAU.LOGIN =" + "'" + login_Id +"'" ));   //= 'pepms'"));	
	   				
	   				
	   		
	   		int vectorSize = sanVector == null ? 0 : sanVector.size();
    		if (sanVector != null && vectorSize>0)				//checking for vector size
    		{
    			for (int i = 0; i < vectorSize; i++)			//iterating to add each record in listVector
    			{
    				objRecord = (Record) sanVector.get(i);
    				sanRecord = (String) objRecord.get("SAN");
    			}
    		}
    		System.out.println("Hi");
    		System.out.println(sanRecord);
    		
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

	   
	   return sanRecord;
   }
   
   public int getRoleTrackingFlag(String login_Id)
	{
//  			    LinkedHashMap objLinkedHashMap =null;
			   	Vector isRoleTrackingEnabled = null; //millAssociatedUser = null;
//			   	Vector millAssociatedNewUser = null;
			   	Session objSession =  null;
			   	Record objRecord = null;
			   	int record = 0;
			   	BigDecimal val = null;
			   	Vector statusVector = new Vector();
			   	
			   	
			   	
			   	try
			   	{
			   		String sanRecord = retrieveSanForPO(login_Id);
			   		System.out.println("po:"+ login_Id);
			   		System.out.println("sanRecord:"+sanRecord);
			   		
			   		objSession = getClientSession();
	//		   		objLinkedHashMap = new LinkedHashMap();
	//		   		millAssociatedNewUser = new Vector();
			   	//	Query to get the mill/merchant associated with the user
			   		//millAssociatedUser = (Vector) objSession.executeSelectingCall(new SQLCall(
			   		isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
			   			
			   				
			   				"SELECT COUNT(1) countr" +
			   				" FROM " +
			   				" PIX_ROLL_SAN_MAPPING PRSM, " +
			   				" PIX_ADMIN_USER_PARTY PAUP, " +
			   				" PIX_ADMIN_USER PAU " +
			   				" WHERE PRSM.SAN = PAUP.SAN " +
			   				" AND PAUP.USER_ID = PAU.USER_ID " +
			   				" AND PAU.ROLE_TYPE = 'M' " +
			   				" and PRSM.active ='Y' " +
			   				" AND PAUP.SAN = " + "'" + sanRecord +"'" )); 

			   				
//			   			"SELECT COUNT(1) countr FROM PIX_ROLL_SAN_MAPPING PRSM, PIX_ADMIN_USER_PARTY PAUP, PIX_ADMIN_USER PAU " +
//			  			"WHERE PRSM.SAN = PAUP.SAN " +
//			   			"AND PAUP.USER_ID = PAU.USER_ID AND PAU.ROLE_TYPE = 'M' AND PAU.LOGIN =" + "'" + login_Id +"'" ));   //= 'pepms'"));	
			   				
			   				
			   		
			   		int vectorSize = isRoleTrackingEnabled == null ? 0 : isRoleTrackingEnabled.size();
		    		if (isRoleTrackingEnabled != null && vectorSize>0)				//checking for vector size
		    		{
		    			for (int i = 0; i < vectorSize; i++)			//iterating to add each record in listVector
		    			{
		    				objRecord = (Record) isRoleTrackingEnabled.get(i);
		    			//	str = (String) objRecord.get("COUNTR");
		    				val = (BigDecimal) objRecord.get("COUNTR");
		    				record = val.intValue();//isRoleTrackingEnabled.size();//(Integer)isRoleTrackingEnabled.get(i);
		    				
		    			//	Record objDatabaseRecord = (Record)millAssociatedUser.get(i);
		    			//	String name = (String)objDatabaseRecord.get(PIXConstants.NAME_1);
		    			//	millAssociatedNewUser.add(name);
		    			}
		    		}
		    	//	System.out.println(str);
		    		System.out.println("Hi");
		    		System.out.println(record);
		    		
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
	 	   
		return record;//2;
	}
   
   
   /**
    * Gets the Delivery Message List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Collection
    */
   public Collection getDeliveryMessageList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,String party,Integer userId) throws AppException
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.getDeliveryMessageList**************");
	   Session objSession = getClientSession();
	   DeliveryMessage objDeliveryMessage = null;
	   Vector objListVector = new Vector();
	   //POHeader poh=new POHeader();
	   
	   
	   try
	   {
		   Record objRecord = null;
		   StoredProcedureCall call = new StoredProcedureCall();
		   if("M".equals(party) || "paperfo".equals(party))
		   {
			  /*Gaurav*/
			   /*call.setProcedureName("PIX_PAPER_DM_PROC");
			   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
	           call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
	           call.addNamedArgumentValue("i_user_id",userId);
	           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
	           call.addNamedArgumentValue("i_order_by",objorderby);
	           call.addNamedArgumentValue("i_sort",objsort); */
			   
			   call.setProcedureName("PIX_PAPER_NEW_DM_MSG_PROC");
		   	   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
		   	   call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
		   	   call.addNamedArgumentValue("i_user_id",userId);
		   	   call.useNamedCursorOutputAsResultSet("list_refcursor");
	           Vector objVector = (Vector)objSession.executeSelectingCall(call);
	           DeliveryMessageLine objDeliveryMessageLine = null;
	           if (objVector != null && objVector.size()>0)
	           {
	          	   for (int i = 0; i < objVector.size(); i++)
	        	   {
	           		  objRecord = (Record)objVector.get(i);
	           		  objDeliveryMessageLine = new DeliveryMessageLine();
	           		  objDeliveryMessageLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));           		  
	           		  objDeliveryMessageLine.setPrinter((String)objRecord.get(PIXConstants.PRINTER));
	           		  objDeliveryMessageLine.setUom((String)objRecord.get("UOM"));
	           		  
	           		  // productCode
	           		String productCodeMatDesc = (String)objRecord.get("L4_MATERIAL_DESC");
	           		String subProductCode = null;
	           		StringBuffer productCodeBuffer = new StringBuffer();
	           		StringBuffer tempBuffer = new StringBuffer();
	           		if(productCodeMatDesc != null)
	           		  {
	           			  int idx = productCodeMatDesc.indexOf("-");
	           			productCodeMatDesc = productCodeMatDesc.substring(0, idx);
	           			
	           			
	           			  for(int j=10; j>productCodeMatDesc.length();j--)
	           			  {
	           				  tempBuffer.append("0");
	           			  }
	           			  productCodeBuffer = tempBuffer.append(productCodeMatDesc);
	           		  }
	           		  String productCode = new String(productCodeBuffer);
	           		objDeliveryMessageLine.setProductCode(productCode);
	           		  
	           		  // end productCode
	           		  System.out.println("**********************DeliveryMessageDAOImpl.getDeliveryMessageList**************"+(String)objRecord.get("UOM"));
	           		  if((String)objRecord.get("MESSAGE_TEXT") != null){
	           		  objDeliveryMessageLine.setMessage_Text((String)objRecord.get("MESSAGE_TEXT"));
	        	   	  }
	          	     else{
	          		  objDeliveryMessageLine.setMessage_Text("");
	          	      }
	           		  System.out.println("**********************DeliveryMessageDAOImpl.getDeliveryMessageList**************"+(String)objRecord.get("MESSAGE_TEXT"));
	           		  objDeliveryMessageLine.setMaterialDesc((String)objRecord.get(PIXConstants.MATERIAL_DESC));	           		
	           		  objDeliveryMessageLine.setDeliveryDate((Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE));
	           		  objDeliveryMessageLine.setRequestedQuantity((BigDecimal)objRecord.get(PIXConstants.BUYER_REQUESTED_QTY));
	           		  objDeliveryMessageLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DM_POSTED)).intValue()));
	           		  objDeliveryMessageLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DM_TO_BE_POSTED)).intValue()));
	           		  objDeliveryMessageLine.setColorFlag(new Integer(((BigDecimal)objRecord.get(PIXConstants.COLOUR_FLAG)).intValue()));
	           		  objDeliveryMessageLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get("QTY_WITH_TOLERANCE")).longValue()));
	           		  objListVector.add(objDeliveryMessageLine);
	        	   }
	          	 }
/*	           else if(objVector.size()== 0 || objVector == null)
	           {
	        	     AppException appException = new AppException();
	        	     log.info("ABCD now");
	        	     appException.printStackTrace();
	  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_DELIVERY_MESSAGE,"DeliveryMessageDAOImpl");  
	  				 throw appException;
	           }*/
		   }
		   else
		   {
			   call.setProcedureName("Pix_Del_Msg_Proc");
			   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
	           call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
	           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
	           call.addNamedArgumentValue("i_order_by",objorderby);
	           call.addNamedArgumentValue("i_sort",objsort); 
	           call.useNamedCursorOutputAsResultSet("list_refcursor");
	           Vector objVector = (Vector)objSession.executeSelectingCall(call);
	           
	         //executing stored procedure  
	           if (objVector != null && objVector.size()>0)
	           {
	          	   for (int i = 0; i < objVector.size(); i++)
	        	   {
	           		  objRecord = (Record)objVector.get(i);
	        		  objDeliveryMessage = new DeliveryMessage();
	        		  objDeliveryMessage.setMsgNo((String)objRecord.get(PIXConstants.DELIVERY_MESSAGE_NO));
	        		  objDeliveryMessage.setCreationDateTime((Date)objRecord.get(PIXConstants.CREATION_DATE_TIME));
	        		  objDeliveryMessage.setCreatedBy((String)objRecord.get(PIXConstants.CREATED_BY));
	        		  objDeliveryMessage.setMsgTypeDetail((String)objRecord.get(PIXConstants.MESSAGE_TYPE));
	        		  objDeliveryMessage.setName_1((String)objRecord.get(PIXConstants.DELIVERY_DESTINATION));
	        		  objDeliveryMessage.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
	        		  objListVector.add(objDeliveryMessage);
	        	   }
	          	 
	           }        
	           else if(objVector.size()== 0 || objVector == null)
	           {
	        	     AppException appException = new AppException();
	        	     
	  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_DELIVERY_MESSAGE,"DeliveryMessageDAOImpl");  
	  				 throw appException;
	           }
		   }
		  /* BigDecimal poId=poHeaderTemp.getPoId();
		   BigDecimal poVersion = poHeaderTemp.getPoVersion();
		   System.out.println("Po id for SQL: "+poId);
		   Session clientSession = getClientSession();
		   
		    Vector status= clientSession.executeSelectingCall(new SQLCall("select ppls.po_no,psc.status_code from pix_po_list_summary ppls,pix_status_code psc where ppls.status_id=psc.status_id and order_type in ('O','S') and ppls.po_id="+poId+" and ppls.po_version="+poVersion));
		   String stat=null;
		   for(int i=0;i<status.size();i++)
		   {
			   Record objRecord1 = (Record)status.get(i);
			   	stat=(String)objRecord1.get("STATUS_CODE");
			   if(stat!=null){
				   break;
			   }
		   }
		   objListVector.add(stat);
		   System.out.println("DAO:"+stat);
		   //poh.setStat(stat);*/
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  DeliverlymessageDaoImpl in list");
		  
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,getDeliveryMessageList",e);
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
    * Gets the Delivery Message Detail for a message number of PO from the database
    * 
    * @param poId
    * @param poVersion
    * @param messageId
    * @return com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO
    */
   public DeliveryMessage getDeliveryMessageDetail(BigDecimal poId,BigDecimal poVersion,Integer messageId,String orderType,String ownrshpMode) throws AppException
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.getDeliveryMessageDetail**************");
	   Session clientSession = null;
	   DeliveryMessage objDeliveryMessage = null;
	 
	   try
	   {
	   
		   
		   Record objRecord = null;
		   clientSession = getClientSession();
		   objDeliveryMessage = new DeliveryMessage();
		   Vector objContactCollection = new Vector();
  		   Vector objPartyCollection = new Vector();
  		   Vector objAddress=new Vector();
  		   if(ownrshpMode.equals("TRUE CONSIGNMENT") || ownrshpMode.equals("CONSIGNMENT")){
  			System.out.println("Naveen:::::::::::Order Type is and ownrshipMode and mesage id  is   "+ orderType+"  "+ownrshpMode+"   "+messageId);
  		    objAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("select pph.po_no,pph.release_no,pgr.RECEIPT_no as msg_no,NULL,ppp.name_1,"+
	                " ppp.address_1,ppp.city,ppp.postal_code,ppp.san,ppp.state,ppp.country_code,pppc.contact_first_name," +
	                " pppc.phone,pppc.mobile,pppc.fax,pppc.email from pix_po_header pph,pix_good_receipt pgr, " +
	                " pix_po_party ppp,pix_po_party_contact pppc where pph.po_id = pgr.po_id " +
	                " and pph.po_version = pgr.po_version and ppp.po_id = pgr.po_id and ppp.po_version = pgr.po_version " +
                    " and pppc.po_id = pgr.po_id and pppc.po_version = pgr.po_version " +
	                " and ppp.party_type = 'S' and pppc.party_line_no = ppp.party_line_no and pgr.receipt_id =" + messageId + ""));
  		    	
  		    	
  		   /*(Vector)clientSession.executeSelectingCall(new SQLCall("select pph.po_no,pph.release_no,pdm.msg_no,pr.description,ppp.name_1,"+
				                " ppp.address_1,ppp.city,ppp.postal_code,ppp.san,ppp.state,ppp.country_code,pppc.contact_first_name," +
				                " pppc.phone,pppc.mobile,pppc.fax,pppc.email from pix_po_header pph,pix_delivery_msg pdm, " +
				                " pix_po_party ppp,pix_po_party_contact pppc,pix_ref pr where pph.po_id = pdm.po_id " +
				                " and pph.po_version = pdm.po_version and ppp.po_id = pdm.po_id and ppp.po_version = pdm.po_version " +
                                " and pppc.po_id = pdm.po_id and pppc.po_version = pdm.po_version and pdm.msg_type_id = pr.ref_id " +
				                " and ppp.party_type = 'S' and pppc.party_line_no = ppp.party_line_no and pdm.msg_id =" + messageId + ""));*/
  		   }
  		   else if(ownrshpMode.equals("OWNED ON DELIVERY") || ownrshpMode.equals("")){
  			 objAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("select pph.po_no,pph.release_no,pdm.msg_no,pr.description,ppp.name_1,"+
		                " ppp.address_1,ppp.city,ppp.postal_code,ppp.san,ppp.state,ppp.country_code,pppc.contact_first_name," +
		                " pppc.phone,pppc.mobile,pppc.fax,pppc.email from pix_po_header pph,pix_delivery_msg pdm, " +
		                " pix_po_party ppp,pix_po_party_contact pppc,pix_ref pr where pph.po_id = pdm.po_id " +
		                " and pph.po_version = pdm.po_version and ppp.po_id = pdm.po_id and ppp.po_version = pdm.po_version " +
		                " and pppc.po_id = pdm.po_id and pppc.po_version = pdm.po_version and pdm.msg_type_id = pr.ref_id " +
		                " and ppp.party_type = 'S' and pppc.party_line_no = ppp.party_line_no and pdm.msg_id =" + messageId + ""));  
  			   
  		   }
  		  
		  if(objAddress != null && objAddress.size()>0)
		  {
			  for (int i = 0;i < objAddress.size();i++)
       	      {
				  objRecord = (Record)objAddress.get(i);
				  POParty poParty = new POParty();
				  Country objCountry = new Country();
				  POPartyContact objPOPartyContact = new POPartyContact();
       		      objDeliveryMessage.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
       		      objDeliveryMessage.setReleaseNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.RELEASE_NO)).intValue()));
       		      objDeliveryMessage.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
       		   
       		      objDeliveryMessage.setMsgTypeDetail((String)objRecord.get(PIXConstants.MESSAGE_TYPE));
       		      if(objDeliveryMessage.getMsgTypeDetail() == null || "".equals(objDeliveryMessage.getMsgTypeDetail()))
       		    	objDeliveryMessage.setMsgTypeDetail("Goods Receipt");
       		      poParty.setSan((String)objRecord.get(PIXConstants.SAN));
       		      poParty.setName1((String)objRecord.get(PIXConstants.NAME_1));
       		      poParty.setAddress1((String)objRecord.get(PIXConstants.ADDRESS_1));
       		      poParty.setCity((String)objRecord.get(PIXConstants.CITY));
       		      poParty.setPostalCode((String)objRecord.get(PIXConstants.POSTAL_CODE));
       		      poParty.setState((String)objRecord.get(PIXConstants.STATE));
       		      objCountry.setCountryCode((String)objRecord.get(PIXConstants.COUNTRY_CODE));
       		      poParty.setCountryDetail(objCountry);
       		      objPOPartyContact.setContactFirstName((String)objRecord.get(PIXConstants.CONTACT_FIRST_NAME));
       		      objPOPartyContact.setPhone((String)objRecord.get(PIXConstants.PHONE));
       		      objPOPartyContact.setMobile((String)objRecord.get(PIXConstants.MOBILE));
       		      objPOPartyContact.setFax((String)objRecord.get(PIXConstants.FAX));
       		      objPOPartyContact.setEmail((String)objRecord.get(PIXConstants.EMAIL));
       		  //  objPOPartyContact.setEmail((String)objRecord.get(PIXConstants.APPROVAL_FLAG));
       		      objContactCollection.add(objPOPartyContact);
       		      poParty.setContactCollection(objContactCollection);
       		      objPartyCollection.add(poParty);
       		  }

		  }
		  else if(objAddress.size()== 0 || objAddress == null)
          {
       	     AppException appException = new AppException();
 			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"DeliveryMessageDAOImpl");  
 			 throw appException;
          }
          
		  objDeliveryMessage.setPartyCollection(objPartyCollection);
	      Vector objVector = getDetailDeliveryMessageLine(poId,poVersion,orderType,messageId,ownrshpMode); 
		  objDeliveryMessage.setDeliveryMsgLineCollection(objVector);
		 
	   }
	 
	   catch(TopLinkException e)
	   {
		   log.debug("In DeliverlymessageDaoImpl in list");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,getDeliveryMessageDetail",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			  
			   clientSession.release();
		   }
	   }    
	   
    return objDeliveryMessage;
   }
   
   /**
    * Gets the Basic Delivery Message information from the database
    * @param poId
    * @param poVersion
    * @return java.util.HashMap
    */
   public HashMap getBasicDeliveryMessageInfo(BigDecimal poId, BigDecimal poVersion,String orderType) throws AppException
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.getBasicDeliveryMessageInfo**************");
	   Session clientSession = null;
	   DeliveryMessage objDeliveryMessage = null;
	   HashMap objHashMap = null;
	   try
	   {
		   Record objRecord = null;
		   clientSession = getClientSession();
		   objDeliveryMessage = new DeliveryMessage();
		   objHashMap = new HashMap();
		   Vector objContactCollection = new Vector();
  		   Vector objPartyCollection = new Vector();
  		   Vector objAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("select pph.po_no,pph.release_no," +
  		   		               " ppp.name_1,ppp.address_1,ppp.city,ppp.san,ppp.postal_code, ppp.state,ppp.country_code, " +
  				               " pppc.contact_first_name,pppc.phone,pppc.mobile,pppc.fax,pppc.email" + 
                               " from pix_po_header pph,pix_po_party ppp,pix_po_party_contact pppc " +
                               " where ppp.po_id = pph.po_id and ppp.po_version = pph.po_version " +
                               " and pppc.po_id = pph.po_id AND pppc.po_version = pph.po_version and " +
                               " ppp.party_type = 'S' and pppc.party_line_no = ppp.party_line_no and" +
                               " pph.po_id =" + poId + " and pph.po_version =" + poVersion + " "));
  		   
  		 if (objAddress != null && objAddress.size()>0)
         {
           for (int i = 0;i < objAddress.size();i++)
      	   {
        	   objRecord = (Record)objAddress.get(i);
        	   POParty poParty = new POParty();
 			   Country objCountry = new Country();
 			   POPartyContact objPOPartyContact = new POPartyContact();
 		       objDeliveryMessage.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
   		       objDeliveryMessage.setReleaseNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.RELEASE_NO)).intValue()));
   		       objDeliveryMessage.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
   		       objDeliveryMessage.setMsgTypeDetail((String)objRecord.get(PIXConstants.MESSAGE_TYPE));
   		       poParty.setSan((String)objRecord.get(PIXConstants.SAN));
   		       poParty.setName1((String)objRecord.get(PIXConstants.NAME_1));
   		       poParty.setAddress1((String)objRecord.get(PIXConstants.ADDRESS_1));
   		       poParty.setCity((String)objRecord.get(PIXConstants.CITY));
   		       poParty.setPostalCode((String)objRecord.get(PIXConstants.POSTAL_CODE));
   		       poParty.setState((String)objRecord.get(PIXConstants.STATE));
   		       objCountry.setCountryCode((String)objRecord.get(PIXConstants.COUNTRY_CODE));
   		       poParty.setCountryDetail(objCountry);
   		       objPOPartyContact.setContactFirstName((String)objRecord.get(PIXConstants.CONTACT_FIRST_NAME));
   		       objPOPartyContact.setPhone((String)objRecord.get(PIXConstants.PHONE));
   		       objPOPartyContact.setMobile((String)objRecord.get(PIXConstants.MOBILE));
   		       objPOPartyContact.setFax((String)objRecord.get(PIXConstants.FAX));
   		       objPOPartyContact.setEmail((String)objRecord.get(PIXConstants.EMAIL));
   		       objContactCollection.add(objPOPartyContact);
   		       poParty.setContactCollection(objContactCollection);
   		       objPartyCollection.add(poParty);
   		       objDeliveryMessage.setPartyCollection(objPartyCollection);
   		   }
        }
  		 else if(objAddress.size()== 0 || objAddress == null)
         {
      	     	 AppException appException=new AppException();
				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"DeliveryMessageDAOImpl");  
				 throw appException;
         }
     
  		Vector  objVector = getNewDeliveryMessageLine(poId,poVersion,orderType);
  		Vector objMsgType = getMessageTypes();
  		objHashMap.put("MessageTypes",objMsgType);
  		objDeliveryMessage.setDeliveryMsgLineCollection(objVector);
		objHashMap.put("objHashMap",objDeliveryMessage);
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("TopLinkException for getBasicDeliveryMessageInfo ");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,getBasicDeliveryMessageInfo",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   
			   clientSession.release();
		   }
	   }    
    return objHashMap;
   }
   /**
    * Gets the Delivery Message Types from the database
    * @param Vector
    */
   private Vector getMessageTypes() throws AppException
   {
	   Session clientSession = null;
	   DeliveryMessage objDeliveryMessage = null;
	   Vector objMessageType = new Vector();
	   try
	   {
		   clientSession = getClientSession();
		   Record objRecord = null;
		   Vector  messageTypes = clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/pr.ref_id,pr.description " +
				                         " from pix_ref pr, pix_table pt where pr.table_id = pt.table_id " +
				                         " and pt.table_name = 'PIX_DELIVERY_MSG' and pr.group_code ='MESSAGE_TYPE' " +
				                         " and pr.active_flag='Y' and pr.ref_code='DMSG' order by display_order"));
		   
		   if (messageTypes != null && messageTypes.size()>0)
	       {
	      	   for (int i = 0;i < messageTypes.size();i++)
	      	   {
	      		   objRecord = (Record)messageTypes.get(i);
	      		   objDeliveryMessage = new DeliveryMessage();
	      		   objDeliveryMessage.setMsg_type_id((BigDecimal)objRecord.get(PIXConstants.REF_ID));
	      		   objDeliveryMessage.setMsg_type((String)objRecord.get(PIXConstants.DESCRIPTION));
	      		   objMessageType.add(objDeliveryMessage);
	      	   }
	       }
		  
		   else if(messageTypes.size()== 0 || messageTypes == null)
	          {
	       	     	 AppException appException=new AppException();
	 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"DeliveryMessageDAOImpl");  
	 				 throw appException;
	          }
	      
	       
	    } 
	   
	   catch(TopLinkException e)
	   {
		   log.debug("TopLinkException for getMessageTypes ");
		    AppException appException = new AppException();
	        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DeliveryMessageDAOImpl,getDetailDeliveryMessageLine",e);
	        
	        throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   
			   clientSession.release();
		   }
	   }
	 return objMessageType;
   }
   /**
    * Gets the Basic Delivery Message Line information from the database
    * 
    * @param messageId
    * @return java.util.Vector
    */
   private Vector getDetailDeliveryMessageLine(BigDecimal poId, BigDecimal poVersion,String orderType,Integer messageId,String ownrshpMode) throws AppException
   {
	   
	   System.out.println("Naveen:::::::::::::::DeliveryMessageDAOImpl.getDetailDeliveryMessageLine");
	   Session clientSession = null;
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   Vector objMsgVector = new Vector();
	   Date objrequestedDeliveryDate = null;
	   Vector  poLineVector=new Vector();
	   String objrequestedDeliveryDate_String = new String();
	   try
	   {
		   Record objRecord = null;
		   Record objRecordPo = null;
		   clientSession = getClientSession();
		   Date objEstimatedDeliveryDate = null;
		   String objEstimatedDeliveryDate_String=null;
		   
		 Vector objVectorPo = (Vector)clientSession.executeSelectingCall(new SQLCall("select distinct po_id,po_version from pix_Delivery_msg_line where msg_id=" + messageId));
			    			  
					  if (objVectorPo != null && objVectorPo.size()>0)
				       {
						  objRecordPo = (Record)objVectorPo.get(0);
						  poVersion=(BigDecimal)objRecordPo.get(PIXConstants.PO_VERSION);
				     }     
					             
					
		   	StoredProcedureCall call = new StoredProcedureCall(); 
	   		call.setProcedureName("PIX_PMS_DEL_MSG_PROC");
	   		call.addNamedArgumentValue("i_po_id",messageId);
	   		call.addNamedArgumentValue("i_po_version",null);
	   		call.addNamedArgumentValue("i_request_type","DETAIL");
	   		call.addNamedArgumentValue("i_ownership_mode",ownrshpMode);
	   		
	   		call.useNamedCursorOutputAsResultSet("list_refcursor");
	   		Vector objVector = (Vector)clientSession.executeSelectingCall(call);
	   		
	       	 //and(builder.get("poVersion").equal(poVersion))
	       if("O".equals(orderType)){
	    	   ExpressionBuilder builder = new ExpressionBuilder();
	    	   Expression wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("orderType").equal(orderType));
	           // Expression wcPO = builder.get("poId").equal(poId).and(builder.get("orderType").equal(orderType));
			   POHeader poHeader = (POHeader)clientSession.readObject(POHeader.class,wcPO);
			   poLineVector=(Vector)poHeader.getLineItemCollection(); 
			  
			   
			   }
	       
	   if (objVector != null && objVector.size()>0)
       {
      	   for (int i = 0;i < objVector.size();i++)
    	   {
      		   objRecord = (Record)objVector.get(i);
      		
      		   objDeliveryMessageLine = new DeliveryMessageLine();
      		   
      		  objDeliveryMessageLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
      		 if("O".equals(orderType)&&poLineVector.size()!=0&&i<=poLineVector.size()){
      		 for(int y=0;y<poLineVector.size();y++){
  			   POLine poLine=(POLine)poLineVector.get(y);
  			 //log.info(poLine.getPoLineNo()+ "====="+poLine.getProductCode());
  			
  			if(((BigDecimal)objRecord.get(PIXConstants.LINE_NO)).equals(poLine.getPoLineNo())){
  			   objDeliveryMessageLine.setLinePartyCollection(poLine.getLinePartyCollection());
  			   }
  			if(objRecord.get(PIXConstants.RECEIVED_QUANTITY)!=null)		//for cumulative posted quantity
			   {
				   objDeliveryMessageLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QUANTITY)).intValue()));
			   }
  			
  			
      		 }
  		       }
      		BigDecimal num = null;
      		   objDeliveryMessageLine.setUom_id((BigDecimal)objRecord.get(PIXConstants.UOM_ID));
      		   objDeliveryMessageLine.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
      		   
      		   
      		   if((String)objRecord.get("LINE_DESCRIPTION")!=null)
      		   {
      			 objDeliveryMessageLine.setLineDecription((String)objRecord.get("LINE_DESCRIPTION"));
      		   }
      		   if((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QUANTITY)!=null)
      		   {
      			   objDeliveryMessageLine.setDeliveredQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QTY)).intValue()));
      		   }
      		
      		   if((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QTY)!=null)
    		   {
      			 objDeliveryMessageLine.setReceivedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QTY)).intValue()));  
    		   }
      		
      		//objDeliveryMessageLine.setReceivedQuantity(new Integer(100));
      		  if((BigDecimal)objRecord.get(PIXConstants.OWNED_QTY)!=null)
      		  {
      			objDeliveryMessageLine.setOwnedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.OWNED_QTY)).intValue()));
      		  }
      		
      			
      		//objDeliveryMessageLine.setOwnedQuantity(new Integer(100));
      		if((BigDecimal)objRecord.get(PIXConstants.TO_BE_OWNED_QTY)!=null)
    		  {
      			objDeliveryMessageLine.setToBeOwnedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.TO_BE_OWNED_QTY)).intValue()));
    		  }
    		
      	
      			
      			//objDeliveryMessageLine.setToBeOwnedQuantity(new Integer(100));
      		if((String)objRecord.get(PIXConstants.OWNERSHIP_MODE)!=null)
  		  {
      			objDeliveryMessageLine.setOwnrshpMode((String)objRecord.get(PIXConstants.OWNERSHIP_MODE));	
  		  }
  		
    	
      	
      			
      			// objDeliveryMessageLine.setOwnrshpMode("CONSIGNMENT");
   		
      		if((BigDecimal)objRecord.get(PIXConstants.OWNING_QTY)!=null)
  		  {
      			objDeliveryMessageLine.setOwningQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.OWNING_QTY)).intValue()));   
  		  }
      	
      			
      			//objDeliveryMessageLine.setOwningQuantity(new Integer(100));
    		  
      	//Naveen
			   if((objrequestedDeliveryDate = (Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
               {
    			   objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
    			   objDeliveryMessageLine.setRequestedDeliveryDate(objrequestedDeliveryDate_String);
           	   } 
			   if((objEstimatedDeliveryDate=(Date)objRecord.get(PIXConstants.ESTIMATED_DELIVERY_DATE))!= null)
               {
			    	objEstimatedDeliveryDate_String= PIXUtil.sqlToStandardDate(objEstimatedDeliveryDate.toString());
			    	objDeliveryMessageLine.setEstimatedDeliveryDate(objEstimatedDeliveryDate_String);
          	   } 
			   if((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)!=null)
      		   {
				   objDeliveryMessageLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)).intValue()));
      		   }
			   if((String)objRecord.get(PIXConstants.PRODUCT_CODE)!=null)
      		   {
				   objDeliveryMessageLine.setProductCode((String)objRecord.get(PIXConstants.PRODUCT_CODE));
      		   }
			   if((String)objRecord.get(PIXConstants.APPROVAL_FLAG)!=null)
      		   {
      			 objDeliveryMessageLine.setApprovalFlag((String)objRecord.get(PIXConstants.APPROVAL_FLAG));
      		   }
			   if((BigDecimal)objRecord.get(PIXConstants.FILE_EXISTS)!=null)
      		   {
				   objDeliveryMessageLine.setFileExists(new Integer(((BigDecimal)objRecord.get(PIXConstants.FILE_EXISTS)).intValue()));
      		   }
			   if((BigDecimal)objRecord.get(PIXConstants.MAX_DM_QTY)!=null)
    		   {
    			   objDeliveryMessageLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get(PIXConstants.MAX_DM_QTY)).longValue()));
    		   }
			   
			// flag
      		   if((BigDecimal)objRecord.get("RT_FLAG")!=null)
      		   {
      			   num = (BigDecimal)objRecord.get("RT_FLAG");
      			   objDeliveryMessageLine.setRtFlag((BigDecimal)objRecord.get("RT_FLAG"));
      			 System.out.println("*******"+num);
      		   }
      		   String rolDMExists = null;
      		   if((String)objRecord.get("ROLL_DM_EXISTS")!=null)
      		   {
      			 rolDMExists = (String)objRecord.get("ROLL_DM_EXISTS");
      			objDeliveryMessageLine.setRollDMExists((String)objRecord.get("ROLL_DM_EXISTS"));
      			//   objDeliveryMessageLine.setRtFlag((BigDecimal)objRecord.get("RT_FLAG"));
      			 System.out.println("*******"+rolDMExists);
      		   }

			   
			   objMsgVector.add(objDeliveryMessageLine);
	       }
      
      	}
	   else if(objVector.size()== 0 || objVector == null)
	   {
       	     	 AppException appException=new AppException();
 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"DeliveryMessageDAOImpl");  
 				 throw appException;
       } 
	  
	   }
	   catch(TopLinkException e)
	     {
		   log.debug("TopLinkException for DeliverymessageLine ");
		    AppException appException = new AppException();
	        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DeliveryMessageDAOImpl,getDetailDeliveryMessageLine",e);
	        
	        throw appException;
	     }
	    finally
		    {
			   if(clientSession!=null)
			   {
				   
				   clientSession.release();
			   }
		    }
	   
	   return objMsgVector;
	   
   }
   /**
    * Gets the Basic Delivery Message Line information from the database
    * @param poId
    * @param poVersion
    * @return java.util.Vector
   */ 
     
   private Vector getNewDeliveryMessageLine(BigDecimal poId, BigDecimal poVersion,String orderType) throws AppException
   {
	   Session clientSession = null;
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   Vector objMsgVector = new Vector();
	   Date objrequestedDeliveryDate = null;
	   String objrequestedDeliveryDate_String = new String();
	   Vector  poLineVector=new Vector();
	   Vector objVector = null;
	   try
	   {
		   clientSession = getClientSession();
		   Record objRecord = null;
		   
		   
		   
		   	if("O".equals(orderType)){
		   		//objVector = (Vector)clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ ppl.po_line_no as line_no,"+
                 //       " ppl.requested_quantity as Original_qty,ppl.uom_id,ppl.product_description ,ppl.requested_delivery_date,ppl.product_code," +
                  //      " (select nvl(sum(pdml.delivered_quantity),0) from pix_delivery_msg_line pdml" +
                   //     " where  pdml.po_id=ppl.po_id and pdml.po_version=ppl.po_version " +
                    //    " and pdml.po_line_no=ppl.po_line_no) as received_quantity " +
                    //    " from pix_po_line ppl  where "+ 
                    //    " ppl.po_id = "+ poId +" and ppl.po_version = "+ poVersion +"" +
                    //    " AND NOT EXISTS(SELECT 'X' FROM pix_status_code psc" +
                     //   " WHERE psc.table_id=(SELECT table_id FROM pix_table WHERE table_name='PIX_PO_LINE')" +
                      //  " AND psc.STATUS_id=ppl.SUPPLIER_STATUS_ID AND psc.STATUS_DESCRIPTION='Rejected')" +
                       // " ORDER BY ppl.po_line_no "));
		   		StoredProcedureCall call = new StoredProcedureCall(); 
		   		call.setProcedureName("PIX_PMS_DEL_MSG_PROC");
		   		call.addNamedArgumentValue("i_po_id",poId);
		   		call.addNamedArgumentValue("i_po_version",poVersion);
		   		//call.addNamedArgumentValue("i_po_version",poVersion);
		   		call.addNamedArgumentValue("i_ownership_mode",null);
		   		call.addNamedArgumentValue("i_request_type","NEW");
		   		call.useNamedCursorOutputAsResultSet("list_refcursor");
		   		objVector = (Vector)clientSession.executeSelectingCall(call);
		   		
                                                
 		   	}
	//	   	else
	//	   	{	
	//		   objVector = (Vector)clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ ppl.po_line_no as line_no,"+
	//	                          " ppl.requested_quantity as Original_qty,ppl.uom_id,ppl.product_description ,ppl.requested_delivery_date,ppl.product_code from pix_po_line ppl  where "+ 
	//	                          " ppl.po_id = "+ poId +" and ppl.po_version = "+ poVersion +" Order by ppl.po_line_no"));
	//	   	}
	         
		   	else
		   	{	
			   objVector = (Vector)clientSession.executeSelectingCall(new SQLCall("select ppl.po_line_no as line_no,ppl.requested_quantity as Original_qty,"+
					   " ppl.uom_id,ppl.product_description , ppl.requested_delivery_date, ppl.product_code "+ 
					   " from pix_po_line ppl,pix_status_code psc  where ppl.po_id = "+ poId +" and ppl.po_version = "+ poVersion + 
					   "  and nvl(ppl.supplier_status_id,pub_unit_status_id)=psc.status_id and   psc.status_code not in ('REJECTED','CANCELLED')"+
					   "  Order by ppl.po_line_no"));
		   	}
		   	
		   HashMap polineMap=new HashMap();
		   
		   if("O".equals(orderType)){
           ExpressionBuilder builder = new ExpressionBuilder();
           Expression wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("orderType").equal(orderType));
		   POHeader poHeader = (POHeader)clientSession.readObject(POHeader.class,wcPO);
		   poLineVector=(Vector)poHeader.getLineItemCollection(); 
		   for(int i=0;i<poLineVector.size();i++){
			   POLine poLineTemp=(POLine)poLineVector.get(i);
			   BigDecimal lineNo=poLineTemp.getPoLineNo();
			   polineMap.put(lineNo,poLineTemp);
		   }
		   
		   
		   }
		   
           objDeliveryMessageLine = new DeliveryMessageLine();
           
           if (objVector != null && objVector.size()>0)
           {
        	   for (int i = 0;i < objVector.size();i++)
        	   {
        		   objRecord = (Record)objVector.get(i);
        		   objDeliveryMessageLine = new DeliveryMessageLine(); 
        		   BigDecimal lineNo=(BigDecimal)objRecord.get(PIXConstants.LINE_NO);
        		 // System.out.println("line no...."+lineNo);  &&contLineNo<=poLineVector.size()
        		   objDeliveryMessageLine.setLineNo(lineNo);
        		   // int contLineNo=lineNo.intValue();
        		   if("O".equals(orderType)&&poLineVector.size()!=0&&polineMap.size()!=0){
        			   // POLine poLine=(POLine)poLineVector.get(contLineNo-1);
        			   POLine poLine=(POLine)polineMap.get(lineNo);
        			   objDeliveryMessageLine.setLinePartyCollection(poLine.getLinePartyCollection());
        			   
        			   if(objRecord.get(PIXConstants.RECEIVED_QUANTITY)!=null)
        			   {
        				   objDeliveryMessageLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QUANTITY)).intValue()));
        			   }
        			   if(PIXUtil.checkNullField(poLine.getLineDecription()))
        			   {
        				   objDeliveryMessageLine.setLineDecription(poLine.getLineDecription());
        			   }
        		   }
        		             		    
        		   
        		   objDeliveryMessageLine.setUom_id((BigDecimal)objRecord.get(PIXConstants.UOM_ID));
        		   objDeliveryMessageLine.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
        		   if((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)!=null)
        		   {	   
        			   objDeliveryMessageLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)).intValue()));
        		   }
        		   if((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QUANTITY)!=null)
        		   {
        			   objDeliveryMessageLine.setDeliveredQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DELIVERED_QUANTITY)).intValue()));
        		   }
        		   if((objrequestedDeliveryDate = (Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
                   {
        			   objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
        			   objDeliveryMessageLine.setRequestedDeliveryDate(objrequestedDeliveryDate_String);
              	   }
        		   
        		   if((String)objRecord.get("PRODUCT_CODE")!=null)
        		   {
        			   objDeliveryMessageLine.setProductCode((String)objRecord.get("PRODUCT_CODE"));
        		   }
        		   if((BigDecimal)objRecord.get(PIXConstants.MAX_DM_QTY)!=null)
        		   {
        			   objDeliveryMessageLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get(PIXConstants.MAX_DM_QTY)).longValue()));
        		   }
        		   
        		   // setting DeliverymessageLine 
        		   objMsgVector.add(objDeliveryMessageLine);

        	   }
        	  
           }
           else if((objVector.size()== 0 || objVector == null) && !"O".equals(orderType))
    	   {
           	  /*  AppException appException=new AppException();
     			appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"DeliveryMessageDAOImpl");  
     			appException.printStackTrace();
     			throw appException;*/
        	   return objMsgVector;
        	   
           } 
           else if((objVector.size()== 0 || objVector == null) && "O".equals(orderType))
           {
        	   return objMsgVector;
           }
           
	   }
	   catch(TopLinkException e)
	     {
		    log.debug("In DeliverlymessageDaoImpl in getNewDeliveryMessageLine");
		    AppException appException = new AppException();
	        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DeliveryMessageDAOImpl,getNewDeliveryMessageLine",e);
	        
	        throw appException;
	     }
	    finally
		    {
			   if(clientSession!=null)
			   {
				   
				   clientSession.release();
			   }
		    }
	   
	   return objMsgVector;
	   
   }
   
   /**
    * Saves the Delivery Message information into the database
    * @param poHeaderTemp
    * @param size
    * @param objUser 
    * @param objDeliveryMessage
    */
   public String insertDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int size,int headerSize,String objUser,String roleType) throws AppException 
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.insertDeliveryMessage**************");
	   Session clientSession = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   PreparedStatement stmtheader = null;
	   Vector   headerseqVal = null;
	   Vector   seqVal = new Vector();
	   PreparedStatement stmtLine = null;
	   PreparedStatement delMsgFile = null;
	   String mgsStr="";
	   try
	   {
		   Record objRecord = null; 
		   String msgNumber = null;
		   BigDecimal statusId = null;
		   Integer msgId = null;
		   transaction = getUserTransaction();
		   conn = getDataSourceConnection(); 
		   String query="";
		   //Statement stmt = conn.createStatement();	  
		   clientSession = getClientSession();
		   Vector status_id=null;
		   Vector msg_id=null;
		   // to get status id for delivery message
		  // System.out.println("start....");
		   if("M".equals(roleType)){
			   for(int i=0; i<size; i++)
			   {
			   BigDecimal lineNo=objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
			   
			   query= "SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_DELIVERY_MSG'),pix_get_status_id_func('REPLACED','PIX_DELIVERY_MSG'))AS STATUS_ID "+
	           " FROM PIX_DELIVERY_MSG_LINE WHERE PO_ID= "+poHeaderTemp.getPoId()+" AND PO_VERSION= "+poHeaderTemp.getPoVersion()+" AND PO_LINE_NO="+lineNo;
			   status_id = (Vector)clientSession.executeSelectingCall(new SQLCall(query));
			   }
		   }else{
		  query= "SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_DELIVERY_MSG'),pix_get_status_id_func('REPLACED','PIX_DELIVERY_MSG'))AS STATUS_ID "+
           " FROM PIX_DELIVERY_MSG WHERE PO_ID= "+poHeaderTemp.getPoId()+" AND PO_VERSION= "+poHeaderTemp.getPoVersion()+"";
		     status_id = (Vector)clientSession.executeSelectingCall(new SQLCall(query));
		   }
		   
		   if(status_id != null)
		   {
			   for (int i = 0; i < status_id.size(); i++)
       	       {
          		  objRecord = (Record)status_id.get(i);
          		  statusId = (BigDecimal)objRecord.get("STATUS_ID");
       		   }
		   }
		   //query for inserting header of Delivery message
		  // Integer status_Id=new Integer(String.valueOf(statusId));
		   if(!"M".equals(roleType)){
		   for(int j=0; j<headerSize; j++)
		   {
			   stmtheader = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG(MSG_ID,MSG_NO,PO_ID,PO_VERSION,MSG_TYPE_ID,STATUS_ID,CREATED_BY,MODIFIED_BY,CREATION_DATE_TIME,MOD_DATE_TIME)" +
	                       " VALUES (seq_PIX_DELIVERY_MSG_id.nextval,('DM_'||?||'_'||?||'_'|| seq_PIX_DELIVERY_MSG_id.CURRVAL),?," +
	                       " ?,?,?,?,?,SYSDATE,SYSDATE)");
			   stmtheader.setObject(1, objDeliveryMessage.getPoNo());
			   stmtheader.setObject(2, objDeliveryMessage.getReleaseNo());
			   stmtheader.setObject(3, poHeaderTemp.getPoId());
			   stmtheader.setObject(4, poHeaderTemp.getPoVersion());
			   stmtheader.setObject(5, objDeliveryMessage.getMsgTypeDetail());
			   stmtheader.setObject(6, statusId);
			   stmtheader.setObject(7, objUser);
			   stmtheader.setObject(8, objUser);
			   stmtheader.executeQuery();
		   }
		   if("M".equals(roleType)){
			   headerseqVal=(Vector)clientSession.executeSelectingCall(new SQLCall("select seq_PIX_DELIVERY_MSG_id.CURRVAL from dual"));
			   Record record = (Record)headerseqVal.get(0);
			   seqVal.add((BigDecimal)record.get("CURRVAL"));
		    }
		  
		   }
		   /*LinkedHashMap lhMap=new LinkedHashMap();
		   if("M".equals(roleType)){
			   LinkedHashSet set= objDeliveryMessage.getSanWithPOLine();
			   Iterator it=set.iterator();
			   int i=0;
			   while(it.hasNext()){
				   String san=(String)it.next() ;
				   BigDecimal seq=(BigDecimal)seqVal.get(i);
				   lhMap.put(san,seq);
				   i++;
			   }
		   }*/
		   //query for inserting Line of Delivery message
		  String oldSAN = null;
		  Vector oldSANColl = new Vector(); 
		
		   for(int i=0; i<size; i++)
		   {
			   
			   /*stmtLine = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY," +
		    		     " UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO) VALUES (seq_PIX_DELIVERY_MSG_id.currval,?, " +
		    		     " ?,?,to_date(?,'MM/DD/YYYY')," +
		    		     " 0,?,?,?)");*/
			   BigDecimal lineNo=null ;
			  
			 if("M".equals(roleType)&&objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDeliveredQuantity().intValue()==0){
					continue;
			   }
			  
			 
			  
			  
			  if("M".equals(roleType)){
				  
				  if (!oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
				  {
					 // System.out.println("...deliver mssg ....if old san not equal ship to san......"+msg_id);
					  stmtheader = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG(MSG_ID,MSG_NO,PO_ID,PO_VERSION,MSG_TYPE_ID,STATUS_ID,CREATED_BY,MODIFIED_BY,PO_LINE_NO,CREATION_DATE_TIME,MOD_DATE_TIME)" +
		                       " VALUES (seq_PIX_DELIVERY_MSG_id.nextval,('DM_'||?||'_'||?||'_'|| seq_PIX_DELIVERY_MSG_id.CURRVAL),?," +
		                       " ?,?,?,?,?,?,SYSDATE,SYSDATE)");
				   stmtheader.setObject(1, objDeliveryMessage.getPoNo());			   
				   stmtheader.setObject(2, objDeliveryMessage.getReleaseNo());
				   stmtheader.setObject(3, poHeaderTemp.getPoId());
				   stmtheader.setObject(4, poHeaderTemp.getPoVersion());
				   stmtheader.setObject(5, objDeliveryMessage.getMsgTypeDetail());
				   stmtheader.setObject(6, statusId);
				   stmtheader.setObject(7, objUser);
				   stmtheader.setObject(8, objUser);
				   stmtheader.setObject(9, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
				   stmtheader.executeQuery();
				   		   				   
				   oldSAN = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan();
				   //oldSANColl.addElement(oldSAN);
				   
				   
				   
				  // System.out.println("...deliver mssg line.........."+msg_id);
				  // System.out.println("msg line number...deliver mssg line...."+objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
					  String insertQuery="INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY," +
			 		     " UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO,CREATED_BY,MODIFIED_BY) VALUES (seq_PIX_DELIVERY_MSG_id.currval,?, " +
			 		     " ?,?,to_date(?,'MM/DD/YYYY')," +
			 		     " 0,?,?,?,?,?)";
					  stmtLine = conn.prepareStatement(insertQuery);
					  //stmtLine.setObject(1, (BigDecimal)lhMap.get(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()));
					   lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
					  stmtLine.setObject(1, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
					   stmtLine.setObject(2, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDeliveredQuantity());
					   stmtLine.setObject(3, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getUom_id());
					   stmtLine.setObject(4, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getEstimatedDeliveryDate());
					   stmtLine.setObject(5, poHeaderTemp.getPoId());
					   stmtLine.setObject(6, poHeaderTemp.getPoVersion());
					   stmtLine.setObject(7, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
					   stmtLine.setObject(8, objUser);
					   stmtLine.setObject(9, objUser);
				  }
				  else if(oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
				  {
					  query= "SELECT MAX(msg_id) as MSG_ID FROM (SELECT pdml.MSG_ID FROM PIX_DELIVERY_MSG_line pdml" +
					  		" WHERE pdml.po_line_no IN (SELECT DISTINCT(pplp.PO_LINE_NO)" +
					  		" FROM pix_po_line_party pplp WHERE pplp.san = '"+objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()+
					  		"' AND pplp.PO_ID="+poHeaderTemp.getPoId()+" AND pplp.PO_VERSION="+poHeaderTemp.getPoVersion()+"))";
					     msg_id = (Vector)clientSession.executeSelectingCall(new SQLCall(query));
					     
					     
				     if(msg_id != null)
					   {
						   for (int j = 0; j < msg_id.size(); j++)
			       	       {
			          		  objRecord = (Record)msg_id.get(j);
			          		  msgId = new Integer(((BigDecimal)objRecord.get("MSG_ID")).intValue());
			       		   }
					   } 
				     
				    // System.out.println("...deliver mssg line.....if......"+msg_id);
				    // System.out.println("msg line number...deliver mssg line....if.."+objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
				     String insertQuery="INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY," +
		 		     " UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO,CREATED_BY,MODIFIED_BY) VALUES (?,?, " +
		 		     " ?,?,to_date(?,'MM/DD/YYYY')," +
		 		     " 0,?,?,?,?,?)";
				  stmtLine = conn.prepareStatement(insertQuery);
				  //stmtLine.setObject(1, (BigDecimal)lhMap.get(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()));
				  stmtLine.setObject(1, msgId);
				  lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
				  stmtLine.setObject(2, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
				   stmtLine.setObject(3, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDeliveredQuantity());
				   stmtLine.setObject(4, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getUom_id());
				   stmtLine.setObject(5, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getEstimatedDeliveryDate());
				   stmtLine.setObject(6, poHeaderTemp.getPoId());
				   stmtLine.setObject(7, poHeaderTemp.getPoVersion());
				   stmtLine.setObject(8, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
				   stmtLine.setObject(9, objUser);
				   stmtLine.setObject(10, objUser);   
				  }

				  
			  }else{
				 // System.out.println("...deliver mssg line.....else......"+msg_id);
				 // System.out.println("msg line number...deliver mssg line....else..."+objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
				  
				  String insertQuery="INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY," +
		 		     " UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO,CREATED_BY,MODIFIED_BY) VALUES (seq_PIX_DELIVERY_MSG_id.currval,?, " +
		 		     " ?,?,to_date(?,'MM/DD/YYYY')," +
		 		     " 0,?,?,?,?,?)";
			   stmtLine = conn.prepareStatement(insertQuery);
			   lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
			   stmtLine.setObject(1, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
			   stmtLine.setObject(2, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDeliveredQuantity());
			   stmtLine.setObject(3, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getUom_id());
			   stmtLine.setObject(4, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getEstimatedDeliveryDate());
			   stmtLine.setObject(5, poHeaderTemp.getPoId());
			   stmtLine.setObject(6, poHeaderTemp.getPoVersion());
			   stmtLine.setObject(7, objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
			   stmtLine.setObject(8, objUser);
			   stmtLine.setObject(9, objUser); 
			  }
			  stmtLine.executeQuery();
			  //change for delivery message file data saving in case of Mill user for uploading information
			  if("M".equals(roleType)){
				  
				  if(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollection()!=null)
				  {
					  int delFileSize = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollection().size();
					  for(int j=0;j<delFileSize;j++)
					  {	 //System.out.println("...deliver mssg file.........."+msg_id);
						 // System.out.println("msg line number......."+objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
						  
						  String insertFileInfo = null;
						  if (!oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
						  {
						  insertFileInfo = "INSERT INTO PIX_DELIVERY_MSG_FILE (FILE_ID, MSG_ID, MSG_LINE_NO, PO_NO," +
					  			" PO_LINE_NO, BILL_OF_LADING, FREIGHT_NO, FILE_URL, CARRIER_NO, CREATION_DATE_TIME," +
					  			" MODIFICATION_DATE_TIME,CREATED_BY, MODIFIED_BY, ACTIVE_FLAG, PMS_TRANSACTION_DATE)"+
					  			" VALUES(seq_PIX_DM_FILE_ID.nextval,seq_PIX_DELIVERY_MSG_id.currval,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?)";
						  }
						  else if(oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
						  {
							  insertFileInfo = "INSERT INTO PIX_DELIVERY_MSG_FILE (FILE_ID, MSG_ID, MSG_LINE_NO, PO_NO," +
					  			" PO_LINE_NO, BILL_OF_LADING, FREIGHT_NO, FILE_URL, CARRIER_NO, CREATION_DATE_TIME," +
					  			" MODIFICATION_DATE_TIME,CREATED_BY, MODIFIED_BY, ACTIVE_FLAG, PMS_TRANSACTION_DATE)"+
					  			" VALUES(seq_PIX_DM_FILE_ID.nextval,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?)";
						  }
						  delMsgFile = conn.prepareStatement(insertFileInfo);
						  
						  if (oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
						  {
							 // System.out.println("..msgIdmsgId......."+msgId);
							  delMsgFile.setObject(1,msgId);
							  lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
							  delMsgFile.setObject(2,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
							  
							  delMsgFile.setObject(3,objDeliveryMessage.getPoNo());
							  delMsgFile.setObject(4,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
							  delMsgFile.setObject(5,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getBillOfLaiding());
							  delMsgFile.setObject(6,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getFrieghtNo());
							  delMsgFile.setObject(7,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getFileUrl());
							  delMsgFile.setObject(8,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCarrierNo());
							  //delMsgFile.setObject(8,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreationDateTime());
							  //delMsgFile.setObject(9,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreationDateTime());
							  delMsgFile.setObject(9,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreatedBy());
							  delMsgFile.setObject(10,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getModifiedBy());
							  delMsgFile.setObject(11,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getActiveFlag());
							  delMsgFile.setObject(12,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getPmsTransactionDate());
							  
						  }
						  else
						  {
						  
						 lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
						  delMsgFile.setObject(1,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
						  
						  delMsgFile.setObject(2,objDeliveryMessage.getPoNo());
						  delMsgFile.setObject(3,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo());
						  delMsgFile.setObject(4,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getBillOfLaiding());
						  delMsgFile.setObject(5,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getFrieghtNo());
						  delMsgFile.setObject(6,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getFileUrl());
						  delMsgFile.setObject(7,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCarrierNo());
						  //delMsgFile.setObject(8,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreationDateTime());
						  //delMsgFile.setObject(9,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreationDateTime());
						  delMsgFile.setObject(8,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getCreatedBy());
						  delMsgFile.setObject(9,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getModifiedBy());
						  delMsgFile.setObject(10,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getActiveFlag());
						  delMsgFile.setObject(11,objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDelMsgFilesCollectionIdx(j).getPmsTransactionDate());
						  //oldSANColl.addElement(oldSAN);
						  }
						  delMsgFile.executeQuery();
						  
						  
						  
						  
					  }
				  }
				  
				  
			  }
			  
			  
			  if (!oldSANColl.contains(objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getShipToSan()))
			  {
				  oldSANColl.addElement(oldSAN);
			  }
			  
			  Vector msseqV=(Vector)clientSession.executeSelectingCall(new SQLCall("select seq_PIX_DELIVERY_MSG_id.CURRVAL from dual"));
			   Record recordMsseq = (Record)msseqV.get(0);
			   BigDecimal msgIdLine=(BigDecimal)recordMsseq.get("CURRVAL");
			   mgsStr= mgsStr.concat(msgIdLine+"~"+lineNo+"#");
			   
			  
			  
		  }
		   // getting delivery message number
		  Vector Delivery_msg_no = (Vector)clientSession.executeSelectingCall(new SQLCall("Select ('DM_'||'"+objDeliveryMessage.getPoNo()+"'||'_'||"+objDeliveryMessage.getReleaseNo()+"||'_'|| seq_PIX_DELIVERY_MSG_id.CURRVAL) AS DELIVERY_MESSAGE_NO from dual"));
		 
		  if (Delivery_msg_no != null && Delivery_msg_no.size()>0)
          {
         	   for (int i = 0; i < Delivery_msg_no.size(); i++)
       	       {
          		  objRecord = (Record)Delivery_msg_no.get(i);
       		      msgNumber = (String)(String)objRecord.get("DELIVERY_MESSAGE_NO");       		      
       	       }
          }	   
		 if(msgNumber!=null&&!"".equals(mgsStr)){
			 msgNumber=msgNumber.concat("#"+mgsStr);
		 }		 
		return msgNumber;
	   }
      catch(TopLinkException e)
      {
    	  log.debug("In DeliverlymessageDaoImpl in insertDeliveryMessage");
    	   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,insertDeliveryMessage",e);
		   try 
  		   { 
  			   transaction.setRollbackOnly();
  		   } 
  		   catch(Throwable tu) 
  		   { 
  			   throw appException; 
  		   }
  		
		   throw appException;
      }
      catch(SQLException se)
 	   {
 		   AppException ae = new AppException();
 		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"DeliveryMessageDAOImpl,insertDeliveryMessage",se);
 		   try 
 		   { 
 			   transaction.setRollbackOnly();
 		   } 
 		   catch(Throwable tu) 
 		   { 
 			   throw ae; 
 		   }
 		   throw ae;
 	   }
      finally
	    {
    	try 
		   { 
    		   if(clientSession!=null)
			       clientSession.release();
			   if(stmtheader!=null)
				   stmtheader.close();
			   if(stmtLine!=null)
				   stmtLine.close();
			   if(conn!=null)
				   conn.close();
		   } 
		   catch(Throwable tu) 
		   { 
			   throw new RuntimeException(tu); 
		   }
      }
  
   }
   
   /**
    * Gets the Delivery Message List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Collection
    */
   public Collection inboxDeliveryMessageList(Integer days,Integer user_id,int currentValue,String objorderby,String objsort) throws AppException
   {
   
	   System.out.println("**********************DeliveryMessageDAOImpl.inboxDeliveryMessageList**************");
	   Session objSession = getClientSession();
	   DeliveryMessage objDeliveryMessage = null;
	   Vector objListVector = new Vector();
	   try
	   {
		   Record objRecord = null;
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("pix_msg_count_proc");
		   call.addNamedArgumentValue("i_user_id",user_id);
           call.addNamedArgumentValue("i_days",days);
           call.addNamedArgumentValue("i_pagination",new Integer(currentValue));
           call.addNamedArgumentValue("i_order_by",objorderby);
           call.addNamedArgumentValue("i_sort",objsort); 
           
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           // executing stored procedure     
           if (objVector != null && objVector.size()>0)
           {
          	   for (int i = 0; i < objVector.size(); i++)
        	   {
           		  objRecord = (Record)objVector.get(i);
        		  objDeliveryMessage = new DeliveryMessage();
        		  objDeliveryMessage.setMsgNo((String)objRecord.get(PIXConstants.DELIVERY_MESSAGE_NO));
        		  objDeliveryMessage.setMaterialNo((String)objRecord.get(PIXConstants.MATERIAL_NO));
        		  objDeliveryMessage.setCreationDateTime((Date)objRecord.get(PIXConstants.CREATION_DATE_TIME));
        		  objDeliveryMessage.setCreatedBy((String)objRecord.get(PIXConstants.CREATED_BY));
        		  objDeliveryMessage.setMsgTypeDetail((String)objRecord.get(PIXConstants.MESSAGE_TYPE));
        		  objDeliveryMessage.setName_1((String)objRecord.get(PIXConstants.DELIVERY_DESTINATION));
        		  objDeliveryMessage.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
        		  objDeliveryMessage.setPoId((BigDecimal)objRecord.get(PIXConstants.PO_ID));
        		  objDeliveryMessage.setPoVersion((BigDecimal)objRecord.get(PIXConstants.PO_VERSION));
                 // setting Deliverymessagelist   
        		  objListVector.add(objDeliveryMessage);
        	   }
          	 
           }        
           else if(objVector.size()== 0 || objVector == null)
           {
        	     AppException appException = new AppException();
  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_DELIVERY_MESSAGE,"DeliveryMessageDAOImpl");  
  				 throw appException;
           }
           
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In DeliverlymessageDaoImpl in inboxDeliveryMessageList");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,inboxDeliveryMessageList",e);
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
   public Collection inboxDeliveryMessageMillList(Integer user_id,int currentValue,String objorderby,String objsort) throws AppException
   {
   
	   System.out.println("**********************DeliveryMessageDAOImpl.inboxDeliveryMessageMillList**************");
	   Session objSession = getClientSession();
	   Vector objInboxHistory = new Vector();
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   try
	   {
		   Record objRecord = null;
		   StoredProcedureCall call = new StoredProcedureCall();
		   //PIX_INBOX_MILL_LIST_PROC
		   call.setProcedureName("PIX_INBOX_MILL_LIST_PROC");
		   call.addNamedArgumentValue("i_user_id",user_id);
		   call.addNamedArgumentValue("I_ORDER_BY ",objorderby);
           call.addNamedArgumentValue("I_SORT",objsort);
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           // executing stored procedure     
           if (objVector != null && objVector.size()>0)
           {
          	   for (int i = 0; i < objVector.size(); i++)
        	   {
          		 objRecord = (Record)objVector.get(i);
          		  objDeliveryMessageLine = new DeliveryMessageLine();
          		  objDeliveryMessageLine.setDeliveryMsgNo((String)objRecord.get(PIXConstants.DELIVERY_MESSAGE_NO));
          		  objDeliveryMessageLine.setMsgId((BigDecimal)objRecord.get("MSG_ID"));
          		  objDeliveryMessageLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));           		  
          		  objDeliveryMessageLine.setPrinter((String)objRecord.get(PIXConstants.PRINTER));
          		  objDeliveryMessageLine.setMaterialDesc((String)objRecord.get(PIXConstants.MATERIAL_DESC));	           		
          		  objDeliveryMessageLine.setDeliveryDate((Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE));
          		  objDeliveryMessageLine.setPostedDate((Date)objRecord.get("DM_POSTED_DATE"));
          		  objDeliveryMessageLine.setRequestedQuantity((BigDecimal)objRecord.get(PIXConstants.BUYER_REQUESTED_QTY));
           		  objDeliveryMessageLine.setTotalDeliveredQuantity(new Integer(((BigDecimal)objRecord.get("TOTAL_DELIVERED_QTY")).intValue()));
          		  objDeliveryMessageLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get("DELIVERED_QUANTITY")).intValue()));
          		  objDeliveryMessageLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get("QTY_WITH_TOLERANCE")).longValue()));
          		  objDeliveryMessageLine.setReceivedQuantity(new Integer(((BigDecimal)objRecord.get("RECEIVED_QTY")).intValue()));
          		  objDeliveryMessageLine.setApprovalFlag((String)objRecord.get(PIXConstants.APPROVAL_FLAG));
          		  objDeliveryMessageLine.setPostedBy((String)objRecord.get("DM_POSTED_BY"));
          		  objDeliveryMessageLine.setPoNo((String)objRecord.get("PO_NO"));
          		objDeliveryMessageLine.setProductCode((String)objRecord.get("PRODUCT_CODE"));
          		  objDeliveryMessageLine.setPoVersion((BigDecimal)objRecord.get("PO_VERSION"));
          		  objInboxHistory.add(objDeliveryMessageLine);
        	   }
          	 
           }        
           else if(objVector.size()== 0 || objVector == null)
           {
        	     AppException appException = new AppException();
  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_DELIVERY_MESSAGE,"DeliveryMessageDAOImpl");  
  				 throw appException;
           }
           
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In DeliverlymessageDaoImpl in inboxDeliveryMessageList");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,inboxDeliveryMessageList",e);
		   throw appException;
	   }
	   finally
	   {
		  
		   if(objSession!=null)
		   {
			  objSession.release();
		   }
	   }    
	   return objInboxHistory;
   }
   
   public String getPurchaseOrderStatus(POHeader poHeaderTemp)throws AppException
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.getPurchaseOrderStatus**************");
	   BigDecimal poId=poHeaderTemp.getPoId();
	   BigDecimal poVersion = poHeaderTemp.getPoVersion();
	   //System.out.println("Po id for SQL: "+poId);
	   Session clientSession = getClientSession();
	   
	   Vector stat= clientSession.executeSelectingCall(new SQLCall("select ppls.po_no,psc.status_code from pix_po_list_summary ppls,pix_status_code psc where ppls.status_id=psc.status_id and order_type in ('O','S') and ppls.po_id="+poId+" and ppls.po_version="+poVersion));
	   String status=null;
	   for(int i=0;i<stat.size();i++)
	   {
		   Record objRecord1 = (Record)stat.get(i);
		   	status=(String)objRecord1.get("STATUS_CODE");
		   if(status!=null){
			   break;
		   }
	   }
	return status;  
   }
   
   public String getPmsLineQuantity(DeliveryMessage objDeliveryMessage,String ponumber ,int size)
   throws AppException
{
	   System.out.println("**********************DeliveryMessageDAOImpl.getPmsLineQuantity**************");
   String value = "";
   String quantFlag = null;
   Session clientSession = getClientSession();
   for(int i = 0; i < size; i++)
   {
       //String poNo = objDeliveryMessage.getPoNo();
	   BigDecimal lineNo = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getLineNo();
       Integer quantity = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i).getDeliveredQuantity();
       StoredProcedureCall call = new StoredProcedureCall();
       call.setProcedureName("pms_get_quantity_proc");
       call.addNamedArgumentValue("i_po_no", ponumber);
       call.addNamedArgumentValue("i_quantity", quantity);
       call.addNamedArgumentValue("i_line_no", lineNo);
       call.addNamedOutputArgument("o_return");
       ValueReadQuery query = new ValueReadQuery();
       query.setCall(call);
       query.bindAllParameters();
       query.addArgument("i_po_no");
       query.addArgument("i_quantity");
       query.addArgument("i_line_no");
       Vector params = new Vector();
       params.addElement(ponumber);
       params.addElement(quantity);
       params.addElement(lineNo);
       value = (String)clientSession.executeQuery(query, params);
       String result[] = value.split(",");
       System.out.println(value);
       if(result[1].equals("H"))
       {
    	   quantFlag = value + "," + lineNo;
    	   break;
       }       
       else if(result[1].equals("N") || result[1].equals("Y2"))
       	{
           if(quantFlag == null)
           {
               quantFlag = value + "," + lineNo;
           } else
           {
               value = value + "," + lineNo;
               quantFlag = quantFlag + "#" + value;
           }
       	}
   }

   return quantFlag;
}
   
   public String getPmsPoHeaderStatus(BigDecimal poId)
   throws AppException
   {	  
	   System.out.println("**********************DeliveryMessageDAOImpl.getPmsPoHeaderStatus**************");
	   String PmsHeaderStatus = null;
	   Session clientSession = getClientSession(); 
	   StoredProcedureCall call = new StoredProcedureCall();
       call.setProcedureName("pms_get_po_status_proc");
       call.addNamedArgumentValue("i_po_id", poId);
       call.addNamedOutputArgument("o_return");
       ValueReadQuery query = new ValueReadQuery();
       query.setCall(call);
       query.bindAllParameters();
       query.addArgument("i_po_id");
       Vector params = new Vector();
       params.addElement(poId);
       PmsHeaderStatus = (String)clientSession.executeQuery(query, params);       
       return PmsHeaderStatus;
   }
   
   public String insertMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId) throws AppException 
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.insertMillDeliveryMessage**************");
		   Session clientSession = null;
		   int deliveryMsgLineSize = objDeliveryMessage.getDeliveryMsgLineCollection().size();
		   StringBuffer bill_no = null;
		   StringBuffer frt_no = null;
		   StringBuffer file_url = null;
		   StringBuffer carrier_no = null;
		   String msgNo = null;
		   String result = null;
		   StringBuffer msgBuff = new StringBuffer();
		   try
		   {   
			   clientSession = getClientSession();
			  
			   for (int i = 0; i < deliveryMsgLineSize; i++)
        	   {
				   bill_no = new StringBuffer();
				   frt_no = new StringBuffer();
				   file_url = new StringBuffer();
				   carrier_no = new StringBuffer();
			    DeliveryMessageLine objDeliveryMessageLine = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i);
			    if(objDeliveryMessageLine.getDelMsgFilesCollection()!= null)
			    {
			    	int deliveryMsgFilesSize = objDeliveryMessageLine.getDelMsgFilesCollection().size();
			    	if(!(objDeliveryMessageLine.getDelMsgFilesCollection().isEmpty()))
			    	{
			    		for(int j=0; j<deliveryMsgFilesSize;j++)
			    		{
			    			bill_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getBillOfLaiding()).append("#");
			    			frt_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getFrieghtNo()).append("#");
			    			file_url.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getFileUrl()).append("#");
			    			carrier_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getCarrierNo()).append("#");
			    		}
			    	}
			    int deliveredQtyValue = objDeliveryMessageLine.getDeliveredQuantity().intValue();
			    if(deliveredQtyValue != 0){	
			   	StoredProcedureCall call = new StoredProcedureCall(); 
		   		call.setProcedureName("Pix_Paper_Dm_Insert_Proc");
		   		call.addNamedArgumentValue("I_PO_ID",poHeaderTemp.getPoId());
		   		call.addNamedArgumentValue("I_PO_VERSION",poHeaderTemp.getPoVersion());
		   		call.addNamedArgumentValue("i_PO_LINE_NO",objDeliveryMessageLine.getLineNo());
		   		call.addNamedArgumentValue("i_user_id",new BigDecimal(userId));
		   		call.addNamedArgumentValue("i_delivery_qty",objDeliveryMessageLine.getDeliveredQuantity());
		   		call.addNamedArgumentValue("i_bill_no",bill_no.toString());
		   		call.addNamedArgumentValue("i_freight_no",frt_no.toString());
		   		call.addNamedArgumentValue("i_file_url",file_url.toString());
		   		call.addNamedArgumentValue("i_carrier_no",carrier_no.toString());
		   		call.addNamedArgumentValue("i_total_bol",new BigDecimal(deliveryMsgFilesSize));
		   		call.addNamedOutputArgument("o_msg_no");
		   		ValueReadQuery query = new ValueReadQuery();
		        query.setCall(call);
		        query.bindAllParameters();
		        query.addArgument("I_PO_ID");
		        query.addArgument("I_PO_VERSION");
		        query.addArgument("i_PO_LINE_NO");
		        query.addArgument("i_user_id");
		        query.addArgument("i_delivery_qty");
		        query.addArgument("i_bill_no");
		        query.addArgument("i_freight_no");
		        query.addArgument("i_file_url");
		        query.addArgument("i_carrier_no");
		        query.addArgument("i_total_bol");
		        Vector params = new Vector();
		        params.addElement(poHeaderTemp.getPoId());
		        params.addElement(poHeaderTemp.getPoVersion());
		        params.addElement(objDeliveryMessageLine.getLineNo());
		        params.addElement(new BigDecimal(userId));
		        params.addElement(objDeliveryMessageLine.getDeliveredQuantity());
		        params.addElement(bill_no.toString());
		        params.addElement(frt_no.toString());
		        params.addElement(file_url.toString());
		        params.addElement(carrier_no.toString());
		        params.addElement(new BigDecimal(deliveryMsgFilesSize));
		        result = (String)clientSession.executeQuery(query, params);
		        if(result != null){
			        msgBuff.append(result).append("#").append(objDeliveryMessageLine.getLineNo()).append("@");
			       }
			    }
		        }
		       }//end of for
			   
			   msgNo = msgBuff.toString();
		   }
		   catch(TopLinkException e)
		   {
			   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "DeliveryMessageDAOImpl,insertDeliveryMessage",e);
			   throw appException;
		   }
		   finally
		   {
			  
			   if(clientSession!=null)
			   {
				   clientSession.release();
			   }
		   }  
		   return msgNo;
	   
    }
   
   public Collection getDeliveryMessageHistory(POHeader poHeaderTemp,int pagination,String lineNo,String requestType,int userId) throws AppException{
	   System.out.println("**********************DeliveryMessageDAOImpl.getDeliveryMessageHistory**************");
	   Vector objHistory = new Vector();
	   Session clientSession = null;
	   //Integer paginationParam=new Integer(1);
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   try
	   {   
		    Record objRecord = null;
		    clientSession = getClientSession();
		    StoredProcedureCall call = new StoredProcedureCall(); 
	   		call.setProcedureName("PIX_PAPER_DM_HIST_PROC");
	   		call.addNamedArgumentValue("I_PO_ID",poHeaderTemp.getPoId());  // 163233
	   		call.addNamedArgumentValue("I_PO_VERSION",poHeaderTemp.getPoVersion()); // 1
	   		call.addNamedArgumentValue("i_PO_LINE_NO",new BigDecimal(String.valueOf(lineNo))); // 1
	   		call.addNamedArgumentValue("i_user_id",new BigDecimal(userId));  // 342
	   		call.addNamedArgumentValue("i_request_type",requestType);
	   		//call.addNamedArgumentValue("I_PAGINATION",paginationParam);
	   		call.useNamedCursorOutputAsResultSet("list_refcursor");
	   		Vector objVector = (Vector)clientSession.executeSelectingCall(call);
	   		if (objVector != null && objVector.size()>0)
	           {
	          	   for (int i = 0; i < objVector.size(); i++)
	        	   {
	          		  objRecord = (Record)objVector.get(i);
	           		  objDeliveryMessageLine = new DeliveryMessageLine();
	           		  objDeliveryMessageLine.setDeliveryMsgNo((String)objRecord.get(PIXConstants.DELIVERY_MESSAGE_NO));
	           		  objDeliveryMessageLine.setMsgId((BigDecimal)objRecord.get("MSG_ID"));
	           		  objDeliveryMessageLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));           		  
	           		  objDeliveryMessageLine.setPrinter((String)objRecord.get(PIXConstants.PRINTER));
	           		  String MaterialDesc = (String)objRecord.get(PIXConstants.MATERIAL_DESC);
	           		  objDeliveryMessageLine.setMaterialDesc(MaterialDesc);
	           //	  objDeliveryMessageLine.setMaterialDesc((String)objRecord.get(PIXConstants.MATERIAL_DESC));	           		
	           		  objDeliveryMessageLine.setDeliveryDate((Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE));
	           		  objDeliveryMessageLine.setPostedDate((Date)objRecord.get("DM_POSTED_DATE"));
	           		  objDeliveryMessageLine.setRequestedQuantity((BigDecimal)objRecord.get(PIXConstants.BUYER_REQUESTED_QTY));
 	           		  objDeliveryMessageLine.setTotalDeliveredQuantity(new Integer(((BigDecimal)objRecord.get("TOTAL_DELIVERED_QTY")).intValue()));
	           		  objDeliveryMessageLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get("DELIVERED_QUANTITY")).intValue()));
	           		  objDeliveryMessageLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get("QTY_WITH_TOLERANCE")).longValue()));
	           		  objDeliveryMessageLine.setReceivedQuantity(new Integer(((BigDecimal)objRecord.get("RECEIVED_QTY")).intValue()));
	           		  objDeliveryMessageLine.setApprovalFlag((String)objRecord.get(PIXConstants.APPROVAL_FLAG));
	           		  objDeliveryMessageLine.setPostedBy((String)objRecord.get("DM_POSTED_BY"));
	           		  
	           		  // productCode
		           		String productCodeMatDesc = MaterialDesc ;
		           		int idx = productCodeMatDesc.indexOf(")");
		           		String productCodeSub = productCodeMatDesc.substring(1, idx);
		           		StringBuffer productCodeBuffer = new StringBuffer();
		           		StringBuffer tempBuffer = new StringBuffer();
		           		for(int j=10; j>productCodeSub.length();j--)
	           			  {
	           				  tempBuffer.append("0");
	           			  }
	           			  productCodeBuffer = tempBuffer.append(productCodeSub);
	           			 String productCode = new String(productCodeBuffer);
	           			 System.out.println(productCode);
			           		objDeliveryMessageLine.setProductCode(productCode);
		           		
		           	
	           		  objHistory.add(objDeliveryMessageLine);
	        	   }//END OF FOR 
	          	 }
	   }
	   catch(TopLinkException e)
	   {
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,insertDeliveryMessage",e);
		   throw appException;
	   }
	   finally
	   {
		  
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }  
	   return objHistory;
   }
   public String updateMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId) throws AppException 
   {
	   System.out.println("**********************DeliveryMessageDAOImpl.updateMillDeliveryMessage**************");
		   Session clientSession = null;
		   int deliveryMsgLineSize = objDeliveryMessage.getDeliveryMsgLineCollection().size();
		   StringBuffer bill_no = null;
		   StringBuffer frt_no = null;
		   StringBuffer file_url = null;
		   StringBuffer carrier_no = null;
		   String msgNo = null;
		   int countBOL = 0;
		   StringBuffer msgBuff = new StringBuffer();
		   try
		   {   
			   clientSession = getClientSession();
			   for (int i = 0; i < deliveryMsgLineSize; i++)
        	   {
				bill_no = new StringBuffer();
	 			frt_no = new StringBuffer();
	 			file_url = new StringBuffer();
	 			carrier_no = new StringBuffer();
			    DeliveryMessageLine objDeliveryMessageLine = objDeliveryMessage.getDeliveryMsgLineCollectionIdx(i);
			    if(objDeliveryMessageLine.getDelMsgFilesCollection()!= null){
			    	countBOL = objDeliveryMessageLine.getDelMsgFilesCollection().size();
			    	if(!(objDeliveryMessageLine.getDelMsgFilesCollection().isEmpty()))
			    	{
			    		for(int j=0; j<countBOL;j++)
			    		{
			    			
			    			bill_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getBillOfLaiding()).append("#");
			    			frt_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getFrieghtNo()).append("#");
			    			file_url.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getFileUrl()).append("#");
			    			carrier_no.append(objDeliveryMessageLine.getDelMsgFilesCollectionIdx(j).getCarrierNo()).append("#");
			    		}
			    		msgBuff.append(objDeliveryMessageLine.getDeliveryMsgNo()).append("#").append(objDeliveryMessageLine.getLineNo()).append("@");
			    	}
			    }
			    else {
			    	countBOL = 0;
			    }
			    
			    
			    if(objDeliveryMessageLine.getPostedQuantity()==null){
			    	objDeliveryMessageLine.setPostedQuantity(new Integer(0));
			    }
			   	StoredProcedureCall call = new StoredProcedureCall(); 
		   		call.setProcedureName("PIX_PAPER_DM_UPDATE_PROC");
		   		call.addNamedArgumentValue("I_PO_ID",poHeaderTemp.getPoId());
		   		call.addNamedArgumentValue("I_PO_VERSION",poHeaderTemp.getPoVersion());
		   		call.addNamedArgumentValue("I_PO_LINE_NO",objDeliveryMessageLine.getLineNo());
		   		call.addNamedArgumentValue("I_MSG_NO",objDeliveryMessageLine.getDeliveryMsgNo());
		   		call.addNamedArgumentValue("I_USER_ID",new BigDecimal(userId));
		   		call.addNamedArgumentValue("I_DELIVERY_QTY",objDeliveryMessageLine.getPostedQuantity());
		   		call.addNamedArgumentValue("I_BILL_NO",bill_no.toString());
		   		call.addNamedArgumentValue("I_FREIGHT_NO",frt_no.toString());
		   		call.addNamedArgumentValue("I_FILE_URL",file_url.toString());
		   		call.addNamedArgumentValue("I_CARRIER_NO",carrier_no.toString());
		   		call.addNamedArgumentValue("I_TOTAL_BOL",new BigDecimal(countBOL));
		   		clientSession.executeNonSelectingCall(call);
        	   }
			   msgNo = msgBuff.toString();
			   return msgNo;
		   }
		   catch(TopLinkException e)
		   {
			   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "DeliveryMessageDAOImpl,insertDeliveryMessage",e);
			   throw appException;
		   }
		   finally
		   {
			  
			   if(clientSession!=null)
			   {
				   clientSession.release();
			   }
		   }  
    }

// costuser
   
   public Vector cuOwnedQtyPopup(String msgId, String ownershipMode, String pono) throws AppException
   {
	   Session clientSession = null;
	   DeliveryMessageLine objDeliveryMessageLine = null;
	   Vector cuPOP = new Vector();
	   try
	   {
		   Record objRecord = null;
		   clientSession = getClientSession();
		   Date objEstimatedDeliveryDate = null;
		   String objEstimatedDeliveryDate_String=null;
		   
		   BigDecimal bMsgId = new BigDecimal(msgId);

		   StoredProcedureCall call = new StoredProcedureCall(); 
	   		call.setProcedureName("PIX_COST_ACCNT_HYPER_LINE_PROC");
	   		call.addNamedArgumentValue("I_PO_NO",pono);
	   		call.addNamedArgumentValue("I_KEY_ID",bMsgId);
	   		call.addNamedArgumentValue("I_MODE",ownershipMode);
	   		
	   		call.useNamedCursorOutputAsResultSet("o_sys_cursor");
	   		Vector objVector = (Vector)clientSession.executeSelectingCall(call);
   
		  if(objVector != null && objVector.size()>0)
		  {
			  for (int i = 0;i < objVector.size();i++)
       	      {
				  objRecord = (Record)objVector.get(i);
       		   
       		   objDeliveryMessageLine = new DeliveryMessageLine();
       		   
       		if((String)objRecord.get("PO_NO")!=null)
       		{
   			   objDeliveryMessageLine.setCuPono((String)objRecord.get("PO_NO"));
       		}
       		if((BigDecimal)objRecord.get("PO_LINE_NO")!=null)
       		{
   			   objDeliveryMessageLine.setCuPoLineNo((BigDecimal)objRecord.get("PO_LINE_NO"));
       		}   
       		   
       		
       		   if((String)objRecord.get("PRODUCT_CODE")!=null)
      		   {
              		String productCode = (String) objRecord.get("PRODUCT_CODE");
        			String pc = null;
        			for(int k=0; k<=productCode.length(); k++)
        			{
        				if(productCode.charAt(k)!='0')
        				{
        					pc = productCode.substring(k,productCode.length());
        					break;
        				}
        			}
        			System.out.println(pc);

//      			objDeliveryMessageLine.setCuProductCode((String)objRecord.get("PRODUCT_CODE"));
        			objDeliveryMessageLine.setCuProductCode(pc);
      		   } 
	       		if((String)objRecord.get("PRODUCT_DESCRIPTION")!=null)
	   		   {
	   			   objDeliveryMessageLine.setCuProductDescription((String)objRecord.get("PRODUCT_DESCRIPTION"));
	   		   }
	       		if((String)objRecord.get("SUPPLIER_NAME")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuSupplierName((String)objRecord.get("SUPPLIER_NAME"));
		   		   }
	       		
	       		if((String)objRecord.get("PRINTER")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuPrinter((String)objRecord.get("PRINTER"));
		   		   }
	       		if((String)objRecord.get("ROLL_NO")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuRollNo((String)objRecord.get("ROLL_NO"));
		   		   }
	       		if((BigDecimal)objRecord.get("DELIVERED_QTY")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuDeliveredQty((BigDecimal)objRecord.get("DELIVERED_QTY"));
		   		   }	       		
	       		if((BigDecimal)objRecord.get("RECEIVED_QTY")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuReceivedQty((BigDecimal)objRecord.get("RECEIVED_QTY"));
		   		   }	       		
	       		if((String)objRecord.get("STATUS")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuStatus((String)objRecord.get("STATUS"));
		   		   }
	       		
	       		cuPOP.add(objDeliveryMessageLine);
       	      }

		  }
          
		 
	   }
	 
	   catch(TopLinkException e)
	   {
		   log.debug("In DeliverlymessageDaoImpl in list");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,getDeliveryMessageDetail",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			  
			   clientSession.release();
		   }
	   }    
	   
    return cuPOP;
   }

   public int printerRtDmNrtHist(String msgId, String DMGRMode) throws AppException
   {  
   		   	Vector isRoleTrackingEnabled = null;
   		   	Session objSession =  null;
   		   	Record objRecord = null;
   		   	int record = 0;
   		   	BigDecimal val = null;
   		   	Vector statusVector = new Vector();
   		   	String bmsgId = null;
   		   	try
   		   	{
   		   		objSession = getClientSession();
   		   		
   		   		if(DMGRMode.equals("DM"))
   		   		{
   		   		bmsgId = msgId;
//   		   			isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
//   			   				" Select count(1) as countr from pix_delivery_msg_roll where msg_id = " + "'" + msgId +"'"));
   		   		}
   		   		if(DMGRMode.equals("GR"))
   		   		{
   		   		Vector msgIdVec = (Vector) objSession.executeSelectingCall(new SQLCall(

	   					" select msg_id from pix_good_receipt_line where receipt_id = " + "'" + msgId +"'"));
	   		
	   			int vecSize = msgIdVec == null ? 0 : msgIdVec.size();
			   	//	record = vectorSize;
		    		if (msgIdVec != null && vecSize>0)				//checking for vector size
		    		{
		    			for (int i = 0; i < vecSize; i++)			//iterating to add each record in listVector
		    			{
		    				objRecord = (Record) msgIdVec.get(i);
		    				val = (BigDecimal) objRecord.get("MSG_ID");
		    				bmsgId = val.toString();
		    			}
		    		}
//   		   			isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
//   			   				" Select count(1) as countr from pix_good_receipt_roll where receipt_id = " + "'" + msgId +"'"));
   		   		}
   		   		
		   			isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
   			   				" Select count(1) as countr from pix_delivery_msg_roll where msg_id = " + "'" + bmsgId +"'"));

   		   		
   		   		
   		   		int vectorSize = isRoleTrackingEnabled == null ? 0 : isRoleTrackingEnabled.size();

   		   	//	record = vectorSize;

   	    		if (isRoleTrackingEnabled != null && vectorSize>0)				//checking for vector size
   	    		{
   	    			for (int i = 0; i < vectorSize; i++)			//iterating to add each record in listVector
   	    			{
   	    				objRecord = (Record) isRoleTrackingEnabled.get(i);
   	    				val = (BigDecimal) objRecord.get("COUNTR");
   	    				record = val.intValue();//isRoleTrackingEnabled.size();//(Integer)isRoleTrackingEnabled.get(i);
   	    			}
   	    		}
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
    	   
   	return record;//2;
   }   
   
   
/*   public Vector cuOwnedQtyPopup(String msgId, String ownershipMode, String pono) throws AppException
   {
	   Session clientSession = null;
	   DeliveryMessage objDeliveryMessage = null;
	   Vector cuPOP = new Vector();
	   System.out.println(pono);
	   try
	   {
		   Record objRecord = null;
		   clientSession = getClientSession();
		   objDeliveryMessage = new DeliveryMessage();
		   
  		   Vector objAddress=new Vector();
  		 DeliveryMessageLine objDeliveryMessageLine = null;
  		   if(ownershipMode.equals("TRUE CONSIGNMENT") || ownershipMode.equals("CONSIGNMENT")){
  		    objAddress = (Vector)clientSession.executeSelectingCall(new SQLCall(
  		    	
  		    	" SELECT "+
  		    	" PPLS.PO_NO, "+
  		    	" PPL.PO_LINE_NO, "+
  		    	"  PPL.PRODUCT_CODE,  "+
  		    	"  PPL.PRODUCT_DESCRIPTION, "+
  		    	"  PPLS.SUPPLIER_NAME,  "+
  		    	"  '('||PPLP.org_unit_code||')' || '-' || PPLP.name_1 AS Printer, "+
  		    	"  PDMR.ROLL_NO,  "+
  		    	"  PDMR.QUANTITY DELIVERED_QTY, "+  
  		    	"  NVL(PGRR.QUANTITY,0) RECEIVED_QTY, "+ 
  		    	"  'STATUS'   "+
  		    	"  FROM   "+
  		    	"  PIX_DELIVERY_MSG_ROLL PDMR,PIX_DELIVERY_MSG_LINE PDML,PIX_PO_LINE PPL,PIX_PO_LIST_SUMMARY PPLS, "+  
  		    	"  PIX_PO_LINE_PARTY PPLP,PIX_GOOD_RECEIPT_LINE PGRL,PIX_GOOD_RECEIPT_ROLL PGRR   "+
  		    	"  WHERE PDMR.MSG_ID = PDML.MSG_ID   "+
  		    	"  AND PDMR.MSG_LINE_NO = PDML.MSG_LINE_NO "+   
  		    	"  AND PDML.PO_ID = PPL.PO_ID   "+
  		    	"  AND PDML.PO_LINE_NO = PPL.PO_LINE_NO "+  
  		    	"  AND PDML.PO_ID =PGRL.PO_ID  "+
  		    	"  AND PDML.PO_LINE_NO = PGRL.PO_LINE_NO "+ 
  		    	"  AND PGRL.RECEIPT_ID = " + "'"+msgId+"'" + // -- V_RECEIPT_ID "+
  		    	"  AND PDML.MSG_ID = PGRL.MSG_ID  "+
  		    	"  AND PGRL.RECEIPT_ID = PGRR.RECEIPT_ID "+
  		    	"  AND PGRL.RECEIPT_LINE_NO = PGRR.RECEIPT_LINE_NO "+
  		    	"  AND PDMR.ROLL_NO = PGRR.ROLL_NO  "+
  		    	"  AND PPL.PO_ID = PPLS.PO_ID   "+
  		    	"  AND PPL.PO_VERSION = PPLS.PO_VERSION "+ 
  		    	"  AND PPL.PO_ID = PPLP.PO_ID  "+
  		    	"  AND PPL.PO_LINE_NO = PPLP.PO_LINE_NO "+ 
  		    	"  AND PPL.PO_VERSION = PPLP.PO_VERSION   "+
  		    	"  AND (PPLS.PO_NO LIKE '7%' OR PPLS.PO_NO LIKE '9%')"));
  		    	
  		     		   }
  		   else if(ownershipMode.equals("OWNED ON DELIVERY") || ownershipMode.equals("")){
  			 objAddress = (Vector)clientSession.executeSelectingCall(new SQLCall(
  					" SELECT "+ 
  					" PPLS.PO_NO, "+
  					" PPL.PO_LINE_NO, "+
  					" PPL.PRODUCT_CODE,  "+
  					" PPL.PRODUCT_DESCRIPTION, "+
  					" PPLS.SUPPLIER_NAME, '('||PPLP.org_unit_code||')' || '-' || PPLP.name_1 AS Printer, "+
  					" PDMR.ROLL_NO,  "+
  					" PDMR.QUANTITY DELIVERED_QTY, "+  
  					" NVL(PGRR.QUANTITY,0) RECEIVED_QTY, "+ 
  					" 'STATUS'   "+
  					" FROM   "+
  					" PIX_DELIVERY_MSG_ROLL PDMR,PIX_DELIVERY_MSG_LINE PDML,PIX_PO_LINE PPL,PIX_PO_LIST_SUMMARY PPLS, "+  
  					" PIX_PO_LINE_PARTY PPLP,PIX_GOOD_RECEIPT_LINE PGRL,PIX_GOOD_RECEIPT_ROLL PGRR   "+
  					" WHERE PDMR.MSG_ID = PDML.MSG_ID   "+
  					" AND PDMR.MSG_LINE_NO = PDML.MSG_LINE_NO "+   
  					" AND PDML.PO_ID = PPL.PO_ID   "+
  					" AND PDML.PO_LINE_NO = PPL.PO_LINE_NO "+  
  					" AND PGRL.MSG_ID = " + "'"+msgId+"'" +// --V_MSG_ID "+
  					" AND PDML.PO_ID =PGRL.PO_ID  "+
  					" AND PDML.PO_LINE_NO = PGRL.PO_LINE_NO "+ 
  					" AND PDML.MSG_ID = PGRL.MSG_ID  "+
  					" AND PGRL.RECEIPT_ID = PGRR.RECEIPT_ID"+ 
  					" AND PGRL.RECEIPT_LINE_NO = PGRR.RECEIPT_LINE_NO "+  
  					" AND PDMR.ROLL_NO = PGRR.ROLL_NO  "+
  					" AND PPL.PO_ID = PPLS.PO_ID   "+
  					" AND PPL.PO_VERSION = PPLS.PO_VERSION "+ 
  					" AND PPL.PO_ID = PPLP.PO_ID  "+
  					" AND PPL.PO_LINE_NO = PPLP.PO_LINE_NO "+ 
  					" AND PPL.PO_VERSION = PPLP.PO_VERSION   "+
  					" AND (PPLS.PO_NO LIKE '7%' OR PPLS.PO_NO LIKE '9%')"));

  		   }
  		  
		  if(objAddress != null && objAddress.size()>0)
		  {
			  for (int i = 0;i < objAddress.size();i++)
       	      {
				  objRecord = (Record)objAddress.get(i);
       		   
       		   objDeliveryMessageLine = new DeliveryMessageLine();
       		   
       		if((String)objRecord.get("PO_NO")!=null)
       		{
   			   objDeliveryMessageLine.setCuPono((String)objRecord.get("PO_NO"));
       		}
       		if((BigDecimal)objRecord.get("PO_LINE_NO")!=null)
       		{
   			   objDeliveryMessageLine.setCuPoLineNo((BigDecimal)objRecord.get("PO_LINE_NO"));
       		}   
       		   
       		   if((String)objRecord.get("PRODUCT_CODE")!=null)
      		   {
      			   objDeliveryMessageLine.setCuProductCode((String)objRecord.get("PRODUCT_CODE"));
      		   } 
	       		if((String)objRecord.get("PRODUCT_DESCRIPTION")!=null)
	   		   {
	   			   objDeliveryMessageLine.setCuProductDescription((String)objRecord.get("PRODUCT_DESCRIPTION"));
	   		   }
	       		if((String)objRecord.get("SUPPLIER_NAME")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuSupplierName((String)objRecord.get("SUPPLIER_NAME"));
		   		   }
	       		
	       		if((String)objRecord.get("PRINTER")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuPrinter((String)objRecord.get("PRINTER"));
		   		   }
	       		if((String)objRecord.get("ROLL_NO")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuRollNo((String)objRecord.get("ROLL_NO"));
		   		   }
	       		if((BigDecimal)objRecord.get("DELIVERED_QTY")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuDeliveredQty((BigDecimal)objRecord.get("DELIVERED_QTY"));
		   		   }	       		
	       		if((BigDecimal)objRecord.get("RECEIVED_QTY")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuReceivedQty((BigDecimal)objRecord.get("RECEIVED_QTY"));
		   		   }	       		
	       		if((String)objRecord.get("STATUS")!=null)
		   		   {
		   			   objDeliveryMessageLine.setCuStatus((String)objRecord.get("STATUS"));
		   		   }
	       		
	       		cuPOP.add(objDeliveryMessageLine);
       	      }

		  }
          
		 
	   }
	 
	   catch(TopLinkException e)
	   {
		   log.debug("In DeliverlymessageDaoImpl in list");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "DeliveryMessageDAOImpl,getDeliveryMessageDetail",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			  
			   clientSession.release();
		   }
	   }    
	   
    return cuPOP;
   }
*/
}
