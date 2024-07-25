package com.pearson.pix.presentation.base.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;



/**
 * Contains the declaration of methods for different commands raised by the user.
 */
public interface Command 
{
   /**
    * Method for display of List screens
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for display of Detail screens
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
  
   
    
   /**
    * Method for Insert operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return String
    */
   public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for Update operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for Delete operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeDelete(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for General purpose if any
    * 
    * @param actioncommand
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for Cancel operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeCancelled(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   /**
    * Method for Forward Finished operation
    * 
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @param messages
    * @return String
    */
   public String executeFinished(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
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
   public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   
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
   public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages);
   
   
   
   
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
   public String executeFileUploading(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response);
   
   
   /**
    * Gets the Action for the command
    * 
    * @return org.apache.struts.action.Action
    */
   public Action getAction();
   
   /**
    * Sets the Action for the command
    * 
    * @param action
    */
   public void setAction(Action action);
}
/**
 * Command.executeGeneral(final Command,final ActionMapping,final ActionForm,final HttpServletRequest,final HttpServletResponse,final ActionMessages)
 * Command.setAction(java.lang.String)
 * Command.executeRelatedList(final Command,final ActionMapping,final ActionForm,final HttpServletRequest,final HttpServletResponse,final ActionMessages)
 * Command.executeDelete(org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.setAction()
 * Command.executeGeneral(final oracle.toplink.remotecommand.Command,final org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.executeRelatedList(final oracle.toplink.remotecommand.Command,final org.apache.struts.action.ActionMapping,final org.apache.struts.action.ActionForm,final javax.servlet.http.HttpServletRequest,final javax.servlet.http.HttpServletResponse,final org.apache.struts.action.ActionMessages)
 * Command.executeBlank()
 */
