/*
 * Generated by XDoclet - Do not edit!
 */
package com.pearson.pix.business.admin.interfaces;

/**
 * Local home interface for Admin.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 * @author shweta.g
 */
public interface AdminLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/AdminLocal";
   //public static final String JNDI_NAME="AdminLocal";
   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/Admin!com.pearson.pix.business.admin.interfaces.AdminLocalHome";
   public com.pearson.pix.business.admin.interfaces.AdminLocal create()
      throws javax.ejb.CreateException;

}