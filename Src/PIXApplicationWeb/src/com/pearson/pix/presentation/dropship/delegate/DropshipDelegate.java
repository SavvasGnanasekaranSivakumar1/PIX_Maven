package com.pearson.pix.presentation.dropship.delegate;

import java.util.Collection;

import com.pearson.pix.business.dropship.interfaces.DropShipLocal;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;
import com.pearson.pix.business.dropship.interfaces.DropShipLocalHome;

import javax.ejb.CreateException;
import javax.naming.NamingException;

/**
 * 
 * @author anil satija
 */

public class DropshipDelegate {

	public Object executeRequest(Object object, Integer integer) throws AppException{
		Object  obj  = getDropShipToolsLocal().executeRequest(object ,integer);
		return obj;
	}
	
	private DropShipLocal getDropShipToolsLocal() throws AppException {
		DropShipLocal dropShipLocal = null;
		try {
			DropShipLocalHome dropShipLocalHome = ServiceLocator.getDropShipLocalHome();
			dropShipLocal = dropShipLocalHome.create();
		} catch (NamingException ne) {
			ne.printStackTrace();
			throw new AppException(ne);
		} catch (CreateException ce) {
			ce.printStackTrace();
			throw new AppException(ce);
		}
		return dropShipLocal;
	}
}
