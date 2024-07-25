package com.pearson.pix.presentation.dropship.dropshipinstruction.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.lowagie.text.Document;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.dropship.DropInstDAO;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.pdf.DropshipPackingSlipPdf;

public class AjaxAction extends Action {

	public final ActionForward execute(final ActionMapping mapping,
            final ActionForm form, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception 
            
    {
		User objUser = null;
		int userId=0;
		HttpSession session = request.getSession(false);
		objUser = (User)request.getSession().getAttribute("USER_INFO");
		userId = objUser.getUserId().intValue();
		
		
		DropInstDTO dropInstDTO = new DropInstDTO();
		
	

	   /*
	    *  
	    DropshipDelegate dropShipDelegate=new DropshipDelegate();
	    DropInstDTO dropShipDTO=null;
	    JSONObject json = new JSONObject();
	    String jsonString=null;*/
	    PrintWriter out =null;
	    DropInstForm objDropInstForm = (DropInstForm) session.getAttribute("dropInstForm");
	  
	    try{
	    	dropInstDTO.setUserId(objDropInstForm.getUserId());
	   out = response.getWriter();
	   if(request.getParameter("tab")==null){
			
			objDropInstForm.setUserId(userId);
			
	   String checkboxvalues = request.getParameter("checkboxvalues");
	   request.getSession().setAttribute("userid",userId);
	   request.getSession().setAttribute("isSchool",request.getParameter("isSchool"));
	   objDropInstForm.setCheckIndexStr(checkboxvalues);
	   }
	   
	   else{
		   dropInstDTO.setUserId(objDropInstForm.getUserId());
		   request.getSession().setAttribute("tab","order");
	   	   request.getSession().setAttribute("bkNumber",request.getParameter("bkNumber"));
	   	   request.getSession().setAttribute("isSchool",request.getParameter("isSchool"));
	   	   request.getSession().setAttribute("userId",userId);
	   	   
	   }
	 
	  
	    /*int userId=0;
		User objUser = null;
		if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
		{
			 objUser = (User)request.getSession().getAttribute("USER_INFO") ;
			 userId = objUser.getUserId().intValue();//to get the user id from login
		}*/
		 Document objDocument = null;
	     String reportName="PackingSlip";
	      try
	      {
	    	DropshipPackingSlipPdf objDropshipPackingSlipPdf = new DropshipPackingSlipPdf();
	        request.getSession().setAttribute("PDF_OBJECT",objDropshipPackingSlipPdf);
	   	    request.getSession().setAttribute("PDF_Name",reportName);
	   	    response.sendRedirect("../../pdfFileDownloadServlet");
	          /* objDocument = objBookSpecificationPdf.display(request,response);
	           objDocument.close();*/
	      }
	      catch(Exception e)
	      {
	      e.printStackTrace();
	   	  AppException ae = new AppException();
	   	   ae.performErrorAction(Exceptions.EXCEPTION,"AjaxAction,executePDF",e);
	   	   String errMsg = ae.getSErrorDescription();
	   	   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
	      }
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
	   return null;
  }	
}
