

package com.pearson.pix.presentation.admin.delegate;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pearson.pix.presentation.admin.command.AdminCommand;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.admin.interfaces.AdminLocal;
import com.pearson.pix.business.admin.interfaces.AdminLocalHome;


import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserCountDTO;


import com.pearson.pix.exception.AppException;


/**
 * Business Delegate for Admin. It acts as the Client for EJB invocation.
 * @author shweta.g
 */
public class AdminDelegate 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(AdminCommand.class.getName());
	/**
	 * Constructor for initializing AdminDelegate
	 */
	public AdminDelegate() 
	{
    
	}
   
	/**
	 * Creates the Home Object first and then return the EJB Object from it
	 * 
	 * @return com.pearson.pix.business.admin.interfaces.AdminLocal
	 */
    private AdminLocal getAdminLocal() throws AppException
    {
    	AdminLocal objAdminLocal = null;
    	try{
    		AdminLocalHome objAdminLocalHome =ServiceLocator.getAdminLocalHome();
    		objAdminLocal = objAdminLocalHome.create();
    	} 
    	catch(NamingException ne)
    	{
    		ne.printStackTrace();
    	}
	   
    	catch(CreateException ce)
    	{
    		ce.printStackTrace();
    	}
	 
    	return objAdminLocal;
    }
    
    /**
     * Method to get the basic information of user to be shown intially in drop-downs 
     * etc.
     * 
     * @return java.util.HashMap
     */
    
    public HashMap getBasicUserInfo() throws AppException
    {
    	HashMap objHashMap = new HashMap();
    	objHashMap = getAdminLocal().getBasicUserInfo();
    	return objHashMap;
    }
    
    
    /**
     * Method to get the basic module information of user to be shown 
     * @return Vector
     */
    
    public Vector getUserModuleInfo() throws AppException
    {
    	Vector objVector = new Vector();
    	objVector = getAdminLocal().getUserModuleInfo();
    	return objVector;
    }
    
    /**
     * Saves the Details of the user
     * 
     * @param objUser
     * @return java.lang.String
     */
    
    public String saveUserDetail(User objUser) throws AppException
    {
    	return getAdminLocal().saveUserDetail(objUser); 
    }
    
    /**
     * Business method for displaying the User Detail
     * 
     * @param userId
     * @return com.pearson.pix.dto.admin.User
     */
    
    public User displayUserDetail(Integer login) throws AppException
    {
    	User objUser = getAdminLocal().displayUserDetail(login);
    	return objUser;
    }
    
    
    public User displayModifiedUserDetail(Integer login, String tokenTab) throws AppException
    {
    	User objUser = getAdminLocal().displayModifiedUserDetail(login, tokenTab);
    	return objUser;
    }
    /**
     * Method for displaying the Users List. This method also takes care of filtering, 
     * pagination and history display by passing page no, filter criteria and its 
     * value, history flag respectively.
     * 
     * @param user
     * @param startDate
     * @param endDate
     * @param currentValue
     * @param objOrderBy
     * @param objSort
     * 
     * @return java.util.Collection
     */
    public Vector displayUsersList(User user,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException 
    {
    	Vector objUserList = getAdminLocal().displayUsersList(user,startDate,endDate,currentValue,objOrderBy,objSort);
    	return objUserList;
    }
    
    /**
   	 * updates the Details of the user
   	 * 
   	 * @param user
   	 * @return java.lang.String
   	 */
   	public String updateUserDetail(User user) throws AppException
   	{
   		return getAdminLocal().updateUserDetail(user);
   	}
   
   	/**
     * Method to delete the users from the database. 
     * 
     * @param userId
     * @return java.lang.String
     * @throws AppException 
     */
    
    public String deleteUser(Integer userId) throws AppException
    {
    	return getAdminLocal().deleteUser(userId);
    }
    
    /**
     * Method to get the basic information of party to be shown intially in drop-downs 
     * etc.
     * 
     * @return java.util.HashMap
     * @throws AppException 
     */
    public HashMap getBasicPartyInfo() throws AppException 
    {
    	HashMap objHashMap = getAdminLocal().getBasicPartyInfo();
    	return objHashMap;
	}
   
    /**
     * Method to get the basic information of partyTransport to be shown intially in drop-downs 
     * etc.
     * 
     * @return java.util.LinkedHashMap
     * @throws AppException 
     */
    public LinkedHashMap getBasicPartyTransportInfo() throws AppException 
    {
    	LinkedHashMap objHashMap = getAdminLocal().getBasicPartyTransportInfo();
    	return objHashMap;
    }
   
   
    /**
     * Saves the Details of the party
     * 
     * @param party
     * @return java.lang.String
     */
    public String savePartyDetail(Party party) throws AppException
    {
    	return getAdminLocal().savePartyDetail(party);
    }
   
    /**
     * Method for displaying the Party Detail
     * 
     * @param san
     * @return com.pearson.pix.dto.admin.Party
     */
   	public Party displayPartyDetail(String san) throws AppException
   	{
   		Party adminParty = getAdminLocal().displayPartyDetail(san);
   		return adminParty;
   	}
   
   	/**
   	 * method for displaying the Parties List. This method also takes care of 
   	 * filtering, pagination and history display by passing page no, filter criteria 
   	 * and its value, history flag respectively.
   	 * 
   	 * @param adminParty
   	 * @param startDate
   	 * @param endDate
   	 * @param currentvalue
   	 * @param objOrderBy
   	 * @param objSort
   	 * 
   	 * @return java.util.Collection
   	 */
   	public Vector displayPartiesList(Party adminParty,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException 
   	{
   		Vector objPubUnitList = getAdminLocal().displayPartiesList(adminParty,startDate,endDate,currentValue,objOrderBy,objSort);
   		return objPubUnitList;
	}
   
   	/**
   	 * updates the Details of the party
   	 * 
   	 * @param party
   	 * @return java.lang.String
   	 */
   	public String updatePartyDetail(Party party) throws AppException
   	{
   		return getAdminLocal().updatePartyDetail(party);
   	}
   	/**
   	 * Method to authenticate user
   	 *
   	 * @param user
   	 * @return com.pearson.pix.dto.admin.User
   	 */
   	public User authenticateUser(User user) throws AppException
   	{
   		User objUser = null;
   		objUser = getAdminLocal().authenticateUser(user);
   		return objUser;
   	}
   	
   	/**
   	 * method to send mail
   	 *
   	 * @param login
   	 */
    public  void sendEmail(String login)throws AppException
    {
    	getAdminLocal().sendEmail(login);
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
    	return getAdminLocal().getPasswordInfo(userid,pass);
    }
    
    
    public UserCountDTO accountLock(HashMap userCount,String loginid) throws AppException
   	{
    	UserCountDTO objUser = null;
    	objUser=getAdminLocal().accountLock(userCount,loginid);
    	return objUser;
   		
   	}
    
}
