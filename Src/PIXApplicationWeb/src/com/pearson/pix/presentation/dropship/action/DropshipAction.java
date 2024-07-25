package com.pearson.pix.presentation.dropship.action;

import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.dropship.command.DropshipCommand;

/**
 * 
 * @author anil satija
 */
public class DropshipAction extends BaseAction {

	public DropshipAction() {
		System.out.println("DropshipAction");
		 super.setCommandAction(new DropshipCommand());	
	}
}
