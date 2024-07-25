package com.pearson.pix.business.arptitlesetup.ejb;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.arptitlesetup.PotentialARPDAO;
import com.pearson.pix.dto.arptitlesetup.PotentialARP;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import javax.ejb.*;

import oracle.toplink.sessions.Record;

/**
 * Stateless session bean containing all the business methods of the PotentialARP
 */
public class PotentialARPEJB implements SessionBean
{

	 public static final long serialVersionUID = 001011L;
	/**
     * Attributes declaration
    */
    
    /**
     * SessionContext variable
     */
    public SessionContext EJB_Context = null;
    
    /**
     * Constructor for initializing PotentialARPEJB
     * @J2EE_METHOD  --  PotentialARPEJB
     */
    public PotentialARPEJB()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbCreate
     * Called by the container to create a session bean instance. Its parameters typically
     * contain the information the client uses to customize the bean instance for its use.
     * It requires a matching pair in the bean class and its home interface.
     */
    public void ejbCreate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbRemove
     * A container invokes this method before it ends the life of the session object. This
     * happens as a result of a client's invoking a remove operation, or when a container
     * decides to terminate the session object after a timeout. This method is called with
     * no transaction context.
     */
    public void ejbRemove    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbActivate
     * The activate method is called when the instance is activated from its 'passive' state.
     * The instance should acquire any resource that it has released earlier in the ejbPassivate()
     * method. This method is called with no transaction context.
     */
    public void ejbActivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  ejbPassivate
     * The passivate method is called before the instance enters the 'passive' state. The
     * instance should release any resources that it can re-acquire later in the ejbActivate()
     * method. After the passivate method completes, the instance must be in a state that
     * allows the container to use the Java Serialization protocol to externalize and store
     * away the instance's state. This method is called with no transaction context.
     */
    public void ejbPassivate    ()  
    { 

    }
    
    /**
     * @J2EE_METHOD  --  setSessionContext
     * Set the associated session context. The container calls this method after the instance
     * creation. The enterprise Bean instance should store the reference to the context
     * object in an instance variable. This method is called with no transaction context.
     */
    public void setSessionContext    (SessionContext sc)  
    { 

    }
    
    /**
     * Business method for displaying the PotentialARP List. This method also takes care of
     *  filtering, pagination and history display by passing page no, filter criteria and
     *  its value, history flag respectively.
     * 
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPotentialARPList
     */
    public Collection displayPotentialARPList(int userId,String roleType,String page,int currentValue,String orderby,String sort,String whereClause,String status) throws AppException 
    { 
    	Collection objCollection = null;
    	
    	PotentialARPDAO objPotentialARPDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPotentialARPDAO = objDAOFactory.getPotentialARPDAO();
    	objCollection = objPotentialARPDAO.getPotentialARPsList(userId,roleType,page,currentValue,orderby,sort,whereClause,status);
    	return objCollection;
    }
    
    /**
     * Business method of PotentialARP EJB for the purpose of PotentialARP 
     * Status information
     * 
     * @return Collection
     */
    public Collection displayPotentialARPStatus() throws AppException 
    { 
        Collection objCollection = null;
    	PotentialARPDAO objPotentialARPDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPotentialARPDAO = objDAOFactory.getPotentialARPDAO();
    	objCollection = (Collection)objPotentialARPDAO.getPotentialARPStatus();
    	return objCollection;
    }
    
    /**
     *  Validating the transaction valid for transmission
     * 
     * @param idVersionString
     * @param login
     * @return String 
     */
    public String validateTransaction(String titleIsbn,String eventId,String vendorPageCount,String isReqProvided,String isReviewReq,String comments,int userId,String mode) throws AppException
    {
    	Vector objValidateVector = null;
    	Vector sybaseUpdateVector = null;
    	String isUpdated ="0";
    	int errorCode =0;
    	String errorMsg ="";
    	Status statusDtl = null;
    	PotentialARPDAO potentialARPDAO = DAOFactory.newInstance().getPotentialARPDAO();
	    if("accept".equalsIgnoreCase(mode))
	    {	   
    	   /*Condition for Validation in Oracle*/
    	   //objValidateVector = potentialARPDAO.validateTransaction(titleId,eventId,vendorPageCount,isReqProvided,comments,userId);
    	   //int lengthOfobjCollection = objValidateVector == null ? 0 : objValidateVector.size(); //Setting the size of the resulting vector
           //if (objValidateVector != null && lengthOfobjCollection> 0) //Checking the vector size for null
           // {
          	     /*Fetch the first place value status of Sybase update*/ 
        	     //statusDtl  = (Status)objValidateVector.elementAt(1);
          		 //System.err.println(statusDtl.getStatusId()+ "  ::: "+statusDtl.getStatusDescription());
          		 //if(statusDtl.getStatusId() ==0)
          		 //{
          			/*Condition for Update in SyBase*/
//          			sybaseUpdateVector = potentialARPDAO.sysBaseUpdVendorAccept(titleIsbn, eventId, vendorPageCount, isReqProvided, comments,userId);
//          			int lengthSysUpd = sybaseUpdateVector == null ? 0 : sybaseUpdateVector.size(); //Setting the size of the resulting vector
//                    if (sybaseUpdateVector != null && lengthSysUpd > 0) //Checking the vector size for null
//                    {
//                   	     /*Fetch the first place value for the status of Oracle Update*/ 
//                 	     statusDtl  = (Status)sybaseUpdateVector.elementAt(1);
//                   		 /*Condition for Update in Oracle*/
//                   		 if(statusDtl.getStatusId() == 0)
//                   		 {
		                    isUpdated = potentialARPDAO.pixUpdVendorAccept(titleIsbn , eventId, vendorPageCount, isReqProvided, isReviewReq ,comments,userId);
                            if ("0".equalsIgnoreCase(isUpdated)) //Checking the vector size for null  
                            {
                           	     /*Fetch the first place value for the status of Oracle Update*/ 
                           		 errorMsg =  titleIsbn+"-"+ "Title has been accepted.";
                           		 return errorMsg;	 
                           	}
                            else{
                           	 errorMsg =  titleIsbn+"-"+ "Unable to update the title..";
                       		 return errorMsg;
                            }
//                   		 }
//                   		 else
//                   		 {
//                   			 errorMsg =  statusDtl.getTitleISBN()+"-"+ statusDtl.getStatusDescription();
//                   			 return errorMsg;
//                   		 }	 
//                    }	 
//	                else
//	          		{
//	          		  return statusDtl.getStatusDescription();
//	          		}	 
             //}
	   }
	   else{
		   /*Condition for Validation in Oracle*/
       	   /*Condition for Update in SyBase*/
//		sybaseUpdateVector = potentialARPDAO.sysBaseUpdVendorReject(titleIsbn, eventId, vendorPageCount, isReqProvided, comments,userId);
//		int lengthSysUpd = sybaseUpdateVector == null ? 0 : sybaseUpdateVector.size(); //Setting the size of the resulting vector
//        if (sybaseUpdateVector != null && lengthSysUpd > 0) //Checking the vector size for null
//        {
//      	     /*Fetch the first place value for the status of Oracle Update*/ 
//    	     statusDtl  = (Status)sybaseUpdateVector.elementAt(1);
//      		 /*Condition for Update in Oracle*/
//      		 if(statusDtl.getStatusId() == 0)
//      		 {
                isUpdated = potentialARPDAO.pixUpdVendorReject(titleIsbn , eventId, vendorPageCount, isReqProvided, isReviewReq ,comments,userId);
               if ("0".equalsIgnoreCase(isUpdated)) //Checking the vector size for null  
               {
              	     /*Fetch the first place value for the status of Oracle Update*/ 
              		 errorMsg =  titleIsbn+"-"+"The title has been flipped with changes.";  
              		 return errorMsg;	 
              	}
               else{
              	 errorMsg =  titleIsbn+"-"+ "Unable to update the title..";
          		 return errorMsg;
               }
//      		 }
//      		 else
//      		 {
//      			 errorMsg =  statusDtl.getTitleISBN()+"-"+ statusDtl.getStatusDescription();
//      			 return errorMsg;
//      		 }	 
//         }	 
//         else
//         {	
//  		    return statusDtl.getStatusDescription();
//  	     }	 
	  }
    }
    
 
     
}