package com.pearson.pix.presentation.dropship.action;

import java.util.List;

import org.apache.struts.upload.FormFile;

import com.pearson.pix.dto.dropship.ShipConfDTO;
import com.pearson.pix.presentation.base.action.BaseForm;

public class UploadShipInfoForm extends BaseForm{
	
	
	private FormFile uploadFile;
	private int userId;
	private String sessionId;
	private String fileName;
	private List<ShipConfDTO> shipConfDTOList;
	private int totalRecords; 
	
	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the shipConfDTOList
	 */
	public List<ShipConfDTO> getShipConfDTOList() {
		return shipConfDTOList;
	}

	/**
	 * @param shipConfDTOList the shipConfDTOList to set
	 */
	public void setShipConfDTOList(List<ShipConfDTO> shipConfDTOList) {
		this.shipConfDTOList = shipConfDTOList;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
