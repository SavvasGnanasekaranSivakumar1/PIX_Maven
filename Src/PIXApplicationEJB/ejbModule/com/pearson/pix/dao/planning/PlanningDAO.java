//Source file: E:\\PIXProject\\PIXApplicationEJB\\src\\com\\pearson\\pix\\dao\\planning\\PlanningDAO.java

package com.pearson.pix.dao.planning;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;


/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author hcl
 */
public interface PlanningDAO 
{
   
   /**
    * Gets the Collection of Planning List information. This method also takes care 
    * of filtering and pagination display by passing page no, filter criteria and its 
    * value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPlanningsList(POHeader objpoHeader,String startDate,String endDate,int userId,String page,int pagination,String orderby,String sort,String sbBDate,String ebBDate,String roleType) throws AppException;
   
   /**
    * Gets the Planning Detail information
    * 
    * @return com.pearson.pix.dto.Planning
    */
   public POHeader getPlanningDetail(BigDecimal poId, BigDecimal poVersion,String orderType) throws AppException;
   
   /**
    * Inserts the Planning Acknowledgement/Comments by Vendor
    * 
    * @return java.util.HashMap
    */
   public HashMap insertPlanningAcknowledgement();
   
   /**
    * Gets the Collection of Planning History List information. This method also 
    * takes care of filtering and pagination display by passing page no, filter 
    * criteria and its value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPlanningHistoryList();
   
   /**
    * Gets the Collection of Planning Status information.
    * 
    * @return java.util.Collection
    */
   public Collection getPlanningStatus()throws AppException;
 
   /**method prototype declaration to save the vendor comments of detail page
    *@ java.util.Collection
*/
   public String saveVendorDetail(POHeader poHeader) throws AppException;
   
   /**
    * Inserts multiple Planning purchase Orders into database for Acceptance
    * 
    * @param idVersionString
    * @param login
    * @return String 
    */
   public String insertMultiplePlanningPOAccept(String idVersionString,String login) throws AppException;
      
}
