package com.pearson.pix.exception;


public interface Exceptions 
{
	/****** System Exceptions defined here ********/

	public static final String EXCEPTION = "9000";

	public static final String SQL_EXCEPTION = "9001";

	public static final String REMOTE_EXCEPTION = "9002";

	public static final String NAMING_EXCEPTION = "9003";

	public static final String CREATE_EXCEPTION = "9004";

	public static final String IO_EXCEPTION = "9005";

	public static final String MISSING_RESOURCE_EXCEPTION = "9006";
	
	public static final String SESSION_EXCEPTION = "9007";
	
	public static final String SAX_EXCEPTION = "9008";
	
	public static final String CLASSNOTFOUND_EXCEPTION = "9009";
	
	public static final String MESSAGING_EXCEPTION = "9010";	

	public static final String SEND_FAILED_EXCEPTION = "9011";	
	
	public static final String SAVE_FAILED_EXCEPTION = "9012";
	
	public static final String FILE_NOT_FOUND_EXCEPTION = "9013";
	
	public static final String SERVLET_EXCEPTION = "9014";
	
	public static final String CLONE_EXCEPTION = "9015";
	
	public static final String MALFORMED_URL_EXCEPTION = "9016";
    
    public static final String INTERRUPTED_EXCEPTION = "9017";
	
    public static final String MISMATCH_QTY_EXCEPTION = "9018";
    
    public static final String ARITHMATIC_EXCEPTION = "9019";
    
    public static final String RECORDS_NOT_EXISTS = "9020";
    
    public static final String DATE_CONVERSION_ERROR = "9021";
   
    public static final String RECORDS_NOT_EXISTS_USER = "9022";
    
    public static final String RECORDS_NOT_EXISTS_PUBLISHER = "9023";
    
    public static final String RECORDS_NOT_EXISTS_PLANNING = "9024"; 
    
    public static final String RECORDS_NOT_EXISTS_ORDER = "9025";
    
    public static final String RECORDS_NOT_EXISTS_ORDER_DETAIL = "9026";
    
    public static final String RECORDS_NOT_EXISTS_BOOK_SPEC = "9027";
    
    public static final String RECORDS_NOT_EXISTS_REPORT = "9028";
    
    public static final String RECORDS_NOT_EXISTS_INVENTORY = "9029";
    
    public static final String RECORDS_NOT_EXISTS_DELIVERY_MESSAGE = "9030";
    
    public static final String RECORDS_NOT_EXISTS_GOODS_RECEIPT= "9031";
    
    public static final String RECORDS_NOT_EXISTS_ORDER_STATUS= "9032";
    
    public static final String RECORDS_NOT_EXISTS_USAGE= "9033";
    
    public static final String RECORDS_NOT_EXISTS_PLANNING_DETAIL = "9034";
    
    public static final String RECORDS_NOT_EXISTS_SUPPLIER = "9035";
    
    public static final String ERROR_OCCURED_TOOPEN_PDF = "9036";
    
    public static final String RECORDS_NOT_EXISTS_POTENTIALARP = "9046";  
    
    public static final String DM_NOT_EXISTS="9037";  
    
    public static final String RECORDS_NOT_EXISTS_MISMATCHREPORT_DETAIL = "9038";
    
    public static final String PO_NOT_EXISTS_MISMATCHREPORT_DETAIL = "9039";
    public static final String APPROVAL_MESSAGE_NOT_EXISTS = "9047";
    /****** Business Exceptions defined here ********/


	/**
	 *  Exceptions starting from 5000 onwards
	 */	
    
    public static final String INVALID_USER = "5001";
    public static final String INVALID_PASSWORD = "5002";
    public static final String ACCOUNT_LOCKED = "5003";
    public static final String ROLE_NOT_EXISTS = "5004";
    public static final String COMPANY_NOT_EXISTS = "5005";
    public static final String PASSWORD_NOT_CHANGED = "5006";
    public static final String PROFILE_NOT_CHANGED = "5007";
    public static final String NO_BUYER_GROUP_WITH_CURRENT_USER = "5008";
    public static final String SUBSIDIARY_NOT_EXISTS = "5009";
    public static final String USERNAME_DUPLICATION = "5015";
    public static final String SUPPLIER_NOT_EXIST = "5016";
    public static final String INITIALS_DUPLICATION = "5017";
    public static final String PRODUCT_GROUPNAME_DUPLICATION = "5018";
    public static final String BUYER_GROUPNAME_DUPLICATION = "5019";
    public static final String RECORD_ALREADY_UPDATED = "5020";
    public static final String EMAIL_ID_DUPLICATION = "5021";
    public static final String RFO_MULTIPLE_SUPPLIER_NOT_ALLOWED = "5022";
    public static final String PROPERTY_FILE_EXCEPTION="5023";
    public static final String EXCEL_PATH_NULL_EXCEPTION="5024";
	public static final String SAN_ALREADY_EXISTS="5030";
	public static final String USER_ALREADY_EXISTS="5031";
	public static final String DATA_CUNCURRENCY="5032";
	
	//Product Cache Exception
	public static final String PRODUCT_CACHE_UPDATION="5029";
	
	
	
	
}