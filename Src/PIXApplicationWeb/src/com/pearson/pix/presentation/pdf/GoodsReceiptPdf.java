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
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.goodsreceipt.GoodsReceiptLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm;
import com.pearson.pix.presentation.goodsreceipt.action.GoodsReceiptForm;

public class GoodsReceiptPdf implements CommonPdfInterface
{

	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		 
		 try
		 {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
		       // float[] columnDefinitionSize = {33.30F, 33.30F, 33.30F, 33.30f,33.30f,33.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	           // POLine poLine = null;
	            GoodsReceiptForm objGoodsReceiptForm;
	            objGoodsReceiptForm = (GoodsReceiptForm)session.getAttribute("goodsReceiptForm");
		        document.open();
	            
	            if (objGoodsReceiptForm.getGoodsreceipt() != null )
	            {
	            	if (objGoodsReceiptForm.getGoodsreceipt().getPoNo() != null && objGoodsReceiptForm.getGoodsreceipt().getReleaseNo() != null && !(objGoodsReceiptForm.getGoodsreceipt().getReleaseNo().intValue()==0)){
	            		heading.add(new Phrase("Purchase Order Number " + objGoodsReceiptForm.getGoodsreceipt().getPoNo() + "-"  + objGoodsReceiptForm.getGoodsreceipt().getReleaseNo(),objFont));
	            	}
	            	else
	            	{
	            		heading.add(new Phrase("Purchase Order Number " + objGoodsReceiptForm.getGoodsreceipt().getPoNo(),objFont));	
	            	}
	            	heading.setSpacingAfter(10f);
	            	document.add(heading);
	            }
	            
	            Paragraph objParagraph  = new Paragraph();
				objParagraph.add(new Phrase("Following are the details of the Goods Receipt No." +objGoodsReceiptForm.getGoodsreceipt().getReceiptNo()+".",objFont));
	            document.add(objParagraph);
	            Chunk objChunk = new Chunk("DeliveryMessage No:     " + objGoodsReceiptForm.getGoodsreceipt().getMsgNo()+ "\t                                         " + "\t" +"Goods Condition :"+ objGoodsReceiptForm.getGoodsreceipt().getHeaderAcceptanceDescription() ,objFont);
	            objChunk.setBackground(new Color(230, 240, 250));
	           
	            document.add(objChunk);
	            Table vendorshiptoDetails = VendorShiptoDetails(objGoodsReceiptForm,objFont);
	            document.add(vendorshiptoDetails);
	            Table lineDetails = LineItemDetails(objGoodsReceiptForm,objFont);
	            document.add(lineDetails);
				document.close();
				
		 }
	
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "GoodsReceiptPdf,display",de);
				   throw appException;
			}
			catch(Exception e)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.IO_EXCEPTION, 
			                "GoodsReceiptPdf,display",e);
				   throw appException;
			}
		return document;
	}	
	
	
	/**
	* This method defines Vendor details.
	* @param objFont
	* @param objGoodsReceiptForm
	* @return Table
	*/	
	private Table VendorShiptoDetails(GoodsReceiptForm objGoodsReceiptForm,Font objFont) throws AppException
	{
		Table objTable = null;
		POParty shipTo = null;
		POParty vendor = null;
		POPartyContact shipToContact = null;
		POPartyContact vendorContact = null;
		Vector partycontactCollection = null;
		POParty poParty = null;
		String attention = "Attention-";
		String vendorname1 = "",vendoraddress1 = "",vendorcity = "",vendorpostalcode = "",vendorstate = "",vendorcountryname = "";
		String vendorcontactfirstname = "",vendorphone = "",vendormobile = "",vendorfax = "",vendoremail = "",vendorsan="";
	
		String shiptoname1 = "",shiptoaddress1 = "",shiptocity = "",shiptopostalcode = "",shiptostate = "",shiptocountryname = "";
		String shiptocontactfirstname = "",shiptophone = "",shiptomobile = "",shiptofax = "",shiptoemail = "",shipTosan="";
		try
		{
			for(int i=0;i<objGoodsReceiptForm.getGoodsreceipt().getPartyCollection().size();i++)
    		{
    		    poParty = (POParty)((Vector)objGoodsReceiptForm.getGoodsreceipt().getPartyCollection()).get(i);
    		    if(poParty.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
    			 { 
    		    String s=poParty.getPartyType();
    				 vendor = poParty;
    				 if(vendor.getSan() != null)
        		     {
        		    	 vendorsan = vendor.getSan();
        		     }
    				 if(vendor.getName1()!= null)
		   			 {
    					 vendorname1 = vendor.getName1();
		   			 }
		   			  if(vendor.getAddress1()!= null)
		   			 {
		   				vendoraddress1 = vendor.getAddress1();
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
				 vendorContact = popartyContact;
					 if(vendorContact.getContactFirstName() != null)
					 {
						 vendorcontactfirstname = vendorContact.getContactFirstName();
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
		   			  if(shipTo.getAddress1()!= null)
		   			 {
		   				 shiptoaddress1 = shipTo.getAddress1();
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
				 shipToContact = popartyContact;
					 if(shipToContact.getContactFirstName() != null)
					 {
						 shiptocontactfirstname = shipToContact.getContactFirstName();
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
    		    objTable = new Table(2);
	          	objTable.setPadding(2);
	    		objTable.setSpacing(0);
	    		Cell bvscell = new Cell();
	    		objTable.setAlignment(Element.ALIGN_LEFT);
	    	    objTable.setWidth(90f);
	    		bvscell.setColspan(1);
	    		bvscell.setRowspan(1);
	    	    //bvscell.setBackgroundColor(new Color(188, 215, 243));
	    		bvscell = new Cell(new Phrase("VENDOR"+ "("+ vendorsan + ")",objFont));
	    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		bvscell.setBackgroundColor(new Color(236, 245, 253));
	    		objTable.addCell(bvscell);
	    		bvscell = new Cell(new Phrase("SHIPTO" + "("+ shipTosan + ")",objFont));
	    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		bvscell.setBackgroundColor(new Color(236, 245, 253));
	    		objTable.addCell(bvscell);
	    		bvscell = new Cell(new Phrase(vendorname1+ "\n"+vendoraddress1 + "\n" + vendorcity + vendorpostalcode+ "," +vendorstate+ " " + vendorcountryname+"\n"+ "-" +
		 				  " "+vendorcontactfirstname+ "\n" +
		 				  " "+vendorphone+"\n" +vendormobile+"\n"+vendorfax+"\n" +
		 				  " "+vendoremail+" ",objFont));  
		  	   objTable.addCell(bvscell);
	    	   bvscell = new Cell(new Phrase(shiptoname1+ "\n"+shiptoaddress1 + "\n" + shiptocity + shiptopostalcode+ "," +shiptostate+ " " + shiptocountryname+"\n"+ "-" +
	 				   " "+shiptocontactfirstname+ "\n" +
	 				   " "+shiptophone+"\n" +shiptomobile+"\n"+shiptofax+"\n" +
	 				   " "+shiptoemail+" ",objFont));  
	 	      objTable.addCell(bvscell);
	    	    
		}
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "GoodsReceiptPdf,display",de);
				   throw appException;
			}
		return objTable;
	}	
	
	/**
	* This method defines Line Item details.
	* @param objFont
	* @param objGoodsReceiptForm
	* @return Table
	*/		
    private Table LineItemDetails(GoodsReceiptForm objGoodsReceiptForm,Font objFont) throws AppException
	{
		Table objTable = null;
		POParty poParty1 = null;
		int headerwidths[]=null;
		try
		{
			int size = objGoodsReceiptForm.getGoodsreceipt().getGoodsReceiptLineCollection().size();
			poParty1 = (POParty)((Vector)objGoodsReceiptForm.getGoodsreceipt().getPartyCollection()).get(0);
			if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
		    {
				objTable = new Table(10);
				headerwidths = new int[]{2,10,12,10,10,10,12,8,10,12};
		    }
			else
			{
				objTable = new Table(8);
				headerwidths = new int[]{2,6,12,12,12,8,12,12};
			}
			
			objTable.setPadding(1);
			objTable.setSpacing(0);	
	        
	        objTable.setWidths(headerwidths);
	        objTable.setAlignment(Element.ALIGN_LEFT);
	        objTable.setWidth(90f);	
	        objTable.setDefaultCellBorderWidth(1);
	        objTable.setDefaultRowspan(1);
	        Cell datatablecell = new Cell();
	        GoodsReceiptLine objGoodsReceiptLine = null;
	        
		    
				  datatablecell = new Cell(new Phrase("",objFont));
				  datatablecell.setBackgroundColor(new Color(171,199,227));
				  objTable.addCell(datatablecell);
				  
				  
				  if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
				    {
					  datatablecell = new Cell(new Phrase("MATERIAL NUMBER",objFont));
					  datatablecell.setBackgroundColor(new Color(171,199,227));
					  objTable.addCell(datatablecell);
					  datatablecell = new Cell(new Phrase("MATERIAL DESCRIPTION",objFont));	
				    }
				  else{
				  datatablecell = new Cell(new Phrase("COMPONENT",objFont));
				  }
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("ORIGINAL QUANTITY",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
				    {
			          datatablecell = new Cell(new Phrase("TOTAL RECEIVED",objFont));
			          datatablecell.setBackgroundColor(new Color(171,199,227));
			          objTable.addCell(datatablecell);
				    }
		          datatablecell = new Cell(new Phrase("QUANTITY",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
				    {
		          datatablecell = new Cell(new Phrase("REQUESTED DELIVERY DATE",objFont));
				    }
		          else{
		        	  datatablecell = new Cell(new Phrase("COMP. DELIVERY DATE",objFont));  
		          }
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("ACTUAL ARRIVAL DATE",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("TRANSPORT DAMAGE",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          datatablecell = new Cell(new Phrase("GOODS CONDITION",objFont));
		          datatablecell.setBackgroundColor(new Color(171,199,227));
		          objTable.addCell(datatablecell);
		          objTable.setBackgroundColor(new Color(188, 215, 243));;
		          objTable.setDefaultCellBorderWidth(1);
		          objTable.setDefaultRowspan(1);
	              for (int j = 0; j <objGoodsReceiptForm.getGoodsreceipt().getGoodsReceiptLineCollection().size(); j++)
	              {
	            	  objGoodsReceiptLine = (GoodsReceiptLine)((Vector)objGoodsReceiptForm.getGoodsreceipt().getGoodsReceiptLineCollection()).get(j);
	            	  
				          BigDecimal poline = objGoodsReceiptLine.getLineNo();
				          if((poline.intValue())%2 == 0)
		            	  {
			        	  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		                  datatablecell.setRowspan(1);
				          datatablecell = new Cell(new Phrase(poline.toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          
				          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
						    {
				        	  datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getProductCode(),objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
						    }
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getProductDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getBalanceQuantity() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getBalanceQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          
				          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
						    {
				        	  datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getCumulativeQuanReceived().toString(),objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
						    }
				          
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getReceivedQuantity() != null){
				        	  datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getReceivedQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getRequestedDeliveryDate() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getRequestedDeliveryDate().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getActualArrivalDate() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getActualArrivalDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getIntransitDamagedQty() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getIntransitDamagedQty().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont) );
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getAcceptanceDescription() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getAcceptanceDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(248, 251, 254));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
		             }
	            	 else if((poline.intValue())%2 != 0)
	            	 { 
	            		 
			        	  objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		                  datatablecell.setRowspan(1);
				          datatablecell = new Cell(new Phrase(poline.toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          
				          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
						    {
				        	  datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getProductCode(),objFont));
					          datatablecell.setBackgroundColor(new Color(236, 245, 253));
					          objTable.addCell(datatablecell);
						    }
				          
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getProductDescription() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getProductDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getBalanceQuantity() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getBalanceQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          
				          if(poParty1.getPartyType().equals(PIXConstants.MILL_ROLE)||poParty1.getPartyType().equals(PIXConstants.SHIPTO_ROLE))
						    {
				        	  datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getCumulativeQuanReceived().toString(),objFont));
					          datatablecell.setBackgroundColor(new Color(236, 245, 253));
					          objTable.addCell(datatablecell);
						    }
				          
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getReceivedQuantity() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getReceivedQuantity().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getRequestedDeliveryDate() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getRequestedDeliveryDate().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getActualArrivalDate() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getActualArrivalDate(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  Cell datatablecell1 = new Cell(new Phrase("",objFont));
					          datatablecell1.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell1);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getIntransitDamagedQty() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getIntransitDamagedQty().toString(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
				          if(objGoodsReceiptLine != null && objGoodsReceiptLine.getAcceptanceDescription() != null){
				          datatablecell = new Cell(new Phrase(objGoodsReceiptLine.getAcceptanceDescription(),objFont));
				          datatablecell.setBackgroundColor(new Color(236, 245, 253));
				          objTable.addCell(datatablecell);
				          }
				          else
				          {
				        	  datatablecell = new Cell(new Phrase("",objFont));
					          datatablecell.setBackgroundColor(new Color(248, 251, 254));
					          objTable.addCell(datatablecell);
				          }
	            	 }
	             }
		        
		}
		 catch(DocumentException de)
			{
				AppException appException = new AppException();
				appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
			                "GoodsReceiptPdf,display",de);
				   throw appException;
			}
		return objTable;
		
	}
	
}
