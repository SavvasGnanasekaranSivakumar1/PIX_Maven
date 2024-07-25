package com.pearson.pix.business.admin.ejb;

import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.HashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.admin.AdminDAO;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserCountDTO;

import com.pearson.pix.exception.AppException;



/**
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
 * @ejb.bean name="Admin"
 *           display-name="Name for Admin"
 *           description="Description for Admin"
 *           jndi-name="ejb/Admin"
 *           type="Stateless"
 *           view-type="local"
 * @author shweta.g
 */
public class AdminEJB implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminEJB() {
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
     * Method to get the basic information of user to be shown intially in drop-downs etc.
     * 
     * @return java.util.HashMap
     * @J2EE_METHOD  --  getBasicUserInfo
     */
    public java.util.HashMap getBasicUserInfo() throws AppException  
    { 
    	AdminDAO objAdminDAO = null;
        HashMap objHashMap = new HashMap();
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	objHashMap.put(PIXConstants.COUNTRY_LIST,getBasicPartyInfo());
    	objHashMap.put(PIXConstants.ROLE_TYPE_LIST,objAdminDAO.getBasicUserInfo());
    	return objHashMap;
    }
    
    /**
     * Method to get the basic module information of user to be shown 
     * @return java.util.Collection
     * @J2EE_METHOD  --  getUserModuleInfo
     */
    public Vector getUserModuleInfo() throws AppException  
    { 
    	AdminDAO objAdminDAO = null;
        Vector objVector = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	objVector = objAdminDAO.getUserModuleInfo();
    	return objVector;
    }
    
    /**
     * Saves the Details of the user
     * 
     * @param objUser
     * @return java.lang.String
     * @J2EE_METHOD  --  saveUserDetail
     */
    public String saveUserDetail(User objUser) throws AppException 
    { 
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	return objAdminDAO.insertUserDetail(objUser);
    }
    
    /**
     * Business method for displaying the User Detail
     * 
     * @param login
     * @return com.pearson.pix.dto.admin.User
     * @J2EE_METHOD  --  displayUserDetail
     */
    public User displayUserDetail(Integer login) throws AppException 
    { 
    	User objUser = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();	
    	AdminDAO objAdminDAO = objDAOFactory.getAdminDAO();
    	objUser = objAdminDAO.getUserDetail(login);
   	  	return objUser;     
    }
    
    /**
     * Business method for displaying the User Detail
     * 
     * @param login
     * @return com.pearson.pix.dto.admin.User
     * @J2EE_METHOD  --  displayUserDetail
     */
    public User displayModifiedUserDetail(Integer login, String tokenTab) throws AppException 
    { 
    	User objUser = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();	
    	AdminDAO objAdminDAO = objDAOFactory.getAdminDAO();
    	objUser = objAdminDAO.getModifiedUserDetail(login, tokenTab);
   	  	return objUser;     
    }
    
    /**
     * Business method for displaying the Users List. This method also takes care of filtering,
     *  pagination and history display by passing page no, filter criteria and its value,
     *  history flag respectively.
     * 
     * @param user
     * @param startDate
     * @param endDate
     * @param currentValue
     * @param objOrderBy
     * @param objSort 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayUsersList
     */
    public Vector displayUsersList(User user,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException 
    { 
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	Vector objUserList=objAdminDAO.getUsersList(user,startDate,endDate,currentValue,objOrderBy,objSort);
    	return objUserList;  
    }
    
    /**
     * Updates the Details of the user
     * 
     * @param user
     * @return java.lang.String
     * @J2EE_METHOD  --  updateUserDetail
     */
    public String updateUserDetail(User user) throws AppException  
    { 
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	return objAdminDAO.editUserDetail(user);
    }
	
    /**
     * Method to delete the user from the database. 
     * 
     * @param userId
     * @return java.lang.String
     * @J2EE_METHOD  --deleteUser
     */
    
    public String deleteUser(Integer userId) throws AppException
    {
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	return objAdminDAO.deleteUser(userId);
    }
    
    /**
     * Method to get the basic information of party to be shown intially in drop-downs etc.
     * 
     * @return java.util.HashMap
     * @J2EE_METHOD  --  getBasicPartyInfo
     */
    
    
    public java.util.HashMap getBasicPartyInfo() throws AppException  
    { 
        
        AdminDAO objAdminDAO = null;
        HashMap objHashMap = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	objHashMap=objAdminDAO.getBasicPartyInfo();
    	return objHashMap;
    }
    
    /**
     * Method to get the basic information of partyTransport to be shown intially in drop-downs etc.
     * 
     * @return java.util.LinkedHashMap
     * @J2EE_METHOD  --  getBasicPartyTransportInfo
     */
    public java.util.LinkedHashMap getBasicPartyTransportInfo() throws AppException  
    { 
        
        AdminDAO objAdminDAO = null;
        LinkedHashMap objHashMap = new LinkedHashMap();
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	
    	objHashMap.put(PIXConstants.ACCESS_METHOD,objAdminDAO.getBasicPartyTransportInfo());
    	objHashMap.put(PIXConstants.COUNTRY_LIST,getBasicPartyInfo());
    	objHashMap.put(PIXConstants.SUPPLIER_TYPES,objAdminDAO.getSupplierTypes());
    	
    	
    	
    	return objHashMap;
    }
    
    /**
     * Saves the Details of the party
     * 
     * @param party
     * @return java.lang.String
     * @J2EE_METHOD  --  savePartyDetail
     */
    public String savePartyDetail(Party party) throws AppException  
    { 
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	return objAdminDAO.insertPartyDetail(party);
    }
    
    /**
     * Business method for displaying the Party Detail
     * 
     * @param san
     * @return com.pearson.pix.dto.admin.Party
     * @J2EE_METHOD  --  displayPartyDetail
     */
    public Party displayPartyDetail(String san) throws AppException  
    { 
    	Party objParty = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();	
    	AdminDAO objAdminDAO = objDAOFactory.getAdminDAO();
    	objParty = objAdminDAO.getPartyDetail(san);
		return objParty;     

    }
    
    /**
     * Business method for displaying the Parties List. This method also takes care of filtering,
     *  pagination and history display by passing page no, filter criteria and its value,
     *  history flag respectively.
     * 
     * @param adminParty
     * @param startDate
     * @param endDate
     * @param currentValue
     * @param objOrderBy
     * @param objSort
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPartiesList
     */
    public Vector displayPartiesList(Party adminParty,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException  
    { 
    	
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	Vector objPubUnitList=objAdminDAO.getPartiesList(adminParty,startDate,endDate,currentValue,objOrderBy,objSort);
    	return objPubUnitList;  
    }
    
    /**
     * Updates the Details of the party
     * 
     * @param party
     * @return java.lang.String
     * @J2EE_METHOD  --  updatePartyDetail
     */
    public String updatePartyDetail(Party party) throws AppException  
    { 
    	AdminDAO objAdminDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objAdminDAO = objDAOFactory.getAdminDAO();
    	return objAdminDAO.editPartyDetail(party);
    }
    
    
    /**
	 * Method to authenticate user
	 *
	 * @param user
	 * @return com.pearson.pix.dto.admin.User
	 */
    public User authenticateUser(User user) throws AppException
    {
        	
    	  DAOFactory objDAOFactory = DAOFactory.newInstance();	
    	  AdminDAO objAdminDAO = objDAOFactory.getAdminDAO();
    	  user = objAdminDAO.authenticateUser(user);
    	  return user;     
    }	
	
    /**
	 * Method to send Email
	 *
	 * @param user
	 */
    public void sendEmail(String login)throws AppException
    {
    	DAOFactory objDAOFactory=DAOFactory.newInstance();
    	AdminDAO objAdminDAO=objDAOFactory.getAdminDAO();
    	objAdminDAO.sendEmail(login);
    }  
    
    /**
   	 * method to verify password
   	 *
   	 * @param userid
   	 * @param pass
   	 * @return   java.lang.String 
   	*/
    public String getPasswordInfo(Integer userid,String pass)throws AppException
    
    {
    	DAOFactory objDAOFactory=DAOFactory.newInstance();
        AdminDAO objAdminDAO=objDAOFactory.getAdminDAO();
        return objAdminDAO.getPasswordInfo(userid,pass);
    }
    
    
    public UserCountDTO accountLock(HashMap userCount,String loginid)throws AppException
    {
    	DAOFactory objDAOFactory=DAOFactory.newInstance();
    	AdminDAO objAdminDAO=objDAOFactory.getAdminDAO();
    	return objAdminDAO.accountLock(userCount,loginid);
    }
    
    
}
