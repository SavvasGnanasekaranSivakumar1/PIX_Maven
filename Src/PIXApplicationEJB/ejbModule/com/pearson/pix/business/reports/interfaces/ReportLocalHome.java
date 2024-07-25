package com.pearson.pix.business.reports.interfaces;
import javax.ejb.*;
/**
 * Local home interface for Report
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ReportLocalHome extends EJBLocalHome
{
	public static final String COMP_NAME="java:comp/env/ejb/ReportLocal";
	   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/Report!com.pearson.pix.business.reports.interfaces.ReportLocalHome";//"ReportLocal";
    /**
     * @throws CreateException
     * @J2EE_METHOD  --  create
     * Called by the client to create an EJB bean instance. It requires a matching pair in
     * the bean class, i.e. ejbCreate().
     */
    public ReportLocal create    () 
                throws CreateException;
}