package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.business.common.PIXUtil;
import com.pearson.pix.dto.bookspecification.BookSpecLine;
import com.pearson.pix.dto.bookspecification.BookSpecMargin;
import com.pearson.pix.dto.bookspecification.BookSpecParty;
import com.pearson.pix.dto.bookspecification.BookSpecPartyContact;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.presentation.bookspecification.action.BookSpecForm;
import com.pearson.pix.dto.bookspecification.BookSpecMisc;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;

public class BookSpecificationPdf implements CommonPdfInterface{
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7,
				Font.NORMAL);
		
	   try
	   {
			
			float height = document.getPageSize().height();
			String lineno = "", productDescription = "";
			
			float[] columnDefinitionSize = { 33.30F, 33.30F, 33.30F, 33.30f };
			Paragraph heading = new Paragraph();
			HttpSession session = req.getSession();
			POLine poLine = null;
			BookSpecForm objBookSpecForm;
			objBookSpecForm = (BookSpecForm) session.getAttribute("bookspecform");
			document.open();
			heading.add(new Phrase("Book Specification No. " + objBookSpecForm.getBookSpec().getSpecNo() +   "-"   + objBookSpecForm.getBookSpec().getReleaseNo(), objFont));
			heading.setSpacingAfter(10f);
			document.add(heading);
			//System.out.println("Page count1:"+document.getPageNumber());
 		// This is for the First table.
			//System.out.println("This is for the First table");
    		Table table = Header(objFont, objBookSpecForm,columnDefinitionSize);
			document.add(table);
			Table bvrDetails = BuyerVendorDetails(objFont, objBookSpecForm);
			document.add(bvrDetails);
			BookSpecLine objBookSpecLine = null;
			if (objBookSpecForm.getBookSpec().getLineItemCollection() != null)
			{
				//System.out.println(objBookSpecForm.getBookSpec().getLineItemCollection().size());
				for (int i = 0; i < objBookSpecForm.getBookSpec().getLineItemCollection().size(); i++)
				{
					objBookSpecLine = (BookSpecLine) ((Vector) objBookSpecForm.getBookSpec().getLineItemCollection()).get(i);
					LinkedHashMap hmproduct = (LinkedHashMap)objBookSpecForm.getBookSpecPrev().get(objBookSpecLine.getProductCode());
					// this condition is for press detail
					//System.out.println("this condition is for press detail");
					//System.out.println(objBookSpecLine.getPressDetail()+":"+objBookSpecLine.getBindingDetail()+":"+objBookSpecLine.getNonPressDetail());
					if (objBookSpecLine.getPressDetail() != null)
					{
						if (objBookSpecLine.getFinishedGoodFlag().equals("Y"))
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription + "\t \t \t \t"+ "Finished Good", objFont);
							chunk.setBackground(new Color(230, 240, 250));
							document.add(chunk);
							PdfPTable presslineDetails = PressLineItemDetails(objFont,objBookSpecForm, objBookSpecLine,hmproduct);
							presslineDetails.setSplitLate(false);
							presslineDetails.setSpacingAfter(10f);
						//	document.add(presslineDetails);
							
						}
						else if (objBookSpecLine.getSpecLineNo() != null && objBookSpecLine.getProductDescription() != null)
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription, objFont);
							chunk.setBackground(new Color(230, 240, 250));
							document.add(chunk);
							PdfPTable presslineDetails = PressLineItemDetails(objFont,objBookSpecForm, objBookSpecLine,hmproduct);
							presslineDetails.setSplitLate(false);
							presslineDetails.setSpacingAfter(10f);
						//	document.add(presslineDetails);
						
						}
						
						
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							//System.out.println("Page count2:"+document.getPageNumber());
							PdfPTable pressmiscDetails = PressMiscLineDetails(objFont, objBookSpecForm,objBookSpecLine);
							pressmiscDetails.setSplitLate(false);
						    pressmiscDetails.setSpacingAfter(10f);
						    document.add(pressmiscDetails);
						}
						
					} 
					// this condition is for all press, Binding and nonpress
					else if(objBookSpecLine.getPressDetail() == null && objBookSpecLine.getBindingDetail() == null && objBookSpecLine.getNonPressDetail() == null )
					{
						//System.out.println(objBookSpecLine.getSpecLineNo()+":"+objBookSpecLine.getProductDescription());
						if (objBookSpecLine.getFinishedGoodFlag().equals("Y"))
						{
							//System.out.println("Page count3:"+document.getPageNumber());
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription + "\t \t \t \t"+ "Finished Good", objFont);
							chunk.setBackground(new Color(230, 240, 250));
							document.add(chunk);
						}
						else if (objBookSpecLine.getSpecLineNo() != null && objBookSpecLine.getProductDescription() != null)
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription , objFont);
							chunk.setBackground(new Color(230, 240, 250));
							//System.out.println("Page count4:"+document.getPageNumber());
							//System.out.println(chunk);
							document.add(chunk);
						}
						//System.out.println("outof4:"+objBookSpecLine.getMiscCollection().size());
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							PdfPTable misccollection = MisccollectionDetails(objFont, objBookSpecForm,hmproduct);
							//misccollection.setSplitLate(false);
							
							misccollection.setSpacingAfter(10f);
							//System.out.println("Page count5:"+document.getPageNumber());
							document.add(misccollection);
						}
					}
					
					// this condition is for Non-press Detail
					else if (objBookSpecLine.getNonPressDetail() != null)
					{
						if (objBookSpecLine.getFinishedGoodFlag().equals("Y"))
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription + "\t \t \t \t"+ "Finished Good", objFont);
							chunk.setBackground(new Color(230, 240, 250));
							//System.out.println("Page count6:"+document.getPageNumber());
							document.add(chunk);
						}
						else if (objBookSpecLine.getSpecLineNo() != null && objBookSpecLine.getProductDescription() != null)
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription, objFont);
							chunk.setBackground(new Color(230, 240, 250));
							//System.out.println("Page count7:"+document.getPageNumber());
							document.add(chunk);
						}
						
						PdfPTable nonpresslineDetails = NonPressLineItemDetails(objFont, objBookSpecForm, objBookSpecLine);
						nonpresslineDetails.setSplitLate(false);
						nonpresslineDetails.setSpacingAfter(10f);
					//	document.add(nonpresslineDetails);
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							
							PdfPTable nonpressmiscDetails = NonPressMiscLineDetails(objFont, objBookSpecForm,objBookSpecLine);
						
							nonpressmiscDetails.setSpacingAfter(10f);
							nonpressmiscDetails.setSplitLate(false);
							//System.out.println("Page count8:"+document.getPageNumber());
							document.add(nonpressmiscDetails);
						}

					}
					// this condition is for Binding Details
					else if (objBookSpecLine.getBindingDetail() != null)
					{
						if (objBookSpecLine.getFinishedGoodFlag().equals("Y"))
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription + "\t \t \t \t"+ "Finished Good", objFont);
							chunk.setBackground(new Color(230, 240, 250));
							//System.out.println("Page count9:"+document.getPageNumber());
							document.add(chunk);
						} 
						else if (objBookSpecLine.getSpecLineNo() != null && objBookSpecLine.getProductDescription() != null)
						{
							lineno = objBookSpecLine.getSpecLineNo().toString();
							productDescription = objBookSpecLine.getProductDescription();
							Chunk chunk = new Chunk(lineno + "."+ productDescription, objFont);
							chunk.setBackground(new Color(230, 240, 250));
							//System.out.println("Page count10:"+document.getPageNumber());
							document.add(chunk);
						}
						
						PdfPTable bindinglineDetails = BindingLineItemDetails(objFont, objBookSpecForm, objBookSpecLine);
						bindinglineDetails.setSpacingAfter(10f);
						bindinglineDetails.setSplitLate(false);
					//	document.add(bindinglineDetails);
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							PdfPTable bindingmiscDetails = BindingMiscLineDetails(objFont, objBookSpecForm,objBookSpecLine);
							bindingmiscDetails.setSpacingAfter(10f);
							bindingmiscDetails.setSplitLate(false);
							//System.out.println("Page count11:"+document.getPageNumber());
							document.add(bindingmiscDetails);
						}
					}
					//System.out.println("in");
				}
				//System.out.println("out");
			}
			Table table3 = new Table(2);
			table3.setPadding(1);
			table3.setSpacing(0);
			//System.out.println("Page count12:"+document.getPageNumber());
			int headerwid[] = { 10, 30 };
			table3.setWidths(headerwid);
			table3.setAlignment(Element.ALIGN_LEFT);
			table3.setDefaultCellBorderWidth(1);
			table3.setDefaultRowspan(1);
			table3.setWidth(90f);
			Cell cell2 = new Cell();
			table3.setDefaultCellBackgroundColor(new Color(230, 240, 250));
			BookSpecParty objBookSpecParty = null;
			BookSpecParty buyerBookSpecParty = null;
			BookSpecParty vendor = null;
			BookSpecParty buyer = null;
			String buyerComments = "";
			String vendorComments = "";
			BookSpecParty bookSpecParty = null;
            for (int i = 0; i < objBookSpecForm.getBookSpec().getPartyCollection().size(); i++)
            {
            	 bookSpecParty = (BookSpecParty) ((Vector) objBookSpecForm.getBookSpec().getPartyCollection()).get(i);
				 if (bookSpecParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
				 {
					buyer = bookSpecParty;
					if(buyer.getComments() != null)
					{
						buyerComments = buyer.getComments();
					}
				 }
				 if(bookSpecParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
				 {
					vendor = bookSpecParty;
					if(vendor.getComments() != null)
					{
						vendorComments = vendor.getComments();
					}
				 }
             }	 
			cell2 = new Cell(new Phrase("BUYER NOTES :", objFont));
			cell2.setBackgroundColor(new Color(236, 245, 253));
			table3.addCell(cell2);
			// table3.addCell(new Phrase("BUYER NOTES :",objFont));
			cell2 = new Cell(new Phrase(buyerComments, objFont));
			cell2.setBackgroundColor(new Color(248, 251, 254));
			table3.addCell(cell2);
			cell2 = new Cell(new Phrase("VENDOR COMMENTS:", objFont));
			cell2.setBackgroundColor(new Color(236, 245, 253));
			//System.out.println("Page count13:"+document.getPageNumber());
			table3.addCell(cell2);
			cell2 = new Cell(new Phrase(vendorComments, objFont));
			cell2.setBackgroundColor(new Color(248, 251, 254));
			table3.addCell(cell2);

			document.add(table3);
			//System.out.println("Page count:"+document.getPageNumber());
			document.close();
		} catch (DocumentException de) {
			AppException appException = new AppException();
			//System.out.println("Exception");
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		} catch (Exception e) {
			//System.out.println("Exception1");
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.IO_EXCEPTION,"BookSpecificationPdf,display", e);
			throw appException;
		}

		// step 5: we close the document (the outputstream is also closed
		// internally)

		return document;
	}

	private Table Header(Font objFont, BookSpecForm objBookSpecForm,float[] columnDefinitionSize) throws AppException {
		Table objTable = null;
		try {
			
				Date objrequestedDeliveryDate = null;
				String objrequestedDeliveryDate_String = new String();
	            if((objrequestedDeliveryDate = (Date)objBookSpecForm.getBookSpec().getSpecIssueDate())!= null)
	            {
			      Calendar calendar = new GregorianCalendar(Locale.US);
				  calendar.setTime(objrequestedDeliveryDate);
				  objrequestedDeliveryDate_String = (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);
	        	} 
				objTable = new Table(4);
				objTable.setPadding(2);
				objTable.setSpacing(0);
			   
			    Cell ncell = new Cell();
			    objTable.setAlignment(Element.ALIGN_LEFT);
			    objTable.setWidth(90f);
			    ncell = new Cell(new Phrase("ISBN 10:",objFont));
			    ncell.setBackgroundColor(new Color(236, 245, 253));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase(objBookSpecForm.getBookSpec().getTitleDetail().getIsbn10(),objFont));
			    ncell.setBackgroundColor(new Color(248, 251, 254));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase("ISSUE DATE:",objFont));
			    ncell.setBackgroundColor(new Color(236, 245, 253));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase(objrequestedDeliveryDate_String,objFont));
			    ncell.setBackgroundColor(new Color(248, 251, 254));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase("ISBN 13:",objFont));
			    ncell.setBackgroundColor(new Color(236, 245, 253));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase(objBookSpecForm.getBookSpec().getTitleDetail().getIsbn13(),objFont));
			    ncell.setBackgroundColor(new Color(248, 251, 254));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase("PRINT NO:",objFont));
			    ncell.setBackgroundColor(new Color(236, 245, 253));
			    objTable.addCell(ncell);
			    ncell = new Cell(new Phrase(objBookSpecForm.getBookSpec().getTitleDetail().getPrintNo(),objFont));
			    ncell.setBackgroundColor(new Color(248, 251, 254));
			    objTable.addCell(ncell);
				
		  }
		  catch (Exception de)
		  {
			  AppException appException = new AppException();
			  appException.performErrorAction(Exceptions.EXCEPTION,"BookSpecificationPdf,display", de);
			  throw appException;
		}
		return objTable;
	}

	/**
	 * @param objBookSpecForm
	 * @param objFont
	 * @return
	 */
	private Table BuyerVendorDetails(Font objFont, BookSpecForm objBookSpecForm) throws AppException {
		Table objTable = null;
		BookSpecParty vendor = null;
		BookSpecParty buyer = null;
		BookSpecPartyContact buyerContact = null;
		BookSpecPartyContact vendorContact = null;
		Vector partycontactCollection = null;
		BookSpecParty bookSpecParty = null;
		String attention = "";
		String vendorname1 = "",vendorname2="",vendorname3="", vendoraddress1 = "", vendoraddress2 = "", vendoraddress3 = "", vendoraddress4 = "", vendorcity = "", vendorpostalcode = "", vendorstate = "", vendorcountryname = "";
		String vendorcontactfirstname = "",vendorcontactlastname ="", vendorphone = "", vendormobile = "", vendorfax = "", vendoremail = "",vendorsan="";

		String buyername1 = "",buyername2="",buyername3="", buyeraddress1 = "",buyeraddress2 = "",buyeraddress3 = "",buyeraddress4 = "", buyercity = "", buyerpostalcode = "", buyerstate = "", buyercountryname = "";
		String buyercontactfirstname = "",buyercontactlastname ="", buyerphone = "", buyermobile = "", buyerfax = "", buyeremail = "";
		try {
			for (int i = 0; i < objBookSpecForm.getBookSpec().getPartyCollection().size(); i++) {
				
				bookSpecParty = (BookSpecParty) ((Vector) objBookSpecForm.getBookSpec().getPartyCollection()).get(i);
				if (bookSpecParty.getPartyType().equals(PIXConstants.BUYER_ROLE)) {
					buyer = bookSpecParty;
					if (buyer.getName1() != null)
					{
						buyername1 = buyer.getName1();
					}
					if (buyer.getName2() != null)
					{
						buyername2 = buyer.getName2();
					}
					if (buyer.getName3() != null)
					{
						buyername3 = buyer.getName3();
					}
					
					if (buyer.getAddress1() != null)
					{
						buyeraddress1 = buyer.getAddress1();
					}
					if (buyer.getAddress2() != null)
					{
						buyeraddress2 = buyer.getAddress2();
					}
					if (buyer.getAddress3() != null)
					{
						buyeraddress3 = buyer.getAddress3();
					}
					if (buyer.getAddress4() != null)
					{
						buyeraddress4 = buyer.getAddress4();
					}
					if (buyer.getCity() != null)
					{
						buyercity = buyer.getCity();
					}
					if (buyer.getPostalCode() != null)
					{
						buyerpostalcode = buyer.getPostalCode();
					}
					if (buyer.getState() != null)
					{
						buyerstate = buyer.getState();
					}
					
					if(buyer.getCountryDetail().getCountryName() != null)
					{
					   buyercountryname = buyer.getCountryDetail().getCountryName();
					}
					partycontactCollection = (Vector) bookSpecParty.getContactCollection();
					for (int j = 0; j < partycontactCollection.size(); j++)
					{
						BookSpecPartyContact bookSpecpartyContact = ((BookSpecPartyContact) partycontactCollection.get(j));
						buyerContact = bookSpecpartyContact;
						if (buyerContact.getContactFirstName() != null)
						{
							buyercontactfirstname = buyerContact.getContactFirstName();
						}
						if (buyerContact.getContactLastName() != null)
						{
							buyercontactlastname = buyerContact.getContactLastName();
						}
						if (buyerContact.getPhone() != null)
						{
							buyerphone = buyerContact.getPhone()+"(Business)";
						}
						if (buyerContact.getMobile() != null)
						{
							buyermobile = buyerContact.getMobile()+"(Mobile)";
						}
						if (buyerContact.getFax() != null)
						{
							vendorfax = buyerContact.getFax()+"(Fax)";
						}
						if (buyerContact.getEmail() != null)
						{
							buyeremail = buyerContact.getEmail();
						}
					}
				}
				if (bookSpecParty.getPartyType().equals(PIXConstants.VENDOR_ROLE)) {
					vendor = bookSpecParty;
					if(vendor.getSan() != null)
					{
						vendorsan = vendor.getSan();
					}
					if (vendor.getName1() != null)
					{
						vendorname1 = vendor.getName1();
					}
					if (vendor.getName2() != null)
					{
						vendorname2 = vendor.getName2();
					}
					if (vendor.getName3() != null)
					{
						vendorname3 = vendor.getName3();
					}
					
					if (vendor.getAddress1() != null)
					{
						vendoraddress1 = vendor.getAddress1();
					}
					if (vendor.getAddress2() != null)
					{
						vendoraddress2 = vendor.getAddress2();
					}
					if (vendor.getAddress3() != null)
					{
						vendoraddress3 = vendor.getAddress3();
					}
					if (vendor.getAddress4() != null)
					{
						vendoraddress4 = vendor.getAddress4();
					}
					if (vendor.getCity() != null)
					{
						vendorcity = vendor.getCity();
					}
					if (vendor.getPostalCode() != null)
					{
						vendorpostalcode = vendor.getPostalCode();
					}
					if (vendor.getState() != null)
					{
						vendorstate = vendor.getState();
					}
					
					if(vendor.getCountryDetail().getCountryName() != null)
					{
						vendorcountryname = vendor.getCountryDetail().getCountryName();
					}
					partycontactCollection = (Vector) bookSpecParty.getContactCollection();
					for (int j = 0; j < partycontactCollection.size(); j++)
					{
						BookSpecPartyContact bookSpecpartyContact = ((BookSpecPartyContact) partycontactCollection.get(j));
						vendorContact = bookSpecpartyContact;
						if (vendorContact.getContactFirstName() != null)
						{
							vendorcontactfirstname = vendorContact.getContactFirstName();
						}
						if (vendorContact.getContactLastName() != null)
						{
							vendorcontactlastname = vendorContact.getContactLastName();
						}
						if (vendorContact.getPhone() != null)
						{
							vendorphone = vendorContact.getPhone()+"(Business)";
						}
						if (vendorContact.getMobile() != null)
						{
							vendormobile = vendorContact.getMobile()+"(Mobile)";
						}
						if (vendorContact.getFax() != null)
						{
							vendorfax = vendorContact.getFax()+"(Fax)";
						}
						if (vendorContact.getEmail() != null)
						{
							vendoremail = vendorContact.getEmail();
						}
					}
				}
			}
			objTable = new Table(2);
			objTable.setPadding(2);
			objTable.setSpacing(0);
			Cell bvscell = new Cell();
			objTable.setAlignment(Element.ALIGN_LEFT);
			objTable.setWidth(90f);
			bvscell.setColspan(1);
			bvscell.setRowspan(1);
			bvscell = new Cell(new Phrase("BUYER", objFont));
			bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			objTable.addCell(bvscell);
			bvscell = new Cell(new Phrase("VENDOR"+ "("+ vendorsan + ")", objFont));
			bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			objTable.addCell(bvscell);

			bvscell = new Cell(new Phrase(buyername1 +"\n"+ buyername2 +"\n"+ buyername3 + "\n" + buyeraddress1	+ "\n" + buyeraddress2	+"\n" + buyeraddress3	+"\n" + buyeraddress4 +"\n" + buyercity  + "\t" + buyerpostalcode + "\t" + buyerstate + "\t" + " "
					+ buyercountryname + "\n" + " " + attention + " - "	+ buyercontactfirstname + "\t" + buyercontactlastname + "\n" + " " + buyerphone
					+ "\n" + buyermobile + "\n" + buyerfax+ "\n" + " " + buyeremail + " ", objFont));
			objTable.addCell(bvscell);
			bvscell = new Cell(new Phrase(vendorname1 + "\n"+ vendorname2 +"\n"+ vendorname3+ "\n" + vendoraddress1 +"\n" + vendoraddress2 +"\n" + vendoraddress3 +"\n" + vendoraddress4 +"\n" +vendorcity + "\t" + vendorpostalcode + "\t" + vendorstate + "\t" + " "
					+ vendorcountryname + "\n" + " " + attention + " - "+ vendorcontactfirstname + "\t" + vendorcontactlastname + "\n" + " " + vendorphone
					+ "\n" + vendormobile + "\n"+ vendorfax + "\n" + " " + vendoremail + " ", objFont));
			objTable.addCell(bvscell);

		} catch (DocumentException de) {
			AppException appException = new AppException();
			appException.performErrorAction(
					Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		} catch (Exception e) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.EXCEPTION,"BookSpecificationPdf,display", e);
			throw appException;
		}
		return objTable;
	}

	// this is for Binding
	private PdfPTable BindingLineItemDetails(Font objFont,BookSpecForm objBookSpecForm,BookSpecLine objBookSpecLine) throws AppException
	{
		PdfPTable objTable = null;
		String lineno = "", productDescription = "";

		String backstyletype = "", bindingExQnty = "", blockEdgeFinish = "", caseDecorationHits = "", caseDecorationType = "", coverScoring = "", endSheetDesc = "";
		String holepunchEdgeDistance = "", holePunchInfo = "", holepunchSize = "", holePunchType = "", perfoEdgeDistance = "", perfoType = "", reinforcement = "", styleType = "", wireGauge = "";

		try {
			if (objBookSpecLine.getBindingDetail() != null)
			{
				if (objBookSpecLine.getFinishedGoodFlag() == "Y")
				{
					lineno = objBookSpecLine.getSpecLineNo().toString();
					productDescription = objBookSpecLine.getProductDescription();
				}
				if (objBookSpecLine.getBindingDetail().getBackStyleType() != null)
				{
					backstyletype = objBookSpecLine.getBindingDetail().getBackStyleType();
				}
				if (objBookSpecLine.getBindingDetail().getBindingExtraQuantity() != null)
				{
					bindingExQnty = objBookSpecLine.getBindingDetail().getBindingExtraQuantity().toString();
				}
				if (objBookSpecLine.getBindingDetail().getBlockEdgeFinish() != null)
				{
					blockEdgeFinish = objBookSpecLine.getBindingDetail().getBlockEdgeFinish();
				}
				if (objBookSpecLine.getBindingDetail().getCaseDecorationHits() != null)
				{
					caseDecorationHits = objBookSpecLine.getBindingDetail().getCaseDecorationHits().toString();
				}
				if (objBookSpecLine.getBindingDetail().getCaseDecorationType() != null)
				{
					caseDecorationType = objBookSpecLine.getBindingDetail().getCaseDecorationType();
				}
				if (objBookSpecLine.getBindingDetail().getCoverScoring() != null)
				{
					coverScoring = objBookSpecLine.getBindingDetail().getCoverScoring();
				}
				if (objBookSpecLine.getBindingDetail().getEndSheetDesc() != null)
				{
					endSheetDesc = objBookSpecLine.getBindingDetail().getEndSheetDesc();
				}
				if (objBookSpecLine.getBindingDetail().getHolepunchEdgeDistance() != null)
				{
					holepunchEdgeDistance = objBookSpecLine.getBindingDetail().getHolepunchEdgeDistance().toString();
				}
				if (objBookSpecLine.getBindingDetail().getHolePunchInfo() != null)
				{
					holePunchInfo = objBookSpecLine.getBindingDetail().getHolePunchInfo();
				}
				if (objBookSpecLine.getBindingDetail().getHolepunchSize() != null)
				{
					holepunchSize = objBookSpecLine.getBindingDetail().getHolepunchSize().toString();
				}
				if (objBookSpecLine.getBindingDetail().getHolePunchType() != null)
				{
					holePunchType = objBookSpecLine.getBindingDetail().getHolePunchType();
				}
				if (objBookSpecLine.getBindingDetail().getPerfoEdgeDistance() != null)
				{
					perfoEdgeDistance = objBookSpecLine.getBindingDetail().getPerfoEdgeDistance().toString();
				}
				if (objBookSpecLine.getBindingDetail().getPerfoType() != null)
				{
					perfoType = objBookSpecLine.getBindingDetail().getPerfoType();
				}
				if (objBookSpecLine.getBindingDetail().getReinforcement() != null)
				{
					reinforcement = objBookSpecLine.getBindingDetail().getReinforcement();
				}
				if (objBookSpecLine.getBindingDetail().getStyleType() != null)
				{
					styleType = objBookSpecLine.getBindingDetail().getStyleType();
				}
				if (objBookSpecLine.getBindingDetail().getWireGauge() != null)
				{
					wireGauge = objBookSpecLine.getBindingDetail().getWireGauge();
				}

			}

			objTable = new PdfPTable(4);
			objTable.getDefaultCell().setPadding(3);
			//objTable.setPadding(2);
			//objTable.setSpacing(0);
			PdfPCell bvscell = new PdfPCell();
			
			objTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			objTable.setWidthPercentage(90f);
			bvscell.setColspan(1);
			
			bvscell.setColspan(4);
			
			bvscell = new PdfPCell(new Phrase("BACK STYLE TYPE:" + "\n"+ "BLOCK EDGE FINISH::" + "\n" + "CASE DECORATION TYPE:"
					+ "\n" + "END SHEET DESC:" + "\n" + "HOLE PUNCH INFO:"+ "\n" + "HOLE PUNCH TYPE:" + "\n" + "PERFO TYPE:" + "\n"
					+ "STYLE TYPE:", objFont));
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			bvscell.setPadding(3);
			objTable.addCell(bvscell);
			bvscell.setColspan(1); 
			bvscell = new PdfPCell(new Phrase(backstyletype + "\n"+ blockEdgeFinish + "\n" + caseDecorationType + "\n"
					+ endSheetDesc + "\n" + holePunchInfo + "\n"+ holePunchType + "\n" + perfoType + "\n" + styleType,
					objFont));
			bvscell.setBackgroundColor(new Color(248, 251, 254));
		
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase("BINDING EXTRA QUANTITY:" + "\n"+ "CASE DECORATION HITS::" + "\n" + "COVER SCORING:" + "\n"
					+ "HOLE PUNCH EDGE DISTANCE:" + "\n" + "HOLE PUNCH SIZE:"+ "\n" + "PERFO EDGE DISTANCE:" + "\n" + "REINFORCEMENT:"
					+ "\n" + "WIRE GUAGE:", objFont));
			bvscell.setBackgroundColor(new Color(236, 245, 253));
		
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase(bindingExQnty + "\n"+ caseDecorationHits + "\n" + coverScoring + "\n"
					+ holepunchEdgeDistance + "\n" + holepunchSize + "\n"+ perfoEdgeDistance + "\n" + reinforcement + "\n"
					+ wireGauge, objFont));
			bvscell.setBackgroundColor(new Color(248, 251, 254));
			
			objTable.addCell(bvscell);

		} catch (Exception de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return objTable;

	}

	// This is miscellinious Details of Binding
	private PdfPTable BindingMiscLineDetails(Font objFont,BookSpecForm objBookSpecForm,BookSpecLine objBookSpecLine) throws AppException
	{
		PdfPTable miscTable = null;
		BookSpecMisc objBookSpecMisc = null;
		
		String label = "", value = "";
		try {
			miscTable = new PdfPTable(4);
				
			PdfPCell miscCell = new PdfPCell();
			PdfPCell colspaceCell = new PdfPCell();
			
			miscTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			miscTable.setWidthPercentage(90f);
			miscCell.setColspan(2);

				if (objBookSpecLine.getBindingDetail() != null)
					{
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							int size = objBookSpecLine.getMiscCollection().size();
							for (int i = 0; i < objBookSpecLine.getMiscCollection().size(); i++)
							{
								value=null;
								objBookSpecMisc = (BookSpecMisc) ((Vector) objBookSpecLine.getMiscCollection()).get(i);
								
								if (objBookSpecMisc.getLabel() != null)
								{
									label = objBookSpecMisc.getLabel().toUpperCase();
								}
								if (objBookSpecMisc.getValue() != null)
								{
									value = objBookSpecMisc.getValue();
								}
								if(i%2 == 0)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									if(i == (size - 1))
									{
										colspaceCell.setColspan(2);
										colspaceCell.setBackgroundColor(new Color(248, 251, 254));
										miscTable.addCell(colspaceCell);
									}
								}
								else if(i%2 == 1)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
								}
							}

						}
					}
				

		}
		catch(Exception de) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return miscTable;

	}

	// This is for Press Details

	private PdfPTable PressLineItemDetails(Font objFont,BookSpecForm objBookSpecForm, BookSpecLine objBookSpecLine,LinkedHashMap hmproduct) throws AppException
	{
	
		PdfPTable objTable = null;
		String gutterlabel = "",headerlabel="",footerlabel="",thumblabel="";
		String guttervalue = "",headervalue="",footervalue="",thumbvalue="";
    	String lineno = "", productDescription = "";
		String barcode = "", inkcolourspecs = "", numberofpages = "", pagespersign = "", perfotype = "", pressprepinputtype = "", printproductcode = "", width = "", numberofcolors = "", trimsizedesc = "", ppiuomdetail = "", widthuomdetail = "";
		String basisweight = "", length = "", numberofsign = "", perfoedgedistance = "", ppi = "", presstype = "", printproductdescription = "", colordesc = "", basisweightuomdetail = "", lengthuomdetail = "";
		try
		{
		    /*	if (objBookSpecLine.getPressDetail() != null) {
				/*
				 * if(objBookSpecLine.getFinishedGoodFlag() == "Y") { lineno =
				 * objBookSpecLine.getSpecLineNo().toString();
				 * productDescription = objBookSpecLine.getProductDescription(); }
				 */

				if (objBookSpecLine.getSpecLineNo() != null)
				{
					lineno = objBookSpecLine.getSpecLineNo().toString();

				}
				if(objBookSpecLine.getProductDescription() != null)
				{
					productDescription = objBookSpecLine.getProductDescription();
				}
				if(objBookSpecLine.getPressDetail().getBarcode() != hmproduct.get("BARCODE"))
				{
					barcode = objBookSpecLine.getPressDetail().getBarcode();
				}
				if(objBookSpecLine.getPressDetail().getBarcode() != null)
				{
					barcode = objBookSpecLine.getPressDetail().getBarcode();
				}
				if(objBookSpecLine.getPressDetail().getBasisWt() != null)
				{
					basisweight = objBookSpecLine.getPressDetail().getBasisWt().toString();
				}
				if(objBookSpecLine.getPressDetail().getInkColourSpecs() != null)
				{
					inkcolourspecs = objBookSpecLine.getPressDetail().getInkColourSpecs();
				}
				if(objBookSpecLine.getPressDetail().getLength() != null)
				{
					length = objBookSpecLine.getPressDetail().getLength().toString();
				}
				if(objBookSpecLine.getPressDetail().getNoOfPages() != null)
				{
					numberofpages = objBookSpecLine.getPressDetail().getNoOfPages().toString();
				}
				if(objBookSpecLine.getPressDetail().getNoOfSign() != null)
				{
					numberofsign = objBookSpecLine.getPressDetail().getNoOfSign().toString();
				}
				if(objBookSpecLine.getPressDetail().getPagesPerSign() != null)
				{
					pagespersign = objBookSpecLine.getPressDetail().getPagesPerSign().toString();
				}
				if(objBookSpecLine.getPressDetail().getPerfoEdgeDistance() != null)
				{
					perfoedgedistance = objBookSpecLine.getPressDetail().getPerfoEdgeDistance().toString();
				}
				if(objBookSpecLine.getPressDetail().getPerfoType() != null)
				{
					perfotype = objBookSpecLine.getPressDetail().getPerfoType();
				}
				if (objBookSpecLine.getPressDetail().getPpi() != null)
				{
					ppi = objBookSpecLine.getPressDetail().getPpi().toString();
				}
				if(objBookSpecLine.getPressDetail().getPressPrepInputType() != null)
				{
					pressprepinputtype = objBookSpecLine.getPressDetail().getPressPrepInputType();
				}
				if(objBookSpecLine.getPressDetail().getPressType() != null)
				{
					presstype = objBookSpecLine.getPressDetail().getPressType();
				}
				if(objBookSpecLine.getPressDetail().getPrintProductCode() != null)
				{
					printproductcode = objBookSpecLine.getPressDetail()	.getPrintProductCode();
				}
				if(objBookSpecLine.getPressDetail().getPrintProductDescription() != null)
				{
					printproductdescription = objBookSpecLine.getPressDetail().getPrintProductDescription();
				}
				if(objBookSpecLine.getPressDetail().getWidth() != null)
				{
					width = objBookSpecLine.getPressDetail().getWidth().toString();
				}
				
				if(objBookSpecLine.getPressDetail().getNoOfColors() != null)
				{
					numberofcolors = objBookSpecLine.getPressDetail().getNoOfColors().toString();
				}
								
				if(objBookSpecLine.getPressDetail().getTrimSizeDesc() != null)
				{
					trimsizedesc = objBookSpecLine.getPressDetail().getTrimSizeDesc();
				}
								
				//System.out.println(" The value of the ppiuomid is === " +objBookSpecLine.getPressDetail().getPpiUomDetail().getUom());
				//PIXUtil.checkNull(objBookSpecLine.getPressDetail().getPpiUomDetail().getUom())
				
				if(objBookSpecLine.getPressDetail() != null && objBookSpecLine.getPressDetail().getPpiUomDetail()!= null && PIXUtil.checkNullField(objBookSpecLine.getPressDetail().getPpiUomDetail().getUom()) && objBookSpecLine.getPressDetail().getPpiUomDetail().getUom() != null)
				{
					ppiuomdetail = objBookSpecLine.getPressDetail().getPpiUomDetail().getUom();
					
				}
				if(objBookSpecLine.getPressDetail() != null && objBookSpecLine.getPressDetail().getPpiUomDetail()!= null && objBookSpecLine.getPressDetail().getWidthUomDetail().getUom() != null)
				{
					widthuomdetail = objBookSpecLine.getPressDetail().getWidthUomDetail().getUom().toString();
					
				}
				if(objBookSpecLine.getPressDetail().getColorDesc() != null)
				{
					colordesc = objBookSpecLine.getPressDetail().getColorDesc();
				}
				if(objBookSpecLine.getPressDetail().getBasisWtUomDetail() != null && objBookSpecLine.getPressDetail().getBasisWtUomDetail().getUom() != null)
				{
					basisweightuomdetail = objBookSpecLine.getPressDetail().getBasisWtUomDetail().getUom().toString();
				}
				if(objBookSpecLine.getPressDetail().getLengthUomDetail() !=null && objBookSpecLine.getPressDetail().getLengthUomDetail().getUom() != null)
				{
					lengthuomdetail = objBookSpecLine.getPressDetail().getLengthUomDetail().getUom().toString();
				}
				BookSpecMargin objBookSpecMargin = null;
		    	
		   		for (int i = 0; i < objBookSpecLine.getPressDetail().getMarginCollection().size(); i++) 
				{
		   			
		   			objBookSpecMargin = (BookSpecMargin) ((Vector) objBookSpecLine.getPressDetail().getMarginCollection()).get(i);
		   			if(i==0)
		   			{	
			    	    if(objBookSpecMargin.getMarginDetail().getDescription() != null)
			    	    {
			    	    	gutterlabel = objBookSpecMargin.getMarginDetail().getDescription().toUpperCase();
			    	    }
			    	    if(objBookSpecMargin.getValue() != null)
			    	    {
			    	    	guttervalue = objBookSpecMargin.getValue().toString();
			    	    }
		   			}
		   			if(i == 1)
		   			{	
			    	    if(objBookSpecMargin.getMarginDetail().getDescription() != null)
			    	    {
			    	    	headerlabel = objBookSpecMargin.getMarginDetail().getDescription().toUpperCase();
			    	    }
			    	    if(objBookSpecMargin.getValue() != null)
			    	    {
			    	    	headervalue = objBookSpecMargin.getValue().toString();
			    	    }
		   			}
		   			if(i == 2)
			   		{	
			    	    if(objBookSpecMargin.getMarginDetail().getDescription() != null)
			    	    {
			    	    	footerlabel = objBookSpecMargin.getMarginDetail().getDescription().toUpperCase();
			    	    }
			    	    if(objBookSpecMargin.getValue() != null)
			    	    {
			    	    	footervalue = objBookSpecMargin.getValue().toString();
			    	    }
			   		}
		   			if(i == 3)
		   			{
			    	    if(objBookSpecMargin.getMarginDetail().getDescription() != null)
			    	    {
			    	    	thumblabel = objBookSpecMargin.getMarginDetail().getDescription().toUpperCase();
			    	    }
			    	    if(objBookSpecMargin.getValue() != null)
			    	    {
			    	    	thumbvalue = objBookSpecMargin.getValue().toString();
			    	    }
		   			}    
				}    	
			
		
		    	

			objTable = new PdfPTable(4);
			objTable.getDefaultCell().setPadding(3);
			//objTable.setPadding(2);
			//objTable.setSpacing(0);
			PdfPCell bvscell = new PdfPCell();
			objTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			objTable.setWidthPercentage(90f);
			bvscell.setColspan(4);
			
			bvscell = new PdfPCell(new Phrase("BARCODE:" + "\n"	+ "INK COLOUR SPECS::" + "\n" + "NUMBER OF PAGES:" + "\n"
					+ "PAGES PER SIGN:" + "\n" + "PERFO TYPE:" + "\n"+ "PRESS PREP INPUT TYPE:" + "\n" + "PRINT PRODUCT CODE:"
					+ "\n" + "NUMBER OF COLORS:" + "\n" + "TRIM SIZE DESC:"	+ "\n" + "PPI UOM :" + "\n" + "WIDTH UOM :" + "\n" + gutterlabel +":" +"\n" + footerlabel +":",objFont));
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase(barcode + "\n" + inkcolourspecs+ "\n" + numberofpages + "\n" + pagespersign + "\n"
					+ perfotype + "\n" + pressprepinputtype + "\n"+ printproductcode + "\n" + numberofcolors + "\n"
					+ trimsizedesc + "\n" + ppiuomdetail + "\n"+ widthuomdetail + "\n" + guttervalue + "\n" + footervalue , objFont));
			 bvscell.setBackgroundColor(new Color(248, 251, 254));
			
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase("BASIS WEIGHT:" + "\n" + "LENGTH:"+ "\n" + "NUMBER OF SIGN:" + "\n" + "PERFO EDGE DISTANCE:"
					+ "\n" + "PPI:" + "\n" + "PRESS TYPE:" + "\n"+ "PRINT PRODUCT DESCRIPTION:" + "\n" + "COLOR DESC:"
					+ "\n" + "BASIS WEIGHT UOM :" + "\n"+ "LENGTH UOM :" + "\n" + "WIDTH:"+ "\n" + headerlabel +":" + "\n" + thumblabel +":", objFont));
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase(basisweight + "\n" + length + "\n"+ numberofsign + "\n" + perfoedgedistance + "\n" + ppi
					+ "\n" + presstype + "\n" + printproductdescription + "\n"+ colordesc + "\n" + basisweightuomdetail + "\n"
					+ lengthuomdetail + "\n" + width + "\n" + headervalue + "\n" + thumbvalue , objFont));
			bvscell.setBackgroundColor(new Color(248, 251, 254));
			
			objTable.addCell(bvscell);

		} catch (Exception de) {
			AppException appException = new AppException();
			appException.performErrorAction(
					Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return objTable;

	}

	// This is miscellinious Details of Binding
	private PdfPTable PressMiscLineDetails(Font objFont,BookSpecForm objBookSpecForm,BookSpecLine objBookSpecLine) throws AppException
	{
		PdfPTable miscTable = null;
		BookSpecMisc objBookSpecMisc = null;
		
		String label = "", value = "";
		try {
			miscTable = new PdfPTable(4);
			
			//miscTable.setPadding(1);
		//	miscTable.sets.setSpacing(0);
			PdfPCell  miscCell = new PdfPCell();
			PdfPCell colspaceCell = new PdfPCell();
			miscTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			miscTable.setWidthPercentage(90.0f);
			miscCell.setColspan(2);
			
				
					    
						if (objBookSpecLine.getFinishedGoodFlag().equals("Y"))
						{
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							int size = objBookSpecLine.getMiscCollection().size();
							
							for (int i = 0; i < objBookSpecLine.getMiscCollection().size(); i++)
							{
								value=null;
								objBookSpecMisc = new BookSpecMisc();
								objBookSpecMisc = (BookSpecMisc) ((Vector) objBookSpecLine.getMiscCollection()).get(i);
								if (objBookSpecMisc.getLabel() != null)
								{
									label = objBookSpecMisc.getLabel().toUpperCase();
								}
								if (objBookSpecMisc.getValue() != null)
								{
									value = objBookSpecMisc.getValue();
								}
								if(i%2 == 0)
								{	
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									if(i == (size - 1))
									{
										colspaceCell.setColspan(2);
										colspaceCell.setBackgroundColor(new Color(248, 251, 254));
										miscTable.addCell(colspaceCell);
									}
								}
								else if(i%2 == 1)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									
								}
							}

						}
					}
					
					else if (objBookSpecLine.getSpecLineNo() != null && objBookSpecLine.getProductDescription() != null)
					{
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							int size = objBookSpecLine.getMiscCollection().size();
							for (int i = 0; i < objBookSpecLine.getMiscCollection().size(); i++)
							{
								value=null;
								objBookSpecMisc = (BookSpecMisc) ((Vector) objBookSpecLine.getMiscCollection()).get(i);
								if (objBookSpecMisc.getLabel() != null)
								{
									label = objBookSpecMisc.getLabel().toUpperCase();
								}
								if (objBookSpecMisc.getValue() != null)
								{
									value = objBookSpecMisc.getValue();
								}
								if(i%2 == 0)
								{	
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									if(i == (size - 1))
									{
										colspaceCell.setColspan(2);
										colspaceCell.setBackgroundColor(new Color(248, 251, 254));
										miscTable.addCell(colspaceCell);
									}
								}
								else if(i%2 == 1)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									
							    }
							}

						}
					}
				 
				}

		
		catch (Exception de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return miscTable;

	}

	// This is for Non Press Details

	private PdfPTable NonPressLineItemDetails(Font objFont,BookSpecForm objBookSpecForm, BookSpecLine objBookSpecLine) throws AppException
	{
	
		PdfPTable objTable = null;
		String lineno = "", productDescription = "";
		String masterstorageoption = "", cdrtype = "", labelcolerdesc = "", numberofcolors = "", printing = "", slidecounterdesc = "", slidelengthuomid = "", slidemountmaterialdesc = "";
		String slideperpages = "", slidewidth = "", wraptype = "";
		String totalunits = "", formattype = "", medialength = "", presspreinputtype = "", slidecolorcode = "", slidelength = "", slidemountmaterial = "", slidepagesperset = "";
		String slideseqnumber = "", slidewidthuomid = "";
		try
		{
			if (objBookSpecLine.getNonPressDetail() != null)
			{
				if (objBookSpecLine.getNonPressDetail().getMasterStorageOption() != null)
				{
					masterstorageoption = objBookSpecLine.getNonPressDetail().getMasterStorageOption();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getCdrType() != null)
				{
					cdrtype = objBookSpecLine.getNonPressDetail().getMediaDetail().getCdrType();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getLabelColorDesc() != null)
				{
					labelcolerdesc = objBookSpecLine.getNonPressDetail().getMediaDetail().getLabelColorDesc();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getNoOfColors() != null)
				{
					numberofcolors = objBookSpecLine.getNonPressDetail().getMediaDetail().getNoOfColors().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getPrinting() != null)
				{
					printing = objBookSpecLine.getNonPressDetail().getMediaDetail().getPrinting();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideCountainerDesc() != null)
				{
					slidecounterdesc = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideCountainerDesc();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideLengthUomId() != null)
				{
					slidelengthuomid = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideLengthUomId().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideMountMaterialDesc() != null)
				{
					slidemountmaterialdesc = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideMountMaterialDesc();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlidePerPages() != null)
				{
					slideperpages = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlidePerPages().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideWidth() != null)
				{
					slidewidth = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideWidth().toString();
				}
				if(objBookSpecLine.getNonPressDetail().getMediaDetail().getWrapType() != null)
				{
					wraptype = objBookSpecLine.getNonPressDetail().getMediaDetail().getWrapType();
				}
				if (objBookSpecLine.getNonPressDetail().getTotalUnits() != null)
				{
					totalunits = objBookSpecLine.getNonPressDetail().getTotalUnits().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getFormatType() != null)
				{
					formattype = objBookSpecLine.getNonPressDetail().getMediaDetail().getFormatType();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getMediaLength() != null)
				{
					medialength = objBookSpecLine.getNonPressDetail().getMediaDetail().getMediaLength().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getPressPreinputType() != null)
				{
					presspreinputtype = objBookSpecLine.getNonPressDetail().getMediaDetail().getPressPreinputType();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideColourCode() != null)
				{
					slidecolorcode = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideColourCode();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideLength() != null)
				{
					slidelength = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideLength().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideMountMaterial() != null)
				{
					slidemountmaterial = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideMountMaterial();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlidePagesPerSet() != null)
				{
					slidepagesperset = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlidePagesPerSet().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideSeqNo() != null)
				{
					slideseqnumber = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideSeqNo().toString();
				}
				if (objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideWidthUomId() != null)
				{
					slidewidthuomid = objBookSpecLine.getNonPressDetail().getMediaDetail().getSlideWidthUomId().toString();
				}

			}

			// Chunk chunk = new Chunk(lineno + "."
			// +productDescription,objFont);
			// chunk.setBackground(new Color(230, 240, 250));
			// document.add(chunk);
			objTable = new PdfPTable(4);
			objTable.getDefaultCell().setPadding(3);
			//objTable.setPadding(2);
			//objTable.setSpacing(0);
			PdfPCell bvscell = new PdfPCell();
			objTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			objTable.setWidthPercentage(90f);
			bvscell.setColspan(4);
		
			bvscell = new PdfPCell(new Phrase("MASTER STORAGE OPTION:" + "\n"+ "CDR TYPE::" + "\n" + "LABEL COLOR DESC:" + "\n"
					+ "NUMBER OF COLORS:" + "\n" + "PRINTING:" + "\n"+ "SLIDE COUNTER DESC:" + "\n" + "SLIDE LENGTH UOMID:"
					+ "\n" + "SLIDE MOUNT MATERIAL DESC:" + "\n"+ "SLIDE PER PAGES:" + "\n" + "SLIDE WIDTH:" + "\n"
					+ "WRAP TYPE:", objFont));
			
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase(masterstorageoption + "\n" + cdrtype+ "\n" + labelcolerdesc + "\n" + numberofcolors + "\n"
					+ printing + "\n" + slidecounterdesc + "\n"	+ slidelengthuomid + "\n" + slidemountmaterialdesc + "\n"
					+ slideperpages + "\n" + slidewidth + "\n" + wraptype,objFont));
			
			bvscell.setBackgroundColor(new Color(248, 251, 254));
			
			objTable.addCell(bvscell);
			
			
			bvscell = new PdfPCell(new Phrase("TOTAL UNITS:" + "\n"	+ "FORMAT TYPE:" + "\n" + "MEDIA LENGTH:" + "\n"
					+ "PRESS PRE INPUT TYPE:" + "\n" + "SLIDE COLOR CODE:"+ "\n" + "SLIDE LENGTH:" + "\n" + "SLIDE MOUNT MATERIAL:"
					+ "\n" + "SLIDE PAGES PER SET:" + "\n"+ "SLIDE SEQ NUMBER:" + "\n" + "SLIDE WIDTH UOMID:",objFont));
			bvscell.setBackgroundColor(new Color(236, 245, 253));
			
			
			objTable.addCell(bvscell);
			
			bvscell = new PdfPCell(new Phrase(totalunits + "\n" + formattype + "\n"	+ medialength + "\n" + presspreinputtype + "\n"
					+ slidecolorcode + "\n" + slidelength + "\n"+ slidemountmaterial + "\n" + slidepagesperset + "\n"
					+ slideseqnumber + "\n" + slidewidthuomid, objFont));
			bvscell.setBackgroundColor(new Color(248, 251, 254));
			
			objTable.addCell(bvscell);

		} catch (Exception de) {
			AppException appException = new AppException();
			appException.performErrorAction(
					Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return objTable;

	}

	// This is miscellinious Details of NonPress
	private PdfPTable NonPressMiscLineDetails(Font objFont,BookSpecForm objBookSpecForm, BookSpecLine objBookSpecLine) throws AppException
	{
		PdfPTable miscTable = null;
		BookSpecMisc objBookSpecMisc = null;
		
		String label = "", value = "";
		try
		{
			miscTable = new PdfPTable(4);
			//miscTable.setPadding(1);
			//miscTable.setSpacing(0);
			PdfPCell  miscCell = new PdfPCell();
			PdfPCell colspaceCell = new PdfPCell();
			miscTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			miscTable.setWidthPercentage(90.0f);
			miscCell.setColspan(2);
			
					if (objBookSpecLine.getNonPressDetail() != null)
					{
						if (objBookSpecLine.getMiscCollection().size() != 0)
						{
							int size = objBookSpecLine.getMiscCollection().size();
							for (int i = 0; i < objBookSpecLine.getMiscCollection().size(); i++)
							{
								value=null;
								objBookSpecMisc = (BookSpecMisc) ((Vector) objBookSpecLine.getMiscCollection()).get(i);
								if (objBookSpecMisc.getLabel() != null)
								{
									label = objBookSpecMisc.getLabel().toUpperCase();
								}
								if (objBookSpecMisc.getValue() != null)
								{
									value = objBookSpecMisc.getValue();
								}
								if(i%2 == 0)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									if(i == (size - 1))
									{
										colspaceCell.setColspan(2);
										colspaceCell.setBackgroundColor(new Color(248, 251, 254));
										miscTable.addCell(colspaceCell);
									}
								}
								else if(i%2 == 1)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
								}
							}

						}
					}
				
		

		} catch (Exception de) {
			AppException appException = new AppException();
			appException.performErrorAction(
					Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return miscTable;

	}

//	 This is miscellinious Details of NonPress
	private PdfPTable MisccollectionDetails(Font objFont,BookSpecForm objBookSpecForm,LinkedHashMap hmproduct) throws AppException
	{
		PdfPTable miscTable = null;
		BookSpecMisc objBookSpecMisc = null;
		BookSpecLine objBookSpecLine = null;
		String label = "", value = "";
		try
		{
			//System.out.println("Here");
			miscTable = new PdfPTable(4);
			//miscTable.setPadding(1);
			//miscTable.setSpacing(0);
			PdfPCell miscCell = new PdfPCell();
			PdfPCell colspaceCell = new PdfPCell();
			miscTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			miscTable.setWidthPercentage(90f);
			miscCell.setColspan(2);
			//System.out.println("size:"+objBookSpecForm.getBookSpec().getLineItemCollection().size());
				for (int i = 0; i < objBookSpecForm.getBookSpec().getLineItemCollection().size(); i++)
				{
					//System.out.println(i);
					objBookSpecLine = (BookSpecLine) ((Vector) objBookSpecForm.getBookSpec().getLineItemCollection()).get(i);
					//System.out.println(objBookSpecLine.getPressDetail()+":"+objBookSpecLine.getBindingDetail()+":"+objBookSpecLine.getNonPressDetail());
				     if(objBookSpecLine.getBindingDetail() == null && objBookSpecLine.getPressDetail() == null && objBookSpecLine.getNonPressDetail() == null)
				     {
				    	 //System.out.println("Misc:"+objBookSpecLine.getMiscCollection().size());
			         	if (objBookSpecLine.getMiscCollection().size() != 0)
			         	{
			         		int size = objBookSpecLine.getMiscCollection().size();
			         		//System.out.println("Book:"+objBookSpecLine.getMiscCollection().size());
							for (int j = 0; j < objBookSpecLine.getMiscCollection().size(); j++)
							{
								value=null;
								objBookSpecMisc = (BookSpecMisc) ((Vector) objBookSpecLine.getMiscCollection()).get(j);
							/*	if(objBookSpecMisc.getValue() != hmproduct.get(objBookSpecMisc.getLabel()))
								{
								 								
									if (objBookSpecMisc.getLabel() != null)
									{
										label = objBookSpecMisc.getLabel().toUpperCase();
									}
									if (objBookSpecMisc.getValue() != null)
									{
										value = objBookSpecMisc.getValue();
									}
								}
								else
								{ */
									if (objBookSpecMisc.getLabel() != null)
									{
										label = objBookSpecMisc.getLabel().toUpperCase();
									}
									if (objBookSpecMisc.getValue() != null)
									{
										value = objBookSpecMisc.getValue();
									}
									
							//	}
								if(j%2 == 0)
								{
									
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									if(j == (size - 1))
									{
										colspaceCell.setColspan(2);
										colspaceCell.setBackgroundColor(new Color(248, 251, 254));
										miscTable.addCell(colspaceCell);
									}
								}
								else if(j%2 == 1)
								{
									miscCell = new PdfPCell(new Phrase(label, objFont));
									miscCell.setBackgroundColor(new Color(230, 240,250));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
									miscCell = new PdfPCell(new Phrase(value, objFont));
									miscCell.setBackgroundColor(new Color(248, 251, 254));
									miscCell.setPadding(3);
									miscTable.addCell(miscCell);
								}
							}
					}
				}
			}
				
		} catch (Exception de) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"BookSpecificationPdf,display", de);
			throw appException;
		}
		return miscTable;
	}	
	
	
	
}
