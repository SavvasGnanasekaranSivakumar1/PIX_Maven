

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
<c:set var="PATH" value='<%=request.getContextPath()%>' />
<c:set var="checkBoxIdList" value='<%=request.getAttribute("checkBoxIdList")%>' />
<c:set var="checkBoxLength" value='${fn:length(costAccountingForm.deliverymessageCollection)}' />
<script language="javascript" src="<%=request.getContextPath()%>/js/helloform.js">
</script> 


<%--
boolean showCheckBox = false;
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.PLANNING_CODE))
{
	showCheckBox = true;
}
--%>

  
  
   <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Approval Message</span></td>
  </tr>
  <tr>
 
     <html-el:form action="/costaccounting/approvallist.do" >
     
     <input type="hidden" name="idHidden" id="hiddenVal" value="${checkBoxIdList}"/>
     
      <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
        <td class="text">To view a Delivery Message/Goods Receipt in details, click on the Delivery Message/Goods Receipt number.</td>
        </tr>
            
        <tr>
        <td align="right" valign="bottom"><img src="<%=request.getContextPath()%>/images/filter.gif" width="10" height="8">
        <html-el:link styleClass="subLinksMain" page="/costaccounting/dmApprovalfilter.do">filter</html-el:link></td>
        </tr>

        <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/>
         <c:if test="${errorMsg == null}"> 
        <%--<table width="100%" border="0" cellspacing="1" cellpadding="0">--%>
          <tr>
           
        
          <td align="center" class="tableHeading">
          <input name="checkall" type="checkbox" value="checkbox" onclick="addDeleteAllIdsFromSetNew(this,document.costAccountingForm.idHidden,document.costAccountingForm.selectedEntry);">
          </td>
          <td class="tableHeading">ORDER NUMBER </td>
          <td class="tableHeading">PO LINE#</td>
          <td class="tableHeading">MATERIAL NUMBER</td>
          <td class="tableHeading">DELIVERY MESSAGE/GOODS RECEIPT  NO.</td>
          <td class="tableHeading">MILL/MERCHANT</td>
          <td class="tableHeading">REQUESTED QTY</td>
          <td class="tableHeading">DELIVERED QTY</td>
          <td class="tableHeading">RECEIVED QTY</td>
          <td class="tableHeading">OWNED QTY</td>
          <td class="tableHeading">OWNERSHIP MODE</td> 
          <td class="tableHeading">STATUS</td>
          <td class="tableHeading">PRINTER</td>
          <td class="tableHeading">CREATION DATE </td>
          </tr>
        
          <c:forEach var="costaccountdm" items="${costAccountingForm.deliverymessageCollection}" varStatus="indexId"> 
           <%--<c:forEach var="vendor" items="${planningpo.partyCollection}" varStatus="partyCount">
			<c:set var="vendorParty" value="${vendor}"/>
		   </c:forEach>--%>
		   
           <%--<input type="hidden" name="querystr_${indexId.index}" value="poid=${planningpo}&poversion=${planningpo.poVersion}&pono=${planningpo.poNo}$deldate=$poHeader.&rno=${planningpo.releaseNo}">  --%>
           
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
	           <html-el:checkbox  property="selectedEntry" value="${costaccountdm.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.costAccountingForm.idHidden,'${costaccountdm.msgId}',document.costAccountingForm.selectedEntry);"/>
	           </c:when>
               <c:otherwise> 
	             <html-el:checkbox  property="selectedEntry" value="${costaccountdm.msgId}" onclick="javascript:addDeleteIdsFromSetNew(document.costAccountingForm.idHidden,'${costaccountdm.msgId}',document.costAccountingForm.selectedEntry[${indexId.index}]);"/>
	            </c:otherwise>
		       </c:choose>	
	          </label></td>
               
          <td class="${class1}">${costaccountdm.poNo}</td>
          <td class="${class1}" align="center">${costaccountdm.lineNo}</td>
          <td class="${class1}">${costaccountdm.materialNo}</td>
          <td class="${class1}"><html-el:link  styleClass="${class2}" page="/costaccounting/deliverymessagedetail.do?MSG_ID=${costaccountdm.msgId}&poid=${costaccountdm.poId}&poversion=${costaccountdm.poVersion}&pono=${costaccountdm.poNo}&orderPaper=O&PAGE_VALUE=${PAGE_VALUE}&msgNo=${costaccountdm.msgNo}&poNoFilter=${poNoFilter}&materialNoFilter=${materialNoFilter}&delMsgNoFilter=${delMsgNoFilter}&millMerchantFilter=${millMerchantFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&statusFilter=${statusFilter}&ownrshpMode=${costaccountdm.ownershipMode}"><c:out value="${costaccountdm.msgNo}"/></html-el:link></td>
          <td class="${class1}">${costaccountdm.millName}</td>
          
          <td class="${class1}" align="right">
          	<fmt:formatNumber value="${costaccountdm.requestedQuan}" pattern="###,###"/></td>
          <td class="${class1}" align="right">
          	<fmt:formatNumber value="${costaccountdm.deliveredQuan}" pattern="###,###"/></td>    
          	 <td class="${class1}"><fmt:formatNumber value="${costaccountdm.receivedQuan}" pattern="###,###"/></td>
          <td class="${class1}"><fmt:formatNumber value="${costaccountdm.ownedQuan}" pattern="###,###"/></td>
          
          <td class="${class1}">${costaccountdm.ownershipMode}</td>      
          
          <td class="${class1}">${costaccountdm.status}</td>
          <td class="${class1}">${costaccountdm.printername}</td>
         <td class="${class1}"><fmt:formatDate value="${costaccountdm.creationDateTime}" type="both" pattern="MM/dd/yy" /></td>
          
          </tr>
          </c:forEach>
         
         </c:if>
         <c:if test="${errorMsg != null}">
         <tr>
          <td height="1" colspan="15" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
           <tr>
             <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
          </tr>
          </c:if>
          <tr>
          <td height="1" colspan="15" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
    
   </table>
         
       <table width="98%" border="0" cellspacing="0" cellpadding="0">
         
        <tr>
          <td width="45%" align="left" class="subLinksMain"></td>
          <td height="15" align="right">
          
         <c:if test="${prevValue!=null && prevValue!=''}">
         <a href="javascript:listfilter('${PATH}/costaccounting/approvallist.do?PAGE_VALUE=${prevValue}')" class="backNext_append">
          <img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0"></a>  
          
                   
           </c:if>
         
           <c:if test="${nextValue!=null && nextValue!=''}">
            <a href="javascript:listfilter('${PATH}/costaccounting/approvallist.do?PAGE_VALUE=${nextValue}')" class="backNext_append">
            <img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0"></a> 
    	  <%--    <html-el:link styleClass="subLinksMain"  page="/planning/planninglist.do?&PAGE_VALUE=${nextValue}&isbn=${poHeader.titleDetail.isbn10}&printno=${poHeader.titleDetail.printNo}">next</html-el:link>--%>
    	   </c:if>
    	  </td>
         
        </tr>
        <tr>
          <td height="10" align="left" class="subLinksMain"></td>
          <td height="10" align="right"></td>
        </tr>
        
        
        <input type="hidden" name="poNoFilter" value="${poNoFilter}"/> 
        <input type="hidden" name="materialNoFilter" value="${materialNoFilter}"/>
        <input type="hidden" name="delMsgNoFilter" value="${delMsgNoFilter}"/>
        <input type="hidden" name="millMerchantFilter" value="${millMerchantFilter}"/>
        <input type="hidden" name="statusFilter" value="${statusFilter}"/>
        <input type="hidden" name="printerFilter" value="${printerFilter}"/>
        <input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        <input type="hidden" name="endDateFilter" value="${endDateFilter}"/>
         
        
         
         
         
         <script language="javascript" >

       comapareCheckedValuesModified(document.costAccountingForm.idHidden.value,document.costAccountingForm.selectedEntry,'${checkBoxLength}',document.costAccountingForm.checkall);
 
        </script>
         
         
         
        <tr>
          <c:if test="${errorMsg == null}">
          <td colspan="2"><label>
          <input type="hidden" name="checksArr"> 
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons2" class="tabSelectTextleft">
          
<%--
if(showCheckBox)
{
--%>

            <input name="PAGE_VALUE" type="hidden" value="${PAGE_VALUE}">         
           <%-- <input name="Button3" type="button" class="buttonMain" onClick="javascript:approveDmList('<%=request.getContextPath()%>/costaccounting/dmListApproval.do')" value="Approve"> --%>
              

<%--}--%>       
           <input name="excel1"  type="button" class="buttonMain" onClick="javascript:acceptdelMsgExcel('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=delMsg')" value="Export to Excel">

            <input name="excel" type="button" class="buttonMain" onClick="submitdelMsgExcel('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=delMsgAll')"  value="Export All to Excel">

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
      </table>
      
    </html-el:form>
  
     
 
	
  
             
              
      
  


