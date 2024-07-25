package com.pearson.pix.business.home.interfaces;

import java.util.Collection;
import java.util.Vector;
import javax.ejb.EJBLocalObject;
import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.home.HomePageDAO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.dto.admin.*;

/**
 * Local interface for HomePage.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface HomePageLocal extends EJBLocalObject
{
	 /**
     * Business method for displaying Homepage
     * 
     * @return  java.util.Collection
     * @J2EE_METHOD  --  displayInboxdetail
     */	
	public Collection displayInboxdetail(int userId) throws AppException;	
	
	/**
     * Business method for fetching the PO details for home page search
     * 
     * @return java.lang.String
     * @J2EE_METHOD  --  getPODetails
     */
	public String getPODetails(String poNo,User user) throws AppException ;
}
