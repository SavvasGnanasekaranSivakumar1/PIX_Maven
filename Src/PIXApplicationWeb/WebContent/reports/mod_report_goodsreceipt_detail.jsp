<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.dto.admin.Party" %>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/goodsreceipt/goodsreceiptnew"> 
<%@ include file="/common/formbegin.jsp"%>
<c:if test="${goodsReceiptForm.goodsreceipt.partyCollection != null && not empty goodsReceiptForm.goodsreceipt.partyCollection}">
<c:forEach var="party" items="${goodsReceiptForm.goodsreceipt.partyCollection}" varStatus="partyCount">
	<c:choose>
		    <c:when test="${party.partyType=='V'}">
			<c:set var="vendor" value="${party}"/>
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
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  <c:out value="${goodsReceiptForm.goodsreceipt.poNo}"/>
    <c:if test="${goodsReceiptForm.goodsreceipt.releaseNo!=null && goodsReceiptForm.goodsreceipt.releaseNo!='0'}">
    - ${goodsReceiptForm.goodsreceipt.releaseNo}
    </c:if></span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Goods Receipt   No. <c:out value="${goodsReceiptForm.goodsreceipt.receiptNo}"/> </td>
      </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="5" colspan="3" valign="bottom" class="headingMain">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" colspan="3" valign="bottom" class="headingMain"><table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td width="20%" class="blueColumn">DELIVERY MESSAGE NO.: </td>
                <td width="30%" class="lightblueColumn">
                 <c:out value="${goodsReceiptForm.goodsreceipt.msgNo}"/></a> 
              <%--  <a href="#" class="tableAlternateRowlink" onClick="MM_openBrWindow('deliverymessage_pop.html','','scrollbars=yes,width=700,height=500')"> <c:out value="${goodsReceiptForm.goodsreceipt.msgNo}"/></a><a href="receipt_detail.html" class="tableAlternateRowlink"></a></td> --%>
                <td width="20%" class="blueColumn">GOODS CONDITION: </td>
                <td width="30%" class="lightblueColumn"><c:out value="${goodsReceiptForm.goodsreceipt.headerAcceptanceDescription}"/></td>
              </tr>
          </table></td>
        </tr>
       <tr>
       	  
          <td width="47%" valign="top">
          <fieldset class="legendBorder">
          <legend class="legendeTitle">VENDOR (${vendor.san}) </legend>
            
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
                  <c:out value="${vendor.state}"/>
                  <c:out value="${vendor.countryDetail.countryName}"/></td>
              </tr>
              <tr>
                <td height="5" class="textLegend"></td>
              </tr>
              <tr>
             
                <td class="textLegend"><span class="textLegendBold">Attention -</span><c:out value="${contactVendor.contactFirstName}"/><br>
                  <c:if test="${contactVendor.phone!=null}">&nbsp;<c:out value="${contactVendor.phone}"/>(Business)</c:if><br>
                  <c:if test="${contactVendor.mobile!=null}">&nbsp;<c:out value="${contactVendor.mobile}"/>(Mobile)</c:if><br>
                  <c:if test="${contactVendor.fax!=null}">&nbsp;<c:out value="${contactVendor.fax}"/>(Fax)</c:if><br>
                 <c:if test="${contactVendor.email!=null}">&nbsp;<c:out value="${contactVendor.email}"/></c:if><br></td>
                </tr>
                </table>
              </fieldset></td>
          
         <td width="3%" valign="top">&nbsp;</td>
       <%--  <c:forEach var="party" items="${goodsReceiptForm.goodsreceipt.partyCollection}" varStatus="indexId">  --%>
         
          <td valign="top">
          <fieldset class="legendBorder">
          
            <legend class="legendeTitle">SHIP TO (${shipTo.san}) </legend>
            
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
                <c:out value="${shipTo.state}"/> 
                 <c:out value="${shipTo.countryDetail.countryName}"/></td>
              </tr>
              <tr>
                <td height="5" class="textLegend"></td>
              </tr>
              <tr>
                <td class="textLegend"><span class="textLegendBold">Attention -</span><c:out value="${contactShipTo.contactFirstName}"/><br> 
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
     <tr><td colspan="3">
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td class="tableHeading">COMPONENT </td>
          <td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">REQUESTED ARRIVAL DATE </td>
          <td class="tableHeading">ACTUAL ARRIVAL DATE </td>
          <td class="tableHeading">TRANSPORT DAMAGE</td>
          <td class="tableHeading">GOODS CONDITION  </td>
        </tr>
        
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
          <td valign="middle" class="${class1}"><c:out value="${goodsreceiptLine.lineNo}"/></td>
          <td valign="middle" class="${class1}"><c:out value="${goodsreceiptLine.productDescription}"/></td>
          <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.balanceQuantity}"></fmt:formatNumber> </td>
          <td valign="middle" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.receivedQuantity}"></fmt:formatNumber> </td>
          <td valign="middle" class="${class1}"><c:out value="${goodsreceiptLine.requestedDeliveryDate}"/></td>
          <td valign="middle" class="${class1}"><c:out value="${goodsreceiptLine.actualArrivalDate}"/></td>
          <td valign="middle" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.intransitDamagedQty}"></fmt:formatNumber> </td>
          
          <td valign="middle" class="${class1}"><c:out value="${goodsreceiptLine.acceptanceDescription}"/></td>
        </tr>
        </c:forEach>
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
        <tr>
          <td height="1" colspan="7" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <div id="buttons2" class="tabSelectText"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
				<td>
				<input name="Button32" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
				</td>
                </tr>
        <tr>
          <td height="35">&nbsp;</td>
           
      </html-el:form>  </tr>
      </table></td>
  </tr>
