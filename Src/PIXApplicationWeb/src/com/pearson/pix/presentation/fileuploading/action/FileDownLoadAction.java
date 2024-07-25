package com.pearson.pix.presentation.fileuploading.action;


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



public class FileDownLoadAction extends  Action {

	private static String FILE_OPR = "fileProperties";
	public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws IOException,ServletException 
	{
		
		FileUploadingForm objFileUploadingForm = (FileUploadingForm)form;
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
			 // get the value of the property        
			 String propValue = properties.getProperty("url_server");
			
			 String fileRelativePath=objFileUploadingForm.getFileURL();
			
			 // String fullPath = propValue + request.getParameter("PATH");
			 String fullPath = propValue + fileRelativePath;
			 objFileUploadingForm.setFileURL(null);
			 objFileUploadingForm.setFileName(null);
			 try {
				 	if(fullPath.contains(" "))
				 	{
				 		fullPath = fullPath.replace(" ","%20");
				 	}
				 	
				 	if(fullPath.contains("#"))
				 	{
				 		fullPath = fullPath.replace("#","%23");
				 	} 
				 	URL url = new URL(fullPath);
				 	System.out.println(":fullpath:"+fullPath);
					conn = url.openConnection();
					in = conn.getInputStream();
					byte[] buffer = new byte[1024];
					int numRead;
					long numWritten = 0;
					while ((numRead = in.read(buffer)) != -1) {
						outs.write(buffer, 0, numRead);
						numWritten += numRead;
					}
					
					
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
