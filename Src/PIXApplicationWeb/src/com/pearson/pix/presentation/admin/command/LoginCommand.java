package com.pearson.pix.presentation.admin.command;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import com.pearson.pims.presentation.commondto.SSOLoginDTO;
import com.pearson.pims.presentation.commondto.UserResponseDTO;
import com.pearson.pims.webservice.sso.GetGroupInfoInterface;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserCountDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.admin.action.LoginForm;
import com.pearson.pix.presentation.admin.delegate.AdminDelegate;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;


public class LoginCommand extends BaseCommand 
{
	/**
     * Logger.
     */
    private static Log log = LogFactory.getLog(LoginCommand.class.getName());
    
   /**
    * Constructor for Initializing LoginCommand
    */
   public LoginCommand() 
   {
    
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
	   Enumeration enval=request.getSession().getAttributeNames();
	   HttpSession session = request.getSession();
	   AdminDelegate objAdminDelegate = new AdminDelegate();
	   LoginForm loginForm = (LoginForm) form;
	   String fromPage=null;
	   String poNo=null;
	   String forwardType=null;
	   
	   while(enval.hasMoreElements()){
           Object obj=enval.nextElement();
           if((obj instanceof String)&&!"USER_COUNT".equals((String)obj)){
           session.removeAttribute((String)obj);
           }
	   }
	       
	   try
	   {
		 fromPage=request.getParameter("fromPage");
		 poNo=request.getParameter("poNo");
		 System.out.println("fromPage = "+fromPage);
		 
	     User user = new User();
	     String ssoid = request.getHeader("CT_REMOTE_USER");				
	     String firstName = request.getHeader("givenname");
	     String lastName = request.getHeader("sn");
	     String email = request.getHeader("mail");
	     if(ssoid == null){
             /*ssoid = "ASHARAH1@savvas.com";
             firstName = "ahamedhaja";	
             lastName = "shareef"; 
             email = "ASHARAH1@savvas.com";*/
             
            /* ssoid = "ahamedhaja.shareef@savvas.com";
             firstName = "ahamedhaja";	
             lastName = "shareef"; 
             email = "ahamedhaja.shareef@savvas.com";*/
	    	
	    	 /*ssoid = "gnanasekaran.sivakumar@savvas.com";//ahamedhaja.shareef@savvas.com,ASHARAH1@savvas.com , gnanasekaran.sivakumar@savvas.com, nithish.cna@savvas.com
	    	 firstName = "ahamedhaja";
	    	 lastName = "shareef";
	    	 email = "ASHARAH1@savvas.com";*/
	    	 
	    	 ssoid = "nithish.cna@savvas.com";
	    	 firstName = "Nithish";
	    	 lastName = "CNA";
	    	 email = "nithish.cna@savvas.com";
	    	 
             
             /*ssoid = "balakrishna.kommineni@savvas.com";
             firstName = "Balakrishna";	
             lastName = "Kommineni"; 
             email = "balakrishna.kommineni@savvas.com";*/
             

             /*ssoid = "ramacharishma.vema@savvas.com ";
             firstName ="Ramacharishma";//Ramacharishma	
             lastName = "Vema"; 
             email = "ramacharishma.vema@savvas.com ";*/
             
             /*ssoid = "poushali.majumder@savvas.com";
             firstName ="Poushali";//Ramacharishma	
             lastName = "Majumder"; 
             email = "poushali.majumder@savvas.com ";*/
             
             /*ssoid = "thota.saileela@savvas.com";
             firstName ="Saileela";//Ramacharishma	
             lastName = "Thota"; 
             email = "thota.saileela@savvas.com ";*/
             
             /*ssoid = "bhavani.chitra@savvas.com";
             firstName ="BHAVANI";//Ramacharishma	
             lastName = "CHITRA"; 
             email = "bhavani.chitra@savvas.com";*/
             
             /*ssoid = "ankit.kumar@savvas.com";
             firstName ="Ankit";
             lastName = "Kumar"; 
             email = "ankit.kumar@savvas.com";*/
             
             /*ssoid = "naveenkumar.diddukunta@savvas.com";
             firstName ="Naveen Kumar";	
             lastName = "Diddukunta"; 
             email = "naveenkumar.diddukunta@savvas.com";*/
             
             /*ssoid = "roshini.bhaskaran@savvas.com";
             firstName ="roshini";	
             lastName = "bhaskaran"; 
             email = "roshini.bhaskaran@savvas.com";*/
             
             /*ssoid = "Dudekula.Karishma@savvas.com";
             firstName ="Dudekula";	
             lastName = "Karishma"; 
             email = "Dudekula.Karishma@savvas.com";*/
              
                          
       

	    	 
             }
	     log.info("ssoid is ------------------"+ssoid);
	     log.info("firstName is ------------------"+firstName);
	     log.info("lastName is ------------------"+lastName);
	     log.info("email is ------------------"+email);
	     user.setSsoid(ssoid);
	     user.setFirstName(firstName);
	     user.setLastName(lastName);
	     user.setEmail(email);
	    
	     if(fromPage!= null && "PPM".equalsIgnoreCase(fromPage) && !(fromPage.equalsIgnoreCase(""))){		// this block is for PIX link on PEPMS
	    	 user.setLogin(request.getParameter("loginId"));
		     user.setPassword(request.getParameter("password"));
//		     user.setSsoid(request.getParameter("ssoid"));
		     user.setLinkFromPepms("yes");
		     
	     }else{
	    	 user.setLogin(loginForm.getLoginId());
		     user.setPassword(loginForm.getPassword()); 
	     }
	     
	     user = objAdminDelegate.authenticateUser(user);
//	     user = null;
	     if(user == null){
	    	try {
	    		
	    		   String access_denied_url = null;
	    		   InputStream inputStream = null;
	    		   inputStream = this.getClass().getClassLoader().getResourceAsStream("fileProperties.properties");
	    			Properties properties = new Properties();  
	    			properties.load(inputStream); 
	    			access_denied_url = properties.getProperty("access_denied_url");
	    			response.sendRedirect(access_denied_url);
//	    		response.sendRedirect("http://amdev.pearson.com/cleartrust/AccessDeniedFrame.jsp?appId=101");
	    		return null;
	    	} catch (Exception e) {e.printStackTrace();
			}
	     }
	     
	     loginForm.setUser(user);
	    	     session.setAttribute("login_id", loginForm.getLoginId());
	     
	    request.setAttribute("userid",user.getUserId()); 
//	    if(user.getPasswordExpiry().equals("Y"))
//	     {
//	    	 session.setAttribute("ADMIN_MODULE","USER");
//		     session.setAttribute("LOGIN","EDITPROFILE");
//		     session.setAttribute("USER_INFO",user);
//		     return FrontEndConstants.EXPIRE;
//	     }

	    if(fromPage!= null && "PPM".equalsIgnoreCase(fromPage) && !(fromPage.equalsIgnoreCase("")) && (user != null)){
	    	 try{
	    		System.out.println("***********************In the PPM2 Loop***********************");
//		    	response.sendRedirect("http://qapix.lin.pearsontc.net/pix/costaccounting/approvallist.do?PAGE_VALUE=1&poNo="+poNo+"&fromPage=PPM");
	    		String path = request.getContextPath();
	    		response.sendRedirect(path + "/costaccounting/approvallist.do?PAGE_VALUE=1&poNo="+poNo+"&fromPage=PPM");
		    	forwardType=null;
	    	 }
	    	 catch(IOException e){
	    		 
	    		 e.printStackTrace();
	    	 }
		 }
	     else{
	    	 System.out.println("***********************In the ELSE2 Loop***********************");
	    	 forwardType=FrontEndConstants.DISPLAY;
	     }
	    
	     session.setAttribute("USER_INFO",user);
	     request.setAttribute("PATH", request.getContextPath());

	     System.out.println("sso web service :"+request.getParameter("parent"));
	     if(request.getParameter("parent")==null)
	     {
	    	 session.setAttribute("isPixPrimary", "true");
	    	 //ssoPopulateWebService(request, response, ssoid);
	     }
	     /* FOR EXTERNAL USER****************/
	     
	     
	     if(request.getHeader("peStatus") != null)
	    	 //		     if(request.getParameter("peStatus") != null)
         {
	         if(request.getHeader("peStatus").contains("Vendor"))
	        	 //		    	 if(request.getParameter("peStatus").contains("Vendor"))
	         {
	       	  request.getSession().setAttribute("isChangePassword", "show");
	         }
	         else
	         {
	         request.getSession().setAttribute("isChangePassword", "no_show");
	         }
         }
	     InputStream inputStream=null ;
	     try{
	    	 
	  	   String changePasswordUrl = null;
	  	   String logout_url = null; 
	  	   inputStream = this.getClass().getClassLoader().getResourceAsStream("fileProperties.properties");
	  		Properties properties = new Properties();
	 		properties.load(inputStream);
			logout_url = properties.getProperty("logout_url");
	 		changePasswordUrl = properties.getProperty("change_password_url");
	 		session.setAttribute("changePasswordUrl", changePasswordUrl);
	 	   session.setAttribute("logout_url", logout_url);
	 		}catch(IOException e){System.out.println("catch catch");e.printStackTrace();}
	 		finally{
	 			if(inputStream!=null){
	 				 try{
	 					 inputStream.close();
	 					}catch(IOException e){System.out.println("catch t22");e.printStackTrace();
	 					 }
	 			}
	 		}
	     
	     return forwardType;
	 }
	 
	 catch(AppException e)
	   {
		 log.debug("Error occurred in login");
		 HashMap userCount=null;
		 UserCountDTO userCountDTO=null;
		 String errMsg = e.getSErrorDescription();
		
		 if ( "Invalid password, Please try again (Password is case sensitive).".equals(errMsg)){                    

             if(request.getSession(false).getAttribute("USER_COUNT")!=null){
                       userCount=(HashMap)request.getSession(false).getAttribute("USER_COUNT");
             }
             try{
            	 userCountDTO=objAdminDelegate.accountLock(userCount,loginForm.getLoginId());
            	 
             }catch(AppException ae){
            	 errMsg = "You had 5 invalid attempts to login. Your account has been locked. Please contact PCS Support Helpdesk.";
            	
            	 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
        		 return FrontEndConstants.ERROR;
             }
             if(request.getSession(false).getAttribute("USER_COUNT")!=null){                                             

              userCount=(HashMap)request.getSession().getAttribute("USER_COUNT");
              Integer count=null;
              if(userCount.get(userCountDTO.getLoginId())!=null){
                        count=(Integer)userCount.get(userCountDTO.getLoginId());
                        userCount.put(userCountDTO.getLoginId(),new Integer(count.intValue()+1));
              }else{
                       userCount.put(userCountDTO.getLoginId(),new Integer(1)); 
              }                                 

              request.getSession(false).setAttribute("USER_COUNT",userCount);
             }else{
                      userCount=new HashMap();
                      userCount.put(userCountDTO.getLoginId(),new Integer(1));
                       request.getSession(true).setAttribute("USER_COUNT",userCount);
             }
		 
		 }
		 e.printStackTrace();
		 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		 return FrontEndConstants.ERROR;
	} 
   }
   
   /**
	 * Method for forgot password handling
	 * @param actioncommand
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param messages
	 * @return String
	 */
   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
   {
	    String login = "";
	   	try
    	 {
	   		String pixChild = request.getParameter("location_id");
	   		if(pixChild!=null)
	   		{
	   			
	   			request.getSession().setAttribute("childPIX","true");
	   			return null;
	   		}
	   		
    	 if("forgotpassword".equals(actioncommand))//checks for the click on forgot password
    	  {
    	 AdminDelegate objAdminDelegate = new AdminDelegate();
		 LoginForm loginForm = (LoginForm) form;
		 String errMsg="Password information has been sent successfully on your email address";
		 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		 login=loginForm.getLoginId();
		 objAdminDelegate.sendEmail(login);
   	     request.setAttribute("PATH", request.getContextPath());
		 return "forgotpassword";
	 }
	return "";  
	   
   }
	   	catch(AppException e)
		   {
	   	     log.debug("Error occurred in forgot password"); 
	   		 String errMsg = e.getSErrorDescription();
			 request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			 return FrontEndConstants.ERROR;
		  }
	    catch(Exception e)
		   {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"LoginCommand,executeGeneral",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
   }
   /**
    * This should be overridden by subclasses.
    * The subclass's executeRelatedLists() is called before any other executeXXXX() 
    * by BaseAction to ensure required lists of related data are available in some 
    * context to the JSPs when each page is called.
    * @param command
    * @param mapping
    * @param form
    * @param request
    * @param reponse
    * @param messages
    */
   //logout 
  public String executeRelatedList(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
   {
	String isDisplay = request.getParameter("isDisplay");
	HttpSession session = request.getSession(false);
       String isPixPrimary = (String)session.getAttribute("isPixPrimary");
       if(isPixPrimary != null && isPixPrimary.equals("true"))
       {	
    	   try {
//        	   response.sendRedirect("http://dev1.sso.pearson.com/cleartrust/ct_logout_en.html");
    		   InputStream inputStream=null ;    		   
    		   inputStream = this.getClass().getClassLoader().getResourceAsStream("fileProperties.properties");
    			Properties properties = new Properties();  
    			
    			properties.load(inputStream); 
    			String logout_url = properties.getProperty("logout_url");
    		       if(session != null)
    		       { 
    		    	   session.invalidate(); 
    			   }
    			response.sendRedirect(logout_url);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	   return null;
       }//
    	   else if(isPixPrimary == null)
    	   {
    		   if(session != null)
		       { 
		    	   session.invalidate(); 
			   }
        	   return "logoutForward";
           }
    	   else
    		   return FrontEndConstants.RELATEDLIST;
}
   public void ssoPopulateWebService(HttpServletRequest request, HttpServletResponse response, String ssoid){
		  HttpSession session = request.getSession();
	   try
	   {
	   Service serviceModel = new ObjectServiceFactory().create(GetGroupInfoInterface.class);
	   SSOLoginDTO ssoLoginDTO = null;
	   String username2 = ssoid;//new String("vveerga@pcdev.com");
	   String contextroot2 = request.getContextPath();//new String("gsr");//
	   int idx = contextroot2.lastIndexOf("/");
	   String contextroot = contextroot2.substring(idx+1); 
	   InputStream inputStream=null ;
	   String sso_ws_url = null;
	   String logout_url = null;
	   String changePasswordUrl = null;
	   inputStream = this.getClass().getClassLoader().getResourceAsStream("fileProperties.properties");
		Properties properties = new Properties();  
		try{
		properties.load(inputStream); 
		sso_ws_url = properties.getProperty("sso_webservice_url");

		}catch(IOException e){System.out.println("exception catch1");e.printStackTrace();}
		finally{
			if(inputStream!=null){
				 try{
					 inputStream.close();
					}catch(IOException e){System.out.println("exception catch2");e.printStackTrace();
					 }
			}
		}
		GetGroupInfoInterface service = (GetGroupInfoInterface)new XFireProxyFactory().create(serviceModel, sso_ws_url);
//		GetGroupInfoInterface service = (GetGroupInfoInterface)new XFireProxyFactory().create(serviceModel, "http://dev1.sso.pearson.com/pims/services/getgroupinfointerface.do");
		//	   ArrayList<com.pearson.pims.presentation.commondto.SSOLoginDTO> list = service.process(username2, contextroot2);
		System.out.println("username: "+contextroot);
		UserResponseDTO userResponseDTO  = service.process(username2,contextroot);
	   boolean isDisplay = userResponseDTO.isDisplay();
	   session.setAttribute("isDisplay", true);					// true denotes that it is a parent window. Hardcoded
	   if(true)													// hardcoded. Actually not needed now.
       {
		   ArrayList<SSOLoginDTO> list = userResponseDTO.getGroupInfo();
		   for(int i = 0; i< list.size(); i++)
           {
                 ssoLoginDTO = list.get(i);
           
                 System.out.println("ssoLoginDTO = "+ssoLoginDTO.getApplicationName()+" - "+ssoLoginDTO.getApplicationURL());
           }
		   if(list.size() > 0){
			   session.setAttribute("ssopix",list);
		   }
		   
       }
	   }
	   catch(Exception e){
		   System.out.println("Exception in Web service for application dropdown");
		   e.printStackTrace();
	   }
	   }
}
