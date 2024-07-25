/**
 * 
 */
package com.pearson.pix.presentation.mismatchreport.command;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.mismatchreport.MismatchDetailsDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.mismatchreport.action.MismatchReportForm;
import com.pearson.pix.presentation.mismatchreport.delegate.MismatchReportDelegate;

/**
 * @author ramakrishna.reddy
 *
 */
public class MismatchReportCommand extends BaseCommand {

	/**
	 * Logger.
	 */
	private static Log log = LogFactory.getLog(MismatchReportCommand.class.getName());

	/**
	 * Constructor for Initializing CostAccountingCommand
	 */
	public MismatchReportCommand() {

	}
	
	
	 /**
	    * Method for Accept operation
	    * 
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */
	   
	   
	   public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
	   {
		   String mapForward = FrontEndConstants.DISPLAY;		 
		   MismatchReportForm mismatchForm = (MismatchReportForm)form;
		   String buttonName = mismatchForm.getButtonName();
		   if(request.getParameter("msr")!=null){		
			   if(buttonName!=null){
			   if(buttonName.equalsIgnoreCase("export to excel")){
				   executeExportMismatchDetails(mapping, form, request, response, messages);
				   mismatchForm.setButtonName(null);
			   }
			   else executeMismatchDetails(mapping, form, request, response, messages);
			   }
			   else executeMismatchDetails(mapping, form, request, response, messages);
		   }
		   if(request.getParameter("mr")!=null){
			   executeDisplay(mapping, form, request, response, messages);
		   }
		  // System.out.println("MAP FORWARD:"+mapForward);
		   return mapForward;
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
	   public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
	   {
		   String mapForward = FrontEndConstants.DISPLAY;	
		   MismatchReportForm mismatchForm = (MismatchReportForm)form;
		   mismatchForm.setMismatchReportDetails(null);
		   mismatchForm.setPoNo(null);		  
		   return mapForward;
	   }
	   
	   private void executeExportMismatchDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages) {
		MismatchReportForm mismatchForm = (MismatchReportForm)form;
		HttpSession session = request.getSession(true);
		session.setAttribute("mismatchvec", mismatchForm.getMismatchReportDetails());
		session.setAttribute("ponum", mismatchForm.getPoNo());
		session.setAttribute("millname1", mismatchForm.getMerchantName());
		session.setAttribute("materialno", mismatchForm.getMaterialNo());
		session.setAttribute("reqqty", mismatchForm.getQuantity());
		try {
			response.sendRedirect("/pix/Mismatchreportservlet");
		} catch (IOException e) {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"MismatchReportCommand,executeExportMismatchDetails",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		}
		
	}


	/**
	    * Method for displaying of MismatchDetails
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @param messages
	    * @return String
	    */
	   public String executeMismatchDetails(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response, final ActionMessages messages) 
	   {
		   MismatchReportForm mismatchForm = (MismatchReportForm)form;
		   MismatchReportDelegate delegate = new MismatchReportDelegate();
		   try{
		   String poNumber = mismatchForm.getPoNo().trim();
		   if(poNumber!=null){
			   Vector mismatchReportDetails = (Vector)delegate.getMismatchReportDetails(poNumber);	
			   MismatchDetailsDTO dto = (MismatchDetailsDTO)mismatchReportDetails.get(0);
			   mismatchForm.setPoNo(dto.getPoNumber());
			   mismatchForm.setMerchantName(dto.getMerchantName());
			   mismatchForm.setMaterialNo(dto.getMaterialNo());
			   mismatchForm.setQuantity(dto.getRequestedQty());
			   mismatchReportDetails.remove(0);
			   mismatchForm.setMismatchReportDetails(mismatchReportDetails);
		   }
		   return FrontEndConstants.DISPLAY;
		   }
		   catch(AppException e)
		   {
			   mismatchForm.setMismatchReportDetails(null);
			   String errMsg = e.getSErrorDescription();
	           request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
		   catch(Exception e)
		   {
			   AppException ae = new AppException();
			   ae.performErrorAction(Exceptions.EXCEPTION,"MismatchReportCommand,executeMismatchDetails",e);
			   String errMsg = ae.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
		  
	   }

}
