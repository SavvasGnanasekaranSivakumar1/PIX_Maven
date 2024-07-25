//Source file: E:\\PIXProject\\PIXApplicationEJB\\src\\com\\pearson\\pix\\dao\\arptitlesetup\\PotentialARPDAOImpl.java

package com.pearson.pix.dao.arptitlesetup;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.queryframework.ValueReadQuery;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.arptitlesetup.WqstatesArp;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 * @author faisalk
 */
public class PotentialARPDAOImpl extends BaseDAO implements PotentialARPDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PotentialARPDAOImpl.class.getName());
   
   /**
    * Constructor for initializing PotentialARPDAO
    */
   public PotentialARPDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Collection of PotentialARP List information. This method also takes care 
    * of filtering and pagination display by passing page no, filter criteria and its 
    * value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPotentialARPsList(int userId,String roleType,String page,int currentValue,String orderby,String sort,String whereClause,String status)throws AppException 
   {
	   Session objSession = null;
	   Vector objAllRecords = new Vector();
	   PotentialARP objPotentialARP = null;
	   String statusCode ="";
	   SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
	   try
	   {	
	   objSession = getClientSession();
	   StoredProcedureCall call = new StoredProcedureCall();
	   if("".equalsIgnoreCase(status)){
		   statusCode ="PENDING";
	   }else if("68".equalsIgnoreCase(status)){
		   statusCode = "PENDING";
	   }else if("67".equalsIgnoreCase(status)){
		   statusCode = "RECEIVED";
	   }else if("66".equalsIgnoreCase(status)){  
		   statusCode = "REJECTED";  
	   }	   
	   //if("V".equals(roleType) || "A".equals(roleType) || "B".equalsIgnoreCase(roleType)){
		   call.setProcedureName("PIX_ARP_FLIP_RPT_PROC");  //Setting the procedure name and providing arguments
		   call.addNamedArgumentValue("I_STAT_CD",statusCode);
		   call.addNamedArgumentValue("I_WHERE_CLAUSE",whereClause);
		   call.addNamedArgumentValue("I_PAGENAT_NBR",new Integer(currentValue));
		   call.addNamedArgumentValue("I_USER_ID",userId);
		   //call.addNamedArgumentValue("I_PAGE",1);
		   call.useNamedCursorOutputAsResultSet("O_REF_CURSOR");      
		   objSession.logMessages();    
		 //  }
		   Vector objVector = (Vector)objSession.executeSelectingCall(call); //Calling the procedure
           int lengthOfobjCollection = objVector == null ? 0 : objVector.size(); //Setting the size of the resulting vector
           if (objVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
           {
          	 for (int i = 0; i < lengthOfobjCollection; i++) //loop for iterating the vector results
        	 {
      		   Record objDatabaseRecord = (Record)objVector.get(i);
      		   objPotentialARP = new PotentialARP();
      		   
      		   objPotentialARP.getPotentialarpDetail().setTitleIsbn((String)objDatabaseRecord.get(PIXConstants.TITLE_ISBN));
      		   objPotentialARP.getPotentialarpDetail().setEventId((BigDecimal)objDatabaseRecord.get(PIXConstants.EVENT_ID));
      		   //objPotentialARP.getPotentialarpDetail().setTitleId((BigDecimal)objDatabaseRecord.get(PIXConstants.TITLE_ID));
      		   //objPotentialARP.getPotentialarpDetail().setVendorPlantId((String)objDatabaseRecord.get(PIXConstants.VENDOR_PLANT_ID));
      		   objPotentialARP.getPotentialarpDetail().setVendorPageCount((BigDecimal)objDatabaseRecord.get(PIXConstants.VENDOR_PAGE_COUNT));
      		   objPotentialARP.getPotentialarpDetail().setBuyerPageCount((BigDecimal)objDatabaseRecord.get(PIXConstants.BUYER_PAGE_COUNT));
      		   objPotentialARP.getPotentialarpDetail().setPrintingNo((BigDecimal)objDatabaseRecord.get(PIXConstants.PRINTING_NO));
      		   objPotentialARP.getPotentialarpDetail().setAuthor((String)objDatabaseRecord.get(PIXConstants.AUTHOR));  
      		   if((String)objDatabaseRecord.get(PIXConstants.TITLE_DESCRIPTION)!=null)
   		       {  
      		      objPotentialARP.getPotentialarpDetail().setTitleDescription((String)objDatabaseRecord.get(PIXConstants.TITLE_DESCRIPTION));
    	       }
      		   objPotentialARP.getPotentialarpDetail().setEdition((String)objDatabaseRecord.get(PIXConstants.EDITION));
      		   objPotentialARP.getPotentialarpDetail().setCopyrightYear((BigDecimal)objDatabaseRecord.get(PIXConstants.COPYRIGHT_YEAR));
      		   objPotentialARP.getPotentialarpDetail().setTitleStatus((String)objDatabaseRecord.get(PIXConstants.TITLE_STATUS));
      		   objPotentialARP.getPotentialarpDetail().setBuyerName((String)objDatabaseRecord.get(PIXConstants.BUYER_NAME));
      		   objPotentialARP.setRequestDate((dateObj.format((Date)objDatabaseRecord.get(PIXConstants.INVENT_REQUEST_DATE))));
      		   objPotentialARP.setArpDueDate(dateObj.format((Date)objDatabaseRecord.get(PIXConstants.INVENT_DUE_DATE)));
      		   if((Double)objDatabaseRecord.get(PIXConstants.UNIT_COST)!=null)
   		       {
      		      objPotentialARP.getPotentialarpDetail().setUnitCost((Double)objDatabaseRecord.get(PIXConstants.UNIT_COST));
   		       }
      		   objPotentialARP.getPotentialarpDetail().setComments((String)objDatabaseRecord.get(PIXConstants.VENDOR_COMMENTS));
      		   if(objDatabaseRecord.get(PIXConstants.REVIEW_COPY_REQ)!=null)
   		       {
      		      objPotentialARP.getPotentialarpDetail().setReviewCopyReq((String)objDatabaseRecord.get(PIXConstants.REVIEW_COPY_REQ));
   		       }
      		   if(objDatabaseRecord.get(PIXConstants.REVIEW_COPY_PROVIDE)!=null) 
   		       {
      		       objPotentialARP.getPotentialarpDetail().setReviewCopyProvide((String)objDatabaseRecord.get(PIXConstants.REVIEW_COPY_PROVIDE));
   		       }  
      		   objPotentialARP.getPotentialarpDetail().setReviewCopyType((String)objDatabaseRecord.get(PIXConstants.REVIEW_COPY_TYPE));
      		   objPotentialARP.getPotentialarpDetail().setBuyerComments((String)objDatabaseRecord.get(PIXConstants.BUYER_COMMENTS));
      		   objPotentialARP.getPotentialarpDetail().setStatusCode((String)objDatabaseRecord.get(PIXConstants.STATUS_CODE));
      		   objPotentialARP.setSpecVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.SPEC_VERSION));
      		   objPotentialARP.setSpecId((BigDecimal)objDatabaseRecord.get(PIXConstants.SPEC_ID));
      		   /*Adding all the records displayed in ARP Potential List*/
      		   objAllRecords.add(objPotentialARP);
             }
           }
           else if(objVector.size()== 0 || objVector == null) //Block to be executed when there are no records
           {
    	     	 AppException appException=new AppException();
    	     	 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_POTENTIALARP,"PotentialARPDAOImpl");  
			     throw appException;    
           }
	    }
        catch(TopLinkException e)
	 	{
	 		   log.debug("TopLinkException for PotentialARP List");
	 		   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "PotentialARPDAOImpl,getPotentialARPList",e);
			   throw appException;
	    }
	 	finally //Session release code 
		{
		   if(objSession!=null)
		   {
			   objSession.release();
		   }
		}
	    return objAllRecords; //Returning the vector containing Party and POHeader object as result
   }
  
   /**
    * Gets the Collection of PotentialARP Status information.
    * 
    * @return java.util.Collection
    */
 public Collection getPotentialARPStatus()throws AppException
 {
	 Collection objallStatus = null;
	 Session clientSession = null;
	 try
	 {
	   clientSession = getClientSession();
	   ExpressionBuilder builder = new ExpressionBuilder(); 
	   Expression expStatus = builder.get("tableDetail").get("tableName").equal(PIXConstants.WQSTATES_ARP);
	   ReadAllQuery query = new ReadAllQuery(WqstatesArp.class);//getting all status for planning and ordering them ascending 
	   //query.setSelectionCriteria(expStatus);
	   query.addAscendingOrdering("id");
	   objallStatus = (Vector)clientSession.executeQuery(query);
	 }
	 catch(TopLinkException e)
	   {
		   log.debug("TopLinkException while fetching potential arp status");
		   AppException ae = new AppException();
		   ae.performErrorAction("Exceptions.SQL_EXCEPTION","PotentialARPDAOImpl,getPotentialARPStatus",e);
		   throw e;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return objallStatus;
 }
 
/**
 *  Validating the transaction valid for transmission
 */
 public Vector validateTransaction(String titleId,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException
 {
 		Session objSession = null;
 		Vector objVector = null;
		int vendorCnt =0;
		int isRequestProvided =0;
		int errorCode =0;
		String errorMsg ="";
		Status statusDtl = null;
    	try {
			objSession = getClientSession();
			StoredProcedureCall call = new StoredProcedureCall();   
		    call.setProcedureName("PIX_IS_ARP_TXN_REQRD");
		    call.addNamedArgumentValue("P_EVENT_ID", new Integer(eventId));
		    call.addNamedArgumentValue("P_TITLE_ID", new Integer(titleId));
			if(vendorPageCount != ""){
				call.addNamedArgumentValue("P_VENDOEPAGECOUNT",new Integer(vendorPageCount));
			}else{
				call.addNamedArgumentValue("P_VENDOEPAGECOUNT",new Integer(0));  
			}	
			if(isReqProvided != null)
			{	
			  if("true".equalsIgnoreCase(isReqProvided)){
				isRequestProvided = 1; 
				call.addNamedArgumentValue("P_REVIEWCOPYPROVIDE", 1);
			  }else{
				isRequestProvided = 0; 
				call.addNamedArgumentValue("P_REVIEWCOPYPROVIDE", isRequestProvided);
			  }	  
			}  
			if (comments != null)
			{	call.addNamedArgumentValue("P_COMMENTS", comments); }
	      
			call.useNamedCursorOutputAsResultSet("P_REF_CRSR");  
 
			objVector = (Vector)objSession.executeSelectingCall(call);
			int lengthOfobjCollection = objVector == null ? 0 : objVector.size(); //Setting the size of the resulting vector
	           if (objVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
	           {
	          	   for (int i = 0; i < lengthOfobjCollection; i++) //loop for iterating the vector results
	        	   {
	          		  Record objDatabaseRecord = (Record)objVector.get(i);
	          		  statusDtl = new Status();
	          		  statusDtl.setStatusId(((BigDecimal)objDatabaseRecord.get(PIXConstants.O_RET_CODE)).intValue());
	          		  statusDtl.setStatusDescription((String)objDatabaseRecord.get(PIXConstants.O_RET_MSG));
	          		  objVector.add(statusDtl);
	          	   }
	           }
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();  
		} finally {
			if (objSession != null) {
				objSession.release();
			}
		}
		return objVector;  
	}
 
 /**
  *   For Accepting transaction on SyBase Side
  */
  public Vector sysBaseUpdVendorAccept(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException
  {
 		Session objSession = null;
 		Vector objVector = null;
		int vendorCnt =0;
		int isRequestProvided =0;
		Status statusDtl = null;
		
 		try {
 			
 			objSession = getARPClientSession();

 			if(objSession == null)
 			{
 				statusDtl = new Status();
 				objVector = new Vector();
 				statusDtl.setTitleISBN("");
        		statusDtl.setStatusId(2);
        		statusDtl.setStatusDescription("Not able to connect to ARP Database.");
        		objVector.add(null); 
        		objVector.add(statusDtl);  
 			}
 			else
 			{
 			objSession.executeNonSelectingCall(new SQLCall("SET CHAINED OFF"));
			StoredProcedureCall call = new StoredProcedureCall();   
			call.setProcedureName("PandB.dbo.P_UpdateVendorAcceptance_ARP");
			call.addNamedArgumentValue("TitleIsbn", titleIsbn);
			call.addNamedArgumentValue("UserId", new Integer(userId));
			call.addNamedArgumentValue("EventId", new Integer(eventId));
			if(vendorPageCount != ""){
				call.addNamedArgumentValue("VendorPageCount",new Integer(vendorPageCount));
			}else{
				call.addNamedArgumentValue("VendorPageCount",new Integer(0));  
			}	
			if(isReqProvided != null)
			{	
			  if("true".equalsIgnoreCase(isReqProvided)){
				isRequestProvided = 1; 
				call.addNamedArgumentValue("ReviewCopyProvide", 1);
			  }else{
				isRequestProvided = 0; 
				call.addNamedArgumentValue("ReviewCopyProvide", isRequestProvided);
			  }	  
			}  
			if (comments != null)
			{	call.addNamedArgumentValue("Comments", comments); }
	      
			objVector = (Vector)objSession.executeSelectingCall(call);
			int lengthOfobjCollection = objVector == null ? 0 : objVector.size(); //Setting the size of the resulting vector
	           if (objVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
	           {
	          	   for (int i = 0; i < lengthOfobjCollection; i++) //loop for iterating the vector results
	        	   {
	          		  Record objDatabaseRecord = (Record)objVector.get(i);
	          		  statusDtl = new Status();
	          		  statusDtl.setTitleISBN((String)objDatabaseRecord.get("TitleIsbn"));
	          		  statusDtl.setStatusId((Integer)objDatabaseRecord.get("OutCode"));
	          		  statusDtl.setStatusDescription((String)objDatabaseRecord.get("OutMessage"));
	          		  objVector.add(statusDtl);    
	          	   }
	           }
 			}   

 		} catch (Exception e) {
 			e.printStackTrace();
 			throw new AppException();
 		} finally {
 			if (objSession != null) {
 				objSession.release();
 			}
 		} 		return objVector;
 	}
  /**
   *  For Rejecting transaction on SyBase Side
   */
   public Vector sysBaseUpdVendorReject(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException
   {
	   Session objSession = null;
		Vector objVector = null;
		int vendorCnt =0;
		int isRequestProvided =0;
		Status statusDtl = null;
		
		try {
			objSession = getARPClientSession();
			objSession.executeNonSelectingCall(new SQLCall("SET CHAINED OFF"));
			StoredProcedureCall call = new StoredProcedureCall();   
			call.setProcedureName("PandB.dbo.P_UpdateVendorRejection_ARP");
			call.addNamedArgumentValue("TitleIsbn", titleIsbn);
			call.addNamedArgumentValue("UserId", new Integer(userId));
			call.addNamedArgumentValue("EventId", new Integer(eventId));
			if(vendorPageCount != ""){
				call.addNamedArgumentValue("VendorPageCount",new Integer(vendorPageCount));
			}else{
				call.addNamedArgumentValue("VendorPageCount",new Integer(0));  
			}	
			if(isReqProvided != null)
			{	
			  if("true".equalsIgnoreCase(isReqProvided)){
				isRequestProvided = 1; 
				call.addNamedArgumentValue("ReviewCopyProvide", 1);
			  }else{
				isRequestProvided = 0; 
				call.addNamedArgumentValue("ReviewCopyProvide", isRequestProvided);
			  }	  
			}  
			if (comments != null)
			{	call.addNamedArgumentValue("Comments", comments); }
	      
			objVector = (Vector)objSession.executeSelectingCall(call);
			int lengthOfobjCollection = objVector == null ? 0 : objVector.size(); //Setting the size of the resulting vector
	           if (objVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
	           {
	          	   for (int i = 0; i < lengthOfobjCollection; i++) //loop for iterating the vector results
	        	   {
	          		  Record objDatabaseRecord = (Record)objVector.get(i);
	          		  statusDtl = new Status();
	          		  statusDtl.setTitleISBN((String)objDatabaseRecord.get("TitleIsbn"));
	          		  statusDtl.setStatusId((Integer)objDatabaseRecord.get("OutCode"));
	          		  statusDtl.setStatusDescription((String)objDatabaseRecord.get("OutMessage"));
	          		  objVector.add(statusDtl);    
	          	   }
	           }

 		} catch (Exception e) {
 			e.printStackTrace();
 			throw new AppException();
 		} finally {
 			if (objSession != null) {
 				objSession.release();
 			}
 		}
 		return objVector;
  	}
   /**
    *  For Accepting transaction on PIX Side
    */
    public String pixUpdVendorAccept(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId)throws AppException
    {
    	Session objSession = null;
 		Vector objVector = null;
		int vendorCnt =0;
		int isRequestProvided =0;
		int isReviewRequested =0;
		int errorCode =0;
		String errorMsg ="";
		String isValid ="";
		Status statusDtl = null;
    	try {
			objSession = getClientSession();
			
			StoredProcedureCall call = new StoredProcedureCall();     
		    call.setProcedureName("PIX_ARP_VENDOR_ACTION_PROC");  
		    call.addNamedArgumentValue("I_TRANSCTN_HIST_NO", new Integer(eventId));
		    call.addNamedArgumentValue("I_ISBN", titleIsbn);
		    if(vendorPageCount != ""){
				call.addNamedArgumentValue("I_VNDR_PAGE_CNT",new Integer(vendorPageCount));
			}else{
				call.addNamedArgumentValue("I_VNDR_PAGE_CNT",new Integer(0));    
			}	
		    if(isReviewReq != null)
			{	
			  if("true".equalsIgnoreCase(isReviewReq)){
				 isReviewRequested = 1; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_REQST", 1);
			  }else{
				isReviewRequested = 0; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_REQST", isReviewRequested);
			  }	  
			}
		    if(isReqProvided != null)
			{	
			  if("true".equalsIgnoreCase(isReqProvided)){
				isRequestProvided = 1; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_PROV", 1);
			  }else{
				isRequestProvided = 0; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_PROV", isRequestProvided);
			  }	  
			} 
        	call.addNamedArgumentValue("I_COMMENTS", comments); 
      
			call.addNamedArgumentValue("I_ACTION_STATUS", "RECEIVED");
			call.addNamedArgumentValue("I_USER_ID", userId);  
			call.addNamedOutputArgument("O_RET_CODE");   
 
			ValueReadQuery query = new ValueReadQuery();
			query.setCall(call);
			objSession.logMessages();  
			isValid = (String) objSession.executeQuery(query);  
		 
		} catch (TopLinkException e) {
			   log.debug("TopLinkException while Calling Procedure for Vendor Accept");  
			   AppException ae = new AppException();
			   ae.performErrorAction("Exceptions.SQL_EXCEPTION","PotentialARPDAOImpl,pixUpdVendorAccept",e);
			   throw ae;
		} finally {
			if (objSession != null) {
				objSession.release();
			}
		}
		return isValid;  
   	}
    /**
     *  For Rejecting transaction on PIX Side
     */
     public String pixUpdVendorReject(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId)throws AppException
     {
    	Session objSession = null;
  		Vector objVector = null;
 		int vendorCnt =0;
 		int isRequestProvided =0;
 		int isReviewRequested =0;
 		int errorCode =0;
 		String errorMsg ="";
 		Status statusDtl = null;
 		String isValid ="";
     	try {
 			objSession = getClientSession();
 			StoredProcedureCall call = new StoredProcedureCall();   
 		    call.setProcedureName("PIX_ARP_VENDOR_ACTION_PROC");
 		    call.addNamedArgumentValue("I_TRANSCTN_HIST_NO", new Integer(eventId));
		    call.addNamedArgumentValue("I_ISBN", titleIsbn);
		    if(vendorPageCount != ""){
				call.addNamedArgumentValue("I_VNDR_PAGE_CNT",new Integer(vendorPageCount));
			}else{
				call.addNamedArgumentValue("I_VNDR_PAGE_CNT",new Integer(0));    
			}	
		    if(isReviewReq != null)
			{	
			  if("true".equalsIgnoreCase(isReviewReq)){
				isReviewRequested = 1; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_REQST", 1);
			  }else{
				isReviewRequested = 0; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_REQST", isReviewRequested);
			  }	  
			}
		    if(isReqProvided != null)
			{	
			  if("true".equalsIgnoreCase(isReqProvided)){
				isRequestProvided = 1; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_PROV", 1);
			  }else{
				isRequestProvided = 0; 
				call.addNamedArgumentValue("I_REVIEW_FLAG_PROV", isRequestProvided);
			  }	  
			} 
        	call.addNamedArgumentValue("I_COMMENTS", comments); 
      
			call.addNamedArgumentValue("I_ACTION_STATUS", "CORRECTION");
			call.addNamedArgumentValue("I_USER_ID", userId);  
			call.addNamedOutputArgument("O_RET_CODE");   
			ValueReadQuery query = new ValueReadQuery();
			query.setCall(call);
			isValid = (String) objSession.executeQuery(query);  

		} catch (Exception e) {
			//e.printStackTrace();
			throw new AppException();  
		} finally {
			if (objSession != null) {
				objSession.release();   
			}
		}
		return isValid;  
    }
}





