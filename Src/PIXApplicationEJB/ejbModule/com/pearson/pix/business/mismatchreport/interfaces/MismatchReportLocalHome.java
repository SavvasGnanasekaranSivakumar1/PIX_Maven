/**
 * 
 */
package com.pearson.pix.business.mismatchreport.interfaces;

import javax.ejb.EJBLocalHome;


public interface MismatchReportLocalHome extends EJBLocalHome {
	
	public static final String COMP_NAME="java:comp/env/ejb/MismatchReportLocal";
	   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/MismatchReport!com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocalHome";//"MismatchReportLocal";

	   public com.pearson.pix.business.mismatchreport.interfaces.MismatchReportLocal create()
	      throws javax.ejb.CreateException;

}
