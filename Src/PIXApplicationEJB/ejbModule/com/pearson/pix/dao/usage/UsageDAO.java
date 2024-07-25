package com.pearson.pix.dao.usage;

import java.util.Collection;
import java.util.HashMap;

import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.exception.AppException;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 */
public interface UsageDAO 
{
   
   /**
    * Gets the Collection of Usage List information from database
    * 
    * @return java.util.Collection
    */
   public Collection getUsageList(Usage objUsage,String startDate,String endDate,String partyType,int userId,String page,int pagination,String orderby,String sort) throws AppException;
   
   /**
    * Gets the Collection of Usage Status information from database
    * 
    * @return java.util.Collection
    */
   public Collection displayUsageStatus() throws AppException;
   
   /**
    * Gets the Usage Detail information
    * 
    * @param poNumber
    * @param usageNumber
    * @param version
    * @return com.pearson.pix.dto.usage.Usage
    */
   public HashMap getUsageDetail(Integer poNumber, Integer poVersion, Integer usageId) throws AppException;
   
   /**
    * Saves the Usage information into the database
    * 
    * @param objUsage
    */
   public String insertUsage(Usage objUsage,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String objUser)  throws AppException;
}
