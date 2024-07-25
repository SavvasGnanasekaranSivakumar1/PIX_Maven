package com.pearson.pix.presentation.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.pearson.pix.presentation.base.command.Command;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.base.common.SessionHelper;

/**
 * Base class for all Action classes. this.execute() calls executeXXXX() which is 
 * overridden by subclass.
 */
public class BaseAction extends Action 
{
   private Command cmdAction;
   /**
    * Constructor for initializing BaseAction
    */
   public BaseAction() 
   {
    
   }
   
   /**
    * Process the specified HTTP request, and create the corresponding HTTP response 
    * (or forward to another web component that will create it).
    * Return an ActionForward instance describing where and how control should be 
    * forwarded, or null if the response has already been completed. This has to 
    * exist since it is called by the Action servlet. It's the first code executed in 
    * the processing of every struts action. 
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return org.apache.struts.action.ActionForward
    */
   public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) 
   {
    	String actionCommand = null;
        String mapForward = null;
        ActionMessages messages=null;
        HttpSession session = null;
        
        if (actionCommand == null) {
            actionCommand = mapping.getParameter();
        }
        
              
        
        try {
        	session = request.getSession();
        	
        	//Session cleaning code start
            String currentURLForm = SessionHelper.getCurrentRequestedURLForm(request.getRequestURI());
            
            //System.out.println("Form Of Interest : " + currentURLForm);
            if(currentURLForm != null){
                HttpSession tempSession = SessionHelper.cleanSession(session, currentURLForm);
                if(null != tempSession){
                    session = tempSession;
                }
            }
            //Session cleaning code end

        	// Set mapForward to ERROR so that any exceptions lead to the
            // Error action forward
            mapForward = FrontEndConstants.ERROR;
           
            
            if (FrontEndConstants.LIST.equals(actionCommand)){
                mapForward = cmdAction.executeList(mapping, form, request, response,messages);
            }
            else if (FrontEndConstants.RELATEDLIST.equals(actionCommand)){
            	mapForward = cmdAction.executeRelatedList(mapping, form, request, response,messages);
		    }
            else if (FrontEndConstants.DISPLAY.equals(actionCommand)) {
        		BaseForm baseForm = (BaseForm) form;
        		baseForm.setDataChange(false);
            	mapForward = cmdAction.executeDisplay(mapping, form, request, response,messages); 
		    } 
            else if (FrontEndConstants.INSERT.equals(actionCommand)) {
                mapForward = cmdAction.executeInsert(mapping, form, request, response,messages);
            } 
            else if (FrontEndConstants.UPDATE.equals(actionCommand)) {
                mapForward = cmdAction.executeUpdate(mapping, form, request, response,messages);
            } 
            else if (FrontEndConstants.DELETE.equals(actionCommand)) {
                mapForward = cmdAction.executeDelete(mapping, form, request, response,messages);
            } 
            else if (FrontEndConstants.FORWARD_FINISHED.equals(actionCommand)) {
                mapForward = cmdAction.executeFinished(mapping, form, request, response,messages);
            } 
            else if (FrontEndConstants.FORWARD_CANCELLED.equals(actionCommand)) {
                mapForward = cmdAction.executeCancelled(mapping, form, request, response,messages);
            }
            else if (FrontEndConstants.EXPORTPDF.equals(actionCommand)) {
                mapForward = cmdAction.executepdf(mapping, form, request, response,messages);
            }
            else if (FrontEndConstants.FILEUPLOAD.equals(actionCommand)) {
                mapForward = cmdAction.executeFileUploading(mapping, form, request, response);
            }else
            {
                mapForward = cmdAction.executeGeneral(actionCommand, mapping, form, request, response,messages);
            }
        } catch (Throwable e) {
        	//e.printStackTrace();
            
        }
	return mapping.findForward(mapForward);
           
   }
   
   /**
    * @param command
    */
   public void setCommandAction(final Command command) 
   {
    	cmdAction = command;
        cmdAction.setAction(this);
   }
   
   
   
}
