// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:45:31 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxResponseHandler.java

package com.pearson.pix.presentation.ajax.progressbar.config;

import java.io.PrintStream;
import java.lang.reflect.Field;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AjaxResponseHandler
{

    public AjaxResponseHandler()
    {
    }

    public void setType(String inType)
    {
        if(!frozen)
            type = inType;
    }

    public String getType()
    {
        return type;
    }

    public void setParameter(String inParameter)
    {
        if(!frozen)
            parameter = inParameter;
    }

    public String getParameter()
    {
        return parameter;
    }

    public void setMatchPattern(String inMatchPattern)
    {
        if(!frozen)
            matchPattern = inMatchPattern;
    }

    public String getMatchPattern()
    {
        return matchPattern;
    }

    public void freeze()
    {
        frozen = validate();
    }

    public boolean isFrozen()
    {
        return frozen;
    }

    private boolean validate()
    {
        boolean valid = true;
        if(type == null)
        {
            log.error("type attribute of <responseHandler> element is required");
            valid = false;
        }
        return valid;
    }

    public String toString()
    {
        String str = null;
        StringBuffer sb = new StringBuffer(1000);
        sb.append("[" + super.toString() + "]={");
        boolean firstPropertyDisplayed = false;
        try
        {
            Field fields[] = getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++)
            {
                if(firstPropertyDisplayed)
                    sb.append(", ");
                else
                    firstPropertyDisplayed = true;
                sb.append(fields[i].getName() + "=" + fields[i].get(this));
            }

            sb.append("}");
            str = sb.toString().trim();
        }
        catch(IllegalAccessException iae)
        {
            iae.printStackTrace();
        }
        return str;
    }

   /* static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw (new NoClassDefFoundError()).initCause(x1);
    }*/

    private static Log log;
    private String type;
    private String parameter;
    private String matchPattern;
    private boolean frozen;

    static 
    {
        try
        {
            Class.forName("org.apache.commons.logging.Log");
            Class.forName("org.apache.commons.logging.LogFactory");
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("AjaxResponseHandler could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.config.AjaxResponseHandler.class);
    }
}