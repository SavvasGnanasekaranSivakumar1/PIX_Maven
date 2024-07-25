<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ page import="com.pearson.pix.business.common.PIXConstants "%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<html-el:form action="/reports/reportsearch">
<html>
<html-el:hidden property="isbn10"/>
<html-el:hidden property="isbn13"/>
<html-el:hidden property="porderNo"/>
<html-el:hidden property="printNo"/>
<html-el:hidden property="item"/>
  <tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Reports </span></td>
  </tr>
  <% String errorMsg=(String)request.getAttribute(PIXConstants.PIX_ERROR); %>
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" class="text">Following are your search results. To view details click on the <span class="textBlue">item number</span>. To search again, click on the <span class="textBlue">back to search</span> button. </td>
      </tr>
    </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td height="10" align="left"></td>
          
        </tr>
        
        <tr>
        
          <td align="left" valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="0">
          <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
        	<c:if test="${errorMsg == null}">
            <tr>
            <td class="tableHeading">ISBN </td>
              <td class="tableHeading" width="4%">PRINT NUMBER </td>
              <td class="tableHeading">PO NUMBER </td>
              <td class="tableHeading">ITEM TYPE </td>
              <td class="tableHeading">ITEM NUMBER </td>
              <td class="tableHeading" width="4%">VERSION NUMBER</td>
              <td class="tableHeading">POSTED DATE </td>
              <td class="tableHeading">POSTED BY </td>
              <td class="tableHeading">VENDOR NAME </td>
              
            </tr>
           
           
          <c:forEach  var="report" items="${reportForm.reportCollection}" varStatus="indexId">
          
	         <c:if test="${indexId.count%2 != 0}" >
	         <c:set var="class1" value="tableRow"/>
	         <c:set var="class2" value="tableRowlink"/>
	         </c:if>
	         <c:if test="${indexId.count%2 == 0}" >
	         <c:set var="class1" value="tableAlternateRow"/>
	         <c:set var="class2" value="tableAlternateRowlink"/>
	         </c:if> 
  			<td class="${class1}"><c:out value="${report.isbn}"/></td>
            <td class="${class1}" ><c:out value="${report.printNo}"/></td>
			<td class="${class1}"><c:out value="${report.PONo}"/></td>
            <td class="${class1}"><c:out value="${report.itemName}"/></td>
       		<c:choose>
            <c:when test="${report.itemName=='Planning'}">
            <td class="${class1}"><a href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportplanningdetail.do?poid=${report.id}&poversion=${report.version}','PlanningDetail','scrollbars=yes,width=800,height=500')" class="${class2}"><c:out value="${report.itemNo}" /></a> </td>
            </c:when>
            <c:when test="${report.itemName=='Book Specification'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportbookspecdetail.do?SPEC_ID=${report.id}&SPEC_VERSION=${report.version}','BookSpecDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Order Detail'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportpurchaseorderdetail.do?poid=${report.id}&poversion=${report.version}&order=P&page=LH','OrderDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Order Status'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportorderstatusdetail.do?OS_ID=${report.id}&pono=${report.PONo}&rno=${report.version}&OS_NO=${report.itemNo}','OrderStatusDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Delivery Message'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportdeliverymessagedetail.do?MSG_ID=${report.id}&poversion=${report.version}','DeliveryMessageDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Goods Receipt'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportgoodsreceiptdetail.do?RECEIPT_ID=${report.id}&poversion=${report.version}&pono=${report.PONo}','GoodReceiptDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Onhand Inventory'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportinventorydetail.do','InventoryDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:when test="${report.itemName=='Usage'}">
            <td class="${class1}"> <a  href="javascript:MM_openBrWindow('<%=request.getContextPath()%>/reports/reportusagedetail.do?uid=${report.id}&poid=${report.poid}&poversion=${report.version}&pono=${report.PONo}','UsageDetail','scrollbars=yes,width=800,height=500')" class="${class2}"> <c:out value="${report.itemNo}"/></a></td>
            </c:when>
            <c:otherwise>
           	<td class="${class1}"> <c:out value="${report.itemNo}"/></td>
            </c:otherwise>
            </c:choose> 
            <c:choose>
            <c:when test="${report.itemName=='Order Status'}">
            <td class="${class1}"><c:out value="1"/></td>
            </c:when>
           <c:otherwise>
           <td  class="${class1}" ><c:out value="${report.version}"/></td>
           </c:otherwise>
           </c:choose> 
           <td class="${class1}"><fmt:formatDate value="${report.postedDate}" type="both" pattern="MM/dd/yyyy" /></td> 
           <td class="${class1}"><c:out value="${report.postedBy}"/></td>
            <td class="${class1}"><c:out value="${report.vendor}"/></td> 
            
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
              <td height="1"  colspan="10" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="15" align="left">&nbsp;</td>
        </tr>
         <c:if test="${errorMsg == null}">
        <tr>
        <td height="15" align="left"><input name="excel" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=report',this)" value="Export to Excel">
            <input type="hidden" name="flag"/>
         <input name="Button2" type="button" class="buttonMain" onClick="submitAction('<%=request.getContextPath()%>/reports/reportsearch.do',this)" value="Back to Search"></td>
        </tr>
        </c:if>
        
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  </html-el:form>
  