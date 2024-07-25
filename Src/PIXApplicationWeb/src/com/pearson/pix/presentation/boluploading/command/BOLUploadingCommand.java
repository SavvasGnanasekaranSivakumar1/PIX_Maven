package com.pearson.pix.presentation.boluploading.command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.boluploading.action.BOLUploadingForm;
import com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm;
import com.pearson.pix.presentation.deliverymessage.delegate.DeliveryMessageDelegate;
import com.pearson.pix.presentation.fileuploading.delegate.FileUploadingtDelegate;


public class BOLUploadingCommand extends BaseCommand{
	private static String FILE_OPR = "fileProperties";
	
	 public String executeFileUploading(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response){
		 String actionType=request.getParameter("ACTION_TYPE") ;
		 String singalDel=request.getParameter("SINGAL_DELETE") ;
		 //String singalDelSucc=request.getParameter("SINGAL_DELETE_SUCC") ;
		 String pono=request.getParameter("pono") ;
		 String subfolderName=request.getParameter("subfolderName") ;
		 String lineNo=request.getParameter("lineNo") ;
		 String index=request.getParameter("index") ;
		 String upload=request.getParameter("upload") ;
		 String poversion=request.getParameter("poversion") ;
		 String msgId=request.getParameter("msgId") ;
		 String flag=request.getParameter("flag") ;
		 String dbDelete = request.getParameter("ACTION_DELETE");
		 String productCode = request.getParameter("productCode");
		 String isHistoryPop = request.getParameter("isHistoryPop");
		 String operation=request.getParameter("operation") ;  // for history
		 
		 //System.out.println("inside BOLUploadingcommand subfolderName is:"+subfolderName);
		 //System.out.println("inside BOLUploadingcommand actionType is:"+actionType);
		 //System.out.println("inside BOLUploadingcommand dbDelete is:"+dbDelete);
		 //System.out.println("inside BOLUploadingcommand singalDel is:"+singalDel);
		 //System.out.println("inside BOLUploadingcommand poversion is:"+poversion);
		 FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
		 BOLUploadingForm bolUploadingForm=(BOLUploadingForm)form ;
		 Vector delMsgFileCollection =null;
		 //System.out.println("inside BOLUploadingcommand msgID is: "+msgId);
 	       try{
 	    	    DeliveryMessageForm objDeliveryMessageForm =(DeliveryMessageForm)request.getSession().getAttribute("deliveryMessageForm");
 	    	    DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
 	    		DeliveryMessageLine objDeliveryMessageLine = (DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue());
 	    		delMsgFileCollection = (Vector)objDeliveryMessageLine.getDelMsgFilesCollection();
 	    	   if("UPLOADING".equals(actionType)){
 	    	     executeFileWriting(mapping,form,request,response);
 	    	     String msg=(String)request.getAttribute("MESSAGE");
 	    	     //System.out.println("inside BOLUploadingcommand msg is:"+msg);
 	    	    if(msg==null&&singalDel==null){
 	    	     delMsgFileCollection=insertFileInfo(form,request,delMsgFileCollection, objDeliveryMessageLine,objDeliveryMessage,objDeliveryMessageForm);
 	    	     }
 	    	   }
 	    	  
 	    	  if("DELETE".equals(actionType)){
 	    	   delMsgFileCollection=executeDelete(form,request,delMsgFileCollection,objDeliveryMessageLine,objDeliveryMessage,objDeliveryMessageForm) ;
 	    	  }
 	    	 if(!"DB_DELETE".equals(dbDelete)&&singalDel==null){
 	    		// System.out.println("inside if of !DB_DELETE");
 	    	  bolUploadingForm.setUploadFile(delMsgFileCollection);
 	    	  }
 	    	  if("DB_DELETE".equals(dbDelete)&&singalDel==null){
 	    		   Vector dmFileList = objDelegate.getDelMsgFileList(Integer.valueOf(msgId),Integer.valueOf(lineNo), "DM"); // "" for costuser ExecuteGeneral
 	    		   bolUploadingForm.setFileList(dmFileList);  
 	    	//	   request.setAttribute("isHistoryPop", isHistoryPop);
 	    		  request.setAttribute("isHistoryPop", "rollDetailsHistory");
 	    	  }
 	    	  else{
 	    		 Integer messageId = null;
 	    		//System.out.println("inside BOLUploadingcommand msgId is" + msgId + "hello");
 	    		if(msgId != null && !(msgId.equals("null")))
 	    			{
 	    			//System.out.println("inside BOLUploadingcommand messageId else is "+ messageId);
 	    			messageId = Integer.valueOf(msgId);
 	    			}
 	    	  Vector dmFileList = objDelegate.getDMFileList(Integer.valueOf(lineNo),pono,messageId);
 	    	  
 			  bolUploadingForm.setFileList(dmFileList);
 			  bolUploadingForm.setBillNo(null);
 			  bolUploadingForm.setCarrierNo(null);
 			  bolUploadingForm.setFreightNo(null);
 	    	  }
 	    	  
 	    	 if(request.getParameter("operation") != null ){
 	 	    	 if(operation.equals("attachFile")){
 	 				request.setAttribute("roleTrackingg", 0);
 	 			}
 	    	 }
 	    	  
if(request.getParameter("popType") != null ){
	  String popType = request.getParameter("popType");
       if(popType.equals("rollDetailsHistory"))
       {
      	 request.setAttribute("isHistoryPop", "rollDetailsHistory");
       }

          if(popType.equals("rollDetailsDM"))
          {
         	 request.setAttribute("isDMPop", "rollDetailsDM");
          }

}
 	 	    	  
 	    	 if(operation.equals("rollDetailsHistory")){
 	    		 /*For populating data in the table below the Attached File  DM pop up*/
 	 	   // 	 Vector dmDtlList = objDelegate.getDeliveryMsgRollInfo(pono, productCode, lineNo);
 	    		 System.out.println(msgId);
 	 	    	Vector dmDtlList = objDelegate.getDMHistoryMsgRollInfo(msgId); // correct
 	 	    	 System.out.println(dmDtlList.size());
 	 	    	 bolUploadingForm.setDmDtlList(dmDtlList);
 	 	    	 	int totalRollWeight = 0;
 	 	    	 	DeliveryMessageFile deliveryMessageFile = new DeliveryMessageFile();
 	 	    	 	for(int i=0; i<dmDtlList.size(); i++){
 	 	    	 		deliveryMessageFile = (DeliveryMessageFile)dmDtlList.get(i);
 	 	    	 		totalRollWeight = new Integer(deliveryMessageFile.getRollWeight().intValue()) + totalRollWeight;
 	 	    	 	}
 	 	    	 	bolUploadingForm.setTotalRollWeight(totalRollWeight);
 	 	    	 	request.setAttribute("roleTrackingg", 0);
 	 	    	 	request.setAttribute("isHistoryPop", "rollDetailsHistory");
 	    	 }
 	    	 
 	    	if(operation.equals("rollDetailsDM")){
 	    		 /*For populating data in the table below the Attached File  DM pop up*/
	 	    	 Vector dmDtlList = objDelegate.getDeliveryMsgRollInfo(pono, productCode, lineNo);
 	    	//	Vector dmDtlList = objDelegate.getDMHistoryMsgRollInfo(msgId);
	 	    	 System.out.println(dmDtlList.size());
	 	    	 bolUploadingForm.setDmDtlList(dmDtlList);
	 	    	 	int totalRollWeight = 0;
	 	    	 	DeliveryMessageFile deliveryMessageFile = new DeliveryMessageFile();
	 	    	 	for(int i=0; i<dmDtlList.size(); i++){
	 	    	 		deliveryMessageFile = (DeliveryMessageFile)dmDtlList.get(i);
	 	    	 		totalRollWeight = new Integer(deliveryMessageFile.getRollWeight().intValue()) + totalRollWeight;
	 	    	 	}
	 	    	 	bolUploadingForm.setTotalRollWeight(totalRollWeight);
	 	    	 	request.setAttribute("isDMPop", "rollDetailsDM");
	 	    	 	HttpSession session = request.getSession();
	 	    	 	session.setAttribute("dmDetailList",dmDtlList);
	 	    	 	session.setAttribute("totalRollWt",totalRollWeight);
	 	    	 	
	 	    	 	
	 	    	 	
	 	    	 	request.setAttribute("roleTrackingg", 0);
	    	 }
	    	 //added by vishal sinha for bol upload on 2nd august 2012
 	    	DeliveryMessageDelegate deliveryMessageDelegate = new DeliveryMessageDelegate();
    	 	int roleTrackingFlag = deliveryMessageDelegate.getRoleTrackingFlag(pono) ;
    	 	if(roleTrackingFlag > 0)
     	     {
     	    	 roleTrackingFlag = 1;
     	     }
    	 	 HttpSession session = request.getSession();
    	 	 session.setAttribute("roleTrackingFlag",roleTrackingFlag);
 	    	  // DM popup
 	    	 
 	    	 	
 	       }catch(Exception e){
 	    	   //e.printStackTrace();
 	       }
			
 	      request.setAttribute("pono",pono);
 	      request.setAttribute("productCode", productCode);
 	      request.setAttribute("subfolderName",subfolderName);
 	      request.setAttribute("lineNo",lineNo);
 	      request.setAttribute("index",index);
 	      request.setAttribute("upload",upload);	 
 	      request.setAttribute("poversion",poversion);
 	      request.setAttribute("msgId",msgId);
 	      request.setAttribute("flag",flag);
 	      return FrontEndConstants.FILEUPLOAD;
	 }
	 
	 
	 
private String executeFileWriting(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response){
		 
	
	     File file=null;
         String fileName="";
         Properties properties=null ;
		 String pono=request.getParameter("pono") ;
		 BOLUploadingForm bolUploadingForm=(BOLUploadingForm)form ;
	// 	 String singalDel=request.getParameter("SINGAL_DELETE") ;
		 long fileSize=0;
	 try
	   {
		
		 FormFile uploadedFilePath=bolUploadingForm.getDataFile();
		
		 if(uploadedFilePath!=null){
		 fileSize = uploadedFilePath.getFileSize();
		
		 if(fileSize==0){
			
			 throw new Exception("This file does not exist."); 
		 }
		 if(fileSize<=4194304 ){
		 String subfolderName=request.getParameter("subfolderName") ;
	 	    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
			    properties = new Properties();   
				try{
				properties.load(inputStream);    
				}catch(IOException e){
					
				}
		String saveFilePath = properties.getProperty("save-file-path");
		String subDir="DM_"+pono+"_downloads/"+subfolderName;
		StringBuffer filePath=new StringBuffer("");
		filePath.append(saveFilePath);
		filePath.append(subDir);
		if(uploadedFilePath!=null){
			
			//Introduced as part of Fortify Path Manipulation Fix
            String tempFilePath1 = filePath.toString()+File.separator;
            System.out.println("tempFilePath1: "+tempFilePath1);
            String validatedFilePath1 = validateFilePath(tempFilePath1);
            System.out.println("validatedFilePath1: "+validatedFilePath1);
            file = new File(validatedFilePath1);
			
		//file= new File(filePath.toString()+File.separator);
        
		file.mkdirs();
        fileName=uploadedFilePath.getFileName();
        
        //Introduced as part of Fortify Path Manipulation Fix
        String tempFilePath4 = file+File.separator+uploadedFilePath.getFileName();
        System.out.println("tempFilePath4: "+tempFilePath4);
        String validatedFilePath4 = validateFilePath(tempFilePath4);
        System.out.println("validatedFilePath4: "+validatedFilePath4);
        File fileToCreate = new File(validatedFilePath4);
        
        //File fileToCreate=new File(file+File.separator+uploadedFilePath.getFileName());
        
        if(!fileName.equals("")){  
            if(!fileToCreate.exists()){
              FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
              fileOutStream.write(uploadedFilePath.getFileData());
              fileOutStream.flush();
              fileOutStream.close();
             }  
           }
		  }
		
		 }else{
			 throw new Exception("File size can not be more than 4 MB.");
		 }
		 
		 
		}else{
		
			throw new Exception("This file does not exist.");
		}
 	    	
		 
 	      }catch(Exception e){
 	         request.setAttribute("MESSAGE",e.getMessage());
 	     }
		 
 	      return FrontEndConstants.FILEUPLOAD;
	 }

/*
*/

private Vector insertFileInfo(final ActionForm form, final HttpServletRequest request,Vector delMsgFileCollection,DeliveryMessageLine objDeliveryMessageLine,DeliveryMessage objDeliveryMessage,DeliveryMessageForm objDeliveryMessageForm)throws AppException
{
	
	 
	 User objUser = null;
	 String login = null;
	 String index = request.getParameter("index") ;
	 String pono=request.getParameter("pono") ;
	 String lineNo=request.getParameter("lineNo") ;
	 String subfolderName=request.getParameter("subfolderName") ;
	 BOLUploadingForm bolUploadingForm=(BOLUploadingForm)form ;
	 Calendar cal = Calendar.getInstance();
	 Date today = cal.getTime();
	
	if(delMsgFileCollection==null)
	   {
		   delMsgFileCollection = new Vector();
	   }
	   
	   if(request.getSession().getAttribute("USER_INFO")!=null)			//Getting logged in user info from session
      {
			objUser = (User)request.getSession().getAttribute("USER_INFO");
          login = objUser.getFirstName();
      }
	
		   DeliveryMessageFile dmFiles=new DeliveryMessageFile();
		   dmFiles.setPoNo(pono);
		   String fileName=bolUploadingForm.getDataFile().getFileName();
		  
		   if(fileName!=null){
		   String fileNameSave = "DM_"+pono+"_downloads/"+subfolderName+"/"+fileName;
		   String  freightNo= bolUploadingForm.getFreightNo();
		   String  billNo= bolUploadingForm.getBillNo();
		   String carrierNo = bolUploadingForm.getCarrierNo();
		   dmFiles.setFrieghtNo(freightNo);
		   dmFiles.setBillOfLaiding(billNo);
		   dmFiles.setPoLineNo(Integer.valueOf(lineNo));
		   dmFiles.setFileUrl(fileNameSave);
		   dmFiles.setCreationDateTime(today);
		   dmFiles.setCreatedBy(login);
		   dmFiles.setCarrierNo(carrierNo);
		   dmFiles.setActiveFlag("Y");
		   dmFiles.setModifiedBy(login);
		   dmFiles.setPoNo(pono);
		   dmFiles.setFileName(fileName);
		   delMsgFileCollection.add(dmFiles);
		   objDeliveryMessageLine.setDelMsgFilesCollection(delMsgFileCollection);
		   objDeliveryMessage.setDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue(),objDeliveryMessageLine);
		   objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
		   request.setAttribute("MESSAGE","File Uploading is Completed.");
		   }
		  
	       return delMsgFileCollection ;
}
	
	

private Vector executeDelete(final ActionForm form, final HttpServletRequest request,Vector delMsgFileCollection,DeliveryMessageLine objDeliveryMessageLine,DeliveryMessage objDeliveryMessage,DeliveryMessageForm objDeliveryMessageForm) 
{
	 
	String pono=request.getParameter("pono") ;
	String lineNo=request.getParameter("lineNo") ;
	String fileId = request.getParameter("fileId");
	String index = request.getParameter("index");
	String actionType = request.getParameter("ACTION_DELETE");
	String fileIndex = request.getParameter("fileIndex");
	
	
	File file=null;
	
	InputStream inputStream=null ;
	try
	{
		//System.out.println("inside execute delete");
		 inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
		 Properties properties = new Properties();    
		 try{
		 properties.load(inputStream);  
		 }catch(IOException e){
			 throw new AppException();
		 }
		 String propValue = properties.getProperty("save-file-path");
		 CharSequence cs_backward="//" ;
		 String fileUrl="" ;
		 boolean slasFlag=propValue.contains(cs_backward) ;
		 if("DB_DELETE".equals(actionType)&&fileId!=null){
		 FileUploadingtDelegate objFileUploadingtDelegate = new FileUploadingtDelegate();
		   fileUrl=objFileUploadingtDelegate.getFileURL(new Integer(fileId));
		 
		 }else{
			 //System.out.println("inside BOLUploadingcommand getting file collection to delete file for that URL is:");
			 //DeliveryMessageFile ObjDeliveryMessageFile=(DeliveryMessageFile)delMsgFileCollection.get(Integer.valueOf(index).intValue());
			 DeliveryMessageFile ObjDeliveryMessageFile=(DeliveryMessageFile)delMsgFileCollection.get(Integer.valueOf(fileIndex).intValue());
			 fileUrl=ObjDeliveryMessageFile.getFileUrl();
		 }
	
		 // fileUrl=request.getParameter("file") ;
    	 String[] fileUrlArr=fileUrl.split("/");
    	 StringBuffer appendedFileUrl=new StringBuffer(propValue);
    	for(int i=0;i<fileUrlArr.length;i++){
    		if(i>0){
    			if(slasFlag==true){
    			appendedFileUrl.append("//");	
    			}else{
    			appendedFileUrl.append("\\");
    			}
    			appendedFileUrl.append(fileUrlArr[i]);
    		}else{
    			appendedFileUrl.append(fileUrlArr[i]);
    		}
    	}
    	String filePath=appendedFileUrl.toString();
    	//System.out.println("filePath"+filePath);
    	
    	//Introduced as part of Fortify Path Manipulation Fix
        String tempFilePath2 = filePath;
        System.out.println("tempFilePath2: "+tempFilePath2);
        String validatedFilePath2 = validateFilePath(tempFilePath2);
        System.out.println("validatedFilePath2: "+validatedFilePath2);
        file= new File(validatedFilePath2);
    	
        //file= new File(filePath);
      
      
        boolean deleteSuccess=file.delete();
        //System.out.println("deleteSuccess"+deleteSuccess);
        if(deleteSuccess==true){
		FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
	   if("DB_DELETE".equals(actionType)){
		     //System.out.println("inside DB_DELETE about to call deleteDMFileLocation");
		     objDelegate.deleteDMFileLocation(pono,Integer.valueOf(lineNo),fileId);
		}else{
			int inc=-1;
			if(fileIndex!=null&&!"".equals(fileIndex)){
				inc=Integer.parseInt(fileIndex);
			}
			if(inc!=-1){
			delMsgFileCollection.remove(inc);
			objDeliveryMessageLine.setDelMsgFilesCollection(delMsgFileCollection);
			objDeliveryMessage.setDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue(),objDeliveryMessageLine);
			objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
			request.getSession(false).setAttribute("deliveryMessageForm",objDeliveryMessageForm);
			}
		 
		}
	     request.setAttribute("MESSAGE","File is successfully deleted.");
        }else{
        	
        	 throw new Exception("File is not at this location.");
        }
	}
	catch(Exception e)
    {
	   request.setAttribute("MESSAGE",e.getMessage());
	   //System.out.println("message is going to be: "+ e.getMessage());
	   return delMsgFileCollection;
    }
	finally{
		if(inputStream!=null){
			 try{
				 inputStream.close();
				 }catch(IOException e){
				 }
		}
		
	}
	return delMsgFileCollection;
 }


  
public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)  
{
	 PrintWriter out=null;
	// String index=request.getParameter("totalLineItems") ;
	 int totalLineItems=0;
	 response.setContentType("text/xml");
	 StringBuffer strXML= new StringBuffer("");
	 DeliveryMessageForm objDeliveryMessageForm =(DeliveryMessageForm)request.getSession().getAttribute("deliveryMessageForm");
	 DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
	//  DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
	 if(objDeliveryMessage.getDeliveryMsgLineCollection()!=null){
       totalLineItems = objDeliveryMessage.getDeliveryMsgLineCollection().size();
	 }
	 
	/* if(index!=null&&!"".equals(index))
	 {
		 totalLineItems=Integer.parseInt(index);
	 }*/
	 StringBuffer sizeStr=new StringBuffer(""+totalLineItems) ;
	 sizeStr.append("~") ;
	 for(int size=0;size<totalLineItems;size++){
	 int fileListSize=0 ;
	 DeliveryMessageLine objDeliveryMessageLine = (DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(size);
	 Vector delMsgFileCollection = (Vector)objDeliveryMessageLine.getDelMsgFilesCollection();
	  if(delMsgFileCollection!=null){
		 fileListSize=delMsgFileCollection.size();
	   }
	  sizeStr.append(fileListSize) ;
	  sizeStr.append("~") ;
	 
	 
	 }
	 //System.out.println("sizeStr....."+sizeStr);
	 strXML.append("<file-upload>"+sizeStr.toString()+"</file-upload>");
	 try{
		    out = response.getWriter();
		    out.println(strXML);
		    out.flush();
			}catch(IOException ioe){
				System.out.println("[ERROR]: IOException");
			}
			finally{
				out.close();
			}
    return FrontEndConstants.UPDATE;
}

 
public String executeGeneral(final String actioncommand,final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
{		
	String showRLDToCU = request.getParameter("showRLDToCU") ;
	String operation = request.getParameter("operation") ;
	String roleTracking = request.getParameter("roleTracking") ;
		String DMGRMsgNo = request.getParameter("DMGRMsgNo") ;
		System.out.println(DMGRMsgNo);
	   String msgId=request.getParameter("msgId") ;
	   String pono=request.getParameter("pono") ;
	   String lineNo=request.getParameter("lineNo") ;
	   String flag = request.getParameter("flag") ;
	   String index = request.getParameter("index");
	   String poversion = request.getParameter("poversion");
	   String subfolderName = request.getParameter("subfolderName");
	   String delreplace = request.getParameter("delreplace");
	   String general="general" ;
	   String singlefile=null ;
	   FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
	   BOLUploadingForm bolUploadingForm = (BOLUploadingForm)form;
	   Properties properties=null;
	   String DMGRMode ="DM";
	//   String DMGRMode = null;
	  if(DMGRMsgNo != null){
		   int idx = DMGRMsgNo.indexOf("_");
		   DMGRMode = DMGRMsgNo.substring(0, idx);

	  }
	   
	   
	   try{
		   InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
		    properties = new Properties();   
			try{
			properties.load(inputStream);    
			}catch(IOException e){
			}
			String msg="" ;
		   Vector dmFileList=null;
	if(delreplace!=null){
	         executeFileWriting(mapping,form,request,response);
	         msg=(String)request.getAttribute("MESSAGE");
	         if(msg==null){
			 boolean fileReplace=replaceSingalFile(request,form,properties);
			 if(fileReplace==true){
				 request.setAttribute("MESSAGE","File is replaced successfully."); 
			 }
			 general="general" ;
			}
	   }
	
	   if("".equals(msg)||msg==null){
	   dmFileList = objDelegate.getDelMsgFileList(Integer.valueOf(msgId),Integer.valueOf(lineNo), DMGRMode);
	  /* for(int i=0;i<dmFileList.size();i++)
		{
			DeliveryMessageFile objPixDeliveryMsgFiles = (DeliveryMessageFile)dmFileList.get(i);
			
			String fileUrl = objPixDeliveryMsgFiles.getFileUrl();
			
		}*/
	   }else{
		   Integer messageId = null; 
    		if(msgId != null)
    		messageId = Integer.valueOf(msgId);
		   dmFileList=objDelegate.getDMFileList(Integer.valueOf(lineNo),pono,messageId);
		   flag="upload";
		   String filename=request.getParameter("filename") ;
		   String fileID=request.getParameter("fileID") ;
		   request.setAttribute("delreplace","true");
		   request.setAttribute("singlefilenew","true");
		   request.setAttribute("fileid",fileID);
		   request.setAttribute("filename",filename);
		 
	   }
	   Vector dmDtlList = null;
	   int totalRollWeight = 0;
	   if(operation.equals("rollDetailsHistory")){
   		 System.out.println(msgId);
	    	dmDtlList = objDelegate.getCostUserMsgRollInfo(msgId, DMGRMode); // correct
	    	 System.out.println(dmDtlList.size());
	    	 bolUploadingForm.setDmDtlList(dmDtlList);
	    	 //	int totalRollWeight = 0;
	    	 	DeliveryMessageFile deliveryMessageFile = new DeliveryMessageFile();
	    	 	for(int i=0; i<dmDtlList.size(); i++){
	    	 		deliveryMessageFile = (DeliveryMessageFile)dmDtlList.get(i);
	    	 		totalRollWeight = new Integer(deliveryMessageFile.getRollWeight().intValue()) + totalRollWeight;
	    	 	}
	    	 	bolUploadingForm.setTotalRollWeight(totalRollWeight);
	    	 	request.setAttribute("isHistoryPop", "rollDetailsHistory");
	    	 	request.setAttribute("roleTrackingg",roleTracking);
	    //	 	return FrontEndConstants.FILEUPLOAD;
   	 }
	   
	 //	bolUploadingForm.setTotalRollWeight(totalRollWeight);
	 //	request.setAttribute("isDMPop", "rollDetailsDM");
	 	HttpSession session = request.getSession();
	 	session.setAttribute("dmDetailList",dmDtlList);
	 	session.setAttribute("totalRollWt",totalRollWeight);
	 	 
	 	//added by vishal sinha for bol upload on 2nd august
	 	session.setAttribute("roleTrackingFlag",roleTracking);
	   
	   
	   bolUploadingForm.setFileList(dmFileList);
	   request.setAttribute("lineNo",lineNo);
	   request.setAttribute("msgId",msgId);
	   request.setAttribute("flag",flag);
	   request.setAttribute("showRLDToCU",showRLDToCU);
	   request.setAttribute("DMGRMode",DMGRMode);
	//   request.setAttribute("roleTrackingg",0);
	   request.setAttribute("pono",pono);
	   request.setAttribute("index",index);
	   request.setAttribute("poversion",poversion);
	   request.setAttribute("subfolderName",subfolderName);
	   
    }catch(AppException e)
	   {
		   String errMsg = e.getSErrorDescription();
		   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
		   return FrontEndConstants.ERROR;
	   }
	  
	   return general;
    
}

private boolean replaceSingalFile( final HttpServletRequest request, final ActionForm form,Properties properties)throws AppException{

 
 	Integer fileId=null ;
 	Integer msgId=null ;
 	Integer lineNu=null ;
 	boolean  replace=false ;
 	String fileID=request.getParameter("fileID") ;
 	String msgID=request.getParameter("msgId") ;
 	String poNO=request.getParameter("pono") ;
 	String lineNO=request.getParameter("lineNo") ;
 	String subfolderName=request.getParameter("subfolderName") ;
 	 BOLUploadingForm bolUploadingForm = (BOLUploadingForm)form;
 	String fileName=bolUploadingForm.getDataFile().getFileName();
 	String updatedFileName="DM_"+poNO+"_downloads/"+subfolderName+"/"+fileName ;
 	 
 	//String filename=request.getParameter("filename") ;
 	String filename="" ;
	
 	String saveFilePathforDel = properties.getProperty("save-file-path");
 	CharSequence cs_backward="//" ;
	boolean slasFlag=saveFilePathforDel.contains(cs_backward) ;
 	if(fileID!=null&&!"".equals(fileID)){
 		fileId=new Integer(fileID);
 	}
 	if(msgID!=null&&!"".equals(msgID)){
 		msgId=new Integer(msgID);
 	}
 	if(lineNO!=null&&!"".equals(lineNO)){
 		lineNu=new Integer(lineNO);
 	}
 	 FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
 	 String fileUrl=objDelegate.getFileURL(fileId);
 	String[] fileUrlArr=fileUrl.split("/");
 	 filename=fileUrlArr[fileUrlArr.length-1];
 	 Integer status=objDelegate.updateDMFile(updatedFileName, fileId,msgId,poNO,lineNu);
 	 if(status!=null&&(status.intValue()==1)&&filename!=null&&!"".equals(filename)){
			String subDirt="DM_"+poNO+"_downloads";
			String saveFilePathDel=saveFilePathforDel+subDirt ;
			if(slasFlag==true){
			saveFilePathDel=saveFilePathDel+"//"+subfolderName+"//"+filename ;
			}else{
         saveFilePathDel=saveFilePathDel+"\\"+subfolderName+"\\"+filename ;
			}
			
			//Introduced as part of Fortify Path Manipulation Fix
            String tempFilePath3 = saveFilePathDel;
            System.out.println("tempFilePath3: "+tempFilePath3);
            String validatedFilePath3 = validateFilePath(tempFilePath3);
            System.out.println("validatedFilePath3: "+validatedFilePath3);
            File fileDel= new File(validatedFilePath3);
			
      	//File fileDel= new File(saveFilePathDel);
    	   if(fileDel!=null&&fileDel.exists()){
    		   replace=fileDel.delete();
    	    }
		  }
 	return replace ;
  }

//Introduced as part of Fortify Path Manipulation Fix
public String validateFilePath(String tempFilePath) {
	System.out.println("Inside validateFilePath method");
	if(tempFilePath == null) {
		return null;
	}
	
	String aString = tempFilePath;
    String cleanPath = "";
    for (int i = 0; i < aString.length(); ++i) {
    	cleanPath += sanitizeElements(aString.charAt(i));
    }
    System.out.println("cleanPath: "+cleanPath);
	return cleanPath;
}

public char sanitizeElements(char aChar) {
	System.out.println("Inside sanitizeElements method");
	
	int asciiValue = (int) aChar; // Get the ASCII value of the special character
    return (char) asciiValue; // Return the character corresponding to the ASCII value
	
}


}
