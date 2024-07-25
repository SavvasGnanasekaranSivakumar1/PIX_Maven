package com.pearson.pix.presentation.arptitlesetup.delegate;

import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocal;
import com.pearson.pix.business.arptitlesetup.interfaces.PotentialARPLocalHome;
import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

/**
 * Business Delegate for PotentialARP. It acts as the Client for EJB invocation.
 */
public class PotentialARPDelegate 
{
   
   /**
    * Constructor for initializing PotentialARPDelegate
    */
   public PotentialARPDelegate() 
   {
    
   }
   
   /**
    * Calls corresponding Business method of PotentialARP EJB for the purpose of PotentialARP 
    * List. This method also takes care of filtering, pagination by passing page no, 
    * filter criteria and its value respectively.
    * 
    * @param String san
    * @return java.util.Collection
    */
   public Collection displayPotentialARPList(int userId,String roleType,String page,int currentValue,String orderby,String sort,String whereClause,String status) throws AppException
   {
	   Collection objCollection = null;
       objCollection = getPotentialARPLocal().displayPotentialARPList(userId,roleType,page,currentValue,orderby,sort,whereClause,status);
       return objCollection; 
	     
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * 
    * @return com.pearson.pix.business.planning.PotentialARPLocal
    */
   private PotentialARPLocal getPotentialARPLocal() throws AppException 
   {
	   PotentialARPLocal objPotentialARPLocal = null;
		try 
		{
			PotentialARPLocalHome objPotentialARPLocalHome = ServiceLocator.getPotentialARPLocalHome();
			objPotentialARPLocal = objPotentialARPLocalHome.create();
		} 
		catch(NamingException ne)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","PotentialARPDelegate,getPotentialARPLocal",ae);
			   throw ae;
		}
		catch(CreateException ce)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","PotentialARPDelegate,getPotentialARPLocal",ae);
			   throw ae;
		}
		return objPotentialARPLocal;
   }
   

   
   /**
    * Calls corresponding Business method of PotentialARP EJB for the purpose of PotentialARP 
    * History List. This method also takes care of filtering, pagination by passing 
    * page no, filter criteria and its value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection displayPotentialARPHistoryList() 
   {
       return null;
   }
   
   /**
    * Calls corresponding Business method of PotentialARP EJB for the purpose of PotentialARP 
    * Status information
    * 
    * @return Collection
    */
   public Collection displayPotentialARPStatus() throws AppException
   {
	   Collection objCollection = null;
       objCollection = getPotentialARPLocal().displayPotentialARPStatus();
       return objCollection; 
   }
  
   /**
    * Calls corresponding Business method of PotentialARP EJB for the purpose of 
    * Validating the transaction valid for transmission
    * 
    * @param idVersionString
    * @param login
    * @return String
    */
   
   public String validateTransaction(String titleId,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId,String mode) throws AppException
   {
	   return getPotentialARPLocal().validateTransaction(titleId,eventId,vendorPageCount,isReqProvided,isReviewReq,comments,userId,mode);
   }
     
}
