package com.pearson.pix.presentation.fileuploading.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStats;
import com.pearson.pix.presentation.fileuploading.command.FileUploadListener.FileUploadStatus;

/**
 * @author Rick Reumann
 * Most of this code based off the great ajax example using prototype ajax javascript 
 * found here: http://www.ioncannon.net/java/38/ajax-file-upload-progress-for-java-using-commons-fileupload-and-prototype/
 */
public class AjaxUploadStatusServlet extends HttpServlet { 

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUploadStats stats = (FileUploadStats) request.getSession().getAttribute("FILE_UPLOAD_STATS");
       /* if("fileoversize".equals(stats.getCurrentStatus())){
        	stats.setCurrentStatus(FileUploadStatus.NONE);
        	request.getSession().setAttribute("FILE_UPLOAD_STATS",stats);
        }*/
        
        if (stats != null) {
            response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.addHeader("Pragma", "no-cache");
            double percentComplete = stats != null ? percentComplete = stats.getPercentComplete() : 0.0;
            long bytesProcessed = stats.getBytesRead();
            
            long sizeTotal = stats.getTotalSize();
            DecimalFormat decFormatter = new DecimalFormat(".00");
            long elapsedTimeInMilliseconds = stats.getElapsedTimeInMilliseconds();
            double bytesPerMillisecond = bytesProcessed / (elapsedTimeInMilliseconds + 0.00001);
            long estimatedMillisecondsLeft = (long)((sizeTotal - bytesProcessed) / (bytesPerMillisecond + 0.00001));
            String timeLeft = null;
            if ( ( estimatedMillisecondsLeft/3600 ) > 24 ) {
                timeLeft = (long)(estimatedMillisecondsLeft/3600) + "hours";
            } else {
                Calendar c = new GregorianCalendar();
                long ad =  estimatedMillisecondsLeft - (c.get(Calendar.ZONE_OFFSET)+c.get(Calendar.DST_OFFSET));
                timeLeft = new SimpleDateFormat("HH:mm:ss").format(new Date(ad));
            }
           
            PrintWriter out = response.getWriter();
            double pComplete=percentComplete * 100 ;
            String bytesPerMillisecondStr=decFormatter.format(bytesPerMillisecond);
            String bytesProcessedStr=decFormatter.format((double)bytesProcessed/(1000*1000));
            String currentSizeStr=decFormatter.format((double)sizeTotal/(1000*1000));
            double ceilingSize=Math.ceil((percentComplete * 100));
            //System.out.println("doPost of AjaxUploadStatusServlet..(percentComplete * 100).."+pComplete);
            //out.print("<div class='prog-border'><div class='prog-bar' style='width: " + pComplete + "%;'></div></div>");
            //out.print("<table><tr><td class='st' width='250px;'>"+timeLeft+
                       // " (at " + bytesPerMillisecondStr + "kb/sec)</td><td class='st'>"+
                      //  bytesProcessedStr + " MB / "+currentSizeStr+" MB ( "+ceilingSize+"% )</td></tr></table>");
            System.out.println("stats.getCurrentStatus()....."+stats.getCurrentStatus());
            out.println("<div id='proggressBar' style='Display:none'>");
            out.println("<script>");
            out.println("setProggressBar('"+pComplete+"','"+bytesPerMillisecondStr+"','"+bytesProcessedStr+"','"+currentSizeStr+"','"+ceilingSize+"','"+timeLeft+"');");
            out.println("</script>");
            out.println("</script>");
            out.println("</div>");
            
            String cstats=stats.getCurrentStatus();
           
           //  System.out.println("status...."+stats.getCurrentStatus());
            if (stats.getCurrentStatus() == FileUploadStatus.DONE ||stats.getCurrentStatus() == FileUploadStatus.ERROR) {
                //out.print( stats.getCurrentStatus() == FileUploadStatus.DONE ? "<b>Upload Complete!</b>" : "<b>Error!</b>");
            	
                out.println("<script>");
                out.println("setTimeout(\"stoppage_checkUploadStatus()\", 500);toggleButton('submit_button');");
                out.println("funcPassValueNew('"+stats.getCurrentStatus()+"');");
                out.println("uploadStatus('"+cstats+"');");
               
                out.println("</script>");
            }
           // System.out.println("Leaving doPost of AjaxUploadServlet");
            out.flush();
        } else {
             PrintWriter out = response.getWriter();
             
             out.println("<div id='timeout' style='Display:none'>");
             out.println("<script>");
             out.println("setTimeout(\"stoppage_checkUploadStatus()\", 500);toggleButton('submit_button');");
             out.println("</script>");
             out.println("</div>");
             out.flush();
        }
    }
}
