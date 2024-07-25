package com.pearson.pix.dao.fileuploading;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import oracle.toplink.exceptions.TopLinkException;
import oracle.toplink.expressions.Expression;
import oracle.toplink.expressions.ExpressionBuilder;
import oracle.toplink.queryframework.ReadAllQuery;
import oracle.toplink.queryframework.SQLCall;
import oracle.toplink.sessions.Record;
import oracle.toplink.sessions.Session;
import oracle.toplink.sessions.UnitOfWork;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dao.base.BaseDAO;
import com.pearson.pix.dto.common.Country;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.purchaseorder.POParty;
import com.pearson.pix.dto.purchaseorder.POPartyContact;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;


public class FileUploadingDAOImpl extends BaseDAO implements FileUploadingDAO {

	/*public Integer insertDMFileLocation(DeliveryMessageFile dmFiles)throws AppException{
		Integer insertFlag=new Integer(0);
		 Session objSession =  null;
		 UnitOfWork objUnitOfWork = null;
		 //ExpressionBuilder builder = null;
		 try
		  {
			  objSession =  getClientSession();
			  objUnitOfWork = getUnitOfWork(objSession);
			  //builder = new ExpressionBuilder();
			  Object ob=objUnitOfWork.registerObject(dmFiles);
			  System.out.println("ob........."+ob);
			  objUnitOfWork.commit();
		
		  }catch(TopLinkException e)
		  {
			  AppException appException = new AppException();
			  e.printStackTrace();
			  appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
					  "FileUploadingDAOImpl,insertDMFileLocation",e);
			  throw appException;
		  }
		  finally
		  {
			  if(objSession!=null)
			  {
				  objSession.release();
			  }
		  }
		
		return insertFlag;
	}
	*/
	
	
	public Vector getDMFileList(Integer poLineNo,String poNo,Integer msgId)throws AppException{
		
		 Vector filesVec=new Vector();
		 Session objSession =  null;
		 //UnitOfWork objUnitOfWork = null;
		 ExpressionBuilder builder = null;
		 Expression objExpression = null;
		 int i=0;
		 String fileUrl[] = null;
		 Vector fileNewvec = new Vector();
		 //System.out.println("inside FileUploadingDAOImpl msgId is:"+ msgId+"pono "+poNo+"lineno "+poLineNo);
		 try
		  {
			   objSession =  getClientSession();
			   
			  
			    builder = new ExpressionBuilder();
			    if(msgId == null)
				objExpression = builder.get("poNo").equal(poNo).and( builder.get("poLineNo").equal(poLineNo)).and(builder.get("activeFlag").equal("Y"));
			    else
			    objExpression = builder.get("poNo").equal(poNo).and( builder.get("poLineNo").equal(poLineNo)).and(builder.get("activeFlag").equal("Y")).and(builder.get("msgId").equal(msgId));
			    ReadAllQuery query = new ReadAllQuery(DeliveryMessageFile.class);
				query.setSelectionCriteria(objExpression);
				query.addDescendingOrdering("creationDateTime");
				filesVec = (Vector)objSession.executeQuery(query);
			    		
				for(i=0;i<filesVec.size();i++)
				{
					DeliveryMessageFile objPixDeliveryMsgFiles = (DeliveryMessageFile)filesVec.get(i);
					fileUrl = objPixDeliveryMsgFiles.getFileUrl().split("/");
					objPixDeliveryMsgFiles.setFileName(fileUrl[fileUrl.length-1]);
					fileNewvec.add(objPixDeliveryMsgFiles);
					
				}
		
		  }catch(TopLinkException e)
		  {
			  AppException appException = new AppException();
			  e.printStackTrace();
			  appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
					  "FileUploadingDAOImpl,getDMFileList",e);
			  throw appException;
		  }
		  finally
		  {
			  if(objSession!=null)
			  {
				  objSession.release();
			  }
		  }
		
		return filesVec;
	}
	
	public void deleteDMFileLocation(String pono,Integer lineNo,String fileId)throws AppException
	{
		 Session objSession =  null;
		 UnitOfWork objUnitOfWork = null;
		 ExpressionBuilder builder = null;
		 Expression objExpression = null;
		 
		 try
		  {
			   objSession =  getClientSession();
			   Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
			    objUnitOfWork = getUnitOfWork(objSession);
			    builder = new ExpressionBuilder();
				objExpression = builder.get("fileSeq").equal(Integer.valueOf(fileId));
				DeliveryMessageFile objPixDeliveryMsgFiles = (DeliveryMessageFile)objUnitOfWork.readObject(DeliveryMessageFile.class,objExpression);
				objPixDeliveryMsgFiles.setActiveFlag("N");
				objPixDeliveryMsgFiles.setPmsTransactionDate(null);
				objPixDeliveryMsgFiles.setModificationDateTime(today);
				
				objUnitOfWork.commit();
		  }catch(TopLinkException e)
		  {
			  AppException appException = new AppException();
			  e.printStackTrace();
			  appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
					  "FileUploadingDAOImpl,deleteDMFileLocation",e);
			  throw appException;
		  }
		  finally
		  {
			  if(objSession!=null)
			  {
				  objSession.release();
			  }
		  }
		
	}
	
	public Vector getDelMsgFileList(Integer msgId,Integer lineNo, String DMGRMode)throws AppException
	{
		Vector filesVec=new Vector();
		 Session objSession =  null;
		 //UnitOfWork objUnitOfWork = null;
		 ExpressionBuilder builder = null;
		 Expression objExpression = null;
		 int i=0;
		 String fileUrl[] = null;
		 Vector fileNewvec = new Vector();
		 try
		  {
			   objSession =  getClientSession();
			   
			  
			    builder = new ExpressionBuilder();
			    ReadAllQuery query = null;
			    if(DMGRMode != null && DMGRMode.equals("DM"))
			    {
			    	objExpression = builder.get("msgId").equal(msgId).and( builder.get("poLineNo").equal(lineNo)).and(builder.get("activeFlag").equal("Y")); 
					query = new ReadAllQuery(DeliveryMessageFile.class);
					query.setSelectionCriteria(objExpression);
					query.addDescendingOrdering("creationDateTime");
			    }
			    
			    if(DMGRMode != null && DMGRMode.equals("GR"))
			    {
					Vector listVector = null;
					Vector popUpVector = new Vector();
					Record objRecord = null;
					
					
					//	objSession = getClientSession();
						listVector = (Vector) objSession.executeSelectingCall(new SQLCall("select msg_id from pix_GOOD_RECEIPT_line where receipt_id = "+ msgId));
						int msgId1 = 0;
						for(int k = 0; k<listVector.size(); k++)
						{
							objRecord = (Record) listVector.get(k);
							BigDecimal bMsgId = (BigDecimal) objRecord.get("MSG_ID");
							msgId1 = bMsgId.intValue();
//							System.out.println(msgId);
//							System.out.println("Hi");
						}
						objExpression = builder.get("msgId").equal(msgId1).and( builder.get("poLineNo").equal(lineNo)).and(builder.get("activeFlag").equal("Y")); 
						query = new ReadAllQuery(DeliveryMessageFile.class);
						query.setSelectionCriteria(objExpression);
						query.addDescendingOrdering("creationDateTime");

			    }

				
				
				filesVec = (Vector)objSession.executeQuery(query); // query
			    				
				for(i=0;i<filesVec.size();i++)
				{
					DeliveryMessageFile objPixDeliveryMsgFiles = (DeliveryMessageFile)filesVec.get(i);
					fileUrl = objPixDeliveryMsgFiles.getFileUrl().split("/");
					objPixDeliveryMsgFiles.setFileName(fileUrl[fileUrl.length-1]);
					fileNewvec.add(objPixDeliveryMsgFiles);
					//filesVec.add(objPixDeliveryMsgFiles);
				}
		
		  }catch(TopLinkException e)
		  {
			  AppException appException = new AppException();
			  e.printStackTrace();
			  appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
					  "FileUploadingDAOImpl,getDelMsgFileList",e);
			  throw appException;
		  }
		  finally
		  {
			  if(objSession!=null)
			  {
				  objSession.release();
			  }
		  }
		
		return fileNewvec;
	}
	
	
	public String getFileURL(Integer fileId)throws AppException
	{
		String fileUrl="" ;
		 Session objSession =  null;
		 Record objRecord = null;
		 try
		  {
			   objSession =  getClientSession();
			   
			  
			   Vector objAddress = (Vector)objSession.executeSelectingCall(new SQLCall("select file_url from pix_delivery_msg_file where file_id =" + fileId + ""));
			   
			
			      if(objAddress != null && objAddress.size()>0)
				     {
						  objRecord = (Record)objAddress.get(0);
						   fileUrl=(String)objRecord.get("FILE_URL");
					  }
		
		  }catch(TopLinkException e)
		  {
			  AppException appException = new AppException();
			  e.printStackTrace();
			  appException.performErrorAction(Exceptions.SQL_EXCEPTION, 
					  "FileUploadingDAOImpl,getDelMsgFileList",e);
			  throw appException;
		  }
		  finally
		  {
			  if(objSession!=null)
			  {
				  objSession.release();
			  }
		  }
		
		return fileUrl;
	}
	
	
	
	
	
	public Integer updateDMFile(String fileUrl,Integer fileId,Integer msgId,String pono,Integer poLineNo)throws AppException{
		   Integer updateFlag=null ;
		   Connection conn = null;
		   PreparedStatement delMsgFile = null;
		   String updateFileInfo="" ;
		   int index=0;
		  
		   try
		   {
			   conn = getDataSourceConnection();
			   updateFileInfo ="UPDATE PIX_DELIVERY_MSG_FILE SET file_url=? WHERE file_id=? AND msg_id=? AND PO_no=? AND PO_line_no=?" ;
			   delMsgFile = conn.prepareStatement(updateFileInfo);
			   if(delMsgFile!=null){
				   delMsgFile.setString(1,fileUrl);
				   delMsgFile.setInt(2,fileId.intValue());
				   delMsgFile.setInt(3,msgId.intValue());
				   delMsgFile.setString(4,pono);
				   delMsgFile.setInt(5,poLineNo.intValue());
				   index=delMsgFile.executeUpdate();
				   updateFlag=new Integer(index);
			   }
		   }	   
		   catch(SQLException se)
	 	   {
			   System.out.println("eee....sys.."+se);
	 		   AppException ae = new AppException();
	 		    throw ae;
	 	   }
	      finally
		    {
	    	  try{
				   if(conn!=null)
					   conn.close();
	    	  }catch(SQLException se)
	    	   {
	    		   
	    	   }
	        }
		   return updateFlag;  
	   }
	
	
	public Map renameDMFileFolder(String concatMsg,String pono)throws AppException{
		   // Integer updateFlag=null ;
		   Connection conn = null;
		   PreparedStatement delMsgFile = null;
		   PreparedStatement selMsgFile = null;
		   String updateFileInfo="" ;
		   String selectFileInfo="" ;
		   //int index=0;
		   //StringBuffer urlBuffer=null ;
		   LinkedHashMap map=new LinkedHashMap();
		  //  LinkedHashSet msgIdLinkedSet=new LinkedHashSet();
		  //  LinkedHashSet lineNoLinkedSet=new LinkedHashSet();
		   LinkedHashMap aggrMap=new LinkedHashMap();
		   try
		   {
			   conn = getDataSourceConnection();
			   selectFileInfo="select file_url,FILE_ID from PIX_DELIVERY_MSG_FILE  WHERE msg_id=? AND PO_no=? AND PO_line_no=?";
			   updateFileInfo ="UPDATE PIX_DELIVERY_MSG_FILE SET file_url=? WHERE msg_id=? AND PO_no=? AND PO_line_no=? AND FILE_ID=?" ;
			   selMsgFile = conn.prepareStatement(selectFileInfo);
			   delMsgFile = conn.prepareStatement(updateFileInfo);
			   String[] msgArr=concatMsg.split("#");
			   for(int j=0;j<msgArr.length;j++){
				   Vector vc=null;
				   String[] msidLineArr=msgArr[j].split("~");
				  //  msgIdLinkedSet.add(msidLineArr[0]);
				   if(aggrMap.get(msidLineArr[0])!=null){
					    vc=(Vector)aggrMap.get(msidLineArr[0]);
				   }else{
					    vc=new Vector();
				   }
				   vc.add(msidLineArr[1]);
				   aggrMap.put(msidLineArr[0],vc);
				  //  lineNoLinkedSet.add(msidLineArr[1]);
			   }
			   
			   
			   //Iterator it_msgIdLinkedSet=msgIdLinkedSet.iterator();
			   Set setAggrMap= (Set)aggrMap.keySet();
			   Iterator it_setAggrMap=setAggrMap.iterator();
				for(int j=0;j<aggrMap.size();j++){
					String[] msidLineArr=msgArr[j].split("~");
					String msgId=(String)it_setAggrMap.next();
					Vector lineVC=(Vector)aggrMap.get(msgId);
					 LinkedHashMap mapFile=new LinkedHashMap();
					 LinkedHashMap mapLineFile=new LinkedHashMap();
					StringBuffer urlBuffer=new StringBuffer();
					if(msidLineArr.length==2){
						
					
						 if(lineVC!=null){
							 for(int i=0;i<lineVC.size();i++){
							 selMsgFile.setInt(1,Integer.parseInt(msgId));
							 selMsgFile.setString(2,pono);
							 selMsgFile.setInt(3,Integer.parseInt((String)lineVC.get(i)));
							 ResultSet rs=selMsgFile.executeQuery(); 
							 if(rs!=null){
								 while (rs.next()) {
									//  pnot++;
									 String file_url=(String)rs.getString("file_url");
									 BigDecimal file_id=(BigDecimal)rs.getBigDecimal("FILE_ID");
									 urlBuffer.append(file_url);
									 String[] file_urlArr=file_url.split("/");
									 String fileName=file_urlArr[file_urlArr.length-1];
									 urlBuffer.append("#");
									 mapFile.put(file_id,fileName);
								     mapLineFile.put(file_id,lineVC.get(i));
								}
							  }
							 }    
						 }
						 map.put(msgId,urlBuffer);
						 Set set= (Set)mapFile.keySet();
						 Iterator it=set.iterator();
						 
						
						 // for(int i=0;i<lineVC.size();i++){
						 for(int k=0;k<mapFile.size();k++){
						 BigDecimal file_id=(BigDecimal)it.next();
						 String appLineNo=(String)mapLineFile.get(file_id);
						 String fileName=(String)mapFile.get(file_id);
						 String fileUrl="DM_"+pono+"_downloads" ;
						 fileUrl=fileUrl+"/"+"sub_"+msgId+"_"+appLineNo+"/"+fileName;
						

						 if(delMsgFile!=null){
							   delMsgFile.setString(1,fileUrl);
							   delMsgFile.setInt(2,Integer.parseInt(msgId));
							   delMsgFile.setString(3,pono);
							   delMsgFile.setInt(4,Integer.parseInt(appLineNo));
							   delMsgFile.setInt(5,file_id.intValue());
							   delMsgFile.executeUpdate();
							 
						   }
					}
						 
						 
						 
					}
				}
			   
			  
			  
		   }	   
		   catch(SQLException se)
	 	   {
			   System.out.println("eee....sys.."+se);
	 		   AppException ae = new AppException();
	 		    throw ae;
	 	   }
	      finally
		    {
	    	  try{
				   if(conn!=null)
					   conn.close();
	    	  }catch(SQLException se)
	    	   {
	    		   
	    	   }
	        }
		   return map;  
	   }
	   
	// DM popup
	public Vector getDeliveryMsgRollInfo(String pono, String productCode, String lineNo) throws AppException
	{
		Vector listVector = null;
		Vector popUpVector = new Vector();
		Session objSession = null;
		Record objRecord = null;
		DeliveryMessageFile deliveryMessageFile = null;
		SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			objSession = getClientSession();
			listVector = (Vector) objSession
					.executeSelectingCall(new SQLCall(
						
							"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
	                        " pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where " + 
	                        " ppls.po_no = " + "'"+pono+"'" + " and " +
	                        " ppl.product_code =" + "'"+productCode+"'" + " and " +
	                        " ppls.po_id = ppl.po_id and " + 
	                        " ppls.po_version = ppl.po_version " +
	                        " and ppl.po_id = pdml.po_id " +
	                        " and ppl.po_line_no = pdml.po_line_no " +
	                        " and pdml.msg_id = pdmr.msg_id " +
	                        " and pdml.msg_line_no = pdmr.msg_line_no " +
	                        " and ppl.po_line_no = " + "'"+lineNo+"'" +
	                        " order by pdmr.roll_no "));
							
						/*	"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
		                  " pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where " + 
                          " ppls.po_no = " + "'"+pono+"'" + " and " +
                          " ppl.product_code =" + "'"+productCode+"'" + " and " +
                          " ppls.po_id = ppl.po_id and " + 
                          " ppls.po_version = ppl.po_version " +
                          " and ppl.po_id = pdml.po_id " +
                          " and ppl.po_line_no = pdml.po_line_no " +
                          " and ppl.po_version = pdml.po_version " +
                          " and pdml.msg_id = pdmr.msg_id " +
                          " and pdml.msg_line_no = pdmr.msg_line_no " +
                          " and ppl.po_line_no = " + "'"+lineNo+"'" +
                          " order by pdmr.roll_no "));*/
                          
						/*	" pix_delivery_msg_roll pdmr, pix_po_list_summary ppls, pix_po_line ppl where "+
						" ppls.po_no = " + "'"+pono+"'" + " and " +
						" ppl.product_code = " + "'"+productCode+"'" + " and " +
						" ppls.po_id = ppl.po_id and " +
						" ppls.po_version = ppl.po_version" +
						" order by pdmr.roll_no")); */
			
		//	System.out.println(listVector.size());
			System.out.println("pono:"+pono);
			System.out.println("productCode:"+productCode);
			System.out.println("lineNo:"+lineNo);
			
			if (listVector != null && listVector.size() > 0) {
				for (int i = 0; i < listVector.size(); i++) {
					objRecord = (Record) listVector.get(i);
					
					deliveryMessageFile = new DeliveryMessageFile();

					deliveryMessageFile.setRollNumber((String) objRecord.get("ROLL_NO")); // setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
					deliveryMessageFile.setRollWeight((BigDecimal) objRecord.get("QUANTITY"));
					deliveryMessageFile.setDmPostedBy((String) objRecord.get("CREATED_BY"));
					deliveryMessageFile.setDmPostedDate(dateObj.format((Date) objRecord.get("CREATION_DATE_TIME")));
					popUpVector.add(deliveryMessageFile);

				}
				System.out.println(popUpVector);
			}
		return popUpVector;
		} catch (TopLinkException e) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.SQL_EXCEPTION,"FileUploadingDAOImpl,getDeliveryMsgRollInfo", e);
		} finally {
			if (objSession != null) {
				objSession.release();
			}
		}

		return popUpVector; 
	}
	
	// DM History popup
	public Vector getDMHistoryMsgRollInfo(String msgId) throws AppException
	{
		Vector listVector = null;
		Vector popUpVector = new Vector();
		Session objSession = null;
		Record objRecord = null;
		DeliveryMessageFile deliveryMessageFile = null;
		SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			objSession = getClientSession();
			listVector = (Vector) objSession
					.executeSelectingCall(new SQLCall(
							
							"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
                            " pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where "+
                            " pdml.msg_id = " + "'"+msgId+"'" +
                            " and "+
                            " ppls.po_id = ppl.po_id and "+
                            " ppls.po_version = ppl.po_version " +
                            " and ppl.po_id = pdml.po_id " +
                            " and ppl.po_line_no = pdml.po_line_no " +
                            " and pdml.msg_id = pdmr.msg_id" +
                            " and pdml.msg_line_no = pdmr.msg_line_no " +
                            " order by pdmr.roll_no "));
							
/*							"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
							" pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where "+
							" pdml.msg_id = " + "'"+msgId+"'" +
							" and "+
							" ppls.po_id = ppl.po_id and "+
							" ppls.po_version = ppl.po_version " +
							" and ppl.po_id = pdml.po_id " +
							" and ppl.po_line_no = pdml.po_line_no " +
							" and ppl.po_version = pdml.po_version "+
							" and pdml.msg_id = pdmr.msg_id" +
							" and pdml.msg_line_no = pdmr.msg_line_no " +
							" order by pdmr.roll_no "));*/

			System.out.println(listVector.size());
			if (listVector != null && listVector.size() > 0) {
				for (int i = 0; i < listVector.size(); i++) {
					objRecord = (Record) listVector.get(i);
					
					deliveryMessageFile = new DeliveryMessageFile();

					deliveryMessageFile.setRollNumber((String) objRecord.get("ROLL_NO")); // setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
					deliveryMessageFile.setRollWeight((BigDecimal) objRecord.get("QUANTITY"));
					deliveryMessageFile.setDmPostedBy((String) objRecord.get("CREATED_BY"));
					deliveryMessageFile.setDmPostedDate(dateObj.format((Date) objRecord.get("CREATION_DATE_TIME")));
					popUpVector.add(deliveryMessageFile);

				}
				System.out.println(popUpVector);
			}
		return popUpVector;
		} catch (TopLinkException e) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.SQL_EXCEPTION,"FileUploadingDAOImpl,getDeliveryMsgRollInfo", e);
		} finally {
			if (objSession != null) {
				objSession.release();
			}
		}

		return popUpVector; 
	}

	
	// costuser popup
	public Vector getCostUserMsgRollInfo(String msgId, String DMGRMode) throws AppException
	{
		Vector listVector = null;
		Vector popUpVector = new Vector();
		Session objSession = null;
		Record objRecord = null;
		DeliveryMessageFile deliveryMessageFile = null;
		SimpleDateFormat dateObj=new SimpleDateFormat("MM/dd/yyyy");
		String msgIdFlag = null;
		
		try {
			objSession = getClientSession();
			
			if(DMGRMode.equals("DM"))
			{
				msgIdFlag = msgId;
			}
			
			if(DMGRMode.equals("GR"))
			{
				Vector tempVector = null;
				tempVector = (Vector) objSession.executeSelectingCall(new SQLCall("select msg_id from pix_GOOD_RECEIPT_line where receipt_id = "+ msgId));
				int msgId1 = 0;
				for(int k = 0; k<tempVector.size(); k++)
				{
					objRecord = (Record) tempVector.get(k);
					BigDecimal bMsgId = (BigDecimal) objRecord.get("MSG_ID");
					int tempMsgIdFlag = bMsgId.intValue();
					msgIdFlag = Integer.toString(tempMsgIdFlag);
//					System.out.println(msgId);
//					System.out.println("Hi");  Integer.toString(aInt)
				}
				
				
			}
			listVector = (Vector) objSession
			.executeSelectingCall(new SQLCall(
					
					"select pdmr.roll_no, pdmr.quantity, pdmr.created_by, pdmr.creation_date_time from"+
					" pix_delivery_msg_roll pdmr,pix_delivery_msg_line pdml, pix_po_list_summary ppls, pix_po_line ppl where "+
					" pdml.msg_id = " + "'"+msgIdFlag+"'" +
					" and "+
					" ppls.po_id = ppl.po_id and "+
					" ppls.po_version = ppl.po_version " +
					" and ppl.po_id = pdml.po_id " +
					" and ppl.po_line_no = pdml.po_line_no " +
					" and ppl.po_version = pdml.po_version "+
					" and pdml.msg_id = pdmr.msg_id" +
					" and pdml.msg_line_no = pdmr.msg_line_no " +
					" order by pdmr.roll_no "));
			

							
							
			
			System.out.println(listVector.size());
			if (listVector != null && listVector.size() > 0) {
				for (int i = 0; i < listVector.size(); i++) {
					objRecord = (Record) listVector.get(i);
					
					deliveryMessageFile = new DeliveryMessageFile();

					deliveryMessageFile.setRollNumber((String) objRecord.get("ROLL_NO")); // setLineNo((BigDecimal)objRecord.get(PIXConstants.LINE_NO));
					deliveryMessageFile.setRollWeight((BigDecimal) objRecord.get("QUANTITY"));
					deliveryMessageFile.setDmPostedBy((String) objRecord.get("CREATED_BY"));
					deliveryMessageFile.setDmPostedDate(dateObj.format((Date) objRecord.get("CREATION_DATE_TIME")));
					popUpVector.add(deliveryMessageFile);

				}
				System.out.println(popUpVector);
			}
		return popUpVector;
		} catch (TopLinkException e) {
			AppException appException = new AppException();
			appException.performErrorAction(Exceptions.SQL_EXCEPTION,"FileUploadingDAOImpl,getDeliveryMsgRollInfo", e);
		} finally {
			if (objSession != null) {
				objSession.release();
			}
		}

		return popUpVector; 
	}

	
}
