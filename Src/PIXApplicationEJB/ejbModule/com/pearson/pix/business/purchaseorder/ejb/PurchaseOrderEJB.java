package com.pearson.pix.business.purchaseorder.ejb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.math.BigDecimal;
import javax.ejb.*;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.planning.PlanningDAO;
import com.pearson.pix.dao.purchaseorder.PurchaseOrderDAO;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POSuppliedComp;
import com.pearson.pix.exception.AppException;

/**
 * Stateless session bean containing all the business methods of the Purchase Order
 */
public class PurchaseOrderEJB implements SessionBean
{
    
    /**
     * Constructor for initializing PurchaseOrderEJB
     * @J2EE_METHOD  --  PurchaseOrderEJB
     * @author Mohit Bajaj
     */
    public PurchaseOrderEJB    ()  
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
     * Business method for displaying the Purchase Order List
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayOrderList
     */
    public Collection displayOrderList (POHeader objpoheader,String startDate,String endDate,String partyType,int userId,String page,int pagination,String orderby,String sort,String sbBDate,String ebBDate) throws AppException  
    { 
        Collection objCollection = null;
        PurchaseOrderDAO objPurchaseOrderDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPurchaseOrderDAO = objDAOFactory.getPurchaseOrderDAO();
    	objCollection = objPurchaseOrderDAO.getOrderList(objpoheader,startDate,endDate,partyType,userId,page,pagination,orderby,sort,sbBDate,ebBDate);
    	return objCollection;
    }
    
    /**
     * Business method for displaying the Purchase Order Detail
     * 
     * @param poId
     * @param poVersion
     * @param party
     * @param userId
     * @param paperFO
     * @return java.util.HashMap
     * @J2EE_METHOD  --  displayOrderDetail
     */
    public HashMap displayOrderDetail(BigDecimal poId, BigDecimal poVersion,String party,Integer userId,String paperFo) throws AppException 
    { 
    	PurchaseOrderDAO poDAO = DAOFactory.newInstance().getPurchaseOrderDAO();
    	return poDAO.getOrderDetail(poId,poVersion,party,userId,paperFo);
    }
    
    /**
     * Business method for saving the Order Confirmation
     * 
     * @return java.lang.String
     * @J2EE_METHOD  --  saveOrderConfirmation
     */
    public String saveOrderConfirmation(POHeader poHeader,String orderType) throws AppException  
    { 
    	String vendorStatusCode= "";
    	HashMap mapAllHeaderStatus = new HashMap();
    	HashMap mapAllLineStatus = new HashMap();
    	HashMap mapAllStatus = new HashMap();
    	PurchaseOrderDAO poDAO = DAOFactory.newInstance().getPurchaseOrderDAO();
    	Collection poAllHeaderStatus = poDAO.getOrderHeaderStatusList();	//Party Status list
    	Collection poAllLineStatus = poDAO.getOrderLineItemsStatusList();	//Line Status list
    	Collection poAllStatus = poDAO.getOrderStatusList();	//Main Status list
    	Iterator iteratorAllHeaderStatus = poAllHeaderStatus.iterator();
    	Status status = null;
    	//Creation of Hashmaps of Status List
    	while(iteratorAllHeaderStatus.hasNext())
    	{
    		status = (Status)iteratorAllHeaderStatus.next();
    		mapAllHeaderStatus.put(status.getStatusId(),status.getStatusCode());
    	}
    	Iterator iteratorAllLineStatus = poAllLineStatus.iterator();
    	while(iteratorAllLineStatus.hasNext())
    	{
    		status = (Status)iteratorAllLineStatus.next();
    		mapAllLineStatus.put(status.getStatusId(),status.getStatusCode());
    	}
    	Iterator iteratorAllStatus = poAllStatus.iterator();
    	while(iteratorAllStatus.hasNext())
    	{
    		status = (Status)iteratorAllStatus.next();
    		mapAllStatus.put(status.getStatusCode(),status.getStatusId());
    	}
    	
    	Collection partyCollection = poHeader.getPartyCollection();
    	int partySize = partyCollection == null ? 0 : partyCollection.size();
    	for(int i=0;i<partySize;i++){
		   	POParty poParty = (POParty)((Vector)partyCollection).get(i);
		   	Integer vendorStatusId = null;
		   
		   	if((PIXConstants.VENDOR_ROLE).equals(poParty.getPartyType()) || ("M").equals(poParty.getPartyType()))	//for Vendor Handling
			{
				vendorStatusId = poParty.getStatusId();
				vendorStatusCode = mapAllHeaderStatus.get(vendorStatusId).toString();
				if((PIXConstants.STATUS_PARTY_NOACTION).equals(vendorStatusCode)){
					poParty.setDeliveryDate(null);
					poParty.setComments("");
				}
				break;
			}
    	}
    	
    	return poDAO.insertOrderConfirmation(getHeaderWithAllStatus(poHeader,mapAllStatus,mapAllLineStatus,vendorStatusCode),orderType);
    }
    
    /**
     * Method for setting all status in the POHeader. Called from saveOrderConfirmation().
     * @return POHeader
     * @J2EE_METHOD  --  getHeaderWithAllStatus
     */
    private POHeader getHeaderWithAllStatus(POHeader poHeader,HashMap mapAllStatus,HashMap mapAllLineStatus,String vendorStatusCode)throws AppException
    {
	   	Collection lineItemCollection = poHeader.getLineItemCollection();
	   	
	   	boolean lineNewFlag = true;
	   	boolean lineAcceptedFlag = true;
	   	boolean lineNoActionFlag = true;
	   	boolean lineRejectedFlag = true;
	   	boolean lineCancelledFlag = true;
	   	boolean lineOrderDeliveredFlag = true;
	   	POLine poLine = null;
	   	Integer supplierStatusId = null;
	   	String supplierStatusCode = null;
	   	
	   	int lineItemsCount = lineItemCollection.size();
	   		   	
	   	for(int i=0;i<lineItemsCount;i++){
	   		System.out.println("done");
		   	poLine = (POLine)((Vector)lineItemCollection).get(i);
		   	supplierStatusId = poLine.getSupplierStatusId();
		   	supplierStatusCode = mapAllLineStatus.get(supplierStatusId).toString();
			
			if(!(PIXConstants.STATUS_LINE_NEW).equals(supplierStatusCode)){
				lineNewFlag = false;
			}
						
			if(!(PIXConstants.STATUS_LINE_ACCEPTED).equals(supplierStatusCode)){
				lineAcceptedFlag = false;
			}
			
			if(!(PIXConstants.STATUS_LINE_NOACTION).equals(supplierStatusCode)){
				lineNoActionFlag = false;
			}
			else{
				//poLine.setEstimatedQuantity(null);
				poLine.setEstimatedDeliveryDate(null);
				poLine.setSupplierComments("");
			}
			
			if(!(PIXConstants.STATUS_LINE_REJECTED).equals(supplierStatusCode)){
				lineRejectedFlag = false;
			}
			
			if(!(PIXConstants.STATUS_LINE_CANCELLED).equals(supplierStatusCode)){
				lineCancelledFlag = false;
			}
						
			if(!(PIXConstants.STATUS_LINE_ORDERDELIVERED).equals(supplierStatusCode)){
				lineOrderDeliveredFlag = false;
			}
		}
	   	
	   	if(lineItemsCount>0)
	   	{
		   	if(lineNewFlag && (PIXConstants.STATUS_PARTY_NEW).equals(vendorStatusCode)){
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORIGINAL));
		   	}
		   	else if(lineAcceptedFlag && (PIXConstants.STATUS_PARTY_ACCEPTED).equals(vendorStatusCode)){
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ACCEPTED));
		   	}
		   	else if(lineNoActionFlag && (PIXConstants.STATUS_PARTY_NOACTION).equals(vendorStatusCode)){
		   		//poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_NOACTION));
		   	}
		   	else if(lineRejectedFlag){
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_REJECTED));
		   	}
		   	else if(lineCancelledFlag){
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_CANCELLED));
		   	}
		   	else if(lineOrderDeliveredFlag){
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORDERDELIVERED));
		   	}
		   	else{
		   		poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_AMENDED));
		   	}
	   	}
	   	else
	   	{
	   		/*if(lineNewFlag && (PIXConstants.STATUS_PARTY_NEW).equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORIGINAL));
	   		}
	   		else if(lineAcceptedFlag && (PIXConstants.STATUS_PARTY_ACCEPTED).equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ACCEPTED));
	   		}
	   		else if(lineRejectedFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_REJECTED));
	   		}
	   		else if(lineCancelledFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_CANCELLED));
	   		}
	   		else if(lineOrderDeliveredFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORDERDELIVERED));
	   		}
	   		else{
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_AMENDED));
	   		}*/
	   		if(PIXConstants.STATUS_PARTY_NEW.equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORIGINAL));
	   		}
	   		else if(PIXConstants.STATUS_PARTY_ACCEPTED.equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ACCEPTED));
	   		}
	   		else if(PIXConstants.STATUS_HEADER_AMENDED.equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_AMENDED));
	   		}
	   		else if(PIXConstants.STATUS_HEADER_CANCELLED.equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_CANCELLED));
	   		}
	   		else if(PIXConstants.STATUS_HEADER_REJECTED.equals(vendorStatusCode)){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_REJECTED));
	   		}
	   		else if(PIXConstants.STATUS_PARTY_NOACTION.equals(vendorStatusCode)){
	   			//poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_PARTY_NOACTION));
	   		}
	   		
	   		
	   		/*else if(lineRejectedFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_REJECTED));
	   		}
	   		else if(lineCancelledFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_CANCELLED));
	   		}
	   		else if(lineOrderDeliveredFlag){
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_ORDERDELIVERED));
	   		}
	   		else{
	   			poHeader.getStatusDetail().setStatusId((Integer)mapAllStatus.get(PIXConstants.STATUS_HEADER_AMENDED));
	   		}*/
	   	}
    	return poHeader;
    }
    
    /**
     * Business method for displaying the Purchase Order Status
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayPurchaseOrderStatus
     */
    
    public Collection displayPurchaseOrderStatus()throws AppException
    {
    	Collection objCollection = null;
    	PurchaseOrderDAO objPurchaseOrderDAO = null;
    	DAOFactory objDAOFactory = DAOFactory.newInstance();
    	objPurchaseOrderDAO = objDAOFactory.getPurchaseOrderDAO();
    	objCollection = (Collection)objPurchaseOrderDAO.getOrderStatusList();
    	return objCollection;
    }
    
    /**
     * Business method for displaying the Purchase Order Line Items for Supplied Components
     * @param poId
     * @param poVersion
     * @return java.util.Collection
     * @J2EE_METHOD  --  displayOrderLineItems
     */
    
    public Collection displayOrderLineItems(BigDecimal poId, BigDecimal poVersion) throws AppException
    {
    	PurchaseOrderDAO poDAO = DAOFactory.newInstance().getPurchaseOrderDAO();
    	return poDAO.getOrderLineItems(poId,poVersion);
    }
    
    /**
     * Business method for Accepting multiple Purchase Orders
     * 
     * @param idVersionString
     * @param login
     * @return String 
     */
    public String insertMultiplePOAccept(String idVersionString,String login) throws AppException
    {
    	PurchaseOrderDAO poDAO = DAOFactory.newInstance().getPurchaseOrderDAO();
    	return poDAO.insertMultiplePOAccept(idVersionString,login);
    }
    
}