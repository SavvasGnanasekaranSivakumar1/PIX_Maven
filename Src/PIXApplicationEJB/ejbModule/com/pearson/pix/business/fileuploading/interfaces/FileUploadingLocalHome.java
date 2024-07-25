package com.pearson.pix.business.fileuploading.interfaces;

public interface FileUploadingLocalHome extends javax.ejb.EJBLocalHome{
	
	public static final String COMP_NAME="java:comp/env/ejb/FileUploadingLocal";
	   public static final String JNDI_NAME="java:global/PIXApplication/PIXApplicationEJB/FileUploading!com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocalHome";//"FileUploadingLocal";

	   public com.pearson.pix.business.fileuploading.interfaces.FileUploadingLocal create()
	      throws javax.ejb.CreateException;

}
