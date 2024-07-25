//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.pearson.pix.presentation.costaccounting.action;


import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.costaccounting.command.CostAccountingCommand;

/**
 * Action class for Cost Accounting Module
 */
public class CostAccountingAction extends BaseAction 
{
   
   /**
    * Constructor for initializing CostAccountingAction
    */
   public CostAccountingAction() 
   {
	  // super();
	   super.setCommandAction(new CostAccountingCommand());
   }
}
