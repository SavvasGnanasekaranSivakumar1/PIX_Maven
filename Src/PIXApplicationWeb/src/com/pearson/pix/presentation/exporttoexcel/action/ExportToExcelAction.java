//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.pearson.pix.presentation.exporttoexcel.action;


import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.exporttoexcel.command.ExportToExcelCommand;

/**
 * Action class for Export To Excel Module
 */
public class ExportToExcelAction extends BaseAction 
{
   
   /**
    * Constructor for initializing ExportToExcelAction
    */
   public ExportToExcelAction() 
   {
	  // super();
	   super.setCommandAction(new ExportToExcelCommand());
   }
}
