package com.pearson.pix.presentation.mismatchreport.command;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.mismatchreport.MismatchDetailsDTO;

public class ExportMismatchReport extends HttpServlet
{
   public void service(HttpServletRequest request, 
    HttpServletResponse response)
      throws ServletException, IOException
   {    
    try    
    {  
        generateExcelAndSendIt( request,response); 
      
    } catch (Exception e)
    {
        e.printStackTrace();     
    }     
   }

private void generateExcelAndSendIt(HttpServletRequest request, HttpServletResponse response) {
	  HttpSession session = request.getSession();
	  Vector mismatchvec=(Vector)session.getAttribute("mismatchvec");
	  String poNumber = (String) session.getAttribute("ponum");
	  String materialNo = (String)session.getAttribute("materialno");
	  String millName = (String)session.getAttribute("millname1");
	  String reqQty = String.valueOf((BigDecimal)session.getAttribute("reqqty"));
	  String reportName = "Pix Mismatch Report";	  
	  response.setContentType("application/download");
      response.setHeader("Content-Disposition", "attachment;filename=\""
              + reportName + ".xls\";");
	  
	  	int rowCount =2;
		HSSFRow row = null;
	  	HSSFCell cell = null;	  	
		HSSFWorkbook doc = new HSSFWorkbook();
      // create one sheet, we are not doing complex pages of reports
      HSSFSheet page = doc.createSheet();
      doc.setSheetName(0, reportName);        
   
      // create the style header, this will be applied to the titles of the
      HSSFCellStyle style = doc.createCellStyle();
      // create the font for the style header
      HSSFFont font = doc.createFont();
      font.setFontHeightInPoints((short) 11);
      font.setColor(HSSFColor.BLACK.index);
      font.setBoldweight((short) HSSFFont.BOLDWEIGHT_NORMAL);
      // attach the font to the style header
      style.setFont(font);
      style = setBorder(style);      
      style.setFillForegroundColor((short) (new HSSFColor.WHITE()).getIndex());
      
      
      HSSFFont headerFont = doc.createFont();
      headerFont.setFontHeightInPoints((short) 10);
     // headerFont.setColor(HSSFColor.BLACK.index);
      headerFont.setBoldweight((short) HSSFFont.BOLDWEIGHT_BOLD);
      
      HSSFFont subheaderFont = doc.createFont();
      subheaderFont.setFontHeightInPoints((short) 10);
     // headerFont.setColor(HSSFColor.BLACK.index);
      subheaderFont.setBoldweight((short) HSSFFont.BOLDWEIGHT_NORMAL);
      
      HSSFCellStyle styleSubHeader = doc.createCellStyle();
      styleSubHeader.setFont(subheaderFont);
      styleSubHeader = setBorder(styleSubHeader);
      styleSubHeader.setFillForegroundColor((short) (new HSSFColor.YELLOW()).getIndex());
      
      HSSFCellStyle styleHeader = doc.createCellStyle();
      styleHeader.setFont(headerFont);
      styleHeader = setBorder(styleHeader);
      styleHeader.setFillForegroundColor((short) (new HSSFColor.PALE_BLUE()).getIndex());
      
      row = page.createRow((short) 0);
        cell = row.createCell((short) 0);
 		cell.setCellValue(PIXConstants.PO_EXCEL);
 		cell.setCellStyle(styleHeader);
 		page.setColumnWidth((short) 0, (short) 4000) ;
 		
 		cell = row.createCell((short) 1);
 		cell.setCellValue(PIXConstants.MERCHANT_EXCEL);
 		cell.setCellStyle(styleHeader);
 		page.setColumnWidth((short) 1, (short) 8000) ;
 		
 		cell = row.createCell((short) 2);
 		cell.setCellValue(PIXConstants.MATERIAL_EXCEL);
 		cell.setCellStyle(styleHeader);
 		page.setColumnWidth((short) 2, (short) 4000) ;
 		
 		cell = row.createCell((short) 3);
 		cell.setCellValue(PIXConstants.QUANTITY_EXCEL);
 		cell.setCellStyle(styleHeader);
 		page.setColumnWidth((short) 3, (short) 3000) ; 		
 		
 		row = page.createRow((short) 1);
        cell = row.createCell((short) 0);
 		cell.setCellValue(poNumber);
 		cell.setCellStyle(style);
 		
 		cell = row.createCell((short) 1);
 		cell.setCellValue(millName);
 		cell.setCellStyle(style);
 		
 		cell = row.createCell((short) 2);
 		cell.setCellValue(materialNo);
 		cell.setCellStyle(style);
 		
 		cell = row.createCell((short) 3);
 		cell.setCellValue(reqQty.toString());
 		cell.setCellStyle(style);
 		
 		 row = page.createRow((short) 2);
         cell = row.createCell((short) 0);
  		cell.setCellValue(PIXConstants.LINE_NO_EXCEL);
  		cell.setCellStyle(styleHeader);
  		//page.setColumnWidth((short) 0, (short) 4000) ;
  		
  		cell = row.createCell((short) 1);
  		cell.setCellValue(PIXConstants.MATERIAL_EXCEL);
  		cell.setCellStyle(styleHeader);
  		//page.setColumnWidth((short) 1, (short) 4000) ;
  		
  		cell = row.createCell((short) 2);
  		cell.setCellValue(PIXConstants.PLANT_EXCEL);
  		cell.setCellStyle(styleHeader);
  		//page.setColumnWidth((short) 2, (short) 4000) ;
  		
  		cell = row.createCell((short) 3);
  		cell.setCellValue(PIXConstants.ORDERED_QTY_EXCEL);
  		cell.setCellStyle(styleHeader);
  		//page.setColumnWidth((short) 3, (short) 4000) ; 	
  		
  		 cell = row.createCell((short) 4);
   		cell.setCellValue(PIXConstants.DM_QTY_EXCEL);
   		cell.setCellStyle(styleHeader);
   		page.setColumnWidth((short) 4, (short) 4000) ;
   		
   		cell = row.createCell((short) 5);
   		cell.setCellValue(PIXConstants.DM_DOC_EXCEL);
   		cell.setCellStyle(styleHeader);
   		page.setColumnWidth((short) 5, (short) 4000) ;
   		
   		cell = row.createCell((short) 6);
   		cell.setCellValue(PIXConstants.GR_QTY_EXCEL);
   		cell.setCellStyle(styleHeader);
   		page.setColumnWidth((short) 6, (short) 4000) ;
   		
   		cell = row.createCell((short) 7);
   		cell.setCellValue(PIXConstants.GR_DOC_NO_EXCEL);
   		cell.setCellStyle(styleHeader);
   		page.setColumnWidth((short) 7, (short) 4000) ; 
   		
   		cell = row.createCell((short) 8);
   		cell.setCellValue(PIXConstants.QTY_VARIANCE_EXCEL);
   		cell.setCellStyle(styleHeader);
   		page.setColumnWidth((short) 8, (short) 4000) ;
 		
 		String polineNum = "";
 		for (int i = 0; i < mismatchvec.size(); i++) {
 			MismatchDetailsDTO dto = (MismatchDetailsDTO)mismatchvec.get(i);
 			rowCount++;
 	   		row = page.createRow((short) rowCount);
 	   		if(polineNum.equalsIgnoreCase(dto.getPoLineNo().toString())){  	   		
 	   	    cell = row.createCell((short) 0);
	  		cell.setCellValue("");
	  		cell.setCellStyle(style);
	  		
	  		cell = row.createCell((short) 1);
	  		cell.setCellValue("");
	  		cell.setCellStyle(style);
	  		
	  		cell = row.createCell((short) 2);
	  		cell.setCellValue("");
	  		cell.setCellStyle(style);
	  		
	  		cell = row.createCell((short) 3);
	  		cell.setCellValue("");
	  		cell.setCellStyle(style);
	  		
	  		
 	  		cell = row.createCell((short) 4);
 	  		if(dto.getDmQty()!=null)
 	   		cell.setCellValue(dto.getDmQty().toString());
 	  		else cell.setCellValue("");
 	   		cell.setCellStyle(style);
 	   		
 	   		cell = row.createCell((short) 5);
 	   		if(dto.getDmDoc()!=null)
 	   		cell.setCellValue(dto.getDmDoc());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(style);
 	   		
 	   		cell = row.createCell((short) 6);
 	   		if(dto.getGrQty()!=null)
 	   		cell.setCellValue(dto.getGrQty().toString());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(style);
 	   		
 	   		cell = row.createCell((short) 7);
 	   		if(dto.getGrDoc()!=null)
 	   		cell.setCellValue(dto.getGrDoc());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(style);
 	   		
 	   		cell = row.createCell((short) 8);
 	   		if(dto.getVarianceQty()!=null)
 	   		cell.setCellValue(dto.getVarianceQty().toString());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(style);
 	   		}
 	   		else{
 	   		cell = row.createCell((short) 0);
 	   		if(dto.getPoLineNo()!=null)
 	  		cell.setCellValue(dto.getPoLineNo().toString());
 	   		else cell.setCellValue("");
 	  		cell.setCellStyle(styleSubHeader);
 	  		
 	  		cell = row.createCell((short) 1);
 	  		if(dto.getMaterialNo()!=null)
 	  		cell.setCellValue(dto.getMaterialNo());
 	  		else cell.setCellValue("");
 	  		cell.setCellStyle(styleSubHeader);
 	  		
 	  		cell = row.createCell((short) 2);
 	  		if(dto.getSapPlantCode()!=null)
 	  		cell.setCellValue(dto.getSapPlantCode());
 	  		else cell.setCellValue("");
 	  		cell.setCellStyle(styleSubHeader);
 	  		
 	  		cell = row.createCell((short) 3);
 	  		if(dto.getRequestedQty()!=null)
 	  		cell.setCellValue(dto.getRequestedQty().toString());
 	  		else cell.setCellValue("");
 	  		cell.setCellStyle(styleSubHeader);
 	  		
 	  		 cell = row.createCell((short) 4);
 	  		 if(dto.getDmQty()!=null)
 	   		cell.setCellValue(dto.getDmQty().toString());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(styleSubHeader);
 	   		
 	   		cell = row.createCell((short) 5);
 	   		if(dto.getDmDoc()!=null)
 	   		cell.setCellValue(dto.getDmDoc());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(styleSubHeader);
 	   		
 	   		cell = row.createCell((short) 6);
 	   		if(dto.getGrQty()!=null)
 	   		cell.setCellValue(dto.getGrQty().toString());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(styleSubHeader);
 	   		
 	   		cell = row.createCell((short) 7);
 	   		if(dto.getGrDoc()!=null)
 	   		cell.setCellValue(dto.getGrDoc());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(styleSubHeader);
 	   		
 	   		cell = row.createCell((short) 8);
 	   //	styleSubHeader.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
 	   		if(dto.getVarianceQty()!=null)
 	   		cell.setCellValue(dto.getVarianceQty().toString());
 	   		else cell.setCellValue("");
 	   		cell.setCellStyle(styleSubHeader); 	   			
 	   		}
 	   		if(dto.getPoLineNo()!=null) 	   			
 	   		polineNum = dto.getPoLineNo().toString();
 		}
 		
	  
		try {
			ServletOutputStream out = response.getOutputStream();
			 doc.write(out);
		     out.flush();                     
		     out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	  session.removeAttribute("mismatchvec");	
	  session.removeAttribute("ponum");	
	  session.removeAttribute("materialno");	
	  session.removeAttribute("millname1");	
	  session.removeAttribute("reqqty");	
}

	private HSSFCellStyle setBorder(HSSFCellStyle style) {
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillPattern((short) HSSFCellStyle.SOLID_FOREGROUND);
		return style;
	}


}