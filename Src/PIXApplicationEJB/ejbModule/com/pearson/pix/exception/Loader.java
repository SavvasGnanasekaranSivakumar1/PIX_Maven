package com.pearson.pix.exception;



import java.io.InputStream;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.MissingResourceException;

/**
 * Insert the type's description here.
 * Creation date: (1/3/2002 5:01:11 PM)
 * @author: Administrator
 */
public class Loader 
{
	private static InputStream objInputStream = null;
    /**
     * Loader constructor comment.
     */
    public Loader() 
    {
        super();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/3/2002 5:13:28 PM)
     * @return java.io.InputStream
     * @param Bundle java.lang.String
     */
    public static InputStream load(String bundle) throws AppException 
    {
    		
    	//ClassLoader objClassLoader = ClassLoader.getSystemClassLoader();
    	 String BundleName = bundle.replace('.', File.separatorChar) + ".xml";
        try
        {
        	
        	/* URL objURL = objClassLoader.getResource(BundleName);
             objInputStream = objURL.openStream();
         	return objInputStream;*/
        	
           objInputStream = Loader.class.getClassLoader().getResourceAsStream(BundleName);	
           return objInputStream;
         
        }
        catch (Exception objIOException) 
        {
            AppException objAppException = new AppException();
	        objAppException.setSErrorId(Exceptions.IO_EXCEPTION);
            objAppException.getErrorDetails(Exceptions.IO_EXCEPTION);
     		objAppException.performErrorAction(objIOException,"Loader,load");
			throw objAppException;
        }
       // return objURL;	
    }
}
