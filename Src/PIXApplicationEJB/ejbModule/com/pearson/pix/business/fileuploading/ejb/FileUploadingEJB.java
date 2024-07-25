package com.pearson.pix.business.fileuploading.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.pearson.pix.dao.base.DAOFactory;
import com.pearson.pix.dao.deliverymessage.DeliveryMessageDAO;
import com.pearson.pix.dao.fileuploading.FileUploadingDAO;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;

public class FileUploadingEJB implements SessionBean{
	
	
	/**
	 * Set the associated session context. The container calls this method 
	 * after the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context 
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context. 
	 * 
	 * @throws EJBException Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
		throws EJBException {
	
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}
	
	  public void ejbCreate() throws javax.ejb.CreateException
	   {
	   }
       
	  
	  /**
	     * Business method for displaying the Delivery Message List
	     * 
	     * @return java.util.Collection
	     * @J2EE_METHOD  --  displayDeliveryMessageList
	     */
	    /*public Integer insertDMFileLocation(DeliveryMessageFile dmFiles) throws AppException 
	    { 
	    	
	    	FileUploadingDAO objFileUploadingDAO = null;
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	objFileUploadingDAO = objDAOFactory.getFileUploadingDAO();
	    	Integer insertFlag = objFileUploadingDAO.insertDMFileLocation(dmFiles);
	    	return insertFlag;
	    }*/
	    
	    public Vector getDMFileList(Integer poLineNo,String poNo,Integer msgId)throws AppException{
	    	
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	return objDAOFactory.getFileUploadingDAO().getDMFileList(poLineNo,poNo,msgId);
	    }
	    
	    public void deleteDMFileLocation(String pono,Integer lineNo,String fileId)throws AppException{
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	objDAOFactory.getFileUploadingDAO().deleteDMFileLocation(pono,lineNo,fileId);
	    }
	    
	    public Vector getDelMsgFileList(Integer msgId,Integer lineNo, String DMGRMode)throws AppException{
			   
			   return DAOFactory.newInstance().getFileUploadingDAO().getDelMsgFileList(msgId,lineNo, DMGRMode);
		   }
	    
	    public Integer updateDMFile(String fileUrl,Integer fileId,Integer msgId,String pono,Integer poLineNo)throws AppException 
	    { 
	    	Integer status = null;
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	status = objDAOFactory.getFileUploadingDAO().updateDMFile(fileUrl,fileId,msgId,pono,poLineNo);
	    	return status;
	    } 
	    
	    public Map renameDMFileFolder(String concatMsg,String pono)throws AppException{
	    	Map status = null;
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	status = objDAOFactory.getFileUploadingDAO().renameDMFileFolder(concatMsg,pono);
	    	return status;
	    	
	    }
	    
	    public String getFileURL(Integer fileId)throws AppException {
	    	
	    	String status = null;
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	status = objDAOFactory.getFileUploadingDAO().getFileURL(fileId);
	    	return status;
	    }
	    
	    
	    public Vector getDeliveryMsgRollInfo(String poNo,String productCode, String lineNo)throws AppException{
	    	
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	return objDAOFactory.getFileUploadingDAO().getDeliveryMsgRollInfo(poNo,productCode, lineNo);
	    }
	    
public Vector getDMHistoryMsgRollInfo(String msgId) throws AppException{
	    	
	    	DAOFactory objDAOFactory = DAOFactory.newInstance();
	    	return objDAOFactory.getFileUploadingDAO().getDMHistoryMsgRollInfo(msgId);
	    }

public Vector getCostUserMsgRollInfo(String msgId, String DMGRMode) throws AppException{
	
	DAOFactory objDAOFactory = DAOFactory.newInstance();
	return objDAOFactory.getFileUploadingDAO().getCostUserMsgRollInfo(msgId,DMGRMode);
}


}
