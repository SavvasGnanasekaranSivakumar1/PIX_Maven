package com.pearson.pix.presentation.planning.action;
import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.planning.command.PlanningCommand;

/**
 * Action class for the purpose of setting the appropriate Command
 * @author faisalk
 */
public class PlanningAction extends BaseAction 
{
   
   /**
    * Constructor for Initializing PlanningAction
    */
   public PlanningAction() 
   {
	   super.setCommandAction(new PlanningCommand());
   }
}
