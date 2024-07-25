package com.pearson.pix.presentation.arptitlesetup.action;
import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.arptitlesetup.command.PotentialARPCommand;

/**
 * Action class for the purpose of setting the appropriate Command
 * @author arvind.yadav	
 */
public class PotentialARPAction extends BaseAction 
{
   
   /**
    * Constructor for Initializing PlanningAction
    */
   public PotentialARPAction() 
   {
	   super.setCommandAction(new PotentialARPCommand());
   }
}
