<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
 
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Usage</span></td>
 </tr>
 <tr>
  <html-el:form action="/usage/usageList"> 
      <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       <tr>
        	<td colspan="2" class="text">To view the Usage of a purchase order, click on the order number. You can also create a new Usage.</td>
       </tr>
       <tr>
       	 <td height="15" colspan="2"></td>
       </tr>
       <tr>
       	 <td width="50%"><table border="0" cellspacing="0" cellpadding="0">
       <tr>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_lt_green.gif" width="10" height="25"></td>
            <td width="119" align="center" valign="middle" class="tabBg">Purchase Orders </td>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_rt_green.gif" width="10" height="25"></td>
           </tr> </table></td>
            <td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
            <html-el:link styleClass="subLinksMain" page="/usage/usageFilter.do">filter</html-el:link></td> 
        <tr>
        	<td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
         <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" align="center" class="tableHeading">
          </td>
          <td class="tableHeading" width="10">ORDER NUMBER </td>
          <td class="tableHeading" width="10">ISBN </td>
          <td class="tableHeading" width="10">PRINT NUMBER</td>
          <td class="tableHeading" width="10">ISSUE DATE </td>
          <td class="tableHeading" width="20">BUYER </td>
          <td class="tableHeading" width="20">VENDOR</td>
          <td class="tableHeading" width="10">VENDOR CONTACT</td>
          <td class="tableHeading" width="6">STATUS </td>
          </tr>
          <c:set var="count" value=""/>
          <c:forEach var="usageList" items="${usageFormList.usageCollection}" varStatus="indexId"> 
          <input type="hidden" name="querystr_${indexId.index}" value="poid=${usageList.poId}&poversion=${usageList.poVersion}&pono=${usageList.poNo}&rno=${usageList.releaseNo}&uid=${usageList.usageId}">  
            <c:if test="${indexId.count%2 != 0}">
           		<c:set var="class1" value="tableRow"/>
           		<c:set var="class2" value="tableRowlink"/>
           </c:if>
           <c:if test="${indexId.count%2 == 0}">
           		<c:set var="class1" value="tableAlternateRow"/>
           		<c:set var="class2" value="tableAlternateRowlink"/>
           </c:if>
	        <tr>
	          <td align="center" class="${class1}"><label>
	          <html-el:radio  property="selectedEntry" value="${indexId.index}" />
	          </label></td>
	          <c:set var="count" value="${indexId.count}"/>
	          <td class="${class1}">
	          <c:if test="${usageList.usageId != null}" >
	          	<html-el:link styleClass="${class2}" page="/usage/usageDetail.do?poid=${usageList.poId}&poversion=${usageList.poVersion}&pono=${usageList.poNo}&rno=${usageList.releaseNo}&uid=${usageList.usageId}&PAGE_VALUE=${PAGE_VALUE}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}"><c:out value="${usageList.poNo}"/>
	          </html-el:link>
	          </c:if>
	          <c:if test="${usageList.usageId == null}">
	          	<c:out value="${usageList.poNo}"/>
	          </c:if>
	          </td>
	          <td class="${class1}"><c:out value="${usageList.titleDetail.isbn10}"/></td>
	          <td class="${class1}"><c:out value="${usageList.titleDetail.printNo}"/></td>
	          <td class="${class1}"><fmt:formatDate value="${usageList.issueDate}" type="both" pattern="MM/dd/yy" /></td>
	          <c:forEach var="party" items="${usageList.partyCollection}" varStatus="partyCount">  
	           <td class="${class1}"><c:out value="${party.name1}"/></td>
	           <td class="${class1}"><c:out value="${party.name2}"/></td> 
	          <td class="${class1}"><c:out value="${party.address1}"/></td> 
	          </c:forEach>  
	          <td class="${class1}"><c:out value="${usageList.statusDetail.statusDescription}"/></td>
		    </tr>
          </c:forEach>
          </c:if>
          <c:if test="${errorMsg != null}">
           <tr>
             <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr>
      </c:if>
        <tr>
          <td height="1" colspan="9" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
     	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
         <td height="15" align="right">  
         <c:if test="${prevValue!=null && prevValue!=''}">
          	<html-el:link  styleClass="backNext_append" href="javascript:listfilter('${PATH}/usage/usageList.do?PAGE_VALUE=${prevValue}')">
          	<img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"> </html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
             <html-el:link styleClass="backNext_append" href="javascript:listfilter('${PATH}/usage/usageList.do?PAGE_VALUE=${nextValue}')">
             <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link> 
    	   </c:if>
    	   </td>
        </tr>
        
        <tr>
        <c:if test="${errorMsg == null}">
	<%
		if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.USAGE_CODE))
	{
	%>    
          <td colspan="2"><label> 
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          	<input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
            <input name="Button2" type="button" class="buttonMain" onClick="javascript:newUsage('<%=request.getContextPath()%>/usage/newusage.do')" value="New Usage">
          </td>
            </tr>
            </table></label></td>
	<%}%>        
           </c:if>
        </tr>
        <tr>
          <td height="35" colspan="2">&nbsp;</td>
        </tr>
      </table>
    </html-el:form>
  </tr>
     
        






