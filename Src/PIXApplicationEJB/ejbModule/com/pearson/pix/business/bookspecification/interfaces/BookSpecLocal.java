package com.pearson.pix.business.bookspecification.interfaces;
import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.exception.AppException;
import javax.ejb.*;
/**
 * Local interface for BookSpec
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface BookSpecLocal extends EJBLocalObject
{
    /**
     * Business method for displaying the Book Specification List. This method also 
     * takes care of filtering, pagination display by passing page no, 
     * filter criteria and its value respectively.
     * * @return java.util.Collection
     * @J2EE_METHOD  --  displayBookSpecList
     */
    public Vector displayBookSpecList(BookSpec objBookSpec,int userId,String page,int currentValue,String orderby,String sort,String startdate,String enddate,String specno) throws AppException;
    
    /**
     * Business method for displaying the Book Specification Detail
     * 
     * @param specid,specversion
     * @return com.pearson.pix.dto.bookspecification.BookSpec
     * @J2EE_METHOD  --  displayBookSpecDetail
     */
    public BookSpec displayBookSpecDetail(Integer specid,Integer specversion)throws AppException ;
    
    /**
     * Business method for displaying the Book Specification last acknowledgement detail
     * @param bookSpecNumber
     * @return com.pearson.pix.dto.bookspecification.BookSpec
     * @J2EE_METHOD  --  displayBookSpecDetail
     */
    public BookSpec displayBookSpecLastAckDetail(Integer specid,Integer specversion)throws AppException ;
    
    /**
     * Business method for insertion of Vendor Acknowledgement/Comments
     * @param com.pearson.pix.dto.bookspecification.BookSpec
	 * @return String
     * @J2EE_METHOD  --  saveBookSpecAcknowledgement
     */
    public String saveBookSpecAcknowledgement(BookSpec objBookSpec)throws AppException ;
    
    /**
     * Displays the history for the specified Book Specification number.
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayBookSpecHistList
     */
    public Collection displayBookSpecHistList    (String bookSpecNumber);
    /**
     * Displays the status for the specified Book Specification number.
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayBookSpecStatus
     */
    public Collection displayBookSpecStatus()throws AppException;
    /**
     * Business method for insertion of multiple bookspec from list page for acknowledgement
     * @return String
     * @J2EE_METHOD  --  insertMultipleBookSpecAccept
     */
    
    public String insertMultipleBookSpecAccept(String idVersionString,String login) throws AppException;
}