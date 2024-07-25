package com.pearson.pix.business.releasetopix.interfaces;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;



public interface ReleaseToPIXLocalHome extends EJBHome
{
    
	 public static final String COMP_NAME="java:comp/env/ejb/ReleaseToPIXLocal";
	  public static final String JNDI_NAME="java:app/PIXApplicationEJB/ReleaseToPIX!com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome";
	
   /**
    * @throws javax.ejb.CreateException	
    * @J2EE_METHOD  --  create
    * Called by the client to create an EJB bean instance. It requires a matching pair in
    * the bean class, i.e. ejbCreate().
    */
   public ReleaseToPIXLocal create    () 
               throws CreateException;
}
