// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 8/14/2007 11:46:48 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxConfig.java

package com.pearson.pix.presentation.ajax.progressbar.config;

import java.util.HashMap;

// Referenced classes of package javawebparts.ajaxparts.taglib.config:
//            AjaxHandlerConfig, AjaxGroup

public class AjaxConfig
{

    public AjaxConfig()
    {
    }

    public int getDummy()
    {
        return dummy;
    }

    public void setDummy(int inDummy)
    {
        dummy = inDummy;
    }

    public static void addHandler(AjaxHandlerConfig handler)
    {
        if(!frozen)
        {
            handler.freeze();
            handlers.put(handler.getName(), handler);
        }
    }

    public static AjaxHandlerConfig getHandler(String name)
    {
        return (AjaxHandlerConfig)handlers.get(name);
    }

    public static void addGroup(AjaxGroup group)
    {
        if(!frozen)
        {
            group.freeze();
            groups.put(group.getAjaxRef(), group);
        }
    }

    public static AjaxGroup getGroup(String ajaxRef)
    {
        return (AjaxGroup)groups.get(ajaxRef);
    }

    public static HashMap getHandlers()
    {
        return handlers;
    }

    public static HashMap getGroups()
    {
        return groups;
    }

    public static void freeze()
    {
        frozen = validate();
    }

    public static boolean isFrozen()
    {
        return frozen;
    }

    private static boolean validate()
    {
        return true;
    }

    private static HashMap handlers = new HashMap();
    private static HashMap groups = new HashMap();
    private static boolean frozen;
    private int dummy;

}