package com.pearson.pix.business.planning.ejb;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.planning.PlanningDAO;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.*;

/**
 * Stateless session bean containing all the business methods of the Planning
 */
public class PlanningEJB implements SessionBean
{

	 public static final long serialVersionUID = 001011L;
	/**
     * Attributes declaration
    */
    
    /**
     * SessionContext variable
     */
    public SessionContext EJB_Context = null;
    
    /**
     * Constructor for initializing PlanningEJB
     * @J2EE_METHOD  --  PlanningEJB
     */
    public PlanningEJB    ()  
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
     * Business method for displaying the Planning List. This method also takes care of
     *  filtering, pagination and history display by passing page no, filter criteria and
     *  its value, history flag respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPlanningList
     */
    public Collection displayPlanningList(POHeader objpoHeader,String startDate,String endDate,int userId,String page,int currentValue,String orderby,String sort,String sbBDate,String ebBDate,String roleType) throws AppException 
    { 
    	Collection objCollection = null;
    	
    	PlanningDAO objPlanningDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPlanningDAO = objDAOFactory.getPlanningDAO();
    	objCollection = objPlanningDAO.getPlanningsList(objpoHeader,startDate,endDate,userId,page,currentValue,orderby,sort,sbBDate,ebBDate,roleType);
    	return objCollection;
    }
    
    
    /**
     * Business method for displaying the Planning Detail
     * 
     * @return com.pearson.pix.dto.Planning
     * @J2EE_METHOD  --  displayPlanningDetail
     */
    public POHeader displayPlanningDetail(BigDecimal poId, BigDecimal poVersion,String orderType) throws AppException   
    { 
    	PlanningDAO objPlanningDAO = DAOFactory.newInstance().getPlanningDAO();
    	return objPlanningDAO.getPlanningDetail(poId,poVersion,orderType);
    }
    
    /**
     * Business method for insertion of Vendor Acknowledgement/Comments
     * 
     * @return java.util.HashMap
     * @J2EE_METHOD  --  savePlanningAcknowledgement
     */
    public String savePlanningAcknowledgement (POHeader poHeader)throws AppException  
    { 
    	PlanningDAO objPlanningDAO=DAOFactory.newInstance().getPlanningDAO();
    	return objPlanningDAO.saveVendorDetail(poHeader);
    }
    
    /**
     * Business method for the purpose of Planning History List. This method also takes
     *  care of filtering, pagination by passing page no, filter criteria and its value
     *  respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPlanningHistoryList
     */
    public Collection displayPlanningHistoryList    ()  
    { 
      return null;
    }
    
    /**
     * Business method of Planning EJB for the purpose of Planning 
     * Status information
     * 
     * @return Collection
     */
    public Collection displayPlanningStatus() throws AppException 
    { 
        Collection objCollection = null;
    	PlanningDAO objPlanningDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPlanningDAO = objDAOFactory.getPlanningDAO();
    	objCollection = (Collection)objPlanningDAO.getPlanningStatus();
    	return objCollection;
    }
    
    /**
     * Business method for Accepting multiple Planning purchase Orders
     * 
     * @param idVersionString
     * @param login
     * @return String 
     */
    public String insertMultiplePlanningPOAccept(String idVersionString,String login) throws AppException
    {
    	PlanningDAO planningDAO = DAOFactory.newInstance().getPlanningDAO();
    	return planningDAO.insertMultiplePlanningPOAccept(idVersionString,login);
    }
     
}