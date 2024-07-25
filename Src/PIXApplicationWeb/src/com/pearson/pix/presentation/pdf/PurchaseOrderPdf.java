package com.pearson.pix.presentation.pdf;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.codec.postscript.ParseException;
import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.Status;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.dto.purchaseorder.DropshipDTO;
import com.pearson.pix.dto.purchaseorder.POLine;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.dto.purchaseorder.POSuppliedComp;
import com.pearson.pix.dto.purchaseorder.POSuppliedCompPartyCon;
import com.pearson.pix.dto.purchaseorder.PriceDetail;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;
import com.pearson.pix.presentation.dropship.delegate.DropshipDelegate;
import com.pearson.pix.presentation.purchaseorder.action.PurchaseOrderDetailForm;

public class PurchaseOrderPdf implements CommonPdfInterface
{

	private String TACHeading1     = "1. CONTRACT:";
	private String TACHeading2     = "2. PACKAGING AND DELIVERY:";
	private String TACHeading3     = "3. BILLING:";
	private String TACHeading4     = "4. WARRANTY";
	private String TACHeading5 	   = "5. INSPECTION AND REJECTION:";
	private String TACHeading6 	   = "6. PAPER AND OTHER MATERIALS:";
	private String TACHeading7     = "7. CANCELLATION: ";
	private String TACHeading8     = "8. CHANGES:";
	private String TACHeading9     = "9. PRODUCT SAFETY: ";
	private String TACHeading10    = "10. COMPLIANCE WITH LAWS: ";
	private String TACHeading11    = "11. HOLD HARMLESS: ";
	private String TACHeading12    = "12. CONFIDENTIALITY: ";
	private String TACHeading13    = "13. NON-ASSIGNMENT: ";
	private String TACHeading14    = "14. GOVERNING LAW. ";
	
	
	private String TAC_DETAIL1="The performance of this purchase order ('Order') by Vendor (in whole or in part) shall constitute acceptance hereof and agreement to the terms and conditions set forth herein. This Order constitutes the sole agreement between the parties relating to the subject matter hereof, except for amendments agreed to in writing by both Pearson Canada Inc. ('Pearson') and Vendor.";
	private String TAC_DETAIL2="Unless otherwise specified on the face hereof, all charges for packing, crating, storing and preparing goods for delivery are included in the purchase price. Vendor shall deliver shipments to Pearson’s warehouse or such other facility specified in the routing instructions of this Order. All shipments must be accompanied by packing slips containing a description of the goods, the order number, and the facility to which the shipment is to be delivered. Title and risk of loss shall pass upon delivery to the facility set forth in the routing instructions of this Order. Any expense resulting from misrouted shipments shall be charged to Vendor. Time is of the essence with respect to Vendor's obligations hereunder. If delivery of goods or rendering of services is not completed by the time required delivery date, Pearson reserves the right, in addition to its other rights at law, to return the goods or cancel all or part of this Order and charge Vendor with all costs, expenses and damages associated with such return or cancellation.";
	private String TAC_DETAIL3="All invoices will be paid pursuant to the terms hereon.  Any applicable sales, use, excise or other similar taxes shall be itemized separately on the invoice. Vendor shall accept any tax exemption certificates supplied by Pearson.";
	private String TAC_DETAIL4="Vendor warrants that (i) it has full power and authority to enter into and perform its obligations under this Order, (ii) it shall use reasonable care in performing the services and delivering the goods hereunder, (iii) the goods and services covered by this Order will conform to any and all specifications, drawings or samples furnished by Pearson, (iv) the goods delivered will be sufficient for their intended purposes, and (v) the goods will function properly and be free from defects in material and workmanship. Such warranties shall be in addition to those warranties available at law and shall survive any acceptance by Pearson of all or part of the goods and services covered by this Order.";
	private String TAC_DETAIL5="All goods shall be received subject to Pearson's inspection and rejection. Defective goods will, at Pearson's option, either be held for Vendor's instruction and at Vendor's risk, or returned to Vendor at Vendor's expense. Goods returned as defective shall not be replaced without a new Order. Payment for goods prior to inspection shall not constitute an acceptance thereof. Notice of defects shall be given by Pearson to Vendor within a reasonable time after receipt by Pearson.";
	private String TAC_DETAIL6="All paper or other materials, including without limitation artwork, film and/or dies, furnished to Vendor by Pearson or created by Vendor from materials furnished by Pearson shall remain the property of Pearson. ";
	private String TAC_DETAIL7="Pearson may cancel this Order in whole or in part for (i) defects in material, equipment, workmanship or quality; (ii) failure to receive goods or services by the required delivery date; or (iii) Vendor's other failure to comply with any of the terms of this Order.";
	private String TAC_DETAIL8="Pearson may make changes in quantities, drawings, specifications, delivery schedules, method of shipment and packaging, and may terminate work on this Order for its own convenience, in whole or in part, by written notice at any time. In such event, any claim for an increase or decrease in price or in the time required for performance shall be settled by negotiation between the parties.";
	private String TAC_DETAIL9="In manufacturing or sourcing goods pursuant to this Order and delivering them to Pearson, Vendor certifies that (i) it complies with and will continue to comply with the strictest applicable federal, provincial, local or other jurisdictions' mandatory and consensus safety standards (including, without limitation, the Canada Consumer Product Safety Act), applicable to such goods, (ii) the goods will be tested based on a reasonable testing program or by an accredited third-party safety testing laboratories as required by applicable law, (iii) Vendor has received verification and will certify, to the extent applicable that such goods have met such regulations and standards, before delivery to Pearson, and (iv) Vendor will maintain all records of safety testing for such goods and make such records available for Pearson's review on reasonable notice for as long as Vendor supplies the goods to Pearson and four (4) years thereafter.\n" +
								"Vendor further certifies that the use of the goods delivered hereunder will not result in injury or damage to person or property, and the appropriate warnings, precautions or disclaimers, concerning any potentially hazardous or dangerous information or uses has been included in or on the goods in accordance with all applicable regulations and standards.";
	private String TAC_DETAIL10="Vendor certifies that it complies with and will continue to comply with all laws and regulations applicable to the production, sale and delivery of the goods or the furnishing of any labor or services under this Order, and any provisions required thereby to be included herein shall be deemed to be incorporated herein by reference.  Vendor further certifies that it complies with and will continue to comply with (i) all relevant laws, regulations, codes of practice and other similar controls, and advice issued by any government or appropriate regulator relating to the treatment of employees, workplace conditions, health and safety, use of labor and human rights (together, the 'Applicable Labor Laws'), and that it holds and will continue to hold all necessary permits, licenses, certificates and approvals required by the Applicable Labor Laws; and (ii) all applicable laws, regulations, codes of practice and other similar controls, and advice issued by any governmental or regulatory body relating to the protection of the environment, including, without limitation, the prevention or reduction of pollution of any land, water or air (together, the 'Applicable Environmental Laws'); and that all necessary permits, licenses, certificates, approvals and other authorizations required by the Applicable Environmental Laws have been obtained and will be maintained; and no hazardous or toxic materials, substances, pollutants, contaminants or wastes have been or will be released into the environment or deposited, discharged, displaced or disposed of by Vendor, unless in accordance with all Applicable Environmental Laws.  Vendor certifies that its practices are consistent with (i) the commitments made by Pearson under the UN Global Compact relating to Labor Standards, Freedom of Association and Working Conditions, and Anti-Corruption Requirements, and (ii) Pearson Code of Business Conduct, each as set forth at http://www.pearson.com/responsibility/values/code-of-conduct/.";
	private String TAC_DETAIL11="Vendor shall indemnify, defend and hold harmless Pearson and its affiliates, directors, officers, employees and agents ('indemnified parties') from and against any and all loss, damage, cost, charge or expense, including reasonable attorney fees and expenses, for which such indemnified parties may suffer or sustain on account of (i) any default, misrepresentation, noncompliance or breach under this Order by Vendor, its personnel or its agents; (ii) any claim of patent, copyright or trademark infringement by reason of the use or sale of the goods by Pearson, or its sub-purchasers, provided the goods are used as normally intended; or (iii) any injury to, or death of, any persons, or damage to or loss of tangible property, arising out of the performance of this Order by Vendor, its employees, agents or representatives.";
	private String TAC_DETAIL12="Vendor shall not disclose this Order or the substance of the goods and services provided hereunder, including without limitation, any pricing, specifications and quantities contained herein.  Vendor will not use the Pearson name or logo in any matter, without the prior written consent of Pearson.";
	private String TAC_DETAIL13="Vendor shall not assign, delegate, subcontract or transfer any of its obligations hereunder, whether voluntarily, involuntarily, by operation of law, or otherwise, without Pearson's prior written consent.  Any assignment or transfer in violation of this provision shall be null and void. ";
	private String TAC_DETAIL14="THIS ORDER SHALL BE GOVERNED BY THE STATUTES AND COMMON LAW OF THE PROVINCE OF ONTARIO AND THE LAWS OF CANADA APPLICABLE THEREIN, WITHOUT GIVING EFFECT TO ANY CHOICE OR CONFLICT OF LAW PROVISION OR RULE (WHETHER OF THE PROVINCE OF ONTARIO OR ANY OTHER JURISDICTION) THAT WOULD CAUSE THE APPLICATION OF THE LAW OF ANY JURISDICTION OTHER THAN THE PROVINCE OF ONTARIO.  THE VENDOR ACCEPTS, GENERALLY AND UNCONDITIONALLY, JURISDICTION OF ALL DISPUTES HEREUNDER IN THE COURTS SITTING IN THE PROVINCE OF ONTARIO AND ATTORNS TO SUCH JURISDICTION, AND WAIVES ANY DEFENSE OF LACK OF PERSONAL JURISDICTION, LACK OF VENUE OR FORUM NON CONVENIENS (OR THE EQUIVALENT THEREOF) TO AN ACTION IN THOSE FORUMS.";
	
	private String CUSTOM_CLEARANCE_AND_DELIVERY_REQ_1 = "	•  This document outlines our most current requirements for the following areas and will be updated as needed:\n" +
			"	•	Actions/documents needed for customs clearance and delivery to our warehouse.\n" +
			"	•	Packaging - relating to cartons and pallets.\n";
	//private String CUSTOM_CLEAR_AND_DEL_REQ_HEADER = "Customs Clearance and Delivery Requirements";
	private String CUSTOM_CLEARANCE_AND_DELIVERY_REQ_2 = "Original copies of all shipping documentation as outlined in the” Type of document required” section, should be handed over to the freight forwarder or carrier so that its gets to one of the offices of our customs broker. " +
			"Duplicate copies of all required paperwork need to accompany the shipment and are be handed over by the driver to staff in the receiving department upon delivery at the warehouse." +
			"\n" +
			"Please contact us about any shipment you send where you are unable to comply with this requirement and require additional assistance. You need to ensure to attach these instructions to any order which is drop shipped to us from a bindery or third party location." +
			"\nInformation on Canadian customs requirements can be found at:\n";
	//private String Contact_Our_Customs_Broker = "Contact Our Customs Broker \n (with complete original paperwork)\n"; 
	private String 	CUSTOMER_BROKER_CONTACT ="6725 Airport Road \n" +
			"Mississauga, Ontario\n" +
			"L4V 1V2\n" +
			"Contact: Team14\n" +
			"Tel. 1-800-226-1875 Ext. 7259\n" +
			"Fax: (905) 405-8062\n" +
			"Email :";
	
	 
	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		 Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		 Font objFont2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
		 Font headingFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD);
		 Font italicFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 9,Font.BOLD);
		 Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL);
		 Font head = FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD);
		  Font blue = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD);
		 Font blue1 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
		 
		 
		 try {
				
				float width = document.getPageSize().width();
				float height = document.getPageSize().height(); 
				
		        float[] columnDefinitionSize = {33.30F, 33.30F, 33.30F, 33.30f,33.30f,33.30f};
	            Paragraph heading = new Paragraph();
	            HttpSession session = req.getSession();
	            POLine poLine = null;
	            
	            DropshipDTO objDropshipDTO;
	            PurchaseOrderDetailForm poDetailForm = (PurchaseOrderDetailForm)session.getAttribute("orderDetailForm");
	            DropInstDTO objDropInstDTO = null;
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
				heading1.add(new Phrase("Title:" +poDetailForm.getPoHeader().getTitleDesc(),objFont));
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
				/**Dropship Module*/ 
				DropshipDelegate delegate = new DropshipDelegate(); 
	            
				/*objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_PDFDOWNLOAD); 
				//DropInstDTO  objDropInstDTO =(DropInstDTO) objDropInstDTOList.get(0);
				List<DropInstDTO> objDropInstDTOList = objDropInstDTO.getObjDropInstDTOList();*/
				if(poDetailForm.getPoHeader().getDropshipshippinfinfo()!=null){
				Table dropshipInstruction = DropshipInstruction(objFont,poDetailForm);
				document.add(dropshipInstruction);
				}
				if(poDetailForm.getPoHeader().getDropshipDTOList()!=null){
				Table shippingInfoUploaded = ShippingInfoUploaded(objFont,poDetailForm);
				document.add(shippingInfoUploaded);
				}
				/**Dropship Module*/
				
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
					   		desc = ((i+1) + "." +poLine.getProductDescription());
					   		
				    objLineItems = LineItems(objFont,poDetailForm,poLine,desc);
				    objLineItems.setSpacingAfter(10f);
				    objLineItems.setSpacingBefore(10f);
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
               if(poDetailForm.getPoHeader() != null)
               {	   
	                
	                /* Information Regarding Terms & Conditions Section */
	            if(poDetailForm.getPoHeader().getPoNo().startsWith("44")){
	            	PdfPTable table10 = new PdfPTable(1);
                    table10.setSplitLate(false);
                    table10.setWidthPercentage(90f);
                    //table1.setWidths(array);
                    table10.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
                    table10.setSpacingBefore(10);
                    table10.getDefaultCell().setBorderWidth(1);

                    PdfPCell cell1 = new PdfPCell(new Phrase("INSTRUCTIONS ", blue));
                    cell1.setBackgroundColor(new Color(200, 250, 250));
                    table10.addCell(cell1);
                    
                    if(poDetailForm.getPoHeader().getTermsConditions()!=null){
    	                PdfPCell p1 = new PdfPCell(new Phrase(poDetailForm.getPoHeader().getTermsConditions(),dataFont));
    	                //p1.add();
    	                //p1.setSpacingAfter(10f);
    	                table10.addCell(p1);
    	                //document.add(p1);
    	                }
                    if(poDetailForm.getPoHeader().getTermsConditions1()!=null){
                    	PdfPCell p2 = new PdfPCell(new Phrase(poDetailForm.getPoHeader().getTermsConditions1(),dataFont));
    	                //p2.add();
    	                //p2.setSpacingAfter(10f);
    	                table10.addCell(p2);
    	               // document.add(p2);
    	                
    	                
    	                }
                    document.add(table10);
	            	///////
                    PdfPTable table11 = new PdfPTable(1);
                    table11.setSplitLate(false);
                    table11.setWidthPercentage(90f);
                    //table1.setWidths(array);
                    table11.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
                    table11.setSpacingBefore(10);
                    table11.getDefaultCell().setBorderWidth(1);

                    PdfPCell cell = new PdfPCell(new Phrase("TERMS & CONDITIONS ", blue));
                    cell.setBackgroundColor(new Color(200, 250, 250));
                    table11.addCell(cell);
                    
                    cell = new PdfPCell();
                    cell = setTermsAndConditionsPandBPO(cell);
                    table11.addCell(cell);
                    document.add(table11);
	    			
	    			/*PdfPTable table13 = new PdfPTable(4);
	    			table13.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	    			table13.setSpacingBefore(10);
	    			table13.getDefaultCell().setBorderWidth(1);
	    			table13.setWidthPercentage(40f);
	    			table13.getDefaultCell().setBorder(Rectangle.BOX);

	    			cell = new PdfPCell(new Phrase("From: ", headingFont));
	    			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	    			cell.setColspan(1);
	    			cell.setBackgroundColor(CMYKColor.WHITE);
	    			table13.addCell(cell);

	    			cell = new PdfPCell(new Phrase("Pearson Education Canada ", headingFont));
	    			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	    			cell.setColspan(3);
	    			cell.setBackgroundColor(CMYKColor.WHITE);
	    			cell.setNoWrap(true);
	    			Paragraph para = new Paragraph();
			para.add(new Phrase("Pearson Education Canada \n", headingFont));
			para.add(new Phrase("A Division of Pearson Canada Inc.", dataFont));
			cell = new PdfPCell(para);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setColspan(3);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setNoWrap(true);			
			table13.addCell(cell);

			cell = new PdfPCell(new Phrase("To:", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setColspan(1);
			table13.addCell(cell);

			cell = new PdfPCell(new Phrase("Pearson Education Canada Suppliers", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setColspan(3);
			cell.setNoWrap(true);
			table13.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Date:", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setColspan(1);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table13.addCell(cell);

			cell = new PdfPCell(new Phrase("         2012", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setColspan(3);
			table13.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Subject:", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setColspan(1);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table13.addCell(cell);

			cell = new PdfPCell(new Phrase("Goods Inbound Instructions", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setNoWrap(true);
			cell.setColspan(3);
			table13.addCell(cell);
			cellTAC.addElement(table13);*/
			/*URL fileURL1 = getClass().getResource("tick.jpg");
			URL fileURL3 = getClass().getResource("circle.jpg");
			Chunk circleImage = new Chunk(Image.getInstance(fileURL3),0,0);
			Chunk gifImage = new Chunk(Image.getInstance(fileURL1),30,0);
			
			
			PdfPTable table14 = new PdfPTable(1);
			table14.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			table14.setSpacingBefore(10);
			table14.setWidthPercentage(100f);
			
			Paragraph para = new Paragraph();
			para.add("\n\n\n");
			Chunk underline6 = new Chunk("PEARSON CANADA GOODS INBOUND INSTRUCTIONS : UPDATED AS OF March 2012", headingFont);
			underline6.setUnderline(0.1f, -2f);
			para.add(underline6);
			para.add(new Phrase("\n\n", headingFont));
			cell = new PdfPCell(para);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorder(0);
			table14.addCell(cell);
			
			
			Paragraph paraP1 = new Paragraph();
			paraP1.add(circleImage);
			paraP1.add(new Phrase(" This document outlines our most current requirements for the following areas and will be \n\t\t\t\tupdated as needed:\n"));
			paraP1.add(circleImage);
			paraP1.add(new Phrase(" Actions/documents needed for customs clearance and delivery to our warehouse.\n"));
			paraP1.add(circleImage);
			paraP1.add(new Phrase(" Packaging - relating to cartons and pallets.\n"));
			cell = new PdfPCell(paraP1);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table14.addCell(cell);
			
			
			Paragraph paraP = new Paragraph();
			Chunk underline7 = new Chunk("Customs Clearance and Delivery Requirements", headingFont);
			underline7.setUnderline(0.1f, -2f);
			paraP.add(underline7);
			cell = new PdfPCell(paraP);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table14.addCell(cell);
			
			cell = new PdfPCell(new Phrase(CUSTOM_CLEARANCE_AND_DELIVERY_REQ_2, dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);//Added by sourajit for test
			cell.setBorder(Rectangle.NO_BORDER);
			table14.addCell(cell);
			
			Paragraph paragraph = new Paragraph();
		    Anchor anchor1 = new Anchor("http://www.livingstonintl.com/Import_Export.aspx?locale=en-ca&uid=ImportingintoCanada", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE, new Color(0, 0, 255)));
		    anchor1.setReference("http://www.livingstonintl.com/Import_Export.aspx?locale=en-ca&uid=ImportingintoCanada");
		    paragraph.add(anchor1); 
		    cell = new PdfPCell(paragraph);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table14.addCell(cell);
			cellTAC.addElement(table14);
			PdfPTable table15 = new PdfPTable(2);
			table15.setWidthPercentage(80f);
			//table1.setWidths(array);
			table15.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			table15.setSpacingBefore(10);
			table15.getDefaultCell().setBorderWidth(1);
			
			Paragraph para1 = new Paragraph();
			para1.add(new Phrase("Contact Our Customs Broker \n (with complete original paperwork) \n \n", headingFont));
			para1.add(new Phrase("Livingston International\n", italicFont));
			para1.add(new Phrase(CUSTOMER_BROKER_CONTACT, dataFont));
			Anchor anchor2 = new Anchor("CST48214@livingstonintl.com", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE, new Color(0, 0, 255)));
		    anchor2.setReference("CST48214@livingstonintl.com");
			para1.add(anchor2);
		    para1.add(new Phrase("\n \n", headingFont));
			para1.add(new Phrase("Ocean, Air & Land Imports \n ", headingFont));
			para1.add(new Phrase("Tel. 1-800-226-1875 Ext. 6155 \n Fax: (905) 676-1690", dataFont));
			cell = new PdfPCell(para1);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table15.addCell(cell);
			
			Paragraph para2 = new Paragraph();
			para2.add(new Phrase(" Pearson Education Canada \n     (contact for assistance)    ", headingFont));
			para2.add(new Phrase("\n \n", headingFont));
			para2.add(new Phrase("Pearson Education Canada\n", italicFont));
			para2.add(new Phrase(" A Division of Pearson Canada Inc.\n 195 Harry Walker Parkway \n Newmarket, Ontario \n L3Y 7B3 Canada \n Att: Petra Liebmann \n Tel. (905) 853-7888 Ext. 5614 \n Fax  (905) 853-7783 \n Email:", dataFont));
			Anchor anchor3 = new Anchor("Petra.Liebmann@pearsoncanada.com", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE, new Color(0, 0, 255)));
		    anchor3.setReference("Petra.Liebmann@pearsoncanada.com");
			para2.add(anchor3);
			para2.add(new Phrase("\n \n", dataFont));
			cell = new PdfPCell(para2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table15.addCell(cell);
			cellTAC.addElement(table15);
			
			PdfPTable table16 = new PdfPTable(2);
			table16.setWidthPercentage(80f);
			//table1.setWidths(array);
			table16.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			table16.setSpacingBefore(10);
			table16.getDefaultCell().setBorderWidth(1);
			
			Paragraph para3 = new Paragraph();
			para3.add(new Phrase(" Following documents are required with every shipment as any errors / omissions are subject to heavy fines by Canada Customs.\n\n", headingFont));
			para3.add(circleImage);
			para3.add(new Phrase(" Bill of Lading.\n", dataFont));
			para3.add(circleImage);
			para3.add(new Phrase(" Commercial Invoice\n", dataFont));
			para3.add(circleImage);
			para3.add(new Phrase(" Canada Customs Invoice with details for each item:\n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tTitle \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tIsbn \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tQuantity \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tPurchase Order reference \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tUnit Cost \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tCurrency used to calculate unit cost (i.e. US or Canadian dollars) \n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tCountry Of Manufacture/Origin\n", dataFont));
			para3.add(gifImage);
			para3.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tTariff Code \n", dataFont));
			para3.add(circleImage);
			para3.add(new Phrase("Note: The general description “Printed Matter” is no longer accepted by Canada Customs  \n", dataFont));
			para3.add(circleImage);
			para3.add(new Phrase("If your product is not listed in the tariff table below, please contact us for assistance: \n", dataFont));
			para3.add(new Phrase("\n \n", dataFont));
			cell = new PdfPCell(para3);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table16.addCell(cell);
			cellTAC.addElement(table16);
			
			PdfPTable table17 = new PdfPTable(2);
			table17.setWidthPercentage(50f);
			//table1.setWidths(array);
			table17.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			table17.setSpacingBefore(10);
			table17.getDefaultCell().setBorderWidth(1);
			
			cell = new PdfPCell(new Phrase("Description", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(new Color(200, 250, 250));
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Tariff Code", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(new Color(200, 250, 250));
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Textbook – HED", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4901.99.0022", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Textbook - School", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4901.99.0021", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Textbook – Trade", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4901.99.0050", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Access Card", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4901.00.0090", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Printing Paper", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4810.13.9049", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Catalogs", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4911.10.1031", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("CD", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("8524.32.9000", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("Promotional Material", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cell = new PdfPCell(new Phrase("4911.10.9000", dataFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table17.addCell(cell);
			cellTAC.addElement(table17);

			PdfPTable table18 = new PdfPTable(1);
			table18.setWidthPercentage(80f);
			//table1.setWidths(array);
			table18.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			table18.setSpacingBefore(10);
			Paragraph para4 = new Paragraph();
			para4.add(gifImage);
			para4.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tNotation in the main body of the invoice: “This shipment is to be cleared by Livingston \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tInternational.”\n \n", dataFont));
			para4.add(circleImage);
			para4.add(new Phrase(" Fumigation Certificate (if sent on wooden pallets from overseas).\n\n\n", dataFont));
			para4.add(circleImage);
			para4.add(new Phrase(" Packing Slips are to accompany all shipments entering the facility and should contain the following \n\t\t\t\t\tinformation:\n", dataFont));
			para4.add(gifImage);
			para4.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\t ISBN and title of each book pertaining to the PO or shipment and/or manifest #.\n", dataFont));
			para4.add(gifImage);
			para4.add(new Phrase("\t\t\t\t\t\t\t\t\t\t\t\t\tQuantities relating to each title.\n", dataFont));
			para4.add(new Phrase("\n\n\n", dataFont));
			para4.add(new Phrase("Shipping and Warehouse Delivery Address:\n\n", headingFont));
			para4.add(new Phrase("\t\t\t\t\t\t\t\tPearson Education Canada\n", headingFont));
			para4.add(new Phrase("\t\t\t\t\t\t\t\tA Division of Pearson Canada Inc.\n\t\t\t\t\t\t\t\tP195 Harry Walker Parkway\n\t\t\t\t\t\t\t\tPNewmarket, Ontario\n\t\t\t\t\t\t\t\tPL3Y-7B3 Canada\n\t\t\t\t\t\t\t\tPTel (905) 853-7869 Art Rebelo\n\t\t\t\t\t\t\t\tPTel (905) 853-7888 ext. 6271  Diane Spurrell\n\t\t\t\t\t\t\t\tPTel. (905) 853-7888 ext. 5612 Pattie Langford\n\t\t\t\t\t\t\t\tPTel  (905) 853-7888 ext 5646 Receiving\n\n", dataFont));
			para4.add(new Phrase("Delivery\n\n\n", headingFont)); 
			para4.add(new Phrase("Carriers must call and set up a delivery appointment for any shipment that contains more than (2) two pallets via:\n\n\n", dataFont));
			para4.add(new Phrase("\t\t\t\t\t\t\t\tTel (905) 853-7888 ext. 6271  Diane Spurrell\n\n\t\t\t\t\t\t\t\tEmail:\t", dataFont)); 
			Anchor anchor4 = new Anchor("Diane.Spurrel@pearsoncanada.com\n", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE, new Color(0, 0, 255)));
			anchor4.setReference("Diane.Spurrel@pearsoncanada.com");
			para4.add(anchor4);
			para4.add(new Phrase("\n", headingFont)); 
			para4.add(new Phrase("\t\t\t\t\t\t\t\tTel. (905) 853-7888 ext. 5612 Pattie Langford\n\n\t\t\t\t\t\t\t\tEmail:\t", dataFont));
			Anchor anchor5 = new Anchor("Pattie.Langford@pearsoncanada.com\n", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE, new Color(0, 0, 255)));
			anchor5.setReference("Pattie.Langford@pearsoncanada.com");
			para4.add(anchor5);
			para4.add(new Phrase("\n", dataFont)); 
			para4.add(new Phrase("\t\t\t\t\t\t\t\tTel  (905) 853-7888 ext 5646 	 Receiving\n\n\n\n", dataFont)); 
			para4.add(new Phrase("Deliveries can only be set up for between 6:00 AM and 3:00 PM, Monday to Friday, and must be scheduled 24 hours in advance of shipping. During the summer, the Friday cutoff time is 2:00 PM.\n\n\n", dataFont)); 
			//para4.add(new Phrase()); 
			Chunk underline4 = new Chunk("Packaging Requirements\n", headingFont);
			underline4.setUnderline(0.1f, -2f);
			para4.add(underline4);
			//para4.add(new Phrase()); 
			Chunk underline5 = new Chunk("\nCartons \n\n", headingFont);
			underline5.setUnderline(0.1f, -2f);
	        para4.add(underline5);
	        cell = new PdfPCell(para4);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(0);
			table18.addCell(cell);
			
			
			//table18.setWidthPercentage(80f);
			Paragraph para5 = new Paragraph("Size, Weight and Strength of Carton\n\n", headingFont);
			para5.add(new Phrase());
			para5.add(circleImage);
			para5.add(new Phrase("\tMaximum size = 18 ¾’’L x 14’’W x 12 ¼’’D.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tMinimum size = 12’’L x 9’’W x 1’’D.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tNo carton should be deeper than its width or length.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tMaximum weight = 44 lbs.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tMust have bursting strength of 275 lbs or edge crush test of 44 lbs/inch.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tFor bindery shipments only: \n", headingFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tLoose fill, plastic or paper on top of books is not permitted. Sheets of cardboard are acceptable \n\t\t\t\t\tprovided they don’t exceed the thickness of one book.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tAll cartons should have no more and no less than 2 stacks of books.\n", dataFont));
			para5.add(circleImage);
			para5.add(new Phrase("\tIt is crucial to note Pearson Education Canada as the purchaser in box 5 of the Canada Customs \n\t\t\t\t\tInvoice!\n\n", headingFont));
			cell = new PdfPCell(para5);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table18.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase("\n\n\n\n", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table18.addCell(cell);
			
			//table18.setWidthPercentage(80f);
			Paragraph para6 = new Paragraph("Carton Markings\n\n", headingFont);
			/*Chunk underline2 = new Chunk();
			underline2.setUnderline(0.1f, -2f);
	        para6.add(underline2);*/
			/*para6.add(new Phrase());
			para6.add(circleImage);
			para6.add(new Phrase("\tTitle and name of Author.\n", dataFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tISBN\n", dataFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tCarton quantity.\n", dataFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tCarton weight.\n", dataFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tCanadian purchase order number(s).\n", dataFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tPartial carton quantities marked easy identification.\n", headingFont));
			para6.add(circleImage);
			para6.add(new Phrase("\tCartons containing more than one title marked for easy identification.\n", dataFont));
			cell = new PdfPCell(para6);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table18.addCell(cell);
			
			cell = new PdfPCell(new Phrase("\n\n\n\n", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table18.addCell(cell);
			
			//table18.setWidthPercentage(80f);
			Paragraph para7 = new Paragraph();
			/*Chunk underline = new Chunk();
	        underline.setUnderline(0.1f, -2f);
	        para7.add(underline);*/
			/*para7.add(new Phrase("Carton Closure\n\n", headingFont));
			para7.add(circleImage);
			para7.add(new Phrase("\tDo not use staples when sealing cartons\n", dataFont));
			para7.add(circleImage);
			para7.add(new Phrase("\tUse 3’’tape when sealing cartons\n", dataFont));
			para7.add(circleImage);
			para7.add(new Phrase("\tWhen taping, do not cover any stenciling or pre-printed information.\n", dataFont));
			cell = new PdfPCell(para7);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table18.addCell(cell);
			
			
			//table18.setWidthPercentage(80f);
			//table18.setKeepTogether(true);
			cell = new PdfPCell();
			Chunk underline1 = new Chunk("Pallets\n\n",headingFont);
	        underline1.setUnderline(0.1f, -2f);
			cell.addElement(underline1);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table18.addCell(cell);
			
			/*table18.setWidthPercentage(80f);
			table18.setKeepTogether(true);*/
			/*Paragraph para8 = new Paragraph();
			para8.add(circleImage);
			para8.add(new Phrase("\tUse 40’’X 48’’, two-way entry pallets only. Must have a 4’’space between top and bottom boards.", dataFont));
			para8.add(new Phrase("\n\t\t\t\t Must have floor boards.\n",headingFont));
			para8.add(circleImage);
			para8.add(new Phrase("\tWhen only one ISBN is on a pallet, each pallet must be as close to a maximum height of 50” \n\t\t\t\t from the floor as possible, without exceeding the 2,500-pound weight restriction.\n", dataFont));
			para8.add(circleImage);
			para8.add(new Phrase("\tAs cartons are stacked on the pallet, cover the face of the pallet using interlocking layers and \n\t\t\t\t do not allow any overhang on the edge of the pallet.\n", dataFont));
			para8.add(circleImage);
			para8.add(new Phrase("\tPallets must be firmly wrapped using film wrap\n", dataFont));
			cell = new PdfPCell(para8);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table18.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase("\n\n\n\n", headingFont));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setBorder(Rectangle.NO_BORDER);
			table18.addCell(cell);
			cellTAC.addElement(table18);
			table11.addCell(cellTAC);
			//document.add(table11);
			setTermsAndConditionsPandBPO(table11);
			document.add(table11);
		     /*if (lHeaderInfo.getPoMasterOrderNo() != null) {
				cell = new PdfPCell(new Phrase("This order is placed under the Terms and Condtions " +
						"of Pearson Education Master Order No. " + lHeaderInfo.getPoMasterOrderNo(), blue1));
			} else {
				cell = new PdfPCell(new Phrase("This order is placed under the Terms and Condtions " +
						"of Pearson Education Master Order No. ", blue1));
			}
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setBackgroundColor(CMYKColor.WHITE);
			table11.addCell(cell);*/
	                }
	                else{
	                	if(poDetailForm.getPoHeader().getTermsConditions() != null || poDetailForm.getPoHeader().getTermsConditions1() != null){
	                	   	Paragraph p = new Paragraph();
	    	                p.add(new Phrase("TERMS & CONDITIONS :",objFont2));
	    	                document.add(p);
	    	                if(poDetailForm.getPoHeader().getTermsConditions()!=null){
	    	                Paragraph p1 = new Paragraph();
	    	                p1.add(new Phrase(poDetailForm.getPoHeader().getTermsConditions(),objFont));
	    	                p1.setSpacingAfter(10f);
	    	                document.add(p1);
	    	                }
	    	                if(poDetailForm.getPoHeader().getTermsConditions1()!=null){
	    	                Paragraph p2 = new Paragraph();
	    	                p2.add(new Phrase(poDetailForm.getPoHeader().getTermsConditions1(),objFont));
	    	                p2.setSpacingAfter(10f);
	    	                document.add(p2);
	    	                
	    	                
	    	                }
	                	}
	                }
	                
               } 
               
              
               PdfPTable objSuppLineItems = null;
			   POLine poline=null;
			   int lineComp=poLine.getSuppliedCompCollection().size();
			   //System.out.println("........."+lineComp);
			    if(poDetailForm.getPoHeader().getLineItemCollection().size() != 0)
			    {	
			    	if(lineComp != 0)
			    	{
			    	Chunk chunk = new Chunk("Supplied Components - Order ",objFont2);
				    chunk.setBackground(new Color(171,199,227));
				    document.add(chunk);
			    	}		    	
					int suppComp=poDetailForm.getPoHeader().getLineItemCollection().size();
					for(int j=0;j<suppComp;j++)
					{	
			        	poline = (POLine)((Vector)poDetailForm.getPoHeader().getLineItemCollection()).get(j);
			        	objSuppLineItems = suppLineItems(objFont,poDetailForm,poline);
								    
				    objSuppLineItems.setSplitLate(false);
				    objSuppLineItems.setSpacingBefore(15f);
				    objSuppLineItems.setSpacingAfter(5f);
				    document.add(objSuppLineItems);
				    
				    }
			    }
		 
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
	
	/*protected void setDataCell( String headingValue, String textValue, PdfPTable table1) {
		Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL);
		 Font headingFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD);
		Paragraph para = new Paragraph(new Phrase(headingValue, headingFont));
		para.add(new Phrase(textValue, dataFont));
		//para.add("test in ");
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100f);
		PdfPCell c1 = new PdfPCell(para);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBorder(Rectangle.NO_BORDER);
		table.addCell(c1);
		PdfPCell cell = new PdfPCell(para);
		cell.addElement(table);
		table1.addCell(cell);
		
	}
	
	public void setTermsAndConditionsPandBPO(PdfPTable table) throws IOException {
		
			 	setDataCell(TACHeading1,TAC_DETAIL1,table);
				setDataCell(TACHeading2,TAC_DETAIL2,table);
				setDataCell(TACHeading3,TAC_DETAIL3,table);
				setDataCell(TACHeading4,TAC_DETAIL4,table);
				setDataCell(TACHeading5,TAC_DETAIL5,table);
				setDataCell(TACHeading6,TAC_DETAIL6,table);
				setDataCell(TACHeading7,TAC_DETAIL7,table);
				setDataCell(TACHeading8,TAC_DETAIL8,table);
				setDataCell(TACHeading9,TAC_DETAIL9,table);
				setDataCell(TACHeading10,TAC_DETAIL10,table);
				setDataCell(TACHeading11,TAC_DETAIL11,table);
				setDataCell(TACHeading12,TAC_DETAIL12,table);
				setDataCell(TACHeading13,TAC_DETAIL13,table);
				setDataCell(TACHeading14,TAC_DETAIL14,table);
		 
	}*/
	
    protected void setDataCell( String headingValue, String textValue, PdfPCell cell) {
    	Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL);
		Font headingFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD);
        Paragraph para = new Paragraph(new Phrase(headingValue, headingFont));
        para.add(new Phrase(textValue, dataFont));
        PdfPTable table = new PdfPTable(1);
        table.setSplitLate(false);
        table.setWidthPercentage(100f);
        PdfPCell c1 = new PdfPCell(para);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        cell.addElement(table);
  }
  
  public PdfPCell setTermsAndConditionsPandBPO(PdfPCell cell) throws IOException {
        
                    setDataCell(TACHeading1,TAC_DETAIL1,cell);
                    setDataCell(TACHeading2,TAC_DETAIL2,cell);
                    setDataCell(TACHeading3,TAC_DETAIL3,cell);
                    setDataCell(TACHeading4,TAC_DETAIL4,cell);
                    setDataCell(TACHeading5,TAC_DETAIL5,cell);
                    setDataCell(TACHeading6,TAC_DETAIL6,cell);
                    setDataCell(TACHeading7,TAC_DETAIL7,cell);
                    setDataCell(TACHeading8,TAC_DETAIL8,cell);
                    setDataCell(TACHeading9,TAC_DETAIL9,cell);
                    setDataCell(TACHeading10,TAC_DETAIL10,cell);
                    setDataCell(TACHeading11,TAC_DETAIL11,cell);
                    setDataCell(TACHeading12,TAC_DETAIL12,cell);
                    setDataCell(TACHeading13,TAC_DETAIL13,cell);
                    setDataCell(TACHeading14,TAC_DETAIL14,cell);
        return cell;
  }

	
	/*protected void setDataCell( String headingValue, String textValue, PdfPCell cell) {
		Font objFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.NORMAL);
		 Font headingFont = FontFactory.getFont(FontFactory.HELVETICA, 9,Font.BOLD);
		Paragraph para = new Paragraph(new Phrase(headingValue, headingFont));
		para.add(new Phrase(textValue, dataFont));
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100f);
		PdfPCell c1 = new PdfPCell(para);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBorder(Rectangle.NO_BORDER);
		table.addCell(c1);
		cell.addElement(table);
	}
	
	public PdfPCell setTermsAndConditionsPandBPO(PdfPCell cell) throws IOException {
		
				setDataCell(TACHeading1,TAC_DETAIL1,cell);
				setDataCell(TACHeading2,TAC_DETAIL2,cell);
				setDataCell(TACHeading3,TAC_DETAIL3,cell);
				setDataCell(TACHeading4,TAC_DETAIL4,cell);
				setDataCell(TACHeading5,TAC_DETAIL5,cell);
				setDataCell(TACHeading6,TAC_DETAIL6,cell);
				setDataCell(TACHeading7,TAC_DETAIL7,cell);
				setDataCell(TACHeading8,TAC_DETAIL8,cell);
				setDataCell(TACHeading9,TAC_DETAIL9,cell);
				setDataCell(TACHeading10,TAC_DETAIL10,cell);
				setDataCell(TACHeading11,TAC_DETAIL11,cell);
				setDataCell(TACHeading12,TAC_DETAIL12,cell);
				setDataCell(TACHeading13,TAC_DETAIL13,cell);
				setDataCell(TACHeading14,TAC_DETAIL14,cell);
		return cell;
	}*/




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
	    ncell = new Cell(new Phrase("ISBN 10:",objFont));
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
	    ncell = new Cell(new Phrase("PRINT NO:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getPrintNo() != null)
    	{
	    ncell = new Cell(new Phrase(poDetailForm.getPoHeader().getTitleDetail().getPrintNo(),objFont));
    	}
	    ncell.setBackgroundColor(new Color(248, 251, 254));
	    objTable.addCell(ncell);
	    ncell = new Cell(new Phrase("ISBN 13:",objFont));
	    ncell.setBackgroundColor(new Color(236, 245, 253));
	    objTable.addCell(ncell);
	    if(poDetailForm.getPoHeader().getTitleDetail() != null && poDetailForm.getPoHeader().getTitleDetail().getIsbn13() != null)
    	{
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
		 		 
		 
		  if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
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
	            if(poParty.getComments() != null && poParty.getComments1() != null){
	            	String partyComments = (poParty.getComments().replaceAll("<br>","\n"))+ (poParty.getComments1().replaceAll("<br>","\n"));
	            	ncell = new Cell(new Phrase(partyComments,objFont));	
	            //ncell = new Cell(new Phrase(poParty.getComments().replaceAll("<br>","\n"),objFont));
	            }
	            else
   			 	{
	            	ncell = new Cell(new Phrase("",objFont)); 
   				 
   			 	}
	            ncell.setBackgroundColor(new Color(249, 249, 249));
	            ncell.setColspan(3);
	            objTable.addCell(ncell);
			}   
		    if(poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
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
* This method is used Buyer vendor and shipto Details.
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
	String vendorcity = "",vendorpostalcode = "",vendorstate = "",vendorcountryname = "",vendorsan ="",vendorplantcode = "";
	
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
		 if(poParty != null && poParty.getPartyType() != null  && poParty.getPartyType().equals(PIXConstants.VENDOR_ROLE))
		 {        
			 vendor = poParty;
			 if(vendor.getSan() != null)
		     {
		    	 vendorsan = vendor.getSan();
		     }
			 if(vendor.getVendorPlantCode() != null)
		     {
		    	 vendorplantcode = vendor.getVendorPlantCode();
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
		bvscell = new Cell(new Phrase("VENDOR"+ "("+ vendorsan+")-("+ vendorplantcode+ ")",objFont));
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
				    " "+buyeremail+" ",objFont));  */
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

/////////////////////////////////////

//private Table DropshipInstruction(Font objFont,PurchaseOrderDetailForm poDetailForm,DropInstDTO objDropInstDTO) throws AppException
private Table DropshipInstruction(Font objFont,PurchaseOrderDetailForm poDetailForm) throws AppException
{
	Table objTable = null;
	Vector partycontactCollection = null;
	/*DropshipDelegate delegate = new DropshipDelegate(); 
	objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_PDFDOWNLOAD); 
	//DropInstDTO  objDropInstDTO =(DropInstDTO) objDropInstDTOList.get(0);
	List<DropInstDTO> objDropInstDTOList = objDropInstDTO.getObjDropInstDTOList();*/
	
	try
	{
		objTable = new Table(5);
		 float relativeWidths[] = {5f,15f,10f,30f,30f};
		Cell  Sit  = new Cell();
		Sit = new Cell(new Phrase("DROPSHIP INSTRUCTIONS",objFont));
		Sit.setBackgroundColor(new Color(188, 215, 243));
		Sit.setColspan(5);
		objTable.addCell(Sit);
		objTable.setPadding(2);
		objTable.setSpacing(0);
		Cell bvscell = new Cell();
		objTable.setAlignment(Element.ALIGN_LEFT);
	    objTable.setWidth(90f);
	    objTable.setWidths(relativeWidths);
		bvscell.setColspan(3);
		bvscell.setRowspan(1);
		bvscell.setBackgroundColor(new Color(230,242,253));
		bvscell = new Cell(new Phrase("S.No",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Bk#",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Quantity",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Ship To",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Bill Freight To",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		Cell datatablecell = new Cell();
		String ne ="";
		
    	for(int i=0;i<poDetailForm.getPoHeader().getDropshipshippinfinfo().size();i++){
    		String s = new Integer(i+1).toString();
    		datatablecell = new Cell(new Phrase(s,objFont));
    		datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getBknumber().toString()!=null?poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getBknumber().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getTotalQty().toString()!=null?poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getTotalQty().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        String str[] =poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getShipTo().split(",");
	        String completeAddress = null;
	        for(int j = 0 ; j<str.length; j++){
	                        completeAddress = completeAddress != null ? str[j].trim().length() > 0 ? completeAddress+"\n" + str[j]:completeAddress+"":str[j];
	        }

	        datatablecell = new Cell(new Phrase(completeAddress!=null?completeAddress:"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        
	        String str1[] =poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getBillTo().split(",");
	        String completeAddress1 = null;
	        for(int j = 0 ; j<str1.length; j++){
	                        completeAddress1 = completeAddress1!= null ? str1[j].trim().length() > 0 ? completeAddress1+"\n" + str1[j]:completeAddress1+"":str1[j];
	        }

	        datatablecell = new Cell(new Phrase(completeAddress1!=null?completeAddress1:"",objFont));
	        /////
	        //datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getBillTo().toString()!=null?poDetailForm.getPoHeader().getDropshipshippinfinfo().get(i).getBillTo().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        
    	}
        //System.out.println("The Elements of BK# is"+poDetailForm.getPoHeader().getDropshipshippinfinfo().get(0).getBknumber());
        //}
		 
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

////////////////////////////////

private Table ShippingInfoUploaded(Font objFont,PurchaseOrderDetailForm poDetailForm) throws AppException, ParseException
{
	Table objTable = null;
	
    try
	{
		
		
    	objTable = new Table(9);
    	Cell  Sit  = new Cell();
    	float relativeWidths[] = {5f,10f,10f,15f,19f,10f,7f,7f,7f};
    	Sit = new Cell(new Phrase("SHIPPING INFORMATION UPLOADED",objFont));
		Sit.setBackgroundColor(new Color(188, 215, 243));
		Sit.setColspan(9);
		objTable.addCell(Sit);
		objTable.setPadding(2);
		objTable.setSpacing(0);
		Cell bvscell = new Cell();
		objTable.setAlignment(Element.ALIGN_LEFT);
	    objTable.setWidth(90f);
	    objTable.setWidths(relativeWidths);
		bvscell.setColspan(3);
		bvscell.setRowspan(1);
		bvscell.setBackgroundColor(new Color(230,242,253));
		bvscell = new Cell(new Phrase("S.No",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Bk#",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Tracking Number",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Carrier Level",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Shipped To",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Ship Date",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Weight",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Total Carton",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		bvscell = new Cell(new Phrase("Units Per Carton",objFont));
		bvscell.setHorizontalAlignment(Element.ALIGN_CENTER);
		bvscell.setBackgroundColor(new Color(230,242,253));
		objTable.addCell(bvscell);
		
		Cell datatablecell = new Cell();
		String ne ="";
    	for(int i=0;i<poDetailForm.getPoHeader().getDropshipDTOList().size();i++){
    		String s = new Integer(i+1).toString();
    		datatablecell = new Cell(new Phrase(s,objFont));
    		datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getBknumber().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getBknumber().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getTrackingNo().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getTrackingNo().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getCarrierLevel().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getCarrierLevel().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getShippedTo().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getShippedTo().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        Date str =poDetailForm.getPoHeader().getDropshipDTOList().get(i).getShipDate();
	        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); //please notice the capital M
	        String si =  formatter.format(str);
	        //System.out.println("the value of si is:"+si);
	        datatablecell = new Cell(new Phrase(si!=null?si:"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getWeight().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getWeight().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getTotalCartons().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getTotalCartons().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        datatablecell = new Cell(new Phrase(poDetailForm.getPoHeader().getDropshipDTOList().get(i).getUnitPerCarton().toString()!=null?poDetailForm.getPoHeader().getDropshipDTOList().get(i).getUnitPerCarton().toString():"",objFont));
	        datatablecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        datatablecell.setBackgroundColor(new Color(248, 251, 254));
	        objTable.addCell(datatablecell);
	        
    	}
		
	}	
    catch(Exception e)
	{
    	e.printStackTrace();
	}
		
	   /* catch(DocumentException de)
		{
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.ERROR_OCCURED_TOOPEN_PDF, 
		                "PurchaseOrderPdf,display",de);
			   throw appException;
		}*/
	    
	 return objTable;
}

//////////////////////////////////////



/**
* This method is used Line item Details .
* @param Font
* @param PurchaseOrderDetailForm
* @return Table
*/	
private PdfPTable LineItems(Font objFont,PurchaseOrderDetailForm poDetailForm, POLine poLine,String desc) throws AppException
{
	PdfPTable objdatatable = null;
	Font objFont2 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
	POParty shipTo = null;
	try
	{		
		
		
		
		   objdatatable = new PdfPTable(5);
    		//objdatatable.setPadding(1);
    		//objdatatable.setSpacingBefore(0);	
            int headerwidths[] = {10,32,7, 8, 7};
            objdatatable.setWidths(headerwidths);
            objdatatable.setHorizontalAlignment(Element.ALIGN_LEFT);
            objdatatable.setWidthPercentage(90f);	
           // objdatatable.setDefaultCellBorderWidth(1);
           // objdatatable.setDefaultRowspan(1);
            PdfPCell datatablecell = new PdfPCell();
            String statusDescription= null;
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
              datatablecell = new PdfPCell(new Phrase(desc,objFont2));
              datatablecell.setBackgroundColor(new Color(171,199,227));
              datatablecell.setColspan(5);
			  datatablecell.setBorder(0);
				
              objdatatable.addCell(datatablecell);
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
     	          
     	          datatablecell = new PdfPCell(new Phrase("BUYER NOTES:",objFont));
     	          datatablecell.setBackgroundColor(new Color(239,239,239));
     	          objdatatable.addCell(datatablecell);
     	          if(poLine.getPubUnitComments() != null || poLine.getPubUnitComments1() != null)
     	          {
     	        	  String pubUnitComments = "";
     	        	  if(poLine.getPubUnitComments() != null && poLine.getPubUnitComments1() != null)
     	        	  {
     	        		  pubUnitComments = poLine.getPubUnitComments()+ " " + poLine.getPubUnitComments1();
     	        	  }
     	        	  else if(poLine.getPubUnitComments() != null && poLine.getPubUnitComments1() == null)
     	        	  {
     	        		 pubUnitComments = poLine.getPubUnitComments();
     	        	  }
     	        	  else if(poLine.getPubUnitComments() == null && poLine.getPubUnitComments1() != null)
     	        	  {
     	        		 pubUnitComments = poLine.getPubUnitComments1();
     	        	  }
     	        	  datatablecell = new PdfPCell(new Phrase(pubUnitComments,objFont));
     	          }
     	         else
     			 {
     				datatablecell = new PdfPCell(new Phrase("",objFont)); 
     				 
     			 }
	     		 datatablecell.setBackgroundColor(new Color(239,239,239));
		         	 
     			 datatablecell.setColspan(4);
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
		        	 
     			 datatablecell.setColspan(4);
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
*/	
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
			      datatablecell.setHorizontalAlignment(Element.ALIGN_RIGHT);
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
               cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
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
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
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

	
	

}
