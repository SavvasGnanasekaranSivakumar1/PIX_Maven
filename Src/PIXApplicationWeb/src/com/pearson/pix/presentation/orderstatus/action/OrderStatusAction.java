package com.pearson.pix.presentation.orderstatus.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.orderstatus.command.OrderStatusCommand;

/**
 * Action class for the Order Status Module
 * @author Dandu Thirupathi
 */

public class OrderStatusAction extends BaseAction 
{
   
   /**
    * Constructor to initialize OrderStatusAction
    */
   public OrderStatusAction() 
   {
	   super.setCommandAction(new OrderStatusCommand());
   }
}
