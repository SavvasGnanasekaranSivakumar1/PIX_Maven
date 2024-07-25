package com.pearson.pix.business.usage.interfaces;

import java.util.Collection;
import java.util.HashMap;

import com.pearson.pix.dto.usage.Usage;
import com.pearson.pix.exception.AppException;

import javax.ejb.*;

/**
 * EJB Home interface for Usage
 * @author Dandu Thirupathi
 */
public interface UsageLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the Usage List. 
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayUsageList
     */
    public Collection displayUsageList(Usage objUsage,String startDate,String endDate,String poNo,int userId,String page,int pagination,String orderby,String sort) throws AppException;
    
    /**
     * Business method for displaying the Usage Detail
     * 
     * @param poNumber
     * @param usageNumber
     * @param usageId
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayUsageDetail
     */
    public HashMap displayUsageDetail(Integer poNumber, Integer poVersion,Integer usageId) throws AppException;
    
    /**
     * Business method for saving the information for Usage.
     * 
     * @param objUsage
     * @param objPoId
     * @return java.lang.String
     * @J2EE_METHOD  --  saveUsage
     */
    public String saveUsage(Usage objUsage,Integer objPoId,Integer objPoVersion,String objPoNo,Integer objRno,String objUser) throws AppException;
    
    /**
     * Business method for Displaying Status for Usage.
     * 
     * @return java.lang.String
     * @J2EE_METHOD  --  displayUsageStatus
     */
    public Collection displayUsageStatus() throws AppException;
    
}