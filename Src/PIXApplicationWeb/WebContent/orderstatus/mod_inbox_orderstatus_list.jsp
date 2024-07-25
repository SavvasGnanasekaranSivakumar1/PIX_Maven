
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.presentation.orderstatus.action.OrderStatusForm,com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="home"><%=request.getParameter("home")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(orderStatusForm.orderStatusCollection)}' />


<html-el:form action="/orderStatus/orderStatusNew.do" >
<input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  ${purchaseordernumber} 
    <c:if test="${releaseno!=null && releaseno!='0'}">
    - ${releaseno}
    </c:if>
    </span></td>
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
          <tr>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg2" ><a href="<%=request.getContextPath()%>/purchaseorder/inboxorderdetail.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P&page=LH" class="tabSubBgLink">Details</a></td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg"><span class="tabSubBgLink">Job Status</span></td>
		  <%--  <a href="<%=request.getContextPath()%>/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=P&days=${days}&home=${home}&PAGE_VALUE=1" class="tabSubBgLink" >Status</a></td> --%>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="12" height="22"></td>
		    <td width="12" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="11" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&days=${days}&home=${home}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Delivery Message</a></td>
		    <td width="11" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="11" height="22"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/goodsreceipt/inboxgoodsreceiptlist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Goods Receipt</a> </td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
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
          <html-el:link styleClass="${class2}" page="/orderStatus/inboxorderStatusDetail.do?&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}&OS_ID=${orderStatus.statusId}&OS_NO=${orderStatus.statusNo}&PAGE_VALUE=${PAGE_VALUE}"><c:out value="${orderStatus.statusNo}"/></html-el:link>
          
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
          	<html-el:link styleClass="backNext_append" href="javascript:listfilter5('${CONTEXT_PATH}/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}&PAGE_VALUE=${prevValue}');">
          	<img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
    	 	<html-el:link styleClass="backNext_append" href="javascript:listfilter5('${CONTEXT_PATH}/orderStatus/inboxorderStatusList.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&order=${order}&days=${days}&home=${home}&PAGE_VALUE=${nextValue}');">
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
        <input type="hidden" name="poId" value="${purchaseorderid}">
        <input type="hidden" name="poVersion" value="${purchaseorderversion}">    
		 <script language="javascript" >
        comapareCheckedValuesModified(document.orderStatusForm.idHidden.value,document.orderStatusForm.selectedEntry,'${checkBoxLength}',document.orderStatusForm.checkall);
        </script>
		
		
		<%		if("F".equals(request.getParameter("order"))){%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=1&party=S&page=C')" value="Close">
		<%}else{%>
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
 
  


