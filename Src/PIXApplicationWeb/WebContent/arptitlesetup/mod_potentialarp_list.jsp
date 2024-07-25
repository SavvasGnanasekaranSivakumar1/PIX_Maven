<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ page language="java"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil,java.text.DecimalFormat"%>
<c:set var="prevValue">${prevValue}</c:set>
<c:set var="nextValue">${nextValue}</c:set>
<c:set var="PAGE_VALUE" value='<%=request.getParameter("PAGE_VALUE")%>' />
<c:set var="CONTEXT_PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(potentialARPForm.potentialARPCollection)}' />
<c:set var="resultsPopUp" value="${potentialARPForm.resultsPopUp}" />
<script language="javascript" src="<%=request.getContextPath()%>/js/helloform.js"></script>  

<%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
try{

%>
   <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Potential ARP List</span></td>
  </tr>
  <tr>
 
     <html-el:form action="/potentialarp/potentialarplist.do" >  
     
     <input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
     <input type="hidden" name="statusFilter" value="${statusFilter}"/>
     <c:if test="${resultsPopUp=='true'}">
		<script type="text/JavaScript">
			// code to open result popup	
			var urlPopup='/pix/potentialarp/potentialarpaccept.do?action=openPopup';
			window.open(urlPopup,'resultpopup','width=600,height=320');
		</script>
	</c:if>
      <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">  
        <tr>
        <td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
        <html-el:link styleClass="subLinksMain" page='/potentialarp/potentialarpfilter.do?status=${statusFilter}'>filter</html-el:link></td>
        </tr>
        <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
          <tr>
          <td width="10px" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSet(this,document.potentialARPForm.idHidden,document.potentialARPForm.selectedEntry);">
          </td>
          <td class="tableHeading">ISBN</td>
          <c:if test="${potentialARPForm.status != 68}" >
          <td class="tableHeading"></td>
          </c:if>
          <td class="tableHeading">PRT NO.</td>
          <td class="tableHeading">TITLE DESCRIPTION</td>
          <td class="tableHeading">AUTHOR</td>
          <td class="tableHeading">EDITION</td>
          <td class="tableHeading">BUYER NAME</td>
          <td class="tableHeading">DATE REQUEST</td>
          <td class="tableHeading">ARP DUE DATE</td>
          <td class="tableHeading">PAGE COUNT</td>
          <td class="tableHeading">VENDOR PAGE COUNT</td>
          <td class="tableHeading">PROOF REQUESTED</td>
          <td class="tableHeading">PROOF PROVIDED</td>
          <td class="tableHeading">PROOF TYPE</td>
          <td class="tableHeading">BUYER COMMENTS</td> 
          <td class="tableHeading" >VENDOR COMMENTS</td>   
          </tr>
            
          <c:forEach var="potentialArp" items="${potentialARPForm.potentialARPCollection}" varStatus="indexId">
          <tr>
          <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	      </c:if>   
	          
 
          <td width="2px" align="center" class="${class1}"><label>
          <c:choose>
              <c:when test="${checkBoxLength == 1}"> 
	           <html-el:checkbox  property="selectedEntry" value="${potentialArp.potentialarpDetail.eventId}-${potentialArp.potentialarpDetail.titleIsbn}" onclick="javascript:addDeleteIdsFromSetNewARP(document.potentialARPForm.idHidden,'${potentialArp.potentialarpDetail.eventId}-${potentialArp.potentialarpDetail.titleIsbn}',document.potentialARPForm.selectedEntry);" />
	           </c:when>  
               <c:otherwise> 
	             <html-el:checkbox  property="selectedEntry" value="${potentialArp.potentialarpDetail.eventId}-${potentialArp.potentialarpDetail.titleIsbn}" onclick="javascript:addDeleteIdsFromSetNewARP(document.potentialARPForm.idHidden,'${potentialArp.potentialarpDetail.eventId}-${potentialArp.potentialarpDetail.titleIsbn}',document.potentialARPForm.selectedEntry);"/>
	            </c:otherwise>  
		   </c:choose>
		   </label></td>		
		   <c:set var="count" value="${indexId.count}"/>	
		   
          <td class="${class1}">	
          <c:if test="${potentialArp.specId != null}">
          <html-el:link styleClass="${class2}" page="/bookspecification/bookspecdetail.do?SPEC_ID=${potentialArp.specId}&SPEC_VERSION=${potentialArp.specVersion}&PAGE_VALUE=${PAGE_VALUE}&pageFilter=${pageFilter}&flipStatusFilter=${flipStatusFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&module=titlesetup"><c:out value="${potentialArp.potentialarpDetail.titleIsbn}"/></html-el:link>
          </c:if>
          <c:if test="${potentialArp.specId == null}">
          <c:out value="${potentialArp.potentialarpDetail.titleIsbn}"/>
          </c:if>
          </td>
          <c:if test="${potentialArp.potentialarpDetail.statusCode == 'CORRECTION'}" >    
          <td class="${class1}"><img src="<%=request.getContextPath()%>/images/errorIcon.gif" width="15" height="15"></td>
          </c:if>
          <c:if test="${potentialArp.potentialarpDetail.statusCode == 'RECEIVED'}" > 
          <td class="${class1}"></td>
          </c:if>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.printingNo}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.titleDescription}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.author}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.edition}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.buyerName}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.requestDate}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.arpDueDate}"/></td>
          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.buyerPageCount}"/></td>     
          <c:if test="${potentialARPForm.status == 68}" > 
	          <td class="${class1}"><html-el:text property="vendorPageCount" styleClass="textfield" onkeypress="return isNumberKey(event)" onblur="checkPageCount(${potentialArp.potentialarpDetail.buyerPageCount},${indexId.count})" maxlength="4" size="3"/></td>
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyReq == 1}" >
	             <td class="${class1}" align="center"><input type="checkbox" id="reviewReq${indexId.count}" checked="checked" disabled="disabled"></td>
	          </c:if>
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyReq != 1}" >
	             <td class="${class1}" align="center"><input type="checkbox" id="reviewReq${indexId.count}" disabled="disabled"></td>  
	          </c:if>
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyProvide == 1}" >
	          	<td class="${class1}" align="center"><input type="checkbox" id="isReviewProv${indexId.count}" checked="checked"></td>
	          </c:if>  
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyProvide != 1}" >  
	          	<td class="${class1}" align="center"><input type="checkbox" id="isReviewProv${indexId.count}"></td>
	          </c:if>
	          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.reviewCopyType}"/></td>
	          <td class="${class1}" style="size: 10"><c:out value="${potentialArp.potentialarpDetail.buyerComments}"/></td>
	          <td class="${class1}"><html-el:text property="comments"  styleClass="textfield" size="10" maxlength="746"/></td>
          </c:if>
          <c:if test="${potentialARPForm.status != 68}" >  
              <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.vendorPageCount}"/></td>
              <c:if test="${potentialArp.potentialarpDetail.reviewCopyReq == 1}" >  
	             <td class="${class1}" align="center"><input type="checkbox" checked="checked" disabled="disabled"></td>
	          </c:if>
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyReq != 1}" >
	             <td class="${class1}" align="center"><input type="checkbox" disabled="disabled"></td>
	          </c:if>  
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyProvide == 1}" >
	             <td class="${class1}" align="center"><input type="checkbox" checked="checked" disabled="disabled"></td>
	          </c:if>
	          <c:if test="${potentialArp.potentialarpDetail.reviewCopyProvide != 1}" >
	             <td class="${class1}" align="center"><input type="checkbox" disabled="disabled"></td>
	          </c:if>
	          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.reviewCopyType}"/></td>
	          <td class="${class1}" style="width: 2px"><c:out value="${potentialArp.potentialarpDetail.buyerComments}"/></td>  
	          <td class="${class1}"><c:out value="${potentialArp.potentialarpDetail.comments}"/></td>
	      </c:if>
          
          </tr>
          </c:forEach>          
          </c:if>
          <c:if test="${errorMsg != null}">
          <tr>
           <td height="1" colspan="12" class="tableLine">
           <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          </tr>  
          <tr>
             <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr>
          </c:if>
          <tr>
          <td height="1" colspan="18" class="tableLine">  
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>    

   </table>
       <table width="98%" border="0" cellspacing="0" cellpadding="0">
         
        <tr>
          <td width="45%" align="left" class="subLinksMain"></td>
          <td height="15" align="right">
          
         <c:if test="${prevValue!=null && prevValue!=''}">
            <a href="javascript:listfilter('${PATH}/potentialarp/potentialarplist.do?PAGE_VALUE=${prevValue}&status=${statusFilter}')" class="backNext_append">
            <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></a>  
         </c:if>
         
         <c:if test="${nextValue!=null && nextValue!=''}">
            <a href="javascript:listfilter('${PATH}/potentialarp/potentialarplist.do?PAGE_VALUE=${nextValue}&status=${statusFilter}');" class="backNext_append">
            <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></a> 
    	 </c:if>
    	 
    	 </td>
        </tr>
        
        <tr>
          <td height="10" align="left" class="subLinksMain"></td>
          <td height="10" align="right"></td>
        </tr>
        <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        <input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        <input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        <input type="hidden" name="flipStatusFilter" value="${flipStatusFilter}"/>  
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
        <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
        <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
     <tr>
    <c:if test="${errorMsg == null}">
          <td colspan="2"><label>
          <input type="hidden" name="checksArr">   
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          
<%
if(showCheckBox)
{
%>
<%}%> 
        <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">           
        <c:if test="${potentialARPForm.status == 68}" > 
        <input name="Button1" type="button" class="buttonMain" onClick="javascript:acceptARPList('<%=request.getContextPath()%>/potentialarp/potentialarpaccept.do')" value="Flip Title">
        <input name="Button2"  type="button" class="buttonMain" onClick="javascript:rejectARPList('<%=request.getContextPath()%>/potentialarp/potentialarpreject.do')" value="Flip Title With Changes">
        </c:if>  
        <input name="Button3"  type="button" class="buttonMain" onClick="javascript:acceptPotentialExcel('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=titleSetup&statusCode=${potentialARPForm.status}')" value="Export to Excel">
        <input name="Button4"  type="button" class="buttonMain" onClick="javascript:acceptPotentialExcelAll('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=titleSetupAll&statusCode=${potentialARPForm.status}')"  value="Export All to Excel">   
        
        <input type="hidden" name="flag"/></td>
        </tr>
        </table>
      </label></td>

        </c:if>
		 </tr>
		   
		<tr>
          <td height="35" colspan="2">&nbsp;</td>
        </tr>
      </table>
     </html-el:form>
  
                  
<%}catch(Exception e)             
{
   e.printStackTrace();
}
%>      
  

