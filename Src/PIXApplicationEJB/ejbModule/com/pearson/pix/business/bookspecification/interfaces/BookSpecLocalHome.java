package com.pearson.pix.business.bookspecification.interfaces;
import javax.ejb.*;

/**
 * Local home interface for BookSpec
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface BookSpecLocalHome extends EJBLocalHome
{
	public static final String COMP_NAME="java:comp/env/ejb/BookSpecLocal";
	   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/BookSpec!com.pearson.pix.business.bookspecification.interfaces.BookSpecLocalHome";//"BookSpecLocal";
    /**
     * @throws CreateException
     * @J2EE_METHOD  --  create
     * Called by the client to create an EJB bean instance. It requires a matching pair in
     * the bean class, i.e. ejbCreate().
     */
    public BookSpecLocal create    () 
                throws CreateException;
}