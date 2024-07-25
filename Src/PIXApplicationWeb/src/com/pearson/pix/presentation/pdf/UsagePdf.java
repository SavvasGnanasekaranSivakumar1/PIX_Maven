package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.dto.usage.UsageLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.usage.action.UsageForm;

public class UsagePdf implements CommonPdfInterface
{
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
		        
		        float[] columnDefinitionSize = {33.30F, 33.30F, 33.30F, 33.30f,33.30f,33.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	            POLine poLine = null;
	            UsageForm objUsageForm;
	            objUsageForm = (UsageForm)session.getAttribute("usageForm");
	            document.open();
	            heading.add(new Phrase("Usage - PO No. " + objUsageForm.getPoHeader().getPoNo() + "-   "  +  "Usage-No -  "   +objUsageForm.getUsage().getUsageNo(),objFont));
				heading.setSpacingAfter(10f);
				document.add(heading);
			/*	Paragraph objParagraph  = new Paragraph();
				objParagraph.add(new Phrase("Following are the Usage Details." ,objFont));
	            document.add(objParagraph); */
				Paragraph heading1 = new Paragraph();
				heading1.add(new Phrase("Title:   " +objUsageForm.getPoHeader().getTitleDesc(),objFont));
				heading1.setSpacingAfter(10f);		
				document.add(heading1);
						
			  // This is for the First table.	
			    Table table = Header(objFont,objUsageForm,width,columnDefinitionSize);
				document.add(table);
						 
			    Table bvrDetails = BVRDetails(objFont,objUsageForm);
			    document.add(bvrDetails);
			   /* Chunk lineHeading = new Chunk("Line Item Details",objFont);
		        document.add(lineHeading); */
		        Table lineDetails = LineItemDetails(objUsageForm,objFont);
	            document.add(lineDetails);
	            document.close();
		 }
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "UsagePdf,display",de);
				   throw appException;
			}
			catch(Exception e)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.IO_EXCEPTION, 
			                "UsagePdf,display",e);
				   throw appException;
			}
       
         // step 5: we close the document (the outputstream is also closed internally)
        
        
         return document;
     }

	
    private Table Header(Font objFont,UsageForm objUsageForm,float width,float[] columnDefinitionSize) throws AppException
	{

		Table objTable = null;
		 try
		 {
		    Date objrequestedDeliveryDate = null;
			String objrequestedDeliveryDate_String = new String();
			POParty buyer = null;
			POParty poParty = null;
			
			String deliverydate = "";
			for(int i=0;i<objUsageForm.getPoHeader().getPartyCollection().size();i++)
			{
			    poParty = (POParty)((Vector)objUsageForm.getPoHeader().getPartyCollection()).get(i);
			 		 
			  if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
			  {  
				
				 buyer = poParty;
				 if(buyer.getDeliveryDate()!= null)
				 {
					 deliverydate = buyer.getDeliveryDate();
				 }
			  }
			}  
			if((objrequestedDeliveryDate = (Date)objUsageForm.getPoHeader().getIssueDate())!= null)
            {
 			  
 			  Calendar calendar = new GregorianCalendar(Locale.US);
 			  calendar.setTime(objrequestedDeliveryDate);
 			  objrequestedDeliveryDate_String = (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);
        	} 
			objTable = new Table(6);
			objTable.setPadding(2);
			objTable.setSpacing(0);
		   
		    Cell ncell = new Cell();
		    objTable.setAlignment(Element.ALIGN_LEFT);
		    objTable.setWidth(90f);
		    ncell = new Cell(new Phrase("ISBN 10:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(objUsageForm.getPoHeader().getTitleDetail().getIsbn10(),objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("ISSUE DATE:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(objrequestedDeliveryDate_String,objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("PRINT NO:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(objUsageForm.getPoHeader().getTitleDetail().getPrintNo(),objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("ISBN 13:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(objUsageForm.getPoHeader().getTitleDetail().getIsbn13(),objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("DELIVERY DATE:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(deliverydate,objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("JOB NO:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(objUsageForm.getPoHeader().getJobNo(),objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
			
		
	}
		catch(Exception de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.EXCEPTION,"UsagePdf,display",de);
			   throw appException;
		}
		
	   return objTable;	
	}	
	
    private Table BVRDetails(Font objFont,UsageForm objUsageForm) throws AppException
    {
    	Table objTable = null;
    	Vector partycontactCollection = null;
    	POParty buyer = null;
    	POParty vendor = null;
    	POParty shipTo = null;
    	POPartyContact buyerContact = null;
    	POPartyContact vendorContact = null;
    	POPartyContact shipToContact = null;
    	POParty poParty = null;
    	
    	String buyername1 = "",buyername2 = "",buyername3= "",buyerorgunitname = "",buyeraddress1 = "",buyeraddress2 = "",buyeraddress3 = "",buyeraddress4 = "";
    	String buyercity = "",buyerpostalcode = "",buyerstate = "",buyercountryname = "",buyersan="";
    	
    	String vendorname1 = "",vendorname2 = "",vendorname3= "",vendororgunitname = "",vendoraddress1 = "",vendoraddress2 = "",vendoraddress3 = "",vendoraddress4 = "";
    	String vendorcity = "",vendorpostalcode = "",vendorstate = "",vendorcountryname = "",vendorsan ="";
    	
    	String shiptoname1 = "",shiptoname2 = "",shiptoname3= "",shiptoorgunitname = "",shiptoaddress1 = "",shiptoaddress2 = "",shiptoaddress3 = "",shiptoaddress4 = "";
    	String shiptocity = "",shiptopostalcode = "",shiptostate = "",shiptocountryname = "";
    		
    	String buyercontactfirstname = "",buyercontactlastname = "",buyerphone = "",buyermobile = "",buyerfax = "",buyeremail = ""; 
    	String vendorcontactfirstname = "",vendorcontactlastname = "",vendorphone = "",vendormobile = "",vendorfax = "",vendoremail = "";
    	String shiptocontactfirstname = "",shiptocontactlastname = "",shiptophone = "",shiptomobile = "",shiptofax = "",shiptoemail = "",shipTosan="";
    	
    	
    	try
    	{
    		
    		for(int i=0;i<objUsageForm.getPoHeader().getPartyCollection().size();i++)
    		{
    		    poParty = (POParty)((Vector)objUsageForm.getPoHeader().getPartyCollection()).get(i);
    		 		 
    		 
    		  if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
    		  {  
    			
    			 buyer = poParty;
    			 if(buyer.getSan() != null)
    		     {
    		    	 buyersan = buyer.getSan();
    		     }
    			 if(buyer.getName1()!= null)
    			 {
    				 buyername1 = buyer.getName1();
    			 }
    			 if(buyer.getName2()!= null)
    			 {
    				 buyername2 = buyer.getName2();
    			 }
    			 if(buyer.getName3()!= null)
    			 {
    				 buyername3 = buyer.getName3();
    			 }
    			 if(buyer.getOrgUnitName()!= null)
    			 {
    				 buyerorgunitname = buyer.getOrgUnitName();
    			 }
    			 if(buyer.getAddress1()!= null)
    			 {
    				 buyeraddress1 = buyer.getAddress1();
    			 }
    			 if(buyer.getAddress2()!= null)
    			 {
    				 buyeraddress2 = buyer.getAddress2();
    			 }
    			 if(buyer.getAddress3()!= null)
    			 {
    				 buyeraddress3 = buyer.getAddress3();
    			 }
    			 if(buyer.getAddress4()!= null)
    			 {
    				 buyeraddress4 = buyer.getAddress4();
    			 }
    			 if(buyer.getCity()!= null)
    			 {
    				 buyercity = buyer.getCity();
    			 }
    			 if(buyer.getPostalCode()!= null)
    			 {
    				 buyerpostalcode = buyer.getPostalCode();
    			 }
    			 if(buyer.getState() != null)
    			 {
    				 buyerstate = buyer.getState(); 
    			 }
    			 if(buyer.getCountryDetail().getCountryName() != null)
    			 {
    				 buyercountryname = buyer.getCountryDetail().getCountryName();
    			 }
    			 
    			 partycontactCollection = (Vector)buyer.getContactCollection();
    			 for(int j=0;j<partycontactCollection.size();j++)
    			 {
    				 POPartyContact popartyContact = ((POPartyContact)partycontactCollection.get(j)); 
    				 if(popartyContact.getOrderNo().intValue()==1)
    				 {
    					 buyerContact = popartyContact;
    					 if(buyerContact.getContactFirstName() != null)
    					 {
    						 buyercontactfirstname = buyerContact.getContactFirstName();
    					 }
    					 if(buyerContact.getContactLastName() != null)
    					 {
    						 buyercontactlastname = buyerContact.getContactLastName(); 
    					 }
    					 if(buyerContact.getPhone() != null)
    					 {
    						 buyerphone = buyerContact.getPhone()+"(Business)";
    					 }
    					 if(buyerContact.getMobile() != null)
    					 {
    						 buyermobile = buyerContact.getMobile()+"(Mobile)";
    					 }
    					 if(buyerContact.getFax() != null)
    					 {
    						 buyerfax = buyerContact.getFax()+"(Fax)";
    					 }
    					 if(buyerContact.getEmail() != null)
    					 {
    						 buyeremail = buyerContact.getEmail();
    					 }
    				 }
    			 }
    		 }
    		 if(poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
    		 {        
    			 
    			 vendor = poParty;
    			 if(vendor.getSan() != null)
    		     {
    		    	 vendorsan = vendor.getSan();
    		     }
    			 if(vendor.getName1()!= null)
    			 {
    				 vendorname1 = vendor.getName1();
    			 }
    			 if(vendor.getName2()!= null)
    			 {
    				 vendorname2 = vendor.getName2();
    			 }
    			 if(vendor.getName3()!= null)
    			 {
    				 vendorname3 = vendor.getName3();
    			 }
    			 if(vendor.getOrgUnitName()!= null)
    			 {
    				 vendororgunitname = vendor.getOrgUnitName();
    			 }
    			 if(vendor.getAddress1()!= null)
    			 {
    				 vendoraddress1 = vendor.getAddress1();
    			 }
    			 if(vendor.getAddress2()!= null)
    			 {
    				 vendoraddress2 = vendor.getAddress2();
    			 }
    			 if(vendor.getAddress3()!= null)
    			 {
    				 vendoraddress3 = vendor.getAddress3();
    			 }
    			 if(vendor.getAddress4()!= null)
    			 {
    				 vendoraddress4 = vendor.getAddress4();
    			 }
    			 if(vendor.getCity()!= null)
    			 {
    				 vendorcity = vendor.getCity();
    			 }
    			 if(vendor.getPostalCode()!= null)
    			 {
    				 vendorpostalcode = vendor.getPostalCode();
    			 }
    			 if(vendor.getState() != null)
    			 {
    				 vendorstate = vendor.getState(); 
    			 }
    			 if(vendor.getCountryDetail().getCountryName() != null)
    			 {
    				 vendorcountryname = vendor.getCountryDetail().getCountryName();
    			 }
    			 
    			 partycontactCollection = (Vector)poParty.getContactCollection();
    			 for(int j=0;j<partycontactCollection.size();j++)
    			 {
    				 POPartyContact popartyContact = ((POPartyContact)partycontactCollection.get(j)); 
    				 if(popartyContact.getOrderNo().intValue()==1)
    				 {
    					 
    					 vendorContact = popartyContact;
    					 if(vendorContact.getContactFirstName() != null)
    					 {
    						 vendorcontactlastname = vendorContact.getContactFirstName();
    					 }
    					 if(vendorContact.getContactLastName() != null)
    					 {
    						 vendorcontactlastname = vendorContact.getContactLastName(); 
    					 }
    					 if(vendorContact.getPhone() != null)
    					 {
    						 vendorphone = vendorContact.getPhone()+"(Business)";
    					 }
    					 if(vendorContact.getMobile() != null)
    					 {
    						 vendormobile = vendorContact.getMobile()+"(Mobile)";
    					 }
    					 if(vendorContact.getFax() != null)
    					 {
    						 vendorfax = vendorContact.getFax()+"(Fax)";
    					 }
    					 if(vendorContact.getEmail() != null)
    					 {
    						 vendoremail = vendorContact.getEmail();
    					 }
    				 }
    			 }
    		 }
    		 if(poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
    		 {        
    			 
    			 shipTo = poParty;
    			 if(shipTo.getSan() != null)
    		     {
    		    	 shipTosan = shipTo.getSan();
    		     }
    			 if(shipTo.getName1()!= null)
    			 {
    				 shiptoname1 = shipTo.getName1();
    			 }
    			 if(shipTo.getName2()!= null)
    			 {
    				 shiptoname2 = shipTo.getName2();
    			 }
    			 if(shipTo.getName3()!= null)
    			 {
    				 shiptoname3 = shipTo.getName3();
    			 }
    			 if(shipTo.getOrgUnitName()!= null)
    			 {
    				 shiptoorgunitname = shipTo.getOrgUnitName();
    			 }
    			 if(shipTo.getAddress1()!= null)
    			 {
    				 shiptoaddress1 = shipTo.getAddress1();
    			 }
    			 if(shipTo.getAddress2()!= null)
    			 {
    				 shiptoaddress2 = shipTo.getAddress2();
    			 }
    			 if(shipTo.getAddress3()!= null)
    			 {
    				 shiptoaddress3 = shipTo.getAddress3();
    			 }
    			 if(shipTo.getAddress4()!= null)
    			 {
    				 shiptoaddress4 = shipTo.getAddress4();
    			 }
    			 if(shipTo.getCity()!= null)
    			 {
    				 shiptocity = shipTo.getCity();
    			 }
    			 if(shipTo.getPostalCode()!= null)
    			 {
    				 shiptopostalcode = shipTo.getPostalCode();
    			 }
    			 if(shipTo.getState() != null)
    			 {
    				 shiptostate = shipTo.getState(); 
    			 }
    			 if(shipTo.getCountryDetail().getCountryName() != null)
    			 {
    				 shiptocountryname = shipTo.getCountryDetail().getCountryName();
    			 } 
    			 
    			 partycontactCollection = (Vector)poParty.getContactCollection();
    			 for(int j=0;j<partycontactCollection.size();j++)
    			 {
    				 POPartyContact popartyContact = ((POPartyContact)partycontactCollection.get(j)); 
    				 if(popartyContact.getOrderNo().intValue()==1)
    				 {
    					 shipToContact = popartyContact;
    					 if(shipToContact.getContactFirstName() != null)
    					 {
    						 shiptocontactlastname = shipToContact.getContactFirstName();
    					 }
    					 if(shipToContact.getContactLastName() != null)
    					 {
    						 shiptocontactlastname = shipToContact.getContactLastName(); 
    					 }
    					 if(shipToContact.getPhone() != null)
    					 {
    						 shiptophone = shipToContact.getPhone()+"(Business)";
    					 }
    					 if(shipToContact.getMobile() != null)
    					 {
    						 shiptomobile = shipToContact.getMobile()+"(Mobile)";
    					 }
    					 if(shipToContact.getFax() != null)
    					 {
    						 shiptofax = shipToContact.getFax()+"(Fax)";
    					 }
    					 if(shipToContact.getEmail() != null)
    					 {
    						 shiptoemail = shipToContact.getEmail();
    					 }
    				 }
    			 }
    		 }
    	  }
        	objTable = new Table(3);
    		objTable.setPadding(2);
    		objTable.setSpacing(0);
    		Cell bvscell = new Cell();
    		objTable.setAlignment(Element.ALIGN_LEFT);
    	    objTable.setWidth(90f);
    		bvscell.setColspan(3);
    		bvscell.setRowspan(1);
    		bvscell.setBackgroundColor(new Color(236, 245, 253));
    		bvscell = new Cell(new Phrase("BUYER"+ "("+ buyersan + ")",objFont));
    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
    		bvscell.setBackgroundColor(new Color(236, 245, 253));
    		objTable.addCell(bvscell);
    		bvscell = new Cell(new Phrase("VENDOR"+ "("+ vendorsan + ")",objFont));
    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
    		bvscell.setBackgroundColor(new Color(236, 245, 253));
    		objTable.addCell(bvscell);
    		bvscell = new Cell(new Phrase("SHIP TO"+ "("+ shipTosan + ")",objFont));
    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
    		bvscell.setBackgroundColor(new Color(236, 245, 253));
    		objTable.addCell(bvscell);
    		
    		bvscell = new Cell(new Phrase(buyername1+ "\n" + buyername2 + "\n" + buyername3 + "\n" + buyeraddress1 + " \n" + buyeraddress2 + " " + buyeraddress3+ "\n" +
    				   " "+buyeraddress4+ "\n" +buyercity+ "\n" +buyerpostalcode+ "" +buyerstate+ " " + buyercountryname+"\n" + "-" +
    				   " "+buyercontactfirstname+ " " +buyercontactlastname+" \n" +
    				   " "+buyerphone+"\n" +
    					" "+buyermobile+"\n" + 
    				    " "+buyerfax+"\n" +
    				    " "+buyeremail+" ",objFont));  
    		 	objTable.addCell(bvscell);
    		bvscell = new Cell(new Phrase(vendorname1+ "\n" + vendorname2 + "\n" + vendorname3 + "\n" + vendoraddress1 + " \n" + vendoraddress2 + " " + vendoraddress3+ "\n" +
    				   " "+vendoraddress4+ "\n" +vendorcity+ "\n" +vendorpostalcode+ "," +vendorstate+ " " + vendorcountryname+"\n" + "-" +
    				   " "+vendorcontactfirstname+ " " +vendorcontactlastname+" \n" +
    				   " "+vendorphone+"\n" +
    				   " "+vendormobile+"\n" + 
    			       " "+vendorfax+"\n" + 
    			       " "+vendoremail+" ",objFont)); 
    			objTable.addCell(bvscell);
    	   bvscell = new Cell(new Phrase(shiptoname1+ "\n" + shiptoname2 + "\n" + shiptoname3 + "\n" +shiptoaddress1 + " \n" + shiptoaddress2 + " " + shiptoaddress3+ "\n" +
    				   " "+shiptoaddress4+ "\n" +shiptocity+ "\n" +shiptopostalcode+ "," +shiptostate+ " " + shiptocountryname+"\n" + "-" +
    				   " "+shiptocontactfirstname+ " "+shiptocontactlastname+" \n" +
    				   " "+shiptophone+" \n" +
    				   " "+shiptomobile+"  \n" + 
    				   " "+shiptofax+" \n" +
    				   " "+shiptoemail+" ",objFont));  
    	  objTable.addCell(bvscell);
    		 
    	}		
    		
    	catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "DeliveryMessagePdf,display",de);
			   throw appException;
		}
    	 return objTable;
    }	
    
    private Table LineItemDetails(UsageForm objUsageForm,Font objFont) throws AppException
	{
		Table objTable = null;
		try
		{
			objTable = new Table(7);
			
			objTable.setPadding(1);
			objTable.setSpacing(0);	
	        int headerwidths[] = {2,8,8,8,8,8,10};
	        objTable.setWidths(headerwidths);
	        objTable.setAlignment(Element.ALIGN_LEFT);
	        objTable.setWidth(90f);	
	        objTable.setDefaultCellBorderWidth(1);
	        objTable.setDefaultRowspan(1);
	        Cell datatablecell = new Cell();
	        UsageLine objUsageLine = null;
	              
	              datatablecell = new Cell(new Phrase("",objFont));
	              datatablecell.setBackgroundColor(new Color(171,199,227));
			      objTable.addCell(datatablecell);
			      datatablecell = new Cell(new Phrase("PRODUCT ID",objFont));
	              datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
	              datatablecell = new Cell(new Phrase("SUPPLEMENT COMPONENT",objFont));
	              datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("COMPONENT",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("USAGE QUANTITY",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("DAMAGED QUANTITY",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("COMMENTS",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          objTable.setBackgroundColor(new Color(171,199,227));
		          objTable.setDefaultCellBorderWidth(1);
		          objTable.setDefaultRowspan(1);
		          int size = objUsageForm.getUsage().getUsageLineCollection().size();
		          for (int j =0;j < size; j++)
	              {
	            	  objUsageLine = (UsageLine)((Vector)objUsageForm.getUsage().getUsageLineCollection()).get(j);
	            	  
	            	  Integer lineno = new Integer(j+1);
			          if((lineno.intValue())%2 == 0)
	            	  {
			        	  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				        	 
		                  datatablecell.setRowspan(1);
		                  datatablecell = new Cell(new Phrase(lineno.toString(),objFont));
		                  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		                  objTable.addCell(datatablecell);
		                  datatablecell = new Cell(new Phrase(objUsageLine.getProductCode().toString(),objFont));
		                  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		                  objTable.addCell(datatablecell);
		                  datatablecell = new Cell(new Phrase(objUsageLine.getProductDescription(),objFont));
		                  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		                  objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getProductClassifDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getUsageQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getDamagedQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getComments(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
	            	  }
	            	  else if((lineno.intValue())%2 != 0)
	            	  {
	            		  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		                  datatablecell.setRowspan(1);
		                  datatablecell = new Cell(new Phrase(lineno.toString(),objFont));
		                  datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getProductCode().toString(),objFont));
		                  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		                  objTable.addCell(datatablecell);
		                  datatablecell = new Cell(new Phrase(objUsageLine.getProductDescription(),objFont));
		                  datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getProductClassifDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getUsageQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getDamagedQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          datatablecell = new Cell(new Phrase(objUsageLine.getComments(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
	            	  }
	             }
		         
		}
		catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "UsagePdf,display",de);
			   throw appException;
		}
		catch(Exception ae)
		{
			 ae.printStackTrace();
		}
		return objTable;
		
	}
}
