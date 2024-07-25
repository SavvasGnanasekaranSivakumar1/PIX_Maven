package com.pearson.pix.dao.home;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;
import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.queryframework.ValueReadQuery;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.common.Inbox;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pearson.pix.dto.admin.*;


/**
 * Implementation of Data Access Object containing all the methods required for
 * DB access through Toplink.
 * 
 * @author faisalk
 */
public class HomePageDAOImpl extends BaseDAO implements HomePageDAO {

	/**
	 * Logger.
	 */
	private static Log log = LogFactory.getLog(HomePageDAOImpl.class.getName());

	/**
	 * Constructor for initializing HomePageDAOImpl
	 */
	public HomePageDAOImpl() {

	}

	/**
	 * Gets the Inbox Details from the database
	 * 
	 * @return java.util.Collection
	 */
	public Collection getInboxDetail(int userId) throws AppException {
		Session clientSession = null;
		Inbox objInbox = null;
		Status objStatus = null;
		Reference objRef = null;
		Vector inboxdetails = new Vector();
		Vector objInboxdetail = null;
		Number result = null;
		try {
			Record objRecord = null;
			clientSession = getClientSession();
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("pix_inbox_proc");
			call.addNamedArgumentValue("i_user_id", new Integer(userId));
			call.addNamedOutputArgument("o_days");
			call.useNamedCursorOutputAsResultSet("list_refcursor");
			call.setUsesBinding(true);
			ValueReadQuery query = new ValueReadQuery();
			clientSession.logMessages();
			query.setCall(call);
			query.bindAllParameters();
			query.addArgument("i_user_id");
			Vector params = new Vector();
			params.addElement(new Integer(5));
			result = (Number) clientSession.executeQuery(query, params);

			objInboxdetail = (Vector) clientSession.executeSelectingCall(call);

			int inboxSize = objInboxdetail.size();

			if (objInboxdetail != null && inboxSize > 0)// Checking vector
														// returned and size for
														// null value
			{
				for (int i = 0; i < inboxSize; i++)// Iterating through the
													// inbox result vector
				{
					objInbox = new Inbox();
					objStatus = new Status();
					objRef = new Reference();
					objRecord = (Record) objInboxdetail.get(i);

					objInbox.setItemTypeId((BigDecimal) objRecord
							.get(PIXConstants.MODULE_ID));
					objInbox.setCount((BigDecimal) objRecord
							.get(PIXConstants.SUM));
					objInbox.setDisplayOrder((BigDecimal) objRecord
							.get(PIXConstants.DISPLAY_ORDER));
					objStatus.setStatusDescription((String) objRecord
							.get(PIXConstants.STATUS_DESCRIPTION));
					if (((BigDecimal) objRecord.get(PIXConstants.STATUS_ID) != null)) {
						objStatus.setStatusId(new Integer(
								((BigDecimal) objRecord
										.get(PIXConstants.STATUS_ID))
										.intValue()));
					}
					objInbox.setStatusDetail(objStatus);
					objRef.setDescription((String) objRecord
							.get(PIXConstants.DESC));
					objInbox.setItemTypeDetail(objRef);
					objInbox.setDays(result);
					inboxdetails.add(objInbox);

				}
			}

		} catch (TopLinkException e) {
			log
					.debug("TopLinkException while fetching inbox details for Home Page");
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.SQL_EXCEPTION,
					"HomePageDAOImpl,getInboxDetail", e);
			throw appException;
		} finally {
			if (clientSession != null) {
				clientSession.release();
			}
		}
		return inboxdetails;
	}

	/**
	 * Gets the PO Details from the database
	 * 
	 * @return java.util.Collection
	 */
	public String getPODetails(String poNo, User user) throws AppException {
		Session clientSession = null;
		Vector msgVector = new Vector();
		String message = "";
		
		
		try {
			clientSession = getClientSession();
			StoredProcedureCall call = new StoredProcedureCall();
			User objUser = (User)user;
			int userId = objUser.getUserId().intValue();
			String roleType = objUser.getRoleTypeDetail().getRoleType();
			//System.out.println("roleType="+roleType+"userId ="+userId+"pono "+poNo);
			//Gaurav
			//set procedure name
			call.setProcedureName("PIX_PO_INFO");
			call.addNamedArgumentValue("i_user_id", new Integer(userId));
			call.addNamedArgumentValue(" i_role_type", roleType);
			call.addNamedArgumentValue("i_po_no", poNo);
			call.useNamedCursorOutputAsResultSet("list_refcursor");
			msgVector = (Vector) clientSession.executeSelectingCall(call);
						
			for (int i = 0; i < msgVector.size(); i++)
			 {   
				Record objDatabaseRecord = (Record)msgVector.get(i);
				BigDecimal po_id= (BigDecimal)objDatabaseRecord.get(PIXConstants.PO_ID);
				BigDecimal po_ver= (BigDecimal)objDatabaseRecord.get(PIXConstants.PO_VERSION);
				String rel_no= (String)objDatabaseRecord.get(PIXConstants.RELEASE_NO);
				String po_number = (String)objDatabaseRecord.get("PO_NO");
				String order_type = (String)objDatabaseRecord.get("ORDER_TYPE");
				String order = (String)objDatabaseRecord.get("PAGE");
				
				message = po_id.toString()+","+po_ver.toString()+","+rel_no+","+po_number+","+order_type+","+order;
			 }
			// message = msgVector.toString();
            //System.out.println("Gaurav"+message);
			//System.out.println("output as returned "+message );
            
            }catch (TopLinkException e) {
			log.debug("TopLinkException while fetching PO details for Home Page");
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.SQL_EXCEPTION,
					"HomePageDAOImpl,getPODetails", e);
			throw appException;
		} finally {
			if (clientSession != null) {
				clientSession.release();
			}
		}
		return message;
	}
}