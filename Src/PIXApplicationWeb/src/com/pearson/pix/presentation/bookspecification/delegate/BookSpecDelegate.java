package com.pearson.pix.presentation.bookspecification.delegate;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.dto.bookspecification.BookSpec;
import com.pearson.pix.business.bookspecification.interfaces.BookSpecLocal;
import com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;

/**
 * Business Delegate for Book Specification. It acts as the Client for EJB 
 * invocation.
 * @author sudam.sahu
 */
public class BookSpecDelegate 
{
   
   /**
    * Constructor to initialize BookSpecDelegate
    */
   public BookSpecDelegate() 
   {
    
   }
   
   /**
    * Calls corresponding Business method of Book Specification EJB for the purpose 
    * of List. This method also takes care of filtering, pagination by passing page 
    * no, filter criteria and its value respectively.
    * @param BookSpec,userId,page,currentValue,orderby,sort,startDate,endDate and specNo
    * @return java.util.Vector
    */
   public Vector displayBookSpecList(BookSpec objBookSpec,int userId,String page,int currentValue,String orderby,String sort,String startdate,String enddate,String specno) throws AppException
   {
	   Vector objVector = null;
       objVector= getBookSpecLocal().displayBookSpecList(objBookSpec,userId,page,currentValue,orderby,sort,startdate,enddate,specno);
       return  objVector;
   }
   
   /**
    * Calls corresponding Business method of BookSpecEJB for the purpose of 
    * Book Specification Detail
    * @param specId and specVersion
    * @return com.pearson.pix.dto.bookspecification.BookSpec
    */
   public BookSpec displayBookSpecDetail(Integer specid,Integer specversion) throws AppException
   	{
	   BookSpec objBookSpec = null;
	   objBookSpec = getBookSpecLocal().displayBookSpecDetail(specid,specversion);
	   return objBookSpec;
    }
   
   /**
    * Calls corresponding Business method of BookSpecEJB for the purpose of 
    * Book Specification last acknowledgement
    * @param specId and specVersion
    * @return com.pearson.pix.dto.bookspecification.BookSpec
    */
   public BookSpec displayBookSpecLastAckDetail(Integer specid,Integer specversion) throws AppException
   	{
	   BookSpec objBookSpec = null;
	   objBookSpec = getBookSpecLocal().displayBookSpecLastAckDetail(specid,specversion);
	   return objBookSpec;
    }
   /**
    * Calls corresponding Business method of Book Specification EJB for the purpose of 
    * Acknowledgement submit
    * @param com.pearson.pix.dto.bookspecification.BookSpec
    * @return String
    */
   public String saveBookSpecAknowledgement(BookSpec objBookSpec) throws AppException
   {
	   getBookSpecLocal().saveBookSpecAcknowledgement(objBookSpec);
	   return "";
   }
   
   /**
    * Calls corresponding Business method of Book Specification EJB for the purpose 
    * of History List.
    * @param bookSpecNumber
    * @return java.util.Collection
    */
   public Collection displayBookSpecHistList(String bookSpecNumber) 
   {
    return null;
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * @return com.pearson.pix.business.bookspecification.interfaces.BookSpecLocal
    */
   private BookSpecLocal getBookSpecLocal() 
   {
	  BookSpecLocal objBookSpecLocal = null;
	  try 
	  {
		  BookSpecLocalHome objBookSpecLocalHome = ServiceLocator.getBookSpecLocalHome();
		  objBookSpecLocal = objBookSpecLocalHome.create();
	  } 
	  catch (NamingException ne)
	  {
			ne.printStackTrace();
	  } 
	  catch (CreateException ce)
	  {
			ce.printStackTrace();
	  }
	  return objBookSpecLocal;
   }
   /**
    * Calls corresponding Business method of Book Specification EJB for the purpose 
    * of status description of the book specification
    * @return java.util.Collection
    * @throws AppException
    */
   public Collection displayBookSpecStatus() throws AppException
   {
	   Collection objCollection = null;
       objCollection = getBookSpecLocal().displayBookSpecStatus();
       return objCollection; 
   }
   
   /**
    * Calls corresponding Business method of Book Specification EJB for the purpose 
    * of multiple book spec acceptance for acknowledgement of  book specification
    * @param idVersionString and login
    * @return String
    * @throws AppException
    */
   
   public String insertMultipleBookSpecAccept(String idVersionString,String login) throws AppException
   {
	   return getBookSpecLocal().insertMultipleBookSpecAccept(idVersionString,login);
   }
}
