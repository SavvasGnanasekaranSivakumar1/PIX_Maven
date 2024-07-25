<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/fileUploading.tld" prefix="fileUploading"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>

<html-el:form action="/deliverymessage/insertnewdeliverymessage" onsubmit="return false"> 
 <%@ include file="/common/formbegin.jsp"%>
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="orderPaper"><%=request.getParameter("orderPaper")%></c:set>
<c:set var="page_order_list" value='<%=request.getParameter("page_order_list")%>' /> 
<c:set var="pageFilter" value='<%=request.getParameter("pageFilter")%>' /> 
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' /> 
<c:set var="isbnFilter" value='<%=request.getParameter("isbnFilter")%>' /> 
<c:set var="printNoFilter" value='<%=request.getParameter("printNoFilter")%>' /> 
<c:set var="statusFilter" value='<%=request.getParameter("statusFilter")%>' /> 
<c:set var="startDateFilter" value='<%=request.getParameter("startDateFilter")%>' /> 
<c:set var="endDateFilter" value='<%=request.getParameter("endDateFilter")%>' />
<c:set var="sbBDateFilter" value='<%=request.getParameter("sbBDateFilter")%>' /> 
<c:set var="ebBDateFilter" value='<%=request.getParameter("ebBDateFilter")%>' />  
<c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}'/>

<c:set var="orderType" value='<%=request.getParameter("orderType")%>' />
<c:set var="order" value='<%=request.getParameter("order")%>' />
<c:set var="fo"><%=request.getParameter("fo")%></c:set>
<%-- checking access rights --%>
<c:forEach var="userPriv" items="${USER_INFO.privilegeCollection}" varStatus="i">
<c:if test= "${userPriv.moduleDetail.refCode == 'DME'}">
<c:set var="accessFlag" value="${userPriv.accessFlag}"/>
<%--<script>
	alert('${accessFlag}');
</script>--%>  
</c:if>
</c:forEach>
<c:set var="roleTracking" value ='<%=session.getAttribute("roleTrackingFlag")%>'/>  <%-- zero value implies roletracking is disabled i.e. pix user and textboxes are enabled --%>


<c:set var="viewOnly" value = "0" />  <%-- zero value implies viewOnly is disabled i.e. textboxes are enabled --%>
<c:if test="${accessFlag == 'R'}">
<c:set var="viewOnly" value = "1" />
</c:if>
<script>
       var httpRequest;
        function getFileDownload(lineno,pono,id){
        //alert("called");
       var filename=document.getElementById('ajaxCall').value;
        if(filename.length!=0){
        var path= '<%=request.getContextPath()%>' ;
		var url = path+'/AjaxAction.do?lineno='+lineno+'&pono='+pono+'&filename='+filename; 
		document.getElementById('ajaxCall').value='' ;
		if (window.ActiveXObject){ 
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
		
		}else if (window.XMLHttpRequest){ 
		httpRequest = new XMLHttpRequest(); 
	    } 
	
		httpRequest.open("GET", url, true); 
		httpRequest.onreadystatechange = function() {
		
		 getFileUpload(id,lineno,pono);
		} ; 
		httpRequest.send(null); 
       }
    
    }
    
     function getFileUpload(id,lineno,pono){
    	if (httpRequest.readyState == 4) { 
			if(httpRequest.status == 200) { 
			
			xml = httpRequest.responseXML;
			 file_upload = xml.getElementsByTagName("file-upload")[0];
			 var fileUpload=false ;
		if(file_upload!=null&&file_upload.firstChild!=null){
			fileUpload=file_upload.firstChild.data;
         }
         //alert(fileUpload);
         funcPassValueNew2(fileUpload,id,lineno,pono);
		}else { 
			alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
		}
	 }
    
    
    function funcPassValueNew2(flag,id,lineno,pono){
             
            if(flag!=true){ 
            	document.getElementById('hid'+id).value="" ;
            	}else{ 
            	document.getElementById('hid'+id).value=""+pono+"-"+lineno;
            	}
            
     }
  var childW=null ;
   function MM_openBrWindowUpload(theURL,winName,features) { //v2.0
       if(childW!=null||childW!=undefined){
       childW.close();
       childW=null ;
       }
           childW=window.open(theURL,winName,features);
           childW.moveTo(0,0);
           window.blur;
        }


</script>
<tr>
   <td height="25" align="left" valign="top">
   <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9">
   <span class="headingText">Purchase Order No.</span>&nbsp;
   <input type="text" name="poNumber" id="poNumber" maxlength="10" class="textboxtext" onKeyPress="return submitEnter('<%=request.getContextPath()%>','<%=request.getContextPath()%>/deliverymessage/deliverymessagemilllist.do?PAGE_VALUE=1&page_order_list=1<c:if test="${roleType == 'B'}">&orderType=O</c:if>','<%=request.getContextPath()%>/deliverymessage/deliverymessagelist.do?PAGE_VALUE=1&page_order_list=1',event)" value="${pono}"/>
   &nbsp;
    <input name="searchTop" id="searchTop" type="button" size="10" maxlength="12" class="buttonMain" onClick="submitSearch('<%=request.getContextPath()%>','<%=request.getContextPath()%>/deliverymessage/deliverymessagemilllist.do?PAGE_VALUE=1&page_order_list=1<c:if test="${roleType == 'B'}">&orderType=O</c:if>','<%=request.getContextPath()%>/deliverymessage/deliverymessagelist.do?PAGE_VALUE=1&page_order_list=1')" value="Search">
	<input type="hidden" name="poid" id="poid" value="${poid}"/>
	<input type="hidden" name="pover" id="pover" value = "${poversion}"/>
	<input type="hidden" name="porel" id="porel" value ="${rno}"/>
	<input type="hidden" name="poorderType" id="poorderType"/>
    </td>
   
  </tr>
  
 <tr>
    <c:forEach var="lineItem" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
    </c:forEach>
   <td align="left" valign="top" class="padding23">
   	  <input type="hidden" name="totalLineItems1" id="totalLineItems1" value ="${totalLineItems}"/>	
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      	
       	<TR>
       	<tr><td>&nbsp;</td></tr>
      
       	<c:forEach var="deliveryMessageLine1" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
       	<c:choose>
       	<c:when test="${deliveryMessageLine1.uom == 'SH'}">
       	<c:set var="sheet" value='Y' />
       	<c:set var="messageText" value="${deliveryMessageLine1.message_Text}" />
       	</c:when>
       	</c:choose>
       	</c:forEach>
       	
       	<c:choose>
       	<c:when test="${sheet == 'Y'}">
       	<td height="15" colspan="1" class="textUOM" >${messageText}</td>
       	</c:when>
       	</c:choose>
       	
       	</TR>
       	<tr><td>&nbsp;</td></tr>
       	<tr>
        	<td height="15" colspan="2" class="text">To view the <span class="textBold">Delivery Message History </span>details, click on the PO Line No.</td>
       	</tr>
       	<tr>
  			
  			<c:if test="${message!=null}">
  				<font color="#CC0000" size="2" face="Verdana">
    			<span >${message}</span>
  			</c:if>	
  		</tr>
       	<tr>
       		<td>
        	<div id="error_div" class="errorMessageTextGR"></div>
       		</td>
       	</tr>
       	<tr>
       		 <td height="0" colspan="2"></td>
       	</tr>
       	<tr>
       	
        	<td width="80%" height="22">
        		<table height="22" border="0" cellpadding="0" cellspacing="0">
         			<c:set var="pageModule" value="deliverymessage" scope="request"></c:set>
         			<c:set var="orderPaper" value="${orderPaper}" scope="request"></c:set>
         			<c:set var="orderType" value="${orderType}" scope="request"></c:set>
         			<c:set var="order" value="${order}" scope="request"></c:set>
         			<%@ include file="/common/mod_tabs.jsp"%>
       			</table>
    		</td>
    		<td align="right" valign="bottom">&nbsp;</td>
 		</tr>  
 		<tr>
    	    <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
 		</tr>
     </table>    
     <table width="98%" border="0" cellspacing="1" cellpadding="0" >
<%-- <script>
      		alert('${fn:length(deliveryMessagesForm.deliveryMessage.deliveryMsgLineCollection)}');
      	</script> --%>
  <c:if test="${errorMsg == null && ((fn:length(deliveryMessagesForm.deliveryMessage.deliveryMsgLineCollection)) >= 0)}">     	 
        <tr>
 
          <td class="tableHeading">PO LINE#</td>		  
		  <td class="tableHeading" width="60px">PRINTER </td>		
		  <td class="tableHeading">L4 MATERIAL DESCRIPTION</td>
		  <td class="tableHeading">REQUESTED DEL  DATE</td>		
          <td class="tableHeading" width="60px">REQUESTED QTY</td>          
          <td class="tableHeading" width="60px">DM POSTED </td>
		  <td class="tableHeading" width="60px">DM TO BE POSTED </td>
			<c:if test = "${roleType == 'M' && viewOnly != '1'}"> 
		  	<c:choose>	
			  	<c:when test = "${roleTracking == 0}"> <%-- zero value implies roletracking is enabled i.e. pix user and textboxes are disabled --%>  
	          		<td class="tableHeading">ENTER DM QTY</td>
	         	</c:when>
	          	<c:otherwise>
	          	   	<td class="tableHeading">ENTER DM QTY</td>
	          	</c:otherwise>
            </c:choose>
		  	 </c:if> 
		  
			
<%-- 		  <c:choose>	
		  	<c:when test = "${(roleType=='M' || roleType == 'V' || roleType == 'B') && viewOnly != '1'}">  
          		<td class="tableHeading" width="30px">UPLOAD BOL</td>
         	</c:when>
          	<c:otherwise>
          	   <c:if test="${(roleType=='M' || roleType == 'V' || roleType == 'B')}">  <%-- roleType != 'V' -%>
          		<td class="tableHeading" width="30px">DOWNLOAD BOL</td>  <%-- || roleType == 'V' || roleType == 'B' -%>
          	   </c:if>	
          	</c:otherwise>
          </c:choose>  --%>
          
          <c:choose>	
		  	<c:when test = "${roleType=='M' && viewOnly != '1'}">  
          		<c:if test="${roleTracking == 0}">
          			<td class="tableHeading" width="30px">UPLOAD BOL</td>
          		</c:if>
          		<c:if test="${roleTracking > 0}">
          			<td class="tableHeading" width="30px">VIEW BOL</td>
          		</c:if>
         	</c:when>
          	<c:otherwise>
          	   <c:if test="${roleType != 'V'}">
          		<td class="tableHeading" width="30px">DOWNLOAD BOL</td>
          	   </c:if>	
          	</c:otherwise>
          </c:choose>
          
          <c:if test="${roleType == 'V' && roleTracking>0}">
          		<td class="tableHeading" width="30px">VIEW BOL</td>
          	   </c:if>
        </tr>
      
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
           
        <tr>
        <c:set var="colorFlag" value="${deliveryMessageLine.colorFlag}"/>
        <%-- if 0 red, if 1 green --%>
        <c:if test="${colorFlag == 0}" >
        <c:set var="class1" value="tableHighRow"/>
        
        </c:if>
        <c:if test="${colorFlag == 2}" >
        <c:set var="class1" value="tableMediumRow"/>
        </c:if>
        <c:if test="${colorFlag == 1}" >
        <c:set var="class1" value="tableLowRow"/>
        </c:if>
        
        <c:choose>	
			  	<c:when test = "${roleType == 'V' && roleTracking > 0}"> <%-- zero value implies roletracking is disabled i.e. pix user and textboxes are enabled --%>  
			          	<td valign="top" align="center" class="${class1}" width="10px">
        <a  href=# style="padding:15px" class="tableRowLink noBackground" onclick="validatePageRtPrinter('<%=request.getContextPath()%>/deliverymessage/deliverymessagemilldetail.do?paperList=list&requestType=HIST&lineNo=${deliveryMessageLine.lineNo}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderPaper=${orderPaper}&fo=${fo}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}')"><c:out value="${deliveryMessageLine.lineNo}"/></a>
        <%--<html-el:link style="padding:15px" styleClass="tableRowLink noBackground" page="/deliverymessage/deliverymessagemilldetail.do?paperList=list&requestType=HIST&lineNo=${deliveryMessageLine.lineNo}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderPaper=${orderPaper}&fo=${fo}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}"><c:out value="${deliveryMessageLine.lineNo}"/></html-el:link>--%>
        </td>
        		</c:when>
				 		<c:otherwise>
			<td valign="top" align="center" class="${class1}" width="10px">
        <a  href=# style="padding:15px" class="tableRowLink noBackground" onclick="validatePage('<%=request.getContextPath()%>/deliverymessage/deliverymessagemilldetail.do?paperList=list&requestType=HIST&lineNo=${deliveryMessageLine.lineNo}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderPaper=${orderPaper}&fo=${fo}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}')"><c:out value="${deliveryMessageLine.lineNo}"/></a>
        <%--<html-el:link style="padding:15px" styleClass="tableRowLink noBackground" page="/deliverymessage/deliverymessagemilldetail.do?paperList=list&requestType=HIST&lineNo=${deliveryMessageLine.lineNo}&poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&orderPaper=${orderPaper}&fo=${fo}&PAGE_VALUE=${PAGE_VALUE}&page_order_list=${page_order_list}&pageFilter=${pageFilter}&ponoFilter=${ponoFilter}&isbnFilter=${isbnFilter}&printNoFilter=${printNoFilter}&statusFilter=${statusFilter}&startDateFilter=${startDateFilter}&endDateFilter=${endDateFilter}&sbBDateFilter=${sbBDateFilter}&ebBDateFilter=${ebBDateFilter}&orderPaper=${orderPaper}&orderType=${orderType}"><c:out value="${deliveryMessageLine.lineNo}"/></html-el:link>--%>
        </td>
						</c:otherwise>
			
        </c:choose>
        
        
        <input type="hidden" name="maxAmtAllow" value="${deliveryMessageLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
       
        
<%-- <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
<script>
        	alert('${deliveryMessageLine.lineNo}');
        </script>  --%>
		<input type="hidden" name="lineNo" value="${deliveryMessageLine.lineNo}" id="lineNo${indexId.index}"/>
        <td valign="top" class="${class1}" height="25px"><c:out value="${deliveryMessageLine.printer}"/></td>
        <td valign="top" class="${class1}" ><c:out value="${deliveryMessageLine.materialDesc}"/></td>
        <td valign="top" class="${class1}" width="12px" align="right"><fmt:formatDate value="${deliveryMessageLine.deliveryDate}" type="both" pattern="MM/dd/yyyy" />
        <td valign="top" class="${class1}" align="right"><fmt:formatNumber value="${deliveryMessageLine.requestedQuantity}" pattern="###,###"/>
        <td valign="top" class="${class1}" align="right"><fmt:formatNumber value="${deliveryMessageLine.postedQuantity}" pattern="###,###"/></td>
        <td valign="top" class="${class1}" align="right"><fmt:formatNumber value="${deliveryMessageLine.balanceQuantity}" pattern="###,###"/>
        <c:if test="${roleType == 'M' && viewOnly != '1'}">
	 
       <c:choose>	
			  	<c:when test = "${roleTracking == 0}"> <%-- zero value implies roletracking is disabled i.e. pix user and textboxes are enabled --%>  
			          	<c:choose> 
				 		<c:when test="${colorFlag == 1}">
				 			<td valign="top" class="${class1}" width="45px" align="right">
							<html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].deliveredQuantity" value="" styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none;visibility:hidden; " readonly="true"/> <%-- visibility:hidden; --%>		
								</td>     
				 		</c:when>
				 		<c:otherwise>
				 			<td valign="top" class="${class1}" width="45px" align="right">
		                     <html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].deliveredQuantity" size="12" styleId="dmQuantity${indexId.index}" styleClass= "textfield"  maxlength="9" onkeypress="return numbersonlyNewDM('${indexId.index}')" />  
							</td>
						</c:otherwise>
						</c:choose>
	         	</c:when>	<%-- end roletracking when --%> 
	          	<c:otherwise>
	          	   	
	          	   	<td valign="top" class="${class1}" width="45px" align="right">
							<html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].deliveredQuantity" value="" styleClass= "textfield" maxlength="9" style="text-align:right;width:0pt;border:none;visibility:hidden; " readonly="true"/> <%-- visibility:hidden; --%>		
								</td>
	          	   	
	          	</c:otherwise>
        </c:choose>
             	
		</c:if>
		<c:set var="productCode" value="${deliveryMessageLine.productCode}"></c:set>	
	
		<c:choose>	
	<%-- 	  	<c:when test = "${(roleType == 'M' || roleType == 'V' || roleType == 'B') && (colorFlag != 1 && viewOnly != '1')}">  --%>  
		<c:when test = "${(roleType=='M' || roleType=='V') && (colorFlag != 1 && viewOnly != '1')}">  
		<script>
		</script>
          		<c:if test="${roleType == 'M'}" >
	          		<td valign="middle" class="${class1}" align="left">
	       				<fileUploading:DMFileSubFolder  id="subfolderName" lineNo="${deliveryMessageLine.lineNo}"/>
	       				&nbsp;
	       				<a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsDM&lineNo=${deliveryMessageLine.lineNo}&pono=${pono}&upload=success&index=${indexId.index}&poversion=${poversion}&subfolderName=${subfolderName}&productCode=${productCode}&roleType=${roleType}','DownLoadFile','scrollbars=yes,width=800,height=600,top=0')" class="subLinksMain">
	       				<img id="bola${indexId.index}" src="../images/upload-image.gif" alt="Upload File" width="10" height="15" class="borderNone"><img id="bolb${indexId.index}" src="../images/upload-image-green.gif" width="15" height="15" style="display:none;" alt="File Uploaded" class="borderNone"></a>
	         		</td> 
         		</c:if>
         		<c:if test="${roleType == 'V' && roleTracking > 0}" >
	         		<td valign="middle" class="${class1}" align="left">
	       				<fileUploading:DMFileSubFolder  id="subfolderName" lineNo="${deliveryMessageLine.lineNo}"/>
	       				&nbsp;
	       				<a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsDM&lineNo=${deliveryMessageLine.lineNo}&pono=${pono}&upload=success&index=${indexId.index}&poversion=${poversion}&subfolderName=${subfolderName}&productCode=${productCode}&roleType=${roleType}','DownLoadFile','scrollbars=yes,width=800,height=600,top=0')" class="subLinksMain">
	       				<img id="bola${indexId.index}" src="../images/upload-image.gif" alt="Upload File" width="10" height="15" class="borderNone"><img id="bolb${indexId.index}" src="../images/upload-image-green.gif" width="15" height="15" style="display:none;" alt="File Uploaded" class="borderNone"></a>
	         		</td>
         		</c:if>
          		<c:if test="${roleType == 'V' && roleTracking > 0 && (colorFlag != 0 && colorFlag != 2)}" >
         		<td valign="middle" class="${class1}" align="left">
						&nbsp;
					</td> 
         		</c:if>
         		
         	</c:when>
          	<%--<c:when test = "${(orderPaper == 'O' && roleType == 'B')|| (roleType == 'M' && (colorFlag == 1 || accessFlag == 'R'))}"> --%>
          	<c:otherwise>
          	    <c:if test="${roleType != 'V'}" >  <%-- --%>
          		<td valign="middle" class="${class1}" align="left">
       				<fileUploading:DMFileSubFolder id="subfolderName" lineNo="${deliveryMessageLine.lineNo}"/>
       				&nbsp;
       				<a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsDM&lineNo=${deliveryMessageLine.lineNo}&pono=${pono}&upload=success&index=${indexId.index}&poversion=${poversion}&subfolderName=${subfolderName}&flag=download&productCode=${productCode}&roleType=${roleType}','DownLoadFile','scrollbars=yes,width=700,height=300,top=0')" class="subLinksMain">
       				<img src="../images/icon_download-1.gif" alt="Download File" width="10"  height="15" class="borderNone"></a>
         		</td> 
         		</c:if>
         		<c:if test="${roleType == 'V' && roleTracking > 0}" >
         		<td valign="middle" class="${class1}" align="left">
						&nbsp;
					</td> 
         		</c:if>
         		
         		
			<%--	<c:if test="${roleType == 'V' && colorFlag == 1}" >
					<td valign="middle" class="${class1}" align="left">
						&nbsp;
					</td> 
				</c:if>  --%>
				        	    
         	</c:otherwise>
         	<%--</c:when>--%>
        </c:choose>
        
		<input type="hidden" name="parentFileCheck" id="hid${indexId.index}"/> 
         </tr>
       
         </c:forEach>
         </c:if> 
          <c:if test="${((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) <= 0)}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage">Currently there are no line items to display</td>   
           </tr>
         </c:if>
         <tr>
          <td height="1" colspan="13" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
        <div id="buttons2" class="tabSelectText"></div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
         <tr>
          <td height="10">&nbsp;</td>
        </tr>
        <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td height="15" align="left">
						<span class="tablelegendHigh" >&nbsp;</span>
						<label class="legendLbl">Less than 90% posted</label>
						<span class="tablelegendMedium">&nbsp;</span>
						<label class="legendLbl">90-99% posted</label>
						<span class="tablelegendLow">&nbsp;</span>
						<label class="legendLbl">100% posted</label>
			</td>
		</tr>
        <tr>
           <td height="20">&nbsp;</td>
        </tr>
        <tr>
           <td id="buttons3" class="tabSelectTextleft">
           <input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="statusFilter" value="${statusFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        	<input type="hidden" name="endDateFilter" value="${endDateFilter}"/> 
        	<input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         	<input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
             <input type="hidden" id="ajaxCall"/>
             <input type="hidden" name="request_time" value="<%=System.currentTimeMillis()%>"/>
             
             
             
            <input type="hidden"  id="fileSize"/>
            <c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection))>0) && (roleType =='M' && viewOnly != '1')}"> 
            	
            	<c:choose>	
			  	<c:when test = "${roleTracking == 0}"> <%-- zero value implies roletracking is disabled i.e. pix user and textboxes are enabled --%>  
	          		<input name="Button1" type="button" id="submitB" class="buttonMain" onClick="javascript:validateBOLUploading(${totalLineItems},'<%=request.getContextPath()%>','<%=request.getContextPath()%>/deliverymessage/insertnewdeliverymessage.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}');" value="Save">
	         	</c:when>
	          	<c:otherwise>
	          	   	
	          	</c:otherwise>
            </c:choose>
            	
            	
     		
     		</c:if>
     		 <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=deliveryMessageNewMill&poid=${poid}&poversion=${poversion}')" value="Export to Excel">
           </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        </table>
</td>
</tr>  
      
</html-el:form>
     
