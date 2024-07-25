
package com.pearson.pix.dao.admin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.transaction.UserTransaction;

import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;

import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.queryframework.ValueReadQuery;


import oracle.toplink.sessions.DatabaseRecord;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;
import oracle.toplink.threetier.ClientSession;
import oracle.toplink.exceptions.TopLinkException;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;

import com.pearson.pix.dto.admin.Party;
import com.pearson.pix.dto.admin.PartyTransport;
import com.pearson.pix.dto.admin.UserCountDTO;

import com.pearson.pix.dto.admin.UserPriv;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserRole;
import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.common.Reference;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of Admin Data Access Object containing all the methods required 
 * for DB access through Toplink.
 * @author shweta.g
 */
public class AdminDAOImpl extends BaseDAO implements AdminDAO 
{
	/**
     * Logger.
     */
	private static Log log = LogFactory.getLog(AdminDAOImpl.class.getName());
    /**
     * Constructor to initialize AdminDAOImpl
     */
	public AdminDAOImpl() 
	{
	}
	/**
	 * Method to get the basic information of user from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 */
	public HashMap getBasicUserInfo() throws AppException
	{
    	HashMap objHashMap =null;
    	Vector listVector=null;
    	Session objSession =  null;
    	try
    	{
    		objSession = getClientSession();
    		objHashMap = new HashMap();
    		/*Query to get the roleType of the user*/
    		listVector = (Vector)objSession.readAllObjects(UserRole.class,new SQLCall("select role_type,description from pix_admin_user_role order by description"));
    		objHashMap.put(PIXConstants.ROLE_TYPE_LIST,listVector);
    		return objHashMap;
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getBasicPartyInfo",e);
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return objHashMap;
	  
	}
	
	/**
	 * Method to get the basic module information of user from DB to be shown 
	 * @return java.util.Collection
	 */
	public Vector getUserModuleInfo() throws AppException
	{
    	Vector listVector=null;
    	Session objSession =  null;
    	try
    	{
    		objSession = getClientSession();
    		/*Query to get the modulesId and their description*/
    		listVector = (Vector)objSession.readAllObjects(Reference.class,new SQLCall("select ref_id,ref_code,description from pix_ref where group_code='MODULE' order by display_order"));
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getBasicPartyInfo",e);
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return listVector;
	  
	}
   
   
	/**
	 * Inserts the details of the user into DB and returns the list of users
	 * 
	 * @param objUser
	 * @return java.lang.String
	 */
	public String insertUserDetail(User objUser) throws AppException
	{
		Session objSession =  null;
    	UnitOfWork objUnitOfWork = null;
    	
    	String login = null;
    	UserPriv objUserPriv = null;
    	Party objParty = null;
    	Vector objPrivilegeCollection = null;
    	Vector objPartyCollection = null;
    	ExpressionBuilder expBuilder = null;
    	Expression loginCheck = null;
    	Expression newCountry = null;
    	Expression newReference = null;
    	Reference newObjReference = null;
    	Expression newPartyCountry = null;
    	Expression newPartySan = null;
    	Expression newUserRole = null;
    	try
    	{
    		objSession =  getClientSession();	//obtaining the clientSession
    		expBuilder = new ExpressionBuilder();
    		objUnitOfWork = getUnitOfWork(objSession); 							//acquiring the unitOfWork
    		
    		
    		login = objUser.getLogin();
    		loginCheck = expBuilder.get("login").equal(login);
    		User newUser = (User)objUnitOfWork.readObject(User.class,loginCheck);
    		if(newUser!= null)					//Checking if the user already exists
    		{
    			AppException appException=new AppException();
    			log.info("log info  :User already Exists ");
  				appException.performErrorAction(Exceptions.USER_ALREADY_EXISTS,"AdminDAOImpl");  
  				throw appException;
    		}
    		else								//if the user doesn't already exists
    		{
    			String country = objUser.getCountryDetail().getCountryCode();
    			newCountry = expBuilder.get("countryCode").equal(country);
    			Country newObjCountry = (Country)objUnitOfWork.readObject(Country.class,newCountry);
    			objUser.setCountryDetail(newObjCountry);
    			
    			objPrivilegeCollection =(Vector) objUser.getPrivilegeCollection();
    			objUser.setPrivilegeCollection(new Vector());
    			int privilegeCollectionSize = objPrivilegeCollection == null ? 0 : objPrivilegeCollection.size();
    			for(int i=0;i<privilegeCollectionSize;i++)			//iterate to set user privileges in UserPriv object
    			{
    				objUserPriv = (UserPriv) objPrivilegeCollection.get(i);
    				Integer refId = objUserPriv.getModuleDetail().getRefId();
    				newReference = expBuilder.get("refId").equal(refId);
    				newObjReference = (Reference)objUnitOfWork.readObject(Reference.class,newReference);
    				objUserPriv.setModuleDetail(newObjReference);
    				objUserPriv.setUserDetail(objUser);
    				objUser.addToPrivilegeCollection(objUserPriv);
    			}
    			
				
    			objPartyCollection = (Vector) objUser.getPartyCollection();
    			objUser.setPartyCollection(new Vector());
    			
    			int partyCollectionSize = objPartyCollection == null ? 0 : objPartyCollection.size();
    			for(int i=0;i<partyCollectionSize;i++)			//iterate to set parties associated to the user
    			{
    				objParty = (Party)objPartyCollection.get(i);
    				String partyCountry = objParty.getCountryDetail().getCountryCode();
    				newPartyCountry = expBuilder.get("countryCode").equal(partyCountry);
        			Country newObjPartyCountry = (Country)objUnitOfWork.readObject(Country.class,newPartyCountry);
        			objParty.setCountryDetail(newObjPartyCountry);
        		
        			String san = objParty.getSan();
        			newPartySan = expBuilder.get("san").equal(san);
        			Party newObjParty = (Party)objUnitOfWork.readObject(Party.class,newPartySan);
        			
        			objUser.addToPartyCollection(newObjParty);
        		}
    			String roleType = objUser.getRoleTypeDetail().getRoleType();
    			newUserRole = expBuilder.get("roleType").equal(roleType);
    			UserRole objUserRole = (UserRole)objUnitOfWork.readObject(UserRole.class,newUserRole);
    			objUser.setRoleTypeDetail(objUserRole);
    			
    			objUnitOfWork.registerObject(objUser);
    			objUnitOfWork.commit();
    		}
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,insertUserDetail",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return login;
	}
   
	
	/**
	 * Gets the detail of a user from the DB
	 * 
	 * @param login
	 * @return com.pearson.pix.dto.admin.User
	 */
	public User getUserDetail(Integer login) throws AppException
	{
		
		User objUser = null;
		Session objSession = null;
		
		ExpressionBuilder objExpressionBuilder = null;
		Expression objExpression = null;
		try{
			objSession = getClientSession();
			objExpressionBuilder = new ExpressionBuilder();
		 	objExpression = objExpressionBuilder.get("userId").equal(login);
		 	objUser = (User)objSession.readObject(User.class,objExpression);
		 	
//		 	   StoredProcedureCall call = new StoredProcedureCall();
//			   call.setProcedureName("PIX_MANAGE_USER_PROC");
//			   call.addNamedArgumentValue("I_USER_ID",login);
//			   call.addNamedArgumentValue("I_TYPE","INDIA");
//			   call.useNamedCursorOutputAsResultSet("o_compare_version_cursor");
//
//			   Vector objVector=(Vector)objSession.executeSelectingCall(call);
		 			 	
		}
	    catch(TopLinkException e)
	    {
	    	AppException appException = new AppException();
	    	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "AdminDAOImpl,getUserDetail",e);
		    throw appException;
	    }
	    finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
		return objUser; 
	}
	
	/**
	 * Gets the detail of a modified user from the DB
	 * 
	 * @param login
	 * @return com.pearson.pix.dto.admin.User
	 */
	public User getModifiedUserDetail(Integer login, String tokenTab) throws AppException
	{
		
		User objUser = new User();
		Session objSession = null;
		
		ExpressionBuilder objExpressionBuilder = null;
		Expression objExpression = null;
		try{
			objSession = getClientSession();
			/*objExpressionBuilder = new ExpressionBuilder();
		 	objExpression = objExpressionBuilder.get("userId").equal(login);
		 	objUser = (User)objSession.readObject(User.class,objExpression);*/
			
//			for(int i=1;i<5;i++)
//			{
		 	   StoredProcedureCall call = new StoredProcedureCall();
			   call.setProcedureName("PIX_MANAGE_USER_PROC");
			   call.addNamedArgumentValue("I_USER_ID",login);
			   call.addNamedArgumentValue("I_TYPE","1");
			   call.useNamedCursorOutputAsResultSet("O_USER_DETAIL_CURSOR");

			    Vector objVector = (Vector)objSession.executeSelectingCall(call);
			    
			    System.out.println(objVector);
	    		int vectorSize = objVector == null ? 0 : objVector.size();
	    		if (objVector != null && vectorSize>0)								//checking for vector size
	    		{
	    			for (int i = 0; i < vectorSize; i++)							//iterating to add each record in listVector
	    			{
	    				Record objDatabaseRecord = (Record)objVector.get(i);
	    				if((String)objDatabaseRecord.get("USER ID")!=null)
	    				{
	    					objUser.setLogin((String)objDatabaseRecord.get("USER ID"));
	    				}
	    				if((String)objDatabaseRecord.get("SSOID")!=null)
	    				{
	    					objUser.setSsoid((String)objDatabaseRecord.get("SSOID"));
	    				}
	    				if((String)objDatabaseRecord.get("FIRST NAME")!=null)
	    				{
	    					objUser.setFirstName((String)objDatabaseRecord.get("FIRST NAME"));
	    				}
	    				if((String)objDatabaseRecord.get("LAST NAME")!=null)
	    				{
	    					objUser.setLastName((String)objDatabaseRecord.get("LAST NAME"));
	    				}
	    				if((String)objDatabaseRecord.get("WEBSITE")!=null)
	    				{
	    					objUser.setWebsite((String)objDatabaseRecord.get("WEBSITE"));
	    				}
	    				String roleType = null;
	    				if((roleType = (String)objDatabaseRecord.get("ACCOUNT TYPE"))!= null)	//get the roleType and set it in user object
	           		  	{   
	    					if(roleType.equals("A"))
	           		  			roleType="A";
	           		  		else if(roleType.equals("B"))
	           		  			roleType="B";
	           		  		else if(roleType.equals("V"))
	           		  			roleType="V";
	           		  	    else if(roleType.equals("M"))
	           		  	    	roleType="M";
	           		  	    else if(roleType.equals("C"))
	           		  	    	roleType="C";
	           		  	    	
	           		  		UserRole objUserRole = new UserRole();
	           		  		objUserRole.setRoleType(roleType);
	           		  		objUser.setRoleTypeDetail(objUserRole);
	           		  	}
	    				if((String)objDatabaseRecord.get("COUNTRY CODE")!= null)
	    				{
	    					Country countryDetail = new Country();
	    					countryDetail.setCountryCode((String)objDatabaseRecord.get("COUNTRY CODE"));
	    					countryDetail.setCountryName((String)objDatabaseRecord.get("COUNTRY NAME"));
	    					objUser.setCountryDetail(countryDetail);
	    				}
	    				if((String)objDatabaseRecord.get("PASSWORD")!= null)
	    				{
	    					objUser.setPassword((String)objDatabaseRecord.get("PASSWORD"));
	    				}
	    				if((String)objDatabaseRecord.get("PASSWORD EXPIRY")!= null)
	    				{
	    					objUser.setPasswordExpiry((String)objDatabaseRecord.get("PASSWORD EXPIRY"));
	    				}
	    				if((String)objDatabaseRecord.get("BUSINESS PHONE 1")!= null)
	    				{
	    					objUser.setPhone1((String)objDatabaseRecord.get("BUSINESS PHONE 1"));
	    				}
	    				if((String)objDatabaseRecord.get("BUSINESS PHONE 2")!= null)
	    				{
	    					objUser.setPhone2((String)objDatabaseRecord.get("BUSINESS PHONE 2"));
	    				}
	    				if((String)objDatabaseRecord.get("FAX 1")!= null)
	    				{
	    					objUser.setFax1((String)objDatabaseRecord.get("FAX 1"));
	    				}
	    				if((String)objDatabaseRecord.get("FAX 2")!= null)
	    				{
	    					objUser.setFax2((String)objDatabaseRecord.get("FAX 2"));
	    				}
	    				if((String)objDatabaseRecord.get("ADDRESS LINE 1")!= null)
	    				{
	    					objUser.setAddress1((String)objDatabaseRecord.get("ADDRESS LINE 1"));
	    				}
	    				if((String)objDatabaseRecord.get("ADDRESS LINE 2")!= null)
	    				{
	    					objUser.setAddress2((String)objDatabaseRecord.get("ADDRESS LINE 2"));
	    				}
	    				if((String)objDatabaseRecord.get("ADDRESS LINE 3")!= null)
	    				{
	    					objUser.setAddress3((String)objDatabaseRecord.get("ADDRESS LINE 3"));
	    				}
	    				if((String)objDatabaseRecord.get("ADDRESS LINE 4")!= null)
	    				{
	    					objUser.setAddress4((String)objDatabaseRecord.get("ADDRESS LINE 4"));
	    				}
	    				if((String)objDatabaseRecord.get("EMAIL USER ON ACTIVITY")!= null)
	    				{
	    					objUser.setEmailActivityFlag((String)objDatabaseRecord.get("EMAIL USER ON ACTIVITY"));
	    				}
	    				String status = null;
	    				if((status = (String)objDatabaseRecord.get("ACTIVE FLAG"))!= null)	//get the activeFlag and set it in user object
	           		  	{  
	           		  		objUser.setActiveFlag(status);
	           		  	}
	    				Date creationDateTime = null;
	           		  	if((creationDateTime = (Date)objDatabaseRecord.get("CREATION DATE TIME"))!= null)	//get the creationDateTime and set it in user object
	           		  	{   
	           		  		objUser.setCreationDateTime(creationDateTime);
	           		  	}
	           		  	Date modDateTime = null;
	           		  	if((modDateTime = (Date)objDatabaseRecord.get("MOD DATE TIME"))!= null)	//get the creationDateTime and set it in user object
	           		  	{   
	           		  		objUser.setModDateTime(modDateTime);
	           		  	}
	           		  	String createdBy = null;
	           		  	if((createdBy = (String)objDatabaseRecord.get("MODIFIED_BY "))!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		objUser.setFullName(createdBy);
	           		  	}
	           		  	if((String)objDatabaseRecord.get("STATE")!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		objUser.setState((String)objDatabaseRecord.get("STATE"));
	           		  	}
	           		  	if((String)objDatabaseRecord.get("CITY")!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		objUser.setCity((String)objDatabaseRecord.get("CITY"));
	           		  	}
	           		  	if((String)objDatabaseRecord.get("ZIP")!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		objUser.setPostalCode((String)objDatabaseRecord.get("ZIP"));
	           		  	}
	           		  	if((String)objDatabaseRecord.get("EMAIL")!= null)
	           		  	{
	           		  		objUser.setEmail((String)objDatabaseRecord.get("EMAIL"));
	           		  	}
	           		  	Integer crby = new Integer(((BigDecimal)objDatabaseRecord.get("CREATED BY")).intValue());
	    				if (crby != null)
	    				{
	    					objUser.setCreatedBy(new Integer(crby).intValue());
	    				}
	           		 	objUser.setUserId(login);
	    			}
	    		}
	    		
			   StoredProcedureCall call2 = new StoredProcedureCall();
			   call2.setProcedureName("PIX_MANAGE_USER_PROC");
			   call2.addNamedArgumentValue("I_USER_ID",login);
			   call2.addNamedArgumentValue("I_TYPE","2");
			   call2.useNamedCursorOutputAsResultSet("O_USER_DETAIL_CURSOR");
			   Vector objVector2 = (Vector)objSession.executeSelectingCall(call2);
			   System.out.println(objVector2);
	    		int vectorSize2 = objVector2 == null ? 0 : objVector2.size();
	    		if (objVector2 != null && vectorSize2>0)								//checking for vector size
	    		{
	    			UserPriv usrpriv[] = new UserPriv[vectorSize2];
	    			for (int i = 0; i < vectorSize2; i++)							//iterating to add each record in listVector
	    			{
	    				Record objDatabaseRecord = (Record)objVector2.get(i);
	    				usrpriv[i] = new UserPriv();
	    				Reference reference = new Reference();
	    				if((String)objDatabaseRecord.get("USER ROLE")!=null)
	    				{
	    					reference.setDescription((String)objDatabaseRecord.get("USER ROLE"));
	    					reference.setRefCode((String) objDatabaseRecord.get("REF CODE"));
	    					usrpriv[i].setModuleDetail(reference);
	    				}
	    				if ((String) objDatabaseRecord.get("ACCESS FLAG") != null)
	    				{
	    					usrpriv[i].setAccessFlag((String) objDatabaseRecord.get("ACCESS FLAG"));
	    				}
	    				
	    				Integer crby = new Integer(((BigDecimal)objDatabaseRecord.get("CREATED BY")).intValue());
	    				if (crby != null)
	    				{
	    					usrpriv[i].setCreatedBy(new Integer(crby).intValue());
	    				}
	    				if ((String) objDatabaseRecord.get("REF CODE") != null)
	    				{
	    					reference.setRefCode((String) objDatabaseRecord.get("REF CODE"));
	    				}
	    				objUser.addToPrivilegeCollection(usrpriv[i]);
	    			}
	    		}
			   
			   
			   StoredProcedureCall call3 = new StoredProcedureCall();
			   call3.setProcedureName("PIX_MANAGE_USER_PROC");
			   call3.addNamedArgumentValue("I_USER_ID",login);
			   call3.addNamedArgumentValue("I_TYPE","3");
			   call3.useNamedCursorOutputAsResultSet("O_USER_DETAIL_CURSOR");

			   Vector objVector3 = (Vector)objSession.executeSelectingCall(call3);
			   System.out.println(objVector3);
			   int vectorSize3 = objVector3 == null ? 0 : objVector3.size();
			   if (objVector3 != null && vectorSize3>0)								//checking for vector size
			   {
				   for (int i = 0; i < vectorSize3; i++)							//iterating to add each record in listVector
	    			{
					   Party aparty = new Party();
					   Record objDatabaseRecord = (Record)objVector3.get(i);
					   
					   if((String)objDatabaseRecord.get("SUPPLIER NAME")!=null)
					   {
						   aparty.setName1((String)objDatabaseRecord.get("SUPPLIER NAME"));
					   }
					   if((String)objDatabaseRecord.get("SAN")!=null)
					   {
						   aparty.setSan((String)objDatabaseRecord.get("SAN"));
					   }
					   if((String)objDatabaseRecord.get("ACCOUNT TYPE")!=null)
					   {
						   aparty.setPartyType((String)objDatabaseRecord.get("ACCOUNT TYPE"));
					   }
					   Date creationDateTime = null;
					   if((creationDateTime = (Date)objDatabaseRecord.get("DATE ADDED"))!= null)	//get the creationDateTime and set it in user object
	           		  	{   
						   aparty.setCreationDateTime(creationDateTime);
	           		  	}
					   String createdBy = null;
	           		  	if((createdBy = (String)objDatabaseRecord.get("USER ADDED"))!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		aparty.setFullName(createdBy);
	           		  	}
	           		  	Integer crby = new Integer(((BigDecimal)objDatabaseRecord.get("CREATED BY")).intValue());
	    				if (crby != null)
	    				{
	    					aparty.setCreatedBy(new Integer(crby).intValue());
	    				}
	           		  	/*if((Integer)objDatabaseRecord.get("CREATED BY")!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		aparty.setCreatedBy((Integer)objDatabaseRecord.get("CREATED BY"));
	           		  	}*/
					   objUser.addToPartyCollection(aparty);
	    			}
			   }
			   
			   
			   StoredProcedureCall call4 = new StoredProcedureCall();
			   call4.setProcedureName("PIX_MANAGE_USER_PROC");
			   call4.addNamedArgumentValue("I_USER_ID",login);
			   call4.addNamedArgumentValue("I_TYPE","4");
			   call4.useNamedCursorOutputAsResultSet("O_USER_DETAIL_CURSOR");

			   Vector objVector4 = (Vector)objSession.executeSelectingCall(call4);
			   System.out.println(objVector4);
			   int vectorSize4 = objVector4 == null ? 0 : objVector4.size();
			   if (objVector4 != null && vectorSize4>0)								//checking for vector size
			   {
				   for (int i = 0; i < vectorSize4; i++)							//iterating to add each record in listVector
	    			{
					   Party aparty = new Party();
					   Record objDatabaseRecord = (Record)objVector4.get(i);
					   
					   if((String)objDatabaseRecord.get("SUPPLIER NAME")!=null)
					   {
						   aparty.setName1((String)objDatabaseRecord.get("SUPPLIER NAME"));
					   }
					   if((String)objDatabaseRecord.get("SAN")!=null)
					   {
						   aparty.setSan((String)objDatabaseRecord.get("SAN"));
					   }
					   if((String)objDatabaseRecord.get("PARTY TYPE")!=null)
					   {
						   aparty.setPartyType((String)objDatabaseRecord.get("PARTY TYPE"));
					   }
					   Integer crby = new Integer(((BigDecimal)objDatabaseRecord.get("CREATED BY")).intValue());
	    				if (crby != null)
	    				{
	    					aparty.setCreatedBy(new Integer(crby).intValue());
	    				}
					   /*if((Integer)objDatabaseRecord.get("CREATED BY")!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		aparty.setCreatedBy((Integer)objDatabaseRecord.get("CREATED BY"));
	           		  	}*/
					   Date creationDateTime = null;
					   if((creationDateTime = (Date)objDatabaseRecord.get("DATE ADDED"))!= null)	//get the creationDateTime and set it in user object
	           		  	{   
						   aparty.setCreationDateTime(creationDateTime);
	           		  	}
					   String createdBy = null;
	           		  	if((createdBy = (String)objDatabaseRecord.get("USER ADDED"))!= null)	//get the creationBy and set it in user object
	           		  	{   
	           		  		aparty.setFullName(createdBy);
	           		  	}
					   objUser.addToPartyCollection(aparty);
	    			}
			   }
//			}
		}
		catch(TopLinkException e) {
	    	AppException appException = new AppException();
	    	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "AdminDAOImpl,getUserDetail",e);
		    throw appException;
	    }
		catch(Exception e) {
	    	AppException appException = new AppException();
	    	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "AdminDAOImpl,getUserDetail",e);
		    throw appException;
	    }
	    finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
		return objUser; 
	}
   
	/**
     * Gets the collection of users from DB based on the parameters decided.
     * 
     * @param user
     * @param startDate
     * @param endDate
     * @param currentValue
     * @param objOrderBy
     * @param objSort 
     * @return java.util.Collection
     */
	public Vector getUsersList(User user,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException
	{
		Session objSession = getClientSession();
    	Vector objListVector = new Vector();
    	String activeFlag=(user.getActiveFlag()=="")?null:user.getActiveFlag();
    	//System.out.println("login......"+user.getLogin());
    	try
    	{
    		StoredProcedureCall call = new StoredProcedureCall(); 
    		call.setProcedureName("Pix_Get_User_Proc");
    		call.addNamedArgumentValue("i_login",user.getLogin());
    		call.addNamedArgumentValue("i_first_name",user.getFirstName());
    		call.addNamedArgumentValue("i_last_name",user.getLastName());
    		call.addNamedArgumentValue("i_role_type",user.getRoleTypeDetail().getRoleType());
    		call.addNamedArgumentValue("i_active_flag",activeFlag);
    		call.addNamedArgumentValue("i_start_date",startDate);
    		call.addNamedArgumentValue("i_end_date",endDate);
    		call.addNamedArgumentValue("i_pagination",new Integer(currentValue));
    		call.addNamedArgumentValue("i_order_by",objOrderBy);
    		call.addNamedArgumentValue("i_sort",objSort); 
    		call.useNamedCursorOutputAsResultSet("list_refcursor");
    		Vector objVector = (Vector)objSession.executeSelectingCall(call);
    		int vectorSize = objVector == null ? 0 : objVector.size();
    		if (objVector != null && vectorSize>0)								//checking for vector size
    		{
    			for (int i = 0; i < vectorSize; i++)							//iterating to add each record in listVector
    			{
    				Record objDatabaseRecord = (Record)objVector.get(i);
           		  	String login = null;
           		  	String firstName = null;
           		  	String lastName = null;
           		  	String roleType = null;
           		  	String status = null;
           		  	Integer userId = null;
           		  	Date creationDateTime = null;
           		  	String createdBy = null;
        		    user = new User();
        		    if((login = (String)objDatabaseRecord.get(PIXConstants.LOGIN))!=null)		//get the login and set it in user object
           		  	{
           		  		user.setLogin(login);
           		  	}	   
           		  	if((firstName = (String)objDatabaseRecord.get(PIXConstants.FIRST_NAME))!= null)	//get the firstname and set it in user object
           		  	{   
           		  		user.setFirstName(firstName);
           		  	}
           		  	if((lastName = (String)objDatabaseRecord.get(PIXConstants.LAST_NAME))!= null)	//get the lastname and set it in user object
        		  	{   
        		  		user.setLastName(lastName);
        		  	}
           		  	if((roleType = (String)objDatabaseRecord.get(PIXConstants.ROLE_TYPE))!= null)	//get the roleType and set it in user object
           		  	{   
           		  		if(roleType.equals("A"))
           		  			roleType="Admin";
           		  		else if(roleType.equals("B"))
           		  			roleType="Buyer";
           		  		else if(roleType.equals("V"))
           		  			roleType="Supplier";
           		  	    else if(roleType.equals("M"))
           		  	    	roleType="Mill";
           		  	    else if(roleType.equals("C"))
           		  	    	roleType="Cost Accounting";
           		  	    	
           		  		
           		  		UserRole objUserRole = new UserRole();
           		  		objUserRole.setRoleType(roleType);
           		  		user.setRoleTypeDetail(objUserRole);
           		  	}
           		  	if((status = (String)objDatabaseRecord.get(PIXConstants.ACTIVE_FLAG))!= null)	//get the activeFlag and set it in user object
           		  	{  
           		  		if(status.equals("Y"))
           		  			status="Active";
           		  		else
           		  			status="Disabled";
           		  		user.setActiveFlag(status);
           		  	}
           		  	if((creationDateTime = (Date)objDatabaseRecord.get(PIXConstants.CREATION_DATE_TIME))!= null)	//get the creationDateTime and set it in user object
           		  	{   
           		  		user.setCreationDateTime(creationDateTime);
           		  	}
           		  	if((createdBy = (String)objDatabaseRecord.get(PIXConstants.MODIFIED_BY))!= null)	//get the creationBy and set it in user object
           		  	{   
           		  		user.setFullName(createdBy);
           		  	}
           		  	userId = new Integer(((BigDecimal)objDatabaseRecord.get(PIXConstants.USER_ID)).intValue());
           		  	user.setUserId(userId);
           		  	
//           		  	if((Integer)objDatabaseRecord.get("CREATED BY")!= null)	//get the creationBy and set it in user object
//        		  	{   
//        		  		user.setCreatedBy((Integer)objDatabaseRecord.get("CREATED BY"));
//        		  	}
           		  	
           		  	objListVector.add(user);
        	    }
    		}
    		else if(vectorSize == 0 || objVector != null)		//check if no records are found
            {
         	    AppException appException=new AppException();
         	    //log.info("log info  :no records ");
   				appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_USER,"AdminDAOImpl");  
   				throw appException;
            }
        }
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getUsersList",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return objListVector; 
	}
	
	
	/**
	 * update the details of the user into DB 
	 * 
	 * @param user
	 * @return java.lang.String
	 */
    
    public String editUserDetail(User user) throws AppException
    {
        	   
    	Session objSession =  null;
    	UnitOfWork objUnitOfWork = null;
    	Party objParty = null;
    	UserPriv objUserPriv = null;
    	ExpressionBuilder expBuilder = null;
    	Expression login = null;
    	Expression objUserPrivDel = null;
    	Expression newReference = null;
    	Reference newObjReference = null;
    	Expression newPartySan = null;
    	Expression newPartyCountry = null;
    	Expression roleExp = null;
    	Expression counExp = null;
    	try
    	{
    		objSession =  getClientSession();
    		expBuilder = new ExpressionBuilder();
    		objUnitOfWork = getUnitOfWork(objSession);
    		objUnitOfWork.setShouldPerformDeletesFirst(true);	
    		
    		login = expBuilder.get("userId").equal(user.getUserId());
    	    User newObjUser = (User) objUnitOfWork.readObject(User.class,login);
    	    objUnitOfWork.logMessages();
    	    newObjUser = (User)objUnitOfWork.registerObject(newObjUser);
    	    if(user.getFirstName()!=null)
    	    {
    	    	newObjUser.setFirstName(user.getFirstName().trim());
    	    }
    	    
    	    if(user.getLastName()!=null)
    	    {
    	    	newObjUser.setLastName(user.getLastName().trim());
    	    }
    	    
    	    if(user.getSsoid()!=null)
    	    {
    	    	newObjUser.setSsoid(user.getSsoid().trim());
    	    }
    	    
    	    Vector objPrivilegeCollection = (Vector)user.getPrivilegeCollection();
    	       		
    		objUserPrivDel = expBuilder.get("userDetail").get("userId").equal(user.getUserId());
    		Collection objUserPrivTemp = objUnitOfWork.readAllObjects(UserPriv.class,objUserPrivDel);
    		objUnitOfWork.logMessages();
    		objUnitOfWork.deleteAllObjects(objUserPrivTemp);
    		
    		newObjUser.setPrivilegeCollection(new Vector());
    		int privilegeCollectionSize = objPrivilegeCollection == null ? 0 : objPrivilegeCollection.size();
    		for(int i=0;i<privilegeCollectionSize;i++)		//iterate to edit user privileges in UserPriv object
			{
				objUserPriv = (UserPriv) objPrivilegeCollection.get(i);
				Integer refId = objUserPriv.getModuleDetail().getRefId();
				newReference = expBuilder.get("refId").equal(refId);
				newObjReference = (Reference)objUnitOfWork.readObject(Reference.class,newReference);
				objUnitOfWork.logMessages();
				objUserPriv.setModuleDetail(newObjReference);
				objUserPriv.setUserDetail(newObjUser);
				objUserPriv.setCreationDateTime(user.getCreationDateTime());
				objUserPriv.setCreatedBy(user.getCreatedBy());
				newObjUser.addToPrivilegeCollection(objUserPriv);
			}
    		Vector objPartyCollection = (Vector)user.getPartyCollection();
			
			newObjUser.setPartyCollection(new Vector());
			int partyCollectionSize = objPartyCollection == null ? 0 : objPartyCollection.size();
			for(int i=0;i<partyCollectionSize;i++)		//iterate to edit parties associated to the user 
			{
				objParty = (Party)objPartyCollection.get(i);
				String san = objParty.getSan();
    			newPartySan = expBuilder.get("san").equal(san);
    			objParty = (Party)objUnitOfWork.readObject(Party.class,newPartySan);
    			objUnitOfWork.logMessages();
				String partyCountry = objParty.getCountryDetail().getCountryCode();
				newPartyCountry = expBuilder.get("countryCode").equal(partyCountry);
    			Country newObjPartyCountry = (Country)objUnitOfWork.readObject(Country.class,newPartyCountry);
    			objParty.setCountryDetail(newObjPartyCountry);
    			  			
    			newObjUser.addToPartyCollection(objParty);
    			
			}
    		
			    					
    	    String roleType = user.getRoleTypeDetail().getRoleType();
    		roleExp = expBuilder.get("roleType").equal(roleType);
    		UserRole objUserRole = (UserRole)objUnitOfWork.readObject(UserRole.class,roleExp);
    		
    	    newObjUser.setRoleTypeDetail(objUserRole);
    	    newObjUser.setActiveFlag(user.getActiveFlag());
    	    
    	    if(user.getPassword()!=null)
    	    {
    	    	newObjUser.setPassword(user.getPassword().trim());
    	    }
    	    
    	   newObjUser.setPasswordExpiry(user.getPasswordExpiry());
    	    
    	   // newObjUser.setPasswordExpiry("Y");
    	    if(user.getPhone1()!=null)
    	    {
    	    	newObjUser.setPhone1(user.getPhone1().trim());
    	    }
    	    
    	    if(user.getPhone2()!=null)
    	    {
    	    	newObjUser.setPhone2(user.getPhone2().trim());
    	    }
    	    if(user.getFax1()!=null)
    	    {
    	    	newObjUser.setFax1(user.getFax1().trim());
    	    }
    	    if(user.getFax2()!=null)
    	    {
    	    	newObjUser.setFax2(user.getFax2().trim());
    	    }
    	   
    	    newObjUser.setEmailActivityFlag(user.getEmailActivityFlag());
    	    if(user.getAddress1()!=null)
    	    {
    	    	newObjUser.setAddress1(user.getAddress1().trim());
    	    }
    	    if(user.getAddress2()!=null)
    	    {
    	    	newObjUser.setAddress2(user.getAddress2().trim());
    	    }
    	    if(user.getAddress3()!=null)
    	    {
    	    	newObjUser.setAddress3(user.getAddress3().trim());
    	    }
    	    if(user.getAddress4()!=null)
    	    {
    	    	newObjUser.setAddress4(user.getAddress4().trim());
    	    }
    	    if(user.getState()!=null)
    	    {
    	    	newObjUser.setState(user.getState().trim());
    	    }
    	    if(user.getCity()!=null)
    	    {
    	    	newObjUser.setCity(user.getCity().trim());
    	    }
    	    if(user.getPostalCode()!=null)
    	    {
    	    	newObjUser.setPostalCode(user.getPostalCode().trim());
    	    }
    	    
    	    String countryCode = user.getCountryDetail().getCountryCode();
     	    counExp = expBuilder.get("countryCode").equal(countryCode);
     		Country objCountry = (Country)objUnitOfWork.readObject(Country.class,counExp);
     		
     		newObjUser.setCountryDetail(objCountry);
     		if(user.getEmail()!=null)
     		{
     			newObjUser.setEmail(user.getEmail().trim());
     		}
     		if(user.getMobile()!=null)
     		{
     			newObjUser.setMobile(user.getMobile().trim());
     		}
     		if(user.getWebsite()!=null)
     		{
     			newObjUser.setWebsite(user.getWebsite().trim());
     		}
    	    newObjUser.setModifiedBy(user.getModifiedBy());  
    	    
    	    objUnitOfWork.commit();
    	    
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,editUserDetail",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return user.getLogin();
    }
   
    
    /**
     * Method to delete the user from the database while editing the user. 
     * 
     * @param userId
     * @return java.lang.String
     */
    
    public String deleteUser(Integer userId) throws AppException
    {
    	Session objSession =  null;
    	User objUser = null;
    	UnitOfWork objUnitOfWork = null;
    	ExpressionBuilder objExpressionBuilder = null;
    	Expression objExpression = null;
    	try
    	{
    		objSession =  getClientSession();
    		objUnitOfWork = getUnitOfWork(objSession);
    		objExpressionBuilder = new ExpressionBuilder();
		 	objExpression = objExpressionBuilder.get("userId").equal(userId);
		 	objUser = (User)objUnitOfWork.readObject(User.class,objExpression); //getting the user Object on the basis of userId to be deleted
		 	
		 	objUser = (User)objUnitOfWork.registerObject(objUser);
		 	
    		objUser.setActiveFlag("N");
    		objUnitOfWork.commit();
    		return objUser.getLogin();
    		
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,deleteUser",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    }
    
    
	/**
	 * Method to get the basic information of party from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 * @throws AppException 
	 */
    public HashMap getBasicPartyInfo() throws AppException
    {
    	
    	HashMap objHashMap = null;
    	Vector listVector = null;
    	Session objSession = null;
    	try
    	{
    		objSession = getClientSession();
	        objHashMap = new HashMap();
	        /*Query to get the country list*/
    		listVector = (Vector)objSession.readAllObjects(Country.class,new SQLCall("select country_code, country_name from pix_country_code order by country_name"));
    		objHashMap.put(PIXConstants.COUNTRY_LIST,listVector);
    		return objHashMap;
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getBasicPartyInfo",e);
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return objHashMap;
	}
    
    
    /**
	 * Method to get the basic information of partyTransport from DB to be shown intially in 
	 * drop-downs etc.
	 * 
	 * @return java.util.HashMap
	 * @throws AppException 
	 */
    
    public HashMap getBasicPartyTransportInfo() throws AppException
    {
    	Session objSession = null;
    	HashMap objHashMap =null;
    	Vector listVector=null;
	    try
    	{
	    	objSession = getClientSession();
	        objHashMap = new HashMap();
	        /*Query to get the access method from Reference*/
    		listVector = (Vector)objSession.readAllObjects(Reference.class,new SQLCall("select ref_id, description from pix_ref where group_code='TRANSPORT_LOCATION' order by description"));
    		objHashMap.put(PIXConstants.ACCESS_METHOD,listVector);
    		return objHashMap;
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getBasicPartyTransportInfo",e);
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return objHashMap;
	  
    }
    
    /**
	 * Inserts the details of the user into DB and returns the list of parties
	 * 
	 * @param party
	 * @return java.lang.String
	 */
	
    public String insertPartyDetail(Party party) throws AppException
    {
	   
    	Session objSession =  null;
    	UnitOfWork objUnitOfWork = null;
    	String san = null;
    	ExpressionBuilder expBuilder = null;
    	Expression sanCheck = null;
    	Expression newCountry = null;
    	Expression newReference = null;
    	Reference newObjReference = null;
    	try
    	{
    		objSession =  getClientSession();
    		PartyTransport objPartyTransport = party.getTransportDetail();
    		expBuilder = new ExpressionBuilder();
    		objUnitOfWork = getUnitOfWork(objSession);
    		san = party.getSan();
    		sanCheck = expBuilder.get("san").equal(san);
    		Party newParty = (Party)objSession.readObject(Party.class,sanCheck);
    		if(newParty != null)		//Check if san already exists
    		{
    			AppException appException=new AppException();
    			log.info("log info  :San already exists ");
  				appException.performErrorAction(Exceptions.SAN_ALREADY_EXISTS,"AdminDAOImpl");  
  				throw appException;
    		}
    		else					//if san doesn't already exists
    		{
    			String country = party.getCountryDetail().getCountryCode();
    			newCountry = expBuilder.get("countryCode").equal(country);
    			Country newObjCountry = (Country)objUnitOfWork.readObject(Country.class,newCountry);
    			party.setCountryDetail(newObjCountry);
    			
    			if(party.getPartyType().equals("M") || party.getPartyType().equals("D") || party.getPartyType().equals("V"))
    			//if(party.getPartyType().equals(PIXConstants.SUPPLIER_PARTY_TYPE))	//for inserting suppliers setting the transportDetails
    			{
    				Integer refId = party.getTransportDetail().getAccessMethodDetail().getRefId();
    				newReference = expBuilder.get("refId").equal(refId);
    				newObjReference = (Reference)objUnitOfWork.readObject(Reference.class,newReference);
    				
    				objPartyTransport.setAccessMethodDetail(newObjReference);
    				party.setTransportDetail(objPartyTransport);
    			}
    			objUnitOfWork.registerObject(party);
    			objUnitOfWork.commit();
    		}
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		e.printStackTrace();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,insertPartyDetail",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return san;
    }
   
    /**
     * Gets the detail of a party from the DB
     * 
     * @param san
     * @return com.pearson.pix.dto.admin.Party
     */
    public Party getPartyDetail(String san) throws AppException
    {
    	Session objSession = null;
    	Party objParty = null;
    	ExpressionBuilder objExpressionBuilder = null;
    	Expression objExpression = null;
    	try{
    		objSession = getClientSession();
    		objExpressionBuilder = new ExpressionBuilder();
    		objExpression = objExpressionBuilder.get("san").equal(san);
    		objParty = (Party)objSession.readObject(Party.class,objExpression);	//getting the party detail on the basis of San
    	}
	    catch(TopLinkException e)
	    {
	    	AppException appException = new AppException();
	    	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "AdminDAOImpl,getPartyDetail",e);
		    throw appException;
	    }
	    finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
		return objParty; 
	}
   
    /**
     * Gets the collection of parties from DB based on the parameters decided.
     * 
     * @param adminParty
     * @param startDate
     * @param endDate
     * @param currentValue
     * @param objOrderBy
     * @param objSort
     * @return java.util.Collection
     */
    
    public Vector getPartiesList(Party adminParty,String startDate,String endDate,int currentValue,String objOrderBy,String objSort) throws AppException
    {
	   
    	Session objSession = getClientSession();
    	Vector objListVector = new Vector();
    	Country objCountry = null;
    	String partyType = null;
    	String activeFlag=(adminParty.getActiveFlag()=="")?null:adminParty.getActiveFlag();
    	try
    	{
    		StoredProcedureCall call = new StoredProcedureCall(); 
    		call.setProcedureName("Pix_Get_Party_Proc");
    		call.addNamedArgumentValue("i_san",adminParty.getSan());
    		call.addNamedArgumentValue("i_name",adminParty.getName1());
    		call.addNamedArgumentValue("i_status",activeFlag);
    		call.addNamedArgumentValue("i_party_type",adminParty.getPartyType());
    		call.addNamedArgumentValue("i_start_date",startDate);
    		call.addNamedArgumentValue("i_end_date",endDate);
    		call.addNamedArgumentValue("i_pagination",new Integer(currentValue));
    		call.addNamedArgumentValue("i_order_by",objOrderBy);
    		call.addNamedArgumentValue("i_sort",objSort); 
    		call.useNamedCursorOutputAsResultSet("list_refcursor");
    		
    		Vector objVector = (Vector)objSession.executeSelectingCall(call);
    		int vectorSize = objVector == null ? 0 : objVector.size();
    		if (objVector != null && vectorSize>0)				//checking for vector size
    		{
    			for (int i = 0; i < vectorSize; i++)			//iterating to add each record in listVector
    			{
    				Record objDatabaseRecord = (Record)objVector.get(i);
           		  	String objsan = null;
           		  	String name = null;
           		  	String status = null;
           		  	Date creationDateTime = null;
           		  	//Integer createdBy = null;
           		  	String createdBy = null;
           		  	String countryCode = null;
        		    adminParty = new Party();
        		    objCountry = new Country();
           		  	if((objsan= (String)objDatabaseRecord.get(PIXConstants.PUBLISHER_SAN))!=null)	//get the san and set it in party object
           		  	{
           		  		adminParty.setSan(objsan);
           		  	}	   
           		  	if((name = (String)objDatabaseRecord.get(PIXConstants.PUBLISHER_NAME))!= null)	//get the name and set it in party object
           		  	{   
           		  		adminParty.setName1(name);
           		  	}
           		  	if((status = (String)objDatabaseRecord.get(PIXConstants.PUBLISHER_STATUS))!= null)	//get the status and set it in party object
           		  	{  
           		  		adminParty.setActiveFlag(status);
           		  	}
           		  	if((creationDateTime = (Date)objDatabaseRecord.get(PIXConstants.CREATION_DATE_TIME))!= null)	//get the creationDateTime and set it in party object
           		  	{   
           		  		adminParty.setCreationDateTime(creationDateTime);
           		  	}
           		  	if((createdBy = (String)objDatabaseRecord.get(PIXConstants.MODIFIED_BY))!= null)	//get the creationBy and set it in party object
           		  	{   
           		 		adminParty.setFullName(createdBy);
           		  	}
           		   
           		  	if((partyType = (String)objDatabaseRecord.get(PIXConstants.PARTY_TYPE))!= null)	//get the creationBy and set it in party object
        		  	{   
        		 		adminParty.setPartyType(partyType);
        		  	}
           		  	
           		 
           		  	countryCode = (String)objDatabaseRecord.get(PIXConstants.COUNTRY_CODE);			
           		  	objCountry.setCountryCode(countryCode);
           		  	adminParty.setCountryDetail(objCountry);
           		  	objListVector.add(adminParty);
        	    }
    		}
    		else if(vectorSize== 0 || objVector != null)	//check if no records are found
            {
         	     AppException appException=new AppException();
         	     log.info("log info  :no records ");
         	     if(adminParty.getPartyType().equals(PIXConstants.PUBLISHING_UNIT_PARTY_TYPE ))
         	     {
         	    	 appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_PUBLISHER,"AdminDAOImpl");
         	     }
         	     else if(adminParty.getPartyType().equals(PIXConstants.SUPPLIER_PARTY_TYPE))
         	     {
         	    	appException.performErrorAction(Exceptions.RECORDS_NOT_EXISTS_SUPPLIER,"AdminDAOImpl");
         	     }
   				 throw appException;
            }
        }
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getPartiesList",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return objListVector;
    } 
    
    /**
	 * update the details of the party into DB 
	 * 
	 * @param party
	 * @return java.lang.String
	 */
    
    public String editPartyDetail(Party party) throws AppException
    {
        	   
    	Session objSession =  null;
    	UnitOfWork objUnitOfWork = null;
    	ExpressionBuilder expBuilder = null;
    	PartyTransport newObjPartyTransport = null;
    	Expression counExp = null;
    	Expression sanexp = null;
    	Reference objReference = null;
    	Expression sanexpTransport = null;
    	try
    	{
    		objSession =  getClientSession();
    		expBuilder = new ExpressionBuilder();
    		objUnitOfWork = getUnitOfWork(objSession);
    		
    		    		
    		String countryCode = party.getCountryDetail().getCountryCode();
    		counExp = expBuilder.get("countryCode").equal(countryCode);
    		Country objCountry = (Country)objUnitOfWork.readObject(Country.class,counExp);
    		party.setCountryDetail(objCountry);
    		
    		sanexp = expBuilder.get("san").equal(party.getSan());
    	    Party newObjParty = (Party) objUnitOfWork.readObject(Party.class,sanexp);
            newObjParty = (Party)objUnitOfWork.registerObject(newObjParty);		//read party object on basis of San to edit
            
            if(party.getName1()!=null)
            {
            	newObjParty.setName1(party.getName1().trim());
            }
            if(party.getActiveFlag()!=null)
            {
            	newObjParty.setActiveFlag(party.getActiveFlag().trim());
            }
            if(party.getContactFirstName()!=null)
            {
            	newObjParty.setContactFirstName(party.getContactFirstName().trim());
            }
            if(party.getContactLastName()!=null)
            {
            	newObjParty.setContactLastName(party.getContactLastName().trim());
            }
            if(party.getPhone1()!=null)
            {
            	newObjParty.setPhone1(party.getPhone1().trim());
            }
            if(party.getPhone2()!=null)
            {
            	newObjParty.setPhone2(party.getPhone2().trim());
            }
            if(party.getFax1()!=null)
            {
            	newObjParty.setFax1(party.getFax1().trim());
            }
            if(party.getFax2()!=null)
            {
            	newObjParty.setFax2(party.getFax2().trim());
            }
            if(party.getMobile()!=null)
            {
            	newObjParty.setMobile(party.getMobile().trim());
            }
            if(party.getAddress1()!=null)
            {
            	newObjParty.setAddress1(party.getAddress1().trim());
            }
            if(party.getAddress2()!=null)
            {
            	newObjParty.setAddress2(party.getAddress2().trim());
            }
            if(party.getAddress3()!=null)
            {
            	newObjParty.setAddress3(party.getAddress3().trim());
            }
            if(party.getAddress4()!=null)
            {
            	newObjParty.setAddress4(party.getAddress4().trim());
            }
            if(party.getState()!=null)
            {
            	newObjParty.setState(party.getState().trim());
            }
            if(party.getCity()!=null)
            {
            	newObjParty.setCity(party.getCity().trim());
            }
            if(party.getPostalCode()!=null)
            {
            	newObjParty.setPostalCode(party.getPostalCode().trim());
            }
            
    	    newObjParty.setCountryDetail(objCountry);
    	    if(party.getEmail()!=null)
    	    {
    	    	newObjParty.setEmail(party.getEmail().trim());
    	    }
    	    if(party.getWebsite()!=null)
    	    {
    	    	newObjParty.setWebsite(party.getWebsite().trim());
    	    }
    	    newObjParty.setModDateTime(party.getModDateTime());
    	    newObjParty.setModifiedBy(party.getModifiedBy());  
    	    newObjParty.setPartyType(party.getPartyType());
    	    //if(party.getPartyType()==PIXConstants.SUPPLIER_PARTY_TYPE)	//if party is supplier then editing transportDetails
    	    if(party.getPartyType().equals("M") || party.getPartyType().equals("D") || party.getPartyType().equals("V"))
    		{
    			Integer refId = party.getTransportDetail().getAccessMethodDetail().getRefId();
    			Expression refIdExp = expBuilder.get("refId").equal(refId);
    			objReference = (Reference)objUnitOfWork.readObject(Reference.class,refIdExp);
    			    			
    			sanexpTransport = expBuilder.get("san").equal(party.getSan());
    			newObjPartyTransport = (PartyTransport)objUnitOfWork.readObject(PartyTransport.class,sanexpTransport);
    			newObjPartyTransport.setAccessMethodDetail(objReference);
    			
    			if(party.getTransportDetail().getServerName()!=null)
    			{
    				newObjPartyTransport.setServerName(party.getTransportDetail().getServerName().trim());
    			}
    			if(party.getTransportDetail().getLogin()!=null)
    			{
    				newObjPartyTransport.setLogin(party.getTransportDetail().getLogin().trim());
    			}
    			if(party.getTransportDetail().getPassword()!=null)
    			{
    				newObjPartyTransport.setPassword(party.getTransportDetail().getPassword().trim());
    			}
    			if(party.getTransportDetail().getFolder()!=null)
    			{
    				newObjPartyTransport.setFolder(party.getTransportDetail().getFolder().trim());
    			}
    			if(party.getTransportDetail().getPutFolder()!=null)
    			{
    				newObjPartyTransport.setPutFolder(party.getTransportDetail().getPutFolder().trim());
    			}
    			if(party.getSan()!=null)
    			{
    				newObjPartyTransport.setSan(party.getSan().trim());
    			}
    			newObjPartyTransport.setModDateTime(party.getModDateTime());
    			newObjPartyTransport.setModifiedBy(party.getModifiedBy());
    			
    			newObjParty.setTransportDetail(newObjPartyTransport);
    	    }
    	    objUnitOfWork.commit();
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,editPartyDetail",e);
    		throw appException;
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return party.getSan();
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
    	String pass = "";
    	UserTransaction transaction = null; 
 	    Connection conn = null;
    	Session objSession = null;
    	
    	
    	try
    	{
    		
    	    transaction = getUserTransaction();
   		    conn = getDataSourceConnection(); 
   		    Statement stmt = conn.createStatement();
    		objSession = getClientSession();
    		
    		String linkFromPepms = user.getLinkFromPepms();
    		
    		ExpressionBuilder expvariable = new ExpressionBuilder();
    		Expression expression = null;
    		if(linkFromPepms != null && linkFromPepms.equals("yes"))
    		{
    			expression = expvariable.get("login").equal(user.getLogin());
    		}
    		else{
    			if(user.getSsoid() == null)
    			{
    				return null;
    			}
    			else
    				expression = (expvariable.get("ssoid").toUpperCase()).equal(user.getSsoid().toUpperCase());
    		}
//    		Expression expression = expvariable.get("ssoid").equal(user.getSsoid());		// to be un commented
//			Expression expression = expvariable.get("login").equal(user.getLogin());
			objUser = (User) objSession.readObject(User.class,expression);
			//objSession.logMessages();
			//System.out.println(".....objUser..."+objUser);
			//System.out.println(".....active flag...."+objUser.getActiveFlag());
			//Integer userid = objUser.getUserId(); 
			if ((objUser!=null) && ("Y".equals(objUser.getActiveFlag()))){
				pass=objUser.getPassword();
//	 			if (!(user.getPassword().equals(pass)))
//				{
//	 				AppException appException = new AppException();
//	 				appException.performErrorAction(Exceptions.INVALID_PASSWORD, "AdminDAOImpl,authenticateUser");
//	 				
//	 				throw appException;
//				}
//	 			else
//	 				
//	 			{
//	 				stmt.executeUpdate("update PIX_ADMIN_USER set last_login=sysdate where login='"+user.getLogin()+"'");
	 				
					stmt.executeUpdate("update PIX_ADMIN_USER set last_login=sysdate, FIRST_NAME = '"+user.getFirstName()+"', LAST_NAME = '"+user.getLastName()+"', EMAIL = '"+user.getEmail()+"' where SSOID='"+user.getSsoid()+"'");	// to be un commented
//	 			}
			}
			
			else if((objUser!=null) && ("N".equals(objUser.getActiveFlag())))
			{
				AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.ACCOUNT_LOCKED, "AdminDAOImpl,authenticateUser");
		        throw appException;
			}
			
			/*else
 			{
 				AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.INVALID_USER, "AdminDAOImpl,authenticateUser");
		        throw appException;
		    }*/	
			
			return (objUser);				
	            	
	    }
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
		                "AdminDAOImpl,authenticateUser",e);
    		try 
   		   	{ 
    			transaction.setRollbackOnly();
   		   	} 
   		   	catch(Throwable tu) 
   		   	{ 
   		   		throw appException; 
   		   	}
    		throw appException;
    	}
    
    	catch(SQLException se)
    	{
		   AppException ae = new AppException();
		   se.printStackTrace();
		   ae.performErrorAction(Exceptions.SQL_EXCEPTION,"AdminDAOImpl,authenticateUser",se);
		   try 
		   { 
			   	transaction.setRollbackOnly();
		   } 
		   catch(Throwable tu) 
		   { 
			   	throw ae; 
		   }
		   throw ae;
	   }
       finally
	   {
    	   try 
		   { 
    		    if(conn!=null)
    		    {
    		    	conn.close();
    		    }
			   	if(objSession!=null)
			   	{
			   		objSession.release();
			   	}
		   } 
		   catch(Throwable tu) 
		   { 
			   	throw new RuntimeException(tu); 
		   }
	   }  
	}
    
    /**
	 * Method to send Email
	 *
	 * @param user
	 */
    
    public void sendEmail(String login) throws AppException
    {
    	User objUser = null;
    	Session objSession = null;
    	
    	
    	try
    	{
    		objSession = getClientSession();
    		ExpressionBuilder expvariable = new ExpressionBuilder();
    		Expression expression = expvariable.get("login").equal(login);
    		objUser = (User) objSession.readObject(User.class,expression);
    		
    		if ((objUser!=null) && ("Y".equals(objUser.getActiveFlag()))&&(objUser.getEmail()!=null)){
    			
	    		Integer userid=objUser.getUserId();
		    	StoredProcedureCall call=new StoredProcedureCall();
		    	call.setProcedureName("PIX_EMAIL_PKG.pix_forgot_password_proc");
		    	call.addNamedArgumentValue("v_user_id",userid);
		    	objSession.executeNonSelectingCall(call);
		   	}
    		else if((objUser!=null) && ("N".equals(objUser.getActiveFlag())))
    		{
    			AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.ACCOUNT_LOCKED, "AdminDAOImpl,sendEmail");
		        throw appException;
    		}
    		else
 			{
 				AppException appException = new AppException();
		        appException.performErrorAction(Exceptions.INVALID_USER, "AdminDAOImpl,sendEmail");
		        throw appException;
		    }
    		
     	}
        catch(TopLinkException e)
        {
        	AppException appException = new AppException();
        	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,sendEmail",e);
        	throw appException;
        }
        finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    
    }
    
    public String getPasswordInfo(Integer userid,String pass)throws AppException
	  {
		Session clientSession = getClientSession();
		String result="";
		try
		{
			StoredProcedureCall call=new StoredProcedureCall();
			call.setProcedureName("Pix_password_hist_Proc");
  		call.addNamedArgumentValue("i_USER_ID",userid);
  		call.addNamedArgumentValue(" i_PASSWORD",pass);
  		call.addNamedOutputArgument(" o_result");
  		
  		call.setUsesBinding(true);
			 ValueReadQuery query = new ValueReadQuery();

			    query.setCall(call);
			    query.bindAllParameters();
			    query.addArgument("i_USER_ID");
			    query.addArgument("i_PASSWORD");
			    Vector params = new Vector();
			    params.addElement(new Integer(5));
			    params.addElement(new Integer(5));
			     result = (String) clientSession.executeQuery(query, params);
		}
		 catch(TopLinkException e)
       {
       	AppException appException = new AppException();
       	appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getPasswordInfo",e);
       	throw appException;
       }
		 
		
       finally
   	{
   		if(clientSession!=null)
   		{
   			clientSession.release();
   		}
   	}
	  
       return result;
	  } 
    
    
    
    /**
	 * Method to get the supplier types from DB to be shown intially in 
	 * drop-downs etc.(MIll/Merchant/Supplier)
	 * 
	 * @return java.util.Vector
	 */
	public Vector getSupplierTypes() throws AppException
	{
		Session objSession = null;
    	//HashMap objHashMap =null;
    	Vector listVector=null;
    	
	    try
    	{
	    	objSession = getClientSession();
	        //objHashMap = new HashMap();
	        /*Query to get the access method from Reference*/
	    		    	
    		listVector = (Vector)objSession.readAllObjects(Reference.class,new SQLCall("select ref_id,ref_code, description from pix_ref where group_code='PARTY_TYPE' order by description"));
    		//objHashMap.put(PIXConstants.ACCESS_METHOD,listVector);
    		return listVector;
    	}
    	catch(TopLinkException e)
    	{
    		AppException appException = new AppException();
    		appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
	                "AdminDAOImpl,getSupplierTypes",e);
    	}
    	finally
    	{
    		if(objSession!=null)
    		{
    			objSession.release();
    		}
    	}
    	return listVector;
	}
	
	
	
	
	public UserCountDTO accountLock(HashMap userCount,String loginid) throws AppException
	{
		UserCountDTO output=null;
		Session objSession =  null;
		UnitOfWork objUnitOfWork = null;
		User objUser=null;
		Integer countIn=null;
		Session clientSession = getClientSession();
		String value="";
		//---------------------------------------------------------
		try{
		     objSession =getClientSession();
		     objUnitOfWork = getUnitOfWork(objSession);
	         ExpressionBuilder b = new ExpressionBuilder();
	         Expression vpcsUserExp = b.get("login").equal(loginid);
	         objUser = (User)objUnitOfWork.readObject(User.class,vpcsUserExp);
	         if(objUser!=null){	        	 
	        	  output=new UserCountDTO();
	        	  output.setLoginId(objUser.getLogin());
	         }
	         if(userCount!=null&&output!=null){	        	 
	        	 countIn=(Integer)userCount.get(output.getLoginId());
	         }         
	        
	        if(objUser!=null&&countIn!=null&&countIn.intValue()==4){
	        StoredProcedureCall call = new StoredProcedureCall();
	 		call.setProcedureName("pix_admin_user_lock");  
	 		call.addNamedArgumentValue("i_LOGIN",loginid);
	 		call.addNamedOutputArgument(" o_result");
	 		ValueReadQuery query = new ValueReadQuery();

		    query.setCall(call);
		    query.bindAllParameters();
		    query.addArgument("i_LOGIN");
	 		
	    Vector params = new Vector();
	    params.addElement(new String());
	    
	    value = (String) clientSession.executeQuery(query, params);
	    if(value.equals("Y")){	    	
	    	output.setMsg(value);
	    	AppException appException = new AppException();
	    	appException.performErrorAction(Exceptions.ACCOUNT_LOCKED, "AdminDAOImpl,accountLock");
	    	throw appException;
	    }
	    
	    
	   }
	}
	 catch(TopLinkException ae ){
	 }
	 finally{
		 if(objSession!=null){
		 objSession.release();
		 }
	 }
	return output;
  
}
	}
