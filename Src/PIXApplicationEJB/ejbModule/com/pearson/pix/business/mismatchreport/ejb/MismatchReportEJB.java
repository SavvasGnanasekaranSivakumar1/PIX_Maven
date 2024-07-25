package com.pearson.pix.business.mismatchreport.ejb;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.mismatchreport.MismatchReportDAO;
import com.pearson.pix.exception.AppException;

public class MismatchReportEJB implements SessionBean {

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	 public void ejbCreate() throws javax.ejb.CreateException
	   {
	   }
	 
	 /**
	  * Business Method to get the MismatchReportDetails of PurchaseOrder.
	  * @param poNumber
	  * @return
	  * @throws AppException
	  */
	 
	 public Collection getMismatchReportDetails(String poNumber) throws AppException{
		 
		 DAOFactory objDAOFactory = DAOFactory.newInstance();
		 MismatchReportDAO mismatchReportDao = objDAOFactory.getMismatchReportDAO();
		 Collection mismatchReportDetails = mismatchReportDao.getMismatchReportDetails(poNumber);
		 return mismatchReportDetails;
		 
	 }

}
