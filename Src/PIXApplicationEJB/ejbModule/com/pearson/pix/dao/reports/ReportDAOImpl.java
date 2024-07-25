package com.pearson.pix.dao.reports;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.dto.reports.ReportPixHistory;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink
 */
public class ReportDAOImpl extends BaseDAO implements ReportDAO 
{
	/**
	     * Logger.
	     */
		
		private static Log log = LogFactory.getLog(ReportDAOImpl.class.getName());
   /**
    * Constructor for initializing ReportDAOImpl
    */
   public ReportDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Report Search Criteria
    * @return java.util.Collection
    */
   public Collection getSearchCriteria() throws AppException
   {
 	   
	   Vector vectorModuleDescription=new Vector();
 	   Session clientSession = null;
 	   Reference objReference=null;
 	   Vector  vectorModuleName=new Vector();
 	   try
	 	 {
	 		   clientSession = getClientSession();
		  	   Record objRecord=null;
		  	   //the following query is used to get the search criteria in the report search page
		  	   vectorModuleName= clientSession.executeSelectingCall(new SQLCall("select ref_code,(decode" +
		  	   		"(description,'Purchase Orders','Order Detail',description))" +
		  	   		" description from PIX_REF where GROUP_CODE='MODULE' and ref_code not in ('ADM','OCO','REP','INV')" +
		  	   		" order by description"));
		  	 
		 	   if (vectorModuleName != null && vectorModuleName.size()>0)//to check the null value of the vector returned by the above query
		       {
		 		   int moduleName=vectorModuleName.size();
		      	   for (int i = 0;i < moduleName;i++)// to iterate values from the vector
		      	   {
		      		 objReference=new Reference();
		      		 objRecord =(Record)vectorModuleName.get(i);
		      		 objReference.setDescription((String)objRecord.get(PIXConstants.DESCRIPTION));
		      		 objReference.setRefCode((String)objRecord.get(PIXConstants.REF_CODE));
		      		 vectorModuleDescription.add(objReference);
		      		 
		      	   }
		       }
		 	   else if(vectorModuleName.size()== 0 || vectorModuleName == null)
		 	   {
	       	     	 AppException appException=new AppException();
	       	     	 log.info("log info  :no records ");
	 				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS,"ReporDAOImpl");  
	 				 throw appException;
		 	   }
	   }
 	   catch(TopLinkException e)
 	   {
		   	 AppException ae = new AppException();
		   	 ae.performErrorAction("Exceptions.SQL_EXCEPTION","ReportDAOImpl,getSearchCriteria",e);
		   	 throw ae;
 	   }
 	   finally
 	   {
		    if(clientSession!=null)
		    {
		    	clientSession.release();
		    }
 	   }
 	   return vectorModuleDescription;
 	   
   }
  
   
   /**
    * Gets the List of Report from database
    * @return java.util.Collection
    */
   public Collection getReportList(String isbn,String pOrderNo,String printNo,int userId,int pagination,String itemType,String orderBy,String sort,String sdate,String edate)throws AppException
   {
	Session objSession = null;
	Vector objListVector = new Vector();
	try
	{
	//the following code is used to set the method parameters into the procedure  and executing the procedurde	
    Report objReport=null;
    objSession = getClientSession();
   
	StoredProcedureCall call = new StoredProcedureCall(); 
	System.out.println("isbn:"+isbn+":pOrderNo:"+pOrderNo+":printNo:"+printNo+":itemType:"+itemType+":userId:"+userId+":orderBy:"+orderBy+":sort:"+sort+":sdate:"+sdate+":edate:"+edate);
    call.setProcedureName("Pix_Report_Proc");
	call.addNamedArgumentValue("i_isbn",isbn);
	call.addNamedArgumentValue("i_po_no",pOrderNo);
	call.addNamedArgumentValue("i_print_no",printNo);
	call.addNamedArgumentValue("i_item_type",itemType);
	call.addNamedArgumentValue("i_user_id",new Integer(userId));
	call.addNamedArgumentValue("i_pagination",new Integer(pagination));
	call.addNamedArgumentValue("i_order_by",orderBy);
	call.addNamedArgumentValue("i_sort",sort);
	call.addNamedArgumentValue("i_start_date",sdate);
	call.addNamedArgumentValue("i_end_date",edate);
	call.useNamedCursorOutputAsResultSet("list_refcursor");
	Vector objVector = (Vector)objSession.executeSelectingCall(call);
	objSession.logMessages();
	if (objVector.size()>0 && objVector != null)//to check the null values of the vector
    {
	     int size=objVector.size();
		 for (int i = 0; i < size; i++)//to iterate values from the vector
		 {  
			 Record objDatabaseRecord = (Record)objVector.get(i);
			 System.out.println(objDatabaseRecord);
			 objReport=new Report();
			 objReport.setPONo((String)objDatabaseRecord.get(PIXConstants.PO_NO));
			 objReport.setItemName((String)objDatabaseRecord.get(PIXConstants.ITEM_TYPE));
			 objReport.setItemNo(objDatabaseRecord.get(PIXConstants.ITEM_NUMBER).toString());
			 objReport.setVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.VERSION_NUMBER));
			 objReport.setPostedDate((Date)objDatabaseRecord.get(PIXConstants.POSTED_DATE));
			 objReport.setPostedBy((String)objDatabaseRecord.get(PIXConstants.POSTED_BY));
			 objReport.setVendor((String)objDatabaseRecord.get(PIXConstants.VENDOR_NAME));
			 objReport.setId((BigDecimal)objDatabaseRecord.get(PIXConstants.ID));
			 objReport.setPoid((BigDecimal)objDatabaseRecord.get(PIXConstants.POID));
			 objReport.setIsbn((String)objDatabaseRecord.get(PIXConstants.ISBN_NUMBER));
			 if(objDatabaseRecord.get(PIXConstants.PRINT_NUM)!=null)
			 {
				 objReport.setPrintNo(objDatabaseRecord.get(PIXConstants.PRINT_NUM).toString());
			 }
			 
			 objListVector.add(objReport);
		 }
	 }
	else if(objVector.size()== 0 || objVector == null)
    {
	   AppException appException=new AppException();
	   appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_REPORT,"ReportDAOImpl");  
	   throw appException;
    }
	   
   }
	catch(TopLinkException e)
	 {
		   	   AppException appException = new AppException();
		   	   appException.performErrorAction(Exceptions.SQL_EXCEPTION, "ReportDAOImpl,getReportList",e);
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
   
   
   /** Added By Sujeet Kumar -- 26 Nov 2009
    * Gets the List of ReportPixHistory from database
    * @return java.util.Collection
    */
   public Collection getPixReportList(String isbn,String printNo,int userId)throws AppException{

		Session objSession = null;
		Vector objListVector = new Vector();
		try
		{
		//the following code is used to set the method parameters into the procedure  and executing the procedurde	
		ReportPixHistory objOrderHistory=null;
	    objSession = getClientSession();
		StoredProcedureCall call = new StoredProcedureCall(); 
	    call.setProcedureName("Pix_Report_ces_Proc");
		call.addNamedArgumentValue("i_isbn",isbn);
		call.addNamedArgumentValue("i_print_no",printNo);
		call.addNamedArgumentValue("i_user_id",new Integer(userId));
		call.useNamedCursorOutputAsResultSet("list_refcursor");
		Vector objVector = (Vector)objSession.executeSelectingCall(call);
		if (objVector.size()>0 && objVector != null)//to check the null values of the vector
	    {
		     int size=objVector.size();
			 for (int i = 0; i < size; i++)//to iterate values from the vector
			 {  
				 Record objDatabaseRecord = (Record)objVector.get(i);
				 objOrderHistory=new ReportPixHistory();
				 
				 objOrderHistory.setTransactionType((String)objDatabaseRecord.get(PIXConstants.TRANSACTION_TYPE));
				 objOrderHistory.setTransactionDate((Date)objDatabaseRecord.get(PIXConstants.TRANSACTION_DATE));
				 objOrderHistory.setIsbn((String)objDatabaseRecord.get(PIXConstants.ISBN_NUMBER));
				 objOrderHistory.setPrintNo((BigDecimal)objDatabaseRecord.get(PIXConstants.PRINT_NUM));
				 objOrderHistory.setPoNo((String)objDatabaseRecord.get(PIXConstants.PO_NO));
				
				 if(objDatabaseRecord.get(PIXConstants.BOOK_BOUND_DATE) != null){
					 
				 objOrderHistory.setBbd((Date)objDatabaseRecord.get(PIXConstants.BOOK_BOUND_DATE));
				 }
				 if(objDatabaseRecord.get(PIXConstants.QUANTITY) != null){
					 
					 objOrderHistory.setQty(new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.QUANTITY)).intValue()));
				 }
				 if(objDatabaseRecord.get(PIXConstants.VENDOR_NAME) != null){
					 objOrderHistory.setVendor((String)objDatabaseRecord.get(PIXConstants.VENDOR_NAME));
				 }
				 if(objDatabaseRecord.get(PIXConstants.VENDOR_DATE) != null){
				 objOrderHistory.setVendorDate((Date)objDatabaseRecord.get(PIXConstants.VENDOR_DATE));
				 }
				 if(objDatabaseRecord.get(PIXConstants.DELIVERED_DATE) != null){
					 objOrderHistory.setDeliveryDate((Date)objDatabaseRecord.get(PIXConstants.DELIVERED_DATE)); 
				 }
				
				 if(objDatabaseRecord.get(PIXConstants.PRESS_DATE) != null){
					 objOrderHistory.setPressDate((Date)objDatabaseRecord.get(PIXConstants.PRESS_DATE));
				 }
				 if(objDatabaseRecord.get(PIXConstants.ARRIVAL_DATE) != null){
					 objOrderHistory.setArrivalDate((Date)objDatabaseRecord.get(PIXConstants.ARRIVAL_DATE)); 
				 }
				 
				 objOrderHistory.setComments((String)objDatabaseRecord.get(PIXConstants.COMMENTS));
				 objOrderHistory.setTransactionId((BigDecimal)objDatabaseRecord.get(PIXConstants.TRANSACTION_ID));
				 
				 if(objDatabaseRecord.get(PIXConstants.PRINT_NUM)!=null)
				 {
					 objOrderHistory.setPrintNo((BigDecimal)objDatabaseRecord.get(PIXConstants.PRINT_NUM));
				 }
				 
				 objListVector.add(objOrderHistory);
			 }
		 }
		else if(objVector.size()== 0 || objVector == null)
	    {
		   AppException appException=new AppException();
		   appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_REPORT,"ReportDAOImpl");  
		   throw appException;
	    }
		   
	   }
		catch(TopLinkException e)
		 {
			   	   AppException appException = new AppException();
			   	   appException.performErrorAction(Exceptions.SQL_EXCEPTION, "ReportDAOImpl,getPixReportList",e);
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
   
}
