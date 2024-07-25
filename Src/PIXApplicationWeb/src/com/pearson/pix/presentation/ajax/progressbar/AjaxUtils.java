// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:23:01 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxUtils.java

package com.pearson.pix.presentation.ajax.progressbar;

import java.io.*;
import java.util.*;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxEvent;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxGroup;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxRequestHandler;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxResponseHandler;
import javawebparts.core.org.apache.commons.lang.StringUtils;
import javax.servlet.jsp.PageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class AjaxUtils
{

    private AjaxUtils()
    {
    }

    public static String getResource(String inResName)
    {
        return stringFromInputStream(getStream(inResName));
    }

    private static InputStream getStream(String inResName)
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream(inResName);
        if(is == null)
            log.error("Resource '" + inResName + "' not found in classpath");
        return is;
    }

    private static String stringFromInputStream(InputStream inIS)
    {
        if(inIS == null)
            return null;
        StringBuffer outBuffer = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader input = null;
        try
        {
            String line = null;
            isr = new InputStreamReader(inIS);
            input = new BufferedReader(isr);
            do
            {
                if((line = input.readLine()) == null)
                    break;
                if(line.indexOf("//") == -1)
                {
                    outBuffer.append(line);
                    outBuffer.append(System.getProperty("line.separator"));
                }
            } while(true);
        }
        catch(IOException ioe)
        {
            log.error("Unable to read from InputStream or write to output buffer");
            ioe.printStackTrace();
            outBuffer = null;
        }
        try
        {
            isr.close();
            input.close();
            inIS.close();
        }
        catch(IOException ioe)
        {
            log.error("InputStream could not be closed");
            ioe.printStackTrace();
        }
        if(outBuffer == null)
            return null;
        else
            return outBuffer.toString();
    }

    public static boolean validateAjaxRef(String ajaxRef)
    {
        boolean retVal = true;
        if(ajaxRef == null || StringUtils.countMatches(ajaxRef, "/") != 1)
            retVal = false;
        return retVal;
    }

    public static String groupRefFromAjaxRef(String ajaxRef)
    {
        StringTokenizer st = new StringTokenizer(ajaxRef, "/");
        return st.nextToken();
    }

    public static String elementRefFromAjaxRef(String ajaxRef)
    {
        StringTokenizer st = new StringTokenizer(ajaxRef, "/");
        st.nextToken();
        return st.nextToken();
    }

    public static void updatePageScopeVars(String ajaxRef, PageContext pageContext, AjaxGroup aGroup, AjaxElement aElement)
    {
        ArrayList ajaxRefs = (ArrayList)pageContext.getAttribute("ajaxRefs", 2);
        if(ajaxRefs == null)
            ajaxRefs = new ArrayList();
        ajaxRefs.add(ajaxRef);
        pageContext.setAttribute("ajaxRefs", ajaxRefs, 2);
        HashSet handlersUsed = (HashSet)pageContext.getAttribute("handlersUsed", 2);
        if(handlersUsed == null)
            handlersUsed = new HashSet();
        AjaxErrorHandler errorHandler;
        for(Iterator it = aGroup.getErrorHandlers().keySet().iterator(); it.hasNext(); handlersUsed.add(errorHandler.getType()))
        {
            String code = (String)it.next();
            errorHandler = aGroup.getErrorHandler(code);
        }

        AjaxErrorHandler errorHandlera;
        for(Iterator it = aElement.getErrorHandlers().keySet().iterator(); it.hasNext(); handlersUsed.add(errorHandlera.getType()))
        {
            String code = (String)it.next();
            errorHandlera = aElement.getErrorHandler(code);
        }

        AjaxEvent aEvent;
        for(Iterator it = aElement.getEvents().keySet().iterator(); it.hasNext(); handlersUsed.add(aEvent.getRequestHandler().getType()))
        {
            String type = (String)it.next();
            aEvent = aElement.getEvent(type);
        }

        for(Iterator it = aElement.getEvents().keySet().iterator(); it.hasNext();)
        {
            String type = (String)it.next();
            AjaxEvent aEvente = aElement.getEvent(type);
            ArrayList responseHandlers = aEvente.getResponseHandlers();
            AjaxResponseHandler responseHandler;
            for(Iterator it1 = responseHandlers.iterator(); it1.hasNext(); handlersUsed.add(responseHandler.getType()))
                responseHandler = (AjaxResponseHandler)it1.next();

            Iterator it2 = aEvente.getErrorHandlers().keySet().iterator();
            while(it2.hasNext()) 
            {
                String code = (String)it2.next();
                AjaxErrorHandler errorHandlere = aEvente.getErrorHandler(code);
                handlersUsed.add(errorHandlere.getType());
            }
        }

        pageContext.setAttribute("handlersUsed", handlersUsed, 2);
    }

    public static String getScopedForm(AjaxGroup aGroup, AjaxElement aElement, AjaxEvent aEvent)
    {
        String form = aGroup.getForm();
        if(aElement.getForm() != null)
            form = aElement.getForm();
        if(aEvent.getForm() != null)
            form = aEvent.getForm();
        return form;
    }

    public static String getScopedMethod(AjaxGroup aGroup, AjaxElement aElement, AjaxEvent aEvent)
    {
        String method = aGroup.getMethod();
        if(aElement.getMethod() != null)
            method = aElement.getMethod();
        if(aEvent.getMethod() != null)
            method = aEvent.getMethod();
        return method;
    }

    public static String getScopedAsync(AjaxGroup aGroup, AjaxElement aElement, AjaxEvent aEvent)
    {
        String async = aGroup.getAsync();
        if(aElement.getAsync() != null)
            async = aElement.getAsync();
        if(aEvent.getAsync() != null)
            async = aEvent.getAsync();
        return async;
    }

    public static String getScopedPreProc(AjaxGroup aGroup, AjaxElement aElement, AjaxEvent aEvent)
    {
        String preProc = aGroup.getPreProc();
        if(aElement.getPreProc() != null)
            preProc = aElement.getPreProc();
        if(aEvent.getPreProc() != null)
            preProc = aEvent.getPreProc();
        return preProc;
    }

    public static String getScopedPostProc(AjaxGroup aGroup, AjaxElement aElement, AjaxEvent aEvent)
    {
        String postProc = aGroup.getPostProc();
        if(aElement.getPostProc() != null)
            postProc = aElement.getPostProc();
        if(aEvent.getPostProc() != null)
            postProc = aEvent.getPostProc();
        return postProc;
    }

    /*static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw (new NoClassDefFoundError()).initCause(x1);
    }*/

    private static Log log;

    static 
    {
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.AjaxUtils.class);
    }
}