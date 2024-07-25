// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:23:12 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxEnableTag.java

package com.pearson.pix.presentation.ajax.progressbar;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxConfig;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxEvent;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxGroup;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxHandlerConfig;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxRequestHandler;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxResponseHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package javawebparts.ajaxparts.taglib:
//            AjaxInit, AjaxUtils

public class AjaxEnableTag extends TagSupport
{

    public AjaxEnableTag()
    {
        debug = "ERROR";
        logger = "JWPAlertLogger";
    }

    public void setLogger(String inLogger)
    {
        if(logger.equalsIgnoreCase("JWPAlertLogger") || logger.equalsIgnoreCase("JWPWindowLogger"))
            logger = inLogger;
        else
            log.error("logger attribute of enable tag must be one of the values 'JWPAlertLogger' or 'JWPWindowLogger' (case DOES matter!)");
    }

    public String getLogger()
    {
        return logger;
    }

    public void setDebug(String inDebug)
    {
        if(!inDebug.equalsIgnoreCase("trace") && !inDebug.equalsIgnoreCase("debug") && !inDebug.equalsIgnoreCase("info") && !inDebug.equalsIgnoreCase("error") && !inDebug.equalsIgnoreCase("fatal"))
        {
            log.error("debug attribute or enable tag must be one of the following values: TRACE, DEBUG. INFO, ERROR, FATAL (case is NOT important)");
            return;
        } else
        {
            debug = inDebug;
            return;
        }
    }

    public String getDebug()
    {
        return debug;
    }

    public void setSuppress(boolean inSuppressFlag)
    {
        suppress = inSuppressFlag;
    }

    public int doStartTag()
        throws JspException
    {
       
        if(!AjaxConfig.isFrozen())
        {
            
            (new AjaxInit()).init(pageContext.getServletContext());
        }
        String jwpLogger = "";
        if(!suppress)
            jwpLogger = AjaxUtils.getResource("js/" + logger + ".js");
        String ajaxPartsTaglib = "";
        if(!suppress)
            ajaxPartsTaglib = AjaxUtils.getResource("js/AjaxPartsTaglib.js");
        ajaxPartsTaglib = ajaxPartsTaglib.replaceAll("__CONTEXT__PATH__", ((HttpServletRequest)pageContext.getRequest()).getContextPath());
        ajaxPartsTaglib = ajaxPartsTaglib.replaceAll("__DEBUG_LEVEL__", debug.toUpperCase());
        ajaxPartsTaglib = ajaxPartsTaglib.replaceAll("__LOGGER__", logger);
        HashSet handlersUsed = (HashSet)pageContext.getAttribute("handlersUsed", 2);
      
        if(handlersUsed == null)
        {
           
            return 0;
        }
        HashMap manFuncNames = (HashMap)pageContext.getAttribute("manFuncNames", 2);
        HashMap timerParams = (HashMap)pageContext.getAttribute("timerParams", 2);
        StringBuffer handlers = new StringBuffer(2048);
        StringBuffer importedHandlers = new StringBuffer(2048);
        StringBuffer regHandlerCalls = new StringBuffer(1024);
      
        if(handlersUsed.contains("std:QueryString") || handlersUsed.contains("std:SendByID") || handlersUsed.contains("std:SimpleXML") || handlersUsed.contains("std:Poster"))
            handlers.append(AjaxUtils.getResource("js/JWPGetElementValue.js") + "\n\n");
        Iterator it = handlersUsed.iterator();
        do
        {
            if(!it.hasNext())
                break;
            String handlerType = (String)it.next();
          
            if(handlerType.substring(0, 4).equalsIgnoreCase("std:"))
            {
               
              
                String res = AjaxUtils.getResource("js/Std" + handlerType.substring(4) + ".js");
                if(res == null)
                {
                    log.error("Unable to render STD handler  ... is " + "there a typo in the 'type' attribute of one of your config " + "entries?");
                } else
                {
                    AjaxHandlerConfig handler = AjaxConfig.getHandler(handlerType);
                    if(!"error".equals(handler.getType()))
                        regHandlerCalls.append(createRegisterCall(handler));
                    handlers.append(res + "\n\n");
                }
            } else
            {
               
                AjaxHandlerConfig handler = AjaxConfig.getHandler(handlerType);
                if(handler == null)
                {
                    log.error("Unable to find configuration for handler .. is it configured in the config file?  Does " + "the 'type' attribute match what is shown here?");
                } else
                {
                   
                    if(!handler.getLocation().equalsIgnoreCase("local"))
                    {
                    	 
                        importedHandlers.append("<script src='" + handler.getLocation() + "'></script>\n\n");
                        if(!"error".equals(handler.getType()))
                            importedHandlers.append("<script>\n" + createRegisterCall(handler) + "</script>");
                    } else
                    	
                    if(!"error".equals(handler.getType()))
                        regHandlerCalls.append(createRegisterCall(handler));
                }
            }
        } while(true);
        ArrayList ajaxRefs = (ArrayList)pageContext.getAttribute("ajaxRefs", 2);
        
        HashMap groupErrorHandlersRendered = new HashMap();
        HashMap elementErrorHandlersRendered = new HashMap();
        HashMap eventErrorHandlersRendered = new HashMap();
        StringBuffer attachCalls = new StringBuffer(1024);
        StringBuffer manualFuncs = new StringBuffer(1024);
        StringBuffer timerFuncs = new StringBuffer(1024);
        for(Iterator ite = ajaxRefs.iterator(); ite.hasNext();)
        {
            String ajaxRef = (String)ite.next();
            StringTokenizer st = new StringTokenizer(ajaxRef, "/");
            String groupRef = st.nextToken();
            String elementRef = st.nextToken();
            AjaxGroup aGroup = AjaxConfig.getGroup(groupRef);
            if(aGroup == null)
            {
                return 0;
            }
            AjaxElement aElement = aGroup.getElement(elementRef);
            if(aElement == null)
            {
                return 0;
            }
            Iterator it1 = aGroup.getErrorHandlers().keySet().iterator();
            do
            {
                if(!it1.hasNext())
                    break;
                String errCode = (String)it1.next();
                if(groupErrorHandlersRendered.get(errCode) != null)
                    break;
                groupErrorHandlersRendered.put(errCode, new Object());
                AjaxErrorHandler errorHandler = (AjaxErrorHandler)aGroup.getErrorHandlers().get(errCode);
                AjaxHandlerConfig handler = AjaxConfig.getHandler(errorHandler.getType());
                regHandlerCalls.append(createErrorRegisterCall(errorHandler.getCode(), groupRef, "", "", handler.getFunction()));
            } while(true);
            it1 = aElement.getErrorHandlers().keySet().iterator();
            do
            {
                if(!it1.hasNext())
                    break;
                String errCode = (String)it1.next();
                if(elementErrorHandlersRendered.get(errCode) != null)
                    break;
                elementErrorHandlersRendered.put(errCode, new Object());
                AjaxErrorHandler errorHandler = (AjaxErrorHandler)aElement.getErrorHandlers().get(errCode);
                AjaxHandlerConfig handler = AjaxConfig.getHandler(errorHandler.getType());
                regHandlerCalls.append(createErrorRegisterCall(errorHandler.getCode(), groupRef, elementRef, "", handler.getFunction()));
            } while(true);
            it1 = aElement.getEvents().keySet().iterator();
            while(it1.hasNext()) 
            {
                String eventType = (String)it1.next();
                AjaxEvent aEvent = aElement.getEvent(eventType);
                AjaxRequestHandler requestHandler = aEvent.getRequestHandler();
                Iterator it2 = aEvent.getErrorHandlers().keySet().iterator();
                do
                {
                    if(!it2.hasNext())
                        break;
                    String errCode = (String)it2.next();
                    if(eventErrorHandlersRendered.get(errCode) != null)
                        break;
                    eventErrorHandlersRendered.put(errCode, new Object());
                    AjaxErrorHandler errorHandler = (AjaxErrorHandler)aEvent.getErrorHandlers().get(errCode);
                    AjaxHandlerConfig handler = AjaxConfig.getHandler(errorHandler.getType());
                    regHandlerCalls.append(createErrorRegisterCall(errorHandler.getCode(), groupRef, elementRef, aEvent.getType(), handler.getFunction()));
                } while(true);
                attachCalls.append("ajaxPartsTaglib.attach(");
                attachCalls.append("\"" + ajaxRef + "\", ");
                attachCalls.append("\"" + ((HttpServletResponse)pageContext.getResponse()).encodeURL(requestHandler.getTarget()) + "\", ");
                attachCalls.append("\"" + requestHandler.getType().replaceAll("std:", "Std") + "\", ");
                attachCalls.append("\"" + requestHandler.getParameter() + "\", [ ");
                boolean oneDone = false;
                for(Iterator it2e = aEvent.getResponseHandlers().iterator(); it2e.hasNext();)
                {
                    if(oneDone)
                        attachCalls.append(", ");
                    oneDone = true;
                    AjaxResponseHandler responseHandler = (AjaxResponseHandler)it2e.next();
                    attachCalls.append("\"" + responseHandler.getType().replaceAll("std:", "Std") + "\", ");
                    attachCalls.append("\"" + responseHandler.getParameter() + "\", ");
                    if(responseHandler.getMatchPattern() == null)
                        attachCalls.append("null");
                    else
                        attachCalls.append("\"" + responseHandler.getMatchPattern() + "\"");
                }

                String form = AjaxUtils.getScopedForm(aGroup, aElement, aEvent);
                String method = AjaxUtils.getScopedMethod(aGroup, aElement, aEvent);
                String async = AjaxUtils.getScopedAsync(aGroup, aElement, aEvent);
                String preProc = AjaxUtils.getScopedPreProc(aGroup, aElement, aEvent);
                String postProc = AjaxUtils.getScopedPostProc(aGroup, aElement, aEvent);
                attachCalls.append(" ], \"" + method + "\", ");
                attachCalls.append("\"" + form + "\", ");
                attachCalls.append("\"" + aEvent.getType() + "\", ");
                attachCalls.append("\"" + preProc + "\", \"" + postProc + "\", ");
                attachCalls.append(async + ");\n\n");
                if(aEvent.getType().equalsIgnoreCase("manual"))
                {
                    manualFuncs.append("  function " + manFuncNames.get(ajaxRef) + "() {\n");
                    manualFuncs.append("    ajaxPartsTaglib.execute(");
                    manualFuncs.append("\"" + ajaxRef + aEvent.getType() + "\");\n");
                    manualFuncs.append("  }\n\n");
                }
                if(aEvent.getType().equalsIgnoreCase("timer"))
                {
                    HashMap params = (HashMap)timerParams.get(ajaxRef);
                    timerFuncs.append("  function start" + ajaxRef.replace('/', '_') + "() {\n");
                    timerFuncs.append("    var evtDef = ajaxPartsTaglib.events[\"" + ajaxRef + aEvent.getType() + "\"];\n");
                    timerFuncs.append("    evtDef.timerObj = setTimeout(\"ajaxPartsTaglib.execute('" + ajaxRef + aEvent.getType() + "');\", " + params.get("frequency") + ");\n");
                    timerFuncs.append("  }\n\n");
                    timerFuncs.append("  function stop" + ajaxRef.replace('/', '_') + "() {\n");
                    timerFuncs.append("    var evtDef = ajaxPartsTaglib.events[\"" + ajaxRef + aEvent.getType() + "\"];\n");
                    timerFuncs.append("    clearTimeout(evtDef.timerObj);\n");
                    timerFuncs.append("    evtDef.timerObj = null;\n");
                    timerFuncs.append("  }\n\n");
                    if("true".equals(params.get("startOnLoad")))
                        timerFuncs.append("  start" + ajaxRef.replace('/', '_') + "(); \n");
                }
            }
        }

        try
        {
            JspWriter out = pageContext.getOut();
            out.print("<script>\n\n" + jwpLogger + "\n\n");
            out.print(ajaxPartsTaglib + "\n\n" + handlers.toString());
            out.print(attachCalls.toString() + regHandlerCalls.toString());
            out.print(manualFuncs.toString() + timerFuncs.toString());
            out.print("\n\n</script>\n\n" + importedHandlers.toString());
        }
        catch(IOException ioe)
        {
            throw new JspException(ioe.toString());
        }
        return 0;
    }

    private String createRegisterCall(AjaxHandlerConfig handler)
    {
        String type = handler.getType().substring(0, 1).toUpperCase() + handler.getType().substring(1, 3);
        StringBuffer result = new StringBuffer();
        result.append("ajaxPartsTaglib.reg");
        result.append(type);
        result.append("Handler(\"");
        result.append(handler.getName().replaceAll("std:", "Std") + "\", ");
        result.append(handler.getFunction() + ");\n\n");
        return result.toString();
    }

    private String createErrorRegisterCall(String code, String groupRef, String elementRef, String type, String function)
    {
        StringBuffer result = new StringBuffer();
        result.append("ajaxPartsTaglib.regErrHandler(");
        result.append("\"" + code + "\", ");
        result.append("\"" + groupRef + "\", ");
        result.append("\"" + elementRef + "\", ");
        result.append("\"" + type + "\", ");
        result.append(function + ");\n\n");
        return result.toString();
    }

   /* static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw (new NoClassDefFoundError()).initCause(x1);
    }*/

    private static Log log;
    private boolean suppress;
    private String debug;
    private String logger;

    static 
    {
        try
        {
            Class.forName("org.apache.commons.logging.Log");
            Class.forName("org.apache.commons.logging.LogFactory");
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("AjaxEnableTag could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.AjaxEnableTag.class);
    }
}