package com.pearson.pix.exception;

import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.StringTokenizer;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*******************************************************************************
* This is the class that is responsible for handling exceptions, system as well
* as application thrown by the components and reads a corresponding User
* specific message from the property file which is been populated from the
* database at startup time and populate the class instance for further reference
*
*******************************************************************************/

public class AppException extends java.lang.Exception 
{

    //stores the error id
    private String sErrorId = " ";

    //stores the error description
    private String sErrorDescription = " ";

    //stores the error Severity
    private String sErrorSeverity = " ";

    //stores the error Type
    private String sErrorType = " ";

    //stores the Information
    private String sInformation = " ";

    //stores the Warnings
    private StringBuffer objStringBufferWarnings = new StringBuffer();

    //stroes the messages in case of nested exceptions
    private StringBuffer obj_StringBufferMessages;

    // stores the Throwable object
    private Throwable objThrowableNestedException;

    // store the stackTrace of the exception
    private String stackTraceString;

    // stores the flag for Exception if true AppException object contains
    // Exception otherwise warning or Information
    private boolean bFlag;

    //stores a boolean flag indicating that the error page should include
    // the contact system administrator header
    private boolean bErrorHeader;
    
    //stores the logger for the error
    private static Log log = LogFactory.getLog(AppException.class.getName());
    

    /** Default Constructor for AppException */
    public AppException() {
        super();
    }
    /** Constructor for AppException with String as an input*/
    public AppException(String sMessage) {
        super(sMessage);
    }
    /** Constructor for AppException with Throwable instance as an input*/
    public AppException(Throwable objThrowableException) 
    {
        this.objThrowableNestedException = objThrowableException;
        stackTraceString = generateStackTraceString(objThrowableException);
    }
    
    
    /******************************************************************************
    * This method is used to convert stackTrace into String so that it will be
    * Serialized
    * @method generateStackTraceString
    * @param obj_ThrowableObject java.lang.Throwable
    *******************************************************************************/
    private String generateStackTraceString(Throwable objThrowableObject) 
    {
        StringWriter objStringWriter = new StringWriter();
        objThrowableObject.printStackTrace(new PrintWriter(objStringWriter));
        return objStringWriter.toString();
    }
    /*******************************************************************************
    * This method gets the boolean header Flag instance
    * @method getErrorHeader
    * @param none
    * @return boolean
    *******************************************************************************/

    public boolean getBErrorHeader() 
    {
        return bErrorHeader;
    }
    /*******************************************************************************
    * This getter method returns an boolean , this method reads the property for the
    * Error code and initializes the instance variables by calling setter methods on
    * them
    * @method getErrorDetails
    * @param sCode java.lang.String
    * @return boolean
    *******************************************************************************/
    public boolean getErrorDetails(String sCode) 
    {
       
        try 
        {
            XMLBundle objXMLBundle = XMLBundle.getBundle("ExceptionsBundle");
            
            Properties objExceptionProperties = objXMLBundle.getObject(sCode);

            if (objExceptionProperties != null) 
            {
                String sErrorDesc = objExceptionProperties.getProperty("Message");

                setSErrorDescription(sErrorDesc);

                String sSeverity = objExceptionProperties.getProperty("Severity@value");

                setSErrorSeverity(sSeverity);

                String sType = objExceptionProperties.getProperty("Type@value");

                setSErrorType(sType);
            }
            return true;
        }
        catch (Exception objException) 
        {
        	objException.printStackTrace();
            return false;
            
           
        }
    }
    /*******************************************************************************
    * This getter method gets String Buffer with messages
    * @method getObj_StringBufferMessages
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getObj_StringBufferMessages() {

        return this.obj_StringBufferMessages.toString();
    }
    /*******************************************************************************
    * This getter method returns Warnings
    *
    * @method getObj_StringBufferWarnings
    * @param none
    * @return java.lang.StringBuffer
    *******************************************************************************/
    public StringBuffer getObj_StringBufferWarnings() {
        return this.objStringBufferWarnings;
    }
    /*******************************************************************************
    * This getter method which retuns the Throwable object
    *
    * @method getObj_ThrowableNestedException
    * @param none
    * @return java.lang.Throwable
    *******************************************************************************/
    public Throwable getObj_ThrowableNestedException() {
        return objThrowableNestedException;
    }
    /*******************************************************************************
    * This getter method returns an ErrorDescription
    * @method getSErrorDescription
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getSErrorDescription() {

        return this.sErrorDescription;
    }
    /*******************************************************************************
    * This getter method returns an ErrorId
    * @method getSErrorId
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getSErrorId() {

        return this.sErrorId;
    }
    /*******************************************************************************
    * This gethod return the String which defines the severity of the error
    * @method getSErrorSeverity
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getSErrorSeverity() {

        return this.sErrorSeverity;
    }
    /*******************************************************************************
    * This getter method gets an Error Type
    * @method getSErrorType
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getSErrorType() {
        return this.sErrorType;
    }
    /*******************************************************************************
    * This getter method gets an Information
    * @method getSInformation
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getSInformation() {
        return this.sInformation;
    }
    /*******************************************************************************
    * This method returns the String which has the exception stack Trace message
    *
    * @method getStackTraceString
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String getStackTraceString() {
        if (objThrowableNestedException == null)
            return null;

        StringBuffer traceBuffer = new StringBuffer();

        if (objThrowableNestedException instanceof AppException) {
            String sTempString =
                ((AppException) objThrowableNestedException).getStackTraceString();
            traceBuffer.append(sTempString);
           }
      traceBuffer.append(stackTraceString);
        return traceBuffer.toString();
    }
    /*******************************************************************************
    * This method returns the flag which repesents the exception, if true it is
    * exception otherwise Information or Warning
    *
    * @method getObj_StringBufferWarnings
    * @param none
    * @return java.lang.StringBuffer
    *******************************************************************************/
    public boolean isBFlag() {
        return bFlag;
    }
    /*******************************************************************************
    * This method takes  a String which represents the location of the exception
    * and gets the message from the exception occured and log it in log file.
    *
    * @method performErrorAction
    * @param sMessage java.lang.String
    * @return boolean
    *******************************************************************************/
    public boolean performErrorAction(String sMessage) {

        String sSever = objThrowableNestedException.getMessage();
        return true;

    }
    /*******************************************************************************
    * This method takes the error code and a String which represents the location of 
    * the exception and perform action on each code according to severity it carries
    * if severity is Information or Warning -- set the description and set the
    * exception flag if severity is Error -- set the description and set the
    * exception flag and log it in log file
    *
    * @method performErrorAction
    * @param sError java.lang.String,sMessage java.lang.String
    * @return boolean
    *******************************************************************************/
    public boolean performErrorAction(String sError, String sMessage , Throwable exception) {

        // log the exception trace
        this.logEvents(sMessage,exception);
        return this.handleError(sError);
    }
    
    
    /*******************************************************************************
    * This method takes the error code and a String which represents the location of 
    * the exception and perform action on each code according to severity it carries
    * if severity is Information or Warning -- set the description and set the
    * exception flag if severity is Error -- set the description and set the
    * exception flag and log it in log file
    *
    * @method performErrorAction
    * @param sError java.lang.String,sMessage java.lang.String
    * @return boolean
    *******************************************************************************/
    public boolean performErrorAction(String sError, String sMessage ) {
        
     return this.handleError(sError);
      
    }
    /*******************************************************************************
    * This method takes a Throwable object and a String which represents the
    * location of the exception and set the description and set the exception flag
    * and log it in the log file
    *
    * @method performErrorAction
    * @param obj_ThrowableInstance java.lang.Throwable,sMessage java.lang.String
    * @return boolean
    *******************************************************************************/
    public boolean performErrorAction(Throwable obj_ThrowableInstance, String sMessage) 
    {
        String sError = null;
        if (obj_ThrowableInstance instanceof SQLException) 
        {
            setBFlag(false);

        if(((SQLException) obj_ThrowableInstance).getMessage().startsWith("ORA")) 
        {
        	StringTokenizer objStringTokenizer = new StringTokenizer( ((SQLException) obj_ThrowableInstance).getMessage(), ":");
        	if(objStringTokenizer.hasMoreTokens())
        	{
        		sError = objStringTokenizer.nextToken();
        		String sTemp = getSErrorDescription();
        		/**
        		 * Obtain error description if it is not set and prepend the error 
        		 * code 
        		 */
        		if( (sTemp != null) || (!sTemp.equals("")) || (sTemp.length() != 0)) 
	        		setSErrorDescription(sError + "-" + getSErrorDescription());
	        	else	
	                setSErrorDescription(sError);
        	}
        }
    }   
	
     
        sError = (String) obj_ThrowableInstance.getMessage();
        return true;
    }
    /*******************************************************************************
    * This method sets the boolean header Flag instance
    *
    * @method setErrorHeader
    * @param bNewErrorHeader boolean
    * @return void
    *******************************************************************************/

    private void setBErrorHeader(boolean bNewErrorHeader) {
        bErrorHeader = bNewErrorHeader;
    }
    /*******************************************************************************
    * This method sets the boolean Flag instance
    *
    * @method setBFlag
    * @param bFlag boolean
    * @return void
    *******************************************************************************/
    public void setBFlag(boolean bFlag) {
        this.bFlag = bFlag;
    }
    /*******************************************************************************
    * This setter method sets String Buffer with messages
    * @method setObj_StringBufferMessages
    * @param sMessage java.lang.String
    * @return void
    *******************************************************************************/
    public void setObj_StringBufferMessages(String sMessage) {

        if (sMessage != null)
            this.obj_StringBufferMessages =
                obj_StringBufferMessages.append(sMessage).append("  ");
    }
    /*******************************************************************************
    * This setter method sets  Warnings
    * @method setObj_StringBufferWarnings
    * @param sWarnings java.lang.String
    * @return void
    *******************************************************************************/
    public void setObj_StringBufferWarnings(String sWarnings) {
        this.objStringBufferWarnings.append(sWarnings);
    }
    /*******************************************************************************
    * This setter method sets an ErrorDescription
    * @method setSErrorDescription
    * @param sErrorDescription java.lang.String
    * @return void
    *******************************************************************************/
    public void setSErrorDescription(String sErrorDescription) {

        this.sErrorDescription = sErrorDescription;
    }
    /*******************************************************************************
    * This setter method sets an ErrorId
    * @method setSErrorId
    * @param sErrorId java.lang.String
    * @return void
    *******************************************************************************/
    public void setSErrorId(String sErrorId) {
        this.sErrorId = sErrorId;
    }
    /*******************************************************************************
    * This setter method sets an Error Severity
    * @method setSErrorSeverity
    * @param sErrorSeverity java.lang.String
    * @return void
    *******************************************************************************/
    public void setSErrorSeverity(String sErrorSeverity) {
        this.sErrorSeverity = sErrorSeverity;
    }
    /*******************************************************************************
    * This setter method sets an Error Type
    * @method setSErrorType
    * @param sErrorType java.lang.String
    * @return void
    *******************************************************************************/
    public void setSErrorType(String sErrorType) {

        this.sErrorType = sErrorType;
    }
    /*******************************************************************************
    * This setter method sets an Information
    * @method setSInformation
    * @param sInformation java.lang.String
    * @return void
    *******************************************************************************/
    public void setSInformation(String sInformation) {

        this.sInformation = sInformation;
    }
    /*******************************************************************************
    * This method overrides the toString method of Throwable class
    *
    * @method toString
    * @param none
    * @return java.lang.String
    *******************************************************************************/
    public String toString() {

        StringBuffer theMsg = new StringBuffer(super.toString());

        if (getObj_ThrowableNestedException() != null) {
            theMsg.append("\n\t");
            theMsg.append("[");
            theMsg.append(getObj_ThrowableNestedException());
            theMsg.append("]");
        }
        return theMsg.toString();
    }
    
    /*******************************************************************************
    * It uses the Logger to log the exceptions in a log file.
    *
    * @method logEvents
    * @param message
    * @param exception
    *******************************************************************************/
       
    
    
    private void logEvents(String message , Throwable exception)
    {
    	//stores error message into the log file 
        if(log.isErrorEnabled())
        {
        	log.error(message,exception);
        }
        	
        	
        	//tokenise the message using comma
            /*StringTokenizer tokens = new StringTokenizer(message,",");
            Properties properties = new PIXENV().getProperties();
            System.out.println(properties);
            String logFolder = properties.getProperty(PIXConstants.PIX_LOG_FILE_PATH);
            FileHandler handler = new FileHandler(logFolder+File.separator+"pixlog.log",true);
            handler.setFormatter(new SimpleFormatter());
            // Send logger output to our FileHandler.
            logger.addHandler(handler);
            // Request that every detail gets logged.
            logger.setLevel(Level.SEVERE);
            LogRecord record = new LogRecord(Level.SEVERE,"");
            record.setSourceClassName(tokens.nextToken());
            record.setSourceMethodName(tokens.nextToken());
            record.setThrown(exception);
            logger.log(record);*/
        
    }
 
    private boolean handleError(String sError)
    {
        if (getErrorDetails(sError)) {

            String sSever = getSErrorSeverity();

            if (sSever.equals("Information") || sSever.equals("Warning")) {
                setBFlag(false);
            }
            else if ((sSever.equals("Error")) || (sSever.equals("Fatal"))) {
                String sExcepObject = null;
                setBFlag(true);
                setSErrorDescription(getSErrorDescription());

            }
            else {
                String sTempString = "Unknown Error Encountered , Please contact Administrator";
                setSErrorDescription(sTempString);
                setBFlag(true);
            }
            return true;
        }
        else {
            String sTempString = "Unknown Error Encountered , Please contact Administrator";
            setSErrorDescription(sTempString);
            setBFlag(true);
            return false;
        }
        
    }
	
} //end of class AppException
