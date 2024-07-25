//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.pearson.pix.presentation.deliverymessage.action;


import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.deliverymessage.command.DeliveryMessageCommand;

/**
 * Action class for Delivery Message Module
 */
public class DeliveryMessageAction extends BaseAction 
{
   
   /**
    * Constructor for initializing DeliveryMessageAction
    */
   public DeliveryMessageAction() 
   {
	  // super();
	   super.setCommandAction(new DeliveryMessageCommand());
   }
}
