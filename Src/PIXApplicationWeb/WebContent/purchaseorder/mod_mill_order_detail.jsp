<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %> 
<html-el:form action="/purchaseorder/orderdetail">
<%@ include file="/common/formbegin.jsp"%>
<c:if test="${orderDetailForm.poHeader.partyCollection != null && not empty orderDetailForm.poHeader.partyCollection}">
<c:set var="readOnlyForm" value="F"/>
<c:set var="readOnlyForm1" value="F"/>
<c:set var="userCheck" value="${USER_INFO}" />
<c:set var="PAGE_VALUE_ORDER_LIST" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
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
<c:set var="lineItemSize" value='${fn:length(orderDetailForm.poHeader.lineItemCollection)} ' />
<c:set var="order" value='<%=request.getParameter("order")%>'/>
<c:set var="roleType" value='${userCheck.roleTypeDetail.roleType}' />

<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>


<c:forEach var="party" items="${orderDetailForm.poHeader.partyCollection}" varStatus="partyCount">
 
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
				<c:if test="${POAccessRight=='READ'}">
				<c:set var="readOnlyForm1" value="T"/>
			</c:if>
			</c:forEach>
		</c:when>
		
		<c:when test="${party.partyType=='M'}">
			<c:set var="mill" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${mill.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactMill" value="${contact}"/>
				</c:if>
				<c:if test="${POAccessRight=='READ'}">
				<c:set var="readOnlyForm1" value="T"/>
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


<c:set var="bookspecdetail" value="${orderDetailForm.poHeader.bookSpecDetail}"/>
</c:if>
<c:set var="totalLineItems" value='0'/>
<c:forEach var="lineItem" items="${orderDetailForm.poHeader.lineItemCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>
 	
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No. ${orderDetailForm.poHeader.poNo}
    <c:if test="${orderDetailForm.poHeader.releaseNo!=null && orderDetailForm.poHeader.releaseNo!='0'}">
    - ${orderDetailForm.poHeader.releaseNo}
    </c:if>
    </span></td>
</tr>  
  <tr>
    <td align="left" valign="top" class="padding23">
    <a name="topofpage"></a>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      
      <tr>
        <td colspan="2" class="text"> Following are the details of the Purchase Order. To view component Specifications click on the arrow beside it.</td>
        </tr>
        <tr>
        <td height="15" colspan="2"></td>
        </tr>
		<tr><td class="errorMessageText">
			<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
          <c:set var="pageModule" value="purchaseorder" scope="request"></c:set>
          <c:set var="lineItem" value="${totalLineItems}" scope="request"></c:set>
          <c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
          
          <c:if test="${HistoryFlag == false}">
	          <%@ include file="/common/mod_tabs.jsp"%>
          </c:if>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right">
          <c:if test="${HistoryFlag == false}">
		  <table width="38%"  border="0" cellspacing="0" cellpadding="0">
            <tr valign="middle" height="16">
           <%--   <td align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/bookspecification/pobookspecdetail.do?SPEC_ID=${bookspecdetail.specId}&SPEC_VERSION=${bookspecdetail.specVersion}','specifications','scrollbars=yes,width=600,height=650,left=200,top=10')" class="subLinksMain">specifications</a></td>
              <td width="46%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openModalBrWindow('<%=request.getContextPath()%>/purchaseorder/suppliedcomponent.do?poid=${orderDetailForm.poHeader.poId}&poversion=${orderDetailForm.poHeader.poVersion}','suppliedComponent','scrollbars=yes,width=860,height=600,left=200,top=10')" class="subLinksMain">supplied component info</a></td>
		if("F".equals(request.getParameter("order"))){%>
            <td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/purchaseorderhistorylist.do?PAGE_VALUE=1&party=S&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}&ORDER=${order}','OrderDetailHistory','scrollbars=yes,width=800,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
<%		}else{--%>
		<c:choose>
               <c:when test="${roleType == 'M'|| roleType=='C'}">
            		<td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=1&party=M&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}&ORDER=${order}','OrderDetailHistory','scrollbars=yes,width=875,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
            	</c:when>
            	<c:otherwise>
            		<td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=1&party=S&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}&ORDER=${order}','OrderDetailHistory','scrollbars=yes,width=875,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
            	</c:otherwise>
        </c:choose>
<%--		}--%>              
              
            </tr>
          </table>
          </c:if>
          </td>
        </tr>
        <tr>
          <td height="20" align="left" valign="top" class="headingMain"><span class="headingMainArrow">&gt; </span>MATERIAL DESCRIPTION:&nbsp;&nbsp;${orderDetailForm.poHeader.titleDesc}</td>
        </tr>
        <tr>
          <td><table width="100%"  border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td width="17%" height="23" class="blueColumn">MATERIAL NUMBER:</td>
              <td width="17%" class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.isbn10}</td>
              <td width="17%" class="blueColumn">ISSUE DATE: </td>
              <td width="17%" class="lightblueColumn"><fmt:formatDate value="${orderDetailForm.poHeader.issueDate}" type="both" pattern="MM/dd/yyyy" /></td>
              <td width="16%" class="blueColumn">
              <c:choose>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='LB'}">
              			QTY.(LBS):
              		</c:when>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='SH'}">
              			QTY.(SH):
              		</c:when>
              		
              	</c:choose>
              </td>
              <td class="lightblueColumn">
              <%--<c:choose>
				<c:when test="${readOnlyForm!='T'}">
	               <html-el:text property="poHeader.totalQuantity" value="${orderDetailForm.poHeader.totalQuantity}" size="12" styleClass= "textfield" maxlength="60"/>
              	</c:when>
				<c:otherwise>
					${orderDetailForm.poHeader.totalQuantity}
				</c:otherwise>
			  </c:choose>--%>
			  	<html-el:hidden property="poHeader.totalQuantity" value="${orderDetailForm.poHeader.totalQuantity}"/>
			  	${orderDetailForm.poHeader.totalQuantity}
              </td>
            </tr>
            <tr>
              <td height="23" class="blueColumn">PAPER GRADE: </td>
              <td class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.paperGrade}</td>
              <td class="blueColumn">DELIVERY DATE: </td>
              <td class="lightblueColumn" id="headerDlDate"><c:out value="${buyer.deliveryDate}"/></td>
              <td class="blueColumn">JOB NO. </td>
              <td class="lightblueColumn">
	          <c:choose>
				<c:when test="${readOnlyForm!='T'}">
	              <html-el:text property="poHeader.jobNo" value="${orderDetailForm.poHeader.jobNo}" size="12" styleClass= "textfield" maxlength="60"/>
              	</c:when>
				<c:otherwise>
					${orderDetailForm.poHeader.jobNo}
				</c:otherwise>
			  </c:choose>	
              </td>
            </tr>
            <tr>
              <td height="23" class="blueColumn">STATUS:
              </td>
              <td class="lightblueColumn" >
              	
              	<c:forEach var="itemBuyer" items="${orderDetailForm.poAllHeaderStatus}">
					<c:if test="${itemBuyer.statusId==buyer.statusId}">
						<c:out value="${itemBuyer.statusDescription}"/>
					</c:if>
				</c:forEach>
				
              </td>
              <td class="blueColumn">BASIS WEIGHT: </td>
              <td class="lightblueColumn" id="headerDlDate"><c:out value="${orderDetailForm.poHeader.titleDetail.basisWeight}"/></td>
              <td class="blueColumn">UOM: </td>
              <td class="lightblueColumn" id="headerDlDate">
              	<c:choose>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='LB'}">
              			<c:out value="Roll"/>
              		</c:when>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='SH'}">
              			<c:out value="Sheet"/>
              		</c:when>
              		
              	</c:choose>
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
    <td width="20%" class="greyColumnHeading">STATUS:</td>
    
    
			
    <td width="30%" class="greyColumnContent">
    	
    <c:choose>
	<c:when test="${readOnlyForm!='T'}">
		<html-el:select property="poHeader.partyCollectionIdx[${partyCounter}].statusId" styleClass= "textfield" onchange="changeLineStatus(${totalLineItems},document.orderDetailForm.elements['poHeader.partyCollectionIdx[${partyCounter}].statusId'])">
			<html-el:option value="">Select</html-el:option>
			<c:forEach var="item" items="${orderDetailForm.poAllHeaderStatus}">
				<c:if test="${item.statusCode != 'NEW' && item.statusCode != 'ORDDELIVER'}">
					<html-el:option value="${item.statusId}"><c:out value="${item.statusDescription}"/></html-el:option>
				</c:if>
			</c:forEach>
		</html-el:select>
	</c:when>
	<c:otherwise>
		<c:forEach var="item" items="${orderDetailForm.poAllHeaderStatus}">
			<c:if test="${item.statusId==mill.statusId}">
				<c:out value="${item.statusDescription}"/>
			</c:if>
		</c:forEach>
	</c:otherwise>
	</c:choose>  

    </td>
    <td width="20%" class="greyColumnHeading">DELIVERY DATE: </td>
    <td height="23" class="greyColumnContent">
    <c:choose>
	    <c:when test="${readOnlyForm!='T'}">
	    	<c:choose>
				<c:when test="${mill.deliveryDate==null}">
					<html-el:text property="poHeader.partyCollectionIdx[${partyCounter}].deliveryDate" value="" size="12" styleClass= "textfield" styleId="header_del_date" maxlength="10" readonly="true"/>
					<html-el:link href="javascript:NewCalPOHeader('header_del_date','MMDDYYYY',${totalLineItems},${partyCounter})">
		                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
	                </html-el:link>
				</c:when>
				<c:otherwise>
					<html-el:text property="poHeader.partyCollectionIdx[${partyCounter}].deliveryDate" value="${mill.deliveryDate}" size="12" styleClass= "textfield" styleId="header_del_date" maxlength="10" readonly="true"/>
					<html-el:link href="javascript:NewCalPOHeader('header_del_date','MMDDYYYY',${totalLineItems},${partyCounter})">
		                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
	                </html-el:link>
				</c:otherwise>
			</c:choose>
	    	&nbsp;
		</c:when>
		<c:otherwise>
			<c:out value="${mill.deliveryDate}"/>
		</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td  valign="middle" class="greyColumnHeading">BUYER NOTES: </td>
    <td colspan="3" valign="middle" class="greyColumnContent"> 
     <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px; text-align:left">  
${buyer.comments}
 </textarea></td>
  </tr>
  <tr>
    <td height="40" valign="middle" class="greyColumnHeading">YOUR COMMENTS:   </td>
    <td height="40" colspan="3" valign="middle" class="greyColumnContent">
    <c:choose>
	    <c:when test="${readOnlyForm!='T'}">
   			<html-el:textarea  property="poHeader.partyCollectionIdx[${partyCounter}].comments" rows="2" style="width:722px" styleClass="textfield" value="${mill.comments}"/>
    	</c:when>
		<c:otherwise>
			${mill.comments}
		</c:otherwise>
	</c:choose>	
    </td>
    </tr>
</table></td>
        </tr>
        <tr><td height="10" align="left" valign="top"></td></tr>
        <tr>
          <td height="16" align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">BUYER (${buyer.san})
                 <c:if test="${buyer.orgUnitCode!=null}">(${buyer.orgUnitCode})</c:if></legend>
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
                <legend class="legendeTitle">MILL (${mill.san})
                <%session.setAttribute("mill_san","${mill.san}"); %>
                 <c:if test="${mill.orgUnitCode!=null}">(${mill.orgUnitCode})</c:if></legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${mill.name1} ${mill.name2} ${mill.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
                      ${mill.address1} ${mill.address2}<br>
                      ${mill.address3} ${mill.address4}<br>
                      ${mill.city} ${mill.postalCode} ${mill.state} ${mill.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactMill.contactFirstName} ${contactMill.contactLastName}<br>
<c:if test="${contactMill.phone!=null}">&nbsp;${contactMill.phone} (Business)</c:if><br>
<c:if test="${contactMill.mobile!=null}">&nbsp;${contactMill.mobile} (Mob)</c:if><br>
<c:if test="${contactMill.fax!=null}">&nbsp;${contactMill.fax} (Fax)</c:if><br>
<c:if test="${contactMill.email!=null}">&nbsp;${contactMill.email} </c:if><br>
                  </tr>
                </table>
              </fieldset></td>
              <td width="2%">&nbsp;</td>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">SHIP TO (${shipTo.san})
                 <c:if test="${shipTo.orgUnitCode!=null}">(${shipTo.orgUnitCode})</c:if></legend>
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
        
<%--Loop for Line Items -Start --%>
<c:forEach var="lineItem" items="${orderDetailForm.poHeader.lineItemCollection}" varStatus="lineInfo">
<c:set var="linePartyItemObj" />
<c:set var="linePartyContactItemObj" />

<c:forEach var="linePartyItem" items="${lineItem.linePartyCollection}" varStatus="linePartyInfo">
<c:set var="linePartyItemObj" value="${linePartyItem}"/>
<c:forEach var="linePartyContactItem" items="${linePartyItem.linePartyContactCollection}" varStatus="linePartyContactInfo">

<c:if test="${linePartyContactItem.poLineNo ==linePartyItem.poLineNo }">
<c:set var="linePartyContactItemObj" value="${linePartyContactItem}"/>
</c:if>
 </c:forEach>
 </c:forEach>
 		
 		
 		<c:forEach var="userParty" items="${userCheck.partyCollection}" varStatus="party">
 			
 			<c:choose>
 				<c:when test="">
 				</c:when>
 			</c:choose>
 		</c:forEach>
        <tr>
          <td align="left" valign="top" ><table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="titleBlue"><B>${lineInfo.index+1}. ${lineItem.productClassifDescription}
          	</B></td>
        </tr>
      </table></td>
    </tr>
  <tr>
    <td align="left" valign="top"><table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td class="titleHeading" colspan="2">MATERIAL NUMBER</td>
        <td width="35%" class="titleHeading">DESCRIPTION</td>
        <td class="titleHeading1">QTY.(LBS)</td>
        <td class="titleHeading1">DELIVERY DATE </td>
        <td class="titleHeading">SHIP TO (${linePartyItemObj.san}) <c:if test="${linePartyItemObj.orgUnitCode!=null}">(${linePartyItemObj.orgUnitCode})</c:if></td>
        <td class="titleHeading">STATUS</td>
        </tr>
      <tr>
        <td valign="top" class="lightblueColumn" colspan="2">${lineItem.productCode}</td>
        <td width="35%" valign="top" bgcolor="#F8FBFE">
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
        <td valign="top" align="right" class="lightblueColumn"><fmt:formatNumber value="${lineItem.requestedQuantity}" minFractionDigits="0" maxFractionDigits="0" pattern="#,###.##"/></td>
       
        <td valign="top" align="center" class="lightblueColumn" id="lineDlDate${lineInfo.count}">${lineItem.requestedDeliveryDate}</td>
        
        <td valign="top" class="lightblueColumn">
                       ${linePartyItemObj.name1} ${linePartyItemObj.name2} ${linePartyItemObj.name3}
                      ${linePartyItemObj.address1} ${linePartyItemObj.address2}<br>
                      ${linePartyItemObj.address3} ${linePartyItemObj.address4}<br>
                      ${linePartyItemObj.city} ${linePartyItemObj.postalCode} <br>
                      ${linePartyItemObj.state} ${linePartyItemObj.countryDetail.countryName}<br>
                      ${linePartyContactItemObj.contactFirstName} ${linePartyContactItemObj.contactLastName}<br>
                     <c:if test="${linePartyContactItemObj.phone!=null}">&nbsp;${linePartyContactItemObj.phone} (Business)</c:if><br>
                     <c:if test="${linePartyContactItemObj.mobile!=null}">&nbsp;${linePartyContactItemObj.mobile} (Mob)</c:if><br>
                     <c:if test="${linePartyContactItemObj.fax!=null}">&nbsp;${linePartyContactItemObj.fax} (Fax)</c:if><br>
                      <c:if test="${linePartyContactItemObj.email!=null}">&nbsp;${linePartyContactItemObj.email} </c:if>
                      
                      
                      </td>
        
        
        
        <td valign="top" class="lightblueColumn">${lineItem.pubUnitStatusDetail.statusDescription}</td>
       
        </tr>
      <tr>
        <td colspan="7" valign="top" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif"></td>
        </tr>
      <tr>
        <td colspan="3" valign="top" class="greyColumn"><img src="<%=request.getContextPath()%>/images/pencil.gif" alt="Update" width="18" height="19"></td>
        <td valign="top" class="greyColumn"><%--<label>
         
        <c:choose>
			<c:when test="${readOnlyForm!='T'}">
				<html-el:text property="poHeader.lineItemCollectionIdx[${lineInfo.index}].estimatedQuantity" size="10" styleClass= "textfield" value="${lineItem.estimatedQuantity}" maxlength="18"/>
			</c:when>
			<c:otherwise>
				${lineItem.estimatedQuantity}
			</c:otherwise>
		</c:choose>
          
        </label>--%></td>
        <td valign="top" class="greyColumn">
        <c:choose>
			<c:when test="${readOnlyForm!='T'}">
				<c:choose>
					<c:when test="${lineItem.estimatedDeliveryDate==null}">
						<html-el:text property="poHeader.lineItemCollectionIdx[${lineInfo.index}].estimatedDeliveryDate" value="" size="12" styleClass= "textfield" styleId="line_del_date_${lineInfo.index}" maxlength="10" readonly="true"/>
						<html-el:link href="javascript:NewCal('line_del_date_${lineInfo.index}','MMDDYYYY')">
			                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
		                </html-el:link>
					</c:when>
					<c:otherwise>
						<html-el:text property="poHeader.lineItemCollectionIdx[${lineInfo.index}].estimatedDeliveryDate" value="${lineItem.estimatedDeliveryDate}" size="12" styleClass= "textfield" styleId="line_del_date_${lineInfo.index}" maxlength="10" readonly="true"/>
						<html-el:link href="javascript:NewCal('line_del_date_${lineInfo.index}','MMDDYYYY')">
			                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
		                </html-el:link>
					</c:otherwise>
				</c:choose>	
			&nbsp;
			</c:when>
			<c:otherwise>
				<c:out value="${lineItem.estimatedDeliveryDate}"/>
			</c:otherwise>
		</c:choose>
          	
		</td>
        <td valign="top" class="greyColumn">
        <c:choose>
			<c:when test="${readOnlyForm!='T'}">
				<c:if test="${orderDetailForm.poAllLineStatus != null && not empty orderDetailForm.poAllLineStatus}">
					<html-el:select property="poHeader.lineItemCollectionIdx[${lineInfo.index}].supplierStatusId" styleClass= "textfield" >
					<html-el:option value="">Select</html-el:option>
					<c:forEach var="itemLine" items="${orderDetailForm.poAllLineStatus}">
						<c:if test="${itemLine.statusCode != 'NEW' && itemLine.statusCode != 'ORDDELIVER'}">
							<html-el:option value="${itemLine.statusId}"><c:out value="${itemLine.statusDescription}"/></html-el:option>
						</c:if>
					</c:forEach>
					</html-el:select>
				</c:if>		
			</c:when>
			<c:otherwise>
				<c:forEach var="itemLine" items="${orderDetailForm.poAllLineStatus}">
					<c:if test="${itemLine.statusId==lineItem.supplierStatusId}">
						<c:out value="${itemLine.statusDescription}"/>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		  
         </td>
         
          <td valign="top" class="greyColumn">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="7" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="18%" class="greyColumn">BUYER NOTES: </td>
            <td class="greyColumn">
            <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px; text-align:left">  
${lineItem.pubUnitComments}
 </textarea>
    </td>
          </tr>
          
        </table></td>
      </tr>
      <tr>
        <td colspan="7" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
          <tr>
            <td width="18%" class="greyColumn">YOUR COMMENTS:</td>
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
        <td colspan="7" valign="top"  height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      
    </table> </td>
    </tr>
</table></td>
        </tr>
        <tr>
          <td height="16" >&nbsp;</td>
        </tr>
</c:forEach>

<%--Loop for Line Items -End --%>
        
		
		<c:set var="roleType1" value="${userCheck.roleTypeDetail.roleType}"/>
	<c:if test="${orderPaper!='O'}">
	    <c:if test="${readOnlyForm!='T' || roleType1 == 'B' || (orderDetailForm.pocosting != 'F' && readOnlyForm1 == 'T')}"> 
	    	
	         <tr>
	          <td align="center" valign="top"><table width="80%"  border="0" cellspacing="0" cellpadding="0">
	            <tr>
	              <td><fieldset class="legendBorder">
	              <legend class="legendeTitle">COSTING </legend>
	              <table width="100%" border="0" cellpadding="0" cellspacing="1">
	                <tr>
	                  <td height="5"></td>
	                  <td></td>
	                </tr>
	                
		                <tr>
		                  <td width="75%" class="blueColumn">${orderDetailForm.poHeader.glcodeDesc} </td>
		                  <td align="right" class="lightbluePrice"></td>
		                </tr>
		                
	                <tr>
	                  <td height="20" colspan="2" align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <td width="75%" height="20" align="right" class="totalPrice">TOTAL PRICE: </td>
	                      <td align="right" class="totalPrice"> <fmt:formatNumber value="${orderDetailForm.poHeader.totalAmount}" minFractionDigits="2" maxFractionDigits="2" pattern="#,###.##"/></td>
	                    </tr>
	                  </table></td>
	                  </tr>
	              </table>
	              </fieldset></td>
	            </tr>
	          </table></td>
	        </tr>
	        
	
		 </c:if> 
	</c:if>
	
	
	<!-- for furnished order paper POs visible to buyers -->
	
 	<c:if test="${orderPaper=='O'}">
	    <c:if test="${readOnlyForm =='T' && roleType1 == 'B'}"> 
	    	
	         <tr>
	          <td align="center" valign="top"><table width="80%"  border="0" cellspacing="0" cellpadding="0">
	            <tr>
	              <td><fieldset class="legendBorder">
	              <legend class="legendeTitle">COSTING </legend>
	              <table width="100%" border="0" cellpadding="0" cellspacing="1">
	                <tr>
	                  <td height="5"></td>
	                  <td></td>
	                </tr>
	                
		                <tr>
		                  <td width="75%" class="blueColumn">${orderDetailForm.poHeader.glcodeDesc} </td>
		                  <td align="right" class="lightbluePrice"></td>
		                </tr>
		                
	                <tr>
	                  <td height="20" colspan="2" align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <td width="75%" height="20" align="right" class="totalPrice">TOTAL PRICE: </td>
	                      <td align="right" class="totalPrice"> <fmt:formatNumber value="${orderDetailForm.poHeader.totalAmount}" minFractionDigits="2" maxFractionDigits="2" pattern="#,###.##"/></td>
	                    </tr>
	                  </table></td>
	                  </tr>
	              </table>
	              </fieldset></td>
	            </tr>
	          </table></td>
	        </tr>
	        
	
		 </c:if> 
	</c:if>
	
	
	
	
		<c:if test="${orderDetailForm.poHeader.termsConditions!=null}">
		<tr>
          <td height="16">&nbsp;</td>
        </tr>
        
                 
        <tr>
          <td align="left" valign="top" class="subLinksMain">TERMS &amp; CONDITIONS: </td>
        </tr>
         
        <tr>
       
         <td align="left" valign="top">
      
    

<c:choose>
      <c:when test="${orderDetailForm.poHeader.termsConditions!= ' '}">
        <textarea class="textsmall" name="textarr" rows="20" readonly="readonly" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left" >  
          ${orderDetailForm.poHeader.termsConditions}
        </textarea>
      </c:when>
</c:choose>

        
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
		<c:if test="${readOnlyForm!='T'}">
			<input name="Button" type="button" class="buttonMain" onClick="if(validateNew(${partyCounter},${totalLineItems})){submitsuccessAction('<%=request.getContextPath()%>/purchaseorder/orderdetailmessage.do?partyM=M',this)}else{return false;}" value="Confirm Order">
		</c:if>
		<c:if test="${HistoryFlag == false}">
<%		if("F".equals(request.getParameter("order"))){%>
            <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/furnishedorderlist.do?PAGE_VALUE=${page_order_list}&party=S&page=C&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
<%		}else{%>

            <c:choose>
               <c:when test="${roleType == 'M' || roleType == 'C'}">
	             <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=M&page=C&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
	           </c:when>
               <c:otherwise> 
	          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelAction('<%=request.getContextPath()%>/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=V&page=C&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}')" value="Close">
	         </c:otherwise>
		   </c:choose>	


            
<%		}%>
                      
            <input name="Button3" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/pdf/purchaseorder.do',this)" value="Export PDF">
         
          </c:if> 
          </td>
         </tr>
         </table></label></td>
        </tr>
        <c:if test="${HistoryFlag == true}">
         <tr>
          <td><label>
          <input name="Button22" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
          </label></td>
        </tr>
        </c:if>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
       
      </html-el:form></table></td>
      
      <html-el:form action="/purchaseorder/purchaseorderlist">
      </html-el:form>
  </tr>
