package com.pearson.pix.dao.mismatchreport;

import java.util.Collection;

import com.pearson.pix.exception.AppException;


/**
 * Method to get the MismatchReportDetails of Purchase Order.
 *
 */

public interface MismatchReportDAO {
	
	public Collection getMismatchReportDetails(String poNumber) throws AppException;

}
