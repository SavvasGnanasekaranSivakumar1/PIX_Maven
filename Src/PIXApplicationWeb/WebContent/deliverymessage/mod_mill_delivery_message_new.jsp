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
<c:set var="poid"><%=request.getParameter("poid")%></c:set>
<c:set var="poversion"><%=request.getParameter("poversion")%></c:set>
<c:set var="pono"><%=request.getParameter("pono")%></c:set>
<c:set var="rno"><%=request.getParameter("rno")%></c:set>
<c:set var="order"><%=request.getParameter("order")%></c:set>
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
      
        }
 
 
</script>
<c:set var="orderPaper" value='<%=request.getParameter("orderPaper")%>'/>
<div id="page">
<tr>
    <td height="25" align="left" valign="top">
    <img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">Purchase Order No.  <c:out value="${deliveryMessageForm.deliveryMessage.poNo}"/>
    <c:if test="${deliveryMessageForm.deliveryMessage.releaseNo!=null && deliveryMessageForm.deliveryMessage.releaseNo!='0'}">
    - ${deliveryMessageForm.deliveryMessage.releaseNo}
    </c:if></span></td>
  </tr>
  <tr>
<c:forEach var="lineItem" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>
   <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
  			
  			<c:if test="${message!=null}">
  				<font color="#CC0000" size="2" face="Verdana">
    			<span >${message}</span>
  			</c:if>	
  		</tr>
      <td>
        <div id="error_div" class="errorMessageText"></div>
      </td>
      
      <tr>
        <td height="15" colspan="2" class="text">Fill in the following details to generate a <span class="textBold">New Delivery Message </span>. You shall be notified the new delivery message number through an email.</td>
      </tr>
      
            <tr>
        <td height="0" colspan="2"></td>
        </tr>
      <tr>
        <td width="80%" height="22"><table height="22" border="0" cellpadding="0" cellspacing="0">
         <c:set var="pageModule" value="deliverymessage" scope="request"></c:set>
         <%@ include file="/common/mod_tabs.jsp"%>
        </table></td>
        <td align="right" valign="bottom">&nbsp;</td>
      </tr>
      <tr>
        <td height="1" colspan="2" class="tableLine"><img src="<%=request.getContextPath()%>/images/trans.gif" width="1"height="1"></td>
        </tr>
    </table>
      <table width="60%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="5" valign="bottom" class="headingMain">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" valign="bottom" class="headingMain">
          <table width="100%" border="0" cellspacing="1" cellpadding="0">
              <tr>
               <html-el:form action="/deliverymessage/insertnewdeliverymessage">
               
    
               
               <c:set var="errorMsg" value='<%=request.getAttribute(PIXConstants.PIX_ERROR)%>' />
               <%@ include file="/common/formbegin.jsp"%>
               <c:set var="readOnlyForm" value="F"/>
              <c:choose> 
               <c:when test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
                <td width="35%" class="blueColumn">MESSAGE TYPE: </td>
               
                <td class="lightblueColumn">
	                <html-el:select styleClass="textfield" property="deliveryMessage.msgTypeDetail">
	                <html-el:option value="">Select</html-el:option>
	                <c:forEach var="item" items="${MessageTypeDetail}" varStatus="indexId">
	                <html-el:option value="${item.msg_type_id}"><c:out value="${item.msg_type}"/></html-el:option>
	                </c:forEach>
	             	</html-el:select>
             	</td> 
              </c:when>
              <c:otherwise>
             	<td align="center" valign="middle" height="30px" class="noRecordsMessage">Currently there are no line items to post delivery message</font></h3></td>   
	          </c:otherwise>
             
             </c:choose>
             
         </tr>
          </table>
        </tr>
        
       
        </table>
      <table width="98%" border="0" cellspacing="1" cellpadding="0">
     	
		<c:if test="${errorMsg == null && ((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}">
        <tr>
          <td width="2%" class="tableHeading">&nbsp;</td>
          <td class="tableHeading" width="8%">MATERIAL NUMBER</td>
          <td class="tableHeading" width="12%">MATERIAL DESCRIPTION</td>
          <td class="tableHeading" width="6%">ORIGINAL QTY</td>
          <td class="tableHeading" width="6%">TOTAL DELIVERED</td>
          <td class="tableHeading" width="6%" >QUANTITY</td>
          <td class="tableHeading" width="5%">REQUESTED DELIVERY DATE </td>
          <td class="tableHeading" width="9%">SHIP DATE </td>
          <td class="tableHeading" width="23%">SHIP TO </td>
          <td class="tableHeading" width="7%">BILL OF LADING</td>
        </tr>
        <tr>
        <c:forEach var="deliveryMessageLine" items="${deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection}" varStatus="indexId">     
        <c:set var="lineNo" value="${deliveryMessageLine.lineNo}"/>
        <c:set var="linePartyCollection" value="${deliveryMessageLine.linePartyCollection}"/>
        <c:set var="linePartyItemObj" />
       <c:set var="linePartyContactItemObj" />

       <c:forEach var="linePartyItem" items="${linePartyCollection}" varStatus="linePartyInfo">
        <c:set var="linePartyItemObj" value="${linePartyItem}"/>
        <html-el:hidden property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].linePartyCollection[${linePartyInfo.index}].san" value="${linePartyItemObj.san}" />
       <c:forEach var="linePartyContactItem" items="${linePartyItem.linePartyContactCollection}" varStatus="linePartyContactInfo">
        <c:if test="${(linePartyContactItem.poLineNo ==linePartyItem.poLineNo)&&(linePartyItem.poLineNo==lineNo)}">
        <c:set var="linePartyContactItemObj" value="${linePartyContactItem}"/>
       </c:if>
      </c:forEach>
      </c:forEach>
        
        
        <c:if test="${lineNo%2 != 0}" >
        <c:set var="class1" value="tableRow"/>
        </c:if>
        <c:if test="${lineNo%2 == 0}" >
        <c:set var="class1" value="tableAlternateRow"/>
        </c:if>
        <%--${deliveryMessageLine.maxToleranceVal}--%>
        <input type="hidden" name="maxAmtAllow" value="${deliveryMessageLine.maxToleranceVal}" id="maxAmtAllow${indexId.index}"/>
        
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.lineNo}"/></td>
        <td valign="top" class="${class1}"><c:out value="${deliveryMessageLine.productCode}"/></td>
        <td valign="top" class="${class1}" width="12%"><c:out value="${deliveryMessageLine.lineDecription}"/></td>
        <td valign="top" class="${class1}" width="6%" ><c:out value="${deliveryMessageLine.balanceQuantity}"/></td>
        <td valign="top" class="${class1}" width="6%" ><c:out value="${deliveryMessageLine.postedQuantity}"/><input type="hidden" name="postedQuan" value="${deliveryMessageLine.postedQuantity}" id="postedQuantity${indexId.index}"/></td>
        <td valign="top" class="${class1}" width="6%">
        <html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].deliveredQuantity" size="10" styleId="dmQuantity${indexId.index}" styleClass= "textfield"  maxlength="9" onkeypress="return numbersonlyNewDM('${indexId.index}')"/>
        <%-- <html-el:text  property="quantity" styleClass="textfield"/></td>  --%>
        <td valign="top" class="${class1}" width="5%"><c:out value="${deliveryMessageLine.requestedDeliveryDate}"/></td>
        <td valign="top" class="${class1}" width="9%">
        <html-el:text property="deliveryMessage.deliveryMsgLineCollection[${indexId.index}].estimatedDeliveryDate" size="12" styleClass= "textfield" styleId="line_del_date_${indexId.index}" maxlength="10" readonly="true"/>
        <a href="javascript:NewCal('line_del_date_${indexId.index}','MMDDYYYY')"> 
        <img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td> 
        
        <td valign="top" class="${class1}" width="23%">
        <table width="100%" height="50%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="25%" valign="top" class="${class1}" bgcolor="#F8FBFE">
            	<div id="a_title${indexId.index}"  class="lTitle" >
            		<img id="a_img${indexId.index}" onClick="sDetails(${indexId.index},'<%=request.getContextPath()%>')"  src="<%=request.getContextPath()%>/images/expand.gif" alt="Collapse" title="Click to Expand">
            		 <c:if test="${linePartyItemObj!=''}">
            		${linePartyItemObj.name1} (${linePartyItemObj.san})
            		<c:if test="${linePartyItemObj.orgUnitCode!=null}">(${linePartyItemObj.orgUnitCode})</c:if>
            		 </c:if>
            	</div>
            	
                <div id="a_hContent${indexId.index}"  class="hide" style="margin-left:2px; margin-top:5px;">
                	 <c:if test="${linePartyItemObj!=''}">
                	${linePartyItemObj.name2} ${linePartyItemObj.name3}
                    ${linePartyItemObj.address1} ${linePartyItemObj.address2}
                   ${linePartyItemObj.address3} ${linePartyItemObj.address4}
                    ${linePartyItemObj.city} ${linePartyItemObj.postalCode} 
                    ${linePartyItemObj.state} ${linePartyItemObj.countryDetail.countryName}
                     </c:if>
                     <c:if test="${linePartyContactItemObj!=''}">
                     ${linePartyContactItemObj.contactFirstName} ${linePartyContactItemObj.contactLastName}
                     <c:if test="${linePartyContactItemObj.phone!=null}">${linePartyContactItemObj.phone} (Business)</c:if>
                     <c:if test="${linePartyContactItemObj.mobile!=null}">${linePartyContactItemObj.mobile} (Mob)</c:if>
                     <c:if test="${linePartyContactItemObj.fax!=null}">${linePartyContactItemObj.fax} (Fax)</c:if>
                      <c:if test="${linePartyContactItemObj.email!=null}">${linePartyContactItemObj.email} </c:if>
                      </c:if>
            	</div>
            </td>
          </tr>
        </table>
       </td>
       <input type="hidden" name="parentFileCheck" id="hid${indexId.index}"/> 
       <td valign="top" class="${class1}" width="7%">
       <fileUploading:DMFileSubFolder id="subfolderName" lineNo="${deliveryMessageLine.lineNo}"/>
       <%-- /purchaseorder/uploadbolloadfile MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/downloadfile1.do?lineNo=${deliveryMessageLine.lineNo}&pono=${pono}&upload=success&index=${indexId.index}&poversion=${poversion}&subfolderName=${subfolderName}','DownLoadFile','scrollbars=yes,width=800,height=410,top=0')--%>
        <a href="#" id="upload${indexId.index}"  onClick="MM_openBrWindowUpload('<%=request.getContextPath()%>/purchaseorder/uploadbolloadfile.do?lineNo=${deliveryMessageLine.lineNo}&pono=${pono}&upload=success&index=${indexId.index}&poversion=${poversion}&subfolderName=${subfolderName}','DownLoadFile','scrollbars=yes,width=800,height=410,top=0')" class="subLinksMain"><c:out value="upload files"/></a>
      
     </td>
         </tr>
         
         </c:forEach>
         </c:if> 
          <c:if test="${errorMsg != null}">
           <tr> 
           <td align="center" valign="middle" height="30px" class="noRecordsMessage"><c:out value="${errorMsg}"/></font></h3></td>   
           </tr>
         </c:if>
         <tr>
          <td height="1" colspan="10" class="tableLine">
          <img src="<%=request.getContextPath()%>/images/trans.gif" width="1" height="1"></td>
        </tr>
      </table>
        <div id="buttons2" class="tabSelectText"></div>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td height="15" align="right">&nbsp;</td>
        </tr>
        <tr>
          <td>
          <label>
          <table cellpadding="0" cellspacing="0">
          <tr>
          <td id="buttons3" class="tabSelectTextleft">
<%
if(PIXUtil.checkVendorWriteAccess(request,PIXConstants.DELMSG_CODE)&& "P".equals(request.getParameter("order"))){
%>         
			<input type="hidden" name="pageFilter" value="${pageFilter}"/> 
        	<input type="hidden" name="ponoFilter" value="${ponoFilter}"/> 
        	<input type="hidden" name="isbnFilter" value="${isbnFilter}"/> 
        	<input type="hidden" name="printNoFilter" value="${printNoFilter}"/>
        	<input type="hidden" name="statusFilter" value="${statusFilter}"/>
        	<input type="hidden" name="startDateFilter" value="${startDateFilter}"/>
        	<input type="hidden" name="endDateFilter" value="${endDateFilter}"/> 
        	<input type="hidden" name="sbBDateFilter" value="${sbBDateFilter}"/>
         	<input type="hidden" name="ebBDateFilter" value="${ebBDateFilter}"/>
         <c:if test="${((fn:length(deliveryMessageForm.deliveryMessage.deliveryMsgLineCollection)) > 0)}"> 
         <%-- if(validateBOLUploading(${totalLineItems},'<%=request.getContextPath()%>')){submitDelmsgAction('<%=request.getContextPath()%>/deliverymessage/insertnewdeliverymessage.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}',this)}else{return false;} --%>
         	<input name="Button1" type="button" id="submitB" class="buttonMain" onClick="javascript:validateBOLUploading(${totalLineItems},'<%=request.getContextPath()%>','<%=request.getContextPath()%>/deliverymessage/insertnewdeliverymessage.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&page_order_list=${page_order_list}');" value="Submit">
         </c:if>
         
            
            
 <%}%>            
            
         <input type="hidden"  id="ajaxCall"/>
         <input type="hidden"  id="fileSize"/>
         <%--   <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/deliverymessage/deliverymessagelist.do?poid=${poid}&poversion=${poversion}&pono=${pono}&rno=${rno}&order=${order}&PAGE_VALUE=1')" value="Cancel"> --%>
         
          <input name="Button2" type="button" class="buttonMain" onClick="submitCancelDelivery('<%=request.getContextPath()%>/purchaseorder/millpurchaseorderlist.do?PAGE_VALUE=${page_order_list}&party=M')" value="Close">
             </td>
            </tr>
            </table>
            </label>
            </td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
        
        </html-el:form>
      </table>
  </tr>
  </div>