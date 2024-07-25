package com.pearson.pix.business.goodsreceipt.ejb;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO;
import com.pearson.pix.dao.goodsreceipt.GoodsReceiptDAO;
import com.pearson.pix.dto.goodsreceipt.GoodsReceipt;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import javax.ejb.*;

/**
 * Stateless session bean containing all the business methods of the Goods Receipt
 */
public class GoodsReceiptEJB implements SessionBean
{ 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for initializing GoodsReceiptEJB
     * @J2EE_METHOD  --  GoodsReceiptEJB
     */
    public GoodsReceiptEJB    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbCreate
     * Called by the container to create a session bean instance. Its parameters typically
     * contain the information the client uses to customize the bean instance for its use.
     * It requires a matching pair in the bean class and its home interface.
     */
    public void ejbCreate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbRemove
     * A container invokes this method before it ends the life of the session object. This
     * happens as a result of a client's invoking a remove operation, or when a container
     * decides to terminate the session object after a timeout. This method is called with
     * no transaction context.
     */
    public void ejbRemove    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbActivate
     * The activate method is called when the instance is activated from its 'passive' state.
     * The instance should acquire any resource that it has released earlier in the ejbPassivate()
     * method. This method is called with no transaction context.
     */
    public void ejbActivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbPassivate
     * The passivate method is called before the instance enters the 'passive' state. The
     * instance should release any resources that it can re-acquire later in the ejbActivate()
     * method. After the passivate method completes, the instance must be in a state that
     * allows the container to use the Java Serialization protocol to externalize and store
     * away the instance's state. This method is called with no transaction context.
     */
    public void ejbPassivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  setSessionContext
     * Set the associated session context. The container calls this method after the instance
     * creation. The enterprise Bean instance should store the reference to the context
     * object in an instance variable. This method is called with no transaction context.
     */
    public void setSessionContext(SessionContext sc)  
    { 

    }
    
    
	// roleTracking
	public int getRoleTrackingFlag(String login_Id) throws AppException
	{
	//	DeliveryMessageDAO deliveryMessageDAO = null;
		GoodsReceiptDAO goodsReceiptDAO = null;
    //  HashMap objHashMap = new HashMap();
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    //	deliveryMessageDAO = objDAOFactory.getDeliveryMessageDAO();
    	goodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	int roleTrackingFlag = goodsReceiptDAO.getRoleTrackingFlag(login_Id);
    	
		return roleTrackingFlag;
	}

    
    
    
    /**
     * Business method for displaying the Goods Receipt List
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayGoodsReceiptList
     */
    public Collection displayGoodsReceiptList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId) throws AppException 
    { 
    	Collection objCollection = null;
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	objCollection = objGoodsReceiptDAO.getGoodsReceiptList(poHeaderTemp,pagination,objorderby,objsort,userId);
        return objCollection;
    }
    
    /**
     * Business method for displaying the Goods Receipt List
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayNewPaperGoodsReceipt
     */
    public Collection displayNewPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId, String ponumber) throws AppException 
    { 
    	Collection objCollection = null;
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	objCollection = objGoodsReceiptDAO.getNewPaperGoodsReceipt(poHeaderTemp,pagination,userId, ponumber);
        return objCollection;
    }
    
    /**
     * Business method for displaying the Goods Receipt List
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayGoodsReceiptList
     */
    public Collection displayHistoryPaperGoodsReceipt(POHeader poHeaderTemp,int pagination,Integer userId,String msgId,String actionKey) throws AppException 
    { 
    	Collection objCollection = null;
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	objCollection = objGoodsReceiptDAO.getPaperGoodsHistory(poHeaderTemp,pagination,userId,msgId,actionKey);
        return objCollection;
    }
    
    /**
     * Business method for displaying the Goods Receipt Detail
     * 
     * @param poNumber
     * @param receiptNumber
     * @param orderPaper //in case of Paper FO
     * @param roleType //in case mill user is logged in
     * @return com.pearson.pix.dto.goodsreceipt.GoodsReceipt
     * @J2EE_METHOD  --  displayGoodsReceiptDetail
     */
    public GoodsReceipt displayGoodsReceiptDetail(String poNumber,Integer receiptId,String orderPaper,String roleType) throws AppException 
    { 
    	GoodsReceipt objGoodsReceipt = null;
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	objGoodsReceipt = objGoodsReceiptDAO.getGoodsReceiptDetail(poNumber,receiptId,orderPaper,roleType);
    	return objGoodsReceipt;
    }
    
    /**
     * Business method for displaying the Basic Information for Goods Receipt 
     * submit.
     * 
     * @param poNumber
     * @param String paperFo
     * @param userId
     * @param String msgId for paper FO
     * @return java.util.HashMap
     * @J2EE_METHOD  --  displayBasicGoodsReceiptInfo
     */
    public java.util.HashMap displayBasicGoodsReceiptInfo(POHeader poHeaderTemp,String objUser,GoodsReceipt objGoodsReceipt,String paperFo,Integer userId,String msgId,String msgLineNo) throws AppException 
    { 
    	HashMap objHashMap = null;
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	if("paperfo".equals(paperFo))
    	{
    		objHashMap = objGoodsReceiptDAO.getBasicGoodsReceiptPaperInfo(poHeaderTemp,objUser,objGoodsReceipt,userId,msgId,msgLineNo);
    	}
    	else
    	{
    		objHashMap = objGoodsReceiptDAO.getBasicGoodsReceiptInfo(poHeaderTemp,objUser,objGoodsReceipt);
    	}
    	return objHashMap;
    }
    
    /**
     * Business method for saving the information for Goods Receipt.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceipt
     */
    public String saveGoodsReceipt(GoodsReceipt objGoodsReceipt,POHeader poHeaderTemp,int size,String objUser,String msgId,String paperFo) throws AppException  
    { 
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	if("paperfo".equals(paperFo))
    	{
    		return objGoodsReceiptDAO.insertGoodsReceiptPaperFo(objGoodsReceipt,poHeaderTemp,size,objUser,msgId);
    	}
    	else
    	{
    		return objGoodsReceiptDAO.insertGoodsReceipt(objGoodsReceipt,poHeaderTemp,size,objUser);
    	}
    	
    }
    
    /**
     * Business method for saving the information for Goods Receipt.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceiptHistory
     * @author anshu.bhardwaj
     */
    public String saveGoodsReceiptHistory(Collection goodsReceiptVector,POHeader poHeaderTemp,String objUser) throws AppException  
    { 
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	
    	return objGoodsReceiptDAO.insertGoodsReceiptHistory(goodsReceiptVector,poHeaderTemp,objUser);    	    	
    }
    
    
    /**
     * Business method for saving the information for Goods Receipt.
     * @param objGoodsReceipt
     * @J2EE_METHOD  --  saveGoodsReceiptHistory
     * @author anshu.bhardwaj
     */
    public Vector getGoodsReceiptRollInfo(String poNumber,String productCode, String DMGRMode) throws AppException 
    {   
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	
    	return objGoodsReceiptDAO.getGoodsReceiptRollInfo(poNumber,productCode, DMGRMode);      	    	
    }
    
    public int printerRtDmNrtHist(String msgId, String DMGRMode) throws AppException 
    {   
    	GoodsReceiptDAO objGoodsReceiptDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objGoodsReceiptDAO = objDAOFactory.getGoodsReceiptDAO();
    	
    	return objGoodsReceiptDAO.printerRtDmNrtHist(msgId, DMGRMode);      	    	
    }
    
}