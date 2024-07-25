package com.pearson.pix.presentation.home.delegate;


import java.util.Collection;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.home.HomePageDAO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.home.interfaces.HomePageLocal;
import com.pearson.pix.business.home.interfaces.HomePageLocalHome;
import com.pearson.pix.dto.admin.*;


/**
 * Business Delegate for Home Page. It acts as the Client for EJB invocation.
 */
public class HomePageDelegate 
{
   
   /**
    * Constructor to initialize HomePageDelegate
    */
   public HomePageDelegate() 
   {
       
   }
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * 
    * @return com.pearson.pix.business.home.interfaces.HomePageLocal
    */
   private HomePageLocal getHomePageLocal() throws AppException 
   {
	   HomePageLocal objHomePageLocal = null;
		try 
		{
			HomePageLocalHome objHomePageLocalHome = ServiceLocator.getHomePageLocalHome();
			objHomePageLocal = objHomePageLocalHome.create();
		} 
		catch(NamingException ne)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","HomePageDelegate,getHomePageLocal",ae);
			   throw ae;
		}
		catch(CreateException ce)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","HomePageDelegate,getHomepageLocal",ae);
			   throw ae;
		}
		
		return objHomePageLocal;
   }
   
   /**
    * Calls corresponding Business method of HomePage EJB for the purpose of Inbox Detail
    * 
    *  @return java.util.Collection
    */
   
   public Collection displayInboxdetail(int userId) throws AppException 
   {
       return getHomePageLocal().displayInboxdetail(userId);
   
   }
   /**
    * Calls corresponding Business method of HomePage EJB for the purpose of PO Detail
    * 
    *  @return java.lang.String
    */
   
   public String getPODetails(String poNo,User user) throws AppException 
   {
	   return getHomePageLocal().getPODetails(poNo,user);
    }
}
   
   
   
   
   