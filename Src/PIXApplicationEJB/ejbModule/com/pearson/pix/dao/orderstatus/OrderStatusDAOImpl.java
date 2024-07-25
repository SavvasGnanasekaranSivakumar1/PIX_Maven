package com.pearson.pix.dao.orderstatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.transaction.UserTransaction;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.dto.orderstatus.OrderStatusLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 * @author Dandu Thirupathi
 */
public class OrderStatusDAOImpl extends BaseDAO implements OrderStatusDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(OrderStatusDAOImpl.class.getName());
   /**
    * Constructor to initialize OrderStatusDAOImpl
    */
   public OrderStatusDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Order Status List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Vector
    */
   public Vector getOrderStatusList(Integer poId,Integer poVersion,Integer pagination,String orderBy,String sort) throws AppException
   {
	   Session objClientSession = getClientSession();
       Vector objVectorList=new Vector();
       BigDecimal objOrderStatus_id=null;
       try
       {
    	   StoredProcedureCall call = new StoredProcedureCall(); 
           call.setProcedureName("Pix_Order_status_Proc");
           call.addNamedArgumentValue("i_po_id",poId);
           call.addNamedArgumentValue("i_po_version",poVersion);
           call.addNamedArgumentValue("i_pagination",pagination);
           call.addNamedArgumentValue("i_order_by",orderBy);
           call.addNamedArgumentValue("i_sort",sort);
           call.addNamedArgumentValue("i_input","");
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           // executing stored procedure  
           Vector objVector = (Vector) objClientSession.executeSelectingCall(call);
                                
           if (objVector != null && objVector.size()>0)
           {
        	   for (int i = 0; i < objVector.size(); i++)
               {
                 OrderStatusLine objOrderStatus=new OrderStatusLine();
                 Record objDatabaseRecord = (Record)objVector.get(i);
                  
                 objOrderStatus.setStatusNo((String) objDatabaseRecord.get(PIXConstants.STATUS_NO));
                 if((objOrderStatus_id=(BigDecimal) objDatabaseRecord.get(PIXConstants.ORDER_STATUS_ID))!= null)
                 {
       			  Integer objStatus_id_Int=new Integer(objOrderStatus_id.intValue());
       			  objOrderStatus.setStatusId(objStatus_id_Int);
                 }
                 objOrderStatus.setCreationDateTime((Date)objDatabaseRecord.get(PIXConstants.CREATION_DATE_TIME));
                 objOrderStatus.setCreatedBy((String) objDatabaseRecord.get(PIXConstants.CREATED_BY));
                 //setting OrderStatus list  
                 objVectorList.add(objOrderStatus);
              }
           }
          else if(objVector.size()== 0 || objVector == null)
          {
       	     AppException appException=new AppException();
       	     log.debug("there is no OrderStatus for given poId="+poId+",poVersion="+poVersion+",pagination="+pagination+",orderBy="+orderBy+"and sort="+sort);
 			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_STATUS,"OrderStatusDAOImpl");
 			 throw appException;
          }
       }catch(TopLinkException e){
    	    AppException appException = new AppException();
            appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,getOrderStatusList",e);
            throw appException;
       }
       finally {
     	 if(objClientSession!=null)
 		 {
     		objClientSession.release();
 		 }
 	   }    
    return objVectorList;     
   }
   
   /**
    * Gets Order Status detail for a status number of PO from the database
    * 
    * @param poId
    * @param poVersion
    * @param statusId
    * @return com.pearson.pix.dto.orderstatus.OrderStatus
    */
   public Vector getOrderStatusDetail(Integer poId,Integer poVersion, String statusId) throws AppException
   {
	   Record objDatabaseRecord=null;
	   Session objClientSession=null;
	   Vector objVectorDisplayList=new Vector();
       Date objTimelineDate=null;
       Date objEstimatedDate=null;
       Date objRequestedDate=null;
       String objEstimatedDelDate=null;
       String objTimelineDateString=null;
       String objRequestedDelDate=null;
       int orderStatusId=Integer.parseInt(statusId);
      
       try
       {
    	  objClientSession  = getClientSession();
          Vector objVectorDisplay = objClientSession.executeSelectingCall(new SQLCall("Select ppl.po_line_no as line_no,ppl.product_description," +
          		                   "ppl.requested_delivery_date,pos.estimated_date as estimated_delivery_date,pst.timeline_description as timeline," +
          		                   "pos.timeline_date,pix_get_status_desc_func ('PIX_ORDER_STATUS', pos.status_id) as status_description,pos.comments " +
          		                   "From pix_order_status pos, pix_po_line ppl, pix_status_timeline pst Where pos.po_id = ppl.po_id And " +
          		                   "pos.po_version = ppl.po_version And pos.po_line_no = ppl.po_line_no And pos.timeline_id = pst.timeline_id " +
        		  				   "And pos.order_status_id =" + orderStatusId + " " +
        		  				   "Order by ppl.po_line_no, pos.estimated_date, pos.timeline_date"));
                   
           if (objVectorDisplay != null && objVectorDisplay.size()>0)
           {
        	 for (int i = 0; i < objVectorDisplay.size(); i++)
             {
        		objDatabaseRecord = (Record)objVectorDisplay.get(i);
        		OrderStatusLine objOrderStatus=new OrderStatusLine();
        		
        		objOrderStatus.setPoLineNo((BigDecimal)objDatabaseRecord.get(PIXConstants.LINE_NO));
        		objOrderStatus.setProductDescription((String) objDatabaseRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
        		if((objRequestedDate=(Date)objDatabaseRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
                {
        			objRequestedDelDate= PIXUtil.sqlToStandardDate(objRequestedDate.toString());
                	objOrderStatus.setRequestedDeliveryDate(objRequestedDelDate);
               	}
        		if((objEstimatedDate=(Date)objDatabaseRecord.get(PIXConstants.ESTIMATED_DELIVERY_DATE))!= null)
                {
        			objEstimatedDelDate= PIXUtil.sqlToStandardDate(objEstimatedDate.toString());
                	objOrderStatus.setEstimatedDeliveryDate(objEstimatedDelDate);
               	}
                objOrderStatus.setTimelineDescription((String) objDatabaseRecord.get(PIXConstants.TIMELINE));
                if((objTimelineDate=(Date)objDatabaseRecord.get(PIXConstants.TIMELINE_DATE))!= null)
                {
                	objTimelineDateString= PIXUtil.sqlToStandardDate(objTimelineDate.toString());
                	 objOrderStatus.setTimelineDate(objTimelineDateString);
                }
                objOrderStatus.setStatusDescription((String) objDatabaseRecord.get(PIXConstants.STATUS_DESC));
                objOrderStatus.setComments((String) objDatabaseRecord.get(PIXConstants.COMMENTS));
                //setting OrderStatus                            
                objVectorDisplayList.add(objOrderStatus);
              }
            }
           else if(objVectorDisplay.size()== 0 || objVectorDisplay == null)
           {
       	   	 AppException appException=new AppException();
       	   	 log.debug("there is no detail for the given statusId= "+statusId);
 			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_STATUS,"OrderStatusDAOImpl");  
 			 throw appException;
          }
          }catch(TopLinkException e)
          {
        	  log.debug("TopLinkException for OrderStatusDetail ");
        	  AppException appException = new AppException();
        	  appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,getOrderStatusDetail",e);
        	  throw appException;
          }
         finally
 	     {
 		   if(objClientSession!=null)
 		   {
 			  log.info("Log Info OrderStatusDAOImpl in finally block");
 			  objClientSession.release();
 		   }
 	    }    
   return objVectorDisplayList;
   }
   
   /**
    * Gets the Basic Order Status information from the database
    * 
    * @param poId
    * @param poVersion
    * @return java.util.HashMap
    */
   public HashMap getBasicOrderStatusInfo(Integer poId,Integer poVersion) throws AppException
   {
	   Record objDatabaseRecord=null;
	   Session objClientSession=null;
	   Vector objVectorDisplay=null;
       Vector objVectorDisplayList=null;
       HashMap objHashMap = new HashMap();
       Date objRequestedDate=null;
       String objRequestedDelDate=null;
       try
       {
    	  objClientSession  = getClientSession();
    	  objVectorDisplay = objClientSession.executeSelectingCall(new SQLCall("Select /* +FIRST_ROWS*/ ppl.po_line_no as line_no," +
                              "ppl.product_description,ppl.requested_delivery_date From pix_po_line ppl " + 
                             "Where ppl.po_id =" + poId + " And ppl.po_version =" + poVersion + " Order by ppl.po_line_no"));
          
         objVectorDisplayList=new Vector(); 
                           
         if (objVectorDisplay != null && objVectorDisplay.size()>0)
         {
           for (int i = 0; i < objVectorDisplay.size(); i++)
           {
        	 objDatabaseRecord = (Record)objVectorDisplay.get(i);
        	 OrderStatusLine objOrderStatus=new OrderStatusLine();
        	 objOrderStatus.setPoLineNo((BigDecimal)objDatabaseRecord.get(PIXConstants.LINE_NO));
        	 
        	 objOrderStatus.setProductDescription((String) objDatabaseRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
        	 if((objRequestedDate=(Date)objDatabaseRecord.get(PIXConstants.REQUESTED_DELIVERY_DATE))!= null)
             {
     			objRequestedDelDate= PIXUtil.sqlToStandardDate(objRequestedDate.toString());
             	objOrderStatus.setRequestedDeliveryDate(objRequestedDelDate);
             }
        	 objVectorDisplayList.add(objOrderStatus);
           }
           //  setting Order Status line items 
            objHashMap.put("ListDisplay",objVectorDisplayList);
        	Vector objTimeLine =getTimeLine();
        	// setting Time line Description 
        	objHashMap.put("ListCombo",objTimeLine);
        	Vector objStatusDesc= getStatusDesc();
            //	setting Status Description list
        	objHashMap.put("StatusCombo",objStatusDesc);
         }
         else if(objVectorDisplay.size()== 0 || objVectorDisplay == null)
         {
       	   	 AppException appException=new AppException();
       	   	 log.debug("There is no line item components for given poId="+poId+" and poVersion="+poVersion);
 			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_STATUS,"OrderStatusDAOImpl");  
 			 throw appException;
         }
         }catch(TopLinkException e)
         {
        	 log.debug("TopLinkException for OrderStatus New");
        	 AppException appException = new AppException();
        	 appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,getOrderStatusDetail",e);
             throw appException;
         }
         finally
	    {
		   if(objClientSession!=null)
		   {
			  objClientSession.release();
		   }
	    }    
     return objHashMap;
   }
   
   
   /**
    * gets the TimeLine information from the database
    * @return java.util.Vector
    */
   private Vector getTimeLine() throws AppException
   {
	   BigDecimal objTimelineId=null;
	   Integer objTimelineIdInt=null;
	   Vector objVectorDisplayList=null;
	   Session objclientSession=null;
	   Record objDatabaseRecord=null;
	   try
       {    	   
    	  Vector objVectorDisplay=null; 
    	  objclientSession = (Session)getClientSession();
          objVectorDisplay = objclientSession.executeSelectingCall(new SQLCall("select pst.timeline_id,pst.timeline_description as timeline "+
        		             "from  pix_status_timeline pst where pst.active_flag='Y' and timeline_id=1"));
          objVectorDisplayList= new Vector();
      	        
           if (objVectorDisplay != null && objVectorDisplay.size()>0)
           {
        	   for (int i = 0; i < objVectorDisplay.size(); i++)
               {
        		  objDatabaseRecord = (Record)objVectorDisplay.get(i);
        		  OrderStatusLine objStatusTimeline=new OrderStatusLine();
                  if((objTimelineId=(BigDecimal) objDatabaseRecord.get(PIXConstants.TIMELINE_ID))!= null)
                  {
        			  objTimelineIdInt=new Integer(objTimelineId.intValue());
                	  objStatusTimeline.setTimelineId(objTimelineIdInt);
                  }
        		  objStatusTimeline.setTimelineDescription((String) objDatabaseRecord.get(PIXConstants.TIMELINE));
        		  objVectorDisplayList.add(objStatusTimeline);
              }
        	   
            }
          else if(objVectorDisplay.size()== 0 || objVectorDisplay == null)
          {
       	     	 AppException appException=new AppException();
       	     	 log.debug("there is no description for Timeline ");
 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_STATUS,"OrderStatusDAOImpl");  
 				 throw appException;
          }
 
       }catch(TopLinkException e)
       {
    	   AppException appException = new AppException();
    	   log.debug("TopLinkException for Timeline ");
    	   appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,getOrderStatusDetail",e);
    	   throw appException;
       }
       finally
	   {
		  if(objclientSession!=null)
		   {
			  objclientSession.release();
		   }
	   }
	return objVectorDisplayList;
   }
   
   
   /**
    * gets the StatusDescription information from the database
    * @return java.util.Vector
    */
   private Vector getStatusDesc() throws AppException
   {
	   Vector objVectorDisplayList=new Vector();
	   Session objClientSession=null;
	   Record objDatabaseRecord=null;
	   BigDecimal objStatusId=null;
	   Integer objStatusIdInt=null;
	   try
       {
       	 
    	   objClientSession  = getClientSession();
    	   Vector objVectorDisplay=null; 
    	   objVectorDisplay = objClientSession.executeSelectingCall(new SQLCall("select /* +FIRST_ROWS*/ psc.status_id,psc.status_description from pix_status_code psc, pix_table pt "+
        		              "where psc.table_id = pt.table_id and pt.table_name = 'PIX_ORDER_STATUS' and psc.active_flag='Y'"));
    	   
      	   if (objVectorDisplay != null && objVectorDisplay.size()>0)
           {
        	   for (int i = 0; i < objVectorDisplay.size(); i++)
               {
        		  objDatabaseRecord = (Record)objVectorDisplay.get(i);
        		  OrderStatusLine objOrderStatus=new OrderStatusLine();
        		  
        		  if((objStatusId=(BigDecimal)objDatabaseRecord.get(PIXConstants.STATUS_ID))!= null)
                  {
        			  objStatusIdInt=new Integer(objStatusId.intValue());
        			  objOrderStatus.setStatusId(objStatusIdInt);
                  }
        		  objOrderStatus.setStatusDescription((String) objDatabaseRecord.get(PIXConstants.STATUS_DESC));
        		  objVectorDisplayList.add(objOrderStatus);
              }
           }
           else if(objVectorDisplay.size()== 0 || objVectorDisplay == null)
           {
       	     AppException appException=new AppException();
       	     log.debug("no records found for status Description dropdown ");
 			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_STATUS,"OrderStatusDAOImpl");  
 			 throw appException;
           }
 
       }catch(TopLinkException e)
       {
        AppException appException = new AppException();
        log.debug("TopLinkException status Description dropdown ");
        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,getOrderStatusDetail",e);
        
        throw appException;
       }
       finally
	   {
		   if(objClientSession!=null)
		   {
			  objClientSession.release();
		   }
	   } 
    return objVectorDisplayList;
   }
   
 
   
   /**
    * Saves the Order Status information into the database
    * @param orderStatus
    * @param poId
    * @param poVersion
    * @param poNo
    * @param rNo
    * @param login
    * @return java.lang.String
    */
   public String insertOrderStatus(OrderStatus objOrderStatus,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String login) throws AppException
   {
	   Session objclientSession=null;
	   Record objRecord=null;
	   String objOrderStatusNo=null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   PreparedStatement stmtHeader = null;
	   PreparedStatement stmtLine = null;
	   
	   Vector objVec= (Vector)objOrderStatus.getLineItemCollection();
	   int size=objVec.size();
	         
	   try
       {    
		   transaction = getUserTransaction();
		   conn = getDataSourceConnection(); 
		   
		   log.info("inserting Order Status Header ");
		   // inserting Order Status Header 
		   stmtHeader = conn.prepareStatement("INSERT INTO PIX_ORDER_STATUS_HEADER(ORDER_STATUS_ID,STATUS_NO,PO_ID,PO_VERSION," +
          		"CREATED_BY,MODIFIED_BY,CREATION_DATE_TIME,MOD_DATE_TIME,TRANSACTION_HISTORY_NO)" +
               " VALUES(seq_PIX_ORDER_STATUS_ID.nextval,('OS_'||?||'_'||?||'_'|| seq_PIX_ORDER_STATUS_ID.currval),?," +
               " ?,?,?,SYSDATE,SYSDATE,null)");
		   stmtHeader.setObject(1, objPoNo);
		   stmtHeader.setObject(2, objRno);
		   stmtHeader.setObject(3, objPoId);
		   stmtHeader.setObject(4, objPoVersion);
		   stmtHeader.setObject(5, login);
		   stmtHeader.setObject(6, login);
		   
		   boolean header=  stmtHeader.execute();
		   log.debug("inserted Order Status Header  : "+header);
		   		   
		   log.info("inserting Order Status Line Items ");
		   	//inserting Order Status Line Items
       for(int i=0; i<size; i++)
	   {
    	   stmtLine = conn.prepareStatement("INSERT INTO PIX_ORDER_STATUS(ORDER_STATUS_ID,PO_ID,PO_VERSION,PO_LINE_NO," +
		    	 " TIMELINE_ID,CREATED_BY,MODIFIED_BY,TIMELINE_DATE,ESTIMATED_DATE,STATUS_ID,COMMENTS) VALUES(seq_PIX_ORDER_STATUS_ID.currval," +
		    	 " ?,?,?,?,?,?,to_date(?,'MM/DD/YYYY'),to_date(?,'MM/DD/YYYY'),?,?)");
    	   
    	   stmtLine.clearParameters();
		   stmtLine.setObject(1,objPoId);
		   stmtLine.setObject(2,objPoVersion);
		   stmtLine.setObject(3,objOrderStatus.getLineItemCollectionIdx(i).getPoLineNo());
		   stmtLine.setObject(4,objOrderStatus.getLineItemCollectionIdx(i).getTimelineId());
		   stmtLine.setObject(5,login);
		   stmtLine.setObject(6,login);
		   stmtLine.setObject(7,objOrderStatus.getLineItemCollectionIdx(i).getTimelineDate());
		   stmtLine.setObject(8,objOrderStatus.getLineItemCollectionIdx(i).getEstimatedDate());
		   int checkStatus=objOrderStatus.getLineItemCollectionIdx(i).getStatusId().intValue();
		   if(checkStatus != 0) {
			   stmtLine.setObject(9,objOrderStatus.getLineItemCollectionIdx(i).getStatusId());
		   }
		   else{
			   stmtLine.setObject(9,null);
		   }
		   stmtLine.setObject(10,objOrderStatus.getLineItemCollectionIdx(i).getComments());
		   
		   boolean line=  stmtLine.execute();
		   log.debug("inserted into PIX_ORDER_STATUS ..."+line);
		       	   
       }   
       // getting for inserted order status number
       objclientSession = getClientSession();
       Vector objOsNo = (Vector)objclientSession.executeSelectingCall(new SQLCall("Select ('OS_'||'"+objPoNo+"'||'_'||"+objRno+"||'_'||seq_PIX_ORDER_STATUS_ID.currval) AS ORDER_STATUS_NO from dual"));
	   if (objOsNo != null && objOsNo.size()>0)
       {
      	 for (int i = 0; i < objOsNo.size(); i++)
    	 {
       	    objRecord = (Record)objOsNo.get(i);
       	   	objOrderStatusNo=(String)objRecord.get(PIXConstants.ORDER_STATUS_NO);
       	 }
      }	   
	return objOrderStatusNo;
                 
     }catch(TopLinkException e)
     {
        AppException appException = new AppException();
        log.debug("TopLinkException occerud in while inserting OrderStatus ");
        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,insertOrderStatus",e);
        try 
		   { 
			   transaction.setRollbackOnly();
			   log.debug("After setRollbackOnly method call ");
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
		   log.debug("SQLException occerud in while inserting OrderStatus ");
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"OrderStatusDAOImpl,insertOrderStatus",se);
		   try 
		   { 
			   transaction.setRollbackOnly();
			   log.debug("After setRollbackOnly method call ");
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
    		   if(stmtHeader!=null)
				   stmtHeader.close();
			   if(stmtLine!=null)
				   stmtLine.close();
			   if(conn!=null)
				   conn.close();
			   if(objclientSession!=null)
			   {
				   objclientSession.release();
			   }
		   } 
		   catch(Throwable tu) 
		   { 
			   throw new RuntimeException(tu); 
		   }
        }
	}
}
