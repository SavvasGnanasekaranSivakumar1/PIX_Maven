package com.pearson.pix.presentation.base.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheControlFilter implements Filter {
	
	private static Log log = LogFactory.getLog(CacheControlFilter.class.getName());

	private static final SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
	public void init(FilterConfig arg0) throws ServletException {
		log.info("init called");
	}
	
	public void destroy() {
		log.info("destroy called");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		if(log.isDebugEnabled()) {
			log.debug("doFilter called");
		}
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
	
		
		String reqURL = httpRequest.getRequestURI().toString();
		if(!reqURL.contains("/download.do") && !reqURL.contains("/generateexcel.do")){
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		log.info("request cacahe:"+httpRequest.getRequestURL().toString());
		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		}
		/*Date expiresDate = new Date((new Date()).getTime() + 9999999);
		httpResponse.setHeader("Expires", "0");*/
        filterChain.doFilter(request, response);
	}



}
