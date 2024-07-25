package com.pearson.pix.presentation.usage.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.usage.command.UsageCommand;

/**
 * Action class for Usage Module
 * @author Dandu Thirupathi
 */
public class UsageAction extends BaseAction 
{
   
   /**
    * Constructor to intialize Usage Action
    */
   public UsageAction() 
   {
	   super.setCommandAction(new UsageCommand());
   }
}
