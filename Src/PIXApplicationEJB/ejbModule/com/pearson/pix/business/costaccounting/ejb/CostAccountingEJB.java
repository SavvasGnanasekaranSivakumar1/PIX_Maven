package com.pearson.pix.business.costaccounting.ejb;


import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.costaccounting.CostAccountingDAO;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO;
import com.pearson.pix.dao.planning.PlanningDAO;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;



/**
 * @author shweta gupta
 * XDoclet-based session bean.  The class must be declared
 * public according to the EJB specification.
 *
 * To generate the EJB related files to this EJB:
 *		- Add Standard EJB module to XDoclet project properties
 *		- Customize XDoclet configuration for your appserver
 *		- Run XDoclet
 *
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="CostAccounting"
 *           display-name="Name for CostAccounting"
 *           description="Description for CostAccounting"
 *           jndi-name="ejb/CostAccounting"
 *           type="Stateless"
 *           view-type="local"
 */
public class CostAccountingEJB implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The session context */
	

	public CostAccountingEJB() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set the associated session context. The container calls this method 
	 * after the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
		throws EJBException {
	
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	
	  public void ejbCreate() throws javax.ejb.CreateException
	   {
	   }

	/**
	 * An example business method
	 *
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void replaceWithRealBusinessMethod() throws EJBException {
		// rename and start putting your business logic here
	}
	
	/**
     * Business method for displaying the Delivery Message Approval List
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  getDmApprovalList
     */
    public Collection getDmApprovalList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId,String materialNoFilter,String delMsgNoFilter,String millMerchantFilter,String printerFilter,String startDate,String endDate) throws AppException 
    { 
    	Collection objCollection = null;
    	CostAccountingDAO objCostAccountingDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objCostAccountingDAO = objDAOFactory.getCostAccountingDAO();
    	objCollection = objCostAccountingDAO.getDmApprovalList(poHeaderTemp,pagination,objorderby,objsort,userId,materialNoFilter,delMsgNoFilter,millMerchantFilter,printerFilter,startDate,endDate);
    	return objCollection;
    }
    
    /**
     * Business method for Approving multiple Delivery Messages
     * 
     * @param idVersionString
     * @param userId
     * @return String 
     */
    public String insertMultipleDMApprove(String idVersionString,Integer userId,String ownrshpMode) throws AppException
    {
    	CostAccountingDAO objCostAccountingDAO = DAOFactory.newInstance().getCostAccountingDAO();
    	return objCostAccountingDAO.insertMultipleDMApprove(idVersionString,userId,ownrshpMode);
    }
    
    /**
     * Calls corresponding Business method of costAccounting EJB for the purpose of 
     * getting dropdown values for filter
     * 
     * @return java.util.LinkedHashMap
     */
    
    public LinkedHashMap getFilterDropDowns(Integer userId) throws AppException
    {
    	CostAccountingDAO objCostAccountingDAO = DAOFactory.newInstance().getCostAccountingDAO();
    	return objCostAccountingDAO.getFilterDropDowns(userId);
    }
    
    /**
     * method for the purpose of updating the quantity of delivery message detail.
     * 
     
     */
    
    public String updateDeliveryMessageLine(DeliveryMessage objDeliveryMessage,Integer messageId , BigDecimal poId , BigDecimal poVersion,Integer userId) throws AppException
    {
    	CostAccountingDAO objCostAccountingDAO = DAOFactory.newInstance().getCostAccountingDAO();
    	return objCostAccountingDAO.updateDeliveryMessageLine(objDeliveryMessage,messageId,poId,poVersion,userId);
    	
    }
	
    
}
