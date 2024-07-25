package com.pearson.pix.dao.purchaseorder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POSuppliedComp;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author Mohit Bajaj
 */
public interface PurchaseOrderDAO 
{
   
   /**
    * Gets the Order List(Purchase Order/Furinished Order) from the database
    * @return java.util.Collection
    */
   public Collection getOrderList(POHeader objpoheader,String startDate,String endDate,String partyType,int userId,String page,int pagination,String orderby,String sort,String sbBDate,String ebBDate) throws AppException;
   
   /**
    * Gets the Order Detail (Purchase Order/Furinished Order) from the database
    * 
    * @param poId
    * @param poVersion
    * @param party
    * @param userId
    * @param paperFO
    * @return java.util.HashMap
    */
   public HashMap getOrderDetail(BigDecimal poId, BigDecimal poVersion,String party,Integer userId,String paperFo) throws AppException;
   
   /**
    * Inserts the Order Confirmation for the Purchase Order
    * 
    * @return java.lang.String
    */
   public String insertOrderConfirmation(POHeader poHeader,String orderType) throws AppException;
   
   /**
    * Gets the Purchase Order Header Status List
    * @return java.util.Collection
    */
   public Collection getOrderHeaderStatusList() throws AppException;
   
   /**
    * Gets the Purchase Order Line Items Status List
    * @return java.util.Collection
    */
   public Collection getOrderLineItemsStatusList() throws AppException;
   
   /**
    * Gets the Purchase Order Status List
    * @return java.util.Collection
    */
   public Collection getOrderStatusList() throws AppException;
   
   /**
    * Gets the Purchase Order Line Items for Supplied Components
    * @param poId
    * @param poVersion
    * @return java.util.Collection
    */
   public Collection getOrderLineItems(BigDecimal poId, BigDecimal poVersion) throws AppException;   
   
   /**
    * Inserts multiple Purchase Orders into database for Acceptance
    * 
    * @param idVersionString
    * @param login
    * @return String 
    */
   public String insertMultiplePOAccept(String idVersionString,String login) throws AppException;
      
}
