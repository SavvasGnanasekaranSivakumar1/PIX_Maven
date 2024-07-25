package com.pearson.pix.presentation.orderstatus.delegate;


import java.util.HashMap;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocal;
import com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome;

/**
 * Business Delegate for Order Status. It acts as the Client for EJB invocation.
 * @author Dandu Thirupathi
 */
public class OrderStatusDelegate 
{
   
   /**
    * Constructor to intialize Order Status Delegate
    */
   public OrderStatusDelegate() 
   {
    
   }
   
   /**
    * Calls corresponding Business method of Order Status EJB for the purpose of 
    * Order Status List. This method also takes care of pagination by 
    * passing page no and its value respectively.
    * 
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Vector
    */
   public Vector displayOrderStatusList(Integer poId,Integer poVersion,Integer pagination,String orderBy,String sort) throws AppException 
   {
	   Vector objVector = getOrderStatusLocal().displayOrderStatusList(poId,poVersion,pagination,orderBy,sort);	   
	
	   return objVector;   
   }
   
   /**
    * Calls corresponding Business method of Order Status EJB for the purpose of 
    * Order Status Detail. 
    * 
    * @param poId
    * @param poVersion
    * @param statusId
    * @return java.util.Vector
    */
   public Vector displayOrderStatusDetail(Integer poId,Integer poVersion, String statusId)throws AppException
   {
	   Vector objVectorDisplay = getOrderStatusLocal().displayOrderStatusDetail(poId,poVersion,statusId);
	   
	   return objVectorDisplay;
   }
   
   /**
    * Calls corresponding Business method of Order Status EJB for the purpose of 
    * Basic Information for Order Status submit.
    * 
    * @param poId
    * @param poVersion
    * @return java.util.HashMap
    */
   public HashMap displayBasicOrderStatusInfo(Integer poId,Integer poVersion) throws AppException
   {
	   HashMap objHashMap = getOrderStatusLocal().displayBasicOrderStatusInfo(poId,poVersion);
	   
	   return objHashMap;
   }
   
   /**
    * Calls corresponding Business method of Order Status EJB for the purpose of 
    * saving information of Order Status.
    * 
    * @param orderStatus
    * @param poId
    * @param poVersion
    * @param poNo
    * @param rNo
    * @param login
    * @return java.lang.String
    */
   public String saveOrderStatus(OrderStatus orderStatus,Integer poId,Integer poVersion,String poNo,Integer rNo,String login)  throws AppException
   {
	   String objStatusNo=null;
	   objStatusNo = getOrderStatusLocal().saveOrderStatus(orderStatus,poId,poVersion,poNo,rNo,login);
	   
	   return objStatusNo;
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * 
    * @return com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocal
    */
   private OrderStatusLocal getOrderStatusLocal() throws AppException
   {
	   OrderStatusLocalHome orderStatusLocalHome=null;
	   OrderStatusLocal objOrderStatusLocal=null;
	   	   
	   try{
		   orderStatusLocalHome= ServiceLocator.getOrderStatusLocalHome(); 
		   objOrderStatusLocal = orderStatusLocalHome.create();
	   
	   }catch(NamingException ne)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.NAMING_EXCEPTION,"UsageDelegate,getUsageLocal",ne);
		   throw ae;
	   }
	   catch(CreateException ce)
	   {
		   AppException ae = new AppException();
		   ae.performErrorAction(Exceptions.CREATE_EXCEPTION,"UsageDelegate,getUsageLocal",ce);
		   throw ae;
	   }
    return objOrderStatusLocal;
   }
}
