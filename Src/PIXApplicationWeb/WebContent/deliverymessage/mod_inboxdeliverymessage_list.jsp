<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="errMsg">${errorMsg}</c:set>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="orderType"><%=request.getParameter("orderType")%></c:set>
<c:set var="userId"><%=request.getParameter("userId")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(deliveryMessageForm.deliverymessageCollection)}' />
<c:set var="partyType" value='<%=request.getParameter("partyType")%>' /> 
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />

<html-el:form action="/deliverymessage/deliverymessagelist"> 
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}" />
<html-el:hidden property="poId" value="0"/>
<html-el:hidden property="poVersion" value="0"/>
<%-- <%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
%> --%>
<tr>
    <td height="25" align="left" valign="top">
</tr>
   
   <%@ include file="/common/formbegin.jsp"%>
  <tr>
     
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">To view the <span class="textBold">Delivery Message</span> details, click on the Delivery Message Number. </td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
      <td>
      <table border="0" cellspacing="0" cellpadding="0" >
      <tr>
      		<td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
            <td width="130" align="center" class="tabSubBg" ><a href="<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?PAGE_VALUE=1&userId=${userId}&days=${days}" class="tabSubBgLink" >Delivery Message</a></td>
            <td  height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="11" height="22"></td>
      </tr>
      </table>
      </td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine">
        <img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
       </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        <c:if test="${errorMsg == null}"> 
          <tr>
<%--<%
if(showCheckBox)
{
%>        
          <td width="2%" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="checkUncheckAll(this);">
          </td>
<%}%> --%>
           <td width="2%" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.deliveryMessageForm.idHidden,document.deliveryMessageForm.selectedEntry);">
          </td> 
          <td class="tableHeading">DELIVERY MESSAGE  NO. </td>
          <td class="tableHeading">MESSAGE POSTED DATE </td>
          <td class="tableHeading">MESSAGE POSTED BY </td>
          <td class="tableHeading">MESSAGE TYPE </td>
          <td class="tableHeading">DELIVERY DESTINATION </td>
       </tr>
       <tr>
         <c:forEach var="deliveryMessage" items="${deliveryMessageForm.deliverymessageCollection}" varStatus="indexId">     
         <c:if test="${indexId.count%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         <c:set var="class2" value="tableRowlink"/>
         </c:if>
         <c:if test="${indexId.count%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
          <c:set var="class2" value="tableAlternateRowlink"/>
         </c:if>   
<%-- <%
if(showCheckBox)
{
%>	          
	          <td align="center" class="${class1}"><label>
	          <%--  <html-el:checkbox property="poCollectionIdx[${deliveryMessage.msgId}]"/> 
	          <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}" />
	          </label></td>
<%}%> --%>	   
          <td align="center" class="${class1}"><label>
	         <c:choose>
              <c:when test="${checkBoxLength == 1}"> 
	          <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}',document.deliveryMessageForm.selectedEntry);"/>
	          </c:when>
               <c:otherwise> 
	            
	            <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}',document.deliveryMessageForm.selectedEntry[${indexId.index}]);"/>
	            
	           </c:otherwise>
		       </c:choose>   
	          </label></td>       
          <c:set var="count" value="${indexId.count}"/> 
         
         <td class="${class1}">
         <html-el:link  styleClass="${class2}"  page="/deliverymessage/inboxdeliverymessagedetail.do?MSG_ID=${deliveryMessage.msgId}&home=H&poid=${deliveryMessage.poId}&poversion=${deliveryMessage.poVersion}&days=${days}&PAGE_VALUE=${PAGE_VALUE}&partyType=${partyType}&order=${order}&rno=${rno}&orderType=${orderType}"><c:out value="${deliveryMessage.msgNo}"/></html-el:link>
         <td class="${class1}"><fmt:formatDate value="${deliveryMessage.creationDateTime}" type="both" pattern="MM/dd/yyyy" /></td>
         <td class="${class1}"><c:out value="${deliveryMessage.createdBy}"/></td>
         <td class="${class1}"><c:out value="${deliveryMessage.msgTypeDetail}"/></td> 
         <td class="${class1}"><c:out value="${deliveryMessage.name_1}"/></td>
         </tr> 
         <c:set var="poid">${deliveryMessage.poId}</c:set>
          <c:set var="poversion">${deliveryMessage.poVersion}</c:set>
         </c:forEach>
         </c:if>  
          <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
           </c:if>
        <tr>
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
        <div id="buttons2" class="tabSelectText"></div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     
         <tr>
          <td height="15" align="right">
          <c:if test="${prevValue!=null && prevValue!=''}">
          <html-el:link styleClass="backNext_append" href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/inboxdeliverylist.do?home=H&PAGE_VALUE=${prevValue}&days=${days}&orderType=${orderType}');">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
          </c:if>
           <c:if test="${nextValue!=null && nextValue!=''}">
    	     <html-el:link styleClass="backNext_append"  href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/inboxdeliverylist.do?home=H&PAGE_VALUE=${nextValue}&days=${days}&orderType=${orderType}');">
    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
    	     </td>
    	     </c:if>
        <tr>
          
        </tr>
        <tr>
          <td colspan="2"><label>
           
           <script language="javascript" >
             comapareCheckedValuesModified(document.deliveryMessageForm.idHidden.value,document.deliveryMessageForm.selectedEntry,'${checkBoxLength}',document.deliveryMessageForm.checkall);
             </script>
            
            <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/home/home.do')" value="Close">
            <input type="hidden" name="checksArr">
            <%-- <input type="hidden" name="poId" value="">
           	<input type="hidden" name="poVersion" value=""> --%>
            <c:if test="${errorMsg == null}">
            <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOsNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=delivery')" value="Export to Excel">

            <input name="excel" type="button" class="buttonMain" onClick="submitDelmsgAction2('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=deliveryAll',this)" value="Export All to Excel">
		 </c:if>
            <input type="hidden" name="flag"/>
             </label></td>
          </tr>
        <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
      </table></td>
</tr>
       
      <%--  <tr>
        
         <td height="1" colspan="8" class="tableLine"> <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr> --%>
        
     </html-el:form>
     
  </tr>
