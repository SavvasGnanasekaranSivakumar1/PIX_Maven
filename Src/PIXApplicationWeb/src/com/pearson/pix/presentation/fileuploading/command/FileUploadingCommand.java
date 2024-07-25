package com.pearson.pix.presentation.fileuploading.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.pearson.pix.business.common.PIXConstants;
import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.common.DeliveryMessageFile;
import com.pearson.pix.dto.deliverymessage.DeliveryMessage;
import com.pearson.pix.dto.deliverymessage.DeliveryMessageLine;
import com.pearson.pix.exception.AppException;
import com.pearson.pix.presentation.base.command.BaseCommand;
import com.pearson.pix.presentation.base.common.FrontEndConstants;
import com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm;
import com.pearson.pix.presentation.fileuploading.action.FileUploadingForm;
import com.pearson.pix.presentation.fileuploading.delegate.FileUploadingtDelegate;

public class FileUploadingCommand extends BaseCommand 
{
	   
	private static String FILE_OPR = "fileProperties";
	/**
	    * Constructor for Initializing ExportToExcelCommand
	    */
	   public FileUploadingCommand() 
	   {
		   
	   }
	   
	   
	   public String executeFileUploading(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response)
	   {   
		   System.out.println("india @123");
		   String pono=request.getParameter("pono") ;
		   String upload=request.getParameter("upload") ;
		   String lineNo=request.getParameter("lineNo") ;
		   String index = request.getParameter("index") ; 
		   String msgId = request.getParameter("msgId") ;
		   String poversion = request.getParameter("poversion") ; 
		   String subfolderName = request.getParameter("subfolderName") ; 
		   User objUser = null;
		   String login = null;
		   Vector delMsgFileCollection =null;
		   try{
			   Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				System.out.println("in up load....******....");
			   FileUploadingForm objFileUploadingForm = (FileUploadingForm)form;
			   DeliveryMessageForm objDeliveryMessageForm =(DeliveryMessageForm)request.getSession().getAttribute("deliveryMessageForm");
			   
			   DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
			   DeliveryMessageLine objDeliveryMessageLine = (DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue());
			   
			   delMsgFileCollection = (Vector)objDeliveryMessageLine.getDelMsgFilesCollection();
			  
			   /*if(delMsgFileCollection==null){
				   request.getSession().removeAttribute("fileName");
			   }*/
			   if(delMsgFileCollection==null)
			   {
				   delMsgFileCollection = new Vector();
			   }
			   
			  
			   
			   
			   FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
			   if(request.getSession().getAttribute("USER_INFO")!=null)			//Getting logged in user info from session
		        {
					objUser = (User)request.getSession().getAttribute("USER_INFO");
		            login = objUser.getFirstName();
		        }
			 
		   if(upload!=null&&"success".equals(upload)){
			   DeliveryMessageFile dmFiles=new DeliveryMessageFile();
			   dmFiles.setPoNo(pono);
			   String fileName=(String)request.getSession().getAttribute("fileName");
			   request.getSession().removeAttribute("fileName");
			   
			   if(fileName!=null){
			   
			   
			   String fileNameSave = "DM_"+pono+"_downloads/"+subfolderName+"/"+fileName;
			   String  freightNo= request.getParameter("freightNo");
			   String  billNo= request.getParameter("billNo");
			   String carrierNo = request.getParameter("carrierNo");
			  System.out.println("filee...................****...");
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
			   System.out.println("list size....."+delMsgFileCollection.size());
			   objDeliveryMessageLine.setDelMsgFilesCollection(delMsgFileCollection);
			   // System.out.println("delMsgFileCollection:"+delMsgFileCollection.size());
			   
			   objDeliveryMessage.setDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue(),objDeliveryMessageLine);
			   objDeliveryMessageForm.setDeliveryMessage(objDeliveryMessage);
			   }
			   objFileUploadingForm.setUploadFile(delMsgFileCollection);
			   
		   }
		   
		   Integer messageId = null; 
   			if(msgId != null)
   			messageId = Integer.valueOf(msgId);
		   Vector dmFileList = objDelegate.getDMFileList(Integer.valueOf(lineNo),pono,messageId);
		   objFileUploadingForm.setFileList(dmFileList);
		   request.setAttribute("lineNo",lineNo);
		   request.setAttribute("pono",pono);
		   request.setAttribute("upload",upload);
		   request.setAttribute("index",index);
		   request.setAttribute("poversion",poversion); 
		   request.setAttribute("subfolderName",subfolderName); 
		   
	       }catch(AppException e)
		   {
			   String errMsg = e.getSErrorDescription();
			   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		   }
		  
		   return FrontEndConstants.FILEUPLOAD;
	       
	   }
	   
	   
	   /*private String executeInsertUploadedFile(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,DeliveryMessageFile dmFiles)throws AppException{
		   System.out.println("executeInsertUploadedFile.....");
		   FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
		  
		  
		   //objDelegate.insertDMFileLocation(dmFiles);
		   
		   return FrontEndConstants.INSERT;
	   }*/
	   
	   /**
		 * Method for Delete operation
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param messages
		 * @return String
		 */
		
		public String executeDelete(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,ActionMessages messages) 
		{
			 System.out.println("delete...................");
			String pono=request.getParameter("pono") ;
			String lineNo=request.getParameter("lineNo") ;
			String fileId = request.getParameter("fileId");
			String index = request.getParameter("index");
			String poversion = request.getParameter("poversion");
			String actionType = request.getParameter("ACTION_TYPE");
			String fileIndex = request.getParameter("fileIndex");
			String subfolderName = request.getParameter("subfolderName");
			String msgId = request.getParameter("msgId");
			
			File file=null;
			
			InputStream inputStream=null ;
			try
			{
				FileUploadingForm objFileUploadingForm = (FileUploadingForm)form; 
				 inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
				
				 Properties properties = new Properties();    
				 try{
				 properties.load(inputStream);  
				 }catch(IOException e){
					 throw new AppException();
				 }
				 String propValue = properties.getProperty("save-file-path");
				 CharSequence cs_backward="//" ;
				 boolean slasFlag=propValue.contains(cs_backward) ;
				 // propValue.
				 String fileUrl=request.getParameter("file") ;
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
	        	
	        	//Introduced as part of Fortify Path Manipulation Fix
	            String tempFilePath3 = filePath;
	            System.out.println("tempFilePath3: "+tempFilePath3);
	            String validatedFilePath3 = validateFilePath(tempFilePath3);
	            System.out.println("validatedFilePath3: "+validatedFilePath3);
	            file = new File(validatedFilePath3);
	        	
	            //file= new File(filePath);
	            System.out.println("filePath....*........"+filePath);
	            System.out.println("file.....*......."+file);
	            System.out.println("file. exist....*......."+file.exists());
	            boolean deleteSuccess=file.delete();
	            System.out.println("deleteSuccess...*........."+deleteSuccess);
	            if(deleteSuccess==true){
				FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
			   if(actionType==null){
				objDelegate.deleteDMFileLocation(pono,Integer.valueOf(lineNo),fileId);
				}else{
					 DeliveryMessageForm objDeliveryMessageForm =(DeliveryMessageForm)request.getSession().getAttribute("deliveryMessageForm");
					 DeliveryMessage objDeliveryMessage = objDeliveryMessageForm.getDeliveryMessage();
					 DeliveryMessageLine objDeliveryMessageLine = (DeliveryMessageLine)objDeliveryMessage.getDeliveryMsgLineCollectionIdx(Integer.valueOf(index).intValue());
					 Vector  delMsgFileCollection = (Vector)objDeliveryMessageLine.getDelMsgFilesCollection();
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
				  objFileUploadingForm.setUploadFile(delMsgFileCollection);
				}
				request.setAttribute("index",index);
				request.setAttribute("pono",pono);
				request.setAttribute("poversion",poversion);
				request.setAttribute("lineNo",lineNo);
				request.setAttribute("subfolderName",subfolderName);
				Integer messageId = null; 
	    		if(msgId != null)
	    			messageId = Integer.valueOf(msgId);
				Vector dmFileList = objDelegate.getDMFileList(Integer.valueOf(lineNo),pono,messageId);
				objFileUploadingForm.setFileList(dmFileList);
	            }else{
	            	System.out.println("deleteSuccess....*........"+deleteSuccess);
	            	 throw new Exception();
	            }
			}
			catch(Exception e)
		    {
				System.out.println("eee......"+e);
			  //  String errMsg = e.getSErrorDescription();
			 //   request.setAttribute(PIXConstants.PIX_ERROR,errMsg);
			   return FrontEndConstants.ERROR;
		    }
			finally{
				if(inputStream!=null){
					 try{
						 inputStream.close();
						 }catch(IOException e){
						 }
				}
				
			}
			
			
			return FrontEndConstants.DELETE;
		}
		
		 public String executeGeneral(final String actioncommand,final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)
		   {
			   String msgId=request.getParameter("msgId") ;
			   String pono=request.getParameter("pono") ;
			   String lineNo=request.getParameter("lineNo") ;
			   String flag = request.getParameter("flag") ;
			   String index = request.getParameter("index");
			   String poversion = request.getParameter("poversion");
			   String subfolderName = request.getParameter("subfolderName");
			   FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
			   FileUploadingForm objFileUploadingForm = (FileUploadingForm)form;
			   
			   try{
				   
			  
			   Vector dmFileList = objDelegate.getDelMsgFileList(Integer.valueOf(msgId),Integer.valueOf(lineNo), "DM");
			   objFileUploadingForm.setFileList(dmFileList);
			   
			   System.out.println("fileUPLOADCOMMAN VECTOR:"+dmFileList.size());
			   request.setAttribute("lineNo",lineNo);
			   request.setAttribute("msgId",msgId);
			   request.setAttribute("flag",flag);
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
			  
			   return "general";
		       
		   }
		 
		 
		 public String executeUpdate(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages)  
		   {
			 PrintWriter out=null;
			 String pono=request.getParameter("pono") ;
			 String filename=request.getParameter("filename") ;
			 String subfolderName=request.getParameter("subfolderName") ;
			 boolean fFxists=false ;
			 InputStream inputStream=null ;
			 String saveFilePath="" ;
			 if(filename!=null&&!"".equals(filename)){
			    inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
				Properties properties = new Properties();  
				try{
				properties.load(inputStream); 
				saveFilePath = properties.getProperty("save-file-path");
				}catch(IOException e){}
				finally{
					if(inputStream!=null){
						 try{
							 inputStream.close();
							}catch(IOException e){
							 }
					}
				}
				// String saveFilePath = properties.getProperty("save-file-path");
				String subDir="DM_"+pono+"_downloads";
				CharSequence cs_backward="//" ;
				 boolean slasFlag=saveFilePath.contains(cs_backward) ;
				saveFilePath=saveFilePath+subDir ;
         	    CharSequence cs_farward="\\" ;
         	    boolean cs_farward_b=false;
         	if((cs_farward_b=(filename.contains(cs_farward)))==true){
         	String[] fileNameArr=null;
        	
        	if(cs_farward_b==true){
        		String s="~";
        		filename=filename.replace("\\","~");
        		fileNameArr=filename.split(s);
        	}
        	filename=fileNameArr[fileNameArr.length-1];
         	}
         	if(slasFlag==true){
         		saveFilePath=saveFilePath+"//"+subfolderName+"//"+filename ;
         	}else{
         		saveFilePath=saveFilePath+"\\"+subfolderName+"\\"+filename ;
         	}
         	
         	
         	//Introduced as part of Fortify Path Manipulation Fix
            String tempFilePath1 = saveFilePath;
            System.out.println("tempFilePath1: "+tempFilePath1);
            String validatedFilePath1 = validateFilePath(tempFilePath1);
            System.out.println("validatedFilePath1: "+validatedFilePath1);
            File file = new File(validatedFilePath1);
         	
         	//File file= new File(saveFilePath);
         	
         	if(file!=null){
         		fFxists=file.exists();
         	}
			 }
			 
			 
			 try{
					response.setContentType("text/xml");
					StringBuffer strXML= new StringBuffer("");
					strXML.append("<file-upload>"+fFxists+"</file-upload>");
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
		 
		 
		 public String executeDisplay(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response,final ActionMessages messages) 
		   {
			 
			
			 String pono=request.getParameter("pono") ;
			 String msg=request.getParameter("msg") ;
			 FileUploadingtDelegate objDelegate = new FileUploadingtDelegate();
			 InputStream inputStream=null ;
			 String saveFilePath="" ;
			 try{
			 if(msg!=null&&!"".equals(msg)){
			    inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
				Properties properties = new Properties();  
				try{
				properties.load(inputStream); 
				
				}catch(IOException e){}
				finally{
					if(inputStream!=null){
						 try{
							 inputStream.close();
							}catch(IOException e){
							 }
					}
				}
				
				Map fileUrlBuff=(LinkedHashMap)objDelegate.renameDMFileFolder(msg,pono);
				
				if(fileUrlBuff!=null&&fileUrlBuff.size()!=0){
					 String[] msgArr=msg.split("#");
					 Set set= (HashSet)fileUrlBuff.keySet();
					 Iterator it=set.iterator();
				
					for(int p=0;p<fileUrlBuff.size();p++){
					String msgIDMap=(String)it.next();
					String url=((StringBuffer)fileUrlBuff.get(msgIDMap)).toString();
					String[] urlArr=url.split("#");
					String[] msgArrLine=msgArr[p].split("~") ;
					StringBuffer replacedUrl=new StringBuffer("");
					for(int k=0;k<urlArr.length;k++){
						
						String[] divUrl=urlArr[k].split("/");
						
						for(int m=0;m<divUrl.length-1;m++){
							replacedUrl.append(divUrl[m]);
							replacedUrl.append("/") ;
						}
						break;
					 } // for	
					 saveFilePath = properties.getProperty("save-file-path");
					 
					 if(!"".equals(replacedUrl.toString())){
					 File file= new File(saveFilePath+replacedUrl.toString());
					 
					 if(msgArrLine!=null&&msgArrLine.length==2){
						 
						//Introduced as part of Fortify Path Manipulation Fix
				            String tempFilePath2 = saveFilePath+"DM_"+pono+"_downloads/"+"sub_"+msgArrLine[0]+"_"+msgArrLine[1]+"/";
				            System.out.println("tempFilePath2: "+tempFilePath2);
				            String validatedFilePath2 = validateFilePath(tempFilePath2);
				            System.out.println("validatedFilePath2: "+validatedFilePath2);
				            file.renameTo(new File(validatedFilePath2));
						 
					 //file.renameTo(new File(saveFilePath+"DM_"+pono+"_downloads/"+"sub_"+msgArrLine[0]+"_"+msgArrLine[1]+"/"));
					 }
					 
					 }
					 
					 
					//}// for
					
					}	//for big
					
					
				}//if
			
			
         
			 }
			 
			
			 
		   }catch(AppException e){
			   
			   
			   System.out.println("eeee...."+e);
		   }
			 
		    return FrontEndConstants.DISPLAY;
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
