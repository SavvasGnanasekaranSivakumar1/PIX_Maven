
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ page import="com.pearson.pix.presentation.orderstatus.action.OrderStatusForm,com.pearson.pix.business.common.PIXConstants,java.util.Vector,com.pearson.pix.dto.orderstatus.OrderStatus"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
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


   <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  ${pono}
    <c:if test="${rno!=null && rno!='0'}">
    - ${rno}
    </c:if></span></td>
  </tr>
  <c:forEach var="lineItem" items="${orderStatusForm.orderStatus.lineItemCollection}" varStatus="orderLine">
	<c:set var="totalLineItems" value="${orderLine.count}"/>
  </c:forEach>
   <tr>
     <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td height="15" colspan="2" class="text">Fill in the following details to generate a ${PAGE_VALUE}<span class="textBold">New Status</span>. </td>
    </tr>
      <td height="15" colspan="2">
        <div id="error_div" class="errorMessageText"></div>
      </td>
     <tr>
        <td width="80%" height="22">
        <table height="22" border="0" cellpadding="0" cellspacing="0">
          <c:set var="pageModule" value="orderstatus" scope="request"></c:set>
          <%@ include file="/common/mod_tabs.jsp"%>
        </table>
        </td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
      </tr>
  </table>
  <table width="98%" border="0" cellspacing="1" cellpadding="0">
  <html-el:form action="/orderStatus/orderStatusNew">
  <%@ include file="/common/formbegin.jsp"%>
      <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
		<c:if test="${errorMsg == null}"> 
		<tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td width="10%" class="tableHeading">COMPONENT  </td>
          <td width="12%" class="tableHeading">REQUESTED <br>
          DELIVERY DATE </td>
          <td width="12%" class="tableHeading">ESTIMATED <br>
          DELIVERY DATE </td>
          <td align="left" valign="top" bgcolor="#4E89C4"><table width="100%" height="32" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="20%" class="tableHeading">PRESSTIMELINE</td>
              <td class="tableHeading">ORDER STATUS</td>
              <td width="39%" class="tableHeading">COMMENTS</td>
            </tr>
          </table></td>
        </tr>
         <c:set var="class1" value="tableRow"/>
         <c:forEach var="orderStatusDetail" items="${orderStatusForm.orderStatus.lineItemCollection}" varStatus="indexId">  
          <c:set var="poLine" value="${orderStatusDetail.poLineNo}"/>
           <c:if test="${poLine%2 != 0}" >
           	<c:set var="class1" value="tableRow"/>
           </c:if>
           <c:if test="${poLine%2 == 0}" >
           	<c:set var="class1" value="tableAlternateRow"/>
           </c:if>
         <tr>
          <td class="${class1}"><c:out value="${orderStatusDetail.poLineNo}."/></td>
          <td class="${class1}"><c:out value="${orderStatusDetail.productDescription}"/></td>  
          <td class="${class1}"><c:out value="${orderStatusDetail.requestedDeliveryDate}"/></td>
          <td class="${class1}">
          	<html-el:text size="12" maxlength="15" styleId="estimated_date_${indexId.index}" property="orderStatus.lineItemCollectionIdx[${indexId.index}].estimatedDate" styleClass="textfield" readonly="true"/>
          	<html-el:link href="javascript:NewCal('estimated_date_${indexId.index}','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/calender.gif" width="14" height="12" border="0"></html-el:link>
          </td>
          <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="1">
            <tr>
            	<c:forEach var="tlcombo" items="${TimeLineCombo}">
                  <html-el:hidden property="orderStatus.lineItemCollectionIdx[${indexId.index}].timelineId" value="${tlcombo.timelineId}"/>
               </c:forEach>
              <td width="20%" class="${class1}">
                <html-el:text size="12" maxlength="15" styleId="timeline_date_${indexId.index}" property="orderStatus.lineItemCollectionIdx[${indexId.index}].timelineDate" styleClass="textfield" readonly="true"/>
                <html-el:link href="javascript:NewCal('timeline_date_${indexId.index}','MMDDYYYY')"><img src="<%=request.getContextPath()%>/images/calender.gif" width="14" height="12" border="0"></html-el:link>
             </td>  
             <td class="${class1}">
              	<html-el:select property="orderStatus.lineItemCollectionIdx[${indexId.index}].statusId" styleClass= "textfield" >
			     <html-el:option value="">Select</html-el:option>
			     <c:forEach var="scombo" items="${StatusCombo}">
					<html-el:option value="${scombo.statusId}"><c:out value="${scombo.statusDescription}" /></html-el:option>
			    </c:forEach>
	  		  </html-el:select></td>
              <td width="40%" class="${class1}">
               <!--  	<html-el:textarea property="orderStatus.lineItemCollectionIdx[${indexId.index}].comments"  rows="2" styleClass="textfield" style="width:95%"/>   -->
            	<html-el:textarea onkeypress="return limitChar(${totalLineItems})" property="orderStatus.lineItemCollectionIdx[${indexId.index}].comments"  rows="2" styleClass="textfield" style="width:95%"/> 
              </td> 
              </tr>
              </table></td>
            </tr>
          </c:forEach> 
         </c:if> 
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" height="30px" valign="middle" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
        <tr>
          <td height="1" colspan="5" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td><label> 
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">  
          	  <input name="PAGE_VALUE" type="hidden" value="${page_order_list}">  
          	  <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        	  <input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	  <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	  <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	  <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        	  <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        	  <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        	  <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
	         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>             
              <input name="Button" type="button" class="buttonMain" onClick="if(validateOrderStatus(${totalLineItems})){submitStatusAction('<%=request.getContextPath()%>/orderStatus/orderStatusInsert.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}',this)} else {return false;}" value="Submit">   
              <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V')" value="Close">
          </td>
            </tr>
            </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </html-el:form></table>
  </tr>

