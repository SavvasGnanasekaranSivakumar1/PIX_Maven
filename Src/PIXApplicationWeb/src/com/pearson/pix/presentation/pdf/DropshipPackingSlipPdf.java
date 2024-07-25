package com.pearson.pix.presentation.pdf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Graphic;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.pearson.pix.business.dropship.DropShipConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.dropship.DropInstDTO;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.dropship.delegate.DropshipDelegate;
import com.pearson.pix.presentation.dropship.dropshipinstruction.action.DropInstForm;

public class DropshipPackingSlipPdf implements CommonPdfInterface{

	private static Log log = LogFactory.getLog(DropshipPackingSlipPdf.class.getName());
	
	
	private String PEARSON_LEARNING_SOLUTION="Pearson Learning Solution";
	private String PEARSON_LEARNING_SOLUTION_HIGHEREDU="Pearson";
	private String BINDRY_SHIP_PACKING_SLIP="Bindery Ship Packing Slip";
	private String INVOICE_NUMBER="Invoice Number:";
	private String CUSTOMER_PO="Customer PO:";
	private String SHIP_TO="Ship To:";
	private String CARRIER="Carrier:";
	private String FRIEGHT_TERMS="Freight Terms:";
	private String BILL_TO_ACC_NUMBER="Carrier Acct:";
	private String BILL_FRIEGHT_TO="Bill Freight To:";
	private String ADDITIONAL_SHIPPING_INST="Additional Shipping Instructions:";
	private String VENDOR_REFERNCE="Vendor Reference #";
	private String UNIT_PRICE="Unit Price";
	private String TITLE="Title";
	private String ISBN10="ISBN-10";
	private String ISBN13="ISBN-13";
	private String QTY="Quantity";

	public Document display(Document document,HttpServletRequest req) throws AppException
	{
		try{
		
			document.open();
			document.setPageSize(PageSize.LETTER);
			HttpSession session = req.getSession();
			///----  added for user id ---/////
			int userId=0;
			User objUser = null;
			objUser = (User)req.getSession().getAttribute("USER_INFO");
			userId = objUser.getUserId().intValue();
			//Integer userId = (Integer)session.getAttribute("userId");
			DropInstDTO objDropInstDTO = new DropInstDTO();
			objDropInstDTO.setUserId(userId);
			////----//////
			if(session.getAttribute("tab")!=null && session.getAttribute("tab").equals("order")){
				session.removeAttribute("tab");
				if(session.getAttribute("bkNumber")!=null && session.getAttribute("isSchool")!=null){
					
					objDropInstDTO.setBkNumber((String) session.getAttribute("bkNumber"));
				if(session.getAttribute("isSchool").equals("Y"))
					schoolTitlePackingSlip(document,objDropInstDTO);
				else
					titlePackingSlip(document, objDropInstDTO);
				document.close();
				}
			}else{
			DropInstForm objDropInstForm = (DropInstForm) session.getAttribute("dropInstForm");
			if(objDropInstForm.getCheckIndexStr()!=null){
				List<DropInstDTO>  objDropInstDTOList = objDropInstForm.getDropshipdtolist();
				if(objDropInstDTOList != null )
				{
					String checkboxvalues = objDropInstForm.getCheckIndexStr();
					StringTokenizer tokenizer = new StringTokenizer(checkboxvalues, ",");
					if(checkboxvalues != null && checkboxvalues.indexOf(",") != -1)
					{ 
						while (tokenizer.hasMoreTokens())
						{
							String chk=tokenizer.nextToken();
							if(chk!=null&&chk.trim().length()>0)
							{
								 objDropInstDTO = objDropInstDTOList.get(Integer.parseInt(chk.trim()));
								objDropInstDTO.setUserId(userId);
								if(objDropInstDTO.getIsSchool() != null && objDropInstDTO.getIsSchool().equals("Y"))
									schoolTitlePackingSlip(document, objDropInstDTO);
								else
									titlePackingSlip(document, objDropInstDTO);
							}
						}
					}

					document.close();

				}}}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	private void schoolTitlePackingSlip(Document document,DropInstDTO objDropInstDTO) throws Exception
	{
		try{  
			DropshipDelegate delegate = new DropshipDelegate(); 
			objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_PDFDOWNLOAD); 
			//DropInstDTO  objDropInstDTO =(DropInstDTO) objDropInstDTOList.get(0);
			List<DropInstDTO> objDropInstDTOList = objDropInstDTO.getObjDropInstDTOList();
			log.info("schoolTitlePackingSlip getBkNumber "+objDropInstDTO.getBkNumber());
			SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("MMMM dd, yyyy");
			String dateAsString = simpleDateFormat.format(new Date());
			if(objDropInstDTOList == null)
				throw new Exception("Dto Should not null");
			document.newPage();
			PdfPTable innerTbl =null;
			PdfPCell c1=null;

			PdfPTable table=null;
			PdfPCell tblCell=null;
			Font header = FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD);
			Font header10 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD);
			Font header8Normal = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);
			Font header8 = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font header7 = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.BOLD);
			Font content8 = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font content8italic = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.ITALIC);
			Font content8Normal = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);
			Font content7Normal = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.NORMAL);
			Font content10 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.NORMAL);
			Font blue8b = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font blue8n = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);

			/*table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
    
			innerTbl = new PdfPTable(1);
			String businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip();
			if(businessOwnerShip==null){
				businessOwnerShip="";
			}else{
				businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip()+"\n";
			}
			c1 = new PdfPCell(new Phrase(businessOwnerShip,header));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(BINDRY_SHIP_PACKING_SLIP,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(2);
			c1 = new PdfPCell(new Phrase(INVOICE_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);


			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(objDropInstDTO.getBkNumber(),content8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(2);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Phrase(CUSTOMER_PO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Phrase(objDropInstDTOList.get(0).getPoNumber(),content8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(2);
			innerTbl.addCell(c1);
			tblCell=null;
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(2);
			table.addCell(tblCell);
			document.add(table);


			//ship to 


			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			innerTbl = new PdfPTable(7);
			c1 = new PdfPCell(new Phrase(SHIP_TO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			//c1.setColspan(1);
			innerTbl.addCell(c1);

			String name1 = objDropInstDTOList.get(0).getShipToName1();
			if(name1==null){
				name1="";
			}else{
				name1 = objDropInstDTOList.get(0).getShipToName1();
			}

			String address1 = objDropInstDTOList.get(0).getShipToAddress1();
			if(address1==null){
				address1="";
			}
			
			String address2 = objDropInstDTOList.get(0).getShipToAddress2();
			if(address2==null){
				address2="";
			}else{
				address2 = objDropInstDTOList.get(0).getShipToAddress2();
			}

			String address3 = objDropInstDTOList.get(0).getShipTOAddress3();
			if(address3==null){
				address3="";
			}
			
			String address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			if(address4==null){
				address4="";
			}else{
				address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			}
			
			String city = objDropInstDTOList.get(0).getShipTOCity();
			if(city==null){
				city="";
			}

			String state = objDropInstDTOList.get(0).getShipTOState();
			if(state==null){
				state="";
			}
			
			String postal = objDropInstDTOList.get(0).getShipTOPostalCode();
			if(postal==null){
				postal="";
			}
			
			String country = objDropInstDTOList.get(0).getShipToCountry();
			if(country==null){
				country="";
			}
			String str=((address3 != null && address3.length() > 0) &&( address4 != null && address4.length() > 0))? "\n" +address3+","+" "+address4:"";
			c1 = new PdfPCell(new Paragraph(
					name1+","+"\n"+
					address1+","+" "+
					address2+","+" "+
					str+"\n"+
					city+","+" "+
					state+","+" "+
					postal+" "+"\n"+
					country
					,content7Normal));
			
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(7);
			
			innerTbl.addCell(c1);
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);
			tblCell = new PdfPCell(new Paragraph(""));
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);
			document.add(table);
			document.add(new Paragraph( "\n\n\n\n" ));*/
			///////////////////
			table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

			innerTbl = new PdfPTable(1);
			String businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip();
			if(businessOwnerShip==null){
				businessOwnerShip="";
			}else{
				businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip()+"\n";
			}

			c1 = new PdfPCell(new Phrase(businessOwnerShip,header));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(BINDRY_SHIP_PACKING_SLIP,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(1);
			c1 = new PdfPCell(new Phrase(dateAsString ,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);
			document.add(table);

			//Invoice Number and customer PO number
			document.add(new Paragraph( "\n\n" ));
			table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			tblCell = new PdfPCell(new Phrase(""));
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setColspan(3);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(3);
			c1 = new PdfPCell(new Phrase(INVOICE_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);

			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(objDropInstDTO.getBkNumber(),content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);


			c1 = new PdfPCell(new Phrase(CUSTOMER_PO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String ponumber=objDropInstDTOList.get(0).getPoNumber();
			if(ponumber==null)
			{
				ponumber="";
			}
			c1 = new PdfPCell(new Phrase(ponumber,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);
			tblCell=null;
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(3);
			
			table.addCell(tblCell);
			document.add(table);

			//ship to & carrier
			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			innerTbl = new PdfPTable(11);
			c1 = new PdfPCell(new Phrase(SHIP_TO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(2);
			innerTbl.addCell(c1);

			String name1 = objDropInstDTOList.get(0).getShipToName1();
			if(name1==null){
				name1="";
			}else{
				name1 = objDropInstDTOList.get(0).getShipToName1();
			}

			String address1 = objDropInstDTOList.get(0).getShipToAddress1();
			if(address1==null){
				address1="";
			}
			
			String address2 = objDropInstDTOList.get(0).getShipToAddress2();
			if(address2==null){
				address2="";
			}else{
				address2 = objDropInstDTOList.get(0).getShipToAddress2();
			}

			String address3 = objDropInstDTOList.get(0).getShipTOAddress3();
			if(address3==null){
				address3="";
			}
			
			String address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			if(address4==null){
				address4="";
			}else{
				address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			}

			String city = objDropInstDTOList.get(0).getShipTOCity();
			if(city==null){
				city="";
			}

			String state = objDropInstDTOList.get(0).getShipTOState();
			if(state==null){
				state="";
			}
			
			String postal = objDropInstDTOList.get(0).getShipTOPostalCode();
			if(postal==null){
				postal="";
			}
			
			String country = objDropInstDTOList.get(0).getShipToCountry();
			if(country==null){
				country="";
			}
			
			
			address2 = address2 != null && address2.trim().length() > 0 ? address2+"\n" : "";
			address3 = address3 != null && address3.trim().length() > 0 ? address3+"\n" : "";
			//String str=((address3 != null && address3.length() > 0) &&( address4 != null && address4.length() > 0))? "\n" +address3+","+" "+address4:"";
			c1 = new PdfPCell(new Paragraph(
					name1+"\n"+
					address1+"\n"+
					address2+
					address3+
					
					//str+"\n"+
					city+","+" "+
					state+" "+
					postal+" "+"\n"+
					country
					,content7Normal));


			c1.setColspan(10);
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(2);
			table.addCell(tblCell);

			tblCell = new PdfPCell(new Phrase());
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(0);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(3);
			c1 = new PdfPCell(new Phrase(CARRIER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Paragraph(objDropInstDTOList.get(0).getCarrierLevel(),content7Normal ));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Phrase(FRIEGHT_TERMS,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String freightTerms= objDropInstDTOList.get(0).getFreightTerms();
			c1 = new PdfPCell(new Phrase(freightTerms,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);
			
			/*c1 = new PdfPCell(new Phrase( BILL_TO_ACC_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String billtoaccnum= objDropInstDTOList.get(0).getBillToAccNumber();
			c1 = new PdfPCell(new Phrase(billtoaccnum,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);*/

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(2);
			table.addCell(tblCell);
			document.add(table);


			//if(freightTerms != null && freightTerms.equalsIgnoreCase("3RD") ) {
				//bill freight to
				String billName1=objDropInstDTOList.get(0).getBillToName1();	
				if(billName1!=null && StringUtils.isNotEmpty(billName1))
				{
				if(billName1!=null)
				{
					table = new PdfPTable(5);
					table.setWidthPercentage(100f);
					tblCell = new PdfPCell(new Phrase(""));
					tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tblCell.setColspan(3);
					tblCell.setBorder(Rectangle.NO_BORDER);
					table.addCell(tblCell);

					innerTbl = new PdfPTable(3);
					
					
						
						
					
						
					// for third party
					if(billName1==null)
						billName1="";
					String namefreinght = objDropInstDTOList.get(0).getBillToName1();
					if(namefreinght==null){
						namefreinght="";
					}
					if (namefreinght != null && namefreinght.trim().length() > 0) {
						
						c1 = new PdfPCell(new Phrase(BILL_FRIEGHT_TO,header8));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(Rectangle.NO_BORDER);
						innerTbl.addCell(c1);
					

					String addressfreinght1 = objDropInstDTOList.get(0).getBillToAddress1();
					if(addressfreinght1==null){
						addressfreinght1="";
					}
					else{
						addressfreinght1=objDropInstDTOList.get(0).getBillToAddress1()+"\n";

					}
					
					String addressfreinght2 = objDropInstDTOList.get(0).getBillToAddress2();
					if(addressfreinght2==null){
						addressfreinght2="";}
					else{
						addressfreinght2=objDropInstDTOList.get(0).getBillToAddress2()+"\n";
					}

					String addressfreinght3 = objDropInstDTOList.get(0).getBillToAddress3();
					if(addressfreinght3==null){
						addressfreinght3="";
					}
					
					String addressfreinght4 = objDropInstDTOList.get(0).getBillToAddress4();
					if(addressfreinght4==null){
						addressfreinght4="";
					}else{
						addressfreinght4=objDropInstDTOList.get(0).getBillToAddress4()+"\n";
					}

					

					String cityfreinght = objDropInstDTOList.get(0).getBillToCity();
					if(cityfreinght==null){
						cityfreinght="";
					}

					String statefreinght = objDropInstDTOList.get(0).getBillToState();
					if(statefreinght==null){
						statefreinght="";
					}else{
						statefreinght = objDropInstDTOList.get(0).getBillToState()+"\n";
					}

					String countryfreinght = objDropInstDTOList.get(0).getBillToCountry();
					if(countryfreinght==null){
						countryfreinght="";
					}
					String postalfreinght = objDropInstDTOList.get(0).getBillToPostalCode();
					if(postalfreinght==null){
						postalfreinght="";
					}
					c1 = new PdfPCell(new Phrase(
							namefreinght+
							addressfreinght1+" "+
							addressfreinght2+
							addressfreinght3+" "+
							addressfreinght4+
							cityfreinght+""+
							statefreinght+
							countryfreinght+" "+
							postalfreinght

							,content7Normal));
					c1.setColspan(4);
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					innerTbl.addCell(c1);
					

				}




				tblCell = new PdfPCell(innerTbl);
				tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tblCell.setBorder(Rectangle.NO_BORDER);
				tblCell.setColspan(2);
				table.addCell(tblCell);
				//if(freightTerms == null || ( freightTerms != null && freightTerms.toUpperCase().contains("(3rd party billing Recipient)")))
				document.add(table);
//////////////////////////
				 document.add(new Paragraph( "\n\n\n\n" ));
					Graphic hr = new Graphic(); 
					hr.setHorizontalLine(1, 100);
					document.add(hr); 

			//Isbn Desc



			
					table = new PdfPTable(18);
				    table.setWidthPercentage(100f);
				    c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(14);
					table.addCell(c1);
					
					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(8);
					 
					table.addCell(c1);
					
					c1 = new PdfPCell(new Phrase("   For Office Use Only",content8italic));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(3);
					c1.setFixedHeight(20);
					table.addCell(c1);
					document.add(table);
			   
			    table = new PdfPTable(18);
			    table.setWidthPercentage(100f);
			    innerTbl=new PdfPTable(20);
			    
			    c1 = new PdfPCell(new Phrase(QTY,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c1.setBorder(Rectangle.NO_BORDER);
				c1.setColspan(2);
				innerTbl.addCell(c1);
			    
			    c1 = new PdfPCell(new Phrase(ISBN10,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				c1.setColspan(3);
				innerTbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(ISBN13,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				c1.setColspan(3);
				innerTbl.addCell(c1);
				c1 = new PdfPCell(new Phrase(TITLE,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				c1.setColspan(10);
				innerTbl.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(UNIT_PRICE,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c1.setBorder(8);
				c1.setColspan(2);
				innerTbl.addCell(c1);
				tblCell = new PdfPCell(innerTbl);
				tblCell.setColspan(15);
				tblCell.setBorder(Rectangle.NO_BORDER);
				table.addCell(tblCell);
				
				c1 = new PdfPCell(new Phrase(" "+VENDOR_REFERNCE,header8Normal));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				c1.setColspan(3);
				table.addCell(c1);
				
				/* for(Object ob1 :OB)
				{  innerTbl=new PdfPTable(20);
					PackingSlipDTO packingSlipDTOInn=(PackingSlipDTO) ob1;*/
				for(Object ob1 :objDropInstDTOList)
				{
					innerTbl=new PdfPTable(20);
					DropInstDTO objDropInst = (DropInstDTO) ob1;
					
					
					c1 = new PdfPCell(new Phrase(objDropInst.getTotalQty()+"",content7Normal));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setColspan(2);
				    c1.setBorder(Rectangle.NO_BORDER);
					innerTbl.addCell(c1);
					
				    c1 = new PdfPCell(new Phrase(objDropInst.getIsbnNo(),content7Normal));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(3);
					innerTbl.addCell(c1);
					c1 = new PdfPCell(new Phrase(objDropInst.getIsbn13(),content7Normal));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(3);
					innerTbl.addCell(c1);
					c1 = new PdfPCell(new Phrase(objDropInst.getTitle(),content7Normal));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(10);
					innerTbl.addCell(c1);
					c1 = new PdfPCell(new Phrase("$"+objDropInst.getUnitPrice().toString(),content7Normal));
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(2);
					c1.setBorder(8);
					innerTbl.addCell(c1);
					
					tblCell = new PdfPCell(innerTbl);
					tblCell.setColspan(15);
					tblCell.setBorder(Rectangle.NO_BORDER);
					table.addCell(tblCell);
					
					c1 = new PdfPCell(new Phrase("    "+objDropInst.getVendorReferenceNumber(),content7Normal));
					c1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
					c1.setColspan(3);
					c1.setBorder(Rectangle.NO_BORDER);
					table.addCell(c1);  
				} 
				document.add(table);	
					
					
					
					
					
					//////////////////////////////////

			//ship to & carrier
			/*document.add(new Paragraph( "\n" ));
			table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			innerTbl = new PdfPTable(4);
			c1 = new PdfPCell(new Phrase(CARRIER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String carrier=objDropInstDTOList.get(0).getCarrierLevel();
			if(carrier==null)
				carrier="";
			c1 = new PdfPCell(new Paragraph(carrier,content7Normal ));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(6);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(FRIEGHT_TERMS,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			String  freight=objDropInstDTOList.get(0).getFreightTerms();
			if(freight==null)
				freight="";
			c1 = new PdfPCell(new Phrase(freight,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(6);
			innerTbl.addCell(c1);
			
			c1 = new PdfPCell(new Phrase( BILL_TO_ACC_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String billtoaccnum= objDropInstDTOList.get(0).getBillToAccNumber();
			if(billtoaccnum==null)
				billtoaccnum="";
			c1 = new PdfPCell(new Phrase(billtoaccnum,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);


			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			tblCell = new PdfPCell(new Phrase(""));
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);
			document.add(table);

			//if(freight != null && freight.equalsIgnoreCase("3RD") ) {
				//freight term
				String billName1=objDropInstDTOList.get(0).getBillToName1();
				
				if(billName1!=null) {
					document.add(new Paragraph( "\n" ));
					table = new PdfPTable(3);
					table.setWidthPercentage(100f);
					innerTbl = new PdfPTable(1);
											
						c1 = new PdfPCell(new Phrase(BILL_FRIEGHT_TO,header8));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(Rectangle.NO_BORDER);
						innerTbl.addCell(c1);
					
					

					// third party
					if(billName1==null)
						billName1="";
					c1 = new PdfPCell(new Paragraph( billName1,content7Normal ));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setBorder(Rectangle.NO_BORDER);
					c1.setColspan(1);
					innerTbl.addCell(c1);

					tblCell = new PdfPCell(innerTbl);
					tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tblCell.setBorder(Rectangle.NO_BORDER);
					table.addCell(tblCell);

					tblCell = new PdfPCell(new Phrase(""));
					tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tblCell.setBorder(Rectangle.NO_BORDER);
					tblCell.setColspan(2);
					table.addCell(tblCell);
					if(freight== null || ( freight != null && freight.toUpperCase().contains("(3RD)")))
					document.add(table);

				}
			//}
			//additional shipping instruction


			document.add(new Paragraph( "\n\n" ));
			table = new PdfPTable(1);
			table.setWidthPercentage(100f);

			c1 = new PdfPCell(new Phrase(ADDITIONAL_SHIPPING_INST,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			String addShip=objDropInstDTOList.get(0).getAddShippingInstruction();
			if(addShip==null)
				addShip="";
			c1 = new PdfPCell(new Phrase(addShip,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			document.add(table);

*/	
			//additional shipping instruction

			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(1);
			table.setWidthPercentage(100f);

			c1 = new PdfPCell(new Phrase(ADDITIONAL_SHIPPING_INST,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			String shippingInstruction=objDropInstDTOList.get(0).getAddShippingInstruction();

			if(shippingInstruction==null)
				shippingInstruction="";
			c1 = new PdfPCell(new Phrase(shippingInstruction,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			document.add(table);	
				}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" schoolTitlePackingSlip "+e.getMessage());
		}
	}
	private  void titlePackingSlip(Document document,	DropInstDTO objDropInstDTO ) throws Exception
	{
		try{
			DropshipDelegate delegate = new DropshipDelegate(); 
			objDropInstDTO = (DropInstDTO)delegate.executeRequest(objDropInstDTO, DropShipConstants.DROPSHIP_INSTRUTCIONS_PDFDOWNLOAD);
			List<DropInstDTO> objDropInstDTOList = objDropInstDTO.getObjDropInstDTOList();
			log.info("titlePackingSlip getBkNumber "+objDropInstDTO.getBkNumber());
			SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat("MMMM dd, yyyy");
			String dateAsString = simpleDateFormat.format(new Date());
			/*SimpleDateFormat simpleDateFormat1=
				new SimpleDateFormat("MMDDYYYY_HHmmss");
			String dateAsString1 = simpleDateFormat1.format(new Date());*/
			
			
			if(objDropInstDTOList == null)
				throw new Exception("Dto Should not null");
			document.newPage();
			PdfPTable innerTbl =null;
			PdfPCell c1=null;

			PdfPTable table=null;
			PdfPCell tblCell=null;
			Font header = FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD);
			Font header10 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.BOLD);
			Font header8Normal = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);
			Font header8 = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font header7 = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.BOLD);
			Font content8 = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font content8italic = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.ITALIC);
			Font content8Normal = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);
			Font content7Normal = FontFactory.getFont(FontFactory.HELVETICA, 7,Font.NORMAL);
			Font content10 = FontFactory.getFont(FontFactory.HELVETICA, 10,Font.NORMAL);
			Font blue8b = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
			Font blue8n = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.NORMAL);

			table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

			innerTbl = new PdfPTable(1);
			String businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip();
			if(businessOwnerShip==null){
				businessOwnerShip="";
			}else{
				businessOwnerShip = objDropInstDTOList.get(0).getBusinessOwnerShip()+"\n";
			}

			c1 = new PdfPCell(new Phrase(businessOwnerShip,header));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(BINDRY_SHIP_PACKING_SLIP,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(1);
			c1 = new PdfPCell(new Phrase(dateAsString ,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);
			document.add(table);

			//Invoice Number and customer PO number
			document.add(new Paragraph( "\n\n" ));
			table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			tblCell = new PdfPCell(new Phrase(""));
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setColspan(3);
			tblCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(3);
			c1 = new PdfPCell(new Phrase(INVOICE_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);

			innerTbl.addCell(c1);
			c1 = new PdfPCell(new Phrase(objDropInstDTO.getBkNumber(),content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);


			c1 = new PdfPCell(new Phrase(CUSTOMER_PO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String ponumber=objDropInstDTOList.get(0).getPoNumber();
			if(ponumber==null)
			{
				ponumber="";
			}
			c1 = new PdfPCell(new Phrase(ponumber,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);
			tblCell=null;
			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(3);
			
			table.addCell(tblCell);
			document.add(table);

			//ship to & carrier
			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			innerTbl = new PdfPTable(11);
			c1 = new PdfPCell(new Phrase(SHIP_TO,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(2);
			innerTbl.addCell(c1);

			String name1 = objDropInstDTOList.get(0).getShipToName1();
			if(name1==null){
				name1="";
			}else{
				name1 = objDropInstDTOList.get(0).getShipToName1();
			}

			String address1 = objDropInstDTOList.get(0).getShipToAddress1();
			if(address1==null){
				address1="";
			}
			
			String address2 = objDropInstDTOList.get(0).getShipToAddress2();
			if(address2==null){
				address2="";
			}else{
				address2 = objDropInstDTOList.get(0).getShipToAddress2();
			}

			String address3 = objDropInstDTOList.get(0).getShipTOAddress3();
			if(address3==null){
				address3="";
			}
			
			String address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			if(address4==null){
				address4="";
			}else{
				address4 = objDropInstDTOList.get(0).getShipTOAddress4();
			}

			String city = objDropInstDTOList.get(0).getShipTOCity();
			if(city==null){
				city="";
			}

			String state = objDropInstDTOList.get(0).getShipTOState();
			if(state==null){
				state="";
			}
			
			String postal = objDropInstDTOList.get(0).getShipTOPostalCode();
			if(postal==null){
				postal="";
			}
			
			String country = objDropInstDTOList.get(0).getShipToCountry();
			if(country==null){
				country="";
			}
			
			
			
			//String str=((address2 != null && address2.length() > 0) &&( address3 != null && address3.length() > 0))? "\n" +address2+"\n"+address3: "";
			address2 = address2 != null && address2.trim().length() > 0 ? address2+"\n" : "";
			address3 = address3 != null && address3.trim().length() > 0 ? address3+"\n" : "";
			c1 = new PdfPCell(new Paragraph(
					name1+"\n"+
					address1+"\n"+
					address2 +
					address3+
					//str+"\n"+
					city+","+" "+
					state+" "+
					postal+" "+"\n"+
					country
					,content7Normal));
			


			c1.setColspan(10);
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(2);
			table.addCell(tblCell);

			tblCell = new PdfPCell(new Phrase());
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(1);
			table.addCell(tblCell);

			innerTbl = new PdfPTable(3);
			c1 = new PdfPCell(new Phrase(CARRIER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Paragraph(objDropInstDTOList.get(0).getCarrierLevel(),content7Normal ));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);

			c1 = new PdfPCell(new Phrase(FRIEGHT_TERMS,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String freightTerms= objDropInstDTOList.get(0).getFreightTerms();
			c1 = new PdfPCell(new Phrase(freightTerms,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);
			
			/*c1 = new PdfPCell(new Phrase( BILL_TO_ACC_NUMBER,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			innerTbl.addCell(c1);

			String billtoaccnum= objDropInstDTOList.get(0).getBillToAccNumber();
			c1 = new PdfPCell(new Phrase(billtoaccnum,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			innerTbl.addCell(c1);*/

			tblCell = new PdfPCell(innerTbl);
			tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tblCell.setBorder(Rectangle.NO_BORDER);
			tblCell.setColspan(2);
			table.addCell(tblCell);
			document.add(table);


			//if(freightTerms != null && freightTerms.equalsIgnoreCase("3RD") ) {
				//bill freight to
				String billName1=objDropInstDTOList.get(0).getBillToName1();	
				
				if(billName1!=null && StringUtils.isNotEmpty(billName1))
				{
					table = new PdfPTable(5);
					table.setWidthPercentage(100f);
					tblCell = new PdfPCell(new Phrase(""));
					tblCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tblCell.setColspan(3);
					tblCell.setBorder(Rectangle.NO_BORDER);
					table.addCell(tblCell);

					innerTbl = new PdfPTable(3);
						
					// for third party
					if(billName1==null)
						billName1="";
					String namefreinght = objDropInstDTOList.get(0).getBillToName1();
					if(namefreinght==null){
						namefreinght="";
					}
					if (namefreinght != null && namefreinght.trim().length() > 0) {
						
						c1 = new PdfPCell(new Phrase(BILL_FRIEGHT_TO,header8));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(Rectangle.NO_BORDER);
						innerTbl.addCell(c1);
						
						String addressfreinght1 = objDropInstDTOList.get(0).getBillToAddress1();
						if(addressfreinght1==null){
							addressfreinght1="";
						}
						else{
							addressfreinght1=objDropInstDTOList.get(0).getBillToAddress1()+"\n";
	
						}
						
						String addressfreinght2 = objDropInstDTOList.get(0).getBillToAddress2();
						if(addressfreinght2==null){
							addressfreinght2="";}
						else{
							addressfreinght2=objDropInstDTOList.get(0).getBillToAddress2()+"\n";
						}
	
						String addressfreinght3 = objDropInstDTOList.get(0).getBillToAddress3();
						if(addressfreinght3==null){
							addressfreinght3="";
						}
						
						String addressfreinght4 = objDropInstDTOList.get(0).getBillToAddress4();
						if(addressfreinght4==null){
							addressfreinght4="";
						}else{
							addressfreinght4=objDropInstDTOList.get(0).getBillToAddress4()+"\n";
						}
	
						
	
						String cityfreinght = objDropInstDTOList.get(0).getBillToCity();
						if(cityfreinght==null){
							cityfreinght="";
						}
	
						String statefreinght = objDropInstDTOList.get(0).getBillToState();
						if(statefreinght==null){
							statefreinght="";
						}else{
							statefreinght = objDropInstDTOList.get(0).getBillToState()+"\n";
						}
	
						String countryfreinght = objDropInstDTOList.get(0).getBillToCountry();
						if(countryfreinght==null){
							countryfreinght="";
						}
						String postalfreinght = objDropInstDTOList.get(0).getBillToPostalCode();
						if(postalfreinght==null){
							postalfreinght="";
						}
						
						
						c1 = new PdfPCell(new Phrase(
								namefreinght+
								addressfreinght1+" "+
								addressfreinght2+
								addressfreinght3+" "+
								addressfreinght4+
								cityfreinght+""+
								statefreinght+
								countryfreinght+" "+
								postalfreinght
	
								,content7Normal));
						c1.setColspan(4);
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setBorder(Rectangle.NO_BORDER);
						innerTbl.addCell(c1);
					}
					
					tblCell = new PdfPCell(innerTbl);
					tblCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tblCell.setBorder(Rectangle.NO_BORDER);
					tblCell.setColspan(2);
					table.addCell(tblCell);
					document.add(table);
				}
				//if(freightTerms == null || ( freightTerms != null && freightTerms.toUpperCase().contains("(3RD)")))
			//}
			/**Isbn Desc Table Row 
			 * 
			 */
			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(8);
			table.setWidthPercentage(100f);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1 = new PdfPCell(new Phrase(QTY,content8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			//


			c1 = new PdfPCell(new Phrase(ISBN10,header8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase(ISBN13,header8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase(TITLE,header8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase(UNIT_PRICE,header8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			String totalqty=objDropInstDTOList.get(0).getTotalQty().toString();	
			if(totalqty==null)
				totalqty="";
			c1 = new PdfPCell(new Phrase(totalqty,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			String isbnno=objDropInstDTOList.get(0).getIsbnNo();
			if(isbnno==null)
				isbnno="";
			c1 = new PdfPCell(new Phrase(isbnno,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			String isbn13=objDropInstDTOList.get(0).getIsbn13();
			if(isbn13==null)
				isbn13="";
			c1 = new PdfPCell(new Phrase(isbn13,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			String title=objDropInstDTOList.get(0).getTitle();
			if(title==null)
				title="";
			c1 = new PdfPCell(new Phrase(objDropInstDTOList.get(0).getTitle(),content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			c1.setColspan(4);
			table.addCell(c1);

			// unit cost
			c1 = new PdfPCell(new Phrase("$"+objDropInstDTOList.get(0).getUnitPrice()+"",content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			document.add(table);

			//additional shipping instruction

			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(1);
			table.setWidthPercentage(100f);

			c1 = new PdfPCell(new Phrase(ADDITIONAL_SHIPPING_INST,header8));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			String shippingInstruction=objDropInstDTOList.get(0).getAddShippingInstruction();

			if(shippingInstruction==null)
				shippingInstruction="";
			c1 = new PdfPCell(new Phrase(shippingInstruction,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			document.add(table);
			//Vendor Reference no

			document.add(new Paragraph( "\n" ));
			table = new PdfPTable(5);
			table.setWidthPercentage(100f);
			c1 = new PdfPCell(new Phrase(""));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setColspan(2);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			c1 = new PdfPCell(new Phrase(VENDOR_REFERNCE,content8Normal));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c1.setColspan(2);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);

			String vendorRef=objDropInstDTOList.get(0).getVendorReferenceNumber();

			if(vendorRef==null)
				vendorRef="";
			c1 = new PdfPCell(new Phrase(vendorRef,content7Normal));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			table.addCell(c1);
			document.add(table);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" titlePackingSlip "+e.getMessage());
		}
	}
}
