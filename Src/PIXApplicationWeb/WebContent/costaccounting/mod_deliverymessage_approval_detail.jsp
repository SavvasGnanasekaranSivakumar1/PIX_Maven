<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/fileUploading.tld" prefix="fileUploading"%>
<%@ page import="com.pearson.pix.presentation.costaccounting.action.CostAccountingForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />
<c:set var="msgNo" value='<%=request.getParameter("msgNo")%>' />
<c:set var="lineItemSize" value='${fn:length(costAccountingForm.deliveryMessage.deliveryMsgLineCollection)} ' />
<c:set var="poNoFilter" value='<%=request.getParameter("poNoFilter")%>' /> 
<c:set var="materialNoFilter" value='<%=request.getParameter("materialNoFilter")%>' /> 
<c:set var="delMsgNoFilter" value='<%=request.getParameter("delMsgNoFilter")%>' /> 
<c:set var="millMerchantFilter" value='<%=request.getParameter("millMerchantFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' /> 
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>' /> 
<c:set var="msgId" value='<%=request.getParameter("MSG_ID")%>' />
<c:set var="ownrshpMode" value='<%=request.getParameter("ownrshpMode")%>' />




<c:set var="onceFlag" value="0" />
<c:set var="rtFlagOutsideForEach" value="0" />

<c:set var="headerOrLineCollection" />
   <c:choose>
   <c:when test="${orderPaper == 'O'}">
   <c:set var="poLineCheck"/>
   <c:forEach var="deliveryMessageLine" items="${costAccountingForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
     <c:if test="${indexId.index==0}">
    <c:set var="headerOrLineCollection" value="${deliveryMessageLine.linePartyCollection}"/>
    </c:if>
   </c:forEach>
	  </c:when>
  </c:choose>	


<html-el:form action="/costaccounting/deliverymessagedetail"> 
<%@ include file="/common/formbegin.jsp"%>
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  ${costAccountingForm.deliveryMessage.poNo}
    
    
    </span>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order")))
{
%>
<tr>



	<c:if test="${ownrshpMode == 'OWNED ON DELIVERY'}">
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Delivery Message  No. <c:out value="${costAccountingForm.deliveryMessage.msgNo}"/> </span>. To go back to the delivery message listing, click on the <span class="textBlue">close</span> button. To create a new delivery message click on the <span class="textBlue">new delivery message </span> button. </td>
     </c:if>
     <c:if test="${(ownrshpMode == 'TRUE CONSIGNMENT') || (ownrshpMode == 'CONSIGNMENT')}">
     
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Goods Receipt  No. <c:out value="${costAccountingForm.deliveryMessage.msgNo}"/> </span>. To go back to the goods receipt  listing, click on the <span class="textBlue">close</span> button. To create a new goods receipt click on the <span class="textBlue">new delivery message </span> button. </td>
     </c:if>
      </tr>
<%		}else{%>
       <tr>
       <c:if test="${ownrshpMode == 'OWNED ON DELIVERY'}">
        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Delivery Message  No. <c:out value="${costAccountingForm.deliveryMessage.msgNo}"/> </span>. To go back to the delivery message listing, click on the <span class="textBlue">close</span> button.</td>
       </c:if>
        <c:if test="${(ownrshpMode == 'TRUE CONSIGNMENT') || (ownrshpMode == 'CONSIGNMENT')}">

        <td height="15" colspan="2" class="text">Following are the details of the <span class="textBold">Goods Receipt  No. <c:out value="${costAccountingForm.deliveryMessage.msgNo}"/> </span>. To go back to the goods receipt  listing, click on the <span class="textBlue">close</span> button. </td>
     	</c:if>
      </tr>
<%		}%>    
      <td>
        <div id="error_div" class="errorMessageText"></div>
      </td>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
      <tr>
        
        
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine">
        <img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
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
              <td class="lightblueColumn"><c:out value="${costAccountingForm.deliveryMessage.msgTypeDetail}"/></td>
            </tr>
          </table></td>
        </tr>
        <tr>
        <c:forEach var="party" items="${headerOrLineCollection}" varStatus="indexId">     
          <td><fieldset class="legendBorder">
          <legend class="legendeTitle">SHIP TO (${party.san}) 
          		<c:choose>
          			<c:when test="${party.orgUnitCode !=null}">
          				(${party.orgUnitCode})
          			</c:when>
          		</c:choose>	
          </legend>
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
             <c:set var="contactCollectionValue"/>
             <c:choose>
            <c:when test="${orderPaper == 'O'}">
         <c:set var="contactCollectionValue" value="${party.linePartyContactCollection}"/>
	     </c:when>
	     
  </c:choose>	
        
			<c:forEach var="contact" items="${contactCollectionValue}" varStatus="indexId">
							
              <td class="textLegend"><span class="textLegendBold"> -</span> <c:out value="${contact.contactFirstName}"/><br>
				<c:if test="${contact.phone!=null}">&nbsp;${contact.phone} (Business)</c:if><br>
				<c:if test="${contact.mobile!=null}">&nbsp;${contact.mobile} (Mobile)</c:if><br>
				<c:if test="${contact.fax!=null}">&nbsp;${contact.fax} (Fax)</c:if><br>
				<c:if test="${contact.email!=null}">&nbsp;${contact.email} </c:if><br>
			</td>
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
          <td width="10px" align="center" class="tableHeading">
         <%-- <input name="checkall" type="checkbox" value="checkbox" onclick="checkUncheckAll(this)"> --%>
          </td>
          <td width="2%" class="tableHeading">PO LINE NO.</td>
           <c:choose>
   				<c:when test="${orderPaper == 'O'}">
   					<td width="5%" class="tableHeading">MATERIAL NUMBER </td>
          			<td width="10%" class="tableHeading">MATERIAL DESCRIPTION </td>
          		</c:when>
          		
          	</c:choose>
          <td class="tableHeading" width="8%">ORIGINAL QTY</td>
          <c:if test="${orderPaper == 'O'}">
          	<td class="tableHeading" width="6%">TOTAL DELIVERED</td>
          </c:if>
          <td class="tableHeading" width="6%">DELIVERED QTY</td>
          <td class="tableHeading" width="6%">REQUESTED DELIVERY DATE </td>
           <td class="tableHeading" width="6%">SHIP DATE </td>
          <td class="tableHeading" width="6%">RECEIVED QTY </td>
          <td class="tableHeading" width="6%">OWNING QTY</td>
          <td class="tableHeading" width="6%">OWNED/TO BE OWNED QTY </td>
          <script>
          </script>
          <td class="tableHeading" width="6%">OWNERSHIP MODE </td>
          
          
          <td class="tableHeading" width="6%">STATUS </td>
          <c:if test="${orderPaper == 'O'}">
          	<td class="tableHeading" width="6%">BILL OF LADING </td>
          </c:if>
           <td class="tableHeading">ON USAGE </td>
            <td class="tableHeading">AFTER  X DAYS </td>
        </tr>
        
        <c:set var="deliveryMessageDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="deliveryMessageLine" items="${costAccountingForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
	      
       
        <c:set var="rtFlag" value="${deliveryMessageLine.rtFlag}"/>
 <%--        
  <script>
         	alert('${deliveryMessageLine.fileExists}');
         </script>
 <script>
        alert('RTF');
         	alert('${rtFlag}');
         </script>
  --%>      <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
        <input type="hidden" name="changedelQuant" id="changeQuantity"/>
        <c:if test="${lineNo%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
         
         
         
         <c:choose>
         <c:when test="${deliveryMessageLine.approvalFlag=='N'}">
	         <tr>
	         <input type="hidden" name="maxAmtAllow" value="${deliveryMessageLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
	         <td valign="top" class="${class1}">
	           	<html-el:checkbox  property="selectedEntry" value="${msgId}-${deliveryMessageLine.lineNo}"/>
	         </td>
	         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
	         <c:choose>
	   				<c:when test="${orderPaper == 'O'}">
	   					<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productCode}"/></td>
	         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineDecription}"/></td>
	         		</c:when>
	         		
	         </c:choose>
	         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.balanceQuantity}"></fmt:formatNumber> </td>
	         
	         <c:if test="${orderPaper == 'O'}">
	         	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.postedQuantity}"></fmt:formatNumber><input type="hidden" name="postedQuan" value="${deliveryMessageLine.postedQuantity}" id="postedQuantity${indexId.index}"/> </td>	
	         </c:if>
	         <td valign="top" class="${class1}">
	         	<fmt:formatNumber pattern="#,###,##0"  value="${deliveryMessageLine.deliveredQuantity}" ></fmt:formatNumber>
	         	<%--<html-el:text property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].deliveredQuantity" value="${deliveryMessageLine.deliveredQuantity}" size="12" styleClass= "textfield" styleId="del_quantity${indexId.index}" maxlength="10" onkeypress="return numbersonlyNew1('${indexId.index}')" onchange="addQuanChange('${indexId.index}');"/>--%>
	          </td>
	          
	         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
	         <td valign="top" class="${class1}">
	          <c:if test="${deliveryMessageLine.ownrshpMode != 'TRUE CONSIGNMENT'}">
	         	<html-el:text property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].estimatedDeliveryDate" value="${deliveryMessageLine.estimatedDeliveryDate}" size="12" styleClass= "textfield" styleId="del_date${indexId.index}" maxlength="10" onchange="addQuanChange('${indexId.index}');" readonly="true"/>
	         	<a href="javascript:NewCal('del_date${indexId.index}','MMDDYYYY')" onclick="addQuanChange('${indexId.index}');"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0"  alt="Pick a date"> </a>
	         </c:if>
	         <c:if test="${deliveryMessageLine.ownrshpMode == 'TRUE CONSIGNMENT'}">
	         	<!--<html-el:text property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].estimatedDeliveryDate" value="${deliveryMessageLine.estimatedDeliveryDate}" size="12" styleClass= "textfield" styleId="del_date${indexId.index}" maxlength="10" onchange="addQuanChange('${indexId.index}');" readonly="true"/>-->
	         	<c:out value="${deliveryMessageLine.estimatedDeliveryDate}"/>
	         </c:if>
	         </td>
	         
	         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.receivedQuantity}"></fmt:formatNumber> </td>
	         <td valign="top" class="${class1}">
	          <c:if test="${deliveryMessageLine.ownrshpMode != 'TRUE CONSIGNMENT'}">
	          <c:choose>
	          <c:when test="${deliveryMessageLine.approvalFlag=='Y'}">
	         			<fmt:formatNumber pattern="#,###,##0"   value="${deliveryMessageLine.owningQuantity}"></fmt:formatNumber>
	         		</c:when>
	         		<c:when test="${deliveryMessageLine.approvalFlag=='N'}">
	         			<html-el:text property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].owningQuantity" value="${deliveryMessageLine.owningQuantity}" size="12" styleClass= "textfield" styleId="del_quantity${indexId.index}" maxlength="10" onkeypress="return numbersonlyNew1('${indexId.index}')" onchange="addQuanChange('${indexId.index}');"/>
	         		</c:when>
	         	</c:choose>
	         </c:if>
	         <c:if test="${deliveryMessageLine.ownrshpMode == 'TRUE CONSIGNMENT'}">
	         <fmt:formatNumber pattern="#,###,##0"   value="${deliveryMessageLine.owningQuantity}"></fmt:formatNumber>
	         </c:if></td>
	         <input type="hidden" name="initialQauntityHidd"  id="initialQauntityHidd${indexId.index}"/>
	         <script language="javascript">
	         setInitialQauntity('del_quantity${indexId.index}','initialQauntityHidd${indexId.index}');
	         </script>
				
			
				
				

<%--        	<script>
         	alert('${deliveryMessageLine.ownrshpMode}');
         </script>
--%>
         <c:if test="${rtFlag > 0}">
         	<c:if test="${onceFlag == 0}">
         		<c:set var="onceFlag" value="1" />
         		<c:set var="rtFlagOutsideForEach" value="1" />
         	</c:if>
         
          <td valign="top" class="${class1}" align="left">
          <a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','${msgNo}', '${deliveryMessageLine.ownrshpMode}','${pono}');" >
          ${deliveryMessageLine.ownedQuantity} / ${deliveryMessageLine.toBeOwnedQuantity}</a></td>
         
	     </c:if>
	         
	     <c:if test="${rtFlag == 0}">
         <script>
         </script>

	     	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.ownedQuantity}"></fmt:formatNumber> / <fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.toBeOwnedQuantity}"></fmt:formatNumber> </td>
	     </c:if>    



<%--	         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.ownedQuantity}"></fmt:formatNumber> / <fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.toBeOwnedQuantity}"></fmt:formatNumber> </td>
--%>
	         <td valign="top" class="${class1}">${deliveryMessageLine.ownrshpMode}</td>
	         <td valign="top" class="${class1}">
	         	<c:choose>
	         		<c:when test="${deliveryMessageLine.approvalFlag=='N'}">
	         			Pending Approval
	         		</c:when>
	         		<c:when test="${deliveryMessageLine.approvalFlag=='Y'}">
	         			Approved
	         		</c:when>
	         	</c:choose>
	         </td>
 <%--	         <script>
					alert('For File Exists');
	         		alert('Ownrshp Mode: ${deliveryMessageLine.ownrshpMode}');
	         		alert('Paper Type: ${orderPaper}');
	         		alert('File Exists: ${deliveryMessageLine.fileExists}');
	         	</script>
--%>	         <c:if test="${orderPaper == 'O'}">
	         	<c:choose>
	         		<c:when test="${deliveryMessageLine.fileExists!='0'}">
<%--		<script>
	         		alert('Roll DM Exists : ${deliveryMessageLine.rollDMExists}');
	         		alert('File Exists: ${deliveryMessageLine.fileExists}');
	         		alert('MSG NO.: ${costAccountingForm.deliveryMessage.msgNo}');
	         	</script>
--%>	         	
	         		    <fileUploading:DMFileSubFolder id="subfolderName" msgId="${msgId}" lineNo="${deliveryMessageLine.lineNo}"/>
	         	<td valign="top" class="${class1}"><a href="#" onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}&DMGRMsgNo=${costAccountingForm.deliveryMessage.msgNo}&roleTracking=${deliveryMessageLine.rollDMExists}&operation=rollDetailsHistory&showRLDToCU=yes','DownLoadFile','scrollbars=yes,width=1000,height=600,left=0,top=0')" class="subLinksMain"><c:out value="download files"/></a></td>
	         	<%-- 		<td valign="top" class="${class1}"><a href="#" onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}&DMGRMsgNo=${costAccountingForm.deliveryMessage.msgNo}&roleTracking=${rtFlag}&operation=rollDetailsHistory&showRLDToCU=yes','DownLoadFile','scrollbars=yes,width=1000,height=600,left=0,top=0')" class="subLinksMain"><c:out value="download files"/></a></td>  --%>
	         	
	         		</c:when>
	         		<c:otherwise>
	         			<td valign="top" class="${class1}">&nbsp;</td>
	         		</c:otherwise>
	         	</c:choose>
	          </c:if>
	          <c:if test="${deliveryMessageLine.ownrshpMode == 'CONSIGNMENT'}">
	         			<td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag"  value="U" ></html-el:radio></td>
	        		    <td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag" value="X" ></html-el:radio></td>
	        	</c:if>	
	        <c:if test="${deliveryMessageLine.ownrshpMode != 'CONSIGNMENT'}">
	         			<td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag" value="U" disabled="true"></html-el:radio></td>
	        		    <td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag" value="" disabled="true"></html-el:radio></td>
	        </c:if>	    
	         
	         </tr>
	      </c:when>
	      
	      <c:when test="${deliveryMessageLine.approvalFlag=='Y'}">
	      	 <tr>
	      	 <input type="hidden" name="maxAmtAllow" value="${deliveryMessageLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
	         <td class="${class1}">
	         	&nbsp;
	         </td>
	         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
	         
	         <c:choose>
	   				<c:when test="${orderPaper == 'O'}">
	   					<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productCode}"/></td>
	         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineDecription}"/></td>
	         		</c:when>
	         		
	         </c:choose>
	         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.balanceQuantity}"></fmt:formatNumber> </td>
	         
	         <c:if test="${orderPaper == 'O'}">
	         	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.postedQuantity}"></fmt:formatNumber> <input type="hidden" name="postedQuan" value="${deliveryMessageLine.postedQuantity}" id="postedQuantity${indexId.index}"/></td>	
	         </c:if>
	         <td valign="top" class="${class1}">
	         	<fmt:formatNumber pattern="#,###,##0"  value="${deliveryMessageLine.deliveredQuantity}"></fmt:formatNumber>
	          </td>
	       
	         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
	         <td valign="top" class="${class1}">
	         	<html-el:hidden property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].estimatedDeliveryDate" value="${deliveryMessageLine.estimatedDeliveryDate}" styleId="del_date${indexId.index}" />
	         	${deliveryMessageLine.estimatedDeliveryDate}
	         	
	         </td>
	         
	       	 <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.receivedQuantity}"></fmt:formatNumber> </td>
	         <td valign="top" class="${class1}">
	         <!--<c:if test="${deliveryMessageLine.ownrshpMode != 'TRUE CONSIGNMENT'}">
	         <html-el:text property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].owningQuantity" value="${deliveryMessageLine.owningQuantity}" size="12" styleClass= "textfield" styleId="del_quantity${indexId.index}" maxlength="10" onkeypress="return numbersonlyNew1('${indexId.index}')" onchange="addQuanChange('${indexId.index}');"/>
	         </c:if>
	         <c:if test="${deliveryMessageLine.ownrshpMode == 'TRUE CONSIGNMENT'}">-->
	         <fmt:formatNumber pattern="#,###,##0"  value="${deliveryMessageLine.owningQuantity}"></fmt:formatNumber>
	         <!--</c:if>-->
	         </td>
	           <input type="hidden" name="initialQauntityHidd"  id="initialQauntityHidd${indexId.index}"/>
	         <script language="javascript">
	         setInitialQauntity('del_quantity${indexId.index}','initialQauntityHidd${indexId.index}');
	         </script>
	         
         
         <c:if test="${rtFlag > 0}">
         <script>
         </script>
          <td valign="top" class="${class1}" align="left">
          <a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','${msgNo}', '${deliveryMessageLine.ownrshpMode}');" >
          ${deliveryMessageLine.ownedQuantity} / ${deliveryMessageLine.toBeOwnedQuantity}</a></td>



<%--          <td valign="top" class="${class1}" align="left">
          <a href="#" class="tableRowLink noBackground" onClick="javascript:dmDetailspopUp('<%=request.getContextPath()%>','path','123','${costaccountdm.poId}','${costaccountdm.poVersion}','${costaccountdm.msgNo}');" >
          ${deliveryMessageLine.ownedQuantity}"> / ${deliveryMessageLine.toBeOwnedQuantity}</a></td>
         
 	         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.ownedQuantity}"></fmt:formatNumber> / <fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.toBeOwnedQuantity}"></fmt:formatNumber> </td>
--%>	     </c:if>
	         
	     <c:if test="${rtFlag == 0}">
	     <script>
         </script>
	     	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.ownedQuantity}"></fmt:formatNumber> / <fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.toBeOwnedQuantity}"></fmt:formatNumber> </td>
	     </c:if>    
	         
	         <td valign="top" class="${class1}">${deliveryMessageLine.ownrshpMode}</td>
	         <td valign="top" class="${class1}">
	         	<c:choose>
	         		<c:when test="${deliveryMessageLine.approvalFlag=='N'}">
	         			Pending Approval
	         		</c:when>
	         		<c:otherwise>
	         			Approved
	         		</c:otherwise>
	         	</c:choose>
	         </td>
	         <c:if test="${orderPaper == 'O'}">
	         	
	         	<c:choose>
	         		<c:when test="${deliveryMessageLine.fileExists!='0'}">
	         		 <fileUploading:DMFileSubFolder id="subfolderName" msgId="${msgId}" lineNo="${deliveryMessageLine.lineNo}"/>
<%-- 		<script>
	         		alert('Roll DM Exists : ${deliveryMessageLine.rollDMExists}');
	         		alert('File Exists: ${deliveryMessageLine.fileExists}');
	         		alert('MSG NO.: ${costAccountingForm.deliveryMessage.msgNo}');
	         	</script>
--%>	         	
	         		    <fileUploading:DMFileSubFolder id="subfolderName" msgId="${msgId}" lineNo="${deliveryMessageLine.lineNo}"/>
	         	<td valign="top" class="${class1}"><a href="#" onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}&DMGRMsgNo=${costAccountingForm.deliveryMessage.msgNo}&roleTracking=${deliveryMessageLine.rollDMExists}&operation=rollDetailsHistory&showRLDToCU=yes','DownLoadFile','scrollbars=yes,width=1000,height=600,left=0,top=0')" class="subLinksMain"><c:out value="download files"/></a></td>

	         <%-- 			<td valign="top" class="${class1}"><a href="#" onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}','DownLoadFile','scrollbars=yes,width=800,height=410,top=10')" class="subLinksMain"><c:out value="download files"/></a></td>  --%>
	         		</c:when>
	         		<c:otherwise>
	         			<td valign="top" class="${class1}">&nbsp;</td>
	         		</c:otherwise>
	         	</c:choose>
	         </c:if>
	         <c:if test="${deliveryMessageLine.ownrshpMode == 'CONSIGNMENT'}">
	         			<td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag"  value="U" ></html-el:radio></td>
	        		    <td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag"  value="X"></html-el:radio></td>
	        </c:if>	
	        <c:if test="${deliveryMessageLine.ownrshpMode != 'CONSIGNMENT'}">
	         			<td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag" value="U" disabled="true"></html-el:radio></td>
	        		    <td valign="top" class="${class1}"><html-el:radio property="deliveryMessage.deliveryMsgLineCollectionIdx[${indexId.index}].consignmentFlag" value="" disabled="true"></html-el:radio></td>
	        </c:if>	    
	         
	         </tr>
	      </c:when>
	     </c:choose>
         </c:forEach>
          </c:if> 
         <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" height="30px" valign="middle" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if>
         
          <tr>
          <c:choose>
          <c:when test="${orderPaper == 'O'}">
          	<td height="1" colspan="17" class="tableLine"/>
          </c:when>
          <c:otherwise>
          	<td height="1" colspan="8" class="tableLine"/>
          </c:otherwise>
          </c:choose>
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
          <td id="buttons3" class="tabSelectTextleft">
          
         
        <input type="hidden" name="poNoFilter" value="${poNoFilter}"/> 
        <input type="hidden" name="materialNoFilter" value="${materialNoFilter}"/>
        <input type="hidden" name="delMsgNoFilter" value="${delMsgNoFilter}"/>
        <input type="hidden" name="millMerchantFilter" value="${millMerchantFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        <input type="hidden" name="pono" value="${costAccountingForm.deliveryMessage.poNo}"/>
         <input type="hidden" name="request_time" value="<%=System.currentTimeMillis()%>"/>







		<input type="hidden" name="checksArr">

        <input name="page_order_list" type="hidden" value="${page_order_list}">
        
         <c:if test="${deliveryMessageLine.approvalFlag !='Y'}">
        <input name="Button5" type="button" class="buttonMain" onClick="return submitApprove('<%=request.getContextPath()%>/costaccounting/dmDetailApproval.do?MSG_ID=${msgId}&poid=${poid}&poversion=${poversion}&PAGE_VALUE=${PAGE_VALUE}&pono=${pono}&orderPaper=O&ownrshpMode=${ownrshpMode}&msg_No=${costAccountingForm.deliveryMessage.msgNo}','${lineItemSize}','${ownrshpMode}')" value="Approve">
        </c:if>
        
         <c:if test="${ownrshpMode != 'TRUE CONSIGNMENT' && ownrshpMode != 'CONSIGNMENT' }">
         <input name="Button3" type="button" class="buttonMain" onClick="submitUpdateNew('<%=request.getContextPath()%>/costaccounting/deliverymessageupdate.do?MSG_ID=${msgId}&poid=${poid}&poversion=${poversion}&ownrshpMode=${ownrshpMode}&pono=${pono}&msgNo=${msgNo}&PAGE_VALUE=${PAGE_VALUE}&orderPaper=O&msg_No=${costAccountingForm.deliveryMessage.msgNo}','${lineItemSize}','${ownrshpMode}')" value="Update">
        </c:if>
         <c:if test="${(ownrshpMode == 'TRUE CONSIGNMENT') || (deliveryMessageLine.approvalFlag=='Y')}">
        <input name="Button3" type="button" class="buttonMain" value="Update" disabled="true">
        </c:if>
        <c:choose>
        	<c:when test="${orderPaper=='O'}">
        		
        		<input name="Button2" type="button" class="buttonMain" onClick="submitClose('<%=request.getContextPath()%>/costaccounting/approvallist.do?PAGE_VALUE=${PAGE_VALUE}')" value="Close">
        	</c:when>
        	
        </c:choose>
    
      
           <input name="Button4" type="button" class="buttonMain" onClick="submitdelPdf('<%=request.getContextPath()%>/pdf/costdeliverymessagepdf.do?msg_No=${costAccountingForm.deliveryMessage.msgNo}')" value="Export PDF">
           <%--<input name="Button4" type="button" class="buttonMain" onClick="submitdelPdf('<%=request.getContextPath()%>/pdf/deliverymessagepdf.do')" value="Export PDF">--%>
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
