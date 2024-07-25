<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>
<script language="javascript" src="<%=request.getContextPath()%>/js/mismatchreport.js"></script>
<script language="JavaScript" type="text/JavaScript">

function submitmismatch(path){
var ponum = trimming(document.getElementById("poNo").value);
if(ponum ==""){
alert("Please Enter PurchaseOrder Number");
return false;
}
else {
document.mismatchReportForm.action=path;
return true;
}
}


function chkvalue(){
var ponum = trimming(document.getElementById("poNo").value);
if(isNaN(ponum)){
alert("PO Number Should be a Number");
document.getElementById("poNo").value = " ";
}
else {
if(ponum.length >10){
alert("PO Number Cannot be more than 10 digits");
document.getElementById("poNo").value = " ";
}
}
}

</script>
<tr>
		<td height="25" align="left" valign="top">
			<img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
			<span class="headingText">Purchase Order - Mismatch Report</span>
		</td>
	</tr>
	<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<html-el:form action="/mismatchreport/mismatchreport" onsubmit="return submitmismatch('${contextPath}/mismatchreport/mismatchreport.do?&msr=y');">
<%@ include file="/common/formbegin.jsp"%>
<tr>
			<td align="left" valign="top" class="padding23">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="text">
							You can search for Purchase Order's Mismatch Report.
						</td>
					</tr>
					<tr>
						<td align="right" valign="bottom">
							&nbsp;
						</td>
					</tr>
				</table>

				<table width="94%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="1" colspan="3" class="tableLine">
						</td>
					</tr>

					<tr>
						<td width="8%" colspan="2" class="titleBlue2">
							PO Number:
						</td>
						<td  class="inboxdrakBlue2">
							<html-el:text property="poNo" styleId="poNo" styleClass="textfield" onkeyup="chkvalue()"/>
						</td>
					</tr>				 
			</table>
				<table width="94%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td height="1" colspan="3" class="tableLine"></td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">

					<tr>
						<td width="45%" height="15" align="left" class="subLinksMain"></td>
						<td height="10" align="right"></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>														
								<html-el:submit property="buttonName" 
							styleClass="buttonMain" value="Search" onclick=""></html-el:submit> 
							</label>
						</td>
					</tr>
					<tr>
						<td height="35" colspan="2">
						 &nbsp; 
						  <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>'/> 
		<tr>
		  <td>&nbsp;
		  <c:if test="${errorMsg == null}">
		 <c:if test="${mismatchReportForm.mismatchReportDetails != null}">  
       <table width="98%" border="0" cellspacing="1" cellpadding="0">
       <tr>
            <td height="1" colspan="9" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	<tr><td>&nbsp;</td>
          </tr>
		  <tr>
          <td width="10%" class="tableHeading2" >Purchase Order</td>
		  <td width="20%" class="tableHeading2" >Mill/Merchant</td>
          <td class="tableHeading2" >Material Description</td>
          <td width="10%" class="tableHeading2">Quantity</td>
          
		  </tr>

		<tr>
		  <td class="lightblueColumn"><c:out value="${mismatchReportForm.poNo}"/></td>
		   <td class="lightblueColumn"><c:out value="${mismatchReportForm.merchantName}"/></td>
          <td class="lightblueColumn"><c:out value="${mismatchReportForm.materialNo}"/></td>
          <td class="lightblueColumn" align="right"><fmt:formatNumber value="${mismatchReportForm.quantity}" type="number" pattern="###,###"/>
          </td>
          </tr>
		  </table>
		 </c:if>
		  </c:if>
		</td>
		  <tr>
		  <td>&nbsp;
		<c:if test="${errorMsg == null}"> 
		 <c:if test="${mismatchReportForm.mismatchReportDetails != null}">   
		 <table width="98%" border="0" cellspacing="1" cellpadding="0">
		  <tr> 
          <td width ="6%" class="tableHeading2">Po Line#</td>
		  <td width ="18%" class="tableHeading2">Material No</td>
          <td  width ="15%"class="tableHeading2">Vendor Plant</td>
          <td  class="tableHeading2">Ordered Qty</td>
		  <td class="tableHeading2">Delv.Qty </td>
		  <td  class="tableHeading2">Delv.Doc Number</td>
		  <td  width ="10%" class="tableHeading2">GR Qty</td>
		  <td  class="tableHeading2">GR Doc.Number</td>
		  <td  width ="7%" class="tableHeading2">Qty Variance</td>
		  </tr>
		  <c:set var="poline1" value=""></c:set>
		  <c:forEach var="mismatchdetails" items="${mismatchReportForm.mismatchReportDetails}" >
		  
		  <c:if test="${poline1 != mismatchdetails.poLineNo}">		  
		<tr>
		  <td class="inboxdrakBlue2"><c:out value="${mismatchdetails.poLineNo}"/> </td>
		  <td class="inboxdrakBlue2"><c:out value="${mismatchdetails.materialNo}"/> </td>
          <td class="inboxdrakBlue2"><c:out value="${mismatchdetails.sapPlantCode}"/></td>
          <td class="inboxdrakBlue2" align="right"><fmt:formatNumber value="${mismatchdetails.requestedQty}" type="number" pattern="###,###"/>
          </td>
		  <td class="inboxdrakBlue2" align="right"><fmt:formatNumber value="${mismatchdetails.dmQty}" type="number" pattern="###,###"/>
		  </td>
		  <td class="inboxdrakBlue2"><c:out value="${mismatchdetails.dmDoc}"/></td>
		  <td class="inboxdrakBlue2" align="right"><fmt:formatNumber value="${mismatchdetails.grQty}" type="number" pattern="###,###"/>
		  </td>
		  <td class="inboxdrakBlue2"><c:out value="${mismatchdetails.grDoc}"/></td>
		  <td class="inboxdrakBlue2" align="right"><fmt:formatNumber value="${mismatchdetails.varianceQty}" type="number" pattern="###,###"/></td>
		  
          </tr>
        </c:if>
        <c:if test="${poline1 == mismatchdetails.poLineNo}">		 
        <tr>
		  <td class="lightblueColumn">&nbsp; </td>
		  <td class="lightblueColumn">&nbsp; </td>
          <td class="lightblueColumn">&nbsp;</td>
          <td class="lightblueColumn">&nbsp;</td>
		  <td class="lightblueColumn" align="right"><fmt:formatNumber value="${mismatchdetails.dmQty}" type="number" pattern="###,###"/></td>
		  <td class="lightblueColumn"><c:out value="${mismatchdetails.dmDoc}"/></td>
		  <td class="lightblueColumn" align="right"><fmt:formatNumber value="${mismatchdetails.grQty}" type="number" pattern="###,###"/></td>
		  <td class="lightblueColumn"><c:out value="${mismatchdetails.grDoc}"/></td>
		  <td class="lightblueColumn" align="right"><fmt:formatNumber value="${mismatchdetails.varianceQty}" type="number" pattern="###,###"/></td>
          </tr>
		 </c:if>
		<c:set var="poline1" value="${mismatchdetails.poLineNo}"></c:set>
          </c:forEach> 
          <tr><td>&nbsp;</td>
          </tr>         
		<tr>
            <td height="1" colspan="9" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	<tr><td>&nbsp;</td>
          </tr>
		  </table>
		  <html-el:submit property="buttonName" styleClass="buttonMain" value="Export to Excel"></html-el:submit> 		
		</c:if>
		</c:if>
		
		 <c:if test="${errorMsg != null}">
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td height="1" colspan="8" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>  
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></td>   
           </tr>
           
           <tr>
            <td height="1" colspan="8" class="tableLine">
            <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
          	</tr>
          	</table>
         </c:if> 	 
		 
		  </td> 
		  </tr>
		  
			</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
</html-el:form>