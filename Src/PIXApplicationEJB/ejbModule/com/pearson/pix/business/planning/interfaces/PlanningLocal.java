package com.pearson.pix.business.planning.interfaces;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.*;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

/**
 * EJB Local interface declaring all the business methods of the Planning
 */
public interface PlanningLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the Planning List. This method also takes care of
     *  filtering, pagination and history display by passing page no, filter criteria and
     *  its value, history flag respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPlanningList
     */
    public Collection displayPlanningList(POHeader objpoHeader,String startDate,String endDate,int userId,String page,int currentValue,String orderby,String sort,String sbBDate,String ebBDate,String roleType) throws AppException;
    
    /**
     * Business method for displaying the Planning Detail
     * 
     * @return com.pearson.pix.dto.Planning
     * @J2EE_METHOD  --  displayPlanningDetail
     */
    public POHeader displayPlanningDetail(BigDecimal poId,BigDecimal poVersion,String orderType) throws AppException;
    
    /**
     * Business method for insertion of Vendor Acknowledgement/Comments
     * 
     * @return java.util.HashMap
     * @J2EE_METHOD  --  savePlanningAcknowledgement
     */
    public String savePlanningAcknowledgement(POHeader poHeader)throws AppException;
    
    /**
     * Business method for the purpose of Planning History List. This method also takes
     *  care of filtering, pagination by passing page no, filter criteria and its value
     *  respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPlanningHistoryList
     */
    public Collection displayPlanningHistoryList    ();
          
    /**
     * Business method for displaying the Planning Status
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPlanningStatus
     */
    public Collection displayPlanningStatus()throws AppException;
    
    /**
     * Business method for Accepting multiple Planning Purchase Orders
     * 
     * @param idVersionString
     * @param login
     * @return String 
     */
    public String insertMultiplePlanningPOAccept(String idVersionString,String login) throws AppException;
}