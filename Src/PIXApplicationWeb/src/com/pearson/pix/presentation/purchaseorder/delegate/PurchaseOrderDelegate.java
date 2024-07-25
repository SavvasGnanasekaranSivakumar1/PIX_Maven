package com.pearson.pix.presentation.purchaseorder.delegate;

import com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocal;
import com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.CreateException;
import javax.naming.NamingException;

public class PurchaseOrderDelegate
{

    public PurchaseOrderDelegate()
    {
    }

    public Collection displayOrderList(POHeader objpoheader, String startDate, String endDate, String partyType, int userId, String page, int pagination, 
            String orderby, String sort, String sbBDate, String ebBDate)
        throws AppException
    {
        Collection objCollection = null;
        objCollection = getOrderLocal().displayOrderList(objpoheader, startDate, endDate, partyType, userId, page, pagination, orderby, sort, sbBDate, ebBDate);
        return objCollection;
    }

    public HashMap displayOrderDetail(BigDecimal poId, BigDecimal poVersion, String party, Integer userId, String paperFo)
        throws AppException
    {
        return getOrderLocal().displayOrderDetail(poId, poVersion, party, userId, paperFo);
    }

    public String saveOrderConfirmation(POHeader poHeader, String orderType)
        throws AppException
    {
        return getOrderLocal().saveOrderConfirmation(poHeader, orderType);
    }

    private PurchaseOrderLocal getOrderLocal()
        throws AppException
    {
        PurchaseOrderLocal poLocal = null;
        try
        {
            PurchaseOrderLocalHome poLocalHome = ServiceLocator.getPurchaseOrderLocalHome();
            poLocal = poLocalHome.create();
        }
        catch(NamingException ne)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9003", "PurchaseOrderDelegate,getOrderLocal", ae);
            throw ae;
        }
        catch(CreateException ce)
        {
            AppException ae = new AppException();
            ae.performErrorAction("9004", "PurchaseOrderDelegate,getOrderLocal", ae);
            throw ae;
        }
        return poLocal;
    }

    public Collection displayPurchaseOrderStatus()
        throws AppException
    {
        Collection objCollection = null;
        objCollection = getOrderLocal().displayPurchaseOrderStatus();
        return objCollection;
    }

    public Collection displayOrderLineItems(BigDecimal poId, BigDecimal poVersion)
        throws AppException
    {
        return getOrderLocal().displayOrderLineItems(poId, poVersion);
    }

    public String insertMultiplePOAccept(String idVersionString, String login)
        throws AppException
    {
        return getOrderLocal().insertMultiplePOAccept(idVersionString, login);
    }
}
