/*
 * Generated by XDoclet - Do not edit!
 */
package com.pearson.pix.business.orderstatus.interfaces;

/**
 * Local home interface for OrderStatus.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface OrderStatusLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/OrderStatusLocal";
   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/OrderStatus!com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocalHome";//"OrderStatusLocal";

   public com.pearson.pix.business.orderstatus.interfaces.OrderStatusLocal create()
      throws javax.ejb.CreateException;

}