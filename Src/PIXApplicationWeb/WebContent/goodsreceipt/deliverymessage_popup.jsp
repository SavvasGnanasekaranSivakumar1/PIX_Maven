<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fileUploading.tld" prefix="fileUploading"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>' /> 
<c:set var="msgId" value='<%=request.getParameter("MSG_ID")%>' />
<c:set var="poversion" value='<%=request.getParameter("poversion")%>' />
<fileUploading:sessionForm form="${deliveryMessageForm}" />
<html>
<head>
<title>Pix</title>
<link href="<%=request.getContextPath()%>/css/pixcss.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/js/pixjs.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/menu.js"></script>
<script type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>
<body class="BodyStyle">

<c:set var="headerOrLineCollection" />
   <c:choose>
   <c:when test="${roleType == 'M' || orderPaper=='O'}">
   <c:set var="poLineCheck"/>
   <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
     <c:if test="${indexId.index==0}">
    <c:set var="headerOrLineCollection" value="${deliveryMessageLine.linePartyCollection}"/>
    </c:if>
   </c:forEach>
	  </c:when>
     <c:otherwise> 
	 <c:set var="headerOrLineCollection" value="${deliveryMessageForm.deliveryMessage.partyCollection}"/>
	 </c:otherwise>
  </c:choose>	

<html-el:form action="/goodsreceipt/deliverymessagedetail">

<%
DeliveryMessageForm messageform = (DeliveryMessageForm)request.getAttribute("deliveryMessageForm");

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="16" align="right" class="popHeaderBlue"><a href="javascript:window.close();">
    <img src="<%=request.getContextPath()%>/images/close.gif" alt="Close" width="16" height="9" border="0"></a></td>
    <td height="16" align="right" class="popHeaderBlue" width="4"></td>
  </tr>
  <tr>
    <td height="4" class="popHeaderGreen"></td>
    <td width="4" bgcolor="#78B7DD">
    <img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
</table>
<table width="100%" height="95%" border="0" cellpadding="0" cellspacing="0">
  
  <tr>
    <td width="2" rowspan="4" align="left" valign="top" class="LeftBorder">
    <img src="<%=request.getContextPath()%>/images/trans.gif" width="2" height="1"></td>
    <td height="10" align="left" valign="top">&nbsp;</td>
    <td width="4" rowspan="4" align="right" valign="top" class="RightBorder">
    <img src="<%=request.getContextPath()%>/images/trans.gif" width="4" height="1"></td>
  </tr>
  <tr>
    <td width="100%" height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
    <span class="headingText">Purchase Order No.  <c:out value="${deliveryMessageForm.deliveryMessage.poNo}"/> 
    <c:if test="${deliveryMessageForm.deliveryMessage.releaseNo!=null && deliveryMessageForm.deliveryMessage.releaseNo!='0'}">
    - ${deliveryMessageForm.deliveryMessage.releaseNo}
    </c:if> </span></td>
  </tr>
  <tr>
    <td align="left" valign="top" class="padding23"><table width="98%" border="0" cellspacing="0" cellpadding="0">
<form>
        <tr>
          <td height="15" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="5" valign="bottom" class="headingMain">&nbsp;</td>
            </tr>
            <tr>
              <td height="20" valign="bottom" class="headingMain">
              <table width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <td width="35%" class="blueColumn">MESSAGE TYPE: </td>
                    <td class="lightblueColumn"><c:out value="${deliveryMessageForm.deliveryMessage.msgTypeDetail}"/></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
            <%--<c:forEach var="party" items="${deliveryMessageForm.deliveryMessage.partyCollection}" varStatus="indexId">     --%>
              <c:forEach var="party" items="${headerOrLineCollection}" varStatus="indexId">	
              <td><fieldset class="legendBorder">
                <legend class="legendeTitle">SHIP TO (${party.san})
                <c:if test="${party.orgUnitCode!=null}">(${party.orgUnitCode})</c:if> </legend>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12"><c:out value="${party.name1}"/></td>
                  </tr>
                <tr>
                    <td class="textLegend"><c:out value="${party.address1}"/><br>
                <c:out value="${party.city}"/> <c:out value="${party.postalCode}"/> <c:out value="${party.state}"/> USA </td>
                </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                  	<c:set var="contactCollectionValue"/>
             		<c:choose>
            			<c:when test="${roleType == 'M' || orderPaper=='O'}">
         					<c:set var="contactCollectionValue" value="${party.linePartyContactCollection}"/>
	     				</c:when>
     					<c:otherwise> 
	  						<c:set var="contactCollectionValue" value="${party.contactCollection}"/>
	 					</c:otherwise>
  					</c:choose>	
                  <%--<c:forEach var="contact" items="${party.contactCollection}" varStatus="indexId">--%>
                  <c:forEach var="contact" items="${contactCollectionValue}" varStatus="indexId">
                     <td class="textLegend"><span class="textLegendBold"> -</span> <c:out value="${contact.contactFirstName}"/><br>
						<%--<c:out value="${contact.phone}"/>(Business);
						<c:out value="${contact.mobile}"/> (Mobile); 
						<c:out value="${contact.fax}"/>(Fax)<br>
						<c:out value="${contact.email}"/>--%>
						
						<c:if test="${contact.phone!=null}">&nbsp;${contact.phone} (Business)</c:if><br>
						<c:if test="${contact.mobile!=null}">&nbsp;${contact.mobile} (Mobile)</c:if><br>
						<c:if test="${contact.fax!=null}">&nbsp;${contact.fax} (Fax)</c:if><br>
						<c:if test="${contact.email!=null}">&nbsp;${contact.email} </c:if><br>
					</td>
				  </c:forEach>
                 </tr>
            </c:forEach>
          </table></fieldset></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
       
        </tr>
      </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="2%" class="tableHeading">PO LINE NO.</td>
          <c:choose>
   				<c:when test="${roleType == 'M' || orderPaper=='O'}">
   					<td class="tableHeading">MATERIAL NUMBER </td>
          			<td class="tableHeading">MATERIAL DESCRIPTION </td>
          		</c:when>
          		<c:otherwise>
          			<td class="tableHeading">COMPONENT </td>
          		</c:otherwise>
          	</c:choose>
          	<td class="tableHeading" width="10%">ORIGINAL QUANTITY</td>
          <c:if test="${roleType == 'M' || orderPaper == 'O'}">
          	<td class="tableHeading" width="10%">TOTAL DELIVERED</td>
          </c:if>
          <td class="tableHeading">QUANTITY</td>
          <td class="tableHeading">REQUESTED DELIVERY DATE </td>
          <td class="tableHeading">SHIP DATE </td>
          <c:if test="${roleType == 'M' || orderPaper == 'O'}">
          	<td class="tableHeading">BILL OF LADING </td>
          </c:if>
          <%--<td class="tableHeading">ESTIMATED DELIVERY DATE </td>--%>
        </tr>
        <tr>
        <c:set var="deliveryMessageDetailPrev" value="" />
        <c:set var="class1" value="tableRow"/>
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">     
        <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
         <c:if test="${lineNo%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${lineNo%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
         <tr>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
         <c:choose>
   				<c:when test="${roleType == 'M' || orderPaper=='O'}">
   					<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productCode}"/></td>
         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineDecription}"/></td>
         		</c:when>
         		<c:otherwise>
         			<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productDescription}"/></td>	
         		</c:otherwise>
         </c:choose>
         <%--<td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productDescription}"/></td>--%>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.balanceQuantity}"></fmt:formatNumber> </td>
         
         <c:if test="${roleType == 'M' || orderPaper == 'O'}">
         	<td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.postedQuantity}"></fmt:formatNumber> </td>	
         </c:if>
         <td valign="top" class="${class1}"><fmt:formatNumber pattern="#,###,##0" value="${deliveryMessageLine.deliveredQuantity}"></fmt:formatNumber> </td>
         <td valign="top" class="${class1}">${deliveryMessageLine.requestedDeliveryDate}</td>
         <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.estimatedDeliveryDate}"  /></td>
         <c:if test="${roleType == 'M' || orderPaper == 'O'}">
         	
         	<c:choose>
         		<c:when test="${deliveryMessageLine.fileExists!='0'}">
         		     <fileUploading:DMFileSubFolder id="subfolderName" msgId="${msgId}" lineNo="${deliveryMessageLine.lineNo}"/>
         			<td valign="top" class="${class1}"><a href="#" id="upload${indexId.index}" onClick="MM_openBrWindow('<%=request.getContextPath()%>/bolfilelist/downloadfile.do?lineNo=${deliveryMessageLine.lineNo}&msgId=${msgId}&flag=download&pono=${pono}&poversion=${poversion}&index=${indexId.index}&subfolderName=${subfolderName}&approvalFlag=${deliveryMessageLine.approvalFlag}','DownLoadFile','scrollbars=yes,width=800,height=410,top=10')" class="subLinksMain"><c:out value="download files"/></a></td>
         		</c:when>
         		<c:otherwise>
         			<td valign="top" class="${class1}">&nbsp;</td>
         		</c:otherwise>
         	</c:choose>
         	
         </c:if>
         </tr>
         </c:forEach>
              
              <tr>
                <%--<td height="1" colspan="5" class="tableLine">--%>
                <c:choose>
          <c:when test="${roleType == 'M' || orderPaper == 'O'}">
          	<td height="1" colspan="9" class="tableLine"/>
          </c:when>
          <c:otherwise>
          	<td height="1" colspan="6" class="tableLine"/>
          </c:otherwise>
          </c:choose>
                <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
              </tr>
            </table>
        </tr>
        <tr>
          <td height="15" align="left">&nbsp;</td>
        </tr>
        <tr>
          <td><label>
            <input name="Button2" type="button" class="buttonMain" onClick="MM_goToURL('parent','javascript:window.close();');return document.MM_returnValue" value="Close">
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="100%" align="center" valign="bottom">
    <span class="bottomtext">copyright savvas 2020</span></td>
  </tr>
</table>
</html-el:form>
</body>
 
</html>