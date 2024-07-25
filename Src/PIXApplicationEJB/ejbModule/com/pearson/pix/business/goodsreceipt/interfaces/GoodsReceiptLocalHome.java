package com.pearson.pix.business.goodsreceipt.interfaces;

import javax.ejb.*;

/**
 * EJB Local interface declaring all the business methods of the Goods Receipt
 */
public interface GoodsReceiptLocalHome extends EJBLocalHome
{
    
	public static final String COMP_NAME="java:comp/env/ejb/GoodsReceiptLocal";
	public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/GoodsReceipt!com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocalHome";//"GoodsReceiptLocal";

    /**
     * @throws CreateException
     * @J2EE_METHOD  --  create
     * Called by the client to create an EJB bean instance. It requires a matching pair in
     * the bean class, i.e. ejbCreate().
     */
    public com.pearson.pix.business.goodsreceipt.interfaces.GoodsReceiptLocal create    () 
                throws CreateException;
}