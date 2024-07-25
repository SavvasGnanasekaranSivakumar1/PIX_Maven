package com.pearson.pix.presentation.dropship.dropshipinstruction.Command;

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
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.business.dropship.DropShipConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.dropship.delegate.DropshipDelegate;
import com.pearson.pix.presentation.dropship.dropshipinstruction.action.DropInstForm;

/**
 * 
 * @author vishal sinha
 */
public class DropInstCommand extends BaseCommand {

	DropshipDelegate delegate = new DropshipDelegate();
	private static Log log = LogFactory.getLog(DropInstCommand.class.getName());

	public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
	{
		HttpSession session = request.getSession(false);
		DropInstForm dropInstForm = (DropInstForm)form;
		DropInstDTO objDropInstDTO =new DropInstDTO();
		int currentValue ;//setting variable for pagination
		String pageValue = request.getParameter(PIXConstants.PAGE_VALUE);
		//String page = "";
		if(pageValue == null)
			currentValue =1;
		else
			currentValue = Integer.parseInt(pageValue);
		log.info("currentValue>>>>"+currentValue);
		try{
			String type="";
			String searchvalue="";
			String homeFlag = null;
			Boolean filter=false;
			if(request.getParameter("filter")!=null){
				String str=request.getParameter("filter");
				filter=Boolean.parseBoolean(str);
			}
			if(dropInstForm.getStatus()==null)
			{
				objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_GET_STATUS);
				dropInstForm.setStatus(objDropInstDTO.getObjDropInstDTOList());
			}
			if(filter.equals(true))
			{
				objDropInstDTO.setBkNumber(dropInstForm.getBkNumber());
				objDropInstDTO.setDocCtrlNo(dropInstForm.getDocCtrlNo());
				objDropInstDTO.setIsbnNo(dropInstForm.getIsbnNo());
				objDropInstDTO.setCustAccountName(dropInstForm.getCustAccountName());
				objDropInstDTO.setPoNumber(dropInstForm.getPoNumber());
				objDropInstDTO.setCarrierLevel(dropInstForm.getCarrierLevel());
			    objDropInstDTO.setPrintNo(dropInstForm.getPrintNo());
			    objDropInstDTO.setStatusId(dropInstForm.getStatusId());
				//objDropInstDTO.setStatus(dropInstForm.getStatus());
				//objDropInstDTO.setTotalQty((BigDecimal)dropInstForm.getTotalQty());
				objDropInstDTO.setStartDate(dropInstForm.getStartDate());
				objDropInstDTO.setEndDate(dropInstForm.getEndDate());
				request.setAttribute("filter1","true");
			}

			if(request.getParameter("type")!=null)
			{
				type=request.getParameter("type");
				log.info("type>>>>>"+type);
				searchvalue=dropInstForm.getQuickfindTxtbox();
				log.info("searchvalue>>>>>"+searchvalue);
				if(type.equalsIgnoreCase("isbn")||type=="isbn")
					objDropInstDTO.setIsbnNo(searchvalue);
				if(type.equalsIgnoreCase("bk")||type=="bk")
					objDropInstDTO.setBkNumber(searchvalue);
				if(type.equalsIgnoreCase("po")||type=="po")
					objDropInstDTO.setPoNumber(searchvalue);

				request.setAttribute("type",type);
				request.setAttribute("search",searchvalue);
			}
			objDropInstDTO.setPagination(currentValue);
			int userId=0;
			User objUser = null;
			if(request.getSession().getAttribute("USER_INFO")!=null)// to check whether the user info field is null or not
			{
				objUser = (User)request.getSession().getAttribute("USER_INFO") ;
				userId = objUser.getUserId().intValue();//to get the user id from login
				objDropInstDTO.setUserId(userId);
				System.out.println("userid>>>"+objDropInstDTO.getUserId());
			}
			if(request.getParameter("type")==null && currentValue==1 && request.getParameter("filter")== null && request.getParameter("pageFilter")==null){
				dropInstForm.setBkNumber(null);
				dropInstForm.setDocCtrlNo(null);
				dropInstForm.setIsbnNo(null);
				dropInstForm.setCustAccountName(null);
				dropInstForm.setPoNumber(null);
				dropInstForm.setCarrierLevel(null);
				dropInstForm.setStatusId(null);
				dropInstForm.setStartDate(null);
				dropInstForm.setPrintNo(null);
				//dropInstForm.setStatus(null);
				dropInstForm.setEndDate(null);
				dropInstForm.setQuickfindSelectbox(null);
				dropInstForm.setQuickfindTxtbox(null);

			}
			String status = request.getParameter("statusw");
			if(null != status && !status.equals("")){
				objDropInstDTO.setStatusId((Integer.parseInt(status)));
			}
			/*else if(status==null){
				objDropInstDTO.setStatusDescription(status);
			}*/
			
			
			String page = request.getParameter("page");
			objDropInstDTO.setPage(page);
			//request.setAttribute("I_TYPE", page);
			/*if(request.getParameter("pageFilter")!=null)
			   {
				   page=request.getParameter("pageFilter");
			   }
			   else
			   {
				   page=request.getParameter("page"); //catching the page parameter
			   }
			   request.setAttribute("pageFilter", page);*/
			   
			log.info("before execute>"+objDropInstDTO);
			objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_GET);	
			System.out.println("objDropInstDTO size in drop inst command before pagination>>>"+objDropInstDTO.getObjDropInstDTOList().size());
			int size = objDropInstDTO.getObjDropInstDTOList().size();
			PIXUtil.getNextPage(request,currentValue,size);
			PIXUtil.getPrevPage(request,currentValue);

			System.out.println(PIXUtil.getNextPage(request,currentValue,size));
			System.out.println(PIXUtil.getPrevPage(request,currentValue));
			//if(size > 2)
			if(size > PIXConstants.PAGE_SIZE)
			{           
				objDropInstDTO.getObjDropInstDTOList().remove(((Vector)objDropInstDTO.getObjDropInstDTOList()).get(size-1));
			} 

			log.info("objDropInstDTO size in drop inst command after pagination>>>"+objDropInstDTO.getObjDropInstDTOList().size());
			dropInstForm.setDropshipdtolist(objDropInstDTO.getObjDropInstDTOList());
			dropInstForm.setDropshipdto(objDropInstDTO);
			
			if(status != null && page != null){
				request.setAttribute("statusw", status);
				request.setAttribute("page", page);
			}
			

		}
		catch(AppException e){
			e.printStackTrace();

		}

		return FrontEndConstants.FORWARD_FINISHED;
	}
	
	/*public String executepdf(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
		   {
		      Document objDocument = null;
		      String reportName="BookSpecification";
		      try
		      {
		    	  DropshipPackingSlipPdf objDropshipPackingSlipPdf = new DropshipPackingSlipPdf();
		           request.getSession().setAttribute("PDF_OBJECT",objDropshipPackingSlipPdf);
		   	    request.getSession().setAttribute("PDF_Name",reportName);
		   	    response.sendRedirect("../pdfFileDownloadServlet");
		           objDocument = objBookSpecificationPdf.display(request,response);
		           objDocument.close();
		      }
		      catch(Exception e)
		      {
		    	  e.printStackTrace();
		   	  AppException ae = new AppException();
		   	   ae.performErrorAction(Exceptions.EXCEPTION,"ProcurementPlanCommand,executePDF",e);
		   	   String errMsg = ae.getSErrorDescription();
		   	   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		      }
		   	  return FrontEndConstants.EXPORTPDF;
		    } */

}











