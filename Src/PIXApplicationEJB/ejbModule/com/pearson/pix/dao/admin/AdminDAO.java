package com.pearson.pix.dao.admin;

import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.admin.UserCountDTO;
import com.pearson.pix.exception.AppException;

/**
 * Admin Data Access Object containing all the methods required for DB access 
 * through Toplink.
 * @author shweta.g
 */
public interface AdminDAO 
{
   
	/**
	 * Method to get the basic information of user from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 */
	public HashMap getBasicUserInfo() throws AppException;
	
	/**
	 * Method to get the basic module information of user from DB to be shown 
	 * @return java.util.Collection
	 */
	public Vector getUserModuleInfo() throws AppException;
   
	/**
	 * Inserts the details of the user into DB and returns the list of users
	 * 
	 * @param objUser
	 * @return java.lang.String
	 */
	public String insertUserDetail(User objUser) throws AppException;
   
	/**
	 * Gets the detail of a user from the DB
	 * 
	 * @param userId
	 * @return com.pearson.pix.dto.admin.User
	 */
	public User getUserDetail(Integer login) throws AppException;
	
	/**
	 * Gets the detail of a modified user from the DB
	 * 
	 * @param userId
	 * @return com.pearson.pix.dto.admin.User
	 */
	public User getModifiedUserDetail(Integer login, String tokenTab) throws AppException;
   
	/**
	 * Gets the collection of users from DB based on the parameters decided.
	 * 
	 * @param user
	 * @param startDate
	 * @param endDate
	 * @param currentValue
	 * @param objOrderBy
	 * @param objsort
	 * 
	 * @return java.util.Collection
	 */
	public Vector getUsersList(User user,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException;
	
	/**
	 * update the details of the user into DB 
	 * 
	 * @param user
	 * @return java.lang.String
	 */
	public String editUserDetail(User user) throws AppException;
	
	
	/**
     * Method to delete the user from the database . 
     * 
     * @param userId
     * @return java.lang.String
     * @J2EE_METHOD  --deleteUser
     */
    
    public String deleteUser(Integer userId) throws AppException;
    
    /**
	 * Method to get the basic information of party from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 */
	public HashMap getBasicPartyInfo() throws AppException;
	
	/**
	 * Method to get the basic information of partyTransport from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 */
	public HashMap getBasicPartyTransportInfo() throws AppException;
	
	/**
	 * Method to get the supplier types from DB to be shown intially in 
	 * drop-downs etc.(MIll/Merchant/Supplier)
	 * 
	 * @return java.util.Vector
	 */
	public Vector getSupplierTypes() throws AppException;
   
	/**
	 * Inserts the details of the user into DB and returns the list of parties
	 * 
	 * @param party
	 * @return java.lang.String
	 */
	public String insertPartyDetail(Party party) throws AppException;
	
	
	
	/**
	 * Gets the detail of a party from the DB
	 * 
	 * @param san
	 * @return com.pearson.pix.dto.admin.Party
	 */
	public Party getPartyDetail(String san)throws AppException;
   
	/**
	 * Gets the collection of parties from DB based on the parameters decided.
	 * 
	 * @param adminParty
	 * @param startDate
	 * @param endDate
	 * @param currentValue
	 * @param objOrderBy
	 * @param objsort
	 * 
	 * @return java.util.Collection
	 * @throws AppException 
	 */
	public Vector getPartiesList(Party adminParty,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException;
	
	/**
	 * update the details of the party into DB 
	 * 
	 * @param party
	 * @return java.lang.String
	 */
	public String editPartyDetail(Party party) throws AppException;
   
	/**
	 * Method to authenticate user
	 *
	 * * @param user
	 * @return com.pearson.pix.dto.admin.User
	 */
	public User authenticateUser(User user) throws AppException;
		 
	/**
	 * Method to send Email
	 *
	 * @param user
	 */
	public void sendEmail(String login)throws AppException;
	
	/**
   	 * method to verify password
   	 *
   	 * @param userid
   	 * @param pass
   	 * @return   java.lang.String 
   	*/
	public String getPasswordInfo(Integer userid,String pass)throws AppException;
	
	public UserCountDTO accountLock(HashMap userCount,String loginid) throws AppException;
}
