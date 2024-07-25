package com.pearson.pix.presentation.planning.delegate;

import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.planning.interfaces.PlanningLocal;
import com.pearson.pix.business.planning.interfaces.PlanningLocalHome;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

/**
 * Business Delegate for Planning. It acts as the Client for EJB invocation.
 */
public class PlanningDelegate 
{
   
   /**
    * Constructor for initializing PlanningDelegate
    */
   public PlanningDelegate() 
   {
    
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of Planning 
    * List. This method also takes care of filtering, pagination by passing page no, 
    * filter criteria and its value respectively.
    * 
    * @param String san
    * @return java.util.Collection
    */
   public Collection displayPlanningList(POHeader objpoHeader,String startDate,String endDate,int userId,String page,int currentValue,String orderby,String sort,String sbBDate,String ebBDate,String roleType) throws AppException
   {
	   Collection objCollection = null;
		   objCollection = getPlanningLocal().displayPlanningList(objpoHeader,startDate,endDate,userId,page,currentValue,orderby,sort,sbBDate,ebBDate,roleType);
       return objCollection; 
	     
   }
   
   /**
    * Creates the Home Object first and then return the EJB Object from it
    * 
    * @return com.pearson.pix.business.planning.PlanningLocal
    */
   private PlanningLocal getPlanningLocal() throws AppException 
   {
	   PlanningLocal objPlanningLocal = null;
		try 
		{
			PlanningLocalHome objPlanningLocalHome = ServiceLocator.getPlanningLocalHome();
			objPlanningLocal = objPlanningLocalHome.create();
		} 
		catch(NamingException ne)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","PlanningDelegate,getPlanningLocal",ae);
			   throw ae;
		}
		catch(CreateException ce)
		{
			   AppException ae = new AppException();
			   ae.performErrorAction("","PlanningDelegate,getPlanningLocal",ae);
			   throw ae;
		}
		return objPlanningLocal;
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of Planning 
    * Detail
    * 
    * @return com.pearson.pix.dto.Planning
    */
   public POHeader displayPlanningDetail(BigDecimal poId,BigDecimal poVersion,String orderType) throws AppException 
   {
	   return getPlanningLocal().displayPlanningDetail(poId,poVersion,orderType);
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of Planning 
    * Acknowledgement/Comments insertion
    * 
    * @return HashMap
    */
   public String savePlanningAcknowledgement(POHeader poHeader) throws AppException 
   {
	   return  getPlanningLocal().savePlanningAcknowledgement(poHeader);
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of Planning 
    * History List. This method also takes care of filtering, pagination by passing 
    * page no, filter criteria and its value respectively.
    * 
    * @return java.util.Collection
    */
   public Collection displayPlanningHistoryList() 
   {
       return null;
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of Planning 
    * Status information
    * 
    * @return Collection
    */
   public Collection displayPlanningStatus() throws AppException
   {
	   Collection objCollection = null;
       objCollection = getPlanningLocal().displayPlanningStatus();
       return objCollection; 
   }
   
   /**
    * Calls corresponding Business method of Planning EJB for the purpose of 
    * Accepting multiple Planning Purchase Orders
    * 
    * @param idVersionString
    * @param login
    * @return String
    */
   
   public String insertMultiplePlanningPOAccept(String idVersionString,String login) throws AppException
   {
	   return getPlanningLocal().insertMultiplePlanningPOAccept(idVersionString,login);
   }
  
   
}
