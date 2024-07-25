package com.pearson.pix.business.home.interfaces;

import javax.ejb.CreateException;



public interface HomePageLocalHome extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/HomePageLocal";
   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/HomePage!com.pearson.pix.business.home.interfaces.HomePageLocalHome";//"HomePageLocal";

/**
 * @throws javax.ejb.CreateException
 * @J2EE_METHOD  --  create
 * Called by the client to create an EJB bean instance. It requires a matching pair in
 * the bean class, i.e. ejbCreate().
 */
public HomePageLocal create    () 
            throws CreateException;

}

