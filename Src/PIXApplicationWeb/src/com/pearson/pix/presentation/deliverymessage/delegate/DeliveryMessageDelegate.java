package com.pearson.pix.presentation.deliverymessage.delegate;

import com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocal;
import com.pearson.pix.business.deliverymessage.interfaces.DeliveryMessageLocalHome;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;

public class DeliveryMessageDelegate
{

    public DeliveryMessageDelegate()
    {
    }

    private DeliveryMessageLocal getDeliveryMessageLocal()
    {
        DeliveryMessageLocal objDeliveryMessageLocal = null;
        try
        {
            DeliveryMessageLocalHome objDeliveryMessageLocalHome = ServiceLocator.getDeliveryMessageLocalHome();
            objDeliveryMessageLocal = objDeliveryMessageLocalHome.create();
        }
        catch(NamingException ne)
        {
            ne.printStackTrace();
        }
        catch(CreateException ce)
        {
            ce.printStackTrace();
        }
        return objDeliveryMessageLocal;
    }
    
	// roleTracking
	public int getRoleTrackingFlag(String login_Id) throws AppException
	{
		int roleTrackingFlag = getDeliveryMessageLocal().getRoleTrackingFlag(login_Id);
		return roleTrackingFlag;
	}


    public Collection displayDeliveryMessageList(POHeader poHeaderTemp, int pagination, String objorderby, String objsort, String party, Integer userId)
        throws AppException
    {
        Collection objCollection = null;
        objCollection = getDeliveryMessageLocal().displayDeliveryMessageList(poHeaderTemp, pagination, objorderby, objsort, party, userId);
        return objCollection;
    }

    // roletra
    
    public Vector cuOwnedQtyPopup(String msgId, String ownershipMode, String pono)
    throws AppException
{
    DeliveryMessage objDeliveryMessage = null;
    Vector cuVec = getDeliveryMessageLocal().cuOwnedQtyPopup(msgId,ownershipMode,pono);
    return cuVec;
}

    public int printerRtDmNrtHist(String msgId, String DMGRMode)
    throws AppException
{
    DeliveryMessage objDeliveryMessage = null;
//    Vector cuVec = getDeliveryMessageLocal().printerRtDmNrtHist(msgId,DMGRMode);
    return getDeliveryMessageLocal().printerRtDmNrtHist(msgId,DMGRMode);//cuVec;
}

    
    public DeliveryMessage displayDeliveryMessageDetail(BigDecimal poId, BigDecimal poVersion, Integer messageId, String orderType,String ownrshpMode)
        throws AppException
    {
        DeliveryMessage objDeliveryMessage = null;
        objDeliveryMessage = getDeliveryMessageLocal().displayDeliveryMessageDetail(poId, poVersion, messageId, orderType,ownrshpMode);
        return objDeliveryMessage;
    }

    public HashMap displayBasicDeliveryMessageInfo(BigDecimal poId, BigDecimal poVersion, String orderType)
        throws AppException
    {
        HashMap objHashMap = null;
        objHashMap = getDeliveryMessageLocal().displayBasicDeliveryMessageInfo(poId, poVersion, orderType);
        return objHashMap;
    }

    public String saveDeliveryMessage(DeliveryMessage objDeliveryMessage, POHeader poHeaderTemp, int size, int headerSize, String objUser, String roleType)
        throws AppException
    {
        return getDeliveryMessageLocal().saveDeliveryMessage(objDeliveryMessage, poHeaderTemp, size, headerSize, objUser, roleType);
    }

    public Collection inboxdeliverymessagelist(Integer days, Integer user_id, int currentValue, String objorderby, String objsort)
        throws AppException
    {
        Collection objCollection = null;
        objCollection = getDeliveryMessageLocal().inboxDeliveryMessagelist(days, user_id, currentValue, objorderby, objsort);
        return objCollection;
    }
    public Collection inboxdeliverymessagemilllist(Integer user_id, int currentValue, String objorderby, String objsort)
    throws AppException
{
    Collection objCollection = null;
    objCollection = getDeliveryMessageLocal().inboxDeliveryMessageMilllist(user_id, currentValue, objorderby, objsort);
    return objCollection;
}

    public String displayPurchaseOrderStatus(POHeader poHeaderTemp)
        throws AppException
    {
        String status = null;
        status = getDeliveryMessageLocal().displayPurchaseOrderStatus(poHeaderTemp);
        return status;
    }

    public String getPmsLineQuantity(DeliveryMessage objDeliveryMessage,String ponumber ,int size)
        throws AppException
    {
        String PmsQuantity = null;
        PmsQuantity = getDeliveryMessageLocal().getPmsLineQuantity(objDeliveryMessage,ponumber ,size);
        return PmsQuantity;
    }
    
    public String getPmsPoHeaderStatus(BigDecimal poId)
    throws AppException
{
    String PmsHeaderStatus = null;
    PmsHeaderStatus = getDeliveryMessageLocal().getPmsPoHeaderStatus(poId);
    return PmsHeaderStatus;
}
    public String saveMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp, int userId)
    throws AppException
{
    	return getDeliveryMessageLocal().insertMillDeliveryMessage(objDeliveryMessage,poHeaderTemp,userId);
}
    public Collection getDeliveryMessageHistory(POHeader poHeaderTemp,int pagination,String lineNo,String requestType,int userId)
    throws AppException
    {
    	return getDeliveryMessageLocal().getDeliveryMessageHistory(poHeaderTemp,pagination,lineNo,requestType,userId);
    }
    public String updateMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId)
    throws AppException{
    	return getDeliveryMessageLocal().updateMillDeliveryMessage(objDeliveryMessage,poHeaderTemp,userId);
    }
}
