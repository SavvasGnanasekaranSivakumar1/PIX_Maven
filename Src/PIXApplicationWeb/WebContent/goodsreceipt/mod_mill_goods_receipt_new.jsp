<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.presentation.goodsreceipt.action.GoodsReceiptForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
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

<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>
<c:set var="MSG_ID" value='<%=request.getParameter("MSG_ID")%>'/>


 <html-el:form action="/goodsreceipt/insertnewgoodsreceipt"> 
                 <%@ include file="/common/formbegin.jsp"%>
                
<c:if test="${goodsReceiptForm.goodsreceipt.partyCollection != null && not empty goodsReceiptForm.goodsreceipt.partyCollection}">	
 <c:set var="readOnlyForm" value="F"/>
<c:forEach var="party" items="${goodsReceiptForm.goodsreceipt.partyCollection}" varStatus="partyCount">
	<c:choose>
		    <c:when test="${party.partyType=='M'}">
			<c:set var="vendor" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${vendor.contactCollection}">
					<c:set var="contactVendor" value="${contact}"/>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='S'}">
			<c:set var="shipTo" value="${party}"/>
			<c:forEach var="contact" items="${shipTo.contactCollection}">
				<c:set var="contactShipTo" value="${contact}"/>
			</c:forEach>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</c:forEach>
</c:if>
<c:set var="totalLineItems"/>
<c:forEach var="lineItem" items="${goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>                 
 
 
 
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. <c:out value="${goodsReceiptForm.goodsreceipt.poNo}"/>
    <c:if test="${goodsReceiptForm.goodsreceipt.releaseNo!=null && goodsReceiptForm.goodsreceipt.releaseNo!='0'}">
    - ${goodsReceiptForm.goodsreceipt.releaseNo}
    </c:if></span></td>
  </tr>
  
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     	
      <tr>
        <td height="25" colspan="2" class="text">Following are the details of the <span class="textBold"> New Goods Receipt </span>. To go back to the Furnished Order listing, click on the <span class="textBlue">close</span> button.</td>
      </tr>
      <td>
        <div id="error_div" class="errorMessageText"></div>
      </td>
      <c:set var="successMsg" value='<%=request.getAttribute("success")%>' />	
      <tr> 
       <td ><h4><font color="blue" ><c:out value="${successMsg}"/></font></h4></td>   
       </tr>
      <tr>
        <td height="10" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
        <c:set var="pageModule" value="goodsreceipt" scope="request"></c:set>
        <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
        <%@ include file="/common/mod_tabs.jsp"%> 
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
    <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="5" colspan="3" valign="bottom" class="headingMain">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" colspan="3" valign="bottom" class="headingMain"><table width="100%" border="0" cellspacing="1" cellpadding="0">
         
		 
              <tr>
                <%--<html-el:form action="/goodsreceipt/insertnewgoodsreceipt"> 
                 <%@ include file="/common/formbegin.jsp"%>--%>
                <%--<td width="20%" class="blueColumn">DELIVERY MESSAGE NO.: </td>
                <td width="30%" class="lightblueColumn">
                <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/goodsreceipt/deliverymessagedetail.do?MSG_ID=${goodsReceiptForm.goodsreceipt.msgId}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}','DeliveryMessageDetail','scrollbars=yes,width=750,height=500')" class="tableAlternateRowlink"><c:out value="${goodsReceiptForm.goodsreceipt.msgNo}"/></a> --%>
              
                <td width="20%" class="blueColumn">GOODS CONDITION: </td>
                <td width="30%" class="lightblueColumn">
                <html-el:select property="goodsreceipt.headerAcceptanceDescription" styleClass="textfield" onchange="changeLineStatus(${totalLineItems},document.goodsReceiptForm.elements['goodsreceipt.headerAcceptanceDescription'])">
                <html-el:option value="">Select</html-el:option>
                <c:forEach var="item" items="${GoodsCondition}" varStatus="indexId">
                <html-el:option value="${item.msg_type_id}"><c:out value="${item.msg_type}"/></html-el:option>
                </c:forEach>
             </html-el:select>
              
              </tr>
          </table></td>
        </tr>
        <tr>
       <td width="47%" valign="top">
          <fieldset class="legendBorder">
          <legend class="legendeTitle">MILL (${vendor.san})
          <c:if test="${vendor.orgUnitCode!=null}">(${vendor.orgUnitCode})</c:if> </legend>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
           
              <tr>
                <td height="5"></td>
              </tr>
             
              <tr>
                 <td class="headingText12"><c:out value="${vendor.name1}"/></td>
              </tr>
              <tr>
                <td class="textLegend"><c:out value="${vendor.address1}"/><br>
                  <c:out value="${vendor.city}"/>
                  <c:out value="${vendor.postalCode}"/>
                  <c:out value="${vendor.state}"/> USA  </td>
              </tr>
              <tr>
                <td height="5" class="textLegend"></td>
              </tr>
              <tr>
             
                <td class="textLegend"><span class="textLegendBold"> -</span><c:out value="${contactVendor.contactFirstName}"/><br>
                  <c:if test="${contactVendor.phone!=null}">&nbsp;<c:out value="${contactVendor.phone}"/>(Business)</c:if><br>
                  <c:if test="${contactVendor.mobile!=null}">&nbsp;<c:out value="${contactVendor.mobile}"/>(Mobile)</c:if><br>
                  <c:if test="${contactVendor.fax!=null}">&nbsp;<c:out value="${contactVendor.fax}"/>(Fax)</c:if><br>
                 <c:if test="${contactVendor.email!=null}">&nbsp;<c:out value="${contactVendor.email}"/></c:if><br></td>
                 
                  </tr>
                </table>
              </fieldset>
              
              </td>
          
         <td width="3%" valign="top">&nbsp;</td>
       <%--  <c:forEach var="party" items="${goodsReceiptForm.goodsreceipt.partyCollection}" varStatus="indexId">  --%>
         
          <td valign="top">
          <fieldset class="legendBorder">
          
            <legend class="legendeTitle">SHIP TO (${shipTo.san})
            <c:if test="${shipTo.orgUnitCode!=null}">(${shipTo.orgUnitCode})</c:if> </legend>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            
              <tr>
                <td height="5"></td>
              </tr>
              <tr>
                <td class="headingText12"><c:out value="${shipTo.name1}"/></td>
              </tr>
              <tr>
                <td class="textLegend"><c:out value="${shipTo.address1}"/><br>
                <c:out value="${shipTo.city}"/>
                <c:out value="${shipTo.postalCode}"/>
                <c:out value="${shipTo.state}"/> USA  </td>
              </tr>
              <tr>
                <td height="5" class="textLegend"></td>
              </tr>
              <tr>
                <td class="textLegend"><span class="textLegendBold"> -</span><c:out value="${contactShipTo.contactFirstName}"/><br> 
                  <c:if test="${contactShipTo.phone!=null}">&nbsp;<c:out value="${contactShipTo.phone}"/>(Business)</c:if><br>
                  <c:if test="${contactShipTo.mobile!=null}">&nbsp;<c:out value="${contactShipTo.mobile}"/>(Mobile)</c:if><br>
                  <c:if test="${contactShipTo.fax!=null}">&nbsp;<c:out value="${contactShipTo.fax}"/>(Fax)</c:if><br>
                 <c:if test="${contactShipTo.email!=null}">&nbsp;<c:out value="${contactShipTo.email}"/></c:if><br></td>
                </tr>
                </table>
              </fieldset></td>
              
				
				
             
         </tr>
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3">      
           
        
    </table>
    
    <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
       <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></font></h3></td>   
           </tr>
        </c:if>
      	
      	
      	<c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">PO LINE NO.</td>
          <td class="tableHeading">MATERIAL NUMBER </td>
          <td class="tableHeading">MATERIAL DESCRIPTION </td>
          <td class="tableHeading" width="8%">ORIGINAL QUANTITY</td>
          <td class="tableHeading">TOTAL RECEIVED</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">COMP. DELIVERY DATE</td>
          <td class="tableHeading">ACTUAL ARRIVAL DATE </td>
          <td class="tableHeading">TRANSPORT DAMAGE</td>
          <td class="tableHeading">GOODS CONDITION  </td>
        </tr>
       <tr>
      
        <c:set var="goodsreceiptDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="goodsreceiptLine" items="${goodsReceiptForm.goodsreceipt.goodsReceiptLineCollection}" varStatus="indexId">     
        <c:set var="lineNo" value="${goodsreceiptLine.lineNo}"/>
         <c:if test="${lineNo%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
        <tr>
        	<%-- ${goodsreceiptLine.maxToleranceVal}--%>
        	        	
        	<input type="hidden" name="maxAmtAllow" value="${goodsreceiptLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.lineNo}"/></td>
          <td valign="top" class="${class1}">
          <c:out value="${goodsreceiptLine.productCode}"/></td>
          <td valign="top" class="${class1}">
          <c:out value="${goodsreceiptLine.productDescription}"/></td>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.balanceQuantity}"/></td>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.cumulativeQuanReceived}"/><input type="hidden" name="cumulativeQuan" value="${goodsreceiptLine.cumulativeQuanReceived}" id="cumulativeQuantityRec${indexId.index}"/></td>
          <td valign="top" class="${class1}">
                  
          <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].receivedQuantity" size="10" styleClass= "textfield"  maxlength="9" onkeypress="return numbersonly()"/>
          <td valign="top" class="${class1}">
          <c:out value="${goodsreceiptLine.requestedDeliveryDate}"/></td>
          <td valign="top" class="${class1}">
                    
          <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].actualArrivalDate" size="12" styleClass= "textfield" styleId="line_del_date_${indexId.index}" maxlength="10" readonly="true"/>
          <a href="javascript:NewCal('line_del_date_${indexId.index}','MMDDYYYY')"> 
          <img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td> 
        
          <td valign="top" class="${class1}">
          <html-el:text property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].intransitDamagedQty" value="0" size="10" styleClass= "textfield" maxlength="9" onkeypress="return numbersonly()"/>
          <td valign="top" class="${class1}">
                           
          <html-el:select property="goodsreceipt.goodsReceiptLineCollection[${indexId.index}].acceptanceDescription" styleClass= "textfield">>
          <html-el:option value="">Select</html-el:option>
          <c:forEach var="item" items="${GoodsCondition}" varStatus="indexId">
          <html-el:option value="${item.msg_type_id}"><c:out value="${item.msg_type}"/></html-el:option>
          </c:forEach>
          </html-el:select>
           
         </td>
        </tr>
        </c:forEach>
         </c:if> 
         
         
         
         
      	
      	
        <tr>
          <td height="1" colspan="10" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      
     <div id="buttons2" class="tabSelectText"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
          <input name="Button1" type="button" class="buttonMain" onClick="if(validateMillGR(${totalLineItems})){submitAction('<%=request.getContextPath()%>/goodsreceipt/insertnewgoodsreceipt.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}&MSG_ID=${MSG_ID}&fo=paperfo&orderPaper=${orderPaper}',this)}else{return false;}" value="Submit">  
          
<%}%>           
          <%--  <input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/goodsreceipt/insertnewgoodsreceipt.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this)" value="Submit"> --%>
          <%-- <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptlist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=1')" value="Cancel"> --%>
          
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C')" value="Close">
          </td>
       </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
     </table> 
      
      </html-el:form>
