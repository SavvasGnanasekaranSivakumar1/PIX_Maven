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
<c:set var="stat"><%=request.getParameter("stat")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="orderType"><%=request.getParameter("orderType")%></c:set>
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
<c:set var="checkBoxLength" value='${fn:length(deliveryMessageForm.deliverymessageCollection)}' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>

<c:set var="lineItemSize" value='<%=request.getParameter("lineItem")%>' /> 
<html-el:form action="/deliverymessage/deliverymessagelist"> 
<input type="hidden" name="stat" id="stat" value="${deliveryMessageForm.stat}"/>
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
<html-el:hidden property="poId" />
<html-el:hidden property="poVersion" />


<%-- <%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
%> --%>
 
        
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. <c:out value="${pono}"/>
    <c:if test="${rno!=null && rno!='0'}">
    - ${rno}
    </c:if> </span></td>
</tr>
   
   <%@ include file="/common/formbegin.jsp"%>
  <tr>
     
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
       
      <tr>
        <td height="15" colspan="2" class="text">To view the <span class="textBold">Delivery Message</span> details, click on the Delivery Message Number. </td>
      </tr>
       
        <c:if test="${PmsPoHeaderStatus!=null}">
  		<font color="#CC0000" size="2" face="Verdana">
    	<span >${PmsPoHeaderStatus}</span>	
  		</c:if>	   
      
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
          <c:set var="pageModule" value="deliverymessage" scope="request"></c:set>
          <c:set var="lineItem" value="${lineItemSize}" scope="request"></c:set>
           <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
          <%@ include file="/common/mod_tabs.jsp"%>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
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
<%}%>
--%>
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
<%}%> 
--%>	     <input type="hidden" name="querystr_${deliveryMessage.msgId}-${deliveryMessage.lineNo}" value="${deliveryMessage.msgId}"> 
      		  <input type="hidden" name="delLine_${deliveryMessage.msgId}-${deliveryMessage.lineNo}" value="${deliveryMessage.lineNo}"> 	
      			
      			
            <td align="center" class="${class1}"><label>
            <c:choose >
            	<c:when test="${orderPaper=='O'}">
				  	 <c:choose>
			              <c:when test="${checkBoxLength == 1}"> 
				          <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}-${deliveryMessage.lineNo}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}-${deliveryMessage.lineNo}',document.deliveryMessageForm.selectedEntry);" />
				         
				         </c:when>
			               <c:otherwise>
			               	 <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}-${deliveryMessage.lineNo}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}-${deliveryMessage.lineNo}',document.deliveryMessageForm.selectedEntry[${indexId.index}]);"/>
				            
				            </c:otherwise>
					  </c:choose>	       
				</c:when>
				<c:otherwise>
					 <c:choose>
			              <c:when test="${checkBoxLength == 1}"> 
				          <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}',document.deliveryMessageForm.selectedEntry);" />
				         
				         </c:when>
			               <c:otherwise>
			               	
				            <html-el:checkbox  property="selectedEntry" value="${deliveryMessage.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.deliveryMessageForm.idHidden,'${deliveryMessage.msgId}',document.deliveryMessageForm.selectedEntry[${indexId.index}]);"/>
				            
				            </c:otherwise>
					       </c:choose>	
				</c:otherwise>
			</c:choose>
	          </label></td>
          <c:set var="count" value="${indexId.count}"/>   
         <td class="${class1}">
         <html-el:link  styleClass="${class2}" page="/deliverymessage/deliverymessagedetail.do?MSG_ID=${deliveryMessage.msgId}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}"><c:out value="${deliveryMessage.msgNo}"/></html-el:link>
         <td class="${class1}"><fmt:formatDate value="${deliveryMessage.creationDateTime}" type="both" pattern="MM/dd/yyyy" /></td>
         <td class="${class1}"><c:out value="${deliveryMessage.createdBy}"/></td>
         <td class="${class1}"><c:out value="${deliveryMessage.msgTypeDetail}"/></td> 
         <td class="${class1}"><c:out value="${deliveryMessage.name_1}"/></td>
         </tr> 
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
         <c:choose >
            	<c:when test="${orderPaper=='O'}"> 
          				
			          <c:if test="${prevValue!=null && prevValue!=''}">
			          <html-el:link styleClass="backNext_append" href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${prevValue}&page_order_list=${page_order_list}&fo=paperfo&orderPaper=O&orderType=${orderType}');">
			          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
			          </c:if>
			           <c:if test="${nextValue!=null && nextValue!=''}">
			    	     <html-el:link styleClass="backNext_append" href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${nextValue}&page_order_list=${page_order_list}&fo=paperfo&orderPaper=O&orderType=${orderType}');">
			    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
						</td>
			    	     </c:if>
			    </c:when>
			    <c:otherwise>
			    	<c:if test="${prevValue!=null && prevValue!=''}">
			          <html-el:link styleClass="backNext_append" href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${prevValue}&page_order_list=${page_order_list}&orderType=${orderType}');">
			          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
			          </c:if>
			           <c:if test="${nextValue!=null && nextValue!=''}">
			    	     <html-el:link styleClass="backNext_append" href="javascript:listfilter3('${CONTEXT_PATH}/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${nextValue}&page_order_list=${page_order_list}&orderType=${orderType}');">
			    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
						</td>
			    	     </c:if>
			    </c:otherwise>
		</c:choose>
        <tr>
          
        </tr>
        <tr>
          <td colspan="2"><label>
 <%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order")))
{	
%> 
			 <input name="page_order_list" type="hidden" value="${page_order_list}">
			 <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="statusFilter" value="${statusFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        	<input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        	<input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
	         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
	                
             <c:choose>
               <c:when test="${roleType == 'M'}">
               		<c:if test="${lineItemSize!='0'}">
	             		<input name="Button1" type="button" class="buttonMain" onClick="submitActionNDM('<%=request.getContextPath()%>/deliverymessage/milldeliverymessagenew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&action=DMList',this)" value="New Delivery Message">
	             	</c:if>
	           </c:when>
               <c:otherwise> 
	          <input name="Button1" type="button" class="buttonMain" onClick="submitActionNDM('<%=request.getContextPath()%>/deliverymessage/deliverymessagenew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this)" value="New Delivery Message">
	         </c:otherwise>
		   </c:choose>	
             
             
            
<%}%>  
		
	
	<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.GOODSREC_CODE))
{
%>	
			<c:if test="${orderPaper=='O'}">
				<input name="Button9" type="button" class="buttonMain" onClick="newPaperGoodReceipt('<%=request.getContextPath()%>/goodsreceipt/millgoodsreceiptnew.do?PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&fo=paperfo&orderPaper=${orderPaper}');" value="New Goods Receipt">
			</c:if>
		
  <%}%>          
            <%--<input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}')" value="Close">--%>  
            <%		if("F".equals(request.getParameter("order"))){%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
		<%}else{%>
		
		 <c:choose>
               <c:when test="${roleType == 'M'}">
	             <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=M')" value="Close">
	           </c:when>
               <c:otherwise> 
	          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V')" value="Close">
	         </c:otherwise>
		   </c:choose>	
		
		
		
          
		<%}%>     
            <input type="hidden" name="checksArr"> 
            <c:if test="${errorMsg == null}">
            	<c:choose>
            		<c:when test="${orderPaper=='O'}">
           				 <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOsNew1('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=delivery')" value="Export to Excel">
					</c:when>
					<c:otherwise>
						<input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOsNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=delivery')" value="Export to Excel">
					</c:otherwise>
				</c:choose>
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
       
     <script language="javascript" >
             comapareCheckedValuesModified(document.deliveryMessageForm.idHidden.value,document.deliveryMessageForm.selectedEntry,'${checkBoxLength}',document.deliveryMessageForm.checkall);
             </script>
        
     </html-el:form>
     </table></td>
  </tr>
