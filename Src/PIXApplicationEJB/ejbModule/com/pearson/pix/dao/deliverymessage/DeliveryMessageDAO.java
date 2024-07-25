package com.pearson.pix.dao.deliverymessage;

import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

public interface DeliveryMessageDAO
{

    public abstract Collection getDeliveryMessageList(POHeader poheader, int i, String s, String s1, String s2, Integer integer)
        throws AppException;

    public abstract DeliveryMessage getDeliveryMessageDetail(BigDecimal bigdecimal, BigDecimal bigdecimal1, Integer integer, String s,String o)
        throws AppException;

    public abstract HashMap getBasicDeliveryMessageInfo(BigDecimal bigdecimal, BigDecimal bigdecimal1, String s)
        throws AppException;

    public abstract String insertDeliveryMessage(DeliveryMessage deliverymessage, POHeader poheader, int i, int j, String s, String s1)
        throws AppException;

    public abstract Collection inboxDeliveryMessageList(Integer integer, Integer integer1, int i, String s, String s1)
        throws AppException;
    
    public abstract Collection inboxDeliveryMessageMillList(Integer integer1, int i, String s, String s1)
    throws AppException;

    public abstract String getPurchaseOrderStatus(POHeader poheader)
        throws AppException;

    public abstract String getPmsLineQuantity(DeliveryMessage deliverymessage,String ponumber, int i)
        throws AppException;
    
    public abstract String getPmsPoHeaderStatus(BigDecimal poId)
    	throws AppException;
    
    public abstract String insertMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId)
    throws AppException;
    
    public abstract Collection getDeliveryMessageHistory(POHeader poHeaderTemp,int pagination,String lineNo,String requestType,int userId)
    throws AppException;
    
    public abstract String updateMillDeliveryMessage(DeliveryMessage objDeliveryMessage,POHeader poHeaderTemp,int userId)
    throws AppException;
    
    int getRoleTrackingFlag(String login_Id);

    public abstract java.util.Vector cuOwnedQtyPopup(String msgId, String ownershipMode, String pono)
    throws AppException;

    public abstract int printerRtDmNrtHist(String msgId, String DMGRMode)
    throws AppException;

    
}
