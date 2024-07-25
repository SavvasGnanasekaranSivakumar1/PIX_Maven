package com.pearson.pix.presentation.fileuploading.command;

/**
 * This Listener found here
 * http://www.ioncannon.net/java/38/ajax-file-upload-progress-for-java-using-commons-fileupload-and-prototype/
 * Rick Reumann modified it slightly and added Enum instead of using String for status. Also returns % complete.
 * 
 */
import com.missiondata.fileupload.OutputStreamListener;

public class FileUploadListener implements OutputStreamListener {
    private FileUploadStats fileUploadStats = new FileUploadStats();

    public FileUploadListener(long totalSize) {
        fileUploadStats.setTotalSize(totalSize); 
    }

    public void start() {
    	
    	//1048576  2097152
    	if(fileUploadStats.getTotalSize()>4194304){
        fileUploadStats.setCurrentStatus(FileUploadStatus.ERROR);
    	}else{
    		 fileUploadStats.setCurrentStatus(FileUploadStatus.START);
    	}
    }

    public void bytesRead(int byteCount) {
    	
    	if(fileUploadStats.getTotalSize()>4194304){
            fileUploadStats.setCurrentStatus(FileUploadStatus.ERROR);
        	}else{
        		    fileUploadStats.incrementBytesRead(byteCount);
        	        fileUploadStats.setCurrentStatus(FileUploadStatus.READING);
        	}
    }

    public void error(String s) {
        fileUploadStats.setCurrentStatus(FileUploadStatus.ERROR);
    }

    public void done() {
    	
    	if(fileUploadStats.getTotalSize()>4194304){
            fileUploadStats.setCurrentStatus(FileUploadStatus.ERROR);
        	}else{
        		 fileUploadStats.setBytesRead(fileUploadStats.getTotalSize());
        	     fileUploadStats.setCurrentStatus(FileUploadStatus.DONE);
        	}
      
    }

    public FileUploadStats getFileUploadStats() {
        return fileUploadStats;
    }

    public static class FileUploadStats {
        
        private long totalSize = 0;
        private long bytesRead = 0;
        private double percentComplete = 0.0;
        private long startTime = System.currentTimeMillis();
        private String currentStatus=FileUploadStatus.NONE;
      

        public long getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(long totalSize) {
            this.totalSize = totalSize;
        }

        public long getBytesRead() {
            return bytesRead;
        }

        public long getElapsedTimeInMilliseconds() {
            return (System.currentTimeMillis() - startTime);
        }

        public String getCurrentStatus() {
            return currentStatus;
        }
        
        public double getPercentComplete() {
            if ( totalSize != 0 ) {
            	
            	if(this.bytesRead>this.totalSize)
            		this.bytesRead=this.bytesRead-this.totalSize;
                percentComplete = (double)bytesRead/(double)totalSize;
            }
            return percentComplete;
        }
        public void setCurrentStatus(String currentStatus) {
            this.currentStatus = currentStatus;
        }

        public void setBytesRead(long bytesRead) {
            this.bytesRead = bytesRead;
            
        }

        public void incrementBytesRead(int byteCount) {
        	
            this.bytesRead += byteCount;
            
        }
    }
    
    public static class FileUploadStatus {
    	static String START="start";
    	static  String NONE="none";
    	static String READING="reading";
    	static String ERROR="error";
    	static String DONE="done";
        
        private String type;
        FileUploadStatus(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }
}
