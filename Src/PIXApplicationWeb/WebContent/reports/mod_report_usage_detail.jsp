<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.presentation.usage.action.UsageForm"%>
<c:if test="${usageForm.poHeader.partyCollection != null && not empty usageForm.poHeader.partyCollection}">
<c:set var="readOnlyForm" value="F"/>
<c:forEach var="party" items="${usageForm.poHeader.partyCollection}" varStatus="partyCount">
	<c:choose>
		<c:when test="${party.partyType=='B'}">
			<c:set var="buyer" value="${party}"/>
			<c:if test="${POAccessRight=='READ'}">
				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			<c:forEach var="contact" items="${buyer.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactBuyer" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='V'}">
			<c:set var="vendor" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${vendor.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactVendor" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='S'}">
			<c:set var="shipTo" value="${party}"/>
			<c:if test="${POAccessRight=='READ'}">
				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			<c:forEach var="contact" items="${shipTo.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactShipTo" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</c:forEach>
</c:if>
<html-el:form action="/usage/usageList">
<%@ include file="/common/formbegin.jsp"%>
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Usage - PO No. ${usageForm.poHeader.poNo} - Usage No.  ${usageForm.usage.usageNo}</span></td>
  </tr>
 

    <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td class="text">Following are the Usage details.</td>
      </tr>
      <tr>
        <td height="15"></td>
      </tr>
      <tr>
        <td height="20" valign="top"><span class="headingMain"><span class="headingMainArrow">&gt; </span>${usageForm.poHeader.titleDesc}</span></td>
      </tr>
      
      <tr>
        <td height="15" align="left" valign="top"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
          <tr>
            <td width="17%" height="23" class="blueColumn">ISBN 10: </td>
            <td width="17%" class="lightblueColumn">${usageForm.poHeader.titleDetail.isbn10}</td>
            <td width="17%" class="blueColumn">ISSUE DATE: </td>
            <td width="17%" class="lightblueColumn"><fmt:formatDate value="${usageForm.poHeader.issueDate}" type="both" pattern="MM/dd/yyyy" /></td>
            <td width="16%" class="blueColumn">PRINT NO: </td>
            <td class="lightblueColumn">${usageForm.poHeader.titleDetail.printNo}</td>
          </tr>
          <tr>
            <td height="23" class="blueColumn">ISBN 13: </td>
            <td class="lightblueColumn">${usageForm.poHeader.titleDetail.isbn13}</td>
            <td class="blueColumn">DELIVERY DATE: </td>
            <td class="lightblueColumn"><c:out value="${buyer.deliveryDate}"/></td>
            <td class="blueColumn">JOB NO. </td>
            <td class="lightblueColumn">${usageForm.poHeader.jobNo}</td>
          </tr>
        </table></td>
        </tr>
      
      <tr>
          <td height="16" align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">BUYER(${buyer.san}) </legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${buyer.name1} ${buyer.name2} ${buyer.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend">
                      ${buyer.address1} ${buyer.address2}<br>
                      ${buyer.address3} ${buyer.address4}<br>
                      ${buyer.city} ${buyer.postalCode} ${buyer.state} ${buyer.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactBuyer.contactFirstName} ${contactBuyer.contactLastName}<br>
						<c:if test="${contactBuyer.phone!=null}">&nbsp;${contactBuyer.phone} (Business)</c:if><br>
						<c:if test="${contactBuyer.mobile!=null}">&nbsp;${contactBuyer.mobile} (Mob)</c:if><br>
						<c:if test="${contactBuyer.fax!=null}">&nbsp;${contactBuyer.fax} (Fax)</c:if><br>
						<c:if test="${contactBuyer.email!=null}">&nbsp;${contactBuyer.email} </c:if><br>
                  </tr>
                </table>
              </fieldset></td>
              <td width="2%" height="170">&nbsp;</td>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">VENDOR(${vendor.san}) </legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${vendor.name1} ${vendor.name2} ${vendor.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend">
                      ${vendor.address1} ${vendor.address2}<br>
                      ${vendor.address3} ${vendor.address4}<br>
                      ${vendor.city} ${vendor.postalCode} ${vendor.state} ${vendor.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactVendor.contactFirstName} ${contactVendor.contactLastName}<br>
						<c:if test="${contactVendor.phone!=null}">&nbsp;${contactVendor.phone} (Business)</c:if><br>
						<c:if test="${contactVendor.mobile!=null}">&nbsp;${contactVendor.mobile} (Mob)</c:if><br>
						<c:if test="${contactVendor.fax!=null}">&nbsp;${contactVendor.fax} (Fax)</c:if><br>
						<c:if test="${contactVendor.email!=null}">&nbsp;${contactVendor.email} </c:if><br>
                  </tr>
                </table>
              </fieldset></td>
              <td width="2%">&nbsp;</td>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">SHIP TO(${shipTo.san}) </legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${shipTo.name1} ${shipTo.name2} ${shipTo.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend">
                      ${shipTo.address1} ${shipTo.address2}<br>
                      ${shipTo.address3} ${shipTo.address4}<br>
                      ${shipTo.city} ${shipTo.postalCode} ${shipTo.state} ${shipTo.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactShipTo.contactFirstName} ${contactShipTo.contactLastName}<br>
						<c:if test="${contactShipTo.phone!=null}">&nbsp;${contactShipTo.phone} (Business)</c:if><br>
						<c:if test="${contactShipTo.mobile!=null}">&nbsp;${contactShipTo.mobile} (Mob)</c:if><br>
						<c:if test="${contactShipTo.fax!=null}">&nbsp;${contactShipTo.fax} (Fax)</c:if><br>
						<c:if test="${contactShipTo.email!=null}">&nbsp;${contactShipTo.email} </c:if><br>

                  </tr>
                </table>
              </fieldset></td>
              </tr>
          </table></td>
        </tr>

      <tr>
        <td height="15" align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
           <td width="2%" class="tableHeading">&nbsp;</td>
           	<td class="tableHeading">PRODUCT ID </td>
            <td class="tableHeading">SUPPLEMENT COMPONENT </td>
            <td class="tableHeading">COMPONENT </td>
            <td class="tableHeading">USAGE QUANTITY</td>
            <td class="tableHeading">DAMAGED QUANTITY </td>
            <td width="35%" class="tableHeading">COMMENTS</td>
          </tr>
          
        <c:forEach var="usageLineItem" items="${usageForm.usage.usageLineCollection}" varStatus="lineInfo">
        	<c:if test="${lineInfo.count%2 != 0}" >
           		<c:set var="class1" value="tableRow"/>
           </c:if>
           <c:if test="${lineInfo.count%2 == 0}" >
           		<c:set var="class1" value="tableAlternateRow"/>
           </c:if>
	      <tr>
            <td class="${class1}"><c:out value="${lineInfo.count}"/></td>
            <td class="${class1}"><c:out value="${usageLineItem.productCode}"/></td> 
            <td class="${class1}"><c:out value="${usageLineItem.productDescription}"/></td> 
            <td class="${class1}"><c:out value="${usageLineItem.productClassifDescription}"/></td>  
            <td class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${usageLineItem.usageQuantity}"></fmt:formatNumber> </td>
            <td class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${usageLineItem.damagedQuantity}"></fmt:formatNumber> </td>
           <%-- <td class="${class1}"><c:out value="${usageLineItem.comments}"/></td> --%>
            <td align="left" valign="top" class="${class1}">
                   		<textarea class="${class1}" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:250px; text-align:left">${usageLineItem.comments}
       			<%-- <textarea class="${class1}" readonly="readonly" rows="2" style="background-color:transparent;border:none; height:100%;overflow:hidden; width:250px;text-align:left">${usageLineItem.comments}  --%>
      	     </textarea></td>
          </tr>
        </c:forEach> 
         
          <tr>
            <td height="1" colspan="7" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          </tr>
        </table></td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
 		
        <tr>
          <td height="15" align="left">&nbsp;</td>
        </tr>
        <tr>
				<td>
				<input name="Button32" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
				</td>
                </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
     </table></td>
  </tr>
   </html-el:form> 