<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<c:set var="pono" value="<%=request.getParameter(\"pono\")%>" /> 


<%String okURL = (String) request.getAttribute(PIXConstants.OK_URL);
 %> 
 

<tr>
    <td height="40%" align="left" valign="top"></td>
  </tr>
  <tr>
  <html-el:form action="<%=okURL%>">
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       
        <tr>
          <td height="25" align="center" valign="top" >
            <table height="100%"  border="0" cellpadding="0" cellspacing="0">
              <tr valign="top">
                <td width="31" align="center"><img src="<%=request.getContextPath()%>/images/successIcon.gif" width="18" height="17"></td>
                <td class="messageText"> 
	                <%
           			String message = (String) request.getAttribute(PIXConstants.SUCCESS_STRING);
           			okURL = request.getContextPath() + okURL;
           			if(message != null)
           			{
            			out.println(message);
           			}
           			
           			
           			// String[] msgBufferArr=msgBuffer.split("-");
   			  		%>
	            </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="4"><br></td>
        </tr>
        <tr>
          <td align="center"><label>
            <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
         
            <input name="Button2" type="button" class="buttonMain" onClick="submitsuccessAction('<%=okURL%>',this)" value=" OK " >
        </td>
            </tr>
            </table>
        </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        </html-el:form>
    </table></td>
  </tr>
  
 