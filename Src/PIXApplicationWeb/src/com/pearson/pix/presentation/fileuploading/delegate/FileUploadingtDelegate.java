package com.pearson.pix.presentation.fileuploading.delegate;

import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocal;
import com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome;
import com.pearson.pix.dao.fileuploading.FileUploadingDAOImpl;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.common.ServiceLocator;

public class FileUploadingtDelegate {
	
	
	
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(FileUploadingDAOImpl.class.getName());
   
   /**
    * Constructor for initializing ExportToExcelDelegate
    */
   public FileUploadingtDelegate() 
   {
    
   }
	
	
	/**
	    * Creates the Home Object first and then return the EJB Object from it
	    * @return com.pearson.pix.business.exportToExcel.interfaces.ExportToExcelLocal
	    */
	   private FileUploadingLocal getFileUploadingLocal() throws AppException 
	   {
		   FileUploadingLocal objFileUploadingLocal = null;
		  try
		    {
			  FileUploadingLocalHome objFileUploadingLocalHome = ServiceLocator.getFileUploadingLocalHome();
			  objFileUploadingLocal = objFileUploadingLocalHome.create();
			} 
			catch (NamingException ne)
			{
				ne.printStackTrace();
			} 
			catch (CreateException ce)
			{
			    ce.printStackTrace();
			}
			
			return objFileUploadingLocal;
		}

	   
	   /**
	    * This method will call the local interface of EJB.
	    * @param objDTO object of ExportToExcelDTO
	    * @return objDTO object of ExportToExcelDTO
	    * @throws AppException in case of error.
	    */
	  /* public Integer insertDMFileLocation(DeliveryMessageFile dmFiles)throws AppException{
		   Integer insertFlag=null;
	       try{
	    	   //insertFlag=getFileUploadingLocal().insertDMFileLocation(dmFiles);
	           }
	       catch(AppException re){
	    	   log.error("Error"+re);
	       }
	       catch(Exception re){
	    	   log.error("Error"+re);
	       }
	       return insertFlag;
	   }*/
	   
	   public Vector getDMFileList(Integer poLineNo,String poNo,Integer msgId)throws AppException{
		   
		   return getFileUploadingLocal().getDMFileList(poLineNo,poNo,msgId);
	   }
	   
	   public Vector getDelMsgFileList(Integer msgId,Integer lineNo, String DMGRMode)throws AppException{
		   
		   return getFileUploadingLocal().getDelMsgFileList(msgId,lineNo, DMGRMode);
	   }
	   
	   public void deleteDMFileLocation(String pono,Integer lineNo,String fileId)throws AppException{
		   getFileUploadingLocal().deleteDMFileLocation(pono,lineNo,fileId);
	   }
	   
	   public Integer updateDMFile(String fileUrl,Integer fileId,Integer msgId,String pono,Integer poLineNo)throws AppException{
		   Integer status = null;
	       status = getFileUploadingLocal().updateDMFile(fileUrl, fileId, msgId, pono, poLineNo);
	       return status;
	   }
	   
	   public Map renameDMFileFolder(String concatMsg,String pono)throws AppException{
		   Map status = null;
	       status = getFileUploadingLocal().renameDMFileFolder(concatMsg,pono);
	       return status;
		   
	   }
	   
	   public String getFileURL(Integer fileId)throws AppException{
		   String status = null;
	       status = getFileUploadingLocal().getFileURL(fileId);
	       return status;
	   }
	   
	   public Vector getDeliveryMsgRollInfo(String poNo,String productCode, String lineNo)throws AppException{
		   
		   return getFileUploadingLocal().getDeliveryMsgRollInfo(poNo,productCode, lineNo);
	   }
	   
public Vector getDMHistoryMsgRollInfo(String msgId)throws AppException{
		   
		   return getFileUploadingLocal().getDMHistoryMsgRollInfo(msgId);
	   }

public Vector getCostUserMsgRollInfo(String msgId, String DMGRMode)throws AppException{
	   
	   return getFileUploadingLocal().getCostUserMsgRollInfo(msgId, DMGRMode);
}

}
