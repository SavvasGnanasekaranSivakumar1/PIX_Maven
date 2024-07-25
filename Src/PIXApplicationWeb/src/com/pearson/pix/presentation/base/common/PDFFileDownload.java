package com.pearson.pix.presentation.base.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.pearson.pix.presentation.pdf.CommonPdfInterface;
import com.pearson.pix.presentation.pdf.PurchaseOrderPdf;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;

public class PDFFileDownload extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		/*initialization of castor resources*/
	} 
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
	public void doGet (HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
   {
		HttpSession session=null;
		Document document = new Document();
		OutputStream out=null;
		PdfWriter docWriter = null;
		try {
			SimpleDateFormat simpleDateFormat1=
				new SimpleDateFormat("MMddyyyy_HHmmss");
			simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			String dateAsString1 = simpleDateFormat1.format(new Date());
			 
			session = req.getSession();
			String reportName="Pix"+(String)session.getAttribute("PDF_Name");
			
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment;filename=\""
			        + reportName +"_"+dateAsString1+".pdf\";");
			out = res.getOutputStream();
			CommonPdfInterface intobj =(CommonPdfInterface)session.getAttribute("PDF_OBJECT");
			
			docWriter = PdfWriter.getInstance(document,out);
			intobj.display(document,req);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			out.flush();
			out.close();
       session.removeAttribute("PDF_OBJECT");
       session.removeAttribute("PDF_Name");
		}
   }
}
