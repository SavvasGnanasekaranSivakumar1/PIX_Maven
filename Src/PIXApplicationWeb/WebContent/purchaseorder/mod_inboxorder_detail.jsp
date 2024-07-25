<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/purchaseorder/orderdetail">
<%@ include file="/common/formbegin.jsp"%>
<c:set var="purchaseorderid"><%=request.getParameter("poid")%></c:set>
<c:set var="purchaseorderversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="purchaseordernumber"><%=request.getParameter("pono")%></c:set>
<c:set var="releaseno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="days"><%=request.getParameter("days")%></c:set>
<c:set var="home"><%=request.getParameter("home")%></c:set>
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:if test="${orderDetailForm.poHeader.partyCollection != null && not empty orderDetailForm.poHeader.partyCollection}">
<c:set var="readOnlyForm" value="F"/>
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
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='M'}">
			<c:set var="mill" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${mill.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactVendor" value="${contact}"/>
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
<c:set var="totalLineItems"/>
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
         
          <c:if test="${HistoryFlag == false}">
	        <tr>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt.gif" width="13" height="22"></td>
		    <td width="90" align="center" class="tabSubBg" > <span class="tabSubBgLink">Details</td>
		   <%-- <a href="<%=request.getContextPath()%>/purchaseorder/inboxorderdetail.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&days=${days}&home=${home}&rno=${releaseno}&order=P" class="tabSubBgLink">Details</a></td> --%>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt.gif" width="12" height="22"></td>
		    <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
		    <c:if test="${roleType!='M'}">
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
		    <a href="<%=request.getContextPath()%>/deliverymessage/inboxdeliverylist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&partyType=${roleType}&days=${days}&home=${home}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Delivery Message</a></td>
		    <td width="11" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="11" height="22"></td>
		    <td width="13" height="22" align="right" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_lt2.gif" width="13" height="22"></td>
		    <td width="120" align="center" class="tabSubBg2">
		    <a href="<%=request.getContextPath()%>/goodsreceipt/inboxgoodsreceiptlist.do?poid=${purchaseorderid}&poversion=${purchaseorderversion}&pono=${purchaseordernumber}&partyType=${roleType}&days=${days}&home=${home}&rno=${releaseno}&order=P&PAGE_VALUE=1" class="tabSubBgLink" >Goods Receipt</a> </td>
		    <td width="12" height="22" align="left" valign="bottom">
		    <img src="<%=request.getContextPath()%>/images/tabsub_rt2.gif" width="12" height="22"></td>
		</tr>   
          </c:if>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <c:choose>
          <c:when test="${roleType!='M'}">
        <tr>
          <td align="right">
          <c:if test="${HistoryFlag == false}">
		  <table width="38%"  border="0" cellspacing="0" cellpadding="0">
            <tr valign="middle" height="16">
              <td align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/bookspecification/pobookspecdetail.do?SPEC_ID=${bookspecdetail.specId}&SPEC_VERSION=${bookspecdetail.specVersion}','specifications','scrollbars=yes,width=600,height=650,left=200,top=10')" class="subLinksMain">specifications</a></td>
              <td width="46%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/suppliedcomponent.do?poid=${orderDetailForm.poHeader.poId}&poversion=${orderDetailForm.poHeader.poVersion}','suppliedComponent','scrollbars=yes,width=600,height=650,left=200,top=10')" class="subLinksMain">supplied component info</a></td>
<%		if("F".equals(request.getParameter("order"))){%>
            <td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/purchaseorderhistorylist.do?PAGE_VALUE=1&party=S&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}','OrderDetailHistory','scrollbars=yes,width=800,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
<%		}else{%>
            <td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/purchaseorderhistorylist.do?PAGE_VALUE=1&party=V&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}','OrderDetailHistory','scrollbars=yes,width=800,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
<%		}%>              
              
            </tr>
          </table>
          </c:if>
          </td>
        </tr>
          </c:when>
          <c:otherwise>
          <c:choose>
               <c:when test="${roleType == 'M'}">
            		<td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=1&party=M&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}&ORDER=${order}','OrderDetailHistory','scrollbars=yes,width=875,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
            	</c:when>
            	<c:otherwise>
            		<td width="18%" align="right" class="subLinksMain">&gt; <a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=1&party=S&page=H&ponumber=${orderDetailForm.poHeader.poNo}&poid=${orderDetailForm.poHeader.poId}&ORDER=${order}','OrderDetailHistory','scrollbars=yes,width=875,height=500,left=100,top=10')" class="subLinksMain">history</a></td>
            	</c:otherwise>
        </c:choose>
        </c:otherwise>
        </c:choose>
          
      <c:choose>
        <c:when test="${roleType!='M'}">
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
              <td class="lightblueColumn"> <c:out value="${buyer.deliveryDate}"/></td>
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
              <td height="23" class="blueColumn">STATUS: </td>
              <td class="lightblueColumn" colspan="5">
              	<c:forEach var="itemBuyer" items="${orderDetailForm.poAllHeaderStatus}">
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
			<c:if test="${item.statusId==vendor.statusId}">
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
				<c:when test="${vendor.deliveryDate==null}">
					<html-el:text property="poHeader.partyCollectionIdx[${partyCounter}].deliveryDate" value="" size="12" styleClass= "textfield" styleId="header_del_date" maxlength="10" readonly="true"/>
					<html-el:link href="javascript:NewCalPOHeader('header_del_date','MMDDYYYY',${totalLineItems},${partyCounter})">
		                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
	                </html-el:link>
				</c:when>
				<c:otherwise>
					<html-el:text property="poHeader.partyCollectionIdx[${partyCounter}].deliveryDate" value="${vendor.deliveryDate}" size="12" styleClass= "textfield" styleId="header_del_date" maxlength="10" readonly="true"/>
					<html-el:link href="javascript:NewCalPOHeader('header_del_date','MMDDYYYY',${totalLineItems},${partyCounter})">
		                <img src="<%=request.getContextPath()%>/images/cal.gif"	width="16" height="16" border="0" alt="Pick a date">
	                </html-el:link>
				</c:otherwise>
			</c:choose>
	    	&nbsp;
		</c:when>
		<c:otherwise>
			<c:out value="${vendor.deliveryDate}"/>
		</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td height="40" valign="middle" class="greyColumnHeading">BUYER NOTES: </td>
    <td colspan="3" valign="middle" class="greyColumnContent"> 
    <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px; text-align:left">  
     <%-- <textarea class="textsmall" readonly="readonly" rows="2" style="color: black; background-color:transparent; border:none; height:100%;overflow:hidden; width:722px;text-align:left">  --%> 
     
${buyer.comments}
 </textarea></td>
  </tr>
  <tr>
    <td height="40" valign="middle" class="greyColumnHeading">YOUR COMMENTS:   </td>
    <td height="40" colspan="3" valign="middle" class="greyColumnContent">
    <c:choose>
	    <c:when test="${readOnlyForm!='T'}">
   			<html-el:textarea  property="poHeader.partyCollectionIdx[${partyCounter}].comments" rows="2" style="width:722px" styleClass= "textfield" value="${vendor.comments}"/>
    	</c:when>
		<c:otherwise>
			${vendor.comments}
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
                <legend class="legendeTitle">VENDOR (${vendor.san})</legend>
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
                <legend class="legendeTitle">SHIP TO (${shipTo.san})</legend>
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
        <tr>
          <td align="left" valign="top" ><table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="40%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="titleBlue"><B>${lineInfo.index+1}. ${lineItem.productDescription}</B></td>
        </tr>
      </table></td>
    </tr>
  <tr>
    <td align="left" valign="top"><table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td class="titleHeading" colspan="2">PRODUCT ID</td>
        <td width="50%" class="titleHeading">DESCRIPTION</td>
        <td class="titleHeading">QTY.</td>
        <td class="titleHeading">DELIVERY DATE </td>
        <td class="titleHeading">STATUS</td>
        </tr>
      <tr>
        <td valign="top" class="lightblueColumn" colspan="2">${lineItem.productCode}</td>
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
        <td valign="top" class="lightblueColumn"><fmt:formatNumber value="${lineItem.requestedQuantity}" minFractionDigits="0" maxFractionDigits="0" pattern="#,###.##"/></td>
        <td valign="top" class="lightblueColumn">${lineItem.requestedDeliveryDate}</td>
        <td valign="top" class="lightblueColumn">${lineItem.pubUnitStatusDetail.statusDescription}</td>
        </tr>
      <tr>
        <td colspan="7" valign="top" height="1" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif"></td>
        </tr>
      <tr>
        <td colspan="4" valign="top" class="greyColumn"><img src="<%=request.getContextPath()%>/images/pencil.gif" alt="Update" width="18" height="19"></td>
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
        </tr>
      <tr>
        <td colspan="7" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="18%" class="greyColumn">BUYER NOTES: </td>
            <td class="greyColumn">
            <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px; text-align:left">  
        <%--    <textarea class="textsmall" readonly="readonly" rows="2" style="color: black; background-color:transparent; border:none; height:100%;overflow:hidden; width:722px;text-align:left">  --%>
${lineItem.pubUnitComments}
 </textarea>
    </td>
          </tr>
          
        </table></td>
      </tr>
      <tr>
        <td colspan="7" align="left" valign="top" class="greyColumn"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          
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
        
		<%-- <c:if test="${shipTo.san!=loggedInSAN}">--%>
	    <c:if test="${readOnlyForm!='T'}">
	        <c:if test="${orderDetailForm.poPriceDetails!=null and not empty orderDetailForm.poPriceDetails}">
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
	                <c:set var="totalPrice" value="0"/>
	                <c:forEach var="gl" items="${orderDetailForm.poPriceDetails}">
		                <tr>
		                  <td width="75%" class="blueColumn"> ${gl.glCode} <c:if test="${gl.glDesc!=null}">-</c:if> ${gl.glDesc}</td>
		                  <c:set var="currCode" value="${gl.currencyDetail.currencyCode}" />
		                  <td align="right" class="lightbluePrice">${gl.currencyDetail.currencyCode} <fmt:formatNumber value="${gl.price}" minFractionDigits="2" maxFractionDigits="2" pattern="#,###.##"/></td>
		                </tr>
		                <c:set var="totalPrice" value="${totalPrice+gl.price}"/>
	                </c:forEach>
	                <tr>
	                  <td height="20" colspan="2" align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                      <td width="75%" height="20" align="right" class="totalPrice">TOTAL PRICE: </td>
	                      <td align="right" class="totalPrice">${currCode} <fmt:formatNumber value="${totalPrice}" minFractionDigits="2" maxFractionDigits="2" pattern="#,###.##"/></td>
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
      
    <%--    <textarea class="textsmall" readonly="readonly"  style="color: black; background-color:transparent; border:none; height:100%;overflow:hidden; width:722px;text-align:left">  

${orderDetailForm.poHeader.termsConditions}
</textarea>  --%>
        
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
		
		
		<c:if test="${HistoryFlag == false}">
<%		if("F".equals(request.getParameter("order"))){%>
            <input name="Button2" type="button" class="buttonMain" onClick="detailSubmit('<%=request.getContextPath()%>/home/home.do')" value="Close">
<%		}else{%>
            <input name="Button2" type="button" class="buttonMain" onClick="detailSubmit('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
      
      
      <html-el:form action="/purchaseorder/purchaseorderlist">
      </html-el:form>
  </tr>
  </c:when>
  <c:otherwise>
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
              <td width="16%" class="blueColumn">QTY.(LBS): </td>
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
        <td class="titleHeading">QTY.(LBS)</td>
        <td class="titleHeading">DELIVERY DATE </td>
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
        <td valign="top" class="lightblueColumn"><fmt:formatNumber value="${lineItem.requestedQuantity}" minFractionDigits="0" maxFractionDigits="0" pattern="#,###.##"/></td>
       
        <td valign="top" class="lightblueColumn" id="lineDlDate${lineInfo.count}">${lineItem.requestedDeliveryDate}</td>
        
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
            <input name="Button2" type="button" class="buttonMain" onClick="detailSubmit('<%=request.getContextPath()%>/home/home.do')" value="Close">
<%		}else{%>

            <c:choose>
               <c:when test="${roleType == 'M' || roleType == 'C'}">
	             <input name="Button2" type="button" class="buttonMain" onClick="detailSubmit('<%=request.getContextPath()%>/home/home.do')" value="Close">
	           </c:when>
               <c:otherwise> 
	          <input name="Button2" type="button" class="buttonMain" onClick="detailSubmit('<%=request.getContextPath()%>/home/home.do')" value="Close">
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
       
      
      
      
  </tr>
  </c:otherwise>
  </c:choose>
  </html-el:form></table></td>
  
