<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %>  
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page import="com.pearson.pix.presentation.bookspecification.action.BookSpecForm,com.pearson.pix.business.common.PIXConstants,com.pearson.pix.exception.AppException"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' /> 
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(bookspecform.bookSpecCollection)}' />
<script language="text/javascript" src="<%=request.getContextPath()%>/js/helloform.js">
</script>
<%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.BOOKSPEC_CODE))
{
	showCheckBox = true;
}
%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>


<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Book Specification  </span></td>
 </tr>
 <html-el:form action="/bookspecification/bookspeclist.do">
 <input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
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
        <td class="text">Click on a book specification number to view it's details.  </td>
        </tr>
      
      <tr>
        <td align="right" valign="bottom">
        <img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
        <a href="<%=request.getContextPath()%>/bookspecification/bookspecfilter.do" class="subLinks">filter</a></td>
      </tr>
      

      	<tr>
        	<td height="15" align="left" valign="top">
        	<table width="100%" border="0" cellspacing="1" cellpadding="0">
        	<c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        	<c:if test="${errorMsg == null}"> 
 			<tr>
      
 <td width="10" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.bookspecform.idHidden,document.bookspecform.selectedEntry);">
          </td> 
          
 
          
            <td class="tableHeading">BOOK SPEC  NUMBER </td>
            <td class="tableHeading">ISBN</td>
            <td class="tableHeading" width="1%">PRINT NUMBER </td>
            <td class="tableHeading">POSTED DATE </td>
            <td class="tableHeading">BUYER</td>
            <td class="tableHeading">VENDOR</td>
            <td class="tableHeading">TRAN STATUS</td>
            <td class="tableHeading" width="1%">ACKNOWLEDGED</td>
          </tr>
         
            <c:set var="count" value=""/>
	        <c:forEach  var="bookspec" items="${bookspecform.bookSpecCollection}" varStatus="indexId">
	        <input type="hidden" name="querystr_${indexId.index}" value="SPEC_ID=${bookspec.specId}&SPEC_VERSION=${bookspec.specVersion}&SPEC_NO=${bookspec.specNo}">  
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
	             <html-el:checkbox  property="selectedEntry" value="${bookspec.specId}-${bookspec.specVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.bookspecform.idHidden,'${bookspec.specId}-${bookspec.specVersion}',document.bookspecform.selectedEntry);"/>
	           </c:when>
               <c:otherwise>
	            <html-el:checkbox  property="selectedEntry" value="${bookspec.specId}-${bookspec.specVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.bookspecform.idHidden,'${bookspec.specId}-${bookspec.specVersion}',document.bookspecform.selectedEntry[${indexId.index}]);"/>
	            
	          </c:otherwise>
		   </c:choose>
	          </label></td> 
	          

	         <c:set var="count" value="${indexId.count}"/> 
	         <td class="${class1}">       
             <html-el:link styleClass="${class2}" page="/bookspecification/bookspecdetail.do?SPEC_ID=${bookspec.specId}&SPEC_VERSION=${bookspec.specVersion}&PAGE_VALUE=${PAGE_VALUE}&pageFilter=${pageFilter}&specnoFilter=${specnoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}"><c:out value="${bookspec.specNo}"/></html-el:link>
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
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
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
        <a class="backNext_append" href="javascript:listfilter('${PATH}/bookspecification/bookspeclist.do?PAGE_VALUE=${prevValue}')" >
        <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" ></a> 
         <%--<html-el:link styleClass="subLinksMain" page="/bookspecification/bookspeclist.do?PAGE_VALUE=${prevValue}">prev</html-el:link>--%>
          </c:if>
          <c:if test="${nextValue!=null && nextValue!=''}">
         <a class="backNext_append" href="javascript:listfilter('${PATH}/bookspecification/bookspeclist.do?PAGE_VALUE=${nextValue}')" >
         <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0" ></a> 
		</td>
         </c:if>
         </tr>
         <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
         <input type="hidden" name="specnoFilter" value="${specnoFilter}"/> 
        <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
          <script language="javascript" >
        comapareCheckedValuesModified(document.bookspecform.idHidden.value,document.bookspecform.selectedEntry,'${checkBoxLength}',document.bookspecform.checkall);
        </script>
         
         <tr>
         <c:if test="${errorMsg == null}">
          <td colspan="2">
          <label>
           <input type="hidden" name="checksArr"> 
           <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
         
          <%
if(showCheckBox)
{
%> 
          <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">
          <input name="Button3" type="button" class="buttonMain" onClick="javascript:acceptBookSpecNew('<%=request.getContextPath()%>/bookspecification/bookspecAcceptMessage.do')" value="Acknowledge"> 
          
 <%}%>
          <input name="excel" type="button" class="buttonMain" onClick="javascript:acceptBookSpecExcelNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=bookSpec')" value="Export to Excel">
           
            <input name="excel" type="button" class="buttonMain" onClick="javascript:submitBspAction('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=bookSpecAll',this)" value="Export All to Excel">
            <input type="hidden" name="flag"/> 
            </td>
            </tr>
            </table>
          </label></td>
          
 
        </c:if> 
        </tr>
         <tr>
          <td height="35" colspan="2">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
 </html-el:form>

