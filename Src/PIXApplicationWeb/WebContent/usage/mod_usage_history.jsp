
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
 
 <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Usage</span></td>
 </tr>
 <tr>
 <html-el:form action="/usage/usageList"> 
     <td align="left" valign="top" class="padding23">
     <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td colspan="2" class="text">To view the Usage of a purchase order, click on the usage number. </td>
        </tr>
      <tr>
        <td height="15" colspan="2"></td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
         <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <tr>
          <td class="tableHeading">USAGE NUMBER </td>
          <td class="tableHeading">ISBN </td>
          <td class="tableHeading">PRINT NUMBER</td>
          <td class="tableHeading">ISSUE DATE </td>
          <td class="tableHeading">BUYER </td>
          <td class="tableHeading" >VENDOR</td>
          <td class="tableHeading" >VENDOR CONTACT</td>
          <td class="tableHeading">STATUS </td>
          </tr>
          <c:set var="count" value=""/>
          <c:forEach var="usageList" items="${usageFormList.usageCollection}" varStatus="indexId"> 
           <c:if test="${indexId.count%2 != 0}" >
           		<c:set var="class1" value="tableRow"/>
           		<c:set var="class2" value="tableRowlink"/>
           </c:if>
           <c:if test="${indexId.count%2 == 0}" >
           		<c:set var="class1" value="tableAlternateRow"/>
           		<c:set var="class2" value="tableAlternateRowlink"/>
           </c:if>
	        <tr>
	          <c:set var="count" value="${indexId.count}"/>
	          <td class="${class1}">
	          <html-el:link styleClass="${class2}" page="/usage/usagehistorydetail.do?poid=${usageList.poId}&poversion=${usageList.poVersion}&pono=${usageList.poNo}&rno=${usageList.releaseNo}&uid=${usageList.usageId}"><c:out value="${usageList.usageNo}"/>
	          </html-el:link></td>
	          <td class="${class1}"><c:out value="${usageList.titleDetail.isbn10}"/></td>
	          <td class="${class1}"><c:out value="${usageList.titleDetail.printNo}"/></td>
	          <td class="${class1}"><fmt:formatDate value="${usageList.issueDate}" type="both" pattern="MM/dd/yy" /></td>
	          <c:forEach var="party" items="${usageList.partyCollection}" varStatus="partyCount">  
	           <td class="${class1}"><c:out value="${party.name1}"/></td>
	           <td class="${class1}"><c:out value="${party.name2}"/></td> 
	          <td class="${class1}"><c:out value="${party.address1}"/></td>
	          </c:forEach>  
	          <td class="${class1}"><c:out value="${usageList.statusDetail.statusDescription}"/></td>
		    </tr>
          </c:forEach>
          </c:if>
          <c:if test="${errorMsg != null}">
           <tr>
        	 <td colspan="2" class="tableLine"></td>
           </tr>
           <tr>
             <td colspan="2" align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr> 
      </c:if>
        <tr>
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        <tr>
         <td height="15" align="right"> 
         <c:if test="${prevValue!=null && prevValue!=''}">
          <html-el:link styleClass="backNext_append" page="/usage/usagehistorylist.do?page=H&pno=${usageForm.poHeader.poNo}&poId=${usageForm.poHeader.poId}&PAGE_ACTION=P&PAGE_VALUE=${prevValue}">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
    	     <html-el:link styleClass="backNext_append" page="/usage/usagehistorylist.do?page=H&pno=${usageForm.poHeader.poNo}&poId=${usageForm.poHeader.poId}&PAGE_ACTION=P&&PAGE_VALUE=${nextValue}">
    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
    	   </c:if>
    	  </td>
        </tr>
        <tr>
           <td colspan="2"><label>
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
           </label></td>
           
        </tr>
        <tr>
          <td height="35" colspan="2">&nbsp;</td>
        </tr>
      </table>
    </html-el:form>
  </tr>
     
        






