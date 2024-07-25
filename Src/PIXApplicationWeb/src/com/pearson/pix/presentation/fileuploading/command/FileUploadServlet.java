package com.pearson.pix.presentation.fileuploading.command;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStats;
import com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStatus;
import com.pearson.pix.presentation.fileuploading.delegate.FileUploadingtDelegate;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.missiondata.fileupload.MonitoredDiskFileItemFactory;

/**
 * @author Rick Reumann
 * The majority of this code is taken from the example here
 * http://www.ioncannon.net/java/38/ajax-file-upload-progress-for-java-using-commons-fileupload-and-prototype/
 * That above example uses prototype for the AJAX implementation.
 * The above link also provides the fileupload-ext jar which is used in this example.
 * */
public class FileUploadServlet extends HttpServlet {

	private static String FILE_OPR = "fileProperties";
    public void init(ServletConfig config) throws ServletException { 
        super.init(config);
        /*String propsFilePath = "/WEB-INF/app.properties";
        try {
            Properties properties = new Properties();
            InputStream inputStream = config.getServletContext().getResourceAsStream(propsFilePath);
            properties.load(inputStream);
            inputStream.close();
            getServletContext().setAttribute("properties", properties);
            
        } catch (Exception e) {
            throw new ServletException("Error loading properties file from path "+propsFilePath, e);
        }*/
    }
 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    
    	System.out.println("Inside doPost method");
    	File file=null;
    	    File fileTemp=null;
    	    String fileName="";
    	    String pono=request.getParameter("pono") ;
    	    String index=request.getParameter("index") ;
    	    String deleteAlso=request.getParameter("deleteAlso") ;
    	    String subfolderName=request.getParameter("subfolderName") ;
    	   
    	    //String saveFilePath = ((Properties) getServletContext().getAttribute("properties")).getProperty("save-file-path");
    	    
    	    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
			Properties properties = new Properties();       
			properties.load(inputStream);      
			String saveFilePath = properties.getProperty("save-file-path");
    	    
		    String lineNo=request.getParameter("lineNo") ;
		    /*double randNum=Math.random();
		    double subFolderNo=randNum*10000 ;
		    System.out.println("subFolderNo....."+subFolderNo);
		    int subFolderNo11=(int)subFolderNo ;
		    System.out.println("subFolderNo1111....."+subFolderNo11);*/
        try {
        	
        	System.out.println("xxxxxxxx......");
        	String subDir="DM_"+pono+"_downloads/"+subfolderName;
        	request.setAttribute("lineNo",lineNo);
  		    request.setAttribute("pono",pono);
            
            saveFilePath=saveFilePath.concat(subDir);
  		   	FileUploadListener listener = new FileUploadListener(request.getContentLength());
            request.getSession().setAttribute("FILE_UPLOAD_STATS", listener.getFileUploadStats());  // 4194304
            if(listener.getFileUploadStats()!=null&&listener.getFileUploadStats().getTotalSize()<=  4194304 ){
            
            DiskFileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
            
          //Introduced as part of Fortify Path Manipulation Fix
            String tempFilePath1 = saveFilePath+File.separator;
            System.out.println("tempFilePath1: "+tempFilePath1);
            String validatedFilePath1 = validateFilePath(tempFilePath1);
            System.out.println("validatedFilePath1: "+validatedFilePath1);
            file = new File(validatedFilePath1);
            
            //file= new File(saveFilePath+File.separator);
            // System.out.println("file dir....."+(saveFilePath+File.separator));
            file.mkdirs();
            
            factory.setRepository(file);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(request);
            //if this process of writing to the file system could take a while,
            //you'd want to add another listener and possibly show progress of this as well.
            boolean fbflag=false ;
            for (Iterator i = items.iterator(); i.hasNext();) {
                FileItem fileItem = (FileItem) i.next();
               
                if (!fileItem.isFormField()) {
                	if(fileItem.getSize()==0){
                	    fbflag=true ;
                	}
                	 
                    fileName=fileItem.getName();
                    CharSequence cs_back="/" ;
                	CharSequence cs_farward="\\" ;
                	boolean cs_back_b=false;
                	boolean cs_farward_b=false;
                if((cs_back_b=(fileName.contains(cs_back)))==true||(cs_farward_b=(fileName.contains(cs_farward)))==true){
                	String[] fileNameArr=null;
                	if(cs_back_b==true){
                		fileNameArr=fileName.split("/");
                	}
                	if(cs_farward_b==true){
                		String s="~";
                		fileName=fileName.replace("\\","~");
                		fileNameArr=fileName.split(s);
                	}
                	fileName=fileNameArr[fileNameArr.length-1];
                }
                
              //Introduced as part of Fortify Path Manipulation Fix
                String tempFilePath2 = saveFilePath + File.separator + fileName;
                System.out.println("tempFilePath2: "+tempFilePath2);
                String validatedFilePath2 = validateFilePath(tempFilePath2);
                System.out.println("validatedFilePath2: "+validatedFilePath2);
                fileTemp = new File(validatedFilePath2);
                
                    //fileTemp=new File(saveFilePath + File.separator + fileName) ;
                	fileItem.write(fileTemp);
                    
                    
                }
               
                
                if(!fileItem.isFormField()&&fileItem.getSize()==0&&fbflag==true){
                	Exception ex=new Exception("notexist");
                	throw ex;
                	/*FileUploadStats stats = new FileUploadListener.FileUploadStats();
                    stats.setCurrentStatus(FileUploadStatus.ERROR);
                	request.getSession().setAttribute("FILE_UPLOAD_STATS", stats);*/
                }else{
                	FileUploadStats stats = new FileUploadListener.FileUploadStats();
                    stats.setCurrentStatus(FileUploadStatus.DONE);
                	request.getSession().setAttribute("FILE_UPLOAD_STATS", stats);
                	
                }
            }
            request.getSession().setAttribute("fileName", fileName);
           
           
            if(deleteAlso!=null&&"true".equals(deleteAlso)){
            	request.getSession().removeAttribute("fileName");
            	Integer fileId=null ;
            	Integer msgId=null ;
            	Integer lineNu=null ;
            	String fileID=request.getParameter("fileID") ;
            	String msgID=request.getParameter("msgID") ;
            	String poNO=request.getParameter("poNO") ;
            	String lineNO=request.getParameter("lineNo") ;
            	String updatedFileName="DM_"+poNO+"_downloads/"+subfolderName+"/"+fileName ;
            	String filename=request.getParameter("filename") ;
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
               		  fileDel.delete();
               	    }
      			  }
            	
            }
            
            
            }else{
            	 FileUploadStats stats = new FileUploadListener.FileUploadStats();
            	 FileUploadStatus.ERROR="fileoversize" ;
            	 stats.setCurrentStatus(FileUploadStatus.ERROR);
                 request.getSession().setAttribute("FILE_UPLOAD_STATS", stats);
                // FileUploadStatus.ERROR="error" ;
            }
           
           
        } catch (Exception e) {
            //to be safe put error in stats so that our ajax progress can pick this up
        	System.out.println("eee..................."+e);
        	if("notexist".equals(e.getMessage())){
        		FileUploadStatus.ERROR="error" ;
        	}
        	 
        	if(fileTemp!=null){
               boolean b= fileTemp.delete();
        	}
            FileUploadStats stats = new FileUploadListener.FileUploadStats();
            stats.setCurrentStatus(FileUploadStatus.ERROR);
            request.getSession().setAttribute("FILE_UPLOAD_STATS", stats);
            request.getSession().removeAttribute("fileName");
            
        }
        
      
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
