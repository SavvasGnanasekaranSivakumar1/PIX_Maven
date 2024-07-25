package com.pearson.pix.dao.mismatchreport;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.mismatchreport.MismatchDetailsDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

public class MismatchReportDAOImpl extends BaseDAO implements MismatchReportDAO {
	
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(MismatchReportDAOImpl.class.getName());
   
	
   public MismatchReportDAOImpl() 
   {
    
   }

	public Collection getMismatchReportDetails(String poNumber) throws AppException {
		 Session objSession = getClientSession();
		   MismatchDetailsDTO objMismatchDetail = null;
		   Vector objListVector = new Vector();
		   try
		   {
			   Record objRecord = null;
			   objMismatchDetail = this.getPOHederDetails(poNumber);
			   if(objMismatchDetail!=null){
			   objListVector.add(0,objMismatchDetail);
			   StoredProcedureCall call = new StoredProcedureCall();			   
			   call.setProcedureName("pix_pms_mismatch_report_proc");
	           call.addNamedArgumentValue("i_po_no",poNumber); 
	           call.addNamedArgumentValue("i_request",null); 
	           call.useNamedCursorOutputAsResultSet("o_cusror");	           
	           Vector objVector = (Vector)objSession.executeSelectingCall(call);	                     
	           if (objVector != null && objVector.size()>0)
	           {
	          	   for (int i = 0; i < objVector.size(); i++)
	        	   {
	           		   objRecord = (Record)objVector.get(i);
	          		   objMismatchDetail = new MismatchDetailsDTO();
	          		   objMismatchDetail.setPoLineNo((BigDecimal)objRecord.get("PO_LINE_NO"));	
	          		   objMismatchDetail.setMaterialNo((String)objRecord.get("MATERIAL_NO"));
	          		   objMismatchDetail.setSapPlantCode((String)objRecord.get("SAP_PLANT_CODE"));
	          		   objMismatchDetail.setRequestedQty((BigDecimal)objRecord.get("REQUESTED_QTY"));
	          		   //System.out.println("Req QTY:"+objRecord.get("REQUESTED_QTY"));
	          		   objMismatchDetail.setGrQty((BigDecimal)objRecord.get("GR_QTY"));
	          		   objMismatchDetail.setGrDoc((String)objRecord.get("GR_DOC_NO"));
	          		   objMismatchDetail.setDmQty((BigDecimal)objRecord.get("DM_QTY"));
	          		   objMismatchDetail.setDmDoc((String)objRecord.get("DM_DOC_NO"));
	          		   objMismatchDetail.setVarianceQty((BigDecimal)objRecord.get("QTY_VARIANCE"));
	        		  objListVector.add(objMismatchDetail);
	        	   }	          	 
	           }        
	           else if(objVector.size()== 0 || objVector == null )
	           {
	        	     AppException appException = new AppException();	        	    
	  				 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_MISMATCHREPORT_DETAIL,"MismatchReportDAOImpl");  
	  				 throw appException;
	           }
	           
			}
			  
		   }
		   catch(TopLinkException e)
		   {
			   log.debug("In  MismatchReportDAOImpl getMismatchReportDetails");			  
			   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "MismatchReportDAOImpl,getMismatchReportDetails",e);
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
	
	
	private MismatchDetailsDTO getPOHederDetails(String poNumber) throws AppException{
		 Session objSession = getClientSession();
		   MismatchDetailsDTO objMismatchDetail  = null;
		   try
		   {
			   Record objRecord = null;
			   StoredProcedureCall call = new StoredProcedureCall();			   
			   call.setProcedureName("pix_pms_mismatch_report_proc");
	           call.addNamedArgumentValue("i_po_no",poNumber); 
	           call.addNamedArgumentValue("i_request","HEADER"); 
	           call.useNamedCursorOutputAsResultSet("o_cusror");
	           Vector objVector = (Vector)objSession.executeSelectingCall(call);	           
	           if (objVector != null && objVector.size()>0)
	           {     
	           		   objRecord = (Record)objVector.get(0);
	          		   objMismatchDetail = new MismatchDetailsDTO();	
	          		   objMismatchDetail.setPoNumber((String)objRecord.get("PO_NO"));
	          		   objMismatchDetail.setMerchantName((String)objRecord.get("MILL_NAME1"));
	          		   objMismatchDetail.setMaterialNo((String)objRecord.get("MATERIAL_NO"));
	          		   objMismatchDetail.setRequestedQty((BigDecimal)objRecord.get("REQUESTED_QTY"));
	           }        
	           else if(objVector.size()== 0 || objVector == null)
	           {
	        	     AppException appException = new AppException();
	        	     appException.performErrorAction(Exceptions.PO_NOT_EXISTS_MISMATCHREPORT_DETAIL,"MismatchReportDAOImpl");
	  				 //appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_MISMATCHREPORT_DETAIL,"getPOHederDetails");  
	  				 throw appException;
	           }
		   }
		   catch(TopLinkException e)
		   {
			   log.debug("In  MismatchReportDAOImpl getPOHederDetails");			  
			   AppException appException = new AppException();
			   appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "MismatchReportDAOImpl,getPOHederDetails",e);
			   throw appException;
		   }
		   finally
		   {
			   if(objSession!=null)
			   {				  
				  objSession.release();
			   }
		   }    
		  return objMismatchDetail;
	}

}
