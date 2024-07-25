
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.pearson.pix.dao.base.BaseDAO"%>
<%@ page import="java.sql.Connection"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>'test.jsp'</title>
    
    
  </head>
  
  <body>
    
    <%
    		BaseDAO baseObj = new BaseDAO();
	    	Connection con = null;
    		try
    		{
	    		//BaseDAO baseObj = new BaseDAO();
	    		con = baseObj.getDataSourceConnection();
	    		    		
	    		
	    		if(con!=null)
	    		{%>
	    		<div align="center" style="color: red;font-size: 30px;font: bold;margin-top: 200	px;">
	    			<%out.println("Database Connection established.");%>
	    		</div>		
	    			
	    		<%}
	    		else
	    		{%>
	    		<div align="center" style="color: red;font-size: 30px;font: bold;margin-top: 200	px;">
	    			<%out.println("Database Connection Failed."); %>
	    		</div>	
	    		<%}
	    	}
	    	catch(Exception e)
	    	{
	    		%>
	    		
	    		<div align="center" style="color: red;font-size: 30px;font: bold;margin-top: 200	px;">
	    		<%out.println("Database Connection Failed.");%>
	    		</div>
	    		
	    	<%}
	    	finally
	    	{
	    		if(con!=null)
    		    {
    		    	con.close();
    		    	con=null;
    		    }
	    	}
	    	%>
    
  </body>
</html>
