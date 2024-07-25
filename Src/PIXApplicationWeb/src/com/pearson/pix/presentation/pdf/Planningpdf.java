package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.io.IOException;
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
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;

public class Planningpdf implements CommonPdfInterface{
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
				
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				float[] columnDefinitionSize = {13.30F, 13.30F, 13.30F, 13.30f,13.30f,13.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	            POLine poLine = null;
	            PurchaseOrderDetailForm poDetailForm;
	            poDetailForm = (PurchaseOrderDetailForm)session.getAttribute("orderDetailForm");
	            document.open();
	            if (poDetailForm.getPoHeader() != null )
	            {
	            	if (poDetailForm.getPoHeader().getPoNo() != null && poDetailForm.getPoHeader().getReleaseNo() != null){
	            		heading.add(new Phrase("Planning No. " + poDetailForm.getPoHeader().getPoNo() +"-" +poDetailForm.getPoHeader().getReleaseNo(),objFont));
	            	}
	            		heading.setSpacingAfter(10f);
	            		document.add(heading);
				Paragraph heading1 = new Paragraph();
				if(poDetailForm.getPoHeader().getTitleDesc() != null){
				heading1.add(new Phrase("Title:  " +poDetailForm.getPoHeader().getTitleDesc(),objFont));
				}
				heading1.setSpacingAfter(10f);		
				document.add(heading1);
	            }
						
			// This method is for Header table.	
			    Table table = Header(objFont,poDetailForm,width,columnDefinitionSize);
				document.add(table);
				
			 // This method is for status table.
				Table statustable = StatusTable(objFont,poDetailForm);
			    document.add(statustable);
		     
			    Table bvrDetails = BVRDetails(objFont,poDetailForm);
			    document.add(bvrDetails);
                PdfPTable objLineItems = null;
			    if(poDetailForm.getPoHeader().getLineItemCollection().size() != 0)
			    {	
			    	int sizem=poDetailForm.getPoHeader().getLineItemCollection().size();
			        for(int i=0;i<sizem;i++)
					{
					   	poLine = (POLine)((Vector)poDetailForm.getPoHeader().getLineItemCollection()).get(i);
					   				   
				// This method is for Line Item Details of the Purchase Order.	
				    objLineItems = LineItems(objFont,poDetailForm,poLine);
				    Chunk chunk = new Chunk((i+1) + "." +poLine.getProductDescription(),objFont);
				    chunk.setBackground(new Color(171,199,227));
				    document.add(chunk);
				    objLineItems.setSplitLate(false);
				    objLineItems.setSpacingAfter(10f);
				    document.add(objLineItems);
				    
				   }	
			       
	            }
			    if(poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTermsConditions() != null)
	            {
				    Paragraph p = new Paragraph();
	                p.add(new Phrase("TERMS & CONDITIONS :",objFont));
	                document.add(p);
	                Paragraph p1 = new Paragraph();
	                p1.add(new Phrase(poDetailForm.getPoHeader().getTermsConditions(),objFont));
	                document.add(p1);
	            }    
		 }
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "Planningpdf,display",de);
				   throw appException;
			}
			catch(Exception e)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.IO_EXCEPTION, 
			                "Planningpdf,display",e);
				   throw appException;
			}
	          // step 5: we close the document (the outputstream is also closed internally)
			    
	            document.close();
	            return document;
	        }
	  
/**
* Purchase Order Header Table
* @param Font
* @param Formbean
* @return PdfpTable
*/	
private Table Header(Font objFont,PurchaseOrderDetailForm poDetailForm,float width,float[] columnDefinitionSize) throws AppException
{
	
		Table objTable = null;
		 try
		 {
		    Date objrequestedDeliveryDate = null;
			String objrequestedDeliveryDate_String = new String();
			POParty buyer = null;
			POParty poParty = null;
			String statusDescription = "";
			String deliverydate = "";
			int sizeh=poDetailForm.getPoHeader().getPartyCollection().size();
			for(int i=0;i<sizeh;i++)
			{
			    poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
			 		 
			 
			  if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
			  {  
				
				 buyer = poParty;
				 if(buyer.getDeliveryDate()!= null)
				 {
					 deliverydate = buyer.getDeliveryDate();
				 }
			  }
			}  
			if((objrequestedDeliveryDate = (Date)poDetailForm.getPoHeader().getIssueDate())!= null)
            {
 			  
 			  Calendar calendar = new GregorianCalendar(Locale.US);
 			  calendar.setTime(objrequestedDeliveryDate);
 			  objrequestedDeliveryDate_String = (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);
        	} 
			if(poDetailForm.getPlanningAllStatus() != null)
			{
			   for(int i=0;i<poDetailForm.getPlanningAllStatus().size();i++)
			   {
			    	Status status = (Status)((Vector)poDetailForm.getPlanningAllStatus()).get(i);
			    	if(status.getStatusId()!=null && buyer.getStatusId()!=null){
			    		if(status.getStatusId().intValue() == buyer.getStatusId().intValue())
					    {
						    statusDescription = status.getStatusDescription();
					    }
			    	}
				    
					
				}
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
		    if (poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getIsbn10() != null){
		    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTitleDetail().getIsbn10(),objFont));
		    }
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
		    if (poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getPrintNo() != null){
		    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTitleDetail().getPrintNo(),objFont));
		    }
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("ISBN 13:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    if (poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getIsbn13() != null){
		    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTitleDetail().getIsbn13(),objFont));
		    }
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("DELIVERY DATE:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(deliverydate,objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase("STATUS:",objFont));
		    ncell.setBackgroundColor(new Color(236, 245, 253));
		    objTable.addCell(ncell);
		    ncell = new Cell(new Phrase(statusDescription,objFont));
		    ncell.setBackgroundColor(new Color(248, 251, 254));
		    objTable.addCell(ncell);
			
		
	}
	    catch(Exception de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.EXCEPTION, 
		                "Planningpdf,display",de);
			   throw appException;
		}
	
   return objTable;	
}
/**
* This method is used for Purchase Order status Table
* @param Font
* @param Formbean
* @return Table
*/	
private Table StatusTable(Font objFont,PurchaseOrderDetailForm poDetailForm) throws AppException
{
	 Table objTable = null;
	 try
	 {
		objTable = new Table(4);
		objTable.setPadding(2);
		objTable.setSpacing(0);
	    POParty poParty = null;
	    Cell ncell = new Cell();
	    objTable.setAlignment(Element.ALIGN_LEFT);
	    objTable.setWidth(90f);
	    int sizes=poDetailForm.getPoHeader().getPartyCollection().size(); 
		for(int i=0;i<sizes;i++){
		   	 poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
		   	if (poParty != null){
		   	if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
			{        
	            ncell = new Cell(new Phrase("BUYER NOTES:",objFont));
	            ncell.setBackgroundColor(new Color(239, 239, 239));
	            objTable.addCell(ncell);
	            ncell = new Cell(new Phrase(poParty.getComments().replaceAll("<br>","\n"),objFont));
	            ncell.setBackgroundColor(new Color(249, 249, 249));
	            ncell.setColspan(3);
	            objTable.addCell(ncell);
			}   
		    if(poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
		    {
	            ncell = new Cell(new Phrase("YOUR COMMENTS:",objFont));
	            ncell.setBackgroundColor(new Color(239, 239, 239));
	            objTable.addCell(ncell);
	            ncell = new Cell(new Phrase(poParty.getComments(),objFont));
	            ncell.setBackgroundColor(new Color(249, 249, 249));
	            ncell.setColspan(3);
	            objTable.addCell(ncell);
		    }    
		
		}
		}
		objTable.setBottom(10f);
	}
	 catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "Planningpdf,display",de);
			   throw appException;
		}
	 return objTable;	
	
}
/**
* This method is used for the details of the Buyer Vendor and Shipto Details.
* @param Font
* @param Formbean
* @return Table
*/	
private Table BVRDetails(Font objFont,PurchaseOrderDetailForm poDetailForm) throws AppException
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
	String buyerStr="";
	String vendorStr="";
	String shiptoStr="";
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
		int sizeBVR=poDetailForm.getPoHeader().getPartyCollection().size();
		for(int i=0;i<sizeBVR;i++)
		{
		    poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
		 		 
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
			 if(buyer.getCountryDetail() !=null && buyer.getCountryDetail().getCountryName() != null)
			 {
				 buyercountryname = buyer.getCountryDetail().getCountryName();
			 }
			 
			 
			 if(buyername1!=""|| buyername2!=""|| buyername3!="")
			 {
				 buyerStr=buyername1+" " +buyername2+" "+buyername3;
			 }
			 if(buyeraddress1!=""|| buyeraddress2!="")
			 {
				 buyerStr=buyerStr+"\n"+buyeraddress1+" " +buyeraddress2;
			 }
			 if(buyeraddress3!=""|| buyeraddress4!="")
			 {
				 buyerStr=buyerStr+"\n"+buyeraddress3+" " +buyeraddress4;
			 }
			 if(buyercity!=""|| buyerpostalcode!=""||buyerstate!=""||buyercountryname!="")
			 {
				 buyerStr=buyerStr+"\n"+buyercity+" "+buyerpostalcode+" "+buyerstate+" "+buyercountryname;
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
			 if(buyercontactfirstname!=""|| buyercontactlastname!="")
			 {
				 buyerStr=buyerStr+"\n"+buyercontactfirstname+" " +buyercontactlastname;
			 }
			 if(buyerphone!=""|| buyermobile!=""||buyerfax!=""||buyeremail!="")
			 {
				 buyerStr=buyerStr+"\n"+buyerphone+"\n" +buyermobile+"\n"+buyerfax+"\n"+buyeremail;
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
			 if(vendor.getCountryDetail() != null && vendor.getCountryDetail().getCountryName() != null)
			 {
				 vendorcountryname = vendor.getCountryDetail().getCountryName();
			 }
			 
			 
			 if(vendorname1!=""|| vendorname2!=""|| vendorname3!="")
			 {
				 vendorStr=vendorname1+" " +vendorname2+" "+vendorname3;
			 }
			 if(vendoraddress1!=""|| vendoraddress2!="")
			 {
				 vendorStr=vendorStr+"\n"+vendoraddress1+" " +vendoraddress2;
			 }
			 if(vendoraddress3!=""|| vendoraddress4!="")
			 {
				 vendorStr=vendorStr+"\n"+vendoraddress3+"" +vendoraddress4;
			 }
			 if(vendorcity!=""|| vendorpostalcode!=""||vendorstate!=""||vendorcountryname!="")
			 {
				 vendorStr=vendorStr+"\n"+vendorcity+" "+vendorpostalcode+" "+vendorstate+" "+vendorcountryname;
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
						 vendorcontactfirstname = vendorContact.getContactFirstName();
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
			 if(vendorcontactfirstname!=""|| vendorcontactlastname!="")
			 {
				 vendorStr=vendorStr+"\n"+vendorcontactfirstname+"" +vendorcontactlastname;
			 }
			 if(vendorphone!=""|| vendormobile!=""||vendorfax!=""||vendoremail!="")
			 {
				 vendorStr=vendorStr+"\n"+vendorphone+"\n" +vendormobile+"\n"+vendorfax+"\n"+vendoremail;
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
			if(shipTo.getCountryDetail() != null && shipTo.getCountryDetail().getCountryName() != null)
			 {
				 shiptocountryname = shipTo.getCountryDetail().getCountryName();
			 }
			 
			 if(shiptoname1!=""|| shiptoname2!=""|| shiptoname3!="")
			 {
				 shiptoStr=shiptoname1+" " +shiptoname2+" "+shiptoname3;
			 }
			 if(shiptoaddress1!=""|| shiptoaddress2!="")
			 {
				 shiptoStr=shiptoStr+"\n"+shiptoaddress1+" " +shiptoaddress2;
			 }
			 if(shiptoaddress3!=""|| shiptoaddress4!="")
			 {
				 shiptoStr=shiptoStr+"\n"+shiptoaddress3+" " +shiptoaddress4;
			 }
			 if(shiptocity!=""|| shiptopostalcode!=""||shiptostate!=""||shiptocountryname!="")
			 {
				 shiptoStr=shiptoStr+"\n"+shiptocity+" "+shiptopostalcode+" "+shiptostate+" "+shiptocountryname;
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
						 shiptocontactfirstname = shipToContact.getContactFirstName();
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
			 if(shiptocontactfirstname!=""|| shiptocontactlastname!="")
			 {
				 shiptoStr=shiptoStr+"\n"+shiptocontactfirstname+"" +shiptocontactlastname;
			 }
			 if(shiptophone!=""|| shiptomobile!=""||shiptofax!=""||shiptoemail!="")
			 {
				 shiptoStr=shiptoStr+"\n"+shiptophone+"\n" +shiptomobile+"\n"+shiptofax+"\n"+shiptoemail;
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
		
		/*bvscell = new Cell(new Phrase(buyername1+ "\n" + buyername2 + "\n" + buyername3 +  "\n" + buyeraddress1 + " \n" + buyeraddress2 + " " + buyeraddress3+ "\n" +
				   " "+buyeraddress4+ "\n" +buyercity+ "\n" +buyerpostalcode+ "" +buyerstate+ " " + buyercountryname+"\n"+ "-" +
				   " "+buyercontactfirstname+ " " +buyercontactlastname+" \n" +
				   " "+buyerphone+"\n" +
					" "+buyermobile+" \n" + 
				    " "+buyerfax+"\n" +
				    " "+buyeremail+" ",objFont));*/ 
		bvscell = new Cell(new Phrase(buyerStr,objFont));
		
		 	objTable.addCell(bvscell);
		/*bvscell = new Cell(new Phrase(vendorname1+ "\n" + vendorname2 + "\n" + vendorname3 + "\n" + vendoraddress1 + " \n" + vendoraddress2 + " " + vendoraddress3+ "\n" +
				   " "+vendoraddress4+ "\n" +vendorcity+ "\n" +vendorpostalcode+ "," +vendorstate+ " " + vendorcountryname+"\n" + "-" +
				   " "+vendorcontactfirstname+ " " +vendorcontactlastname+" \n" +
				   " "+vendorphone+"\n" +
				   " "+vendormobile+" \n" + 
			       " "+vendorfax+" \n" + 
			       " "+vendoremail+" ",objFont));*/ 
		 	bvscell = new Cell(new Phrase(vendorStr,objFont));
				objTable.addCell(bvscell);
		/*bvscell = new Cell(new Phrase(shiptoname1+ "\n" + shiptoname2 + "\n" + shiptoname3 + "\n" +shiptoaddress1 + " \n" + shiptoaddress2 + " " + shiptoaddress3+ "\n" +
				   " "+shiptoaddress4+ "\n" +shiptocity+ "\n" +shiptopostalcode+ "," +shiptostate+ " " + shiptocountryname+"\n" + "-" +
				   " "+shiptocontactfirstname+ " "+shiptocontactlastname+" \n" +
				   " "+shiptophone+"\n" +
				   " "+shiptomobile+" \n" + 
				   " "+shiptofax+"\n" +
				   " "+shiptoemail+" ",objFont)); */ 
				bvscell = new Cell(new Phrase(shiptoStr,objFont));
	   objTable.addCell(bvscell);
		 
	}		
		
	catch(DocumentException de)
	{
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
	                "Planningpdf,display",de);
		   throw appException;
	}
	 return objTable;
}
/**
* This method is used for the details of the Line Items.
* @param Font
* @param Formbean
* @return Table
*/	
private PdfPTable LineItems(Font objFont,PurchaseOrderDetailForm poDetailForm, POLine poLine) throws AppException
{
	PdfPTable objdatatable = null;
	try
	{
			objdatatable = new PdfPTable(5);
    		//objdatatable.setPadding(1);
    		//objdatatable.setSpacing(0);	
            int headerwidths[] = {10,32,7, 8, 9};
            objdatatable.setWidths(headerwidths);
            objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
            objdatatable.setWidthPercentage(90f);	
           // objdatatable.setDefaultCellBorderWidth(1);
            //objdatatable.setDefaultRowspan(1);
            PdfPCell datatablecell = new PdfPCell();
           
      		  datatablecell = new PdfPCell(new Phrase("PRODUCT ID",objFont));
      		  datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("DESCRIPTION",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("QTY",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("DELIVERY DATE",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("STATUS",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          //objdatatable.setBackgroundColor(new Color(230,242,253));
	     
	         // objdatatable.setDefaultCellBorderWidth(1);
	         
	        //  objdatatable.setDefaultRowspan(1);
     
	          objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
	           	 // objdatatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
	           	 //objdatatable.setDefaultCellBackgroundColor(new Color(248, 251, 254));
                 // datatablecell.setRowspan(1);
	            if (poLine != null){
			          datatablecell = new PdfPCell(new Phrase(poLine.getProductCode(),objFont));
			          datatablecell.setBackgroundColor(new Color(248, 251, 254));
           	     
		          objdatatable.addCell(datatablecell);
		          if(poLine.getLineDecription() != null)
		          {
		        	  datatablecell = new PdfPCell(new Phrase(poLine.getLineDecription().replaceAll("<br>","\n"),objFont));
		        	  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		        	  objdatatable.addCell(datatablecell);
		          }
		          else
		          {
		        	  datatablecell = new PdfPCell(new Phrase("",objFont));
		        	  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		        	  objdatatable.addCell(datatablecell);
		          }
		         if (poLine.getRequestedQuantity() != null){
	              datatablecell = new PdfPCell(new Phrase(poLine.getRequestedQuantity().toString(),objFont));
		         }
		          datatablecell.setBackgroundColor(new Color(248, 251, 254));
	            
		          objdatatable.addCell(datatablecell);
		          if (poLine.getRequestedDeliveryDate() != null){
		          datatablecell = new PdfPCell(new Phrase(poLine.getRequestedDeliveryDate(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          if (poLine.getPubUnitStatusDetail() != null && poLine.getPubUnitStatusDetail().getStatusDescription() != null){
		          datatablecell = new PdfPCell(new Phrase(poLine.getPubUnitStatusDetail().getStatusDescription(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          datatablecell = new PdfPCell(new Phrase("BUYER NOTES:",objFont));
     	          datatablecell.setBackgroundColor(new Color(239,239,239));
     	          objdatatable.addCell(datatablecell);
     	          if (poLine.getPubUnitComments() != null){
     	          datatablecell = new PdfPCell(new Phrase(poLine.getPubUnitComments(),objFont));
     	          }
	     	      datatablecell.setBackgroundColor(new Color(239,239,239));
			     
     			  datatablecell.setColspan(4);
     			  objdatatable.addCell(datatablecell);
     		      datatablecell = new PdfPCell(new Phrase("YOUR COMMENTS/QUERIES:",objFont));
     		      datatablecell.setBackgroundColor(new Color(239,239,239));
     			  objdatatable.addCell(datatablecell);
     			 if (poLine.getSupplierComments() != null){
	     		 datatablecell = new PdfPCell(new Phrase(poLine.getSupplierComments(),objFont));
     			 }
	     		 datatablecell.setBackgroundColor(new Color(239,239,239));
		           
     			  datatablecell.setColspan(4);
     			  objdatatable.addCell(datatablecell);
	       }
	}
	catch(DocumentException de)
	{
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
	                "Planningpdf,display",de);
		   throw appException;
	}
	catch(Exception de)
	{
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.EXCEPTION, 
	                "Planningpdf,display",de);
		   throw appException;
	}
	 return objdatatable;
}


}
