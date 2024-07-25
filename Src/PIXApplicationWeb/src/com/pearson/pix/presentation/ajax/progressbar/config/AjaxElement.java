// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 12:44:25 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxElement.java

package com.pearson.pix.presentation.ajax.progressbar.config;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package javawebparts.ajaxparts.taglib.config:
//            AjaxEvent, AjaxErrorHandler

public class AjaxElement
{

    public AjaxElement()
    {
        events = new HashMap();
        errorHandlers = new HashMap();
    }

    public void setPreProc(String inPreProc)
    {
        if(!frozen)
            preProc = inPreProc;
    }

    public String getPreProc()
    {
        return preProc;
    }

    public void setPostProc(String inPostProc)
    {
        if(!frozen)
            postProc = inPostProc;
    }

    public String getPostProc()
    {
        return postProc;
    }

    public void setAjaxRef(String inAjaxRef)
    {
        if(!frozen)
            ajaxRef = inAjaxRef;
    }

    public String getAjaxRef()
    {
        return ajaxRef;
    }

    public void setForm(String inForm)
    {
        if(!frozen)
            form = inForm;
    }

    public String getForm()
    {
        return form;
    }

    public void setMethod(String inMethod)
    {
        if(!frozen)
            method = inMethod.toLowerCase();
    }

    public String getMethod()
    {
        return method;
    }

    public void setAsync(String inAsync)
    {
        if(!frozen)
            async = inAsync.toLowerCase();
    }

    public String getAsync()
    {
        return async;
    }

    public void addEvent(AjaxEvent event)
    {
        if(!frozen)
        {
            event.freeze();
            events.put(event.getType(), event);
        }
    }

    public AjaxEvent getEvent(String type)
    {
        return (AjaxEvent)events.get(type);
    }

    public HashMap getEvents()
    {
        return events;
    }

    public void addErrorHandler(AjaxErrorHandler errorHandler)
    {
        if(!frozen)
        {
            errorHandler.freeze();
            errorHandlers.put(errorHandler.getCode(), errorHandler);
        }
    }

    public AjaxErrorHandler getErrorHandler(String inCode)
    {
        return (AjaxErrorHandler)errorHandlers.get(inCode);
    }

    public HashMap getErrorHandlers()
    {
        return errorHandlers;
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
        if(ajaxRef == null)
        {
            valid = false;
        }
        if(form != null && form.equalsIgnoreCase(""))
        {
            valid = false;
        }
        if(method != null && method.equalsIgnoreCase(""))
        {
            valid = false;
        }
        if(async != null && async.equalsIgnoreCase(""))
        {
            valid = false;
        }
        if(method != null && !method.equalsIgnoreCase("head") && !method.equalsIgnoreCase("get") && !method.equalsIgnoreCase("post") && !method.equalsIgnoreCase("put") && !method.equalsIgnoreCase("delete") && !method.equalsIgnoreCase("trace") && !method.equalsIgnoreCase("options") && !method.equalsIgnoreCase("connect"))
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
    private String ajaxRef;
    private String form;
    private String method;
    private String async;
    private String preProc;
    private String postProc;
    private HashMap events;
    private HashMap errorHandlers;
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
            System.err.println("AjaxElement could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement.class);
    }
}