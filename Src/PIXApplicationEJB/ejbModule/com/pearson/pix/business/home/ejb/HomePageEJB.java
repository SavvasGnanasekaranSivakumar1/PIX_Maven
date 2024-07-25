package com.pearson.pix.business.home.ejb;

import java.util.Collection;
import java.util.Vector;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.home.HomePageDAO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.dto.admin.*;

public class HomePageEJB implements SessionBean
{
      
	/**
     * Constructor for initializing HomePageEJB
     * @J2EE_METHOD  --  HomePageEJB
     */
    public HomePageEJB()  
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
     * Business method for displaying the Home Page inbox
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayInboxdetail
     */
    public Collection displayInboxdetail(int userId) throws AppException 
    {
    	HomePageDAO objHomePageDAO = DAOFactory.newInstance().getHomePageDAO();
    	return objHomePageDAO.getInboxDetail(userId);
    	
    
    }
    /**
     * Business method for fetching the PO details for home page search
     * @return java.lang.String
     * @J2EE_METHOD  --  getPODetails
     */
    public String getPODetails(String poNo,User user) throws AppException 
    {
    	HomePageDAO objHomePageDAO = DAOFactory.newInstance().getHomePageDAO();
    	return objHomePageDAO.getPODetails(poNo,user);
     }
}
