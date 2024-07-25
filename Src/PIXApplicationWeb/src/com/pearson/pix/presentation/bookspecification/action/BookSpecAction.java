package com.pearson.pix.presentation.bookspecification.action;
import com.pearson.pix.presentation.base.action.BaseAction;
import com.pearson.pix.presentation.bookspecification.command.BookSpecCommand;
/**
 * Action class for the purpose of setting the appropriate Command
 */
public class BookSpecAction extends BaseAction 
{
   
   /**
    * Constructor for Initializing BookSpecAction
    */
   public BookSpecAction() 
   {
	   super.setCommandAction(new BookSpecCommand());
   }
}
