package com.pearson.pix.business.arptitlesetup.interfaces;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.*;

import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;  

/**
 * EJB Local interface declaring all the business methods of the PotentialARP
 */
public interface PotentialARPLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the PotentialARP List. This method also takes care of
     *  filtering, pagination and history display by passing page no, filter criteria and
     *  its value, history flag respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPotentialARPList
     */
    public Collection displayPotentialARPList(int userId,String roleType,String page,int currentValue,String orderby,String sort,String whereClause,String status) throws AppException;
    
    /**
     * Business method for displaying the PotentialARP Detail
     * 
     * @return com.pearson.pix.dto.PotentialARP
     * @J2EE_METHOD  --  displayPotentialARPStatus
     */

    public Collection displayPotentialARPStatus()throws AppException;
    
     /**
     * Calls corresponding Business method of PotentialARP EJB for the purpose of 
     * Validating the transaction valid for transmission
     * 
     * @param idVersionString
     * @param login
     * @return String
     */
    
    public String validateTransaction(String titleId,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId,String mode) throws AppException;
   

   
    
}