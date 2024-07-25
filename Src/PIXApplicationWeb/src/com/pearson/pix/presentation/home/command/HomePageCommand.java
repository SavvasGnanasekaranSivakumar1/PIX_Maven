package com.pearson.pix.presentation.home.command;


import java.util.Vector;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.home.action.HomePageForm;
import com.pearson.pix.presentation.home.delegate.HomePageDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.*;

public class HomePageCommand extends BaseCommand
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(HomePageCommand.class.getName());
    
   /**
    * Constructor for Initializing HomePageCommand
    */
    public HomePageCommand() 
	   {
	    
	   }
	 
	/**
	    * Method for display of Home Page
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */
	 public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
	   {
		 Vector objInboxdetail=new Vector();
		 User objUser=null;
		 String roleType = null;
		 int userId=0;
		 if(request.getSession().getAttribute("USER_INFO")!=null){ //Getting the user information from session
			   objUser = (User)request.getSession().getAttribute("USER_INFO");
			   userId = objUser.getUserId().intValue();
			   roleType = objUser.getRoleTypeDetail().getRoleType();
		   }
		try
		{
			if(request.getSession().getAttribute("USER_INFO")!=null){	
				HomePageDelegate objHomePageDelegate=new HomePageDelegate();
				HomePageForm objHomePageForm=new HomePageForm();
				objInboxdetail=(Vector)objHomePageDelegate.displayInboxdetail(userId);
				if(objInboxdetail.size()==0) //Checking condition for no records in inbox
				{
					request.setAttribute("norecordsmsg","Currently there are no messages to display in your inbox.");
				}
				else if("C".equals(roleType))
				{
					request.setAttribute("norecordsmsg","There are no items to display.");
				}
				request.setAttribute("PATH", request.getContextPath()); 
				objHomePageForm.setModCollection(objInboxdetail);
				request.setAttribute("inboxdetails",objInboxdetail);
				}	
				
		return FrontEndConstants.DISPLAY;
		
	     }
		 
		 catch(AppException e)
		 {
			 log.debug("Error occurred while displaying HomePage");
			 String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		 }
		 	  
	   }

//Gaurav
	 /**
	    * Method for fetching P.O. details from backend through AJAX for home page search in case of DM & GR
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */

public String executeList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
{
	HomePageDelegate homeDelegate = null;
	String msg=null;
	User objUser = null;
 	int userId = 0;
 	
 	
 	
 	 if(request.getSession().getAttribute("USER_INFO")!=null){ //Getting the user information from session
		   objUser = (User)request.getSession().getAttribute("USER_INFO");
		   userId = objUser.getUserId().intValue();
		   //roleType = objUser.getRoleTypeDetail().getRoleType();
	   }
		try
		{   
			homeDelegate = new HomePageDelegate();
			String poNo = request.getParameter("pono");
			msg = homeDelegate.getPODetails(poNo,objUser);
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
		    StringBuffer strXML= new StringBuffer("<?xml version='1.0' ?>");
		    strXML.append("<message>"+ msg+ "</message>");
		    PrintWriter out = response.getWriter();
		    out.println(strXML);	   
		    out.flush();
		} 
	   	catch(Exception e)
	   	{
			e.printStackTrace();
	   	}
	
	return null;
}

}
