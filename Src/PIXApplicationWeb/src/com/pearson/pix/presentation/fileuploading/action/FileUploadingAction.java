package com.pearson.pix.presentation.fileuploading.action;

import com.pearson.pix.presentation.base.action.BaseAction;

import com.pearson.pix.presentation.fileuploading.command.FileUploadingCommand;

public class FileUploadingAction extends BaseAction 
{
	   
	   /**
	    * Constructor for initializing ExportToExcelAction
	    */
	   public FileUploadingAction() 
	   {
		 
		   super.setCommandAction(new FileUploadingCommand());
	   }
}
