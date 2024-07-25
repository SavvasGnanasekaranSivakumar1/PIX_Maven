<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.dto.admin.Party" %>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="home"><%=request.getParameter("home")%></c:set>
<c:set var="partyType" value='<%=request.getParameter("partyType")%>' /> 
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />

<html-el:form action="/goodsreceipt/goodsreceiptnew"> 
<%@ include file="/common/formbegin.jsp"%>
<c:if test="${goodsReceiptForm.goodsreceipt.partyCollection != null && not empty goodsReceiptForm.goodsreceipt.partyCollection}">
<c:forEach var="party" items="${goodsReceiptForm.goodsreceipt.partyCollection}" varStatus="partyCount">
	<c:choose>
		    <c:when test="${party.partyType=='V'|| party.partyType=='M'}">
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
    </c:if>
    </span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Goods Receipt   No. <c:out value="${goodsReceiptForm.goodsreceipt.receiptNo}"/> </span>. To go back to the goods receipt listing, click on the <span class="textBlue">close</span> button.</td>
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
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <c:if test="${partyType!='M'}">
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
                <td width="20%" class="blueColumn">DELIVERY MESSAGE NO.: </td>
                <td width="30%" class="lightblueColumn">
                <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/goodsreceipt/deliverymessagedetail.do?MSG_ID=${goodsReceiptForm.goodsreceipt.msgId}&poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&rno=${releaseno}&days=${days}&home=${home}&order=${order}','DeliveryMessageDetail','scrollbars=yes,width=750,height=500')" class="tableAlternateRowlink"><c:out value="${goodsReceiptForm.goodsreceipt.msgNo}"/></a> 
              <%--  <a href="#" class="tableAlternateRowlink" onClick="MM_openBrWindow('deliverymessage_pop.html','','scrollbars=yes,width=700,height=500')"> <c:out value="${goodsReceiptForm.goodsreceipt.msgNo}"/></a><a href="receipt_detail.html" class="tableAlternateRowlink"></a></td> --%>
                <td width="20%" class="blueColumn">GOODS CONDITION: </td>
                <td width="30%" class="lightblueColumn"><c:out value="${goodsReceiptForm.goodsreceipt.headerAcceptanceDescription}"/></td>
              </tr>
          </table></td>
        </tr>
       <tr>
       	  
          <td width="47%" valign="top">
          <fieldset class="legendBorder">
          <legend class="legendeTitle">VENDOR (${vendor.san})
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
                  <c:out value="${vendor.state}"/>
                  <c:out value="${vendor.countryDetail.countryName}"/></td>
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
              </fieldset></td>
          
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
                <c:out value="${shipTo.state}"/> 
                 <c:out value="${shipTo.countryDetail.countryName}"/></td>
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
     <tr><td colspan="3">
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <c:choose>
          	<c:when test="${orderPaper=='O' || roleType=='M'}">
          		<td class="tableHeading">MATERIAL NUMBER </td>
          		<td class="tableHeading">MATERIAL DESCRIPTION </td>
          	</c:when>
          	<c:otherwise>
          		<td class="tableHeading">COMPONENT </td>
          	</c:otherwise>
          </c:choose>
          <td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <c:if test="${orderPaper=='O' || roleType=='M'}">
          		<td class="tableHeading">TOTAL RECEIVED</td>
           </c:if>
          <td class="tableHeading">QUANTITY</td>
          <c:choose>
          	<c:when test="${orderPaper=='O' || roleType=='M'}">
          		<td class="tableHeading">REQUESTED DELIVERY DATE</td>
          	</c:when>
          	<c:otherwise>
          		<td class="tableHeading">COMP. DELIVERY DATE</td>
          	</c:otherwise>
          </c:choose>
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
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.lineNo}"/></td>
          <c:choose>
          	<c:when test="${orderPaper=='O' || roleType=='M'}">
          		<td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.productCode}"/></td>
          		<td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.productDescription}"/></td>
          	</c:when>
          	<c:otherwise>
          		<td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.productDescription}"/></td>
          	</c:otherwise>
          </c:choose>
          <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.balanceQuantity}"></fmt:formatNumber> </td>
          <c:if test="${orderPaper=='O' || roleType=='M'}">
          	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.cumulativeQuanReceived}"></fmt:formatNumber> </td>
          </c:if>
          <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.receivedQuantity}"></fmt:formatNumber> </td>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.requestedDeliveryDate}"/></td>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.actualArrivalDate}"/></td>
          <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${goodsreceiptLine.intransitDamagedQty}"></fmt:formatNumber> </td>
          <td valign="top" class="${class1}"><c:out value="${goodsreceiptLine.acceptanceDescription}"/></td>
        </tr>
        </c:forEach>
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
        <tr>
          <c:choose>
          		<c:when test="${orderPaper=='O' || roleType=='M'}">
          			<td height="1" colspan="10" class="tableLine">
          		</c:when>
          		<c:otherwise>
          			<td height="1" colspan="8" class="tableLine">
          		</c:otherwise>
          	</c:choose>
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <div id="buttons2" class="tabSelectText"></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td><label>
            <%-- <input name="Button" type="button" class="buttonMain" onClick="MM_goToURL('parent','receipt_new.html');return document.MM_returnValue" value="New Goods Receipt"> --%>
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.GOODSREC_CODE)&& "F".equals(request.getParameter("order")))
{
%>             
            <input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/goodsreceipt/goodsreceiptnew.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}',this)" value="New Goods Receipt">
<%}%>           
  
        <input name="Button2" type="button" class="buttonMain" onClick="submitCancelReceipt('<%=request.getContextPath()%>/home/home.do')" value="Close"> 
        <input name="Button2" type="button" class="buttonMain" onClick="submitGoodsAction2('<%=request.getContextPath()%>/pdf/goodsreceiptpdf.do',this)" value="Export PDF">
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
           
      </html-el:form>  </tr>
      </table></td>
  </tr>
