<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ page language="java"%>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="pageFilter"><%=request.getParameter("pageFilter")%></c:set>
<c:set var="isbnFilter"><%=request.getParameter("isbnFilter")%></c:set>
<c:set var="printNoFilter"><%=request.getParameter("printNoFilter")%></c:set>
<c:set var="statusFilter"><%=request.getParameter("statusFilter")%></c:set>
<c:set var="startDateFilter"><%=request.getParameter("startDateFilter")%></c:set>
<c:set var="endDateFilter"><%=request.getParameter("endDateFilter")%></c:set>
<c:set var="sbBDateFilter" value='<%=request.getParameter("sbBDateFilter")%>' /> 
<c:set var="ebBDateFilter" value='<%=request.getParameter("ebBDateFilter")%>' /> 
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Planning  No.  ${orderDetailForm.poHeader.poNo}</span></td>
</tr>
<html-el:form action="/planning/planningdetail">
<%@ include file="/common/formbegin.jsp"%>
<c:if test="${orderDetailForm.poHeader.partyCollection != null && not empty orderDetailForm.poHeader.partyCollection}">
<c:set var="readOnlyForm" value="F"/>
<c:forEach var="party" items="${orderDetailForm.poHeader.partyCollection}" varStatus="partyCount">
	<c:choose>
		<c:when test="${party.partyType=='B'}">
			<c:set var="buyer" value="${party}"/>
			<c:if test="${PlanningAccessRight=='READ'}">
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
			<c:if test="${PlanningAccessRight=='READ'}">
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
<c:set var="bookspecdetail" value="${orderDetailForm.poHeader.bookSpecDetail}"/>
</c:if>
<c:set var="totalLineItems"/>
<c:forEach var="lineItem" items="${orderDetailForm.poHeader.lineItemCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>	

  <tr>
    <td align="left" valign="top" class="padding23">
     <a name="topofpage"></a>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
     <tr>
        <td colspan="2" class="text"> Following are the details of the Planning. To view component Specifications click on the arrow beside it.</td>
        </tr>
        <tr>
        <td height="15" colspan="2"></td>
        </tr> 
<tr><td class="errorMessageText">
			<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
 
        <tr>
          <td align="right">
		  <table width="38%"  border="0" cellspacing="0" cellpadding="0">
            <tr valign="middle" height="16">
              <td align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/bookspecification/planningbookspecdetail.do?SPEC_ID=${bookspecdetail.specId}&SPEC_VERSION=${bookspecdetail.specVersion}','specifications','scrollbars=yes,width=860,height=650')" class="subLinksMain">specifications</a></td>
              <td width="46%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openModalBrWindow('<%=request.getContextPath()%>/purchaseorder/suppliedcomponent.do?poid=${orderDetailForm.poHeader.poId}&poversion=${orderDetailForm.poHeader.poVersion}','suppliedComponent','scrollbars=yes,width=860,height=600')" class="subLinksMain">supplied component info</a></td>
              <td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/planning/planninghistorylist.do?PAGE_VALUE=1&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}','OrderDetailHistory','scrollbars=yes,width=960,height=500')" class="subLinksMain">history</a> </td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="20" align="left" valign="top" class="headingMain"><span class="headingMainArrow">&gt; </span>TITLE:&nbsp;&nbsp;${orderDetailForm.poHeader.titleDesc}</td>
        </tr>
        <tr>
          <td><table width="100%"  border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td width="17%" height="23" class="blueColumn">ISBN 10: </td>
              <td width="17%" class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.isbn10}</td>
              <td width="17%" class="blueColumn">ISSUE DATE: </td>
              <td width="17%" class="lightblueColumn"><fmt:formatDate value="${orderDetailForm.poHeader.issueDate}" type="both" pattern="MM/dd/yyyy" /></td>
              <td width="16%" class="blueColumn">PRINT NO: </td>
              <td class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.printNo}</td>
            </tr>
            <tr>
              <td height="23" class="blueColumn">ISBN 13: </td>
              <td class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.isbn13}</td>
              <td class="blueColumn">DELIVERY DATE: </td>
              <td class="lightblueColumn"><c:out value="${buyer.deliveryDate}"/></td>
             <td height="23" class="blueColumn">STATUS: </td>
              <td class="lightblueColumn" colspan="5">
              	<c:forEach var="itemBuyer" items="${planningAllStatus}">
              		<c:if test="${itemBuyer.statusId==buyer.statusId}">
						<c:out value="${itemBuyer.statusDescription}"/>
					</c:if>
				</c:forEach>
              </td>
            </tr>
           
          </table></td>
        </tr>
        <tr>
          <td height="16" align="left" valign="top"></td>
        </tr>
            <tr>
          <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" class="tableBorderDash">
    <tr>
    <td  valign="middle" class="greyColumnHeading">BUYER NOTES: </td>
    <td colspan="3" valign="middle" class="greyColumnContent">
    <%-- <td align="left" valign="top">--%>
    <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  
${buyer.comments}
 </textarea>
   
    </td>
  </tr>
  <tr>
    <td height="40" valign="middle" class="greyColumnHeading">YOUR COMMENTS:   </td>
    <td height="40" colspan="3" valign="middle" class="greyColumnContent">
    <c:choose>
	    <c:when test="${readOnlyForm!='T'}">
   			<html-el:textarea property="poHeader.partyCollectionIdx[${partyCounter}].comments" rows="2" style="width:722px" styleClass= "textfield" value="${vendor.comments}"/>
    	</c:when>
		<c:otherwise>
			${vendor.comments}
		</c:otherwise>
	</c:choose>	
    </td>
    </tr>
</table></td>
        </tr>

        
      <tr>
          <td height="16" align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">BUYER (${buyer.san})</legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${buyer.name1} ${buyer.name2} ${buyer.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
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
                <legend class="legendeTitle">VENDOR (${vendor.san}) </legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${vendor.name1} ${vendor.name2} ${vendor.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
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
                    <td class="textLegend"><br>
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
          <td height="10"></td>
        </tr>
        
 <c:forEach var="lineItem" items="${orderDetailForm.poHeader.lineItemCollection}" varStatus="lineInfo">
        <tr>
          <td align="left" valign="top" ><table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="titleBlue">${lineInfo.index+1}. ${lineItem.productDescription}</td>
        </tr>
      </table></td>
    </tr>
  <tr>
    <td align="left" valign="top"><table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td class="titleHeading">PRODUCT ID</td>
        <td width="50%" class="titleHeading">DESCRIPTION</td>
        <td class="titleHeading">QTY.</td>
        <td class="titleHeading">DELIVERY DATE </td>
        <td class="titleHeading">STATUS</td>
        </tr>
      <tr>
        <td  valign="top" class="lightblueColumn">${lineItem.productCode}</td>
        <td width="50%" valign="top" bgcolor="#F8FBFE">
         <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="50%" valign="top" class="lightblueColumnspec">
            	<div id="a_title${lineInfo.index}" class="lTitle" >
            		<img id="a_img${lineInfo.index}" onClick="sDetails(${lineInfo.index},'<%=request.getContextPath()%>')"  src="<%=request.getContextPath()%>/images/expand.gif" alt="Collapse" title="Click to Expand">
            		Specifications:
            	</div>
                <div id="a_hContent${lineInfo.index}" class="hide">
            		<pre>${lineItem.lineDecription}</pre>
            	</div>
            </td>
          </tr>
        </table></td> 		 


<%--<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="50%" valign="top" class="lightblueColumn">${lineItem.lineDecription}</td>
          </tr>
        </table></td>--%>
        <td valign="top" class="lightblueColumn"><fmt:formatNumber value="${lineItem.requestedQuantity}" minFractionDigits="0" maxFractionDigits="0" pattern="#,###.##"/></td>
        <td valign="top" class="lightblueColumn">${lineItem.requestedDeliveryDate}</td>
        <td valign="top" class="lightblueColumn">${lineItem.pubUnitStatusDetail.statusDescription}</td>
        </tr>
      <tr>
        <td colspan="6" valign="top" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif"></td>
        </tr>
      
      <tr>
        <td colspan="6" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="18%" class="greyColumn">BUYER NOTES: </td>
            <td class="greyColumn">
            <%--<td align="left" valign="top">--%>
            
    <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  
${lineItem.pubUnitComments}
 </textarea>
   
    </td>
           
          </tr>
          
        </table></td>
      </tr>
       <tr>
        <td colspan="6" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td width="18%" class="greyColumn">YOUR COMMENTS / QUERIES:</td>
            <td class="greyColumn">
            <c:choose>	
				<c:when test="${readOnlyForm!='T'}">
					<html-el:textarea  property="poHeader.lineItemCollectionIdx[${lineInfo.index}].supplierComments" rows="2" style="width:722px" styleClass= "textfield" value="${lineItem.supplierComments}"/>
				</c:when>
				<c:otherwise>
					${lineItem.supplierComments}
				</c:otherwise>
			</c:choose>
            
            </td>
          </tr>
        </table></td>
        </tr>
      <tr>
        <td colspan="6" valign="top"  height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      
    </table> </td>
    </tr>
</table></td>
        </tr>
        <tr>
          <td height="16" >&nbsp;</td>
        </tr>
</c:forEach>
     

        <c:if test="${orderDetailForm.poHeader.termsConditions!=null}">
		<tr>
          <td height="16">&nbsp;</td>
        </tr>
        <tr>
          <td align="left" valign="top" class="subLinksMain">TERMS &amp; CONDITIONS: </td>
        </tr>
        <tr>
          <td align="left" valign="top">
          <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  

${orderDetailForm.poHeader.termsConditions}
</textarea>
        
		</td>
        </tr>
        </c:if>
        <tr>
          <td height="16">&nbsp;</td>
        </tr>
        
        <tr>
          <td><label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
          <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
          <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
          <input type="hidden" name="statusFilter" value="${statusFilter}"/>
          <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
          <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
          <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
          <c:if test="${readOnlyForm!='T'}">
          	<input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
            <input name="Button" type="button" class="buttonMain" onClick="if(validate(${partyCounter},${totalLineItems})){submitPlanningAction('<%=request.getContextPath()%>/planning/planningdetailsave.do',this)}else{return false;}" value="Acknowledge">
           
          </c:if>
            <input name="Button2" type="button" class="buttonMain" onClick="submitPlanningCancelAction('<%=request.getContextPath()%>/planning/planninglist.do?PAGE_VALUE=${PAGE_VALUE}&pageFilter=${pageFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
            <input name="Button2" type="button" class="buttonMain" onClick="submitPlanningActionExcel('<%=request.getContextPath()%>/pdf/planningpdf.do',this)" value="Export PDF">
         </td>
         </tr>
         </table>
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
    </html-el:form> 
    <html-el:form action="/planning/planninglist.do">
    </html-el:form>
      </table></td>
  </tr>