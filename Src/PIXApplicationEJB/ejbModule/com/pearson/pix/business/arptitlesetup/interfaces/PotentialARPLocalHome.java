package com.pearson.pix.business.arptitlesetup.interfaces;

import javax.ejb.*;

/**
 * EJB Home interface for Potential ARP
 */
public interface PotentialARPLocalHome extends EJBLocalHome {

	public static final String COMP_NAME = "java:comp/env/ejb/PotentialARPLocal";
	public static final String JNDI_NAME = "PotentialARPLocal";

	/**
	 * @throws javax.ejb.CreateException
	 * @J2EE_METHOD -- create Called by the client to create an EJB bean
	 *              instance. It requires a matching pair in the bean class,
	 *              i.e. ejbCreate().
	 */
	public PotentialARPLocal create() throws CreateException;
}