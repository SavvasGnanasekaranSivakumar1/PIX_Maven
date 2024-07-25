package com.pearson.pix.dao.dropship;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.StoredProcedureCall;
import oracle.toplink.sessions.DatabaseRecord;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Cell;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.dropship.ShipConfDTO;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ShipmentConfDetailDAO extends BaseDAO {
	private static Log log = LogFactory.getLog(ShipmentConfDetailDAO.class.getName());

	public List<ShipConfDTO> getShipmentConfDetails(int userId, FormFile fileName) throws Exception {
		List<ShipConfDTO> shipmentConfList = new ArrayList<ShipConfDTO>();

		int pageNo=1;
		try{
			deleteShipmentConfDetails(userId);
			readExcel(userId, fileName);
			validateShipConf(userId);
			shipmentConfList = getShipConfDetail(userId, pageNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return shipmentConfList;
	}

	/**
	 * To validate shipment confirmation data.
	 * @param userId
	 * @throws Exception 
	 */
	public void validateShipConf(int userId) throws Exception {
		Session session = null;
		UnitOfWork unitOfWork=null;
		try{
			if(session==null)
				session =getClientSession();
			if(unitOfWork==null)
				unitOfWork=getUnitOfWork(session);
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("PIX_ETL_ASN_LOAD_EXCEL_PROC");
			call.addNamedArgumentValue("I_USER_ID",userId);
			call.useNamedCursorOutputAsResultSet("O_GET_ASN_EXCEL_CURSOR");
			unitOfWork.executeSelectingCall(call);
			log.info("Validate shipment confimation data: ");
		} catch(Exception e){ 
			e.printStackTrace();
		} finally {
			session.release();
		} 

	}

	/**
	 *  To validate uploaded excel file format.
	 *  Read header and compare it wth template file header.  
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	protected Boolean validateExcelSheet(FormFile fileName) throws Exception {
		boolean flag = false;
		try{
			//InputStream inputStream = new FileInputStream(fileName);
			InputStream inputStream = fileName.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			String headerItems[] = {"Pearson PO" , "BK#", "ISBN-10", "Tracking Number", "Carrier & Level","Destination Location, City, State, Zip)", "Ship Date", "Shipment Weight (Lbs)",  "Total Units Shipped", "Total Cartons Shipped", "Desk Copy", "No of Cartons", "Carton Qty" };
			int countColumns = 0;
			HSSFCell cell = null;
			HSSFRow headerRow = sheet.getRow(0);
			if (headerRow != null) 
			{
				countColumns = headerRow.getPhysicalNumberOfCells();
			}
			ArrayList<String> headerList = new ArrayList<String>();
			for(int i=0 ; i< countColumns; i++) {
				cell=headerRow.getCell((short)i);
				if(cell!=null)
				headerList.add(cell.toString().trim());
			}
			List<String> headerItemsList = Arrays.asList(headerItems);
			if(headerList.size()>0)
			{
				flag = headerList.size() == 11;
				//flag = headerItemsList.containsAll(headerList);
			}
			log.info("Default Header are: "+headerItemsList );
			//log.info("Uploaded file header are:"+ headerList );
		} catch(Exception e){ 
			e.printStackTrace();
			throw e;
		}

		return flag;
	}

	/*	To read excel file content and insert into temporary table. */
	public Boolean readExcel(int userId, FormFile fileName) {
		log.info("Inside readExcel");
		boolean flag = true;
		ArrayList<ShipConfDTO> shipmentConfList = new ArrayList<ShipConfDTO>();
		int rowType;
		Session newSession = null;
		UnitOfWork unitOfWork=null;
		try{
			InputStream inputStream = fileName.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			int countRows = sheet.getPhysicalNumberOfRows();
			log.info("Count Row: "+countRows);
			HSSFCell cell = null;
			ShipConfDTO shipConfDTO = new ShipConfDTO();
			ShipConfDTO oldshipConfDTO = new ShipConfDTO();
			//String truckShipmentFlag = "n";
			for (int j = 1; j < countRows; j++) { // For Loop For Moving through Rows
				HSSFRow row = sheet.getRow(j);
				if (row != null)  {
					int countColumns = row.getPhysicalNumberOfCells();
					log.info(j+" Count Columns: "+countColumns);
					try{
						rowType = checkRowType(row);
						//  skip the blank row.	
						if(rowType == 0)	
							continue;
						else if(rowType == 1){	//Partial Row, copy content frm above row
							shipConfDTO =  oldshipConfDTO.clone();
							//truckShipmentFlag = "y";
							//System.out.println("flag"+shipConfDTO.getTruckShipmentFlag());
						}
						else if(rowType > 1)
						{
							shipConfDTO = new ShipConfDTO();   
							shipConfDTO.setUserId(userId);
							shipConfDTO.setSessionId("12345");
							try{
								cell=row.getCell((short) 0);
								
								if(cell != null && !cell.toString().trim().equals(""))
								{
									/*String poNumber = cell.toString();
									//cell.setCellType(Cell.CELL_TYPE_STRING);
									//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									
									
								 if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
									 poNumber = String.valueOf(Double.valueOf(cell.getNumericCellValue())
					        					.intValue());
										
									 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
										 poNumber = cell.getStringCellValue();
									
					            	if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
					            		poNumber = "";
					            	if(poNumber.length()>10) {
										shipConfDTO.setErrorDesc("'PO NUMBER' IS NOT VALID AND SHOULD BE ONLY 10 DIGIT");
										shipConfDTO.setProcessingFlag("E");
									} else 
										shipConfDTO.setPoNumber(poNumber.trim());
								}*/
							
								/*String poNumber = cell.toString();
							if (cell != null && !poNumber.trim().equals("")) {	
									if(poNumber.length()>10) {
										shipConfDTO.setErrorDesc("'PO Number' is not valid and should be only 10 digit");
										shipConfDTO.setProcessingFlag("E");
									} else 
										shipConfDTO.setPoNumber(poNumber.trim());
								}*/
									String poNumber = cell.toString();
									String errorMsg = null ;
								
							 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								 poNumber = cell.getStringCellValue();	
								 
								 if(poNumber.trim().length()>10) {
										shipConfDTO.setErrorDesc("PO NUMBER SHOULD BE 10 DIGITS LONG ONLY");
										shipConfDTO.setProcessingFlag("E");
										
								 }
								 
								 else
								 {
								 shipConfDTO.setPoNumber(poNumber.trim());
								 }
							 }
							 else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
								 poNumber = "";
			            		 shipConfDTO.setPoNumber(poNumber);
							 }
							 /*else if(poNumber.length()>10) {
									shipConfDTO.setErrorDesc("PO NUMBER SHOULD BE 10 DIGITS LONG ONLY");
									shipConfDTO.setProcessingFlag("E");
						  	 }*/else{
								
								errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD PO NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",  KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
								shipConfDTO.setErrorDesc(errorMsg);
								shipConfDTO.setProcessingFlag("E");
								}
								}
									
									
							} catch(Exception e) {
								e.printStackTrace();
								shipConfDTO.setErrorDesc("KINDLY UPLOAD PO NUMBER IN TEXT FORMAT ONLY");
								shipConfDTO.setProcessingFlag("E");
							}
							
							try{
								cell=row.getCell((short) 1);
								if(cell != null && !cell.toString().trim().equals(""))
								{
									/*String bkNumber = cell.toString();
									//cell.setCellType(Cell.CELL_TYPE_STRING);
									//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									
									
								 if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 
									 bkNumber = String.valueOf(Double.valueOf(cell.getNumericCellValue())
					        					.intValue());
										
									 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) 
										 bkNumber = cell.getStringCellValue();
									
					            	if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
					            		bkNumber = "";
					            	if(bkNumber.length()>13) {
					            		shipConfDTO.setErrorDesc("'BK NUMBER' IS NOT VALID AND SHOULD BE ONLY BE ONLY 13 CHAR");
										shipConfDTO.setProcessingFlag("E");
									} else 
										shipConfDTO.setBkNumber(bkNumber.trim());*/
        								/*String bkNumber = cell.toString();
								if(bkNumber == null)
								{
									shipConfDTO.setBkNumber(" ");
								}
								if (cell != null && !bkNumber.trim().equals("")) {
									//if (!bkNumber.trim().equals("")) {
									if(bkNumber.length()>13) {
										shipConfDTO.setErrorDesc("'BK NUMBER' IS NOT VALID AND SHOULD BE ONLY BE ONLY 13 CHAR");
										shipConfDTO.setProcessingFlag("E");
									} else 
										shipConfDTO.setBkNumber(bkNumber.trim());*/
									String bkNumber = cell.toString();
									String errorMsg = null ;
								
							 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								 bkNumber = cell.getStringCellValue();	
								 if(bkNumber.trim().length()>13) {
										shipConfDTO.setErrorDesc("BK NUMBER SHOULD BE 13 DIGITS LONG ONLY");
										shipConfDTO.setProcessingFlag("E");
								 }
								 else{
								 shipConfDTO.setBkNumber(bkNumber.trim());
							 }
							 }
							 else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
								 bkNumber = "";
			            		 shipConfDTO.setBkNumber(bkNumber);
							 }
							/* else if(bkNumber.length()>13) {
									shipConfDTO.setErrorDesc("BK NUMBER SHOULD BE 13 DIGITS LONG ONLY");
									shipConfDTO.setProcessingFlag("E");
						  	 }*/else{
								
								errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD BK NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",  KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
								shipConfDTO.setErrorDesc(errorMsg);
								shipConfDTO.setProcessingFlag("E");
								}
								}
									
								 }catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD BK NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",'BK NUMBER' IS CORREPTED";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}

								try{
									cell=row.getCell((short) 2);
									
									if (cell != null && !cell.toString().trim().equals("")) {
										String isbn = cell.toString().trim();
										String errorMsg = null ;
										/*if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
											isbn = (String)cell.getStringCellValue();
											if(isbn.trim().length()>10) {
												 errorMsg = shipConfDTO.getErrorDesc()==null ? "ISBN SHOULD BE 10 DIGITS LONG ONLY": shipConfDTO.getErrorDesc()+", ISBN SHOULD BE 10 DIGITS LONG ONLY";
												shipConfDTO.setErrorDesc(errorMsg);
												shipConfDTO.setProcessingFlag("E");
											} else
												shipConfDTO.setIsbn10(isbn.trim());
										}*/
										/*if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
											//isbn = String.valueOf((long)cell.getNumericCellValue());
											isbn = String.valueOf(Double.valueOf(cell.getNumericCellValue())
						        					.intValue());
											
											if(isbn.trim().length()>10) {
												 errorMsg = shipConfDTO.getErrorDesc()==null ? "ISBN SHOULD BE 10 DIGITS LONG ONLY" : shipConfDTO.getErrorDesc()+", ISBN SHOULD BE 10 DIGITS LONG ONLY";
												shipConfDTO.setErrorDesc(errorMsg);
												shipConfDTO.setProcessingFlag("E");
											}*//* else
												shipConfDTO.setIsbn10(isbn.trim());
										} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
											 isbn = "";
						            		 shipConfDTO.setIsbn10(isbn);
										 }	
										else if(isbn.trim().length()>10) {
											shipConfDTO.setErrorDesc("ISBN SHOULD BE 10 DIGITS LONG ONLY");
											shipConfDTO.setProcessingFlag("E");
											}
										else{
											errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD ISBN IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",  KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
											shipConfDTO.setErrorDesc(errorMsg);
											shipConfDTO.setProcessingFlag("E");
									}*/
								
										
									
								 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									 isbn = cell.getStringCellValue();	
									 
									 if(isbn.trim().length()>10) {
											shipConfDTO.setErrorDesc("ISBN SHOULD BE 10 DIGITS LONG ONLY");
											shipConfDTO.setProcessingFlag("E");
									 }
									 else
									 {
									 shipConfDTO.setIsbn10(isbn.trim());
									 }
								 }
								 
								 else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
									 isbn = "";
				            		 shipConfDTO.setIsbn10(isbn);
								 }
								/* else if(isbn.trim().length()>10) {
										shipConfDTO.setErrorDesc("ISBN SHOULD BE 10 DIGITS LONG ONLY");
										shipConfDTO.setProcessingFlag("E");
										}*/
								 
								 else{
									
									errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD ISBN IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",  KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
									}
									
									// End of if   
									
								}
								}
								 catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD ISBN IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD ISBN IN TEXT FORMAT ONLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
								    cell=row.getCell((short) 3);
									if(cell != null && !cell.toString().trim().equals(""))
									{
										String trakingNumber = cell.toString();
										String errorMsg = null ;
										
									/*if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
										
										String errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc();
										shipConfDTO.setErrorDesc(errorMsg);
										shipConfDTO.setProcessingFlag("E");}*/
									
								 if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
									 trakingNumber = cell.getStringCellValue();	
									 if(trakingNumber.trim().length()>25) {
										 errorMsg = shipConfDTO.getErrorDesc()==null ? " TRACKING NUMBER IS NOT VALID": shipConfDTO.getErrorDesc()+", TRACKING NUMBER IS NOT VALID";
										 shipConfDTO.setErrorDesc(errorMsg);
									 }
									 else {
									 shipConfDTO.setTrackingNumber(trakingNumber.trim());
								 }
								 }
								 else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
				            		 trakingNumber = "";
				            		 shipConfDTO.setTrackingNumber(trakingNumber);
								 }
								 /*else if(trakingNumber.trim().length()>25) {
									 errorMsg = shipConfDTO.getErrorDesc()==null ? " TRACKING NUMBER IS NOT VALID": shipConfDTO.getErrorDesc()+", TRACKING NUMBER IS NOT VALID";
									 shipConfDTO.setErrorDesc(errorMsg);
							  	 }*/else{
									
									errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+",  KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
									}
								}

									
								} catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ?  " KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD TRACKING NUMBER IN TEXT FORMAT ONLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
									cell=row.getCell((short) 4);
									if (cell != null && !cell.toString().trim().equals("")) {	
										String carrierLevel = cell.toString();
										if(carrierLevel.trim().length()>50) {
											String errorMsg = shipConfDTO.getErrorDesc()==null ?  "'CARRIER & LEVEL' SHOULD BE 50 CHAR ONLY" : shipConfDTO.getErrorDesc()+", 'CARRIER & LEVEL' SHOULD BE 50 CHAR ONLY";
											shipConfDTO.setErrorDesc(errorMsg);
										} else 
											shipConfDTO.setCarrierLevel(carrierLevel.trim());
									}
								}catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD CARRIER & LEVEL PROPERLY": shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'CARRIER & LEVEL' PROPERLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
									cell=row.getCell((short) 5);
									if (cell != null && !cell.toString().trim().equals("")) {
										String shipTo = cell.toString();
										if(shipTo.trim().length()>500) {
											String errorMsg = shipConfDTO.getErrorDesc()==null ? "DESTINATION VALUE IS EXCEED" : shipConfDTO.getErrorDesc()+",DESTINATION VALUE IS EXCEED";
											shipConfDTO.setErrorDesc(errorMsg);
											shipConfDTO.setProcessingFlag("E");

											shipConfDTO.setShipTo(shipTo.substring(0, 495)+"...");
										} else 
											shipConfDTO.setShipTo(shipTo.trim());
									}
								}catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD 'DESTINATION' PROPERLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'DESTINATION' PROPERLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
									cell=row.getCell((short) 6);
									if (cell != null && !cell.toString().trim().equals("")) {						
										String temp = cell.toString().trim();
										//System.out.println("Date: "+temp);
										if(temp != "") {
											java.util.Date utilDate = new java.util.Date(temp);
											Date shipDate = new Date(utilDate.getTime());
											shipConfDTO.setShipDate(shipDate);
										}
									}
								}catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ?  "KINDLY UPLOAD 'SHIP DATE ' PROPERLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'SHIP DATE ' PROPERLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
									cell=row.getCell((short) 7);
									String errorMsg= null ;
									BigDecimal grossWeight ;
									if (cell != null) 
									
									   {
										shipConfDTO.setGrossWeight(new java.math.BigDecimal(cell.getNumericCellValue()));
									   
									   }
																			
									 
								}catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD SHIPMENT WEIGHT IN NUMBER FORMAT ONLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD SHIPMENT WEIGHT IN NUMBER FORMAT ONLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								try{
									cell=row.getCell((short) 8);
									if (cell != null  && !cell.toString().trim().equals("")) {	
										if(cell.toString().trim().equalsIgnoreCase("Y") || cell.toString().trim().equalsIgnoreCase("N"))
											shipConfDTO.setDeskCopy(cell.toString());
										else {
											String errorMsg = shipConfDTO.getErrorDesc()==null ? "DESK COPY SHOULD BE 'Y' OR 'N' ONLY": shipConfDTO.getErrorDesc()+", DESK COPY SHOULD BE 'Y' OR 'N' ONLY";
											shipConfDTO.setErrorDesc(errorMsg);
											shipConfDTO.setProcessingFlag("E");
										}											
									}
								}catch(Exception e) {
									e.printStackTrace();
									String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD 'DESK COPY' PROPERLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'DESK COPY' PROPERLY";
									shipConfDTO.setErrorDesc(errorMsg);
									shipConfDTO.setProcessingFlag("E");
								}
								oldshipConfDTO = shipConfDTO;
						}
						try {
							cell=row.getCell((short) 9);
							if (cell != null) {	
								String temp = cell.toString().trim();
								if(temp != "")
									shipConfDTO.setTotalCartons(new java.math.BigDecimal(temp));
							}
						}catch(Exception e) {
							e.printStackTrace();
							String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD 'NO OF CARTONS' PROPERLY": shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'NO OF CARTONS' PROPERLY";
							shipConfDTO.setErrorDesc(errorMsg);
							shipConfDTO.setProcessingFlag("E");
						}
						try {
							cell=row.getCell((short) 10);
							if (cell != null) {						
								String temp = cell.toString().trim();
								if(temp != "")
									shipConfDTO.setTotalUnits((new java.math.BigDecimal(temp)));
							}
						}catch(Exception e) {
							e.printStackTrace();
							String errorMsg = shipConfDTO.getErrorDesc()==null ? "KINDLY UPLOAD 'CARTON QTY' PROPERLY" : shipConfDTO.getErrorDesc()+", KINDLY UPLOAD 'CARTON QTY' PROPERLY";
							shipConfDTO.setErrorDesc(errorMsg);
							shipConfDTO.setProcessingFlag("E");
						}
					} catch(Exception e) {
						e.printStackTrace();
						shipConfDTO.setErrorDesc("ROW IS NOT VALID");
						shipConfDTO.setProcessingFlag("E");
					}
					shipConfDTO.setRowId(j+"");
					/*shipConfDTO.setTruckShipmentFlag(truckShipmentFlag);
					System.out.println("flag"+shipConfDTO.getTruckShipmentFlag());*/
					shipmentConfList.add(shipConfDTO);
					//unitOfWork.registerObject(shipConfDTO);
				} else {
					countRows++;
				}
				//System.out.println("Row: "+j);
			}
			log.info("Shipment Conf List size after read: "+shipmentConfList.size());
			if(newSession==null)
				newSession =getClientSession();
			unitOfWork = newSession.getActiveUnitOfWork();
			if (unitOfWork == null) {
				unitOfWork = newSession.acquireUnitOfWork();
			}
			unitOfWork.logMessages();
			log.info("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			unitOfWork.registerAllObjects(shipmentConfList);
			unitOfWork.commit();
			log.info("11111111111111");
			//unitOfWork.readAllObjects(ShipConfDTO.class,expression);
			log.info("insert into DB "+shipmentConfList.size());
		} /* catch(TopLinkException e) {
				e.printStackTrace();
				throw e;
			} */catch(Exception e){ 
				flag = false;
				e.printStackTrace();
			} finally {
				newSession.release();
				//unitOfWork.release();
				log.info("In Finally block");
			} 
			return flag;	
	}

	/**
	 * 
	 * @param row
	 * @return 0(zero) if blank row, return 1 if partial otherwise 2(or gt 1).
	 * @throws Exception 
	 */
	private int checkRowType(HSSFRow row) throws Exception {
		int rowType = 0;
		boolean flag =  true;
		HSSFCell cell=null;
		//int totalColumns = row.getPhysicalNumberOfCells();
		try {
			for(int countColumn=0; countColumn<9 && flag!= false; countColumn++) {
				cell=row.getCell((short)countColumn);
				if (cell != null) {		
					//System.out.println("value: "+cell.toString().trim());
					if(cell.toString().trim() != "") {
						flag = false;
						break;
					}
				} 
			}
		} catch(Exception e) {
			throw e;
		}
		if(flag == true) {
			boolean flagUnit, flagQty;
			flagUnit = (cell=row.getCell((short)9)) != null ? (cell.toString().trim() != "" ? true: false) : false;
			flagQty = (cell=row.getCell((short)10)) != null ? (cell.toString().trim() != "" ? true: false) : false;

			if(flagUnit==false && flagQty==false){
				rowType=0;			// blank Row
			}
			else{
				rowType=1;		// partial row
				
			}
		} else
			rowType = 2;		// normal row 

		log.info("Row Type: "+rowType);
		return rowType;
	}

	public List<ShipConfDTO> getShipConfDetail(int userId, int  pageNo) throws Exception 
	{
		List<ShipConfDTO> shipmentConfList = new ArrayList<ShipConfDTO>();
		Session newSession = null;
		UnitOfWork unitOfWork=null;
		try{
			if(newSession==null)
				newSession =getClientSession();
			if(unitOfWork==null)
				unitOfWork=getUnitOfWork(newSession);
			unitOfWork.logMessages();
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("PIX_ETL_GET_ASN_STATUS_PROC");
			call.addNamedArgumentValue("I_USER_ID",userId);
			call.addNamedArgumentValue("I_PAGINATION",pageNo);
			call.useNamedCursorOutputAsResultSet("O_GET_ASN_STATUS_CURSOR");

			List<DatabaseRecord> resultList=(List<DatabaseRecord>) unitOfWork.executeSelectingCall(call);
			log.info("ShipmentConfList resultList: "+ resultList );
			if(!resultList.isEmpty()){
				ShipConfDTO shipConfDTO = null;
				for (DatabaseRecord dbRecord : resultList) {
					shipConfDTO = new ShipConfDTO();
					shipConfDTO.setPoNumber((String)dbRecord.get("PO_NO"));
					shipConfDTO.setBkNumber((String)dbRecord.get("BK_NUMBER"));
					System.out.println("bk num>>>>>>>"+shipConfDTO.getBkNumber());
					shipConfDTO.setCarrierLevel((String)dbRecord.get("CARRIER_LEVEL"));
					shipConfDTO.setDeskCopy((String)dbRecord.get("DESK_COPY"));
					shipConfDTO.setErrorDesc((String)dbRecord.get("ERROR_DESCRIPTION"));
					shipConfDTO.setGrossWeight((java.math.BigDecimal)dbRecord.get("GROSS_WEIGHT"));
					System.out.println("gross>>>>>>>>>>>>>>>>>"+shipConfDTO.getGrossWeight());
					shipConfDTO.setIsbn10((String)dbRecord.get("ISBN10"));
					shipConfDTO.setProcessingFlag((String)dbRecord.get("PROCESSING_FLAG"));
					System.out.println("flag>>>>>>>"+shipConfDTO.getProcessingFlag());
					shipConfDTO.setShipDate((java.util.Date)dbRecord.get("SHIP_DATE"));
					shipConfDTO.setShipTo((String)dbRecord.get("SHIP_TO"));
					shipConfDTO.setTotalCartons((java.math.BigDecimal)dbRecord.get("TOTAL_CARTONS_SHIPPED"));
					System.out.println("total caton>>>>>>"+shipConfDTO.getTotalCartons());
					shipConfDTO.setTotalUnits((java.math.BigDecimal)dbRecord.get("UNITS_PER_CARTON"));
					System.out.println("unit carton>>>>>>>"+shipConfDTO.getTotalUnits());
					shipConfDTO.setTrackingNumber((String)dbRecord.get("TRACKING_NUMBER"));
					System.out.println("tracking no >>>>>>>>>"+shipConfDTO.getTrackingNumber());
//					Object trackingNum = dbRecord.get("TRACKING_NUMBER");
//					if(trackingNum != null) {
//					shipConfDTO.setTrackingNumber(new java.math.BigDecimal(trackingNum.toString()));
//					}		
					shipmentConfList.add(shipConfDTO);
				}
			}
			log.info("ShipmentConfList List size:"+ shipmentConfList.size() );
		} /*catch(TopLinkException e) {
			e.printStackTrace();
			throw e;
		}*/ catch(Exception e){ 
			log.info("TopLinkException"+e);
			e.printStackTrace();
		} finally {
			if(newSession!=null) {
				newSession.release();
			}
		} 
		return shipmentConfList;
	}

	public Integer deleteShipmentConfDetails(int userId) throws Exception {
		List list = null;
		Session newSession = null;
		UnitOfWork unitOfWork=null;
		try{
			//if(newSession==null)
			newSession =getClientSession();
			if(unitOfWork==null)
				unitOfWork=getUnitOfWork(newSession);
			Expression expression = new ExpressionBuilder().get("userId").equal(userId);
			list = newSession.readAllObjects(ShipConfDTO.class,expression);
			unitOfWork.deleteAllObjects(list);
			unitOfWork.commit();
			log.info("delete data from ShipConfDTO ");
		} catch(TopLinkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e){ 
			e.printStackTrace();
			throw e;
		} finally {
			if(newSession!=null)
			{
				newSession.release();
			}
		} 
		return list.size();
	}

	public Object confirmShipmentConfDetails(int userId) throws Exception {
		log.info("confirm data from ShipConfDTO ");
		Session newSession = null;
		UnitOfWork unitOfWork=null;
		try{
			if(newSession==null)
				newSession =getClientSession();
			if(unitOfWork==null)
				unitOfWork=getUnitOfWork(newSession);
			StoredProcedureCall call = new StoredProcedureCall();

			call.setProcedureName("PIX_ETL_ASN_LOAD_PIX_PROC");
			call.addNamedArgumentValue("I_USER_ID",userId);
			//call.addNamedArgumentValue("O_ERROR_TEXT","E");
			call.useNamedCursorOutputAsResultSet("O_GET_ASN_PIX_CURSOR");

			Object resultList= unitOfWork.executeSelectingCall(call);
			log.info("shipment confimation data: "+resultList);
		} catch(TopLinkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e){ 
			e.printStackTrace();
			throw e;
		} finally {
			if(newSession!=null)
			{
				newSession.release();
			}
		} 
		return null;
	}

	public int totalShipConfDetail(int userId) throws Exception {

		int total =0;
		Session newSession = null;
		UnitOfWork unitOfWork=null;
		try{
			if(newSession==null)
				newSession =getClientSession();
			if(unitOfWork==null)
				unitOfWork=getUnitOfWork(newSession);
			//newSession.executeNonSelectingCall(new SQLCall("SET CHAINED OFF"));
			Expression expression=new ExpressionBuilder().get("userId").equal(userId);
			//Expression expression=exp1.and(exp2);
			List<ShipConfDTO> shipmentConfList=(List<ShipConfDTO>)newSession.readAllObjects(ShipConfDTO.class,expression);
			total = shipmentConfList.size();

		} catch(TopLinkException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e){ 
			e.printStackTrace();
			throw e;
		} finally {
			if(newSession!=null)
			{
				newSession.release();
			}
		} 
		return total;
	}
}