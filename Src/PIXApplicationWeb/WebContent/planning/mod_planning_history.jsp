<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page language="java"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<c:set var="party" value="${poHeader.partyCollection}"/>
<c:set var="poid" value='<%=request.getParameter("poid")%>'></c:set>
<c:set var="ponumber" value='<%=request.getParameter("ponumber")%>'>
</c:set>  
  
   <html-el:form action="/planning/planninghistorylist.do">
     <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Planning - History </span></td>
    </tr>
    <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
        <td class="text">To view a Plan in details, click on the ISBN number. </td>
        </tr>
        
        <tr>
        <td width="50%"></td>
        <td align="right" valign="bottom" height="16"></td>
      	</tr>
               
      </table>
     <%-- <table width="98%" border="0" cellspacing="1" cellpadding="0">--%>
      <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>

        <c:if test="${errorMsg == null}"> 
        <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td class="tableHeading">ISBN </td>
          <td width="4px"class="tableHeading">PRINT NUMBER</td>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">BOUND BOOK DATE </td>
          <td class="tableHeading">TITLE DESCRIPTION</td>
          <td class="tableHeading">TRAN STATUS </td>
          <td class="tableHeading">PLAN POSTED BY </td>
           <td class="tableHeading">VENDOR</td>
           <td class="tableHeading">VENDOR CONTACT</td>
           <td class="tableHeading">PLAN POSTED DATE </td>
           <td class="tableHeading">ACK</td>
           <td class="tableHeading">DATE ACCEPTED</td>
        </tr>
        
          <c:forEach var="planningpo" items="${planningForm.planningCollection}" varStatus="indexId"> 
          <c:forEach var="vendor" items="${planningpo.partyCollection}" varStatus="partyCount">
			<c:set var="vendorParty" value="${vendor}"/>
		   </c:forEach>
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
          <html-el:link styleClass="${class2}" page="/planning/planninghistorydetail.do?poid=${planningpo.poId}&poversion=${planningpo.poVersion}&pono=${planningpo.poNo}"><c:out value="${planningpo.titleDetail.isbn10}"/></html-el:link>
          <td width="4px" class="${class1}"><c:out value="${planningpo.titleDetail.printNo}"/></td>
          <td class="${class1}"><c:out value="${planningpo.totalQuantity}"/></td>
          <td class="${class1}">${vendorParty.deliveryDate}</td>
          <td class="${class1}"><c:out value="${planningpo.titleDesc}"/></td>
		  <td class="${class1}"><c:out value="${planningpo.statusDetail.statusDescription}"/></td>
          <td class="${class1}"><c:out value="${planningpo.createdBy}"/></td>
          <td class="${class1}"><c:out value="${vendorParty.name2}"/></td> 
		  <td class="${class1}"><c:out value="${vendorParty.address1}"/></td> 
		  <td class="${class1}"><fmt:formatDate value="${planningpo.postedDate}" type="both" pattern="MM/dd/yy" /></td> 
		   <td class="${class1}"><c:out value="${planningpo.acknowledgeFlag}"/></td>
		   <td class="${class1}"><fmt:formatDate value="${planningpo.modDateTime}" type="both" pattern="MM/dd/yy" /></td>
          </tr>
          </c:forEach>
          <tr>
          <td height="1" colspan="12" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          </tr>
          </table>
         </c:if>
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
           
           </table>
          </c:if>  
        
          <c:if test="${errorMsg == null}"> 
          <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="15" align="right">
          <c:if test="${prevValue!=null && prevValue!=''}">
          <html-el:link styleClass="backNext_append"  page="/planning/planninghistorylist.do?PAGE_VALUE=${prevValue}&page=H&poid=${poid}&ponumber=${ponumber}">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
    	     <html-el:link styleClass="backNext_append"  page="/planning/planninghistorylist.do?PAGE_VALUE=${nextValue}&page=H&poid=${poid}&ponumber=${ponumber}">
    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></html-el:link>
    	 </c:if>
        </td>
         </tr>
         
         
        <tr>
          <td height="10" align="left" class="subLinksMain"></td>
          <td height="10" align="right"></td>
        </tr>
       
        
         </table>
          </c:if>                     
         <table> 
         
         <tr>
          <td colspan="2"><label>
            <input name="Button22" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
</label></td>
          </tr>
          <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
         </table> 
        
  </td>
  </tr>
  
     
          
    </html-el:form>  