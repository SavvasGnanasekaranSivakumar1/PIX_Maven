package com.pearson.pix.presentation.dropship.dropshipinstruction.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.dropship.dropshipinstruction.Command.DropInstCommand;

public class DropInstAction extends BaseAction{
	
		   
		   /**
		    * Constructor for Initializing DropInstAction
		    */
		   public DropInstAction() 
		   {	
			   super();
			   super.setCommandAction(new DropInstCommand());
		   }
		   
		   
		}

