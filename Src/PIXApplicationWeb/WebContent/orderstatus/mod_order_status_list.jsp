
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.presentation.orderstatus.action.OrderStatusForm,com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="page_order_list" value='<%=request.getParameter("page_order_list")%>' /> 
<c:set var="pageFilter" value='<%=request.getParameter("pageFilter")%>' /> 
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' /> 
<c:set var="isbnFilter" value='<%=request.getParameter("isbnFilter")%>' /> 
<c:set var="printNoFilter" value='<%=request.getParameter("printNoFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
<c:set var="sbBDateFilter" value='<%=request.getParameter("sbBDateFilter")%>' /> 
<c:set var="ebBDateFilter" value='<%=request.getParameter("ebBDateFilter")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(orderStatusForm.orderStatusCollection)}' />

<html-el:form action="/orderStatus/orderStatusNew.do" >
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  ${pono}
    <c:if test="${rno!=null && rno!='0'}">
    - ${rno}
    </c:if></span></td>
 </tr>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
 <tr>
    <%@ include file="/common/formbegin.jsp"%>
    <td align="left" valign="top" class="padding23">
    <table width="98%" border="0" cellspacing="0" cellpadding="0">
    <tr>
    	<td height="15" colspan="2" class="text">To view the   <span class="textBold">Job Status</span> details, click on the Status Number. </td>
    </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
          <c:set var="pageModule" value="orderstatus" scope="request"></c:set>
          <%@ include file="/common/mod_tabs.jsp"%>
        </table></td>
        <td align="right" valign="bottom"></td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        <c:if test="${errorMsg == null}"> 
       	<tr>
       	  <td width="2%" align="center" class="tableHeading">
          	<input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.orderStatusForm.idHidden,document.orderStatusForm.selectedEntry);">
          </td>
          <td class="tableHeading">STATUS NUMBER </td>
          <td class="tableHeading">POSTED DATE </td>
          <td class="tableHeading">POSTED BY </td>
        </tr>
        <tr>
       <c:forEach var="orderStatus" items="${orderStatusForm.orderStatusCollection}"  varStatus="indexId">  
       	   <c:if test="${indexId.count%2 != 0}" >
        	   <c:set var="class1" value="tableRow"/>
           	   <c:set var="class2" value="tableRowlink"/>
           </c:if>
           <c:if test="${indexId.count%2 == 0}" >
           	  <c:set var="class1" value="tableAlternateRow"/>
           	  <c:set var="class2" value="tableAlternateRowlink"/>
           </c:if>  
           <td align="center" class="${class1}"><label>
           
            <c:choose>
               <c:when test="${checkBoxLength == 1}">
	             <html-el:checkbox  property="selectedEntry" value="${orderStatus.statusId}" onclick="javascript:addDeleteIdsFromSetNew(document.orderStatusForm.idHidden,'${orderStatus.statusId}',document.orderStatusForm.selectedEntry);" />
	           </c:when>
               <c:otherwise> 
	             <html-el:checkbox  property="selectedEntry" value="${orderStatus.statusId}" onclick="javascript:addDeleteIdsFromSetNew(document.orderStatusForm.idHidden,'${orderStatus.statusId}',document.orderStatusForm.selectedEntry[${indexId.index}]);"/>
	            
	         </c:otherwise>
		   </c:choose>	
		       
	       </label></td> 
          <td class="${class1}">
          <html-el:link styleClass="${class2}" page="/orderStatus/orderStatusDetail.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&OS_ID=${orderStatus.statusId}&OS_NO=${orderStatus.statusNo}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}"><c:out value="${orderStatus.statusNo}"/></html-el:link>
          <td class="${class1}"><fmt:formatDate value="${orderStatus.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          <td class="${class1}"><c:out value="${orderStatus.createdBy}"/></td>
         </tr>
        </c:forEach>
         </c:if>
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
       <tr>
          <td height="1" colspan="4" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="15" align="right">
          <c:if test="${prevValue!=null && prevValue!=''}">
          	<html-el:link styleClass="backNext_append" href="javascript:listfilter5('${CONTEXT_PATH}/orderStatus/orderStatusList.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${prevValue}&page_order_list=${page_order_list}');">
          	<img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
    	 	<html-el:link styleClass="backNext_append" href="javascript:listfilter5('${CONTEXT_PATH}/orderStatus/orderStatusList.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${nextValue}&page_order_list=${page_order_list}');">
    	 	<img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
    	 	</c:if>
         </td>
       </tr>
       <tr>
          <td><label>
           <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
		<input type="hidden" name="checksArr"> 
        <input type="hidden" name="poId" value="${poid}">
        <input type="hidden" name="poVersion" value="${poversion}">    
        <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        <input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
          <script language="javascript" >
        comapareCheckedValuesModified(document.orderStatusForm.idHidden.value,document.orderStatusForm.selectedEntry,'${checkBoxLength}',document.orderStatusForm.checkall);
        </script>
		<%
		if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.ORDERSTATUS_CODE) && "P".equals(request.getParameter("order")))
     	{
		%>  
		  <%--<input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">        --%>
		  <input name="page_order_list" type="hidden" value="${page_order_list}">
          <input name="Button" type="button" class="buttonMain" onClick="submitStatusAction('<%=request.getContextPath()%>/orderStatus/orderStatusNew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this)" value="New Status">
		<%}%> 
		<%		if("F".equals(request.getParameter("order"))){%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C')" value="Close">
		<%}else{%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V')" value="Close">
		<%}%>         
		   <c:if test="${errorMsg == null}">     
		  <input name="excel1" type="button" class="buttonMain" onClick="javascript:acceptOrderStatusNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=order')" value="Export to Excel">
          <input name="excel2" type="button" class="buttonMain" onClick="submitStatusAction('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=orderAll',this)" value="Export All to Excel">
          </c:if>
          <input type="hidden" name="flag"/>
          </td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table>
     </td>
     </tr>
   </html-el:form>
 
  


