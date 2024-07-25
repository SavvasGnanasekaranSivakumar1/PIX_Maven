package com.pearson.pix.dao.dropship;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.DatabaseRecord;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;

import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

public class DropInstDAO extends BaseDAO {
	private static Log log = LogFactory.getLog(DropInstDAO.class.getName());
	public DropInstDTO getDropInstructionsData(DropInstDTO objDropInstDTO){
		Vector<DropInstDTO> objDropInstDTOList = new Vector<DropInstDTO>();
		Session session = null;
		UnitOfWork unitOfWork = null;
		session = getClientSession();
		try
        {
			log.info("inside try in drops impl dao isbn>>>>>"+objDropInstDTO.getIsbnNo());
			log.info("bknum>>>>>"+objDropInstDTO.getBkNumber());
			log.info("ponum>>>>>"+objDropInstDTO.getPoNumber());
			log.info("prev page>>>>>"+objDropInstDTO.getPagination());
			log.info("next page>>>>>"+objDropInstDTO.getPagination());
			
						
			session.logMessages();
	
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("PIX.PIX_DS_PROC");
			call.addNamedArgumentValue("I_PO_NO", objDropInstDTO.getPoNumber());
			call.addNamedArgumentValue("I_ISBN", objDropInstDTO.getIsbnNo());
			call.addNamedArgumentValue("I_PRINT_NO", objDropInstDTO.getPrintNo());
			call.addNamedArgumentValue("I_BK_NO", objDropInstDTO.getBkNumber());
			call.addNamedArgumentValue("I_CUST_NAME", objDropInstDTO.getCustAccountName());
			call.addNamedArgumentValue("I_CARRIER_LEVEL", objDropInstDTO.getCarrierLevel());
			call.addNamedArgumentValue("I_STATUS_ID", objDropInstDTO.getStatusId());
			call.addNamedArgumentValue("I_TYPE",objDropInstDTO.getPage());
			call.addNamedArgumentValue("I_START_DATE", objDropInstDTO.getStartDate());
			call.addNamedArgumentValue("I_END_DATE", objDropInstDTO.getEndDate());
			call.addNamedArgumentValue("I_USER_ID", objDropInstDTO.getUserId());
			call.addNamedArgumentValue("I_PAGINATION", objDropInstDTO.getPagination());
			
			call.useNamedCursorOutputAsResultSet("O_DS_REFCURSOR");
			session.logMessages();
			Vector resultVec = (Vector)session.executeSelectingCall(call);
					if(!resultVec.isEmpty()){
						for(int i=0; i < resultVec.size();i++){
							DropInstDTO dropInstDTO = new DropInstDTO();
							DatabaseRecord record = (DatabaseRecord) resultVec.elementAt(i);
									dropInstDTO.setDs_Id((BigDecimal)record.get("DS_ID"));
								    dropInstDTO.setBkNumber((String)record.get("BK#"));
								    dropInstDTO.setDocCtrlNo((String)record.get("DOC CTRL#"));
								    dropInstDTO.setIsbnNo((String)record.get("ISBN"));
								    dropInstDTO.setCustAccountName((String)record.get("CUST ACCOUNT NAME"));
								    dropInstDTO.setPoNumber((String)record.get("PO#"));
								    dropInstDTO.setVendor((String)record.get("VENDOR"));
								    dropInstDTO.setVendorContact((String)record.get("VENDOR_CONTACT"));
								    dropInstDTO.setCarrierLevel((String)record.get("CARRIER & LEVEL"));
								    dropInstDTO.setInstructionDate((Date)record.get("INSTRUCTION DATE"));
								    dropInstDTO.setTotalQty((BigDecimal)record.get("TOTAL QTY"));
								    dropInstDTO.setPkgSlipFlag((String)record.get("PCK_SLIP_DOWNLOAD_FLAG"));
								    dropInstDTO.setStatus((String)record.get("STATUS"));
								    //dropInstDTO.setPage((String)record.get("INPUTTYPE"));
								    dropInstDTO.setRn((BigDecimal)record.get("RN"));
								    dropInstDTO.setIsSchool((String)record.get("IS_SCHOOL"));
									
								    /*log.info("  BK NUM>>>"+dropInstDTO.getBkNumber());
								    log.info("  STATUS>>>"+dropInstDTO.getStatus());
								    log.info("  package flag >>>"+dropInstDTO.getPkgSlipFlag());*/
									objDropInstDTOList.add(dropInstDTO);
								
						}
					}	
					log.info("size of list>>>a"+objDropInstDTOList.size());
				    objDropInstDTO.setDropInstListSize(objDropInstDTOList.size());
					objDropInstDTO.setObjDropInstDTOList(objDropInstDTOList);			
        }
		catch (TopLinkException e) {
	    	 AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DropInstDAO,getDropInstructionsData",e);
		        try {
					throw appException;
				} catch (AppException e1) {
					e1.printStackTrace();
				}
		} finally {
			if (session != null) {
				session.release();
			}
		}
		return objDropInstDTO;
        }
	
	
	public DropInstDTO getPackingSlipDetail(DropInstDTO objdropInstDTO)throws Exception{
		Session session = null;
		DropInstDTO dropInstDTO = null;
		session = getClientSession();
		List<DropInstDTO> objDropInstDTOList = new ArrayList<DropInstDTO>();
		try
        {		
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("PIX.PIX_DS_PCK_SLIP_PROC"); 
			call.addNamedArgumentValue("I_BK_NUMBER",objdropInstDTO.getBkNumber());
			call.addNamedArgumentValue("I_USER_ID",objdropInstDTO.getUserId());
			call.useNamedCursorOutputAsResultSet("O_DS_PCK_SLIP_CURSOR");
			Vector resultVec = (Vector)session.executeSelectingCall(call);
			
					if(!resultVec.isEmpty()){
						for(int i=0; i < resultVec.size();i++){
							  DatabaseRecord record = (DatabaseRecord) resultVec.elementAt(i);
							  dropInstDTO = new DropInstDTO();
							  
								    dropInstDTO.setBkNumber((String)record.get("INVOICE NUMBER"));
								    dropInstDTO.setDocCtrlNo((String)record.get("CUSTOMER PO"));
								    dropInstDTO.setIsbnNo((String)record.get("ISBN-10"));
								    dropInstDTO.setFreightTerms((String)record.get("FREIGHT TERMS"));
								    dropInstDTO.setUnitPrice((String)record.get("UNIT COST"));
								    dropInstDTO.setIsbn13((String)record.get("ISBN-13"));
								    dropInstDTO.setTitle((String)record.get("TITLE"));
								    dropInstDTO.setAddShippingInstruction((String)record.get("ADD SHIPPING INSTRUCTIONS"));
								    dropInstDTO.setBusinessOwnerShip((String)record.get("BUSINESS_OWNERSHIP"));
								    dropInstDTO.setShipToName1((String)record.get("SHIP_TO_NAME1"));
								    dropInstDTO.setShipToAddress1((String)record.get("SHIP_TO_ADDRESS1"));
								    dropInstDTO.setShipToAddress2((String)record.get("SHIP_TO_ADDRESS2"));
								    dropInstDTO.setShipTOAddress3((String)record.get("SHIP_TO_ADDRESS3"));
								    dropInstDTO.setShipTOAddress4((String)record.get("SHIP_TO_ADDRESS4"));
								    dropInstDTO.setShipTOCity((String)record.get("SHIP_TO_CITY"));
								    dropInstDTO.setShipTOState((String)record.get("SHIP_TO_STATE"));
								    dropInstDTO.setShipTOPostalCode((String)record.get("SHIP_TO_POSTAL_CODE"));
								    dropInstDTO.setShipToCountry((String)record.get("SHIP_TO_COUNTRY"));
								    dropInstDTO.setBillToName1((String)record.get("BILL_TO_NAME1"));
								    dropInstDTO.setBillToAddress1((String)record.get("BILL_TO_ADDRESS1"));
								    dropInstDTO.setBillToAddress2((String)record.get("BILL_TO_ADDRESS2"));
								    dropInstDTO.setBillToAddress3((String)record.get("BILL_TO_ADDRESS3"));
								    dropInstDTO.setBillToAddress4((String)record.get("BILL_TO_ADDRESS4"));
								    dropInstDTO.setBillToCity((String)record.get("BILL_TO_CITY"));
								    dropInstDTO.setBillToState((String)record.get("BILL_TO_STATE"));
								    dropInstDTO.setBillToPostalCode((String)record.get("BILL_TO_POSTAL_CODE"));
								    dropInstDTO.setBillToCountry((String)record.get("BILL_TO_COUNTRY"));
								    dropInstDTO.setVendorReferenceNumber((String)record.get("VENDOR REFERENCE NUMBER"));
								    log.info("dropInstDTO.getVendorReferenceNumber()>>>>"+record.get("VENDOR REFERENCE NUMBER"));
								    // dropInstDTO.setCustAccountName((String)record.get("CUST ACCOUNT NAME"));
								    dropInstDTO.setPoNumber((String)record.get("CUSTOMER PO"));
								    dropInstDTO.setCarrierLevel((String)record.get("CARRIER"));
								    // dropInstDTO.setInstructionDate((Date)record.get("INSTRUCTION DATE"));
								    dropInstDTO.setTotalQty((BigDecimal)record.get("QUANTITY"));
								    dropInstDTO.setBillToAccNumber((String)record.get("BILL_TO_ACC_NUMBER"));
								    //dropInstDTO.setUserId((Integer)record.get("USER_ID"));
								    //dropInstDTO.setPkgSlipFlag((String)record.get("PCK_SLIP_FLAG"));
								    //dropInstDTO.setStatus((String)record.get("STATUS"));
								   // dropInstDTO.setRn((BigDecimal)record.get("RN"));
								    objDropInstDTOList.add(dropInstDTO)		;						
						}
					}
					objdropInstDTO.setObjDropInstDTOList(objDropInstDTOList)	;	
        }
		catch (TopLinkException e) {
	    	 AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DropInstDAO,getPackingSlipDetail",e);
		        try {
					throw appException;
				} catch (AppException e1) {
					e1.printStackTrace();
				}
		} finally {
			if (session != null) {
				session.release();
			}
		}
		return objdropInstDTO;
	}
	
	public DropInstDTO getStatusDropDown()throws Exception{
		Session session = null;
		DropInstDTO objdropInstDTO = new DropInstDTO();
		DropInstDTO dropInstDTO = null;
		List<DropInstDTO> objDropInstDTOList = new ArrayList<DropInstDTO>();
		try
        {	
			
			session = getClientSession();
			session.logMessages();
			String query = "SELECT STATUS_ID , STATUS_DESCRIPTION FROM PIX_STATUS_CODE WHERE TABLE_ID = 61 ORDER BY DISPLAY_ORDER";
			Vector resultVec = (Vector)session.executeSelectingCall(new SQLCall(query));
					if(!resultVec.isEmpty()){
						for(int i=0; i < resultVec.size();i++){
							  DatabaseRecord record = (DatabaseRecord) resultVec.elementAt(i);
							  dropInstDTO = new DropInstDTO();
							  
								    dropInstDTO.setStatusId(((BigDecimal)record.get("STATUS_ID")).intValue());
								    dropInstDTO.setStatus((String)record.get("STATUS_DESCRIPTION"));
								    objDropInstDTOList.add(dropInstDTO);						
						}
					}	
					objdropInstDTO.setObjDropInstDTOList(objDropInstDTOList);	
        }
		catch (TopLinkException e) {
	    	 AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.SQL_EXCEPTION,"DropInstDAO,getStatusDropDown",e);
		        try {
					throw appException;
				} catch (AppException e1) {
					e1.printStackTrace();
				}
		} finally {
			if (session != null) {
				session.release();
			}
		}
		return objdropInstDTO;
	}
}
