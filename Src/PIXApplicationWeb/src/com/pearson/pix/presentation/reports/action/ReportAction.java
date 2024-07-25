package com.pearson.pix.presentation.reports.action;
import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.reports.command.ReportCommand;

/**
 * Action class for Report Module
 * @author Sudam.Sahu
 */
public class ReportAction extends BaseAction 
{
   
   /**
    * Constructor to intialize Report Action
    */
   public ReportAction() 
   {
	   super.setCommandAction(new ReportCommand());
   }
}
