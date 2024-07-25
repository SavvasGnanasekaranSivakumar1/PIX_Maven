
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>



  <html-el:form action="/admin/listuser?PAGE_VALUE=1&ADMIN_MODULE=USER">
  <%@ include file="/common/formbegin.jsp"%>
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Manage Users - Filter</span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="text">You can filter  users based on the following attributes. </td>
        </tr>
        <tr>
          <td height="15" align="right"></td>
        </tr>
        <tr>
          <td height="15" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td height="1" colspan="3" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
              <tr>
                <td colspan="2" class="titleBlue2">USER ID:</td>
                <td width="70%" class="inboxdrakBlue2"><html-el:text property="user.login" maxlength="20" styleClass="textfield" /></td>
              </tr>
              
              <tr>
                <td colspan="2" class="titleBlue2">FIRST NAME:</td>
                <td width="70%" class="inboxdrakBlue2"><html-el:text property="user.firstName" maxlength="20" styleClass="textfield" /></td>
              </tr>
              
              <tr>
                <td colspan="2" class="titleBlue2">LAST NAME:</td>
                <td width="70%" class="inboxdrakBlue2"><html-el:text property="user.lastName" maxlength="20" styleClass="textfield" /></td>
              </tr>
              
              <tr>
                <td colspan="2" class="titleBlue2">ACCOUNT TYPE: </td>
                <td class="inboxdrakBlue2"><html-el:select property="user.roleTypeDetail.roleType" styleClass= "textfield">
						<html-el:option value="">Choose Account Type</html-el:option>
						
						<c:forEach var="item" items="${userList}" varStatus="indexId">
							<c:if test="${item.description != 'SHIPTO'}">
								<html-el:option value="${item.roleType}"><c:out value="${item.description}"/></html-el:option>
							</c:if>
						</c:forEach>
					</html-el:select> </td>
              </tr>
              <tr>
                <td colspan="2" class="titleBlue2">ACCOUNT STATUS: </td>
                <td class="inboxdrakBlue2"><html-el:select property="user.activeFlag" styleClass="textfield">
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
            </table></td>
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
            <html-el:submit value="Filter" styleClass="buttonMain" onclick="javascript: return dateCheck(document.userForm.endDate.value,document.userForm.startDate.value,'>=','Start Date should be ','End Date');"/> 
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