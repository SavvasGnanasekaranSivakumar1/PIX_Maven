package com.pearson.pix.business.fileuploading.interfaces;

import java.util.Map;
import java.util.Vector;

import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.exception.AppException;

public interface FileUploadingLocal extends javax.ejb.EJBLocalObject{

	
	
	 /**
     * Business method for insertion of File info
     * 
     * @return Integer
     * @J2EE_METHOD  --  insertDMFileLocation
     */
	//public Integer insertDMFileLocation(DeliveryMessageFile dmFiles)throws AppException;
	
	public Vector getDMFileList(Integer poLineNo,String poNo,Integer msgId)throws AppException;
	
	public void deleteDMFileLocation(String pono,Integer lineNo,String fileId)throws AppException;
	
	public Vector getDelMsgFileList(Integer msgId,Integer lineNo, String DMGRMode)throws AppException;
	
	 public Integer updateDMFile(String fileUrl,Integer fileId,Integer msgId,String pono,Integer poLineNo)throws AppException;
	 
	 public Map renameDMFileFolder(String concatMsg,String pono)throws AppException ;
	 
	 public String getFileURL(Integer fileId)throws AppException ; 
	 
	 public Vector getDeliveryMsgRollInfo(String poNo,String productCode, String lineNo)throws AppException;

	 public Vector getDMHistoryMsgRollInfo(String msgId) throws AppException;
	 
	 public Vector getCostUserMsgRollInfo(String msgId, String DMGRMode) throws AppException;
}
