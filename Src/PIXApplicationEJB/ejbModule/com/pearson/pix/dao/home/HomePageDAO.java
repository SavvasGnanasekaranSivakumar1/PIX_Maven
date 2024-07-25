package com.pearson.pix.dao.home;

import java.util.Collection;
import java.util.Vector;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.dto.admin.*;

/**
 * Data Access Object containing all the methods required for DB access through 
 * Toplink.
 * @author faisalk
 */
public interface HomePageDAO 
{
	
	 /**
	    * Gets the Inbox Details from the database
	    * @return java.util.Collection
	    */
	public Collection getInboxDetail(int userId) throws AppException;
	/**
	    * Gets the PO Details from the database
	    * @return java.lang.String
	    */
	public String getPODetails(String poNo,User user) throws AppException;
}