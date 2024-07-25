// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:46:17 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxHandlerConfig.java

package com.pearson.pix.presentation.ajax.progressbar.config;

import java.io.PrintStream;
import java.lang.reflect.Field;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AjaxHandlerConfig
{

    public AjaxHandlerConfig()
    {
        stdOrCustom = "custom";
    }

    public void setName(String inName)
    {
        if(!frozen)
            name = inName;
    }

    public String getName()
    {
        return name;
    }

    public void setType(String inType)
    {
        if(!frozen)
            if(inType.equalsIgnoreCase("request") || inType.equalsIgnoreCase("response") || inType.equalsIgnoreCase("error"))
                type = inType.toLowerCase();
            else
                log.error("The type attribute of the custom <handler> element in the AjaxParts Taglib must have a value of 'request', 'response' or 'error' (value found was '" + inType + "'");
    }

    public String getType()
    {
        return type;
    }

    public void setFunction(String inFunction)
    {
        if(!frozen)
            function = inFunction;
    }

    public String getFunction()
    {
        return function;
    }

    public void setLocation(String inLocation)
    {
        if(!frozen)
            location = inLocation;
    }

    public String getLocation()
    {
        return location;
    }

    public void setSTD()
    {
        if(!frozen)
            stdOrCustom = "std";
    }

    public void setCustom()
    {
        if(!frozen)
            stdOrCustom = "custom";
    }

    public boolean isSTD()
    {
        return stdOrCustom.equalsIgnoreCase("std");
    }

    public boolean isCustom()
    {
        return stdOrCustom.equalsIgnoreCase("custom");
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
        if(name == null)
        {
            valid = false;
        }
        if(type == null)
        {
            valid = false;
        }
        if(type != null && !type.equalsIgnoreCase("request") && !type.equalsIgnoreCase("response") && !type.equalsIgnoreCase("error"))
        {
            valid = false;
        }
        if(function == null)
        {
            valid = false;
        }
        if(location == null)
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
    private String name;
    private String type;
    private String function;
    private String location;
    private String stdOrCustom;
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
            System.err.println("AjaxHandlerConfig could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.config.AjaxHandlerConfig.class);
    }
}