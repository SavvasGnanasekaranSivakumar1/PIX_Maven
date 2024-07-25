package com.pearson.pix.dao.bookspecification;
import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.dto.bookspecification.BookSpec;
/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author Sudam.Sahu
 */
public interface BookSpecDAO 
{	/**method prototype declaration for getting bookspec list
    *@ java.util.Vector
    */
	
	public Vector getBookSpecList(BookSpec objBookSpec,int userId,String page,int currentValue,String orderby,String sort,String startdate,String enddate,String specno)throws AppException;
	
	/**method prototype declaration for getting bookspec status
	    *@ java.util.Collection
	*/
	
	public Collection getBookSpecStatus()throws AppException;
	
	/**method prototype declaration for getting bookspec detail
	    *@ @return com.pearson.pix.dto.bookspecification.BookSpec
	*/
	
	public BookSpec getBookSpecDetail(Integer specid,Integer specversion) throws AppException;
	
	/**method prototype declaration for getting bookspec last acknowledgement detail
	    *@return com.pearson.pix.dto.bookspecification.BookSpec
	*/
	
	public BookSpec getBookSpecLastAckDetail(Integer specid,Integer specversion) throws AppException;
	
	/**method prototype declaration to save the vendor comments of detail page
	 *@return String
	*/
	
	public String saveVendorDetail(BookSpec objBookSpec) throws AppException;
	
	/**method prototype declaration to insert multiple bookspec from list page for acknowledgement
	 *@return String
	*/
	
	public String insertMultipleBookSpecAccept(String idVersionString,String login) throws AppException;
}
