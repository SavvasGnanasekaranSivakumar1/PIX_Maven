package com.pearson.pix.business.usage.ejb;

import java.util.Collection;
import java.util.HashMap;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.usage.UsageDAO;
import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.exception.AppException;

import javax.ejb.*;

/**
 * Stateless session bean containing all the business methods of the Usage
 */
public class UsageEJB implements SessionBean
{
    
    /**
     * Constructor for initializing UsageEJB
     * @J2EE_METHOD  --  UsageEJB
     */
    public UsageEJB    ()  
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
     * Business method for displaying the Usage List. 
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayUsageList
     */
    public Collection displayUsageList (Usage objUsage,String startDate,String endDate,String partyType,int userId,String page,int pagination,String orderby,String sort) throws AppException  
    { 
        Collection objCollection = null;
        UsageDAO objUsageDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objUsageDAO = objDAOFactory.getUsageDAO();
    	objCollection = objUsageDAO.getUsageList(objUsage,startDate,endDate,partyType,userId,page,pagination,orderby,sort);
    	
    	return objCollection;
    }
    
    /**
     * Business method for displaying the Usage Status List. 
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayUsageStatus
     */
    public Collection displayUsageStatus() throws AppException
    {
    	Collection objCollection = null;
    	UsageDAO objUsageDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objUsageDAO = objDAOFactory.getUsageDAO();
    	objCollection = (Collection)objUsageDAO.displayUsageStatus();
    	
    	return objCollection;
    	
    }
    
    /**
     * Business method for displaying the Usage Detail
     * 
     * @param poNumber
     * @param usageNumber
     * @param version
     * @return com.pearson.pix.dto.usage.Usage
     * @J2EE_METHOD  --  displayUsageDetail
     */
    public HashMap displayUsageDetail(Integer poNumber, Integer poVersion, Integer usageId) throws AppException  
    { 
    	UsageDAO objUsageDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objUsageDAO = objDAOFactory.getUsageDAO();
    	HashMap objHashMap= objUsageDAO.getUsageDetail(poNumber,poVersion,usageId);
    	
     return objHashMap;
    }
    
    /**
     * Business method for saving the information for Usage.
     * 
     * @param objUsage
     * @J2EE_METHOD  --  saveUsage
     */
    public String saveUsage(Usage objUsage,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String objUser)  throws AppException  
    { 
    	String usageNo=null;
    	UsageDAO objUsageDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objUsageDAO = objDAOFactory.getUsageDAO();
    	usageNo= objUsageDAO.insertUsage(objUsage,objPoId,objPoVersion,objPoNo,objRno,objUser);
    	
    	return usageNo;
    }
}