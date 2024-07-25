/**
 * 
 */
package com.pearson.pix.presentation.mismatchreport.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.mismatchreport.command.MismatchReportCommand;

/**
 * @author ramakrishna.reddy
 *
 */
public class MismatchReportAction extends BaseAction {
	
	 /**
	    * Constructor for initializing MismatchReportAction
	    */
	   public MismatchReportAction() 
	   {
		   super.setCommandAction(new MismatchReportCommand());
	   }

}
