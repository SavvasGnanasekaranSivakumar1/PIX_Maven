package com.pearson.pix.dao.usage;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.common.UOM;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.dto.usage.UsageLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink
 * @author Dandu Thirupathi
 */
public class UsageDAOImpl extends BaseDAO implements UsageDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(UsageDAOImpl.class.getName());
	
   /**
    * Constructor for initializing UsageDAOImpl
    */
   public UsageDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Collection of Usage List information from database
    * 
    * @param Usage
    * @param startDate
    * @param endDate
    * @param poNo
    * @param userId
    * @param page
    * @param pagination
    * @param orderby
    * @param sort
    * @return java.util.Collection
    */
   public Collection getUsageList(Usage objUsage,String startDate,String endDate,String poNo,int userId,String page,int pagination,String orderby,String sort) throws AppException  
   {
	   Session objSession = null;
	   Vector objListVector = new Vector();
	   Integer rno =null;
	   BigDecimal rnobig=null;
	   Integer statusId;
	   String objIsbnNo=null;
	   String objPrintNo=null;	
	   Integer poId=null;
	   
	   	try
	   	{	
		   
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   if(page.equals("C"))
		   { 
			   call.setProcedureName("Pix_usage_Proc");
		   }
		   else if(page.equals("H"))
		   {
			   call.setProcedureName("Pix_usage_hist_Proc");
		   }
		   call.addNamedArgumentValue("i_po_no",poNo);
		   call.addNamedArgumentValue("i_po_id",objUsage.getPoId());
		   call.addNamedArgumentValue("i_usage_no",null);
		   if(objUsage.getTitleDetail().getIsbn10()!=null){
			   objIsbnNo=objUsage.getTitleDetail().getIsbn10();
		   }
		   call.addNamedArgumentValue("i_isbn",objIsbnNo);
		   if(objUsage.getTitleDetail().getPrintNo()!=null){
			   objPrintNo=objUsage.getTitleDetail().getPrintNo();
		   }
		   call.addNamedArgumentValue("i_print_number",objPrintNo);
		   if(objUsage.getStatusDetail().getStatusId()!=null)
		   {
			   if(objUsage.getStatusDetail().getStatusId().intValue()==0)
			   {
				   statusId =null;
			   }
			   else
			   {
				   statusId = objUsage.getStatusDetail().getStatusId();
			   }
		   }
		   else
		   {
			   statusId = null;
		   }
		   call.addNamedArgumentValue("i_status_id",statusId);
		   call.addNamedArgumentValue("i_start_date",startDate);
		   call.addNamedArgumentValue("i_end_date",endDate);
		   call.addNamedArgumentValue("i_user_id", new Integer(userId));
		   call.addNamedArgumentValue("i_page",page);
           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
           call.addNamedArgumentValue("i_order_by",orderby);
           call.addNamedArgumentValue("i_sort",sort); 
           call.useNamedCursorOutputAsResultSet("list_refcursor");
           
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
                     
           if (objVector != null && objVector.size()> 0)
           {
          	   for (int i = 0; i < objVector.size(); i++)
        	   {
          		   Record objDatabaseRecord = (Record)objVector.get(i);
          		   Usage objUsageList=new Usage();
        		   Status objStatus=new Status();
        		   POParty objParty=new POParty();
                   TitlePrinting objTitlePrinting=new TitlePrinting();
                   
                   objUsageList.setPoNo(((String)objDatabaseRecord.get(PIXConstants.PO_NO)).trim());
                   objUsageList.setPoId((BigDecimal)objDatabaseRecord.get(PIXConstants.POID));
                   objUsageList.setPoVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.POVERSION));
                   objUsageList.setUsageId((BigDecimal)objDatabaseRecord.get(PIXConstants.USAGE_ID));
                   objUsageList.setUsageNo((String)objDatabaseRecord.get(PIXConstants.USAGE_NO));
                   	rnobig =(BigDecimal)objDatabaseRecord.get(PIXConstants.RELEASE_NO);
                   	rno=new Integer(rnobig.toString());
                   objUsageList.setReleaseNo(rno);
                   objTitlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.ISBN_NO));
                   objTitlePrinting.setPrintNo(objDatabaseRecord.get(PIXConstants.PRINT_NUMBER).toString());
                   objUsageList.setIssueDate((Date)objDatabaseRecord.get(PIXConstants.POSTED_DATE));
                   objParty.setName1((String)objDatabaseRecord.get(PIXConstants.NAME_1));
                   objParty.setName2((String)objDatabaseRecord.get(PIXConstants.NAME_2));
                   objParty.setAddress1((String)objDatabaseRecord.get(PIXConstants.VENDOR_CONTACT));
       		       objStatus.setStatusDescription((String)objDatabaseRecord.get(PIXConstants.STATUS));
                   objUsageList.setTitleDetail(objTitlePrinting);
                   objUsageList.setStatusDetail(objStatus);
                   Vector objpartyvector=new Vector();
	               objpartyvector.add(objParty);
	               objUsageList.setPartyCollection(objpartyvector);
	               objListVector.add(objUsageList);
	           }
           }
           else if(objVector.size()== 0 || objVector == null)
           {
        	 log.debug("there is no Usages for given poNo="+poNo+",IsbnNo="+objIsbnNo+"PrintNo="+objPrintNo+",pagination="+pagination+",orderBy="+orderby+"and sort="+sort);
       	     AppException appException=new AppException();
   			 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_USAGE,"UsageDAOImpl");  
   			 throw appException;
           }
	   	 }
         catch(TopLinkException te)
	     {
        	 log.debug("TopLinkException for getUsageList ");
        	 AppException appException = new AppException();
        	 appException.performErrorAction(Exceptions.SQL_EXCEPTION,"UsageDAOImpl,getUsageList",te);
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
    * Gets the Usage Status List
    * @return java.util.Collection
    */
   public Collection displayUsageStatus() throws AppException
   {
	   Session clientSession = null;
	   Collection usageStatus=null;
	   try
	   {
		   clientSession = getClientSession();
	       ExpressionBuilder builder = new ExpressionBuilder();
	       
	       Expression wcPOStatus = builder.get("tableDetail").get("tableName").equal(
				   PIXConstants.PIX_PO_HEADER).and(builder.get("activeFlag").equal("Y"));
	       
	       ReadAllQuery query = new ReadAllQuery(Status.class);
	       query.setSelectionCriteria(wcPOStatus);
	       query.addAscendingOrdering("statusDescription");
	       usageStatus = (Vector)clientSession.executeQuery(query);
		 	 
	   }
	   catch(TopLinkException te)
	   {
		   log.debug("TopLinkException for displayUsageStatus ");
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"UsageDAOImpl,displayUsageStatus",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	return usageStatus;
   }   
   
   /**
    * Gets the Usage Detail information
    * 
    * @param poId
    * @param poVersion
    * @param usageId
    * @return java.util.HashMap
    */
   public HashMap getUsageDetail(Integer poId, Integer poVersion, Integer usageId) throws AppException
   {
	   Session clientSession = null;
	   POHeader objPOHeader=null;
	   Usage objUsage=null;
	   HashMap usageHashMap=null;
	   Vector objVectorDisplayLine=null;
	   Record objDatabaseRecord=null;
	   Vector objVectorDisplay=null; 
	   UsageLine objUsageLine=null;
	   UOM objUom=null;
	      	   	   
	   try
	   {
		   usageHashMap = new HashMap();
		   objVectorDisplayLine= new Vector();
		   clientSession = getClientSession();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Expression wcUsage = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("activeFlag").equal("Y")).and(builder.get("orderType").equal("S"));
		   objPOHeader = (POHeader)clientSession.readObject(POHeader.class,wcUsage);
		   	   
		   clientSession = (Session)getClientSession();
		    	  
		   objVectorDisplay = clientSession.executeSelectingCall(new SQLCall("SELECT /*+FIRST_ROWS*/ usgmst.PO_ID,usgmst.LINE_ITEM_NO,usgmst.PRODUCT_CODE, "+
	        		 " usgmst.PRODUCT_DESCRIPTION,usgmst.COMPONENT_TYPE,usgmst.PARENT_PRODUCT_CODE,usgmst.PARENT_PRODUCT_DESC," +
	        		 " usglist.usage_id,usglist.usage_no,usglist.USAGE_QUANTITY,usglist.DAMAGED_QUANTITY,usgmst.uom_id,usglist.COMMENTS" +
	        		 " FROM (SELECT /*+FIRST_ROWS*/ PPU.PO_ID,PPU.LINE_ITEM_NO,PPU.PRODUCT_CODE,PPU.PRODUCT_DESCRIPTION,PPU.COMPONENT_TYPE," +
	        		 " PPU.PARENT_PRODUCT_CODE,PPU.PARENT_PRODUCT_DESC,PPU.UOM_ID FROM PIX_PO_USAGE PPU WHERE PPU.PO_ID =" + poId + ") usgmst, " +
	        		 " (SELECT /*+FIRST_ROWS*/ PU.PO_ID,PU.usage_id,PU.usage_no,PUL.USAGE_QUANTITY,PUL.DAMAGED_QUANTITY,PUL.COMMENTS,PUL.LINE_ITEM_NO " +
	        		 " FROM PIX_USAGE PU,PIX_USAGE_LINE PUL  WHERE PU.USAGE_ID = PUL.USAGE_ID AND PU.PO_ID = " + poId + " " +
	        		 " AND PU.Usage_id = NVL(" + usageId + ", PU.Usage_id)) usglist WHERE usgmst.po_id = usglist.po_id(+) AND " +
	        		 " usgmst.line_item_no = usglist.line_item_no(+) ORDER BY usgmst.LINE_ITEM_NO "));
		  		     
		  objUom = new UOM();
		  objUsage=new Usage();
		  
		  if (objVectorDisplay != null && objVectorDisplay.size()>0)
		  {
		     for (int i = 0; i < objVectorDisplay.size(); i++)
		     {
		       objUsageLine=new UsageLine();
		       		       
		       objDatabaseRecord = (Record)objVectorDisplay.get(i);
		       objUsage.setUsageId((BigDecimal)objDatabaseRecord.get(PIXConstants.USAGE_ID));
		       objUsage.setUsageNo((String)objDatabaseRecord.get(PIXConstants.USAGE_NO));
		       objUsageLine.setPoId((BigDecimal)objDatabaseRecord.get(PIXConstants.POID));
		       objUsageLine.setUsageLineNo((BigDecimal)objDatabaseRecord.get(PIXConstants.LINE_ITEM_NO));
		       objUsageLine.setProductCode((String)objDatabaseRecord.get(PIXConstants.PRODUCT_CODE));
		       objUsageLine.setProductDescription((String)objDatabaseRecord.get(PIXConstants.PRODUCT_DESCRIPTION));
		       objUsageLine.setProductClassifCode((String)objDatabaseRecord.get(PIXConstants.PARENT_PRODUCT_CODE));
		       objUsageLine.setProductClassifDescription((String)objDatabaseRecord.get(PIXConstants.PARENT_PRODUCT_DESC));
		       if(objDatabaseRecord.get(PIXConstants.USAGE_QUANTITY)!=null){
		       objUsageLine.setUsageQuantity(new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.USAGE_QUANTITY)).intValue()));
		       }
		       if(objDatabaseRecord.get(PIXConstants.DAMAGED_QUANTITY)!=null){
		       objUsageLine.setDamagedQuantity(new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.DAMAGED_QUANTITY)).intValue()));
		       }
		       if(objDatabaseRecord.get(PIXConstants.COMMENTS)!=null){
		       objUsageLine.setComments((String)objDatabaseRecord.get(PIXConstants.COMMENTS));
		       }
		       if(objDatabaseRecord.get(PIXConstants.UOM_ID)!=null){
		       objUom.setUomId((BigDecimal)objDatabaseRecord.get(PIXConstants.UOM_ID));
		        }
		       
		         objVectorDisplayLine.add(objUsageLine);
		        
		    }
		          	   
		  }
		  else if(objVectorDisplay.size()== 0 || objVectorDisplay == null)
		  {
			  log.debug("there is no Usage Detail for given poId="+poId+",poVersion="+poVersion+"usageId="+usageId);
		   	 AppException appException=new AppException();
		   	 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_USAGE,"OrderStatusDAOImpl,getUsageDetail");  
		 	 throw appException;
		  }
		   objUsage.setUsageLineCollection(objVectorDisplayLine);
		   objUsage.setUOMDetail(objUom);
		   usageHashMap.put("PoHeader",objPOHeader);
		   usageHashMap.put("UsageLineList",objUsage);
		   
	   }catch(TopLinkException te)
	   {
		   log.debug("TopLinkException for getUsageDetail ");
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"UsageDAOImpl,getUsageDetail",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return usageHashMap;
	   
   }
   
   /**
    * Saves the Usage information into the database
    *
    * @param Usage
    * @param poId
    * @param PoVersion
    * @param PoNo
    * @param Rno
    * @param login
    * @return java.lang.String
    */
   public String insertUsage(Usage objUsage,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String login)  throws AppException 
   {
	   
	   Session objclientSession=null;
  	   
	   Vector objVec= (Vector)objUsage.getUsageLineCollection();
	   int size=objVec.size();
	   Record objRecord=null;
	   String usageNo=null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   BigDecimal sId=null;
	   PreparedStatement stmtHeader = null;
	   PreparedStatement stmtLine = null;
	   int sizeStatus=0;
	   try
       { 
		   transaction = getUserTransaction();
		   conn = getDataSourceConnection(); 
		   objclientSession = getClientSession();
		   Vector objStatusVector= objclientSession.executeSelectingCall(new SQLCall("SELECT DECODE(COUNT(1),0, " +
		   		" pix_get_status_id_func('ORIGINAL','PIX_USAGE'),pix_get_status_id_func('REPLACED','PIX_USAGE')) " +
		   		" AS STATUS_ID FROM PIX_USAGE WHERE PO_ID="+objPoId+""));
		   if(objStatusVector != null)
		   {
			   sizeStatus=objStatusVector.size();
			   for (int i = 0; i < sizeStatus; i++)
       	       {
          		  objRecord = (Record)objStatusVector.get(i);
          		  sId = (BigDecimal)objRecord.get("STATUS_ID");
          	   }
		   }
		   stmtHeader = conn.prepareStatement("INSERT INTO PIX_USAGE(USAGE_ID,USAGE_NO,STATUS_ID,PO_ID," +
          	   " PO_VERSION,TRANSACTION_HISTORY_NO,CREATED_BY,MODIFIED_BY,CREATION_DATE_TIME,MOD_DATE_TIME)" +
               " VALUES(seq_PIX_USAGE_id.nextval,('UN_'||?||'_'||?||'_'||seq_PIX_USAGE_id.CURRVAL)," +
               " ?,?,?,?,?,?,SYSDATE,SYSDATE)");
		   stmtHeader.setObject(1, objPoNo);
		   stmtHeader.setObject(2, objRno);
		   stmtHeader.setObject(3, sId);
		   stmtHeader.setObject(4, objPoId);
		   stmtHeader.setObject(5, objPoVersion);
		   stmtHeader.setObject(6, objUsage.getUsageId());
		   stmtHeader.setObject(7, login);
		   stmtHeader.setObject(8, login);
		   boolean header=  stmtHeader.execute();
		 		   
		   log.debug("inserted into PIX_USAGE ..."+header);
		   for(int i=0; i<size; i++)
		   {
			   stmtLine = conn.prepareStatement("INSERT INTO PIX_USAGE_LINE(USAGE_ID,STATUS_ID,USAGE_QUANTITY,DAMAGED_QUANTITY,UOM_ID," +
    			 " COMMENTS,PO_ID,LINE_ITEM_NO, CREATED_BY, MODIFIED_BY ,CREATION_DATE_TIME, MOD_DATE_TIME ) " +
    	  		 " VALUES(seq_PIX_USAGE_id.currval,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE)");
			   
    	   	   stmtLine.clearParameters();
			   stmtLine.setObject(1,sId);
			   stmtLine.setObject(2,objUsage.getUsageLineItemCollectionIdx(i).getUsageQuantity());
			   stmtLine.setObject(3,objUsage.getUsageLineItemCollectionIdx(i).getDamagedQuantity());
			   stmtLine.setObject(4,objUsage.getUOMDetail().getUomId());
			   stmtLine.setObject(5,objUsage.getUsageLineItemCollectionIdx(i).getComments());
			   stmtLine.setObject(6,objPoId);
			   stmtLine.setObject(7,objUsage.getUsageLineItemCollectionIdx(i).getUsageLineNo());
			   stmtLine.setObject(8,login);
			   stmtLine.setObject(9,login);
			   boolean line=  stmtLine.execute();
			   log.debug("inserted into PIX_USAGE_LINE ..."+line);
		   }
       
       
       Vector usage_no = (Vector)objclientSession.executeSelectingCall(new SQLCall("Select ('UN_'||'"+objPoNo+"'||'_'||"+objRno+"||'_'||seq_PIX_USAGE_id.CURRVAL) AS USAGE_NO from dual"));
	   if (usage_no != null && usage_no.size()>0)
       {
      	 for (int i = 0; i < usage_no.size(); i++)
    	 {
       	    objRecord = (Record)usage_no.get(i);
    		objUsage = new Usage();
    		objUsage.setUsageNo((String)objRecord.get(PIXConstants.USAGE_NO));
    	}
      }	   
	     usageNo = objUsage.getUsageNo();
     return usageNo;
	     
       }catch(TopLinkException e)
       {
    	  log.debug("TopLinkException for insertUsage ");
          AppException appException = new AppException();
          appException.performErrorAction(Exceptions.SQL_EXCEPTION,"UsageDAOImpl,insertUsage",e);
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
    	   log.debug("SQLException for insertUsage ");
  		   AppException ae = new AppException();
  		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"UsageDAOImpl,insertUsage",se);
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
