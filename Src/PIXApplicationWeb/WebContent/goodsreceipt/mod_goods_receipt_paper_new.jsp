<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.presentation.goodsreceipt.action.GoodsReceiptForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="page_order_list" value='<%=request.getParameter("page_order_list")%>' /> 
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />
<c:set var="pageFilter" value='<%=request.getParameter("pageFilter")%>' /> 
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' /> 
<c:set var="isbnFilter" value='<%=request.getParameter("isbnFilter")%>' /> 
<c:set var="printNoFilter" value='<%=request.getParameter("printNoFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}'/>

<c:set var="MSG_ID" value='<%=request.getParameter("MSG_ID")%>'/>
<%--<script>
	alert('poversion');
	alert('${poversion}');
</script>
 checking access rights --%>
<c:forEach var="userPriv" items="${USER_INFO.privilegeCollection}" varStatus="i">
<c:if test= "${userPriv.moduleDetail.refCode == 'GRE'}">
<c:set var="accessFlag" value="${userPriv.accessFlag}"/>
</c:if>
</c:forEach>
<c:set var="viewOnly" value = "0" />
<c:if test="${accessFlag == 'R' || accessFlag == 'N'}">
<c:set var="viewOnly" value = "1" />
</c:if>

<c:set var="rtFlag" value = "0" />
<c:set var="chrtFlag" value = "0" />

<%-- <c:set var="grRoleTracking" value ='<%=session.getAttribute("grRoleTrackingFlag")%>' />   zero value implies roletracking is disabled i.e. pix user and textboxes are enabled --%>

 <html-el:form action="/goodsreceipt/goodsreceiptpapernew" onsubmit="return false"> 
  <%@ include file="/common/formbegin.jsp"%>
<%--  <html-el:hidden property=""/>
  <html-el:hidden property=""/>
 --%>  
<c:if test="${goodsReceiptForm.goodsreceipt.partyCollection != null && not empty goodsReceiptForm.goodsreceipt.partyCollection}">	
 <c:set var="readOnlyForm" value="F"/>

</c:if>
<c:set var="totalLineItems"/>
<c:forEach var="lineItem" items="${goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>                 

 <tr>
<td height="25" align="left" valign="top">
<input type="hidden" name="totalLineItems2" id="totalLineItems2" value="${totalLineItems}"/>
<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
<span class="headingText">Purchase Order No.</span>&nbsp;
<input type="text" name="poNumber" id="poNumber" maxlength="10" class="textboxtext" onKeyPress="return submitEnter('<%=request.getContextPath()%>','<%=request.getContextPath()%>/goodsreceipt/goodsreceiptpapernew.do?PAGE_VALUE=1&orderPaper=O&paperlist=paperlist&page_order_list=1<c:if test="${roleType == 'B'}">&orderType=O</c:if>','<%=request.getContextPath()%>/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&page_order_list=1',event)" value="${pono}"/>
&nbsp;
<input name="searchTop" id="searchTop" type="button" size="10" maxlength="12" class="buttonMain" onClick="submitSearch('<%=request.getContextPath()%>','<%=request.getContextPath()%>/goodsreceipt/goodsreceiptpapernew.do?PAGE_VALUE=1&orderPaper=O&paperlist=paperlist&page_order_list=1<c:if test="${roleType == 'B'}">&orderType=O</c:if>','<%=request.getContextPath()%>/goodsreceipt/goodsreceiptlist.do?PAGE_VALUE=1&page_order_list=1')" value="Search">
<input type="hidden" name="poid" id="poid" value="${poid}"/>
<input type="hidden" name="pover" id="pover" value = "${poversion}"/>
<input type="hidden" name="porel" id="porel" value ="${rno}"/>
<input type="hidden" name="poorderType" id="poorderType"/>
</td>

</tr>
  
<tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25" colspan="2" class="text">To view the <span class="textBold"> Goods Receipt History </span>details, click on the Delivery Message No. details.</td>
      </tr>
      <td>
        <div id="error_div" class="errorMessageTextGR"></div>
      </td>
      <%--
      <c:set var="successMsg" value='<%=request.getAttribute("success")%>' />	
      <tr> 
       <td ><h4><font color="blue" ><c:out value="${successMsg}"/></font></h4></td>   
       </tr>
       --%>
      <tr>
        <td height="10" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
        <c:set var="pageModule" value="goodsreceipt" scope="request"></c:set>
        <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
        <c:set var="order" value="${order}" scope="request"></c:set>
        <%@ include file="/common/mod_tabs.jsp"%> 
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
       </tr>
    </table>
   
    
    <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
       <c:if test="${errorMsg != null}">
           <tr> 
           <td align="middle" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
        </c:if>
      	
      	
      	<c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">PO LINE#</td>
          <td class="tableHeading">MILL/ MERCHANT </td>
          <td class="tableHeading" width="11%">PRINTER </td>
          <td class="tableHeading">DELIVERY MESSAGE# </td>
          <td class="tableHeading">L4 MATERIAL DESC</td>
          <td class="tableHeading">DELIVERED DATE</td>
          <td class="tableHeading" width="70px">REQUESTED QTY</td>
          <td class="tableHeading" width="70px">DELIVERED QTY</td>
          <td class="tableHeading" width="70px">RECEIVED QTY</td>
          <td class="tableHeading">GR TO BE POSTED</td>
          
          <c:if test="${roleType != 'M' && roleType != 'B' && viewOnly != '1'}">
          			
		<%-- 	<c:choose>
				<c:when test = "${grRoleTracking != 1}"> --%>	    
	          		<td class="tableHeading">ENTER RECEIVED QTY</td>
          </c:if>
        </tr>
       <tr>
      
        
        <%--<c:set var="class1" value="tableRow"/>--%>
        <c:forEach var="goodsreceiptLine" items="${goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection}" varStatus="indexId">  		
			
	     <c:if test="${chrtFlag == 0}" >
	     <c:if test="${goodsreceiptLine.roleTracking == 0}" >
	     	<c:set var="chrtFlag" value = "1" />
	     		<c:set var="rtFlag" value = "1" />
	     </c:if>
	     </c:if>		
<c:set var="chrtFlag" value = "0" />
			
			<c:set var="productCode" value="${goodsreceiptLine.productCode}"></c:set>
			
			<c:set var="colorFlag" value="${goodsreceiptLine.colorFlag}"/>			
			<c:if test="${colorFlag == 0}" >			
			<c:set var="class1" value="tableHighRow"/>			
			</c:if>			
			<c:if test="${colorFlag == 1}" >			
			<c:set var="class1" value="tableMediumRow"/>			
			</c:if>			
			<c:if test="${colorFlag == 2}" >			
			<c:set var="class1" value="tableLowRow"/>			
			</c:if>        
          
       <c:set var="lineNo" value="${goodsreceiptLine.lineNo}"/>
        	<input type="hidden" name="maxAmtAllow" value="${goodsreceiptLine.postedQuantity}" id="maxAmtAllow${indexId.index}"/>
        	<input type="hidden" name="poLineNo" value="${goodsreceiptLine.poLineNo}" id="poLineNo${indexId.index}"/>        	
          <td valign="top" class="${class1}" align="center">
          	<c:out value="${goodsreceiptLine.poLineNo}"/></td>
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.millName}"/></td> 
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.printer}"/></td>	         
          <td valign="top" class="${class1}">
          			
          <a href=# class="tableRowLink noBackground" onclick="validatePage('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId=${goodsreceiptLine.msgId}&poid=${poid}&pono=${pono}&rno=${rno}&poversion=${poversion}&PAGE_VALUE=1&page_order_list=1&lineItem=${lineItem}&orderPaper=${orderPaper}&actionKey=HIST&order=${order}&grRoleTracking=${goodsreceiptLine.roleTracking}')"><c:out value="${goodsreceiptLine.msgNo}"/> </a>
          <%--	 <html-el:link styleClass="${class2}" page="/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId=${goodsreceiptLine.msgId}&poid=${poid}&pono=${pono}&rno=${rno}&poversion=${poversion}&PAGE_VALUE=1&page_order_list=1&lineItem=${lineItem}&orderPaper=${orderPaper}&actionKey=HIST&order=${order}"><c:out value="${goodsreceiptLine.msgNo}"/></html-el:link> '${goodsreceiptLine.roleTracking}' --%>
          </td>
          <td valign="top" class="${class1}" width="300" title="${goodsreceiptLine.materialTooltip}">
          	<c:out value="${goodsreceiptLine.materialDesc}"/></td>
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.actualArrivalDate}"/></td>
         <%-- <td valign="top" class="${class1}" align="right">
          	<c:out value="${goodsreceiptLine.requestedQuantity}"/></td>--%>
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.requestedQuantity}" pattern="###,###"/></td>	          
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.deliveredQuantity}" pattern="###,###"/></td>

			<c:if test= "${goodsreceiptLine.cumulativeQuanReceived > 0 && goodsreceiptLine.roleTracking > 0}">
	<%-- 		<script>
				alert('${pono}');
			</script>  --%>
				<td valign="top" class="${class1}" align="right">
          <a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','path','${pono}','${poid}','${poversion}','${goodsreceiptLine.msgNo}','${goodsreceiptLine.roleTracking}');" >
          <fmt:formatNumber value="${goodsreceiptLine.cumulativeQuanReceived}" pattern="###,###"/></a></td>
			</c:if>

			<c:if test= "${goodsreceiptLine.cumulativeQuanReceived > 0 && goodsreceiptLine.roleTracking == 0}">
				<td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.cumulativeQuanReceived}" pattern="###,###"/></td>
			</c:if>
			
	
			<c:if test= "${goodsreceiptLine.cumulativeQuanReceived == 0}">
				<td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.cumulativeQuanReceived}" pattern="###,###"/></td>
          </c:if>
          	
  <%--        ${goodsreceiptLine.msgNo}
  <td valign="top" class="${class1}" align="left">
          <a href="#" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','path','${productCode}','${poid}','${poversion}');" >
          <img src="<%=request.getContextPath()%>/images/search_icon.gif" width="16" height="9" border="0"></a>
          	<fmt:formatNumber value="${goodsreceiptLine.cumulativeQuanReceived}" pattern="###,###"/></td>	
           --%>
          
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.postedQuantity}" pattern="###,###"/></td>	       	          
          <c:if test= "${roleType != 'M' && roleType != 'B' && viewOnly != '1'}">
	       <td valign="top" class="${class1}" align="right">
	     
	       <c:choose>	
			  	<c:when test = "${goodsreceiptLine.roleTracking == 0}"> <%-- applies only for V --%>  
	     		          	
			          	<c:choose> 
			       	<c:when test="${colorFlag == 2}">
			       		<html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" value="" size="10" styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none; visibility:hidden;" readonly="true"/>   
			        </c:when>
			          <c:otherwise>
				          <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" value="" size="10" styleClass= "textfield" maxlength="9" onkeypress="return numbersonly()"/>            
			          </c:otherwise>
		       			</c:choose>
	         	</c:when>	<%-- end roletracking when --%> 
	          	
	          	<c:otherwise>
	          			       
					      <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" value="" size="10" styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none; visibility:hidden;" readonly="true"/>
	          	</c:otherwise>
        </c:choose>
	       
	<%-- 
	<c:choose> 
			       	<c:when test="${colorFlag == 2}">
			       		<html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" value="" size="10" styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none; visibility:hidden;" readonly="true"/>   
			        </c:when>
			          <c:otherwise>
				          <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" value="" size="10" styleClass= "textfield" maxlength="9" onkeypress="return numbersonly()"/>            
			          </c:otherwise>
		       </c:choose>
	--%> 
	       </td>   
	       </c:if>      
     
        </tr>
        </c:forEach>
         </c:if>      
      	
      	
        <tr>
          <td height="1" colspan="11" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
     <div id="buttons2" class="tabSelectText"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="10" colspan="2"></td>
        </tr>
      <tr>
          <td height="15" align="left">
						<span class="tablelegendHigh">&nbsp;</span>
						<label class="legendLbl">Less than 90% posted</label>
						<span class="tablelegendMedium">&nbsp;</span>
						<label class="legendLbl">90-99% posted</label>
						<span class="tablelegendLow">&nbsp;</span>
						<label class="legendLbl">100% posted</label>
			</td>		
        </tr>
        
        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td id="hideButtons" class="tabSelectTextleft">
          <input type="hidden" name="request_time" value="<%=System.currentTimeMillis()%>"/>
          
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.GOODSREC_CODE)&& "F".equals(request.getParameter("order")))
{
%>  
		  <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
          <input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
          <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
          <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
          <input type="hidden" name="statusFilter" value="${statusFilter}"/>
          <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
          <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>

          
<%}%>        
<c:set var="size" value='${fn:length(goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection)}' />

<%--           <c:if test="${(errorMsg == null && ((fn:length(goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection)) > 0))&& roleType != 'M' && roleType != 'B' && goodsreceiptLine.roleTracking == 0}">  <%--  && goodsreceiptLine.roleTracking == 0  viewOnly != '1'  --%>             
            <c:if test="${(errorMsg == null && ((fn:length(goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection)) > 0))&& roleType != 'M' && roleType != 'B' && rtFlag == 1}">
            <input name="Button1" type="button" class="buttonMain" onClick="if(validateNewPaperGR(${totalLineItems})){submitAction('<%=request.getContextPath()%>/goodsreceipt/insertnewgoodsreceipt.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&MSG_ID=${MSG_ID}&fo=paperfo&orderPaper=${orderPaper}',this)}else{return false;}" value="Save">             
            </c:if>
       <c:if test="${errorMsg == null}">
            <input name="excel" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=goodReceiptPaperNew&poid=${poid}&poversion=${poversion}')" value="Export to Excel">
           
            </c:if>
          </td>
       </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
     </table> 
      
      </html-el:form>
