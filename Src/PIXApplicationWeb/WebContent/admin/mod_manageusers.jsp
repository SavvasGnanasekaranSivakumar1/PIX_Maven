


<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>

<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 

<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Manage Users </span></td>
  </tr>
  
   
  <tr>
    <td align="left" valign="top" class="padding23">
    <html-el:form action="/admin/listinuser">
      <%@ include file="/common/formbegin.jsp"%>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="text">Following are the list of users. You can edit a user by clicking on the <span class="textBlue">userid</span>. You can <span class="textBlue">add or disable</span> a user by clicking on the respective buttons. </td>
        </tr>
        <tr>
          <td height="15" align="right"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8"><a href="<%=request.getContextPath()%>/admin/filteruser.do" class="subLinks">filter</a></td>
        </tr>
        <tr>
          <td height="15" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td height="1" colspan="8" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
               <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
               <c:if test="${errorMsg == null}"> 
              <tr>
                <td width="4%" class="tableHeading">&nbsp;</td>
                <td class="tableHeading">USER ID </td>
                <td class="tableHeading">FIRST NAME </td>
                <td class="tableHeading">LAST NAME </td>
                <td class="tableHeading">ACCOUNT TYPE </td>
                <td class="tableHeading">ACCOUNT STATUS </td>
                <td class="tableHeading">CREATED DATE </td>
                <td class="tableHeading">CREATED BY </td>
              </tr>
              <tr>
              	<c:forEach var="userListDisplay" items="${userForm.userCollection}" varStatus="indexId"> 
              		<c:if test="${indexId.count%2 != 0}" >
	         			<c:set var="class1" value="tableRow"/>
	         			<c:set var="class2" value="tableRowlink"/>
	         		</c:if>
	         		<c:if test="${indexId.count%2 == 0}" >
	         			<c:set var="class1" value="tableAlternateRow"/>
	         			<c:set var="class2" value="tableAlternateRowlink"/>
	         		</c:if>  
              	<td class="${class1}">
          			<html-el:radio property="radioUser" value="${userListDisplay.userId}"></html-el:radio>
          			<html-el:hidden property="activeFlag" value="${userListDisplay.activeFlag}"/>	
          		</td>
          		
          		
          		<td class="${class1}">
          		<html-el:link styleClass="${class2}" page="/admin/edituser.do?ADMIN_MODULE=USER&LOGIN=${userListDisplay.userId}&USERNAME=${userListDisplay.firstName} ${userListDisplay.lastName}&FLAGSUPP=0&FLAGPUB=0&CREATEDBY=${userListDisplay.fullName}&PAGE_VALUE=${PAGE_VALUE}&userFilter=${userFilter}&accountStatusFilter=${accountStatusFilter}&accountTypeFilter=${accountTypeFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}">
              		<c:out value="${userListDisplay.login}"/>
              	</html-el:link></td>
          		<td class="${class1}"><c:out value="${userListDisplay.firstName}"/></td>
          		<td class="${class1}"><c:out value="${userListDisplay.lastName}"/></td>
          		<td class="${class1}"><c:out value="${userListDisplay.roleTypeDetail.roleType}"/></td>
          		<td class="${class1}"><c:out value="${userListDisplay.activeFlag}"/></td>
          		
          		<td class="${class1}"><fmt:formatDate value="${userListDisplay.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<td class="${class1}"><c:out value="${userListDisplay.fullName}"/></td>
              </tr>
              </c:forEach>
              </c:if>
         	  <c:if test="${errorMsg != null}">
         	    <tr> 
           			<td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           		</tr>
         	 </c:if>
              
              
              <tr>
                <td height="1" colspan="8" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td height="15" align="right">
          
          <c:if test="${prevValue!=null && prevValue!=''}">
          <a class="backNext_append" href="javascript:listfilterUser('${PATH}/admin/listuser.do?PAGE_VALUE=${prevValue}&ADMIN_MODULE=USER')" >
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" ></a>  
          </c:if>
           <c:if test="${nextValue!=null && nextValue!=''}">
    	     <a class="backNext_append" href="javascript:listfilterUser('${PATH}/admin/listuser.do?PAGE_VALUE=${nextValue}&ADMIN_MODULE=USER')" >
    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0" ></a> 
			</td>
    	 </c:if>
          
        </tr>
      </table>
     
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
	
        	<tr>
          		<td><label>
          		
          		 <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          		    <input name="FLAGSUPP" type="hidden" value="0">
          		    <input name="FLAGPUB" type="hidden" value="0">
          		    <input name="ADMIN_MODULE" type="hidden" value="USER">
          		    <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
          		    <input name="userFilter" type="hidden" value="${userFilter}">
					<input name="accountStatusFilter" type="hidden" value="${accountStatusFilter}">
					<input name="accountTypeFilter" type="hidden" value="${accountTypeFilter}">
					<input name="startDateFilter" type="hidden" value="${startDateFilter}">
					<input name="endDateFilter" type="hidden" value="${endDateFilter}">
          			<input name="Button" type="button" class="buttonMain" onClick="submitActionUser('<%=request.getContextPath()%>/admin/listinuser.do')" value="Add User">
            		<input name="Button1" type="button" class="buttonMain" onClick="if(validateDeleteUser()){submitActionUser('<%=request.getContextPath()%>/admin/deleteuser.do')}else{return false;}" value="Disable User">
            	</td>
            </tr>
            </table></label></td>
        	</tr>
        	<tr>
          		<td height="35">&nbsp;</td>
        	</tr>
    	</table></html-el:form></td>
  </tr>