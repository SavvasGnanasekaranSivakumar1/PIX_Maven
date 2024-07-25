package com.pearson.pix.dao.goodsreceipt;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dao.orderstatus.OrderStatusDAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.record.ObjRecord;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.queryframework.ValueReadQuery;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.goodsreceipt.GoodsReceiptLine;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLineParty;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;


/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 */
public class GoodsReceiptDAOImpl extends BaseDAO implements GoodsReceiptDAO 
{
   
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(GoodsReceiptDAOImpl.class.getName());
  
   /**
    * Construtor to initialize GoodsReceiptDAOImpl
    */
   public GoodsReceiptDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Goods Receipt List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Collection
    */
   public Collection getGoodsReceiptList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId)  throws AppException
   {
	   Session objSession = getClientSession();
	   GoodsReceipt objGoodsReceipt = null;
	   Vector objListVector = new Vector();
	   Record objRecord = null;
	   try
	   {
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_goods_rcpt_Proc");
		   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
		   call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
           call.addNamedArgumentValue("i_order_by",objorderby);
           call.addNamedArgumentValue("i_sort",objsort); 
           call.addNamedArgumentValue("i_user_id",userId); 
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           // to execute stored procedure
           if (objVector != null && objVector.size()>0)
           {
          	   for (int i = 0; i < objVector.size(); i++)
        	   {
           		  objRecord = (Record)objVector.get(i);
           		  objGoodsReceipt = new GoodsReceipt();
           		  objGoodsReceipt.setReceiptNo((String)objRecord.get(PIXConstants.RECEIPT_NO));
           		  objGoodsReceipt.setMaterialNo((String)objRecord.get(PIXConstants.MATERIAL_NO));
           		  objGoodsReceipt.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
           		  objGoodsReceipt.setActualarrivalDate((Date)objRecord.get(PIXConstants.ACTUAL_ARRIVAL_DATE));
           		  objGoodsReceipt.setComp_vendor((String)objRecord.get(PIXConstants.COMP_VENDOR));
           		  objGoodsReceipt.setShip_to_vendor((String)objRecord.get(PIXConstants.SHIP_TO_VENDOR));
           		  objGoodsReceipt.setCreationDateTime((Date)objRecord.get(PIXConstants.CREATION_DATE_TIME));
           		  objGoodsReceipt.setCreatedBy((String)objRecord.get(PIXConstants.CREATED_BY));
           		  objGoodsReceipt.setReceiptId((BigDecimal)objRecord.get(PIXConstants.RECEIPT_ID));
           		  objGoodsReceipt.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
                //setting Goods receipt list 
           		  objListVector.add(objGoodsReceipt);
        	   }
          	
           }        
           else if(objVector.size()== 0 || objVector == null)
           {
        	     AppException appException = new AppException();
  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_GOODS_RECEIPT,"GoodsReceiptDAOImpl");  
  				 throw appException;
           }
           
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in list");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getGoodsReceiptList",e);
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
    * Gets the Goods Receipt Detail for a goods receipt number of PO from the database
    * @param poNumber
    * @param receiptNumber
    * @param orderPaper //in case of Paper FO
    * @param roleType //in case mill user is logged in
    * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
    */
   public GoodsReceipt getGoodsReceiptDetail(String poNumber, Integer receiptNumber,String orderPaper,String roleType) throws AppException
   {
	   Session clientSession = null;
	   GoodsReceipt objGoodsReceipt = null;
	   Vector objVendorAddress = null;
	   try
	   {
		   Record objRecord = null;
		   clientSession = getClientSession();
		   Vector objVendorContactCollection = null;
		   Vector objPartyCollection = new Vector();
		   POParty poParty = null;
		   Country objCountry = null;
		   POPartyContact objPOPartyContact = null;
		   System.out.println("here in good receipt");
		   
		   if("O".equals(orderPaper) || "M".equals(roleType))
		   {	   
			   
			   
			   
			   	StoredProcedureCall partyCall = new StoredProcedureCall(); 
	   	    	partyCall.setProcedureName("pix_pms_good_receipt_proc");
	   	    	partyCall.addNamedArgumentValue("i_po_id ",null);
	   	    	partyCall.addNamedArgumentValue("i_po_version",null);
	   	    	partyCall.addNamedArgumentValue("i_input_1",receiptNumber);
	   	    	partyCall.addNamedArgumentValue("i_input_2",null);
	   	    	partyCall.addNamedArgumentValue("i_request_type","GOOD_RECEIPT_DET");
	   	    	partyCall.useNamedCursorOutputAsResultSet("list_refcursor");  
	   	    	objVendorAddress = (Vector)clientSession.executeSelectingCall(partyCall);
	   	    //	System.out.println("objVendorAddress::"+objVendorAddress.size());
			   


		   }
		   else
		   {
			  objVendorAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("select pph.po_no,pph.release_no,pdm.msg_no,pdm.msg_id,pgr.receipt_no," +
   		               " ppp.name_1,ppp.san,ppp.address_1,ppp.city,ppp.postal_code,pr.description,ppp.state,ppp.country_code," +
   		               " pppc.contact_first_name,ppp.party_type,pppc.phone,pppc.mobile,pppc.fax,pppc.email from" +
   		               " pix_po_header pph,pix_good_receipt pgr,pix_po_party ppp,pix_po_party_contact pppc," +
   		               " pix_delivery_msg pdm,pix_ref pr where pph.po_id = pgr.po_id and pph.po_version = pgr.po_version " +
   		               " and ppp.po_id = pgr.po_id and pgr.header_acceptance_id = pr.ref_id and pr.GROUP_CODE='GR_ACCEPTANCE' and ppp.po_version = pgr.po_version and pppc.po_id = pgr.po_id " +
   		               " and pppc.po_version = pgr.po_version and ppp.party_type IN ('V','S') and pppc.party_line_no = ppp.party_line_no" +
   		               " and pgr.msg_id = pdm.msg_id and pgr.receipt_id = "+receiptNumber+""));
		   }
		   
         if(objVendorAddress != null && objVendorAddress.size()>0)
		 {
			  for (int i = 0;i < objVendorAddress.size();i++)
       	      {
				  objRecord = (Record)objVendorAddress.get(i);
				  poParty = new POParty();
				  objGoodsReceipt = new GoodsReceipt();
				  objCountry = new Country();
				  objPOPartyContact = new POPartyContact();
				  objGoodsReceipt.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
     			  objGoodsReceipt.setReleaseNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.RELEASE_NO)).intValue()));
				  objGoodsReceipt.setHeaderAcceptanceDescription((String)objRecord.get(PIXConstants.HEADER_ACCEPTANCE_DESCRIPTION));
				  objGoodsReceipt.setReceiptNo((String)objRecord.get(PIXConstants.RECEIPT_NO));
				  objGoodsReceipt.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
				  objGoodsReceipt.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
				  poParty.setSan((String)objRecord.get(PIXConstants.SAN));
				  poParty.setOrgUnitCode((String)objRecord.get(PIXConstants.SAP_PLANT_CODE));
				  poParty.setName1((String)objRecord.get(PIXConstants.NAME_1));
       		      poParty.setAddress1((String)objRecord.get(PIXConstants.ADDRESS_1));
       		      poParty.setPartyType((String)objRecord.get(PIXConstants.PARTY_TYPE));
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
       		      objVendorContactCollection = new Vector();
       		      objVendorContactCollection.add(objPOPartyContact);
       		      poParty.setContactCollection(objVendorContactCollection);
       		      objPartyCollection.add(poParty);
       		   
       	      }
		  }
          
          objGoodsReceipt.setPartyCollection(objPartyCollection);
          Vector objGoodReceiptLine = getGoodsReceiptLine(receiptNumber);
          objGoodsReceipt.setGoodsReceiptLineCollection(objGoodReceiptLine);
         
		   
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in list");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getGoodsReceiptDetail",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }    
      return objGoodsReceipt;
   }
   
   /**
    * Gets the Basic Goods Receipt information from the database
    * @param poNumber
    * @return java.util.HashMap
    */
   public HashMap getBasicGoodsReceiptInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objgoodsreceipt) throws AppException
   {
	  Session clientSession = null;
	  GoodsReceipt objGoodsReceipt = null;
	  HashMap objHashMap = null;
	  Vector objVendorAddress = null;
	  try
	  {
		  Record objRecord = null;
		  objHashMap = new HashMap();
		  clientSession = getClientSession();
		  objGoodsReceipt = new GoodsReceipt();
		  Vector objVendorContactCollection = null;
		  Vector objPartyCollection = new Vector();
		  POParty poParty = null;
		  Country objCountry = null;
		  POPartyContact objPOPartyContact = null;
		  Vector  objVector = getNewGoodsReceiptLine(poHeaderTemp,objUser,objgoodsreceipt); 
		  GoodsReceiptLine msgId = (GoodsReceiptLine)objVector.get(0);
		  if(msgId.getMsgId() != null )
		  {
			   objVendorAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT pph.po_no,pph.release_no,ppp.name_1,ppp.address_1,ppp.san,ppp.city,ppp.postal_code," +
			  		" ppp.state,ppp.country_code,pppc.contact_first_name,ppp.party_type,pppc.phone,pppc.mobile,pppc.fax," +
			  		" pppc.email,pdm.msg_no,pdm.msg_id FROM PIX_PO_HEADER pph,PIX_PO_PARTY ppp,PIX_PO_PARTY_CONTACT pppc,PIX_DELIVERY_MSG pdm " +
			  		" WHERE pph.po_id ="+poHeaderTemp.getPoId()+"AND pph.po_version = "+poHeaderTemp.getPoVersion()+" AND ppp.po_id = pdm.po_id AND ppp.po_version = pdm.po_version " +
			  		" AND pppc.po_id = pdm.po_id and pppc.po_version = pdm.po_version AND ppp.party_type IN('V','S')  AND" +
			  		" pppc.party_line_no = ppp.party_line_no and pdm.msg_id = "+msgId.getMsgId()+" "));
		 }
		  else
		  {
			   objVendorAddress = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT pph.po_no,pph.release_no,ppp.name_1,ppp.address_1,ppp.san,ppp.city,ppp.postal_code," +
				  		" ppp.state,ppp.country_code,pppc.contact_first_name,ppp.party_type,pppc.phone,pppc.mobile,pppc.fax," +
				  		" pppc.email FROM PIX_PO_HEADER pph,PIX_PO_PARTY ppp,PIX_PO_PARTY_CONTACT pppc" +
				  		" WHERE pph.po_id = "+poHeaderTemp.getPoId()+"  AND pph.po_version = "+poHeaderTemp.getPoVersion()+" AND ppp.po_id = pph.po_id AND ppp.po_version = pph.po_version" +
				  		" AND pppc.po_id = ppp.po_id AND pppc.po_version = ppp.po_version AND ppp.party_type IN('V','S')" +
				  		" AND pppc.party_line_no = ppp.party_line_no"));
		  }
		if(objVendorAddress != null && objVendorAddress.size()>0)
        {
           for (int i = 0;i < objVendorAddress.size();i++)
           {
        	   objRecord = (Record)objVendorAddress.get(i);
        	   poParty = new POParty();
        	   objGoodsReceipt = new GoodsReceipt();
        	   objCountry = new Country();
        	   objPOPartyContact = new POPartyContact();
        	   
        	   objGoodsReceipt.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
        	   objGoodsReceipt.setReleaseNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.RELEASE_NO)).intValue()));
        	   objGoodsReceipt.setReceiptNo((String)objRecord.get(PIXConstants.RECEIPT_NO));
        	   objGoodsReceipt.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
        	   objGoodsReceipt.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
        	   poParty.setName1((String)objRecord.get(PIXConstants.NAME_1));
        	   poParty.setSan((String)objRecord.get(PIXConstants.SAN));
        	   poParty.setAddress1((String)objRecord.get(PIXConstants.ADDRESS_1));
        	   poParty.setPartyType((String)objRecord.get(PIXConstants.PARTY_TYPE));
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
        	   objVendorContactCollection = new Vector();
        	   objVendorContactCollection.add(objPOPartyContact);
        	   poParty.setContactCollection(objVendorContactCollection);
        	   objPartyCollection.add(poParty);
        	   objGoodsReceipt.setPartyCollection(objPartyCollection);
           }
        }
        
        Vector objGoodsCondition = getGoodsConditionTypes();
  		objHashMap.put("GoodsCondition",objGoodsCondition);
  		objGoodsReceipt.setGoodsReceiptLineCollection(objVector);
  		objHashMap.put("HeaderandLineDetails",objGoodsReceipt);
		  
	  }
	  catch(TopLinkException e)
	   {
		    log.debug("In  GoodsReceiptDaoImpl in getBasicGoodsReceiptInfo method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getBasicGoodsReceiptInfo",e);
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
    * Gets the Basic Goods Receipt information from the database
    * @param POHeader poHeaderTemp
    * @param String objUser
    * @param GoodsReceipt objGoodsReceipt
    * @param Integer userId
    * @param String msgId for paper FO
    * @return java.util.HashMap
    */
   public HashMap getBasicGoodsReceiptPaperInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objgoodsreceipt,Integer userId,String msgId,String msgLineNo) throws AppException
   {
	  Session clientSession = null;
	  GoodsReceipt objGoodsReceipt = null;
	  HashMap objHashMap = null;
	  Vector objVendorAddress = null;
	  GoodsReceiptLine objGoodsReceiptLine = null;
	  Date objrequestedDeliveryDate = null;
	  String objrequestedDeliveryDate_String = new String();
	  Vector objgoodsReceiptLineVector = new Vector();

	  //Integer dmExists = null;
	  try
	  {
		  Record objRecord = null;
		  objHashMap = new HashMap();
		  clientSession = getClientSession();
		  objGoodsReceipt = new GoodsReceipt();
		  Vector objVendorContactCollection = null;
		  Vector objPartyCollection = new Vector();
		  POParty poParty = null;
		  Country objCountry = null;
		  POPartyContact objPOPartyContact = null;
		  Vector objGoodReceiptLineItem = null;
		  
		  StoredProcedureCall call = new StoredProcedureCall(); 
   	      call.setProcedureName("pix_pms_get_dm_proc");
   	      call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
   	      call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
   	      call.addNamedArgumentValue("i_user_id",userId);
   	      call.addNamedOutputArgument("dm_exists","dm_exists",Integer.class);
   	      call.setUsesBinding(true);
		  ValueReadQuery query = new ValueReadQuery();
		  query.setCall(call);
	      query.bindAllParameters();
	      query.addArgument("i_po_id");
          query.addArgument("i_po_version");
          query.addArgument("i_user_id");
          Vector params = new Vector();
          params.addElement(poHeaderTemp.getPoId());
          params.addElement(poHeaderTemp.getPoVersion());
          params.addElement(userId);
          Number dmExists = (Number) clientSession.executeQuery(query,params);
   	      //Vector objVector = (Vector)clientSession.executeSelectingCall(call);
          clientSession.executeNonSelectingCall(call);
   	      /*for(int k=0;k<objVector.size();k++)
   	      {
   	    	  System.out.println("vector:"+objVector.get(k));
   	    	  Record data = (Record)objVector.get(k);
		   
   	    	  dmExists = new Integer(((BigDecimal)data.get("DM_EXISTS")).intValue());
   	    	  
   	    	 
   	      }*/
          log.info("dmExists:"+dmExists);
          log.info("msgId:"+msgId);
   	      if(dmExists.toString().equals("0"))
   	      {
   	    	AppException appException=new AppException();
     	    log.info("log info  :no records ");
			appException.performErrorAction(Exceptions.DM_NOT_EXISTS,"GoodsReceiptDAOImpl");  
			throw appException;  
   	      }
   	      else
   	      {
   	    	
   	    	StoredProcedureCall partyCall = new StoredProcedureCall(); 
   	    	partyCall.setProcedureName("Pix_Pms_Good_Receipt_Proc");
   	    	partyCall.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
   	    	partyCall.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
   	    	partyCall.addNamedArgumentValue("i_input_1",Integer.valueOf(msgId));
   	    	partyCall.addNamedArgumentValue("i_input_2",Integer.valueOf(msgLineNo));
   	    	partyCall.addNamedArgumentValue("i_request_type",PIXConstants.SHIPTO);
   	    	partyCall.useNamedCursorOutputAsResultSet("list_refcursor");  
   	    	objVendorAddress = (Vector)clientSession.executeSelectingCall(partyCall);
   	    	if(objVendorAddress != null && objVendorAddress.size()>0)
   	        {
   	           for (int i = 0;i < objVendorAddress.size();i++)
   	           {
   	        	   
	   	          objRecord = (Record)objVendorAddress.get(i);
	         	  poParty = new POParty();
	         	  objGoodsReceipt = new GoodsReceipt();
	         	  objCountry = new Country();
	         	  objPOPartyContact = new POPartyContact();
	         	  
	         	  objGoodsReceipt.setPoNo((String)objRecord.get(PIXConstants.PO_NO));
	        	  objGoodsReceipt.setReleaseNo(new Integer(((BigDecimal)objRecord.get(PIXConstants.RELEASE_NO)).intValue()));  
	         	  poParty.setName1((String)objRecord.get(PIXConstants.NAME_1));
	        	  poParty.setSan((String)objRecord.get(PIXConstants.SAN));
	        	  poParty.setOrgUnitCode((String)objRecord.get(PIXConstants.SAP_PLANT_CODE));
	        	  poParty.setAddress1((String)objRecord.get(PIXConstants.ADDRESS_1));
	        	  poParty.setPartyType((String)objRecord.get(PIXConstants.PARTY_TYPE));
	        	  poParty.setCity((String)objRecord.get(PIXConstants.CITY));
	        	  poParty.setPostalCode((String)objRecord.get(PIXConstants.POSTAL_CODE));
	        	  poParty.setState((String)objRecord.get(PIXConstants.STATE));
	        	  objCountry.setCountryCode((String)objRecord.get(PIXConstants.COUNTRY_CODE));
	        	  poParty.setCountryDetail(objCountry);
	        	  if((String)objRecord.get(PIXConstants.CONTACT_FIRST_NAME)!=null)
	        	  {	  
	        		  objPOPartyContact.setContactFirstName((String)objRecord.get(PIXConstants.CONTACT_FIRST_NAME));
	        	  }
	        	  objPOPartyContact.setPhone((String)objRecord.get(PIXConstants.PHONE));
	        	  objPOPartyContact.setMobile((String)objRecord.get(PIXConstants.MOBILE));
	        	  objPOPartyContact.setFax((String)objRecord.get(PIXConstants.FAX));
	        	  objPOPartyContact.setEmail((String)objRecord.get(PIXConstants.EMAIL));
	        	  objVendorContactCollection = new Vector();
	        	  objVendorContactCollection.add(objPOPartyContact);
	        	  poParty.setContactCollection(objVendorContactCollection);
	        	  objPartyCollection.add(poParty);
	        	  objGoodsReceipt.setPartyCollection(objPartyCollection);
   	           }
   	        }
   	    	  
   	    	//System.out.println("party col size in DAO:"+objGoodsReceipt.getPartyCollection().size());
   	    	//get the line items
   	    	StoredProcedureCall receiptCall = new StoredProcedureCall(); 
   	    	receiptCall.setProcedureName("Pix_Pms_Good_Receipt_Proc");
   	    	receiptCall.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
   	    	receiptCall.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
   	    	receiptCall.addNamedArgumentValue("i_input_1",Integer.valueOf(msgId));
   	    	receiptCall.addNamedArgumentValue("i_input_2",Integer.valueOf(msgLineNo));
   	    	receiptCall.addNamedArgumentValue("i_request_type",PIXConstants.LINE_DETAIL);
   	    	receiptCall.useNamedCursorOutputAsResultSet("list_refcursor");
    		objGoodReceiptLineItem = (Vector)clientSession.executeSelectingCall(receiptCall);
    		if (objGoodReceiptLineItem != null && objGoodReceiptLineItem.size()>0)
	        {
    			for (int i = 0;i < objGoodReceiptLineItem.size();i++)
	        	{
    			   objRecord = (Record)objGoodReceiptLineItem.get(i);
	        	   objGoodsReceiptLine = new GoodsReceiptLine();
	        	   
	        	   objGoodsReceiptLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
	        		if((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)!=null)
	        		{
        			   objGoodsReceiptLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)).intValue()));
	        		}
	        		objGoodsReceiptLine.setUom_id((BigDecimal)objRecord.get(PIXConstants.UOM_ID));
	        		objGoodsReceiptLine.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
	        		if((objrequestedDeliveryDate = (Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
                    {
        			   objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
        			   objGoodsReceiptLine.setRequestedDeliveryDate(objrequestedDeliveryDate_String);
              	    }
	        		
	        		if(objRecord.get(PIXConstants.MATERIAL_NO)!=null)
	        		{
	        			objGoodsReceiptLine.setProductCode((String)objRecord.get(PIXConstants.MATERIAL_NO));
	        		}
	        		if(objRecord.get(PIXConstants.RECEIVED_QUANTITY)!=null)
	        		{
	        			objGoodsReceiptLine.setCumulativeQuanReceived(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QUANTITY)).intValue()));
	        		}
	        		if((BigDecimal)objRecord.get(PIXConstants.MAX_GR_QTY)!=null)
        		    {
	        			objGoodsReceiptLine.setMaxToleranceVal(new Long(((BigDecimal)objRecord.get(PIXConstants.MAX_GR_QTY)).longValue()));
        		    }
	        	   objGoodsReceiptLine.setMsgLineNo((BigDecimal)objRecord.get(PIXConstants.MSG_LINE_NO));
	        	   
	        	   objGoodsReceiptLine.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
	        	   
	        	   objGoodsReceiptLine.setMsgLine(Integer.valueOf(msgLineNo));
	        	   
	        	   objgoodsReceiptLineVector.add(objGoodsReceiptLine);
	        	}
    			
        	}	
	   	    else if(objGoodReceiptLineItem.size()== 0 || objGoodReceiptLineItem == null)
            {
        	     AppException appException = new AppException();
  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_GOODS_RECEIPT,"GoodsReceiptDAOImpl");  
  				 throw appException;
            }
   	      }
   	      
		  
        Vector objGoodsCondition = getGoodsConditionTypes();
  		objHashMap.put("GoodsCondition",objGoodsCondition);
  		objGoodsReceipt.setGoodsReceiptLineCollection(objgoodsReceiptLineVector);
  		objHashMap.put("HeaderandLineDetails",objGoodsReceipt);
		  
	  }
	  catch(TopLinkException e)
	  {
		    log.debug("In  GoodsReceiptDaoImpl in getBasicGoodsReceiptInfo method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getBasicGoodsReceiptInfo",e);
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
	   //return null;
   }
   
   /**
    * Saves the Goods Receipt information into the database
    * @param objGoodsReceipt
    */
   public String insertGoodsReceipt(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser) throws AppException
   {
	   Session clientSession = null;
	   GoodsReceipt objGoodsReceiptForMsgId = null;
	   GoodsReceipt objGoodsReceiptMsgId = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   PreparedStatement deliverystmtheader = null;
	   PreparedStatement deliverystmtLine = null;
	   PreparedStatement goodsstmtheader = null;
	   PreparedStatement goodsstmtLine = null;
	   try
	   {
		   transaction = getUserTransaction();
		   Vector lineCollection = (Vector)objGoodsReceipt.getGoodsReceiptLineCollection();
		   Record objRecord = null;
		   BigDecimal msg_status_id = null;
		   BigDecimal statusId = null;
		   String receiptNumber = null;
		   clientSession = getClientSession();
		   conn = getDataSourceConnection();
		   String commnets = "SYSTEM GENERATED DELIVERY MESSAGE";
		   // to get the status for goods receipt 
		   Vector status_id = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_GOOD_RECEIPT'),pix_get_status_id_func('REPLACED','PIX_GOOD_RECEIPT')) AS STATUS_ID " +
                                     " FROM PIX_GOOD_RECEIPT WHERE PO_ID = "+poHeaderTemp.getPoId()+" AND PO_VERSION = "+poHeaderTemp.getPoVersion()+""));
           if(status_id != null)
           {
			   for (int i = 0; i < status_id.size(); i++)
		       {
				  objRecord = (Record)status_id.get(i);
				  statusId = (BigDecimal)objRecord.get("STATUS_ID");
			   }
           }
           // to get status for delivery message
           Vector message_status_id = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_DELIVERY_MSG'),pix_get_status_id_func('REPLACED','PIX_DELIVERY_MSG'))AS STATUS_ID "+
                   " FROM PIX_DELIVERY_MSG WHERE PO_ID= "+poHeaderTemp.getPoId()+" AND PO_VERSION= "+poHeaderTemp.getPoVersion()+""));
           if(message_status_id != null)
           {
	          for (int i = 0; i < message_status_id.size(); i++)
              {
	        	  objRecord = (Record)message_status_id.get(i);
	        	  msg_status_id = (BigDecimal)objRecord.get("STATUS_ID");
              }
           }
           
           
		   BigDecimal messageType = getDeliveryMessageType();
		   Vector messageId = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT NVL(MAX(pdm.msg_id),0) AS MSG_ID FROM PIX_DELIVERY_MSG pdm " +
		              " WHERE  pdm.po_id= "+poHeaderTemp.getPoId()+" AND   pdm.po_version="+poHeaderTemp.getPoVersion()+""));
		
			if (messageId != null && messageId.size()>0)
			 {
			       for (int i = 0; i < messageId.size(); i++)
			     {
			    	   objRecord = (Record)messageId.get(i);
			    	   objGoodsReceiptForMsgId = new GoodsReceipt();
			    	   objGoodsReceiptForMsgId.setMsgId((BigDecimal)objRecord.get("MSG_ID"));
			     }
			 }	   
			 // if messageid is 0 then it enters in to block it executes both delivery message header, Line and goods receipt header and Line.
			 if(objGoodsReceiptForMsgId.getMsgId().intValue() == 0)
			 {
				 deliverystmtheader = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG(MSG_ID,MSG_NO,PO_ID,PO_VERSION,MSG_TYPE_ID,CREATED_BY,MODIFIED_BY,CREATION_DATE_TIME,MOD_DATE_TIME,COMMENTS,STATUS_ID)" +
				   	  " VALUES (seq_PIX_DELIVERY_MSG_id.nextval,('DM_'||?||'_'||?||'_'|| seq_PIX_DELIVERY_MSG_id.CURRVAL),?," +
			        " ?,?,?,?,SYSDATE,SYSDATE,?,?)");
				  
				 deliverystmtheader.setObject(1, poHeaderTemp.getPoNo());
				 deliverystmtheader.setObject(2, poHeaderTemp.getReleaseNo());
				 deliverystmtheader.setObject(3, poHeaderTemp.getPoId());
				 deliverystmtheader.setObject(4, poHeaderTemp.getPoVersion());
				 deliverystmtheader.setObject(5, messageType);
				 deliverystmtheader.setObject(6, objUser);
				 deliverystmtheader.setObject(7, objUser);
				 deliverystmtheader.setObject(8, commnets);
				 deliverystmtheader.setObject(9, msg_status_id);
				 deliverystmtheader.executeQuery();
				
			 for(int i=0; i<size; i++)
			 {
				
				 deliverystmtLine = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY,UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO) " + 
			       " SELECT seq_PIX_DELIVERY_MSG_id.CURRVAL,PO_LINE_NO,?,UOM_ID,REQUESTED_DELIVERY_DATE,NULL AS BALANCE_QUANTITY, " +
			       " PO_ID,PO_VERSION,PO_LINE_NO FROM PIX_PO_LINE WHERE po_id= ? AND  po_version= ? AND PO_LINE_NO = ?");
				
				 deliverystmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
				 deliverystmtLine.setObject(2, poHeaderTemp.getPoId());
				 deliverystmtLine.setObject(3, poHeaderTemp.getPoVersion());
				 deliverystmtLine.setObject(4, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				 deliverystmtLine.executeQuery();
				 
			 }	
			        
			       Vector msgId = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT seq_PIX_DELIVERY_MSG_id.CURRVAL AS MESSAGE_ID FROM dual"));
			  	  
		            if (msgId != null && msgId.size()>0)
		             {
		    	           for (int i = 0; i < msgId.size(); i++)
		  	           {
		    	        	   objRecord = (Record)msgId.get(i);
		    	        	   objGoodsReceiptMsgId = new GoodsReceipt();
		    	        	   objGoodsReceiptMsgId.setMsgId((BigDecimal)objRecord.get("MESSAGE_ID"));
		  	           }
		             }	       
			        
		          goodsstmtheader = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT(RECEIPT_NO,CREATION_DATE_TIME," +
				   			" CREATED_BY,MOD_DATE_TIME,MODIFIED_BY,PO_ID,PO_VERSION,STATUS_ID,MSG_ID,RECEIPT_ID,ACCEPTANCE_ID,TRANSACTION_HISTORY_NO," +
				   			" HEADER_ACCEPTANCE_ID) VALUES(('GR_'||?||'_'||?||'_'||seq_PIX_GOOD_RCPT_ID.currval)," +
				   			" SYSDATE,?,SYSDATE,?,?,?,?,?," +
				        	" seq_PIX_GOOD_RCPT_ID.nextval,null,null,?)");
		 
		         
		          goodsstmtheader.setObject(1, objGoodsReceipt.getPoNo());
		          goodsstmtheader.setObject(2, objGoodsReceipt.getReleaseNo());
		          goodsstmtheader.setObject(3, objUser);
		          goodsstmtheader.setObject(4, objUser);
		          goodsstmtheader.setObject(5, poHeaderTemp.getPoId());
		          goodsstmtheader.setObject(6, poHeaderTemp.getPoVersion());
		          goodsstmtheader.setObject(7, statusId);
		          goodsstmtheader.setObject(8, objGoodsReceiptMsgId.getMsgId());
		          goodsstmtheader.setObject(9, objGoodsReceipt.getHeaderAcceptanceDescription());
		          goodsstmtheader.executeQuery();
			
		         
		         
			   for(int i=0; i<size; i++)
			   {
				   goodsstmtLine = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT_LINE (ACTUAL_ARRIVAL_DATE,INTRANSIT_DAMAGED_QTY," +
							" CREATION_DATE_TIME,CREATED_BY, MOD_DATE_TIME,MODIFIED_BY,PO_LINE_NO,RECEIPT_ID,RECEIPT_LINE_NO, " +
							" ACCEPTANCE_ID,RECEIVED_QUANTITY,BALANCE_QUANTITY,REQUESTED_ARRIVAL_DATE,PO_ID,PO_VERSION, " +
							" MSG_ID,MSG_LINE_NO,UOM_ID) VALUES (to_date(?,'MM/DD/YYYY')," +
							" ?,sysdate,?,sysdate,?," +
							" ?,seq_PIX_GOOD_RCPT_ID.currval," +
							" ?,?,?,0," +
							" to_date(?,'MM/DD/YYYY')," +
							" ?,?,?," +
							" ?,?)");
				 
				   goodsstmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getActualArrivalDate());
				   goodsstmtLine.setObject(2, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getIntransitDamagedQty());
				   goodsstmtLine.setObject(3, objUser);
				   goodsstmtLine.setObject(4, objUser);
				   goodsstmtLine.setObject(5, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				   goodsstmtLine.setObject(6, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				   goodsstmtLine.setObject(7, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getAcceptanceDescription());
				   goodsstmtLine.setObject(8, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
				   goodsstmtLine.setObject(9, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getRequestedDeliveryDate());
				   goodsstmtLine.setObject(10, poHeaderTemp.getPoId());
				   goodsstmtLine.setObject(11, poHeaderTemp.getPoVersion());
				   goodsstmtLine.setObject(12, objGoodsReceiptMsgId.getMsgId());
				   goodsstmtLine.setObject(13, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLineNo());
				   goodsstmtLine.setObject(14, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getUom_id());
				   goodsstmtLine.executeQuery();
				 
				 
		   		}
			 }
			 // if message not 0 then it execute goods receipt Header and Line
			 else
			 {	 
				 // goods receipt Header Prepared Statement
				 goodsstmtheader = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT(RECEIPT_NO,CREATION_DATE_TIME," +
				   			" CREATED_BY,MOD_DATE_TIME,MODIFIED_BY,PO_ID,PO_VERSION,STATUS_ID,MSG_ID,RECEIPT_ID,ACCEPTANCE_ID,TRANSACTION_HISTORY_NO," +
				   			" HEADER_ACCEPTANCE_ID) VALUES(('GR_'||?||'_'||?||'_'||seq_PIX_GOOD_RCPT_ID.currval)," +
				   			" SYSDATE,?,SYSDATE,?,?,?,?,?," +
				        	" seq_PIX_GOOD_RCPT_ID.nextval,null,null,?)");
		 
		         
				 goodsstmtheader.setObject(1, objGoodsReceipt.getPoNo());
				 goodsstmtheader.setObject(2, objGoodsReceipt.getReleaseNo());
				 goodsstmtheader.setObject(3, objUser);
				 goodsstmtheader.setObject(4, objUser);
				 goodsstmtheader.setObject(5, poHeaderTemp.getPoId());
				 goodsstmtheader.setObject(6, poHeaderTemp.getPoVersion());
				 goodsstmtheader.setObject(7, statusId);
				 goodsstmtheader.setObject(8, objGoodsReceipt.getMsgId());
				 goodsstmtheader.setObject(9, objGoodsReceipt.getHeaderAcceptanceDescription());
				 goodsstmtheader.executeQuery();
				   
				   // goods receipt Line Prepared Statement
				   for(int i=0; i<size; i++)
				   {
					   goodsstmtLine = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT_LINE (ACTUAL_ARRIVAL_DATE,INTRANSIT_DAMAGED_QTY," +
								" CREATION_DATE_TIME,CREATED_BY, MOD_DATE_TIME,MODIFIED_BY,PO_LINE_NO,RECEIPT_ID,RECEIPT_LINE_NO, " +
								" ACCEPTANCE_ID,RECEIVED_QUANTITY,BALANCE_QUANTITY,REQUESTED_ARRIVAL_DATE,PO_ID,PO_VERSION, " +
								" MSG_ID,MSG_LINE_NO,UOM_ID) VALUES (to_date(?,'MM/DD/YYYY')," +
								" ?,sysdate,?,sysdate,?," +
								" ?,seq_PIX_GOOD_RCPT_ID.currval," +
								" ?,?,?,0," +
								" to_date(?,'MM/DD/YYYY')," +
								" ?,?,?," +
								" ?,?)");
					   
					   
					   goodsstmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getActualArrivalDate());
					   goodsstmtLine.setObject(2, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getIntransitDamagedQty());
					   goodsstmtLine.setObject(3, objUser);
					   goodsstmtLine.setObject(4, objUser);
					   goodsstmtLine.setObject(5, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
					   goodsstmtLine.setObject(6, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
					   goodsstmtLine.setObject(7, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getAcceptanceDescription());
					   goodsstmtLine.setObject(8, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
					   goodsstmtLine.setObject(9, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getRequestedDeliveryDate());
					   goodsstmtLine.setObject(10, poHeaderTemp.getPoId());
					   goodsstmtLine.setObject(11, poHeaderTemp.getPoVersion());
					   goodsstmtLine.setObject(12, objGoodsReceipt.getMsgId());
					   goodsstmtLine.setObject(13, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLineNo());
					   goodsstmtLine.setObject(14, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getUom_id());
					   goodsstmtLine.executeQuery();
			 
			 }	 
		 }
			 // to get the Receipt Number
		   Vector ReceiptNumber = (Vector)clientSession.executeSelectingCall(new SQLCall("Select ('GR_'||'"+objGoodsReceipt.getPoNo()+"'||'_'||"+objGoodsReceipt.getReleaseNo()+"||'_'||seq_PIX_GOOD_RCPT_ID.currval) AS GOODS_RECEIPT_NO from dual"));
			  if (ReceiptNumber != null && ReceiptNumber.size()>0)
	          {
	         	   for (int i = 0; i < ReceiptNumber.size(); i++)
	       	       {
	          		  objRecord = (Record)ReceiptNumber.get(i);
	          		  objGoodsReceipt = new GoodsReceipt();
	          		  objGoodsReceipt.setReceiptNo((String)objRecord.get("GOODS_RECEIPT_NO"));
	       	       }
	          }	   
			 receiptNumber = objGoodsReceipt.getReceiptNo();
			  
			return receiptNumber;

	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in insertmethod");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,insertGoodsReceipt",e);
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
		   se.printStackTrace();
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"GoodsReceiptDAOImpl,insertGoodsReceipt",se);
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
			   if(deliverystmtheader!=null)
				   deliverystmtheader.close();
			   if(deliverystmtLine!=null)
				   deliverystmtLine.close();
			   if(goodsstmtheader!=null)
				   goodsstmtheader.close();
			   if(goodsstmtLine!=null)
				   goodsstmtLine.close();
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
    * Saves the Goods Receipt information into the database for paper FO
    * @param objGoodsReceipt
    */
   public String insertGoodsReceiptPaperFo(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser,String msgId) throws AppException
   {
	   Session objSession = null;
	   String result = null;
	   try
	   {
		   
		   Collection goodsreceiptCollection=null;
		   GoodsReceiptLine objGoodsReceiptLine = null;
		   //System.out.println("grobj from params"+objGoodsReceipt.toString());
	   
	   
		   goodsreceiptCollection=objGoodsReceipt.getGoodsReceiptLineCollection();
	   
		   String poline=null;
		   String recvd_qty=null;
		   String msg_id=null;
		   
	   
		   //log.info("in insertGoodsReceiptPaperFo");
		   for(int i=0;i<goodsreceiptCollection.size();i++)
		   {
			  
				objGoodsReceiptLine=objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i);
				if((objGoodsReceiptLine.getColorFlag()).intValue()!=2)
				{
				 if(((objGoodsReceiptLine.getReceivedQuantity()).intValue()>0) && (objGoodsReceiptLine.getPostedQuantity()).intValue()>=((objGoodsReceiptLine.getReceivedQuantity()).intValue()))
				   {								   
					  if(poline==null)
					   {			   
						   poline=(objGoodsReceiptLine.getPoLineNo()).toString();			  
					   }
					   else
					   {
						   poline=poline+','+(objGoodsReceiptLine.getPoLineNo()).toString();
					   }
					   if(recvd_qty==null)
					   {
						  recvd_qty=(objGoodsReceiptLine.getReceivedQuantity()).toString(); 
					   }
					   else
					   {
						  recvd_qty=recvd_qty+','+ (objGoodsReceiptLine.getReceivedQuantity()).toString();
					   }
					   if(msg_id==null)
					   {
						   msg_id=objGoodsReceiptLine.getMsgId().toString();				   
					   }
					   else
					   {
						   msg_id=msg_id+','+objGoodsReceiptLine.getMsgId().toString();
					   }
				   }
				}
		   }	     
	   
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_Paper_Gr_Insert_Proc");
		   call.addNamedArgumentValue("I_PO_ID",poHeaderTemp.getPoId());
		   call.addNamedArgumentValue("I_PO_VERSION",poHeaderTemp.getPoVersion());
		   call.addNamedArgumentValue("I_PO_LINE_NO",poline);		  
		   call.addNamedArgumentValue("I_USER_ID",new Integer(Integer.parseInt(objUser)));
		   call.addNamedArgumentValue("I_MSG_ID",msg_id);
		   call.addNamedArgumentValue("I_RECEIVED_QTY",recvd_qty);
		   call.addNamedOutputArgument("O_RECEIPT_NO");
	   		ValueReadQuery query = new ValueReadQuery();
	        query.setCall(call);
	        query.bindAllParameters();
	        query.addArgument("I_PO_ID");
	        query.addArgument("I_PO_VERSION");
	        query.addArgument("i_PO_LINE_NO");
	        query.addArgument("I_USER_ID");
	        query.addArgument("I_MSG_ID");
	        query.addArgument("I_RECEIVED_QTY");	        
	        Vector params = new Vector();
	        params.addElement(poHeaderTemp.getPoId());
	        params.addElement(poHeaderTemp.getPoVersion());
	        params.addElement(poline);
	        params.addElement(new Integer(Integer.parseInt(objUser)));
	        params.addElement(msg_id);
	        params.addElement(recvd_qty);	
	        
	        //log.info("params "+params);
	        
	        result = (String)objSession.executeQuery(query, params);
		    
	       /* if(result != null){
	        msgBuff.append(result).append("#");
	        }
	        bill_no = null;
	        frt_no=null;
	        file_url=null;
	        carrier_no=null;
	     //end of for
		   msgNo = msgBuff.toString();
		   */
		  //System.out.println("result"+result);
        
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  New Paper Insert GoodsReceiptDaoImpl");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,insertGoodsReceiptPaperFo",e);
		   throw appException;
	   }
	   finally
	   {
		   if(objSession!=null)
		   {
			  objSession.release();
		   }
	   }    
	   return result;
	   
	   /*RFS5922 Session clientSession = null;
	   GoodsReceipt objGoodsReceiptForMsgId = null;
	   GoodsReceipt objGoodsReceiptMsgId = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   PreparedStatement deliverystmtheader = null;
	   PreparedStatement deliverystmtLine = null;
	   PreparedStatement goodsstmtheader = null;
	   PreparedStatement goodsstmtLine = null;
	   try
	   {
		   transaction = getUserTransaction();
		   Vector lineCollection = (Vector)objGoodsReceipt.getGoodsReceiptLineCollection();
		   Record objRecord = null;
		   BigDecimal msg_status_id = null;
		   BigDecimal statusId = null;
		   String receiptNumber = null;
		   clientSession = getClientSession();
		   conn = getDataSourceConnection();
		   String commnets = "SYSTEM GENERATED DELIVERY MESSAGE";
		   
		   Vector status_id = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_GOOD_RECEIPT'),pix_get_status_id_func('REPLACED','PIX_GOOD_RECEIPT')) AS STATUS_ID " +
                                     " FROM PIX_GOOD_RECEIPT WHERE PO_ID = "+poHeaderTemp.getPoId()+" AND PO_VERSION = "+poHeaderTemp.getPoVersion()+""));
           if(status_id != null)
           {
			   for (int i = 0; i < status_id.size(); i++)
		       {
				  objRecord = (Record)status_id.get(i);
				  statusId = (BigDecimal)objRecord.get("STATUS_ID");
			   }
           }
           
         //  System.out.println("pono:"+poHeaderTemp.getPoNo());
         //  System.out.println("release no:"+poHeaderTemp.getReleaseNo());
           /*Vector message_status_id = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT DECODE(COUNT(1),0,pix_get_status_id_func('ORIGINAL','PIX_DELIVERY_MSG'),pix_get_status_id_func('REPLACED','PIX_DELIVERY_MSG'))AS STATUS_ID "+
                   " FROM PIX_DELIVERY_MSG WHERE PO_ID= "+poHeaderTemp.getPoId()+" AND PO_VERSION= "+poHeaderTemp.getPoVersion()+""));
           if(message_status_id != null)
           {
	          for (int i = 0; i < message_status_id.size(); i++)
              {
	        	  objRecord = (Record)message_status_id.get(i);
	        	  msg_status_id = (BigDecimal)objRecord.get("STATUS_ID");
              }
           }
           
           
		   BigDecimal messageType = getDeliveryMessageType();
		   Vector messageId = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT NVL(MAX(pdm.msg_id),0) AS MSG_ID FROM PIX_DELIVERY_MSG pdm " +
		              " WHERE  pdm.po_id= "+poHeaderTemp.getPoId()+" AND   pdm.po_version="+poHeaderTemp.getPoVersion()+""));
		
			if (messageId != null && messageId.size()>0)
			 {
			       for (int i = 0; i < messageId.size(); i++)
			     {
			    	   objRecord = (Record)messageId.get(i);
			    	   objGoodsReceiptForMsgId = new GoodsReceipt();
			    	   objGoodsReceiptForMsgId.setMsgId((BigDecimal)objRecord.get("MSG_ID"));
			     }
			 }	   
			 // if messageid is 0 then it enters in to block it executes both delivery message header, Line and goods receipt header and Line.
			 if(objGoodsReceiptForMsgId.getMsgId().intValue() == 0)
			 {
				 deliverystmtheader = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG(MSG_ID,MSG_NO,PO_ID,PO_VERSION,MSG_TYPE_ID,CREATED_BY,MODIFIED_BY,CREATION_DATE_TIME,MOD_DATE_TIME,COMMENTS,STATUS_ID)" +
				   	  " VALUES (seq_PIX_DELIVERY_MSG_id.nextval,('DM_'||?||'_'||?||'_'|| seq_PIX_DELIVERY_MSG_id.CURRVAL),?," +
			        " ?,?,?,?,SYSDATE,SYSDATE,?,?)");
				  
				 deliverystmtheader.setObject(1, poHeaderTemp.getPoNo());
				 deliverystmtheader.setObject(2, poHeaderTemp.getReleaseNo());
				 deliverystmtheader.setObject(3, poHeaderTemp.getPoId());
				 deliverystmtheader.setObject(4, poHeaderTemp.getPoVersion());
				 deliverystmtheader.setObject(5, messageType);
				 deliverystmtheader.setObject(6, objUser);
				 deliverystmtheader.setObject(7, objUser);
				 deliverystmtheader.setObject(8, commnets);
				 deliverystmtheader.setObject(9, msg_status_id);
				 deliverystmtheader.executeQuery();
				
			 for(int i=0; i<size; i++)
			 {
				
				 deliverystmtLine = conn.prepareStatement("INSERT INTO PIX_DELIVERY_MSG_LINE(MSG_ID,MSG_LINE_NO,DELIVERED_QUANTITY,UOM_ID,DELIVERY_DATE,BALANCE_QUANTITY,PO_ID,PO_VERSION,PO_LINE_NO) " + 
			       " SELECT seq_PIX_DELIVERY_MSG_id.CURRVAL,PO_LINE_NO,?,UOM_ID,REQUESTED_DELIVERY_DATE,NULL AS BALANCE_QUANTITY, " +
			       " PO_ID,PO_VERSION,PO_LINE_NO FROM PIX_PO_LINE WHERE po_id= ? AND  po_version= ? AND PO_LINE_NO = ?");
				
				 deliverystmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
				 deliverystmtLine.setObject(2, poHeaderTemp.getPoId());
				 deliverystmtLine.setObject(3, poHeaderTemp.getPoVersion());
				 deliverystmtLine.setObject(4, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				 deliverystmtLine.executeQuery();
				 
			 }	
			        
			       Vector msgId = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT seq_PIX_DELIVERY_MSG_id.CURRVAL AS MESSAGE_ID FROM dual"));
			  	  
		            if (msgId != null && msgId.size()>0)
		             {
		    	           for (int i = 0; i < msgId.size(); i++)
		  	           {
		    	        	   objRecord = (Record)msgId.get(i);
		    	        	   objGoodsReceiptMsgId = new GoodsReceipt();
		    	        	   objGoodsReceiptMsgId.setMsgId((BigDecimal)objRecord.get("MESSAGE_ID"));
		  	           }
		             }	       
			        
		          goodsstmtheader = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT(RECEIPT_NO,CREATION_DATE_TIME," +
				   			" CREATED_BY,MOD_DATE_TIME,MODIFIED_BY,PO_ID,PO_VERSION,STATUS_ID,MSG_ID,RECEIPT_ID,ACCEPTANCE_ID,TRANSACTION_HISTORY_NO," +
				   			" HEADER_ACCEPTANCE_ID) VALUES(('GR_'||?||'_'||?||'_'||seq_PIX_GOOD_RCPT_ID.currval)," +
				   			" SYSDATE,?,SYSDATE,?,?,?,?,?," +
				        	" seq_PIX_GOOD_RCPT_ID.nextval,null,null,?)");
		 
		         
		          goodsstmtheader.setObject(1, objGoodsReceipt.getPoNo());
		          goodsstmtheader.setObject(2, objGoodsReceipt.getReleaseNo());
		          goodsstmtheader.setObject(3, objUser);
		          goodsstmtheader.setObject(4, objUser);
		          goodsstmtheader.setObject(5, poHeaderTemp.getPoId());
		          goodsstmtheader.setObject(6, poHeaderTemp.getPoVersion());
		          goodsstmtheader.setObject(7, statusId);
		          goodsstmtheader.setObject(8, objGoodsReceiptMsgId.getMsgId());
		          goodsstmtheader.setObject(9, objGoodsReceipt.getHeaderAcceptanceDescription());
		          goodsstmtheader.executeQuery();
			
		         
		         
			   for(int i=0; i<size; i++)
			   {
				   goodsstmtLine = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT_LINE (ACTUAL_ARRIVAL_DATE,INTRANSIT_DAMAGED_QTY," +
							" CREATION_DATE_TIME,CREATED_BY, MOD_DATE_TIME,MODIFIED_BY,PO_LINE_NO,RECEIPT_ID,RECEIPT_LINE_NO, " +
							" ACCEPTANCE_ID,RECEIVED_QUANTITY,BALANCE_QUANTITY,REQUESTED_ARRIVAL_DATE,PO_ID,PO_VERSION, " +
							" MSG_ID,MSG_LINE_NO,UOM_ID) VALUES (to_date(?,'MM/DD/YYYY')," +
							" ?,sysdate,?,sysdate,?," +
							" ?,seq_PIX_GOOD_RCPT_ID.currval," +
							" ?,?,?,0," +
							" to_date(?,'MM/DD/YYYY')," +
							" ?,?,?," +
							" ?,?)");
				 
				   goodsstmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getActualArrivalDate());
				   goodsstmtLine.setObject(2, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getIntransitDamagedQty());
				   goodsstmtLine.setObject(3, objUser);
				   goodsstmtLine.setObject(4, objUser);
				   goodsstmtLine.setObject(5, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				   goodsstmtLine.setObject(6, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
				   goodsstmtLine.setObject(7, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getAcceptanceDescription());
				   goodsstmtLine.setObject(8, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
				   goodsstmtLine.setObject(9, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getRequestedDeliveryDate());
				   goodsstmtLine.setObject(10, poHeaderTemp.getPoId());
				   goodsstmtLine.setObject(11, poHeaderTemp.getPoVersion());
				   goodsstmtLine.setObject(12, objGoodsReceiptMsgId.getMsgId());
				   goodsstmtLine.setObject(13, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLineNo());
				   goodsstmtLine.setObject(14, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getUom_id());
				   goodsstmtLine.executeQuery();
				 
				 
		   		}
			 }*/
			 // if message not 0 then it execute goods receipt Header and Line
			 //else
			 //{	 
				 // goods receipt Header Prepared Statement
			/*RFS5922	 goodsstmtheader = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT(RECEIPT_NO,CREATION_DATE_TIME," +
				   			" CREATED_BY,MOD_DATE_TIME,MODIFIED_BY,PO_ID,PO_VERSION,STATUS_ID,MSG_ID,RECEIPT_ID,ACCEPTANCE_ID,TRANSACTION_HISTORY_NO," +
				   			" HEADER_ACCEPTANCE_ID) VALUES(('GR_'||?||'_'||?||'_'||seq_PIX_GOOD_RCPT_ID.currval)," +
				   			" SYSDATE,?,SYSDATE,?,?,?,?,?," +
				        	" seq_PIX_GOOD_RCPT_ID.nextval,null,null,?)");
		 
		         
				 goodsstmtheader.setObject(1, poHeaderTemp.getPoNo());
				 goodsstmtheader.setObject(2, poHeaderTemp.getReleaseNo());
				 goodsstmtheader.setObject(3, objUser);
				 goodsstmtheader.setObject(4, objUser);
				 goodsstmtheader.setObject(5, poHeaderTemp.getPoId());
				 goodsstmtheader.setObject(6, poHeaderTemp.getPoVersion());
				 goodsstmtheader.setObject(7, statusId);
				 goodsstmtheader.setObject(8, Integer.valueOf(msgId));
				 goodsstmtheader.setObject(9, objGoodsReceipt.getHeaderAcceptanceDescription());
				 goodsstmtheader.executeQuery();
				   
				   // goods receipt Line Prepared Statement
				   for(int i=0; i<size; i++)
				   {
					   goodsstmtLine = conn.prepareStatement("INSERT INTO PIX_GOOD_RECEIPT_LINE (ACTUAL_ARRIVAL_DATE,INTRANSIT_DAMAGED_QTY," +
								" CREATION_DATE_TIME,CREATED_BY, MOD_DATE_TIME,MODIFIED_BY,PO_LINE_NO,RECEIPT_ID,RECEIPT_LINE_NO, " +
								" ACCEPTANCE_ID,RECEIVED_QUANTITY,BALANCE_QUANTITY,REQUESTED_ARRIVAL_DATE,PO_ID,PO_VERSION, " +
								" MSG_ID,MSG_LINE_NO,UOM_ID) VALUES (to_date(?,'MM/DD/YYYY')," +
								" ?,sysdate,?,sysdate,?," +
								" ?,seq_PIX_GOOD_RCPT_ID.currval," +
								" ?,?,?,0," +
								" to_date(?,'MM/DD/YYYY')," +
								" ?,?,?," +
								" ?,?)");
					 
					 //  System.out.println("msgId:"+Integer.valueOf(msgId));
					 //  System.out.println("line no:"+objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLineNo());
					 //  System.out.println("GOOD RECEIPT LINE:"+objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLine());
					   goodsstmtLine.setObject(1, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getActualArrivalDate());
					   goodsstmtLine.setObject(2, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getIntransitDamagedQty());
					   goodsstmtLine.setObject(3, objUser);
					   goodsstmtLine.setObject(4, objUser);
					   goodsstmtLine.setObject(5, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
					   goodsstmtLine.setObject(6, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getLineNo());
					   goodsstmtLine.setObject(7, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getAcceptanceDescription());
					   goodsstmtLine.setObject(8, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getReceivedQuantity());
					   goodsstmtLine.setObject(9, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getRequestedDeliveryDate());
					   goodsstmtLine.setObject(10, poHeaderTemp.getPoId());
					   goodsstmtLine.setObject(11, poHeaderTemp.getPoVersion());
					   goodsstmtLine.setObject(12, Integer.valueOf(msgId));
					   goodsstmtLine.setObject(13, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getMsgLine());
					   goodsstmtLine.setObject(14, objGoodsReceipt.getGoodsReceiptLineCollectionIdx(i).getUom_id());
					   goodsstmtLine.executeQuery();
			 
			 }	 
		// }
			 // to get the Receipt Number
		   Vector ReceiptNumber = (Vector)clientSession.executeSelectingCall(new SQLCall("Select ('GR_'||'"+poHeaderTemp.getPoNo()+"'||'_'||"+poHeaderTemp.getReleaseNo()+"||'_'||seq_PIX_GOOD_RCPT_ID.currval) AS GOODS_RECEIPT_NO from dual"));
			  if (ReceiptNumber != null && ReceiptNumber.size()>0)
	          {
	         	   for (int i = 0; i < ReceiptNumber.size(); i++)
	       	       {
	          		  objRecord = (Record)ReceiptNumber.get(i);
	          		  objGoodsReceipt = new GoodsReceipt();
	          		  objGoodsReceipt.setReceiptNo((String)objRecord.get("GOODS_RECEIPT_NO"));
	       	       }
	          }	   
			 receiptNumber = objGoodsReceipt.getReceiptNo();
			  
			return receiptNumber;

	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in insertmethod");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,insertGoodsReceipt",e);
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
		   se.printStackTrace();
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"GoodsReceiptDAOImpl,insertGoodsReceipt",se);
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
			   if(deliverystmtheader!=null)
				   deliverystmtheader.close();
			   if(deliverystmtLine!=null)
				   deliverystmtLine.close();
			   if(goodsstmtheader!=null)
				   goodsstmtheader.close();
			   if(goodsstmtLine!=null)
				   goodsstmtLine.close();
			   if(conn!=null)
				   conn.close();
 		   } 
 		   catch(Throwable tu) 
 		   { 
 			   throw new RuntimeException(tu); 
 		   }
       }
	   RFS 5922*/
   
   }
   
   
   
   
   
   
   /**
    * Gets the delivery message type id information from the database
    * @return java.math.BigDecimalp
    */
   private BigDecimal getDeliveryMessageType() throws AppException
   {
	   Session clientSession = null;
	   GoodsReceipt objGoodsReceipt = null;
	   BigDecimal messageType=null;
	  // Vector objDeliveryCondition = new Vector();
	   try
	   {
		   clientSession = getClientSession();
		   Record objRecord = null;
          // to get message type id   
		   Vector  messageTypeId = clientSession.executeSelectingCall(new SQLCall("SELECT /* +FIRST_ROWS*/pr.ref_id  FROM PIX_REF pr, PIX_TABLE pt WHERE pr.table_id = pt.table_id " +
				   " AND pt.table_name = 'PIX_DELIVERY_MSG' AND pr.group_code ='MESSAGE_TYPE' AND pr.active_flag='Y' " +
				   " AND pr.ref_code='DMSG'"));
		   if (messageTypeId != null && messageTypeId.size()>0)
	       {
	      	   for (int i = 0;i < messageTypeId.size();i++)
	      	   {
	      		   objRecord = (Record)messageTypeId.get(i);
	      		   objGoodsReceipt = new GoodsReceipt();
	      		   objGoodsReceipt.setMsg_type_id((BigDecimal)objRecord.get(PIXConstants.REF_ID));
	      	   }
	       }
		   if(objGoodsReceipt!=null)
		   {
		      messageType = objGoodsReceipt.getMsg_type_id();
		   }
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in getDeliveryMessageType method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getDeliveryMessageType",e);
		   throw appException;
	   }
	   return messageType;
	   
   }
   /**
    * Gets the Basic Goods Receipt information from the database
    * 
    * @param poNumber
    * @return java.util.Vector
    */
   
   private Vector getGoodsConditionTypes() throws AppException
   {
	   Session clientSession = null;
	   GoodsReceipt objGoodsReceipt = null;
	   Vector objGoodsCondition = new Vector();
	   try
	   {
		   clientSession = getClientSession();
		   Record objRecord = null;
		   Vector  goodscondition = clientSession.executeSelectingCall(new SQLCall("SELECT /* +FIRST_ROWS*/ REF_ID,REF_CODE," +
		   		                    " DESCRIPTION FROM PIX_REF WHERE GROUP_CODE='GR_ACCEPTANCE' AND ACTIVE_FLAG='Y' " +
		   		                    " ORDER BY DISPLAY_ORDER"));
		   if (goodscondition != null && goodscondition.size()>0)
	       {
	      	   for (int i = 0;i < goodscondition.size();i++)
	      	   {
	      		   objRecord = (Record)goodscondition.get(i);
	      		   objGoodsReceipt = new GoodsReceipt();
	      		   objGoodsReceipt.setMsg_type_id((BigDecimal)objRecord.get(PIXConstants.REF_ID));
	      		   objGoodsReceipt.setMsg_type((String)objRecord.get(PIXConstants.DESCRIPTION));
	      		   objGoodsCondition.add(objGoodsReceipt);
	      	   }
	       }
	   }   
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in getGoodsConditionTypes method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getGoodsConditionTypes",e);
		   throw appException;
	   }
	   return objGoodsCondition;
   }
   
 
   /**
    * Gets the Basic Goods Receipt Line information from the database
    * 
    * @param receiptNumber
    * @return java.util.Vector
    */
   
   private Vector getGoodsReceiptLine(Integer receiptNumber) throws AppException
   {
	   Session clientSession = null;
	   GoodsReceiptLine objGoodsReceiptLine = null;
	   Vector objgoodsReceiptLineVector = new Vector();
	   try
	   {
		   clientSession = getClientSession();
		   Record objRecord = null;
		   Date objActualArrivalDate = null;
		   String objActualArrivalDate_String = new String();
		   Date objrequestedDeliveryDate = null;
		   String objrequestedDeliveryDate_String = new String();
		   
		  


		   
		   
		  
		  //  Vector objGoodReceiptLineItem = (Vector)clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ ppl.po_line_no as line_no,ppl.product_code," +
		   		/*" pgrl.receipt_line_no,pgrl.msg_line_no,ppl.product_description,pr.description,pgrl.uom_id,pgrl.received_quantity,ppl.requested_delivery_date,pgrl.actual_arrival_date," +
		   		" pgrl.intransit_damaged_qty,ppl.requested_quantity as Original_qty,nvl((select sum(received_quantity) " +
		   		" from pix_good_receipt_line pgrl where pgrl.po_id = ppl.po_id and pgrl.po_version = ppl.po_version" +
		   		" and pgrl.po_line_no = ppl.po_line_no),0) received_quantity" +
		   		" from pix_good_receipt_line pgrl, pix_po_line ppl,pix_ref pr  where pgrl.po_id = ppl.po_id" +
		   		" and pgrl.po_version = ppl.po_version and pgrl.acceptance_id = pr.ref_id and pr.GROUP_CODE='GR_ACCEPTANCE'and " +
		   		" pgrl.po_line_no = ppl.po_line_no and pgrl.receipt_id = "+receiptNumber+""));*/
		   
		   
		   
		   Vector objGoodReceiptLineItem = (Vector)clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ ppl.po_line_no as line_no,ppl.product_code," +
			   		" pgrl.receipt_line_no,pgrl.msg_line_no,ppl.product_description,pr.description,pgrl.uom_id,pgrl.received_quantity,ppl.requested_delivery_date,pgrl.actual_arrival_date," +
			   		"pgrl.intransit_damaged_qty, ppl.requested_quantity as Original_qty, nvl((select sum(received_quantity) " +
			   		" from pix_good_receipt_line pgrl where pgrl.po_id = ppl.po_id and pgrl.po_version = ppl.po_version" +
			   		" and pgrl.po_line_no = ppl.po_line_no),0) received_quantity" +
			   		" from pix_good_receipt_line pgrl,pix_po_line ppl,pix_delivery_msg_line pdml,pix_ref pr where pgrl.po_id = ppl.po_id" +
			   		" and pdml.po_version = ppl.po_version  and pgrl.msg_id=pdml.msg_id and pgrl.msg_line_no=pdml.msg_line_no and pgrl.acceptance_id = pr.ref_id and pr.GROUP_CODE='GR_ACCEPTANCE'and " +
			   		" pgrl.po_line_no = ppl.po_line_no and pgrl.receipt_id = "+receiptNumber+""));
		   
		   if (objGoodReceiptLineItem != null && objGoodReceiptLineItem.size()>0)
           {
        	   for (int i = 0;i < objGoodReceiptLineItem.size();i++)
        	   {
        		   objRecord = (Record)objGoodReceiptLineItem.get(i);
        		   objGoodsReceiptLine = new GoodsReceiptLine();
        		   objGoodsReceiptLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
        		   objGoodsReceiptLine.setPoLineNo((BigDecimal)objRecord.get(PIXConstants.MSG_LINE_NO));
        		   if(objRecord.get(PIXConstants.PRODUCT_CODE)!=null)
        		   {
        			   objGoodsReceiptLine.setProductCode((String)objRecord.get(PIXConstants.PRODUCT_CODE));
        		   }
        		 //objGoodsReceiptLine.setReceiptLineNo((BigDecimal)objRecord.get(PIXConstants.RECEIPT_LINE_NO));
        		 //objGoodsReceiptLine.setUOM((String)objRecord.get(PIXConstants.UOM_ID).toString());
        		   objGoodsReceiptLine.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
        		 //userId = new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.USER_ID)).intValue());
        		   objGoodsReceiptLine.setReceivedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QUANTITY)).intValue()));
        		   if((objrequestedDeliveryDate = (Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
                   {
        			   objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
        			   objGoodsReceiptLine.setRequestedDeliveryDate(objrequestedDeliveryDate_String);
              	   } 
        	     // objGoodsReceiptLine.setRequestedDeliveryDate((Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE));
        		  if((objActualArrivalDate=(Date)objRecord.get(PIXConstants.ACTUAL_ARRIVAL_DATE))!= null)
                   {
        			      objActualArrivalDate_String= PIXUtil.sqlToStandardDate(objActualArrivalDate.toString());
        			      
        			     objGoodsReceiptLine.setActualArrivalDate(objActualArrivalDate_String);
              	   } 
        		  if(objRecord.get(PIXConstants.INTRANSIT_DAMAGED_QTY) != null)
        		  {	  
           		   	  objGoodsReceiptLine.setIntransitDamagedQty(new Integer(((BigDecimal)objRecord.get(PIXConstants.INTRANSIT_DAMAGED_QTY)).intValue()));
        		  }
        		  if((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)!=null)
         		   {
        			  objGoodsReceiptLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)).intValue()));
         		   }
        		   if(objRecord.get(PIXConstants.RECEIVED_QUANTITY)!=null)
	        		{
	        			objGoodsReceiptLine.setCumulativeQuanReceived(new Integer(((BigDecimal)objRecord.get(PIXConstants.RECEIVED_QUANTITY)).intValue()));
	        		}
        		  
        		   objGoodsReceiptLine.setAcceptanceDescription((String)objRecord.get(PIXConstants.ACCEPTANCE_DESCRIPTION));
        		   objgoodsReceiptLineVector.add(objGoodsReceiptLine);
        		 }
        	}	
		   else if(objGoodReceiptLineItem.size()== 0 || objGoodReceiptLineItem == null)
		   {
			  
	       	   AppException appException=new AppException();
	       	   appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"GoodsReceiptDAOImpl");  
	 		   throw appException;
	       } 
		   	
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in getGoodsReceiptLine method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getGoodsReceiptLine",e);
		   throw appException;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }    
	  return objgoodsReceiptLineVector; 
      
   }
     


/**
 * Gets the Basic Goods Receipt Line information from the database
 * 
 * @param poHeaderTemp
 * @param objUser
 * @param objgoodsreceipt
 * @return java.util.Vector
*/ 

private Vector getNewGoodsReceiptLine(POHeader poHeaderTemp,String objUser,GoodsReceipt objgoodsreceipt) throws AppException
{
	   Session clientSession = null;
	   GoodsReceiptLine objGoodsReceiptLine = null;
	   Vector objgoodsReceiptLineVector = new Vector();
	   Date objrequestedDeliveryDate = null;
	   String objrequestedDeliveryDate_String = new String();
	   GoodsReceipt objGoodsReceiptForMsgId = null;
	   GoodsReceipt objGoodsReceiptMsgId = null;
	   Vector objGoodReceiptLineItem = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;   
	   
	    
	   try
	   {
		  
		   clientSession = getClientSession();
		   Record objRecord = null;
		   String commnets = "SYSTEM GENERATED DELIVERY MESSAGE";
		    
		   // BigDecimal messageType = getDeliveryMessageType();
		    
		   // to get the message Id
		    
		   Vector messageId = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT NVL(MAX(pdm.msg_id),0) AS MSG_ID FROM PIX_DELIVERY_MSG pdm " +
				              " WHERE  pdm.po_id= "+poHeaderTemp.getPoId()+" AND   pdm.po_version="+poHeaderTemp.getPoVersion()+""));
		   // to check if messge id is null of greater than 0		
           if (messageId != null && messageId.size()>0)
           {
   	           for (int i = 0; i < messageId.size(); i++)
 	           {
   	        	   objRecord = (Record)messageId.get(i);
   	        	   objGoodsReceiptForMsgId = new GoodsReceipt();
   	        	   objGoodsReceiptForMsgId.setMsgId((BigDecimal)objRecord.get("MSG_ID"));
 	           }
            }	   
           transaction = getUserTransaction();
		   conn = getDataSourceConnection(); 
		   Statement stmt = conn.createStatement();
		   
		   
		  
		   
		   // to check message id 
		   if(objGoodsReceiptForMsgId.getMsgId().intValue() == 0)
           {
    	        objGoodReceiptLineItem = (Vector)clientSession.executeSelectingCall(new SQLCall("SELECT /* +FIRST_ROWS*/ ppl.po_line_no AS " +
    	        " line_no,ppl.requested_quantity as Original_qty,ppl.uom_id,ppl.product_description, ppl.requested_delivery_date FROM pix_po_line ppl" +
    	        " WHERE  ppl.po_id = "+poHeaderTemp.getPoId()+" AND ppl.po_version = "+poHeaderTemp.getPoVersion()+" ORDER BY ppl.po_line_no" ));
           } 
           else
           {
       	 
		            objGoodReceiptLineItem = (Vector)clientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ ppl.po_line_no as line_no," +
			   		" ppl.requested_quantity as Original_qty,ppl.uom_id,ppl.product_description,ppl.requested_delivery_date,msg_line_no,msg_id from pix_po_line ppl,PIX_delivery_msg_line pdml" +
			   		" where ppl.po_id=pdml.po_id and ppl.po_version=pdml.po_version and ppl.po_line_no=pdml.po_line_no and ppl.po_id = "+poHeaderTemp.getPoId()+"" +
			   		" and ppl.po_version = "+poHeaderTemp.getPoVersion()+" and pdml.msg_id= "+objGoodsReceiptForMsgId.getMsgId()+"" +
			   		" Order by ppl.po_line_no"));
             } 
		   	   if (objGoodReceiptLineItem != null && objGoodReceiptLineItem.size()>0)
	           {
	        	   for (int i = 0;i < objGoodReceiptLineItem.size();i++)
	        	   {
	        		   objRecord = (Record)objGoodReceiptLineItem.get(i);
	        		   objGoodsReceiptLine = new GoodsReceiptLine();
	        		   objGoodsReceiptLine.setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
	        		   if((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)!=null)
	        		   {
	        			   objGoodsReceiptLine.setBalanceQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.ORIGINAL_QTY)).intValue()));
	        		   }
	        		   objGoodsReceiptLine.setUom_id((BigDecimal)objRecord.get(PIXConstants.UOM_ID));
	        		   objGoodsReceiptLine.setMsgLineNo((BigDecimal)objRecord.get(PIXConstants.MSG_LINE_NO));
	        		   objGoodsReceiptLine.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
	        		   objGoodsReceiptLine.setProductDescription((String)objRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
	        		   if((objrequestedDeliveryDate = (Date)objRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
	                   {
	        			   objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
	        			   objGoodsReceiptLine.setRequestedDeliveryDate(objrequestedDeliveryDate_String);
	              	   } 
	           		   objgoodsReceiptLineVector.add(objGoodsReceiptLine);
	        	   }
	        	}	
		   	  else if(objGoodReceiptLineItem.size()== 0 || objGoodReceiptLineItem == null)
	           {
	        	     AppException appException = new AppException();
	  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_GOODS_RECEIPT,"GoodsReceiptDAOImpl");  
	  				 throw appException;
	           }
			 }
	   catch(TopLinkException e)
	   {
		   log.debug("In  GoodsReceiptDaoImpl in getNewGoodsReceiptLine method");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getNewGoodsReceiptLine",e);
		   throw appException;
	   }
	   
	   catch(SQLException se)
  	   {
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"GoodsReceiptDAOImpl,getNewGoodsReceiptLine",se);
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
  			   conn.close();
  			   if(clientSession!=null)
  			   {
  				 clientSession.release();
  			   }
  		   } 
  		   catch(Throwable tu) 
  		   { 
  			   throw new RuntimeException(tu); 
  		   }
        }
	  return objgoodsReceiptLineVector; 
   }


/**
 * Gets the Goods Receipt for new paper PO(RFS 5592)
 * @param poId
 * @param poVersion
 * @param pagination
 * @param orderBy
 * @param sort
 * @author anshu.bhardwaj
 * @return java.util.Collection
 */
public Collection getNewPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId, String ponumber)  throws AppException
{
	   Session objSession = getClientSession();
	   
	   GoodsReceiptLine objGoodsReceiptLine = null;
	   Vector objgoodsReceiptLineVector = new Vector();
	  
	   Record objRecord = null;
	   Date objActualArrivalDate = null;
	   String objActualArrivalDate_String = new String();	 
	   String s = ponumber;
	   	   
	   try
	   {
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_Pms_New_Gr_Proc");
		   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
		   call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());
		  // call.addNamedArgumentValue("i_request_type","NEW");
		   call.addNamedArgumentValue("i_user_id",userId);
	       //call.addNamedArgumentValue("i_pagination",new Integer(pagination)); 
	        
	        call.useNamedCursorOutputAsResultSet("list_refcursor");
	        Vector objVector = (Vector)objSession.executeSelectingCall(call);
	        //log.info("objVector"+objVector);
	        // to execute stored procedure
	        if (objVector != null && objVector.size()>0)
	        {
	       	   for (int i = 0; i < objVector.size(); i++)
	     	   {
        		  objRecord = (Record)objVector.get(i);
        		  objGoodsReceiptLine = new GoodsReceiptLine();
        		  BigDecimal poLineNumber = (BigDecimal)objRecord.get(PIXConstants.PO_LINE_NO);
        		  objGoodsReceiptLine.setPoLineNo((BigDecimal)objRecord.get(PIXConstants.PO_LINE_NO));           		
        		  objGoodsReceiptLine.setMillName((String)objRecord.get(PIXConstants.MILL_NAME));
        		  objGoodsReceiptLine.setPrinter((String)objRecord.get(PIXConstants.PRINTER));
        		  objGoodsReceiptLine.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
        		//  objGoodsReceiptLine.setMaterialDesc((String)objRecord.get(PIXConstants.L4_MATERIAL_DESC));
        		  
        		
	           		String productCodeMatDesc = (String)objRecord.get("L4_MATERIAL_DESC");  // (216) 40# ABIBOOK 18.5 ", (1961) 40# ABIBOOK 75 18.5 "
	           		objGoodsReceiptLine.setMaterialDesc(productCodeMatDesc);
	           	// productCode
	           		String tokens[] = productCodeMatDesc.split(" ");      		
	           		String subProductCode = tokens[0];
	           		
	           		StringBuffer subProductCodeBuffer = new StringBuffer();
	           		
	           		for(int j=1; j<subProductCode.length()-1; j++)
	           		{
	           			subProductCodeBuffer.append(subProductCode.charAt(j));
	           		}
	           		
	           		StringBuffer productCodeBuffer = new StringBuffer();
	           		StringBuffer tempBuffer = new StringBuffer();
	           			  for(int j=10; j>subProductCodeBuffer.length();j--)
	           			  {
	           				  tempBuffer.append("0");
	           			  }
	           			  productCodeBuffer = tempBuffer.append(subProductCodeBuffer);
	               		  String productCode = new String(productCodeBuffer);
	               		  System.out.println("Hello");
	               		  System.out.println(productCode);
	               		  
	           		objGoodsReceiptLine.setProductCode(productCode);
	           	 // end productCode
	           		//  start roletracking  poLineNumber ponumber
	           			String poLineNumStr = poLineNumber.toString(); // line number = 1
	           			System.out.println("ponumber"+ponumber); // ponumber 7000000392
	           			System.out.println("ponumber:"+ ponumber);
	           			int roleTracking = getRoleTracking(ponumber,poLineNumStr);
	           			System.out.println("roleTracking:"+roleTracking);
	           			String roleTrackingFlag = Integer.toString(roleTracking);
	           			objGoodsReceiptLine.setRoleTracking(roleTrackingFlag);
	           		//  end roletracking getRoleTracking
        		  objGoodsReceiptLine.setMaterialTooltip((String)objRecord.get(PIXConstants.L4_MATERIAL_TOOLTIP));
        		  if((objActualArrivalDate = (Date)objRecord.get(PIXConstants.ACTUAL_ARRIVAL_DATE))!= null)
                  {
        			  objActualArrivalDate_String = PIXUtil.sqlToStandardDate(objActualArrivalDate.toString());
	      			   objGoodsReceiptLine.setActualArrivalDate(objActualArrivalDate_String);
            	  }
        		  objGoodsReceiptLine.setRequestedQuantity((BigDecimal)objRecord.get(PIXConstants.BUYER_REQUESTED_QTY));
        		  objGoodsReceiptLine.setDeliveredQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DM_QTY)).intValue()));
        		  objGoodsReceiptLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.GR_TO_BE_POSTED)).intValue()));        		  
        		  if(objRecord.get(PIXConstants.CUMULATIVE_RECEIVED_QUANTITY)!=null)
        			  objGoodsReceiptLine.setCumulativeQuanReceived(new Integer(((BigDecimal)objRecord.get(PIXConstants.CUMULATIVE_RECEIVED_QUANTITY)).intValue()));
        		  objGoodsReceiptLine.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
        		  objGoodsReceiptLine.setColorFlag(new Integer(((BigDecimal)objRecord.get(PIXConstants.COLOUR_FLAG)).intValue()));
        		  
             //setting Goods receipt list 
        		  objgoodsReceiptLineVector.add(objGoodsReceiptLine);        		  
     	   }
	       		
        } 
	        
        else if(objVector.size()== 0 || objVector == null)
        {
     	     AppException appException = new AppException();
				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_GOODS_RECEIPT,"GoodsReceiptDAOImpl");  
				 throw appException;
        }
        
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  New Paper GoodsReceiptDaoImpl");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,getNewPaperGoodsReceipt",e);
		   throw appException;
	   }
	   finally
	   {
		   if(objSession!=null)
		   {
			  objSession.release();
		   }
	   }    
	  return objgoodsReceiptLineVector;
}



public String retrieveSanForRoleTrack(String poNum, String lineNumber)
{
	   
	   Vector sanVector = null;
	   	Session objSession =  null;
	   	Record objRecord = null;
	   	String sanRecord = null;
	   	BigDecimal val = null;
	   	Vector statusVector = new Vector();
	   	String ss = poNum;
	   	try
	   	{
	   		objSession = getClientSession();
	   		sanVector = (Vector) objSession.executeSelectingCall(new SQLCall(
	   				" select a.san san from pix_po_list_summary b, pix_po_line_party a where a.po_id = b.po_id "+
	   				" and  b.po_no = " + "'" + poNum +"' and a.po_line_no = " + "'" + lineNumber +"'"));

	   				
	   				//		  "select supplier_san san from pix_po_list_summary " +
	   		//	      " where po_no = " + "'" + login_Id +"'"));
	   			
	   				
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



public int getRoleTracking(String poNum, String lineNumber) throws AppException
	{  
//			    LinkedHashMap objLinkedHashMap =null;
			   	Vector isRoleTrackingEnabled = null; //millAssociatedUser = null;
//			   	Vector millAssociatedNewUser = null;
			   	Session objSession =  null;
			   	Record objRecord = null;
			   	int record = 0;
			   	BigDecimal val = null;
			   	Vector statusVector = new Vector();
			   	
			   	
			   	
			   	try
			   	{
			   		String sanRecord = retrieveSanForRoleTrack(poNum, lineNumber);
			   		objSession = getClientSession();
	//		   		objLinkedHashMap = new LinkedHashMap();
	//		   		millAssociatedNewUser = new Vector();
			   	//	Query to get the mill/merchant associated with the user
			   		//millAssociatedUser = (Vector) objSession.executeSelectingCall(new SQLCall(
			   		isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
			   			
			   				" SELECT COUNT(1) countr"+
			   				" FROM "+
			   				" PIX_ROLL_SAN_MAPPING PRSM, " +
			   				" PIX_ADMIN_USER_PARTY PAUP, " +
			   				" PIX_ADMIN_USER PAU "+
			   				" WHERE PRSM.SAN = PAUP.SAN "+
			   				" AND PAUP.USER_ID = PAU.USER_ID"+ 
			   				" AND PAU.ROLE_TYPE = 'V' "+
			   				" and PRSM.active ='Y' " +
			   				" AND PAUP.SAN = " + "'" + sanRecord +"'"));
			   		
			   		System.out.println("poNum:"+poNum);
			   		System.out.println("lineNumber:"+lineNumber);
			   		System.out.println("sanRecord:"+sanRecord);

			   				
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



// end roll tracking





/**
 * Gets the Goods Receipt for new paper PO(RFS 5592)
 * @param poId
 * @param poVersion
 * @param pagination
 * @param orderBy
 * @param sort
 * @author anshu.bhardwaj
 * @return java.util.Collection
 */
public Collection getPaperGoodsHistory(POHeader poHeaderTemp,int pagination,Integer userId,String msgId,String actionKey)  throws AppException
{  
	//System.out.println("inside GRDAOimpl");
	Session objSession = getClientSession();
	   GoodsReceipt objGoodsReceipt = null;
	   GoodsReceiptLine objGoodsReceiptLine= null;
	   Date objActualArrivalDate = null;
	   Date objgrRecDate = null;
	   String objActualArrivalDate_String = new String();
	   String objgrRecDateString = new String();
	   int x = 0;
	   Vector gRLineVector = null;
	   
	   
	   Vector objListVector = new Vector();
	   Record objRecord = null;
	   try
	   {
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_Pms_Gr_hist_Proc");
		   call.addNamedArgumentValue("i_po_id",poHeaderTemp.getPoId());
		   call.addNamedArgumentValue("i_po_version",poHeaderTemp.getPoVersion());		   
		   call.addNamedArgumentValue("i_msg_id",msgId);
		   call.addNamedArgumentValue("i_user_id",userId);
		   call.addNamedArgumentValue("i_request_type",actionKey);
	       //call.addNamedArgumentValue("i_pagination",new Integer(pagination));	         
	       call.useNamedCursorOutputAsResultSet("list_refcursor");
	       Vector objVector = (Vector)objSession.executeSelectingCall(call);
	        // to execute stored procedure
	        if (objVector != null && objVector.size()>0)
	        { 
	       	   for (int i = 0; i < objVector.size(); i++)
	     	   { 
	       		 
	        		  objRecord = (Record)objVector.get(i);
	        		  //System.out.println(" objVector.size() "+objVector.size()+"DB returns "+objRecord.toString());
	        		  objGoodsReceipt = new GoodsReceipt();
	        		  objGoodsReceiptLine = new GoodsReceiptLine();
	        		  objGoodsReceipt.setReceiptNo((String)objRecord.get(PIXConstants.RECEIPT_NO));
	        		  //preparing GRLINE collection
	        		  objGoodsReceiptLine.setPoLineNo((BigDecimal)objRecord.get(PIXConstants.PO_LINE_NO));
	        		  objGoodsReceiptLine.setMillName((String)objRecord.get(PIXConstants.MILL_NAME));
	        		  objGoodsReceiptLine.setPrinter((String)objRecord.get(PIXConstants.PRINTER));
	        		  objGoodsReceiptLine.setMsgNo((String)objRecord.get(PIXConstants.MSG_NO));
	        		  objGoodsReceiptLine.setMaterialDesc((String)objRecord.get(PIXConstants.L4_MATERIAL_DESC));
	        		  objGoodsReceiptLine.setMaterialTooltip((String)objRecord.get(PIXConstants.L4_MATERIAL_TOOLTIP));
	        		  if((objActualArrivalDate = (Date)objRecord.get(PIXConstants.DELIVERED_DATE))!= null)
	                  {
	        			  objActualArrivalDate_String = PIXUtil.sqlToStandardDate(objActualArrivalDate.toString());
		      			  objGoodsReceiptLine.setActualArrivalDate(objActualArrivalDate_String);
	            	  }
	        		  objGoodsReceiptLine.setRequestedQuantity((BigDecimal)objRecord.get(PIXConstants.BUYER_REQUESTED_QTY));
	        		  objGoodsReceiptLine.setDeliveredQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.TOTAL_DELIVERED_QTY)).intValue()));
	        		  objGoodsReceiptLine.setPostedQuantity(new Integer(((BigDecimal)objRecord.get(PIXConstants.DM_QTY)).intValue()));
	        		  if(objRecord.get(PIXConstants.TOTAL_RECEIVED_QUANTITY)!=null)
	        		  objGoodsReceiptLine.setCumulativeQuanReceived(new Integer(((BigDecimal)objRecord.get(PIXConstants.TOTAL_RECEIVED_QUANTITY)).intValue()));
	        		  objGoodsReceiptLine.setMsgId((BigDecimal)objRecord.get(PIXConstants.MSG_ID));
	        		  objGoodsReceiptLine.setDeliveredBy((String)objRecord.get(PIXConstants.DELIVERED_BY));
	        		  objGoodsReceiptLine.setApprovalFlag((String)objRecord.get(PIXConstants.APPROVAL_FLAG));
	        		  objGoodsReceiptLine.setDefaultQuantity((BigDecimal)objRecord.get(PIXConstants.DEFAULT_QTY));
	        		  //objGoodsReceiptLine.setReceivedBy((String)objRecord.get(PIXConstants.RECEIVED_BY));
	        		  if((objgrRecDate = (Date)objRecord.get(PIXConstants.REC_DATE))!= null)
	                  {
	        			  objgrRecDateString = PIXUtil.sqlToStandardDate(objgrRecDate.toString());
		      			  objGoodsReceiptLine.setGrRecDate(objgrRecDateString);
	            	  }
	        		  //objGoodsReceiptLine.setColorFlag(new Integer(((BigDecimal)objRecord.get(PIXConstants.COLOUR_FLAG)).intValue()));
	        		  	        		  
	        		  gRLineVector = new Vector();
	        		  gRLineVector.add(objGoodsReceiptLine);
	        		  objGoodsReceipt.setGoodsReceiptLineCollection(gRLineVector);
	            
	        		  objListVector.add(objGoodsReceipt);
	     	   }
	       	  // System.out.println("DAO, History objListVector"+objListVector);
	       
	        }        
	        else if(objVector.size()== 0 || objVector == null)
	        {
	     	     AppException appException = new AppException();
					 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_GOODS_RECEIPT,"GoodsReceiptDAOImpl");  
					 throw appException;
	        }
	        
		   }
		   catch(TopLinkException e)
		   {
			   log.debug("In  GoodsReceiptDaoImpl in History");
			   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "GoodsReceiptDAOImpl,getPaperGoodsHistory",e);
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
 * Saves the Goods Receipt information into the database for paper FO
 * @param objGoodsReceipt
 * @author anshu.bhardwaj
 */
public String insertGoodsReceiptHistory(Collection goodsReceiptCollection,POHeader poHeaderTemp,String objUser) throws AppException
{
	   Session objSession = null;	   
	   String receiptNumber=null;
	   GoodsReceipt objGoodsReceipt=new GoodsReceipt();
	   //System.out.println("inside GR DAO insertGoodsReceiptHistory");
	   try
	   {		   
		   Vector goodsReceiptVector=(Vector)goodsReceiptCollection;
		   GoodsReceiptLine objGoodsReceiptLine = null;
		   Vector goodsReceiptLineVector= null;
		   String poline=null;
		   String recvd_qty=null;
		  for(int i=0;i<goodsReceiptVector.size();i++)
		  {
			objGoodsReceipt= (GoodsReceipt)goodsReceiptVector.get(i);
			
			goodsReceiptLineVector=(Vector)objGoodsReceipt.getGoodsReceiptLineCollection();
			
			   for(int j=0;j<goodsReceiptLineVector.size();j++)
			   {			  
				   objGoodsReceiptLine = (GoodsReceiptLine)goodsReceiptLineVector.get(j);
				   //System.out.println("objGoodsReceiptLine flag: "+objGoodsReceiptLine.getApprovalFlag());
				if(!(objGoodsReceiptLine.getApprovalFlag().equalsIgnoreCase("Yes")))
				{
				if(((objGoodsReceiptLine.getReceivedQuantity()).intValue()>=0) && (objGoodsReceiptLine.getPostedQuantity()).intValue()>=((objGoodsReceiptLine.getReceivedQuantity()).intValue()))
				   {
					//System.out.println("inside History condition objGoodsReceiptLine.getPostedQuantity()).intValue()" + (objGoodsReceiptLine.getPostedQuantity()).intValue());
					if(receiptNumber==null)
					   {
						   receiptNumber=objGoodsReceipt.getReceiptNo(); 
					   }
					   else
					   {
						   receiptNumber=receiptNumber+','+ objGoodsReceipt.getReceiptNo();
					   }
					  if(poline==null)
					   {			   
						   poline=(objGoodsReceiptLine.getPoLineNo()).toString();			  
					   }
					   else
					   {
						   poline=poline+','+(objGoodsReceiptLine.getPoLineNo()).toString();
					   }
					   if(recvd_qty==null)
					   {
						  recvd_qty=(objGoodsReceiptLine.getReceivedQuantity()).toString(); 
					   }
					   else
					   {
						  recvd_qty=recvd_qty+','+ (objGoodsReceiptLine.getReceivedQuantity()).toString();
					   }					   
				   }
				}
		  	}
		  }
		  //System.out.println("input params "+"I_PO_ID "+poHeaderTemp.getPoId()+"_PO_VERSION "+poHeaderTemp.getPoVersion()+"I_PO_LINE_NO "+poline + "I_RECEIPT_NO "+receiptNumber+"I_USER_ID "+new Integer(Integer.parseInt(objUser))+"I_RECEIVED_QTY "+recvd_qty);
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_Paper_Gr_Update_Proc");
		   call.addNamedArgumentValue("I_PO_ID",poHeaderTemp.getPoId());
		   call.addNamedArgumentValue("I_PO_VERSION",poHeaderTemp.getPoVersion());
		   call.addNamedArgumentValue("I_PO_LINE_NO",poline);
		   call.addNamedArgumentValue("I_RECEIPT_NO",receiptNumber);
		   call.addNamedArgumentValue("I_USER_ID",new Integer(Integer.parseInt(objUser)));		   
		   call.addNamedArgumentValue("I_RECEIVED_QTY",recvd_qty);
		   objSession.executeNonSelectingCall(call);
	   }
	   catch(TopLinkException e)
	   {
		   log.debug("In  Insert Paper History GoodsReceiptDaoImpl");
		   AppException appException = new AppException();
		   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "GoodsReceiptDAOImpl,insertGoodsReceiptHistory",e);
		   throw appException;
	   }
	   finally
	   {
		   if(objSession!=null)
		   {
			  objSession.release();
		   }
	   } 
	  // System.out.println("inside GRDAO insertGoodsReceiptHistory end"+receiptNumber);
	   return receiptNumber;
}

//End of changes of RFS 5592


// rolltracking 
public String retrieveSanForPO(String poNum)
{
	   
	   Vector sanVector = null;
	   	Session objSession =  null;
	   	Record objRecord = null;
	   	String sanRecord = null;
	   	BigDecimal val = null;
	   	Vector statusVector = new Vector();
	   	String ss = poNum;
	   	try
	   	{
	   		objSession = getClientSession();
	   		sanVector = (Vector) objSession.executeSelectingCall(new SQLCall(
	   				" select a.san san from pix_po_list_summary b, pix_po_line_party a where a.po_id = b.po_id "+
	   				" and  b.po_no = " + "'" + poNum +"' and rownum = 1 "));

	   				
	   				//		  "select supplier_san san from pix_po_list_summary " +
	   		//	      " where po_no = " + "'" + login_Id +"'"));
	   			
	   				
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



public int getRoleTrackingFlag(String poNum) throws AppException
	{  
			   	Vector isRoleTrackingEnabled = null; //millAssociatedUser = null;
			   	Session objSession =  null;
			   	Record objRecord = null;
			   	int record = 0;
			   	BigDecimal val = null;
			   	Vector statusVector = new Vector();
			   	try
			   	{
			   		String sanRecord = retrieveSanForPO(poNum);
			   		objSession = getClientSession();
			   		isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
			   				" SELECT COUNT(1) countr"+
			   				" FROM "+
			   				" PIX_ROLL_SAN_MAPPING PRSM, " +
			   				" PIX_ADMIN_USER_PARTY PAUP, " +
			   				" PIX_ADMIN_USER PAU "+
			   				" WHERE PRSM.SAN = PAUP.SAN "+
			   				" AND PAUP.USER_ID = PAU.USER_ID"+ 
			   				" AND PAU.ROLE_TYPE = 'V' "+
			   				" and PRSM.active ='Y' " +
			   				" AND PAUP.SAN = " + "'" + sanRecord +"'"));
			   		int vectorSize = isRoleTrackingEnabled == null ? 0 : isRoleTrackingEnabled.size();
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



// end roll tracking

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
//		   			isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
//			   				" Select count(1) as countr from pix_delivery_msg_roll where msg_id = " + "'" + msgId +"'"));
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
		   			
		   			
//		   			isRoleTrackingEnabled = (Vector) objSession.executeSelectingCall(new SQLCall(
//			   				" Select count(1) as countr from pix_good_receipt_roll where receipt_id = " + "'" + msgId +"'"));
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




/*
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
*/




//popup getDeliveryMsgRollInfo
public Vector getGoodsReceiptRollInfo(String msgId, String ponumber, String DMGRMode) throws AppException // pono
{
	Vector listVector = null;
	Vector popUpVector = new Vector();
	Session objSession = null;
	Record objRecord = null;
	
	GoodsReceiptLine objGoodsReceiptLine = null;
	
	DeliveryMessageFile deliveryMessageFile = null;
	SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
//	String pono1 = pono;
	String pono2 = ponumber;
	
	  
	
	
	
	try {
		objSession = getClientSession();
		StoredProcedureCall call = new StoredProcedureCall();
		if(DMGRMode != null && DMGRMode.equals("DM"))
		{
			call.setProcedureName("PIX_DM_GR_ROLL_HYPER_LINK_PROC");
			call.addNamedArgumentValue("I_PO_NO",ponumber);
			call.addNamedArgumentValue("I_KEY_ID",msgId);
			call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");
			listVector = (Vector)objSession.executeSelectingCall(call);
		/*	listVector = (Vector) objSession
			.executeSelectingCall(new SQLCall(
					
					"SELECT "+
					" PPLS.PO_NO,PPL.PO_LINE_NO,PPL.PRODUCT_CODE, PPL.PRODUCT_DESCRIPTION," +
					" PPLS.SUPPLIER_NAME, "+
					" '('||PPLP.org_unit_code||')' || '-' || PPLP.name_1 AS PRINTER, " +
					" PDMR.ROLL_NO, "+
					" PDMR.QUANTITY DELIVERED_QTY, "+ 
					" NVL(PGRR.QUANTITY,0) RECEIVED_QTY, "+
					" 'STATUS'  "+
					" FROM  "+
					" PIX_DELIVERY_MSG_ROLL PDMR,PIX_DELIVERY_MSG_LINE PDML,PIX_PO_LINE PPL,PIX_PO_LIST_SUMMARY PPLS, "+ 
					" PIX_PO_LINE_PARTY PPLP,PIX_GOOD_RECEIPT_LINE PGRL,PIX_GOOD_RECEIPT_ROLL PGRR  "+
					" WHERE PDMR.MSG_ID = PDML.MSG_ID  "+
					" AND PDMR.MSG_LINE_NO = PDML.MSG_LINE_NO "+  
					" AND PDML.PO_ID = PPL.PO_ID  "+
					" AND PDML.PO_LINE_NO = PPL.PO_LINE_NO "+ 
					" AND PDML.MSG_ID =  " + "'" + msgId +"'"+ //'964805' "+  --V_MSG_ID  --- MSG_ID
					" AND PDML.PO_ID =PGRL.PO_ID "+
					" AND PDML.PO_LINE_NO = PGRL.PO_LINE_NO "+
					" AND PDML.MSG_ID = PGRL.MSG_ID "+
					" AND PGRL.RECEIPT_ID = NVL(PGRR.RECEIPT_ID,PGRL.RECEIPT_ID) "+
					" AND PGRL.RECEIPT_LINE_NO = NVL(PGRR.RECEIPT_LINE_NO,PGRL.RECEIPT_LINE_NO) "+ 
					" AND PDMR.ROLL_NO = PGRR.ROLL_NO(+) "+
					" AND PPL.PO_ID = PPLS.PO_ID  "+
					" AND PPL.PO_VERSION = PPLS.PO_VERSION "+
					" AND PPL.PO_ID = PPLP.PO_ID "+
					" AND PPL.PO_LINE_NO = PPLP.PO_LINE_NO "+
					" AND PPL.PO_VERSION = PPLP.PO_VERSION  "+
					" AND (PPLS.PO_NO LIKE '7%' OR PPLS.PO_NO LIKE '9%')"));
			*/

		}
		
		if(DMGRMode != null && DMGRMode.equals("GR"))
		{
			call.setProcedureName("PIX_GR_ROLL_HYPER_LINK_PROC");
			call.addNamedArgumentValue("I_PO_NO",ponumber);
			call.addNamedArgumentValue("I_KEY_ID",msgId);
			call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");
			listVector = (Vector)objSession.executeSelectingCall(call);
			System.out.println(listVector.size());
/*			listVector = (Vector) objSession
			.executeSelectingCall(new SQLCall(
			" SELECT " +
			" PPLS.PO_NO,PPL.PO_LINE_NO, " +
			" PPL.PRODUCT_CODE, " +
			" PPL.PRODUCT_DESCRIPTION, " +
			" PPLS.SUPPLIER_NAME, " +
			" '('||PPLP.org_unit_code||')' || '-' || PPLP.name_1 AS PRINTER, " +
			" PGRR.ROLL_NO, " +
			" PDMR.QUANTITY      DELIVERED_QTY, " +
			" PGRR.QUANTITY        RECEIVED_QTY, " +
			" 'STATUS' " +
			" FROM " +
			" PIX_DELIVERY_MSG_ROLL PDMR, " +
			" PIX_DELIVERY_MSG_LINE PDML,PIX_PO_LINE PPL,PIX_PO_LIST_SUMMARY PPLS, " +
			" PIX_PO_LINE_PARTY PPLP,PIX_GOOD_RECEIPT_LINE PGRL,PIX_GOOD_RECEIPT_ROLL PGRR " +
			" WHERE " +
			" PGRL.RECEIPT_ID = " + "'" + msgId +"'"+
			" AND PGRL.RECEIPT_ID = PGRR.RECEIPT_ID " +
			" AND PGRR.ROLL_NO = PDMR.ROLL_NO " +
			" AND PDMR.MSG_ID = PDML.MSG_ID " +
			" AND PGRL.MSG_ID = PDML.MSG_ID " +
			" AND PGRL.PO_ID = PDML.PO_ID " +
			" AND PGRL.PO_LINE_NO = PDML.PO_LINE_NO " +
			" AND PGRL.PO_VERSION = PDML.PO_VERSION " +
			" AND PDML.PO_ID = PPL.PO_ID " +
			" AND PDML.PO_LINE_NO = PPL.PO_LINE_NO " +
			" AND PPL.PO_ID = PPLS.PO_ID " +
			" AND PPL.PO_VERSION = PPLS.PO_VERSION " +
			" AND PPL.PO_ID = PPLP.PO_ID " +
			" AND PPL.PO_LINE_NO = PPLP.PO_LINE_NO " +
			" AND PPL.PO_VERSION = PPLP.PO_VERSION " +
			" AND (PPLS.PO_NO LIKE '7%' OR PPLS.PO_NO LIKE '9%')"));
*/
		}
						

		
		if (listVector != null && listVector.size() > 0) {
			for (int i = 0; i < listVector.size(); i++) {
				objRecord = (Record) listVector.get(i);
				
				objGoodsReceiptLine = new GoodsReceiptLine();
				
				objGoodsReceiptLine.setPoNo((String) objRecord.get("PO_NO"));
				objGoodsReceiptLine.setPoLineNo((BigDecimal) objRecord.get("PO_LINE_NO"));
				
				//	objGoodsReceiptLine.setPoLineNo((String) objRecord.get("PO_LINE_NO"));
				// for removing zeros
				
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
			//	objGoodsReceiptLine.setProductCode((String) objRecord.get("PRODUCT_CODE"));
				objGoodsReceiptLine.setProductCode(pc);   // product code - truncated
				objGoodsReceiptLine.setProductDescription((String) objRecord.get("PRODUCT_DESCRIPTION"));
				objGoodsReceiptLine.setSupplierName((String) objRecord.get("SUPPLIER_NAME"));
			//	objGoodsReceiptLine.setOrgUnitCode((String) objRecord.get("ORG_UNIT_CODE"));
				objGoodsReceiptLine.setOrgUnitName((String) objRecord.get("PRINTER"));
				objGoodsReceiptLine.setRollNumber((String) objRecord.get("ROLL_NO"));
			//	objGoodsReceiptLine.setQuantity((BigDecimal) objRecord.get("QUANTITY"));
				objGoodsReceiptLine.setDelQuantity((BigDecimal) objRecord.get("DELIVERED_QTY"));
				objGoodsReceiptLine.setRecQuantity((BigDecimal) objRecord.get("RECEIVED_QTY"));
				objGoodsReceiptLine.setStatus("STATUS");
				
			//	deliveryMessageFile = new DeliveryMessageFile();

			//	deliveryMessageFile.setRollNumber((String) objRecord.get("ROLL_NO")); // setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
			//	deliveryMessageFile.setRollWeight((BigDecimal) objRecord.get("QUANTITY"));
			//	deliveryMessageFile.setDmPostedBy((String) objRecord.get("CREATED_BY"));
			//	deliveryMessageFile.setDmPostedDate(dateObj.format((Date) objRecord.get("CREATION_DATE_TIME")));
				popUpVector.add(objGoodsReceiptLine);

			}
	}
	return popUpVector;
	} catch (TopLinkException e) {
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.SQL_EXCEPTION,"FileUploadingDAOImpl,getDeliveryMsgRollInfo", e);
	} finally {
		if (objSession != null) {
			objSession.release();
		}
	}

	return popUpVector; 
}


}



