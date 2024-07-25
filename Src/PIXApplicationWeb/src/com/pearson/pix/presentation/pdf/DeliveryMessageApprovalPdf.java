package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
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
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.dto.purchaseorder.POLineParty;
import com.pearson.pix.dto.purchaseorder.POLinePartyContact;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.costaccounting.action.CostAccountingForm;


public class DeliveryMessageApprovalPdf implements CommonPdfInterface
{

	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
		
		
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
				
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	           // POLine poLine = null;
	            
	            CostAccountingForm objDeliveryMessageForm;
	            objDeliveryMessageForm = (CostAccountingForm)session.getAttribute("costAccountingForm");
	            
	            document.open();
	            User objUser = (User)session.getAttribute("USER_INFO");
	            String roleType = "";
	            if(objUser!=null)
	            {
	            	roleType = objUser.getRoleTypeDetail().getRoleType();
	            }
	            
	            if (objDeliveryMessageForm.getDeliveryMessage() != null )
	            {
	            	if (objDeliveryMessageForm.getDeliveryMessage().getPoNo() != null && objDeliveryMessageForm.getDeliveryMessage().getReleaseNo() != null && !(objDeliveryMessageForm.getDeliveryMessage().getReleaseNo().intValue()==0)){
	            		heading.add(new Phrase("Purchase Order Number " + objDeliveryMessageForm.getDeliveryMessage().getPoNo() +"-" +objDeliveryMessageForm.getDeliveryMessage().getReleaseNo(),objFont));
	            	}
	            	else
	            	{
	            		heading.add(new Phrase("Purchase Order Number " + objDeliveryMessageForm.getDeliveryMessage().getPoNo(),objFont));	
	            	}
	            	heading.setSpacingAfter(10f);
	            	document.add(heading);
	            }
	            
				Paragraph objParagraph  = new Paragraph();
				if(objDeliveryMessageForm.getDeliveryMessage().getMsgTypeDetail() != null && objDeliveryMessageForm.getDeliveryMessage().getMsgTypeDetail().startsWith("Goods"))
					objParagraph.add(new Phrase("Following are the details of the Goods Receipt  No." +objDeliveryMessageForm.getDeliveryMessage().getMsgNo()+ ".",objFont));
				else	
					objParagraph.add(new Phrase("Following are the details of the Delivery Message  No." +objDeliveryMessageForm.getDeliveryMessage().getMsgNo()+ ".",objFont));
	            document.add(objParagraph);
	            Chunk objChunk = new Chunk("MessageType:     " + objDeliveryMessageForm.getDeliveryMessage().getMsgTypeDetail(),objFont);
	            objChunk.setBackground(new Color(230, 240, 250));
	            document.add(objChunk);
	            Table shiptoDetails = ShiptoDetails(objDeliveryMessageForm,objFont,roleType);
	          	document.add(shiptoDetails);
	         /*	Chunk lineHeading = new Chunk("Line Item Details",objFont);
		        document.add(lineHeading); */
		        Table lineDetails = LineDetails(objDeliveryMessageForm,objFont,roleType);
	            document.add(lineDetails);
				document.close();
				
		 }
	
		catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "DeliveryMessageApprovalPdf,display",de);
			   throw appException;
		}
		catch(Exception e)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.IO_EXCEPTION, 
		                "DeliveryMessageApprovalPdf,display",e);
			   throw appException;
		}
		return document;
	}	
	/**
	* This method is defines Shipto details.
	* @param objFont
	* @param objDeliveryMessageForm
	* @return Table
	*/	
	private Table ShiptoDetails(CostAccountingForm objDeliveryMessageForm,Font objFont,String roleType) throws AppException
	{
		Table objTable = null;
		POParty shipTo = null;
		POLineParty shipToLine = null;
		POPartyContact shipToContact = null;
		POLinePartyContact shipToLineContact = null;
		Vector partycontactCollection = null;
		Vector partycontactLineCollection = null;
		POParty poParty = null;
		POLineParty poLineParty = null;
		Vector r=null;
		String attention = "Attention-";
		String shiptoname1 = "",shiptoaddress1 = "",shiptocity = "",shiptopostalcode = "",shiptostate = "",shiptocountryname = "";
		String shiptocontactfirstname = "",shiptophone = "",shiptomobile = "",shiptofax = "",shiptoemail = "",san = "";
		try
		{
			if(roleType.equalsIgnoreCase("C"))
			{
			Vector sTest	=(Vector)objDeliveryMessageForm.getDeliveryMessage().getDeliveryMsgLineCollection();
			DeliveryMessageLine d=(DeliveryMessageLine)sTest.get(0);
			r=(Vector)d.getLinePartyCollection();
			for(int i=0;i<r.size();i++)
    		{
				poLineParty = (POLineParty)(r).get(i);
    		    shipToLine = poLineParty;
    		     if(shipToLine.getSan() != null)
    		     {
    		    	 san = shipToLine.getSan();
    		     }
	    		 if(shipToLine.getName1()!= null)
	   			 {
	   				 shiptoname1 = shipToLine.getName1();
	   			 }
	   			  if(shipToLine.getAddress1()!= null)
	   			 {
	   				 shiptoaddress1 = shipToLine.getAddress1();
	   			 }
	   			 if(shipToLine.getCity()!= null)
	   			 {
	   				 shiptocity = shipToLine.getCity();
	   			 }
	   			 if(shipToLine.getPostalCode()!= null)
	   			 {
	   				 shiptopostalcode = shipToLine.getPostalCode();
	   			 }
	   			 if(shipToLine.getState() != null)
	   			 {
	   				 shiptostate = shipToLine.getState(); 
	   			 }
	   			 if(shipToLine.getCountryDetail().getCountryName() != null)
	   			 {
	   				 shiptocountryname = shipToLine.getCountryDetail().getCountryName();
	   			 }
	   			partycontactLineCollection = (Vector)poLineParty.getLinePartyContactCollection();
		   		 for(int j=0;j<partycontactLineCollection.size();j++)
				 {
		   			POLinePartyContact popartyContact = ((POLinePartyContact)partycontactLineCollection.get(j)); 
		   			shipToLineContact = popartyContact;
						 if(shipToLineContact.getContactFirstName() != null)
						 {
							 shiptocontactfirstname = shipToLineContact.getContactFirstName();
						 }
						 if(shipToLineContact.getPhone() != null)
						 {
							 shiptophone = shipToLineContact.getPhone()+"(Business)";
						 }
						 if(shipToLineContact.getMobile() != null)
						 {
							 shiptomobile = shipToLineContact.getMobile()+"(Mobile)";
						 }
						 if(shipToLineContact.getFax() != null)
						 {
							 shiptofax = shipToLineContact.getFax()+"(Fax)";
						 }
						 if(shipToLineContact.getEmail() != null)
						 {
							 shiptoemail = shipToLineContact.getEmail();
						 }
					
				 }
			}
			}
			else{
				r=(Vector)objDeliveryMessageForm.getDeliveryMessage().getPartyCollection();
			
			for(int i=0;i<r.size();i++)
    		{
    		    poParty = (POParty)(r).get(i);
    		    shipTo = poParty;
    		     if(shipTo.getSan() != null)
    		     {
    		    	 san = shipTo.getSan();
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
			}    objTable = new Table(1);
	          	objTable.setPadding(2);
	    		objTable.setSpacing(0);
	    		Cell bvscell = new Cell();
	    		objTable.setAlignment(Element.ALIGN_LEFT);
	    	    objTable.setWidth(50f);
	    		bvscell.setColspan(1);
	    		bvscell.setRowspan(1);
	    		bvscell = new Cell(new Phrase("SHIPTO" + "("+ san + ")",objFont));
	    		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		bvscell.setBackgroundColor(new Color(236, 245, 253));
	    		objTable.addCell(bvscell);
	    		bvscell = new Cell(new Phrase(shiptoname1+ "\n"+shiptoaddress1 + "\n" + shiptocity + shiptopostalcode+ "\n" +shiptostate + shiptocountryname+"\n" + "-" +
	 				   " "+shiptocontactfirstname+ "\n" +
	 				   " "+shiptophone+"" +shiptomobile+""+shiptofax+"\n" +
	 				   " "+shiptoemail+" ",objFont));  
	 	       objTable.addCell(bvscell);
	    	    
		}
		catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "DeliveryMessageApprovalPdf,display",de);
			   throw appException;
		}
		return objTable;
	}
	/**
	* This method defines Line Item details.
	* @param objFont
	* @param objDeliveryMessageForm
	* @return Table
	*/		
private Table LineDetails(CostAccountingForm objDeliveryMessageForm,Font objFont,String rollType) throws AppException
{
	Table objTable = null;
	float headerwidths[]=null;
	
	try
	{
		
		int size = objDeliveryMessageForm.getDeliveryMessage().getDeliveryMsgLineCollection().size();
			
		if(rollType.equalsIgnoreCase("C"))
	    {
			objTable = new Table(14);
			headerwidths = new float[]{4,7,7,6,8,7,8,7,6,6,5,8,7,7};	
	    }
		else{
			objTable = new Table(6);
			headerwidths = new float[]{4,12,12,12,12,12};
		}
		objTable.setPadding(1);
		objTable.setSpacing(0);
        objTable.setWidths(headerwidths);
        objTable.setAlignment(Element.ALIGN_LEFT);
        objTable.setWidth(105f);	
        objTable.setDefaultCellBorderWidth(1);
        objTable.setDefaultRowspan(1);
        Cell datatablecell = new Cell();
        DeliveryMessageLine objDeliveryMessageLine = null;
        datatablecell = new Cell(new Phrase("PO LINE NO.",objFont));
	    datatablecell.setBackgroundColor(new Color(171,199,227));
	    objTable.addCell(datatablecell);
	    if(rollType.equalsIgnoreCase("C"))
	    {
	    	datatablecell = new Cell(new Phrase("MATERIAL NO",objFont));
	        datatablecell.setBackgroundColor(new Color(171,199,227));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase("MATERIAL DESCRIPTION",objFont));
	        datatablecell.setBackgroundColor(new Color(171,199,227));
	        objTable.addCell(datatablecell);
	    }
	    else{
        datatablecell = new Cell(new Phrase("COMPONENT",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
	    }
        datatablecell = new Cell(new Phrase("ORIGINAL QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("TOTAL DELIVERED",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("DELIVERED QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("REQUESTED DELIVERY DATE",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("SHIP DATE",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("RECEIVED QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("OWNING QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("OWNED QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("TO BE OWNED QTY",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        datatablecell = new Cell(new Phrase("OWNERSHIP MODE",objFont));
        datatablecell.setBackgroundColor(new Color(171,199,227));
        objTable.addCell(datatablecell);
        if(rollType.equalsIgnoreCase("C"))
	    {
        	datatablecell = new Cell(new Phrase("STATUS",objFont));
            datatablecell.setBackgroundColor(new Color(171,199,227));
            objTable.addCell(datatablecell);
	    }
        objTable.setBackgroundColor(new Color(188, 215, 243));;
        objTable.setDefaultCellBorderWidth(1);
        objTable.setDefaultRowspan(1);
        for (int j = 0; j <objDeliveryMessageForm.getDeliveryMessage().getDeliveryMsgLineCollection().size(); j++)
        {
            objDeliveryMessageLine = (DeliveryMessageLine)((Vector)objDeliveryMessageForm.getDeliveryMessage().getDeliveryMsgLineCollection()).get(j);
        	 
		    BigDecimal poline = objDeliveryMessageLine.getLineNo();
			
		       objTable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
		      // objTable.setDefaultCellBackgroundColor(new Color(230, 240, 250));
	           datatablecell.setRowspan(1);
			   datatablecell = new Cell(new Phrase(poline.toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   if(rollType.equalsIgnoreCase("C"))
			    {
				   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getProductCode(),objFont));
				   datatablecell.setBackgroundColor(new Color(248, 251, 254));
				   objTable.addCell(datatablecell); 
				   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getLineDecription(),objFont));
				   datatablecell.setBackgroundColor(new Color(248, 251, 254));
				   objTable.addCell(datatablecell); 
			    }
			   else{
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getProductDescription(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   }
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getBalanceQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getPostedQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getDeliveredQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getRequestedDeliveryDate().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			         
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getEstimatedDeliveryDate(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   //Naveen
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getReceivedQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getOwningQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getOwnedQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getToBeOwnedQuantity().toString(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   objTable.addCell(datatablecell);
			   datatablecell = new Cell(new Phrase(objDeliveryMessageLine.getOwnrshpMode(),objFont));
			   datatablecell.setBackgroundColor(new Color(248, 251, 254));
			   //Naveen
			   objTable.addCell(datatablecell);
			   if(rollType.equalsIgnoreCase("C"))
			    {
				   String flag = "";
				   if("N".equals(objDeliveryMessageLine.getApprovalFlag()))
				   {
					   flag = "Pending Approval";
				   }
				   else if("Y".equals(objDeliveryMessageLine.getApprovalFlag()))
				   {
					   flag = "Approved";
				   }
				   datatablecell = new Cell(new Phrase(flag,objFont));
				   datatablecell.setBackgroundColor(new Color(248, 251, 254));
				        
				   objTable.addCell(datatablecell);
			    }
	        
            
        }
	         
	}
	catch(DocumentException de)
	{
		AppException appException = new AppException();
		appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
	                "DeliveryMessageApprovalPdf,display",de);
		   throw appException;
	}
	return objTable;
	
}

}


