
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>






  <html-el:form action="/admin/listpub?PAGE_VALUE=1&ADMIN_MODULE=PUB">
    <%@ include file="/common/formbegin.jsp"%>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Manage PUB Units - Filter </span></td>
  </tr>

  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="text">You can filter the list of pub units based on thefollowing attributes. </td>
        </tr>
        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td height="15" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
            </tr>
            <tr>
              <td colspan="2" class="titleBlue2">PUBLISHER SAN: </td>
              <td width="70%" class="inboxdrakBlue2"><html-el:text property="party.san"  maxlength="60" styleClass="textfield" /></td>
            </tr>
            <tr>
              <td colspan="2" class="titleBlue2">PUBLISHER NAME: </td>
              <td width="70%" class="inboxdrakBlue2"><html-el:text property="party.name1" maxlength="60" styleClass="textfield" /></td>
            </tr>
            <tr>
              <td colspan="2" class="titleBlue2">PUBLISHER STATUS: </td>
              <td class="inboxdrakBlue2">
              <html-el:select property="party.activeFlag" styleClass="textfield">
                 <html-el:option value="">Choose Status</html-el:option>
                 <html-el:option value="Y">Active</html-el:option>
                 <html-el:option value="N">Disabled</html-el:option>
              </html-el:select></td>
            </tr>
            
           <tr>
              <td width="15%" rowspan="2" class="titleBlue2">CREATION DATE - </td>
              <td class="titleBlue2">START DATE: </td>
              <td class="inboxdrakBlue2"><html-el:text size="12" maxlength="30" styleId="start_date" styleClass="textfield" property="startDate" readonly="true"/>
                  
                 <html-el:link href="javascript:NewCal('start_date','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/calender.gif" width="16" height="13" border="0"></html-el:link></td>
                 
            </tr>
			 <tr>
              <td class="titleBlue2">END DATE: </td>
              <td class="inboxdrakBlue2"><html-el:text size="12" maxlength="30" styleId="end_date" styleClass="textfield" property="endDate" readonly="true"/>
				<html-el:link href="javascript:NewCal('end_date','MMDDYYYY');"><img src="<%=request.getContextPath()%>/images/calender.gif" width="16" height="13" border="0"></html-el:link></td>
			
			 </tr>
           </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
            </table>		  </td>
        </tr>
        <tr>
          <td height="15" align="right" valign="bottom"></td>
        </tr>
      </table>
      
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
	
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
			 <html-el:submit value="Filter" styleClass="buttonMain" onclick="javascript: return dateCheck(document.addPubUnitForm.endDate.value,document.addPubUnitForm.startDate.value,'>=','Start Date should be ','End Date');"/> 
			 </td>
            </tr>
            </table>
		  </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
   </table></td>
  </tr>
   </html-el:form>