<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %>  
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page language="java"%>
<%@ page import="com.pearson.pix.presentation.bookspecification.action.BookSpecForm,com.pearson.pix.business.common.PIXConstants,com.pearson.pix.exception.AppException"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

 
	<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Book Specification History </span></td>
    </tr>
     <html-el:form action="/bookspecification/bookspeclist.do">
 <%@ include file="/common/formbegin.jsp"%>
  		<% String errorMsg=(String)request.getAttribute(PIXConstants.PIX_ERROR); %>
		
  		<tr>
    	<td align="left" valign="top" class="padding23">
      	<table width="98%" border="0" cellspacing="0" cellpadding="0">
      	 <tr><td class="errorMessageText">
    		<div id="error_div" class="errorMessageText"></div>
    	</td></tr>
      	<tr> 
		</tr>
		
      	<tr>
        <td class="text">Click on a book specification number to view it's details.</td>
        </tr>
      <tr>
            <td height="1" colspan="6" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
     

      	<tr>
        	<td height="15" align="left" valign="top">
        	<table width="100%" border="0" cellspacing="1" cellpadding="0">

        	<c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        	<c:if test="${errorMsg == null}">
 
        	
          <tr>
            <td class="tableHeading">BOOK SPEC  NUMBER </td>
            <td class="tableHeading">ISBN</td>
            <td class="tableHeading">PRINT NUMBER </td>
            <td class="tableHeading">POSTED DATE </td>
            <td class="tableHeading">BUYER</td>
            <td class="tableHeading">VENDOR</td>
            <td class="tableHeading">TRAN STATUS</td>
            <td class="tableHeading" width="1%">ACKNOWLEDGED</td>
            </tr>
          <tr>
	        <c:forEach  var="bookspec" items="${bookspecform.bookSpecCollection}" varStatus="indexId">
	         <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if>   
	         <td class="${class1}">     
             <html-el:link styleClass="${class2}" page="/bookspecification/bookspechistorydetail.do?SPEC_ID=${bookspec.specId}&SPEC_VERSION=${bookspec.specVersion}">
             <c:out value="${bookspec.specNo}"/></html-el:link>
            <td class="${class1}"><c:out value="${bookspec.titleDetail.isbn10}"/></td>
            <td class="${class1}"><c:out value="${bookspec.titleDetail.printNo}"/></td>
            <td class="${class1}"><fmt:formatDate value="${bookspec.specIssueDate}" type="both" pattern="MM/dd/yyyy" /></td> 
         	<c:forEach var="party" items="${bookspec.partyCollection}" varStatus="partyCount">
         	<td class="${class1}"><c:out value="${party.name1}"/></td> 
         	<td class="${class1}"><c:out value="${party.name2}"/></td>
		  	</c:forEach> 
            <td class="${class1}"><c:out value="${bookspec.statusDetail.statusDescription}"/></td> 
            <td class="${class1}"><c:out value="${bookspec.ackflag}"/></td>
             </tr>     
        </c:forEach> 
        </c:if>
        <c:if test="${errorMsg != null}">
           <tr> 
           <td height="30px" align="center" valign="middle" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
         </c:if> 	 
              
          <tr>
            <td height="1" colspan="10" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
        	</table></td>
      </tr>
      
      
   </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
        
        <tr>
        <td height="15" align="right">
         <c:if test="${prevValue!=null && prevValue!=''}">
         <html-el:link styleClass="backNext_append" page="/bookspecification/bookspechistorylist.do?PAGE_VALUE=${prevValue}&SPEC_NO=${bookspecform.bookSpec.specNo}&page=H">
         <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" ></html-el:link>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
    	     <html-el:link styleClass="backNext_append" page="/bookspecification/bookspechistorylist.do?PAGE_VALUE=${nextValue}&SPEC_NO=${bookspecform.bookSpec.specNo}&page=H">
    	     <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0" ></html-el:link>
       	  </c:if>
         </tr>
           <tr>
          <td><input name="Button3" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close"></td>
        </tr>
          <td>
          <label></label></td>
        
         <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
 
</html-el:form>
