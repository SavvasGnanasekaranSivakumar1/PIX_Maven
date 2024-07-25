package com.pearson.pix.dao.dropship;

import java.util.Collection;

import com.pearson.pix.exception.AppException;

/**
 * 
 * @author anil satija
 */
public interface DropShipDAO {

	public Object executeRequest(Object object, Integer integer) throws AppException;
	
	
}