//Source file: E:\\PIXProject\\PIXApplicationWeb\\src\\com\\pearson\\pix\\presentation\\base\\command\\BaseCommand.java

//Source file: E:\\PIXPROJECT\\PIXAPPLICATIONWEB\\src\\com\\pearson\\pix\\presentation\\base\\command\\Command.java

package com.pearson.pix.presentation.base.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.pearson.pix.presentation.base.common.FrontEndConstants;


/**
 * Contains the definitions of methods for different commands raised by the user.
 */
public class BaseCommand implements Command 
{
	
	private Action action;
   
   /**
    * Method for display of List screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
     
   
   /**
    * Method for display of Detail screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   /**
    * Method for display of status detail in filter screens
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   
      
   /**
    * Method for Insert operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return String
    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)  
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   
   /**
    * Method for Update operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   
   /**
    * Method for Delete operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDelete(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   
   /**
    * Method for General purpose if any
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   
   /**
    * Method for Cancel operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeCancelled(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
    return FrontEndConstants.FORWARD_CANCELLED;
   }
   
   /**
    * Method for Forward Finished operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeFinished(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
    return FrontEndConstants.FORWARD_FINISHED;
   }
   /**
    * Method for Forward Finished operation
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
    return FrontEndConstants.EXPORTPDF;
   }
   
   
   /**
    * This should be overridden by subclasses.
    * The subclass's executeRelatedLists() is called before any other executeXXXX() 
    * by BaseAction to ensure required lists of related data are available in some 
    * context to the JSPs when each page is called.
    * 
    * @param mapping
    * @param form
    * @param request
    * @param reponse
    * @param messages
    * @return String
    */
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
   {
	   return FrontEndConstants.RELATEDLIST;
   }
   
   
   
   public String executeFileUploading(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response)
   {
	   return FrontEndConstants.FILEUPLOAD;
   }
   
   /**
    * Gets the Action for the command
    * 
    * @return org.apache.struts.action.Action
    */
   public Action getAction() 
   {
    	return action;
   }
   
   /**
    * Sets the Action for the command
    * @param action
    */
   public void setAction(Action action) 
   {
    	this.action = action;
   }
   
   
   public boolean isRepeated(final HttpServletRequest request)
   {
       //This flag is used to avoid recurring calling
       final String this_request_time = request.getParameter("request_time");
       final String last_request_time = (String)request.getSession().getAttribute("last_request_time");
       if (this_request_time==null)
       {//page does not need validation
           return false;
       }
       else
       {
               //   Time of the last request should be stored in session to avoid recurring calls
           request.getSession().setAttribute("last_request_time",this_request_time);
           if (last_request_time==null)
           {//page does not need validation
               return false;
           }
           else
           {//If page needs validation then check that time of the last request is not the same as time of this request
               return last_request_time.equalsIgnoreCase(this_request_time);
           }
       }
   }
}
/**
 * BaseCommand.setAction(java.lang.String)
 * BaseCommand.executeRelatedList(final Command,final ActionMapping,final ActionForm,final HttpServletRequest,final HttpServletResponse,final ActionMessages)
 * BaseCommand.executeGeneral(final Command,final ActionMapping,final ActionForm,final HttpServletRequest,final HttpServletResponse,final ActionMessages)
 * Command.executeDelete(org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.setAction()
 * Command.executeGeneral(final oracle.toplink.remotecommand.Command,final org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.executeRelatedList(final oracle.toplink.remotecommand.Command,final org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.executeBlank()
 */
