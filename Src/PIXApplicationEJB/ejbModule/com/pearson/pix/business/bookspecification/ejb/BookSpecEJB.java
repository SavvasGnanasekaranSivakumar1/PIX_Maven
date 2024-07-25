package com.pearson.pix.business.bookspecification.ejb;
import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dto.bookspecification.BookSpec;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import com.pearson.pix.dao.bookspecification.BookSpecDAO;
import com.pearson.pix.exception.AppException;
/**
 * Stateless session bean containing all the business methods of  BookSpec
 */
public class BookSpecEJB implements SessionBean
{
    
	private static final long serialVersionUID = 1L;
	/**
     * Attributes declaration
    */
    public SessionContext EJB_Context = null;
    
    /**
     * Constructor for initializing BookSpecEJB
     * @J2EE_METHOD  --  BookSpecEJB
     * @author Sudam.Sahu 
     */
    public BookSpecEJB    ()  
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
     * Business method for displaying the Book Specification List. This method also 
     * takes care of filtering, pagination and history display by passing page no, 
     * filter criteria and its value respectively.
     * @return java.util.Vector
     * @param BookSpec,userId,page,currentValue,orderby,sort,startDate,endDate and specNo
     * @J2EE_METHOD  --  displayBookSpecList
     */
    public Vector displayBookSpecList(BookSpec objBookSpec,int userId,String page,int currentValue,String orderby,String sort,String startdate,String enddate,String specno)throws AppException
    { 
    	Vector objCollection = null;
		BookSpecDAO objBookSpecDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objBookSpecDAO = objDAOFactory.getBookSpecDAO();
    	objCollection = objBookSpecDAO.getBookSpecList(objBookSpec,userId,page,currentValue,orderby,sort, startdate, enddate,specno);
    	return objCollection;
    }
    
    /**
     * Business method for displaying the Book Specification Detail
     * @return com.pearson.pix.dto.bookspecification.BookSpecification
     * @J2EE_METHOD  --  displayBookSpecDetail
     * @param specid,specversion
     */
    public BookSpec displayBookSpecDetail(Integer specid,Integer specversion)  throws AppException
    { 
    	BookSpec objBookSpec = null;
    	BookSpecDAO objBookSpecDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objBookSpecDAO = objDAOFactory.getBookSpecDAO();
    	objBookSpec = (BookSpec)objBookSpecDAO.getBookSpecDetail(specid,specversion);
    	return objBookSpec;
    }
    
    /**
     * Business method for displaying the Book Specification last acknowledgement versioin detail
     * @return com.pearson.pix.dto.bookspecification.BookSpecification
     * @J2EE_METHOD  --  displayBookSpecDetail
     * @param specid,specversion
     */
    public BookSpec displayBookSpecLastAckDetail(Integer specid,Integer specversion)  throws AppException
    { 
    	BookSpec objBookSpec = null;
    	BookSpecDAO objBookSpecDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objBookSpecDAO = objDAOFactory.getBookSpecDAO();
    	objBookSpec = (BookSpec)objBookSpecDAO.getBookSpecLastAckDetail(specid,specversion);
    	return objBookSpec;
    }
    /**
     * Business method for insertion of Vendor Acknowledgement/Comments
	 * @return String
     * @J2EE_METHOD  --  saveBookSpecAcknowledgement
     * @param  com.pearson.pix.dto.bookspecification.BookSpec
     */
    public String saveBookSpecAcknowledgement(BookSpec objBookSpec)throws AppException
    { 
    	BookSpecDAO objBookSpecDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objBookSpecDAO = objDAOFactory.getBookSpecDAO();
    	objBookSpecDAO.saveVendorDetail(objBookSpec);
    	return "";
    }
    
    /**
     * Displays the history for the specified Book Specification number.
     * @J2EE_METHOD  --  displayBookSpecHistList
     * @param bookSpecNumber
     * @return java.util.Collection
     */
    public Collection displayBookSpecHistList(String bookSpecNumber)  
    { 
    	return null;
    }
    
    /**
     * Business method for displaying the Book Specification status
     * @return java.util.Collection
     * @throws AppException
     */
    public Collection displayBookSpecStatus()throws AppException
    {
    	
        Collection objCollection = null;
        BookSpecDAO objBookspecDAO = null;
        DAOFactory objDAOFactory = DAOFactory.newInstance();
        objBookspecDAO = objDAOFactory.getBookSpecDAO();
        objCollection = (Collection)objBookspecDAO.getBookSpecStatus();
        return objCollection;
        }
    /**
     * Business method for inserting multiple bookspec from list page for acknowledgement
     * @return String
     * @param idVersionString,login
     * @throws AppException
     */
    public String insertMultipleBookSpecAccept(String idVersionString,String login) throws AppException
    {
    	BookSpecDAO bookspecDAO = DAOFactory.newInstance().getBookSpecDAO();
    	return bookspecDAO.insertMultipleBookSpecAccept(idVersionString,login);
    }
    }
