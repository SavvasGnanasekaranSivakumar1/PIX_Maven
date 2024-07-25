package com.pearson.pix.dao.costaccounting;



import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 */
public interface CostAccountingDAO 
{

	/**
    * Business method for displaying the Delivery Messages Approval List
    * 
    * @return java.util.Collection
    * @J2EE_METHOD  --  getDmApprovalList
    */
   public Collection getDmApprovalList(POHeader poHeaderTemp,int pagination,String objorderby,String objsort,Integer userId,String materialNoFilter,String delMsgNoFilter,String millMerchantFilter,String printerFilter,String startDate,String endDate) throws AppException;
   
   /**
    * Business method for Approving multiple Delivery Message
    * 
    * @param idVersionString
    * @param userId
    * @return String 
    */
   public String insertMultipleDMApprove(String idVersionString,Integer userId,String ownrshpMode) throws AppException;
   
   /**
    * Calls corresponding Business method of costAccounting EJB for the purpose of 
    * getting dropdown values for filter
    * 
    * @return java.util.LinkedHashMap
    */
   
   public LinkedHashMap getFilterDropDowns(Integer userId) throws AppException;
   
   /**
    * method for the purpose of updating the quantity of delivery message detail.
    * 
    */
   
   public String updateDeliveryMessageLine(DeliveryMessage objDeliveryMessage,Integer messageId , BigDecimal poId , BigDecimal poVersion,Integer userId) throws AppException;
   
   
}
