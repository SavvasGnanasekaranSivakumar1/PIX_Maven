package com.pearson.pix.business.inventory.ejb;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.inventory.InventoryDAO;
import com.pearson.pix.exception.AppException;
import java.util.Collection;
import javax.ejb.*;
import java.math.BigDecimal;

/**
 * Stateless session bean containing all the business methods of the Inventory
 * @author Sudam.Sahu
 */
public class InventoryEJB implements SessionBean
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for initializing InventoryEJB
     * @J2EE_METHOD  --  InventoryEJB
      */
    public InventoryEJB    ()  
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
     * Business method for displaying the Inventory Detail
     * @param isbn
     * @param san
     * @param rawMaterialNumber
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayInventory
     */
    public Collection displayInventory(String isbn,String isbn13, String rawMaterialNumber,String san)  throws AppException
    { 
    	Collection objCollection = null;
    	InventoryDAO objInventoryDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objInventoryDAO = objDAOFactory.getInventoryDAO();
    	objCollection =(Collection)objInventoryDAO.getInventory(isbn,isbn13,rawMaterialNumber,san);
    	return objCollection;
    }
    
    /**
     * Business method for saving the information for Inventory.
     * @J2EE_METHOD  --  saveInventory
     * @return String
     */
    public String saveInventory(Integer printNumber,String san,String createdBy,Collection inventoryCollection,BigDecimal inventoryId,BigDecimal inventoryVersion) throws AppException 
    { 
    	InventoryDAO objInventoryDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objInventoryDAO = objDAOFactory.getInventoryDAO();
    	objInventoryDAO.insertInventory(printNumber,san,createdBy,inventoryCollection,inventoryId,inventoryVersion);
    	return "";
    }
    
    /**
     * Business method to get party name to display in the inventory search page
     * @J2EE_METHOD  --  displayPartyName
     * @return java.util.Collection
     */
    public Collection displayPartyName(int userId)throws AppException
    {
    	
        Collection objCollection = null;
        InventoryDAO objInventoryDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objInventoryDAO = objDAOFactory.getInventoryDAO();
        objCollection = (Collection)objInventoryDAO.getPartyName(userId);
        return objCollection;
        }
}