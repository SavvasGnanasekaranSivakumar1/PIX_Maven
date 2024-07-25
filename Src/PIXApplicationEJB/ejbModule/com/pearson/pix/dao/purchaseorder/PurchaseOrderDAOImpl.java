package com.pearson.pix.dao.purchaseorder;

import com.pearson.pix.dao.base.BaseDAO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.transaction.UserTransaction;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.DatabaseRecord;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;

import oracle.toplink.sessions.UnitOfWork;

import com.pearson.pix.dto.common.Currency;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.TitlePrinting;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.dto.purchaseorder.DropshipDTO;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.PriceDetail;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of Data Access Object containing all the methods required for DB 
 * access through Toplink. The methods have been written for Purchase Order module.
 * @author Mohit Bajaj
 */
public class PurchaseOrderDAOImpl extends BaseDAO implements PurchaseOrderDAO 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(PurchaseOrderDAOImpl.class.getName());
   
   /**
    * Purchase Order Header Status List
    */
   private static Collection poAllHeaderStatus;
   
   /**
    * Purchase Order Line Items Status List
    */
   private static Collection poAllLineStatus;
   
   /**
    * Purchase Order Status List
    */
   private static Collection poAllStatus;
   
   /**
    * Constructor for initializing PurchaseOrderDAOImpl
    */
   public PurchaseOrderDAOImpl() 
   {
    
   }
   
   /**
    * Gets the Order List(Purchase Order/Furinished Order) from the database
    * @return java.util.Collection
    */
   public Collection getOrderList(POHeader objpoheader,String startDate,String endDate,String orderType,int userId,
		   String page,int pagination,String orderby,String sort,String sbBDate,String ebBDate) throws AppException  
   {
	   Session objSession = null;
	   Vector objListVector = new Vector();
	  
	   try
	   {	
		  /* if("H".equals(page))
		   {
			   orderType = null;
		   }*/
		   objSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   if(page.equals("C")||page.equals("I"))
		   {
			   if(orderType.equals("PO"))
			   {
				  call.setProcedureName("Pix_Po_Proc");
				  call.addNamedArgumentValue("i_po_no",objpoheader.getPoNo());
				   call.addNamedArgumentValue("i_po_id ",objpoheader.getPoId());
				   call.addNamedArgumentValue("i_isbn",objpoheader.getTitleDetail().getIsbn10());
				  // BigDecimal statusId = (objPurchaseOrder.getStatusDetail().getStatusId().intValue()==0)?null:objPurchaseOrder.getStatusDetail().getStatusId();
				   call.addNamedArgumentValue("i_print_number",objpoheader.getTitleDetail().getPrintNo());
				   call.addNamedArgumentValue("i_status_id",objpoheader.getStatusDetail().getStatusDescription());
				   call.addNamedArgumentValue("i_start_date",startDate);
				   call.addNamedArgumentValue("i_end_date",endDate);
				   call.addNamedArgumentValue("i_order_type",orderType);
				   call.addNamedArgumentValue("i_job_no",objpoheader.getJobNo());
				   call.addNamedArgumentValue("i_gang",objpoheader.getGangName());
				   call.addNamedArgumentValue("i_user_id", new Integer(userId));
		           call.addNamedArgumentValue("i_page",page);
		           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
		           call.addNamedArgumentValue("i_order_by",orderby);
		           call.addNamedArgumentValue("i_sort",sort); 
		           call.addNamedArgumentValue("i_bbd_start_date",sbBDate);
		           call.addNamedArgumentValue("i_bbd_end_date",ebBDate);
		           call.useNamedCursorOutputAsResultSet("list_refcursor");
		           //i_gang
			   }
			   else if(orderType.equals("FO"))
			   {
				   call.setProcedureName("Pix_Fo_Proc");
				   call.addNamedArgumentValue("i_po_no",objpoheader.getPoNo());
				   call.addNamedArgumentValue("i_po_id ",objpoheader.getPoId());
				   call.addNamedArgumentValue("i_isbn",objpoheader.getTitleDetail().getIsbn10());
				  // BigDecimal statusId = (objPurchaseOrder.getStatusDetail().getStatusId().intValue()==0)?null:objPurchaseOrder.getStatusDetail().getStatusId();
				   call.addNamedArgumentValue("i_print_number",objpoheader.getTitleDetail().getPrintNo());
				   call.addNamedArgumentValue("i_status_id",objpoheader.getStatusDetail().getStatusDescription());
				   call.addNamedArgumentValue("i_start_date",startDate);
				   call.addNamedArgumentValue("i_end_date",endDate);
				   call.addNamedArgumentValue("i_order_type",orderType);
				   call.addNamedArgumentValue("i_job_no",objpoheader.getJobNo());
				   call.addNamedArgumentValue("i_user_id", new Integer(userId));
		           call.addNamedArgumentValue("i_page",page);
		           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
		           call.addNamedArgumentValue("i_order_by",orderby);
		           call.addNamedArgumentValue("i_sort",sort); 
		           call.useNamedCursorOutputAsResultSet("list_refcursor");
			   }else if("MILL_PO".equals(orderType)){
				   
				   call.setProcedureName("Pix_Paper_Po_Proc");
					  call.addNamedArgumentValue("i_po_no",objpoheader.getPoNo());
					   call.addNamedArgumentValue("i_po_id ",objpoheader.getPoId());
					   call.addNamedArgumentValue("i_material_no",objpoheader.getTitleDetail().getIsbn10());
					
					   //call.addNamedArgumentValue("i_print_number",objpoheader.getTitleDetail().getPrintNo());
					   call.addNamedArgumentValue("i_status_id",objpoheader.getStatusDetail().getStatusDescription());
					   call.addNamedArgumentValue("i_paper_grade",objpoheader.getTitleDetail().getPrintNo());
					   call.addNamedArgumentValue("i_order_type","O");
					   //call.addNamedArgumentValue("i_job_no",objpoheader.getJobNo());
					   call.addNamedArgumentValue("i_user_id", new Integer(userId));
			           call.addNamedArgumentValue("i_page",page);
			           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
			           call.addNamedArgumentValue("i_order_by",orderby);
			           call.addNamedArgumentValue("i_sort",sort); 
	                   call.addNamedArgumentValue("i_start_date",startDate);
					   call.addNamedArgumentValue("i_end_date",endDate);
			           //call.addNamedArgumentValue("i_bbd_start_date",sbBDate);
			           //call.addNamedArgumentValue("i_bbd_end_date",ebBDate);
			           call.useNamedCursorOutputAsResultSet("list_refcursor");
				   
			   }
		   }
		   else if(page.equals("H"))
		   {
			   if(orderType.equals("PO"))
			   {
				  call.setProcedureName("Pix_po_Hist_Proc");
			   }
			   else if(orderType.equals("FO"))
			   {
				  call.setProcedureName("Pix_Fo_Hist_Proc");
			   }
			   else if(orderType.equals("MILL_PO"))
			   {
				   orderType="O";
				  call.setProcedureName("Pix_Paper_Po_Hist_Proc");
			   }
			   //call.setProcedureName("Pix_Fo_Proc");
			   call.addNamedArgumentValue("i_po_no",objpoheader.getPoNo());
			   call.addNamedArgumentValue("i_po_id ",objpoheader.getPoId());
			   call.addNamedArgumentValue("i_isbn",objpoheader.getTitleDetail().getIsbn10());
			  // BigDecimal statusId = (objPurchaseOrder.getStatusDetail().getStatusId().intValue()==0)?null:objPurchaseOrder.getStatusDetail().getStatusId();
			   call.addNamedArgumentValue("i_print_number",objpoheader.getTitleDetail().getPrintNo());
			   call.addNamedArgumentValue("i_status_id",objpoheader.getStatusDetail().getStatusDescription());
			   call.addNamedArgumentValue("i_start_date",startDate);
			   call.addNamedArgumentValue("i_end_date",endDate);
			   call.addNamedArgumentValue("i_order_type",orderType);
			   call.addNamedArgumentValue("i_job_no",objpoheader.getJobNo());
			   call.addNamedArgumentValue("i_user_id", new Integer(userId));
	           call.addNamedArgumentValue("i_page",page);
	           call.addNamedArgumentValue("i_pagination",new Integer(pagination));
	           call.addNamedArgumentValue("i_order_by",orderby);
	           call.addNamedArgumentValue("i_sort",sort); 
	           call.useNamedCursorOutputAsResultSet("list_refcursor");
			  		   
		   }
		   
		   if(orderType.equals("O"))
		   {
			   orderType="MILL_PO";
			  
		   }		   
           Vector objVector = (Vector)objSession.executeSelectingCall(call);
           
           int lengthOfobjColllection = objVector == null ? 0 : objVector.size();
           if (objVector != null && lengthOfobjColllection> 0)
           {
          	   for (int i = 0; i < lengthOfobjColllection; i++)
        	   {
          		   Record objDatabaseRecord = (Record)objVector.get(i);
        		   objpoheader = new POHeader();
        		   Status objStatus=new Status();
        		   POParty objparty=new POParty();
                   TitlePrinting objTitlePrinting=new TitlePrinting();
                   objpoheader.setPoNo((String)objDatabaseRecord.get(PIXConstants.PO_NO));
                   objpoheader.setPoId((BigDecimal)objDatabaseRecord.get(PIXConstants.POID));
                   objpoheader.setPoVersion((BigDecimal)objDatabaseRecord.get(PIXConstants.POVERSION));
                   if(objDatabaseRecord.get(PIXConstants.RELEASE_NO)!=null){
                	   objpoheader.setReleaseNo(new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.RELEASE_NO)).intValue()));
                   }
                   if("MILL_PO".equals(orderType)){
                	   
                   objTitlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.MATERIAL_NO));
                  
                   objTitlePrinting.setPrintNo(objDatabaseRecord.get(PIXConstants.PAPER_BW_GRD).toString());
                   
                   objpoheader.setLineItemCount(new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.PO_LINE_COUNT)).intValue()));
                	   
                   }else{
                   objTitlePrinting.setIsbn10((String)objDatabaseRecord.get(PIXConstants.ISBN_NO));
	                   
                           String prnStr= objDatabaseRecord.get(PIXConstants.PRINT_NUMBER).toString();
	                	   objTitlePrinting.setPrintNo(prnStr);
	                
                   }
       		       if((String)objDatabaseRecord.get("GANG")!= null){
       		    	objpoheader.setGangName((String)objDatabaseRecord.get("GANG"));
       		       }
	               objpoheader.setPostedDate((Date)objDatabaseRecord.get(PIXConstants.POSTED_DATE));
       		       objparty.setName1((String)objDatabaseRecord.get(PIXConstants.NAME_1));
       		       objparty.setName2((String)objDatabaseRecord.get(PIXConstants.NAME_2));
       		       objparty.setAddress1((String)objDatabaseRecord.get(PIXConstants.VENDOR_CONTACT));
	        	   objStatus.setStatusDescription((String)objDatabaseRecord.get(PIXConstants.STATUS));
	               objpoheader.setTitleDetail(objTitlePrinting);
	               objpoheader.setStatusDetail(objStatus);
	               objpoheader.setOrderType((String)objDatabaseRecord.get(PIXConstants.ORDER_TYPE)) ;
	               
	               if((orderType.equals("PO") || orderType.equals("MILL_PO")) && page.equals("H"))
	               {
	            	   objpoheader.setModDateTime((Date)objDatabaseRecord.get(PIXConstants.MOD_DATE_TIME));
	            	   
	            	  
	               }
	               //!"MILL_PO".equals(orderType)&&......me
	               if((String)objDatabaseRecord.get(PIXConstants.TITLE_DESC)!=null)
       		       {
	            	   String titleDESC=(String)objDatabaseRecord.get(PIXConstants.TITLE_DESC);
	            	   objpoheader.setTitleDesc(titleDESC);
       		       }
	               
	               if(objDatabaseRecord.get("PAPER_GRADE")!=null)
       		       {
	            	   String PAPER_GRADE=(String)objDatabaseRecord.get("PAPER_GRADE");
	            	   objpoheader.setPaperGrade(PAPER_GRADE);
       		       }
	               
	               
	               if((String)objDatabaseRecord.get("AMENDED_BY")!=null)
	               {
	            	  objpoheader.setModifiedBy((String)objDatabaseRecord.get("AMENDED_BY"));
	               }
	               
	               Vector objpartyvector=new Vector();
	               objpartyvector.add(objparty);
	               objpoheader.setPartyCollection(objpartyvector);
	               
	               if(page.equals("C")||page.equals("I"))		//getting orderType for Pix_fo_proc (to distinguidh between Paper FO and normal FO
	    		   {
	            	   if(orderType.equals("FO"))
	    			   {
	            		   objpoheader.setOrderType((String)objDatabaseRecord.get(PIXConstants.ORDER_TYPE));
	            		               		   
	            		   if(objDatabaseRecord.get("PAPER_GRADE")!=null)
		            	   {
		            		   objpoheader.setPaperGrade((String)objDatabaseRecord.get("PAPER_GRADE"));
		            	   }
	    			   }
	    		   }
	               objListVector.add(objpoheader);
				   	
               }
           }
          	   else if(lengthOfobjColllection== 0 || objVector == null)
             {
          	     	 AppException appException=new AppException();
    				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER,"PurchaseOrderDAOImpl");  
    				 throw appException;
             }
	      
	     
	   }
         
	 	   catch(TopLinkException e)
	     {
		   e.printStackTrace();
	    	AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "PurchaseOrderDAOImpl,getOrderList",e);
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
    * Gets the Order Detail (Purchase Order/Furinished Order) from the database
    * 
    * @param poId
    * @param poVersion
    * @param party
    * @param userId
    * @param paperFO
    * @return java.util.HashMap
    */
   public HashMap getOrderDetail(BigDecimal poId, BigDecimal poVersion,String party,Integer userId,String paperFo) throws AppException
   {
	   HashMap poDetailData = null;
	   Session clientSession = null;
	   PriceDetail priceDetail = null;
	   Record objDatabaseRecord = null;
	   BigDecimal price = null;
	   String currencyCode = "";
	   String orderType = "S";
	   Expression wcPO = null;
	   POHeader poHeader =null;
	   
	   Expression poLine = null;
	   POLine objPoline = null;
	   Vector poLineCol = new Vector();
	   
	   try
	   {
		   if(party!=null && party!="" && party.equals("M"))
			   orderType="O";
		   clientSession = getClientSession();
		   poDetailData = new HashMap();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   clientSession.logMessages();
		   if("paperfo".equals(paperFo))
		   {
			   wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("orderType").equal(orderType));
			   poHeader = (POHeader)clientSession.readObject(POHeader.class,wcPO);
			   
			   StoredProcedureCall call = new StoredProcedureCall(); 
	    	   call.setProcedureName("pix_pms_get_auth_line_proc");
	    	   call.addNamedArgumentValue("i_po_id",poId);
	    	   call.addNamedArgumentValue("i_po_version",poVersion);
	    	   call.addNamedArgumentValue("i_user_id",userId);
	    	   call.useNamedCursorOutputAsResultSet("list_refcursor");
	    	   Vector objVector = (Vector)clientSession.executeSelectingCall(call);
	    	   
	    	   //log.info("vector size:"+objVector.size());
	    	   
	    	   for(int k=0;k<objVector.size();k++)
	    	   {
	    		   
	    		   Record data = (Record)objVector.get(k);
	    		   
	    		   BigDecimal polineNO = (BigDecimal)data.get("LINE_NO");
	    		   
	    		   poLine = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("poLineNo").equal(polineNO));
	    		   objPoline = (POLine)clientSession.readObject(POLine.class,poLine);
	    		   
	    		   poLineCol.add(objPoline);
	    		   
	    	   }
	    	   poHeader.setLineItemCollection(poLineCol);
	    	   
	    	   
			   
		   }
		   // upload shipping confirmation
		   else
		   {	   
			    wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(builder.get("orderType").equal(orderType));
			    poHeader = (POHeader)clientSession.readObject(POHeader.class,wcPO);
			    clientSession.logMessages();
			    
			    StoredProcedureCall call1 = new StoredProcedureCall(); 
		    	   call1.setProcedureName("PIX_GET_DS_DETAILS_PROC");
		    	   //call.addNamedArgumentValue("I_PO_ID",164423);
		    	   call1.addNamedArgumentValue("I_PO_ID",poId);
		    	   call1.addNamedArgumentValue("I_TYPE","DS");
		    	   call1.addNamedArgumentValue("I_USER_ID",userId);
		    	   call1.useNamedCursorOutputAsResultSet("O_GET_DS_DETAILS_CURSOR");
		    	   clientSession.logMessages();
		    	   Vector resultVec1 = (Vector)clientSession.executeSelectingCall(call1);
		    	   if(resultVec1!=null && !resultVec1.isEmpty()){
			    	   DropshipDTO objDropshipDto2 = null;
	    		       List<DropshipDTO> objDropshipDTOList2 = new ArrayList<DropshipDTO>();
			    	   for(int k=0;k<resultVec1.size();k++)
			    	   {
			    		   objDropshipDto2 = new DropshipDTO();
			    		   Record data = (Record)resultVec1.get(k);
			    		   objDropshipDto2.setBknumber((String)data.get("BK_NUMBER"));	
			    		   objDropshipDto2.setTotalQty((BigDecimal)data.get("QUANTITY"));
			    		   objDropshipDto2.setShipTo((String)data.get("SHIP_TO"));
			    		   objDropshipDto2.setBillTo((String)data.get("BILL_TO"));
			    		   objDropshipDto2.setSchool((String)data.get("IS_SCHOOL"));
			    		   objDropshipDTOList2.add(objDropshipDto2);
			    	   }
			    	   poHeader.setDropshipshippinfinfo(objDropshipDTOList2);
		    	   }
		    	   
			    StoredProcedureCall call = new StoredProcedureCall(); 
		    	   call.setProcedureName("PIX_GET_DS_DETAILS_PROC");
		    	   //call.addNamedArgumentValue("I_PO_ID",164423);
		    	   call.addNamedArgumentValue("I_PO_ID",poId);
		    	   call.addNamedArgumentValue("I_TYPE","SC");
		    	   call.addNamedArgumentValue("I_USER_ID",userId);
		    	   call.useNamedCursorOutputAsResultSet("O_GET_DS_DETAILS_CURSOR");
		    	   Vector resultVec = (Vector)clientSession.executeSelectingCall(call);
		    	   if(resultVec!=null && !resultVec.isEmpty()){
			    		   DropshipDTO objDropshipDto = null;
			    		   List<DropshipDTO> objDropshipDTOList = new ArrayList<DropshipDTO>();
			    	   for(int k=0;k<resultVec.size();k++)
			    	   {
			    		   objDropshipDto = new DropshipDTO();
			    		   Record data = (Record)resultVec.get(k);
	
			    		   objDropshipDto.setBknumber((String)data.get("BK_NUMBER"));
			    		   objDropshipDto.setTrackingNo((String)data.get("TRACKING_NUMBER"));
			    		   objDropshipDto.setCarrierLevel((String)data.get("CARRIER_LEVEL"));
			    		   objDropshipDto.setShippedTo((String)data.get("SHIPPED_TO"));
			    		   objDropshipDto.setShipDate((Date)data.get("SHIP DATE"));
			    		   objDropshipDto.setWeight((BigDecimal)data.get("WEIGHT"));
			    		   objDropshipDto.setTotalCartons((BigDecimal)data.get("TOTAL_CARTONS_SHIPPED"));
			    		   objDropshipDto.setUnitPerCarton((BigDecimal)data.get("UNITS_PER_CARTON"));
			    		   objDropshipDTOList.add(objDropshipDto);
			    	   }
			    	   /// for dropship instructions
			    	   poHeader.setDropshipDTOList(objDropshipDTOList);
		    	   	}
		   } 	   
		   if(poHeader==null)	//For Purchase Order not found though not possible
		   {
			   AppException ae = new AppException(Exceptions.RECORDS_NOT_EXISTS_ORDER_DETAIL);
			   ae.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_ORDER_DETAIL, "PurchaseOrderDAOImpl,getOrderDetail");
		       throw ae;
		   }
		   
		   if("O".equals(orderType))
		   {
			  // Reference data1 = (Reference)clientSession.readObject(Reference.class,new SQLCall("SELECT REF_ID,DESCRIPTION FROM PIX_REF PR WHERE PR.REF_CODE='PAPER' AND PR.GROUP_CODE='GL_CODE'"));
			   Reference data1 = (Reference)clientSession.readObject(Reference.class,new SQLCall("select po_id,po_version,ref_id,description ||' '|| cwt_price description,cwt_price from pix_ref pr,pix_po_header pph  where ref_code='PAPER' and group_code='GL_CODE' and order_type='O' and po_id="+poId+" and po_version="+poVersion));

			   poHeader.setGlcodeDesc(data1.getDescription());
			//System.out.println(data1.getDescription());
			
			   
		   }
		   poDetailData.put(PIXConstants.PO_HEADER,poHeader);	//Placing PO in HashMap
		   
		   Collection poAllHeaderStatus = getOrderHeaderStatusList();
		   poDetailData.put(PIXConstants.PO_ALL_HEADER_STATUS,poAllHeaderStatus);	//Placing PO Header (Party) Status in HashMap
		   
		   Collection poAllLineStatus = getOrderLineItemsStatusList();
		   	
		   poDetailData.put(PIXConstants.PO_ALL_LINE_STATUS,poAllLineStatus);	//Placing PO Line Status in HashMap
		  
		  /* Collection hcoll=poAllLineStatus.getLineItemCollection();
		    Iterator it=hcoll.iterator();
		    for(int i=0;i<hcoll.size();i++){
		    	POLine oo=(POLine)it.next();
		    	System.out.println("line..in detail......."+oo.getLineNo());
		    	
		    }*/
		   
		   /*Price Details query separate for display. Used separately because the data is to be fetched group by GL_code,currency_code,GL_desc to finally calculate total price etc*/
		   Vector poPriceDet = clientSession.executeSelectingCall(
				   new SQLCall("select GL_code,"
						   		+"GL_desc,"
						   		+"SUM(price) as price, "
						   		+"currency_code "
						   		+"from pix_price_detail "
						   		+"where po_id="+poId+" and po_version="+poVersion+" "
						   		+"group by GL_code,currency_code,GL_desc"));
		   Vector poPriceDetails = new Vector();
		   int priceSize = poPriceDet == null ? 0 : poPriceDet.size();
		  // System.out.println("........poPriceSize..."+priceSize);
		   for(int i=0;i<priceSize;i++)	//Checking Price Details for Price Collection
		   {
			   priceDetail = new PriceDetail();
			   objDatabaseRecord = (DatabaseRecord)poPriceDet.get(i);
			   String glCode = "";
			   String glDesc = "";
			   price = new BigDecimal(0);
			   
			   if((glCode = (String)objDatabaseRecord.get("GL_CODE"))!=null)
    		   {
				   priceDetail.setGlCode(glCode);
    		   }
			   if((glDesc = (String)objDatabaseRecord.get("GL_DESC"))!=null)
    		   {
				   priceDetail.setGlDesc(glDesc);
    		   }
			   if((price = (BigDecimal)objDatabaseRecord.get("PRICE"))!=null)
    		   {
				   priceDetail.setPrice(price);
    		   }
			   if((currencyCode = (String)objDatabaseRecord.get("CURRENCY_CODE"))!=null)
    		   {
				   Currency currency = new Currency();
				   currency.setCurrencyCode(currencyCode);
				   priceDetail.setCurrencyDetail(currency);
    		   }
			   //priceDetail
			   poPriceDetails.add(priceDetail);
		   }
		   /*Condition has been placed because price details may not be available*/
		   if(poPriceDet.size()>0){
			   poDetailData.put(PIXConstants.PO_PRICE_DETAILS,poPriceDetails);	//Placing Price details in HashMap
		   }		   
		   }
	  
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,getOrderDetail",te);
		   throw ae;
	   }
	   catch(Exception e)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.EXCEPTION,"PurchaseOrderDAOImpl,getOrderDetail",e);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return poDetailData;
   }
   

   /**
    * Gets the Purchase Order Header Status List
    * @return java.util.Collection
    */
   public Collection getOrderHeaderStatusList() throws AppException
   {
	   Session clientSession = null;
	   try
	   {
		   if(poAllHeaderStatus==null){
			   clientSession = getClientSession();
			   ExpressionBuilder builder = new ExpressionBuilder();
			   //Takes all Status for PIX_PO_PARTY including Order Delivered
			   Expression wcPOStatus = builder.get("tableDetail").get("tableName").equal(
					   PIXConstants.PIX_PO_PARTY).and(builder.get("activeFlag").equal("Y"));
			   ReadAllQuery query = new ReadAllQuery(Status.class);
			   query.setSelectionCriteria(wcPOStatus);
			   query.addAscendingOrdering("statusDescription");
			   poAllHeaderStatus = (Vector)clientSession.executeQuery(query);
			   //System.out.println("poAllHeaderStatus");
		   }
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,getOrderHeaderStatusList",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return poAllHeaderStatus;
   }
   
   /**
    * Gets the Purchase Order Line Items Status List
    * @return java.util.Collection
    */
   public Collection getOrderLineItemsStatusList() throws AppException
   {
	   Session clientSession = null;
	   try
	   {
		   if(poAllLineStatus==null){
			   clientSession = getClientSession();
			   ExpressionBuilder builder = new ExpressionBuilder();
			   //Takes all Status for PIX_PO_LINE except Order Delivered and New
			   Expression wcPOStatus = builder.get("tableDetail").get("tableName").equal(
					   PIXConstants.PIX_PO_LINE).and(builder.get("activeFlag").equal("Y")).and(
									   builder.get("statusCode").notEqual(PIXConstants.STATUS_LINE_NEW));
			   ReadAllQuery query = new ReadAllQuery(Status.class);
			   query.setSelectionCriteria(wcPOStatus);
			   query.addAscendingOrdering("statusDescription");
			   poAllLineStatus = (Vector)clientSession.executeQuery(query);
		   }
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,getOrderLineItemsStatusList",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return poAllLineStatus;
   }  
   
   /**
    * Gets the Purchase Order Status List
    * @return java.util.Collection
    */
   public Collection getOrderStatusList() throws AppException
   {
	   Session clientSession = null;
	   try
	   {
		   if(poAllStatus==null){
			   clientSession = getClientSession();
			   ExpressionBuilder builder = new ExpressionBuilder();
			   //Takes all status for PIX_PO_HEADER
			   Expression wcPOStatus = builder.get("tableDetail").get("tableName").equal(
					   PIXConstants.PIX_PO_HEADER).and(builder.get("activeFlag").equal("Y"));
			   ReadAllQuery query = new ReadAllQuery(Status.class);
			   query.setSelectionCriteria(wcPOStatus);
			   query.addAscendingOrdering("statusDescription");
			   poAllStatus = (Vector)clientSession.executeQuery(query);
			   
		   }
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,getOrderStatusList",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return poAllStatus;
   }   
		   
		   
   /**
    * Inserts the Order Confirmation for the Purchase Order
    * 
    * @return java.lang.String
    */
   public String insertOrderConfirmation(POHeader poHeader,String party) throws AppException 
   {
	   Session clientSession = null;
	   Integer vendorStatusId = null;
	   //String vendorModifiedBy = null;
	   String deliveryDate = null;
	   String comments = null;
	   BigDecimal poId = null;
	   BigDecimal poVersion = null;
	   String poNumber = null;
	   POParty poParty = null;
	   POLine poLine = null;
	   Integer supplierStatusId = null;
	   String estimatedDeliveryDate = null;
	   String supplierComments = null;
	   java.util.Date currentDateTime = null;
	   UserTransaction transaction = null; 
	   Connection conn = null;
	   PreparedStatement stmtHeader = null;
	   PreparedStatement stmtHeaderInterface = null;
	   PreparedStatement stmtParty = null;
	   PreparedStatement stmtLine = null;
	   PreparedStatement stmtLineInterface = null;
	   String orderType = "S";
	   String partyType="V";
	   
		   
	   try
	   {
		   transaction = getUserTransaction();	//For JTA UserTransaction
		   conn = getDataSourceConnection(); 	//For DataSource Connection
		   if(party!=null)
		   {
			   orderType="O";
			   partyType="M";
		   }
		   clientSession = getClientSession();
		   poId = poHeader.getPoId();
		   poVersion = poHeader.getPoVersion();
		   poNumber = poHeader.getPoNo();
		   Collection partyCollection = poHeader.getPartyCollection();
		   
		   //Check for data change(cuncurrency) in-between	: Start
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Expression wcPO = builder.get("poId").equal(poId).and(builder.get("poVersion").equal(poVersion)).and(
				   builder.get("orderType").equal(orderType));
		   POHeader poHeaderCurrent = (POHeader)clientSession.readObject(POHeader.class,wcPO);
		   currentDateTime = poHeaderCurrent.getModDateTime();
		   /* 
		   ResultSet rsHeaderCurrent = stmt.executeQuery("select mod_date_time from pix_po_header where po_id="+poId+" and po_version="+poVersion+" and order_type='S'");
		   while (rsHeaderCurrent.next())
		   {
			   currentDateTime = rsHeaderCurrent.getDate(1);
		   }
		   
		   log.info("poHeader.getModDateTime()==="+poHeader.getModDateTime());
		   log.info("currentDateTime==="+currentDateTime);
		   */
		   
		   if(poHeader.getModDateTime().before(currentDateTime)){
			   AppException aex = new AppException();
			   aex.performErrorAction(Exceptions.DATA_CUNCURRENCY, "PurchaseOrderDAOImpl,insertOrderConfirmation");
		       throw aex;
		   }
		   //Check for data change(cuncurrency) in-between	: End
		   
		   int partySize = partyCollection == null ? 0 : partyCollection.size();
		   for(int i=0;i<partySize;i++){
			   	poParty = (POParty)((Vector)partyCollection).get(i);
			    
			   	if((PIXConstants.VENDOR_ROLE).equals(poParty.getPartyType()) || ("M").equals(poParty.getPartyType()))	//We have to consider vendor only
				{
					deliveryDate = poParty.getDeliveryDate();
					deliveryDate = PIXUtil.standardToSqlDate(deliveryDate);
					if(deliveryDate==null){
						deliveryDate = "";
					}
					comments = poParty.getComments();
					if("".equals(comments))
						comments = PIXConstants.Acknowledged;
					vendorStatusId = poParty.getStatusId();
					//vendorModifiedBy = poParty.getModifiedBy();
				}
		   }
		   
		   //Updating data in PIX_PO_HEADER table for Status, acknowledgement,job no
		   stmtHeader = conn.prepareStatement("update PIX_PO_HEADER set "
				   						+"status_id=?,"
				   						+"acknowledge_flag='Y',"
				   						+"job_no=?,"
				   						+"mod_date_time=sysdate,"
				   						+"modified_by=? "
				   						+"where po_id=? and po_version=?");
		   stmtHeader.setObject(1, poHeader.getStatusDetail().getStatusId());
		   stmtHeader.setObject(2, poHeader.getJobNo());
		   stmtHeader.setObject(3, poHeader.getModifiedBy());
		   stmtHeader.setObject(4, poId);
		   stmtHeader.setObject(5, poVersion);
		   stmtHeader.executeUpdate();
		   
		   /*if("M".equals(partyType)){
		   String poStatus="";
		   if(poHeader.getStatusDetail()!=null)
		   {
			   poStatus = poHeader.getStatusDetail().getStatusCode();
		   }
		   stmtHeaderInterface =conn.prepareStatement("insert into PIX_PMS_PO_INTERFACE(PO_INTERFACE_ID, PO_NUMBER, PO_LINE_NO, STATUS, COMMENTS, DELIVERY_DATE) VALUES("
						+"seq_PMS_PO_INTERFACE_id.nextval,"
   						+"?,"
   						+"?,"
   						+"?,"
   						+"?,"
   						+"? )");
            
		   stmtHeaderInterface.setObject(1, poHeader.getPoNo());
		   stmtHeaderInterface.setObject(2, new Integer(0));
		   stmtHeaderInterface.setObject(3, poStatus);
		   stmtHeaderInterface.setObject(4, poParty.getComments());
		   stmtHeaderInterface.setObject(5, poParty.getDeliveryDate());
		   stmtHeaderInterface.executeQuery();
		   }*/
		   
		   //Updating data in PIX_PO_PARTY (For Vendor) table for Status, delivery date and comments
		   stmtParty = conn.prepareStatement("update PIX_PO_PARTY set "	
						+"status_id=?,"
   						+"delivery_date=?,"
   						+"comments=? "
   						+"where po_id=? and po_version=? and party_type='"+partyType+"'");
		   stmtParty.setObject(1,vendorStatusId);
		   stmtParty.setObject(2,deliveryDate);
		   stmtParty.setObject(3,comments);
		   stmtParty.setObject(4,poId);
		   stmtParty.setObject(5,poVersion);
		   stmtParty.executeUpdate();
		   
		   
		   Collection lineItemCollection = poHeader.getLineItemCollection();
		   
		   int lineItemSize = lineItemCollection == null ? 0 : lineItemCollection.size();
		   for(int i=0;i<lineItemSize;i++){	//For all line items update
			   	poLine = (POLine)((Vector)lineItemCollection).get(i);
			   	supplierStatusId = poLine.getSupplierStatusId();
				estimatedDeliveryDate = poLine.getEstimatedDeliveryDate();
				estimatedDeliveryDate = PIXUtil.standardToSqlDate(estimatedDeliveryDate);
				if(estimatedDeliveryDate==null){
					estimatedDeliveryDate = "";
				}
				supplierComments = poLine.getSupplierComments();
				if("".equals(supplierComments))
					supplierComments = PIXConstants.Acknowledged;
				//Updating data in PIX_PO_LINE table for Status, estiamted delivery date and supplier comments
				
				 stmtLine = conn.prepareStatement("update PIX_PO_LINE set "
						+"supplier_status_id=?,"
						+"estimated_delivery_date=?,"
						+"supplier_comments=?,"
						+"mod_date_time=sysdate,"
						+"modified_by=? "
						+"where po_id=? and po_version=? "
						+"and po_line_no=?");
				
				stmtLine.clearParameters();
				stmtLine.setObject(1,supplierStatusId);
				stmtLine.setObject(2,estimatedDeliveryDate);
				stmtLine.setObject(3,supplierComments);
				stmtLine.setObject(4,poLine.getModifiedBy());
				stmtLine.setObject(5,poId);
				stmtLine.setObject(6,poVersion);
				stmtLine.setObject(7,poLine.getPoLineNo());
				stmtLine.executeUpdate();
				
				
			 /*if("M".equals(partyType)){
			 stmtLineInterface= conn.prepareStatement("insert into PIX_PMS_PO_INTERFACE(PO_INTERFACE_ID, PO_NUMBER, PO_LINE_NO, STATUS, COMMENTS, DELIVERY_DATE) VALUES("
						+"seq_PMS_PO_INTERFACE_id.currval,"
   						+"?,"
   						+"?,"
   						+"?,"
   						+"?,"
   						+"? )");

			 stmtLineInterface.setObject(1, poHeader.getPoNo());
			 stmtLineInterface.setObject(2, poLine.getPoLineNo());
			 stmtLineInterface.setObject(3, "AMENDED");
			 stmtLineInterface.setObject(4, poLine.getSupplierComments());
			 stmtLineInterface.setObject(5, poLine.getEstimatedDeliveryDate());
			 stmtLineInterface.executeQuery();
			 }	*/
			}
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,insertOrderConfirmation",te);
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
	   catch(SQLException se)	//Can come into this through JDBC Call
	   {
		   AppException ae = new AppException();
		   se.printStackTrace();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,insertOrderConfirmation",se);
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
			   if(stmtParty!=null)
				   stmtParty.close();
			   if(stmtLine!=null)
				   stmtLine.close();
			   if(conn!=null)
				   conn.close();
			   if(clientSession!=null)
			      clientSession.release();
		   } 
		   catch(Throwable tu) 
		   { 
			   throw new RuntimeException(tu); 
		   }
		   
	   }
	   
	   return poNumber;
   }
   
   /**
    * Gets the Purchase Order Line Items for Supplied Components
    * @param poId
    * @param poVersion
    * @return java.util.Collection
    */
   public Collection getOrderLineItems(BigDecimal poId, BigDecimal poVersion) throws AppException   
   {
	   Session clientSession = null;
	   Vector lineItems = null;
	   try
	   {
		   clientSession = getClientSession();
		   ExpressionBuilder builder = new ExpressionBuilder();
		   Expression wcPOSuppliedComp = builder.get("poId").equal(poId).and(
				   builder.get("poVersion").equal(poVersion));
		   lineItems = (Vector)clientSession.readAllObjects(POLine.class,wcPOSuppliedComp);
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,getOrderLineItems",te);
		   throw ae;
	   }
	   finally
	   {
		   if(clientSession!=null)
		   {
			   clientSession.release();
		   }
	   }
	   
	   return lineItems;
   }
   
   /**
    * Inserts multiple Purchase Orders into database for Acceptance
    * 
    * @param idVersionString
    * @param login
    * @return String 
    */
   public String insertMultiplePOAccept(String idVersionString,String login) throws AppException
   {
	   Session clientSession = null;
	   try
	   {
		   clientSession = getClientSession();
		   StoredProcedureCall call = new StoredProcedureCall(); 
		   call.setProcedureName("Pix_split_proc");
		   call.addNamedArgumentValue("v_in",idVersionString);
		   call.addNamedArgumentValue("v_user",login);
		   clientSession.executeNonSelectingCall(call);
	   }
	   catch(TopLinkException te)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"PurchaseOrderDAOImpl,insertMultiplePOAccept",te);
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
