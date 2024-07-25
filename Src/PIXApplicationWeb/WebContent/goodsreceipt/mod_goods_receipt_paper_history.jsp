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
<c:set var="actionKey" value='<%=request.getParameter("actionKey")%>' />
<c:set var="actionKeyVal" value='HIST' />
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>
<c:set var="msgId" value='<%=request.getParameter("msgId")%>'/>
<c:set var="grRoleTracking" value='<%=request.getParameter("grRoleTracking")%>'/>
<script>
</script>
<%-- checking access rights --%>
<c:forEach var="userPriv" items="${USER_INFO.privilegeCollection}" varStatus="i">
<c:if test= "${userPriv.moduleDetail.refCode == 'GRE'}">
<c:set var="accessFlag" value="${userPriv.accessFlag}"/>
</c:if>
</c:forEach>
<c:set var="viewOnly" value = "0" />
<c:if test="${accessFlag == 'R' || accessFlag == 'N'}">
<c:set var="viewOnly" value = "1" />
</c:if>

<html-el:form action="/goodsreceipt/insertnewgoodsreceipt"> 
<%@ include file="/common/formbegin.jsp"%>
                
<c:if test="${goodsReceiptForm.goodsreceipt.partyCollection != null && not empty goodsReceiptForm.goodsreceipt.partyCollection}">	
 <c:set var="readOnlyForm" value="F"/>
</c:if>

<%--  
<script>
	alert('${grRoleTracking}');
	alert('${roleType}');
</script>
--%>

<c:set var="totalLineItemsOuter"/>
<c:forEach var="lineItemOuter" items="${goodsReceiptForm.goodsreceiptCollection}" varStatus="lineInfoOuter">
	<c:set var="totalLineItemsOuter" value="${lineInfoOuter.count}"/>	
</c:forEach> 
<c:set var="hideSave"/>
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. ${pono}
    - ${poversion}
   
    </span></td>
</tr> 
  
  <tr>
    <td align="left" valign="top" class="padding23">
    <input type="hidden" name="totalLineItems2" id="totalLineItems2" value="${totalLineItemsOuter}"/>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     	
      <td>
        <div id="error_div" class="errorMessageTextGR"></div>
      </td>
      <c:set var="successMsg" value='<%=request.getAttribute("success")%>' />	
      <tr> 
       <td><h4><font color="blue" ><c:out value="${successMsg}"/></font></h4></td>   
       </tr>
      <tr>
        <td height="10" colspan="2"></td>
        </tr>
       
        <tr>
       	 <td width="50%" height="22"><table border="0" cellspacing="0" cellpadding="0">
       <tr>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_lt_green.gif" width="10" height="25"></td>
            <td width="180" align="center" valign="middle" class="tabBg">Goods Receipt History </td>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_rt_green.gif" width="10" height="25"></td>
           </tr> </table></td>
           <c:if test="${actionKey=='HIST'}"> 
            <td align="right" valign="bottom">
            <%--<html-el:link styleClass="subLinksMain" page="/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId=${msgId}&poid=${poid}&pono=${pono}&rno=${rno}&poversion=${poversion}&PAGE_VALUE=1&lineItem=${lineItem}&orderPaper=${orderPaper}&page_order_list=1&order=${order}&actionKey=LIST">
            List All Line Items</html-el:link>&nbsp;--%>
            <A href=# class="subLinksMain" onclick="submitCancelReceipt1('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptpaperhistory.do?paperlist=paperGRHistory&msgId=${msgId}&poid=${poid}&pono=${pono}&rno=${rno}&poversion=${poversion}&PAGE_VALUE=1&lineItem=${lineItem}&orderPaper=${orderPaper}&page_order_list=1&order=${order}&actionKey=LIST&grRoleTracking=${grRoleTracking}')">List All Line Items</A>
            </td>
            </c:if>
            <tr>
        <td height="4" colspan="2"></td>
        </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
       </tr>
    </table>
   
    
    <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
       <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
        </c:if>  	
      	
      	<c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">PO LINE#</td>
          <td class="tableHeading">MILL/ MERCHANT </td>
          <td class="tableHeading">PRINTER</td>
          <td class="tableHeading">GOODS RECEIPT# </td>
          <td class="tableHeading">L4 MATERIAL DESC</td>
          <td class="tableHeading">DELIVERED DATE</td>
          <td class="tableHeading" width="60px">REQ PO LINE QTY</td>
          <td class="tableHeading" width="60px">DEL PO LINE QTY</td>
          <td class="tableHeading" width="60px">TOTAL REC QTY</td>
          <td class="tableHeading" width="60px">DEL QTY</td>
          <td class="tableHeading">DEL BY</td>
          <%-- <td class="tableHeading">APPR FLAG</td> --%>
          <td class="tableHeading">RECEIVED QTY</td>
          <%--<td class="tableHeading" width="12px">REC BY</td> --%>
          <td class="tableHeading">RECEIVED DATE</td>
        </tr>
      
      <c:forEach var="goodsreceipt" items="${goodsReceiptForm.goodsreceiptCollection}" varStatus="indexId"> 
      <c:set var="lineNo1" value="${indexId.index}"/>
   		<%--<c:forEach var="goodsreceiptLine" items="${goodsreceipt.goodsReceiptLineCollection}" varStatus="indexId1">--%>
   		<c:set var="goodsreceiptLine" value="${goodsreceipt.goodsReceiptLineCollection[0]}"/>
         <c:if test="${lineNo1%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo1%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
       
          <tr>  
            
          <input type="hidden" name="maxAmtAllow" value="${goodsreceiptLine.postedQuantity}" id="maxAmtAllow${indexId.index}"/> 
          <input type="hidden" name="textBoxQty" value="${goodsreceiptLine.defaultQuantity}" id="textBoxQty${indexId.index}"/>        	       	
          <input type="hidden" name="zeroGR" value="${goodsreceipt.receiptNo}" id="zeroGR${indexId.index}"/>
          <input type="hidden" name="DMNo" value="${goodsreceiptLine.msgNo}" id="DMNo${indexId.index}"/>
          <input type="hidden" name="poLineNo" value="${goodsreceiptLine.poLineNo}" id="poLineNo${indexId.index}"/>
          

          <c:if test="${goodsreceiptLine.approvalFlag == 'No'&& goodsreceiptLine.defaultQuantity != 0}">
          	<c:if test="${hideSave !='No'}">
          		<c:set var="hideSave" value="No"/>
          	</c:if>
          </c:if>	
          <td valign="top" class="${class1}" align="left">
          	&nbsp; <c:out value="${goodsreceiptLine.poLineNo}"/><c:if test="${goodsreceiptLine.approvalFlag == 'Yes'}"><IMG src="<%=request.getContextPath()%>/images/tick-rfs1.gif" height="12px" width="12px"/></c:if><c:if test="${goodsreceiptLine.defaultQuantity == 0}">&nbsp;<IMG src="<%=request.getContextPath()%>/images/delete_rfs.gif"/></c:if></td>
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.millName}"/></td> 
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.printer}"/></td>	          	
          
          <td valign="top" class="${class1}" title="${goodsreceiptLine.msgNo}">          
          	<c:out value="${goodsreceipt.receiptNo}"/></td>
          	
          <td valign="top" class="${class1}" width="300" title="${goodsreceiptLine.materialTooltip}">
          	<c:out value="${goodsreceiptLine.materialDesc}"/></td>
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.actualArrivalDate}"/></td>
          	          
          <%--<td valign="top" class="${class1}" align="right">
          	<c:out value="${goodsreceiptLine.deliveredQuantity}"/></td>--%>
          	
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.requestedQuantity}" pattern="###,###"/></td>
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.deliveredQuantity}" pattern="###,###"/></td>
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.cumulativeQuanReceived}" pattern="###,###"/></td>
          <td valign="top" class="${class1}" align="right">
          	<fmt:formatNumber value="${goodsreceiptLine.postedQuantity}" pattern="###,###"/></td>
                	          
          
          <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.deliveredBy}"/></td>
          	<%-- <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.approvalFlag}"/></td>--%>
          	
           <c:set var="approvalFlag" value="${goodsreceiptLine.approvalFlag}"/>
	                
	       <%--         <script>
	          		alert('${approvalFlag}');
	          	</script>  --%>
	                   
          <td valign="top" class="${class1}" align="right"> 
	          <c:choose> 
				<c:when test="${goodsreceiptLine.approvalFlag == 'Yes' || (roleType != 'V')|| goodsreceiptLine.defaultQuantity == 0 || viewOnly == '1'}"> 
	                
				    <c:if test= "${grRoleTracking == 0}">
	          		<html-el:text property="goodsreceiptCollection[${indexId.index}].goodsReceiptLineCollection[0].receivedQuantity" value="${goodsreceiptLine.defaultQuantity}" size="10"  styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none;visibility:hidden;" readonly="true" /><fmt:formatNumber value="${goodsreceiptLine.defaultQuantity}" pattern="###,###"/>
	          		</c:if>
	          		<c:if test= "${grRoleTracking > 0}">
	          		<a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','path','${pono}','${poid}','${poversion}','${goodsreceipt.receiptNo}','1');" >
		          <fmt:formatNumber value="${goodsreceiptLine.defaultQuantity}" pattern="###,###"/></a>
	          		
	          		</c:if>
	          		<%--<c:out value="${goodsreceiptLine.defaultQuantity}"/>--%>
	          	</c:when> 
	          	<c:otherwise>
	          	<c:if test= "${grRoleTracking == 0}">    
			        <html-el:text property="goodsreceiptCollection[${indexId.index}].goodsReceiptLineCollection[0].receivedQuantity" value="${goodsreceiptLine.defaultQuantity}" size="10" styleClass= "textfield" maxlength="9" onkeypress="return numbersonly()"/> 
	          	</c:if>
	          	<c:if test= "${grRoleTracking > 0}">
	   <%--       	<script>
	          		alert('${goodsreceiptLine.msgNo}');
	          		alert('${goodsreceipt.receiptNo}');
	          	</script>    --%>
			        <a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','path','${pono}','${poid}','${poversion}','${goodsreceipt.receiptNo}','1');" >
		          <fmt:formatNumber value="${goodsreceiptLine.defaultQuantity}" pattern="###,###"/></a>
<%-- 			        <html-el:text property="goodsreceiptCollection[${indexId.index}].goodsReceiptLineCollection[0].receivedQuantity" value="${goodsreceiptLine.defaultQuantity}" size="10"  styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none;visibility:hidden;" readonly="true" /><fmt:formatNumber value="${goodsreceiptLine.defaultQuantity}" pattern="###,###"/> 
--%>	          	</c:if>
	          	</c:otherwise>
	          </c:choose>
          </td>
           <td valign="top" class="${class1}">
          	<c:out value="${goodsreceiptLine.grRecDate}"/></td>         
     
        </tr>
        <%--</c:forEach>--%>
        </c:forEach>
         </c:if>      
      	
      	
        <tr>
          <td height="1" colspan="14" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
     <div id="buttons2" class="tabSelectText"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="10" colspan="2"></td>
        </tr>
        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td id="hideButtons" class="tabSelectTextleft">
          
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
        <tr>
           <td id="buttons3" class="tabSelectTextleft">
    		<c:if test="${errorMsg == null && ((fn:length(goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection)) > 0)&& roleType != 'M' && roleType != 'B'&& hideSave =='No' && viewOnly != '1'}">                      																			
    				<c:if test= "${grRoleTracking == 0}">
    					<input name="Button1" type="button" class="buttonMain" onClick="if(validatePaperGRHistory(${totalLineItemsOuter})){submitAjaxAction('<%=request.getContextPath()%>/goodsreceipt/goodsreceipthistoryupdate.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&fo=paperfo&orderPaper=${orderPaper}&paperlist=paperGRHistory&actionKey=${actionKey}&msgId=${msgId}',this)}else{return false;}" value="Save"> 															
    			  	</c:if>
    			  <!--<input name="Button1" type="button" class="buttonMain" onClick="submitAjaxAction('<%=request.getContextPath()%>/goodsreceipt/goodsreceipthistoryupdate.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&fo=paperfo&orderPaper=${orderPaper}&paperlist=paperGRHistory&actionKey=${actionKey}&msgId=${msgId}')" value="Save">--> 
    		</c:if> 
     		<input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt1('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptpapernew.do?paperlist=paperlist&lineItem=${lineItem}&orderPaper=${orderPaper}&page_order_list=${page_order_list}&poversion=${poversion}&pono=${pono}&poid=${poid}&rno=${rno}&fo=paperfo&PAGE_VALUE=1&orderPaper=${orderPaper}&order=${order}')" value="Close">
          	<c:if test="${errorMsg == null}">
            <input name="excel" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=goodReceiptPaperHistory&poid=${poid}&poversion=${poversion}')" value="Export to Excel">           
            </c:if>
          </td>
       </tr>
           <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
       <tr>
       <td colspan="2" class="text">Indicators shown along with status field: </td>
       </tr>
       <tr>
       <td colspan="2" > 
        			 <IMG src="<%=request.getContextPath()%>/images/tick-rfs1.gif" height = "12px" width="12px"/><font class="text" style="color:green;"> - Approved Goods Receipt. </font> <br>
        			 <IMG src="<%=request.getContextPath()%>/images/delete_rfs.gif"/><font class="text" style="color:red;"> - Cancelled Goods Receipt.</font> <br>
        </td>
         </tr>
     </table> 
      
      </html-el:form>
