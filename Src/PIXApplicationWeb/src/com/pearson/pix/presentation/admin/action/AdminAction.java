package com.pearson.pix.presentation.admin.action;


import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.admin.command.AdminCommand;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @author shweta.g
 */
public class AdminAction extends BaseAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public AdminAction()
	{
		super.setCommandAction(new AdminCommand());
	}

}

