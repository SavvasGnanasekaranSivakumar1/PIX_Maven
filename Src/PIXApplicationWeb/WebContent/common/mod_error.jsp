<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%> 
<%@ page import="com.pearson.pix.exception.AppException"%>
<%@ page import="com.pearson.pix.exception.Exceptions"%>
<%@ page isErrorPage="true" %>
<%
String okURL = "/error";
if(request.getAttribute(PIXConstants.OK_URL)!=null){
	okURL = (String) request.getAttribute(PIXConstants.OK_URL); 
%>
 <tr>
    <td height="40%" align="left" valign="top"></td>
  </tr>
 
  <tr>
   <html-el:form action="<%=okURL%>"> 
    <td align="center" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
    
        <tr>
          <td height="31" align="center" valign="top" ><table height="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
              <td width="31" valign="middle" align="center"><img src="<%=request.getContextPath()%>/images/errorIcon.gif" width="17" height="17"></td>
              <td class="errorMessageText">
              <%
           			String errMsg = (String) request.getAttribute(PIXConstants.PIX_ERROR);
           			okURL = request.getContextPath() + okURL;
           			if(errMsg != null)
           			{
            			out.println(errMsg);
           			}
   			  %>
   			</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="4"></td>
        </tr>
        <tr>
          <td align="center"><label>
          	<div id="buttons2" class="tabSelectText"></div>
            <input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=okURL%>',this)" value=" Ok ">
</label>
</td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
         </html-el:form>
    </table></td>
  </tr>
<%}
else if(request.getAttribute(PIXConstants.PIX_ERROR)!=null){
%>
 <tr>
    <td height="40%" align="left" valign="top"></td>
  </tr>
  <tr>
    <td align="center" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
		<tr>
          <td height="31" align="center" valign="top" ><table height="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
              <td width="31" valign="middle" align="center"><img src="<%=request.getContextPath()%>/images/errorIcon.gif" width="17" height="17"></td>
              <td class="errorMessageText">
              <%
           			String errMsg = (String) request.getAttribute(PIXConstants.PIX_ERROR);
           			if(errMsg != null)
           			{
           				
            			out.println(errMsg);
           			}
   			  %>
   			</td>
         </tr>
	</table></td>
  </tr>
  <tr>
  	<td height="4"></td>
  </tr>
  <tr>
  	<td height="175">&nbsp;</td>
  </tr>
<%
}
else
{
%>
 <tr>
    <td height="40%" align="left" valign="top"></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
		<tr>
          <td height="31" align="center" valign="top" ><table height="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
              <td width="31" valign="middle" align="center"><img src="<%=request.getContextPath()%>/images/errorIcon.gif" width="17" height="17"></td>
              <td class="errorMessageText">
              <%
           			AppException ae = new AppException();
		   			ae.performErrorAction(Exceptions.EXCEPTION,"mod_error,mod_error",exception);
		   			String errMsg = ae.getSErrorDescription();
            		out.println(errMsg);

   			  %>
   			</td>
         </tr>
	</table></td>
  </tr>
  <tr >
  	<td  height="300"></td>
  </tr>
<%
}
%>  
</table>
</td>
</tr>