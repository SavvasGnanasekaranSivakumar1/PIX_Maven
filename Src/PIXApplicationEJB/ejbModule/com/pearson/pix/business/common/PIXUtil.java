package com.pearson.pix.business.common;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pearson.pix.dto.admin.User;
import com.pearson.pix.dto.admin.UserPriv;
import com.pearson.pix.exception.AppException;



public class PIXUtil {
	
	
	private final int Xbits_key_len = 12;

	 private char [] Xbits_key =
	    {
	     23, 18, 71, 35, 43, 11, 17, 15, 13, 19, 28, 21
	    };
	
	
	
	 /**
     * This mehtod can use to convert boolean value to an integer
     */
    public static int booleanToInt(boolean b)
	{
		if(b)
			return 1;
		return 0;
	}

/**
 * This mehtod can use to conver int value to boolean
 */
    public static boolean intToBoolean(int i)
	{
		if(i==1)
			return true;
		return false;
	}
    
      
    public static String dateToString(java.sql.Date date)
	{
		/* String strDate;
		int day;
		int month;
		int year;
		day = date.getDate();
		month = date.getMonth()+1;
		year = date.getYear()+1900;
		
		strDate =day+"/"+month+"/"+year; */
		
		return null;
	}
    
    /*
     * Converts the passed Double object to String. For null object, returns "Not Available"
     *
     * @param	String      input Double object (null or not-null)
     * @return	String      output string ("Not Available" or converted String )
     * Author: zaheerm
     */
	public static String doubleToString(Double dblParam)
	{
		if(dblParam == null)
		{
			return "Not Available";
		}
		else
		{
			return dblParam.intValue() + "";
		}
	}
	
	  /*
     * Checks whether the passed string is null or not
     *
     * @param	String      input string (null or not-null)
     * @return	String      output string ("" or input not-null String )
     * Author: zaheerm
     */
	public static String checkNull(String strParam)
	{
		if(strParam == null)
		{
			return "";
		}
		else
		{
			return strParam;
		}
	}
		
	 /*
     * Strips the white spaces out of a string and replaces them with blank.
     *
     * @param	String      input string (with white spaces)
     * @return	String      output string (without white spaces)
     * Author: zaheerm
     */
    public static String stripWhiteSpaces(String strParam)
    {
        String str = checkNull(strParam);
		return str.replaceAll("[ \t\n\f\r]", "");
    }
    
    /*
     * Checks to see if a given String is numeric... *grumble*
     * Author: zaheerm
     */
    public static boolean isNumeric(String str) {
        boolean isnumeric = true;

        try {
           // BigDecimal test = new BigDecimal(str);
        }

        catch (NumberFormatException e) {
            isnumeric = false;
        }

        return isnumeric;
    }
    
    
    /*
     * Checks to see if a given String is numeric AND a whole number
     * Author: zaheerm
     */
    public static boolean isIntegerNumeric(String str) {
        boolean isnumeric = true;

        try {
         //   int d = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            isnumeric = false;
        }
        return isnumeric;
    }

    /**
     * Used to check if the parameter sent inside is null, blank or "null", then
	 * return false else true is returned indicaing a valid value
     * @param sField String object representing the data
     * @return true of false
	 * Author: zaheerm
	 */
	public static boolean checkNullField(String sField){
		if(sField == null || (sField.trim()).equals("") || (sField.trim()).equals("null"))
			return false;
		else
			return true;
	}
    
	 /*
     * Strips the newline characters out of a string and replaces them with blank.
     *
     * @param	String      input string (with newline characters)
     * @return	String      output string (without newline characters)
     * @author	zaheerm
     */
    public static String stripNewlineCharacters(String str)
    {
        if((str == null) || (str.length() == 0))
            return str;

        String returnString = str.replaceAll("\n", "");
        if(returnString != null)
            returnString = returnString.replaceAll("\r", "");

        return returnString;
    }
    
    /** This function used to get the newmber of week in this year...     ***/
	   public static int getWeekOfTheYear(String strDate)
		{
	
			String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
			SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
			Calendar calendar = new GregorianCalendar(pdt);
			calendar.set(Integer.parseInt(strDate.substring(0,4)),Integer.parseInt(strDate.substring(4,6))-1,Integer.parseInt(strDate.substring(6,8)));
		    return(calendar.get(Calendar.WEEK_OF_YEAR)-1); 
		
		}
       
	   /**
	     * Returns the current Year
	     * @return int
	     * 
	     */
	    public int getCurrentYear() 
	    {
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.YEAR);       
	    }
    
	    /**
	     * Method to encrypt a string .... usually the password string 
	     * @param password : string to be encrypted 
	     * @return String : Encypted string 
	     * @common:operation
	     */
	    public String encryptString(String password)
	    {
	        if(password == null) 
	                    return null;
	        
	        char c;
	        int k = 0;
	        int len = password.length();
	        
	        StringBuffer ePassword = new StringBuffer();
	        
	        for(int i = 0 ; i < len ; i++,k++)
	        {
	            c = password.charAt(i);
	            if( k == Xbits_key_len) k = 0;
	            char temp = (char)((int)c + (int)Xbits_key[k]);
	            ePassword.append(temp);
	        }
	        
	        return ePassword.toString();
	    }
	    
	    /**
	     * Method to de-crypt a string .... usually the password string 
	     * @param ePassword : string to be de-crypted 
	     * @return String : de-cypted string 
	     * @common:operation
	     */
	    public String decryptString(String ePassword)
	    {
	        if(ePassword == null) 
	                    return null;
	        
	        char c;
	        int k = 0;
	        int len = ePassword.length();
	        StringBuffer dPassword = new StringBuffer();
	        
	        for(int i = 0 ; i < len ; i++,k++)
	        {
	            c = ePassword.charAt(i);
	            if( k == Xbits_key_len) k = 0;
	            char temp = (char)((int)c - (int)Xbits_key[k]);
	            dPassword.append(temp);
	        }
	        
	        return dPassword.toString();
	    }
	    
	    public static String getCurrentDate()
		{
			/*
	    	 * retrieve the current date
	    	 */
			String currentDate = "";
			SimpleDateFormat objcurrentDate = new SimpleDateFormat("yyyy/MM/dd");
				currentDate = objcurrentDate.format(new Date());
	    	return currentDate;
		}

		public static String getCurrentTime()
		{
			/*
	    	 * retrieve the current time
	    	 */
			String currentTime = "";
				SimpleDateFormat objcurrentTime = new SimpleDateFormat("hh/mm/ss");
				currentTime = objcurrentTime.format(new Date());
	    	return currentTime;
		}
	    
		/**
		 * <p>
		 * This method is used for left padding the string 
		 * with blanks to make it of some certain length.
		 * <p>
		 * @param str
		 * @param noOfPads
		 * @return
		 */
		public static String leftPad(String strToPad,String pad, int noOfPads)
	    {
			String tempStr = "";
			for(int i=strToPad.length();i<noOfPads;i++)
			{
				tempStr += pad; 
			}
			return tempStr + strToPad;
		}
		
		/**
		 * <p>
		 * This method is used for right padding the string 
		 * with blanks to make it of some certain length.
		 * <p>
		 * @param str
		 * @param noOfPads
		 * @return
		 */
		public static String rightPad(String strToPad,String pad,int noOfPads)
	    {
		  String tempStr = strToPad;
		  for(int i=strToPad.length();i<noOfPads;i++)
		   {
			 tempStr += pad; 
		   }
			return tempStr;
		}
		
		/**
		 * It validates the given whether the given date is in correct format.
		 * @param date as yyyymmdd format
		 */
		//private SimpleDateFormat CurrentDate = null;
		public static boolean validateDate(String date) throws NumberFormatException
		{
			
			int[] days_in_month = {31,28,31,30,31,30,31,31,30,31,30,31};
			boolean IsValidDate= false;
			int year;
			int month;
			int day;
			
			// If date is blank, set it to 99999999
			if (date.trim().equals("")) date="99999999";
			
			// If date is 99999999 or 0000000, do not validate
			if (date.equals("99999999") ||date.equals("00000000") )
			{
				IsValidDate = true;
			}
			else  
			{	
				try
				{
					IsValidDate = true;
					year = Integer.parseInt(date.substring(0,4));
					month = Integer.parseInt(date.substring(4,6));       
					day = Integer.parseInt(date.substring(6,8));
							
					if((month == 2) && (year%4 == 0))
						if (day>(days_in_month[month-1]+1) || day<1)
							return false;		
					if(month>12 || month <1)
						return false;
					else if(day>days_in_month[month-1] || day<1)		
						return false;		
					}catch(Exception e){
						return  false;
				}
			}
			return IsValidDate;
		}
		
	public static boolean compareDate(String fromDate,String toDate){
		
		
		try{
		 /*   int fromYear;
			int fromMonth;
			int fromDay;
			int toYear;
			int toMonth;
			int toDay;
			try{
				fromYear = Integer.parseInt(fromDate.substring(0,4));
				fromMonth = Integer.parseInt(fromDate.substring(4,6));
				fromDay = Integer.parseInt(fromDate.substring(6,8));			
				toYear = Integer.parseInt(toDate.substring(0,4));
				toMonth = Integer.parseInt(toDate.substring(4,6));
				toDay = Integer.parseInt(toDate.substring(6,8));
				return new Date(toYear,toMonth,toDay).after(new Date(fromYear,fromMonth,fromDay)); */
				return true;
			}
			catch(Exception e)
			{
				return false;
			}		
			
		}	
		
	 /*
     * It returns the currency.
     *
     * @param	String      input currencyCode 
     * @return	String      output string 
     * @author	mohit
     */
	public static String getCurrency(String currencyCode,Double currency)
	{
             java.text.NumberFormat fNumber = java.text.NumberFormat.getInstance();
			 String curr = "";
			 if(currency!= null) curr = fNumber.format(currency);
			 if(currencyCode != null){
					 if(currencyCode.equals("USD"))	return "$"+curr;
					 else if(currencyCode.equals("CAD"))  return ("$"+curr+" "+currencyCode);
					 else if(currencyCode.equals("HKD"))  return ("$"+curr+" "+currencyCode);
					 else  return  (curr+" "+currencyCode);
			 }
			 return curr ;
	}

	
	/*
     * It returns the Number in format ##,### (US format).
     * @param	String      input lngValue 
     * @return	String      output string 
     * @author	Mohit
     */
	public static String getCommaNumber(long lngValue)
	{
		return java.text.NumberFormat.getInstance(java.util.Locale.US).format(lngValue);
	}
		
    
	public static int getNextPage(HttpServletRequest request,int currentValue,int size) throws AppException
	{
		int nextValue = 0;
		if(size > 10)
		{
		    nextValue = currentValue + 10; 
		    request.setAttribute("nextValue",String.valueOf(nextValue));
		}
		return nextValue;
	}
	
	public static int getPrevPage(HttpServletRequest request,int currentValue)
	{
		int prevValue = 0;
		if(currentValue > 10)
		{
			prevValue = currentValue - 10;
			request.setAttribute("prevValue",String.valueOf(prevValue));
		}
		return prevValue;
	}
	
	/**
     * This method returns the String format of date in MM/dd/yyyy format.
     *
     * @param inputDate String
     * @return String
     */
    public static String sqlToStandardDate(String inputDate) throws AppException{
        String stdDate = null;
        if (inputDate != null && !"".equals(inputDate)) {
        	try
        	{
        		if(inputDate.indexOf('-')>0){
	        		inputDate = inputDate.replace(' ','-');
	        		String element[] = inputDate.split("-");	//Input date is YYYY-MM-DD HH:MI:SS:000
	        		if(element.length >= 2){
	        			stdDate = element[1] + "/" + element[2] + "/" + element[0];	//stdDate is MM/dd/yyyy
	        		}
        		}
        		else{
        			return inputDate;
        		}
        	   		
        	}
        	catch(PatternSyntaxException pse)
        	{
        		AppException ae = new AppException();
        		pse.printStackTrace();
     		   	ae.performErrorAction("Exceptions.DATE_CONVERSION_ERROR","PIXUtil,sqlToStandardDate",pse);
     		   	throw ae;
        	}
        	return stdDate;
        }
        else
        {
        	return inputDate;
        }
        
    }
    
	/**
     * This method returns the String format of date in DD-Mon-YYYY format.
     *
     * @param inputDate String
     * @return String
     */
    public static String standardToSqlDate(String inputDate) throws AppException{
        String stdDate = null;
        //System.out.println("inputDate=="+inputDate);
        if (inputDate != null && !"".equals(inputDate)) {
        	try
        	{
        		if(inputDate.indexOf('/')>0){
	        		inputDate = inputDate.replace(' ','/');
	        		String element[] = inputDate.split("/");	//Input date is MM/dd/yyyy
	        		int month = Integer.parseInt(element[0]);
	        		String Mon = "";
	        		switch(month){
	        		case 1:	Mon = "Jan";break;
	        		case 2:	Mon = "Feb";break;
	        		case 3:	Mon = "Mar";break;
	        		case 4:	Mon = "Apr";break;
	        		case 5:	Mon = "May";break;
	        		case 6:	Mon = "Jun";break;
	        		case 7:	Mon = "Jul";break;
	        		case 8:	Mon = "Aug";break;
	        		case 9:	Mon = "Sep";break;
	        		case 10:	Mon = "Oct";break;
	        		case 11:	Mon = "Nov";break;
	        		case 12:	Mon = "Dec";break;
	        		}
	        		
	        		if(element.length >= 2){
	        			stdDate = element[1] + "-" + Mon + "-" + element[2];	//stdDate is DD-Mon-YYYY
	        		}
        		}
        		else{
        			return inputDate;
        		}	
        	}
        	catch(PatternSyntaxException pse)
        	{
        		AppException ae = new AppException();
        		pse.printStackTrace();
     		   	ae.performErrorAction("Exceptions.DATE_CONVERSION_ERROR","PIXUtil,standardToSqlDate",pse);
     		   	throw ae;
        	}
        	catch(NumberFormatException nfe)
        	{
        		AppException ae = new AppException();
        		nfe.printStackTrace();
     		   	ae.performErrorAction("Exceptions.DATE_CONVERSION_ERROR","PIXUtil,standardToSqlDate",nfe);
     		   	throw ae;
        	}
            
        }
        return stdDate;
    }    
    
	/**
     * This method returns the Privileges of the user for a module (Book Spec, PO, Planning etc.)
     * @param request HttpServletRequest
     * @param moduleCode String
     * @return String
     */
    public static String getPrivilege(HttpServletRequest request,String moduleCode) throws AppException{
    	HttpSession session = null;
    	User objUser = null;
    	Vector privCollection = null;
    	UserPriv objUserPriv = null;
    	String accessFlag = "";
    	String accessRight = "";
    	try{
    		session = request.getSession();
    		if(session.getAttribute("USER_INFO")!=null)
    		{
	    		objUser = (User)session.getAttribute("USER_INFO");
	    		privCollection = (Vector)objUser.getPrivilegeCollection();
	    		for(int i=0;i<privCollection.size();i++){
	    			objUserPriv = (UserPriv)privCollection.get(i);
	    			System.out.println("RefCode=="+objUserPriv.getModuleDetail().getRefCode()); ///....
	    			if(moduleCode.equalsIgnoreCase(objUserPriv.getModuleDetail().getRefCode())){
	    				accessFlag = objUserPriv.getAccessFlag();
	    				//System.out.println("accessFlag=="+accessFlag);//.....
	    				if((PIXConstants.READ_RIGHT).equalsIgnoreCase(accessFlag)){
	    					accessRight = "READ";
	    				}
	    				else if((PIXConstants.WRITE_RIGHT).equalsIgnoreCase(accessFlag)){
	    					accessRight = "WRITE";
	    				}
	    				else if((PIXConstants.BOTH_RIGHT).equalsIgnoreCase(accessFlag)){
	    					accessRight = "BOTH";
	    				}
	    				else if((PIXConstants.NONE_RIGHT).equalsIgnoreCase(accessFlag)){
	    					accessRight = "NONE";
	    				}
	    				//System.out.println("moduleCode="+moduleCode+"		accessRight="+accessRight);///..........
	    				break;
	    			}
	    		}
    		}
    		return accessRight;
    	}
    	catch(Exception e){
    		AppException ae = new AppException();
    		e.printStackTrace();
 		   	ae.performErrorAction("Exceptions.EXCEPTION","PIXUtil,getPrivilege",e);
 		   	throw ae;
    	}
    }
    
    /**
     * This method checks the Write Privileges of the Vendor for a module (Book Spec, PO, Planning etc.)
     * @param request HttpServletRequest
     * @param moduleCode String
     * @return boolean
     */
    public static boolean checkVendorWriteAccess(HttpServletRequest request,String moduleCode) throws AppException{
       	HttpSession session = null;
    	User objUser = null;
    	String accessRight = "";
    	String roleType = "";
    	boolean vendorRight = false;
    	try{
    		session = request.getSession();
    		if(session.getAttribute("USER_INFO")!=null)
    		{
	    		objUser = (User)session.getAttribute("USER_INFO");
	    		if(objUser!=null)
	    		{
	    			roleType = objUser.getRoleTypeDetail().getRoleType();
	    			accessRight = getPrivilege(request,moduleCode);
	    			//System.out.println("IN checkVendorWriteAccess()..moduleCode=>"+moduleCode);
	    			//System.out.println("IN checkVendorWriteAccess()..accessRight=>"+accessRight);
	    			if(((PIXConstants.VENDOR_ROLE).equals(roleType) ||(PIXConstants.MILL_ROLE).equals(roleType))&& (("WRITE".equals(accessRight) || "BOTH".equals(accessRight)))){
	    				vendorRight = true;
	    			}
	    		}
	    		
    		}
    		return vendorRight;
    	}
    	catch(Exception e){
    		AppException ae = new AppException();
    		e.printStackTrace();
 		   	ae.performErrorAction("Exceptions.EXCEPTION","PIXUtil,getPrivilege",e);
 		   	throw ae;
    	}    	
    }
    
    /**
     * This method checks the Read/Write/Both Privileges of the User for a module (Book Spec, PO, Planning etc.)
     * @param request HttpServletRequest
     * @param moduleCode String
     * @return boolean
     */
    public static boolean checkUserReadWriteAccess(HttpServletRequest request,String moduleCode) throws AppException{
       	HttpSession session = null;
    	User objUser = null;
    	String accessRight = "";
    	String roleType = "";
    	boolean vendorRight = false;
    	try{
    		session = request.getSession();
    		if(session.getAttribute("USER_INFO")!=null)
    		{
	    		objUser = (User)session.getAttribute("USER_INFO");
	    		if(objUser!=null)
	    		{
	    			roleType = objUser.getRoleTypeDetail().getRoleType();
	    			accessRight = getPrivilege(request,moduleCode);
	    			/*Commented by Arvind Yadav for user access on 26/06/10*/
//	    			if((PIXConstants.BUYER_ROLE).equals(roleType) || (PIXConstants.ADMIN_ROLE).equals(roleType) || ((PIXConstants.VENDOR_ROLE).equals(roleType) || (PIXConstants.MILL_ROLE).equals(roleType) || (PIXConstants.COST_ACCOUNTING_ROLE).equals(roleType) && ("READ".equals(accessRight) || "WRITE".equals(accessRight) || "BOTH".equals(accessRight)))){
//	    				vendorRight = true;
//	    			}
	    			if((PIXConstants.BUYER_ROLE).equals(roleType) || (PIXConstants.ADMIN_ROLE).equals(roleType) || ((PIXConstants.VENDOR_ROLE).equals(roleType) || (PIXConstants.MILL_ROLE).equals(roleType) || (PIXConstants.COST_ACCOUNTING_ROLE).equals(roleType))){
	    				if(("READ".equals(accessRight) || "WRITE".equals(accessRight) || "BOTH".equals(accessRight)))
	    						{		
	    				vendorRight = true;
	    						}
	    			}
	    		}
	    		
    		}
    		return vendorRight;
    	}
    	catch(Exception e){
    		AppException ae = new AppException();
    		e.printStackTrace();
 		   	ae.performErrorAction("Exceptions.EXCEPTION","PIXUtil,getPrivilege",e);
 		   	throw ae;
    	}    	
    }    
    
    /**
     * Used to escape the HTML characters in the string 
     * @param sField String object representing the data
     * @return escaped String
	 */
	public static String escapeHTMLChars(String sField){
		if(checkNullField(sField))
		{
			sField = sField.replaceAll("\'","\'\'");
		}
		return sField;	
	}
	
    /**
     * Used to Un-escape the HTML characters in the string 
     * @param sField String object representing the data
     * @return un-escaped String
	 */
	public static String unEscapeHTMLChars(String sField){
		if(checkNullField(sField))
		{
			sField = sField.replaceAll("\'\'","\'");
		}
		return sField;	
	}	
	
	/** Added by Arvind for checking null value
	    * Return true if passed parameter is null otherwise false;
	    * 
	    * @param    value
	    * @return   boolean 
	    */
	   public static boolean isNull(String value) {
	      return value == null || "".equals(value.trim()); 
	   }
}
