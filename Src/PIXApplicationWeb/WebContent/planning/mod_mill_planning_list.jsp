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
<c:set var="checkBoxLength" value='${fn:length(planningForm.planningCollection)}' />
<script language="javascript" src="<%=request.getContextPath()%>/js/helloform.js">
</script> 


<%
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
%>

  
  
   <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Planning</span></td>
  </tr>
  <tr>
 
     <html-el:form action="/planning/planninglist.do" >
     
     <input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
     
      <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
        <td class="text">To view a Plan in details, click on the PLAN NUMBER.</td>
        </tr>
            
        <tr>
        <td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
        <html-el:link styleClass="subLinksMain" page="/planning/millplanningfilter.do">filter</html-el:link></td>
        </tr>

        <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <%--<table width="100%" border="0" cellspacing="1" cellpadding="0">--%>
          <tr>
           
        
          <td width="10px" align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.planningForm.idHidden,document.planningForm.selectedEntry);">
          </td>
          <td width="4px" class="tableHeading">PLAN NUMBER </td>
          <td width="4px" class="tableHeading">MATERIAL NUMBER </td>
          <td class="tableHeading">PAPER GRADE</td>
          <td width="4px" class="tableHeading">QUANTITY(LB/SH)</td>
          <td width="4px" class="tableHeading">DELIVERY DATE </td>
          <td width="4px" class="tableHeading">MATERIAL DESCRIPTION</td>
          <td width="4px" class="tableHeading">TRAN STATUS </td>
          <td width="4px" class="tableHeading">PLAN POSTED BY </td>
           <td width="4px" class="tableHeading">MILL/MERCHANT</td>
           <td width="4px" class="tableHeading">MILL/MERCHANT CONTACT</td>
           <td width="4px" class="tableHeading">PLAN POSTED DATE </td>
           
          </tr>
        
          <c:forEach var="planningpo" items="${planningForm.planningCollection}" varStatus="indexId"> 
           <c:forEach var="vendor" items="${planningpo.partyCollection}" varStatus="partyCount">
			<c:set var="vendorParty" value="${vendor}"/>
		   </c:forEach>
		   
           <input type="hidden" name="querystr_${indexId.index}" value="poid=${planningpo}&poversion=${planningpo.poVersion}&pono=${planningpo.poNo}$deldate=$poHeader.&rno=${planningpo.releaseNo}">  
	          <tr>
	          <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if>   
	          
	          <td width="10px" align="center" class="${class1}"><label>
	        
	          <c:choose>
              <c:when test="${checkBoxLength == 1}"> 
	           <html-el:checkbox  property="selectedEntry" value="${planningpo.poId}-${planningpo.poVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.planningForm.idHidden,'${planningpo.poId}-${planningpo.poVersion}',document.planningForm.selectedEntry);"/>
	           </c:when>
               <c:otherwise> 
	             <html-el:checkbox  property="selectedEntry" value="${planningpo.poId}-${planningpo.poVersion}" onclick="javascript:addDeleteIdsFromSetNew(document.planningForm.idHidden,'${planningpo.poId}-${planningpo.poVersion}',document.planningForm.selectedEntry[${indexId.index}]);"/>
	            </c:otherwise>
		       </c:choose>	
	          </label></td>
         
             
         
          <c:set var="count" value="${indexId.count}"/>
          <td class="${class1}" align="center">
          	<html-el:link styleClass="${class2}" page="/planning/millplanningdetail.do?poid=${planningpo.poId}&poversion=${planningpo.poVersion}&pono=${planningpo.poNo}&PAGE_VALUE=${PAGE_VALUE}&roleType=M&pageFilter=${pageFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}"><c:out value="${planningpo.poNo}"/></html-el:link>
          </td>
          <td class="${class1}"> 
          	${planningpo.titleDetail.isbn10}
          </td>
          <td class="${class1}"><c:out value="${planningpo.titleDetail.printNo}"/></td>
          
		  <td class="${class1}" align="right"><fmt:formatNumber pattern="#,###,##0" value="${planningpo.totalQuantity}"></fmt:formatNumber> </td>
      <%--   <td class="${class1}">  <c:out value="${planningpo.totalQuantity}"/></td> --%>
          <td class="${class1}">${vendorParty.deliveryDate}</td>
         
          <td class="${class1}" ><c:out value="${planningpo.titleDesc}"/></td>
          <td class="${class1}"><c:out value="${planningpo.statusDetail.statusDescription}"/></td>
          <td class="${class1}"><c:out value="${planningpo.createdBy}"/></td>
          <td class="${class1}"><c:out value="${vendorParty.name2}"/></td> 
		  <td class="${class1}"><c:out value="${vendorParty.address1}"/></td>  
          <td class="${class1}"><fmt:formatDate value="${planningpo.postedDate}" type="both" pattern="MM/dd/yy" /></td>
          
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
          <td height="1" colspan="12" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
    
   </table>
         
       <table width="98%" border="0" cellspacing="0" cellpadding="0">
         
        <tr>
          <td width="45%" align="left" class="subLinksMain"></td>
          <td height="15" align="right">
          
         <c:if test="${prevValue!=null && prevValue!=''}">
         <a href="javascript:listfilter('${PATH}/planning/millplanninglist.do?PAGE_VALUE=${prevValue}')" class="backNext_append">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></a>  
          
                   
           </c:if>
         
           <c:if test="${nextValue!=null && nextValue!=''}">
            <a href="javascript:listfilter('${PATH}/planning/millplanninglist.do?PAGE_VALUE=${nextValue}')" class="backNext_append">
            <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></a> 
    	  <%--    <html-el:link styleClass="subLinksMain"  page="/planning/planninglist.do?&PAGE_VALUE=${nextValue}&isbn=${poHeader.titleDetail.isbn10}&printno=${poHeader.titleDetail.printNo}">next</html-el:link>--%>
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
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
         <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
         <input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         <input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
         
         
         <script language="javascript" >

       comapareCheckedValuesModified(document.planningForm.idHidden.value,document.planningForm.selectedEntry,'${checkBoxLength}',document.planningForm.checkall);
 
        </script>
         
         
         
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
            <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">         
            <input name="Button3" type="button" class="buttonMain" onClick="javascript:acceptPlanningPOsNew('<%=request.getContextPath()%>/planning/planningpoAcceptMessage.do')" value="Acknowledge">
              

<%}%>       
           <input name="excel"  type="button" class="buttonMain" onClick="javascript:acceptPlanningPOExcelNew('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=plan&Roll_Type=M')" value="Export to Excel">

            <input name="excel" type="button" class="buttonMain" onClick="submitPlanningActionExcel('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=planAll&Roll_Type=M',this)"  value="Export All to Excel">

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
  
     
 
	
  
             
              
      
  


