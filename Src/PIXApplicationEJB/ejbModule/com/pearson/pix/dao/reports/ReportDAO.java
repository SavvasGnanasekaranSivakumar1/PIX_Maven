package com.pearson.pix.dao.reports;
import java.util.Collection;
import com.pearson.pix.exception.AppException;
/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 */
public interface ReportDAO 
{
   
   /**
    * Gets the Report Search Criteria
    * @return java.util.Collection
    */
   public Collection getSearchCriteria()throws AppException;
   
   /**
    * Gets the List of Report from database
    * @return java.util.Collection
    */
   public Collection getReportList(String isbn,String pOrderNo,String printNo,int userId,int pagination,String itemType,String orderBy,String sort,String sdate,String edate)throws AppException;
   
   
   /** Added By Sujeet Kumar -- 26 Nov 2009
    * Gets the List of Report from database
    * @return java.util.Collection
    */
   public Collection getPixReportList(String isbn,String printNo,int userId)throws AppException;
}
