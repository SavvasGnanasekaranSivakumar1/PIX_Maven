package com.pearson.pix.business.usage.interfaces;

import javax.ejb.*;

/**
 * EJB Local interface declaring all the business methods of the Usage
 */
public interface UsageLocalHome extends EJBLocalHome
{
    
	public static final String COMP_NAME="java:comp/env/ejb/UsageLocal";
	public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/Usage!com.pearson.pix.business.usage.interfaces.UsageLocalHome";//"UsageLocal";
	
	
    /**
     * @throws CreateException
     * @J2EE_METHOD  --  create
     * Called by the client to create an EJB bean instance. It requires a matching pair in
     * the bean class, i.e. ejbCreate().
     */
    public UsageLocal create() throws CreateException;
}