package com.pearson.pix.presentation.boluploading.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pearson.pix.presentation.admin.delegate.AdminDelegate;
import com.pearson.pix.presentation.fileuploading.delegate.FileUploadingtDelegate;



public class BOLFileDownLoadAction extends  Action {

	private static String FILE_OPR = "fileProperties";
	public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws IOException,ServletException 
	{
		
		BOLUploadingForm objFileUploadingForm = (BOLUploadingForm)form;
		//String fileName = request.getParameter("FILENAME");
		String fileName = objFileUploadingForm.getFileName();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename ="+fileName);
		
		ServletOutputStream outs = response.getOutputStream();
		
		
		URLConnection conn = null;
		InputStream  in = null;
		//int ch;

		try
		{
			
			 
			 InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_OPR + ".properties"); 
			 Properties properties = new Properties();       
			 properties.load(inputStream); 
			 FileUploadingtDelegate objFileUploadingtDelegate = new FileUploadingtDelegate();
			 
			 Integer fileID=null;
			 // String propValue = properties.getProperty("url_server");
			 String fileId=request.getParameter("fileId");
			
			 if(fileId!=null&&!"".equals(fileId)){
				 fileID=new Integer(fileId);
			 }
			// String fileRelativePath=objFileUploadingForm.getFileURL();
			 String fileRelativePath=objFileUploadingtDelegate.getFileURL(fileID);
			 String propValue = properties.getProperty("save-file-path");
			
			 CharSequence cs_backward="//" ;
			 boolean slasFlag=propValue.contains(cs_backward) ;
	    	 String[] fileUrlArr=fileRelativePath.split("/");
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
			 
			 
			 File file = new File(filePath);
			
			 
			 // String fullPath = propValue +"/"+ fileRelativePath;
			 // String fullPath = propValue + fileRelativePath;
			 objFileUploadingForm.setFileURL(null);
			 objFileUploadingForm.setFileName(null);
			 try {
				     // System.out.println("...."+fullPath.contains("   "));
				   
				 if(file!=null&&file.exists()==true){
				 FileInputStream fileInputStream = new FileInputStream(file);
				 int i;
				 while ((i=fileInputStream.read())!=-1)
				 {
				 response.getOutputStream().write(i);
				 }
				 response.getOutputStream().flush();
				 response.getOutputStream().close();
				 fileInputStream.close();
				 }
				 
				 /*
				 	if(fullPath.contains(" "))
				 	{
				 		fullPath = fullPath.replace(" ","%20");
				 		System.out.println(".1....."+fullPath);
				 	}
				 	
				 	if(fullPath.contains("  "))
				 	{
				 		fullPath = fullPath.replace("  ","%20%20");
				 		System.out.println("..2...."+fullPath);
				 	}
				 	
				 	if(fullPath.contains("   "))
				 	{
				 		fullPath = fullPath.replace("   ","%20%20%20");
				 		System.out.println("..3...."+fullPath);
				 	}
				 	
				 	if(fullPath.contains("    "))
				 	{
				 		fullPath = fullPath.replace("    ","%20%20%20%20");
				 		System.out.println("..4...."+fullPath);
				 		
				 	}
				 	if(fullPath.contains("     "))
				 	{
				 		fullPath = fullPath.replace("     ","%20%20%20%20%20");
				 		System.out.println("..5...."+fullPath);
				 		
				 	}
				 	// abcw                     mhh5,236
				 	if(fullPath.contains("#"))
				 	{
				 		fullPath = fullPath.replace("#","%23");
				 	} 
				 
				 	if(fullPath.contains(","))
				 	{
				 		fullPath = fullPath.replace(",","%2C");
				 	} 
					System.out.println(".*..."+fullPath);
				 	URL url = new URL(fullPath);
				 	System.out.println("url....."+url);
					conn = url.openConnection();
					System.out.println("conn....."+conn);
					in = conn.getInputStream();
					System.out.println("inb....."+in);
					byte[] buffer = new byte[1024];
					int numRead;
					long numWritten = 0;
					while ((numRead = in.read(buffer)) != -1) {
						outs.write(buffer, 0, numRead);
						numWritten += numRead;
					}*/
					
					
					/*while ((ch = in.read()) != -1) {
						outs.print((char) ch);
						}*/
					
					
					
				} catch (Exception exception) {
					//exception.printStackTrace();
				} finally {
					try {
						if (in != null) {
							in.close();
						}
						if (outs != null) {
							outs.close();
						}
					} catch (IOException ioe) {
					}
				}

			
		}
		catch(Exception e)
		{
			
		}

		
		
		return null;
	}
	
	
	
	
}
