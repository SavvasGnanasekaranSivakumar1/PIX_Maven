<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
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
 <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
<tr>
    <td height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  <c:out value="${deliveryMessageForm.deliveryMessage.poNo}"/>
    <c:if test="${deliveryMessageForm.deliveryMessage.releaseNo!=null && deliveryMessageForm.deliveryMessage.releaseNo!='0'}">
    - ${deliveryMessageForm.deliveryMessage.releaseNo}
    </c:if></span></td>
  </tr>
  <tr>
<c:forEach var="lineItem" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>
   <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">Fill in the following details to generate a <span class="textBold">New Delivery Message </span>. You shall be notified the new delivery message number through an email.</td>
      </tr>
      
      <td>
        <div id="error_div" class="errorMessageText"></div>
      </td>
      <tr>
        <td height="0" colspan="2"></td>
      </tr>
      <tr>
        <td width="80%" height="22">
        	<table height="22" border="0" cellpadding="0" cellspacing="0">
         		<c:set var="pageModule" value="deliverymessage" scope="request"></c:set>
         		<%@ include file="/common/mod_tabs.jsp"%>
        </table>
        </td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
   
     		<table width="60%" border="0" cellspacing="0" cellpadding="0">
				<tr>
         			 <td height="5" valign="bottom" class="headingMain">&nbsp;</td>
       		    </tr>
        		<tr>
        		     <td height="20" valign="bottom" class="headingMain">
            
         			 <table width="100%" border="0" cellspacing="1" cellpadding="0">
            	     <tr>
               			<html-el:form action="/deliverymessage/insertnewdeliverymessage">
              			 <%@ include file="/common/formbegin.jsp"%>
            	  		   <c:set var="readOnlyForm" value="F"/>
             			 <c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
                			<td width="35%" class="blueColumn">MESSAGE TYPE: </td>
               
               				 <td class="lightblueColumn">
               				 <html-el:select styleClass="textfield" property="deliveryMessage.msgTypeDetail">
               				 	<html-el:option value="">Select</html-el:option>
                					<c:forEach var="item" items="${MessageTypeDetail}" varStatus="indexId">
                						<html-el:option value="${item.msg_type_id}"><c:out value="${item.msg_type}"/></html-el:option>
               				 		</c:forEach>
             				</html-el:select>
             				</td> 
            			</c:if>
         			 </tr>
         			 </table>
         			 </td>
        		</tr>
        		<tr>
        				<c:forEach var="party" items="${deliveryMessageForm.deliveryMessage.partyCollection}" varStatus="indexId">     
          					<td>
          						<c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
          						<fieldset class="legendBorder">
           						 <legend class="legendeTitle">SHIP TO (${party.san}) </legend>
            					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
             						 <tr>
               							 <td height="5"></td>
              						 </tr>
              						 <tr>
                						 <td class="headingText12"><c:out value="${party.name1}"/></td>
              						 </tr>
              						 <tr>
                						 <td class="textLegend"><c:out value="${party.address1}"/><br>
                  						 <c:out value="${party.city}"/> <c:out value="${party.postalCode}"/> <c:out value="${party.state}"/> <c:out value="${party.countryDetail.countryName}"/> </td>
             						 </tr>
              						 <tr>
                							<td height="5" class="textLegend"></td>
              						 </tr>
              						 <tr>
              							 <c:forEach var="contact" items="${party.contactCollection}" varStatus="indexId">
			  								<td class="textLegend"><span class="textLegendBold"> -</span> <c:out value="${contact.contactFirstName}"/><br>
              								<c:out value="${contact.phone}"/>(Business); <c:out value="${contact.mobile}"/> (Mobile); <c:out value="${contact.fax}"/>(Fax)<br>
              								<c:out value="${contact.email}"/>
              								</td>
              							 </c:forEach>
		    						 </tr>
            		
            					</table>
          						</fieldset>
          						</c:if>
          					</td>
          				
						</c:forEach>
        		</tr>
    			<tr>
          			<td>&nbsp;</td>
        		</tr>

          </table>
       
    
  
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
     
      	<c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td class="tableHeading">COMPONENT </td>
          <td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">REQUESTED DELIVERY DATE </td>
          <td class="tableHeading">SHIP DATE </td>
        </tr>
        <tr>
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">     
        <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
        <c:if test="${lineNo%2 != 0}" >
        <c:set var="class1" value="tableRow"/>
        </c:if>
        <c:if test="${lineNo%2 == 0}" >
        <c:set var="class1" value="tableAlternateRow"/>
        </c:if>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productDescription}"/></td>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.balanceQuantity}"/></td>
        <td valign="top" class="${class1}">
        <html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].deliveredQuantity" size="10" styleClass= "textfield"  maxlength="9" onkeypress="return numbersonlyNew(this.value)"/>
        <%-- <html-el:text  property="quantity" styleClass="textfield"/></td>  --%>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
        <td valign="top" class="${class1}">
        <html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].estimatedDeliveryDate" size="12" styleClass= "textfield" styleId="line_del_date_${indexId.index}" maxlength="10" readonly="true"/>
        <a href="javascript:NewCal('line_del_date_${indexId.index}','MMDDYYYY')"> 
        <img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td> 
         </tr>
         </c:forEach>
         </c:if> 
          <c:if test="${((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) <= 0)}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage">Currently there are no line items to post delivery message</font></h3></td>   
           </tr>
         </c:if>
         <tr>
          <td height="1" colspan="6" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
        <div id="buttons2" class="tabSelectText"></div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td>
          <label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons3" class="tabSelectTextleft">
           <input type="hidden" name="request_time" value="<%=System.currentTimeMillis()%>"/>
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order"))){
%>         
			<input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="statusFilter" value="${statusFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        	<!-- <input type="hidden" name="endDateFilter" value="${endDateFilter}"/> -->
        	
        	<input type="hidden" name="endDateFilter" value="<c:out value=${endDateFilter}"/>"/>
        	 
        	<input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         	<input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
           <c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
            <input name="Button1" type="button" class="buttonMain" onClick="if(validate(${totalLineItems})){submitDelmsgAction('<%=request.getContextPath()%>/deliverymessage/insertnewdeliverymessage.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}',this)}else{return false;}" value="Submit">
            </c:if>
 <%}%>            
            
    
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V')" value="Close">
             </td>
            </tr>
            </table>
            </label>
            </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        
        </html-el:form>
      </table>
  </tr>