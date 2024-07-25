package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.pearson.pix.dto.orderstatus.OrderStatusLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.orderstatus.action.OrderStatusForm;

public class OrderStatusPdf implements CommonPdfInterface
{
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		 
		
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
				
		        // float[] columnDefinitionSize = {33.30F, 33.30F, 33.30F, 33.30f,33.30f,33.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	            OrderStatusForm objOrderStatusForm;
	            objOrderStatusForm = (OrderStatusForm)session.getAttribute("orderStatusForm");
	            document.open();
	            
	            if (objOrderStatusForm.getPoNo() != null && objOrderStatusForm.getReleaseNo() != null && !((objOrderStatusForm.getReleaseNo()).equals("0"))){
	            	heading.add(new Phrase("Purchase Order Number " +objOrderStatusForm.getPoNo()+"-" +objOrderStatusForm.getReleaseNo(),objFont));
	            }
	            else
	            {
	            	heading.add(new Phrase("Purchase Order Number " + objOrderStatusForm.getPoNo(),objFont));	
	            }
				heading.setSpacingAfter(10f);
				document.add(heading);
	            
	            Paragraph objParagraph  = new Paragraph();
				objParagraph.add(new Phrase("Following are the details of the Status  No." +objOrderStatusForm.getStatusNo(),objFont));
	            document.add(objParagraph);
	            Table lineDetails = LineDetails(objOrderStatusForm,objFont);
	            document.add(lineDetails);
	            document.close();
		    }
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "OrderStatusPdf,display",de);
				   throw appException;
			}
			catch(Exception e)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.IO_EXCEPTION, 
			                "OrderStatusPdf,display",e);
				   throw appException;
			}
			return document;
	}
	
// this method is used to display Line Item Detials in the Order status	
	
	/**
	* This method defines Line Item  details.
	* @param objFont
	* @param objOrderStatusForm
	* @return Table
	*/	
	private Table LineDetails(OrderStatusForm objOrderStatusForm,Font objFont) throws AppException
	{
		Table objTable = null;
		try
		{
			objTable = new Table(7);
			objTable.setPadding(1);
			objTable.setSpacing(0);	
	        int headerwidths[] = {2,8,8,8,10,10,10};
	        objTable.setWidths(headerwidths);
	        objTable.setAlignment(Element.ALIGN_LEFT);
	        objTable.setWidth(90f);	
	        objTable.setDefaultCellBorderWidth(1);
	        objTable.setDefaultRowspan(1);
	        Cell datatablecell = new Cell();
	        OrderStatusLine objOrderStatusLine = null;
	     		  datatablecell = new Cell(new Phrase("",objFont));
				  datatablecell.setBackgroundColor(new Color(171,199,227));
				  objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("COMPONENT",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("REQUESTED DELIVERY DATE",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("ESTIMATED DELIVERY DATE",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("PRESSTIMELINE",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("ORDERSTATUS",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("COMMENTS",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          objTable.setBackgroundColor(new Color(188, 215, 243));;
		          objTable.setDefaultCellBorderWidth(1);
		          objTable.setDefaultRowspan(1);
	             for (int j = 0; j <objOrderStatusForm.getOrderStatusCollection().size(); j++)
	              {
	            	  objOrderStatusLine = (OrderStatusLine)((Vector)objOrderStatusForm.getOrderStatusCollection()).get(j);
			          BigDecimal poline = objOrderStatusLine.getPoLineNo();
			          if((poline.intValue())%2 == 0)
			          {
				          
			        	  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		                  datatablecell.setRowspan(1);
				          datatablecell = new Cell(new Phrase(poline.toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getProductDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getRequestedDeliveryDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getEstimatedDeliveryDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getTimelineDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getStatusDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getComments(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
			          }
			          if((poline.intValue()%2 != 0))
			          {
		            	  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
			              datatablecell.setRowspan(1);
				          datatablecell = new Cell(new Phrase(poline.toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getProductDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getRequestedDeliveryDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getEstimatedDeliveryDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getTimelineDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getStatusDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objOrderStatusLine.getComments(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
			          }
	             }
		 }
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "OrderStatusPdf,display",de);
				   throw appException;
			}
		return objTable;
		
	}
}
