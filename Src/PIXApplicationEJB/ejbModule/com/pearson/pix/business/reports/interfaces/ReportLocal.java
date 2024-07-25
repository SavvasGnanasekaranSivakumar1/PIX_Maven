package com.pearson.pix.business.reports.interfaces;
import java.util.Collection;
import javax.ejb.*;
import com.pearson.pix.exception.AppException;
/**
 * Local interface for Report
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ReportLocal extends EJBLocalObject
{
    
    /**
     * Business method for displaying the Search Criteria
     * @return java.util.Collection
     * @J2EE_METHOD  --  displaySearchCriteria
     */
    public Collection displaySearchCriteria()throws AppException;
    
    /**
     * Business method for displaying the Report List. This method also takes care of 
     * Search Criteria entered plus filtering, pagination by passing page no, filter 
     * criteria and its values.
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayReportList
     */
    public Collection displayReportList(String isbn,String pOrderNo,String printNo,int userId,int pagination,String itemType,String orderBy,String sort,String sdate,String edate)throws AppException;
}