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
<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="home"><%=request.getParameter("home")%></c:set>
<c:set var="partyType" value='<%=request.getParameter("partyType")%>' /> 
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(goodsReceiptForm.goodsreceiptCollection)}' />

<html-el:form action="/goodsreceipt/goodsreceiptlist"> 
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
<html-el:hidden property="poId" value="0"/>
<html-el:hidden property="poVersion" value="0"/>

<tr>
    <td height="25" align="left" valign="top">
<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
<span class="headingText">Purchase Order No. <c:out value="${purchaseordernumber}"/> 
<c:if test="${releaseno!=null && releaseno!='0'}">
    - ${releaseno}
</c:if>
</span></td>
  </tr>
   
    <%@ include file="/common/formbegin.jsp"%>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">To view the <span class="textBold">Goods Receipt </span> details, click on the Goods Receipt Number.</td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
			<tr>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>/purchaseorder/inboxorderdetail.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&partyM=${partyType}&days=${days}&home=${home}&rno=${releaseno}&order=P&page=LH" class="tabSubBgLink">Details</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    
			<c:if test="${partyType!='M'}">
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=P&days=${days}&home=${home}&PAGE_VALUE=1" class="tabSubBgLink" >Job Status</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    </c:if>
		    
		    <td width="12" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="11" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		     <a href="<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&partyType=${partyType}&rno=${releaseno}&order=P&days=${days}&home=${home}&PAGE_VALUE=1" class="tabSubBgLink" >Delivery Message</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    <td width="12" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="11" height="22"></td>
		    <td width="120" align="center" class="tabSubBg">
		    <span class="tabSubBgLink">Goods Receipt</span></td>
		    <%--  <a href="<%=request.getContextPath()%>/goodsreceipt/inboxgoodsreceiptlist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Goods Receipt</a> </td> --%>
		    <td width="12" height="22" align="left" valign="bottom"><img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="12" height="22"></td>
		</tr>   
			
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
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.goodsReceiptForm.idHidden,document.goodsReceiptForm.selectedEntry);">
          </td>

          <td class="tableHeading">GOODS RECEIPT NO. </td>
          <td class="tableHeading">DELIVERY MESSAGE  NO. </td>
          <td class="tableHeading">GOODS ARRIVAL DATE </td>
          <td class="tableHeading">COMPONENT VENDOR </td>
          <td class="tableHeading">SHIP TO VENDOR </td>
          <td class="tableHeading">RECEIPT POSTED DATE </td>
          <td class="tableHeading">RECEIPT POSTED BY </td>
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
          
	          <td align="center" class="${class1}"><label>
	          
	         <c:choose>
               <c:when test="${checkBoxLength == 1}"> 
	             <html-el:checkbox  property="selectedEntry" value="${goodsreceipt.receiptId}" onclick="javascript:addDeleteIdsFromSet(document.goodsReceiptForm.idHidden,'${goodsreceipt.receiptId}',document.goodsReceiptForm.selectedEntry);"/>
	           </c:when>
               <c:otherwise> 
	             <html-el:checkbox  property="selectedEntry" value="${goodsreceipt.receiptId}" onclick="javascript:addDeleteIdsFromSet(document.goodsReceiptForm.idHidden,'${goodsreceipt.receiptId}',document.goodsReceiptForm.selectedEntry[${indexId.index}]);"/>
	           </c:otherwise>
		    </c:choose>
		       
	          </label></td>

             
         
          <c:set var="count" value="${indexId.count}"/>       
           <td class="${class1}"><html-el:link styleClass="${class2}" page="/goodsreceipt/inboxgoodsreceiptdetial.do?RECEIPT_ID=${goodsreceipt.receiptId}&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&partyType=${partyType}&rno=${releaseno}&order=${order}&days=${days}&home=${home}&PAGE_VALUE=${PAGE_VALUE}"><c:out value="${goodsreceipt.receiptNo}"/></html-el:link>
           <td class="${class1}"> <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/goodsreceipt/deliverymessagedetail.do?MSG_ID=${goodsreceipt.msgId}&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&days=${days}&home=${home}&order=${order}','DeliveryMessageDetail','scrollbars=yes,width=750,height=500')" class="${class2}"><c:out value="${goodsreceipt.msgNo}"/></a> 
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
          <html-el:link styleClass="backNext_append" href="javascript:listfilter4('${CONTEXT_PATH}/goodsreceipt/inboxgoodsreceiptlist.do?PAGE_VALUE=${prevValue}&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}');">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
           </c:if>
            <c:if test="${nextValue!=null && nextValue!=''}">
            <html-el:link styleClass="backNext_append" href="javascript:listfilter4('${CONTEXT_PATH}/goodsreceipt/inboxgoodsreceiptlist.do?PAGE_VALUE=${nextValue}&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}');">
            <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
          <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" width="16" height="6">
          </c:if>
          <tr>
          
        </tr>
        <tr>
          <td colspan="2"><label>
          <input type="hidden" name="checksArr"> 
          
           <script language="javascript" >
             comapareCheckedValuesModified(document.goodsReceiptForm.idHidden.value,document.goodsReceiptForm.selectedEntry,'${checkBoxLength}',document.goodsReceiptForm.checkall);
             </script>
             
            <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
