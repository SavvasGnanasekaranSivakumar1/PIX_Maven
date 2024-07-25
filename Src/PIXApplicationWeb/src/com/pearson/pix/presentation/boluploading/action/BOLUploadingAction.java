package com.pearson.pix.presentation.boluploading.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.boluploading.command.BOLUploadingCommand;


public class BOLUploadingAction extends BaseAction 
{
	   
	   /**
	    * Constructor for initializing ExportToExcelAction
	    */
	   public BOLUploadingAction() 
	   {
		 
		   super.setCommandAction(new BOLUploadingCommand());
	   }
}

