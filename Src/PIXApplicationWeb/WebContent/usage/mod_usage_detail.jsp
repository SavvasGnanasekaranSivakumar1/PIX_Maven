<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.presentation.usage.action.UsageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="ponoFilter"><%=request.getParameter("ponoFilter")%></c:set>
<c:set var="isbnFilter"><%=request.getParameter("isbnFilter")%></c:set>
<c:set var="printNoFilter"><%=request.getParameter("printNoFilter")%></c:set>
<c:set var="startDateFilter"><%=request.getParameter("startDateFilter")%></c:set>
<c:set var="endDateFilter"><%=request.getParameter("endDateFilter")%></c:set>

<c:if test="${usageForm.poHeader.partyCollection != null && not empty usageForm.poHeader.partyCollection}">
<c:forEach var="party" items="${usageForm.poHeader.partyCollection}" varStatus="partyCount">
	<c:choose>
		<c:when test="${party.partyType=='B'}">
			<c:set var="buyer" value="${party}"/>
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
 <%
      boolean permission=false;
		if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.USAGE_CODE))
	     { 
	       permission=true;
	     }
 %>  
<html-el:form action="/usage/usageDetail">
<%@ include file="/common/formbegin.jsp"%>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Usage - PO No. ${usageForm.poHeader.poNo} - Usage No.  ${usageForm.usage.usageNo}</span></td>
  </tr> 
  <c:forEach var="lineItem" items="${usageForm.usage.usageLineCollection}" varStatus="orderLine">
	<c:set var="totalLineItems" value="${orderLine.count}"/>
 </c:forEach>
    <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="16" align="right" valign="bottom" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/usage/usagehistorylist.do?PAGE_VALUE=1&page=H&pno=${usageForm.poHeader.poNo}&poId=${usageForm.poHeader.poId}','UsageDetailHistory','scrollbars=yes,width=800,height=500')" class="subLinksMain">history</a> </td>
      </tr>
      <tr>
        <td class="text">Following are the Usage details. You can change the usage details. To go back to Purchase Order Usage listing, click on the <span class="textBlue">close</span> button.</td>
      </tr>
      <td height="15" colspan="2">
        <div id="error_div" class="errorMessageText"></div>
      </td>
      <tr>
        <td height="15"></td>
      </tr>
      <tr>
        <td height="20" valign="top"><span class="headingMain"><span class="headingMainArrow">&gt;</span>TITLE:&nbsp;&nbsp; ${usageForm.poHeader.titleDesc}</span></td>
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
                <legend class="legendeTitle">SHIP TO (${shipTo.san}) </legend>
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
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
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
			<%
			if(permission)
			{
			%>	 
            <td class="${class1}"> <html-el:text property="usage.usageLineItemCollectionIdx[${lineInfo.index}].usageQuantity" value="${usageLineItem.usageQuantity}" size="10" styleClass= "textfield"  maxlength="9" onkeypress="return numbersonly()" />  </td>
          	<td class="${class1}"> <html-el:text property="usage.usageLineItemCollectionIdx[${lineInfo.index}].damagedQuantity" value="${usageLineItem.damagedQuantity}" size="10" styleClass="textfield" maxlength="9" onkeypress="return numbersonly()" />  </td>
          	<td valign="top" class="${class1}">
          	<html-el:textarea property="usage.usageLineItemCollectionIdx[${lineInfo.index}].comments" rows="2" value="${usageLineItem.comments}" styleClass= "textfield" style="width:300px;text-align:left" />
          	</td>
       		<%--	<textarea class="${class1}" rows="2" value="${usageLineItem.comments}" style="border:none; height:100%;overflow:hidden; width:250px;text-align:left">
      		 
       </textarea> --%>
       
         <%-- 	<td class="${class1}"> <html-el:textarea property="usage.usageLineItemCollectionIdx[${lineInfo.index}].comments" rows="2" value="${usageLineItem.comments}" styleClass= "textfield" style="width:95%" /> </td>
         	   <td class="${class1}"> <html-el:textarea onkeypress="return limitChar(${totalLineItems})" property="usage.usageLineItemCollectionIdx[${lineInfo.index}].comments" rows="2" value="${usageLineItem.comments}" styleClass= "textfield" style="width:95%" /> </td>  --%>
         <%}%>
         <%
		  if(!permission)
		  {
		  %>
         	 <td class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${usageLineItem.usageQuantity}"></fmt:formatNumber> </td>
         	 
    	     <td class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${usageLineItem.damagedQuantity}"></fmt:formatNumber> </td>
    	     
        	 <td class="${class1}"><c:out value="${usageLineItem.comments}"/></td> 
          <%}%>
         </tr>
         </c:forEach> 
         </c:if>
          <c:if test="${errorMsg != null}">
           <tr>
             <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr>
          </c:if>
          <tr>
            <td height="1" colspan="7" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          </tr>
        </table></td>
      </tr>
    </table>
      <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
 		<tr>
          <td height="15" align="left">&nbsp;</td>
        </tr>
         <tr>
           <% if(permission)
			{
			%>
			<td width="6%"> 
			 <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
			 <input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         	<input type="hidden" name="endDateFilter" value="${endDateFilter}"/>     
            <input name="Button" type="button" class="buttonMain" onClick="if(validate(${totalLineItems})) { submitAction('<%=request.getContextPath()%>/usage/insertusage.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&uid=${usageForm.usage.usageId}',this) } else {return false;}" value="Submit">
           </td>
            <%}%>
            </html-el:form> 
         	<html-el:form action="/usage/usageList"> 
        	<td>
        	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         	<input type="hidden" name="endDateFilter" value="${endDateFilter}"/> 
        	<input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/usage/usageList.do?PAGE_VALUE=${PAGE_VALUE}')" value="Close"> </td>  
            <td><input name="Button3" type="button" class="buttonMain" onClick="submitUsageAction('<%=request.getContextPath()%>/pdf/usagepdf.do',this)" value="Export PDF"> 
        	</td>
        	</html-el:form> 
          </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
     </table>
     </td>
      </tr>
   </table>
 </tr>
    
	