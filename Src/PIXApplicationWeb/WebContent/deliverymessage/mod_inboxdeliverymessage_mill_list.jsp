<%@ taglib prefix="logic-el" uri="/WEB-INF/struts-logic-el.tld" %> 
<%@ taglib prefix="bean-el" uri="/WEB-INF/struts-bean-el.tld" %>
<%@ taglib prefix="html-el" uri="/WEB-INF/struts-html-el.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ taglib uri="/WEB-INF/fileUploading.tld" prefix="fileUploading"%>
<%@ page import="com.pearson.pix.presentation.deliverymessage.action.DeliveryMessageForm"%>
<%@ page import="com.pearson.pix.business.common.PIXConstants"%>
<%@ page import="com.pearson.pix.business.common.PIXUtil"%>
<%@ page errorPage="/common/mod_error.jsp" isErrorPage="false" %>

<html-el:form action="/deliverymessage/deliverymessagemilldetail"> 
 <%@ include file="/common/formbegin.jsp"%>

<c:set var="PAGE_VALUE"><%=request.getParameter("PAGE_VALUE")%></c:set>
<c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
<c:set var="roleType" value='${USER_INFO.roleTypeDetail.roleType}' />
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
<c:set var="ponoFilter" value='<%=request.getParameter("ponoFilter")%>' />

<%-- <c:set var="productCode" value="${productCode}"></c:set> --%>
<c:set var="productCode"><%=request.getParameter("productCode")%></c:set>

<script>
       var httpRequest;
        function getFileDownload(lineno,pono,id){
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
      
        }
</script>

<tr>
    <%--<td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.</span></td> --%>
    
</tr> 
<tr>
<c:forEach var="delMsg" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>
   <td align="left" valign="top" class="padding23">
   	  <input type="hidden" name="totalLineItems1" id="totalLineItems1" value ="${totalLineItems}"/>
   	  
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <TR><td height="5">&nbsp;</td></TR>
      <tr> <td height="15" colspan="2" class="text">Details of the <span class="textBold">Delivery Message </span>posted in Last Five Days:</td></tr>
      <tr>
      <td height="5">&nbsp;</td>
      </tr>
      
      <tr>
         <td width="50%" height="22"><table border="0" cellspacing="0" cellpadding="0">
        	<tr>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_lt_green.gif" width="10" height="25"></td>
            <td width="180" align="center" valign="middle" class="tabBg">Delivery Message History </td>
            <td width="10"><img src="<%=request.getContextPath()%>/images/tab_rt_green.gif" width="10" height="25"></td>
           </tr> </table>
         </td>
         <td height="4" colspan="2"></td>
      </tr>  
      <tr>
      <td height="4" colspan="2"></td>
      </tr>
      <tr>
        <td height="1" colspan="13" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
      </tr>
     </table>    
     <table width="98%" border="0" cellspacing="1" cellpadding="0">
     
      	<c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
        <tr>
          
          <td class="tableHeading">DELIVERY MESSAGE#</td>
          <td class="tableHeading">PO LINE#</td>		  
		  <td class="tableHeading">PRINTER </td>		
		  <td class="tableHeading">L4 MATERIAL DESC</td>
		  <td class="tableHeading">REQUESTED DEL DATE</td>
		  <td class="tableHeading">DM POSTED DATE</td>		
          <td class="tableHeading">REQ QTY</td>          
          <td class="tableHeading">TOTAL DEL QTY </td>
		  <td class="tableHeading">DM POSTED </td>
		  <td class="tableHeading">GR QTY</td>		  
          <%--<td class="tableHeading">APP FLAG </td>--%>
          <td class="tableHeading">DM POSTED BY  </td>
          <td class="tableHeading" width="20px">DOWNLOAD BOL</td>
        </tr>
        
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">
        <tr>     
         <c:set var="lineNo1" value="${deliveryMessageLine.lineNo}"/>
         <c:if test="${indexId.index%2 != 0}" >
         <c:set var="class1" value="tableRow"/>
         </c:if>
         <c:if test="${indexId.index%2 == 0}" >
         <c:set var="class1" value="tableAlternateRow"/>
         </c:if>
        
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.deliveryMsgNo}"/><c:if test="${deliveryMessageLine.approvalFlag == 'Yes'}">&nbsp;<IMG src="<%=request.getContextPath()%>/images/tick-rfs1.gif" height = "12px" width="12px"/></c:if><c:if test="${deliveryMessageLine.postedQuantity== 0}">&nbsp;<IMG src="<%=request.getContextPath()%>/images/delete_rfs.gif"/></c:if></td>
        <input type="hidden" name="maxAmtAllow" value="${deliveryMessageLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
        <input type="hidden" name="lineNumber" value="${deliveryMessageLine.lineNo}" id="lineNumber${indexId.index}"/>
        <input type="hidden" name="minAmtAllow" value="${deliveryMessageLine.receivedQuantity}" id = "minAmtAllow${indexId.index}"/>
        <input type="hidden" name="postedQuantity" value="${deliveryMessageLine.postedQuantity}" id = "postedQuantity${indexId.index}"/>
        <input type="hidden" name="zeroDM" value="${deliveryMessageLine.deliveryMsgNo}" id = "zeroDM${indexId.index}"/>
        <td valign="top" class="${class1}" width="40px">&nbsp;<c:out value="${deliveryMessageLine.lineNo}"/></td>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.printer}"/></td>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.materialDesc}"/></td>
        <td valign="top" class="${class1}" width="12px" align="right"><fmt:formatDate value="${deliveryMessageLine.deliveryDate}" type="both" pattern="MM/dd/yyyy" /></td>
        <td valign="top" class="${class1}" width="12px" align="right"><fmt:formatDate value="${deliveryMessageLine.postedDate}" type="both" pattern="MM/dd/yyyy" /></td>
        <td valign="top" class="${class1}" width="60px" align="right"><fmt:formatNumber value="${deliveryMessageLine.requestedQuantity}" pattern="###,###"/></td>
        <td valign="top" class="${class1}" width="60px" align="right"><fmt:formatNumber value="${deliveryMessageLine.totalDeliveredQuantity}" pattern="###,###"/></td>
        <td valign="top" class="${class1}" width="60px" align="right"><fmt:formatNumber value="${deliveryMessageLine.postedQuantity}" pattern="###,###"/></td>  
        <td valign="top" class="${class1}" width="50px" align="right"><fmt:formatNumber value="${deliveryMessageLine.receivedQuantity}" pattern="###,###"/></td>  
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.postedBy}"/></td>      
        <td valign="middle" class="${class1}" align="left">
        <input type="hidden" name = "pono" value = "${pono}">
        
   	   				<fileUploading:DMFileSubFolder id="subfolderName" lineNo="${deliveryMessageLine.lineNo}" msgId="${deliveryMessageLine.msgId}" />
       				&nbsp;
       				<!-- Added by vishal kumar sinha for tracker:475986- Blank Screen is getting displayed on clicking Upload BOL icon for Inbox items in PIX  : Start-->
       				<%-- <a href="javascript:void(0);" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsDM&lineNo=${deliveryMessageLine.lineNo}&pono=${deliveryMessageLine.poNo}&upload=success&index=${indexId.index}&subfolderName=${subfolderName}&flag=download &productCode=${productCode}&roleType=${roleType}','DownLoadFile','scrollbars=yes,width=700,height=300,top=0')" class="subLinksMain">--%>
       				 <%--<a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsDM&lineNo=${deliveryMessageLine.lineNo}&pono=${deliveryMessageLine.poNo}&upload=success&index=${indexId.index}&poversion=${deliveryMessageLine.poVersion}&subfolderName=${subfolderName}&msgId=${deliveryMessageLine.msgId}&flag=download&approvalFlag=${deliveryMessageLine.approvalFlag}&productCode=${deliveryMessageLine.productCode}','DownLoadFile','scrollbars=yes,width=800,height=600,top=0')" class="subLinksMain">--%>
       				 <%--<a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsHistory&historyFlag=DMHistory&lineNo=${deliveryMessageLine.lineNo}&pono=${deliveryMessageLine.poNo}&upload=success&index=${indexId.index}&poversion=${deliveryMessageLine.poVersion}&subfolderName=${subfolderName}&msgId=${deliveryMessageLine.msgId}&flag=download&approvalFlag=${deliveryMessageLine.approvalFlag}&productCode=${deliveryMessageLine.productCode}','DownLoadFile','scrollbars=yes,width=700,height=600,top=0')" class="subLinksMain">--%>
       				 <a href="#" id="upload${indexId.index}" style="padding:8px;" onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?operation=rollDetailsHistory&historyFlag=DMHistory&lineNo=${deliveryMessageLine.lineNo}&pono=${deliveryMessageLine.poNo}&upload=success&index=${indexId.index}&poversion=${deliveryMessageLine.poVersion}&subfolderName=${subfolderName}&msgId=${deliveryMessageLine.msgId}&approvalFlag=${deliveryMessageLine.approvalFlag}&productCode=${deliveryMessageLine.productCode}','DownLoadFile','scrollbars=yes,width=800,height=600,top=0')" class="subLinksMain">
       																											                             
       				
       				<!-- Added by vishal kumar sinha for tracker:475986- Blank Screen is getting displayed on clicking Upload BOL icon for Inbox items in PIX  : End-->
       				<img src="../images/icon_download-1.gif" alt="Download File" width="10" height="15" class="borderNone"></a>
         		</td> 
         		
         </tr>
         </c:forEach>
         </c:if> 
          <c:if test="${((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) <= 0)}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage">Currently there are no Delivery Message to display.</td>   
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
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
           <td height="20">&nbsp;</td>
        </tr>
        
        <tr>
           <td id="buttons3" class="tabSelectTextleft">
             <input type="hidden" id="ajaxCall"/>
            <input type="hidden" id="fileSize"/>
            <input name="Button2" type="button" id="closeB" class="buttonMain" onClick="submitCancelDelivery1('<%=request.getContextPath()%>/home/home.do')" value="Close">
     		<input name="Button3" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/exporttoexcel/generateexcel.do?flag=inboxDMHistory')" value="Export to Excel">
           </td>
        </tr>
           <tr>
          <td height="35" colspan="2">&nbsp;</td>
          </tr>
       <tr>
       <td colspan="2" class="text">Indicators shown along with DELIVERY MESSAGE# field: </td>
       </tr>
       <tr>
        <td colspan="2" > 
        			 <IMG src="<%=request.getContextPath()%>/images/tick-rfs1.gif" height = "12px" width="12px"/><font class="text" style="color:green;"> - Approved Delivery Message.</font> <br>
        			 <IMG src="<%=request.getContextPath()%>/images/delete_rfs.gif"/><font class="text" style="color:red;"> - Cancelled Delivery Message.</font> <br>

 					
        </td>
         </tr>
        
        </table>
</html-el:form>