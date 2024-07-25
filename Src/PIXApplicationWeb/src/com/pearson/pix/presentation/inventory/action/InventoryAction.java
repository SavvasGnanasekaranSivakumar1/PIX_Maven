package com.pearson.pix.presentation.inventory.action;
import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.inventory.command.InventoryCommand;

/**
 * Action class for Inventory Module
 * @author Sudam.Sahu
 */
public class InventoryAction extends BaseAction 
{
   
   /**
    * Constructor to intialize Usage Action
    */
   public InventoryAction() 
   {
	   super.setCommandAction(new InventoryCommand());
   }
}
