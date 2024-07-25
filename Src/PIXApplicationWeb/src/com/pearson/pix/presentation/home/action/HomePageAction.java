package com.pearson.pix.presentation.home.action;

import com.pearson.pix.presentation.home.command.HomePageCommand;
import com.pearson.pix.presentation.base.action.BaseAction;

public class HomePageAction extends BaseAction 
{
   
   /**
    * Constructor for Initializing HomePageAction
    */
   public HomePageAction() 
   {
	   super.setCommandAction(new HomePageCommand());
	  
   }
}
