package com.pearson.pix.business.orderstatus.ejb;


import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.orderstatus.OrderStatusDAO;
import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.exception.AppException;

import javax.ejb.*;

/**
 * Stateless session bean containing all the business methods of the Order Status
 */
public class OrderStatusEJB implements SessionBean
{
    
    /**
     * Constructor for initializing OrderStatusEJB
     * @J2EE_METHOD  --  OrderStatusEJB
     */
    public OrderStatusEJB    ()  
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
    public void setSessionContext    (SessionContext sc)  
    { 

    }
    
    /**
     * Business method for displaying the Order Status List
     * @param poId
     * @param poVersion
     * @param Pagination
     * @param OrderBy
     * @param Sort
     * @return java.util.Vector
     * @J2EE_METHOD  --  displayOrderStatusList
     */
    public Vector displayOrderStatusList(Integer objPoId,Integer objPoVersion,Integer objPagination,String objOrderBy,String objSort) throws AppException  
    { 
    	     	 
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	OrderStatusDAO objOrderStatusDAO = objDAOFactory.getOrderStatusDAO();
    	Vector objVector=objOrderStatusDAO.getOrderStatusList(objPoId,objPoVersion,objPagination,objOrderBy,objSort);
    	
    	return objVector;
    }
    
    /**
     * Business method for displaying the Order Status Detail
     * 
     * @param poId
     * @param poVersion
     * @param statusId
     * @return com.pearson.pix.dto.orderstatus.OrderStatus
     * @J2EE_METHOD  --  displayOrderStatusDetail
     */
    public Vector displayOrderStatusDetail(Integer poId,Integer poVersion, String statusId) throws AppException 
    { 
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	OrderStatusDAO objOrderStatusDAO = objDAOFactory.getOrderStatusDAO();
    	Vector objVectorDetail=objOrderStatusDAO.getOrderStatusDetail(poId,poVersion,statusId);
    	
    	return objVectorDetail;
    }
    
    /**
     * Business method for displaying the Basic Information for Order Status submit.
     * 
     * @param poId
     * @param poVersion
     * @return java.util.HashMap
     * @J2EE_METHOD  --  displayBasicOrderStatusInfo
     */
    public java.util.HashMap displayBasicOrderStatusInfo(Integer poId,Integer poVersion) throws AppException  
    { 
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	OrderStatusDAO objOrderStatusDAO = objDAOFactory.getOrderStatusDAO();
    	HashMap objHashMap=objOrderStatusDAO.getBasicOrderStatusInfo(poId,poVersion);
    	
    	return objHashMap;
    }
    
    /**
     * Business method for saving the information for Order Status.
     * @param OrderStatus
     * @param objPoId
     * @param PoVersion
     * @param PoNo
     * @param Rno
     * @param User
     * @return java.lang.String
     * @J2EE_METHOD  --  saveOrderStatus
     */
    public String saveOrderStatus(OrderStatus objOrderStatus,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String objUser) throws AppException   
    { 
    	String objStatusNo=null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	OrderStatusDAO objOrderStatusDAO = objDAOFactory.getOrderStatusDAO();
    	objStatusNo = objOrderStatusDAO.insertOrderStatus(objOrderStatus,objPoId,objPoVersion,objPoNo,objRno,objUser);
    	return objStatusNo;
    }
}