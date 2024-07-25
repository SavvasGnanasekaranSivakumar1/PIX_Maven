
<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>

<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<%@ page language="java"%>

<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="varparty" value='<%=request.getAttribute("partytype")%>'></c:set>
<c:set var="PAGE_VALUE_ORDER_LIST" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(orderListForm.poCollection)}' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<%
boolean poWriteAccess = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PO_CODE))
{
	poWriteAccess = true;
}
%>
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Order</span></td>
 </tr>
 <html-el:form action="/purchaseorder/purchaseorderlist"> 
 <input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td colspan="2" class="text"> Following are the Purchase Orders. You can view the Furnished Orders by clicking on the relevant tab.</td>
        </tr>
      <tr>
        <td height="15" colspan="2"></td>
        </tr>
        
      <tr>
        <td width="50%"><table border="0" cellspacing="0" cellpadding="0">
           <tr>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_lt_green.gif" width="10" height="25"></td>
            <td width="119" align="center" valign="middle" class="tabBg">Purchase Orders </td>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_rt_green.gif" width="10" height="25"></td>
            <td width="1"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
            <td width="10" valign="bottom"><img src="<%=request.getContextPath()%>/images/tab_lt_green2.gif" width="10" height="21"></td>
            <td width="119" align="center" valign="middle" class="tabBg2"><html-el:link page="/purchaseorder/furnishedorderlist.do?PAGE_VALUE=1&party=S&page=C" styleClass="tabBg2Link">Furnished Orders</html-el:link></td>
            <td width="10" valign="bottom"><img src="<%=request.getContextPath()%>/images/tab_rt_green2.gif" width="10" height="21"></td>
          </tr> 
          </table></td>
        
        <td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
        <html-el:link styleClass="subLinksMain" page="/purchaseorder/purchaseorderfilter.do">filter</html-el:link></td>
        </tr>
         <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
         <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <tr>
        
          <td width="2%" align="center" class="tableHeading">
          	<input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.orderListForm.idHidden,document.orderListForm.selectedEntry);">
          </td>
          <td class="tableHeading">ORDER NUMBER </td>
          <td class="tableHeading">ISBN/MATERIAL NO </td>
          <td width="4px" class="tableHeading">PRINT NUMBER</td>
          <c:if test="${roleType == 'B'}">
          <td class="tableHeading">PAPER GRADE</td>
          </c:if>
          <td class="tableHeading">TITLE DESCRIPTION</td>
          <td class="tableHeading">BUYER </td>
          <td class="tableHeading">VENDOR</td>
          <td class="tableHeading">VENDOR CONTACT</td>
          <td class="tableHeading">TRAN STATUS </td>
          <td class="tableHeading">GANG NAME</td>
          <td class="tableHeading">POSTED DATE</td>
          <td class="tableHeading">AMENDED BY</td>
          
          
          </tr>
          <c:set var="count" value=""/>
          <c:forEach var="poHeader" items="${orderListForm.poCollection}" varStatus="indexId">
			<input type="hidden" name="querystr_${poHeader.poId}" value="poid=${poHeader.poId}&poversion=${poHeader.poVersion}&pono=${poHeader.poNo}&rno=${poHeader.releaseNo}&order=P">
			<%--<input type="hidden" id="lineItem_${poHeader.poId}" value="${poHeader.lineItemCount}">  --%> 
	          <tr>
	          <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if>  
	          <td align="center" class="${class1}"><label>
	          
	         <c:choose>
              <c:when test="${checkBoxLength == 1}"> 
	             <html-el:checkbox  property="selectedEntry" value="${poHeader.poId}-${poHeader.poVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.orderListForm.idHidden,'${poHeader.poId}-${poHeader.poVersion}',document.orderListForm.selectedEntry);"/>
	          </c:when>
              <c:otherwise> 
	            <html-el:checkbox  property="selectedEntry" value="${poHeader.poId}-${poHeader.poVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.orderListForm.idHidden,'${poHeader.poId}-${poHeader.poVersion}',document.orderListForm.selectedEntry[${indexId.index}]);"/>
	          </c:otherwise>
		    </c:choose>	
		       
	          </label></td>
              <c:set var="count" value="${indexId.count}"/>
	          <td class="${class1}">
	          <c:choose>
	          <c:when test="${poHeader.orderType == 'O'}" >
	          <html-el:link styleClass="${class2}" page="/purchaseorder/orderdetail.do?poid=${poHeader.poId}&poversion=${poHeader.poVersion}&pono=${poHeader.poNo}&rno=${poHeader.releaseNo}&order=P&PAGE_VALUE=${PAGE_VALUE_ORDER_LIST}&page_order_list=${PAGE_VALUE_ORDER_LIST}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&jobnoFilter=${jobnoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${poHeader.orderType}&orderPaper=${poHeader.orderType}"><c:out value="${poHeader.poNo}"/></html-el:link>
	          </c:when>
	          <c:when test="${poHeader.orderType != 'O'}" >
	          <html-el:link styleClass="${class2}" page="/purchaseorder/orderdetail.do?poid=${poHeader.poId}&poversion=${poHeader.poVersion}&pono=${poHeader.poNo}&rno=${poHeader.releaseNo}&order=P&PAGE_VALUE=${PAGE_VALUE_ORDER_LIST}&page_order_list=${PAGE_VALUE_ORDER_LIST}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&jobnoFilter=${jobnoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&page=LH&orderType=${poHeader.orderType}"><c:out value="${poHeader.poNo}"/></html-el:link>
	          </c:when>
	          </c:choose>
	          <td class="${class1}"><c:out value="${poHeader.titleDetail.isbn10}"/></td>
	          <td width="4px" class="${class1}"><c:out value="${poHeader.titleDetail.printNo}"/>  </td>
	         <c:if test="${roleType == 'B'}">
	          <td class="${class1}"><c:out value="${poHeader.paperGrade}"/></td> 
	         </c:if> 
	          <td class="${class1}"><c:out value="${poHeader.titleDesc}"/></td> 
	          <c:forEach var="party" items="${poHeader.partyCollection}" varStatus="partyCount">
	          <td class="${class1}"><c:out value="${party.name1}"/></td> 
	          <td class="${class1}"><c:out value="${party.name2}"/></td> 
	          <td class="${class1}"><c:out value="${party.address1}"/></td> 
	          </c:forEach>
	                
	       <c:choose>
          	<c:when test="${poHeader.statusDetail.statusCode == 'papcos'}">
          		<td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/><img src="<%=request.getContextPath()%>/images/paper.gif" border="0" alt="Paper Assigned" >  <img src="<%=request.getContextPath()%>/images/doller.gif" border="0" alt="Finalized/Costs available" ></td>   
            </c:when>
            <c:when test="${poHeader.statusDetail.activeFlag == 'paper'}">
          		<td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/><img src="<%=request.getContextPath()%>/images/paper.gif" border="0" alt="Paper Assigned" ></td>   
            </c:when>
            <c:when test="${poHeader.statusDetail.statusCode == 'cost'}">
          		<td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/><img src="<%=request.getContextPath()%>/images/doller.gif" border="0" alt="Finalized/Costs available" ></td>   
            </c:when>
            <c:otherwise>
            	<c:if test="${poHeader.statusDetail.activeFlag != 'paper' && poHeader.statusDetail.statusCode != 'cost'}" >
              		<td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/></td>   
               </c:if>
   			</c:otherwise>
   		  </c:choose>
   		   <input type="hidden" id="orderStatus_${poHeader.poId}" value="${poHeader.statusDetail.statusDescription}"> 
	      <td class="${class1}">${poHeader.gangName}</td>
	      <td class="${class1}"><fmt:formatDate value="${poHeader.postedDate}" type="both" pattern="MM/dd/yy" /></td>	 
	      <td class="${class1}">${poHeader.modifiedBy}</td>	
          </tr>
          </c:forEach>
           </c:if>
            <c:if test="${errorMsg != null}">
          <tr>
             <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr>
          </c:if>
          <tr>
          <td height="1" colspan="13" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
    
   </table>
		    
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
          <td height="15" align="right">
          
         <c:if test="${prevValue!=null && prevValue!=''}">
          <a class="backNext" href="javascript:listfilter('${PATH}/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${prevValue}&party=V')" >
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></a>  
         </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
          <a class="backNext" href="javascript:listfilter('${PATH}/purchaseorder/purchaseorderlist.do?PAGE_VALUE=${nextValue}&party=V')" >
          <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></a> 
    	 </c:if>
         </td>
        </tr>
        <tr>
          <td height="10" align="left" class="subLinksMain"></td>
          <td height="10" align="right"></td>
        </tr>
        <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        <input type="hidden" name="ponoFilter" value="${ponoFilter}"/>
        <input type="hidden" name="jobnoFilter" value="${jobnoFilter}"/>
        <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
         <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
         <input type="hidden" name="gangNameFilter" value="${gangNameFilter}"/>
        <script language="javascript" >
        comapareCheckedValuesModified(document.orderListForm.idHidden.value,document.orderListForm.selectedEntry,'${checkBoxLength}',document.orderListForm.checkall);
        </script>
        
        <tr>
          <c:if test="${errorMsg == null}">
          <td colspan="2"><label>
           <input type="hidden" name="checksArr"> 
           
           
<table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">  
          <input name="page_order_list" type="hidden" value="${PAGE_VALUE_ORDER_LIST}">        
<%
if(poWriteAccess){
%>          
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:acceptPOsNew('<%=request.getContextPath()%>/purchaseorder/ordersAcceptMessage.do')" value="Confirm Order">
<%
}
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.ORDERSTATUS_CODE)){
%>
			
            <input name="Button" type="button" class="buttonMain" onClick="javascript:newPOStatus('<%=request.getContextPath()%>/orderStatus/orderStatusNew.do','<%=request.getParameter("party")%>')" value="New Status">
<%
}
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)){
%>
            <input name="Button2" type="button" class="buttonMain" onClick="javascript:newPOStatusDelMsgNew('<%=request.getContextPath()%>/deliverymessage/deliverymessagenew.do','<%=request.getParameter("party")%>')" value="New Delivery Message">
<%}%>
            <c:choose>
            <c:when test="${roleType == 'V'}">
            <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOExcelNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=purchase&Roll_Type=V','<%=request.getParameter("party")%>')" value="Export To Excel">
            <input name="excel" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=purchaseAll&Roll_Type=V',this)" value="Export All to Excel">
            </c:when>
            <c:otherwise>
           <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptPOExcelNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=purchase','<%=request.getParameter("party")%>')" value="Export To Excel">
            <input name="excel" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=purchaseAll',this)" value="Export All to Excel">
        	</c:otherwise>
        	</c:choose>
        </td>
            </tr>
            </table>  </label></td>
  
  </c:if>
 </tr>
 
           <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
       <tr>
       <td colspan="2" class="text">Indicators shown along with status field:${poHeader.orderType} </td>
       </tr>
       <tr>
        <td colspan="2" class="text" style="color:red;"> 
        			 <img src="<%=request.getContextPath()%>/images/doller.gif" border="0" alt="Finalized/Costs available" > - Buyer finalized the PO and costs are available. <br>

 					 <img src="<%=request.getContextPath()%>/images/paper.gif" border="0" alt="Paper Assigned" > - Paper Assigned for this title.
        </td>
         </tr>
          
       </table>
     </html-el:form>   
     
        
        
     



