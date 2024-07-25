package com.pearson.pix.business.deliverymessage.ejb;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.ejb.*;

public class DeliveryMessageEJB
    implements SessionBean
{

    private static final long serialVersionUID = 1L;

    public DeliveryMessageEJB()
    {
    }

    public void setSessionContext(SessionContext sessioncontext)
        throws EJBException
    {
    }

    public void ejbRemove()
        throws EJBException, RemoteException
    {
    }

    public void ejbActivate()
        throws EJBException, RemoteException
    {
    }

    public void ejbPassivate()
        throws EJBException, RemoteException
    {
    }

    public void ejbCreate()
        throws CreateException
    {
    }

    public void replaceWithRealBusinessMethod()
        throws EJBException
    {
    }

    public Collection displayDeliveryMessageList(POHeader poHeaderTemp, int pagination, String objorderby, String objsort, String party, Integer userId)
        throws AppException
    {
        Collection objCollection = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        objCollection = objDeliveryMessageDAO.getDeliveryMessageList(poHeaderTemp, pagination, objorderby, objsort, party, userId);
        return objCollection;
    }

	// roleTracking
	public int getRoleTrackingFlag(String login_Id) throws AppException
	{
		DeliveryMessageDAO deliveryMessageDAO = null;
    //  HashMap objHashMap = new HashMap();
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	deliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    	int roleTrackingFlag = deliveryMessageDAO.getRoleTrackingFlag(login_Id);
    	
		return roleTrackingFlag;
	}

    
    public DeliveryMessage displayDeliveryMessageDetail(BigDecimal poId, BigDecimal poVersion, Integer messageId, String orderType,String ownrshpMode)
        throws AppException
    {
        DeliveryMessage objDeliveryMessage = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        objDeliveryMessage = objDeliveryMessageDAO.getDeliveryMessageDetail(poId, poVersion, messageId, orderType,ownrshpMode);
        return objDeliveryMessage;
    }

    public HashMap displayBasicDeliveryMessageInfo(BigDecimal poId, BigDecimal poVersion, String orderType)
        throws AppException
    {
        HashMap objHashMap = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        objHashMap = objDeliveryMessageDAO.getBasicDeliveryMessageInfo(poId, poVersion, orderType);
        return objHashMap;
    }

    public String saveDeliveryMessage(DeliveryMessage objDeliveryMessage, POHeader poHeaderTemp, int size, int headerSize, String objUser, String roleType)
        throws AppException
    {
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        return objDeliveryMessageDAO.insertDeliveryMessage(objDeliveryMessage, poHeaderTemp, size, headerSize, objUser, roleType);
    }

    public Collection inboxDeliveryMessagelist(Integer days, Integer user_id, int currentValue, String objorderby, String objsort)
        throws AppException
    {
        Collection objCollection = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        objCollection = objDeliveryMessageDAO.inboxDeliveryMessageList(days, user_id, currentValue, objorderby, objsort);
        return objCollection;
    }
    
    public Collection inboxDeliveryMessageMilllist(Integer user_id, int currentValue, String objorderby, String objsort)
    throws AppException
{
    Collection objCollection = null;
    DeliveryMessageDAO objDeliveryMessageDAO = null;
    DAOFactory objDAOFactory = DAOFactory.newInstance();
    objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    objCollection = objDeliveryMessageDAO.inboxDeliveryMessageMillList(user_id, currentValue, objorderby, objsort);
    return objCollection;
}

    public String displayPurchaseOrderStatus(POHeader poHeaderTemp)
        throws AppException
    {
        String status = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        status = objDeliveryMessageDAO.getPurchaseOrderStatus(poHeaderTemp);
        return status;
    }

    public String getPmsLineQuantity(DeliveryMessage objDeliveryMessage,String ponumber ,int size)
        throws AppException
    {
        String PmsQuantity = null;
        DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        PmsQuantity = objDeliveryMessageDAO.getPmsLineQuantity(objDeliveryMessage,ponumber ,size);
        return PmsQuantity;
    }
    
    public String getPmsPoHeaderStatus(BigDecimal poId)
    throws AppException
    {
    	String PmsHeaderStatus = null;
    	DeliveryMessageDAO objDeliveryMessageDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    	PmsHeaderStatus = objDeliveryMessageDAO.getPmsPoHeaderStatus(poId);
    	return PmsHeaderStatus;
    }
    public String insertMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId)throws AppException
    {
    	DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        return objDeliveryMessageDAO.insertMillDeliveryMessage(objDeliveryMessage,poHeaderTemp,userId);
    	
    }
    public Collection getDeliveryMessageHistory(POHeader poHeaderTemp,int pagination,String lineNo,String requestType,int userId) throws AppException
    {
    	DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        return objDeliveryMessageDAO.getDeliveryMessageHistory(poHeaderTemp,pagination,lineNo,requestType,userId);
    	
    }
    public String updateMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId) throws AppException{
    	DeliveryMessageDAO objDeliveryMessageDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
        return objDeliveryMessageDAO.updateMillDeliveryMessage(objDeliveryMessage,poHeaderTemp,userId);
    }

    // costuser
    
    public Vector cuOwnedQtyPopup(String msgId, String ownershipMode, String pono)
    throws AppException
{
    DeliveryMessage objDeliveryMessage = null;
    Vector cuVec = null;
    DeliveryMessageDAO objDeliveryMessageDAO = null;
    DAOFactory objDAOFactory = DAOFactory.newInstance();
    objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    cuVec = objDeliveryMessageDAO.cuOwnedQtyPopup(msgId, ownershipMode, pono);
    return cuVec;
}

    public int printerRtDmNrtHist(String msgId, String DMGRMode)
    throws AppException
{
    DeliveryMessage objDeliveryMessage = null;
    Vector cuVec = null;
    DeliveryMessageDAO objDeliveryMessageDAO = null;
    DAOFactory objDAOFactory = DAOFactory.newInstance();
    objDeliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    return objDeliveryMessageDAO.printerRtDmNrtHist(msgId, DMGRMode);
}   
    
}
