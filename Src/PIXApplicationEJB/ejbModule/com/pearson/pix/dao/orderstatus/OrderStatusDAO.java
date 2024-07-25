package com.pearson.pix.dao.orderstatus;

import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dto.orderstatus.OrderStatus;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through Toplink.
 * @author Dandu Thirupathi
 */
public interface OrderStatusDAO 
{
   
   /**
    * Gets the Order Status List of PO from the database
    * @param poId
    * @param poVersion
    * @param pagination
    * @param orderBy
    * @param sort
    * @return java.util.Vector
    */
   public Vector getOrderStatusList(Integer poId,Integer poVersion,Integer pagination,String orderBy,String sort ) throws AppException;
   
   /**
    * Gets Order Status detail for a status number of PO from the database
    * 
    * @param poId
    * @param poVersion
    * @param statusId
    * @return java.util.Vector
    */
   public Vector getOrderStatusDetail(Integer poId,Integer poVersion, String statusId) throws AppException;
   
   /**
    * Gets the Basic Order Status information from the database
    * 
    * @param poId
    * @param poVersion
    * @return java.util.HashMap
    */
   public HashMap getBasicOrderStatusInfo(Integer poId,Integer poVersion) throws AppException;
   
   /**
    * Saves the Order Status information into the database
    * @param orderStatus
    * @param poId
    * @param poVersion
    * @param poNo
    * @param rNo
    * @param login
    * @return java.lang.String
    */
   public String insertOrderStatus(OrderStatus orderStatus,Integer poId,Integer poVersion,String poNo,Integer rNo,String login) throws AppException;
}
