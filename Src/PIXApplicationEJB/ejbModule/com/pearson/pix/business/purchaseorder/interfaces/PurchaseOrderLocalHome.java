/*
 * Generated by XDoclet - Do not edit!
 */
package com.pearson.pix.business.purchaseorder.interfaces;

/**
 * Local home interface for PurchaseOrder.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PurchaseOrderLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PurchaseOrderLocal";
   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/PurchaseOrder!com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocalHome";//"PurchaseOrderLocal";

   public com.pearson.pix.business.purchaseorder.interfaces.PurchaseOrderLocal create()
      throws javax.ejb.CreateException;

}