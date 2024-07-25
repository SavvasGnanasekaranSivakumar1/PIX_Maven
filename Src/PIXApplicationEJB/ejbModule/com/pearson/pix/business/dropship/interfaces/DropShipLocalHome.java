package com.pearson.pix.business.dropship.interfaces;

import javax.ejb.EJBLocalHome;

/**
 * 
 * @author anil satija
 */
public interface DropShipLocalHome  extends EJBLocalHome{
	 public static final String JNDI_NAME="DropShipLocal";

	   public DropShipLocal create()
	      throws javax.ejb.CreateException;
}
