// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:23:19 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxEventTag.java

package com.pearson.pix.presentation.ajax.progressbar;

import java.io.IOException;
import java.io.PrintStream;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxConfig;
import com.pearson.pix.presentation.ajax.progressbar.config.AjaxGroup;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package javawebparts.ajaxparts.taglib:
//            AjaxInit, AjaxUtils

public class AjaxEventTag extends TagSupport
{

    public AjaxEventTag()
    {
    }

    public void setAjaxRef(String inAjaxRef)
    {
        ajaxRef = inAjaxRef;
    }

    public String getAjaxRef()
    {
        return ajaxRef;
    }

    public int doStartTag()
        throws JspException
    {
        if(!AjaxConfig.isFrozen())
            (new AjaxInit()).init(pageContext.getServletContext());
        if(!AjaxUtils.validateAjaxRef(ajaxRef))
        {
            return 0;
        }
        String groupRef = AjaxUtils.groupRefFromAjaxRef(ajaxRef);
        String elementRef = AjaxUtils.elementRefFromAjaxRef(ajaxRef);
        AjaxGroup aGroup = AjaxConfig.getGroup(groupRef);
        if(aGroup == null)
        {
            return 0;
        }
        com.pearson.pix.presentation.ajax.progressbar.config.AjaxElement aElement = aGroup.getElement(elementRef);
        if(aElement == null)
        {
            return 0;
        }
        AjaxUtils.updatePageScopeVars(ajaxRef, pageContext, aGroup, aElement);
        String sCode = "<jwpaptl id=\"" + ajaxRef + "\"/>";
        JspWriter out = pageContext.getOut();
        try
        {
            out.print(sCode);
        }
        catch(IOException ioe)
        {
            throw new JspException(ioe.toString());
        }
        return 0;
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

    static 
    {
        try
        {
            Class.forName("org.apache.commons.logging.Log");
            Class.forName("org.apache.commons.logging.LogFactory");
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("AjaxEventTag could not be loaded by classloader because classes it depends on could not be found in the classpath...");
            e.printStackTrace();
        }
        log = LogFactory.getLog(com.pearson.pix.presentation.ajax.progressbar.AjaxEventTag.class);
    }
}