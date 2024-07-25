package com.pearson.pix.presentation.exporttoexcel.command;

import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.exporttoexcel.ExportToExcelDTO;
import com.pearson.pix.dto.purchaseorder.POHeader;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.costaccounting.action.CostAccountingForm;
import com.pearson.pix.presentation.deliverymessage.delegate.DeliveryMessageDelegate;
import com.pearson.pix.presentation.exporttoexcel.action.ExportToExcelForm;
import com.pearson.pix.presentation.exporttoexcel.delegate.ExportToExcelDelegate;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts.action.*;

public class ExportToExcelCommand extends BaseCommand
{

    public ExportToExcelCommand()
    {
    }

    public String executeDisplay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ActionMessages messages)
    {
        ExportToExcelForm objExportToExcel = (ExportToExcelForm)form;
        String flag = request.getParameter("flag");
        String Roll_Type = request.getParameter("Roll_Type");
        User objUser = null;
        String page = request.getParameter("page");
        if(!PIXUtil.checkNullField(page))
        {
            page = "C";
        }
       try
       {
        if(request.getSession().getAttribute("USER_INFO") != null)
        {
            objUser = (User)request.getSession().getAttribute("USER_INFO");
            objExportToExcel.setUserId(objUser.getUserId().toString());
        }
        else 
        {
        	response.sendRedirect("/pix");
        }
       }
       catch(IOException e)
       {
           String errMsg = e.getMessage();
           request.setAttribute("PIX_ERROR", errMsg);
           return "error";
       }
        if(flag.equalsIgnoreCase("plan"))
        {
            String idVersionString = "";
            String strSelectedPoId[] = (String[])null;
            String strSelectedPoVersion[] = (String[])null;
            String strSelectedCheckboxes = request.getParameter("checksArr");
            if(strSelectedCheckboxes != null)
            {
                if(strSelectedCheckboxes.indexOf("|") != -1)
                {
                    String strSelectedCheckboxesNew = strSelectedCheckboxes.replace('|', '~');
                    strSelectedPoVersion = strSelectedCheckboxesNew.split("~");
                } else
                {
                    strSelectedPoVersion = new String[1];
                    strSelectedPoVersion[0] = strSelectedCheckboxes;
                }
                int temp = 0;
                for(int i = 0; i < strSelectedPoVersion.length; i++)
                {
                    if(temp != 0)
                    {
                        idVersionString = idVersionString + ",";
                    }
                    strSelectedPoId = strSelectedPoVersion[i].split("-");
                    idVersionString = idVersionString + strSelectedPoId[0];
                    temp++;
                }

            }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDTO returnDTO = null;
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                if(Roll_Type != null && Roll_Type.equalsIgnoreCase("M"))
                {
                    returnDTO = objDelegate.getMillDetail(dto);
                } else
                {
                    returnDTO = objDelegate.getDetail(dto);
                }
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Planning Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("planAll"))
        {
            String idVersionString = "";
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = null;
                if(Roll_Type != null && Roll_Type.equalsIgnoreCase("M"))
                {
                    returnDTO = objDelegate.getMillDetail(dto);
                } else
                {
                    returnDTO = objDelegate.getDetail(dto);
                }
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Planning Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("purchase"))
        {
            String idVersionString = "";
            String strSelectedPoId[] = (String[])null;
            String strSelectedPoVersion[] = (String[])null;
            String strSelectedCheckboxes = request.getParameter("checksArr");
            if(strSelectedCheckboxes != null)
            {
                if(strSelectedCheckboxes.indexOf("|") != -1)
                {
                    String strSelectedCheckboxesNew = strSelectedCheckboxes.replace('|', '~');
                    strSelectedPoVersion = strSelectedCheckboxesNew.split("~");
                } else
                {
                    strSelectedPoVersion = new String[1];
                    strSelectedPoVersion[0] = strSelectedCheckboxes;
                }
                int temp = 0;
                for(int i = 0; i < strSelectedPoVersion.length; i++)
                {
                    if(temp != 0)
                    {
                        idVersionString = idVersionString + ",";
                    }
                    strSelectedPoId = strSelectedPoVersion[i].split("-");
                    idVersionString = idVersionString + strSelectedPoId[0];
                    temp++;
                }

            }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setOrderType("PO");
            dto.setUserId(objExportToExcel.getUserId());
            dto.setRoleType(request.getParameter("Roll_Type"));
            try
            {
                ExportToExcelDTO returnDTO = null;
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                if(Roll_Type != null && Roll_Type.equalsIgnoreCase("M"))
                {
                    returnDTO = objDelegate.getPurchaseMillDetail(dto);
                } else
                {
                    returnDTO = objDelegate.getPurchaseDetail(dto);
                }
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Purchase Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("purchaseAll"))
        {
            String idVersionString = "";
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setOrderType("PO");
            dto.setUserId(objExportToExcel.getUserId());
            dto.setRoleType(request.getParameter("Roll_Type"));
            try
            {
                ExportToExcelDTO returnDTO = null;
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                if(Roll_Type != null && Roll_Type.equalsIgnoreCase("M"))
                {
                    returnDTO = objDelegate.getPurchaseMillDetail(dto);
                } else
                {
                    returnDTO = objDelegate.getPurchaseDetail(dto);
                }
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Purchase Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("furnish"))
        {
            String idVersionString = "";
            String strSelectedPoId[] = (String[])null;
            String strSelectedPoVersion[] = (String[])null;
            String strSelectedCheckboxes = request.getParameter("checksArr");
            if(strSelectedCheckboxes != null)
            {
                if(strSelectedCheckboxes.indexOf("|") != -1)
                {
                    String strSelectedCheckboxesNew = strSelectedCheckboxes.replace('|', '~');
                    strSelectedPoVersion = strSelectedCheckboxesNew.split("~");
                } else
                {
                    strSelectedPoVersion = new String[1];
                    strSelectedPoVersion[0] = strSelectedCheckboxes;
                }
                int temp = 0;
                for(int i = 0; i < strSelectedPoVersion.length; i++)
                {
                    if(temp != 0)
                    {
                        idVersionString = idVersionString + ",";
                    }
                    strSelectedPoId = strSelectedPoVersion[i].split("-");
                    idVersionString = idVersionString + strSelectedPoId[0];
                    temp++;
                }

            }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setOrderType("FO");
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getPurchaseDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Furnish order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("furnishAll"))
        {
            String idVersionString = "";
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setOrderType("FO");
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getPurchaseDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Furnish Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("goodReceipt"))
        {
            String poId = request.getParameter("poId");
            String poVersion = request.getParameter("poVersion");
            BigDecimal poid = new BigDecimal(String.valueOf(poId));
            BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
            String idVersionString = request.getParameter("checksArr");
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getGoodReceiptDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Goods Receipt";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("goodReceiptAll"))
        {
            String poId = request.getParameter("poId");
            String poVersion = request.getParameter("poVersion");
            BigDecimal poid = new BigDecimal(String.valueOf(poId));
            BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
            String idVersionString = "";
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getGoodReceiptDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Goods Receipt";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        //RFS GR @anshu.bhardwaj
        if(flag.equalsIgnoreCase("goodReceiptPaperHistory"))
        {
        	//System.out.println("anshu flag"+flag);
            String poId = request.getParameter("poid");
            String poVersion = request.getParameter("poversion");
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            
            BigDecimal poid = new BigDecimal(String.valueOf(poId));
            BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
           // String idVersionString = "";
            //objExportToExcel.setPageNo(page);
            //objExportToExcel.setInputString(idVersionString);
            
            //dto.setPageNo(objExportToExcel.getPageNo());
            //dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);            
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getGoodReceiptPaperHistory(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Goods Receipt History";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("goodReceiptPaperNew"))
        {
        	//System.out.println("anshu flag"+flag);
            String poId = request.getParameter("poid");
            String poVersion = request.getParameter("poversion");
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            
            BigDecimal poid = new BigDecimal(String.valueOf(poId));
            BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
           // String idVersionString = "";
            //objExportToExcel.setPageNo(page);
            //objExportToExcel.setInputString(idVersionString);
            
            //dto.setPageNo(objExportToExcel.getPageNo());
            //dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);            
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getGoodReceiptPaperNew(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "New Goods Receipt Summary";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        
        if(flag.equalsIgnoreCase("cuOwnedPop"))
        {
        	String operation = request.getParameter("operation");
     	   String ownershipMode = request.getParameter("ownershipMode");
           String msgNo = request.getParameter("msgNo");  // DM_7000000392_4_964805
			 String pono = request.getParameter("pono");
			 	int idx = msgNo.lastIndexOf('_');
			 	String msgId = msgNo.substring(idx+1);
           ExportToExcelDTO dto = new ExportToExcelDTO();
           dto.setCuOwnershipMode(ownershipMode);
           dto.setCuPono(pono);
           dto.setCuMsgId(msgId);
           try
           {
               ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
               ExportToExcelDTO returnDTO = objDelegate.cuOwnedQtyPopup(dto); 
               int count = returnDTO.getExportElementVec().size();
               String reportName = "GR Roll Level Info";
               generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
           }
           catch(IOException e)
           {
               String errMsg = e.getMessage();
               request.setAttribute("PIX_ERROR", errMsg);
               return "error";
           }
           catch(AppException e)
           {
               String errMsg = e.getSErrorDescription();
               request.setAttribute("PIX_ERROR", errMsg);
               return "error";
           }
           return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("goodReceiptPaperPop"))
        {
        	String msgNo = request.getParameter("msgNo");
        	String pono = request.getParameter("pono");
            String poId = request.getParameter("poid");
            String poVersion = request.getParameter("poversion");
            String productCode = request.getParameter("productCode");
//            String msgId = request.getParameter("msgId");
            int idx = msgNo.lastIndexOf('_');
		 	String msgId = msgNo.substring(idx+1);
            
		 	int idx1 = msgNo.indexOf('_');
		 	String dmGRMode = msgNo.substring(0, idx1);
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            String ownershipMode = request.getParameter("ownershipMode");
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            
            BigDecimal poid = new BigDecimal(String.valueOf(poId));
            BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
          
            // String idVersionString = "";
            //objExportToExcel.setPageNo(page);
            //objExportToExcel.setInputString(idVersionString);
            
            //dto.setPageNo(objExportToExcel.getPageNo());
            //dto.setInputString(objExportToExcel.getInputString());
        //    dto.setUserId(objExportToExcel.getUserId());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);            
            dto.setProductCode(productCode);
            dto.setMsgID(msgId);
            dto.setDmGRMode(dmGRMode);
            dto.setPono(pono);
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getGoodReceiptPaperPop(dto); 
                int count = returnDTO.getExportElementVec().size();
                String reportName = "GR Roll Level Info";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        
        if(flag.equalsIgnoreCase("DMHistoryPaperPop"))
        {//
        	//System.out.println("anshu flag"+flag);
       //     String poId = request.getParameter("poid");   po_no = '7000000457' , product_code =  '0000004121', po_line_no = '2'
        	String poId = "7000000457";
        //    String poVersion = request.getParameter("poversion");
        //	String poVersion = "0000000280";
        	
        	String msgID = request.getParameter("msgID");
        	String DMGRMode = request.getParameter("DMGRMode");
      //  	String productCode = request.getParameter("productCode");
      //  	String lineNo = request.getParameter("lineNo");
        	
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            
         dto.setMsgID(msgID);
         dto.setCuDMGRMode(DMGRMode);
         
         
            
        //    BigDecimal poid = new BigDecimal(String.valueOf(poId));
        //    BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
         //   dto.setUserId(objExportToExcel.getUserId());
        //    dto.setPoId(poid);
         //   dto.setPoVersion(poversion);            
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDMHistoryPaperPop(dto); // getGoodReceiptPaperPop, getGoodReceiptPaperNew
                int count = returnDTO.getExportElementVec().size();
                String reportName = "DM History Roll Level";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        
        
        
        if(flag.equalsIgnoreCase("DMPaperPop"))
        {
        	//System.out.println("anshu flag"+flag);
       //     String poId = request.getParameter("poid");   po_no = '7000000457' , product_code =  '0000004121', po_line_no = '2'
        	String poId = "7000000457";
        //    String poVersion = request.getParameter("poversion");
        //	String poVersion = "0000000280";
        	
        	String pono = request.getParameter("pono");
        	String productCode = request.getParameter("productCode");
        	String lineNo = request.getParameter("lineNo");
        	
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            
            dto.setPono(pono);
            dto.setProductCode(productCode);
            dto.setLineNo(lineNo);
            
            
        //    BigDecimal poid = new BigDecimal(String.valueOf(poId));
        //    BigDecimal poversion = new BigDecimal(String.valueOf(poVersion));
         //   dto.setUserId(objExportToExcel.getUserId());
        //    dto.setPoId(poid);
         //   dto.setPoVersion(poversion);            
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDMPaperPop(dto); // getGoodReceiptPaperPop, getGoodReceiptPaperNew
                int count = returnDTO.getExportElementVec().size();
                Vector details = returnDTO.getReport_details();
                System.out.println(details.size());
                String reportName = "DM Roll Level Details";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        
        
        //end of GR RFS @anshu.bhardwaj
        if(flag.equalsIgnoreCase("delivery"))
        {
            BigDecimal poid = null;
            BigDecimal poversion = null;
            if(request.getParameter("poId") == "" && request.getParameter("poVersion") == "")
            {
                poid = null;
                poversion = null;
            } else
            {
                String poId = request.getParameter("poId");
                String poVersion = request.getParameter("poVersion");
                poid = new BigDecimal(String.valueOf(poId));
                poversion = new BigDecimal(String.valueOf(poVersion));
            }
            String idVersionString = request.getParameter("checksArr");
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDeliveryDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Delivery Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("deliveryAll"))
        {
            BigDecimal poid = null;
            BigDecimal poversion = null;
            if(request.getParameter("poId") == "" && request.getParameter("poVersion") == "")
            {
                poid = null;
                poversion = null;
            } else
            {
                String poId = request.getParameter("poId");
                String poVersion = request.getParameter("poVersion");
                poid = new BigDecimal(String.valueOf(poId));
                poversion = new BigDecimal(String.valueOf(poVersion));
            }
            String idVersionString = "";
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDeliveryDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Delivery Order";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                e.printStackTrace();
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        //RFS new DM Excel 
        if(flag.equalsIgnoreCase("deliveryMessageNewMill"))
        {
            BigDecimal poid = null;
            BigDecimal poversion = null;
            if(request.getParameter("poId") == "" && request.getParameter("poversion") == "")
            {
                poid = null;
                poversion = null;
            } else
            {
                String poId = request.getParameter("poid");
                String poVersion = request.getParameter("poversion");
                poid = new BigDecimal(String.valueOf(poId));
                poversion = new BigDecimal(String.valueOf(poVersion));
            }            
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setUserId(objExportToExcel.getUserId());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDeliveryMessageNewMill(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "New Delivery Message";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                e.printStackTrace();
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("deliveryMessageHistory"))
        {
            BigDecimal poid = null;
            BigDecimal poversion = null;
            if(request.getParameter("poid") == "" && request.getParameter("poversion") == "")
            {
                poid = null;
                poversion = null;
            } else
            {
                String poId = request.getParameter("poid"); // 163233
                String poVersion = request.getParameter("poversion"); // 1
                poid = new BigDecimal(String.valueOf(poId));
                poversion = new BigDecimal(String.valueOf(poVersion));
            }            
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setUserId(objExportToExcel.getUserId());
            dto.setPoId(poid);
            dto.setPoVersion(poversion);
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getDeliveryMessageHistory(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Delivery Message History";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                e.printStackTrace();
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("inboxDMHistory"))
        {
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getInboxDeliveryMessageHistory(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Delivery Message History";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
           catch(AppException e)
            {
                e.printStackTrace();
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        //end of RFS
        if(flag.equalsIgnoreCase("order"))
        {
            String poid = request.getParameter("poId");
            String poversion = request.getParameter("poVersion");
            Integer objPoId = new Integer(poid);
            Integer objPoVersion = new Integer(poversion);
            String idVersionString = request.getParameter("checksArr");
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoid(objPoId);
            dto.setPoversion(objPoVersion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getOrderStatusDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Order Status";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                e.printStackTrace();
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("orderAll"))
        {
            String idVersionString = "";
            String poid = null;
            String poversion = null;
            if(request.getParameter("poId") != null)
            {
                poid = request.getParameter("poId");
            }
            if(request.getParameter("poVersion") != null)
            {
                poversion = request.getParameter("poVersion");
            }
            Integer objPoId = new Integer(poid);
            Integer objPoVersion = new Integer(poversion);
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setPoid(objPoId);
            dto.setPoversion(objPoVersion);
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getOrderStatusDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Order Status";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("bookSpec"))
        {
            String idVersionString = "";
            String strSelectedPoId[] = (String[])null;
            String strSelectedPoVersion[] = (String[])null;
            String strSelectedCheckboxes = request.getParameter("checksArr");
            if(strSelectedCheckboxes != null)
            {
                if(strSelectedCheckboxes.indexOf(",") != -1)
                {
                    strSelectedPoVersion = strSelectedCheckboxes.split(",");
                } else
                {
                    strSelectedPoVersion = new String[1];
                    strSelectedPoVersion[0] = strSelectedCheckboxes;
                }
                int temp = 0;
                for(int i = 0; i < strSelectedPoVersion.length; i++)
                {
                    if(temp != 0)
                    {
                        idVersionString = idVersionString + ",";
                    }
                    strSelectedPoId = strSelectedPoVersion[i].split("-");
                    idVersionString = idVersionString + strSelectedPoId[0];
                    temp++;
                }

            }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getBookSpecDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Book Spec";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("bookSpecAll"))
        {
            String idVersionString = "";
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(idVersionString);
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getBookSpecDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Book Spec";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        if(flag.equalsIgnoreCase("report"))
        {
            if(request.getParameter("isbn10") != null)
            {
                objExportToExcel.setIsbn(request.getParameter("isbn10"));
            } else
            {
                objExportToExcel.setIsbn(request.getParameter("isbn13"));
            }
            objExportToExcel.setItem(request.getParameter("item"));
            objExportToExcel.setPorderNo(request.getParameter("porderNo"));
            objExportToExcel.setPrintNo(request.getParameter("printNo"));
            String sdate = (String)request.getSession().getAttribute("sdate");
            String edate = (String)request.getSession().getAttribute("edate");
            objExportToExcel.setOrderBy("5");
            objExportToExcel.setSort("ASC");
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            objExportToExcel.setPagination("1");
            ExportToExcelDTO objDTO = new ExportToExcelDTO();
            objDTO.setIsbn(objExportToExcel.getIsbn());
            objDTO.setItem(objExportToExcel.getItem());
            objDTO.setPorderNo(objExportToExcel.getPorderNo());
            objDTO.setPrintNo(objExportToExcel.getPrintNo());
            objDTO.setOrderBy(objExportToExcel.getOrderBy());
            objDTO.setSort(objExportToExcel.getSort());
            objDTO.setUserId(objExportToExcel.getUserId());
            objDTO.setsdate(sdate);
            objDTO.setedate(edate);
            objDTO.setPagination(objExportToExcel.getPagination());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                ExportToExcelDTO returnDTO = objDelegate.getReportDetail(objDTO);
                int count = returnDTO.getExportElementVec().size() - 2;
                String reportName = "Report";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
        } 
        /**
         * Flag for Displaying Outstanding Delivery Report 
         *          
         * @author Anshu Bhardwaj 
         */
        if(flag.equalsIgnoreCase("pending"))
        {            
            if(request.getSession().getAttribute("USER_INFO") != null)
            {
                objUser = (User)request.getSession().getAttribute("USER_INFO");
                objExportToExcel.setUserId(objUser.getUserId().toString());
            }
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            
            dto.setUserId(objExportToExcel.getUserId());
            try
            {
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();                
                ExportToExcelDTO returnDTO = objDelegate.getOutstandingDetail(dto);
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Outstanding Report";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            return "forwardfinished";
        }
        /*end of changes for Displaying Outstanding Delivery Report*/
        else
        {
            if(flag.equalsIgnoreCase("delMsg"))
            {
                String strSelectedCheckboxes = request.getParameter("checksArr");
                if(request.getSession().getAttribute("USER_INFO") != null)
                {
                    objUser = (User)request.getSession().getAttribute("USER_INFO");
                    objExportToExcel.setUserId(objUser.getUserId().toString());
                }
                objExportToExcel.setInputString(strSelectedCheckboxes);
                ExportToExcelDTO dto = new ExportToExcelDTO();
                dto.setInputString(objExportToExcel.getInputString());
                dto.setUserId(objExportToExcel.getUserId());
                try
                {
                    ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                    ExportToExcelDTO returnDTO = objDelegate.getDeliveryMessageApprovalDetail(dto);
                    int count = returnDTO.getExportElementVec().size();
                    String reportName = "Message Approval";
                    generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
                }
                catch(IOException e)
                {
                    String errMsg = e.getMessage();
                    request.setAttribute("PIX_ERROR", errMsg);
                    return "error";
                }
                catch(AppException e)
                {
                    String errMsg = e.getSErrorDescription();
                    request.setAttribute("PIX_ERROR", errMsg);
                    return "error";
                }
                return "forwardfinished";
            }
            if(flag.equalsIgnoreCase("delMsgAll"))
            {
                String strSelectedCheckboxes = "";
                if(request.getSession().getAttribute("USER_INFO") != null)
                {
                    objUser = (User)request.getSession().getAttribute("USER_INFO");
                    objExportToExcel.setUserId(objUser.getUserId().toString());
                }
                objExportToExcel.setInputString(strSelectedCheckboxes);
                ExportToExcelDTO dto = new ExportToExcelDTO();
                dto.setInputString(objExportToExcel.getInputString());
                dto.setUserId(objExportToExcel.getUserId());
                try
                {   
                	if(objUser != null){
                    ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                    ExportToExcelDTO returnDTO = objDelegate.getDeliveryMessageApprovalDetail(dto);
                    int count = returnDTO.getExportElementVec().size();
                    String reportName = "Message Approval";
                    generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
                	}
                	else{
                    	response.sendRedirect("/pix");
                 }
                }
                catch(IOException e)
                {
                    String errMsg = e.getMessage();
                    request.setAttribute("PIX_ERROR", errMsg);
                    return "error";
                }
                catch(AppException e)
                {
                    String errMsg = e.getSErrorDescription();
                    request.setAttribute("PIX_ERROR", errMsg);
                    return "error";
                }
                return "forwardfinished";
            }
        }
        if(flag.equalsIgnoreCase("titleSetup"))
        {
            String strSelectedCheckboxes = request.getParameter("checksArr");
            String status = request.getParameter("statusCode");
            String statusCode ="";
            if("68".equalsIgnoreCase(status)){
     		   statusCode = "PENDING";
     	    }else if("67".equalsIgnoreCase(status)){
     		   statusCode = "RECEIVED";
     	    }else if("72".equalsIgnoreCase(status)){
     		   statusCode = "CORRECTION";
     	    }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString(strSelectedCheckboxes);
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setStatusCode(statusCode); 
            try
            {
                ExportToExcelDTO returnDTO = null;
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                returnDTO = objDelegate.getPotentialARPDetail(dto);
                
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Potential ARP Setup";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
        }
        if(flag.equalsIgnoreCase("titleSetupAll"))  
        {
        	String status = request.getParameter("statusCode");
            String statusCode ="";
            if("68".equalsIgnoreCase(status)){
     		   statusCode = "PENDING";
     	    }else if("67".equalsIgnoreCase(status)){
     		   statusCode = "RECEIVED";
     	    }else if("72".equalsIgnoreCase(status)){
     		   statusCode = "CORRECTION";  
     	    }
            objExportToExcel.setPageNo(page);
            objExportToExcel.setInputString("");
            
            ExportToExcelDTO dto = new ExportToExcelDTO();
            dto.setPageNo(objExportToExcel.getPageNo());
            dto.setInputString(objExportToExcel.getInputString());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setUserId(objExportToExcel.getUserId());
            dto.setStatusCode(statusCode); 
            try
            {
                ExportToExcelDTO returnDTO = null;
                ExportToExcelDelegate objDelegate = new ExportToExcelDelegate();
                returnDTO = objDelegate.getPotentialARPDetail(dto);
                
                int count = returnDTO.getExportElementVec().size();
                String reportName = "Potential ARP Setup";
                generateExcel(request, response, objExportToExcel, returnDTO.getExportElementVec(), returnDTO.getReport_details(), count, reportName);
            }
            catch(IOException e)
            {
                String errMsg = e.getMessage();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
            catch(AppException e)
            {
                String errMsg = e.getSErrorDescription();
                request.setAttribute("PIX_ERROR", errMsg);
                return "error";
            }
        }
        
        return "forwardfinished";
    }

    private void generateExcel(HttpServletRequest request, HttpServletResponse response, ExportToExcelForm exportForm, Vector resultVec, Vector reportDetails, int count, String report_Name)
        throws IOException
    {
    	System.out.println(reportDetails.size());
        String reportName = "Pix " + report_Name; 
        response.setContentType("application/download");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + reportName + ".xls\";");
        HSSFWorkbook doc = new HSSFWorkbook();
        HSSFSheet page = doc.createSheet();
        doc.setSheetName(0, reportName);
        HSSFCellStyle style = doc.createCellStyle();
        HSSFFont font = doc.createFont();
        font.setFontHeightInPoints((short)11);
        font.setColor((short)8);
        font.setBoldweight((short)700);
        style.setFont(font);
        style.setBorderBottom((short)1);
        style.setFillPattern((short)1);
        style.setFillForegroundColor((new org.apache.poi.hssf.util.HSSFColor.WHITE()).getIndex());
        insertXLSColumnTitles(resultVec, doc, page, style, exportForm, count);
        int rowNum = 0;
        if(count != 0)
        {
        	System.out.println(reportDetails.size());
            rowNum = reportDetails.size() / count;
            System.out.println(rowNum);
        } else
        {
            rowNum = reportDetails.size();
        }
        int y = 1;
        int flag = 0;
        for(int i = 0; i < rowNum; i++)
        {
            HSSFRow row = page.createRow((short)(y++));
            for(int j = 0; j < count; j++)
            {
                HSSFCell cell = row.createCell((short)j);
                if(reportDetails.elementAt(flag).toString() != null)
                {
                        if(reportDetails.elementAt(flag) instanceof BigDecimal){
                                    cell.setCellValue(Double.parseDouble(reportDetails.elementAt(flag).toString()));
                        //System.out.println("Hello1");
                                    }
                        else{
                                    cell.setCellValue(reportDetails.elementAt(flag).toString());
                        //System.out.println("Hello1");
                                    }
                }
               // System.out.println(reportDetails.elementAt(flag));

                flag++;
            }

        }

        ServletOutputStream out = response.getOutputStream();
        doc.write(out);
        out.flush();
    }

    protected void insertXLSColumnTitles(Vector resultVec, HSSFWorkbook doc, HSSFSheet page, HSSFCellStyle style, ExportToExcelForm exportForm, int count)
    {
        HSSFRow row = page.createRow(0);
        for(int i = 0; i < count; i++)
        {
            HSSFCell cell = row.createCell((short)i);
            cell.setCellValue(resultVec.get(i).toString());
            cell.setCellStyle(style);
        }

    }
}
