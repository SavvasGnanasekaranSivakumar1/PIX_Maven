package com.pearson.pix.presentation.usage.delegate;

import com.pearson.pix.business.usage.interfaces.UsageLocal;
import com.pearson.pix.business.usage.interfaces.UsageLocalHome;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.common.ServiceLocator;

/**
 * Business Delegate for Usage. It acts as the Client for EJB invocation.
 * @author Dandu Thirupathi
 */
public class UsageDelegate 
{
   
   /**
    * Constructor for Initializing UsageDelegate
    */
   public UsageDelegate() 
   {
    
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * @return com.pearson.pix.business.usage.interfaces.UsageLocal
    */
   private UsageLocal getUsageLocal() throws AppException
   {
	   UsageLocal usageLocal = null;
	   UsageLocalHome usageLocalHome =null;
	   try
	   {     
		   usageLocalHome = ServiceLocator.getUsageLocalHome();
		   usageLocal = usageLocalHome.create();
	   }
	   catch(NamingException ne)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.NAMING_EXCEPTION,"UsageDelegate,getUsageLocal",ae);
		   throw ae;
	   }
	   catch(CreateException ce)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.CREATE_EXCEPTION,"UsageDelegate,getUsageLocal",ae);
		   throw ae;
	   }
	   return usageLocal;
   }
   
   /**
    * Calls corresponding Business method of Usage EJB for the purpose of Usage List. 
    * This method also takes care of filtering, pagination by passing page no, filter 
    * criteria and its value respectively.
    * 
    * @param Usage
    * @param startDate
    * @param endDate
    * @param poNo
    * @param userId
    * @param page
    * @param pagination
    * @param orderby
    * @param sort
    * @return java.util.Collection
    */
   public Collection displayUsageList(Usage objUsage,String startDate,String endDate,String poNo,int userId,String page,int pagination,String orderby,String sort) throws AppException 
   {
	   Collection objCollection = null;
       objCollection = getUsageLocal().displayUsageList(objUsage,startDate,endDate,poNo,userId,page,pagination,orderby,sort);
       
       return objCollection;
   }
     
   /**
    * Calls corresponding Business method of Usage EJB for the purpose of Usage Status
    * 
    * @return java.util.Collection
    */
   
   public Collection displayUsageStatus() throws AppException
   {
	   Collection objCollection = null;
       objCollection = getUsageLocal().displayUsageStatus();
       
       return objCollection; 
   }
   
   /**
    * Calls corresponding Business method of Usage EJB for the purpose of Usage Detail
    * 
    * @param poNumber
    * @param usageNumber
    * @param usageId
    * @return java.util.Collection
    */
   public HashMap displayUsageDetail(Integer poNumber, Integer poVersion, Integer usageId) throws AppException
   {
	 HashMap objHashMap= getUsageLocal().displayUsageDetail(poNumber,poVersion,usageId);
	 
     return objHashMap;
   }
   
   /**
    * Calls corresponding Business method of Usage EJB for the purpose of 
    * saving information of Usage info.
    * 
    * @param Usage
    * @param PoId
    * @param objPoVersion
    * @param PoNo
    * @param Rno
    * @param User
    * @return java.lang.String
    */
   public String saveUsage(Usage objUsage,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String objUser)  throws AppException 
   {
	   String usageNo=null;
	   
	   usageNo= getUsageLocal().saveUsage(objUsage,objPoId,objPoVersion,objPoNo,objRno,objUser);
	   
	   return usageNo;
   }
}
