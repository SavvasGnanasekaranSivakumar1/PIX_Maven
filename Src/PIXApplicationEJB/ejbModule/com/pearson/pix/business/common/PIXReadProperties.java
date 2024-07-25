package com.pearson.pix.business.common;


import java.io.InputStream;
import java.util.Properties;

import com.pearson.pix.exception.AppException;
import com.pearson.pix.exception.Exceptions;


public class PIXReadProperties {
	
	
	
	
	private  static Properties envProps;
	

	public Properties getProperties(String fileName) throws AppException
	{

		    if(fileName.equalsIgnoreCase(PIXConstants.ENVPROPS))
	        {
	           envProps = loadProperties(fileName,envProps);
	           
	        }
		    return envProps;
	       
	       
   }

	/**
	 * <p>Checks if the properties file has already been loaded if not then loads the file in the
	 * properties object otherwise returns the already loaded object.</p>
	 * @param String - file Name
	 * @param Properties - properties object.
	 * @return Properties - properties object.
	 */
	private Properties loadProperties(String fileName,Properties props) throws AppException
	{
	    try
	    {
	        if(props != null)
	        {
	            return props;
	        }
	        props = new Properties();
	        
	       // InputStream FileStream = this.getClass().getClassLoader().getResourceAsStream("ExceptionsBundle.xml");
	       //System.out.println("*******" + FileStream);
	      
	        
	       InputStream propFileStream = this.getClass().getClassLoader().getResourceAsStream(fileName+".properties");
	       props.load(propFileStream);
	    }
	   catch(Exception se)
	   {
	        AppException appException = new AppException();		
	        appException.performErrorAction(Exceptions.PROPERTY_FILE_EXCEPTION, 
	        "PIXReadProperties,loadProperties",se);
	        throw appException;
	   }
	   return props;
	}
	
}
