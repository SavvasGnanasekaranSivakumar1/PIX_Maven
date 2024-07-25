package com.pearson.pix.presentation.admin.action;


import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.admin.command.LoginCommand;

public class LoginAction extends BaseAction 
{
   
   /**
    * Constructor for Initializing AdminAction
    */
   public LoginAction() 
   {
	   super.setCommandAction(new LoginCommand());
	  
   }
}
