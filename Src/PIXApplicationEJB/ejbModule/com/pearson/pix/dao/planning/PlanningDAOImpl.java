//Source file: E:\\PIXProject\\PIXApplicationEJB\\src\\com\\pearson\\pix\\dao\\planning\\PlanningDAOImpl.java

package com.pearson.pix.dao.planning;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.transaction.UserTransaction;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink.
 * @author faisalk
 */
public class PlanningDAOImpl extends BaseDAO implements PlanningDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PlanningDAOImpl.class.getName());
   
   /**
    * Constructor for initializing PlanningDAO
    */
   public PlanningDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Collection of Planning List information. This method also takes care 
    * of filtering and pagination display by passing page no, filter criteria and its 
    * value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPlanningsList(POHeader objpoHeader,String startDate,String endDate,int userId,String page,int pagination,String orderby,String sort,String sbBDate,String ebBDate,String roleType) throws AppException 
   {
	   Session objSession = null;
	   Vector objListVector = new Vector();
	   String deliveryDate2=null;
	   Date deliveryDate1=null;
	   try
	   {	
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   if(page.equals("C")||page.equals("I"))
		   {
			   
			   if("M".equals(roleType)){
				   
				   call.setProcedureName("Pix_Paper_Plan_Proc");  //Setting the procedure name and providing arguments
				   call.addNamedArgumentValue("i_po_no",objpoHeader.getPoNo());
				   call.addNamedArgumentValue("i_po_id ",objpoHeader.getPoId());
				   call.addNamedArgumentValue("i_material_no",objpoHeader.getTitleDetail().getIsbn10());
				   call.addNamedArgumentValue("i_paper_grade",objpoHeader.getTitleDetail().getPrintNo());
				   call.addNamedArgumentValue("i_status_id",objpoHeader.getStatusDetail().getStatusDescription());
				   call.addNamedArgumentValue("i_order_type","P"); 
				   call.addNamedArgumentValue("i_user_id",new Integer(userId));
				   call.addNamedArgumentValue("i_page",page);
				   call.addNamedArgumentValue("i_pagination",new Integer(pagination));
				   call.addNamedArgumentValue("i_order_by",orderby);
				   call.addNamedArgumentValue("i_sort",sort); 
				   call.addNamedArgumentValue("i_start_date",startDate);
				   call.addNamedArgumentValue("i_end_date",endDate);
				   call.addNamedArgumentValue("i_plan_start_date",sbBDate);
		           call.addNamedArgumentValue("i_plan_end_date",ebBDate);
				   
			   }else{
			   call.setProcedureName("Pix_planning_Proc");  
			   call.addNamedArgumentValue("i_po_no ",objpoHeader.getPoNo());
			   call.addNamedArgumentValue("i_po_id ",objpoHeader.getPoId());
			   call.addNamedArgumentValue("i_isbn",objpoHeader.getTitleDetail().getIsbn10());
			   call.addNamedArgumentValue("i_print_number",objpoHeader.getTitleDetail().getPrintNo());
			   call.addNamedArgumentValue("i_status",objpoHeader.getStatusDetail().getStatusDescription());
			   call.addNamedArgumentValue("i_start_date",startDate);
			   call.addNamedArgumentValue("i_end_date",endDate);
			   call.addNamedArgumentValue("i_user_id",new Integer(userId));
	           call.addNamedArgumentValue("i_page",page);
	           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
	           call.addNamedArgumentValue("i_order_by",orderby);
	           call.addNamedArgumentValue("i_sort",sort); 
	           call.addNamedArgumentValue("i_bbd_start_date",sbBDate);
	           call.addNamedArgumentValue("i_bbd_end_date",ebBDate);
			   }
			   
	           call.useNamedCursorOutputAsResultSet("list_refcursor");
		   }
		   else if(page.equals("H"))						//for history pages
		   {
			   if("M".equals(roleType)){
				   call.setProcedureName("Pix_PAPER_Plan_Hist_Proc");   //Setting the procedure name and providing arguments when coming from History link
				   
				   call.addNamedArgumentValue("i_po_no ",objpoHeader.getPoNo());
				   call.addNamedArgumentValue("i_po_id ",objpoHeader.getPoId());
				   call.addNamedArgumentValue("i_material_no",objpoHeader.getTitleDetail().getIsbn10());
				   call.addNamedArgumentValue("i_status",objpoHeader.getStatusDetail().getStatusDescription());
				   call.addNamedArgumentValue("i_start_date",startDate);
				   call.addNamedArgumentValue("i_end_date",endDate);
				   call.addNamedArgumentValue("i_user_id",new Integer(userId));
		           call.addNamedArgumentValue("i_page",page);
		           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
		           call.addNamedArgumentValue("i_order_by",orderby);
		           call.addNamedArgumentValue("i_sort",sort); 
			   }
			   else
			   {
				   call.setProcedureName("Pix_planning_Hist_Proc");   //Setting the procedure name and providing arguments when coming from History link
				   
				   call.addNamedArgumentValue("i_po_no ",objpoHeader.getPoNo());
				   call.addNamedArgumentValue("i_po_id ",objpoHeader.getPoId());
				   call.addNamedArgumentValue("i_isbn",objpoHeader.getTitleDetail().getIsbn10());
				   call.addNamedArgumentValue("i_print_number",objpoHeader.getTitleDetail().getPrintNo());
				   call.addNamedArgumentValue("i_status",objpoHeader.getStatusDetail().getStatusDescription());
				   call.addNamedArgumentValue("i_start_date",startDate);
				   call.addNamedArgumentValue("i_end_date",endDate);
				   call.addNamedArgumentValue("i_user_id",new Integer(userId));
		           call.addNamedArgumentValue("i_page",page);
		           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
		           call.addNamedArgumentValue("i_order_by",orderby);
		           call.addNamedArgumentValue("i_sort",sort); 
			   }
	           
	           call.useNamedCursorOutputAsResultSet("list_refcursor");
		   }
		 /*  call.addNamedArgumentValue("i_po_no ",objpoHeader.getPoNo());
		   call.addNamedArgumentValue("i_po_id ",objpoHeader.getPoId());
		   call.addNamedArgumentValue("i_isbn",objpoHeader.getTitleDetail().getIsbn10());
		   call.addNamedArgumentValue("i_print_number",objpoHeader.getTitleDetail().getPrintNo());
		   call.addNamedArgumentValue("i_status",objpoHeader.getStatusDetail().getStatusDescription());
		   call.addNamedArgumentValue("i_start_date",startDate);
		   call.addNamedArgumentValue("i_end_date",endDate);
		   call.addNamedArgumentValue("i_user_id",new Integer(userId));
           call.addNamedArgumentValue("i_page",page);
           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
           call.addNamedArgumentValue("i_order_by",orderby);
           call.addNamedArgumentValue("i_sort",sort); 
           call.addNamedArgumentValue("i_bbd_start_date",sbBDate);
           System.out.println("sbBDate  ::"+sbBDate);
           call.addNamedArgumentValue("i_bbd_end_date",ebBDate);
           System.out.println("ebBDate  ::"+ebBDate);
           call.useNamedCursorOutputAsResultSet("list_refcursor");*/
           Vector objVector = (Vector)objSession.executeSelectingCall(call); //Calling the procedure
           int lengthOfobjCollection = objVector == null ? 0 : objVector.size(); //Setting the size of the resulting vector
           if (objVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
           {
          	   for (int i = 0; i < lengthOfobjCollection; i++) //loop for iterating the vector results
        	   {
          		   Record objDatabaseRecord = (Record)objVector.get(i);
          		   objpoHeader = new POHeader();
        		   Status objStatus=new Status();
        		   POParty objparty=new POParty();
        		   Vector vecParty=new Vector();
        		   TitlePrinting objTitlePrinting=new TitlePrinting();
                   objpoHeader.setPoNo((String)objDatabaseRecord.get(PIXConstants.PO_NO));
                   objpoHeader.setPoId((BigDecimal)objDatabaseRecord.get(PIXConstants.POID));
                   objpoHeader.setPoVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.POVERSION));
                   if("M".equals(roleType)){
                	   
                	   objTitlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.MATERIAL_NO));
                       objTitlePrinting.setPrintNo(objDatabaseRecord.get(PIXConstants.PAPER_BW_GRD).toString());
                     
                       if((deliveryDate1=(Date)objDatabaseRecord.get(PIXConstants.ORDER_DELIVERY_DATE))!=null){ //Checking delivery date for null
            			   deliveryDate2=PIXUtil.sqlToStandardDate(deliveryDate1.toString()); //Setting delivery date format to standard
            			   objparty.setDeliveryDate(deliveryDate2);
            			   vecParty.add(objparty);//Adding Party object to vector
            			  
            		   }
                	   
                   }else{
                	   
                	   objTitlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.ISBN_NO));
            		   objTitlePrinting.setPrintNo((String)objDatabaseRecord.get(PIXConstants.PRINT_NUMBER).toString()); 
            		   if((deliveryDate1=(Date)objDatabaseRecord.get(PIXConstants.BOOK_BOUND_DATE))!=null){ //Checking delivery date for null
            			   deliveryDate2=PIXUtil.sqlToStandardDate(deliveryDate1.toString()); //Setting delivery date format to standard
            			   objparty.setDeliveryDate(deliveryDate2);
            			   vecParty.add(objparty);//Adding Party object to vector
            		   }
                   }
                  
        		   objpoHeader.setTotalQuantity((BigDecimal)objDatabaseRecord.get(PIXConstants.QUANTITY));
        		   objparty.setName2((String)objDatabaseRecord.get(PIXConstants.NAME_2));
        		   objparty.setAddress1((String)objDatabaseRecord.get(PIXConstants.VENDOR_CONTACT));
        		  
        		   
        		   objStatus.setStatusDescription((String)objDatabaseRecord.get(PIXConstants.STATUS));
        		   objpoHeader.setAcknowledgeFlag((String)objDatabaseRecord.get(PIXConstants.ACKNOWLEDGE_FLAG));
       		       objpoHeader.setPostedDate((Date)objDatabaseRecord.get(PIXConstants.POSTED_DATE));
       		       objpoHeader.setCreatedBy((String)objDatabaseRecord.get(PIXConstants.PLAN_POSTED_BY));
       		       if(page.equals("H"))
       		       {
       		    	   objpoHeader.setModDateTime((Date)objDatabaseRecord.get(PIXConstants.MOD_DATE_TIME));
       		       }
       		       
       		       if((String)objDatabaseRecord.get(PIXConstants.TITLE_DESC)!=null)
       		       {
       		    	   objpoHeader.setTitleDesc((String)objDatabaseRecord.get(PIXConstants.TITLE_DESC));
       		       }
       		       objpoHeader.setTitleDetail(objTitlePrinting);
       		       objpoHeader.setStatusDetail(objStatus);
       		       objpoHeader.setPartyCollection(vecParty);
	               objListVector.add(objpoHeader);//Adding POHeader object to vector
				       	
               }
           }
          	   else if(objVector.size()== 0 || objVector == null) //Block to be executed when there are no records
             {
          	     	 AppException appException=new AppException();
          	     	 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_PLANNING,"PlanningDAOImpl");  
    				 throw appException;
             }
	      
	     
	   }
         
	 	   catch(TopLinkException e)
	 	{
	 		   log.debug("TopLinkException for Planning List");
	 		   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "PlanningDAOImpl,getPlanningList",e);
			   throw appException;
	     }
	 	  finally //Session release code 
		   {
			   if(objSession!=null)
			   {
				    objSession.release();
			   }
		   }
	
	 	  return objListVector; //Returning the vector containing Party and POHeader object as result
      }
   
   
   /**
    * Gets the Planning Detail information
    * 
    * @return com.pearson.pix.dto.POHeader
    */
   public POHeader getPlanningDetail(BigDecimal poId, BigDecimal poVersion,String orderType) throws AppException
   { 
	   Session clientSession = null;
       POHeader poHeader=null;
	   try
	   {
	   clientSession = getClientSession();
	  
	   if(orderType==null)
		   orderType="R";
	   
	   ExpressionBuilder builder = new ExpressionBuilder();
	   Expression wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("activeFlag").equal("Y")).and(builder.get("orderType").equal(orderType));
	   poHeader = (POHeader)clientSession.readObject(POHeader.class,wcPO);//Getting the POHeader object 
	  
	   if(poHeader==null)
	   {
		   AppException ae = new AppException(Exceptions.RECORDS_NOT_EXISTS_ORDER_DETAIL);
		   ae.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_PLANNING_DETAIL, "PlanningDAOImpl,getPlanningDetail");
	       throw ae;
	   }
	   }
	   catch(TopLinkException te)
	   {
		   log.debug("TopLinkException for Planning Detail");
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PlanningDAOImpl,getPlanningDetail",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			    clientSession.release();
		   }
	   }
	   
	   return poHeader;
   }
   
   /**
    * Inserts the Planning Acknowledgement/Comments by Vendor
    * @return java.util.HashMap
    */
   public HashMap insertPlanningAcknowledgement() 
   {
    return null;
   }
   
   /**
    * Gets the Collection of Planning History List information. This method also 
    * takes care of filtering and pagination display by passing page no, filter 
    * criteria and its value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPlanningHistoryList() 
   {
    return null;
   }
   
   /**
    * Gets the Collection of Planning Status information.
    * 
    * @return java.util.Collection
    */
 public Collection getPlanningStatus()throws AppException
 {
	
	 Collection objallStatus = null;
	 Session clientSession = null;
	 try
	 {
	   clientSession = getClientSession();
	   ExpressionBuilder builder = new ExpressionBuilder(); 
	   Expression expStatus = builder.get("tableDetail").get("tableName").equal(PIXConstants.PIX_PLANNING).and(builder.get("activeFlag").equal("Y"));
	   ReadAllQuery query = new ReadAllQuery(Status.class);//getting all status for planning and ordering them ascending 
	   query.setSelectionCriteria(expStatus);
	   query.addAscendingOrdering("statusDescription");
	   objallStatus = (Vector)clientSession.executeQuery(query);
	 }
	 catch(TopLinkException e)
	   {
		   log.debug("TopLinkException while fetching planning status");
		   AppException ae = new AppException();
		   ae.performErrorAction("Exceptions.SQL_EXCEPTION","PlanningDAOImpl,getPlanningStatus",e);
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
 
 /**method prototype declaration to save the vendor comments of Planning detail page
  *@return java.lang.String
*/
 public String saveVendorDetail(POHeader poHeader) throws AppException
 {
 Session clientSession = null;
 BigDecimal poId = null;
 BigDecimal poVersion = null;
 String poNumber = null;
 POLine poLine = null;
 String supplierComments = null;
 UserTransaction transaction = null; 
 Connection conn = null;
 String comments = null;
 POParty poParty = null;
 PreparedStatement stmtHeader = null;
 PreparedStatement stmtParty = null;
 PreparedStatement stmtLine = null;
 try
   {
	   transaction = getUserTransaction();
	   conn = getDataSourceConnection(); 
	   Statement stmt = conn.createStatement();
	   clientSession = getClientSession();
	   poId = poHeader.getPoId();
	   poVersion = poHeader.getPoVersion();
	   poNumber = poHeader.getPoNo();
	   Collection partyCollection = poHeader.getPartyCollection();
	   // Check for data change(cuncurrency) in-between	: Start
       ExpressionBuilder builder = new ExpressionBuilder();
       Expression wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("activeFlag").equal("Y")).and(builder.get("orderType").equal(poHeader.getOrderType()));
       POHeader poHeaderCurrent = (POHeader)clientSession.readObject(POHeader.class,wcPO);
       
       for(int i=0;i<partyCollection.size();i++){ //Loop for iterating party collection
		   	poParty = (POParty)((Vector)partyCollection).get(i);
		   
		   	if((PIXConstants.VENDOR_ROLE).equals(poParty.getPartyType())||(PIXConstants.MILL_ROLE).equals(poParty.getPartyType()))//Checking party for vendor role or mill role
			{
		   		comments = poParty.getComments();
		   		
		   		if("".equals(comments))
					comments = PIXConstants.Acknowledged;
			}
       }
//     Updating data in PIX_PO_HEADER table for acknowledgement
       stmtHeader = conn.prepareStatement("update PIX_PO_HEADER set "
  				+"acknowledge_flag='Y',"
  				+"mod_date_time=sysdate,"
  				+"modified_by=? "
  				+"where po_id=? and po_version=?");

       stmtHeader.setObject(1,poHeader.getModifiedBy());
  	   stmtHeader.setObject(2, poId);
  	   stmtHeader.setObject(3, poVersion);
  	   stmtHeader.executeUpdate();
       
//  Updating data in PIX_PO_PARTY  table for comments   
  	   stmtParty = conn.prepareStatement("update PIX_PO_PARTY set "	
  					+"comments=? "
  					+"where po_id=? and po_version=? and (party_type='V' or party_type='M')");
    
    stmtParty.setObject(1,comments);
    stmtParty.setObject(2,poId);
    stmtParty.setObject(3,poVersion);
    stmtParty.executeUpdate();  
    
       if(poHeader.getModDateTime().before(poHeaderCurrent.getModDateTime()))
       {
       log.debug("Exception occurred due to data concurrency failure");	   
	   AppException ae = new AppException();
	   ae.performErrorAction(Exceptions.DATA_CUNCURRENCY, "PlanningDAOImpl,saveVendorDetail");
       throw ae;
       }
      //Check for data change(cuncurrency) in-between	: End
       //query for updating the modification date and time
     stmt.executeUpdate("update PIX_PO_HEADER set mod_date_time=sysdate,acknowledge_flag='Y' where po_id="+poId+" and po_version="+poVersion+"");
     //query to update comments
     stmt.executeUpdate("update PIX_PO_PARTY set "
					+"comments='"+comments+"' "
				+"where po_id="+poId+" and po_version="+poVersion+" and (party_type='V' or party_type='M')");
     Collection lineItemCollection = poHeader.getLineItemCollection();
     for(int i=0;i<lineItemCollection.size();i++){//iterating the line items
	   	poLine = (POLine)((Vector)lineItemCollection).get(i);
	   	supplierComments = poLine.getSupplierComments();
	   	if("".equals(supplierComments))
	   		supplierComments = PIXConstants.Acknowledged;
	   	//query to update supplier comments in line items
	   //stmt.executeUpdate("update PIX_PO_LINE set supplier_comments='"+supplierComments+"',mod_date_time=sysdate,modified_by='"+poLine.getModifiedBy()+"' where po_id="+poId+" and po_version="+poVersion+" and po_line_no="+poLine.getPoLineNo());
      
	   stmtLine = conn.prepareStatement("update PIX_PO_LINE set "
				+"supplier_comments=?,"
				+"mod_date_time=sysdate,"
				+"modified_by=? "
				+"where po_id=? and po_version=? "
				+"and po_line_no=?");
		
		stmtLine.clearParameters();
		stmtLine.setObject(1,supplierComments);
		stmtLine.setObject(2,poLine.getModifiedBy());
		stmtLine.setObject(3,poId);
		stmtLine.setObject(4,poVersion);
		stmtLine.setObject(5,poLine.getPoLineNo());
		stmtLine.executeUpdate();
     }

     
   }
 catch(TopLinkException te)
  {
	   log.debug("TopLinkException while saving Planning Detail");  
	   AppException ae = new AppException();
	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PlanningDAOImpl,saveVendorDetail",te);
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
 catch(SQLException se)
 {
	   log.debug("SQLException while saving Vendor Detail");
	   AppException ae = new AppException();
	   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PlanningDAOImpl,insertOrderConfirmation",se);
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
 finally{
	 
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
 return poNumber;
 } 
 
 
 /**
  * Inserts multiple Planning Purchase Orders into database for Acceptance
  * 
  * @param idVersionString
  * @param login
  * @return String 
  */
 
 public String insertMultiplePlanningPOAccept(String idVersionString,String login) throws AppException
 {
	   Session clientSession = null;
	   try
	   {
		   clientSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_plan_split_proc");//setting procedure used to split the string containing poid and po version
		   call.addNamedArgumentValue("v_in",idVersionString);
		   call.addNamedArgumentValue("v_user",login);
	       clientSession.executeNonSelectingCall(call);//calling the procedure
	   }
	   catch(TopLinkException te)
	   {
		   log.debug("SQLException while accepting multiple plans");
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PlanningDAOImpl,insertMultiplePlanningPOAccept",te);
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
 
}





