//Source file: E:\\PIXProject\\PIXApplicationEJB\\src\\com\\pearson\\pix\\dao\\planning\\PotentialARPDAO.java

package com.pearson.pix.dao.arptitlesetup;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;


/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author hcl
 */
public interface PotentialARPDAO 
{
   
   /**
    * Gets the Collection of PotentialARP List information. This method also takes care 
    * of filtering and pagination display by passing page no, filter criteria and its 
    * value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection getPotentialARPsList(int userId,String roleType,String page,int currentValue,String orderby,String sort,String whereClause,String status) throws AppException;
   
   /**
    * Gets the Collection of PotentialARP Status information.
    * 
    * @return java.util.Collection
    */
   public Collection getPotentialARPStatus()throws AppException;
 
   /**
    * Validating the transaction valid for transmission.
    * @param titleId
    * @param eventId
    * @param vendorPageCount
    * @param isReqProvided
    * @param comments
    * @return
    * @throws AppException
    */
    public Vector validateTransaction(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException;  
   
    /**
     * For Accepting transaction on SyBase Side.
     * @param titleId
     * @param eventId
     * @param vendorPageCount
     * @param isReqProvided
     * @param comments
     * @return
     * @throws AppException
     */
     public Vector sysBaseUpdVendorAccept(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException;  

     /**
      * For Rejecting transaction on SyBase Side.
      * @param titleId
      * @param eventId
      * @param vendorPageCount
      * @param isReqProvided
      * @param comments
      * @return
      * @throws AppException
      */
      public Vector sysBaseUpdVendorReject(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String comments,int userId)throws AppException;  
      /**
       * For Accepting transaction on PIX Side
       * @param titleId
       * @param eventId
       * @param vendorPageCount
       * @param isReqProvided
       * @param comments
       * @return
       * @throws AppException
       */
       public String pixUpdVendorAccept(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId)throws AppException;  
       /**
        * For Rejecting transaction on PIX Side
        * @param titleId
        * @param eventId
        * @param vendorPageCount
        * @param isReqProvided
        * @param comments
        * @return
        * @throws AppException
        */
        public String pixUpdVendorReject(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId)throws AppException;  
     
   
}
