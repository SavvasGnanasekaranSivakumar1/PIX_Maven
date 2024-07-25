package com.pearson.pix.business.common;


import java.util.Properties;

import com.pearson.pix.exception.AppException;




public class PIXENV {
	
  //	private  static Properties props;
//	This is temp. property
	//public static String IMG_STORE_PATH ;

	/**
	 * This utility method is used to read the properties from the properties file.
	 * @return Properties - properties object containing properties.
	 * @throws Exception.AppException 
	 */
	public Properties getProperties()throws AppException
	{
	    Properties props = new PIXReadProperties().getProperties(PIXConstants.ENVPROPS);
	    return props;
	}
	
	
}
