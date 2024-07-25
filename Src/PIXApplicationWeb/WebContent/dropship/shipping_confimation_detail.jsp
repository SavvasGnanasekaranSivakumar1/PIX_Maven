<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<html>
<head>
<title>PIX Shipping Confirmation Details</title>
<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/js/pixjs.js"></script>
<script language="javascript" type="text/javascript">
		function closepopup() {
			 window.close();	
		}
		function shipConfirm() {
			//var flag = confirm("Confirm and save the validate shipment confirmation detail.");	
			//if(flag == true) {
				//document.shipmentConfForm.action="/pix/dropship/shippingConfirmation/confirm.do?actionType=confirm";
			//	window.opener.location.reload();
				window.opener.location.href = "/pix/dropship/shippingConfirmation/confirm.do?actionType=ShipInfoConfirm";	
				//document.shipmentConfForm.submit();
				closepopup();
		}
		function getShipInfo(pageNumber) {
			//alert("page number:"+pageNumber);
			if(pageNumber<1)
				pageNumber=1;
			var url = "/pix/dropship/shippingConfirmation/read.do?actionType=display&PageNo="+pageNumber;
			//alert("URL: "+url);
			document.shipmentConfForm.action=url;
			document.shipmentConfForm.submit();
		}
</script>
</head>
<body class="BodyStyle">
<c:set var="PageNum" value='<%=request.getParameter("PageNo")%>'></c:set>
 <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" align="right" valign="middle" class="topLinkPopup"><a href="javascript:window.close();" style="cursor:hand"><font class="textWhite">close </font><img src="<%=request.getContextPath()%>\images\close.gif" width="16" height="9" border="0"></a></td>
  </tr>
  <tr>
    <td width="4" rowspan="4" align="left" valign="top" class="LeftBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
    <td width="100%" height="4" align="right" valign="top" class="LinkGreen"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
    <td width="4" rowspan="8" align="right" valign="top" class="RightBorder"><img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
 <tr>
    <td height="20" align="left" valign="top" class="paddingSmallT paddingSmallR">
    	<div class="floatLeft headingText padding1px"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"> Shipping Confirmation Details</div>
     </td>
  </tr>
  
	<c:if test="${errorFlag eq true}">
		  <tr><td>
			 <br /> 
			 	<h3>Uploaded File format not matched. Please try again.</h3>
			<br /><br />
			To download template <a href="/pix/dropship/DropShipmentConfermationTemplate.xls" target="_blank">click here</a>. 
		  </td>
		  </tr>
   </c:if>
  <c:if test="${errorFlag ne true}">
  <tr>
    <td align="left" valign="top" class="padding23">
      <table width="99%" border="1" cellspacing="1" cellpadding="1" bordercolor="#00CDCD">
        <tr align="center">
          <td class="tableHeading" width="15%">Failure Message</td>
          <td class="tableHeading">PEARSON PO</td>
          <td class="tableHeading">BK #</td>
          <td class="tableHeading">ISBN-10</td>
          <td class="tableHeading">TRACKING NUMBER</td>
          <td class="tableHeading">CARRIER & LEVEL</td>
          <td class="tableHeading">DESTINATION</td>
          <td class="tableHeading">SHIP DATE</td>
          <td class="tableHeading">Gross Wt.<br />(lbs)</td>
          <td class="tableHeading">Desk Copy</td>
          <td class="tableHeading">No of Cart.</td>
          <td class="tableHeading">Cart. Qty</td>
        </tr>
		<c:choose>
	        <c:when test="${fn:length(uploadShipInfoForm.shipConfDTOList) eq 0}">
	        	<tr><td class="tableRow" colspan="10">No Record Found.</td></tr> 
	        </c:when>
        <c:otherwise>
			<c:forEach var="shipConfDTO" items="${uploadShipInfoForm.shipConfDTOList}" varStatus="indexId">
				<tr>
					<c:choose>
						<c:when test="${shipConfDTO.processingFlag == 'S' || shipConfDTO.processingFlag == 's'}">
		            		<td class="tableRow">&nbsp;</td>
		            	</c:when>
			            <c:when test="${shipConfDTO.processingFlag == 'W' || shipConfDTO.processingFlag == 'w'}">
			            	<td class="tableRow" style="background: Pink"><c:out value='${shipConfDTO.errorDesc}'/></td>
			            </c:when>
		            	<c:otherwise>
		            		<td class="tableRow" style="background:#FF5A49;"><c:out value='${shipConfDTO.errorDesc}'/></td>
		            	</c:otherwise>  
		            </c:choose>	 
		            	<td class="tableRow" ><c:out value='${shipConfDTO.poNumber}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.bkNumber}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.isbn10}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.trackingNumber}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.carrierLevel}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.shipTo}' /></td>
			            <td class="tableRow" ><fmt:formatDate value="${shipConfDTO.shipDate}" type="both" pattern="MM/dd/yyyy" /></td>
			            <td class="tableRow" ><fmt:formatNumber value="${shipConfDTO.grossWeight}" pattern="#,##0.00;-#,##0.00" /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.deskCopy}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.totalCartons}' /></td>
			            <td class="tableRow" ><c:out value='${shipConfDTO.totalUnits}' /></td>  	
				</tr>
			</c:forEach>
		</c:otherwise>	
		</c:choose>
	</table>
	</td>
	</tr>
		<html-el:form action="/dropship/shippingConfirmation/confirm.do" styleId="shipmentConfForm">
			<tr>
			<td align="right" width="995px">
			<br/>
				<c:if test="${PageNum gt 1}">
	            <a href="javascript:void(0);" onClick="getShipInfo(${PageNum-1})" class="backNext_append"><img src="<%=request.getContextPath()%>/images/prev.gif" alt="previous" border="0" width="67" height="20"></a>
	           </c:if>
	            <c:if test="${(uploadShipInfoForm.totalRecords gt 0) and (PageNum*10 lt uploadShipInfoForm.totalRecords)}">
	            <a href="javascript:void(0);" onClick="getShipInfo(${PageNum+1})" class="backNext_append"><img src="<%=request.getContextPath()%>/images/next.gif" alt="next" border="0" width="67" height="20"></a>&nbsp;&nbsp;
				</c:if>
			</td>
			</tr>
			<tr>
			<td colspan="8" align="left">
			<br/>
			<br/>
			   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	           <input name="Confirm" type="button" class="buttonMain" value="Confirm" onClick="shipConfirm()" style="width:80px" />
	           &nbsp;&nbsp;&nbsp;&nbsp;
	           <input name="Cancel" type="button" class="buttonMain" value="Close" onClick="window.close()" style="width:80px" />
	        </td>
			</tr>
		</html-el:form>
</c:if>
	<table>
       <tr><td height="5" colspan="1">&nbsp;</td></tr>
       <tr><td colspan="2" class="text">Note::</td></tr>
       <tr>
        <td colspan="1" class="text" style="color:red;"> 
        	<span style="color:#FF5A49;font-weight:bold;font-size:18px; !important;" >*</span> - Rows marked in RED will not be uploaded into the system.<br/>
        	<span style="color:Pink;font-weight:bold;font-size:18px; !important;" >*</span> - Rows marked in PINK are only meant for an alert.
        </td>
       </tr> 
     </table>
	</table>
</body>
</html>