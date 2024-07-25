package com.pearson.pix.business.mismatchreport.interfaces;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

import com.pearson.pix.exception.AppException;

public interface MismatchReportLocal extends EJBLocalObject {
	
	
	public Collection getMismatchReportDetails(String poNumber) throws AppException;

}
