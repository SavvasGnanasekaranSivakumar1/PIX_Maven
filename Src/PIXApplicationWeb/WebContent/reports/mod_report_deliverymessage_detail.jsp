<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/deliverymessage/deliverymessagenew"> 
<%@ include file="/common/formbegin.jsp"%>
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  <c:out value="${deliveryMessageForm.deliveryMessage.poNo}"/> 
    <c:if test="${deliveryMessageForm.deliveryMessage.releaseNo!=null && deliveryMessageForm.deliveryMessage.releaseNo!='0'}">
    - ${deliveryMessageForm.deliveryMessage.releaseNo}
    </c:if> </span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Delivery Message  No. <c:out value="${deliveryMessageForm.deliveryMessage.msgNo}"/> </td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
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
              <td width="35%" class="blueColumn">MESSAGE TYPE: </td>
              <td class="lightblueColumn"><c:out value="${deliveryMessageForm.deliveryMessage.msgTypeDetail}"/></td>
            </tr>
          </table></td>
        </tr>
        <tr>
        <c:forEach var="party" items="${deliveryMessageForm.deliveryMessage.partyCollection}" varStatus="indexId">     
          <td><fieldset class="legendBorder">
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
                <c:out value="${party.city}"/> <c:out value="${party.postalCode}"/> <c:out value="${party.state}"/> <c:out value="${party.countryDetail.countryName}"/></td>
            </tr>
             
            <tr>
              <td height="5" class="textLegend"></td>
            </tr>
            <tr>
           
			<c:forEach var="contact" items="${party.contactCollection}" varStatus="indexId">
							
              <td class="textLegend"><span class="textLegendBold">Attention -</span> <c:out value="${contact.contactFirstName}"/><br>
<c:out value="${contact.phone}"/>(Business); <c:out value="${contact.mobile}"/> (Mobile); <c:out value="${contact.fax}"/>(Fax)<br>
<c:out value="${contact.email}"/></td>
</c:forEach>
		
            </tr>
            </c:forEach>
          </table></fieldset></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
       
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        <c:if test="${errorMsg == null}"> 
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td class="tableHeading">COMPONENT </td>
          <td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">REQUESTED DELIVERY DATE </td>
          <td class="tableHeading">SHIP DATE </td>
        </tr>
        
        <c:set var="deliveryMessageDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">     
        <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
         <c:if test="${lineNo%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
         <tr>
         <td class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productDescription}"/></td>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.balanceQuantity}"></fmt:formatNumber> </td>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.deliveredQuantity}"></fmt:formatNumber> </td>
         
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.estimatedDeliveryDate}"  /></td>
         </tr>
         </c:forEach>
          </c:if> 
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" height="30px" valign="middle" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
         
          <tr>
          <td height="1" colspan="5" class="tableLine"/>
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <div id="buttons2" class="tabSelectText"> </div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
         <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
				<td>
				<input name="Button32" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
				</td>
                </tr>
            </table>
           
           </label>
            
          </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        
     </table></td>
    
     </html-el:form>
  </tr>
