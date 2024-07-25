
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page language="java"%>

<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%
String partyType = request.getParameter("party");
%>
<c:set var="order" value='<%=request.getParameter("ORDER")%>'/>

   <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">History - Order</span></td>
  </tr>
  <html-el:form action="/purchaseorder/purchaseorderhistorylist"> 
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="text">Following is the Order History. </td>
        </tr>
      <tr>
        <td width="50%"></td>
        <td align="right" valign="bottom" height="16"></td>
      </tr>
    </table>
       <table width="98%" border="0" cellspacing="1" cellpadding="0">
         <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <tr>
          <td class="tableHeading">ORDER NUMBER </td>
          <td class="tableHeading">RELEASE NUMBER</td>
          <td class="tableHeading">MATERIAL NUMBER</td>
          <td class="tableHeading">PAPER GRADE</td>
          <td class="tableHeading">PAPER DESCRIPTION</td>
          <td class="tableHeading">BUYER </td>
          <td class="tableHeading">VENDOR</td>
          <td class="tableHeading">VENDOR CONTACT</td>
          <td class="tableHeading">POSTED DATE</td>
          <td class="tableHeading">TRAN STATUS </td>
          
          
          
          <c:if test="${order=='P'}">
          	<td class="tableHeading">DATE ACCEPTED</td>		
          </c:if>
          <td class="tableHeading">AMENDED BY</td>
          </tr>
          <c:set var="headeroutofloop"/>	
          <c:forEach var="poHeader" items="${orderListForm.poCollection}" varStatus="indexId">
          <c:set var="headeroutofloop" value="${poHeader}"/> 
          <tr>
          <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if>   
          <td class="${class1}">
          <html-el:link styleClass="${class2}" page="/purchaseorder/millorderdetailhistory.do?ordernew=${order}&poid=${poHeader.poId}&poversion=${poHeader.poVersion}&pono=${poHeader.poNo}&rno=${poHeader.releaseNo}&page=H&partyM=M"><c:out value="${poHeader.poNo}"/></html-el:link>
          <td class="${class1}"><c:out value="${poHeader.releaseNo}"/></td>
          <td class="${class1}"><c:out value="${poHeader.titleDetail.isbn10}"/></td>
          <td class="${class1}"><c:out value="${poHeader.titleDetail.printNo}"/></td>
           <td class="${class1}"><c:out value="${poHeader.titleDesc}"/></td>
          
          <c:forEach var="party" items="${poHeader.partyCollection}" varStatus="partyCount">
	          <td class="${class1}"><c:out value="${party.name1}"/></td> 
	          <td class="${class1}"><c:out value="${party.name2}"/></td> 
	          <td class="${class1}"><c:out value="${party.address1}"/></td> 
	          </c:forEach> 
		 <%-- <td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/></td>  --%>
		 <td class="${class1}"><fmt:formatDate value="${poHeader.postedDate}" type="both" pattern="MM/dd/yy" /></td>
		 <c:choose>
          	<c:when test="${poHeader.statusDetail.statusCode == 'papcos'}">
          		<td class="${class1}"><c:out value="${poHeader.statusDetail.statusDescription}"/><img src="<%=request.getContextPath()%>/images/paper.gif" border="0" alt="Paper Assigned" ><img src="<%=request.getContextPath()%>/images/doller.gif" border="0" alt="Finalized/Costs available" ></td>   
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
   		 
   		  <c:if test="${order=='P'}">
   		  	<td class="${class1}"><fmt:formatDate value="${poHeader.modDateTime}" type="both" pattern="MM/dd/yy" /></td>
   		  </c:if>
		 <td class="${class1}">${poHeader.modifiedBy}</td>	
		  
          </tr>
          </c:forEach>
                 
		<tr>
          <td height="1" colspan="12" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
     </c:if>
     <input type="hidden" name="poidFilter" value="${poidFilter}"/> 
     <c:if test="${errorMsg != null}">
         <table width="98%"  border="0" cellspacing="1" cellpadding="0"> 
           <tr>
              <td height="1" colspan="2" class="tableLine">
              <img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
           </tr>
            <tr>
           <tr>
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
           <tr>
              <td height="1" colspan="2" class="tableLine">
              <img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
           </tr>
           <tr>
          <td height="25">&nbsp;</td>
        </tr>
           <tr>
          <td><label>
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
          </label></td>
        </tr>
        
           </table>
          </c:if>
     
     <c:if test="${errorMsg == null}"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">
          <c:if test="${prevValue!=null && prevValue!=''}">
          
           <a class="backNext" href="javascript:listfilter('${PATH}/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=${prevValue}&party=<%=partyType%>&page=H&ponumber=${headeroutofloop.poNo}&poid=${headeroutofloop.poId}')">
           <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></a> 
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
          <a class="backNext" href="javascript:listfilter('${PATH}/purchaseorder/millpurchaseorderhistorylist.do?PAGE_VALUE=${nextValue}&party=<%=partyType%>&page=H&ponumber=${headeroutofloop.poNo}&poid=${headeroutofloop.poId}')">
          <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></a> </td>
    	     </c:if>
        </tr>
        <tr>
          <td><label>
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
          </label></td>
        </tr>
        <tr>
          <td height="25">&nbsp;</td>
        </tr>
        
        
      </table></td>
  </tr>
</c:if> 

      </html-el:form>
