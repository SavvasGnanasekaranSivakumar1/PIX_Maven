// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:47:05 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxInit.java

package com.pearson.pix.presentation.ajax.progressbar;

import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxConfig;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxHandlerConfig;
import javawebparts.core.org.apache.commons.digester.Digester;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

public class AjaxInit
{

    public AjaxInit()
    {
    }

    public void init(ServletContext sc)
    {
        try
        {
            parseConfig(sc);
            addSTDHandlers();
            AjaxConfig.freeze();
        }
        catch(SAXException se)
        {
            log.error("SAXException in AjaxInit.contextInitialized(): " + se);
        }
        catch(IOException ioe)
        {
            log.error("IOException in AjaxInit.contextInitialized(): " + ioe);
        }
    }

    private void parseConfig(ServletContext sc)
        throws SAXException, IOException
    {
        Digester digester = new Digester();
        digester.setLogger(log);
        String validateConfig = sc.getInitParameter("AjaxPartsTaglibValidateConfig");
        if(validateConfig == null || validateConfig.equalsIgnoreCase("true"))
            digester.setValidating(true);
        if(validateConfig != null && validateConfig.equalsIgnoreCase("false"))
            digester.setValidating(false);
        URL dtdurl = getClass().getResource("/WEB-INF/ajax-config.dtd");
        digester.register("ajax-config", dtdurl.toString());
        digester.addObjectCreate("ajaxConfig/handler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxHandlerConfig");
        digester.addSetProperties("ajaxConfig/handler");
        digester.addBeanPropertySetter("ajaxConfig/handler/function", "function");
        digester.addBeanPropertySetter("ajaxConfig/handler/location", "location");
        digester.addSetNext("ajaxConfig/handler", "addHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxHandlerConfig");
        digester.addObjectCreate("ajaxConfig/group", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxGroup");
        digester.addSetProperties("ajaxConfig/group");
        digester.addSetNext("ajaxConfig/group", "addGroup", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxGroup");
        digester.addObjectCreate("ajaxConfig/group/errorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addSetProperties("ajaxConfig/group/errorHandler");
        digester.addSetNext("ajaxConfig/group/errorHandler", "addErrorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addObjectCreate("ajaxConfig/group/element", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement");
        digester.addSetProperties("ajaxConfig/group/element");
        digester.addSetNext("ajaxConfig/group/element", "addElement", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement");
        digester.addObjectCreate("ajaxConfig/group/element/errorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addSetProperties("ajaxConfig/group/element/errorHandler");
        digester.addSetNext("ajaxConfig/group/element/errorHandler", "addErrorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addObjectCreate("ajaxConfig/group/element/event", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxEvent");
        digester.addSetProperties("ajaxConfig/group/element/event");
        digester.addSetNext("ajaxConfig/group/element/event", "addEvent", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxEvent");
        digester.addObjectCreate("ajaxConfig/group/element/event/errorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addSetProperties("ajaxConfig/group/element/event/errorHandler");
        digester.addSetNext("ajaxConfig/group/element/event/errorHandler", "addErrorHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxErrorHandler");
        digester.addObjectCreate("ajaxConfig/group/element/event/requestHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxRequestHandler");
        digester.addSetProperties("ajaxConfig/group/element/event/requestHandler");
        digester.addBeanPropertySetter("ajaxConfig/group/element/event/requestHandler/target", "target");
        digester.addBeanPropertySetter("ajaxConfig/group/element/event/requestHandler/parameter", "parameter");
        digester.addSetNext("ajaxConfig/group/element/event/requestHandler", "setRequestHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxRequestHandler");
        digester.addObjectCreate("ajaxConfig/group/element/event/responseHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxResponseHandler");
        digester.addSetProperties("ajaxConfig/group/element/event/responseHandler");
        digester.addBeanPropertySetter("ajaxConfig/group/element/event/responseHandler/parameter", "parameter");
        digester.addSetNext("ajaxConfig/group/element/event/responseHandler", "addResponseHandler", "com.pearson.pix.presentation.ajax.progressbar.config.AjaxResponseHandler");
        String configFiles = sc.getInitParameter("AjaxPartsTaglibConfig");
        StringTokenizer st = new StringTokenizer(configFiles, ",");
        AjaxConfig ajaxConfig = new AjaxConfig();
        do
        {
            if(!st.hasMoreTokens())
                break;
            String configFile = st.nextToken();
            InputStream stream = null;
            digester.push(ajaxConfig);
            try
            {
                stream = sc.getResourceAsStream(configFile);
                digester.parse(stream);
            }
            finally
            {
                if(stream != null)
                    try
                    {
                        stream.close();
                    }
                    catch(IOException ioe)
                    {
                        log.error("Exception closing config file stream: " + ioe);
                    }
            }
        } while(true);
    }

    private void addSTDHandlers()
    {
        AjaxHandlerConfig stdSimpleRequestHandler = new AjaxHandlerConfig();
        stdSimpleRequestHandler.setName("std:SimpleRequest");
        stdSimpleRequestHandler.setType("request");
        stdSimpleRequestHandler.setFunction("StdSimpleRequest");
        stdSimpleRequestHandler.setLocation("local");
        stdSimpleRequestHandler.setSTD();
        stdSimpleRequestHandler.freeze();
        AjaxConfig.addHandler(stdSimpleRequestHandler);
        AjaxHandlerConfig stdQueryStringHandler = new AjaxHandlerConfig();
        stdQueryStringHandler.setName("std:QueryString");
        stdQueryStringHandler.setType("request");
        stdQueryStringHandler.setFunction("StdQueryString");
        stdQueryStringHandler.setLocation("local");
        stdQueryStringHandler.setSTD();
        stdQueryStringHandler.freeze();
        AjaxConfig.addHandler(stdQueryStringHandler);
        AjaxHandlerConfig stdPoster = new AjaxHandlerConfig();
        stdPoster.setName("std:Poster");
        stdPoster.setType("request");
        stdPoster.setFunction("StdPoster");
        stdPoster.setLocation("local");
        stdPoster.setSTD();
        stdPoster.freeze();
        AjaxConfig.addHandler(stdPoster);
        AjaxHandlerConfig stdSimpleXML = new AjaxHandlerConfig();
        stdSimpleXML.setName("std:SimpleXML");
        stdSimpleXML.setType("request");
        stdSimpleXML.setFunction("StdSimpleXML");
        stdSimpleXML.setLocation("local");
        stdSimpleXML.setSTD();
        stdSimpleXML.freeze();
        AjaxConfig.addHandler(stdSimpleXML);
        AjaxHandlerConfig stdSendByID = new AjaxHandlerConfig();
        stdSendByID.setName("std:SendByID");
        stdSendByID.setType("request");
        stdSendByID.setFunction("StdSendByID");
        stdSendByID.setLocation("local");
        stdSendByID.setSTD();
        stdSendByID.freeze();
        AjaxConfig.addHandler(stdSendByID);
        AjaxHandlerConfig stdInnerHTMLHandler = new AjaxHandlerConfig();
        stdInnerHTMLHandler.setName("std:InnerHTML");
        stdInnerHTMLHandler.setType("response");
        stdInnerHTMLHandler.setFunction("StdInnerHTML");
        stdInnerHTMLHandler.setLocation("local");
        stdInnerHTMLHandler.setSTD();
        stdInnerHTMLHandler.freeze();
        AjaxConfig.addHandler(stdInnerHTMLHandler);
        AjaxHandlerConfig stdCodeExecuter = new AjaxHandlerConfig();
        stdCodeExecuter.setName("std:CodeExecuter");
        stdCodeExecuter.setType("response");
        stdCodeExecuter.setFunction("StdCodeExecuter");
        stdCodeExecuter.setLocation("local");
        stdCodeExecuter.setSTD();
        stdCodeExecuter.freeze();
        AjaxConfig.addHandler(stdCodeExecuter);
        AjaxHandlerConfig stdTextboxArea = new AjaxHandlerConfig();
        stdTextboxArea.setName("std:TextboxArea");
        stdTextboxArea.setType("response");
        stdTextboxArea.setFunction("StdTextboxArea");
        stdTextboxArea.setLocation("local");
        stdTextboxArea.setSTD();
        stdTextboxArea.freeze();
        AjaxConfig.addHandler(stdTextboxArea);
        AjaxHandlerConfig stdXSLTHandler = new AjaxHandlerConfig();
        stdXSLTHandler.setName("std:XSLT");
        stdXSLTHandler.setType("response");
        stdXSLTHandler.setFunction("StdXSLT");
        stdXSLTHandler.setLocation("local");
        stdXSLTHandler.setSTD();
        stdXSLTHandler.freeze();
        AjaxConfig.addHandler(stdXSLTHandler);
        AjaxHandlerConfig stdDoNothing = new AjaxHandlerConfig();
        stdDoNothing.setName("std:DoNothing");
        stdDoNothing.setType("response");
        stdDoNothing.setFunction("StdDoNothing");
        stdDoNothing.setLocation("local");
        stdDoNothing.setSTD();
        stdDoNothing.freeze();
        AjaxConfig.addHandler(stdDoNothing);
        AjaxHandlerConfig stdSelectboxHandler = new AjaxHandlerConfig();
        stdSelectboxHandler.setName("std:Selectbox");
        stdSelectboxHandler.setType("response");
        stdSelectboxHandler.setFunction("StdSelectbox");
        stdSelectboxHandler.setLocation("local");
        stdSelectboxHandler.setSTD();
        stdSelectboxHandler.freeze();
        AjaxConfig.addHandler(stdSelectboxHandler);
        AjaxHandlerConfig stdAlerter = new AjaxHandlerConfig();
        stdAlerter.setName("std:Alerter");
        stdAlerter.setType("response");
        stdAlerter.setFunction("StdAlerter");
        stdAlerter.setLocation("local");
        stdAlerter.setSTD();
        stdAlerter.freeze();
        AjaxConfig.addHandler(stdAlerter);
        AjaxHandlerConfig stdIFrameDisplay = new AjaxHandlerConfig();
        stdIFrameDisplay.setName("std:IFrameDisplay");
        stdIFrameDisplay.setType("response");
        stdIFrameDisplay.setFunction("StdIFrameDisplay");
        stdIFrameDisplay.setLocation("local");
        stdIFrameDisplay.setSTD();
        stdIFrameDisplay.freeze();
        AjaxConfig.addHandler(stdIFrameDisplay);
        AjaxHandlerConfig stdFormManipulator = new AjaxHandlerConfig();
        stdFormManipulator.setName("std:FormManipulator");
        stdFormManipulator.setType("response");
        stdFormManipulator.setFunction("StdFormManipulator");
        stdFormManipulator.setLocation("local");
        stdFormManipulator.setSTD();
        stdFormManipulator.freeze();
        AjaxConfig.addHandler(stdFormManipulator);
        AjaxHandlerConfig stdWindowOpener = new AjaxHandlerConfig();
        stdWindowOpener.setName("std:WindowOpener");
        stdWindowOpener.setType("response");
        stdWindowOpener.setFunction("StdWindowOpener");
        stdWindowOpener.setLocation("local");
        stdWindowOpener.setSTD();
        stdWindowOpener.freeze();
        AjaxConfig.addHandler(stdWindowOpener);
        AjaxHandlerConfig stdRedirecter = new AjaxHandlerConfig();
        stdRedirecter.setName("std:Redirecter");
        stdRedirecter.setType("response");
        stdRedirecter.setFunction("StdRedirecter");
        stdRedirecter.setLocation("local");
        stdRedirecter.setSTD();
        stdRedirecter.freeze();
        AjaxConfig.addHandler(stdRedirecter);
        AjaxHandlerConfig stdAlertErrorHandler = new AjaxHandlerConfig();
        stdAlertErrorHandler.setName("std:AlertErrorHandler");
        stdAlertErrorHandler.setType("error");
        stdAlertErrorHandler.setFunction("StdAlertErrorHandler");
        stdAlertErrorHandler.setLocation("local");
        stdAlertErrorHandler.setSTD();
        stdAlertErrorHandler.freeze();
        AjaxConfig.addHandler(stdAlertErrorHandler);
    }

   /* static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw (new NoClassDefFoundError()).initCause(x1);
    }*/

    private static Log log;

    static 
    {
        try
        {
            Class.forName("javawebparts.core.org.apache.commons.digester.Digester");
            Class.forName("org.apache.commons.logging.Log");
            Class.forName("org.apache.commons.logging.LogFactory");
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("AjaxInit could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.AjaxInit.class);
    }
}