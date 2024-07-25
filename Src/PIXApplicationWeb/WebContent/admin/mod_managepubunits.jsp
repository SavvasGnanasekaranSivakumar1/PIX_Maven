
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 

  <tr>
    <td height="25" align="left" valign="top">
    
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <span class="headingText">Manage PUB Units</span></td>
  </tr>
  
 
  

  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <html-el:form action="/admin/listinpubunit">
         
        <tr>
          <td class="text">Following are the list of Pub Units. You can edit a pub unit by clicking on the <span class="textBlue">publisher san</span>. You can <span class="textBlue">add </span> a pub unit by clicking on the respective button.</td>
        </tr>
        
        <tr>
          <td height="15" align="right"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8"><a href="<%=request.getContextPath()%>/admin/filterpubunit.do" class="subLinks">filter</a></td>
        </tr>
        

        <tr>
          <td height="15" align="left" valign="top">
          <table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td height="1" colspan="5" class="tableLine">
                <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
              <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
               <c:if test="${errorMsg == null}"> 
              <tr>
                <td class="tableHeading">PUBLISHER SAN </td>
                <td class="tableHeading">PUBLISHER NAME </td>
                <td class="tableHeading">PUBLISHER STATUS </td>
                <td class="tableHeading">CREATED DATE </td>
                <td class="tableHeading">CREATED BY </td>
              </tr>
              
              <tr>
         		<c:forEach var="pubUnitList" items="${addPubUnitForm.pubUnitListCollection}" varStatus="indexId">     
          			<c:if test="${indexId.count%2 != 0}" >
	         			<c:set var="class1" value="tableRow"/>
	         			<c:set var="class2" value="tableRowlink"/>
	         		</c:if>
	         		<c:if test="${indexId.count%2 == 0}" >
	         			<c:set var="class1" value="tableAlternateRow"/>
	         			<c:set var="class2" value="tableAlternateRowlink"/>
	         		</c:if> 
          		
          		
          		<td class="${class1}">
          			<html-el:link styleClass="${class2}" page="/admin/editpubunit.do?SAN_VALUE=${pubUnitList.san}&ADMIN_MODULE=PUB&PAGE_VALUE=${PAGE_VALUE}&sanFilter=${sanFilter}&nameFilter=${nameFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}">
              			<c:out value="${pubUnitList.san}"/>
          			</html-el:link></td>
          		<td class="${class1}"><c:out value="${pubUnitList.name1}"/></td>
          		<c:choose>
          			<c:when test="${pubUnitList.activeFlag=='Y'}">
						<td class="${class1}"><c:out value="Active"/></td>
					</c:when>
					<c:otherwise>
						<td class="${class1}"><c:out value="Disabled"/></td>
					</c:otherwise>
				</c:choose>
          		
          		<td class="${class1}"><fmt:formatDate value="${pubUnitList.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          		<td class="${class1}"><c:out value="${pubUnitList.fullName}"/></td>
         	 </tr> 
         	 
         		</c:forEach>
         	  </c:if>
         	  <c:if test="${errorMsg != null}">
           		<tr> 
           			<td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           		</tr>
         	 </c:if>
              <tr>
                <td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td height="15" align="right">
          
          <c:if test="${prevValue!=null && prevValue!=''}">
          <a class="backNext_append" href="javascript:listfilterPublisher('${PATH}/admin/listpub.do?PAGE_VALUE=${prevValue}&ADMIN_MODULE=PUB')" >
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" ></a>  
          </c:if>
                
             <c:if test="${nextValue!=null && nextValue!=''}">
    	     <a class="backNext_append" href="javascript:listfilterPublisher('${PATH}/admin/listpub.do?PAGE_VALUE=${nextValue}&ADMIN_MODULE=PUB')" >
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
		<input name="ADMIN_MODULE" type="hidden" value="PUB">
		<input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
		<input name="sanFilter" type="hidden" value="${sanFilter}">
		<input name="nameFilter" type="hidden" value="${nameFilter}">
		<input name="statusFilter" type="hidden" value="${statusFilter}">
		<input name="startDateFilter" type="hidden" value="${startDateFilter}">
		<input name="endDateFilter" type="hidden" value="${endDateFilter}">
		<input name="Button" type="button" class="buttonMain" onClick="submitActionPublisher('<%=request.getContextPath()%>/admin/listinpubunit.do')" value="Add Pub Unit">
		
        </td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
    </html-el:form></table></td>
  </tr>
    