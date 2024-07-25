package com.pearson.pix.business.dropship.interfaces;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

import com.pearson.pix.exception.AppException;

/**
 * 
 * @author anil satija
 */
public interface DropShipLocal   extends EJBLocalObject{

	public Object executeRequest(Object object, Integer integer) throws AppException;
	
}

