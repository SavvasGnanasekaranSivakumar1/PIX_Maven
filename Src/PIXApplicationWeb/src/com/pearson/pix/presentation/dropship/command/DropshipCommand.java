package com.pearson.pix.presentation.dropship.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.pearson.pix.business.dropship.DropShipConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.dropship.ShipConfDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.dropship.action.UploadShipInfoForm;
import com.pearson.pix.presentation.dropship.delegate.DropshipDelegate;
/**
 * 
 * @author anil satija
 */
public class DropshipCommand extends BaseCommand{
	private static Log log = LogFactory.getLog(DropshipCommand.class.getName());
	DropshipDelegate delegate = new DropshipDelegate();
	public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
	{
		log.info("DropshipCommand: executeDisplay() - start");
		UploadShipInfoForm uploadShipInfoForm  = (UploadShipInfoForm )form;
		try {
			String  action = request.getParameter("actionType"); 
			log.info("actionType: "+action);
			FormFile file = uploadShipInfoForm.getUploadFile();

			if("uploadShipInfo".equals(action)) {
				ShipConfDTO shipConfDTO = new ShipConfDTO();
				shipConfDTO.setFile(file);
				Boolean uploadFileflag = (Boolean)delegate.executeRequest(shipConfDTO, DropShipConstants.VALIDATE_SHIPINFO);

				if(uploadFileflag == true)
					request.setAttribute("validateTemplateFlag", "true");
				else
					request.setAttribute("validateTemplateFlag", "false");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FrontEndConstants.FORWARD_FINISHED;
	}


	public String executeGeneral(final String actioncommand, final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
	{
		log.info("DropshipCommand: executeGeneral() - start");
		UploadShipInfoForm uploadShipInfoForm  = (UploadShipInfoForm )form;
		try {
			String actionType = request.getParameter("actionType");
			log.info("actionType: "+actionType);

			//String fileName = uploadShipInfoForm.getFileName();
			//log.info("File name: "+fileName);
			User objUser = null;
			ShipConfDTO shipConfDTO = new ShipConfDTO();
			int userId=0;
			if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
			{
				objUser = (User)request.getSession().getAttribute("USER_INFO") ;
				userId = objUser.getUserId().intValue();//to get the user id from login
				shipConfDTO.setUserId(userId);
				FormFile file = uploadShipInfoForm.getUploadFile();
				shipConfDTO.setFile(file);
			}
			String sPageNum = request.getParameter("PageNo");
			log.info("Page Number: "+sPageNum);
			List<ShipConfDTO> shipConfDTOList = null;
			int pageNo = sPageNum != null ? Integer.parseInt(sPageNum.trim()) : 1;
			if("displayShipInfo".equals(actionType) ) {
				shipConfDTO.setPageNo(1);
				delegate.executeRequest(shipConfDTO, DropShipConstants.DELETE_SHIPMENT_DETAIL);
				Boolean flag = (Boolean)delegate.executeRequest(shipConfDTO, DropShipConstants.INSERT_SHIPMENT_DETAIL);
				if(flag) {
					delegate.executeRequest(shipConfDTO, DropShipConstants.VALIDATE_SHIPMENT_DETAIL);
					shipConfDTOList = (List<ShipConfDTO>)delegate.executeRequest(shipConfDTO, DropShipConstants.DISPLAY_SHIPMENT_DETAIL);
					int totalRecords = (Integer)delegate.executeRequest(shipConfDTO, DropShipConstants.TOTAL_SHIPMENT_DETAIL);
					log.info("Total records inserted: "+totalRecords);
					uploadShipInfoForm.setTotalRecords(totalRecords);
				}
			} else {
				shipConfDTO.setPageNo(pageNo);
				shipConfDTOList = (List<ShipConfDTO>)delegate.executeRequest(shipConfDTO, DropShipConstants.DISPLAY_SHIPMENT_DETAIL);
			}
//			if(shipConfDTOList.size() == 0) {
//			log.info("error page");
//			request.setAttribute("errorFlag", "true");
//			//return FrontEndConstants.ERROR;
//			}
			uploadShipInfoForm.setShipConfDTOList(shipConfDTOList);

		} catch (AppException e) {
			e.printStackTrace();
		}

		return FrontEndConstants.FORWARD_FINISHED;
	}

	public String executeDelete(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
	{
		log.info("DropshipCommand: executeDelete() - start");
		User objUser = null;
		ShipConfDTO shipConfDTO = new ShipConfDTO();
		int userId=0;
		try {
			if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
			{
				objUser = (User)request.getSession().getAttribute("USER_INFO") ;
				userId = objUser.getUserId().intValue();//to get the user id from login
				shipConfDTO.setUserId(userId);
				Integer totalRecords = (Integer)delegate.executeRequest(shipConfDTO, DropShipConstants.DELETE_SHIPMENT_DETAIL);

				log.info("Records deleted from ShipConfDTO "+totalRecords);
			}	
		} catch (AppException e) {
			e.printStackTrace();
		}

		return FrontEndConstants.FORWARD_FINISHED;
	}

	public String executeInsert(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)  
	{
		log.info("DropshipCommand: executeInsert() - start");
		User objUser = null;
		ShipConfDTO shipConfDTO = new ShipConfDTO();
		int userId=0;
		try {
			if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
			{
				objUser = (User)request.getSession().getAttribute("USER_INFO") ;
				userId = objUser.getUserId().intValue();//to get the user id from login
				shipConfDTO.setUserId(userId);
				Integer totalRecords = (Integer)delegate.executeRequest(shipConfDTO, DropShipConstants.CONFIRM_SHIPMENT_DETAIL);
				request.setAttribute("ShipDataConfirm", true);
			}	
		} catch (AppException e) {
			e.printStackTrace();
		}
		return FrontEndConstants.FORWARD_FINISHED;
	}


}