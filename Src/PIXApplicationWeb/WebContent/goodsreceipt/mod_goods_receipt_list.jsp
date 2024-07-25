<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.presentation.goodsreceipt.action.GoodsReceiptForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="errMsg">${errorMsg}</c:set>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
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
<c:set var="checkBoxLength" value='${fn:length(goodsReceiptForm.goodsreceiptCollection)}' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />

<c:set var="lineItemSize" value='<%=request.getParameter("lineItem")%>' /> 
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>
<html-el:form action="/goodsreceipt/goodsreceiptlist"> 
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
<html-el:hidden property="poId" />
<html-el:hidden property="poVersion" />
 <%--<%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
%> --%>
<tr>
    <td height="25" align="left" valign="top">
<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
<span class="headingText">Purchase Order No. <c:out value="${pono}"/> 
	<c:if test="${rno!=null && rno!='0'}">
    - ${rno}
    </c:if> </span></td>
  </tr>
   
    <%@ include file="/common/formbegin.jsp"%>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">To view the <span class="textBold">Goods Receipt </span> details, click on the Goods Receipt Number. </td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
        
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
			<c:set var="pageModule" value="goodsreceipt" scope="request"></c:set>
			<c:set var="lineItem" value="${lineItemSize}" scope="request"></c:set>
			 <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
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
<%-- <%
if(showCheckBox)
{
%>        
          <td width="2%" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="checkUncheckAll(this);">
          </td>
<%}%>  --%>
           <td width="2%" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.goodsReceiptForm.idHidden,document.goodsReceiptForm.selectedEntry);">
          </td> 
          <td class="tableHeading">GOODS RECEIPT NO. </td>
          <td class="tableHeading">DELIVERY MESSAGE  NO. </td>
          
          <c:choose>
          	<c:when test="${orderPaper=='O' || roleType=='M'}">
          	  <td class="tableHeading">GOODS ARRIVAL DATE </td>	
          	  <td class="tableHeading">MILL/MERCHANT </td>
	          <td class="tableHeading">RECEIVER </td>
	          <td class="tableHeading">POSTED DATE </td>
	          <td class="tableHeading">POSTED BY </td>
          	</c:when>
          	<c:otherwise>
          	  <td class="tableHeading">GOODS ARRIVAL DATE </td>	
	          <td class="tableHeading">COMPONENT VENDOR </td>
	          <td class="tableHeading">SHIP TO VENDOR </td>
	          <td class="tableHeading">RECEIPT POSTED DATE </td>
	          <td class="tableHeading">RECEIPT POSTED BY </td> 
	        </c:otherwise>
	     </c:choose>
        </tr>
        <tr>
         <c:forEach var="goodsreceipt" items="${goodsReceiptForm.goodsreceiptCollection}" varStatus="indexId">   
          <c:if test="${indexId.count%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         <c:set var="class2" value="tableRowlink"/>
         </c:if>
         <c:if test="${indexId.count%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         <c:set var="class2" value="tableAlternateRowlink"/>
         </c:if>   
<%--<%
if(showCheckBox)
{
%>	          
	          <td align="center" class="${class1}"><label>
	          <%--  <html-el:checkbox property="poCollectionIdx[${indexId.index}]"/> 
	          <html-el:checkbox  property="selectedEntry" value="${goodsreceipt.receiptId}" />
	          </label></td>
<%}%>	          
 --%>            
          <td align="center" class="${class1}"><label>
          
	           <c:choose>
              <c:when test="${checkBoxLength == 1}"> 
	          <html-el:checkbox  property="selectedEntry" value="${goodsreceipt.receiptId}" onclick="javascript:addDeleteIdsFromSetNew(document.goodsReceiptForm.idHidden,'${goodsreceipt.receiptId}',document.goodsReceiptForm.selectedEntry);"/>
	           </c:when>
               <c:otherwise> 
	         <html-el:checkbox  property="selectedEntry" value="${goodsreceipt.receiptId}" onclick="javascript:addDeleteIdsFromSetNew(document.goodsReceiptForm.idHidden,'${goodsreceipt.receiptId}',document.goodsReceiptForm.selectedEntry[${indexId.index}]);"/>
	          </c:otherwise>
		       </c:choose> 
		       
	          </label></td>
          <c:set var="count" value="${indexId.count}"/> 
          	      
           <td class="${class1}"><html-el:link styleClass="${class2}" page="/goodsreceipt/goodsreceiptdetial.do?RECEIPT_ID=${goodsreceipt.receiptId}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}"><c:out value="${goodsreceipt.receiptNo}"/></html-el:link>
           <td class="${class1}"> <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/goodsreceipt/deliverymessagedetail.do?MSG_ID=${goodsreceipt.msgId}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderPaper=${orderPaper}','DeliveryMessageDetail','scrollbars=yes,width=750,height=500')" class="${class2}"><c:out value="${goodsreceipt.msgNo}"/></a> 
         <%-- <td class="tableRow"><html-el:link page="/deliverymessagedetail.do?MSG_ID=${goodsreceipt.msgId}"><c:out value="${goodsreceipt.msgNo}"/></html-el:link> --%>
           <td class="${class1}"><fmt:formatDate value="${goodsreceipt.actualarrivalDate}" type="both" pattern="MM/dd/yyyy" /></td>
           <td class="${class1}"><c:out value="${goodsreceipt.comp_vendor}"/></td>
           <td class="${class1}"><c:out value="${goodsreceipt.ship_to_vendor}"/></td>
           <td class="${class1}"><fmt:formatDate value="${goodsreceipt.creationDateTime}" type="both" pattern="MM/dd/yyyy" /></td>
           <td class="${class1}"><c:out value="${goodsreceipt.createdBy}"/></td>
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
      </table>
        <div id="buttons2" class="tabSelectText"></div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">
          <c:if test="${prevValue!=null && prevValue!=''}">
          
          <html-el:link styleClass="backNext_append" href="javascript:listfilter4('${CONTEXT_PATH}/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=${prevValue}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&orderType=${orderType}');">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
           </c:if>
            <c:if test="${nextValue!=null && nextValue!=''}">
            <html-el:link styleClass="backNext_append" href="javascript:listfilter4('${CONTEXT_PATH}/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=${nextValue}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&orderType=${orderType}');">
            <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
          
          </c:if>
          <tr>
          
        </tr>
        <tr>
          <td colspan="2"><label>
          <input type="hidden" name="checksArr"> 
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.GOODSREC_CODE)&& "F".equals(request.getParameter("order")))
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
	         <script language="javascript" >
             comapareCheckedValuesModified(document.goodsReceiptForm.idHidden.value,document.goodsReceiptForm.selectedEntry,'${checkBoxLength}',document.goodsReceiptForm.checkall);
             </script>
            
            <%-- <c:choose>
             	<c:when test="${orderPaper=='O'}">
             		<input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/goodsreceipt/millgoodsreceiptnew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&fo=paperfo&orderPaper=${orderPaper}',this)" value="New Goods Receipt">
             	</c:when>
             	<c:otherwise>
            		<input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptnew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this)" value="New Goods Receipt">
            	</c:otherwise>
            </c:choose>--%>
            <c:if test="${orderPaper!='O'}">
            	<input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptnew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderType=${orderType}',this)" value="New Goods Receipt">
            </c:if>
            
            
<%}%>       
           
            <%--<input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}')" value="Close">--%>
            <%		if("F".equals(request.getParameter("order"))){%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C')" value="Close">
		<%}else{%>
		
		  <c:choose>
               <c:when test="${roleType == 'M'}">
	             <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=M&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
	           </c:when>
               <c:otherwise> 
	          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
	         </c:otherwise>
		   </c:choose>	
		
          
		<%}%>   
            <c:if test="${errorMsg == null}">
            <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOsNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=goodReceipt')" value="Export to Excel">

            <input name="excel" type="button" class="buttonMain" onClick="submitGoodsAction2('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=goodReceiptAll',this)" value="Export All to Excel">
            </c:if>
            <input type="hidden" name="flag"/>
          </label></td>
        </tr>
        <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
      </table></td>
</tr>
        
       <%-- <tr>
        <td height="1" colspan="8" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
--%>        
     </html-el:form>
     </table></td>
  </tr>
