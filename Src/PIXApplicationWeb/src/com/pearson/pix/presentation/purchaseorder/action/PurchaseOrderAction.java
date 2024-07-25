package com.pearson.pix.presentation.purchaseorder.action;

import com.pearson.pix.presentation.purchaseorder.command.PurchaseOrderCommand;
import com.pearson.pix.presentation.base.action.BaseAction;

/**
 * Action class for Purchase Order Module
 * @author Mohit Bajaj
 */
public class PurchaseOrderAction extends BaseAction 
{
   
	/**
	 * 	Constructor to intialize Purchase Order Action
	 */
	public PurchaseOrderAction() 
	{
		super.setCommandAction(new PurchaseOrderCommand());
	}
}
