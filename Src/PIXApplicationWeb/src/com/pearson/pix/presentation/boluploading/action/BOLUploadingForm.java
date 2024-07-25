package com.pearson.pix.presentation.boluploading.action;

import java.io.File;
import java.util.Vector;

import org.apache.struts.upload.FormFile;

import com.pearson.pix.presentation.base.action.BaseForm;

public class BOLUploadingForm extends BaseForm{
  
    private Vector fileList;
	private String fileName;
	private String fileURL;
	private Vector uploadFile;
	private String billNo;
	private String freightNo;
	private String carrierNo;
	// private String dataFile;
	private FormFile dataFile;
	private Vector dmDtlList;
	private int totalRollWeight;

	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request) 
    {
		uploadFile = new Vector();
		
    }

	public Vector getFileList() {
		return fileList;
	}
	public void setFileList(Vector fileList) {
		this.fileList = fileList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public Vector getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(Vector uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getCarrierNo() {
		return carrierNo;
	}

	public void setCarrierNo(String carrierNo) {
		this.carrierNo = carrierNo;
	}

	public String getFreightNo() {
		return freightNo;
	}

	public void setFreightNo(String freightNo) {
		this.freightNo = freightNo;
	}

	public FormFile getDataFile() {
		return dataFile;
	}

	public void setDataFile(FormFile dataFile) {
		this.dataFile = dataFile;
	}

	public Vector getDmDtlList() {
		return dmDtlList;
	}

	public void setDmDtlList(Vector dmDtlList) {
		this.dmDtlList = dmDtlList;
	}

	public int getTotalRollWeight() {
		return totalRollWeight;
	}

	public void setTotalRollWeight(int totalRollWeight) {
		this.totalRollWeight = totalRollWeight;
	}
}