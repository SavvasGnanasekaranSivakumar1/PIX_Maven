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
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POLineParty;
import com.pearson.pix.dto.purchaseorder.POLinePartyContact;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;

import com.pearson.pix.dto.purchaseorder.PriceDetail;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;

public class PurchaseOrderMillPdf implements CommonPdfInterface
{
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		 Font objFont2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
		 
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
		       
		        
		        float[] columnDefinitionSize = {33.30F, 33.30F, 33.30F, 33.30f,33.30f,33.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	            POLine poLine = null;
	            POParty poParty1 = null;
	            PurchaseOrderDetailForm poDetailForm;
	            poDetailForm = (PurchaseOrderDetailForm)session.getAttribute("orderDetailForm");
	            document.open();
	            if (poDetailForm.getPoHeader() != null )
	            {
	            	if (poDetailForm.getPoHeader().getPoNo() != null && poDetailForm.getPoHeader().getReleaseNo() != null && !(poDetailForm.getPoHeader().getReleaseNo().intValue()==0)){
	            		heading.add(new Phrase("Purchase Order Number " + poDetailForm.getPoHeader().getPoNo() +"-" +poDetailForm.getPoHeader().getReleaseNo(),objFont));
	            	}
	            	else
	            	{
	            		heading.add(new Phrase("Purchase Order Number " + poDetailForm.getPoHeader().getPoNo(),objFont));	
	            	}
				heading.setSpacingAfter(10f);
				document.add(heading);
				Paragraph heading1 = new Paragraph();
				if (poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTitleDesc() != null ){
				heading1.add(new Phrase("MATERIAL DESCRIPTION: " +poDetailForm.getPoHeader().getTitleDesc(),objFont));
				}
				heading1.setSpacingAfter(10f);
				document.add(heading1);
	            }
						
			// This is for the First table.	
	            Table table= Header(objFont,poDetailForm,width,columnDefinitionSize);
			    document.add(table);
			  				
			    // This is for second table.
			    Table statustable = StatusTable(objFont,poDetailForm);
			    document.add(statustable);
				Table bvrDetails = BVRDetails(objFont,poDetailForm);
				document.add(bvrDetails);
			    PdfPTable objLineItems = null;
			    
			    
			    if(poDetailForm.getPoHeader().getLineItemCollection().size() != 0)
			    {	
			    //	Table objTable = new Table(3);
			        for(int i=0;i<poDetailForm.getPoHeader().getLineItemCollection().size();i++)
					{
					   	poLine = (POLine)((Vector)poDetailForm.getPoHeader().getLineItemCollection()).get(i);
					   	String desc="";   	  	
				 // This is for Fourth Table.
					   	if(poLine.getProductDescription() != null)
					   		desc = ((i+1) + "." +poLine.getProductClassifDescription());
					   		
				    objLineItems = LineItems(objFont,poDetailForm,poLine,desc,i);
				    objLineItems.setSpacingBefore(15f);
				    document.add(objLineItems);
				        
				    }
			     }
			    		  			    
			    User objUser = null;
			    objUser = (User)req.getSession().getAttribute("USER_INFO");
			    
			   if(poDetailForm.getPoPriceDetails() != null)
			   { 
				   if(poDetailForm.getPocosting().equals("P") || (poDetailForm.getPocosting().equals("F") && objUser != null && objUser.getRoleTypeDetail() != null && objUser.getRoleTypeDetail().getRoleType() != null && objUser.getRoleTypeDetail().getRoleType().equals("B")))
				   {
					   PdfPTable objCostingTable = CostingTable(objFont,poDetailForm,session);
					   objCostingTable.setSplitLate(false);
					   objCostingTable.setSpacingBefore(10f);
					   document.add(objCostingTable);
				   }
			   }
			  //-----------------------------------------------------------------------------
			    User objUser1 = null;
			    objUser1 = (User)req.getSession().getAttribute("USER_INFO");
			   // System.out.println("roletype..."+objUser1.getRoleTypeDetail().getRoleType());
			    if((objUser1.getRoleTypeDetail() != null && objUser1.getRoleTypeDetail().getRoleType() != null && objUser1.getRoleTypeDetail().getRoleType().equals("B"))||(objUser1.getRoleTypeDetail() != null && objUser1.getRoleTypeDetail().getRoleType() != null && objUser1.getRoleTypeDetail().getRoleType().equals("M"))||(objUser1.getRoleTypeDetail() != null && objUser1.getRoleTypeDetail().getRoleType() != null && objUser1.getRoleTypeDetail().getRoleType().equals("C")))
			    {
				   PdfPTable objCostingTableMill = CostingTableForMill(objFont,poDetailForm,session);
				   objCostingTableMill.setSplitLate(false);
				   objCostingTableMill.setSpacingBefore(10f);
				   document.add(objCostingTableMill);
			    }
			   
			   //-------------------------------------------------------------------------------
			   
               if(poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getTermsConditions() != null)
               {	   
	                Paragraph p = new Paragraph();
	                p.add(new Phrase("TERMS & CONDITIONS :",objFont2));
	                document.add(p);
	                Paragraph p1 = new Paragraph();
	                p1.add(new Phrase(poDetailForm.getPoHeader().getTermsConditions(),objFont));
	                p1.setSpacingAfter(10f);
	                document.add(p1);
               } 
               
              
               /*PdfPTable objSuppLineItems = null;
			   POLine poline=null;
			    if(poDetailForm.getPoHeader().getLineItemCollection().size() != 0)
			    {
			    	Chunk chunk = new Chunk("Supplied Components - Order ",objFont2);
				    chunk.setBackground(new Color(171,199,227));
				    document.add(chunk);
			    			    	
					int suppComp=poDetailForm.getPoHeader().getLineItemCollection().size();
					for(int j=0;j<suppComp;j++)
					{	
			        	poline = (POLine)((Vector)poDetailForm.getPoHeader().getLineItemCollection()).get(j);
			        	objSuppLineItems = suppLineItems(objFont,poDetailForm,poline);
								    
				    objSuppLineItems.setSplitLate(false);
				    objSuppLineItems.setSpacingAfter(5f);
				    document.add(objSuppLineItems);
				    
				    }
			    }*/
		 
		 }
		    catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF,"PurchaseOrderPdf,display",de);
				   throw appException;
			}
			catch(Exception e)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.IO_EXCEPTION, 
			                "PurchaseOrderPdf,display",e);
				   throw appException;
			}
	            // step 5: we close the document (the outputstream is also closed internally)
	           
	            document.close();
	            return document;
	        }
	  
/**
* This method is used Header details.
* @param Font
* @param PurchaseOrderDetailForm
* @return Table
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
		for(int i=0;i<poDetailForm.getPoHeader().getPartyCollection().size();i++)
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
		for(int i=0;i<poDetailForm.getPoAllHeaderStatus().size();i++)
		{
			Status status = (Status)((Vector)poDetailForm.getPoAllHeaderStatus()).get(i);
			if(status.getStatusId()!=null && buyer.getStatusId()!=null){
				if(status.getStatusId().intValue() == buyer.getStatusId().intValue())
				{
					statusDescription = status.getStatusDescription();
				}
			}
			
					
		}  
		
		if((objrequestedDeliveryDate = (Date)poDetailForm.getPoHeader().getIssueDate())!= null)
        {
			   //objrequestedDeliveryDate_String = PIXUtil.sqlToStandardDate(objrequestedDeliveryDate.toString());
			  Calendar calendar = new GregorianCalendar(Locale.US);
			 //Date trialTime = new Date();
			  calendar.setTime(objrequestedDeliveryDate);
			  objrequestedDeliveryDate_String = (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.YEAR);
    	} 
		objTable = new Table(6);
		objTable.setPadding(2);
		objTable.setSpacing(0);
	   
	    Cell ncell = new Cell();
	    objTable.setAlignment(Element.ALIGN_LEFT);
	    objTable.setWidth(90f);
	    ncell = new Cell(new Phrase("MATERIAL NUMBER:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader() != null)
	    {
	    	if(poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getIsbn10() != null)
	    	{
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
	    
	    if(poDetailForm.getPoHeader().getUOMDetail() != null&&poDetailForm.getPoHeader().getUOMDetail().getUom() != null)
	    {
	    	String uom=poDetailForm.getPoHeader().getUOMDetail().getUom();
	    	if(uom.equalsIgnoreCase("lb"))
	    	{
	    		ncell = new Cell(new Phrase("QTY.(LBS):",objFont));
	        }
	    	else{
	    		ncell = new Cell(new Phrase("QTY.(LBS):",objFont));
	    	}
	    }
	    
	    
	    
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getTotalQuantity() != null )
    	{
	    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTotalQuantity().toString(),objFont));
    	}
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase("PAPER GRADE: ",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getTitleDetail() != null)
    	{
	    ncell = new Cell(new Phrase((poDetailForm.getPoHeader().getTitleDetail().getPaperGrade()),objFont));
    	}
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
	    if(poDetailForm.getPoHeader().getJobNo() != null)
	    {
	    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getJobNo(),objFont));
	    }
	    else
		 {
       	ncell = new Cell(new Phrase("",objFont)); 
			 
		 }
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase("STATUS:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase(statusDescription,objFont));
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase("BASIS WEIGHT:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getTitleDetail().getBasisWeight() != null)
	    {
	    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTitleDetail().getBasisWeight().toString(),objFont));
	    }
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase("UOM:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getUOMDetail() != null&&poDetailForm.getPoHeader().getUOMDetail().getUom() != null)
	    {
	    	String uom=poDetailForm.getPoHeader().getUOMDetail().getUom();
	    	if(uom.equalsIgnoreCase("lb"))
	    	{
	    ncell = new Cell(new Phrase("Roll",objFont));
	        }
	    	else{
	    		ncell = new Cell(new Phrase("Sheet",objFont));
	    	}
	    }
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    }
	
     }
	catch(Exception de)
	{
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.EXCEPTION, 
	                "PurchaseOrderPdf,display",de);
		   throw appException;
	}
	
   return objTable;	
}
/**
* This method is used for Status Table.
* @param Font
* @param PurchaseOrderDetailForm
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
		ncell.setColspan(4);
		POParty vendor = null;
		int j=0;
        String statusDescription = "";
		String deliverydate = "";
		
		if(poDetailForm != null && poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getPartyCollection() != null )
			j=poDetailForm.getPoHeader().getPartyCollection().size();
		
		for(int i=0;i<j;i++)
		{
			if(poDetailForm != null && poDetailForm.getPoHeader() != null && poDetailForm.getPoHeader().getPartyCollection() != null )
		    poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
		 		 
		 
		  if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals("M"))
		  {  
			
			 vendor = poParty;
			 
			 if(vendor.getDeliveryDate()!= null)
			 {
				 deliverydate = vendor.getDeliveryDate();
			 }
		  }
		}
		int temp=poDetailForm.getPoAllHeaderStatus().size();
		for(int i=0;i<temp;i++)
		{
			Status status = (Status)((Vector)poDetailForm.getPoAllHeaderStatus()).get(i);
			if(status.getStatusId()!=null && vendor.getStatusId()!=null){
				if(status.getStatusId().intValue() == vendor.getStatusId().intValue())
				{
					statusDescription = status.getStatusDescription();
				}
			}
			
				
			
		}  
		
		ncell = new Cell(new Phrase("STATUS :",objFont));
		ncell.setBackgroundColor(new Color(239, 239, 239));
		objTable.addCell(ncell);
		ncell = new Cell(new Phrase(statusDescription,objFont));
		ncell.setBackgroundColor(new Color(249, 249, 249));
		
		objTable.addCell(ncell);
		ncell = new Cell(new Phrase("DELIVERY DATE :",objFont));
		ncell.setBackgroundColor(new Color(239, 239, 239));
		objTable.addCell(ncell);
		ncell = new Cell(new Phrase(deliverydate,objFont));
		ncell.setBackgroundColor(new Color(249, 249, 249));
		objTable.addCell(ncell);
		int temp1=poDetailForm.getPoHeader().getPartyCollection().size();
		for(int i=0;i<temp1;i++){
		   	 poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
		   	if(poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
			{        
	            ncell = new Cell(new Phrase("BUYER NOTES:",objFont));
	            ncell.setBackgroundColor(new Color(239, 239, 239));
	            objTable.addCell(ncell);
	            if(poParty.getComments() != null){
	            ncell = new Cell(new Phrase(poParty.getComments().replaceAll("<br>","\n"),objFont));
	            }
	            else
   			 	{
	            	ncell = new Cell(new Phrase("",objFont)); 
   				 
   			 	}
	            ncell.setBackgroundColor(new Color(249, 249, 249));
	            ncell.setColspan(3);
	            objTable.addCell(ncell);
			}   
		    if(poParty.getPartyType().equals("M"))
		    {
	            ncell = new Cell(new Phrase("YOUR COMMENTS:",objFont));
	            ncell.setBackgroundColor(new Color(239, 239, 239));
	            objTable.addCell(ncell);
	            if( poParty.getComments() != null){
	            ncell = new Cell(new Phrase(poParty.getComments(),objFont));
	            }
	            else
    			 {
	            	ncell = new Cell(new Phrase("",objFont)); 
    				 
    			 }
	            ncell.setBackgroundColor(new Color(249, 249, 249));
	            ncell.setColspan(3);
	            objTable.addCell(ncell);
		    }    
		
		}
		objTable.setBottom(10f);
	}
	    catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
	 return objTable;	
	
}
/**
* This method is used Buyer vendor and shipto Details details.
* @param Font
* @param PurchaseOrderDetailForm
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
		
		for(int i=0;i<poDetailForm.getPoHeader().getPartyCollection().size();i++)
		{
		    poParty = (POParty)((Vector)poDetailForm.getPoHeader().getPartyCollection()).get(i);
		  if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals(PIXConstants.BUYER_ROLE))
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
			 if(buyer.getCountryDetail() != null && buyer.getCountryDetail().getCountryName() != null)
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
				 if(popartyContact != null && popartyContact.getOrderNo() != null && popartyContact.getOrderNo().intValue()==1)
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
		 if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals("M"))
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
				 vendorStr=vendorStr+"\n"+vendoraddress3+" " +vendoraddress4;
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
				 vendorStr=vendorStr+"\n"+vendorcontactfirstname+" " +vendorcontactlastname;
			 }
			 if(vendorphone!=""|| vendormobile!=""||vendorfax!=""||vendoremail!="")
			 {
				 vendorStr=vendorStr+"\n"+vendorphone+"\n" +vendormobile+"\n"+vendorfax+"\n"+vendoremail;
			 }
		 }
		 if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
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
				 shiptoStr=shiptoStr+"\n"+shiptocontactfirstname+" " +shiptocontactlastname;
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
		bvscell.setBackgroundColor(new Color(188, 215, 243));
		bvscell = new Cell(new Phrase("BUYER"+ "("+ buyersan + ")",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(236, 245, 253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("MILL"+ "("+ vendorsan + ")",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(236, 245, 253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("SHIP TO"+ "("+ shipTosan + ")",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(236, 245, 253));
		objTable.addCell(bvscell);
		
		/*bvscell = new Cell(new Phrase(buyername1+ "\n" + buyername2 + "\n" + buyername3 + "\n" + buyeraddress1 + " \n" + buyeraddress2 + " " + buyeraddress3+ "\n" +
				   " "+buyeraddress4+ "\n" +buyercity+ "\n" +buyerpostalcode+ " " +buyerstate+ " " + buyercountryname+"\n"+ "-" +
				   " "+buyercontactfirstname+ " " +buyercontactlastname+" \n" +
				   " "+buyerphone+"\n" +
					" "+buyermobile+"\n" + 
				    " "+buyerfax+"\n" +
				    " "+buyeremail+" ",objFont)); */ 
		bvscell = new Cell(new Phrase(buyerStr,objFont));
		 		objTable.addCell(bvscell);
		/*bvscell = new Cell(new Phrase(vendorname1+ "\n" + vendorname2 + "\n" + vendorname3 + "\n" + vendoraddress1 + " \n" + vendoraddress2 + " " + vendoraddress3+ "\n" +
				   " "+vendoraddress4+ "\n" +vendorcity+ "\n" +vendorpostalcode+ "," +vendorstate+ " " + vendorcountryname+"\n" + "-" +
				   " "+vendorcontactfirstname+ " " +vendorcontactlastname+" \n" +
				   " "+vendorphone+"\n" +
				   " "+vendormobile+"\n" + 
			       " "+vendorfax+"\n" + 
			       " "+vendoremail+" ",objFont)); */
		 		bvscell = new Cell(new Phrase(vendorStr,objFont));
			objTable.addCell(bvscell);
		/*bvscell = new Cell(new Phrase(shiptoname1+ "\n" + shiptoname2 + "\n" + shiptoname3 + "\n" +shiptoaddress1 + " \n" + shiptoaddress2 + " " + shiptoaddress3+ "\n" +
				   " "+shiptoaddress4+ "\n" +shiptocity+ "\n" +shiptopostalcode+ "," +shiptostate+ " " + shiptocountryname+"\n"  + "-"+
				   " "+shiptocontactfirstname+ " "+shiptocontactlastname+" \n" +
				   " "+shiptophone+" \n" +
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
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
	 return objTable;
}
/**
* This method is used Line item Details .
* @param Font
* @param PurchaseOrderDetailForm
* @return Table
*/	
private PdfPTable LineItems(Font objFont,PurchaseOrderDetailForm poDetailForm, POLine poLine,String desc,int k) throws AppException
{
	PdfPTable objdatatable = null;
	Font objFont2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
	POLineParty shipTo = null;
	POLinePartyContact shipToContact = null;
	POLineParty poParty = null;
	String shiptoname1 = "",shiptoname2 = "",shiptoname3= "",shiptoorgunitname = "",shiptoaddress1 = "",shiptoaddress2 = "",shiptoaddress3 = "",shiptoaddress4 = "";
	String shiptocity = "",shiptopostalcode = "",shiptostate = "",shiptocountryname = "";
	String shiptocontactfirstname = "",shiptocontactlastname = "",shiptophone = "",shiptomobile = "",shiptofax = "",shiptoemail = "",shipTosan="";
	Vector partycontactCollection = null;
	try
	{
			objdatatable = new PdfPTable(6);
    		//objdatatable.setPadding(1);
    		//objdatatable.setSpacingBefore(0);	
            int headerwidths[] = {10,30,9, 8,18,9};
            objdatatable.setWidths(headerwidths);
            objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
            objdatatable.setWidthPercentage(90f);	
           // objdatatable.setDefaultCellBorderWidth(1);
           // objdatatable.setDefaultRowspan(1);
            PdfPCell datatablecell = new PdfPCell();
            String statusDescription = null;
            for(int i=0;i<poDetailForm.getPoAllLineStatus().size();i++)
    		{
    			Status status = (Status)((Vector)poDetailForm.getPoAllLineStatus()).get(i);
    		   
    			if(poLine.getSupplierStatusId() != null)
    			{
    				    			
	    			if(status.getStatusId().intValue() == poLine.getSupplierStatusId().intValue())
	    			{
	    				statusDescription = status.getStatusDescription();
	    			}
    			}
    		
    		}
            
        		
        		//for(int i=0;i<poDetailForm.getPoHeader().getPartyCollection().size();i++)
        		//{
                 
        		    poParty = (POLineParty)((Vector)poLine.getLinePartyCollection()).get(0);
        		    
        		    if(poParty != null)
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
        				 
        				 partycontactCollection = (Vector)poParty.getLinePartyContactCollection();
        				 //for(int j=0;j<partycontactCollection.size();j++)
        				 //{
        					 //if(j==k)
        					 //{
        				 POLinePartyContact popartyContact = ((POLinePartyContact)partycontactCollection.get(0)); 
        					 
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
        					// }
        				 //}
        				 //}
        			 }
	//}
              datatablecell = new PdfPCell(new Phrase(desc,objFont2));
              datatablecell.setBackgroundColor(new Color(171,199,227));
              datatablecell.setColspan(6);
			  datatablecell.setBorder(0);
				
              objdatatable.addCell(datatablecell);
              datatablecell = new PdfPCell(new Phrase("MATERIAL NUMBER",objFont));
              datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("DESCRIPTION",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          
	         
	          if(poDetailForm.getPoHeader().getUOMDetail() != null&&poDetailForm.getPoHeader().getUOMDetail().getUom() != null)
	  	    {
	  	    	String uom=poDetailForm.getPoHeader().getUOMDetail().getUom();
	  	    	if(uom.equalsIgnoreCase("lb"))
	  	    	{
	  	    		datatablecell = new PdfPCell(new Phrase("QTY.(LBS)",objFont));
	  	        }
	  	    	else{
	  	    		datatablecell = new PdfPCell(new Phrase("QTY.(LBS)",objFont));
	  	    	}
	  	    }
	          
	          
	         // datatablecell = new PdfPCell(new Phrase("QTY",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("DELIVERY DATE",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("SHIP TO"+ "("+ shipTosan + ")",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	  		  objdatatable.addCell(datatablecell);
	          datatablecell = new PdfPCell(new Phrase("STATUS",objFont));
	          datatablecell.setBackgroundColor(new Color(230,242,253));
	          objdatatable.addCell(datatablecell);
	         
	          //objdatatable.getDefaultCell().setBorderColor(new Color(230,242,253));
	          
	         // objdatatable.setDefaultCellBorderWidth(1);
	         
	             
	           	  objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	//  objdatatable.setDefaultCellBackgroundColor(new Color(248, 251, 254));
                 // datatablecell.setRowspan(1);
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
		       // datatablecell = new Cell(new Phrase(poLine.getLineDecription(),objFont));
		       //   objdatatable.addCell(datatablecell);
		          if (poLine.getRequestedQuantity() != null)
		          {
			      datatablecell = new PdfPCell(new Phrase(poLine.getRequestedQuantity().toString(),objFont));
			      datatablecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		             
		          objdatatable.addCell(datatablecell);
		          if (poLine.getRequestedDeliveryDate() != null)
		          {
			      datatablecell = new PdfPCell(new Phrase(poLine.getRequestedDeliveryDate(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          
		          datatablecell = new PdfPCell(new Phrase(shiptoname1+ "\n" + shiptoname2 + "\n" + shiptoname3 + "\n" +shiptoaddress1 + " \n" + shiptoaddress2 + " " + shiptoaddress3+ "\n" +
						   " "+shiptoaddress4+ "\n" +shiptocity+ "\n" +shiptopostalcode+ "," +shiptostate+ " " + shiptocountryname+"\n"  + "-"+
						   " "+shiptocontactfirstname+ " "+shiptocontactlastname+" \n" +
						   " "+shiptophone+" \n" +
						   " "+shiptomobile+" \n" + 
						   " "+shiptofax+"\n" +
						   " "+shiptoemail+" ",objFont)); 
		          datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          if (poLine.getPubUnitStatusDetail() != null && poLine.getPubUnitStatusDetail().getStatusDescription() != null)
		          {
		          datatablecell = new PdfPCell(new Phrase(poLine.getPubUnitStatusDetail().getStatusDescription(),objFont));
		          }
		          datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);  
		          datatablecell = new PdfPCell(new Phrase("      ",objFont));
		          datatablecell.setBackgroundColor(new Color(239,239,239));
		          
		        
		          datatablecell = new PdfPCell(new Phrase("      ",objFont));
		          datatablecell.setBackgroundColor(new Color(239,239,239));
		          objdatatable.addCell(datatablecell);
		          
		          if(poLine.getEstimatedDeliveryDate() == null)
		          {	  
		          datatablecell.setColspan(3);
		          datatablecell.setBackgroundColor(new Color(239,239,239));
		          objdatatable.addCell(datatablecell);
		          }	          
		          
		          if(poLine.getEstimatedDeliveryDate() != null)
		          {
		          datatablecell.setColspan(2);
			      datatablecell.setBackgroundColor(new Color(239,239,239));
			      objdatatable.addCell(datatablecell);	  
		        	  
		          datatablecell = new PdfPCell(new Phrase(poLine.getEstimatedDeliveryDate(),objFont));		         
	     	      datatablecell.setBackgroundColor(new Color(239,239,239));		            
     	          objdatatable.addCell(datatablecell);
		          }
     	          datatablecell = new PdfPCell(new Phrase(statusDescription,objFont));
     	          datatablecell.setBackgroundColor(new Color(239,239,239));
     	          objdatatable.addCell(datatablecell);     	        
     	        
     	          datatablecell = new PdfPCell(new Phrase("      ",objFont));
		          datatablecell.setBackgroundColor(new Color(239,239,239));
		          objdatatable.addCell(datatablecell);
     	         
     	          datatablecell = new PdfPCell(new Phrase("BUYER NOTES:",objFont));
     	          datatablecell.setBackgroundColor(new Color(239,239,239));
     	          objdatatable.addCell(datatablecell);
     	          if(poLine.getPubUnitComments() != null)
     	          {
     	         datatablecell = new PdfPCell(new Phrase(poLine.getPubUnitComments(),objFont));
     	          }
     	         else
     			 {
     				datatablecell = new PdfPCell(new Phrase("",objFont)); 
     				 
     			 }
	     		 datatablecell.setBackgroundColor(new Color(239,239,239));
		         	 
     			 datatablecell.setColspan(6);
     			 objdatatable.addCell(datatablecell);
     		   	 datatablecell = new PdfPCell(new Phrase("YOUR COMMENTS/QUERIES:",objFont));
     			 datatablecell.setBackgroundColor(new Color(239,239,239));
     			 objdatatable.addCell(datatablecell);
     			 if(poLine.getSupplierComments() != null)
     			 {
     			 datatablecell = new PdfPCell(new Phrase(poLine.getSupplierComments(),objFont));
     			 }
     			else
    			 {
    				datatablecell = new PdfPCell(new Phrase("",objFont)); 
    				 
    			 }
	     		 datatablecell.setBackgroundColor(new Color(239,239,239));
		        	 
     			 datatablecell.setColspan(6);
     			 objdatatable.addCell(datatablecell);
     		
	}
	 catch(DocumentException de)
		{
		 
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
	catch(Exception de)
    {
		
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.EXCEPTION, 
	                "PurchaseOrderPdf,display",de);
		   throw appException;
    }
	 return objdatatable;
}

/**
* This method is used supp Line item Details .
* @param Font
* @param PurchaseOrderDetailForm
* @return Table
*//*	
private PdfPTable suppLineItems(Font objFont,PurchaseOrderDetailForm poDetailForm, POLine poLine) throws AppException
{
	PdfPTable objdatatable = null;
	try
	{
			objdatatable = new PdfPTable(6);
    		int headerwidths[] = {10,15,7, 6, 8,20};
            objdatatable.setWidths(headerwidths);
            objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
            objdatatable.setWidthPercentage(90f);
            PdfPCell datatablecell = new PdfPCell();
           
            String name1 = "",name2 = "",name3= "",address1 = "",address2 = "",address3 = "",address4 = "";
        	String city = "",postalcode = "",state = "",countryname = "";
        	String contactFirstName="",contactLastName="",bphone="",mobile="",fax="",email="";
			
			  POSuppliedComp poline=null;
	          if(poLine.getSuppliedCompCollection().size() != 0){
	        	  int suppSize=poLine.getSuppliedCompCollection().size();
	          for(int i=0;i<suppSize;i++)
	    	  {
	        	  datatablecell = new PdfPCell(new Phrase("PRODUCT ID",objFont));
	              datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
		          Font objFont1 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		          datatablecell = new PdfPCell(new Phrase("SUPPLIED COMPONENT NAME",objFont1));
		          datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
		          datatablecell = new PdfPCell(new Phrase("QUANTITY",objFont));
		          datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
		          datatablecell = new PdfPCell(new Phrase("UOM",objFont));
		          datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
		          datatablecell = new PdfPCell(new Phrase("COMPONENT DELIVERY DATE",objFont));
		          datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
		          datatablecell = new PdfPCell(new Phrase("VENDOR DETAILS",objFont));
		          datatablecell.setBackgroundColor(new Color(230,242,253));
		          objdatatable.addCell(datatablecell);
	        	  
	          poline=(POSuppliedComp)((Vector)poLine.getSuppliedCompCollection()).get(i);
	           	  objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	    
	           	  if(poline.getProductCode() != null){
	           	      datatablecell = new PdfPCell(new Phrase(poline.getProductCode(),objFont));
			          datatablecell.setBackgroundColor(new Color(248, 251, 254));
	           	       objdatatable.addCell(datatablecell);
	           	  }
		          if(poline.getProductDescription() != null)
		          {
		        	  datatablecell = new PdfPCell(new Phrase(poline.getProductDescription().replaceAll("<br>","\n"),objFont1));
		        	  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		        	  objdatatable.addCell(datatablecell);
		          }
		          else
		          {
		        	  datatablecell = new PdfPCell(new Phrase("",objFont));
		        	  datatablecell.setBackgroundColor(new Color(248, 251, 254));
		        	  objdatatable.addCell(datatablecell);
		          }
		      
		          if (poline.getQuantity() != null)
		          {
			      datatablecell = new PdfPCell(new Phrase(poline.getQuantity().toString(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          
		          if (poline.getUOMDetail() != null && poline.getUOMDetail().getUom() != null)
		          {
			      datatablecell = new PdfPCell(new Phrase(poline.getUOMDetail().getUom(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		         		                 
		          if (poline.getShipDate() != null)
		          {
		        	 datatablecell = new PdfPCell(new Phrase(poline.getShipDate(),objFont));
		          }
			      datatablecell.setBackgroundColor(new Color(248, 251, 254));
		          objdatatable.addCell(datatablecell);
		          	
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getName1() != null)
		        	  name1=poline.getPartyDetail().getName1();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getName2() != null)
		        	  name2=poline.getPartyDetail().getName2();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getName3() != null)
		        	  name3=poline.getPartyDetail().getName3();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getAddress1() != null)
		        	  address1=poline.getPartyDetail().getAddress1();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getAddress2() != null)
		        	  address2=poline.getPartyDetail().getAddress2();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getAddress3() != null)
		        	  address3=poline.getPartyDetail().getAddress3();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getAddress4() != null)
		        	  address4=poline.getPartyDetail().getAddress4();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getCity() != null)
		        	  city=poline.getPartyDetail().getCity();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getPostalCode() != null)
		        	  postalcode=poline.getPartyDetail().getPostalCode();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getState() != null)
		        	  state=poline.getPartyDetail().getState();
		          if(poline.getPartyDetail() != null && poline.getPartyDetail().getCountryDetail() != null && poline.getPartyDetail().getCountryDetail().getCountryName() != null)
		        	  countryname=poline.getPartyDetail().getCountryDetail().getCountryName();
		          POSuppliedCompPartyCon  poSuppCompParty=null;
		          if(poline.getPartyDetail().getContactCollection().size() != 0){
		        	  int conSize=poline.getPartyDetail().getContactCollection().size();
		          	for(int k=0;k<conSize;k++)
		          	{
		          		contactFirstName="";
		          		contactLastName="";
		          		bphone="";
		          		mobile="";
		          		fax="";
		          		email="";
		          		      		
		          		poSuppCompParty=(POSuppliedCompPartyCon )((Vector)poline.getPartyDetail().getContactCollection()).get(k);
		          	//	((Vector)poline.getPartyDetail().getContactCollection()).get(k);
		          		if(poSuppCompParty.getContactFirstName() != null)
		          		contactFirstName =poSuppCompParty.getContactFirstName();
		          		if(poSuppCompParty.getContactLastName() != null)
		          			contactLastName =poSuppCompParty.getContactLastName();
		          		if(poSuppCompParty.getPhone() != null){
			          		bphone =poSuppCompParty.getPhone();
			          		bphone=bphone+ "(Business)";
		          		}
		          		if(poSuppCompParty.getMobile()!= null){
			          		mobile =poSuppCompParty.getMobile();
			          		mobile=mobile+"(Mob)";
		          		}
		          		if(poSuppCompParty.getFax() != null){
			          		fax =poSuppCompParty.getFax();
			          		fax=fax+"(Fax)";
		          		}
		          		if(poSuppCompParty.getEmail() != null)
			          		email =poSuppCompParty.getEmail();
		          		
		          	}
		          }
		              
     	          datatablecell = new PdfPCell(new Phrase(name1+" "+name2 +" "+name3+ "\n" +
     	        		  "\n" +address1+" "+address2+" "+ address3+" "+address4 + "\n" + city +" "+ postalcode+" " +state+" " +countryname 
     	        		  + "\n" +"\n " +"Attention-"+"\n"+
     	        		  contactFirstName +" "+contactLastName+"\n" +bphone + "\n" + mobile +"\n"+ fax +"\n" +email,objFont));
     	          datatablecell.setBackgroundColor(new Color(248, 251, 254));
     	          objdatatable.addCell(datatablecell);
     	           			 
     		   	 datatablecell = new PdfPCell(new Phrase("SUPPLIED COMPONENT NOTES:",objFont));
     			 datatablecell.setBackgroundColor(new Color(239,239,239));
     			 objdatatable.addCell(datatablecell);
     			 if(poline.getPubUnitComments() != null)
     			 {
     			 datatablecell = new PdfPCell(new Phrase(poline.getPubUnitComments(),objFont));
     			 }
     			 else
     			 {
     				datatablecell = new PdfPCell(new Phrase("",objFont)); 
     				 
     			 }
	     		 datatablecell.setBackgroundColor(new Color(239,239,239));
		        	 
     			 datatablecell.setColspan(5);
     			 objdatatable.addCell(datatablecell);
     			
     			 //added to make space between tables.
     			 if(i!=(suppSize-1))
     			 {
     				datatablecell = new PdfPCell(new Phrase());
     				datatablecell.setColspan(6);
     				datatablecell.setBorder(0);
     				datatablecell.setFixedHeight(5f);
     				objdatatable.addCell(datatablecell);
     			 }
     			    			
	    		}
	          }
	}
	 catch(DocumentException de)
		{
		 
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
	catch(Exception de)
    {
		
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.EXCEPTION, 
	                "PurchaseOrderPdf,display",de);
		   throw appException;
    }
	 return objdatatable;
}
*/

/**
* This method is defines the Costing details.
* @param Font
* @param PurchaseOrderDetailForm
* @return Table
*/	
private PdfPTable CostingTable(Font objFont,PurchaseOrderDetailForm poDetailForm,HttpSession session) throws AppException
{
	PdfPTable objCostingtable = null;
	try
	{
		objCostingtable = new PdfPTable(2);
		objCostingtable.setHorizontalAlignment(Element.ALIGN_LEFT);
        objCostingtable.setWidthPercentage(90f);	
		//objCostingtable.setPadding(1);
	//	objCostingtable.setSpacing(0);
		//objCostingtable.setWidth(90f);
       
        int headerwdh[] = {30, 10 };
        objCostingtable.setWidths(headerwdh);
       // objCostingtable.setAlignment(Element.ALIGN_LEFT);
       // objCostingtable.setDefaultCellBorderWidth(1);
        //objCostingtable.setDefaultRowspan(1);
        PdfPCell cell5 = new PdfPCell();
        
        PriceDetail pricedetail = null;
        if(poDetailForm.getPoPriceDetails() != null)
        {	
        	Vector priceDetails = (Vector)poDetailForm.getPoPriceDetails();
        	cell5 = new PdfPCell(new Phrase("COSTING",objFont));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell5.setLeading(10);
            cell5.setColspan(2);
            cell5.setBackgroundColor(new Color(236, 245, 253));
            objCostingtable.addCell(cell5);
            double totalPrice = 0;
            for(int i =0;i<priceDetails.size();i++)
        	{
        		pricedetail = (PriceDetail)priceDetails.get(i);
        		if(pricedetail != null && pricedetail.getGlCode() != null && pricedetail.getGlDesc() != null)
        		{
               cell5 = new PdfPCell(new Phrase(pricedetail.getGlCode()+"-"+pricedetail.getGlDesc() + " ",objFont));
        		}
               cell5.setBackgroundColor(new Color(236, 245, 253));
               objCostingtable.addCell(cell5);
               int decimalplaces = 2;
               
               BigDecimal priceDetail = pricedetail.getPrice().setScale(decimalplaces, BigDecimal.ROUND_DOWN);
               
               if (pricedetail.getCurrencyDetail() != null && pricedetail.getCurrencyDetail().getCurrencyCode() != null)
               {
               cell5 = new PdfPCell(new Phrase(pricedetail.getCurrencyDetail().getCurrencyCode()+" "+priceDetail.toString(), objFont));
               }
               cell5.setBackgroundColor(new Color(236, 245, 253));
               objCostingtable.addCell(cell5);
               totalPrice = totalPrice + pricedetail.getPrice().doubleValue();
               
              
        	}
           
            cell5 = new PdfPCell(new Phrase("TOTAL PRICE", objFont));
            cell5.setBackgroundColor(new Color(171,199,227));
            objCostingtable.addCell(cell5);
            BigDecimal totalCost = new BigDecimal((double)totalPrice);
            int totaldecimalplaces = 2;
            BigDecimal total = totalCost.setScale(totaldecimalplaces, BigDecimal.ROUND_DOWN);
            if (pricedetail.getCurrencyDetail() != null && pricedetail.getCurrencyDetail().getCurrencyCode() != null)
            {
            cell5 = new PdfPCell(new Phrase(pricedetail.getCurrencyDetail().getCurrencyCode()+" "+total, objFont));
            }
            cell5.setBackgroundColor(new Color(171,199,227));
            objCostingtable.addCell(cell5);
      }
    
	}
	 catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
   return objCostingtable;

   }

private PdfPTable CostingTableForMill(Font objFont,PurchaseOrderDetailForm poDetailForm,HttpSession session) throws AppException
{
	PdfPTable objCostingtableMill = null;
	try
	{
		objCostingtableMill = new PdfPTable(2);
		objCostingtableMill.setHorizontalAlignment(Element.ALIGN_LEFT);
        objCostingtableMill.setWidthPercentage(90f);	
		//objCostingtable.setPadding(1);
	//	objCostingtable.setSpacing(0);
		//objCostingtable.setWidth(90f);
       
        int headerwdh[] = {30, 10 };
        objCostingtableMill.setWidths(headerwdh);
       // objCostingtable.setAlignment(Element.ALIGN_LEFT);
       // objCostingtable.setDefaultCellBorderWidth(1);
        //objCostingtable.setDefaultRowspan(1);
        PdfPCell cell5 = new PdfPCell();
        
        
        	cell5 = new PdfPCell(new Phrase("COSTING",objFont));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell5.setLeading(10);
            cell5.setColspan(2);
            cell5.setBackgroundColor(new Color(236, 245, 253));
            objCostingtableMill.addCell(cell5);
          //  double totalPrice = 0;
         		
            if(poDetailForm.getPoHeader().getGlcodeDesc() != null)
        		{
               cell5 = new PdfPCell(new Phrase(poDetailForm.getPoHeader().getGlcodeDesc(), objFont));
        		}
               cell5.setColspan(2);
               cell5.setBackgroundColor(new Color(171,199,227));
               objCostingtableMill.addCell(cell5);
              
           
            cell5 = new PdfPCell(new Phrase("TOTAL PRICE", objFont));
            cell5.setBackgroundColor(new Color(171,199,227));
            objCostingtableMill.addCell(cell5);
            
            if(poDetailForm.getPoHeader().getTotalAmount() != null)
    		{
            	int totaldecimalplaces = 2;
            	BigDecimal total = poDetailForm.getPoHeader().getTotalAmount().setScale(totaldecimalplaces, BigDecimal.ROUND_DOWN);
            	cell5 = new PdfPCell(new Phrase(total + " ",objFont));
            	cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
    		}
           cell5.setBackgroundColor(new Color(171,199,227));
           objCostingtableMill.addCell(cell5);
            
          // BigDecimal totalCost = poDetailForm.getPoHeader().getTotalAmount();
           // int totaldecimalplaces = 2;
           // BigDecimal total = totalCost.setScale(totaldecimalplaces, BigDecimal.ROUND_DOWN);
           // if (poDetailForm.getPoHeader().getTotalAmount() != null)
          //  {
            //cell5 = new PdfPCell(total, objFont);
          //  }
           // cell5.setBackgroundColor(new Color(171,199,227));
           // objCostingtableMill.addCell(cell5);
     // }
    
	}
	 catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}
   return objCostingtableMill;

   }


	

}
