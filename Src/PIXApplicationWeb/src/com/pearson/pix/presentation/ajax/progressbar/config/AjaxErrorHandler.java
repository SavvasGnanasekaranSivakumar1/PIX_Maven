// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 1:15:45 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxErrorHandler.java

package com.pearson.pix.presentation.ajax.progressbar.config;

import java.io.PrintStream;
import java.lang.reflect.Field;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AjaxErrorHandler
{

    public AjaxErrorHandler()
    {
    }

    public void setCode(String inCode)
    {
        if(!frozen)
            code = inCode;
    }

    public String getCode()
    {
        return code;
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
        if(code == null)
        {
            valid = false;
        }
        if(type == null)
        {
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

    /*static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw (new NoClassDefFoundError()).initCause(x1);
    }*/

    private static Log log;
    private String code;
    private String type;
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
            System.err.println("AjaxErrorHandler could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler.class);
    }
}