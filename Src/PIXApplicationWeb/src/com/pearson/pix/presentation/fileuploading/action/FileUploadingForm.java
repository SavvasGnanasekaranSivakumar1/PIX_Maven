package com.pearson.pix.presentation.fileuploading.action;


import java.util.Vector;

import com.pearson.pix.presentation.base.action.BaseForm;

public class FileUploadingForm extends BaseForm{
	
	private Vector fileList;
	
	private String fileName;
	private String fileURL;
	
	private Vector uploadFile;
	
	
	
	public final void reset
    (org.apache.struts.action.ActionMapping mapping,
    javax.servlet.http.HttpServletRequest request) 
    {
		uploadFile = new Vector();
		
    }

	public Vector getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(Vector uploadFile) {
		this.uploadFile = uploadFile;
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

}
