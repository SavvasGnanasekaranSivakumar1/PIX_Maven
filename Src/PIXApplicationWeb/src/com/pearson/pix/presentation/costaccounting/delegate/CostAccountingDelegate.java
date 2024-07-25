package com.pearson.pix.presentation.costaccounting.delegate;


import com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocal;
import com.pearson.pix.business.costaccounting.interfaces.CostAccountingLocalHome;
import com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocal;
import com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;

/**
 * Business Delegate for CostAccounting. It acts as the Client for EJB 
 * invocation.
 * @author shweta gupta
 */
public class CostAccountingDelegate 
{
   
   /**
    * Constructor for initializing DeliveryMessageDelegate
    */
   public CostAccountingDelegate() 
   {
    
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * @return com.pearson.pix.business.costaccouting.interfaces.CostAccountingLocal
    */
   private CostAccountingLocal getCostAccountingLocal() 
   {
	   CostAccountingLocal objCostAccountingLocal = null;
	  try
	    {
			
		    CostAccountingLocalHome objCostAccountingLocalHome = ServiceLocator.getCostAccountingLocalHome();
			objCostAccountingLocal = objCostAccountingLocalHome.create();
		} 
		catch (NamingException ne)
		{
			ne.printStackTrace();
		} 
		catch (CreateException ce)
		{
		    ce.printStackTrace();
		}
		return objCostAccountingLocal;
	}
   
   /**
    * Calls corresponding Business method of Cost Accounting EJB for the purpose of 
    * Delivery Message Approval List. This method also takes care of filtering, pagination by 
    * passing page no, filter criteria and its value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getDmApprovalList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId,String materialNoFilter,String delMsgNoFilter,String millMerchantFilter,String printerFilter,String startDate,String endDate) throws AppException
   {
	   Collection objCollection = null;
       objCollection = getCostAccountingLocal().getDmApprovalList(poHeaderTemp,pagination,objorderby,objsort,userId,materialNoFilter,delMsgNoFilter,millMerchantFilter,printerFilter,startDate,endDate);
       return objCollection;
   }
   
   /**
    * Calls corresponding Business method of costAccounting EJB for the purpose of 
    * Approving multiple Delivery Message
    * 
    * @param idVersionString
    * @param userId
    * @return String
    */
   
   public String insertMultipleDMApprove(String idVersionString,Integer userId,String ownrshpMode) throws AppException
   {
	   return getCostAccountingLocal().insertMultipleDMApprove(idVersionString,userId,ownrshpMode);
   }
   
   /**
    * Calls corresponding Business method of costAccounting EJB for the purpose of 
    * getting dropdown values for filter
    * 
    * @return java.util.LinkedHashMap
    */
   
   public LinkedHashMap getFilterDropDowns(Integer userId) throws AppException
   {
	   return getCostAccountingLocal().getFilterDropDowns(userId);
   }
   
   /**
    * Calls corresponding Business method of costAccounting EJB for the purpose of 
    * updating the quantity of delivery message detail.
    * 
    
    */
   
   public String updateDeliveryMessageLine(DeliveryMessage objDeliveryMessage,Integer messageId , BigDecimal poId , BigDecimal poVersion,Integer userId) throws AppException
   {
	   return getCostAccountingLocal().updateDeliveryMessageLine(objDeliveryMessage,messageId,poId,poVersion,userId);
   }
   
   
   
   
   
   
}
