package com.pearson.pix.presentation.goodsreceipt.action;

import com.pearson.pix.presentation.base.action.BaseAction;

import com.pearson.pix.presentation.goodsreceipt.command.GoodsReceiptCommand;

/**
 * Action class for Goods Receipt Module
 */
public class GoodsReceiptAction extends BaseAction 
{
   
   /**
    * Constructor to initialize GoodsReceiptAction
    */
   public GoodsReceiptAction() 
   {
	   // super();
	   super.setCommandAction(new GoodsReceiptCommand());
   }
}
