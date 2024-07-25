package com.pearson.pix.business.planning.interfaces;

import javax.ejb.*;

/**
 * EJB Home interface for Planning
 */
public interface PlanningLocalHome extends EJBLocalHome
{
    
	 public static final String COMP_NAME="java:comp/env/ejb/PlanningLocal";
	  public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/Planning!com.pearson.pix.business.planning.interfaces.PlanningLocalHome";//"PlanningLocal";
	
    /**
     * @throws javax.ejb.CreateException
     * @J2EE_METHOD  --  create
     * Called by the client to create an EJB bean instance. It requires a matching pair in
     * the bean class, i.e. ejbCreate().
     */
    public PlanningLocal create    () 
                throws CreateException;
}